package com.umeng.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.umeng.common.b.g;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import javax.microedition.khronos.opengles.GL10;

public class b
{
  protected static final String a = b.class.getName();
  protected static final String b = "Unknown";
  public static final int c = 8;
  private static final String d = "2G/3G";
  private static final String e = "Wi-Fi";
  
  public b() {}
  
  public static int a(Date paramDate1, Date paramDate2)
  {
    Date localDate;
    if (paramDate1.after(paramDate2))
    {
      localDate = paramDate2;
      paramDate2 = paramDate1;
    }
    for (;;)
    {
      long l = localDate.getTime();
      return (int)((paramDate2.getTime() - l) / 1000L);
      localDate = paramDate1;
    }
  }
  
  public static String a()
  {
    Object localObject2 = null;
    localObject1 = null;
    for (;;)
    {
      try
      {
        FileReader localFileReader = new FileReader("/proc/cpuinfo");
        if (localFileReader != null) {
          localObject1 = localObject2;
        }
        try
        {
          BufferedReader localBufferedReader = new BufferedReader(localFileReader, 1024);
          localObject1 = localObject2;
          localObject2 = localBufferedReader.readLine();
          localObject1 = localObject2;
          Object localObject3 = localObject2;
          Log.e(a, "Could not open file /proc/cpuinfo", localFileNotFoundException1);
        }
        catch (IOException localIOException)
        {
          try
          {
            localBufferedReader.close();
            localObject1 = localObject2;
            localObject3 = localObject2;
            localFileReader.close();
            localObject1 = localObject2;
            localObject2 = localObject1;
            if (localObject1 != null) {
              localObject2 = localObject1.substring(localObject1.indexOf(':') + 1);
            }
            return ((String)localObject2).trim();
          }
          catch (FileNotFoundException localFileNotFoundException1)
          {
            localObject1 = localObject3;
          }
          localIOException = localIOException;
          localObject3 = localObject1;
          Log.e(a, "Could not read from file /proc/cpuinfo", localIOException);
        }
      }
      catch (FileNotFoundException localFileNotFoundException2)
      {
        localObject1 = null;
      }
    }
  }
  
