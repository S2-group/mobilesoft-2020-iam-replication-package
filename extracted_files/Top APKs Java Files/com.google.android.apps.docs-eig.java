import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.apps.docs.drive.backup.ApplicationBackupInfo;
import com.google.android.gms.backup.ApplicationBackupStats;
import com.google.android.gms.backup.BackupStatsRequestConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class eig
{
  public final jbd a;
  private jbe b;
  private PackageManager c;
  
  @lzy
  public eig(Context paramContext)
  {
    this.c = paramContext.getPackageManager();
    this.b = new jbe(paramContext);
    this.a = new jbd(paramContext);
  }
  
  public final List<ApplicationBackupInfo> a()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = this.b.a(new BackupStatsRequestConfig());
    HashMap localHashMap = new HashMap();
    int j;
    int i;
    Object localObject2;
    if (localObject1 != null)
    {
      j = localObject1.length;
      i = 0;
      while (i < j)
      {
        localObject2 = localObject1[i];
        localHashMap.put(((ApplicationBackupStats)localObject2).a, localObject2);
        i += 1;
      }
    }
    localObject1 = this.c.getInstalledPackages(0).iterator();
    do
    {
      if (!((Iterator)localObject1).hasNext()) {
        break;
      }
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
    } while (!localHashMap.containsKey(((PackageInfo)localObject2).packageName));
    for (;;)
    {
      try
      {
        localObject3 = this.c.getApplicationInfo(((PackageInfo)localObject2).packageName, 0);
        localApplicationBackupInfo = new ApplicationBackupInfo(this.c.getApplicationLabel((ApplicationInfo)localObject3).toString());
        localObject3 = ((ApplicationInfo)localObject3).packageName;
        String str = localApplicationBackupInfo.d;
        if (str == localObject3) {
          break label346;
        }
        if ((str == null) || (!str.equals(localObject3))) {
          break label336;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Object localObject3;
        ApplicationBackupInfo localApplicationBackupInfo;
        localObject2 = ((PackageInfo)localObject2).packageName;
      }
      if (i == 0)
      {
        localApplicationBackupInfo.d = ((String)localObject3);
        localApplicationBackupInfo.f = null;
      }
      localApplicationBackupInfo.c = true;
      localApplicationBackupInfo.b = Long.valueOf(((ApplicationBackupStats)localHashMap.get(((PackageInfo)localObject2).packageName)).d);
      i = ((ApplicationBackupStats)localHashMap.get(((PackageInfo)localObject2).packageName)).b;
      j = ((ApplicationBackupStats)localHashMap.get(((PackageInfo)localObject2).packageName)).c;
      localObject3 = localApplicationBackupInfo.a;
      if (i == 0)
      {
        i = j;
        localApplicationBackupInfo.e = i;
        localArrayList.add(localApplicationBackupInfo);
        break;
        break;
        label336:
        i = 0;
        continue;
      }
      continue;
      return localArrayList;
      label346:
      i = 1;
    }
  }
}
