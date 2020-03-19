package c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.content.pm.Signature;
import android.net.TrafficStats;
import android.os.AsyncTask;
import android.util.Log;
import com.p3group.insight.InsightConfig;
import com.p3group.insight.InsightCore;
import com.p3group.insight.controller.DeviceController;
import com.p3group.insight.i.c;
import com.p3group.insight.i.h;
import com.p3group.insight.results.InstalledAppsResult;
import com.p3group.insight.results.appusage.InstalledApp;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class lLt
{
  private static final String a = lLt.class.getSimpleName();
  private final Context b;
  private final com.p3group.insight.b c;
  private boolean d;
  private boolean e = false;
  
  public lLt(Context paramContext)
  {
    this.b = paramContext;
    this.c = new com.p3group.insight.b(paramContext);
  }
  
  public final void a()
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
      } while (!localB.a());
      l = com.p3group.insight.g.b.a(this.b);
    } while (l - this.c.c() <= localB.b());
    this.d = true;
    new lll((byte)0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Long[] { Long.valueOf(l) });
  }
  
  final class lll
    extends AsyncTask
  {
    private lll() {}
    
    private InstalledAppsResult a(Long... paramVarArgs)
    {
      InstalledAppsResult localInstalledAppsResult = new InstalledAppsResult(InsightCore.getInsightConfig().PROJECT_ID(), InsightCore.getGUID());
      localInstalledAppsResult.DeviceInfo = DeviceController.getDeviceInfo(lLt.a(lLt.this));
      localInstalledAppsResult.TimestampSnapshot = c.a(paramVarArgs[0].longValue());
      ArrayList localArrayList1 = new ArrayList();
      PackageManager localPackageManager = lLt.a(lLt.this).getPackageManager();
      int i;
      Object localObject1;
      final InstalledApp localInstalledApp;
      Object localObject2;
      int k;
      int j;
      try
      {
        paramVarArgs = localPackageManager.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, lll.class });
        if (lLt.a(lLt.this).checkCallingOrSelfPermission("android.permission.GET_PACKAGE_SIZE") == 0)
        {
          i = 1;
          Iterator localIterator = localPackageManager.getInstalledPackages(4160).iterator();
          if (!localIterator.hasNext()) {
            break label731;
          }
          localObject1 = (PackageInfo)localIterator.next();
          localInstalledApp = new InstalledApp();
          localInstalledApp.PackageName = h.a(((PackageInfo)localObject1).packageName);
          localInstalledApp.AppName = h.a(localPackageManager.getApplicationLabel(((PackageInfo)localObject1).applicationInfo).toString());
          localInstalledApp.FirstInstallTimestamp = c.a(((PackageInfo)localObject1).firstInstallTime);
          localInstalledApp.LastUpdateTimestamp = c.a(((PackageInfo)localObject1).lastUpdateTime);
          localInstalledApp.VersionCode = ((PackageInfo)localObject1).versionCode;
          localInstalledApp.VersionName = h.a(((PackageInfo)localObject1).versionName);
          if ((((PackageInfo)localObject1).applicationInfo.flags & 0x1) == 0) {
            break label400;
          }
          bool = true;
          localInstalledApp.IsSystemApp = bool;
          localInstalledApp.TrafficUsageRx = TrafficStats.getUidRxBytes(((PackageInfo)localObject1).applicationInfo.uid);
          localInstalledApp.TrafficUsageTx = TrafficStats.getUidTxBytes(((PackageInfo)localObject1).applicationInfo.uid);
          localInstalledApp.UID = ((PackageInfo)localObject1).applicationInfo.uid;
          localObject2 = ((PackageInfo)localObject1).signatures;
          if (localObject2 == null) {
            break label427;
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
          Log.e(lLt.b(), "IA: " + paramVarArgs.getMessage());
          paramVarArgs = null;
          continue;
          i = 0;
          continue;
          label400:
          boolean bool = false;
        }
        localInstalledApp.Signatures = ((String[])localArrayList2.toArray(new String[localArrayList2.size()]));
        label427:
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
                    break label759;
                  }
                  l2 = l1 + ((File)localObject2).length();
                  break label759;
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
            paramVarArgs.invoke(localPackageManager, new Object[] { localInstalledApp.PackageName, new lll.lll()
            {
              public final void a(PackageStats paramAnonymousPackageStats)
              {
                localInstalledApp.CacheSize = paramAnonymousPackageStats.cacheSize;
                localInstalledApp.DataSize = paramAnonymousPackageStats.dataSize;
              }
            } });
            localArrayList1.add(localInstalledApp);
            break;
            localInstalledApp.NativeLibSize = 0L;
            continue;
            localNameNotFoundException = localNameNotFoundException;
            localNameNotFoundException.printStackTrace();
            continue;
            localInstalledApp.NativeLibSize = 0L;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            continue;
          }
        }
        label731:
        localInstalledAppsResult.InstalledApps = ((InstalledApp[])localArrayList1.toArray(new InstalledApp[localArrayList1.size()]));
        return localInstalledAppsResult;
        label759:
        j += 1;
        long l1 = l2;
      }
    }
  }
}