  public static String a(Date paramDate)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paramDate);
  }
  
  public static Date a(String paramString)
  {
    try
    {
      paramString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramString);
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static boolean a(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.toString().equals(Locale.CHINA.toString());
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0;
  }
  
  public static boolean a(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public static String[] a(GL10 paramGL10)
  {
    try
    {
      String str = paramGL10.glGetString(7936);
      paramGL10 = paramGL10.glGetString(7937);
      return new String[] { str, paramGL10 };
    }
    catch (Exception paramGL10)
    {
      Log.e(a, "Could not read gpu infor:", paramGL10);
    }
    return new String[0];
  }
  
  public static Set<String> b(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      localHashSet.add(((PackageInfo)paramContext.get(i)).packageName);
      i += 1;
    }
    return localHashSet;
  }
  
  public static boolean b()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static String c()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("yyyy-MM-dd").format(localDate);
  }
  
  public static boolean c(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 1;
  }
  
  public static String d(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return String.valueOf(i);
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "Unknown";
  }
  
  public static String e(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "Unknown";
  }
  
  public static String f(Context paramContext)
  {
    Object localObject = (TelephonyManager)paramContext.getSystemService("phone");
    if (localObject == null) {
      Log.w(a, "No IMEI.");
    }
    try
    {
      if (a(paramContext, "android.permission.READ_PHONE_STATE"))
      {
        str = ((TelephonyManager)localObject).getDeviceId();
        localObject = str;
        if (TextUtils.isEmpty(str))
        {
          Log.w(a, "No IMEI.");
          str = q(paramContext);
          localObject = str;
          if (TextUtils.isEmpty(str))
          {
            Log.w(a, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
            localObject = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
            Log.w(a, "getDeviceId: Secure.ANDROID_ID: " + (String)localObject);
          }
        }
        return localObject;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.w(a, "No IMEI.", localException);
        String str = "";
      }
    }
  }
  
  public static String g(Context paramContext)
  {
    return g.b(f(paramContext));
  }
  
  public static String h(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext == null) {
        return "Unknown";
      }
      paramContext = paramContext.getNetworkOperatorName();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "Unknown";
  }
  
  public static String i(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      int i = localDisplayMetrics.widthPixels;
      int j = localDisplayMetrics.heightPixels;
      paramContext = String.valueOf(j) + "*" + String.valueOf(i);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "Unknown";
  }
  
  public static String[] j(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "Unknown";
    arrayOfString[1] = "Unknown";
    if (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0)
    {
      arrayOfString[0] = "Unknown";
      return arrayOfString;
    }
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null)
    {
      arrayOfString[0] = "Unknown";
      return arrayOfString;
    }
    if (paramContext.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED)
    {
      arrayOfString[0] = "Wi-Fi";
      return arrayOfString;
    }
    paramContext = paramContext.getNetworkInfo(0);
    if (paramContext.getState() == NetworkInfo.State.CONNECTED)
    {
      arrayOfString[0] = "2G/3G";
      arrayOfString[1] = paramContext.getSubtypeName();
      return arrayOfString;
    }
    return arrayOfString;
  }
  
  public static boolean k(Context paramContext)
  {
    return "Wi-Fi".equals(j(paramContext)[0]);
  }
  
  public static Location l(Context paramContext)
  {
    try
    {
      LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
      if (a(paramContext, "android.permission.ACCESS_FINE_LOCATION"))
      {
        Location localLocation = localLocationManager.getLastKnownLocation("gps");
        if (localLocation != null)
        {
          Log.i(a, "get location from gps:" + localLocation.getLatitude() + "," + localLocation.getLongitude());
          return localLocation;
        }
      }
      if (a(paramContext, "android.permission.ACCESS_COARSE_LOCATION"))
      {
        paramContext = localLocationManager.getLastKnownLocation("network");
        if (paramContext != null)
        {
          Log.i(a, "get location from network:" + paramContext.getLatitude() + "," + paramContext.getLongitude());
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      Log.e(a, paramContext.getMessage());
      return null;
    }
    Log.i(a, "Could not get location from GPS or Cell-id, lack ACCESS_COARSE_LOCATION or ACCESS_COARSE_LOCATION permission?");
    return null;
  }
  
  public static boolean m(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = paramContext.isConnectedOrConnecting();
        return bool;
      }
      return false;
    }
    catch (Exception paramContext) {}
    return true;
  }
  
  public static int n(Context paramContext)
  {
    try
    {
      Locale localLocale = Locale.getDefault();
      Configuration localConfiguration = new Configuration();
      Settings.System.getConfiguration(paramContext.getContentResolver(), localConfiguration);
      paramContext = localLocale;
      if (localConfiguration != null)
      {
        paramContext = localLocale;
        if (localConfiguration.locale != null) {
          paramContext = localConfiguration.locale;
        }
      }
      paramContext = Calendar.getInstance(paramContext);
      if (paramContext != null)
      {
        int i = paramContext.getTimeZone().getRawOffset() / 3600000;
        return i;
      }
    }
    catch (Exception paramContext)
    {
      Log.i(a, "error in getTimeZone", paramContext);
    }
    return 8;
  }
  
  public static String[] o(Context paramContext)
  {
    arrayOfString = new String[2];
    arrayOfString[0] = "Unknown";
    arrayOfString[1] = "Unknown";
    try
    {
      Configuration localConfiguration = new Configuration();
      Settings.System.getConfiguration(paramContext.getContentResolver(), localConfiguration);
      if ((localConfiguration != null) && (localConfiguration.locale != null))
      {
        arrayOfString[0] = localConfiguration.locale.getCountry();
        arrayOfString[1] = localConfiguration.locale.toString();
      }
      for (;;)
      {
        if (TextUtils.isEmpty(arrayOfString[0])) {
          arrayOfString[0] = "Unknown";
        }
        if (!TextUtils.isEmpty(arrayOfString[1])) {
          break;
        }
        arrayOfString[1] = "Unknown";
        return arrayOfString;
        paramContext = Locale.getDefault();
        if (paramContext != null)
        {
          arrayOfString[0] = paramContext.getCountry();
          arrayOfString[1] = paramContext.getLanguage();
        }
      }
      return arrayOfString;
    }
    catch (Exception paramContext)
    {
      Log.e(a, "error in getLocaleInfo", paramContext);
    }
  }
  
  public static String p(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData.getString("UMENG_APPKEY");
        if (paramContext != null) {
          return paramContext.trim();
        }
        Log.e(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Log.e(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", paramContext);
      }
    }
    return null;
  }
  
  public static String q(Context paramContext)
  {
    try
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      if (a(paramContext, "android.permission.ACCESS_WIFI_STATE")) {
        return localWifiManager.getConnectionInfo().getMacAddress();
      }
      Log.w(a, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Log.w(a, "Could not get mac address." + paramContext.toString());
      }
    }
    return "";
  }
  
  public static String r(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      int i = localDisplayMetrics.widthPixels;
      int j = localDisplayMetrics.heightPixels;
      paramContext = new StringBuffer();
      paramContext.append(i);
      paramContext.append("*");
      paramContext.append(j);
      paramContext = paramContext.toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Log.e(a, "read resolution fail", paramContext);
    }
    return "Unknown";
  }
  
  public static String s(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Log.i(a, "read carrier fail", paramContext);
    }
    return "Unknown";
  }
  
  public static String t(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if ((paramContext != null) && (paramContext.metaData != null))
      {
        paramContext = paramContext.metaData.get("UMENG_CHANNEL");
        if (paramContext != null)
        {
          paramContext = paramContext.toString();
          if (paramContext != null) {
            return paramContext;
          }
          Log.i(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
        }
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Log.i(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
        paramContext.printStackTrace();
      }
    }
    return "Unknown";
  }
  
  public static String u(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  public static String v(Context paramContext)
  {
    return paramContext.getPackageManager().getApplicationLabel(paramContext.getApplicationInfo()).toString();
  }
}
