package com.alifesoftware.stockscreener.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import java.util.Iterator;
import java.util.List;

public class Utils
{
  public Utils() {}
  
  private static boolean doesPackageExist(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      while (paramContext.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        if (!localApplicationInfo.packageName.equalsIgnoreCase(paramString))
        {
          boolean bool = localApplicationInfo.packageName.contains(paramString);
          if (!bool) {
            break;
          }
        }
        else
        {
          return true;
        }
      }
      return false;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  private static boolean findExistingPackage(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 128);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static int getStatusBarHeight(Context paramContext)
  {
    return (int)paramContext.getResources().getDimension(2131165391);
  }
  
  public static int getToolbarHeight(Context paramContext)
  {
    return (int)paramContext.getResources().getDimension(2131165186);
  }
  
  public static boolean isPackageInstalled(Context paramContext, String paramString)
  {
    return (doesPackageExist(paramContext, paramString)) || (findExistingPackage(paramContext, paramString));
  }
  
  public static boolean isPlayStoreInstalled(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getApplicationInfo("com.android.vending", 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static void showStockScreenerPlayStorePage(Context paramContext)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.alifesoftware.stockscreener")));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.alifesoftware.stockscreener")));
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static void showStockTrainerPlayStorePage(Context paramContext)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.alifesoftware.stocktrainer")));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.alifesoftware.stocktrainer")));
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
}
