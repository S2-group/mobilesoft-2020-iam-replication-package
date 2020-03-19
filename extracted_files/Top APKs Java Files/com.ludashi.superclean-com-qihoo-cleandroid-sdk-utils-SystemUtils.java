package com.qihoo.cleandroid.sdk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SystemUtils
{
  private static final String a = SystemUtils.class.getSimpleName();
  
  public SystemUtils() {}
  
  /* Error */
  private static boolean a(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 29	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: ldc 31
    //   6: invokevirtual 35	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   9: checkcast 37	android/telephony/TelephonyManager
    //   12: astore_0
    //   13: aload_0
    //   14: invokevirtual 41	android/telephony/TelephonyManager:getDataState	()I
    //   17: istore_1
    //   18: iload_1
    //   19: ifne +5 -> 24
    //   22: iconst_0
    //   23: ireturn
    //   24: iconst_1
    //   25: ireturn
    //   26: astore_0
    //   27: iconst_0
    //   28: istore_1
    //   29: goto -11 -> 18
    //   32: astore_0
    //   33: iconst_m1
    //   34: istore_1
    //   35: goto -17 -> 18
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	38	0	paramContext	Context
    //   17	18	1	i	int
    // Exception table:
    //   from	to	target	type
    //   0	13	26	java/lang/Exception
    //   13	18	32	java/lang/Exception
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  private static boolean b(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x80) != 0;
  }
  
  public static Resources getApkResByRefrect(Context paramContext, String paramString)
  {
    try
    {
      Class localClass = Class.forName("android.content.res.AssetManager");
      Object localObject = localClass.newInstance();
      localClass.getMethod("addAssetPath", new Class[] { String.class }).invoke(localObject, new Object[] { paramString });
      paramContext = paramContext.getResources();
      paramContext = new Resources((AssetManager)localObject, paramContext.getDisplayMetrics(), paramContext.getConfiguration());
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static final Drawable getAppIcon(String paramString, PackageManager paramPackageManager)
  {
    try
    {
      paramString = paramPackageManager.getApplicationIcon(paramString);
      return paramString;
    }
    catch (Throwable paramString) {}
    return paramPackageManager.getDefaultActivityIcon();
  }
  
  public static final String getAppName(String paramString, PackageManager paramPackageManager)
  {
    try
    {
      ApplicationInfo localApplicationInfo = paramPackageManager.getApplicationInfo(paramString, 128);
      String str = paramString;
      if (localApplicationInfo != null) {
        str = localApplicationInfo.loadLabel(paramPackageManager).toString();
      }
      paramString = str;
    }
    catch (Throwable paramPackageManager)
    {
      for (;;) {}
    }
    paramPackageManager = paramString;
    if (paramString == null) {
      paramPackageManager = "";
    }
    return paramPackageManager;
  }
  
  public static List<String> getInstalledAppList(Context paramContext)
  {
    int i = 0;
    label84:
    for (;;)
    {
      ArrayList localArrayList;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledApplications(0);
        if (0 != 0) {
          break;
        }
        if (paramContext != null) {
          break label84;
        }
        paramContext = new ArrayList(1);
        int j = paramContext.size();
        localArrayList = new ArrayList(j);
        if (i < j)
        {
          localArrayList.add(((ApplicationInfo)paramContext.get(i)).packageName);
          i += 1;
          continue;
        }
      }
      catch (Exception paramContext)
      {
        paramContext = null;
        continue;
      }
      return localArrayList;
    }
    return null;
  }
  
  public static List<String> getLauncherAppNameList(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    Object localObject1 = new Intent("android.intent.action.MAIN");
    ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
    localObject1 = paramContext.queryIntentActivities((Intent)localObject1, 0);
    if (localObject1 == null) {
      return null;
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (ResolveInfo)((Iterator)localObject1).next();
      if (localObject2 != null)
      {
        localObject2 = ((ResolveInfo)localObject2).loadLabel(paramContext);
        if (localObject2 != null) {
          localArrayList.add(((CharSequence)localObject2).toString());
        }
      }
    }
    return localArrayList;
  }
  
  public static List<String> getLauncherAppPkgList(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
    try
    {
      paramContext = paramContext.queryIntentActivities((Intent)localObject, 0);
      if (paramContext == null) {
        return null;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        localObject = (ResolveInfo)paramContext.next();
        if (localObject != null)
        {
          localObject = ((ResolveInfo)localObject).activityInfo;
          if (localObject != null)
          {
            localObject = ((ActivityInfo)localObject).applicationInfo;
            if (localObject != null)
            {
              localObject = ((ApplicationInfo)localObject).packageName;
              if ((localObject != null) && (!localArrayList.contains(localObject))) {
                localArrayList.add(localObject);
              }
            }
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static String getResStringById(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Resources localResources = paramContext.getResources();
      paramContext = localResources.getString(localResources.getIdentifier(paramString1, "string", paramContext.getPackageName()));
      return paramContext;
    }
    catch (Exception paramContext) {}
    return paramString2;
  }
  
  public static boolean isDataConnected(Context paramContext)
  {
    return (isWifiConnected(paramContext)) || (a(paramContext));
  }
  
  public static boolean isPkgInstalled(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (localPackageManager == null) {}
    do
    {
      return false;
      paramContext = null;
      try
      {
        paramString = localPackageManager.getPackageInfo(paramString, 0);
        paramContext = paramString;
      }
      catch (Throwable paramString)
      {
        for (;;) {}
      }
    } while (paramContext == null);
    return true;
  }
  
  public static final boolean isSystemApp(String paramString, PackageManager paramPackageManager)
  {
    boolean bool1 = false;
    try
    {
      paramString = paramPackageManager.getPackageInfo(paramString, 0);
      if (!a(paramString))
      {
        boolean bool2 = b(paramString);
        if (!bool2) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    catch (Throwable paramString) {}
    return false;
  }
  
  public static boolean isWifiConnected(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity")).getNetworkInfo(1);
      if (paramContext != null)
      {
        boolean bool = paramContext.isConnected();
        return bool;
      }
    }
    catch (Exception paramContext)
    {
      return false;
    }
    return false;
  }
  
  public static Drawable loadApkIcon(Context paramContext, int paramInt, String paramString)
  {
    if (paramInt == 0) {}
    do
    {
      return null;
      paramContext = getApkResByRefrect(paramContext, paramString);
    } while (paramContext == null);
    try
    {
      paramContext = paramContext.getDrawable(paramInt);
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static void startActivity(Activity paramActivity, Intent paramIntent)
  {
    if (paramActivity != null) {}
    try
    {
      paramActivity.startActivity(paramIntent);
      return;
    }
    catch (Exception paramActivity) {}
  }
}
