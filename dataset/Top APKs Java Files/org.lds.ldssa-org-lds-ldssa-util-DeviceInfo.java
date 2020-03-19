package org.lds.ldssa.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.os.Build;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public class DeviceInfo
{
  private static final String APP_PRODUCT = "Gospel_Library";
  public static final int FAKE_KINDLE_MENU_ID = 1000;
  private static final String GOOGLE_PLAY_STORE_PACKAGE_NAME_NEW = "com.android.vending";
  private static final String GOOGLE_PLAY_STORE_PACKAGE_NAME_OLD = "com.google.market";
  private static final String TAG = "gl.DeviceInfo";
  
  private DeviceInfo() {}
  
  public static int getApiLevel()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String getAppFullVersion()
  {
    return "3.2.14.0";
  }
  
  public static String getAppFullVersion(boolean paramBoolean)
  {
    String str2 = getAppFullVersion();
    String str1 = str2;
    if (paramBoolean)
    {
      int i = str2.indexOf('-');
      str1 = str2;
      if (i > 1) {
        str1 = str2.substring(0, i);
      }
    }
    return str1;
  }
  
  public static String getAppVersion()
  {
    return "3.2.14";
  }
  
  public static String getAppVersion(boolean paramBoolean)
  {
    String str2 = getAppVersion();
    String str1 = str2;
    if (paramBoolean)
    {
      int i = str2.indexOf('-');
      str1 = str2;
      if (i > 1) {
        str1 = str2.substring(0, i);
      }
    }
    return str1;
  }
  
  public static String getDeviceId(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
  }
  
  public static String getDeviceInfo()
  {
    Object localObject2 = Build.BRAND + " " + Build.MODEL;
    Object localObject1 = localObject2;
    if (!((String)localObject2).contains(Build.DEVICE)) {
      localObject1 = (String)localObject2 + " " + Build.DEVICE;
    }
    localObject2 = localObject1;
    if (!((String)localObject1).contains(Build.BOARD)) {
      localObject2 = (String)localObject1 + " " + Build.BOARD;
    }
    localObject1 = localObject2;
    if (!((String)localObject2).contains(Build.PRODUCT)) {
      localObject1 = (String)localObject2 + " " + Build.PRODUCT;
    }
    return localObject1;
  }
  
  public static String getDisplayInfo(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
      paramContext = localDisplayMetrics.widthPixels + "x" + localDisplayMetrics.heightPixels + " " + DensityHandler.getDensityHandler(paramContext).getDensityDpi() + "dpi (" + localDisplayMetrics.density + ")";
      return paramContext;
    }
    catch (Exception paramContext)
    {
      GLLog.e("gl.DeviceInfo", "getDisplayInfo", paramContext);
    }
    return "Unknown";
  }
  
  public static String getInstallInfo(Context paramContext)
  {
    String str1 = "Unknown";
    try
    {
      String str2 = paramContext.getPackageManager().getInstallerPackageName(paramContext.getApplicationContext().getPackageName());
      paramContext = str1;
      if (str2 != null) {
        paramContext = str2;
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      GLLog.e("gl.DeviceInfo", "getInstallInfo", paramContext);
    }
    return "Unknown";
  }
  
  public static int getLandscapeWidth(Context paramContext)
  {
    int k = 480;
    int m = 0;
    if (paramContext == null) {
      return Math.min(480, 0);
    }
    paramContext = (WindowManager)paramContext.getSystemService("window");
    int i = m;
    int j = k;
    Point localPoint;
    if (paramContext != null)
    {
      paramContext = paramContext.getDefaultDisplay();
      i = m;
      j = k;
      if (paramContext != null)
      {
        if (Build.VERSION.SDK_INT < 13) {
          break label95;
        }
        localPoint = new Point();
        paramContext.getSize(localPoint);
        j = localPoint.x;
      }
    }
    for (i = localPoint.y;; i = paramContext.getHeight())
    {
      return Math.max(j, i);
      label95:
      j = paramContext.getWidth();
    }
  }
  
  public static String getNetworkInfo(Context paramContext)
  {
    Object localObject1 = "Unknown";
    Object localObject2 = (ConnectivityManager)paramContext.getSystemService("connectivity");
    paramContext = (Context)localObject1;
    if (localObject2 != null)
    {
      localObject2 = ((ConnectivityManager)localObject2).getActiveNetworkInfo();
      paramContext = (Context)localObject1;
      if (localObject2 != null)
      {
        localObject1 = new StringBuilder().append(((NetworkInfo)localObject2).getTypeName()).append(" ").append(((NetworkInfo)localObject2).getSubtypeName()).append(" ").append(((NetworkInfo)localObject2).getDetailedState().toString()).append(" ");
        if (((NetworkInfo)localObject2).getExtraInfo() != null) {
          break label98;
        }
      }
    }
    label98:
    for (paramContext = "";; paramContext = ((NetworkInfo)localObject2).getExtraInfo())
    {
      paramContext = paramContext;
      return paramContext;
    }
  }
  
  public static int getPortraitHeight(Context paramContext)
  {
    int k = 480;
    int m = 0;
    int i = m;
    int j = k;
    Point localPoint;
    if (paramContext != null)
    {
      paramContext = (WindowManager)paramContext.getSystemService("window");
      i = m;
      j = k;
      if (paramContext != null)
      {
        paramContext = paramContext.getDefaultDisplay();
        i = m;
        j = k;
        if (paramContext != null)
        {
          if (Build.VERSION.SDK_INT < 13) {
            break label92;
          }
          localPoint = new Point();
          paramContext.getSize(localPoint);
          j = localPoint.x;
        }
      }
    }
    for (i = localPoint.y;; i = paramContext.getHeight())
    {
      return Math.max(j, i);
      label92:
      j = paramContext.getWidth();
    }
  }
  
  public static int getPortraitWidth(Context paramContext)
  {
    int k = 320;
    int m = 0;
    if (paramContext == null) {
      return Math.min(320, 0);
    }
    paramContext = (WindowManager)paramContext.getSystemService("window");
    int i = m;
    int j = k;
    Point localPoint;
    if (paramContext != null)
    {
      paramContext = paramContext.getDefaultDisplay();
      i = m;
      j = k;
      if (paramContext != null)
      {
        if (Build.VERSION.SDK_INT < 13) {
          break label95;
        }
        localPoint = new Point();
        paramContext.getSize(localPoint);
        j = localPoint.x;
      }
    }
    for (i = localPoint.y;; i = paramContext.getHeight())
    {
      return Math.min(j, i);
      label95:
      j = paramContext.getWidth();
    }
  }
  
  public static int getScaledLandscapeWidth(Context paramContext)
  {
    return (int)(getLandscapeWidth(paramContext) / paramContext.getResources().getDisplayMetrics().density);
  }
  
  public static int getScaledPortraitWidth(Context paramContext)
  {
    return (int)(getPortraitWidth(paramContext) / paramContext.getResources().getDisplayMetrics().density);
  }
  
  public static float getScreenWidth(Context paramContext)
  {
    if (isLandscape(paramContext)) {
      return getLandscapeWidth(paramContext);
    }
    return getPortraitWidth(paramContext);
  }
  
  public static String getUserAgent()
  {
    String str1 = Build.VERSION.INCREMENTAL;
    String str2 = Build.MODEL;
    String str3 = Build.MANUFACTURER;
    return "Gospel_Library " + getAppVersion() + " / " + "Android " + Build.VERSION.RELEASE + " " + str1 + " / " + str3 + " " + str2;
  }
  
  public static String getWebKitInfo(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).getString("user_agent", System.getProperty("http.agent"));
    if (paramContext != null)
    {
      paramContext = paramContext.split(" ");
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramContext[i];
        int k = localObject.toLowerCase().indexOf("webkit/");
        if (k > 0) {
          return localObject.substring("webkit/".length() + k);
        }
        i += 1;
      }
    }
    return "Unknown";
  }
  
  public static boolean hasGooglePlayStore(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo.packageName.equals("com.google.market")) || (localPackageInfo.packageName.equals("com.android.vending"))) {
        return true;
      }
    }
    return false;
  }
  
  @SuppressLint({"NewApi"})
  public static boolean hasSoftNavButtons(Context paramContext)
  {
    return (Build.VERSION.SDK_INT >= 14) && (!ViewConfiguration.get(paramContext).hasPermanentMenuKey());
  }
  
  public static int howManyActionBarItems(Context paramContext, boolean paramBoolean)
  {
    boolean bool1 = isActionBarSplit(paramContext);
    if (!hasSoftNavButtons(paramContext)) {}
    for (int j = 1;; j = 0)
    {
      boolean bool2 = isLandscape(paramContext);
      float f1 = paramContext.getResources().getDimension(2131296299);
      float f2 = getScreenWidth(paramContext);
      int i = 3;
      if (f1 < f2)
      {
        int k = (int)Math.floor(f2 / f1);
        if (!paramBoolean)
        {
          i = k;
          if (j != 0) {}
        }
        else
        {
          i = k - 1;
        }
        if (!bool2)
        {
          j = i;
          if (bool1) {}
        }
        else
        {
          j = i - 4;
        }
        i = Math.min(j, 5);
      }
      return i;
    }
  }
  
  public static boolean isActionBarSplit(Context paramContext)
  {
    return (isPortrait(paramContext)) && (paramContext.getResources().getBoolean(2131361794));
  }
  
  public static boolean isAlphaBuild()
  {
    return "alpha".equalsIgnoreCase("release");
  }
  
  public static boolean isBetaBuild()
  {
    return "beta".equalsIgnoreCase("release");
  }
  
  public static boolean isDeveloperBuild()
  {
    return "debug".equalsIgnoreCase("release");
  }
  
  public static boolean isDeviceLargeOrGreater(Context paramContext)
  {
    return paramContext.getResources().getBoolean(2131361795);
  }
  
  public static boolean isDeviceNormalOrGreater(Context paramContext)
  {
    return paramContext.getResources().getBoolean(2131361796);
  }
  
  public static boolean isDeviceXLargeOrGreater(Context paramContext)
  {
    return paramContext.getResources().getBoolean(2131361798);
  }
  
  public static boolean isEmulator()
  {
    return (isDeveloperBuild()) && ((Build.MODEL.startsWith("sdk")) || ("generic_x86".equalsIgnoreCase(Build.DEVICE)));
  }
  
  public static boolean isGingerBreadEmu()
  {
    return (isEmulator()) && (Build.VERSION.SDK_INT == 10);
  }
  
  public static boolean isHardwareDpadAvailable(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().navigation == 2;
  }
  
  public static boolean isHardwareKeyboardAvailable(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().keyboard != 1;
  }
  
  public static boolean isKindle1stGen()
  {
    return "kindle fire".equalsIgnoreCase(Build.MODEL);
  }
  
  public static boolean isKindleFire()
  {
    String str = Build.MODEL.toLowerCase();
    return ("amazon".equalsIgnoreCase(Build.MANUFACTURER)) && (("kindle fire".equals(str)) || (str.startsWith("kf")));
  }
  
  public static boolean isLandscape(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 2;
  }
  
  public static boolean isNook()
  {
    return "nook".equalsIgnoreCase(Build.BRAND);
  }
  
  public static boolean isPortrait(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 1;
  }
  
  public static boolean isReleaseBuild()
  {
    return "release".equalsIgnoreCase("release");
  }
  
  public static boolean isTabletDevice(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      paramContext = paramContext.getResources().getConfiguration();
      try
      {
        boolean bool = ((Boolean)paramContext.getClass().getMethod("isLayoutSizeAtLeast", new Class[] { Integer.TYPE }).invoke(paramContext, new Object[] { Integer.valueOf(4) })).booleanValue();
        return bool;
      }
      catch (Exception paramContext)
      {
        GLLog.e("gl.DeviceInfo", "isTabletDevice", paramContext);
      }
    }
    return false;
  }
  
  public static int numberOfVisibleMenuItemsOnKindle1stGen(Context paramContext)
  {
    if (isKindle1stGen())
    {
      if (isLandscape(paramContext)) {
        return 9;
      }
      return 18;
    }
    return 9999;
  }
  
  public static boolean showArtInListMode(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 13) {
      return paramContext.getResources().getBoolean(2131361797);
    }
    return getScaledPortraitWidth(paramContext) > 340.0F;
  }
}
