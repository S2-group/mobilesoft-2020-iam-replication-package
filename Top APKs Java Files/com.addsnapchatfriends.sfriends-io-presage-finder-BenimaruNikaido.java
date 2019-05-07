package io.presage.finder;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import io.presage.finder.model.App;
import io.presage.finder.model.CollectionFinderResult;
import io.presage.goto.SaishuKusanagi;
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
    try
    {
      List localList = this.a.getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        if ((localPackageInfo != null) && (localPackageInfo.packageName != null) && (localPackageInfo.applicationInfo != null))
        {
          Object localObject = null;
          ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
          try
          {
            Intent localIntent = this.a.getPackageManager().getLaunchIntentForPackage(localPackageInfo.packageName);
            localObject = localIntent;
          }
          catch (RuntimeException localRuntimeException)
          {
            SaishuKusanagi.b("finder", localRuntimeException.getMessage(), localRuntimeException);
          }
          if (localObject != null)
          {
            boolean bool1 = ((Intent)localObject).getCategories().contains("android.intent.category.HOME");
            boolean bool2 = true;
            if (bool1)
            {
              bool1 = true;
            }
            else
            {
              if (!((Intent)localObject).getCategories().contains("android.intent.category.LAUNCHER")) {
                break label252;
              }
              bool1 = false;
            }
            localObject = new App();
            ((App)localObject).a(localPackageInfo.packageName);
            ((App)localObject).b(localPackageInfo.versionName);
            ((App)localObject).c(String.valueOf(localPackageInfo.versionCode));
            if ((localApplicationInfo.flags & 0x1) != 1) {
              bool2 = false;
            }
            ((App)localObject).a(bool2);
            ((App)localObject).b(bool1);
            localCollectionFinderResult.a((IFinderResult)localObject);
          }
        }
        label252:
        i += 1;
      }
      return localCollectionFinderResult;
    }
    catch (Exception localException) {}
    return localCollectionFinderResult;
  }
  
  public String b()
  {
    return "apps";
  }
}
