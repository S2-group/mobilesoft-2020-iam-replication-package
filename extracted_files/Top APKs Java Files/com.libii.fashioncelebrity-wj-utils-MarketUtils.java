package wj.utils;

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
  private static List<String> MarketPackages = new ArrayList();
  
  static
  {
    MarketPackages.add("com.lenovo.leos.appstore");
    MarketPackages.add("com.xiaomi.market");
    MarketPackages.add("com.qihoo.appstore");
    MarketPackages.add("com.wandoujia.phoenix2");
    MarketPackages.add("com.baidu.appsearch");
    MarketPackages.add("com.tencent.android.qqdownloader");
    MarketPackages.add("com.coolapk.market");
    MarketPackages.add("zte.com.market");
    MarketPackages.add("com.oppo.market");
    MarketPackages.add("com.pp.assistant");
    MarketPackages.add("com.mappn.gfan");
    MarketPackages.add("com.tencent.qqpimsecure");
    MarketPackages.add("com.yingyonghui.market");
    MarketPackages.add("com.hiapk.marketpho");
    MarketPackages.add("com.dragon.android.pandaspace");
    MarketPackages.add("com.huawei.appmarket");
  }
  
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
  
  public static List<ApplicationInfo> filterInstalledPkgs(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramContext == null) || (MarketPackages == null) || (MarketPackages.size() == 0)) {}
    for (;;)
    {
      return localArrayList;
      List localList = paramContext.getPackageManager().getInstalledPackages(0);
      int k = localList.size();
      int m = MarketPackages.size();
      int i = 0;
      while (i < m)
      {
        int j = 0;
        String str2;
        for (;;)
        {
          if (j >= k) {
            break label170;
          }
          paramContext = "";
          str2 = (String)MarketPackages.get(i);
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
            localArrayList.add(((PackageInfo)localList.get(j)).applicationInfo);
          }
          if (!TextUtils.isEmpty(paramContext)) {
            break;
          }
          j += 1;
        }
        label170:
        i += 1;
      }
    }
  }
  
  public static void launchAppDetail(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      if (TextUtils.isEmpty(paramString1)) {
        return;
      }
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString1));
      if (!TextUtils.isEmpty(paramString2)) {
        paramString1.setPackage(paramString2);
      }
      paramString1.addFlags(268435456);
      paramContext.startActivity(paramString1);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static List<ActivityInfo> queryInstalledMarketInfos(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramContext == null) {}
    do
    {
      return localArrayList;
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.APP_MARKET");
      paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, 0);
    } while ((paramContext == null) || (localArrayList.size() == 0));
    int i = 0;
    while (i < paramContext.size()) {
      try
      {
        localArrayList.add(((ResolveInfo)paramContext.get(i)).activityInfo);
        i += 1;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
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
