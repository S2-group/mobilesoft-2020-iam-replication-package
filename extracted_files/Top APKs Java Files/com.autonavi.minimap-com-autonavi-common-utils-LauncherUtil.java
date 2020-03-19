package com.autonavi.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class LauncherUtil
{
  static final String TAG = "LauncherUtil";
  private static String mBufferedValue = null;
  
  public LauncherUtil() {}
  
  public static String getAuthorityFromPermission(Context paramContext, String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {
      return "";
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8);
      if (paramContext == null) {
        return "";
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
        if (arrayOfProviderInfo != null)
        {
          int k = arrayOfProviderInfo.length;
          int i = 0;
          while (i < k)
          {
            ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
            int m = paramArrayOfString.length;
            int j = 0;
            while (j < m)
            {
              String str = paramArrayOfString[j];
              if ((str.equals(localProviderInfo.readPermission)) || (str.equals(localProviderInfo.writePermission)))
              {
                paramContext = localProviderInfo.authority;
                return paramContext;
              }
              j += 1;
            }
            i += 1;
          }
        }
      }
      return "";
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static String getAuthorityFromPermissionDefault(Context paramContext)
  {
    if (TextUtils.isEmpty(mBufferedValue)) {
      mBufferedValue = getAuthorityFromPermission(paramContext, new String[] { "com.android.launcher.permission.READ_SETTINGS", "com.google.android.launcher.permission.READ_SETTINGS" });
    }
    return mBufferedValue;
  }
  
  public static String getCurrentLauncherPackageName(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    if ((paramContext == null) || (paramContext.activityInfo == null)) {
      return "";
    }
    if (paramContext.activityInfo.packageName.equals("android")) {
      return "";
    }
    return paramContext.activityInfo.packageName;
  }
}
