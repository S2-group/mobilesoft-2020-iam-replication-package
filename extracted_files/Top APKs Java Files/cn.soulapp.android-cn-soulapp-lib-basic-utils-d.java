package cn.soulapp.lib.basic.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import cn.soulapp.lib.basic.app.MartianApp;
import com.d.a.j;
import com.google.a.a.a.a.a.a;
import java.util.Iterator;
import java.util.List;

public class d
{
  public d() {}
  
  public static int a(Context paramContext)
  {
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(64).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.equals(paramContext.getPackageName())) {
        return localPackageInfo.signatures[0].hashCode();
      }
    }
    return 0;
  }
  
  public static String a(@StringRes int paramInt)
  {
    return a(MartianApp.h().getString(paramInt));
  }
  
  public static String a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new NullPointerException("key == null!!!");
    }
    Object localObject2 = null;
    Object localObject3 = MartianApp.h();
    try
    {
      PackageManager localPackageManager = ((Context)localObject3).getPackageManager();
      Object localObject1 = localObject2;
      if (localPackageManager != null)
      {
        localObject3 = localPackageManager.getApplicationInfo(((Context)localObject3).getPackageName(), 128);
        localObject1 = localObject2;
        if (localObject3 != null)
        {
          localObject1 = localObject2;
          if (((ApplicationInfo)localObject3).metaData != null) {
            localObject1 = ((ApplicationInfo)localObject3).metaData.getString(paramString);
          }
        }
      }
      return localObject1;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      a.b(paramString);
    }
    return null;
  }
  
  public static boolean a()
  {
    MartianApp localMartianApp = MartianApp.h();
    Object localObject = (ActivityManager)localMartianApp.getSystemService("activity");
    if (localObject == null) {
      return false;
    }
    localObject = ((ActivityManager)localObject).getRunningAppProcesses().iterator();
    while (((Iterator)localObject).hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
      if (localRunningAppProcessInfo.processName.equals(localMartianApp.getPackageName()))
      {
        j.c(localMartianApp.getPackageName(), new Object[] { "此appimportace =" + localRunningAppProcessInfo.importance + ",context.getClass().getName()=" + localMartianApp.getClass().getName() });
        if (localRunningAppProcessInfo.importance != 100)
        {
          j.c(localMartianApp.getPackageName(), new Object[] { "处于后台" + localRunningAppProcessInfo.processName });
          return true;
        }
        j.c(localMartianApp.getPackageName(), new Object[] { "处于前台" + localRunningAppProcessInfo.processName });
        return false;
      }
    }
    return false;
  }
  
  public static boolean b(Context paramContext)
  {
    try
    {
      Object localObject = (ActivityManager)paramContext.getSystemService("activity");
      if (localObject == null) {
        return true;
      }
      localObject = ((ActivityManager)localObject).getRunningAppProcesses();
      paramContext = paramContext.getPackageName();
      int i = Process.myPid();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
        j.d(" PROCESS : " + localRunningAppProcessInfo.pid + " : " + localRunningAppProcessInfo.processName, new Object[0]);
        if (localRunningAppProcessInfo.pid == i)
        {
          boolean bool = paramContext.equals(localRunningAppProcessInfo.processName);
          if (bool) {
            return true;
          }
        }
      }
      return false;
    }
    catch (Exception paramContext) {}
    return true;
  }
}
