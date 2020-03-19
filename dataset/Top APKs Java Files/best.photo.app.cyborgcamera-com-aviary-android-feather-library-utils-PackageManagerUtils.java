package com.aviary.android.feather.library.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import com.aviary.android.feather.library.content.FeatherIntent.PluginType;
import com.aviary.android.feather.library.log.LoggerFactory;
import com.aviary.android.feather.library.log.LoggerFactory.Logger;
import com.aviary.android.feather.library.log.LoggerFactory.LoggerType;
import com.aviary.android.feather.library.plugins.ApplicationType;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class PackageManagerUtils
{
  private static final LoggerFactory.Logger logger = LoggerFactory.getLogger("package-utils", LoggerFactory.LoggerType.ConsoleLoggerType);
  private static ApplicationInfo mInfo;
  private static PackageInfo mPackageInfo;
  
  public PackageManagerUtils() {}
  
  public static List<ApplicationInfo> fetchInstalledApplications(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledApplications(0);
  }
  
  public static Map<String, ApplicationType> fetchInstalledPlugins(Context paramContext)
  {
    long l1 = System.currentTimeMillis();
    logger.info(new Object[] { "fetchInstalledPlugins" });
    Object localObject1 = fetchInstalledApplications(paramContext);
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    Object localObject2 = ApplicationType.getDefault(paramContext);
    localLinkedHashMap.put(((ApplicationType)localObject2).getPackageName(), localObject2);
    localObject1 = ((List)localObject1).iterator();
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        long l2 = System.currentTimeMillis();
        paramContext = logger;
        localObject1 = new StringBuilder("completed in ");
        ((StringBuilder)localObject1).append(l2 - l1);
        paramContext.log(new Object[] { ((StringBuilder)localObject1).toString() });
        return localLinkedHashMap;
      }
      localObject2 = new ApplicationInfo((ApplicationInfo)((Iterator)localObject1).next()).packageName;
      Object localObject3 = new int[1];
      Object localObject4 = new int[1];
      Object localObject5 = new int[1];
      if (validPackage(paramContext, (String)localObject2, (int[])localObject3, (int[])localObject4, (int[])localObject5))
      {
        PackageInfo localPackageInfo = getPackageInfo(paramContext, (String)localObject2, 0);
        int j = localObject3[0];
        int k = localObject4[0];
        int m = localObject5[0];
        int i;
        if (localPackageInfo != null) {
          i = localPackageInfo.versionCode;
        } else {
          i = 0;
        }
        localObject3 = new ApplicationType((String)localObject2, j, k, m, i);
        localObject4 = logger;
        localObject5 = new StringBuilder("adding: ");
        ((StringBuilder)localObject5).append((String)localObject2);
        ((StringBuilder)localObject5).append(", ");
        ((StringBuilder)localObject5).append(((ApplicationType)localObject3).getPackageVersionCode());
        ((LoggerFactory.Logger)localObject4).log(new Object[] { ((StringBuilder)localObject5).toString() });
        localLinkedHashMap.put(localObject2, localObject3);
      }
    }
  }
  
  public static ApplicationInfo getApplicationInfo(Context paramContext)
  {
    logger.info(new Object[] { "getApplicationInfo" });
    if (mInfo == null)
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      try
      {
        mInfo = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return mInfo;
  }
  
  public static ApplicationInfo getApplicationInfo(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getApplicationInfo(paramString, 0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static int getPackageInfo(Context paramContext, String paramString, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int i2 = 0;
    try
    {
      paramContext = paramContext.getPackageManager().getResourcesForApplication(paramString);
      int i = paramContext.getIdentifier("is_sticker", "integer", paramString);
      if (i != 0) {
        i = paramContext.getInteger(i);
      } else {
        i = 0;
      }
      int j = paramContext.getIdentifier("is_filter", "integer", paramString);
      int m;
      if (j != 0) {
        m = paramContext.getInteger(j);
      } else {
        m = 0;
      }
      j = paramContext.getIdentifier("is_tool", "integer", paramString);
      int k;
      if (j != 0) {
        k = paramContext.getInteger(j);
      } else {
        k = 0;
      }
      j = paramContext.getIdentifier("is_border", "integer", paramString);
      if (j != 0) {
        j = paramContext.getInteger(j);
      } else {
        j = 0;
      }
      int n = paramContext.getIdentifier("sticker_version", "integer", paramString);
      if (n != 0) {
        n = paramContext.getInteger(n);
      } else {
        n = 0;
      }
      int i1 = paramContext.getIdentifier("border_version", "integer", paramString);
      if (i1 != 0) {
        i1 = paramContext.getInteger(i1);
      } else {
        i1 = 0;
      }
      paramArrayOfInt1[0] = n;
      paramArrayOfInt2[0] = i1;
      if (m == 1) {
        m = 1;
      } else {
        m = 0;
      }
      if (i == 1) {
        i = 2;
      } else {
        i = 0;
      }
      if (k == 1) {
        k = 8;
      } else {
        k = 0;
      }
      n = i2;
      if (j == 1) {
        n = 4;
      }
      return m | i | k | n;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static PackageInfo getPackageInfo(Context paramContext)
  {
    logger.info(new Object[] { "getPackageInfo" });
    if (mPackageInfo == null)
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      try
      {
        mPackageInfo = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0);
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return mPackageInfo;
  }
  
  public static PackageInfo getPackageInfo(Context paramContext, int paramInt)
  {
    return getPackageInfo(paramContext, paramContext.getPackageName(), paramInt);
  }
  
  public static PackageInfo getPackageInfo(Context paramContext, String paramString, int paramInt)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, paramInt);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static boolean isStandalone(Context paramContext)
  {
    return "com.aviary.android.feather".equals(paramContext.getPackageName());
  }
  
  public static boolean validPackage(Context paramContext, String paramString, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
  {
    if (!validPackageName(paramString)) {
      return false;
    }
    paramArrayOfInt1[0] = getPackageInfo(paramContext, paramString, paramArrayOfInt2, paramArrayOfInt3);
    boolean bool1 = FeatherIntent.PluginType.isSticker(paramArrayOfInt1[0]);
    boolean bool2 = FeatherIntent.PluginType.isFilter(paramArrayOfInt1[0]);
    boolean bool3 = FeatherIntent.PluginType.isTool(paramArrayOfInt1[0]);
    boolean bool4 = FeatherIntent.PluginType.isBorder(paramArrayOfInt1[0]);
    return (bool1) || (bool2) || (bool3) || (bool4);
  }
  
  public static boolean validPackageName(String paramString)
  {
    return paramString.startsWith("com.aviary.android.feather.plugins.");
  }
}
