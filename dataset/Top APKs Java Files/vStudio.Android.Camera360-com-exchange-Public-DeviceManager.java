package com.exchange.Public;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONObject;

public class DeviceManager
{
  public static Set<String> installedApps = new HashSet();
  
  public DeviceManager() {}
  
  public static int dpToPix(int paramInt, Context paramContext)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt * f + 0.5F);
  }
  
  public static String getAppkey(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData.getString("UMENG_APPKEY");
        if (paramContext != null) {
          return paramContext;
        }
        Log.i(ExchangeConstants.LOG_TAG, "Could not read EXCHANGE_APPKEY meta-data from AndroidManifest.xml.");
        return null;
      }
    }
    catch (Exception paramContext)
    {
      Log.i(ExchangeConstants.LOG_TAG, "Could not read EXCHANGE_APPKEY meta-data from AndroidManifest.xml.", paramContext);
    }
    return null;
  }
  
  public static String getFileVersionDes(String paramString)
  {
    return ExchangeConstants.banben + paramString;
  }
  
  public static int getImageHeight(Context paramContext, float paramFloat)
  {
    return Math.round(paramContext.getResources().getDisplayMetrics().heightPixels * paramFloat);
  }
  
  public static Set<String> getInstalledPackages(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= paramContext.size())
      {
        installedApps = localHashSet;
        return localHashSet;
      }
      localHashSet.add(((PackageInfo)paramContext.get(i)).packageName);
      i += 1;
    }
  }
  
  public static JSONObject getMessage(Context paramContext)
  {
    localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("device_key", ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId());
      try
      {
        localJSONObject.put("app_version", paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName);
        localJSONObject.put("os_version", Build.VERSION.RELEASE);
        Object localObject = new Configuration();
        Settings.System.getConfiguration(paramContext.getContentResolver(), (Configuration)localObject);
        if (((Configuration)localObject).locale != null)
        {
          localJSONObject.put("country", ((Configuration)localObject).locale.getCountry());
          localJSONObject.put("language", ((Configuration)localObject).locale.toString());
          localJSONObject.put("timezone", Calendar.getInstance(((Configuration)localObject).locale).getTimeZone().getRawOffset() / 3600000);
        }
        localJSONObject.put("lat", 0.0D);
        localJSONObject.put("lng", 0.0D);
        localObject = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        paramContext = localObject.split(" ")[0];
        localObject = localObject.split(" ")[1];
        localJSONObject.put("date", paramContext);
        localJSONObject.put("time", localObject);
        return localJSONObject;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          localJSONObject.put("app_version", "unknown");
        }
      }
      return localJSONObject;
    }
    catch (Exception paramContext)
    {
      Log.e("error", paramContext.getMessage());
    }
  }
  
  public static boolean isChinese(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.toString().equals("zh_CN");
  }
  
  public static boolean isScreenPortrait(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 1;
  }
}
