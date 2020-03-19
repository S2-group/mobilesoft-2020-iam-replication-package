package com.cuebiq.cuebiqsdk.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManager;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManagerFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
  private static final Pattern IPV4_LOCAL_PATTERN = Pattern.compile("((((127)|(10))\\.[0-9]+\\.[0-9]+\\.[0-9]+)|(((172\\.(1[6-9]|2[0-9]|3[0-1]))|(192\\.168))\\.[0-9]+\\.[0-9]+)|(^172\\.3[0-1]\\.)|(^fe80:(:[0-9a-fA-F]{0,4}){0,4}))$");
  private static final Pattern IPV4_PATTERN = Pattern.compile("(([0-9](?!\\d)|[1-9][0-9](?!\\d)|1[0-9]{2}(?!\\d)|2[0-4][0-9](?!\\d)|25[0-5](?!\\d))[.]?){4}");
  private static final Pattern IPV6_LOCAL_PATTERN = Pattern.compile("(^(fe80)|(FE80):(:[0-9a-fA-F]{0,4}){0,4})$");
  private static final Pattern IPV6_PATTERN = Pattern.compile("(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))");
  
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
  
  public static List<com.cuebiq.cuebiqsdk.model.wrapper.BluetoothDevice> getPairedDevices(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      if ((Build.VERSION.SDK_INT >= 23) && (paramContext.checkSelfPermission("android.permission.BLUETOOTH") == 0))
      {
        paramContext = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (paramContext != null)
        {
          paramContext = paramContext.iterator();
          while (paramContext.hasNext())
          {
            android.bluetooth.BluetoothDevice localBluetoothDevice = (android.bluetooth.BluetoothDevice)paramContext.next();
            com.cuebiq.cuebiqsdk.model.wrapper.BluetoothDevice localBluetoothDevice1 = new com.cuebiq.cuebiqsdk.model.wrapper.BluetoothDevice();
            if (localBluetoothDevice.getName() != null)
            {
              if (localBluetoothDevice.getBluetoothClass() != null)
              {
                localBluetoothDevice1.setName(localBluetoothDevice.getName());
                localBluetoothDevice1.setType(Integer.valueOf(localBluetoothDevice.getType()));
                localBluetoothDevice1.setDeviceClass(Integer.valueOf(localBluetoothDevice.getBluetoothClass().getDeviceClass()));
              }
              localArrayList.add(localBluetoothDevice1);
            }
          }
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext) {}
  }
  
  public static boolean isAndroidVersionNotSupported(int paramInt)
  {
    if (Build.VERSION.SDK_INT < paramInt)
    {
      CuebiqSDKImpl.log("CuebiqSDK works only on Android API Level " + paramInt + "+");
      return true;
    }
    return false;
  }
  
  public static boolean isFlushCounterActive(Context paramContext)
  {
    int i = PersistenceManagerFactory.get().getNextFlushCounter(paramContext);
    if (i == 0) {}
    do
    {
      return false;
      if (i < 0)
      {
        PersistenceManagerFactory.get().setNextFlushingContent(paramContext, 0);
        return false;
      }
    } while (i <= 0);
    PersistenceManagerFactory.get().setNextFlushingContent(paramContext, i - 1);
    return true;
  }
  
  public static boolean isIPv4(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return false;
    }
    return IPV4_PATTERN.matcher(paramString).find();
  }
  
  public static boolean isIPv6(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return false;
    }
    return IPV6_PATTERN.matcher(paramString).find();
  }
  
  public static boolean isLocationEnabled(Context paramContext)
  {
    if ((Build.VERSION.SDK_INT > 22) && (paramContext.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0))
    {
      CuebiqSDKImpl.log("CuebiqSDK -> Permission about LOCATION is not granted.");
      return false;
    }
    LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
    try
    {
      boolean bool = localLocationManager.isProviderEnabled(CuebiqSDKImpl.getBeAudienceConfiguration(paramContext).getAcc());
      return bool;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  public static boolean isOptedOut(Context paramContext)
  {
    return (CuebiqSDKImpl.getBeAudienceConfiguration(paramContext).getTase() != 1) && (PersistenceManagerFactory.get().isGAIDDisabled(paramContext));
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
