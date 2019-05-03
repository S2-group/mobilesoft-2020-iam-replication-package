package walking.weightloss.walk.tracker.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class j
{
  private static j a;
  
  private j() {}
  
  public static j a()
  {
    try
    {
      if (a == null) {
        a = new j();
      }
      j localJ = a;
      return localJ;
    }
    finally {}
  }
  
  public boolean a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        boolean bool;
        do
        {
          PackageInfo localPackageInfo;
          do
          {
            if (!paramContext.hasNext()) {
              break;
            }
            localPackageInfo = (PackageInfo)paramContext.next();
          } while (TextUtils.isEmpty(localPackageInfo.packageName));
          bool = localPackageInfo.packageName.equals("com.google.android.gm");
        } while (!bool);
        return true;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    catch (Error paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public boolean b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        boolean bool;
        do
        {
          PackageInfo localPackageInfo;
          do
          {
            if (!paramContext.hasNext()) {
              break;
            }
            localPackageInfo = (PackageInfo)paramContext.next();
          } while (TextUtils.isEmpty(localPackageInfo.packageName));
          bool = localPackageInfo.packageName.equals("com.android.email");
        } while (!bool);
        return true;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    catch (Error paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
}
