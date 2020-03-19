package com.cleanmaster.ncmanager.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.cleanmaster.entity.AppInfo;
import com.cleanmaster.ncbridge.NCEntryAgent;
import com.cleanmaster.ncbridge.config.LocalConfig;
import com.cleanmaster.ncmanager.util.CollectionUtils;
import com.cleanmaster.ncmanager.util.PackageUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QueryPackageInfoTask
{
  private PackageManager mPM;
  private LocalConfig mServiceConfigManager;
  
  public QueryPackageInfoTask()
  {
    init();
  }
  
  private void addAppInfoToList(List<AppInfo> paramList, List<String> paramList1, AppInfo paramAppInfo)
  {
    if ((paramList == null) || (paramList1 == null) || (paramAppInfo == null)) {
      return;
    }
    paramList1 = paramList1.iterator();
    String str;
    do
    {
      if (!paramList1.hasNext()) {
        break;
      }
      str = (String)paramList1.next();
    } while ((TextUtils.isEmpty(str)) || (!str.equals(paramAppInfo.packageName)));
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        paramList.add(0, paramAppInfo);
        paramAppInfo.setmIsWhite(1);
        return;
      }
      paramList.add(paramAppInfo);
      return;
    }
  }
  
  private void init()
  {
    this.mPM = NCEntryAgent.getInstance().getAppContext().getPackageManager();
    this.mServiceConfigManager = NCEntryAgent.getInstance().LocalConfig();
  }
  
  private boolean isInCloudWhite(Set<String> paramSet, String paramString)
  {
    if ((paramSet == null) || (paramSet.size() <= 0)) {}
    while ((CollectionUtils.array2Set(this.mServiceConfigManager.getNotificationHandleList().split("#")).contains(paramString)) || (!paramSet.contains(paramString))) {
      return false;
    }
    return true;
  }
  
  private AppInfo loadAppInfo(PackageInfo paramPackageInfo)
  {
    AppInfo localAppInfo = new AppInfo();
    localAppInfo.appName = paramPackageInfo.applicationInfo.loadLabel(this.mPM).toString();
    localAppInfo.packageName = paramPackageInfo.packageName;
    return localAppInfo;
  }
  
  private Set<String> parserJson(String paramString)
  {
    localHashSet = new HashSet();
    try
    {
      paramString = new JSONObject(paramString).optJSONArray("data");
      int i = 0;
      while (i < paramString.length())
      {
        localHashSet.add((String)paramString.opt(i));
        i += 1;
      }
      return localHashSet;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public List<AppInfo> getPackageInfo()
  {
    AppInfo localAppInfo2 = null;
    int i = 0;
    System.currentTimeMillis();
    Object localObject2 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    g localG = new g();
    Object localObject1 = PackageUtil.getInstalledPackagesNoThrow(this.mPM, 0);
    List localList = NotificationDataManager.getInst().getWhiteList();
    if (localObject1 != null)
    {
      Iterator localIterator = ((List)localObject1).iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          try
          {
            localObject1 = this.mPM.getLaunchIntentForPackage(localPackageInfo.packageName);
            if (localObject1 != null) {
              addAppInfoToList((List)localObject2, localList, loadAppInfo(localPackageInfo));
            }
          }
          catch (Exception localException)
          {
            for (;;)
            {
              localException.printStackTrace();
              localAppInfo1 = null;
            }
          }
        }
      }
    }
    localObject2 = ((List)localObject2).iterator();
    AppInfo localAppInfo1 = localAppInfo2;
    while (((Iterator)localObject2).hasNext())
    {
      localAppInfo2 = (AppInfo)((Iterator)localObject2).next();
      if (localAppInfo2.getmIsWhite() == 1)
      {
        if ((!TextUtils.isEmpty(localAppInfo2.packageName)) && (localAppInfo2.packageName.equalsIgnoreCase(NCEntryAgent.getInstance().getAppContext().getPackageName())))
        {
          localAppInfo1 = localAppInfo2;
          i = 1;
        }
        else
        {
          localArrayList2.add(localAppInfo2);
        }
      }
      else {
        localArrayList3.add(localAppInfo2);
      }
    }
    Collections.sort(localArrayList2, localG);
    Collections.sort(localArrayList3, localG);
    if ((i != 0) && (localAppInfo1 != null)) {
      localArrayList2.add(localAppInfo1);
    }
    localArrayList1.addAll(localArrayList3);
    localArrayList1.addAll(localArrayList2);
    return localArrayList1;
  }
  
  public void startQueryPackageInfo(QueryPackageInfoTask.INotificationJobListener paramINotificationJobListener)
  {
    new Thread(new f(this, paramINotificationJobListener), "nc_query_task").start();
  }
}
