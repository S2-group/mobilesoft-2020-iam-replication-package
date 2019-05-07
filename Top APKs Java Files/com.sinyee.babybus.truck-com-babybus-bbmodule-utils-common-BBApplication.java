package com.babybus.bbmodule.utils.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.babybus.bbmodule.utils.BBResources;
import com.babybus.bbmodule.utils.BBStringUtil;
import com.babybus.bbmodule.utils.ReflectUtil;
import com.babybus.bbmodule.utils.SDCardUtil;
import com.babybus.bbmodule.utils.SharedPreUtil;
import com.babybus.bbmodule.utils.frameworkutils.ReflectFrameworkConstUtil;
import com.babybus.common.wiget.BBWebActivity;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@TargetApi(11)
public class BBApplication
{
  public static final String CMNET = "cmnet";
  public static final String CMWAP = "cmwap";
  public static final String CTNET = "ctnet";
  public static final String CTWAP = "ctwap";
  public static final int ERGEDD_COLLECTION_TYPE = 22;
  public static final int ERGEDD_OFFLINE_TYPE = 26;
  public static final int ERGEDD_PLAYSONG_TYPE = 25;
  public static final int ERGEDD_SONGLIST_TYPE = 23;
  public static final int ERGEDD_SONGPLAYLIST_TYPE = 24;
  public static final int HTTP_TYPE = 21;
  public static final String NET_3G = "3gnet";
  public static final int NULL_TYPE = 20;
  public static final int OTHER_TYPE = 30;
  public static Uri PREFERRED_APN_URI = Uri.parse("content://telephony/carriers/preferapn");
  public static final int TYPE_CM_NET = 10;
  public static final int TYPE_CM_NET_2G = 12;
  public static final int TYPE_CM_WAP = 9;
  public static final int TYPE_CM_WAP_2G = 11;
  public static final int TYPE_CT_NET = 6;
  public static final int TYPE_CT_NET_2G = 8;
  public static final int TYPE_CT_WAP = 5;
  public static final int TYPE_CT_WAP_2G = 7;
  public static final int TYPE_CU_NET = 14;
  public static final int TYPE_CU_NET_2G = 16;
  public static final int TYPE_CU_WAP = 13;
  public static final int TYPE_CU_WAP_2G = 15;
  public static final int TYPE_NET_WORK_DISABLED = 0;
  public static final int TYPE_OTHER = 17;
  public static final int TYPE_WIFI = 4;
  public static final String UNINET = "uninet";
  public static final String UNIWAP = "uniwap";
  public static final String WAP_3G = "3gwap";
  public static String appkey = "";
  private List<Activity> activityList = new LinkedList();
  private ArrayList<String> appInstalledList = new ArrayList();
  private String appsInfo = "";
  private int base_height;
  private int base_width;
  public boolean loadAd = false;
  private int screen_height;
  private int screen_width;
  
  private BBApplication() {}
  
  private void checkPermission(Activity paramActivity, String paramString)
  {
    if (!Settings.System.canWrite(paramActivity)) {
      paramActivity.startActivity(new Intent(paramString, Uri.parse("package:" + paramActivity.getPackageName())));
    }
  }
  
  public static boolean checkPluginIsExist(Activity paramActivity, String paramString)
  {
    try
    {
      Class.forName(paramString);
      return true;
    }
    catch (ClassNotFoundException paramActivity) {}
    return false;
  }
  
  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static String getAndroidID(Activity paramActivity)
  {
    return Settings.Secure.getString(paramActivity.getContentResolver(), "android_id");
  }
  
  public static String getIMEI(Activity paramActivity)
  {
    String str = "";
    if (!getInstance().isTablet(paramActivity)) {
      str = ((TelephonyManager)paramActivity.getSystemService("phone")).getDeviceId();
    }
    return str;
  }
  
