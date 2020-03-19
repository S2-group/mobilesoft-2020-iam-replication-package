package mobi.oneway.sdk.port;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.File;
import java.util.Locale;
import java.util.TimeZone;
import mobi.oneway.sdk.a.d;
import mobi.oneway.sdk.b.a;
import mobi.oneway.sdk.c.i;
import mobi.oneway.sdk.d.b;
import mobi.oneway.sdk.d.h;
import mobi.oneway.sdk.d.j;
import mobi.oneway.sdk.d.n;
import mobi.oneway.sdk.d.u;
import mobi.oneway.sdk.e.l;
import org.json.JSONArray;

public class MobileInfo
{
  public MobileInfo() {}
  
  @l
  public static String getAdvertisingTrackingId()
  {
    return d.o();
  }
  
  @l
  public static String getAndroidId()
  {
    return d.n();
  }
  
  @l
  public static int getApiLevel()
  {
    return Build.VERSION.SDK_INT;
  }
  
  @l
  public static long getAvailableMemory()
  {
    return d.D();
  }
  
  @l
  public static float getBatteryLevel()
  {
    return d.z();
  }
  
  @l
  public static int getBatteryStatus()
  {
    return d.A();
  }
  
  @l
  public static String getBuildSerial()
  {
    return Build.SERIAL;
  }
  
  @l
  public static String getConnectionType()
  {
    if (d.r()) {
      return "wifi";
    }
    if (d.u()) {
      return "cellular";
    }
    return "none";
  }
  
  @l
  public static String getCpuName()
  {
    return h.a();
  }
  
  @l
  public static String getCpuSerial()
  {
    return h.b();
  }
  
  @l
  public static int getDeviceVolume(Integer paramInteger)
  {
    return d.a(paramInteger.intValue());
  }
  
  private static File getFileForStorageType(MobileInfo.StorageType paramStorageType)
  {
    switch (MobileInfo.1.$SwitchMap$mobi$oneway$sdk$port$MobileInfo$StorageType[paramStorageType.ordinal()])
    {
    default: 
      n.c(new Object[] { "Unhandled storagetype: " + paramStorageType });
      return null;
    case 1: 
      return a.b().getCacheDir();
    }
    return a.b().getExternalCacheDir();
  }
  
  @l
  public static long getFreeMemory()
  {
    return d.C();
  }
  
  @l
  public static mobi.oneway.sdk.e.c getFreeSpace(String paramString)
  {
    try
    {
      MobileInfo.StorageType localStorageType = getStorageTypeFromString(paramString);
      if (localStorageType == null) {
        return mobi.oneway.sdk.e.c.a(i.c, new Object[] { paramString });
      }
      paramString = mobi.oneway.sdk.e.c.a(new Object[] { Long.valueOf(j.a(getFileForStorageType(localStorageType))) });
      return paramString;
    }
    catch (Exception paramString)
    {
      n.a("Exception on getFreeSpace.", paramString);
    }
    return mobi.oneway.sdk.e.c.a(tmp58_55);
  }
  
  @l
  public static boolean getHeadset()
  {
    return d.w();
  }
  
  @l
  public static String getIMEI()
  {
    return d.j();
  }
  
  @l
  public static String getIMSI()
  {
    return d.g();
  }
  
  @l
  public static JSONArray getInstalledPackages(boolean paramBoolean)
  {
    return new JSONArray(mobi.oneway.sdk.d.c.a(paramBoolean));
  }
  
  @l
  public static boolean getLimitAdTrackingFlag()
  {
    return mobi.oneway.sdk.a.c.b();
  }
  
  @l
  public static String getLinuxCoreVersion()
  {
    return d.t();
  }
  
  @l
  public static String getMacAddress()
  {
    return d.k();
  }
  
  @l
  public static String getManufacturer()
  {
    return Build.MANUFACTURER;
  }
  
  @l
  public static String getMemoryInfo()
  {
    return d.C() + "/" + d.B();
  }
  
  @l
  public static String getModel()
  {
    return Build.MODEL;
  }
  
  @l
  public static String getNetworkOperator()
  {
    return d.c();
  }
  
  @l
  public static String getNetworkOperatorName()
  {
    return d.d();
  }
  
  @l
  public static int getNetworkType()
  {
    return d.b();
  }
  
  @l
  public static String getOsVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  @l
  public static String getPhoneBuildInfo()
  {
    return d.a();
  }
  
  @l
  public static mobi.oneway.sdk.e.c getRingerMode()
  {
    int i = d.x();
    if (i > -1) {
      return mobi.oneway.sdk.e.c.a(new Object[] { Integer.valueOf(i) });
    }
    switch (i)
    {
    default: 
      n.c(new Object[] { "getRingerMode error: " + i });
      return mobi.oneway.sdk.e.c.a(new Object[] { Integer.valueOf(i) });
    case -1: 
      return mobi.oneway.sdk.e.c.a(i.a, new Object[] { Integer.valueOf(i) });
    }
    return mobi.oneway.sdk.e.c.a(i.b, new Object[] { Integer.valueOf(i) });
  }
  
