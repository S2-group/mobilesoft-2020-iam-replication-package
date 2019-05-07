package com.beintoo.beaudiencesdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import com.beintoo.beaudiencesdk.BeAudienceImpl;
import com.beintoo.beaudiencesdk.model.config.BeAudienceConfiguration;
import com.beintoo.beaudiencesdk.model.helper.BeAudienceCustomResourcesHelper;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.conn.util.InetAddressUtils;

public class Utils
{
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
    paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    if (paramContext == null) {}
    int i;
    int j;
    do
    {
      return 50.0F;
      i = paramContext.getIntExtra("level", -1);
      j = paramContext.getIntExtra("scale", -1);
    } while ((i == -1) || (j == -1));
    return i / j * 100.0F;
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
      } while ((!InetAddressUtils.isIPv4Address((String)localObject)) || (Pattern.compile("((((127)|(10))\\.[0-9]+\\.[0-9]+\\.[0-9]+)|(((172\\.(1[6-9]|2[0-9]|3[0-1]))|(192\\.168))\\.[0-9]+\\.[0-9]+)|(^172\\.3[0-1]\\.)|(^fe80:(:[0-9a-fA-F]{0,4}){0,4}))$").matcher((CharSequence)localObject).find()));
      BeAudienceImpl.log("Utils -> IP Pubblico: " + (String)localObject);
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
          if (InetAddressUtils.isIPv4Address((String)localObject)) {
            break;
          }
          int i = ((String)localObject).indexOf('%');
          if (i < 0) {}
          while (!Pattern.compile("(^(fe80)|(FE80):(:[0-9a-fA-F]{0,4}){0,4})$").matcher((CharSequence)localObject).find())
          {
            BeAudienceImpl.log("Utils -> IP Pubblico: " + (String)localObject);
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
      if ((localApplicationInfo.flags & 0x80) == 1) {
        paramContext.add(localApplicationInfo.packageName);
      } else if ((localApplicationInfo.flags & 0x1) != 1) {
        paramContext.add(localApplicationInfo.packageName);
      }
    }
    return paramContext;
  }
  
  public static boolean isLocationEnabled(Context paramContext)
  {
    return ((LocationManager)paramContext.getSystemService("location")).isProviderEnabled(BeAudienceImpl.getBeAudienceConfiguration().getAcc());
  }
  
  public static boolean isOptedOut(Context paramContext)
  {
    if (!PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("beaudience_optin_agreed", true)) {}
    do
    {
      return true;
      if (!PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("beaudience_is_gai_disabled", false)) {
        break;
      }
    } while (PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("beaudience_source_optout_sent", false));
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("beaudience_source_optout_sent", true).commit();
    BeAudienceCustomResourcesHelper.get().trackDeviceIsOptOut(paramContext);
    return true;
    return false;
  }
}
