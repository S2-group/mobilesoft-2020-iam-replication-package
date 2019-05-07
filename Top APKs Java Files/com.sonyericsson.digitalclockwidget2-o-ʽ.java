package o;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class ʽ
  implements Callable
{
  public ʽ(Context paramContext) {}
  
  private List ˊ()
  {
    Object localObject = this.ˊ.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getInstalledPackages(0);
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return new ArrayList();
  }
}
