package com.samsungimaging.filesharing;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.webkit.MimeTypeMap;
import com.samsungimaging.connectionmanager.CMInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
  private static String ACCESS_METHOD_MANUAL = "manual";
  private static String ACCESS_METHOD_NFC;
  private static ArrayList<String> dscPrefixList = null;
  static String[] noTelephonyDeviceList = { "GT-P7500", "SCH-I905", "SHW-M300W", "SHW-M380K", "SHW-M380S", "SHW-M380W", "P3", "SGH-T859", "SHW-M180K", "SMT-i9100", "GT-P7300", "GT-P7310", "SGH-I957", "YP-G1", "YP-G70", "YP-GB1", "YP-GB70", "YP-GS1", "GT-B7510", "GT-B7510B", "GT-B7510L", "GT-B5510", "GT-B5510B", "GT-B5510L", "GT-B5512", "SGH-T939", "GT-I5500", "GT-I5500B", "GT-I5500L", "GT-I5503", "GT-B5512B", "GT-I5500M", "GT-I5503T", "GT-I5508", "GT-I5700L", "GT-I5800L", "GT-P1010", "GT-P1013", "GT-P6210", "GT-P6211", "GT-P6810", "GT-P7300B", "GT-P7320", "GT-P7500D", "GT-P7500M", "GT-P7500R", "GT-P7501", "GT-P7503", "GT-P7511", "GT-S5360", "GT-S5360B", "GT-S5360L", "GT-S5360T", "GT-S5363", "GT-S5368", "GT-S5369", "GT-S5570B", "GT-S5570I", "GT-S5570L", "GT-S5578", "GT-S5670B", "GT-S5670L", "GT-S6102", "GT-I7500", "GT-S5670", "GT-S5570", "SPH-M900", "SPH-M580", "SC-01D", "SCH-I559", "SCH-I815", "SCH-P739", "SCH-i509", "SCH-i559", "SGH-I957D", "SGH-I957M", "SGH-T499", "SGH-T499V", "SGH-T499Y", "SGH-T869", "SHV-E140K", "SHV-E140L", "SHV-E140S", "SHW-M180W", "SHW-M305W", "SHW-M430W", "SPH-M580BST", "YP-G50", "GT-P7510" };
  private static String userAgent;
  
  static
  {
    dscPrefixList = new ArrayList();
    dscPrefixList.add("WB150");
    dscPrefixList.add("DV300");
    dscPrefixList.add("DV500");
    dscPrefixList.add("WB300");
    dscPrefixList.add("ST200");
    dscPrefixList.add("WB850");
    dscPrefixList.add("NX20");
    dscPrefixList.add("NX210");
    dscPrefixList.add("NX1000");
    dscPrefixList.add("EX2");
    dscPrefixList.add("MV900");
    dscPrefixList.add("QF30");
    userAgent = null;
    ACCESS_METHOD_NFC = "nfc";
  }
  
  public Utils() {}
  
  public static boolean CheckTelephonyDevice(String paramString)
  {
    String[] arrayOfString;
    int j;
    int i;
    if (paramString != null)
    {
      arrayOfString = noTelephonyDeviceList;
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (paramString.equals(arrayOfString[i])) {
        return true;
      }
      i += 1;
    }
  }
  
  public static String formatSize(long paramLong)
  {
    return String.format("%.2f", new Object[] { Double.valueOf(paramLong / 1024.0D / 1024.0D) }) + "MB";
  }
  
  public static String getAccessMethod()
  {
    String str = ACCESS_METHOD_MANUAL;
    if (CMInfo.getInstance().getIsNFCLaunch()) {
      return ACCESS_METHOD_NFC;
    }
    return ACCESS_METHOD_MANUAL;
  }
  
  public static long getAvailableExternalMemorySize()
  {
    if (isExternalMemoryAvailable())
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      return localStatFs.getAvailableBlocks() * l;
    }
    return -1L;
  }
  
  public static long getAvailableInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  public static String getDefaultStorage()
  {
    return Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/Samsung Smart Camera Application/MobileLink";
  }
  
  public static boolean getDisplayConnGuide(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("connguide", true);
  }
  
  public static boolean getDisplayDetailGuide(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("guide", true);
  }
  
  public static boolean getDisplayNotice(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("notice", true);
  }
  
  public static String getExtention(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    if (paramString != null)
    {
      str1 = str2;
      if (paramString.lastIndexOf(".") != -1) {
        str1 = paramString.substring(paramString.lastIndexOf(".") + 1, paramString.length());
      }
    }
    return str1;
  }
  
  public static String getMacAddress(Context paramContext)
  {
    String str = "";
    WifiInfo localWifiInfo = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
    paramContext = str;
    if (localWifiInfo != null)
    {
      paramContext = localWifiInfo.getMacAddress();
      if (paramContext == null) {}
    }
    else
    {
      return paramContext;
    }
    return "UNKNOWN";
  }
  
  public static String getMimetype(String paramString)
  {
    String str = "";
    if (paramString.lastIndexOf(".") != -1) {
      str = paramString.substring(paramString.lastIndexOf(".") + 1, paramString.length()).toLowerCase();
    }
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
  }
  
  public static Map<String, PackageInfo> getPackageMap(Context paramContext)
  {
    SortedMap localSortedMap = Collections.synchronizedSortedMap(new TreeMap());
    paramContext = paramContext.getPackageManager();
    Iterator localIterator = paramContext.getInstalledApplications(128).iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localSortedMap;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      try
      {
        localSortedMap.put(localApplicationInfo.packageName, paramContext.getPackageInfo(localApplicationInfo.packageName, 0));
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
  }
  
  public static PackageInfo getPackageSmartWiFiCM(Context paramContext)
  {
    paramContext = getPackageMap(paramContext).values().iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return null;
      }
      localPackageInfo = (PackageInfo)paramContext.next();
    } while (!localPackageInfo.packageName.startsWith("com.skt.network.wificm."));
    return localPackageInfo;
  }
  
  public static String getThumbStorage()
  {
    return getDefaultStorage() + "/LazyList";
  }
  
  public static String getUserAgent()
  {
    return userAgent;
  }
  
  private static boolean isExternalMemoryAvailable()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean isICS()
  {
    return Build.MODEL.contains("Galaxy Nexus");
  }
  
  public static boolean isMemoryFull()
  {
    boolean bool = false;
    try
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      int i = localStatFs.getAvailableBlocks();
      if (i * l < 10000000L) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean isMountedExternalStorage()
  {
    boolean bool = true;
    String str = Environment.getExternalStorageState();
    if ((str.equals("removed")) || (str.equals("shared")) || (str.equals("bad_removal")) || (str.equals("unmounted")) || (str.equals("checking")) || (str.equals("unmountable"))) {
      bool = false;
    }
    return bool;
  }
  
  public static boolean isTab101()
  {
    return Build.MODEL.contains("SHW-M380");
  }
  
  public static boolean isTopActivity(Context paramContext, String paramString)
  {
    Object localObject;
    if ((paramContext != null) && (paramString != null))
    {
      localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
      paramContext = null;
      localObject = ((List)localObject).iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        if (!paramContext.topActivity.getClassName().equals(paramString)) {
          break;
        }
        return true;
      }
      paramContext = (ActivityManager.RunningTaskInfo)((Iterator)localObject).next();
    }
    return false;
  }
  
  public static void noop(long paramLong)
  {
    try
    {
      Thread.sleep(paramLong);
      Thread.yield();
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  private static String rename(String paramString)
  {
    Matcher localMatcher = Pattern.compile("(\\-)(\\d*)+[.]").matcher(paramString);
    if (localMatcher.find())
    {
      int i = Integer.parseInt(localMatcher.group().replace("-", "").replace(".", ""));
      return paramString.replaceAll("(\\-)(\\d*)+[.]", "-" + (i + 1) + ".");
    }
    return paramString.substring(0, paramString.lastIndexOf(".")) + "-0" + paramString.substring(paramString.lastIndexOf("."), paramString.length());
  }
  
  public static String renameFile(String paramString1, String paramString2)
  {
    File localFile = new File(paramString1, paramString2);
    String str = paramString2;
    for (paramString2 = localFile;; paramString2 = new File(paramString1, str))
    {
      if (!paramString2.exists()) {
        return str;
      }
      str = rename(str);
    }
  }
  
  public static void setDisplayConnGuide(Context paramContext, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("connguide", paramBoolean);
    paramContext.commit();
  }
  
  public static void setDisplayDetailGuide(Context paramContext, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("guide", paramBoolean);
    paramContext.commit();
  }
  
  public static void setDisplayNotice(Context paramContext, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("notice", paramBoolean);
    paramContext.commit();
  }
  
  public static void setUseragent(Context paramContext)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("SEC_DSC_");
    Object localObject1 = null;
    Object localObject3 = null;
    if (paramContext != null)
    {
      Object localObject2 = localObject3;
      if (!FileSharing.bNoTelephonyDevice)
      {
        localObject1 = (TelephonyManager)paramContext.getSystemService("phone");
        localObject2 = localObject3;
        if (((TelephonyManager)localObject1).getSimState() != 1) {
          localObject2 = ((TelephonyManager)localObject1).getLine1Number();
        }
      }
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (((String)localObject2).length() != 0) {}
      }
      else
      {
        localObject1 = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress().toLowerCase();
      }
    }
    if (localObject1 != null)
    {
      paramContext = (Context)localObject1;
      if (((String)localObject1).length() != 0) {}
    }
    else
    {
      paramContext = "UNKNOWN";
    }
    localStringBuffer.append(paramContext);
    userAgent = localStringBuffer.toString();
  }
  
  public static boolean supportDSCPrefix(String paramString)
  {
    Iterator localIterator;
    if (paramString != null) {
      localIterator = dscPrefixList.iterator();
    }
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!paramString.contains((String)localIterator.next()));
    return true;
  }
  
  public static long waitFor(long paramLong, IFunc paramIFunc)
  {
    long l3 = System.currentTimeMillis();
    long l1 = 0L;
    for (;;)
    {
      if (paramIFunc.func()) {
        return l1;
      }
      try
      {
        Thread.sleep(10L);
        Thread.yield();
        long l2 = System.currentTimeMillis() - l3;
        l1 = l2;
        if (l2 <= paramLong) {
          continue;
        }
        return l2;
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
  }
  
  public static abstract interface IFunc
  {
    public abstract boolean func();
  }
}
