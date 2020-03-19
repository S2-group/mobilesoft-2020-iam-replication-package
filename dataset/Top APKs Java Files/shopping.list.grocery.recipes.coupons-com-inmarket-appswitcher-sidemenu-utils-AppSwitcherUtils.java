package com.inmarket.appswitcher.sidemenu.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.util.Iterator;
import java.util.List;

public class AppSwitcherUtils
{
  public AppSwitcherUtils() {}
  
  public static int dpfromPixel(Activity paramActivity, int paramInt)
  {
    float f = paramActivity.getApplication().getResources().getDisplayMetrics().density;
    return (int)(paramInt / f + 0.5F);
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    do
    {
      if (!paramContext.hasNext()) {
        return false;
      }
    } while (!((ApplicationInfo)paramContext.next()).packageName.equals(paramString));
    return true;
  }
  
  public static boolean isFileInResources(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getPackageName());
      if (i != 0) {}
      return false;
    }
    finally {}
  }
  
  public static int pixelsFromDp(Activity paramActivity, int paramInt)
  {
    float f = paramActivity.getApplication().getResources().getDisplayMetrics().density;
    return (int)(paramInt * f + 0.5F);
  }
}
