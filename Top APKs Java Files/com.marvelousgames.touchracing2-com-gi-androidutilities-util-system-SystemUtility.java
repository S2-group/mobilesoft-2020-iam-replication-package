package com.gi.androidutilities.util.system;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import com.gi.androidutilities.exception.SecureIdNotFoundException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class SystemUtility
{
  public static final String SECURE_ID_FAIL = "9774d56d682e549c";
  
  public SystemUtility() {}
  
  public static void changeLanguageResources(Context paramContext, Locale paramLocale)
  {
    paramContext = paramContext.getResources();
    DisplayMetrics localDisplayMetrics = paramContext.getDisplayMetrics();
    Configuration localConfiguration = paramContext.getConfiguration();
    localConfiguration.locale = paramLocale;
    paramContext.updateConfiguration(localConfiguration, localDisplayMetrics);
  }
  
  public static String getCountryPhone()
  {
    return Locale.getDefault().getCountry();
  }
  
  public static String getDeviceId(Activity paramActivity)
    throws SecureIdNotFoundException
  {
    return getDeviceId(paramActivity);
  }
  
  public static String getDeviceId(Context paramContext)
    throws SecureIdNotFoundException
  {
    Object localObject;
    try
    {
      String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      if (str != null)
      {
        localObject = str;
        if (!str.equals("9774d56d682e549c")) {
          return localObject;
        }
        paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
        if (paramContext != null)
        {
          localObject = paramContext;
          if (!paramContext.trim().equals("")) {
            return localObject;
          }
        }
        throw new SecureIdNotFoundException();
      }
    }
    catch (Exception paramContext)
    {
      throw new SecureIdNotFoundException();
    }
    throw new SecureIdNotFoundException();
    return localObject;
  }
  
  public static int getDimenInPixel(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    return (int)TypedValue.applyDimension(1, paramInt, paramContext.getDisplayMetrics());
  }
  
  public static int getHeightScreen(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public static String getLanguagePhone()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static List<String> getPackageInstalledApps(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if (localPackageInfo.packageName.startsWith(paramString)) {
        localArrayList.add(localPackageInfo.packageName);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static Currency getPhoneCurrency()
  {
    try
    {
      Currency localCurrency = Currency.getInstance(Locale.getDefault());
      return localCurrency;
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}
    return null;
  }
  
  public static String getVersionCode(Context paramContext)
    throws PackageManager.NameNotFoundException
  {
    return Integer.toString(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode);
  }
  
  @Deprecated
  public static String getVersionName(Activity paramActivity)
    throws PackageManager.NameNotFoundException
  {
    return paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0).versionName;
  }
  
  public static String getVersionName(Context paramContext)
    throws PackageManager.NameNotFoundException
  {
    return paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
  }
  
  public static int getWidthScreen(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  public static boolean isGoogleTV(Context paramContext)
  {
    return paramContext.getPackageManager().hasSystemFeature("com.google.android.tv");
  }
  
  public static boolean isHoneycomb(Context paramContext)
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  public static boolean isInstalled(Context paramContext, String paramString)
    throws ActivityNotFoundException, PackageManager.NameNotFoundException
  {
    new Intent();
    return paramContext.getPackageManager().getLaunchIntentForPackage(paramString) != null;
  }
  
  public static boolean isLarge(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) == 3;
  }
  
  public static boolean isTablet(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) > 3;
  }
  
  public static boolean isTabletNewCriteria(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static boolean isVersionTablet(Context paramContext)
  {
    int i = Build.VERSION.SDK_INT;
    if (i < 11) {
      return false;
    }
    if ((i >= 11) && (i < 14)) {
      return true;
    }
    if ((i >= 14) && (i < 17)) {
      return isTablet(paramContext);
    }
    return isTabletNewCriteria(paramContext);
  }
  
  public static boolean isXLarge(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) == 4;
  }
  
  public static void openAplication(Context paramContext, String paramString)
    throws ActivityNotFoundException, PackageManager.NameNotFoundException
  {
    new Intent();
    paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (paramString != null)
    {
      paramContext.startActivity(paramString);
      return;
    }
    throw new ActivityNotFoundException();
  }
  
  public static void uninstallAplication(Context paramContext, String paramString)
    throws ActivityNotFoundException, PackageManager.NameNotFoundException
  {
    Intent localIntent = new Intent("android.intent.action.DELETE");
    localIntent.setData(Uri.parse("package:" + paramString));
    paramContext.startActivity(localIntent);
  }
}
