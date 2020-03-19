package com.bytedance.common.antifraud.functionlality;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.bytedance.common.antifraud.model.WhiteApp;
import com.bytedance.common.utility.Logger;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AppInfo
{
  public static final String TAG = "AppInfo";
  private static AppInfo mInstance = null;
  private Context mContext = null;
  public int systemAppCnt = 0;
  public int userAppCnt = 0;
  
  AppInfo(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
  }
  
  public static AppInfo getInstance(Context paramContext)
  {
    if (mInstance == null) {}
    try
    {
      if (mInstance == null) {
        mInstance = new AppInfo(paramContext);
      }
      return mInstance;
    }
    finally {}
  }
  
  public String getAppName()
  {
    Object localObject = "";
    try
    {
      if (this.mContext == null) {
        return "";
      }
      String str = this.mContext.getPackageName();
      localObject = str;
      if (str == null) {
        return "";
      }
    }
    catch (Exception localException)
    {
      ThrowableExtension.printStackTrace(localException);
    }
    return localObject;
  }
  
  public String getAppVersion()
  {
    Object localObject;
    if (this.mContext == null) {
      localObject = "";
    }
    for (;;)
    {
      return localObject;
      try
      {
        String str = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
        localObject = str;
        if (str == null) {
          return "";
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Logger.d("AppInfo", "Cannot get app version");
      }
    }
    return "";
  }
  
  public Map<String, Object> getAppsInfo(Map<String, WhiteApp> paramMap)
  {
    int i;
    int k;
    label319:
    for (;;)
    {
      try
      {
        this.systemAppCnt = 0;
        this.userAppCnt = 0;
        HashMap localHashMap1 = new HashMap();
        ArrayList localArrayList = new ArrayList();
        HashMap localHashMap2 = new HashMap();
        HashMap localHashMap3 = new HashMap();
        Object localObject1 = this.mContext;
        if (localObject1 == null) {
          return localHashMap1;
        }
        Object localObject2;
        if (paramMap != null)
        {
          paramMap = paramMap.entrySet().iterator();
          if (paramMap.hasNext())
          {
            localObject1 = (Map.Entry)paramMap.next();
            localObject2 = (String)((Map.Entry)localObject1).getKey();
            localHashMap3.put(((WhiteApp)((Map.Entry)localObject1).getValue()).getPackageName(), localObject2);
            continue;
          }
        }
        try
        {
          paramMap = this.mContext.getPackageManager();
          localObject1 = paramMap.getInstalledPackages(0);
          Collections.sort((List)localObject1, new Comparator()
          {
            public int compare(PackageInfo paramAnonymousPackageInfo1, PackageInfo paramAnonymousPackageInfo2)
            {
              return Long.valueOf(-paramAnonymousPackageInfo1.firstInstallTime).compareTo(Long.valueOf(-paramAnonymousPackageInfo2.firstInstallTime));
            }
          });
          i = 0;
          k = 0;
          if (k < ((List)localObject1).size())
          {
            localObject2 = (PackageInfo)((List)localObject1).get(k);
            int j = 0;
            if (((((PackageInfo)localObject2).applicationInfo.flags & 0x1) > 0) || ((((PackageInfo)localObject2).applicationInfo.flags & 0x80) > 0))
            {
              this.systemAppCnt += 1;
              j = 0;
            }
            for (;;)
            {
              if (!localHashMap3.containsKey(((PackageInfo)localObject2).packageName)) {
                break label436;
              }
              localHashMap2.put((String)localHashMap3.get(((PackageInfo)localObject2).packageName), Integer.valueOf(1));
              break;
              if ((((PackageInfo)localObject2).applicationInfo.flags & 0x80) == 0)
              {
                this.userAppCnt += 1;
                j = 1;
              }
            }
            localArrayList.add(((PackageInfo)localObject2).firstInstallTime + "," + ((PackageInfo)localObject2).packageName + "," + ((PackageInfo)localObject2).applicationInfo.loadLabel(paramMap) + "," + j);
            i += 1;
          }
        }
        catch (Exception paramMap)
        {
          Logger.e("AppInfo", "Get app info error", paramMap);
          localHashMap1.put("apps", localArrayList);
          localHashMap1.put("whiteapps", localHashMap2);
        }
      }
      finally {}
    }
    for (;;)
    {
      k += 1;
      break;
      label436:
      if (i <= 30) {
        break label319;
      }
    }
  }
  
  public String getDisplayAppName()
  {
    try
    {
      if (this.mContext == null) {
        return "";
      }
      Object localObject = this.mContext.getPackageManager();
      localObject = ((PackageManager)localObject).getPackageInfo(this.mContext.getPackageName(), 0).applicationInfo.loadLabel((PackageManager)localObject).toString();
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Logger.e("AppInfo", "get app name failed: " + localNameNotFoundException.getMessage());
    }
    return "";
  }
  
  public int getSelfTargetSdkVer()
  {
    return this.mContext.getApplicationInfo().targetSdkVersion;
  }
  
  public int getSystemAppCount()
  {
    try
    {
      int i = this.systemAppCnt;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public int getUserAppCount()
  {
    try
    {
      int i = this.userAppCnt;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}
