package com.arcane.incognito.service;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

public final class l
  extends a
  implements e
{
  private final Context a;
  
  public l(Context paramContext)
  {
    this.a = paramContext;
  }
  
  public final List<String> a(String paramString, List<String> paramList)
  {
    paramString = paramString.toLowerCase();
    PackageManager localPackageManager = this.a.getPackageManager();
    List localList = localPackageManager.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    while (j < localList.size())
    {
      Object localObject = (PackageInfo)localList.get(j);
      String str1 = ((PackageInfo)localObject).packageName;
      if (str1 != null)
      {
        boolean bool = str1.toLowerCase().contains(paramString);
        int m = 1;
        int k = m;
        if (!bool) {
          if (a_(str1, paramList))
          {
            k = m;
          }
          else
          {
            int i;
            try
            {
              String str2 = (String)localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(str1, 0));
              if ((str2 == null) || (!str2.toLowerCase().contains(paramString)))
              {
                bool = a_(str2, paramList);
                if (!bool) {}
              }
              else
              {
                i = 1;
              }
            }
            catch (Exception localException)
            {
              b.a.a.a(localException, "could not get application name", new Object[0]);
              i = 0;
            }
            localObject = ((PackageInfo)localObject).applicationInfo.processName;
            if (localObject != null)
            {
              k = m;
              if (((String)localObject).toLowerCase().contains(paramString)) {}
            }
            else if (a_((String)localObject, paramList))
            {
              k = m;
            }
            else
            {
              k = i;
            }
          }
        }
        if (k != 0) {
          localArrayList.add(str1);
        }
      }
      j += 1;
    }
    return localArrayList;
  }
}