  public static BBApplication getInstance()
  {
    try
    {
      BBApplication localBBApplication = BBApplicationHolder.INSTANCE;
      return localBBApplication;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static int getNavigationBarHeight(Window paramWindow)
  {
    int i = 0;
    int m = getUsedDisplayWidth(paramWindow);
    int j = getUsedDisplayHeight(paramWindow);
    int n = getRealDisplayWidth(paramWindow);
    int k = getRealDisplayHeight(paramWindow);
    if (m < n) {
      i = n - m;
    }
    while (j >= k) {
      return i;
    }
    return k - j;
  }
  
  public static int getRealDisplayHeight(Window paramWindow)
  {
    paramWindow = paramWindow.getWindowManager().getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    try
    {
      Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[] { DisplayMetrics.class }).invoke(paramWindow, new Object[] { localDisplayMetrics });
      int i = localDisplayMetrics.heightPixels;
      return i;
    }
    catch (Exception paramWindow)
    {
      paramWindow.printStackTrace();
    }
    return 0;
  }
  
  public static int getRealDisplayWidth(Window paramWindow)
  {
    paramWindow = paramWindow.getWindowManager().getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    try
    {
      Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[] { DisplayMetrics.class }).invoke(paramWindow, new Object[] { localDisplayMetrics });
      int i = localDisplayMetrics.widthPixels;
      return i;
    }
    catch (Exception paramWindow)
    {
      paramWindow.printStackTrace();
    }
    return 0;
  }
  
