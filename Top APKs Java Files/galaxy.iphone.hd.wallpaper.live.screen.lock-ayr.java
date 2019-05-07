import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AppOpsManager;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Network;
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
import galaxy.iphone.hd.wallpaper.live.screen.lock.view.dialog.SecurityFailDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

public class ayr
{
  private static boolean a(Context paramContext)
  {
    if (fo.checkSelfPermission(paramContext, "android.permission.CAMERA") != 0) {}
    while ((fo.checkSelfPermission(paramContext, "android.permission.READ_PHONE_STATE") != 0) || ((Build.VERSION.SDK_INT >= 23) && (!Settings.System.canWrite(paramContext)))) {
      return false;
    }
    return true;
  }
  
  @TargetApi(19)
  private static boolean a(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      AppOpsManager localAppOpsManager = (AppOpsManager)paramContext.getSystemService("appops");
      try
      {
        paramInt = ((Integer)AppOpsManager.class.getDeclaredMethod("checkOp", new Class[] { Integer.TYPE, Integer.TYPE, String.class }).invoke(localAppOpsManager, new Object[] { Integer.valueOf(paramInt), Integer.valueOf(Binder.getCallingUid()), paramContext.getPackageName() })).intValue();
        return paramInt == 0;
      }
      catch (Exception paramContext) {}
    }
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
  
