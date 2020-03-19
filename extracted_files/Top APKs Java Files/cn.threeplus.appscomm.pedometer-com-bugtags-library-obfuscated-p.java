package com.bugtags.library.obfuscated;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class p
{
  public static boolean b(Context paramContext, String paramString)
  {
    return c(paramContext).contains(paramString);
  }
  
  public static List<String> c(Context paramContext)
  {
    Object localObject1 = new ArrayList();
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(4096).iterator();
    if (localIterator.hasNext())
    {
      Object localObject2 = (PackageInfo)localIterator.next();
      if (!((PackageInfo)localObject2).packageName.equals(paramContext.getPackageName())) {
        break label81;
      }
      localObject2 = ((PackageInfo)localObject2).requestedPermissions;
      if ((localObject2 == null) || (localObject2.length <= 0)) {
        break label81;
      }
      localObject1 = Arrays.asList((Object[])localObject2);
    }
    label81:
    for (;;)
    {
      break;
      return localObject1;
    }
  }
}
