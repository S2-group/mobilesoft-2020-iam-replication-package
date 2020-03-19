package g;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.List;

public class b
{
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if ((paramContext != null) && (paramContext.size() > 0))
    {
      int i = 0;
      boolean bool1 = false;
      for (;;)
      {
        bool2 = bool1;
        if (i >= paramContext.size()) {
          break;
        }
        bool2 = bool1;
        if (bool1) {
          break;
        }
        if (paramString.equals(((PackageInfo)paramContext.get(i)).packageName)) {
          bool1 = true;
        }
        i += 1;
      }
    }
    boolean bool2 = false;
    return bool2;
  }
}
