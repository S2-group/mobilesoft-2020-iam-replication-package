package com.jykplugin.notification;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import com.unity3d.player.UnityPlayer;
import java.util.Calendar;
import java.util.List;

public class AndroidNotificator
  extends BroadcastReceiver
{
  public AndroidNotificator() {}
  
  public static void ClearNotification(int paramInt)
  {
    ((NotificationManager)UnityPlayer.currentActivity.getApplicationContext().getSystemService("notification")).cancelAll();
    AlarmManager localAlarmManager = (AlarmManager)UnityPlayer.currentActivity.getSystemService("alarm");
    if (localAlarmManager != null)
    {
      Object localObject = new Intent("UNITY_NOTIFICATOR");
      ((Intent)localObject).setClass(UnityPlayer.currentActivity, AndroidNotificator.class);
      ((Intent)localObject).setData(Uri.parse("tel:" + paramInt));
      localObject = PendingIntent.getBroadcast(UnityPlayer.currentActivity, paramInt, (Intent)localObject, 536870912);
      if (localObject != null) {
        localAlarmManager.cancel((PendingIntent)localObject);
      }
    }
  }
  
  public static String[] GetPackageNameList()
  {
    UnityPlayer.currentActivity.getApplicationContext();
    List localList = UnityPlayer.currentActivity.getApplicationContext().getPackageManager().getInstalledPackages(0);
    String[] arrayOfString = new String[localList.size()];
    int i = 0;
    for (;;)
    {
      if (i >= localList.size()) {
        return arrayOfString;
      }
      arrayOfString[i] = ((PackageInfo)localList.get(i)).packageName;
      i += 1;
    }
  }
  
  public static boolean PackageInstalled(String paramString)
  {
    UnityPlayer.currentActivity.getApplicationContext();
    PackageManager localPackageManager = UnityPlayer.currentActivity.getApplicationContext().getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 64);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public static void ShowNotification(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2, boolean paramBoolean)
    throws IllegalArgumentException
  {
    if (paramInt2 < 0) {
      throw new IllegalArgumentException("The param: pDelaySecond < 0");
    }
    Activity localActivity = UnityPlayer.currentActivity;
    Intent localIntent = new Intent("UNITY_NOTIFICATOR");
    localIntent.putExtra("appname", paramString1);
    localIntent.putExtra("title", paramString2);
    localIntent.putExtra("content", paramString3);
    localIntent.putExtra("id", paramInt1);
    localIntent.setClass(UnityPlayer.currentActivity, AndroidNotificator.class);
    localIntent.setData(Uri.parse("tel:" + paramInt1));
    localIntent.putExtra("activity", "com.jykplugin.googleiab.AndroidAgent");
    paramString1 = PendingIntent.getBroadcast(localActivity, paramInt1, localIntent, 0);
    paramString2 = (AlarmManager)localActivity.getSystemService("alarm");
    paramString3 = Calendar.getInstance();
    paramString3.add(13, paramInt2);
    long l = paramString3.getTimeInMillis();
    if (paramBoolean)
    {
      paramString2.setRepeating(0, l, 86400000L, paramString1);
      return;
    }
    paramString2.set(0, l, paramString1);
  }
  
  public void createWeidgt() {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((paramIntent == null) || (!paramIntent.getAction().equals("UNITY_NOTIFICATOR"))) {
      return;
    }
    Object localObject2 = paramIntent.getStringExtra("activity");
    Object localObject1 = null;
    try
    {
      localObject2 = Class.forName((String)localObject2);
      localObject1 = localObject2;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        try
        {
          localObject1 = ((PackageManager)localObject1).getApplicationInfo(paramContext.getPackageName(), 128);
          paramIntent = paramIntent.getExtras();
          localObject2 = PendingIntent.getActivity(paramContext, 0, (Intent)localObject2, 0);
          Bitmap localBitmap = BitmapFactory.decodeResource(paramContext.getResources(), ((ApplicationInfo)localObject1).icon);
          localObject1 = new Notification.Builder(paramContext).setContentTitle((String)paramIntent.get("title")).setContentText((String)paramIntent.get("content")).setSmallIcon(((ApplicationInfo)localObject1).icon).setTicker((String)paramIntent.get("appname")).setWhen(System.currentTimeMillis()).setContentIntent((PendingIntent)localObject2).setLargeIcon(localBitmap).build();
          ((Notification)localObject1).flags = 16;
          ((NotificationManager)paramContext.getSystemService("notification")).notify(paramIntent.getInt("id"), (Notification)localObject1);
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
        }
        localClassNotFoundException = localClassNotFoundException;
        localClassNotFoundException.printStackTrace();
      }
    }
    localObject2 = new Intent(paramContext, (Class)localObject1);
    localObject1 = paramContext.getPackageManager();
  }
}
