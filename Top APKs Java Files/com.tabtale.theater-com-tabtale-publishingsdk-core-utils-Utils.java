package com.tabtale.publishingsdk.core.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.json.JSONArray;

public final class Utils
{
  public static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
  private static final String TAG = Utils.class.getSimpleName();
  private static String mAdvertisingId;
  private static PackageManager mPackageManager;
  private static List<PackageInfo> mPacks;
  
  private Utils() {}
  
  public static Utils.BannerSize calculateBannerSize(Activity paramActivity, String paramString)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    float f;
    if (Build.VERSION.SDK_INT >= 17)
    {
      paramActivity.getWindowManager().getDefaultDisplay().getRealMetrics(localDisplayMetrics);
      boolean bool = paramString.equals("landscape");
      if (!bool) {
        break label108;
      }
      f = 0.7F;
      label47:
      if (!bool) {
        break label122;
      }
      if (localDisplayMetrics.widthPixels <= localDisplayMetrics.heightPixels) {
        break label113;
      }
    }
    label108:
    label113:
    for (int i = localDisplayMetrics.widthPixels;; i = localDisplayMetrics.heightPixels)
    {
      if (728.0F >= i / localDisplayMetrics.density * f) {
        break label153;
      }
      return Utils.BannerSize.BannerSizeLarge;
      paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      break;
      f = 1.0F;
      break label47;
    }
    label122:
    if (localDisplayMetrics.widthPixels < localDisplayMetrics.heightPixels) {}
    for (i = localDisplayMetrics.widthPixels;; i = localDisplayMetrics.heightPixels) {
      break;
    }
    label153:
    if (468.0F < i / localDisplayMetrics.density * f) {
      return Utils.BannerSize.BannerSizeMedium;
    }
    return Utils.BannerSize.BannerSizeSmall;
  }
  
  public static boolean compareJsonArrays(JSONArray paramJSONArray1, JSONArray paramJSONArray2)
  {
    if (paramJSONArray1.length() != paramJSONArray2.length()) {}
    while (paramJSONArray1.toString().compareTo(paramJSONArray2.toString()) != 0) {
      return false;
    }
    return true;
  }
  
  public static String getAdvertisingId()
  {
    return mAdvertisingId;
  }
  
  public static Set<String> getInstalledPackages()
  {
    HashSet localHashSet = new HashSet();
    if (mPacks != null)
    {
      int i = 0;
      while (i < mPacks.size())
      {
        localHashSet.add(((PackageInfo)mPacks.get(i)).packageName);
        i += 1;
      }
    }
    return localHashSet;
  }
  
  public static void initAdvertisingId(Context paramContext)
  {
    Object localObject = null;
    try
    {
      paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext, new Utils.1());
      if (paramContext != null) {
        updateAdvertisingID(paramContext);
      }
      return;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localObject;
      }
    }
  }
  
  public static boolean isAndroidL()
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      System.out.println("Running on Android L");
      return true;
    }
    return false;
  }
  
  public static boolean isConnectedMobile(Activity paramActivity)
  {
    paramActivity = ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramActivity != null) && (paramActivity.isConnected()) && (paramActivity.getType() == 0);
  }
  
  public static boolean isConnectedWifi(Activity paramActivity)
  {
    paramActivity = ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramActivity != null) && (paramActivity.isConnected()) && (paramActivity.getType() == 1);
  }
  
  public static boolean isKindle()
  {
    return Build.MANUFACTURER.equals("Amazon");
  }
  
  public static boolean isNetworkAvailable(Activity paramActivity)
  {
    paramActivity = ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramActivity != null) && (paramActivity.isConnected());
  }
  
  public static boolean isPackageInstalled(String paramString, Context paramContext)
  {
    if (mPackageManager == null) {
      mPackageManager = paramContext.getPackageManager();
    }
    try
    {
      mPackageManager.getPackageInfo(paramString, 1);
      Log.d(TAG, "Utils::isPackageInstalled: " + paramString + " is Installed");
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.d(TAG, "Utils::isPackageInstalled: " + paramString + " is not Installed");
      return false;
    }
    catch (Exception paramContext)
    {
      Log.d(TAG, "Utils::isPackageInstalled: " + paramString + " is not Installed");
    }
    return false;
  }
  
  public static String md5(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuffer();
      int i = 0;
      while (i < paramString.length)
      {
        ((StringBuffer)localObject).append(Integer.toHexString(paramString[i] & 0xFF));
        i += 1;
      }
      paramString = ((StringBuffer)localObject).toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static long now()
  {
    return new Date().getTime() / 1000L;
  }
  
  public static Set<String> refreshInstalledPackages(PackageManager paramPackageManager)
  {
    try
    {
      mPacks = paramPackageManager.getInstalledPackages(0);
      paramPackageManager = new HashSet();
      if (mPacks != null)
      {
        int i = 0;
        while (i < mPacks.size())
        {
          paramPackageManager.add(((PackageInfo)mPacks.get(i)).packageName);
          i += 1;
        }
      }
    }
    catch (Exception paramPackageManager)
    {
      for (;;)
      {
        Log.d(TAG, "refreshInstalledPackages Exception: " + paramPackageManager.getMessage());
      }
    }
    return paramPackageManager;
  }
  
  public static long timeStampToSeconds(String paramString1, String paramString2)
  {
    String str = paramString1;
    if (paramString1.endsWith("Z")) {
      str = paramString1.substring(0, paramString1.length() - 1).concat("-0800");
    }
    if (paramString2 != null) {}
    for (;;)
    {
      paramString1 = new SimpleDateFormat(paramString2, Locale.US);
      try
      {
        long l = paramString1.parse(str).getTime() / 1000L;
        return l;
      }
      catch (ParseException paramString1)
      {
        Log.e(TAG, "could not parse timeStamp.");
      }
      paramString2 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    }
    return -1L;
  }
  
  private static void updateAdvertisingID(AdvertisingIdClient.AdInfo paramAdInfo)
  {
    if (!paramAdInfo.isLimitAdTrackingEnabled())
    {
      if (paramAdInfo.getId() != null)
      {
        mAdvertisingId = paramAdInfo.getId();
        return;
      }
      Log.e(TAG, "advertising id is null");
      mAdvertisingId = "";
      return;
    }
    Log.e(TAG, "advertising id is limited");
    mAdvertisingId = "";
  }
}