  private static int c(Context paramContext)
  {
    int i;
    if (Build.VERSION.SDK_INT >= 19) {
      try
      {
        localObject = paramContext.getSystemService("appops");
        if (localObject == null) {
          return -1;
        }
        i = ((Integer)localObject.getClass().getMethod("checkOp", new Class[] { Integer.TYPE, Integer.TYPE, String.class }).invoke(localObject, new Object[] { Integer.valueOf(24), Integer.valueOf(Binder.getCallingUid()), paramContext.getPackageName() })).intValue();
        return i;
      }
      catch (Throwable paramContext)
      {
        return -1;
      }
    }
    Object localObject = (ActivityManager)paramContext.getSystemService("activity");
    if (localObject == null) {
      return -1;
    }
    if (!"com.lionmobi.battery".equalsIgnoreCase(((ActivityManager.RunningTaskInfo)((ActivityManager)localObject).getRunningTasks(1).get(0)).topActivity.getPackageName())) {
      try
      {
        paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0);
        if (paramContext != null)
        {
          i = paramContext.flags;
          if ((i & 0x8000000) == 0) {
            return 0;
          }
        }
      }
      catch (Throwable paramContext) {}
    }
    return -1;
  }
  
  public static void cancelScreenPwdByUser(Context paramContext)
  {
    try
    {
      if (isMiui())
      {
        localIntent = new Intent();
        localIntent.setComponent(new ComponentName("com.android.settings", "com.android.settings.MiuiSettings"));
        localIntent.setFlags(268435456);
        paramContext.startActivity(localIntent);
        return;
      }
      Intent localIntent = new Intent("android.app.action.SET_NEW_PASSWORD");
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
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
    int m = 1;
    int k = 0;
    Object localObject1 = "";
    Object localObject2 = paramContext.getPackageManager();
    List localList = ((PackageManager)localObject2).getInstalledPackages(0);
    int j = 0;
    int i = 0;
    paramContext = (Context)localObject1;
    if (j < localList.size()) {
      localObject1 = (PackageInfo)localList.get(j);
    }
    for (;;)
    {
      String str;
      try
      {
        str = ((PackageInfo)localObject1).applicationInfo.loadLabel((PackageManager)localObject2).toString();
        if ((!str.toLowerCase().equals("calculator")) && (!str.equals("计算器"))) {
          break label181;
        }
        paramContext = ((PackageInfo)localObject1).packageName;
        i = m;
        if (i != 0) {
          break label245;
        }
        i = k;
        if (i >= localList.size()) {
          break label245;
        }
        localObject1 = (PackageInfo)localList.get(i);
        localObject2 = ((PackageInfo)localObject1).packageName;
        if ((!((String)localObject2).toLowerCase().contains("calculator")) || (((String)localObject2).toLowerCase().contains("widget"))) {
          break label238;
        }
        return ((PackageInfo)localObject1).packageName;
      }
      catch (Exception localException)
      {
        j += 1;
      }
      break;
      label181:
      if (((str.toLowerCase().contains("calculator")) || (str.contains("计算器"))) && ((!str.toLowerCase().contains("widget")) || (!str.contains("小插件"))))
      {
        paramContext = localException.packageName;
        i = 1;
        continue;
        label238:
        i += 1;
        continue;
        label245:
        return paramContext;
      }
      else {}
    }
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
    boolean bool = false;
    try
    {
      BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
      if (localBluetoothAdapter != null) {
        bool = localBluetoothAdapter.isEnabled();
      }
      return bool;
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
    catch (Exception paramContext) {}
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
    if ((localTelephonyManager.getSimState() == 1) || (localTelephonyManager.getSimState() == 0)) {
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
      boolean bool = ((Boolean)localClass.getMethod("getMobileDataEnabled", paramContext).invoke(localConnectivityManager, paramArrayOfObject)).booleanValue();
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      if (localTelephonyManager.getDataState() == 2) {
        return true;
      }
    }
    return false;
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
      if (i - j >= 0) {
        return i - j;
      }
      return 0;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static boolean getRingStatus(Context paramContext)
  {
    try
    {
      int i = ((AudioManager)paramContext.getSystemService("audio")).getRingerMode();
      return 2 == i;
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
      if (i == 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        int i = 0;
      }
    }
  }
  
  public static int getScreenOffTimeout(Context paramContext)
  {
    try
    {
      int i = Settings.System.getInt(paramContext.getContentResolver(), "screen_off_timeout", -1);
      return i;
    }
    catch (Exception paramContext) {}
    return 30;
  }
  
  public static boolean getSyncStatus(Context paramContext)
  {
    try
    {
      if (((ConnectivityManager)paramContext.getSystemService("connectivity")).getBackgroundDataSetting())
      {
        boolean bool = ContentResolver.getMasterSyncAutomatically();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  /* Error */
  public static String getSystemProperty()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: new 398	java/io/BufferedReader
    //   5: dup
    //   6: new 400	java/io/InputStreamReader
    //   9: dup
    //   10: invokestatic 406	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   13: ldc_w 408
    //   16: invokevirtual 412	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   19: invokevirtual 418	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   22: invokespecial 420	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   25: sipush 1024
    //   28: invokespecial 423	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   31: astore_1
    //   32: aload_1
    //   33: astore_0
    //   34: aload_1
    //   35: invokevirtual 426	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   38: astore_2
    //   39: aload_1
    //   40: ifnull +7 -> 47
    //   43: aload_1
    //   44: invokevirtual 429	java/io/BufferedReader:close	()V
    //   47: aload_2
    //   48: areturn
    //   49: astore_0
    //   50: aload_0
    //   51: invokevirtual 430	java/io/IOException:printStackTrace	()V
    //   54: aload_2
    //   55: areturn
    //   56: astore_2
    //   57: aconst_null
    //   58: astore_1
    //   59: aload_1
    //   60: astore_0
    //   61: aload_2
    //   62: invokevirtual 430	java/io/IOException:printStackTrace	()V
    //   65: aload_1
    //   66: ifnull +7 -> 73
    //   69: aload_1
    //   70: invokevirtual 429	java/io/BufferedReader:close	()V
    //   73: ldc_w 432
    //   76: areturn
    //   77: astore_0
    //   78: aload_0
    //   79: invokevirtual 430	java/io/IOException:printStackTrace	()V
    //   82: goto -9 -> 73
    //   85: astore_2
    //   86: aload_0
    //   87: astore_1
    //   88: aload_2
    //   89: astore_0
    //   90: aload_1
    //   91: ifnull +7 -> 98
    //   94: aload_1
    //   95: invokevirtual 429	java/io/BufferedReader:close	()V
    //   98: aload_0
    //   99: athrow
    //   100: astore_1
    //   101: aload_1
    //   102: invokevirtual 430	java/io/IOException:printStackTrace	()V
    //   105: goto -7 -> 98
    //   108: astore_2
    //   109: aload_0
    //   110: astore_1
    //   111: aload_2
    //   112: astore_0
    //   113: goto -23 -> 90
    //   116: astore_2
    //   117: goto -58 -> 59
    // Local variable table:
    //   start	length	slot	name	signature
    //   1	33	0	localObject1	Object
    //   49	2	0	localIOException1	IOException
    //   60	1	0	localObject2	Object
    //   77	10	0	localIOException2	IOException
    //   89	24	0	localObject3	Object
    //   31	64	1	localObject4	Object
    //   100	2	1	localIOException3	IOException
    //   110	1	1	localObject5	Object
    //   38	17	2	str	String
    //   56	6	2	localIOException4	IOException
    //   85	4	2	localObject6	Object
    //   108	4	2	localObject7	Object
    //   116	1	2	localIOException5	IOException
    // Exception table:
    //   from	to	target	type
    //   43	47	49	java/io/IOException
    //   2	32	56	java/io/IOException
    //   69	73	77	java/io/IOException
    //   2	32	85	finally
    //   94	98	100	java/io/IOException
    //   34	39	108	finally
    //   61	65	108	finally
    //   34	39	116	java/io/IOException
  }
  
  public static int getUidByPkg(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getApplicationInfo(paramString, 1).uid;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 1000;
  }
  
  public static boolean getVibrate(Context paramContext)
  {
    boolean bool2 = false;
    AudioManager localAudioManager = (AudioManager)paramContext.getSystemService("audio");
    boolean bool1;
    if (Build.VERSION.SDK_INT < 16) {
      if (localAudioManager.getVibrateSetting(0) == 1) {
        bool1 = true;
      }
    }
    for (;;)
    {
      return bool1;
      bool1 = false;
      continue;
      bool1 = bool2;
      try
      {
        switch (localAudioManager.getRingerMode())
        {
        case 0: 
        case 2: 
          if (Settings.System.getInt(paramContext.getContentResolver(), "vibrate_when_ringing", 0) == 1) {
            return true;
          }
          int i = Settings.System.getInt(paramContext.getContentResolver(), "vibrate_when_ringing", 0);
          bool1 = bool2;
          if (i == 0) {
            return false;
          }
          break;
        }
      }
      catch (Exception paramContext)
      {
        return false;
      }
    }
    return false;
    return true;
  }
  
  public static int getWifiState(Context paramContext)
  {
    try
    {
      int i = ((WifiManager)paramContext.getSystemService("wifi")).getWifiState();
      return i;
    }
    catch (Exception paramContext) {}
    return 4;
  }
  
  public static int getWinDowsType(Context paramContext)
  {
    String str = Build.MANUFACTURER.toLowerCase();
    if ((!TextUtils.isEmpty(str)) && (str.equals("samsung"))) {
      if (!a(paramContext)) {}
    }
    for (;;)
    {
      return 2010;
      if ((!isMiui()) || (Build.VERSION.SDK_INT >= 23) || (isMiuiPopupAllowed(paramContext))) {}
    }
  }
  
  public static boolean hasNavBar(Context paramContext)
  {
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
      return (j - m > 0) || (i - k > 0);
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isAutoBrightness(Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    try
    {
      int i = Settings.System.getInt(paramContext, "screen_brightness_mode");
      return i == 1;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isEMUI()
  {
    boolean bool1 = true;
    Object localObject = new File("/system/build.prop");
    for (;;)
    {
      try
      {
        localObject = new BufferedReader(new FileReader((File)localObject));
        str = ((BufferedReader)localObject).readLine();
        if (str == null) {
          break label84;
        }
        bool2 = str.contains("ro.build.version.emui");
        if (!bool2) {}
      }
      catch (Exception localException1)
      {
        String str;
        boolean bool2;
        bool1 = false;
        localException1.printStackTrace();
        return bool1;
      }
      try
      {
        ((BufferedReader)localObject).close();
        return bool1;
      }
      catch (Exception localException2)
      {
        continue;
      }
      bool2 = str.contains("ro.product.manufacturer=HUAWEI");
      if (bool2)
      {
        continue;
        label84:
        bool1 = false;
      }
    }
  }
  
  public static boolean isInLockScreen(Context paramContext)
  {
    try
    {
      boolean bool = ((KeyguardManager)paramContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
      if (bool) {
        return true;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isIntentAvailable(Context paramContext, Intent paramIntent)
  {
    boolean bool = true;
    if (paramIntent == null) {
      return false;
    }
    if (paramContext.getPackageManager().queryIntentActivities(paramIntent, 1).size() > 0) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
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
  
  public static boolean isMiPad()
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
      } while (!str.toLowerCase().contains("mi pad"));
      ((BufferedReader)localObject).close();
      return true;
      ((BufferedReader)localObject).close();
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
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
      for (;;)
      {
        localIOException.printStackTrace();
      }
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
      for (;;)
      {
        localException.printStackTrace();
      }
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
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean isVpnConnected(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return false;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      Network[] arrayOfNetwork = paramContext.getAllNetworks();
      int j = arrayOfNetwork.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = arrayOfNetwork[i];
        if (localObject != null)
        {
          localObject = paramContext.getNetworkInfo((Network)localObject);
          if ((localObject != null) && (((NetworkInfo)localObject).getType() == 17) && (((NetworkInfo)localObject).isAvailable())) {
            return true;
          }
        }
        i += 1;
      }
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
  
  public static void setAutoSync(boolean paramBoolean)
  {
    try
    {
      ContentResolver.setMasterSyncAutomatically(paramBoolean);
      return;
    }
    catch (Exception localException) {}
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
        return;
      }
    }
    catch (Exception localException) {}
  }
  
  public static void setFingerprint(Context paramContext)
  {
    try
    {
      switch ()
      {
      case 0: 
      case 1: 
        Intent localIntent1 = new Intent();
        localIntent1.setAction("android.settings.SECURITY_SETTINGS");
        paramContext.startActivity(localIntent1);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      new SecurityFailDialog(paramContext).show();
      return;
    }
    Intent localIntent2 = new Intent();
    localIntent2.setClassName("com.zte.fingerprints", "com.zte.fingerprint.main.FingerSettingActivity");
    paramContext.startActivity(localIntent2);
    return;
    localIntent2 = new Intent();
    localIntent2.setClassName("com.android.settings", "com.android.settings.fingerprint.FingerprintMainSettingsActivity");
    paramContext.startActivity(localIntent2);
    return;
    localIntent2 = new Intent();
    localIntent2.setClassName("com.android.settings", "com.android.settings.Settings$AsusFingerprintSettings");
    paramContext.startActivity(localIntent2);
    return;
    localIntent2 = new Intent();
    localIntent2.setAction("android.settings.SETTINGS");
    paramContext.startActivity(localIntent2);
    return;
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
    if ((((TelephonyManager)localObject).getSimState() == 1) || (((TelephonyManager)localObject).getSimState() == 0)) {
      try
      {
        Toast.makeText(paramContext, 2131558938, 0).show();
        return false;
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          paramContext.printStackTrace();
        }
      }
    }
    try
    {
      localObject = (ConnectivityManager)paramContext.getSystemService("connectivity");
      localObject.getClass().getMethod("setMobileDataEnabled", new Class[] { Boolean.TYPE }).invoke(localObject, new Object[] { Boolean.valueOf(paramBoolean) });
      return true;
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
    }
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
      for (;;)
      {
        localException2.printStackTrace();
        Toast.makeText(paramContext, 2131558741, 0).show();
      }
    }
  }
  
  public static void setRotationStatus(Context paramContext, int paramInt)
  {
    try
    {
      Settings.System.putInt(paramContext.getContentResolver(), "accelerometer_rotation", paramInt);
      return;
    }
    catch (Exception paramContext)
    {
      System.out.println("===== exception: " + paramContext.getMessage());
    }
  }
  
  public static void setScreenOffTimeout(Context paramContext, int paramInt)
  {
    try
    {
      Settings.System.putInt(paramContext.getContentResolver(), "screen_off_timeout", paramInt);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void setVibrate(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = 1;
    paramContext = (AudioManager)paramContext.getSystemService("audio");
    if (paramBoolean2)
    {
      if (paramBoolean1) {}
      for (;;)
      {
        paramContext.setRingerMode(i);
        return;
        i = 0;
      }
    }
    if (paramBoolean1)
    {
      paramContext.setRingerMode(2);
      paramContext.setVibrateSetting(0, 1);
      return;
    }
    paramContext.setRingerMode(2);
    paramContext.setVibrateSetting(0, 0);
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
