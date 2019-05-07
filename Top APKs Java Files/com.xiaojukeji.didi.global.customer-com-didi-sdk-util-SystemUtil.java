package com.didi.sdk.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.annotation.Keep;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import com.didi.sdk.log.Logger;
import com.didi.sdk.utdevice.UTDeviceHolder;
import com.didi.sdk.utdevice.UTDeviceListener;
import com.didi.sdk.utilslib.R.string;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Keep
public class SystemUtil
{
  public static final String CHANNEL_ID = "channel_id";
  private static final String LOC_GPS = "gps";
  private static final Lock MAIN_PROCESS_LOCK = new ReentrantLock();
  private static volatile int MAIN_PROCESS_PID = -1;
  private static final String TAG = "SystemUtil";
  private static final Pattern VERSION_NAME_PATTERN = Pattern.compile("(\\d+\\.\\d+\\.\\d+)\\-*.*");
  private static boolean isCPUSerialnoObtained = false;
  private static boolean isMacSerialnoObtained = false;
  private static String mCPUSerial;
  private static String mDeviceId;
  private static String mImsi = "";
  private static String mMacSerial;
  private static String sAppVersion;
  private static Context sContext;
  private static int sVersionCode = -1;
  private static String strImei;
  
  public SystemUtil() {}
  
  public static boolean checkPermission(Context paramContext, String paramString)
  {
    boolean bool = checkPermission(paramContext, paramString, false);
    if (!bool)
    {
      paramContext = new StringBuilder();
      paramContext.append(" permission:");
      paramContext.append(paramString);
      paramContext.append("  disable");
      Log.e("SystemUtil", paramContext.toString());
    }
    return bool;
  }
  
  public static boolean checkPermission(Context paramContext, String paramString, boolean paramBoolean)
  {
    try
    {
      int i = paramContext.checkCallingOrSelfPermission(paramString);
      return i == 0;
    }
    catch (Exception paramContext) {}
    return paramBoolean;
  }
  
