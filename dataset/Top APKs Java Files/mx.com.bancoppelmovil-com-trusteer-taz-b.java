package com.trusteer.taz;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import com.trusteer.taz.c.f;
import com.trusteer.taz.c.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class b
  extends e
{
  b() {}
  
  private static boolean a(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return false;
    }
    return Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps", 0) > 0;
  }
  
  private static f[] b(Context paramContext)
  {
    paramContext = g.a(paramContext, "android.intent.action.BOOT_COMPLETED");
    int i = paramContext.size();
    if (i > 0)
    {
      f[] arrayOfF = new f[i];
      paramContext.toArray(arrayOfF);
      return arrayOfF;
    }
    return null;
  }
  
  private static String[] c(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0);
    paramContext = new ArrayList(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramContext.add(((PackageInfo)((Iterator)localObject).next()).packageName);
    }
    return (String[])paramContext.toArray(new String[paramContext.size()]);
  }
}
