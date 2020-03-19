package com.africasunrise.skinseed;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import com.africasunrise.skinseed.utils.ResolutionUtils;
import java.util.Iterator;
import java.util.List;

public class Application
  extends MultiDexApplication
{
  private static Context context;
  private static int screenViewCount;
  
  public Application() {}
  
  public static boolean appInstalledOrNot(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean checkPermission(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static Context getAppContext()
  {
    return context;
  }
  
  public static int getRowViewSkinCount(Context paramContext)
  {
    if (screenViewCount == 0)
    {
      screenViewCount = paramContext.getResources().getInteger(2131361794);
      if (ResolutionUtils.getInches(paramContext) <= 4.0D) {
        screenViewCount = paramContext.getResources().getInteger(2131361800);
      }
    }
    if (isLandscape(paramContext)) {
      return paramContext.getResources().getInteger(2131361793);
    }
    return screenViewCount;
  }
  
  public static int getSdkVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static boolean isApplicationInForeground()
  {
    return MyLifecycleHandler.isApplicationInForeground();
  }
  
  public static boolean isApplicationVisible()
  {
    return MyLifecycleHandler.isApplicationVisible();
  }
  
  public static boolean isFroyo()
  {
    return getSdkVersion() >= 8;
  }
  
  public static boolean isHoneycomb()
  {
    return getSdkVersion() >= 11;
  }
  
  public static boolean isHoneycombTablet(Context paramContext)
  {
    return (isHoneycomb()) && (isTablet(paramContext));
  }
  
  public static boolean isLandscape(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getResources().getConfiguration().orientation;
      if (i == 2) {
        bool = true;
      }
      return bool;
    }
    catch (NullPointerException paramContext) {}
    return false;
  }
  
  public static boolean isPackageExisted(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)paramContext.next()).packageName.equals(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  public static boolean isTablet(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
    MultiDex.install(this);
  }
  
  public void onCreate()
  {
    super.onCreate();
    context = getApplicationContext();
    registerActivityLifecycleCallbacks(new MyLifecycleHandler());
  }
}
