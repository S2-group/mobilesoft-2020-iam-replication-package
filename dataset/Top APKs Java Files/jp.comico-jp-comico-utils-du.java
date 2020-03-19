package jp.comico.utils;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.System;
import android.telephony.ServiceState;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class du
{
  private static final String LOG_FORMAT = "%1$s\n%2$s";
  public static String TAG;
  private static final int TIME_CHECK = 0;
  public static long delayMillis;
  public static boolean isDebugMode = true;
  private static du.MemoryMonitorRunnable mMonitorRunnable;
  private static Thread mMonitorThread;
  private static long sPeakNativeUsed = 0L;
  private static long sPeakVmUsed;
  private static long tempMillis;
  
  static
  {
    TAG = "comico_log";
    tempMillis = 0L;
    delayMillis = 0L;
    sPeakVmUsed = 0L;
  }
  
  public du() {}
  
  public static void d(Object... paramVarArgs)
  {
    trace(3, paramVarArgs);
  }
  
  public static void e(Object... paramVarArgs)
  {
    trace(6, paramVarArgs);
  }
  
  private static String format(double paramDouble)
  {
    if (paramDouble < 'Ѐ') {
      return paramDouble + " B";
    }
    int i = (int)(Math.log(paramDouble) / Math.log('Ѐ'));
    String str = "KMGTPE".charAt(i - 1) + "";
    return String.format("%.2f %sB", new Object[] { Double.valueOf(paramDouble / Math.pow('Ѐ', i)), str });
  }
  
  private static String getData(long paramLong)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    return localSimpleDateFormat.format(localCalendar.getTime());
  }
  
  private static String getMByteSizeFromByteSize(long paramLong)
  {
    String str2 = new String();
    double d = paramLong / 1024.0D / 1024.0D;
    String str1 = str2;
    if (d < 10.0D) {
      str1 = str2 + " ";
    }
    return str1 + String.format("%2.3f", new Object[] { Double.valueOf(d) });
  }
  
  public static String getStringFromAssetsJson(Activity paramActivity, String paramString)
  {
    String str = paramString;
    try
    {
      if (!paramString.contains(".json")) {
        str = paramString + ".json";
      }
      paramActivity = paramActivity.getResources().getAssets().open(str);
      paramString = new byte[paramActivity.available()];
      paramActivity.read(paramString);
      paramActivity.close();
      paramActivity = new String(paramString, "UTF-8");
      return paramActivity;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return "";
  }
  
  public static void i(Object... paramVarArgs)
  {
    trace(4, paramVarArgs);
  }
  
  public static void printApplicationInfo(Activity paramActivity)
  {
    try
    {
      printApplicationInfo("==== ApplicationInfo this", paramActivity, paramActivity.getApplication().getPackageManager().getPackageInfo(paramActivity.getApplication().getPackageName(), 0));
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static void printApplicationInfo(Activity paramActivity, String paramString)
  {
    String str = "==== ApplicationInfo target (" + paramString + ")";
    List localList = paramActivity.getPackageManager().getInstalledPackages(8192);
    int i = 0;
    int j = localList.size();
    while (i < j)
    {
      if (((PackageInfo)localList.get(i)).packageName.indexOf(paramString) != -1) {
        printApplicationInfo(str, paramActivity, (PackageInfo)localList.get(i));
      }
      i += 1;
    }
    if (localList.size() == 0) {
      d(new Object[] { str, "not install" });
    }
  }
  
  private static void printApplicationInfo(String paramString, Activity paramActivity, PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo != null)
    {
      paramActivity = paramActivity.getPackageManager();
      d(new Object[] { paramString, "App/Package: <" + paramPackageInfo.applicationInfo.loadLabel(paramActivity).toString() + " / " + paramPackageInfo.packageName + ">" });
      d(new Object[] { paramString, "versionName (versionCode): " + paramPackageInfo.versionName + " (" + paramPackageInfo.versionCode + ")" });
      d(new Object[] { paramString, "sharedUserId (sharedUserLabel): " + paramPackageInfo.sharedUserId + " (" + paramPackageInfo.sharedUserLabel + ")" });
      d(new Object[] { paramString, "install/update: " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(paramPackageInfo.firstInstallTime)) + "/" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(paramPackageInfo.lastUpdateTime)) });
    }
  }
  
  public static void printDeviceInfo(Activity paramActivity)
  {
    d(new Object[] { "==== DeviceInfo", "BOARD: " + Build.BOARD });
    d(new Object[] { "==== DeviceInfo", "BOOTLOADER: " + Build.BOOTLOADER });
    d(new Object[] { "==== DeviceInfo", "BRAND: " + Build.BRAND });
    d(new Object[] { "==== DeviceInfo", "CPU_ABI: " + Build.CPU_ABI });
    d(new Object[] { "==== DeviceInfo", "CPU_ABI2: " + Build.CPU_ABI2 });
    d(new Object[] { "==== DeviceInfo", "DEVICE: " + Build.DEVICE });
    d(new Object[] { "==== DeviceInfo", "DISPLAY: " + Build.DISPLAY });
    d(new Object[] { "==== DeviceInfo", "FINGERPRINT: " + Build.FINGERPRINT });
    d(new Object[] { "==== DeviceInfo", "HARDWARE: " + Build.HARDWARE });
    d(new Object[] { "==== DeviceInfo", "HOST: " + Build.HOST });
    d(new Object[] { "==== DeviceInfo", "ID: " + Build.ID });
    d(new Object[] { "==== DeviceInfo", "MANUFACTURER: " + Build.MANUFACTURER });
    d(new Object[] { "==== DeviceInfo", "MODEL: " + Build.MODEL });
    d(new Object[] { "==== DeviceInfo", "PRODUCT: " + Build.PRODUCT });
    d(new Object[] { "==== DeviceInfo", "RADIO: " + Build.RADIO });
    d(new Object[] { "==== DeviceInfo", "TAGS: " + Build.TAGS });
    d(new Object[] { "==== DeviceInfo", "TIME: " + Build.TIME });
    d(new Object[] { "==== DeviceInfo", "TYPE: " + Build.TYPE });
    d(new Object[] { "==== DeviceInfo", "UNKNOWN: unknown" });
    d(new Object[] { "==== DeviceInfo", "USER: " + Build.USER });
    d(new Object[] { "==== DeviceInfo", "VERSION.CODENAME: " + Build.VERSION.CODENAME });
    d(new Object[] { "==== DeviceInfo", "VERSION.INCREMENTAL: " + Build.VERSION.INCREMENTAL });
    d(new Object[] { "==== DeviceInfo", "VERSION.RELEASE: " + Build.VERSION.RELEASE });
    d(new Object[] { "==== DeviceInfo", "VERSION.SDK: " + Build.VERSION.SDK });
    d(new Object[] { "==== DeviceInfo", "VERSION.SDK_INT: " + Build.VERSION.SDK_INT });
    d(new Object[] { "==== DeviceInfo", "isSdcardMount:" + Environment.getExternalStorageState().equals("mounted") });
    d(new Object[] { "==== DeviceInfo", "isCallAbleDevice:" + paramActivity.getPackageManager().hasSystemFeature("android.hardware.telephony") });
    WifiManager localWifiManager = (WifiManager)paramActivity.getSystemService("wifi");
    boolean bool = localWifiManager.isWifiEnabled();
    Object localObject = "";
    if (!bool)
    {
      d(new Object[] { "==== DeviceInfo", "getMacAddress:" + (String)localObject });
      localObject = new StatFs(Environment.getExternalStorageDirectory().getPath());
      d(new Object[] { "==== DeviceInfo", "getExternalMemorySize Available/Total :" + format(((StatFs)localObject).getBlockSize() * ((StatFs)localObject).getAvailableBlocks()) + " / " + format(((StatFs)localObject).getBlockSize() * ((StatFs)localObject).getBlockCount()) });
      localObject = new StatFs(Environment.getDataDirectory().getPath());
      d(new Object[] { "==== DeviceInfo", "getInternalMemorySize Available/Total :" + format(((StatFs)localObject).getBlockSize() * ((StatFs)localObject).getAvailableBlocks()) + " / " + format(((StatFs)localObject).getBlockSize() * ((StatFs)localObject).getBlockCount()) });
      localObject = new DisplayMetrics();
      paramActivity.getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
      double d = Math.sqrt(Math.pow(((DisplayMetrics)localObject).widthPixels / ((DisplayMetrics)localObject).xdpi, 2.0D) + Math.pow(((DisplayMetrics)localObject).heightPixels / ((DisplayMetrics)localObject).ydpi, 2.0D));
      d(new Object[] { "==== DeviceInfo", "Screen width : " + ((DisplayMetrics)localObject).widthPixels });
      d(new Object[] { "==== DeviceInfo", "Screen height : " + ((DisplayMetrics)localObject).heightPixels });
      d(new Object[] { "==== DeviceInfo", "Screen inches : " + d });
      return;
    }
    localObject = localWifiManager.getConnectionInfo();
    if (localObject == null) {}
    for (localObject = null;; localObject = ((WifiInfo)localObject).getMacAddress()) {
      break;
    }
  }
  
  public static void printInfoAll(Activity paramActivity)
  {
    printMemoryInfo();
    printDeviceInfo(paramActivity);
    printNetrorkInfo(paramActivity);
    printApplicationInfo(paramActivity);
  }
  
  public static void printInfoAll(Activity paramActivity, String paramString)
  {
    printMemoryInfo();
    printDeviceInfo(paramActivity);
    printNetrorkInfo(paramActivity);
    printApplicationInfo(paramActivity, paramString);
  }
  
  public static void printLogHeap(Class<?> paramClass)
  {
    double d1 = new Double(Debug.getNativeHeapAllocatedSize()).doubleValue() / new Double(1048576.0D).doubleValue();
    double d2 = new Double(Debug.getNativeHeapSize()).doubleValue() / 1048576.0D;
    double d3 = Double.valueOf(Debug.getNativeHeapFreeSize()).doubleValue() / 1048576.0D;
    DecimalFormat localDecimalFormat = new DecimalFormat();
    localDecimalFormat.setMaximumFractionDigits(2);
    localDecimalFormat.setMinimumFractionDigits(2);
    d(new Object[] { "==== LogHeapInfo", "debug.heap native: allocated " + localDecimalFormat.format(Double.valueOf(d1)) + "MB of " + localDecimalFormat.format(Double.valueOf(d2)) + "MB (" + localDecimalFormat.format(Double.valueOf(d3)) + "MB free) in [" + paramClass.getName().replaceAll("com.myapp.android.", "") + "]" });
    d(new Object[] { "==== LogHeapInfo", "debug.memory: allocated: " + localDecimalFormat.format(new Double(Runtime.getRuntime().totalMemory() / 1048576L)) + "MB of " + localDecimalFormat.format(new Double(Runtime.getRuntime().maxMemory() / 1048576L)) + "MB (" + localDecimalFormat.format(new Double(Runtime.getRuntime().freeMemory() / 1048576L)) + "MB free)" });
    System.gc();
    System.gc();
  }
  
  public static void printMemoryInfo()
  {
    long l1 = Runtime.getRuntime().maxMemory();
    long l2 = Runtime.getRuntime().totalMemory();
    long l3 = Runtime.getRuntime().freeMemory();
    long l4 = Debug.getNativeHeapSize();
    long l5 = Debug.getNativeHeapFreeSize();
    long l6 = Debug.getNativeHeapAllocatedSize();
    d(new Object[] { "==== MemoryInfo", "   VM Max   = " + getMByteSizeFromByteSize(l1) });
    d(new Object[] { "==== MemoryInfo", "   VM Total = " + getMByteSizeFromByteSize(l2) });
    d(new Object[] { "==== MemoryInfo", "   VM Used  = " + getMByteSizeFromByteSize(l2 - l3) });
    d(new Object[] { "==== MemoryInfo", "   VM Free  = " + getMByteSizeFromByteSize(l3) });
    d(new Object[] { "==== MemoryInfo", "Native Size = " + getMByteSizeFromByteSize(l4) });
    d(new Object[] { "==== MemoryInfo", "Native Used = " + getMByteSizeFromByteSize(l6) });
    d(new Object[] { "==== MemoryInfo", "Native Free = " + getMByteSizeFromByteSize(l5) });
  }
  
  public static void printMonitorPeakMemoryStart()
  {
    sPeakVmUsed = 0L;
    sPeakNativeUsed = 0L;
    mMonitorRunnable = new du.MemoryMonitorRunnable();
    mMonitorThread = new Thread(mMonitorRunnable);
    mMonitorThread.start();
  }
  
  public static void printMonitorPeakMemoryStop()
  {
    if ((mMonitorThread != null) && (mMonitorRunnable != null))
    {
      mMonitorRunnable.destroy();
      mMonitorRunnable = null;
      mMonitorThread = null;
    }
    i(new Object[] { "MonitorPeakMemoryInfo", "PeakVmTotal    = " + getMByteSizeFromByteSize(sPeakVmUsed) });
    i(new Object[] { "MonitorPeakMemoryInfo", "PeakNativeSize = " + getMByteSizeFromByteSize(sPeakNativeUsed) });
  }
  
  public static void printNetrorkInfo(Activity paramActivity)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramActivity.getApplication().getSystemService("connectivity");
    bool1 = false;
    try
    {
      localNetworkInfo1 = localConnectivityManager.getNetworkInfo(0);
      localNetworkInfo2 = localConnectivityManager.getNetworkInfo(1);
      NetworkInfo localNetworkInfo3 = localConnectivityManager.getNetworkInfo(6);
      if (((localNetworkInfo1 == null) || (!localNetworkInfo1.isConnected())) && ((localNetworkInfo2 == null) || (!localNetworkInfo2.isConnected())))
      {
        bool3 = bool1;
        if (localNetworkInfo3 != null)
        {
          bool2 = localNetworkInfo3.isConnected();
          bool3 = bool1;
          if (!bool2) {}
        }
      }
      else
      {
        bool3 = true;
      }
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        NetworkInfo localNetworkInfo1;
        boolean bool4;
        d(new Object[] { "==== NetrorkInfo", "Exception during isNetworkConnected. - " + localException2.getLocalizedMessage() });
        boolean bool3 = bool1;
      }
    }
    bool4 = false;
    try
    {
      bool1 = ((WifiManager)paramActivity.getApplication().getSystemService("wifi")).isWifiEnabled();
      bool4 = bool1;
    }
    catch (Exception localException3)
    {
      for (;;)
      {
        d(new Object[] { "==== NetrorkInfo", "Exception during isWifiEnabled. - " + localException3.getLocalizedMessage() });
        continue;
        if ((localException3 != null) && (localException3.isConnected()))
        {
          bool1 = true;
        }
        else
        {
          bool1 = bool2;
          if (localNetworkInfo2 != null)
          {
            bool5 = localNetworkInfo2.isConnected();
            bool1 = bool2;
            if (bool5) {
              bool1 = true;
            }
          }
        }
      }
    }
    bool2 = false;
    try
    {
      localNetworkInfo1 = localConnectivityManager.getNetworkInfo(1);
      localNetworkInfo2 = localConnectivityManager.getNetworkInfo(6);
      if ((localNetworkInfo1 != null) || (localNetworkInfo2 != null)) {
        break label526;
      }
      d(new Object[] { "==== NetrorkInfo", "wifiNetworkInfo is null." });
      bool1 = bool2;
    }
    catch (Exception localException4)
    {
      for (;;)
      {
        boolean bool5;
        d(new Object[] { "==== NetrorkInfo", "Exception during isWifiConnected. - " + localException4.getLocalizedMessage() });
        bool1 = bool2;
      }
    }
    bool5 = false;
    try
    {
      bool2 = localConnectivityManager.getNetworkInfo(0).isConnected();
      bool5 = bool2;
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;)
      {
        d(new Object[] { "==== NetrorkInfo", "NullPointerException during isMobile3G. - " + localNullPointerException.getLocalizedMessage() });
      }
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        d(new Object[] { "==== NetrorkInfo", "Exception during isMobile3G. - " + localException1.getLocalizedMessage() });
        continue;
        bool2 = false;
      }
    }
    bool2 = false;
    try
    {
      int i = Settings.System.getInt(paramActivity.getApplication().getContentResolver(), "airplane_mode_on", 0);
      if (i == 0) {
        break label707;
      }
      bool2 = true;
    }
    catch (Exception paramActivity)
    {
      for (;;)
      {
        d(new Object[] { "==== NetrorkInfo", "Exception during isAirplaneMode. - " + paramActivity.getLocalizedMessage() });
      }
    }
    d(new Object[] { "==== NetrorkInfo", "isNetworkConnected: " + bool3 });
    d(new Object[] { "==== NetrorkInfo", "isWifiEnabled: " + bool4 });
    d(new Object[] { "==== NetrorkInfo", "isWifiConnected: " + bool1 });
    d(new Object[] { "==== NetrorkInfo", "isMobileConnected: " + bool5 });
    d(new Object[] { "==== NetrorkInfo", "isAirplaneMode: " + bool2 });
    d(new Object[] { "==== NetrorkInfo", "ServiceStateRoaming: " + new ServiceState().getRoaming() });
  }
  
  public static void printStackTrace()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    StackTraceElement[] arrayOfStackTraceElement = new Exception().getStackTrace();
    int j = arrayOfStackTraceElement.length;
    int i = 0;
    while (i < j)
    {
      StackTraceElement localStackTraceElement = arrayOfStackTraceElement[i];
      localStringBuffer.append(localStackTraceElement.toString() + "\n");
      i += 1;
    }
    e(new Object[] { localStringBuffer.toString() });
  }
  
  public static void t(Object... paramVarArgs)
  {
    trace(0, paramVarArgs);
  }
  
  private static void trace(int paramInt, Object... paramVarArgs)
  {
    int i = 0;
    if (!isDebugMode) {
      return;
    }
    try
    {
      Object localObject1 = new StringBuffer();
      int j = paramVarArgs.length;
      while (i < j)
      {
        Object localObject2 = paramVarArgs[i];
        ((StringBuffer)localObject1).append(localObject2 + " : ");
        i += 1;
      }
      localObject1 = ((StringBuffer)localObject1).substring(0, ((StringBuffer)localObject1).length() - 3);
      paramVarArgs = (Object[])localObject1;
      i = paramInt;
      if (paramInt == 0)
      {
        delayMillis = System.currentTimeMillis() - tempMillis;
        if (tempMillis == 0L) {
          delayMillis = 0L;
        }
        tempMillis = System.currentTimeMillis();
        paramVarArgs = String.format("%1$s\n%2$s", new Object[] { "[[[ " + delayMillis + " ]]]", localObject1 });
        i = 3;
      }
      Log.println(i, TAG, paramVarArgs);
      return;
    }
    catch (Exception paramVarArgs) {}
  }
  
  public static void v(Object... paramVarArgs)
  {
    trace(2, paramVarArgs);
  }
  
  public static void w(Object... paramVarArgs)
  {
    trace(5, paramVarArgs);
  }
}
