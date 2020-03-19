package o;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

final class ᑊ
  implements Callable
{
  ᑊ(Context paramContext, ᐩ.if paramIf) {}
  
  private ArrayList ˊ()
  {
    ArrayList localArrayList = new ArrayList();
    label281:
    label286:
    label291:
    for (;;)
    {
      try
      {
        PackageManager localPackageManager = this.ˊ.getPackageManager();
        Iterator localIterator = ((ActivityManager)this.ˊ.getSystemService("activity")).getRunningAppProcesses().iterator();
        Object localObject;
        if (localIterator.hasNext())
        {
          localObject = (ActivityManager.RunningAppProcessInfo)localIterator.next();
          if ((((ActivityManager.RunningAppProcessInfo)localObject).pkgList.length > 0) && ("com.android.vending".equals(localPackageManager.getInstallerPackageName(localObject.pkgList[0])))) {
            localArrayList.add(localObject);
          }
        }
        else
        {
          localIterator = localPackageManager.getInstalledPackages(0).iterator();
          if (localIterator.hasNext())
          {
            localObject = (PackageInfo)localIterator.next();
            ᐩ.if localIf = this.ˋ;
            String str = localPackageManager.getInstallerPackageName(((PackageInfo)localObject).packageName);
            if ((ᐩ.ˏ() != null) && ((str == null) || (!ᐩ.ˏ().contains(str)))) {
              break label291;
            }
            long l = ᐩ.ᐝ().ˊ((PackageInfo)localObject);
            if (l > localIf.ˎ) {
              localIf.ˋ.add(localObject);
            }
            if ((l <= 0L) || (l >= localIf.ˊ)) {
              break label291;
            }
            if (((PackageInfo)localObject).applicationInfo == null) {
              break label281;
            }
            if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) == 0) {
              break label286;
            }
            break label281;
            if (i != 0) {
              break label291;
            }
            localIf.ˊ = l;
            break label291;
          }
          return localArrayList;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        return null;
      }
      continue;
      int i = 1;
      continue;
      i = 0;
    }
  }
}
