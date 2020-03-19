package cmn;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

final class j
  implements Callable
{
  j(Context paramContext) {}
  
  private List a()
  {
    Object localObject = this.a.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getInstalledPackages(0);
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return Collections.emptyList();
  }
}
