package com.b.a;

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

public final class a
{
  private static String a = null;
  
  public static String a(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.HOME");
      paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
      if ((paramContext != null) && (paramContext.activityInfo != null))
      {
        if (paramContext.activityInfo.packageName.equals("android")) {
          return "";
        }
        paramContext = paramContext.activityInfo.packageName;
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      return "";
    }
    return "";
  }
  
  public static String a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
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
          int j = arrayOfProviderInfo.length;
          int i = 0;
          while (i < j)
          {
            ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
            if ((paramString.equals(localProviderInfo.readPermission)) || (paramString.equals(localProviderInfo.writePermission)))
            {
              paramContext = localProviderInfo.authority;
              return paramContext;
            }
            i += 1;
          }
        }
      }
      return "";
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static String b(Context paramContext)
  {
    if (TextUtils.isEmpty(a)) {
      a = a(paramContext, "com.android.launcher.permission.READ_SETTINGS");
    }
    return a;
  }
}
