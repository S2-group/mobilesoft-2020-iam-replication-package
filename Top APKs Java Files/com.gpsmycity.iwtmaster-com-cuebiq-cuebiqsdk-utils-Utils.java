package com.cuebiq.cuebiqsdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.helper.CustomResourcesHelper;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
  private static final Pattern IPV4_LOCAL_PATTERN = Pattern.compile("((((127)|(10))\\.[0-9]+\\.[0-9]+\\.[0-9]+)|(((172\\.(1[6-9]|2[0-9]|3[0-1]))|(192\\.168))\\.[0-9]+\\.[0-9]+)|(^172\\.3[0-1]\\.)|(^fe80:(:[0-9a-fA-F]{0,4}){0,4}))$");
  private static final String IPV4_LOCAL_REGEX = "((((127)|(10))\\.[0-9]+\\.[0-9]+\\.[0-9]+)|(((172\\.(1[6-9]|2[0-9]|3[0-1]))|(192\\.168))\\.[0-9]+\\.[0-9]+)|(^172\\.3[0-1]\\.)|(^fe80:(:[0-9a-fA-F]{0,4}){0,4}))$";
  private static final Pattern IPV4_PATTERN = Pattern.compile("(([0-9](?!\\d)|[1-9][0-9](?!\\d)|1[0-9]{2}(?!\\d)|2[0-4][0-9](?!\\d)|25[0-5](?!\\d))[.]?){4}");
  private static final String IPV4_REGEX = "(([0-9](?!\\d)|[1-9][0-9](?!\\d)|1[0-9]{2}(?!\\d)|2[0-4][0-9](?!\\d)|25[0-5](?!\\d))[.]?){4}";
  private static final Pattern IPV6_LOCAL_PATTERN = Pattern.compile("(^(fe80)|(FE80):(:[0-9a-fA-F]{0,4}){0,4})$");
  private static final String IPV6_LOCAL_REGEX = "(^(fe80)|(FE80):(:[0-9a-fA-F]{0,4}){0,4})$";
  private static final Pattern IPV6_PATTERN = Pattern.compile("(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))");
  private static final String IPV6_REGEX = "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";
  
  public Utils() {}
  
  public static float distanceBetweenMinorThreshold(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    Location localLocation1 = new Location("network");
    localLocation1.setLatitude(paramDouble1);
    localLocation1.setLongitude(paramDouble2);
    Location localLocation2 = new Location("network");
    localLocation2.setLatitude(paramDouble3);
    localLocation2.setLongitude(paramDouble4);
    return localLocation1.distanceTo(localLocation2);
  }
  
  public static float getBatteryLevel(Context paramContext)
  {
    try
    {
      paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      if (paramContext == null) {
        return 50.0F;
      }
      int i = paramContext.getIntExtra("level", -1);
      int j = paramContext.getIntExtra("scale", -1);
      if ((i != -1) && (j != -1)) {
        return i / j * 100.0F;
      }
    }
    catch (Throwable paramContext) {}
    return 50.0F;
  }
  
  public static String getIPAddressV4()
  {
    try
    {
      Object localObject;
      do
      {
        do
        {
          Iterator localIterator1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
          Iterator localIterator2;
          while (!localIterator2.hasNext())
          {
            if (!localIterator1.hasNext()) {
              break;
            }
            localIterator2 = Collections.list(((NetworkInterface)localIterator1.next()).getInetAddresses()).iterator();
          }
          localObject = (InetAddress)localIterator2.next();
        } while (((InetAddress)localObject).isLoopbackAddress());
        localObject = ((InetAddress)localObject).getHostAddress().toUpperCase();
      } while ((!isIPv4((String)localObject)) || (IPV4_LOCAL_PATTERN.matcher((CharSequence)localObject).find()));
      CuebiqSDKImpl.log("Utils -> IP Pubblico: " + (String)localObject);
      return localObject;
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  public static String getIPAddressV6()
  {
    try
    {
      Iterator localIterator1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
      label12:
      if (localIterator1.hasNext())
      {
        Iterator localIterator2 = Collections.list(((NetworkInterface)localIterator1.next()).getInetAddresses()).iterator();
        for (;;)
        {
          if (!localIterator2.hasNext()) {
            break label12;
          }
          Object localObject = (InetAddress)localIterator2.next();
          if (((InetAddress)localObject).isLoopbackAddress()) {
            break;
          }
          localObject = ((InetAddress)localObject).getHostAddress().toUpperCase();
          int i = ((String)localObject).indexOf('%');
          if (i < 0) {}
          while ((isIPv6((String)localObject)) && (!IPV6_LOCAL_PATTERN.matcher((CharSequence)localObject).find()))
          {
            CuebiqSDKImpl.log("Utils -> IP Pubblico: " + (String)localObject);
            return localObject;
            localObject = ((String)localObject).substring(0, i);
          }
        }
      }
      return null;
    }
    catch (Throwable localThrowable) {}
  }
  
  public static ArrayList<String> getInstalledApps(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(0);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if ((localApplicationInfo.flags & 0x1) != 1) {
        paramContext.add(localApplicationInfo.packageName);
      }
    }
    return paramContext;
  }
  
  public static String[] getMCCandMNC(Context paramContext)
  {
    String str = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperator();
    if ((str != null) && (!str.isEmpty())) {
      try
      {
        paramContext = str.substring(0, 3);
        str = str.substring(3);
        return new String[] { paramContext, str };
      }
      catch (Throwable paramContext) {}
    }
    return null;
  }
  
  public static boolean isAndroidVersionSupported(int paramInt)
  {
    if (Build.VERSION.SDK_INT < paramInt)
    {
      CuebiqSDKImpl.log("BeAudience works only on Android API Level " + paramInt + "+");
      return false;
    }
    return true;
  }
  
  public static boolean isIPv4(String paramString)
  {
    return IPV4_PATTERN.matcher(paramString).find();
  }
  
  public static boolean isIPv6(String paramString)
  {
    return IPV6_PATTERN.matcher(paramString).find();
  }
  
  public static boolean isLocationEnabled(Context paramContext)
  {
    if ((Build.VERSION.SDK_INT > 22) && (paramContext.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0))
    {
      CuebiqSDKImpl.log("CuebiqSDK -> Permission about LOCATION is not granted.");
      return false;
    }
    return ((LocationManager)paramContext.getSystemService("location")).isProviderEnabled(CuebiqSDKImpl.getBeAudienceConfiguration(paramContext).getAcc());
  }
  
  public static boolean isOptedOut(Context paramContext)
  {
    if (CuebiqSDKImpl.getBeAudienceConfiguration(paramContext).getTase() == 1) {}
    do
    {
      return false;
      if (!PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("beaudience_optin_agreed", true)) {
        return true;
      }
    } while (!PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("beaudience_is_gai_disabled", false));
    if (!PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("beaudience_source_optout_sent", false))
    {
      PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("beaudience_source_optout_sent", true).commit();
      CustomResourcesHelper.get().trackDeviceIsOptOut(paramContext, null);
    }
    return true;
  }
  
  public static boolean isWifiAlwaysScanning(Context paramContext)
  {
    if ((Build.VERSION.SDK_INT > 22) && (paramContext.checkSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0)) {
      CuebiqSDKImpl.log("CuebiqSDK -> Permission about WIFI is not granted.");
    }
    do
    {
      do
      {
        return false;
      } while (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", paramContext.getPackageName()) != 0);
      paramContext = (WifiManager)paramContext.getSystemService("wifi");
    } while ((Build.VERSION.SDK_INT < 18) || (!paramContext.isScanAlwaysAvailable()));
    return true;
  }
  
  public static boolean isWifiEnabled(Context paramContext)
  {
    if ((Build.VERSION.SDK_INT > 22) && (paramContext.checkSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0)) {
      CuebiqSDKImpl.log("CuebiqSDK -> Permission about WIFI is not granted.");
    }
    while (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", paramContext.getPackageName()) != 0) {
      return false;
    }
    return ((WifiManager)paramContext.getSystemService("wifi")).isWifiEnabled();
  }
}