  public static String getUUID(Activity paramActivity)
  {
    String str2 = SharedPreUtil.getValue(paramActivity, "uuid");
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (str2.length() != 0) {}
    }
    else
    {
      str1 = UUID.randomUUID().toString();
      SharedPreUtil.setValue(paramActivity, "uuid", str1);
    }
    return str1;
  }
  
  public static int getUsedDisplayHeight(Window paramWindow)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramWindow.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public static int getUsedDisplayWidth(Window paramWindow)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramWindow.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  public static boolean hasNavigationBar(Window paramWindow)
  {
    int i = getUsedDisplayWidth(paramWindow);
    int j = getUsedDisplayHeight(paramWindow);
    int k = getRealDisplayWidth(paramWindow);
    int m = getRealDisplayHeight(paramWindow);
    return (i < k) || (j < m);
  }
  
  public static void hideNavigation(Activity paramActivity)
  {
    paramActivity = paramActivity.getWindow();
    if (Build.VERSION.SDK_INT >= 19) {}
    for (int i = 1;; i = 0)
    {
      if (i != 0) {
        paramActivity.getDecorView().setSystemUiVisibility(5894);
      }
      return;
    }
  }
  
  private boolean isFastMobileNetwork(Context paramContext)
  {
    switch (((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType())
    {
    case 0: 
    case 1: 
    case 2: 
    case 4: 
    case 7: 
    case 11: 
    default: 
      return false;
    case 5: 
      return true;
    case 6: 
      return true;
    case 8: 
      return true;
    case 10: 
      return true;
    case 9: 
      return true;
    case 3: 
      return true;
    case 14: 
      return true;
    case 12: 
      return true;
    case 15: 
      return true;
    }
    return true;
  }
  
  public static boolean isNavigationBarPositionOnBottom(Window paramWindow)
  {
    return getUsedDisplayHeight(paramWindow) < getRealDisplayHeight(paramWindow);
  }
  
  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static float scaleValue(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    float f2 = paramInt3 / paramInt1;
    float f3 = paramInt4 / paramInt2;
    float f1 = f3;
    if (f2 < f3) {
      f1 = f2;
    }
    return f1;
  }
  
  public void MIUI6Style(Activity paramActivity)
  {
    paramActivity = paramActivity.getWindow();
    Object localObject = paramActivity.getClass();
    try
    {
      Class localClass = Class.forName("android.view.MiuiWindowManager$LayoutParams");
      int i = localClass.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT").getInt(localClass);
      int j = localClass.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(localClass);
      localObject = ((Class)localObject).getMethod("setExtraFlags", new Class[] { Integer.TYPE, Integer.TYPE });
      ((Method)localObject).invoke(paramActivity, new Object[] { Integer.valueOf(i), Integer.valueOf(i) });
      ((Method)localObject).invoke(paramActivity, new Object[] { Integer.valueOf(i | j), Integer.valueOf(i | j) });
      ((Method)localObject).invoke(paramActivity, new Object[] { Integer.valueOf(0), Integer.valueOf(j) });
      return;
    }
    catch (NoSuchMethodException paramActivity)
    {
      paramActivity.printStackTrace();
      return;
    }
    catch (ClassNotFoundException paramActivity)
    {
      paramActivity.printStackTrace();
      return;
    }
    catch (NoSuchFieldException paramActivity)
    {
      paramActivity.printStackTrace();
      return;
    }
    catch (IllegalAccessException paramActivity)
    {
      paramActivity.printStackTrace();
      return;
    }
    catch (IllegalArgumentException paramActivity)
    {
      paramActivity.printStackTrace();
      return;
    }
    catch (InvocationTargetException paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public void addActivity(Activity paramActivity)
  {
    this.activityList.add(paramActivity);
  }
  
  public void bbref_UmengUpdate_requestUrl(String paramString1, String paramString2, BBUmeng paramBBUmeng)
    throws Exception
  {
    ReflectUtil.invokeMethod(ReflectUtil.newInstance("com.babybus.bbmodule.plugin.umengupdate.PluginUmengUpdate"), "requestUrl", new Object[] { paramString1, paramString2, paramBBUmeng });
  }
  
  public void collectInstalledApps(Activity paramActivity)
  {
    this.appsInfo = "[";
    paramActivity = paramActivity.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= paramActivity.size())
      {
        if (this.appsInfo.length() > 1) {
          this.appsInfo = this.appsInfo.substring(0, this.appsInfo.length() - 1);
        }
        this.appsInfo += "]";
        return;
      }
      PackageInfo localPackageInfo = (PackageInfo)paramActivity.get(i);
      int j = localPackageInfo.applicationInfo.flags;
      ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
      if (((j & 0x1) <= 0) && (localPackageInfo.packageName.startsWith("com.sinyee.babybus")))
      {
        this.appInstalledList.add(localPackageInfo.packageName);
        this.appsInfo = (this.appsInfo + localPackageInfo.packageName + ",");
      }
      i += 1;
    }
  }
  
  public boolean debugModel()
  {
    return SDCardUtil.checkDirExist(SDCardUtil.getSDPATH() + SDCardUtil.BABYBUS_PATH + "debug/");
  }
  
  public boolean downloadManagerIsEnabled(Context paramContext)
  {
    boolean bool = true;
    int i = paramContext.getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");
    if (Build.VERSION.SDK_INT > 18) {
      if ((i == 2) || (i == 3) || (i == 4)) {
        bool = false;
      }
    }
    while ((i != 2) && (i != 3)) {
      return bool;
    }
    return false;
  }
  
  public void exit()
  {
    Iterator localIterator = this.activityList.iterator();
    if (!localIterator.hasNext())
    {
      System.exit(0);
      return;
    }
    Activity localActivity = (Activity)localIterator.next();
    int i;
    if ("A005".equals(ReflectFrameworkConstUtil.getStaticPropertyS_S("UMENG_CHANNEL")))
    {
      if ((Build.VERSION.SDK_INT != 23) && (Build.VERSION.SDK_INT <= 23)) {
        break label99;
      }
      if (Settings.System.canWrite(localActivity))
      {
        i = SharedPreUtil.getValueInt(localActivity, "user_timeout");
        Settings.System.putInt(localActivity.getContentResolver(), "screen_off_timeout", i);
      }
    }
    for (;;)
    {
      localActivity.finish();
      break;
      label99:
      i = SharedPreUtil.getValueInt(localActivity, "user_timeout");
      Settings.System.putInt(localActivity.getContentResolver(), "screen_off_timeout", i);
    }
  }
  
  public String getAllApps()
  {
    return this.appsInfo;
  }
  
  public String getAllApps(Context paramContext)
  {
    Object localObject = "";
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (paramContext = (Context)localObject;; paramContext = (Context)localObject)
    {
      if (i >= localList.size()) {
        return paramContext.substring(0, paramContext.length() - 1);
      }
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      int j = localPackageInfo.applicationInfo.flags;
      localObject = localPackageInfo.applicationInfo;
      localObject = paramContext;
      if ((j & 0x1) <= 0) {
        localObject = paramContext + localPackageInfo.packageName + "|";
      }
      i += 1;
    }
  }
  
  public String getAppChannel()
  {
    try
    {
      String str = (String)ReflectUtil.getStaticProperty("com.sinyee.framework.constant.Const", "UMENG_CHANNEL");
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public String getAppKey(Activity paramActivity)
  {
    return appkey;
  }
  
  public String getAppVersion(Activity paramActivity)
  {
    try
    {
      paramActivity = paramActivity.getPackageManager().getPackageInfo(getAppKey(paramActivity), 0).versionName;
      return paramActivity;
    }
    catch (PackageManager.NameNotFoundException paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return "0.0";
  }
  
  public int getBase_height()
  {
    return this.base_height;
  }
  
  public int getBase_width()
  {
    return this.base_width;
  }
  
  public String getCacheKey(String paramString)
  {
    String str = null;
    if (paramString != null) {
      str = "media" + String.valueOf(paramString.hashCode());
    }
    return str;
  }
  
  public String getCarrier(Context paramContext)
  {
    String str1 = "";
    String str2 = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
    paramContext = str1;
    if (str2 != null)
    {
      if ((!str2.startsWith("46000")) && (!str2.startsWith("46002"))) {
        break label48;
      }
      paramContext = "46000";
    }
    label48:
    do
    {
      return paramContext;
      if (str2.startsWith("46001")) {
        return "46001";
      }
      if (str2.startsWith("46003")) {
        return "46003";
      }
      paramContext = str1;
    } while (!str2.startsWith("46020"));
    return "46020";
  }
  
  public String getChannel()
  {
    return ReflectFrameworkConstUtil.getStaticPropertyS_S("UMENG_CHANNEL").trim();
  }
  
  public String getConnectType(Context paramContext)
  {
    String str = "6";
    int i = getNetworkType(paramContext);
    if (i == 4) {
      paramContext = "2";
    }
    do
    {
      return paramContext;
      if ((i == 7) || (i == 8) || (i == 11) || (i == 12) || (i == 15) || (i == 16)) {
        return "4";
      }
      if ((i == 5) || (i == 6) || (i == 9) || (i == 10) || (i == 13) || (i == 14)) {
        return "5";
      }
      paramContext = str;
    } while (i != 0);
    return "0";
  }
  
  public String getDeviceMacAddress(Activity paramActivity)
  {
    return ((WifiManager)paramActivity.getSystemService("wifi")).getConnectionInfo().getMacAddress();
  }
  
  public String getDeviceModel()
  {
    return Build.MODEL;
  }
  
  public String getDeviceOperationVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public float getDevicePixelRatio(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels / localDisplayMetrics.density;
  }
  
  public String getDeviceVendor()
  {
    return Build.MANUFACTURER;
  }
  
  public String getKeyChain(String paramString)
  {
    try
    {
      paramString = (String)ReflectUtil.invokeStaticMethod("com.babybus.bbmodule.system.jni.PlatformSystem", "getKeyChain", new Object[] { paramString });
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public String getLanguage()
  {
    try
    {
      String str = (String)ReflectUtil.invokeStaticMethod("com.babybus.bbmodule.system.jni.PlatformSystem", "getLanguage");
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "en";
  }
  
  public String getLat(Context paramContext)
  {
    String str = "";
    Location localLocation = getLocationInfo(paramContext);
    paramContext = str;
    if (localLocation != null) {
      paramContext = BBStringUtil.interceptionString(String.valueOf(localLocation.getLatitude()), 5);
    }
    return paramContext;
  }
  
  public Location getLocationInfo(Context paramContext)
  {
    String str = "";
    LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
    List localList = localLocationManager.getProviders(true);
    if (localList.contains("gps")) {
      paramContext = "gps";
    }
    while ((paramContext != null) && (!"".equals(paramContext)))
    {
      return localLocationManager.getLastKnownLocation(paramContext);
      paramContext = str;
      if (localList.contains("network")) {
        paramContext = "network";
      }
    }
    return null;
  }
  
  public String getLon(Context paramContext)
  {
    String str = "";
    Location localLocation = getLocationInfo(paramContext);
    paramContext = str;
    if (localLocation != null) {
      paramContext = BBStringUtil.interceptionString(String.valueOf(localLocation.getLongitude()), 5);
    }
    return paramContext;
  }
  
  public String getMD5(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("md5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuilder();
      int i = 0;
      for (;;)
      {
        if (i >= paramString.length) {
          return ((StringBuilder)localObject).toString().toLowerCase();
        }
        ((StringBuilder)localObject).append(String.format("%02X", new Object[] { Byte.valueOf(paramString[i]) }));
        i += 1;
      }
      return "";
    }
    catch (Exception paramString) {}
  }
  
  public int getNetworkType(Context paramContext)
  {
    boolean bool1;
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localNetworkInfo == null) || (!localNetworkInfo.isAvailable())) {
        break label255;
      }
      int i = localNetworkInfo.getType();
      if (i == 1) {
        return 4;
      }
      if (i == 0)
      {
        bool1 = isFastMobileNetwork(paramContext);
        paramContext = paramContext.getContentResolver().query(PREFERRED_APN_URI, null, null, null, null);
        if (paramContext != null)
        {
          paramContext.moveToFirst();
          String str = paramContext.getString(paramContext.getColumnIndex("user"));
          if (!TextUtils.isEmpty(str))
          {
            if (str.startsWith("ctwap"))
            {
              if (!bool1) {
                break label257;
              }
              return 5;
            }
            if (str.startsWith("ctnet"))
            {
              if (!bool1) {
                break label260;
              }
              return 6;
            }
          }
          paramContext.close();
        }
        paramContext = localNetworkInfo.getExtraInfo();
        if (paramContext != null)
        {
          paramContext = paramContext.toLowerCase();
          if (paramContext.equals("cmwap"))
          {
            if (!bool1) {
              break label263;
            }
            return 9;
          }
          if (paramContext.equals("cmnet"))
          {
            if (!bool1) {
              break label266;
            }
            return 10;
          }
          if ((paramContext.equals("3gnet")) || (paramContext.equals("uninet"))) {
            break label269;
          }
          if (!paramContext.equals("3gwap"))
          {
            boolean bool2 = paramContext.equals("uniwap");
            if (!bool2) {}
          }
          else
          {
            if (bool1) {
              return 13;
            }
            return 15;
          }
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return 17;
    }
    return 17;
    label255:
    return 0;
    label257:
    return 7;
    label260:
    return 8;
    label263:
    return 11;
    label266:
    return 12;
    label269:
    if (bool1) {
      return 14;
    }
    return 16;
  }
  
  public int getSDCardAllSize()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return (int)(localStatFs.getBlockCount() * l / 1024L / 1024L);
  }
  
  public int getSDCardAvailaleSize()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return (int)(localStatFs.getAvailableBlocks() * l / 1024L / 1024L);
  }
  
  public String getScreenHeight(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public String getScreenSize(Context paramContext)
  {
    return getScreenWidth(paramContext) + "X" + getScreenHeight(paramContext);
  }
  
  public String getScreenWidth(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  public int getScreen_height()
  {
    return this.screen_height;
  }
  
  public int getScreen_width()
  {
    return this.screen_width;
  }
  
  public String getVersion(Context paramContext)
    throws PackageManager.NameNotFoundException
  {
    return paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
  }
  
  public int getVersionCode(Context paramContext)
    throws PackageManager.NameNotFoundException
  {
    return paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
  }
  
  public boolean hasAnyMarketInstalled(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setData(Uri.parse("market://details?id=android.browser"));
    return paramContext.getPackageManager().queryIntentActivities(localIntent, 65536).size() != 0;
  }
  
  public boolean hasAppInstalled(Context paramContext, String paramString)
  {
    boolean bool = false;
    Object localObject = null;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = localObject;
      }
    }
  }
  
  public boolean isAppInstalled(String paramString)
  {
    return (this.appInstalledList != null) && (this.appInstalledList.size() > 0) && (this.appInstalledList.contains(paramString));
  }
  
  public boolean isTablet(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public void launchApp(Activity paramActivity, String paramString, boolean paramBoolean)
  {
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).setPackage(paramString);
    localObject = (ResolveInfo)paramActivity.getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator().next();
    if (localObject != null)
    {
      paramString = ((ResolveInfo)localObject).activityInfo.packageName;
      localObject = ((ResolveInfo)localObject).activityInfo.name;
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addFlags(268435456);
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setComponent(new ComponentName(paramString, (String)localObject));
      paramActivity.startActivity(localIntent);
    }
  }
  
  public void openBabybusBrowser(Activity paramActivity, String paramString, int paramInt)
  {
    Intent localIntent = new Intent(paramActivity, BBWebActivity.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("url", paramString);
    localIntent.putExtras(localBundle);
    paramActivity.startActivityForResult(localIntent, paramInt);
    paramActivity.overridePendingTransition(17432576, 17432577);
  }
  
  public void openBrowser(Activity paramActivity, String paramString, int paramInt)
  {
    if ((paramString != null) && (!"".equals(paramString)))
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addFlags(268435456);
      localIntent.setData(Uri.parse(paramString));
      paramActivity.startActivityForResult(localIntent, paramInt);
    }
  }
  
  public void openMarket(Context paramContext, String paramString)
  {
    paramString = Uri.parse("market://details?id=" + paramString.trim());
    if (getInstance().hasAnyMarketInstalled(paramContext))
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", paramString));
      return;
    }
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setData(paramString);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramString)
    {
      Toast.makeText(paramContext, BBResources.getIdentifier(paramContext, "bb_network_not_available", "string"), 0).show();
    }
  }
  
  public void openPublisherMarket(Context paramContext, String paramString, int paramInt)
  {
    Activity localActivity = (Activity)paramContext;
    paramString = Uri.parse("market://search?q=pub:" + paramString);
    if (getInstance().hasAnyMarketInstalled(paramContext))
    {
      localActivity.startActivityForResult(new Intent("android.intent.action.VIEW", paramString), paramInt);
      return;
    }
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setData(paramString);
      paramContext.startActivity(localIntent);
      localActivity.startActivityForResult(localIntent, paramInt);
      return;
    }
    catch (Exception paramString)
    {
      Toast.makeText(paramContext, BBResources.getIdentifier(paramContext, "bb_network_not_available", "string"), 0).show();
    }
  }
  
  public void openSearchMarket(Context paramContext, String paramString, int paramInt)
  {
    Activity localActivity = (Activity)paramContext;
    paramString = Uri.parse("market://search?q=" + paramString + "&c=apps");
    if (getInstance().hasAnyMarketInstalled(paramContext))
    {
      localActivity.startActivityForResult(new Intent("android.intent.action.VIEW", paramString), paramInt);
      return;
    }
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setData(paramString);
      localActivity.startActivityForResult(localIntent, paramInt);
      return;
    }
    catch (Exception paramString)
    {
      Toast.makeText(paramContext, BBResources.getIdentifier(paramContext, "bb_network_not_available", "string"), 0).show();
    }
  }
  
  public void setBaseResolution(int paramInt1, int paramInt2)
  {
    this.base_width = paramInt1;
    this.base_height = paramInt2;
  }
  
  public void setBottomDialogTheme(AlertDialog paramAlertDialog)
  {
    Window localWindow = paramAlertDialog.getWindow();
    localWindow.setGravity(80);
    paramAlertDialog = paramAlertDialog.getWindow().getAttributes();
    paramAlertDialog.width = -1;
    localWindow.setAttributes(paramAlertDialog);
  }
  
  public void setKeyChain(String paramString1, String paramString2)
  {
    try
    {
      ReflectUtil.invokeStaticMethod("com.babybus.bbmodule.system.jni.PlatformSystem", "setKeyChain", new Object[] { paramString1, paramString2 });
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public void setScreenResolution(int paramInt1, int paramInt2)
  {
    this.screen_width = paramInt1;
    this.screen_height = paramInt2;
  }
  
  public void showContinueCancelDialog(Context paramContext, int paramInt, String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    paramContext.setCancelable(true);
    paramContext.setIcon(paramInt);
    paramContext.setTitle(paramString1);
    paramContext.setMessage(paramString2);
    paramContext.setInverseBackgroundForced(true);
    paramContext.setPositiveButton("Continue", paramOnClickListener1);
    paramContext.setNegativeButton("Cancel", paramOnClickListener2);
    paramContext.create().show();
  }
  
  private static class BBApplicationHolder
  {
    private static final BBApplication INSTANCE = new BBApplication(null);
    
    private BBApplicationHolder() {}
  }
}
