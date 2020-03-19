package com.p3group.insight.manager.a;

import android.a.a.a.a;
import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStats.Bucket;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.content.pm.Signature;
import android.net.TrafficStats;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.p3group.insight.InsightConfig;
import com.p3group.insight.InsightCore;
import com.p3group.insight.controller.DeviceController;
import com.p3group.insight.enums.FileTypes;
import com.p3group.insight.results.InstalledAppsResult;
import com.p3group.insight.results.appusage.InstalledApp;
import com.p3group.insight.utils.AppUsageUtils;
import com.p3group.insight.utils.d;
import com.p3group.insight.utils.e;
import com.p3group.insight.utils.m;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class h
{
  private static final String a = h.class.getSimpleName();
  private final Context b;
  private final com.p3group.insight.b c;
  private boolean d;
  
  public h(Context paramContext)
  {
    this.b = paramContext;
    this.c = new com.p3group.insight.b(paramContext);
  }
  
  public void a()
  {
    a(false);
  }
  
  public void a(boolean paramBoolean)
  {
    if (this.d) {}
    com.p3group.insight.b localB;
    long l;
    do
    {
      do
      {
        return;
        localB = new com.p3group.insight.b(this.b.getApplicationContext());
      } while ((!localB.a()) && (!paramBoolean));
      l = com.p3group.insight.f.b.b();
    } while (l - this.c.c() <= localB.b());
    this.d = true;
    new a(null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Long[] { Long.valueOf(l) });
  }
  
  private class a
    extends AsyncTask<Long, Void, InstalledAppsResult>
  {
    private a() {}
    
    private String a(int paramInt)
    {
      if (paramInt == 0) {
        return ((TelephonyManager)h.a(h.this).getSystemService("phone")).getSubscriberId();
      }
      return "";
    }
    
    @TargetApi(23)
    private HashMap<Integer, long[]> a()
    {
      HashMap localHashMap = new HashMap();
      long l = System.currentTimeMillis() - SystemClock.elapsedRealtime();
      Object localObject1 = (NetworkStatsManager)h.a(h.this).getSystemService(NetworkStatsManager.class);
      Object localObject2;
      label239:
      do
      {
        for (;;)
        {
          long[] arrayOfLong;
          try
          {
            NetworkStats.Bucket localBucket = new NetworkStats.Bucket();
            localObject2 = ((NetworkStatsManager)localObject1).querySummary(1, null, l, System.currentTimeMillis());
            if (localObject2 == null) {
              break label239;
            }
            if (!((NetworkStats)localObject2).getNextBucket(localBucket)) {
              break;
            }
            if (localHashMap.containsKey(Integer.valueOf(localBucket.getUid())))
            {
              arrayOfLong = (long[])localHashMap.get(Integer.valueOf(localBucket.getUid()));
              arrayOfLong[0] += localBucket.getRxBytes();
              arrayOfLong[1] += localBucket.getTxBytes();
              localHashMap.put(Integer.valueOf(localBucket.getUid()), arrayOfLong);
              continue;
            }
            arrayOfLong = new long[2];
          }
          catch (Exception localException)
          {
            Log.d(h.b(), "getUidBytesForAndroidM: " + localException.getMessage());
            return localHashMap;
          }
          tmp189_187 = arrayOfLong;
          tmp189_187[0] = 0L;
          tmp193_189 = tmp189_187;
          tmp193_189[1] = 0L;
          tmp193_189;
          arrayOfLong[0] = localException.getRxBytes();
          arrayOfLong[1] = localException.getTxBytes();
          localHashMap.put(Integer.valueOf(localException.getUid()), arrayOfLong);
        }
        ((NetworkStats)localObject2).close();
        localObject1 = ((NetworkStatsManager)localObject1).querySummary(0, a(0), l, System.currentTimeMillis());
      } while (localObject1 == null);
      while (((NetworkStats)localObject1).getNextBucket(localException)) {
        if (localHashMap.containsKey(Integer.valueOf(localException.getUid())))
        {
          localObject2 = (long[])localHashMap.get(Integer.valueOf(localException.getUid()));
          localObject2[0] += localException.getRxBytes();
          localObject2[1] += localException.getTxBytes();
          localHashMap.put(Integer.valueOf(localException.getUid()), localObject2);
        }
        else
        {
          localObject2 = new long[2];
          Object tmp356_354 = localObject2;
          tmp356_354[0] = 0L;
          Object tmp360_356 = tmp356_354;
          tmp360_356[1] = 0L;
          tmp360_356;
          localObject2[0] = localException.getRxBytes();
          localObject2[1] = localException.getTxBytes();
          localHashMap.put(Integer.valueOf(localException.getUid()), localObject2);
        }
      }
      ((NetworkStats)localObject1).close();
      return localHashMap;
    }
    
    protected InstalledAppsResult a(Long... paramVarArgs)
    {
      InsightConfig localInsightConfig = InsightCore.getInsightConfig();
      InstalledAppsResult localInstalledAppsResult = new InstalledAppsResult(localInsightConfig.PROJECT_ID(), InsightCore.getGUID());
      localInstalledAppsResult.DeviceInfo = DeviceController.getDeviceInfo(h.a(h.this));
      localInstalledAppsResult.TimeInfo = com.p3group.insight.f.b.a();
      localInstalledAppsResult.TimestampSnapshot = e.a(paramVarArgs[0].longValue());
      localInstalledAppsResult.HostAppPackageName = h.a(h.this).getPackageName();
      ArrayList localArrayList1 = new ArrayList();
      PackageManager localPackageManager = h.a(h.this).getPackageManager();
      int i;
      label211:
      Object localObject1;
      final InstalledApp localInstalledApp;
      Object localObject2;
      ArrayList localArrayList2;
      int k;
      int j;
      try
      {
        paramVarArgs = localPackageManager.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, android.a.a.a.class });
        if (h.a(h.this).checkCallingOrSelfPermission("android.permission.GET_PACKAGE_SIZE") == 0)
        {
          i = 1;
          HashMap localHashMap = new HashMap();
          if ((Build.VERSION.SDK_INT < 23) || (((AppOpsManager)h.a(h.this).getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Process.myUid(), h.a(h.this).getPackageName()) != 0)) {
            break label1009;
          }
          localHashMap = a();
          Iterator localIterator = localPackageManager.getInstalledPackages(4160).iterator();
          if (!localIterator.hasNext()) {
            break label981;
          }
          localObject1 = (PackageInfo)localIterator.next();
          localInstalledApp = new InstalledApp();
          localInstalledApp.PackageName = m.a(((PackageInfo)localObject1).packageName);
          localInstalledApp.AppName = m.a(localPackageManager.getApplicationLabel(((PackageInfo)localObject1).applicationInfo).toString());
          localInstalledApp.FirstInstallTimestamp = e.a(((PackageInfo)localObject1).firstInstallTime);
          localInstalledApp.LastUpdateTimestamp = e.a(((PackageInfo)localObject1).lastUpdateTime);
          localInstalledApp.VersionCode = ((PackageInfo)localObject1).versionCode;
          localInstalledApp.VersionName = m.a(((PackageInfo)localObject1).versionName);
          if ((((PackageInfo)localObject1).applicationInfo.flags & 0x1) == 0) {
            break label520;
          }
          bool = true;
          localInstalledApp.IsSystemApp = bool;
          localInstalledApp.UID = ((PackageInfo)localObject1).applicationInfo.uid;
          if ((localHashMap == null) || (localHashMap.isEmpty())) {
            break label526;
          }
          localObject2 = (long[])localHashMap.get(Integer.valueOf(localInstalledApp.UID));
          if (localObject2 != null)
          {
            localInstalledApp.TrafficUsageRx = localObject2[0];
            localInstalledApp.TrafficUsageTx = localObject2[1];
          }
          if (!localInsightConfig.INSTALLED_APP_SNAPSHOT_SIGNATURES_ENABLED()) {
            break label582;
          }
          localObject2 = ((PackageInfo)localObject1).signatures;
          if (localObject2 == null) {
            break label582;
          }
          localArrayList2 = new ArrayList();
          k = localObject2.length;
          j = 0;
          while (j < k)
          {
            localArrayList2.add(localObject2[j].toCharsString());
            j += 1;
          }
        }
      }
      catch (NoSuchMethodException paramVarArgs)
      {
        for (;;)
        {
          Log.e(h.b(), "IA: " + paramVarArgs.getMessage());
          paramVarArgs = null;
          continue;
          i = 0;
          continue;
          label520:
          boolean bool = false;
          continue;
          label526:
          localInstalledApp.TrafficUsageRx = TrafficStats.getUidRxBytes(((PackageInfo)localObject1).applicationInfo.uid);
          localInstalledApp.TrafficUsageTx = TrafficStats.getUidTxBytes(((PackageInfo)localObject1).applicationInfo.uid);
        }
        localInstalledApp.Signatures = ((String[])localArrayList2.toArray(new String[localArrayList2.size()]));
        label582:
        if ((localInsightConfig.INSTALLED_APP_SNAPSHOT_PERMISSIONS_ENABLED()) && (((PackageInfo)localObject1).requestedPermissions != null)) {
          localInstalledApp.Permissions = ((PackageInfo)localObject1).requestedPermissions;
        }
      }
      for (;;)
      {
        long l2;
        try
        {
          localObject1 = localPackageManager.getApplicationInfo(localInstalledApp.PackageName, 0);
          if (((ApplicationInfo)localObject1).publicSourceDir != null)
          {
            localObject2 = new File(((ApplicationInfo)localObject1).publicSourceDir);
            if ((((File)localObject2).exists()) && (((File)localObject2).isFile()))
            {
              localInstalledApp.ApkSize = ((File)localObject2).length();
              localObject2 = com.p3group.insight.b.b.b.a((File)localObject2);
              if (localObject2 != null) {
                localInstalledApp.Sha256CheckSum = d.a((byte[])localObject2);
              }
            }
          }
          if (((ApplicationInfo)localObject1).nativeLibraryDir != null)
          {
            localObject2 = new File(((ApplicationInfo)localObject1).nativeLibraryDir);
            if ((((File)localObject2).exists()) && (((File)localObject2).isDirectory()))
            {
              l1 = 0L;
              localObject2 = ((File)localObject2).listFiles();
              l2 = l1;
              if (localObject2 != null)
              {
                k = localObject2.length;
                j = 0;
                l2 = l1;
                if (j < k)
                {
                  localArrayList2 = localObject2[j];
                  l2 = l1;
                  if (!localArrayList2.isFile()) {
                    break label1012;
                  }
                  l2 = l1 + localArrayList2.length();
                  break label1012;
                }
              }
              localInstalledApp.NativeLibSize = l2;
              if ((paramVarArgs != null) && (i != 0))
              {
                j = Build.VERSION.SDK_INT;
                if (j >= 26) {}
              }
            }
          }
        }
        catch (Exception localException1)
        {
          try
          {
            paramVarArgs.invoke(localPackageManager, new Object[] { localInstalledApp.PackageName, new a.a()
            {
              public void a(PackageStats paramAnonymousPackageStats, boolean paramAnonymousBoolean)
                throws RemoteException
              {
                localInstalledApp.CacheSize = paramAnonymousPackageStats.cacheSize;
                localInstalledApp.DataSize = paramAnonymousPackageStats.dataSize;
              }
            } });
            if (Build.VERSION.SDK_INT >= 26) {
              localInstalledApp.AppCategory = AppUsageUtils.getAppCategory(((ApplicationInfo)localObject1).category);
            }
            localArrayList1.add(localInstalledApp);
            break label211;
            localInstalledApp.NativeLibSize = -1L;
            continue;
            localException1 = localException1;
            Log.d(h.b(), "getApplicationInfo: " + localException1.getMessage());
            continue;
            localInstalledApp.NativeLibSize = -1L;
          }
          catch (Exception localException2)
          {
            Log.d(h.b(), "methodGetPackageSizeInfo: " + localException2.getMessage());
            continue;
          }
        }
        label981:
        localInstalledAppsResult.InstalledApps = ((InstalledApp[])localArrayList1.toArray(new InstalledApp[localArrayList1.size()]));
        return localInstalledAppsResult;
        label1009:
        break;
        label1012:
        j += 1;
        long l1 = l2;
      }
    }
    
    protected void a(InstalledAppsResult paramInstalledAppsResult)
    {
      InsightCore.getDatabaseHelper().a(FileTypes.IA, paramInstalledAppsResult);
      h.b(h.this).a(com.p3group.insight.f.b.b());
      h.a(h.this, false);
    }
  }
}
