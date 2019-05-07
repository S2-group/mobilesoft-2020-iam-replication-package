package com.ecovacs.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.EditText;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class AndroidUtil
{
  private static final String GooglePlayStorePackageNameNew = "com.android.vending";
  private static final String GooglePlayStorePackageNameOld = "com.google.market";
  private long lastClickTime;
  
  public AndroidUtil() {}
  
  public static boolean checkIntent(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }
  
  public static void clearWebViewCookie(Context paramContext)
  {
    CookieManager localCookieManager = CookieManager.getInstance();
    if (Build.VERSION.SDK_INT >= 21)
    {
      localCookieManager.removeAllCookies(null);
      return;
    }
    CookieSyncManager.createInstance(paramContext);
    localCookieManager.removeAllCookie();
  }
  
  public static void closeKeyBox(Context paramContext)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(((Activity)paramContext).getWindow().peekDecorView().getWindowToken(), 0);
  }
  
  public static void closeKeyBox(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static boolean existSdcard()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static String gainChannelId(Context paramContext, Class paramClass, String paramString)
  {
    paramContext = ToolData.gainMetaData(paramContext, paramClass, paramString);
    if (!TextUtils.isEmpty(paramContext)) {
      return paramContext;
    }
    return "";
  }
  
  public static int getAndroidSDKVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      Log.e("VersionInfo", "Exception", paramContext);
    }
    return 0;
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    Object localObject1 = "0.0.0";
    Object localObject2;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      if (paramContext != null)
      {
        localObject1 = paramContext;
        localObject2 = paramContext;
        if (paramContext.length() > 0) {}
      }
      else
      {
        return "";
      }
    }
    catch (Exception paramContext)
    {
      Log.e("VersionInfo", "Exception", paramContext);
      localObject2 = localObject1;
    }
    return localObject2;
  }
  
  public static float getDensity(Context paramContext)
  {
    return paramContext.getApplicationContext().getResources().getDisplayMetrics().density;
  }
  
  public static String getIMEI(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return null;
    }
    return paramContext.getDeviceId();
  }
  
  public static String getPhoneMODEL()
  {
    return Build.MODEL;
  }
  
  public static String getPhoneOs()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String getSDPath()
  {
    File localFile = null;
    if (Environment.getExternalStorageState().equals("mounted")) {
      localFile = Environment.getExternalStorageDirectory();
    }
    return localFile.toString();
  }
  
  public static DisplayMetrics getScreenDisplayMetrics(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public static final Display getScreenSize(Context paramContext)
  {
    return ((Activity)paramContext).getWindowManager().getDefaultDisplay();
  }
  
  public static String getScreenString(Context paramContext)
  {
    paramContext = getScreenSize(paramContext);
    return paramContext.getWidth() + "," + paramContext.getHeight();
  }
  
  public static String getText(EditText paramEditText)
  {
    if (paramEditText == null) {
      return "";
    }
    return paramEditText.getText().toString();
  }
  
  public static int getTextSize(int paramInt)
  {
    if (paramInt < 480) {
      return 30;
    }
    if (paramInt <= 720) {
      return 40;
    }
    if (paramInt < 1080) {
      return 50;
    }
    return 60;
  }
  
  public static void goToMarket(Context paramContext)
    throws Exception
  {
    try
    {
      openAppRating(paramContext);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      throw paramContext;
    }
  }
  
  public static void hideInputMethodWindow(Activity paramActivity)
  {
    View localView = paramActivity.getCurrentFocus();
    if (localView != null) {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(localView.getWindowToken(), 0);
    }
  }
  
  public static void installApk(Context paramContext, Uri paramUri)
  {
    if (paramUri == null) {
      return;
    }
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(paramUri, "application/vnd.android.package-archive");
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  public static void installApk(Context paramContext, String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramString.startsWith("http"))) {
      return;
    }
    installApk(paramContext, Uri.fromFile(new File(paramString)));
  }
  
  public static boolean isAppInstailed(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isCurrentAppForeground(Context paramContext)
  {
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if ((localObject == null) || (((List)localObject).size() == 0)) {}
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      while (!((Iterator)localObject).hasNext())
      {
        return false;
        localObject = ((List)localObject).iterator();
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
    } while ((localRunningAppProcessInfo.importance != 100) || (!localRunningAppProcessInfo.processName.equals(paramContext.getPackageName())));
    return true;
  }
  
  public static boolean isPlayStoreInstalled(Context paramContext)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo("com.android.vending", 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static void openAppRating(Context paramContext)
  {
    String str = paramContext.getPackageName();
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str));
    int j = 0;
    Object localObject = paramContext.getPackageManager().queryIntentActivities(localIntent, 0).iterator();
    ResolveInfo localResolveInfo;
    do
    {
      i = j;
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
      localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
    } while (!localResolveInfo.activityInfo.applicationInfo.packageName.equals("com.android.vending"));
    localObject = localResolveInfo.activityInfo;
    localObject = new ComponentName(((ActivityInfo)localObject).applicationInfo.packageName, ((ActivityInfo)localObject).name);
    localIntent.addFlags(268435456);
    localIntent.addFlags(2097152);
    localIntent.addFlags(67108864);
    localIntent.setComponent((ComponentName)localObject);
    paramContext.startActivity(localIntent);
    int i = 1;
    if (i == 0) {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + str)));
    }
  }
}
