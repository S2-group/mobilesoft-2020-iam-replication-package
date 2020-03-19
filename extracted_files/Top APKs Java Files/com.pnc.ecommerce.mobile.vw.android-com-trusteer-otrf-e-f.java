package com.trusteer.otrf.e;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class f
  extends j
{
  f() {}
  
  private static String a(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getInstallerPackageName(paramString);
  }
  
  private static boolean a(PackageInfo paramPackageInfo, String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return true;
    }
    if (paramPackageInfo.requestedPermissions != null)
    {
      paramPackageInfo = paramPackageInfo.requestedPermissions;
      int j = paramPackageInfo.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label52;
        }
        if (paramPackageInfo[i].contains(paramString)) {
          break;
        }
        i += 1;
      }
    }
    label52:
    return false;
  }
  
  private static boolean a(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.length() == 0) || (paramString2 == null) || (paramString2.length() == 0)) {}
    label105:
    int m;
    int n;
    do
    {
      return false;
      paramString1 = paramString1.toLowerCase();
      paramString2 = paramString2.toLowerCase();
      int k = 0;
      int j = 0;
      for (;;)
      {
        if ((j >= paramString1.length()) || (k >= paramString2.length()) || (paramString2.charAt(k) == '*')) {
          break label105;
        }
        if ((paramString1.charAt(j) != paramString2.charAt(k)) && (paramString2.charAt(k) != '?')) {
          break;
        }
        k += 1;
        j += 1;
      }
      int i = k;
      m = j;
      for (;;)
      {
        n = i;
        if (m >= paramString1.length()) {
          break;
        }
        if ((i < paramString2.length()) && (paramString2.charAt(i) == '*'))
        {
          k = i + 1;
          if (k == paramString2.length()) {
            return true;
          }
          j = m + 1;
          i = k;
        }
        else if ((i < paramString2.length()) && ((paramString2.charAt(i) == '?') || (paramString1.charAt(m) == paramString2.charAt(i))))
        {
          i += 1;
          m += 1;
        }
        else
        {
          m = j;
          j += 1;
          i = k;
        }
      }
      while (paramString2.charAt(n) == '*') {
        n += 1;
      }
    } while ((m != paramString1.length()) || (n < paramString2.length()));
    return true;
  }
  
  private static String[] a(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(12288);
    paramContext = new ArrayList(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      boolean bool1 = a(localPackageInfo.packageName, paramString1);
      boolean bool2 = a(localPackageInfo, paramString2);
      if ((bool1) && (bool2)) {
        paramContext.add(localPackageInfo.packageName);
      }
    }
    return (String[])paramContext.toArray(new String[paramContext.size()]);
  }
}
