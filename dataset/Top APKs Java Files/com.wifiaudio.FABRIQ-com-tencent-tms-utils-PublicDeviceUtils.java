package com.tencent.tms.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.view.View;
import com.tencent.tms.QubePackageManager;
import com.tencent.tms.common.utils.ApplicationDebugUtils;
import com.tencent.tms.kapalaiadapter.KapalaiAdapterUtil;
import com.tencent.tms.kapalaiadapter.MobileIssueSettings;
import com.tencent.tms.qlauncher.sim.SimManager;
import com.tencent.tms.remote.utils.QubeRemoteConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import qrom.component.log.QRomLog;

@SuppressLint({"DefaultLocale"})
public class PublicDeviceUtils
{
  private static final String TAG = "DeviceUtils";
  public static boolean isC8500;
  public static boolean isChaCha;
  private static boolean isCheckEUI;
  private static boolean isCheckEmui;
  private static boolean isCheckFuntouchOS;
  private static boolean isCheckGioNEEOS;
  private static boolean isCheckQiku;
  private static boolean isCheckTOS;
  private static boolean isCheckYunOS;
  private static boolean isColorOSROM;
  public static boolean isE15i;
  public static boolean isEMUI;
  private static boolean isEUI;
  private static boolean isFlyOSROM;
  private static boolean isFuntouchOS;
  public static boolean isGN700W;
  private static boolean isGioNEEOS;
  public static boolean isHero;
  public static boolean isHm;
  public static boolean isHtcHero;
  public static boolean isHtcOneX;
  public static boolean isI9000;
  public static boolean isI9100;
  public static boolean isI9300;
  public static boolean isI959;
  private static boolean isIuniROM;
  public static boolean isMB525;
  public static boolean isME525;
  public static boolean isME811;
  private static boolean isMIUI;
  public static boolean isMeizuM9;
  public static boolean isMi2;
  public static boolean isMi3;
  private static boolean isMiuiV5ROM;
  private static boolean isMiuiV6ROM;
  public static boolean isMx = false;
  public static boolean isMx2 = false;
  public static boolean isMx3 = false;
  public static boolean isMx4 = true;
  public static boolean isOppoX909;
  public static boolean isP6;
  private static boolean isQiku;
  public static boolean isS5360;
  public static boolean isS5830;
  public static boolean isS5830i;
  public static boolean isS880;
  public static boolean isSchW999;
  public static boolean isSumsang;
  private static boolean isTOSROM;
  public static boolean isU2;
  public static boolean isU20I;
  public static boolean isU8500;
  public static boolean isV880;
  public static boolean isV889D;
  public static boolean isW619;
  public static boolean isW719;
  public static boolean isW970;
  public static boolean isW9913;
  public static boolean isWildFire;
  public static boolean isXT800;
  private static boolean isYunOs;
  public static boolean isZTEU795;
  public static boolean ishtcEvo;
  private static int mDeviceCpuCores;
  private static int mDeviceCpuFrequency;
  private static String mDeviceImei;
  private static int mDeviceMemorySize;
  private static String mDeviceModel;
  private static String mIccid;
  private static boolean mMIUISCheckFinished;
  private static int mMaxAppMemory;
  private static boolean mMiuiV5ROMCheckFinished;
  private static boolean mMiuiV6ROMCheckFinished;
  private static int mSdkVersion;
  private static Method sMethodForgetLoadedWallpaper;
  private static Method sMethodGetActionBar;
  
