package io.presage.finder;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import io.presage.finder.model.App;
import io.presage.finder.model.CollectionFinderResult;
import io.presage.long.IoriYagami;
import java.util.List;
import java.util.Set;

public class BenimaruNikaido
  implements HeavyD
{
  private Context a;
  
  public BenimaruNikaido(Context paramContext)
  {
    this.a = paramContext;
  }
  
  public IFinderResult a()
  {
    CollectionFinderResult localCollectionFinderResult = new CollectionFinderResult("apps");
    PackageInfo localPackageInfo;
    try
    {
      List localList = this.a.getPackageManager().getInstalledPackages(0);
      int i = 0;
      for (;;)
      {
        if (i >= localList.size()) {
          break label252;
        }
        localPackageInfo = (PackageInfo)localList.get(i);
        if ((localPackageInfo != null) && (localPackageInfo.packageName != null) && (localPackageInfo.applicationInfo != null)) {
          break;
        }
        i += 1;
      }
      localApplicationInfo = localPackageInfo.applicationInfo;
    }
    catch (Exception localException)
    {
      return localCollectionFinderResult;
    }
    for (;;)
    {
      Object localObject2;
      try
      {
        ApplicationInfo localApplicationInfo;
        Object localObject1 = this.a.getPackageManager().getLaunchIntentForPackage(localPackageInfo.packageName);
        if (localObject1 == null) {
          break;
        }
        if (((Intent)localObject1).getCategories().contains("android.intent.category.HOME"))
        {
          bool1 = true;
          localObject1 = new App();
          ((App)localObject1).a(localPackageInfo.packageName);
          ((App)localObject1).b(localPackageInfo.versionName);
          ((App)localObject1).c(String.valueOf(localPackageInfo.versionCode));
          if ((localApplicationInfo.flags & 0x1) != 1) {
            break label247;
          }
          bool2 = true;
          ((App)localObject1).a(bool2);
          ((App)localObject1).b(bool1);
          localCollectionFinderResult.a((IFinderResult)localObject1);
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        IoriYagami.b("finder", localRuntimeException.getMessage(), localRuntimeException);
        localObject2 = null;
        continue;
      }
      if (!localObject2.getCategories().contains("android.intent.category.LAUNCHER")) {
        break;
      }
      boolean bool1 = false;
      continue;
      label247:
      boolean bool2 = false;
    }
    label252:
    return localCollectionFinderResult;
  }
  
  public String b()
  {
    return "apps";
  }
}
