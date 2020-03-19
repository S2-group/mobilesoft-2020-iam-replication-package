package com.dia.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.crashlytics.android.Crashlytics;
import com.dia.data.IAPIdentification;
import com.dia.data.web.IAPCrashlytics;
import com.dia.model.request.Data;
import com.dia.model.request.Report;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.JodaTimeAndroid;
import timber.log.Timber;
import timber.log.Timber.Tree;

public final class ReportUtils
{
  private static final String AC_CHARGE = "ac_charge";
  private static final String APPS = "apps";
  private static final String APP_TARGET_SDK = "target_sdk";
  private static final String APP_VERSION = "app_version";
  private static final String AVAILABLE_DISK = "available_disk";
  private static final String BATTERY_LEVEL = "battery_level";
  private static final String BRAND = "brand";
  private static final String BUILD_ID = "build_id";
  private static final String BUILD_TAGS = "build_tags";
  private static final String BUILD_TYPE = "build_type";
  private static final String CARRIER_INFO = "carrier_info";
  private static final String CODENAME = "codename";
  private static final String DEVICE_SDK = "device_sdk";
  private static final String DISPLAY_INFO = "display_info";
  private static final int ERROR_SERVICE_INST_AVAILABLE = -4;
  private static final int ERROR_UNEXPECTED = -3;
  private static final String FINGERPRINT = "fingerprint";
  private static final String HARDWARE = "hardware";
  private static final String HOST = "host";
  private static final String ID = "id";
  private static final String INSTALL_TIME = "install_time";
  private static final String IS_CHARGING = "is_charging";
  private static final String LANGUAGE = "language";
  private static final String LOCATION = "location";
  private static final String MANUFACTURER = "manufacturer";
  private static final String MEMORY = "memory";
  private static final String MODEL = "model";
  private static final String NETWORK_TYPE = "network_type";
  private static final int NO_DATA = -2;
  private static final int NO_PERMISSION = -1;
  private static final String NUMBER_OF_PROCESSORS = "number_of_processors";
  private static final String ORIENTATION = "orientation";
  private static final String OS = "os";
  private static final String PACKAGE_NAME = "package_name";
  private static final String PRODUCT = "product";
  private static final String ROM_BIRTHDAY = "rom_birthday";
  private static final String ROOTED = "rooted";
  private static final String SD_CARD = "sd_card";
  private static final String SENSORS = "sensors";
  private static final String SERIAL = "serial";
  private static final String TIMEZONE = "timezone";
  private static final String USB_CHARGE = "usb_charge";
  private static final String USER = "user";
  private static final String WEBVIEW_USERAGENT = "webview_useragent";
  private final Context context;
  private final IAPIdentification identification;
  
  public ReportUtils(Context paramContext)
  {
    this.context = paramContext;
    this.identification = new IAPIdentification(paramContext);
  }
  
  private void addAppVersion(List<Data> paramList)
  {
    String str2;
    try
    {
      String str1 = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
    }
    catch (Exception localException)
    {
      Timber.e(localException);
      str2 = String.valueOf(-3);
    }
    paramList.add(new Data("app_version", str2));
  }
  
  private void addBatteryData(List<Data> paramList)
  {
    int i;
    int m;
    try
    {
      localIntent = this.context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      n = -4;
      if (localIntent == null) {
        break label280;
      }
      i = localIntent.getIntExtra("status", -2);
    }
    catch (Exception localException)
    {
      Intent localIntent;
      int n;
      label60:
      Timber.e(localException);
      IAPCrashlytics.logException(localException);
      paramList.add(new Data("is_charging", Integer.valueOf(-3)));
      paramList.add(new Data("usb_charge", Integer.valueOf(-3)));
      paramList.add(new Data("ac_charge", Integer.valueOf(-3)));
      paramList.add(new Data("battery_level", Integer.valueOf(-3)));
      return;
    }
    if (localIntent != null)
    {
      m = localIntent.getIntExtra("plugged", -2);
      break label313;
      if (localIntent == null) {
        break label338;
      }
      m = localIntent.getIntExtra("level", -2);
    }
    for (;;)
    {
      if (localIntent != null) {
        n = localIntent.getIntExtra("scale", -2);
      }
      paramList.add(new Data("is_charging", Integer.valueOf(i)));
      int j;
      paramList.add(new Data("usb_charge", Integer.valueOf(j)));
      paramList.add(new Data("ac_charge", Integer.valueOf(k)));
      paramList.add(new Data("battery_level", Float.valueOf(m / n)));
      return;
      label280:
      i = -4;
      int k = 0;
      if ((i != 2) && (i != 5))
      {
        i = 0;
        break;
      }
      i = 1;
      break;
      m = -4;
      label313:
      if (m == 2) {
        j = 1;
      } else {
        j = 0;
      }
      if (m != 1) {
        break label60;
      }
      k = 1;
      break label60;
      label338:
      m = -4;
    }
  }
  