  static
  {
    isMeizuM9 = false;
    isOppoX909 = false;
    isSumsang = false;
    isI9300 = false;
    isI959 = false;
    isI9000 = false;
    isS5830i = false;
    isS5830 = false;
    isMi2 = false;
    isMi3 = false;
    isHm = false;
    isGN700W = false;
    isXT800 = false;
    isMB525 = false;
    isME525 = false;
    isME811 = false;
    isHtcOneX = false;
    isU2 = false;
    isW619 = false;
    isW719 = false;
    isV889D = false;
    isZTEU795 = false;
    isS880 = false;
    isW970 = false;
    isSchW999 = false;
    isMiuiV5ROM = false;
    isMiuiV6ROM = false;
    mMiuiV5ROMCheckFinished = false;
    mMiuiV6ROMCheckFinished = false;
    isMx4 = false;
    isWildFire = false;
    isChaCha = false;
    isMIUI = false;
    isU8500 = false;
    isV880 = false;
    isW9913 = false;
    isS5360 = false;
    isC8500 = false;
    isE15i = false;
    isU20I = false;
    isHtcHero = false;
    isHero = false;
    isP6 = false;
    ishtcEvo = false;
    isI9100 = false;
    mSdkVersion = -1;
    mMIUISCheckFinished = false;
    mDeviceMemorySize = -1;
    mDeviceCpuCores = -1;
    mDeviceCpuFrequency = -1;
    mMaxAppMemory = -1;
    mDeviceImei = null;
    mIccid = null;
    mDeviceModel = null;
    isEMUI = false;
    isCheckEmui = false;
    isCheckTOS = false;
    isCheckYunOS = false;
    isCheckGioNEEOS = false;
    isCheckFuntouchOS = false;
    isCheckEUI = false;
    isCheckQiku = false;
    isYunOs = false;
    isGioNEEOS = false;
    isFuntouchOS = false;
    isEUI = false;
    isQiku = false;
    isTOSROM = false;
    isFlyOSROM = false;
    isColorOSROM = false;
    isIuniROM = false;
    String str = Build.MODEL.trim().toLowerCase();
    if (str.equals("m030")) {
      isMx = true;
    }
    do
    {
      do
      {
        return;
        if (str.equals("m040"))
        {
          isMx2 = true;
          return;
        }
        if ((str.equals("m351")) || (str.equals("m353")))
        {
          isMx3 = true;
          return;
        }
        if (str.equals("m9"))
        {
          isMeizuM9 = true;
          return;
        }
        if (str.equals("x909"))
        {
          isOppoX909 = true;
          return;
        }
        if (str.contains("gt-i9000"))
        {
          isI9000 = true;
          return;
        }
        if (str.contains("gt-i9100"))
        {
          isI9100 = true;
          return;
        }
        if (str.contains("i9300"))
        {
          isI9300 = true;
          return;
        }
        if (str.contains("i959"))
        {
          isI959 = true;
          return;
        }
        if ((str.contains("gt")) || (str.contains("sm")))
        {
          isSumsang = true;
          return;
        }
        if (str.contains("xt800"))
        {
          isXT800 = true;
          return;
        }
        if (str.contains("me525"))
        {
          isME525 = true;
          return;
        }
        if (str.contains("mb525"))
        {
          isMB525 = true;
          return;
        }
        if (str.contains("me811"))
        {
          isME811 = true;
          return;
        }
        if (!str.contains("s5830")) {
          break;
        }
        isS5830 = true;
      } while (!str.contains("s5830i"));
      isS5830i = true;
      return;
      if (str.contains("one x"))
      {
        isHtcOneX = true;
        return;
      }
      if (str.contains("mi 2"))
      {
        isMi2 = true;
        return;
      }
      if (str.contains("mi 3"))
      {
        isMi3 = true;
        return;
      }
      if (str.startsWith("hm"))
      {
        isHm = true;
        return;
      }
      if (str.contains("gn700w"))
      {
        isGN700W = true;
        return;
      }
      if (str.equals("u2"))
      {
        isU2 = true;
        return;
      }
      if (str.contains("w719"))
      {
        isW719 = true;
        return;
      }
      if (str.contains("w619"))
      {
        isW619 = true;
        return;
      }
      if (str.contains("v889"))
      {
        isV889D = true;
        return;
      }
      if (str.equals("zte u795"))
      {
        isZTEU795 = true;
        return;
      }
      if (str.contains("s880"))
      {
        isS880 = true;
        return;
      }
      if (str.contains("w970"))
      {
        isW970 = true;
        return;
      }
      if (str.equals("sch-w999"))
      {
        isSchW999 = true;
        return;
      }
      if (str.contains("wildfire"))
      {
        isWildFire = true;
        return;
      }
      if (str.contains("e15i"))
      {
        isE15i = true;
        return;
      }
      if (str.contains("u20i"))
      {
        isU20I = true;
        return;
      }
      if (str.equals("m9"))
      {
        isMeizuM9 = true;
        return;
      }
      if (str.equals("htc hero"))
      {
        isHtcHero = true;
        return;
      }
      if (str.equals("zte-u v880"))
      {
        isV880 = true;
        return;
      }
      if (str.contains("w9913"))
      {
        isW9913 = true;
        return;
      }
      if (str.contains("c8500"))
      {
        isC8500 = true;
        return;
      }
      if (str.contains("s5360"))
      {
        isS5360 = true;
        return;
      }
      if (str.contains("u8500"))
      {
        isU8500 = true;
        return;
      }
      if (str.equalsIgnoreCase("Hero"))
      {
        isHero = true;
        return;
      }
      if (str.contains("p6"))
      {
        isP6 = true;
        return;
      }
      if (str.contains("htc evo 3d x515m"))
      {
        ishtcEvo = true;
        return;
      }
    } while (!str.equals("mx4"));
  }
  
  private PublicDeviceUtils() {}
  
  @TargetApi(16)
  public static void adapterNavigationBar(View paramView)
  {
    if (!QubeRemoteConstants.sLessJellybean) {
      paramView.setSystemUiVisibility(772);
    }
  }
  
  @TargetApi(11)
  public static void adapterStatusBar(View paramView)
  {
    if (!QubeRemoteConstants.sLessHoneycomb) {}
    try
    {
      Field localField = View.class.getDeclaredField("SYSTEM_UI_FLAG_TRANSPARENT_BACKGROUND");
      if (localField.getType() == Integer.TYPE) {
        paramView.setSystemUiVisibility(localField.getInt(null));
      }
      return;
    }
    catch (Exception paramView) {}
  }
  
