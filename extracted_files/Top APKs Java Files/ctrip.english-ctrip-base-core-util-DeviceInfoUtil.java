package ctrip.base.core.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import ctrip.foundation.FoundationContextHolder;
import ctrip.foundation.util.LogUtil;
import ctrip.foundation.util.StringUtil;
import java.io.File;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DeviceInfoUtil
{
  private static final int BLUETOOTH_OFF = 0;
  private static Map<String, String> emulatorInfoMap;
  private static Boolean isEmulatorDevice;
  private static final int kSystemRootStateDisable = 0;
  private static final int kSystemRootStateEnable = 1;
  private static final int kSystemRootStateUnknow = -1;
  private static int systemRootState = -1;
  
  public DeviceInfoUtil() {}
  
  public static String checkAndGetEmuiVesion()
  {
    String str = getSystemProperty("ro.build.version.emui");
    if (!StringUtil.emptyOrNull(str)) {
      return str;
    }
    return null;
  }
  
  public static String checkAndGetMIUIVersion()
  {
    if (!StringUtil.emptyOrNull(getSystemProperty("ro.miui.ui.version.name"))) {
      return "MIUI_" + Build.VERSION.INCREMENTAL;
    }
    return null;
  }
  
  public static String getAndroidID()
  {
    try
    {
      String str = Settings.Secure.getString(FoundationContextHolder.context.getContentResolver(), "android_id");
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static boolean getAnimationSetting(Context paramContext)
  {
    return StringUtil.toDouble(Settings.System.getString(paramContext.getContentResolver(), "transition_animation_scale")) > 0.0D;
  }
  
  public static List<AppInfo> getAppList(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    ArrayList localArrayList = new ArrayList();
    Object localObject = paramContext.getInstalledApplications(8192);
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        if ((localApplicationInfo.flags & 0x1) == 0)
        {
          AppInfo localAppInfo = new AppInfo();
          localAppInfo.packageName = localApplicationInfo.packageName;
          localAppInfo.appName = localApplicationInfo.loadLabel(paramContext).toString();
          localArrayList.add(localAppInfo);
        }
      }
    }
    return localArrayList;
  }
  
  public static String getAvailMemory(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    localArrayList.add(Formatter.formatFileSize(paramContext, localMemoryInfo.availMem));
    if (Build.VERSION.SDK_INT >= 16) {
      localArrayList.add(Formatter.formatFileSize(paramContext, localMemoryInfo.totalMem));
    }
    localArrayList.add(String.valueOf(localMemoryInfo.lowMemory));
    return localArrayList.toString();
  }
  
  public static Map<String, String> getBuildExtInfo()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("BID", Build.ID);
    localHashMap.put("Board", Build.BOARD);
    localHashMap.put("Brand", Build.BRAND);
    localHashMap.put("Device", Build.DEVICE);
    localHashMap.put("Display", Build.DISPLAY);
    localHashMap.put("FingerPrint", Build.FINGERPRINT);
    localHashMap.put("HardWare", Build.HARDWARE);
    localHashMap.put("Manufacturer", Build.MANUFACTURER);
    localHashMap.put("Model", Build.MODEL);
    localHashMap.put("Product", Build.PRODUCT);
    localHashMap.put("User", Build.USER);
    return localHashMap;
  }
  
  public static float getDesity(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().density;
  }
  
  public static String getDeviceModel()
  {
    String str2 = Build.MODEL;
    String str1 = str2;
    if (str2 == null) {
      str1 = "";
    }
    return str1;
  }
  
  public static DeviceType getDeviceType()
  {
    DeviceType localDeviceType = DeviceType.UNKNOW;
    switch (((TelephonyManager)FoundationContextHolder.context.getSystemService("phone")).getPhoneType())
    {
    default: 
      return localDeviceType;
    case 1: 
    case 2: 
    case 3: 
      return DeviceType.PHONE;
    }
    return DeviceType.TABLET;
  }
  
  public static String getMac()
  {
    String str2 = "";
    Object localObject = "";
    try
    {
      LineNumberReader localLineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ").getInputStream()));
      do
      {
        str1 = str2;
        if (localObject == null) {
          break;
        }
        str1 = localLineNumberReader.readLine();
        localObject = str1;
      } while (str1 == null);
      String str1 = str1.trim();
      return str1;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return "";
  }
  
  public static String getMacAddress()
  {
    Object localObject1 = "";
    Object localObject4 = (WifiManager)FoundationContextHolder.context.getSystemService("wifi");
    localObject3 = localObject1;
    if (localObject4 != null)
    {
      localObject4 = ((WifiManager)localObject4).getConnectionInfo();
      localObject3 = localObject1;
      if (localObject4 != null)
      {
        localObject3 = localObject1;
        if (((WifiInfo)localObject4).getMacAddress() != null) {
          localObject3 = ((WifiInfo)localObject4).getMacAddress().replace(":", "");
        }
      }
    }
    if (!StringUtil.emptyOrNull((String)localObject3))
    {
      localObject1 = localObject3;
      if (!((String)localObject3).equalsIgnoreCase("020000000000")) {
        break label231;
      }
    }
    for (;;)
    {
      try
      {
        localObject4 = NetworkInterface.getNetworkInterfaces();
        localObject1 = localObject3;
        if (((Enumeration)localObject4).hasMoreElements())
        {
          localObject1 = (NetworkInterface)((Enumeration)localObject4).nextElement();
          localObject5 = ((NetworkInterface)localObject1).getHardwareAddress();
          if ((localObject5 == null) || (localObject5.length == 0)) {
            continue;
          }
          StringBuilder localStringBuilder = new StringBuilder();
          int j = localObject5.length;
          int i = 0;
          if (i < j)
          {
            localStringBuilder.append(String.format("%02X:", new Object[] { Byte.valueOf(localObject5[i]) }));
            i += 1;
            continue;
          }
          if (localStringBuilder.length() > 0) {
            localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
          }
          localObject5 = localStringBuilder.toString();
          if (!((NetworkInterface)localObject1).getName().startsWith("wlan0")) {
            continue;
          }
          localObject1 = ((String)localObject5).replace(":", "");
        }
      }
      catch (SocketException localSocketException)
      {
        Object localObject5;
        label231:
        Object localObject2 = localObject3;
        continue;
      }
      localObject3 = localObject1;
      if (!StringUtil.emptyOrNull((String)localObject1))
      {
        localObject3 = localObject1;
        if (((String)localObject1).contains("000000000000"))
        {
          localObject1 = getMac();
          localObject3 = localObject1;
          if (!StringUtil.emptyOrNull((String)localObject1))
          {
            ((String)localObject1).replace(":", "");
            localObject3 = localObject1;
          }
        }
      }
      return getUnNullString((String)localObject3);
      if (((NetworkInterface)localObject1).getName().startsWith("eth0")) {
        localObject1 = ((String)localObject5).replace(":", "");
      }
    }
  }
  
  public static int getPixelFromDip(float paramFloat)
  {
    return getPixelFromDip(FoundationContextHolder.context.getResources().getDisplayMetrics(), paramFloat);
  }
  
  public static int getPixelFromDip(DisplayMetrics paramDisplayMetrics, float paramFloat)
  {
    return (int)(TypedValue.applyDimension(1, paramFloat, paramDisplayMetrics) + 0.5F);
  }
  
  public static String getRomVersion()
  {
    Object localObject = checkAndGetMIUIVersion();
    if (!StringUtil.emptyOrNull((String)localObject)) {}
    String str;
    do
    {
      return localObject;
      str = checkAndGetEmuiVesion();
      localObject = str;
    } while (!StringUtil.emptyOrNull(str));
    return Build.VERSION.INCREMENTAL;
  }
  
  public static double getRunningMemory(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    Iterator localIterator = paramContext.getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      if (localRunningAppProcessInfo.processName.equalsIgnoreCase("ctrip.android.view"))
      {
        int i = localRunningAppProcessInfo.pid;
        int j = localRunningAppProcessInfo.uid;
        paramContext = paramContext.getProcessMemoryInfo(new int[] { i });
        double d1 = paramContext[0].dalvikPrivateDirty / 1024.0D;
        double d2 = paramContext[0].dalvikPss / 1024.0D;
        LogUtil.d("QQ", "dalvikPrivateDirty=" + d1 + "MB, heapSize=" + d2 + "MB");
        LogUtil.d("QQ", "nativePrivateDirty=" + paramContext[0].nativePrivateDirty / 1024.0D + "MB, heapSize=" + paramContext[0].nativePss / 1024.0D + "MB");
        LogUtil.d("QQ", "otherPrivateDirty=" + paramContext[0].otherPrivateDirty / 1024.0D + "MB, heapSize=" + paramContext[0].otherPss / 1024.0D + "MB");
        return paramContext[0].otherPrivateDirty / 1024.0D;
      }
    }
    return 0.0D;
  }
  
  public static int getSDKVersionInt()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static float getScreenBrightness(Activity paramActivity)
  {
    if (paramActivity != null) {}
    for (float f1 = paramActivity.getWindow().getAttributes().screenBrightness;; f1 = 0.0F)
    {
      float f2 = f1;
      if (f1 <= 0.0F) {
        paramActivity = paramActivity.getContentResolver();
      }
      try
      {
        int i = Settings.System.getInt(paramActivity, "screen_brightness");
        f2 = i / 255.0F;
        return f2;
      }
      catch (Settings.SettingNotFoundException paramActivity)
      {
        return 0.6F;
      }
    }
  }
  
  public static int[] getScreenSize(DisplayMetrics paramDisplayMetrics)
  {
    return new int[] { paramDisplayMetrics.widthPixels, paramDisplayMetrics.heightPixels };
  }
  
  public static String getSerialNum()
  {
    try
    {
      Object localObject = Class.forName("android.os.SystemProperties");
      localObject = ((Class)localObject).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject, new Object[] { "ro.serialno", "unknown" });
      if ((localObject != null) && ((localObject instanceof String)))
      {
        localObject = (String)localObject;
        return localObject;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  /* Error */
  public static String getSystemProperty(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 589	java/io/BufferedReader
    //   5: dup
    //   6: new 323	java/io/InputStreamReader
    //   9: dup
    //   10: invokestatic 329	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   13: new 54	java/lang/StringBuilder
    //   16: dup
    //   17: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   20: ldc_w 591
    //   23: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: aload_0
    //   27: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   33: invokevirtual 335	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   36: invokevirtual 341	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   39: invokespecial 344	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   42: sipush 1024
    //   45: invokespecial 594	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   48: astore_2
    //   49: aload_2
    //   50: astore_1
    //   51: aload_2
    //   52: invokevirtual 595	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   55: astore_0
    //   56: aload_2
    //   57: astore_1
    //   58: aload_2
    //   59: invokevirtual 598	java/io/BufferedReader:close	()V
    //   62: aload_0
    //   63: astore_1
    //   64: aload_2
    //   65: ifnull +9 -> 74
    //   68: aload_2
    //   69: invokevirtual 598	java/io/BufferedReader:close	()V
    //   72: aload_0
    //   73: astore_1
    //   74: aload_1
    //   75: areturn
    //   76: astore_1
    //   77: aload_1
    //   78: invokevirtual 587	java/lang/Exception:printStackTrace	()V
    //   81: aload_0
    //   82: areturn
    //   83: astore_3
    //   84: aconst_null
    //   85: astore_2
    //   86: aconst_null
    //   87: astore_0
    //   88: aload_2
    //   89: astore_1
    //   90: aload_3
    //   91: invokevirtual 587	java/lang/Exception:printStackTrace	()V
    //   94: aload_0
    //   95: astore_1
    //   96: aload_2
    //   97: ifnull -23 -> 74
    //   100: aload_2
    //   101: invokevirtual 598	java/io/BufferedReader:close	()V
    //   104: aload_0
    //   105: areturn
    //   106: astore_1
    //   107: aload_1
    //   108: invokevirtual 587	java/lang/Exception:printStackTrace	()V
    //   111: aload_0
    //   112: areturn
    //   113: astore_0
    //   114: aload_1
    //   115: ifnull +7 -> 122
    //   118: aload_1
    //   119: invokevirtual 598	java/io/BufferedReader:close	()V
    //   122: aload_0
    //   123: athrow
    //   124: astore_1
    //   125: aload_1
    //   126: invokevirtual 587	java/lang/Exception:printStackTrace	()V
    //   129: goto -7 -> 122
    //   132: astore_0
    //   133: goto -19 -> 114
    //   136: astore_3
    //   137: aconst_null
    //   138: astore_0
    //   139: goto -51 -> 88
    //   142: astore_3
    //   143: goto -55 -> 88
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	146	0	paramString	String
    //   1	74	1	localObject1	Object
    //   76	2	1	localException1	Exception
    //   89	7	1	localObject2	Object
    //   106	13	1	localException2	Exception
    //   124	2	1	localException3	Exception
    //   48	53	2	localBufferedReader	java.io.BufferedReader
    //   83	8	3	localException4	Exception
    //   136	1	3	localException5	Exception
    //   142	1	3	localException6	Exception
    // Exception table:
    //   from	to	target	type
    //   68	72	76	java/lang/Exception
    //   2	49	83	java/lang/Exception
    //   100	104	106	java/lang/Exception
    //   2	49	113	finally
    //   118	122	124	java/lang/Exception
    //   51	56	132	finally
    //   58	62	132	finally
    //   90	94	132	finally
    //   51	56	136	java/lang/Exception
    //   58	62	142	java/lang/Exception
  }
  
  public static String getTelePhoneIMEI()
  {
    Object localObject = (TelephonyManager)FoundationContextHolder.context.getSystemService("phone");
    if (localObject != null) {
      try
      {
        localObject = getUnNullString(((TelephonyManager)localObject).getDeviceId());
        return localObject;
      }
      catch (Exception localException) {}
    }
    return "";
  }
  
  public static String getTelePhoneIMSI()
  {
    Object localObject = (TelephonyManager)FoundationContextHolder.context.getSystemService("phone");
    if (localObject != null) {
      try
      {
        localObject = getUnNullString(((TelephonyManager)localObject).getSubscriberId());
        return localObject;
      }
      catch (SecurityException localSecurityException) {}
    }
    return "";
  }
  
  private static String getUnNullString(String paramString)
  {
    String str = paramString;
    if (StringUtil.emptyOrNull(paramString)) {
      str = "";
    }
    return str;
  }
  
  public static boolean isARMCPU()
  {
    String str = Build.CPU_ABI;
    return (str != null) && (str.toLowerCase().contains("arm"));
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isBluetoothPersistedStateOn()
  {
    boolean bool = false;
    try
    {
      int i = Settings.Secure.getInt(FoundationContextHolder.context.getContentResolver(), "bluetooth_on", 0);
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean isIntentAvailable(Context paramContext, Intent paramIntent)
  {
    if ((paramContext == null) || (paramIntent == null)) {}
    do
    {
      return false;
      paramContext = paramContext.getPackageManager();
    } while ((paramContext == null) || (paramContext.queryIntentActivities(paramIntent, 65536).size() <= 0));
    return true;
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isAvailable());
  }
  
  public static boolean isPhone()
  {
    return getDeviceType() == DeviceType.PHONE;
  }
  
  public static boolean isRoot()
  {
    if (systemRootState == 1) {
      return true;
    }
    if (systemRootState == 0) {
      return false;
    }
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "/system/bin/";
    arrayOfString[1] = "/system/xbin/";
    arrayOfString[2] = "/system/sbin/";
    arrayOfString[3] = "/sbin/";
    arrayOfString[4] = "/vendor/bin/";
    int i = 0;
    for (;;)
    {
      try
      {
        if (i < arrayOfString.length)
        {
          File localFile = new File(arrayOfString[i] + "su");
          if ((localFile == null) || (!localFile.exists())) {
            break label114;
          }
          systemRootState = 1;
          return true;
        }
      }
      catch (Exception localException)
      {
        systemRootState = 0;
        return false;
      }
      label114:
      i += 1;
    }
  }
  
  public static void setScreenBrightness(Activity paramActivity, float paramFloat)
  {
    if (paramActivity != null)
    {
      WindowManager.LayoutParams localLayoutParams = paramActivity.getWindow().getAttributes();
      localLayoutParams.screenBrightness = paramFloat;
      paramActivity.getWindow().setAttributes(localLayoutParams);
    }
  }
  
  public static class AppInfo
  {
    public String appName;
    public String packageName;
    
    public AppInfo() {}
  }
  
  public static enum DeviceType
  {
    PHONE,  TABLET,  UNKNOW;
    
    private DeviceType() {}
  }
  
  public static class WifiExtInfo
  {
    public String BSSID;
    public String SSID;
    public int SignalLevel;
    
    public WifiExtInfo() {}
  }
}
