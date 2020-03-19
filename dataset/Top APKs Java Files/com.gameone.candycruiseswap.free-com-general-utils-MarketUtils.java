package com.general.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public class MarketUtils
{
  public MarketUtils() {}
  
  public static ArrayList<String> filterInstalledPkgs(Context paramContext, ArrayList<String> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramContext == null) || (paramArrayList == null) || (paramArrayList.size() == 0)) {}
    for (;;)
    {
      return localArrayList;
      List localList = paramContext.getPackageManager().getInstalledPackages(0);
      int k = localList.size();
      int m = paramArrayList.size();
      int i = 0;
      while (i < m)
      {
        int j = 0;
        String str2;
        for (;;)
        {
          if (j >= k) {
            break label143;
          }
          paramContext = "";
          str2 = (String)paramArrayList.get(i);
          try
          {
            String str1 = ((PackageInfo)localList.get(j)).applicationInfo.packageName;
            paramContext = str1;
          }
          catch (Exception localException)
          {
            do
            {
              for (;;)
              {
                localException.printStackTrace();
              }
            } while (!paramContext.equals(str2));
            localArrayList.add(paramContext);
          }
          if (!TextUtils.isEmpty(paramContext)) {
            break;
          }
          j += 1;
        }
        label143:
        i += 1;
      }
    }
  }
  
  public static void launchAppDetail(Context paramContext)
  {
    String str = paramContext.getPackageName();
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str)));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.Google.com/store/apps/details?id=" + str)));
    }
  }
  
  public static ArrayList<String> queryInstalledMarketPkgs(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramContext == null) {}
    for (;;)
    {
      return localArrayList;
      Object localObject = new Intent();
      ((Intent)localObject).setAction("android.intent.action.MAIN");
      ((Intent)localObject).addCategory("android.intent.category.APP_MARKET");
      List localList = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0);
      if ((localList != null) && (localList.size() != 0))
      {
        int j = localList.size();
        int i = 0;
        while (i < j)
        {
          paramContext = "";
          try
          {
            localObject = ((ResolveInfo)localList.get(i)).activityInfo.packageName;
            paramContext = (Context)localObject;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              localException.printStackTrace();
            }
          }
          if (!TextUtils.isEmpty(paramContext)) {
            localArrayList.add(paramContext);
          }
          i += 1;
        }
      }
    }
  }
}
