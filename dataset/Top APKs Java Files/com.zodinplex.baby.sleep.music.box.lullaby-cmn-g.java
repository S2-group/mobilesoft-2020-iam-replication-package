package cmn;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

final class g
  implements Callable
{
  g(Context paramContext) {}
  
  private List a()
  {
    Object localObject = this.a.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getInstalledPackages(this.b);
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return new ArrayList();
  }
}
