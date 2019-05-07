package com.aviary.android.feather.library.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.util.Log;
import com.aviary.android.feather.library.content.FeatherIntent.PluginType;
import com.aviary.android.feather.library.log.LoggerFactory;
import com.aviary.android.feather.library.plugins.ApplicationType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class PackageManagerUtils
{
  private static String CONTENT_BASE_URL;
  private static final String CONTENT_BASE_URL_DEBUG = "http://testassets.aviary.com.s3.amazonaws.com";
  private static final String CONTENT_BASE_URL_RELEASE = "http://assets.aviary.com";
  private static final String CONTENT_JSON_NAME = "assets_android_v06.json";
  private static final String LOG_TAG = "PackageManagerUtils";
  private static ApplicationInfo mInfo;
  private static PackageInfo mPackageInfo;
  
  public PackageManagerUtils() {}
  
  public static List<ApplicationInfo> fetchInstalledApplications(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledApplications(0);
  }
  
  public static Map<String, ApplicationType> fetchInstalledPlugins(Context paramContext, int paramInt)
  {
    Object localObject1;
    LinkedHashMap localLinkedHashMap;
    Object localObject2;
    if (paramInt > 0)
    {
      localObject1 = fetchInstalledApplications(paramContext);
      localLinkedHashMap = new LinkedHashMap();
      localObject2 = ApplicationType.getDefault(paramContext);
      localLinkedHashMap.put(((ApplicationType)localObject2).getPackageName(), localObject2);
      localObject1 = ((List)localObject1).iterator();
    }
    Object localObject3;
    int[] arrayOfInt1;
    int[] arrayOfInt2;
    do
    {
      if (!((Iterator)localObject1).hasNext())
      {
        return localLinkedHashMap;
        localObject1 = new ArrayList();
        break;
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
      if (LoggerFactory.LOG_ENABLED) {
        Log.d("PackageManagerUtils", localObject2 + ", apptype: " + ((ApplicationType)localObject3).getPluginType() + " & " + paramInt + " == " + (((ApplicationType)localObject3).getPluginType() & paramInt));
      }
      if ((((ApplicationType)localObject3).getPluginType() & paramInt) <= 0) {
        break;
      }
      localLinkedHashMap.put(localObject2, localObject3);
      break;
    }
  }
  
  public static ApplicationInfo getApplicationInfo(Context paramContext)
  {
    PackageManager localPackageManager;
    if (mInfo == null) {
      localPackageManager = paramContext.getPackageManager();
    }
    try
    {
      mInfo = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128);
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
  
  private static String getCDSBaseUrl(Context paramContext)
  {
    if (CONTENT_BASE_URL == null) {
      if (!isDebugVersion(paramContext)) {
        break label24;
      }
    }
    label24:
    for (paramContext = "http://testassets.aviary.com.s3.amazonaws.com";; paramContext = "http://assets.aviary.com")
    {
      CONTENT_BASE_URL = paramContext;
      return CONTENT_BASE_URL;
    }
  }
  
  public static String getCDSContentProviderUrl(Context paramContext)
  {
    return getCDSBaseUrl(paramContext) + "/" + "assets_android_v06.json";
  }
  
  public static String getCDSContentUrl(Context paramContext)
  {
    return getCDSBaseUrl(paramContext) + "/android";
  }
  
  public static int getPackageInfo(Context paramContext, String paramString, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getResourcesForApplication(paramString);
        i = 0;
        m = 0;
        j = 0;
        k = 0;
        int n = 0;
        int i1 = 0;
        int i2 = paramContext.getIdentifier("is_sticker", "integer", paramString);
        if (i2 != 0) {
          i = paramContext.getInteger(i2);
        }
        i2 = paramContext.getIdentifier("is_filter", "integer", paramString);
        if (i2 != 0) {
          m = paramContext.getInteger(i2);
        }
        i2 = paramContext.getIdentifier("is_tool", "integer", paramString);
        if (i2 != 0) {
          j = paramContext.getInteger(i2);
        }
        i2 = paramContext.getIdentifier("is_border", "integer", paramString);
        if (i2 != 0) {
          k = paramContext.getInteger(i2);
        }
        i2 = paramContext.getIdentifier("sticker_version", "integer", paramString);
        if (i2 != 0) {
          n = paramContext.getInteger(i2);
        }
        i2 = paramContext.getIdentifier("border_version", "integer", paramString);
        if (i2 != 0) {
          i1 = paramContext.getInteger(i2);
        }
        paramArrayOfInt1[0] = n;
        paramArrayOfInt2[0] = i1;
        if (m == 1)
        {
          m = 1;
          if (i != 1) {
            break label243;
          }
          i = 2;
          if (j != 1) {
            break label249;
          }
          j = 8;
          if (k != 1) {
            break label255;
          }
          k = 4;
          return k | m | i | j;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        return 0;
      }
      int m = 0;
      continue;
      label243:
      int i = 0;
      continue;
      label249:
      int j = 0;
      continue;
      label255:
      int k = 0;
    }
  }
  
  public static PackageInfo getPackageInfo(Context paramContext)
  {
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
  
  public static boolean isDebugVersion(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      paramContext = paramContext.getApplicationInfo();
      int i = paramContext.flags & 0x2;
      paramContext.flags = i;
      bool1 = bool2;
      if (i != 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean isStandalone(Context paramContext)
  {
    return "com.aviary.android.feather".equals(paramContext.getPackageName());
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
