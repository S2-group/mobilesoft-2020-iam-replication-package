package com.zf.modules.device;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.v4.app.aj;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.zf3.core.b;
import com.zf3.core.events.GameActivityOnDestroyCalled;
import com.zf3.core.events.GameActivityOnResumeCalled;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.greenrobot.eventbus.c;
import org.greenrobot.eventbus.j;

public class ZSystemInfo
{
  private static final String TAG = "ZSystemInfo";
  private final Activity m_activity;
  private String m_cachedAdvertisingId = null;
  
  public ZSystemInfo(Activity paramActivity)
  {
    this.m_activity = paramActivity;
    b.a().b().a(this);
  }
  
  public static void createServiceInstance()
  {
    ZSystemInfo localZSystemInfo = new ZSystemInfo(b.a().c());
    b.a().a(ZSystemInfo.class, localZSystemInfo);
  }
  
  public static String getLocaleStatic()
  {
    Object localObject = Locale.getDefault();
    String str1 = ((Locale)localObject).getLanguage().toLowerCase();
    String str2 = ((Locale)localObject).getCountry().toLowerCase();
    if ((str1.equals("pt")) && (str2.equals("br"))) {
      localObject = "br";
    }
    do
    {
      do
      {
        return localObject;
        localObject = str1;
      } while (!str1.equals("zh"));
      localObject = str1;
    } while (!str2.equals("cn"));
    return "zh-hans";
  }
  
  public static Object getServiceInstance()
  {
    return b.a().a(ZSystemInfo.class);
  }
  