  private void addCarrierData(List<Data> paramList)
  {
    String[] arrayOfString = new String[4];
    Object localObject = (TelephonyManager)this.context.getSystemService("phone");
    if (localObject != null) {}
    try
    {
      String str1 = ((TelephonyManager)localObject).getSimOperatorName();
      String str2 = ((TelephonyManager)localObject).getNetworkOperatorName();
      String str3 = ((TelephonyManager)localObject).getSimOperator();
      localObject = str3.substring(0, 3);
      str3 = str3.substring(3);
      arrayOfString[0] = str1;
      arrayOfString[1] = str2;
      arrayOfString[2] = localObject;
      arrayOfString[3] = str3;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    arrayOfString[0] = String.valueOf(-4);
    arrayOfString[1] = String.valueOf(-4);
    arrayOfString[2] = String.valueOf(-4);
    arrayOfString[3] = String.valueOf(-4);
    break label150;
    arrayOfString[0] = String.valueOf(-3);
    arrayOfString[1] = String.valueOf(-3);
    arrayOfString[2] = String.valueOf(-3);
    arrayOfString[3] = String.valueOf(-3);
    label150:
    paramList.add(new Data("carrier_info", arrayOfString));
  }
  
  private void addDisplayInfo(List<Data> paramList)
  {
    double[] arrayOfDouble = new double[4];
    for (;;)
    {
      try
      {
        Point localPoint = new Point();
        Object localObject = (WindowManager)this.context.getSystemService("window");
        DisplayMetrics localDisplayMetrics = Resources.getSystem().getDisplayMetrics();
        int i = -4;
        if (localObject == null) {
          break label237;
        }
        localObject = ((WindowManager)localObject).getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17)
        {
          ((Display)localObject).getRealSize(localPoint);
          i = localPoint.x;
          j = localPoint.y;
        }
        else
        {
          i = localDisplayMetrics.widthPixels;
          j = localDisplayMetrics.heightPixels;
        }
        float f1 = i / localDisplayMetrics.xdpi;
        float f2 = j / localDisplayMetrics.ydpi;
        d = round(Math.sqrt(f1 * f1 + f2 * f2));
        arrayOfDouble[0] = i;
        arrayOfDouble[1] = j;
        arrayOfDouble[2] = d;
        arrayOfDouble[3] = localDisplayMetrics.density;
      }
      catch (Exception localException)
      {
        Timber.e(localException);
        IAPCrashlytics.logException(localException);
        arrayOfDouble[0] = -3.0D;
        arrayOfDouble[1] = -3.0D;
        arrayOfDouble[2] = -3.0D;
        arrayOfDouble[3] = -3.0D;
      }
      paramList.add(new Data("display_info", arrayOfDouble));
      return;
      label237:
      double d = -4.0D;
      int j = -4;
    }
  }
  
