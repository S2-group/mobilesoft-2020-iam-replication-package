package com.pingstart.adsdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class DeviceUtils
{
  public static TimerTask sTask;
  public static Timer sTimer;
  
  public DeviceUtils() {}
  
  public static String getAndroidId(Context paramContext)
  {
    try
    {
      paramContext = trimSpace(Settings.Secure.getString(paramContext.getContentResolver(), "android_id").toLowerCase());
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static String getCountryISOCode(Context paramContext)
  {
    try
    {
      localObject1 = (TelephonyManager)paramContext.getSystemService("phone");
      localObject2 = ((TelephonyManager)localObject1).getSimCountryIso();
      if ((localObject2 == null) || (((String)localObject2).length() != 2)) {
        break label66;
      }
      localObject1 = ((String)localObject2).toLowerCase(Locale.US);
    }
    catch (Exception localException)
    {
      try
      {
        for (;;)
        {
          localObject2 = paramContext.getResources().getConfiguration().locale.getCountry();
          return trimSpace(((String)localObject2).toLowerCase());
          label66:
          if (((TelephonyManager)localObject1).getPhoneType() == 2) {
            break;
          }
          Object localObject1 = ((TelephonyManager)localObject1).getNetworkCountryIso();
          if ((localObject1 == null) || (((String)localObject1).length() != 2)) {
            break;
          }
          localObject1 = ((String)localObject1).toLowerCase(Locale.US);
        }
        localException = localException;
        localException.printStackTrace();
        String str = "us";
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          Object localObject2 = "us";
        }
      }
    }
    localObject2 = localObject1;
    if (!TextUtils.isEmpty((CharSequence)localObject1)) {}
  }
  
  public static String getDeviceUUID(Context paramContext)
  {
    int i = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      Object localObject = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      paramContext = MessageDigest.getInstance("SHA-1");
      localObject = ((String)localObject).getBytes("UTF-8");
      paramContext.update((byte[])localObject, 0, localObject.length);
      paramContext = paramContext.digest();
      int j = paramContext.length;
      for (;;)
      {
        if (i >= j) {
          return trimSpace(localStringBuilder.toString().toLowerCase());
        }
        localStringBuilder.append(String.format("%02X", new Object[] { Byte.valueOf(paramContext[i]) }));
        i += 1;
      }
      return "";
    }
    catch (Exception paramContext) {}
  }
  
  public static ArrayList<String> getInstalledAppList(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      int i = 0;
      for (;;)
      {
        if (i >= paramContext.size()) {
          return localArrayList;
        }
        PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          localArrayList.add(localPackageInfo.packageName);
        }
        i += 1;
      }
      return null;
    }
    catch (Exception paramContext) {}
  }
  
  public static String getLocaleLanguage(Context paramContext)
  {
    paramContext = paramContext.getResources().getConfiguration().locale.getLanguage();
    if (paramContext != null) {
      Locale localLocale = Locale.US;
    }
    return trimSpace(paramContext);
  }
  
  public static int getOrientation(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation;
  }
  
  public static float getScreenDensity(Context paramContext)
  {
    float f = 0.0F;
    if ((paramContext instanceof Activity))
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      f = localDisplayMetrics.density;
    }
    return f;
  }
  
  public static float getScreenWidth(Context paramContext)
  {
    float f = 0.0F;
    if ((paramContext instanceof Activity))
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      f = localDisplayMetrics.widthPixels;
    }
    return f;
  }
  
  public static int getVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 8192).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 100;
  }
  
  public static boolean isApkInstalled(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static String trimSpace(String paramString)
  {
    return paramString.replaceAll("\\s", "").trim();
  }
}
