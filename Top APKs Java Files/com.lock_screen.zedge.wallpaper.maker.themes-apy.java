import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import com.lock_screen.zedge.wallpaper.maker.themes.view.dialog.SecurityFailDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

public class apy
{
  private static boolean a(Context paramContext)
  {
    if (fp.checkSelfPermission(paramContext, "android.permission.CAMERA") != 0) {
      return false;
    }
    if (fp.checkSelfPermission(paramContext, "android.permission.READ_PHONE_STATE") != 0) {
      return false;
    }
    return (Build.VERSION.SDK_INT < 23) || (Settings.System.canWrite(paramContext));
  }
  
  @TargetApi(19)
  private static boolean a(Context paramContext, int paramInt)
  {
    int i = Build.VERSION.SDK_INT;
    boolean bool2 = false;
    boolean bool1 = bool2;
    AppOpsManager localAppOpsManager;
    if (i >= 19) {
      localAppOpsManager = (AppOpsManager)paramContext.getSystemService("appops");
    }
    try
    {
      paramInt = ((Integer)AppOpsManager.class.getDeclaredMethod("checkOp", new Class[] { Integer.TYPE, Integer.TYPE, String.class }).invoke(localAppOpsManager, new Object[] { Integer.valueOf(paramInt), Integer.valueOf(Binder.getCallingUid()), paramContext.getPackageName() })).intValue();
      bool1 = bool2;
      if (paramInt == 0) {
        bool1 = true;
      }
      return bool1;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  private static boolean b(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = c(paramContext);
      if (i == 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  /* Error */
  private static int c(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 22	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 19
    //   5: if_icmplt +87 -> 92
    //   8: aload_0
    //   9: ldc 36
    //   11: invokevirtual 42	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   14: astore_2
    //   15: aload_2
    //   16: ifnonnull +5 -> 21
    //   19: iconst_m1
    //   20: ireturn
    //   21: aload_2
    //   22: invokevirtual 95	java/lang/Object:getClass	()Ljava/lang/Class;
    //   25: ldc 46
    //   27: iconst_3
    //   28: anewarray 48	java/lang/Class
    //   31: dup
    //   32: iconst_0
    //   33: getstatic 54	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   36: aastore
    //   37: dup
    //   38: iconst_1
    //   39: getstatic 54	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   42: aastore
    //   43: dup
    //   44: iconst_2
    //   45: ldc 56
    //   47: aastore
    //   48: invokevirtual 98	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   51: aload_2
    //   52: iconst_3
    //   53: anewarray 4	java/lang/Object
    //   56: dup
    //   57: iconst_0
    //   58: bipush 24
    //   60: invokestatic 64	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   63: aastore
    //   64: dup
    //   65: iconst_1
    //   66: invokestatic 70	android/os/Binder:getCallingUid	()I
    //   69: invokestatic 64	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   72: aastore
    //   73: dup
    //   74: iconst_2
    //   75: aload_0
    //   76: invokevirtual 74	android/content/Context:getPackageName	()Ljava/lang/String;
    //   79: aastore
    //   80: invokevirtual 80	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   83: checkcast 50	java/lang/Integer
    //   86: invokevirtual 83	java/lang/Integer:intValue	()I
    //   89: istore_1
    //   90: iload_1
    //   91: ireturn
    //   92: aload_0
    //   93: ldc 100
    //   95: invokevirtual 42	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   98: checkcast 102	android/app/ActivityManager
    //   101: astore_2
    //   102: aload_2
    //   103: ifnonnull +5 -> 108
    //   106: iconst_m1
    //   107: ireturn
    //   108: aload_2
    //   109: iconst_1
    //   110: invokevirtual 106	android/app/ActivityManager:getRunningTasks	(I)Ljava/util/List;
    //   113: iconst_0
    //   114: invokeinterface 112 2 0
    //   119: checkcast 114	android/app/ActivityManager$RunningTaskInfo
    //   122: getfield 118	android/app/ActivityManager$RunningTaskInfo:topActivity	Landroid/content/ComponentName;
    //   125: astore_2
    //   126: aload_0
    //   127: invokevirtual 122	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   130: aload_0
    //   131: invokevirtual 74	android/content/Context:getPackageName	()Ljava/lang/String;
    //   134: iconst_0
    //   135: invokevirtual 128	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   138: astore_0
    //   139: aload_0
    //   140: ifnull +17 -> 157
    //   143: aload_0
    //   144: getfield 133	android/content/pm/ApplicationInfo:flags	I
    //   147: istore_1
    //   148: iload_1
    //   149: ldc -122
    //   151: iand
    //   152: ifne +5 -> 157
    //   155: iconst_0
    //   156: ireturn
    //   157: iconst_m1
    //   158: ireturn
    //   159: astore_0
    //   160: iconst_m1
    //   161: ireturn
    //   162: astore_0
    //   163: iconst_m1
    //   164: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	paramContext	Context
    //   89	63	1	i	int
    //   14	112	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   8	15	159	java/lang/Throwable
    //   21	90	159	java/lang/Throwable
    //   126	139	162	java/lang/Throwable
    //   143	148	162	java/lang/Throwable
  }
  
  public static void cancelScreenPwdByUser(Context paramContext)
  {
    try
    {
      Intent localIntent;
      if (isMiui())
      {
        localIntent = new Intent();
        localIntent.setComponent(new ComponentName("com.android.settings", "com.android.settings.MiuiSettings"));
      }
      for (;;)
      {
        localIntent.setFlags(268435456);
        paramContext.startActivity(localIntent);
        return;
        localIntent = new Intent("android.app.action.SET_NEW_PASSWORD");
      }
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static boolean checkHuaWeiFloatWindowPermission(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return a(paramContext, 24);
    }
    return true;
  }
  
  public static boolean checkIsHuaweiRom()
  {
    return Build.MANUFACTURER.contains("HUAWEI");
  }
  
  public static void closeRest(Context paramContext)
  {
    paramContext = (AudioManager)paramContext.getSystemService("audio");
    paramContext.setStreamVolume(2, paramContext.getStreamMaxVolume(2), 0);
    paramContext.setStreamVolume(5, paramContext.getStreamMaxVolume(5), 0);
    paramContext.setStreamVolume(1, paramContext.getStreamMaxVolume(1), 0);
  }
  
  public static String findcalculator(Context paramContext)
  {
    Object localObject2 = paramContext.getPackageManager();
    int m = 0;
    List localList = ((PackageManager)localObject2).getInstalledPackages(0);
    paramContext = "";
    int j = 0;
    Object localObject1;
    for (i = 0;; i = k)
    {
      localObject1 = paramContext;
      k = i;
      if (j >= localList.size()) {
        break;
      }
      PackageInfo localPackageInfo = (PackageInfo)localList.get(j);
      try
      {
        String str = localPackageInfo.applicationInfo.loadLabel((PackageManager)localObject2).toString();
        if ((!str.toLowerCase().equals("calculator")) && (!str.equals("计算器")))
        {
          if (!str.toLowerCase().contains("calculator"))
          {
            localObject1 = paramContext;
            k = i;
            if (!str.contains("计算器")) {}
          }
          else if (str.toLowerCase().contains("widget"))
          {
            localObject1 = paramContext;
            k = i;
            if (str.contains("小插件")) {}
          }
          else
          {
            localObject1 = localPackageInfo.packageName;
            k = 1;
          }
        }
        else
        {
          localObject1 = localPackageInfo.packageName;
          k = 1;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Context localContext = paramContext;
          k = i;
        }
      }
      j += 1;
      paramContext = (Context)localObject1;
    }
    if (k == 0)
    {
      i = m;
      while (i < localList.size())
      {
        paramContext = (PackageInfo)localList.get(i);
        localObject2 = paramContext.packageName;
        if ((((String)localObject2).toLowerCase().contains("calculator")) && (!((String)localObject2).toLowerCase().contains("widget"))) {
          return paramContext.packageName;
        }
        i += 1;
      }
    }
    return localObject1;
  }
  
  public static int getAndroidSDKVersion()
  {
    try
    {
      int i = Build.VERSION.SDK_INT;
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
    }
    return 0;
  }
  
  public static boolean getBluetoothStatus()
  {
    try
    {
      BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
      if (localBluetoothAdapter != null)
      {
        boolean bool = localBluetoothAdapter.isEnabled();
        return bool;
      }
      return false;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static int getCurrentLightness(Context paramContext)
  {
    try
    {
      int i = Settings.System.getInt(paramContext.getContentResolver(), "screen_brightness", -1);
      return i;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return 0;
  }
  
  public static int getCurrentVolume(Context paramContext)
  {
    return ((AudioManager)paramContext.getSystemService("audio")).getStreamVolume(2);
  }
  
  public static double getEMUIVersion()
  {
    j = 0;
    try
    {
      Object localObject = new Properties();
      ((Properties)localObject).load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
      localObject = ((Properties)localObject).getProperty("ro.build.version.sdk");
      i = j;
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        i = Integer.parseInt((String)localObject);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        int i = j;
      }
    }
    return i;
  }
  
  public static int getMaxVolume(Context paramContext)
  {
    return ((AudioManager)paramContext.getSystemService("audio")).getStreamMaxVolume(2);
  }
  
  public static boolean getMobileDataState(Context paramContext, Object[] paramArrayOfObject)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    int i = localTelephonyManager.getSimState();
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (i != 1)
    {
      if (localTelephonyManager.getSimState() == 0) {
        return false;
      }
      try
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
        Class localClass = localConnectivityManager.getClass();
        paramContext = null;
        if (paramArrayOfObject != null)
        {
          paramContext = new Class[1];
          paramContext[0] = paramArrayOfObject.getClass();
        }
        bool1 = ((Boolean)localClass.getMethod("getMobileDataEnabled", paramContext).invoke(localConnectivityManager, paramArrayOfObject)).booleanValue();
        return bool1;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        bool1 = bool2;
        if (localTelephonyManager.getDataState() == 2) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static int getNavigationBarHeight(Context paramContext)
  {
    try
    {
      paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      paramContext.getRealMetrics(localDisplayMetrics);
      int i = localDisplayMetrics.heightPixels;
      localDisplayMetrics = new DisplayMetrics();
      paramContext.getMetrics(localDisplayMetrics);
      int j = localDisplayMetrics.heightPixels;
      i -= j;
      if (i >= 0) {
        return i;
      }
      return 0;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static boolean getRingStatus(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = ((AudioManager)paramContext.getSystemService("audio")).getRingerMode();
      if (2 == i) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean getRotationStatus(Context paramContext)
  {
    boolean bool = false;
    try
    {
      i = Settings.System.getInt(paramContext.getContentResolver(), "accelerometer_rotation", 0);
    }
    catch (Exception paramContext)
    {
      int i;
      for (;;) {}
    }
    i = 0;
    if (i == 0) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean getVibrate(Context paramContext)
  {
    AudioManager localAudioManager = (AudioManager)paramContext.getSystemService("audio");
    if (Build.VERSION.SDK_INT < 16) {
      return localAudioManager.getVibrateSetting(0) == 1;
    }
    try
    {
      switch (localAudioManager.getRingerMode())
      {
      case 2: 
        if (Settings.System.getInt(paramContext.getContentResolver(), "vibrate_when_ringing", 0) == 1) {
          return true;
        }
        int i = Settings.System.getInt(paramContext.getContentResolver(), "vibrate_when_ringing", 0);
        if (i == 0) {
          return false;
        }
      case 1: 
        return true;
      case 0: 
        return false;
      }
    }
    catch (Exception paramContext)
    {
      return false;
    }
    return false;
  }
  
  public static int getWifiState(Context paramContext)
  {
    try
    {
      int i = ((WifiManager)paramContext.getSystemService("wifi")).getWifiState();
      return i;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return 4;
  }
  
  public static int getWinDowsType(Context paramContext)
  {
    String str = Build.MANUFACTURER.toLowerCase();
    int i = Build.VERSION.SDK_INT;
    int j = 2010;
    if (i >= 26) {
      return 2038;
    }
    if ((!TextUtils.isEmpty(str)) && (str.equals("samsung")))
    {
      a(paramContext);
      return 2010;
    }
    i = j;
    if (isMiui())
    {
      i = j;
      if (Build.VERSION.SDK_INT < 23)
      {
        i = j;
        if (!isMiuiPopupAllowed(paramContext)) {
          i = 2005;
        }
      }
    }
    return i;
  }
  
  public static boolean hasNavBar(Context paramContext)
  {
    boolean bool = false;
    try
    {
      paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      paramContext.getRealMetrics(localDisplayMetrics);
      int i = localDisplayMetrics.heightPixels;
      int j = localDisplayMetrics.widthPixels;
      localDisplayMetrics = new DisplayMetrics();
      paramContext.getMetrics(localDisplayMetrics);
      int k = localDisplayMetrics.heightPixels;
      int m = localDisplayMetrics.widthPixels;
      if ((j - m > 0) || (i - k > 0)) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isAutoBrightness(Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    boolean bool = false;
    try
    {
      int i = Settings.System.getInt(paramContext, "screen_brightness_mode");
      if (i == 1) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isEMUI()
  {
    Object localObject = new File("/system/build.prop");
    boolean bool4 = false;
    boolean bool3 = false;
    boolean bool2 = bool4;
    for (;;)
    {
      try
      {
        localObject = new BufferedReader(new FileReader((File)localObject));
        bool2 = bool4;
        String str = ((BufferedReader)localObject).readLine();
        bool1 = bool3;
        if (str != null)
        {
          bool2 = bool4;
          if (!str.contains("ro.build.version.emui"))
          {
            bool2 = bool4;
            if (!str.contains("ro.product.manufacturer=HUAWEI")) {
              continue;
            }
          }
        }
        else
        {
          bool2 = bool1;
          ((BufferedReader)localObject).close();
          return bool1;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return bool2;
      }
      boolean bool1 = true;
    }
  }
  
  public static boolean isInLockScreen(Context paramContext)
  {
    try
    {
      boolean bool = ((KeyguardManager)paramContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  @SuppressLint({"NewApi"})
  public static boolean isKeyguardSecure(Context paramContext)
  {
    try
    {
      boolean bool = ((KeyguardManager)paramContext.getSystemService("keyguard")).isKeyguardSecure();
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isMiui()
  {
    Object localObject = new File("/system/build.prop");
    try
    {
      localObject = new BufferedReader(new FileReader((File)localObject));
      String str;
      do
      {
        str = ((BufferedReader)localObject).readLine();
        if (str == null) {
          break;
        }
      } while (!str.toLowerCase().contains("ro.miui.ui.version.name"));
      ((BufferedReader)localObject).close();
      return true;
      ((BufferedReader)localObject).close();
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return false;
  }
  
  public static boolean isMiuiPopupAllowed(Context paramContext)
  {
    return (isMiui()) && (b(paramContext));
  }
  
  public static boolean isNetworkConnected(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null) {
        return paramContext.isAvailable();
      }
    }
    return false;
  }
  
  public static boolean isOppo()
  {
    Object localObject = new File("/system/build.prop");
    try
    {
      localObject = new BufferedReader(new FileReader((File)localObject));
      String str;
      do
      {
        str = ((BufferedReader)localObject).readLine();
        if (str == null) {
          break;
        }
      } while (!str.toLowerCase().contains("ro.build.version.opporom"));
      ((BufferedReader)localObject).close();
      return true;
      ((BufferedReader)localObject).close();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public static boolean isRestOpen(Context paramContext)
  {
    paramContext = (AudioManager)paramContext.getSystemService("audio");
    int i = paramContext.getStreamVolume(2);
    int j = paramContext.getStreamVolume(5);
    int k = paramContext.getStreamVolume(1);
    return (i == 0) && (j == 0) && (k == 0);
  }
  
  public static boolean isVivo()
  {
    Object localObject = new File("/system/build.prop");
    try
    {
      localObject = new BufferedReader(new FileReader((File)localObject));
      String str;
      do
      {
        str = ((BufferedReader)localObject).readLine();
        if (str == null) {
          break;
        }
      } while (!str.toLowerCase().contains("ro.vivo.os.version"));
      ((BufferedReader)localObject).close();
      return true;
      ((BufferedReader)localObject).close();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public static void openRest(Context paramContext)
  {
    paramContext = (AudioManager)paramContext.getSystemService("audio");
    paramContext.setStreamVolume(2, 0, 0);
    paramContext.setStreamVolume(5, 0, 0);
    paramContext.setStreamVolume(1, 0, 0);
  }
  
  public static void reenableKeyguard(Context paramContext)
  {
    try
    {
      PowerManager.WakeLock localWakeLock = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(10, "Lock");
      localWakeLock.acquire();
      localWakeLock.release();
      ((KeyguardManager)paramContext.getSystemService("keyguard")).newKeyguardLock("Lock").reenableKeyguard();
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void setBluetooth(boolean paramBoolean)
  {
    try
    {
      BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
      if (localBluetoothAdapter != null)
      {
        if (!paramBoolean)
        {
          localBluetoothAdapter.disable();
          return;
        }
        localBluetoothAdapter.enable();
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public static void setFingerprint(Context paramContext)
  {
    for (;;)
    {
      try
      {
        switch ()
        {
        case 5: 
          localIntent = new Intent();
          str1 = "android.settings.SETTINGS";
          localIntent.setAction(str1);
          paramContext.startActivity(localIntent);
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        Intent localIntent;
        String str1;
        String str2;
        continue;
      }
      localIntent = new Intent();
      str1 = "com.android.settings";
      str2 = "com.android.settings.Settings$AsusFingerprintSettings";
      localIntent.setClassName(str1, str2);
      continue;
      localIntent = new Intent();
      str1 = "com.android.settings";
      str2 = "com.android.settings.fingerprint.FingerprintMainSettingsActivity";
      continue;
      localIntent = new Intent();
      str1 = "com.zte.fingerprints";
      str2 = "com.zte.fingerprint.main.FingerSettingActivity";
      continue;
      localIntent = new Intent();
      str1 = "android.settings.SECURITY_SETTINGS";
      continue;
      new SecurityFailDialog(paramContext).show();
      return;
    }
  }
  
  public static void setLightness(Context paramContext, int paramInt)
  {
    try
    {
      Settings.System.putInt(paramContext.getContentResolver(), "screen_brightness", paramInt);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean setMobileData(Context paramContext, boolean paramBoolean)
  {
    Object localObject = (TelephonyManager)paramContext.getSystemService("phone");
    if ((((TelephonyManager)localObject).getSimState() != 1) && (((TelephonyManager)localObject).getSimState() != 0)) {
      try
      {
        localObject = (ConnectivityManager)paramContext.getSystemService("connectivity");
        localObject.getClass().getMethod("setMobileDataEnabled", new Class[] { Boolean.TYPE }).invoke(localObject, new Object[] { Boolean.valueOf(paramBoolean) });
        return true;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        try
        {
          Intent localIntent = new Intent();
          localIntent.setClassName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity");
          localIntent.addFlags(268435456);
          paramContext.startActivity(localIntent);
          return false;
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          Toast.makeText(paramContext, 2131427590, 0).show();
          return false;
        }
      }
    }
    try
    {
      Toast.makeText(paramContext, 2131427741, 0).show();
      return false;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static void setRotationStatus(Context paramContext, int paramInt)
  {
    try
    {
      Settings.System.putInt(paramContext.getContentResolver(), "accelerometer_rotation", paramInt);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void setVibrate(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static void setVolume(Context paramContext, int paramInt)
  {
    ((AudioManager)paramContext.getSystemService("audio")).setStreamVolume(2, paramInt, 0);
  }
  
  public static void setWifiData(Context paramContext, boolean paramBoolean)
  {
    ((WifiManager)paramContext.getSystemService("wifi")).setWifiEnabled(paramBoolean);
  }
  
  public static void startAutoBrightness(Context paramContext)
  {
    try
    {
      Settings.System.putInt(paramContext.getContentResolver(), "screen_brightness_mode", 1);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void stopAutoBrightness(Context paramContext)
  {
    try
    {
      Settings.System.putInt(paramContext.getContentResolver(), "screen_brightness_mode", 0);
      return;
    }
    catch (Exception paramContext) {}
  }
}
