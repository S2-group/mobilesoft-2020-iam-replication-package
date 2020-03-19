package com.a.an;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class l
{
  public static void a(Context paramContext, final a paramA)
  {
    new Thread(new Runnable()
    {
      private List<UsageStats> c;
      
      @RequiresApi(api=21)
      public void run()
      {
        try
        {
          Object localObject1 = this.a.getPackageManager();
          int i = Build.VERSION.SDK_INT;
          if (i >= 21) {
            try
            {
              this.c = ((UsageStatsManager)this.a.getSystemService("usagestats")).queryUsageStats(3, System.currentTimeMillis() - 31536000000L, System.currentTimeMillis());
            }
            catch (Exception localException2)
            {
              localException2.printStackTrace();
            }
          }
          Object localObject3 = ((PackageManager)localObject1).getInstalledPackages(1);
          Object localObject2 = new JSONArray();
          localObject1 = new JSONArray();
          localObject3 = ((List)localObject3).iterator();
          PackageInfo localPackageInfo;
          JSONObject localJSONObject;
          while (((Iterator)localObject3).hasNext())
          {
            localPackageInfo = (PackageInfo)((Iterator)localObject3).next();
            if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
            {
              localJSONObject = new JSONObject();
              localJSONObject.put("pkg", localPackageInfo.packageName);
              localJSONObject.put("app_ver", localPackageInfo.versionCode);
              localJSONObject.put("install_time", localPackageInfo.firstInstallTime);
              localJSONObject.put("update_time", localPackageInfo.lastUpdateTime);
              Iterator localIterator;
              if ((this.c != null) && (this.c.size() > 0)) {
                localIterator = this.c.iterator();
              }
              while (localIterator.hasNext())
              {
                UsageStats localUsageStats = (UsageStats)localIterator.next();
                if (localPackageInfo.packageName.equals(localUsageStats.getPackageName()))
                {
                  localJSONObject.put("usg_tm", localUsageStats.getTotalTimeInForeground());
                  break;
                  localJSONObject.put("usg_tm", "");
                }
              }
              ((JSONArray)localObject2).put(localJSONObject);
            }
          }
          localObject3 = new JSONObject();
          ((JSONObject)localObject3).put("apps", localObject2);
          try
          {
            localObject2 = AccountManager.get(this.a).getAccounts();
            int j = localObject2.length;
            i = 0;
            while (i < j)
            {
              localPackageInfo = localObject2[i];
              localJSONObject = new JSONObject();
              localJSONObject.put("acnt", localPackageInfo.name);
              localJSONObject.put("type", localPackageInfo.type);
              ((JSONArray)localObject1).put(localJSONObject);
              i += 1;
            }
            ((JSONObject)localObject3).put("acnts", localObject1);
          }
          catch (Exception localException3)
          {
            localException3.printStackTrace();
            ((JSONObject)localObject3).put("acnts", localObject1);
          }
          if (paramA != null)
          {
            paramA.a(((JSONObject)localObject3).toString());
            return;
          }
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
          if (paramA != null) {
            paramA.a();
          }
        }
      }
    }).start();
  }
  
  public static abstract interface a
  {
    public abstract void a();
    
    public abstract void a(String paramString);
  }
}
