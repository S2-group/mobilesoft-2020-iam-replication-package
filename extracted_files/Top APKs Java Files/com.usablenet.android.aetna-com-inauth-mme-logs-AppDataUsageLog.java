package com.inauth.mme.logs;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.RemoteException;
import com.google.gson.Gson;
import com.inauth.mme.InAuthManager;
import com.inauth.mme.beans.AppDataUsageBean;
import com.inauth.mme.header.LogHeader;
import com.inauth.mme.response.LogResponse;
import com.inauth.utilities.InAuthUtilities;
import java.lang.reflect.Method;
import java.util.List;

public class AppDataUsageLog
{
  private boolean chk;
  
  public AppDataUsageLog() {}
  
  private boolean isChk()
  {
    return this.chk;
  }
  
  private void setChk(boolean paramBoolean)
  {
    this.chk = paramBoolean;
  }
  
  public LogResponse deserialize(String paramString)
  {
    return (LogResponse)new Gson().fromJson(paramString, LogResponse.class);
  }
  
  public String serialize(Application paramApplication, String paramString1, String paramString2, String paramString3)
  {
    LogHeader localLogHeader = new LogHeader();
    localLogHeader.setAccountGUID(paramString2);
    localLogHeader.setDeviceGUID(paramString1);
    localLogHeader.setDynamicID(InAuthManager.getInstance().getInAuthDynamicID());
    localLogHeader.setTransaction_id(paramString3);
    localLogHeader.setSDKVersion(InAuthManager.getInstance().getSDKVersion());
    localLogHeader.setType("app_data_usage_logs");
    for (;;)
    {
      int i;
      PackageInfo localPackageInfo;
      final AppDataUsageBean localAppDataUsageBean;
      try
      {
        paramString2 = paramApplication.getPackageManager().getInstalledPackages(0);
        paramString3 = paramApplication.getPackageManager();
        i = 0;
        if (i < paramString2.size())
        {
          localPackageInfo = (PackageInfo)paramString2.get(i);
          if ((localPackageInfo.packageName == null) || (localPackageInfo.packageName.equals(""))) {
            break label304;
          }
          localAppDataUsageBean = new AppDataUsageBean();
          if (localPackageInfo.packageName.equals("com.android.keyguard"))
          {
            paramString1 = "com.android.keyguard";
            localAppDataUsageBean.setName(paramString1);
            localAppDataUsageBean.setPackageName(localPackageInfo.packageName);
            paramString1 = paramString3.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
            setChk(false);
            paramString1.invoke(paramString3, new Object[] { localPackageInfo.packageName, new IPackageStatsObserver.Stub()
            {
              public void onGetStatsCompleted(PackageStats paramAnonymousPackageStats, boolean paramAnonymousBoolean)
                throws RemoteException
              {
                AppDataUsageLog localAppDataUsageLog = AppDataUsageLog.this;
                if (paramAnonymousBoolean) {}
                try
                {
                  localAppDataUsageBean.setCodeSize(Long.toString(paramAnonymousPackageStats.codeSize));
                  localAppDataUsageBean.setDataSize(Long.toString(paramAnonymousPackageStats.dataSize));
                  localAppDataUsageBean.setCacheSize(Long.toString(paramAnonymousPackageStats.cacheSize));
                  AppDataUsageLog.this.notify();
                  AppDataUsageLog.this.setChk(true);
                  return;
                }
                finally {}
              }
            } });
            try
            {
              if (isChk()) {
                break label287;
              }
              wait();
              continue;
            }
            finally {}
          }
        }
        else
        {
          return new Gson().toJson(localLogHeader);
        }
      }
      catch (Exception paramApplication) {}
      paramString1 = localPackageInfo.applicationInfo.loadLabel(paramApplication.getApplicationContext().getPackageManager()).toString();
      continue;
      label287:
      localAppDataUsageBean.setCheckedAt(InAuthUtilities.GetIsoDateTime());
      localLogHeader.addLogs(localAppDataUsageBean);
      label304:
      i += 1;
    }
  }
}
