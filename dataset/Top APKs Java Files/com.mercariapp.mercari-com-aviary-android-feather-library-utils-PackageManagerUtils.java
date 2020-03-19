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
    Object localObject3;
    int[] arrayOfInt1;
    int[] arrayOfInt2;
    do
    {
      if (!((Iterator)localObject1).hasNext())
      {
        long l2 = System.currentTimeMillis();
        logger.log(new Object[] { "completed in " + (l2 - l1) });
        return localLinkedHashMap;
      }
      localObject2 = new ApplicationInfo((ApplicationInfo)((Iterator)localObject1).next()).packageName;
      localObject3 = new int[1];
      arrayOfInt1 = new int[1];
      arrayOfInt2 = new int[1];
    } while (!validPackage(paramContext, (String)localObject2, (int[])localObject3, arrayOfInt1, arrayOfInt2));
    PackageInfo localPackageInfo = getPackageInfo(paramContext, (String)localObject2, 0);
    int j = localObject3[0];
    int k = arrayOfInt1[0];
    int m = arrayOfInt2[0];
    if (localPackageInfo != null) {}
    for (int i = localPackageInfo.versionCode;; i = 0)
    {
      localObject3 = new ApplicationType((String)localObject2, j, k, m, i);
      logger.log(new Object[] { "adding: " + (String)localObject2 + ", " + ((ApplicationType)localObject3).getPackageVersionCode() });
      localLinkedHashMap.put(localObject2, localObject3);
      break;
    }
  }
  
  public static ApplicationInfo getApplicationInfo(Context paramContext)
  {
    logger.info(new Object[] { "getApplicationInfo" });
    PackageManager localPackageManager;
    if (mInfo == null) {
      localPackageManager = paramContext.getPackageManager();
    }
    try
    {
      mInfo = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
      return mInfo;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
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
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getResourcesForApplication(paramString);
        i = paramContext.getIdentifier("is_sticker", "integer", paramString);
        if (i == 0) {
          break label274;
        }
        i = paramContext.getInteger(i);
        j = paramContext.getIdentifier("is_filter", "integer", paramString);
        if (j == 0) {
          break label268;
        }
        m = paramContext.getInteger(j);
        j = paramContext.getIdentifier("is_tool", "integer", paramString);
        if (j == 0) {
          break label262;
        }
        k = paramContext.getInteger(j);
        j = paramContext.getIdentifier("is_border", "integer", paramString);
        if (j == 0) {
          break label256;
        }
        j = paramContext.getInteger(j);
        n = paramContext.getIdentifier("sticker_version", "integer", paramString);
        if (n == 0) {
          break label250;
        }
        n = paramContext.getInteger(n);
        i1 = paramContext.getIdentifier("border_version", "integer", paramString);
        if (i1 == 0) {
          break label244;
        }
        i1 = paramContext.getInteger(i1);
        paramArrayOfInt1[0] = n;
        paramArrayOfInt2[0] = i1;
        if (m == 1)
        {
          m = 1;
          if (i != 1) {
            break label232;
          }
          i = 2;
          if (k != 1) {
            break label238;
          }
          k = 8;
          n = i2;
          if (j == 1) {
            n = 4;
          }
          return n | k | m | i;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        return 0;
      }
      int m = 0;
      continue;
      label232:
      int i = 0;
      continue;
      label238:
      int k = 0;
      continue;
      label244:
      int i1 = 0;
      continue;
      label250:
      int n = 0;
      continue;
      label256:
      int j = 0;
      continue;
      label262:
      k = 0;
      continue;
      label268:
      m = 0;
      continue;
      label274:
      i = 0;
    }
  }
  
  public static PackageInfo getPackageInfo(Context paramContext)
  {
    logger.info(new Object[] { "getPackageInfo" });
    PackageManager localPackageManager;
    if (mPackageInfo == null) {
      localPackageManager = paramContext.getPackageManager();
    }
    try
    {
      mPackageInfo = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0);
      return mPackageInfo;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
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
  
  public static boolean validPackage(Context paramContext, String paramString, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
  {
    if (!validPackageName(paramString)) {}
    boolean bool1;
    boolean bool2;
    boolean bool3;
    boolean bool4;
    do
    {
      return false;
      paramArrayOfInt1[0] = getPackageInfo(paramContext, paramString, paramArrayOfInt2, paramArrayOfInt3);
      bool1 = FeatherIntent.PluginType.isSticker(paramArrayOfInt1[0]);
      bool2 = FeatherIntent.PluginType.isFilter(paramArrayOfInt1[0]);
      bool3 = FeatherIntent.PluginType.isTool(paramArrayOfInt1[0]);
      bool4 = FeatherIntent.PluginType.isBorder(paramArrayOfInt1[0]);
    } while ((!bool1) && (!bool2) && (!bool3) && (!bool4));
    return true;
  }
  
  public static boolean validPackageName(String paramString)
  {
    return paramString.startsWith("com.aviary.android.feather.plugins.");
  }
}
