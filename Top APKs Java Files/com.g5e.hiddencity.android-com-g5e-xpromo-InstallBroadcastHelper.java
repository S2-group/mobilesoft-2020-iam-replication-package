package com.g5e.xpromo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Log;
import com.a.ac;
import com.adjust.sdk.ar;
import java.util.Iterator;
import java.util.List;

public class InstallBroadcastHelper
  extends BroadcastReceiver
{
  public InstallBroadcastHelper() {}
  
  static void applist(Context paramContext, long paramLong)
  {
    new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER");
    paramContext = paramContext.getPackageManager();
    Iterator localIterator = paramContext.getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      String str = localApplicationInfo.packageName;
      if (((localApplicationInfo.flags & 0x1) == 0) && (!str.startsWith("com.google.")) && (!str.startsWith("com.amazon."))) {
        applist_entry(paramLong, str, paramContext.getApplicationLabel(localApplicationInfo).toString());
      }
    }
  }
  
  static native void applist_entry(long paramLong, String paramString1, String paramString2);
  
  private void onReceiveInstall(Context paramContext, Intent paramIntent)
  {
    Class[] arrayOfClass = new Class[2];
    arrayOfClass[0] = ac.class;
    arrayOfClass[1] = ar.class;
    int j = arrayOfClass.length;
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        Class localClass = arrayOfClass[i];
        try
        {
          ((BroadcastReceiver)localClass.newInstance()).onReceive(paramContext, paramIntent);
          i += 1;
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            localThrowable.printStackTrace();
          }
        }
      }
    }
  }
  
  private void onReceiveUninstall(Context paramContext, Intent paramIntent)
  {
    if (((!paramIntent.getData().toString().startsWith("package:com.g5e.")) && (!paramIntent.getData().toString().startsWith("package:air.com.g5e."))) || (!paramIntent.getExtras().getBoolean("android.intent.extra.DATA_REMOVED")) || (paramIntent.getExtras().getBoolean("android.intent.extra.REPLACING"))) {}
    ResolveInfo localResolveInfo;
    do
    {
      return;
      localObject = paramContext.getPackageManager().queryBroadcastReceivers(new Intent("android.intent.action.PACKAGE_REMOVED", Uri.parse("package:")), 0).iterator();
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      } while (!getClass().getName().equals(localResolveInfo.activityInfo.name));
    } while (!paramContext.getPackageName().equals(localResolveInfo.activityInfo.packageName));
    Log.d("xpromo", paramIntent.toString());
    Object localObject = (Intent)paramIntent.clone();
    ((Intent)localObject).setClass(paramContext, IsolatedHelperService.class);
    paramContext.startService((Intent)localObject);
    localObject = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    paramIntent = paramIntent.getData().toString().substring(8);
    paramIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.g5e.com/moregames?g=" + paramIntent + "&u=" + (String)localObject));
    paramIntent.addFlags(268435456);
    paramContext.startActivity(paramIntent);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("com.android.vending.INSTALL_REFERRER".equals(paramIntent.getAction())) {
      onReceiveInstall(paramContext, paramIntent);
    }
    while (!"android.intent.action.PACKAGE_REMOVED".equals(paramIntent.getAction())) {
      return;
    }
    onReceiveUninstall(paramContext, paramIntent);
  }
}
