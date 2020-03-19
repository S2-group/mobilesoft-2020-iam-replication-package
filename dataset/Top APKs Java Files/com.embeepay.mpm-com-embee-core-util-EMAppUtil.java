package com.embee.core.util;

import android.annotation.TargetApi;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.PowerManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

class EMAppUtil
  extends EMAnalyticsUtil
{
  protected static final String TAG = "EMUtil";
  
  EMAppUtil() {}
  
  private static boolean checkRootMethod1()
  {
    String str = Build.TAGS;
    return (str != null) && (str.contains("test-keys"));
  }
  
  private static boolean checkRootMethod2()
  {
    String[] arrayOfString = new String[9];
    arrayOfString[0] = "/system/app/Superuser.apk";
    arrayOfString[1] = "/sbin/su";
    arrayOfString[2] = "/system/bin/su";
    arrayOfString[3] = "/system/xbin/su";
    arrayOfString[4] = "/data/local/xbin/su";
    arrayOfString[5] = "/data/local/bin/su";
    arrayOfString[6] = "/system/sd/xbin/su";
    arrayOfString[7] = "/system/bin/failsafe/su";
    arrayOfString[8] = "/data/local/su";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (new File(arrayOfString[i]).exists()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private static boolean checkRootMethod3()
  {
    Object localObject3 = null;
    Object localObject1 = null;
    try
    {
      localProcess = Runtime.getRuntime().exec(new String[] { "/system/xbin/which", "su" });
      localObject1 = localProcess;
      localObject3 = localProcess;
      String str = new BufferedReader(new InputStreamReader(localProcess.getInputStream())).readLine();
      return str != null;
    }
    catch (Throwable localThrowable)
    {
      Process localProcess;
      return false;
    }
    finally
    {
      if (localThrowable != null) {
        localThrowable.destroy();
      }
    }
  }
  
  public static boolean doesPackageExist(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 128);
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static String getAppName(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager = paramPackageManager.getApplicationLabel(paramPackageManager.getApplicationInfo(paramString, 0)).toString();
      return paramPackageManager;
    }
    catch (NullPointerException paramPackageManager)
    {
      return "";
    }
    catch (PackageManager.NameNotFoundException paramPackageManager) {}
    return "";
  }
  
  public static String getAppVersionCode(Context paramContext)
  {
    try
    {
      paramContext = Integer.toString(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      return "";
    }
    catch (NullPointerException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static int getNumApps(Context paramContext)
  {
    int i = 0;
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    label72:
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      if ((localApplicationInfo.flags & 0x81) != 0) {}
      for (int j = 1;; j = 0)
      {
        if ((localApplicationInfo.uid <= 10000) || (j != 0)) {
          break label72;
        }
        i += 1;
        break;
      }
    }
    return i;
  }
  
  public static boolean isDeviceRooted()
  {
    return (checkRootMethod1()) || (checkRootMethod2()) || (checkRootMethod3());
  }
  
  public static boolean isEqualOrGreaterThanApi(int paramInt)
  {
    return Build.VERSION.SDK_INT >= paramInt;
  }
  
  public static boolean isEqualOrLessThanApi(int paramInt)
  {
    return Build.VERSION.SDK_INT <= paramInt;
  }
  
  public static boolean isInternetConnected(Context paramContext)
  {
    if (paramContext == null) {}
    do
    {
      do
      {
        return false;
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      } while (paramContext == null);
      paramContext = paramContext.getActiveNetworkInfo();
    } while ((paramContext == null) || (!paramContext.isConnected()));
    return true;
  }
  
  public static boolean isInviteAppInstalled(Context paramContext)
  {
    return doesPackageExist(paramContext, "com.embeepay.invites");
  }
  
  @TargetApi(20)
  public static boolean isScreenOn(Context paramContext)
  {
    paramContext = (PowerManager)paramContext.getSystemService("power");
    if (Build.VERSION.SDK_INT >= 20) {}
    for (int i = 1; i != 0; i = 0) {
      return paramContext.isInteractive();
    }
    return paramContext.isScreenOn();
  }
  
  @TargetApi(21)
  public static boolean isUsageStatsPermissionEnable(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1;
    if (Build.VERSION.SDK_INT < 21) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramContext == null);
      bool1 = bool2;
    } while (((UsageStatsManager)paramContext.getSystemService("usagestats")).queryUsageStats(0, 0L, System.currentTimeMillis()).isEmpty());
    return true;
  }
}