  @TargetApi(16)
  public static void clearPreloadedDrawables()
  {
    if (Looper.myLooper() != Looper.getMainLooper()) {}
    for (;;)
    {
      return;
      try
      {
        Object localObject = Resources.class.getDeclaredField("sPreloadedDrawables");
        if (localObject == null) {
          continue;
        }
        ((Field)localObject).setAccessible(true);
        localObject = ((Field)localObject).get(Resources.class);
        if (localObject == null) {
          continue;
        }
        if ((localObject instanceof LongSparseArray))
        {
          localObject = (LongSparseArray)localObject;
          if (localObject == null) {
            continue;
          }
          ((LongSparseArray)localObject).clear();
          return;
        }
        if (!(localObject instanceof LongSparseArray[])) {
          continue;
        }
        localObject = (LongSparseArray[])localObject;
        if ((localObject == null) || (localObject.length <= 0)) {
          continue;
        }
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          localObject[i].clear();
          i += 1;
        }
        return;
      }
      catch (Exception localException) {}
    }
  }
  
  public static void execCommand(String[] paramArrayOfString)
  {
    execCommand(paramArrayOfString, null);
  }
  
  /* Error */
  public static void execCommand(String[] paramArrayOfString, ProcessHandler paramProcessHandler)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +8 -> 9
    //   4: aload_0
    //   5: arraylength
    //   6: ifne +4 -> 10
    //   9: return
    //   10: new 11	com/tencent/tms/utils/PublicDeviceUtils$a
    //   13: dup
    //   14: aload_0
    //   15: invokespecial 454	com/tencent/tms/utils/PublicDeviceUtils$a:<init>	([Ljava/lang/String;)V
    //   18: astore 4
    //   20: aconst_null
    //   21: astore_0
    //   22: aconst_null
    //   23: astore_3
    //   24: aload 4
    //   26: invokevirtual 457	com/tencent/tms/utils/PublicDeviceUtils$a:start	()V
    //   29: aload_0
    //   30: astore_2
    //   31: aload 4
    //   33: ldc2_w 458
    //   36: invokevirtual 463	com/tencent/tms/utils/PublicDeviceUtils$a:join	(J)V
    //   39: aload_0
    //   40: astore_2
    //   41: aload 4
    //   43: invokevirtual 466	com/tencent/tms/utils/PublicDeviceUtils$a:a	()Ljava/lang/Integer;
    //   46: ifnull +39 -> 85
    //   49: aload_0
    //   50: astore_2
    //   51: aload 4
    //   53: invokevirtual 470	com/tencent/tms/utils/PublicDeviceUtils$a:b	()Ljava/lang/Process;
    //   56: astore_0
    //   57: aload_0
    //   58: astore_3
    //   59: aload_0
    //   60: ifnull +25 -> 85
    //   63: aload_0
    //   64: astore_3
    //   65: aload_1
    //   66: ifnull +19 -> 85
    //   69: aload_0
    //   70: astore_2
    //   71: aload_0
    //   72: astore_3
    //   73: aload_1
    //   74: aload_0
    //   75: invokevirtual 476	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   78: invokeinterface 480 2 0
    //   83: aload_0
    //   84: astore_3
    //   85: aload_3
    //   86: ifnull -77 -> 9
    //   89: aload_3
    //   90: invokevirtual 483	java/lang/Process:destroy	()V
    //   93: return
    //   94: astore_0
    //   95: return
    //   96: astore_0
    //   97: aload_2
    //   98: astore_3
    //   99: aload 4
    //   101: invokevirtual 486	com/tencent/tms/utils/PublicDeviceUtils$a:interrupt	()V
    //   104: aload_2
    //   105: ifnull -96 -> 9
    //   108: aload_2
    //   109: invokevirtual 483	java/lang/Process:destroy	()V
    //   112: return
    //   113: astore_0
    //   114: return
    //   115: astore_0
    //   116: aconst_null
    //   117: astore_3
    //   118: aload_3
    //   119: ifnull +7 -> 126
    //   122: aload_3
    //   123: invokevirtual 483	java/lang/Process:destroy	()V
    //   126: aload_0
    //   127: athrow
    //   128: astore_1
    //   129: goto -3 -> 126
    //   132: astore_0
    //   133: goto -15 -> 118
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	136	0	paramArrayOfString	String[]
    //   0	136	1	paramProcessHandler	ProcessHandler
    //   30	79	2	arrayOfString	String[]
    //   23	100	3	localObject	Object
    //   18	82	4	localA	a
    // Exception table:
    //   from	to	target	type
    //   89	93	94	java/lang/Exception
    //   31	39	96	java/lang/Exception
    //   41	49	96	java/lang/Exception
    //   51	57	96	java/lang/Exception
    //   73	83	96	java/lang/Exception
    //   108	112	113	java/lang/Exception
    //   31	39	115	finally
    //   41	49	115	finally
    //   51	57	115	finally
    //   122	126	128	java/lang/Exception
    //   73	83	132	finally
    //   99	104	132	finally
  }
  
  public static void forgetLoadedWallpaper(WallpaperManager paramWallpaperManager)
  {
    if (sMethodForgetLoadedWallpaper == null) {}
    try
    {
      sMethodForgetLoadedWallpaper = WallpaperManager.class.getMethod("forgetLoadedWallpaper", new Class[0]);
      if ((sMethodForgetLoadedWallpaper == null) || (paramWallpaperManager == null)) {}
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          sMethodForgetLoadedWallpaper.invoke(paramWallpaperManager, new Object[0]);
          return;
        }
        catch (Exception paramWallpaperManager)
        {
          QRomLog.w("DeviceUtils", paramWallpaperManager.getMessage());
        }
        localException = localException;
        sMethodForgetLoadedWallpaper = null;
      }
    }
  }
  
  public static ActionBar getActionBar(Activity paramActivity)
  {
    if (sMethodGetActionBar == null) {}
    try
    {
      sMethodGetActionBar = Activity.class.getMethod("getActionBar", new Class[0]);
      if ((sMethodGetActionBar == null) || (paramActivity == null)) {}
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          paramActivity = (ActionBar)sMethodGetActionBar.invoke(paramActivity, new Object[0]);
          return paramActivity;
        }
        catch (Exception paramActivity)
        {
          return null;
        }
        localException = localException;
        sMethodGetActionBar = null;
      }
    }
    return null;
  }
  
  public static List<PackageInfo> getAllAppList(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = QubePackageManager.getInstalledPackages(paramContext, 0).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if (localPackageInfo != null) {
          localArrayList.add(localPackageInfo);
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext) {}
  }
  
  public static int getAppMaxMemory(Context paramContext)
  {
    if (mMaxAppMemory == -1) {
      mMaxAppMemory = ((ActivityManager)paramContext.getApplicationContext().getSystemService("activity")).getMemoryClass();
    }
    return mMaxAppMemory;
  }
  
  public static long getAvailableDataStorage()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l = localStatFs.getBlockSize();
      int i = localStatFs.getAvailableBlocks();
      return i * l >> 20;
    }
    catch (Exception localException) {}
    return 0L;
  }
  
  public static int getCpuCoreNum()
  {
    if (mDeviceCpuCores != -1) {
      return mDeviceCpuCores;
    }
    mDeviceCpuCores = Runtime.getRuntime().availableProcessors();
    return mDeviceCpuCores;
  }
  
  public static int getCpuFrequency()
  {
    if (mDeviceCpuFrequency != -1) {
      return mDeviceCpuFrequency;
    }
    ProcessHandler local1 = new ProcessHandler()
    {
      public void handleProcessInputStream(InputStream paramAnonymousInputStream)
      {
        try
        {
          paramAnonymousInputStream = new BufferedReader(new InputStreamReader(paramAnonymousInputStream)).readLine();
          if (!TextUtils.isEmpty(paramAnonymousInputStream))
          {
            PublicDeviceUtils.access$002(Integer.parseInt(paramAnonymousInputStream.trim()) / 1000);
            return;
          }
          PublicDeviceUtils.access$002(0);
          return;
        }
        catch (Exception paramAnonymousInputStream)
        {
          QRomLog.w("DeviceUtils", paramAnonymousInputStream.getMessage());
        }
      }
    };
    execCommand(new String[] { "/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" }, local1);
    return mDeviceCpuFrequency;
  }
  
  public static long getDeviceAvailableMemory(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext != null)
    {
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      paramContext.getMemoryInfo(localMemoryInfo);
      return localMemoryInfo.availMem / 1024L;
    }
    return 0L;
  }
  
  @SuppressLint({"DefaultLocale"})
  public static String getDeviceBrand()
  {
    String str = Build.BRAND;
    if (str == null) {
      return "";
    }
    return str.toLowerCase();
  }
  
  @SuppressLint({"DefaultLocale"})
  public static String getDeviceImei(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    Object localObject1;
    if (TextUtils.isEmpty(mDeviceImei))
    {
      localObject1 = (TelephonyManager)paramContext.getSystemService("phone");
      if (localObject1 == null) {}
    }
    else
    {
      for (;;)
      {
        try
        {
          localObject1 = ((TelephonyManager)localObject1).getDeviceId();
          Object localObject2;
          localException1.printStackTrace();
        }
        catch (Exception localException1)
        {
          try
          {
            QRomLog.trace("DeviceUtils", "telephonyManager.getDeviceId() imei = " + (String)localObject1);
            localObject2 = localObject1;
            if (TextUtils.isEmpty((CharSequence)localObject1))
            {
              localObject2 = SimManager.getInstance(paramContext).getSimImei(1);
              QRomLog.trace("DeviceUtils", "SimManager.getInstance().getSimImei(1) imei = " + (String)localObject2);
            }
            if (!TextUtils.isEmpty((CharSequence)localObject2)) {
              break label136;
            }
            paramContext = "";
            mDeviceImei = paramContext;
            return mDeviceImei;
          }
          catch (Exception localException2)
          {
            for (;;) {}
          }
          localException1 = localException1;
          localObject1 = "";
        }
        continue;
        label136:
        paramContext = localException1.toUpperCase();
      }
    }
    if (TextUtils.isEmpty(mDeviceImei)) {}
    for (paramContext = "";; paramContext = mDeviceImei)
    {
      mDeviceImei = paramContext;
      break;
    }
  }
  
  @SuppressLint({"DefaultLocale"})
  public static String getDeviceManufacturer()
  {
    String str = Build.MANUFACTURER;
    if (str == null) {
      return "";
    }
    return str.toLowerCase();
  }
  
  public static String getDeviceModel()
  {
    if (mDeviceModel == null) {
      mDeviceModel = Build.MODEL.replaceAll("[ |\\/|\\_|\\&|\\|]", "");
    }
    return mDeviceModel;
  }
  
  /* Error */
  public static int getDeviceTotalMemory()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_1
    //   4: getstatic 202	com/tencent/tms/utils/PublicDeviceUtils:mDeviceMemorySize	I
    //   7: iconst_m1
    //   8: if_icmpeq +7 -> 15
    //   11: getstatic 202	com/tencent/tms/utils/PublicDeviceUtils:mDeviceMemorySize	I
    //   14: ireturn
    //   15: new 699	java/io/FileInputStream
    //   18: dup
    //   19: new 587	java/io/File
    //   22: dup
    //   23: ldc_w 701
    //   26: invokespecial 702	java/io/File:<init>	(Ljava/lang/String;)V
    //   29: invokespecial 705	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   32: astore_0
    //   33: new 707	java/io/BufferedReader
    //   36: dup
    //   37: new 709	java/io/InputStreamReader
    //   40: dup
    //   41: aload_0
    //   42: invokespecial 711	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   45: invokespecial 714	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   48: astore_2
    //   49: aload_2
    //   50: invokevirtual 717	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   53: astore_1
    //   54: aload_1
    //   55: ifnull +51 -> 106
    //   58: new 719	java/util/StringTokenizer
    //   61: dup
    //   62: aload_1
    //   63: invokespecial 720	java/util/StringTokenizer:<init>	(Ljava/lang/String;)V
    //   66: astore_1
    //   67: aload_1
    //   68: invokevirtual 723	java/util/StringTokenizer:hasMoreTokens	()Z
    //   71: ifeq -22 -> 49
    //   74: aload_1
    //   75: invokevirtual 726	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   78: astore_3
    //   79: aload_1
    //   80: invokevirtual 723	java/util/StringTokenizer:hasMoreTokens	()Z
    //   83: ifeq -34 -> 49
    //   86: aload_3
    //   87: ldc_w 728
    //   90: invokevirtual 359	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   93: ifeq -44 -> 49
    //   96: aload_1
    //   97: invokevirtual 726	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   100: invokestatic 732	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   103: putstatic 202	com/tencent/tms/utils/PublicDeviceUtils:mDeviceMemorySize	I
    //   106: aload_0
    //   107: ifnull +7 -> 114
    //   110: aload_0
    //   111: invokevirtual 735	java/io/FileInputStream:close	()V
    //   114: aload_2
    //   115: ifnull +7 -> 122
    //   118: aload_2
    //   119: invokevirtual 736	java/io/BufferedReader:close	()V
    //   122: getstatic 202	com/tencent/tms/utils/PublicDeviceUtils:mDeviceMemorySize	I
    //   125: ireturn
    //   126: astore_0
    //   127: ldc 19
    //   129: aload_0
    //   130: invokevirtual 506	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   133: invokestatic 512	qrom/component/log/QRomLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   136: goto -22 -> 114
    //   139: astore_0
    //   140: ldc 19
    //   142: aload_0
    //   143: invokevirtual 737	java/io/IOException:getMessage	()Ljava/lang/String;
    //   146: invokestatic 512	qrom/component/log/QRomLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   149: goto -27 -> 122
    //   152: astore_0
    //   153: aconst_null
    //   154: astore_0
    //   155: iconst_0
    //   156: putstatic 202	com/tencent/tms/utils/PublicDeviceUtils:mDeviceMemorySize	I
    //   159: aload_0
    //   160: ifnull +7 -> 167
    //   163: aload_0
    //   164: invokevirtual 735	java/io/FileInputStream:close	()V
    //   167: aload_1
    //   168: ifnull -46 -> 122
    //   171: aload_1
    //   172: invokevirtual 736	java/io/BufferedReader:close	()V
    //   175: goto -53 -> 122
    //   178: astore_0
    //   179: ldc 19
    //   181: aload_0
    //   182: invokevirtual 737	java/io/IOException:getMessage	()Ljava/lang/String;
    //   185: invokestatic 512	qrom/component/log/QRomLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   188: goto -66 -> 122
    //   191: astore_0
    //   192: ldc 19
    //   194: aload_0
    //   195: invokevirtual 506	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   198: invokestatic 512	qrom/component/log/QRomLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   201: goto -34 -> 167
    //   204: astore_0
    //   205: aconst_null
    //   206: astore_2
    //   207: aload_3
    //   208: astore_1
    //   209: aload_1
    //   210: ifnull +7 -> 217
    //   213: aload_1
    //   214: invokevirtual 735	java/io/FileInputStream:close	()V
    //   217: aload_2
    //   218: ifnull +7 -> 225
    //   221: aload_2
    //   222: invokevirtual 736	java/io/BufferedReader:close	()V
    //   225: aload_0
    //   226: athrow
    //   227: astore_1
    //   228: ldc 19
    //   230: aload_1
    //   231: invokevirtual 506	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   234: invokestatic 512	qrom/component/log/QRomLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   237: goto -20 -> 217
    //   240: astore_1
    //   241: ldc 19
    //   243: aload_1
    //   244: invokevirtual 737	java/io/IOException:getMessage	()Ljava/lang/String;
    //   247: invokestatic 512	qrom/component/log/QRomLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   250: goto -25 -> 225
    //   253: astore_3
    //   254: aconst_null
    //   255: astore_2
    //   256: aload_0
    //   257: astore_1
    //   258: aload_3
    //   259: astore_0
    //   260: goto -51 -> 209
    //   263: astore_3
    //   264: aload_0
    //   265: astore_1
    //   266: aload_3
    //   267: astore_0
    //   268: goto -59 -> 209
    //   271: astore_3
    //   272: aload_1
    //   273: astore_2
    //   274: aload_0
    //   275: astore_1
    //   276: aload_3
    //   277: astore_0
    //   278: goto -69 -> 209
    //   281: astore_2
    //   282: goto -127 -> 155
    //   285: astore_1
    //   286: aload_2
    //   287: astore_1
    //   288: goto -133 -> 155
    // Local variable table:
    //   start	length	slot	name	signature
    //   32	79	0	localFileInputStream	java.io.FileInputStream
    //   126	4	0	localException1	Exception
    //   139	4	0	localIOException1	java.io.IOException
    //   152	1	0	localException2	Exception
    //   154	10	0	localObject1	Object
    //   178	4	0	localIOException2	java.io.IOException
    //   191	4	0	localException3	Exception
    //   204	53	0	localObject2	Object
    //   259	19	0	localObject3	Object
    //   3	211	1	localObject4	Object
    //   227	4	1	localException4	Exception
    //   240	4	1	localIOException3	java.io.IOException
    //   257	19	1	localObject5	Object
    //   285	1	1	localException5	Exception
    //   287	1	1	localObject6	Object
    //   48	226	2	localObject7	Object
    //   281	6	2	localException6	Exception
    //   1	207	3	str	String
    //   253	6	3	localObject8	Object
    //   263	4	3	localObject9	Object
    //   271	6	3	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   110	114	126	java/lang/Exception
    //   118	122	139	java/io/IOException
    //   15	33	152	java/lang/Exception
    //   171	175	178	java/io/IOException
    //   163	167	191	java/lang/Exception
    //   15	33	204	finally
    //   213	217	227	java/lang/Exception
    //   221	225	240	java/io/IOException
    //   33	49	253	finally
    //   49	54	263	finally
    //   58	106	263	finally
    //   155	159	271	finally
    //   33	49	281	java/lang/Exception
    //   49	54	285	java/lang/Exception
    //   58	106	285	java/lang/Exception
  }
  
  public static String getIccid(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    if (TextUtils.isEmpty(mIccid))
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext == null) {}
    }
    else
    {
      for (;;)
      {
        try
        {
          paramContext = paramContext.getSimSerialNumber();
          QRomLog.trace("DeviceUtils", "telephonyManager.getSimSerialNumber() iccid = " + paramContext);
          if (!TextUtils.isEmpty(paramContext)) {
            continue;
          }
          paramContext = "";
          mDeviceImei = paramContext;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          continue;
        }
        return mIccid;
        paramContext = paramContext.toUpperCase();
      }
    }
    if (TextUtils.isEmpty(mIccid)) {}
    for (paramContext = "";; paramContext = mIccid)
    {
      mIccid = paramContext;
      break;
    }
  }
  
  @TargetApi(11)
  private static int getLargeMemoryClass(ActivityManager paramActivityManager)
  {
    return paramActivityManager.getLargeMemoryClass();
  }
  
  public static int getSdkVersion()
  {
    if (-1 == mSdkVersion) {
      mSdkVersion = Integer.parseInt(Build.VERSION.SDK);
    }
    return mSdkVersion;
  }
  
  public static long getTotalDataStorage()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l = localStatFs.getBlockSize();
      int i = localStatFs.getBlockCount();
      return i * l >> 20;
    }
    catch (Exception localException) {}
    return 0L;
  }
  
  public static float getUsedMemoryUsage(Context paramContext)
  {
    long l1 = getDeviceTotalMemory();
    long l2 = getDeviceAvailableMemory(paramContext);
    if ((l1 != 0L) && (l2 != 0L)) {
      return (float)(l1 - l2) / (float)l1;
    }
    return 0.0F;
  }
  
  public static void hideSmartBar(Activity paramActivity)
  {
    if (QubeRemoteConstants.sLessHoneycomb) {}
    do
    {
      return;
      localObject = getActionBar(paramActivity);
    } while (localObject == null);
    Object localObject = localObject.getClass();
    try
    {
      ((Class)localObject).getMethod("setTabsShowAtBottom", new Class[] { Boolean.TYPE }).invoke(getActionBar(paramActivity), new Object[] { Boolean.valueOf(true) });
      return;
    }
    catch (Exception paramActivity) {}
  }
  
  public static boolean isColorOS()
  {
    if (!isColorOSROM)
    {
      String str = ApplicationDebugUtils.getSystemProperty("ro.build.version.opporom");
      if ((str != null) && (!str.equals(""))) {
        isColorOSROM = true;
      }
    }
    return isColorOSROM;
  }
  
  public static boolean isEUI()
  {
    if (!isCheckEUI)
    {
      String str = ApplicationDebugUtils.getSystemProperty("ro.letv.eui");
      isCheckEUI = true;
      if (!TextUtils.isEmpty(str)) {
        isEUI = true;
      }
    }
    return isEUI;
  }
  
  public static boolean isEmuiHeighSystem(Context paramContext)
  {
    if (!isCheckEmui)
    {
      Intent localIntent = new Intent();
      localIntent.setClassName("com.android.settings", "com.android.settings.Settings$PreferredSettingsActivity");
      if (QubePackageManager.queryIntentActivities(paramContext, localIntent, 65536).size() <= 0) {
        break label55;
      }
    }
    label55:
    for (boolean bool = true;; bool = false)
    {
      isEMUI = bool;
      isCheckEmui = true;
      return isEMUI;
    }
  }
  
  public static boolean isFlymeOS()
  {
    if (!isFlyOSROM)
    {
      String str = ApplicationDebugUtils.getSystemProperty("ro.build.display.id");
      if (str != null) {
        if (!str.toLowerCase().contains("flyme")) {
          break label40;
        }
      }
    }
    label40:
    for (boolean bool = true;; bool = false)
    {
      isFlyOSROM = bool;
      return isFlyOSROM;
    }
  }
  
  public static boolean isFuntouchOS()
  {
    if (!isCheckFuntouchOS)
    {
      String str = ApplicationDebugUtils.getSystemProperty("ro.vivo.os.name");
      isCheckFuntouchOS = true;
      if (!TextUtils.isEmpty(str)) {
        isFuntouchOS = true;
      }
    }
    return isFuntouchOS;
  }
  
  public static boolean isGioNEEOS()
  {
    if (!isCheckGioNEEOS)
    {
      String str = ApplicationDebugUtils.getSystemProperty("ro.gn.gnromvernumber");
      isCheckGioNEEOS = true;
      if (!TextUtils.isEmpty(str)) {
        isGioNEEOS = true;
      }
    }
    return isGioNEEOS;
  }
  
  public static boolean isIuniOS()
  {
    if (!isIuniROM)
    {
      String str = ApplicationDebugUtils.getSystemProperty("ro.gn.iuniznvernumber");
      if ((str != null) && (!str.equals(""))) {
        isIuniROM = true;
      }
    }
    return isIuniROM;
  }
  
  @TargetApi(11)
  private static boolean isLargeHeap(Context paramContext)
  {
    return (paramContext.getApplicationInfo().flags & 0x100000) != 0;
  }
  
  public static boolean isMIUI(Context paramContext)
  {
    if (!mMIUISCheckFinished)
    {
      paramContext = ApplicationDebugUtils.getSystemProperty("ro.miui.ui.version.name");
      mMIUISCheckFinished = true;
      if (!TextUtils.isEmpty(paramContext)) {
        isMIUI = true;
      }
    }
    return isMIUI;
  }
  
  public static boolean isMiuiV5ROM()
  {
    if (!mMiuiV5ROMCheckFinished)
    {
      isMiuiV5ROM = TextUtils.equals(ApplicationDebugUtils.getSystemProperty("ro.miui.ui.version.name"), "V5");
      mMiuiV5ROMCheckFinished = true;
    }
    return isMiuiV5ROM;
  }
  
  public static boolean isMiuiV6ROM()
  {
    if (!mMiuiV6ROMCheckFinished)
    {
      isMiuiV6ROM = TextUtils.equals(ApplicationDebugUtils.getSystemProperty("ro.miui.ui.version.name"), "V6");
      mMiuiV6ROMCheckFinished = true;
    }
    return isMiuiV6ROM;
  }
  
  public static boolean isMxSmartBar()
  {
    return (isMx2) || (isMx3);
  }
  
  public static boolean isPadWithDownStatusbar()
  {
    return false;
  }
  
  public static boolean isQiku()
  {
    if (!isCheckQiku)
    {
      String str = ApplicationDebugUtils.getSystemProperty("sys.qiku.input.intercept");
      isCheckQiku = true;
      if (!TextUtils.isEmpty(str)) {
        isQiku = true;
      }
    }
    return isQiku;
  }
  
  public static boolean isScreenOn(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = (PowerManager)paramContext.getSystemService("power");
      try
      {
        boolean bool = paramContext.isScreenOn();
        return bool;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean isServiceActive(Context paramContext, String paramString)
  {
    paramContext = (ArrayList)((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(30);
    int i = 0;
    boolean bool2;
    for (boolean bool1 = false; i < paramContext.size(); bool1 = bool2)
    {
      ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)paramContext.get(i);
      bool2 = bool1;
      if (localRunningServiceInfo != null)
      {
        bool2 = bool1;
        if (localRunningServiceInfo.service.getClassName().toString().equals(paramString)) {
          bool2 = true;
        }
      }
      i += 1;
    }
    return bool1;
  }
  
  public static boolean isSupportFlashLight(Context paramContext)
  {
    paramContext = QubePackageManager.getSystemAvailableFeatures(paramContext);
    int i;
    if (paramContext != null)
    {
      int j = paramContext.length;
      i = 0;
      if (i < j)
      {
        Object localObject = paramContext[i];
        if ((localObject == null) || (!"android.hardware.camera.flash".equals(localObject.name))) {}
      }
    }
    for (boolean bool = true;; bool = false)
    {
      if ((isMeizuM9) || (isI9000) || (isXT800) || (isU2) || (isW719) || (isS5830i) || (isW619) || (isV889D) || (isS880)) {
        bool = false;
      }
      if ((isW970) || (isGN700W)) {
        bool = true;
      }
      if (!MobileIssueSettings.isSupportFlashLight) {
        bool = KapalaiAdapterUtil.getKAUInstance().getSupportFlashLight();
      }
      if (!MobileIssueSettings.isNotSupportButHasFlashLight) {
        bool = KapalaiAdapterUtil.getKAUInstance().getNotSupportButHasFlashLight();
      }
      return bool;
      i += 1;
      break;
    }
  }
  
  public static boolean isSystemApp(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {}
    for (;;)
    {
      return false;
      try
      {
        int i = QubePackageManager.getApplicationInfo(paramContext, paramString, 0).flags;
        if ((i & 0x1) != 0) {
          return true;
        }
      }
      catch (Exception paramContext) {}
    }
    return false;
  }
  
  public static boolean isTOS()
  {
    if (!isCheckTOS)
    {
      String str = ApplicationDebugUtils.getSystemProperty("ro.qrom.build.version.name");
      QRomLog.d("Set_default.DeviceUtils.isTOS", "tosName:" + str);
      isCheckTOS = true;
      if ((str != null) && (!str.equals(""))) {
        isTOSROM = true;
      }
    }
    return isTOSROM;
  }
  
  public static boolean isYunOS()
  {
    if (!isCheckYunOS)
    {
      String str = ApplicationDebugUtils.getSystemProperty("ro.yunos.version");
      isCheckYunOS = true;
      if ((str != null) && (!str.equals(""))) {
        isYunOs = true;
      }
    }
    return isYunOs;
  }
  
  public static void resetIPackageManager(Context paramContext)
  {
    Object localObject;
    if ((paramContext instanceof ContextWrapper)) {
      localObject = ((ContextWrapper)paramContext).getBaseContext();
    }
    try
    {
      Field localField = paramContext.getClassLoader().loadClass("android.app.ActivityThread").getDeclaredField("sPackageManager");
      localField.setAccessible(true);
      localField.set(null, null);
      localField = paramContext.getClassLoader().loadClass("android.app.ContextImpl").getDeclaredField("mPackageManager");
      localField.setAccessible(true);
      localField.set(localObject, null);
      paramContext = paramContext.getClassLoader().loadClass("android.os.ServiceManager");
      localObject = paramContext.getDeclaredField("sServiceManager");
      ((Field)localObject).setAccessible(true);
      ((Field)localObject).set(null, null);
      localObject = paramContext.getDeclaredField("sCache");
      ((Field)localObject).setAccessible(true);
      localObject = (HashMap)((Field)localObject).get(null);
      if ((localObject != null) && ((IBinder)((HashMap)localObject).remove("package") != null))
      {
        paramContext = paramContext.getDeclaredMethod("getService", new Class[] { String.class });
        paramContext.setAccessible(true);
        paramContext = (IBinder)paramContext.invoke(null, new Object[] { "package" });
        if (paramContext != null) {
          ((HashMap)localObject).put("package", paramContext);
        }
      }
      return;
    }
    catch (Exception paramContext)
    {
      QRomLog.e("DeviceUtils", paramContext);
    }
  }
  
  public static abstract interface ProcessHandler
  {
    public abstract void handleProcessInputStream(InputStream paramInputStream);
  }
  
  private static class a
    extends Thread
  {
    private String[] a;
    private Integer b;
    private Process c;
    
    public a(String[] paramArrayOfString)
    {
      this.a = paramArrayOfString;
    }
    
    public Integer a()
    {
      return this.b;
    }
    
    public Process b()
    {
      return this.c;
    }
    
    public void run()
    {
      try
      {
        this.c = new ProcessBuilder(this.a).start();
        this.b = Integer.valueOf(this.c.waitFor());
        return;
      }
      catch (Exception localException)
      {
        QRomLog.w("DeviceUtils", localException.getMessage());
      }
    }
  }
}
