package com.avast.android.sdk.engine.obfuscated;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public class bq
{
  public static List<ApplicationInfo> a(PackageManager paramPackageManager, String... paramVarArgs)
  {
    paramPackageManager = paramPackageManager.getInstalledApplications(0);
    Iterator localIterator = paramPackageManager.iterator();
    label135:
    while (localIterator.hasNext())
    {
      Object localObject = (ApplicationInfo)localIterator.next();
      if (((ApplicationInfo)localObject).sourceDir.startsWith("/system"))
      {
        localIterator.remove();
      }
      else if (paramVarArgs != null)
      {
        int j = paramVarArgs.length;
        int i = 0;
        for (;;)
        {
          if (i >= j) {
            break label135;
          }
          String str = paramVarArgs[i];
          if (((ApplicationInfo)localObject).packageName.equals(str))
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("Skipping: ");
            ((StringBuilder)localObject).append(str);
            ar.a(((StringBuilder)localObject).toString());
            break;
          }
          i += 1;
        }
      }
    }
    return paramPackageManager;
  }
}
