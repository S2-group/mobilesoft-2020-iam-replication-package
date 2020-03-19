package com.contapps.android.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.contapps.android.lib.R.string;
import java.util.Iterator;
import java.util.List;

public class ContextUtils
{
  public static Intent a(PackageManager paramPackageManager, Intent paramIntent)
  {
    paramPackageManager = paramPackageManager.queryIntentActivities(paramIntent, 0).iterator();
    while (paramPackageManager.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramPackageManager.next();
      String str = localResolveInfo.activityInfo.packageName;
      if (ContactsPlusConsts.a.equals(str))
      {
        paramIntent.setComponent(new ComponentName(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name));
        return paramIntent;
      }
    }
    return null;
  }
  
  private static void a(Context paramContext, boolean paramBoolean)
  {
    if ((paramBoolean) && (paramContext != null)) {
      GlobalUtils.a(paramContext, R.string.unknown_intent, 1);
    }
  }
  
  public static boolean a(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    return b(paramActivity, paramIntent, paramInt);
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    return a(paramContext, paramIntent, true);
  }
  
  public static boolean a(Context paramContext, Intent paramIntent, boolean paramBoolean)
  {
    if (paramContext == null) {
      return false;
    }
    try
    {
      new StringBuilder("ContextUtils.startActivity: ").append(paramIntent);
      LogUtils.a();
      paramContext.startActivity(paramIntent);
      return true;
    }
    catch (Exception localException)
    {
      localStringBuilder = new StringBuilder("Other exception for ");
      localStringBuilder.append(paramIntent);
      localStringBuilder.append(" -- ");
      localStringBuilder.append(localException.getMessage());
      LogUtils.d(localStringBuilder.toString());
      a(paramContext, paramBoolean);
      return false;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      StringBuilder localStringBuilder = new StringBuilder("ActivityNotFound for ");
      localStringBuilder.append(paramIntent);
      localStringBuilder.append(" -- ");
      localStringBuilder.append(localActivityNotFoundException.getMessage());
      LogUtils.d(localStringBuilder.toString());
      a(paramContext, paramBoolean);
    }
    return false;
  }
  
  @SuppressLint({"InlinedApi"})
  public static boolean a(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=".concat(String.valueOf(paramString))));
    localIntent.setFlags(1208483840);
    if (!a(paramContext, localIntent, false))
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=".concat(String.valueOf(paramString))));
      paramString.setFlags(1208483840);
      return a(paramContext, paramString, true);
    }
    return true;
  }
  
  public static boolean a(PackageManager paramPackageManager, String paramString)
  {
    paramPackageManager = paramPackageManager.getInstalledApplications(0).iterator();
    while (paramPackageManager.hasNext()) {
      if (((ApplicationInfo)paramPackageManager.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean b(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    if (paramActivity == null) {
      return false;
    }
    try
    {
      new StringBuilder("ContextUtils.startActivityForResult: ").append(paramIntent);
      LogUtils.a();
      paramActivity.startActivityForResult(paramIntent, paramInt);
      return true;
    }
    catch (Exception localException)
    {
      localStringBuilder = new StringBuilder("Other exception for ");
      localStringBuilder.append(paramIntent);
      localStringBuilder.append(" -- ");
      localStringBuilder.append(localException.getMessage());
      LogUtils.d(localStringBuilder.toString());
      a(paramActivity, true);
      return false;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      StringBuilder localStringBuilder = new StringBuilder("ActivityNotFound for ");
      localStringBuilder.append(paramIntent);
      localStringBuilder.append(" -- ");
      localStringBuilder.append(localActivityNotFoundException.getMessage());
      LogUtils.d(localStringBuilder.toString());
      a(paramActivity, true);
    }
    return false;
  }
}
