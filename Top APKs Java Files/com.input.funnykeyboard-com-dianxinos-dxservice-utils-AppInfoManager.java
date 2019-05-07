package com.dianxinos.dxservice.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.dianxinos.dxservice.stat.BaseAppInfo;
import com.dianxinos.library.dxbase.DXBDataStorageHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class AppInfoManager
{
  private static Context a;
  private static PackageManager b;
  private static ContentResolver c;
  
  public AppInfoManager() {}
  
  public static BaseAppInfo getBaseAppInfoByPackageName(String paramString1, String paramString2)
  {
    return new BaseAppInfo(paramString1, a, paramString2);
  }
  
  public static List<BaseAppInfo> getBaseAppInfos(String paramString)
  {
    localArrayList = new ArrayList();
    try
    {
      Iterator localIterator = b.getInstalledApplications(0).iterator();
      while (localIterator.hasNext()) {
        localArrayList.add(getBaseAppInfoByPackageName(((ApplicationInfo)localIterator.next()).packageName, paramString));
      }
      return localArrayList;
    }
    catch (Exception paramString)
    {
      if (CommonUtils.LOGE_ENABLED) {
        Log.e("stat.AppInfoManager", "Failed to get base app infos.", paramString);
      }
    }
  }
  
  public static void init(Context paramContext)
  {
    a = paramContext.getApplicationContext();
    b = a.getPackageManager();
    c = a.getContentResolver();
  }
  
  public static boolean isPackageNameReported(BaseAppInfo paramBaseAppInfo)
  {
    Object localObject = DXBDataStorageHelper.getInstance(a).getString("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}", "");
    if (localObject == null) {}
    for (;;)
    {
      return false;
      localObject = ((String)localObject).split(",");
      paramBaseAppInfo = CommonUtils.hashData(paramBaseAppInfo.getmPackageName());
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        if (localObject[i].equals(paramBaseAppInfo)) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static void updateReportList(BaseAppInfo paramBaseAppInfo)
  {
    DXBDataStorageHelper localDXBDataStorageHelper = DXBDataStorageHelper.getInstance(a);
    paramBaseAppInfo = CommonUtils.hashData(paramBaseAppInfo.getmPackageName());
    String str = localDXBDataStorageHelper.getString("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}", "");
    if (str == null)
    {
      localDXBDataStorageHelper.putString("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}", paramBaseAppInfo);
      return;
    }
    localDXBDataStorageHelper.putString("android.{F46B117B-CBC7-4ac2-8F3C-43C1649DC7PN}", str + "," + paramBaseAppInfo);
  }
}