  public static String getAllInstalledPkg()
  {
    try
    {
      Object localObject1 = new Intent("android.intent.action.MAIN");
      ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
      Object localObject2 = sContext.getPackageManager();
      int i = 0;
      localObject1 = ((PackageManager)localObject2).queryIntentActivities((Intent)localObject1, 0);
      localObject2 = new StringBuilder();
      if ((localObject1 != null) && (!((List)localObject1).isEmpty())) {
        while (i < ((List)localObject1).size())
        {
          ((StringBuilder)localObject2).append(((ResolveInfo)((List)localObject1).get(i)).activityInfo.packageName);
          ((StringBuilder)localObject2).append("*");
          i += 1;
        }
      }
      localObject1 = ((StringBuilder)localObject2).toString();
      return localObject1;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static String getAndroidID()
  {
    return Settings.Secure.getString(sContext.getContentResolver(), "android_id");
  }
  
  public static String getCPUSerialno()
  {
    if (!TextUtil.isEmpty(mCPUSerial)) {
      return mCPUSerial;
    }
    if (isCPUSerialnoObtained)
    {
      mCPUSerial = "";
      return mCPUSerial;
    }
    Object localObject1 = "";
    try
    {
      isCPUSerialnoObtained = true;
      Object localObject2 = Runtime.getRuntime().exec("cat /proc/cpuinfo");
      if (localObject2 == null) {
        return null;
      }
      LineNumberReader localLineNumberReader = new LineNumberReader(new InputStreamReader(((Process)localObject2).getInputStream()));
      while (localObject1 != null)
      {
        localObject2 = localLineNumberReader.readLine();
        localObject1 = localObject2;
        if (localObject2 != null) {
          mCPUSerial = ((String)localObject2).trim();
        }
      }
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return mCPUSerial;
  }
  
  public static String getCellID(Context paramContext)
  {
    if (checkPermission(paramContext, "android.permission.ACCESS_COARSE_LOCATION"))
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null)
      {
        paramContext = paramContext.getCellLocation();
        if (paramContext == null) {
          return null;
        }
        if ((paramContext instanceof GsmCellLocation))
        {
          paramContext = (GsmCellLocation)paramContext;
          if (paramContext == null) {
            return null;
          }
          return Integer.toString(paramContext.getCid());
        }
        if ((paramContext instanceof CdmaCellLocation))
        {
          paramContext = (CdmaCellLocation)paramContext;
          if (paramContext == null) {
            return null;
          }
          return Integer.toString(paramContext.getBaseStationId());
        }
      }
      else
      {
        return null;
      }
    }
    return null;
  }
  
  public static String getChannelId()
  {
    return ChannelUtil.a(sContext);
  }
  
  public static String getDeviceTime()
  {
    long l = System.currentTimeMillis();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(l);
    localStringBuilder.append("");
    return localStringBuilder.toString();
  }
  
  private static final char getHexchar(int paramInt)
  {
    if (paramInt < 10) {
      return (char)(paramInt + 48);
    }
    return (char)(paramInt + 65 - 10);
  }
  
  public static String getIMEI()
  {
    if (!TextUtil.isEmpty(strImei)) {
      return strImei;
    }
    strImei = mDeviceId;
    if (!checkPermission(sContext, "android.permission.READ_PHONE_STATE")) {
      return getVirutalIMEI();
    }
    TelephonyManager localTelephonyManager = (TelephonyManager)sContext.getSystemService("phone");
    if (localTelephonyManager != null) {
      try
      {
        if (TextUtil.isEmpty(mDeviceId))
        {
          mDeviceId = localTelephonyManager.getDeviceId();
          strImei = mDeviceId;
        }
      }
      catch (Throwable localThrowable)
      {
        Log.e("SystemUtil", "", localThrowable);
      }
    }
    if (isSameChar(strImei)) {
      strImei = null;
    }
    if ((strImei == null) || (strImei.length() == 0) || (strImei.equals("null")))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("35");
      localStringBuilder.append(Build.BOARD.length() % 10);
      localStringBuilder.append(Build.BRAND.length() % 10);
      localStringBuilder.append(Build.CPU_ABI.length() % 10);
      localStringBuilder.append(Build.DEVICE.length() % 10);
      localStringBuilder.append(Build.DISPLAY.length() % 10);
      localStringBuilder.append(Build.HOST.length() % 10);
      localStringBuilder.append(Build.ID.length() % 10);
      localStringBuilder.append(Build.MANUFACTURER.length() % 10);
      localStringBuilder.append(Build.MODEL.length() % 10);
      localStringBuilder.append(Build.PRODUCT.length() % 10);
      localStringBuilder.append(Build.TAGS.length() % 10);
      localStringBuilder.append(Build.TYPE.length() % 10);
      localStringBuilder.append(Build.USER.length() % 10);
      strImei = localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(strImei);
    localStringBuilder.append(getLastModifiedMD5Str());
    strImei = localStringBuilder.toString();
    return strImei;
  }
  
  public static String getIMEI(Context paramContext)
  {
    if (!checkPermission(paramContext, "android.permission.READ_PHONE_STATE")) {
      return null;
    }
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (TextUtil.isEmpty(mDeviceId)) {
      mDeviceId = paramContext.getDeviceId();
    }
    if (paramContext == null) {
      return null;
    }
    return mDeviceId;
  }
  
  @Deprecated
  public static String getIMSI()
  {
    if ((TextUtils.isEmpty(mImsi)) && (checkPermission(sContext, "android.permission.READ_PHONE_STATE"))) {}
    for (;;)
    {
      try
      {
        Object localObject = (TelephonyManager)sContext.getSystemService("phone");
        if (TextUtils.isEmpty(((TelephonyManager)localObject).getSubscriberId())) {
          break label83;
        }
        localObject = ((TelephonyManager)localObject).getSubscriberId();
        mImsi = (String)localObject;
        localObject = mImsi;
        return localObject;
      }
      catch (Exception localException)
      {
        Logger.d(localException.toString(), new Object[0]);
        return "";
      }
      return mImsi;
      label83:
      String str = "";
    }
  }
  
  public static String getIPAddress(Context paramContext)
  {
    Object localObject = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localObject != null) && (((NetworkInfo)localObject).isConnected()))
    {
      if (((NetworkInfo)localObject).getType() == 0) {
        try
        {
          InetAddress localInetAddress;
          do
          {
            paramContext = NetworkInterface.getNetworkInterfaces();
            while (!((Enumeration)localObject).hasMoreElements())
            {
              if (!paramContext.hasMoreElements()) {
                break;
              }
              localObject = ((NetworkInterface)paramContext.nextElement()).getInetAddresses();
            }
            localInetAddress = (InetAddress)((Enumeration)localObject).nextElement();
          } while ((localInetAddress.isLoopbackAddress()) || (!(localInetAddress instanceof Inet4Address)));
          paramContext = localInetAddress.getHostAddress();
          return paramContext;
        }
        catch (SocketException paramContext)
        {
          paramContext.printStackTrace();
        }
      }
      if (((NetworkInfo)localObject).getType() == 1) {
        return intIP2StringIP(((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getIpAddress());
      }
    }
    return null;
  }
  
  public static String getLac(Context paramContext)
  {
    if (checkPermission(paramContext, "android.permission.ACCESS_COARSE_LOCATION"))
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null)
      {
        paramContext = paramContext.getCellLocation();
        if (paramContext == null) {
          return null;
        }
        if ((paramContext instanceof GsmCellLocation))
        {
          paramContext = (GsmCellLocation)paramContext;
          if (paramContext == null) {
            return null;
          }
          return Integer.toString(paramContext.getLac());
        }
        if ((paramContext instanceof CdmaCellLocation))
        {
          paramContext = (CdmaCellLocation)paramContext;
          if (paramContext == null) {
            return null;
          }
          return Integer.toString(paramContext.getNetworkId());
        }
      }
      else
      {
        return null;
      }
    }
    return null;
  }
  
  private static final String getLastModifiedMD5Str()
  {
    char[] arrayOfChar = md5(Long.valueOf(new File("/system/build.prop").lastModified()).toString());
    if (arrayOfChar == null) {
      return null;
    }
    return new String(arrayOfChar);
  }
  
  public static String getMacSerialno()
  {
    if (!TextUtil.isEmpty(mMacSerial)) {
      return mMacSerial;
    }
    if (isMacSerialnoObtained)
    {
      mMacSerial = "";
      return mMacSerial;
    }
    Object localObject1 = "";
    try
    {
      isMacSerialnoObtained = true;
      Object localObject2 = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address");
      if (localObject2 == null) {
        return null;
      }
      LineNumberReader localLineNumberReader = new LineNumberReader(new InputStreamReader(((Process)localObject2).getInputStream()));
      while (localObject1 != null)
      {
        localObject2 = localLineNumberReader.readLine();
        localObject1 = localObject2;
        if (localObject2 != null) {
          mMacSerial = ((String)localObject2).trim();
        }
      }
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return mMacSerial;
  }
  
  public static String getModel()
  {
    String str = Build.MODEL;
    if (TextUtil.isEmpty(str)) {
      return "";
    }
    return str;
  }
  
  private static String getNavBarOverride()
  {
    if (Build.VERSION.SDK_INT >= 19) {}
    try
    {
      Object localObject = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[] { String.class });
      ((Method)localObject).setAccessible(true);
      localObject = (String)((Method)localObject).invoke(null, new Object[] { "qemu.hw.mainkeys" });
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static int getNavigationBarHeight(Context paramContext)
  {
    if (hasNavBar(paramContext))
    {
      paramContext = paramContext.getResources();
      int i = paramContext.getIdentifier("navigation_bar_height", "dimen", "android");
      if (i > 0) {
        return paramContext.getDimensionPixelSize(i);
      }
    }
    return 0;
  }
  
  public static String getNetworkType()
  {
    Object localObject = (ConnectivityManager)sContext.getSystemService("connectivity");
    try
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      localTelephonyManager = null;
    }
    if ((localTelephonyManager != null) && (1 == localTelephonyManager.getType())) {
      return "WIFI";
    }
    TelephonyManager localTelephonyManager = (TelephonyManager)sContext.getSystemService("phone");
    if (localTelephonyManager == null) {
      return "UNKNOWN";
    }
    switch (localTelephonyManager.getNetworkType())
    {
    default: 
      return "UNKNOWN";
    case 13: 
      return "4G";
    case 3: 
    case 5: 
    case 6: 
    case 8: 
    case 9: 
    case 10: 
    case 12: 
    case 14: 
    case 15: 
      return "3G";
    case 1: 
    case 2: 
    case 4: 
    case 7: 
    case 11: 
      return "2G";
    }
    return "UNKNOWN";
  }
  
  public static PackageInfo getPackageApkInfo(String paramString)
  {
    Object localObject = sContext.getPackageManager();
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    while (i < ((List)localObject).size())
    {
      if (((PackageInfo)((List)localObject).get(i)).packageName.equalsIgnoreCase(paramString)) {
        return (PackageInfo)((List)localObject).get(i);
      }
      i += 1;
    }
    return null;
  }
  
  public static String getPhoneNumber()
  {
    if (!checkPermission(sContext, "android.permission.READ_PHONE_STATE")) {
      return "";
    }
    TelephonyManager localTelephonyManager = (TelephonyManager)sContext.getSystemService("phone");
    if (localTelephonyManager == null) {
      return null;
    }
    return localTelephonyManager.getLine1Number();
  }
  
  public static String getProcessName(Context paramContext, int paramInt)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if ((paramContext != null) && (!paramContext.isEmpty()))
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localRunningAppProcessInfo.pid == paramInt) {
          return localRunningAppProcessInfo.processName;
        }
      }
    }
    return null;
  }
  
  public static int getScreenDpi()
  {
    return sContext.getResources().getDisplayMetrics().densityDpi;
  }
  
  public static int getScreenHeight()
  {
    return sContext.getResources().getDisplayMetrics().heightPixels;
  }
  
  public static int getScreenWidth()
  {
    return sContext.getResources().getDisplayMetrics().widthPixels;
  }
  
  public static String getServiceProvider(Context paramContext)
  {
    if (!checkPermission(paramContext, "android.permission.READ_PHONE_STATE")) {
      return "None";
    }
    String str2 = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
    if (TextUtil.isEmpty(str2)) {
      return "None";
    }
    String str1;
    if ((!str2.startsWith("46000")) && (!str2.startsWith("46002")))
    {
      if (str2.startsWith("46001")) {
        return paramContext.getString(R.string.china_unicom);
      }
      if (str2.startsWith("46003")) {
        return paramContext.getString(R.string.china_telecom);
      }
      if (str2.startsWith("30272")) {
        return paramContext.getString(R.string.ata);
      }
      if (str2.startsWith("36251")) {
        return paramContext.getString(R.string.telcel);
      }
      if (!str2.startsWith("21402"))
      {
        str1 = str2;
        if (!str2.startsWith("21407")) {}
      }
      else
      {
        return paramContext.getString(R.string.movistart);
      }
    }
    else
    {
      str1 = paramContext.getString(R.string.china_mobile);
    }
    return str1;
  }
  
  public static String getUtDid(Context paramContext)
  {
    try
    {
      UTDeviceListener localUTDeviceListener = UTDeviceHolder.getInstance().getDeviceListener();
      if (localUTDeviceListener != null)
      {
        paramContext = localUTDeviceListener.getUtdid(paramContext);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      Logger.e(paramContext.toString(), new Object[0]);
    }
    return "";
  }
  
  public static String getVersion()
  {
    String str = Build.VERSION.SDK;
    if (TextUtil.isEmpty(str)) {
      return "";
    }
    return str;
  }
  
  public static int getVersionCode()
  {
    if (sVersionCode != -1) {
      return sVersionCode;
    }
    Object localObject = sContext.getPackageName();
    try
    {
      localObject = sContext.getPackageManager().getPackageInfo((String)localObject, 16384);
      if (localObject != null)
      {
        sVersionCode = ((PackageInfo)localObject).versionCode;
        int i = sVersionCode;
        return i;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 1;
  }
  
  @Deprecated
  public static String getVersionName()
  {
    return getVersionName(sContext);
  }
  
  public static String getVersionName(Context paramContext)
  {
    if (!TextUtils.isEmpty(sAppVersion)) {
      return sAppVersion;
    }
    str1 = "";
    Object localObject = str1;
    try
    {
      String str2 = paramContext.getApplicationInfo().packageName;
      localObject = str1;
      str1 = paramContext.getPackageManager().getPackageInfo(str2, 0).versionName;
      paramContext = str1;
      if (str1 != null) {
        paramContext = str1;
      }
      return str1;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        try
        {
          if (str1.length() > 0)
          {
            localObject = VERSION_NAME_PATTERN.matcher(str1);
            paramContext = str1;
            if (((Matcher)localObject).matches())
            {
              paramContext = ((Matcher)localObject).group(1);
              localObject = paramContext;
              sAppVersion = paramContext;
              localObject = paramContext;
              paramContext = (Context)localObject;
            }
          }
          return paramContext;
        }
        catch (Exception paramContext) {}
        paramContext = paramContext;
      }
    }
  }
  
  private static String getVirutalIMEI()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("35");
    localStringBuilder.append(Build.BOARD.length() % 10);
    localStringBuilder.append(Build.BRAND.length() % 10);
    localStringBuilder.append(Build.CPU_ABI.length() % 10);
    localStringBuilder.append(Build.DEVICE.length() % 10);
    localStringBuilder.append(Build.DISPLAY.length() % 10);
    localStringBuilder.append(Build.HOST.length() % 10);
    localStringBuilder.append(Build.ID.length() % 10);
    localStringBuilder.append(Build.MANUFACTURER.length() % 10);
    localStringBuilder.append(Build.MODEL.length() % 10);
    localStringBuilder.append(Build.PRODUCT.length() % 10);
    localStringBuilder.append(Build.TAGS.length() % 10);
    localStringBuilder.append(Build.TYPE.length() % 10);
    localStringBuilder.append(Build.USER.length() % 10);
    return localStringBuilder.toString();
  }
  
  public static WifiInfo getWifiInfo(Context paramContext)
  {
    try
    {
      if (checkPermission(paramContext, "android.permission.ACCESS_WIFI_STATE"))
      {
        paramContext = (WifiManager)paramContext.getSystemService("wifi");
        if (paramContext == null) {
          return null;
        }
        paramContext = paramContext.getConnectionInfo();
        return paramContext;
      }
      return null;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  private static boolean hasNavBar(Context paramContext)
  {
    Resources localResources = paramContext.getResources();
    int i = localResources.getIdentifier("config_showNavigationBar", "bool", "android");
    if (i != 0)
    {
      boolean bool = localResources.getBoolean(i);
      paramContext = getNavBarOverride();
      if ("1".equals(paramContext)) {
        return false;
      }
      if ("0".equals(paramContext)) {
        return true;
      }
      return bool;
    }
    return ViewConfiguration.get(paramContext).hasPermanentMenuKey() ^ true;
  }
  
  public static void hideSoftKeyboard(Activity paramActivity)
  {
    View localView = paramActivity.getCurrentFocus();
    if (localView != null)
    {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(localView.getWindowToken(), 2);
      return;
    }
    paramActivity.getWindow().setSoftInputMode(3);
  }
  
  public static void hideSoftKeyboard(View paramView)
  {
    if (paramView != null) {
      ((InputMethodManager)paramView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 2);
    }
  }
  
  public static void init(Context paramContext)
  {
    if ((sContext == null) && (paramContext != null))
    {
      sContext = paramContext.getApplicationContext();
      new Thread(new SystemUtil.1()).start();
    }
  }
  
  public static String intIP2StringIP(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt & 0xFF);
    localStringBuilder.append(".");
    localStringBuilder.append(paramInt >> 8 & 0xFF);
    localStringBuilder.append(".");
    localStringBuilder.append(paramInt >> 16 & 0xFF);
    localStringBuilder.append(".");
    localStringBuilder.append(paramInt >> 24 & 0xFF);
    return localStringBuilder.toString();
  }
  
  public static boolean isAccessibilitySettingsOn(Context paramContext)
  {
    boolean bool2 = false;
    try
    {
      paramContext = (AccessibilityManager)paramContext.getSystemService("accessibility");
      boolean bool3 = paramContext.isEnabled();
      boolean bool4 = paramContext.isTouchExplorationEnabled();
      paramContext = new StringBuilder();
      paramContext.append("isAccessibilityEnabled:");
      paramContext.append(bool3);
      paramContext.append("   isExploreByTouchEnabled:");
      paramContext.append(bool4);
      Log.d("AccessibilitySettings", paramContext.toString());
      boolean bool1 = bool2;
      if (bool3)
      {
        bool1 = bool2;
        if (bool4) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isGpsEnabled()
  {
    String str = Settings.System.getString(sContext.getContentResolver(), "location_providers_allowed");
    return (!TextUtils.isEmpty(str)) && (str.contains("gps"));
  }
  
  public static boolean isMainProcess(Context paramContext, int paramInt)
  {
    if (MAIN_PROCESS_PID == -1)
    {
      MAIN_PROCESS_LOCK.lock();
      try
      {
        if (MAIN_PROCESS_PID == -1)
        {
          String str = paramContext.getPackageName();
          paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
          while (paramContext.hasNext())
          {
            ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
            if (str.equals(localRunningAppProcessInfo.processName)) {
              MAIN_PROCESS_PID = localRunningAppProcessInfo.pid;
            }
          }
        }
      }
      finally
      {
        MAIN_PROCESS_LOCK.unlock();
      }
    }
    return (MAIN_PROCESS_PID != -1) && (MAIN_PROCESS_PID == paramInt);
  }
  
  public static boolean isRoot()
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "/system/bin/su";
    arrayOfString[1] = "/system/xbin/su";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      File localFile = new File(arrayOfString[i]);
      if ((localFile != null) && (localFile.exists())) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isRunningOnEmualtor(Context paramContext)
  {
    boolean bool3 = true;
    try
    {
      if (!checkPermission(paramContext, "android.permission.READ_PHONE_STATE")) {
        return false;
      }
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (!TextUtil.isEmpty(mDeviceId)) {
        break label248;
      }
      mDeviceId = paramContext.getDeviceId();
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        boolean bool1;
        int i;
        continue;
        if (paramContext == null) {
          paramContext = null;
        }
      }
    }
    paramContext = mDeviceId;
    if (paramContext != null)
    {
      bool1 = paramContext.equals("000000000000000");
      if (bool1)
      {
        i = 1;
        break label73;
      }
    }
    i = 0;
    for (;;)
    {
      label73:
      boolean bool2;
      int j;
      try
      {
        paramContext = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[] { String.class });
        if (paramContext != null)
        {
          paramContext.setAccessible(true);
          bool1 = "1".equals(paramContext.invoke(null, new Object[] { "ro.kernel.qemu" }));
        }
        else
        {
          bool1 = false;
        }
      }
      catch (Exception paramContext)
      {
        int k;
        continue;
      }
      try
      {
        if ((!Build.MODEL.equalsIgnoreCase("sdk")) && (!Build.MODEL.equalsIgnoreCase("google_sdk")))
        {
          boolean bool4 = Build.MODEL.contains("Droid4X");
          bool2 = bool1;
          j = i;
          if (!bool4) {
            continue;
          }
        }
        j = 1;
      }
      catch (Exception paramContext)
      {
        bool2 = bool1;
        j = i;
      }
    }
    i = 0;
    bool2 = false;
    j = i;
    k = 0;
    i = j;
    j = k;
    bool1 = bool2;
    bool2 = bool3;
    if (!bool1)
    {
      bool2 = bool3;
      if (j == 0)
      {
        if (i != 0) {
          return true;
        }
        bool2 = false;
      }
    }
    return bool2;
  }
  
  public static boolean isSameChar(String paramString)
  {
    if (paramString != null)
    {
      if ("".equals(paramString)) {
        return false;
      }
      int i = 0;
      while (i < paramString.length() - 1)
      {
        int k = paramString.charAt(i);
        int j = i + 1;
        i = j;
        if (k != paramString.charAt(j)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }
  
  public static boolean isSoftInputOpen(Context paramContext)
  {
    return ((InputMethodManager)paramContext.getSystemService("input_method")).isActive();
  }
  
  public static boolean isWifiEnabled()
  {
    return ((WifiManager)sContext.getSystemService("wifi")).isWifiEnabled();
  }
  
  private static final char[] md5(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      int k = paramString.length << 1;
      localObject = new char[k];
      int i = 0;
      int j = 0;
      while (i < k)
      {
        int m = paramString[j] & 0xFF;
        j = (byte)(j + 1);
        if (m < 16)
        {
          localObject[i] = 48;
          localObject[(i + 1)] = getHexchar(m);
        }
        else
        {
          localObject[i] = getHexchar(m >> 4);
          localObject[(i + 1)] = getHexchar(m & 0xF);
        }
        i += 2;
      }
      return localObject;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static boolean needDownOrUpdate(String paramString, int paramInt)
  {
    paramString = getPackageApkInfo(paramString);
    if (paramString == null) {
      return true;
    }
    return paramInt > paramString.versionCode;
  }
  
  public static void showSofyKeyboard(View paramView)
  {
    paramView.postDelayed(new SystemUtil.2(paramView), 500L);
  }
}
