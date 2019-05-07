package com.wefi.sdk.common;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.wefi.base.BaseLogger;
import com.wefi.base.LogChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeFiPackageInfoUtil
{
  protected static final LogChannel LOG = ;
  private static final String WEFI_MAIN_ACTIVITY_NAME = ".WeFiApp";
  private static final String WEFI_PREFIX_PACKAGE_NAME = "com.wefi";
  
  public WeFiPackageInfoUtil() {}
  
  private static List<String> GetWeFiPackages(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      if (localApplicationInfo.packageName.startsWith("com.wefi"))
      {
        LOG.d(new Object[] { "WeFiPackageInfoUtil found wefi package ", localApplicationInfo.packageName });
        localArrayList.add(localApplicationInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public static int compareEngineVersions(String paramString1, String paramString2)
  {
    int j;
    if ((paramString1 == null) || (paramString2 == null)) {
      j = -2;
    }
    int m;
    int n;
    label119:
    label130:
    label139:
    do
    {
      return j;
      if (paramString1.equalsIgnoreCase(paramString2)) {
        return 0;
      }
      int i1 = 0;
      paramString1 = paramString1.split("\\.");
      paramString2 = paramString2.split("\\.");
      m = paramString1.length;
      n = paramString2.length;
      j = m;
      if (m > n) {
        j = n;
      }
      int k = 0;
      int i;
      for (;;)
      {
        i = i1;
        int i2;
        if (k < j)
        {
          i = Integer.valueOf(paramString1[k]).intValue();
          i2 = Integer.valueOf(paramString2[k]).intValue();
          if (i <= i2) {
            break label119;
          }
        }
        for (i = 1;; i = -1)
        {
          j = i;
          if (i != 0) {
            break;
          }
          if (m <= n) {
            break label139;
          }
          return 1;
          if (i2 <= i) {
            break label130;
          }
        }
        k += 1;
      }
      j = i;
    } while (n <= m);
    return -1;
  }
  
  public static ArrayList<String> getMissingPackages(Context paramContext, ArrayList<String> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramArrayList == null) || ((paramArrayList != null) && (paramArrayList.size() == 0))) {}
    for (;;)
    {
      return localArrayList;
      localArrayList.addAll(paramArrayList);
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if (paramArrayList.contains(localPackageInfo.packageName)) {
          localArrayList.remove(localPackageInfo.packageName);
        }
      }
    }
  }
  
  private static PackageInfo getOtherWeFiPkgInfo(Context paramContext, String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    int i = 0;
    int j = 0;
    try
    {
      Iterator localIterator = GetWeFiPackages(paramContext).iterator();
      String str;
      PackageInfo localPackageInfo;
      ActivityInfo[] arrayOfActivityInfo;
      do
      {
        localObject1 = localObject2;
        i = j;
        localObject3 = localObject2;
        k = j;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject1 = localObject2;
        i = j;
        str = (String)localIterator.next();
        localObject1 = localObject2;
        i = j;
        localPackageInfo = paramContext.getPackageManager().getPackageInfo(str, 1);
        localObject1 = localObject2;
        i = j;
        arrayOfActivityInfo = localPackageInfo.activities;
      } while (arrayOfActivityInfo == null);
      localObject1 = localObject2;
      i = j;
      int n = arrayOfActivityInfo.length;
      int m = 0;
      int k = j;
      Object localObject3 = localObject2;
      for (;;)
      {
        localObject2 = localObject3;
        j = k;
        if (m >= n) {
          break;
        }
        localObject2 = localObject3;
        j = k;
        localObject1 = localObject3;
        i = k;
        if (arrayOfActivityInfo[m].name.indexOf(".WeFiApp") != -1)
        {
          localObject1 = localObject3;
          i = k;
          LOG.d(new Object[] { "WeFiPackageInfoUtil found package ", str, " with wefi activity" });
          k += 1;
          localObject1 = localObject3;
          i = k;
          boolean bool = str.equalsIgnoreCase(paramString);
          localObject2 = localObject3;
          j = k;
          if (!bool)
          {
            localObject2 = localPackageInfo;
            j = k;
          }
        }
        m += 1;
        localObject3 = localObject2;
        k = j;
      }
      return localObject3;
    }
    catch (Exception paramContext)
    {
      LOG.e(new Object[] { Log.getStackTraceString(paramContext) });
      k = i;
      localObject3 = localObject1;
      if (k == 1) {
        localObject3 = null;
      }
    }
  }
  
  public static PackageInfo getWFF_1_3_PackageInfo(Context paramContext)
  {
    Object localObject = null;
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    do
    {
      paramContext = localObject;
      if (!localIterator.hasNext()) {
        break;
      }
      paramContext = (PackageInfo)localIterator.next();
    } while (!paramContext.packageName.equalsIgnoreCase("com.timewarnercable.wififinder"));
    if (paramContext != null)
    {
      int i = compareEngineVersions(paramContext.versionName, "3.1");
      LOG.d(new Object[] { "MultiInst getWFF_1_3_PackageInfo - compare= ", Integer.valueOf(i), ",wffInfo.versionName=", paramContext.versionName, ",wffVersion=", "3.1" });
      if (i == -1) {
        return paramContext;
      }
    }
    return null;
  }
  
  public static String getWeFiAlreadyInstalledPackageName(Context paramContext)
  {
    Object localObject = null;
    PackageInfo localPackageInfo = getOtherWeFiPkgInfo(paramContext, paramContext.getPackageName());
    paramContext = localObject;
    if (localPackageInfo != null) {
      paramContext = localPackageInfo.packageName;
    }
    return paramContext;
  }
  
  public static boolean isPackageInstalled(Context paramContext, String paramString)
  {
    if ((paramString == null) || ((paramString != null) && (paramString.length() == 0))) {}
    do
    {
      while (!paramContext.hasNext())
      {
        return false;
        paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      }
    } while (!((PackageInfo)paramContext.next()).packageName.equalsIgnoreCase(paramString));
    return true;
  }
}