  @l
  public static String getRomBaseBoard()
  {
    return d.s();
  }
  
  @l
  public static mobi.oneway.sdk.e.c getScreenBrightness()
  {
    int i = d.y();
    if (i > -1) {
      return mobi.oneway.sdk.e.c.a(new Object[] { Integer.valueOf(i) });
    }
    switch (i)
    {
    default: 
      n.c(new Object[] { "getScreenBrightness error: " + i });
      return mobi.oneway.sdk.e.c.a(new Object[] { Integer.valueOf(-1) });
    }
    return mobi.oneway.sdk.e.c.a(i.a, new Object[] { Integer.valueOf(i) });
  }
  
  @l
  public static int getScreenDensity()
  {
    return u.a();
  }
  
  @l
  public static int getScreenHeight()
  {
    return u.c();
  }
  
  @l
  public static int getScreenLayout()
  {
    return u.d();
  }
  
  @l
  public static int getScreenWidth()
  {
    return u.b();
  }
  
  @l
  public static String getSdCardStorageInfo()
  {
    return j.a();
  }
  
  @l
  public static String getSensorList()
  {
    return d.q();
  }
  
  @l
  public static String getSimOperator()
  {
    return d.e();
  }
  
  @l
  public static String getSimOperatorName()
  {
    return d.f();
  }
  
  @l
  public static String getSimSerialNumber()
  {
    return d.h();
  }
  
  @l
  public static int getSimState()
  {
    return d.i();
  }
  
  private static MobileInfo.StorageType getStorageTypeFromString(String paramString)
  {
    try
    {
      MobileInfo.StorageType localStorageType = MobileInfo.StorageType.valueOf(paramString);
      return localStorageType;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      n.a("Illegal argument: " + paramString, localIllegalArgumentException);
    }
    return null;
  }
  
  @l
  public static String getSystemLanguage()
  {
    return Locale.getDefault().toString();
  }
  
  @l
  public static String getSystemProperty(String paramString1, String paramString2)
  {
    return d.a(paramString1, paramString2);
  }
  
  @l
  public static String getSystemStorageInfo()
  {
    return j.b();
  }
  
  @l
  public static String getTimeZone(Boolean paramBoolean)
  {
    return TimeZone.getDefault().getDisplayName(paramBoolean.booleanValue(), 0, Locale.US);
  }
  
  @l
  public static long getTotalMemory()
  {
    return d.B();
  }
  
  @l
  public static mobi.oneway.sdk.e.c getTotalSpace(String paramString)
  {
    try
    {
      MobileInfo.StorageType localStorageType = getStorageTypeFromString(paramString);
      if (localStorageType == null) {
        return mobi.oneway.sdk.e.c.a(i.c, new Object[] { paramString });
      }
      paramString = mobi.oneway.sdk.e.c.a(new Object[] { Long.valueOf(j.b(getFileForStorageType(localStorageType))) });
      return paramString;
    }
    catch (Exception paramString)
    {
      n.a("Exception on getTotalSpace.", paramString);
    }
    return mobi.oneway.sdk.e.c.a(tmp59_56);
  }
  
  @l
  public static String getUniqueEventId()
  {
    return d.v();
  }
  
  @l
  public static String getWifiBSSID()
  {
    return d.m();
  }
  
  @l
  public static String getWifiSSID()
  {
    return d.l();
  }
  
  @l
  public static Boolean hasEmuBuildInfo()
  {
    return b.f();
  }
  
  @l
  public static boolean hasEmuBuildSerialInfo()
  {
    return b.b(a.b());
  }
  
  @l
  public static boolean hasEmuCpuInfo()
  {
    return b.b();
  }
  
  @l
  public static Boolean hasEmuDriverFiles()
  {
    return b.d();
  }
  
  @l
  public static boolean hasEmuOperatorName()
  {
    return b.c();
  }
  
  @l
  public static boolean hasEmuPipeFiles()
  {
    return b.a();
  }
  
  @l
  public static Boolean hasEmuSystemFiles()
  {
    return b.e();
  }
  
  @l
  public static boolean hasSdCard()
  {
    return j.c();
  }
  
  @l
  public static boolean isAppInstalled(String paramString)
  {
    return mobi.oneway.sdk.d.c.b(paramString);
  }
  
  @l
  public static boolean isRooted()
  {
    return d.E();
  }
  
  @l
  public static boolean isSimulator()
  {
    return b.a(a.b());
  }
  
  @l
  public static boolean isSupportBlueTooth()
  {
    return d.p();
  }
  
  @l
  public static boolean isTablet()
  {
    return d.a(a.b());
  }
}
