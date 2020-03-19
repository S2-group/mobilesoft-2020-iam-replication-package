package com.playrix.lib;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.os.Process;
import android.os.StrictMode.ThreadPolicy;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Playrix
{
  private static final double PHABLET_DIAGONAL_MIN = 5.5D;
  public static final boolean USE_SDCARD_WORK_DIR = false;
  private static ActivityManager actMgr;
  private static ConnectivityManager connMgr = null;
  private static InputMethodManager inputMgr;
  private static PlayrixActivity mActivity;
  private static Context mContext;
  private static boolean nativeLibLoaded;
  private static WindowManager winMgr;
  
  static
  {
    StrictMode.ThreadPolicy localThreadPolicy = PlayrixStrictMode.SwitchThreadPolicy(PlayrixStrictMode.NewPermissiveThreadPolicy());
    System.loadLibrary("openal");
    PlayrixStrictMode.SwitchThreadPolicy(localThreadPolicy);
    mActivity = null;
    mContext = null;
    nativeLibLoaded = false;
    actMgr = null;
    winMgr = null;
    inputMgr = null;
  }
  
  public Playrix() {}
  
  private static String capitalize(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    char[] arrayOfChar = paramString.toCharArray();
    int i = 1;
    paramString = "";
    int k = arrayOfChar.length;
    int j = 0;
    if (j < k)
    {
      char c = arrayOfChar[j];
      if ((i != 0) && (Character.isLetter(c)))
      {
        paramString = paramString + Character.toUpperCase(c);
        i = 0;
      }
      for (;;)
      {
        j += 1;
        break;
        if (Character.isWhitespace(c)) {
          i = 1;
        }
        paramString = paramString + c;
      }
    }
    return paramString;
  }
  
  public static boolean findBlacklistedPackages()
  {
    Pattern localPattern = Pattern.compile("supersu|superuser|greenify|\\.(xposed|chainfire)\\.|saurik\\.substrate|madkite\\.freedom|dimonvideo\\.luckypatcher|rootcloakplus", 2);
    if ((mContext.getApplicationInfo().flags & 0x2) == 2) {}
    boolean bool1;
    for (int i = 1;; i = 0)
    {
      Object localObject = mActivity.getPackageManager().getInstalledPackages(1);
      new ArrayList();
      bool1 = false;
      localObject = ((List)localObject).iterator();
      PackageInfo localPackageInfo;
      boolean bool2;
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          return bool1;
        }
        localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        bool2 = bool1;
        if (localPattern.matcher(localPackageInfo.packageName).find())
        {
          if (i == 0) {
            break;
          }
          Log.d("[CheatCheck]", localPackageInfo.packageName);
          bool2 = true;
        }
        bool1 = bool2;
      } while (localPackageInfo.activities == null);
      ActivityInfo[] arrayOfActivityInfo = localPackageInfo.activities;
      int k = arrayOfActivityInfo.length;
      int j = 0;
      for (;;)
      {
        bool1 = bool2;
        if (j >= k) {
          break;
        }
        ActivityInfo localActivityInfo = arrayOfActivityInfo[j];
        if (localPattern.matcher(localActivityInfo.name).find())
        {
          if (i == 0) {
            break label227;
          }
          Log.d("[CheatCheck]", localPackageInfo.packageName + ":" + localActivityInfo.name);
          bool2 = true;
        }
        j += 1;
      }
    }
    return true;
    label227:
    return true;
    return bool1;
  }
  
  public static PlayrixActivity getActivity()
  {
    return mActivity;
  }
  
  public static String getAppName()
  {
    PackageManager localPackageManager = mContext.getPackageManager();
    try
    {
      Object localObject = localPackageManager.getApplicationInfo(mContext.getPackageName(), 0);
      if (localObject != null)
      {
        localObject = localPackageManager.getApplicationLabel((ApplicationInfo)localObject);
        return (String)localObject;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        String str = null;
        continue;
        str = "(unknown)";
      }
    }
  }
  
  public static String getAppPath()
  {
    return mContext.getApplicationInfo().sourceDir;
  }
  
  public static String getAppVersion()
  {
    Object localObject = mActivity.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(mActivity.getPackageName(), 0).versionName;
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return "";
  }
  
  public static int getAppVersionCode()
  {
    PackageManager localPackageManager = mActivity.getPackageManager();
    try
    {
      int i = localPackageManager.getPackageInfo(mActivity.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return 0;
  }
  
  public static String getBuildTags()
  {
    return Build.TAGS + "," + Build.TYPE;
  }
  
  public static String getCachesPath()
  {
    return mContext.getCacheDir().getAbsolutePath();
  }
  
  public static Context getContext()
  {
    return mContext;
  }
  
  public static Activity getDefaultActivity()
  {
    return mActivity;
  }
  
  public static String getDeviceLanguage()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static String getDeviceManufacturer()
  {
    String str = Build.MANUFACTURER;
    if (str.equalsIgnoreCase("HTC")) {
      return "HTC";
    }
    return capitalize(str);
  }
  
  public static String getDeviceModel()
  {
    String str2 = Build.MODEL;
    String str3 = Build.MANUFACTURER;
    String str1 = str2;
    if (str2.startsWith(str3)) {
      str1 = str2.substring(str3.length()).trim();
    }
    return str1;
  }
  
  @TargetApi(9)
  public static String getDeviceSerialNumber()
  {
    if (Build.VERSION.SDK_INT >= 9) {
      return Build.SERIAL;
    }
    return "";
  }
  
  public static double getDisplayDiagonal()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    winMgr.getDefaultDisplay().getMetrics(localDisplayMetrics);
    double d1 = localDisplayMetrics.widthPixels / localDisplayMetrics.xdpi;
    double d2 = localDisplayMetrics.heightPixels / localDisplayMetrics.ydpi;
    return Math.sqrt(d1 * d1 + d2 * d2);
  }
  
  public static int getDisplayPpi()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    winMgr.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return (int)((localDisplayMetrics.xdpi + localDisplayMetrics.ydpi) / 2.0F);
  }
  
  public static String getInternetConnectionType()
  {
    NetworkInfo localNetworkInfo = connMgr.getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected())) {
      return "No";
    }
    int i = localNetworkInfo.getType();
    if ((i == 1) || (i == 6)) {
      return "WiFi";
    }
    if (i == 9) {
      return "Ethernet";
    }
    if (i == 7) {
      return "Bluetooth";
    }
    return "Mobile";
  }
  
  public static String getLocaleCountry()
  {
    return Locale.getDefault().getCountry();
  }
  
  public static String getOsVer()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static SharedPreferences getPreferences()
  {
    if (mContext == null) {
      return null;
    }
    return PreferenceManager.getDefaultSharedPreferences(mContext);
  }
  
  public static long getProcSize()
  {
    int i = Process.myPid();
    return actMgr.getProcessMemoryInfo(new int[] { i })[0].getTotalPss();
  }
  
  public static float getResolutionScale()
  {
    return mActivity.getGLView().getResolutionScale();
  }
  
  public static float getResolutionScaleH()
  {
    return mActivity.getGLView().getResolutionScaleH();
  }
  
  public static float getResolutionScaleW()
  {
    return mActivity.getGLView().getResolutionScaleW();
  }
  
  public static String getSecureAndroidId()
  {
    return Settings.Secure.getString(mContext.getContentResolver(), "android_id");
  }
  
  public static String getWriteablePath()
  {
    return mContext.getApplicationInfo().dataDir;
  }
  
  public static boolean isDevicePhablet()
  {
    boolean bool = false;
    if (!isDeviceTablet())
    {
      if (getDisplayDiagonal() > 5.5D) {
        bool = true;
      }
    }
    else {
      return bool;
    }
    return false;
  }
  
  public static boolean isDeviceTablet()
  {
    return (mActivity.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static boolean isPackageInstalled(String paramString)
  {
    PackageManager localPackageManager = getContext().getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public static void loadNativeLibrary(GLSurfaceViewV17 paramGLSurfaceViewV17)
  {
    synchronized (new Playrix.1(nativeLibLoaded))
    {
      paramGLSurfaceViewV17.queueEvent(???);
      try
      {
        ???.wait();
        nativeLibLoaded = true;
        return;
      }
      catch (InterruptedException paramGLSurfaceViewV17)
      {
        for (;;)
        {
          paramGLSurfaceViewV17.printStackTrace();
        }
      }
    }
  }
  
  public static native void nativeDisableInput(boolean paramBoolean);
  
  public static native int nativeGetOrientationSettings();
  
  public static native void nativeKeyDown(int paramInt);
  
  public static native void nativeOnCreate();
  
  public static native void nativeOnDestroy();
  
  public static native void nativeOnLowMemory();
  
  public static native void nativeOnNotificationReceived(int paramInt1, int paramInt2, String paramString, Object paramObject);
  
  public static native void nativeOnOrientationChanged(int paramInt);
  
  public static native void nativeOnPause();
  
  public static native void nativeOnResume();
  
  public static native void nativeOnStart();
  
  public static native void nativeOnStop();
  
  public static native void nativeOnUrlActivate(String paramString);
  
  private static native void nativeRebindNativeLibrary();
  
  public static native void nativeRender();
  
  public static native void nativeResize(int paramInt1, int paramInt2);
  
  public static native void nativeScale(int paramInt1, int paramInt2, float paramFloat);
  
  public static native void nativeScaleBegin(int paramInt1, int paramInt2, float paramFloat);
  
  public static native void nativeScaleEnd();
  
  private static native void nativeThreadStart(long paramLong);
  
  public static native void nativeTouch(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static void onCreate(PlayrixActivity paramPlayrixActivity)
  {
    mActivity = paramPlayrixActivity;
    mContext = paramPlayrixActivity.getApplicationContext();
    actMgr = (ActivityManager)mContext.getSystemService("activity");
    winMgr = (WindowManager)mContext.getSystemService("window");
    inputMgr = (InputMethodManager)mContext.getSystemService("input_method");
    connMgr = (ConnectivityManager)mContext.getSystemService("connectivity");
  }
  
  public static void runOnGLThread(Runnable paramRunnable)
  {
    if ((mActivity != null) && (mActivity.getGLView() != null)) {
      mActivity.getGLView().queueEvent(paramRunnable);
    }
  }
  
  public static void setDeviceOrientation(Activity paramActivity, int paramInt)
  {
    setDeviceOrientation(paramActivity, paramInt, paramInt);
  }
  
  public static void setDeviceOrientation(Activity paramActivity, int paramInt1, int paramInt2)
  {
    setDeviceOrientation(paramActivity, paramInt1, paramInt1, paramInt2);
  }
  
  public static void setDeviceOrientation(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3)
  {
    if (Build.VERSION.SDK_INT > 8)
    {
      if (isDeviceTablet()) {
        paramActivity.setRequestedOrientation(paramInt3);
      }
    }
    else {
      return;
    }
    if (isDevicePhablet())
    {
      paramActivity.setRequestedOrientation(paramInt2);
      return;
    }
    paramActivity.setRequestedOrientation(paramInt1);
  }
  
  public static void setInputText(String paramString)
  {
    mActivity.runOnUiThread(new Playrix.3(paramString));
  }
  
  public static void setResolutionScale(float paramFloat)
  {
    mActivity.getGLView().setResolutionScale(new GLSurfaceViewV17.Scale2D(paramFloat, paramFloat));
  }
  
  public static void setResolutionScale(float paramFloat1, float paramFloat2)
  {
    mActivity.getGLView().setResolutionScale(new GLSurfaceViewV17.Scale2D(paramFloat1, paramFloat2));
  }
  
  public static void toggleKeyboard(boolean paramBoolean)
  {
    mActivity.runOnUiThread(new Playrix.2(paramBoolean));
  }
  
  public static String urlEncode(String paramString)
  {
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
}
