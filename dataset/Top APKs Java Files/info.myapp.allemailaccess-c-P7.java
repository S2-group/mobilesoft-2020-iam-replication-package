package c;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStats.Bucket;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.content.pm.Signature;
import android.net.TrafficStats;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.p3group.insight.InsightConfig;
import com.p3group.insight.InsightCore;
import com.p3group.insight.controller.DeviceController;
import com.p3group.insight.i.c;
import com.p3group.insight.i.i;
import com.p3group.insight.results.InstalledAppsResult;
import com.p3group.insight.results.appusage.InstalledApp;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class P7
{
  private static final String ˊ = P7.class.getSimpleName();
  private final Context ˋ;
  private final com.p3group.insight.b ˎ;
  private boolean ˏ;
  private boolean ᐝ = false;
  
  public P7(Context paramContext)
  {
    this.ˋ = paramContext;
    this.ˎ = new com.p3group.insight.b(paramContext);
  }
  
  public void ˊ()
  {
    if (this.ˏ) {}
    com.p3group.insight.b localB;
    long l;
    do
    {
      do
      {
        return;
        localB = new com.p3group.insight.b(this.ˋ.getApplicationContext());
      } while (!localB.a());
      l = com.p3group.insight.g.b.b();
    } while (l - this.ˎ.c() <= localB.b());
    this.ˏ = true;
    new RYG((byte)0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Long[] { Long.valueOf(l) });
  }
  
  final class RYG
    extends AsyncTask
  {
    private RYG() {}
    
    private InstalledAppsResult ˊ(Long... paramVarArgs)
    {
      InstalledAppsResult localInstalledAppsResult = new InstalledAppsResult(InsightCore.getInsightConfig().PROJECT_ID(), InsightCore.getGUID());
      localInstalledAppsResult.DeviceInfo = DeviceController.getDeviceInfo(P7.ˊ(P7.this));
      localInstalledAppsResult.TimestampSnapshot = c.a(paramVarArgs[0].longValue());
      localInstalledAppsResult.HostAppPackageName = P7.ˊ(P7.this).getPackageName();
      ArrayList localArrayList1 = new ArrayList();
      PackageManager localPackageManager = P7.ˊ(P7.this).getPackageManager();
      int i;
      label199:
      Object localObject1;
      final InstalledApp localInstalledApp;
      Object localObject2;
      int k;
      int j;
      try
      {
        paramVarArgs = localPackageManager.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, RYG.class });
        if (P7.ˊ(P7.this).checkCallingOrSelfPermission("android.permission.GET_PACKAGE_SIZE") == 0)
        {
          i = 1;
          HashMap localHashMap = new HashMap();
          if ((Build.VERSION.SDK_INT < 23) || (((AppOpsManager)P7.ˊ(P7.this).getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Process.myUid(), P7.ˊ(P7.this).getPackageName()) != 0)) {
            break label890;
          }
          localHashMap = ˊ();
          Iterator localIterator = localPackageManager.getInstalledPackages(4160).iterator();
          if (!localIterator.hasNext()) {
            break label862;
          }
          localObject1 = (PackageInfo)localIterator.next();
          localInstalledApp = new InstalledApp();
          localInstalledApp.PackageName = i.a(((PackageInfo)localObject1).packageName);
          localInstalledApp.AppName = i.a(localPackageManager.getApplicationLabel(((PackageInfo)localObject1).applicationInfo).toString());
          localInstalledApp.FirstInstallTimestamp = c.a(((PackageInfo)localObject1).firstInstallTime);
          localInstalledApp.LastUpdateTimestamp = c.a(((PackageInfo)localObject1).lastUpdateTime);
          localInstalledApp.VersionCode = ((PackageInfo)localObject1).versionCode;
          localInstalledApp.VersionName = i.a(((PackageInfo)localObject1).versionName);
          if ((((PackageInfo)localObject1).applicationInfo.flags & 0x1) == 0) {
            break label492;
          }
          bool = true;
          localInstalledApp.IsSystemApp = bool;
          localInstalledApp.UID = ((PackageInfo)localObject1).applicationInfo.uid;
          if (localHashMap.isEmpty()) {
            break label498;
          }
          localObject2 = (long[])localHashMap.get(Integer.valueOf(localInstalledApp.UID));
          if (localObject2 != null)
          {
            localInstalledApp.TrafficUsageRx = localObject2[0];
            localInstalledApp.TrafficUsageTx = localObject2[1];
          }
          localObject2 = ((PackageInfo)localObject1).signatures;
          if (localObject2 == null) {
            break label554;
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
        ArrayList localArrayList2;
        for (;;)
        {
          Log.e(P7.ˋ(), "IA: " + paramVarArgs.getMessage());
          paramVarArgs = null;
          continue;
          i = 0;
          continue;
          label492:
          boolean bool = false;
          continue;
          label498:
          localInstalledApp.TrafficUsageRx = TrafficStats.getUidRxBytes(((PackageInfo)localObject1).applicationInfo.uid);
          localInstalledApp.TrafficUsageTx = TrafficStats.getUidTxBytes(((PackageInfo)localObject1).applicationInfo.uid);
        }
        localInstalledApp.Signatures = ((String[])localArrayList2.toArray(new String[localArrayList2.size()]));
        label554:
        if (((PackageInfo)localObject1).requestedPermissions != null) {
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
                localInstalledApp.Sha256CheckSum = com.p3group.insight.i.b.a((byte[])localObject2);
              }
            }
          }
          if (((ApplicationInfo)localObject1).nativeLibraryDir != null)
          {
            localObject1 = new File(((ApplicationInfo)localObject1).nativeLibraryDir);
            if ((((File)localObject1).exists()) && (((File)localObject1).isDirectory()))
            {
              l1 = 0L;
              localObject1 = ((File)localObject1).listFiles();
              l2 = l1;
              if (localObject1 != null)
              {
                k = localObject1.length;
                j = 0;
                l2 = l1;
                if (j < k)
                {
                  localObject2 = localObject1[j];
                  l2 = l1;
                  if (!((File)localObject2).isFile()) {
                    break label893;
                  }
                  l2 = l1 + ((File)localObject2).length();
                  break label893;
                }
              }
              localInstalledApp.NativeLibSize = l2;
              if ((paramVarArgs == null) || (i == 0)) {}
            }
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          try
          {
            paramVarArgs.invoke(localPackageManager, new Object[] { localInstalledApp.PackageName, new RYG.RYG()
            {
              public void ˊ(PackageStats paramAnonymousPackageStats)
              {
                localInstalledApp.CacheSize = paramAnonymousPackageStats.cacheSize;
                localInstalledApp.DataSize = paramAnonymousPackageStats.dataSize;
              }
            } });
            localArrayList1.add(localInstalledApp);
            break label199;
            localInstalledApp.NativeLibSize = -1L;
            continue;
            localNameNotFoundException = localNameNotFoundException;
            localNameNotFoundException.printStackTrace();
            continue;
            localInstalledApp.NativeLibSize = -1L;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            continue;
          }
        }
        label862:
        localInstalledAppsResult.InstalledApps = ((InstalledApp[])localArrayList1.toArray(new InstalledApp[localArrayList1.size()]));
        return localInstalledAppsResult;
        label890:
        break;
        label893:
        j += 1;
        long l1 = l2;
      }
    }
    
    @TargetApi(23)
    private HashMap ˊ()
    {
      HashMap localHashMap = new HashMap();
      long l = System.currentTimeMillis() - SystemClock.elapsedRealtime();
      Object localObject1 = (NetworkStatsManager)P7.ˊ(P7.this).getSystemService(NetworkStatsManager.class);
      Object localObject2;
      label238:
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
              break label238;
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
            Log.d(P7.ˋ(), "getUidBytesForAndroidM: " + localException.getMessage());
            return localHashMap;
          }
          tmp188_186 = arrayOfLong;
          tmp188_186[0] = 0L;
          tmp192_188 = tmp188_186;
          tmp192_188[1] = 0L;
          tmp192_188;
          arrayOfLong[0] = localException.getRxBytes();
          arrayOfLong[1] = localException.getTxBytes();
          localHashMap.put(Integer.valueOf(localException.getUid()), arrayOfLong);
        }
        ((NetworkStats)localObject2).close();
        localObject1 = ((NetworkStatsManager)localObject1).querySummary(0, ((TelephonyManager)P7.ˊ(P7.this).getSystemService("phone")).getSubscriberId(), l, System.currentTimeMillis());
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
          Object tmp369_367 = localObject2;
          tmp369_367[0] = 0L;
          Object tmp373_369 = tmp369_367;
          tmp373_369[1] = 0L;
          tmp373_369;
          localObject2[0] = localException.getRxBytes();
          localObject2[1] = localException.getTxBytes();
          localHashMap.put(Integer.valueOf(localException.getUid()), localObject2);
        }
      }
      ((NetworkStats)localObject1).close();
      return localHashMap;
    }
  }
}