  private void addInstallTime(List<Data> paramList)
  {
    long l;
    try
    {
      l = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).firstInstallTime;
    }
    catch (Exception localException)
    {
      Timber.e(localException);
      l = -3L;
    }
    paramList.add(new Data("install_time", Long.valueOf(l)));
  }
  
  private void addInstalledApps(List<Data> paramList)
  {
    PackageManager localPackageManager = this.context.getPackageManager();
    ArrayList localArrayList = new ArrayList();
    if (localPackageManager != null)
    {
      Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageManager.getLaunchIntentForPackage(localPackageInfo.packageName) != null) && ((localPackageInfo.applicationInfo.flags & 0x80) == 0) && ((localPackageInfo.applicationInfo.flags & 0x1) == 0)) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
    }
    if (!localArrayList.isEmpty()) {
      paramList.add(new Data("apps", localArrayList));
    }
  }
  
  private void addNetworkData(List<Data> paramList)
  {
    if (!isGrantedPermission("android.permission.ACCESS_NETWORK_STATE"))
    {
      paramList.add(new Data("network_type", Integer.valueOf(-1)));
      return;
    }
    try
    {
      paramList.add(new Data("network_type", Integer.valueOf(NetworkUtils.getNetworkType(this.context, -4))));
      return;
    }
    catch (Exception localException)
    {
      Timber.e(localException);
      IAPCrashlytics.logException(localException);
      paramList.add(new Data("network_type", Integer.valueOf(-3)));
    }
  }
  
  private void addSensorsData(List<Data> paramList)
  {
    paramList.add(new Data("sensors", SensorUtils.getSensors(this.context)));
  }
  
  private void addWebviewData(List<Data> paramList)
  {
    String str2;
    try
    {
      String str1 = new WebView(this.context).getSettings().getUserAgentString();
    }
    catch (Exception localException)
    {
      Timber.e(localException);
      IAPCrashlytics.logException(localException);
      str2 = String.valueOf(-3);
    }
    paramList.add(new Data("webview_useragent", str2));
  }
  
  private boolean checkRootMethod1()
  {
    String str = Build.TAGS;
    return (str != null) && (str.contains("addMemoryInfo-keys"));
  }
  
  private boolean checkRootMethod2()
  {
    String[] arrayOfString = new String[10];
    arrayOfString[0] = "/system/app/Superuser.apk";
    arrayOfString[1] = "/sbin/su";
    arrayOfString[2] = "/system/bin/su";
    arrayOfString[3] = "/system/xbin/su";
    arrayOfString[4] = "/data/local/xbin/su";
    arrayOfString[5] = "/data/local/bin/su";
    arrayOfString[6] = "/system/sd/xbin/su";
    arrayOfString[7] = "/system/bin/failsafe/su";
    arrayOfString[8] = "/data/local/su";
    arrayOfString[9] = "/su/bin/su";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (new File(arrayOfString[i]).exists()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  /* Error */
  private boolean checkRootMethod3()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aconst_null
    //   3: astore 4
    //   5: aconst_null
    //   6: astore_3
    //   7: invokestatic 471	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   10: iconst_2
    //   11: anewarray 185	java/lang/String
    //   14: dup
    //   15: iconst_0
    //   16: ldc_w 473
    //   19: aastore
    //   20: dup
    //   21: iconst_1
    //   22: ldc_w 475
    //   25: aastore
    //   26: invokevirtual 479	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   29: astore_2
    //   30: new 481	java/io/BufferedReader
    //   33: dup
    //   34: new 483	java/io/InputStreamReader
    //   37: dup
    //   38: aload_2
    //   39: invokevirtual 489	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   42: invokespecial 492	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   45: invokespecial 495	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   48: invokevirtual 498	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   51: astore_3
    //   52: aload_3
    //   53: ifnull +5 -> 58
    //   56: iconst_1
    //   57: istore_1
    //   58: aload_2
    //   59: ifnull +7 -> 66
    //   62: aload_2
    //   63: invokevirtual 501	java/lang/Process:destroy	()V
    //   66: iload_1
    //   67: ireturn
    //   68: astore_3
    //   69: goto +13 -> 82
    //   72: goto +20 -> 92
    //   75: astore 4
    //   77: aload_3
    //   78: astore_2
    //   79: aload 4
    //   81: astore_3
    //   82: aload_2
    //   83: ifnull +7 -> 90
    //   86: aload_2
    //   87: invokevirtual 501	java/lang/Process:destroy	()V
    //   90: aload_3
    //   91: athrow
    //   92: aload_2
    //   93: ifnull +7 -> 100
    //   96: aload_2
    //   97: invokevirtual 501	java/lang/Process:destroy	()V
    //   100: iconst_0
    //   101: ireturn
    //   102: astore_2
    //   103: aload 4
    //   105: astore_2
    //   106: goto -14 -> 92
    //   109: astore_3
    //   110: goto -38 -> 72
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	113	0	this	ReportUtils
    //   1	66	1	bool	boolean
    //   29	68	2	localObject1	Object
    //   102	1	2	localThrowable1	Throwable
    //   105	1	2	localObject2	Object
    //   6	47	3	str	String
    //   68	10	3	localObject3	Object
    //   81	10	3	localObject4	Object
    //   109	1	3	localThrowable2	Throwable
    //   3	1	4	localObject5	Object
    //   75	29	4	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   30	52	68	finally
    //   7	30	75	finally
    //   7	30	102	java/lang/Throwable
    //   30	52	109	java/lang/Throwable
  }
  
  private String getDefaultDownloadPath()
  {
    String str = Environment.getExternalStorageDirectory().toString();
    File localFile = new File(str);
    if ((localFile.exists()) && (localFile.isDirectory())) {
      return str;
    }
    if (localFile.mkdirs()) {
      return str;
    }
    return "";
  }
  
  private long getFreeSpace()
  {
    String str = getDefaultDownloadPath();
    try
    {
      long l2 = new File(str).getUsableSpace();
      l1 = l2;
      if (l2 != 0L) {
        break label42;
      }
      throw new RuntimeException();
    }
    catch (Exception localException)
    {
      long l1;
      label42:
      for (;;) {}
    }
    l1 = getFreeStatSpace(str);
    return l1 / 1048576L;
  }
  
  private long getFreeStatSpace(String paramString)
  {
    try
    {
      paramString = new StatFs(paramString);
      if (Build.VERSION.SDK_INT >= 18) {
        return paramString.getAvailableBytes();
      }
      int i = paramString.getAvailableBlocks();
      int j = paramString.getBlockSize();
      return i * j;
    }
    catch (Exception paramString)
    {
      Crashlytics.logException(paramString);
      Timber.e(paramString);
    }
    return -1L;
  }
  
  @SuppressLint({"MissingPermission"})
  private double[] getLocation()
  {
    if ((!isGrantedPermission("android.permission.ACCESS_FINE_LOCATION")) && (!isGrantedPermission("android.permission.ACCESS_COARSE_LOCATION"))) {
      return new double[] { -1.0D, -1.0D };
    }
    try
    {
      Object localObject = (LocationManager)this.context.getSystemService("location");
      if (localObject != null)
      {
        localObject = ((LocationManager)localObject).getLastKnownLocation(((LocationManager)localObject).getBestProvider(new Criteria(), false));
        return new double[] { ((Location)localObject).getLatitude(), ((Location)localObject).getLongitude() };
      }
      return new double[] { -4.0D, -4.0D };
    }
    catch (Exception localException)
    {
      Timber.e(localException);
      if (!(localException instanceof NullPointerException)) {
        IAPCrashlytics.logException(localException);
      }
    }
    return tmp129_123;
  }
  
  private int hasSdCard()
  {
    boolean bool1 = Environment.getExternalStorageState().equals("mounted");
    boolean bool2 = Environment.isExternalStorageRemovable();
    if ((bool1) && (bool2)) {
      return 1;
    }
    return 0;
  }
  
  private int isDeviceRooted()
  {
    if ((!checkRootMethod1()) && (!checkRootMethod2()) && (!checkRootMethod3())) {
      return 0;
    }
    return 1;
  }
  
  private boolean isGrantedPermission(String paramString)
  {
    return ContextCompat.checkSelfPermission(this.context, paramString) == 0;
  }
  
  private long[] memory(Context paramContext)
  {
    try
    {
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      paramContext = (ActivityManager)paramContext.getSystemService("activity");
      if (paramContext != null)
      {
        paramContext.getMemoryInfo(localMemoryInfo);
        long l1 = localMemoryInfo.totalMem / 1048576L;
        long l2 = localMemoryInfo.availMem / 1048576L;
        Timber.tag("REPORT").w("memory %s %s", new Object[] { Long.valueOf(l1), Long.valueOf(l2) });
        return new long[] { l1, l2 };
      }
      return new long[] { -4L, -4L };
    }
    catch (Exception paramContext)
    {
      Timber.e(paramContext);
      IAPCrashlytics.logException(paramContext);
    }
    return tmp129_123;
  }
  
  private double round(double paramDouble)
  {
    return new BigDecimal(String.valueOf(paramDouble)).setScale(1, RoundingMode.HALF_UP).doubleValue();
  }
  
  public Single<Report> getDeviceDetails(boolean paramBoolean)
  {
    try
    {
      JodaTimeAndroid.init(this.context);
      Single localSingle1 = Single.create(new ReportUtils..Lambda.0(this)).subscribeOn(AndroidSchedulers.mainThread());
      Single localSingle2 = Single.create(new ReportUtils..Lambda.1(this)).subscribeOn(Schedulers.io());
      localSingle1 = Single.zip(localSingle1, Single.create(new ReportUtils..Lambda.2(this, paramBoolean)).subscribeOn(Schedulers.io()), localSingle2, ReportUtils..Lambda.3.$instance).subscribeOn(Schedulers.io());
      return localSingle1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}
