package com.ford.oa.commons.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.ford.oa.FordApplication;
import com.ford.powertrain.utils.ExceptionLogger;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PackageUtil
{
  private final ExceptionLogger mExceptionLogger;
  
  @Inject
  public PackageUtil(ExceptionLogger paramExceptionLogger)
  {
    this.mExceptionLogger = paramExceptionLogger;
  }
  
  public static void openApp(Context paramContext, String paramString)
  {
    if (packageExists(paramContext, paramString)) {}
    Intent localIntent;
    for (paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);; paramString = localIntent)
    {
      paramContext.startActivity(paramString);
      return;
      localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse("market://details?id=" + paramString));
    }
  }
  
  public static boolean packageExists(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext()) {
        if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public final String getVersionName()
  {
    Object localObject = FordApplication.getApplication();
    try
    {
      localObject = ((Context)localObject).getPackageManager().getPackageInfo(((Context)localObject).getPackageName(), 0).versionName;
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      this.mExceptionLogger.logException(localNameNotFoundException);
    }
    return "";
  }
}
