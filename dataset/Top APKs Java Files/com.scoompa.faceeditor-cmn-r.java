package cmn;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public final class r
{
  public static List a(Context paramContext)
  {
    paramContext = new FutureTask(new Callable()
    {
      private List a()
      {
        Object localObject = r.this.getPackageManager();
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
    });
    at.b(paramContext);
    try
    {
      paramContext = (List)paramContext.get(10L, TimeUnit.SECONDS);
      Object localObject = paramContext;
      if (paramContext == null) {
        localObject = Collections.emptyList();
      }
      return localObject;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        bool1 = bool2;
        if (paramContext.versionCode >= -1) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
}