  @SuppressLint({"NewApi"})
  public boolean amazonTimeBombIsActive()
  {
    if (Build.VERSION.SDK_INT >= 9) {}
    do
    {
      do
      {
        try
        {
          long l = this.m_activity.getPackageManager().getPackageInfo(this.m_activity.getPackageName(), 0).firstInstallTime;
          Calendar localCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-7"));
          localCalendar.setTimeInMillis(l);
          if (localCalendar.get(1) != 2013) {
            return false;
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          return false;
        }
      } while ((localException.get(2) != 5) || (localException.get(5) < 4) || ((localException.get(5) == 4) && (localException.get(11) < 4)));
      localException.setTimeInMillis(System.currentTimeMillis());
    } while ((localException.get(1) != 2013) || (localException.get(2) != 5) || (localException.get(5) > 6) || ((localException.get(5) == 6) && (localException.get(11) > 12)));
    return true;
  }
  
  public boolean areNotificationsEnabled()
  {
    return aj.a(this.m_activity.getApplicationContext()).b();
  }
  
  public String getAdvertisingId()
  {
    return this.m_cachedAdvertisingId;
  }
  
  public String getAndroidId()
  {
    String str = Settings.Secure.getString(this.m_activity.getContentResolver(), "android_id");
    if (str != null) {
      return str;
    }
    return "UNDEFINED";
  }
  
  public String getAppName()
  {
    try
    {
      Object localObject = this.m_activity.getPackageManager().getActivityInfo(this.m_activity.getComponentName(), 0);
      localObject = this.m_activity.getResources().getString(((ActivityInfo)localObject).labelRes);
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return "UNDEFINED";
  }
  
  public String getAppVersion()
  {
    try
    {
      String str = this.m_activity.getPackageManager().getPackageInfo(this.m_activity.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return "UNDEFINED";
  }
  
  public String getCarrierId()
  {
    Object localObject1 = (TelephonyManager)this.m_activity.getSystemService("phone");
    if (localObject1 != null) {}
    for (localObject1 = ((TelephonyManager)localObject1).getSimOperator();; localObject1 = null)
    {
      Object localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = "";
      }
      return localObject2;
    }
  }
  
  public String getCarrierName()
  {
    Object localObject1 = (TelephonyManager)this.m_activity.getSystemService("phone");
    if (localObject1 != null) {}
    for (localObject1 = ((TelephonyManager)localObject1).getSimOperatorName();; localObject1 = null)
    {
      Object localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = "";
      }
      return localObject2;
    }
  }
  
  public String getCountryISOCode()
  {
    return Locale.getDefault().getCountry().toUpperCase();
  }
  
  public float getDensity()
  {
    return this.m_activity.getResources().getDisplayMetrics().density;
  }
  
  public float getDensityMagic()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.m_activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f = localDisplayMetrics.xdpi;
    return (localDisplayMetrics.ydpi + f) / 2.0F / 2.54F;
  }
  
  public String getDeviceManufacturer()
  {
    return Build.MANUFACTURER;
  }
  
  public String[] getInstalledApps(ArrayList paramArrayList)
  {
    paramArrayList = new ArrayList();
    Iterator localIterator = this.m_activity.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.startsWith("com.zeptolab")) {
        paramArrayList.add(localPackageInfo.packageName + ":" + localPackageInfo.versionName);
      }
    }
    return (String[])paramArrayList.toArray(new String[paramArrayList.size()]);
  }
  
  public String getIpAddress()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
        Iterator localIterator;
        while (!localIterator.hasNext())
        {
          if (!((Iterator)localObject).hasNext()) {
            break;
          }
          localIterator = Collections.list(((NetworkInterface)((Iterator)localObject).next()).getInetAddresses()).iterator();
        }
        localInetAddress = (InetAddress)localIterator.next();
      } while ((localInetAddress.isLoopbackAddress()) || (!(localInetAddress instanceof Inet4Address)));
      Object localObject = localInetAddress.getHostAddress();
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public String getLocale()
  {
    return getLocaleStatic();
  }
  
  public String getModel()
  {
    return Build.MODEL;
  }
  
  public int getNetworkType()
  {
    if (this.m_activity.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", getPackageName()) == 0)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.m_activity.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        switch (localNetworkInfo.getType())
        {
        default: 
          return 2;
        case 1: 
        case 6: 
          return 1;
        }
        return 2;
      }
    }
    return 0;
  }
  
  public int getOSVersionAsInt()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public String getOSVersionAsString()
  {
    return Build.VERSION.RELEASE;
  }
  
  public String getPackageName()
  {
    return this.m_activity.getPackageName();
  }
  
  public String getTimeZone()
  {
    return new SimpleDateFormat("Z").format(new Date());
  }
  
  public String getWifiMacAddress()
  {
    Object localObject = (WifiManager)this.m_activity.getApplicationContext().getSystemService("wifi");
    try
    {
      localObject = ((WifiManager)localObject).getConnectionInfo().getMacAddress();
      if (localObject != null) {
        return localObject;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "UNDEFINED";
  }
  
  public boolean haveApplicationInstalled(String paramString)
  {
    PackageManager localPackageManager = this.m_activity.getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public boolean isInstalledFromStore()
  {
    String str = this.m_activity.getPackageManager().getInstallerPackageName(this.m_activity.getPackageName());
    return (str != null) && (str.startsWith("com.android.vending"));
  }
  
  public boolean isNetworkAvailable()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.m_activity.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
  
  public boolean isRooted()
  {
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "/sbin/";
    arrayOfString[1] = "/system/bin/";
    arrayOfString[2] = "/system/xbin/";
    arrayOfString[3] = "/data/local/xbin/";
    arrayOfString[4] = "/data/local/bin/";
    arrayOfString[5] = "/system/sd/xbin/";
    arrayOfString[6] = "/system/bin/failsafe/";
    arrayOfString[7] = "/data/local/";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      if (new File(str + "su").exists()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public boolean isSignatureMatches(String paramString)
  {
    try
    {
      PackageInfo localPackageInfo = this.m_activity.getPackageManager().getPackageInfo(this.m_activity.getPackageName(), 64);
      if (localPackageInfo.signatures.length != 1) {
        return false;
      }
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA");
      localMessageDigest.update(localPackageInfo.signatures[0].toByteArray());
      boolean bool = paramString.equals(Base64.encodeToString(localMessageDigest.digest(), 0));
      return bool;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public boolean isTablet()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.m_activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    double d1 = localDisplayMetrics.widthPixels / localDisplayMetrics.xdpi;
    double d2 = localDisplayMetrics.heightPixels / localDisplayMetrics.ydpi;
    return Math.sqrt(d2 * d2 + d1 * d1) >= 6.5D;
  }
  
  @j
  public void onMainActivityDestroyed(GameActivityOnDestroyCalled paramGameActivityOnDestroyCalled)
  {
    b.a().b().c(this);
  }
  
  @j
  public void onMainActivityResumed(GameActivityOnResumeCalled paramGameActivityOnResumeCalled) {}
  
  public int totalMemory()
  {
    int i = 0;
    try
    {
      Pattern localPattern = Pattern.compile("MemTotal: *([0-9]+).*");
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"));
      for (;;)
      {
        Object localObject = localBufferedReader.readLine();
        if (localObject == null) {
          break;
        }
        localObject = localPattern.matcher(((String)localObject).trim());
        if (((Matcher)localObject).matches()) {
          i = Integer.parseInt(((Matcher)localObject).group(1));
        }
      }
      localBufferedReader.close();
      if (i == 0) {
        return 512;
      }
      i /= 1024;
      return i;
    }
    catch (Exception localException) {}
    return 512;
  }
}
