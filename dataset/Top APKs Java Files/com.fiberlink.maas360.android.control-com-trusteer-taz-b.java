package com.trusteer.taz;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import com.trusteer.taz.b.c;
import com.trusteer.taz.b.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class b
  extends e
{
  b() {}
  
  private static boolean a(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 17) {
      if (Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps", 0) <= 0) {}
    }
    while (Settings.Global.getInt(paramContext.getContentResolver(), "install_non_market_apps", 0) > 0)
    {
      return true;
      return false;
    }
    return false;
  }
  
  private static c[] b(Context paramContext)
  {
    Object localObject = null;
    ArrayList localArrayList = d.a(paramContext, "android.intent.action.BOOT_COMPLETED");
    int i = localArrayList.size();
    paramContext = localObject;
    if (i > 0)
    {
      paramContext = new c[i];
      localArrayList.toArray(paramContext);
    }
    return paramContext;
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
