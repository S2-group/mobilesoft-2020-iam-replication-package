package com.smule.android.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.smule.android.logging.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PackageInfoUtils
{
  private static final String TAG = PackageInfoUtils.class.getName();
  
  public PackageInfoUtils() {}
  
  public static int getAppVersion(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw new RuntimeException("Could not get package name: " + paramContext);
    }
  }
  
  private static ArrayList<AppPackageInfo> getListOfInstalledApps(Context paramContext, boolean paramBoolean)
  {
    paramContext = getMapOfInstalledApps(paramContext, paramBoolean);
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(paramContext.values());
    return localArrayList;
  }
  
  private static HashMap<String, AppPackageInfo> getMapOfInstalledApps(Context paramContext, boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i < localList.size())
    {
      Object localObject = (PackageInfo)localList.get(i);
      if ((!paramBoolean) && (((PackageInfo)localObject).versionName == null)) {}
      for (;;)
      {
        i += 1;
        break;
        localObject = AppPackageInfo.createAppPackageInfo((PackageInfo)localObject, paramContext);
        localHashMap.put(((AppPackageInfo)localObject).packageName, localObject);
      }
    }
    return localHashMap;
  }
  
  public static boolean hasBrokenGmail(Context paramContext)
  {
    try
    {
      paramContext = getMapOfInstalledApps(paramContext, true);
      if (paramContext != null)
      {
        paramContext = (AppPackageInfo)paramContext.get("com.google.android.gm");
        if (paramContext == null)
        {
          Log.d(TAG, "hasBrokenGmail - Package with package name com.google.android.gm not found");
          return false;
        }
        if (paramContext.versionName.equals("4.2.1"))
        {
          Log.d(TAG, "hasBrokenGmail - found Gmail package with version 4.2.1");
          return true;
        }
        Log.d(TAG, "hasBrokenGmail - found Gmail package with version: " + paramContext.versionName);
        return false;
      }
    }
    catch (Exception paramContext)
    {
      Log.e(TAG, "hasBrokenGmail - exception thrown!");
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getApplicationInfo(paramString, 0);
      if (paramContext != null)
      {
        Log.d(TAG, "App " + paramString + " installed in " + paramContext.dataDir);
        return true;
      }
      Log.d(TAG, "No info for app " + paramString);
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isAutoRapInstalled(Context paramContext)
  {
    return isAppInstalled(paramContext, "com.smule.autorap");
  }
  
  public static boolean isMagicPianoInstalled(Context paramContext)
  {
    return isAppInstalled(paramContext, "com.smule.magicpiano");
  }
  
  public static boolean isSingInstalled(Context paramContext)
  {
    return isAppInstalled(paramContext, "com.smule.singandroid");
  }
  
  public static class AppPackageInfo
  {
    private String appName = "";
    private String packageName = "";
    private int versionCode = 0;
    private String versionName = "";
    
    public AppPackageInfo() {}
    
    public static AppPackageInfo createAppPackageInfo(PackageInfo paramPackageInfo, Context paramContext)
    {
      AppPackageInfo localAppPackageInfo = new AppPackageInfo();
      localAppPackageInfo.appName = paramPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
      localAppPackageInfo.packageName = paramPackageInfo.packageName;
      localAppPackageInfo.versionName = paramPackageInfo.versionName;
      localAppPackageInfo.versionCode = paramPackageInfo.versionCode;
      return localAppPackageInfo;
    }
    
    public String toString()
    {
      return this.appName + ", " + this.packageName + ", " + this.versionName + ", " + this.versionCode;
    }
  }
}
