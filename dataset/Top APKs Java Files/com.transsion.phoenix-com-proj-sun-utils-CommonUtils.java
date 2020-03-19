package com.proj.sun.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import com.proj.sun.SunApp;
import com.proj.sun.dialog.c.a;
import com.transsion.api.utils.SPUtils;
import com.transsion.api.utils.a;
import com.transsion.api.widget.TLog;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

public class CommonUtils
{
  private static final List<Integer> bco = new CommonUtils.1();
  static JSONObject bcp = null;
  private static long bcq = 0L;
  
  public CommonUtils() {}
  
  public static String MD5(String paramString)
  {
    for (;;)
    {
      StringBuilder localStringBuilder;
      int i;
      try
      {
        paramString = MessageDigest.getInstance("md5").digest(paramString.getBytes());
        localStringBuilder = new StringBuilder();
        int j = paramString.length;
        i = 0;
        if (i < j)
        {
          int k = paramString[i] & 0xFF;
          if ((k < 16) && (k >= 0)) {
            localStringBuilder.append("0" + Integer.toHexString(k));
          } else {
            localStringBuilder.append(Integer.toHexString(k));
          }
        }
      }
      catch (NoSuchAlgorithmException paramString)
      {
        paramString.printStackTrace();
        return "";
      }
      paramString = localStringBuilder.toString();
      return paramString;
      i += 1;
    }
  }
  
  public static boolean canClick()
  {
    if (Math.abs(System.currentTimeMillis() - bcq) < 1000L) {
      return false;
    }
    bcq = System.currentTimeMillis();
    return true;
  }
  
  public static boolean canClick(long paramLong)
  {
    if (Math.abs(System.currentTimeMillis() - bcq) < paramLong) {
      return false;
    }
    bcq = System.currentTimeMillis();
    return true;
  }
  
  /* Error */
  public static boolean canShowRecommend()
  {
    // Byte code:
    //   0: invokestatic 98	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   3: invokevirtual 101	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   6: astore_2
    //   7: invokestatic 104	com/proj/sun/utils/CommonUtils:getCountryCode	()Ljava/lang/String;
    //   10: astore_3
    //   11: getstatic 22	com/proj/sun/utils/CommonUtils:bcp	Lorg/json/JSONObject;
    //   14: ifnonnull +15 -> 29
    //   17: new 106	org/json/JSONObject
    //   20: dup
    //   21: ldc 108
    //   23: invokespecial 111	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   26: putstatic 22	com/proj/sun/utils/CommonUtils:bcp	Lorg/json/JSONObject;
    //   29: aload_2
    //   30: ifnull +114 -> 144
    //   33: aload_3
    //   34: ifnull +110 -> 144
    //   37: getstatic 22	com/proj/sun/utils/CommonUtils:bcp	Lorg/json/JSONObject;
    //   40: ifnull +104 -> 144
    //   43: getstatic 22	com/proj/sun/utils/CommonUtils:bcp	Lorg/json/JSONObject;
    //   46: invokevirtual 115	org/json/JSONObject:keys	()Ljava/util/Iterator;
    //   49: astore 4
    //   51: aload 4
    //   53: invokeinterface 120 1 0
    //   58: ifeq +86 -> 144
    //   61: aload 4
    //   63: invokeinterface 124 1 0
    //   68: checkcast 40	java/lang/String
    //   71: astore 5
    //   73: aload_3
    //   74: aload 5
    //   76: invokevirtual 128	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   79: ifeq -28 -> 51
    //   82: getstatic 22	com/proj/sun/utils/CommonUtils:bcp	Lorg/json/JSONObject;
    //   85: aload 5
    //   87: invokevirtual 132	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   90: astore 5
    //   92: iconst_0
    //   93: istore_0
    //   94: iload_0
    //   95: aload 5
    //   97: invokevirtual 138	org/json/JSONArray:length	()I
    //   100: if_icmpge -49 -> 51
    //   103: aload_2
    //   104: aload 5
    //   106: iload_0
    //   107: invokevirtual 141	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   110: invokevirtual 128	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   113: istore_1
    //   114: iload_1
    //   115: ifeq +12 -> 127
    //   118: iconst_1
    //   119: ireturn
    //   120: astore_2
    //   121: aload_2
    //   122: invokestatic 147	com/transsion/api/widget/TLog:e	(Ljava/lang/Throwable;)V
    //   125: iconst_0
    //   126: ireturn
    //   127: iload_0
    //   128: iconst_1
    //   129: iadd
    //   130: istore_0
    //   131: goto -37 -> 94
    //   134: astore 5
    //   136: aload 5
    //   138: invokestatic 147	com/transsion/api/widget/TLog:e	(Ljava/lang/Throwable;)V
    //   141: goto -90 -> 51
    //   144: iconst_0
    //   145: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   93	38	0	i	int
    //   113	2	1	bool	boolean
    //   6	98	2	str1	String
    //   120	2	2	localJSONException1	org.json.JSONException
    //   10	64	3	str2	String
    //   49	13	4	localIterator	Iterator
    //   71	34	5	localObject	Object
    //   134	3	5	localJSONException2	org.json.JSONException
    // Exception table:
    //   from	to	target	type
    //   17	29	120	org/json/JSONException
    //   82	92	134	org/json/JSONException
    //   94	114	134	org/json/JSONException
  }
  
  public static boolean checkSystemGDPR(Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    return (Settings.Global.getInt(paramContext, "os_supreme_user_experience", 0) != 0) || (1 == Settings.System.getInt(paramContext, "user_experience", 0));
  }
  
  public static String formatWithEn(String paramString)
  {
    try
    {
      double d = Double.valueOf(paramString).doubleValue();
      if (d <= 0.0D) {
        return "0";
      }
      paramString = new DecimalFormat("#,###.#");
      int i = (int)StrictMath.log10(d);
      paramString = paramString.format(d / Math.pow(10.0D, i / 3 * 3));
      String str = paramString + " KMBT".charAt(i / 3);
      paramString = str;
      if (str.length() > 4)
      {
        paramString = str.replaceAll("\\.[0-9]+", "");
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      TLog.e(paramString);
      paramString = "";
    }
    return paramString;
  }
  
  public static String getAcString()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)SunApp.uX().getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo != null) {
      return localNetworkInfo.getTypeName();
    }
    return "null";
  }
  
  public static String getAndroidID()
  {
    return Settings.Secure.getString(SunApp.uX().getContentResolver(), "android_id");
  }
  
  public static long getAvailSpace(String paramString)
  {
    paramString = new StatFs(paramString);
    long l = paramString.getBlockSize();
    return paramString.getAvailableBlocks() * l;
  }
  
  public static long getAvailableSize()
  {
    try
    {
      long l = getAvailSpace(Environment.getDataDirectory().getAbsolutePath()) / 1024L / 1024L;
      Log.e("gsk1", "romSize--" + l);
      return l;
    }
    catch (Exception localException) {}
    return 0L;
  }
  
  public static String getCountryCode()
  {
    String str = SPUtils.getString("country_custom_key", null);
    if (str != null) {
      return str.toUpperCase();
    }
    str = ((TelephonyManager)SunApp.uX().getSystemService("phone")).getSimCountryIso();
    if ((str == null) || (str.length() == 0)) {
      return Locale.getDefault().getCountry().toUpperCase();
    }
    return str.toUpperCase();
  }
  
  public static String getLocalIconAbsolutePath(String paramString)
  {
    paramString = getLocalIconRelativePath(paramString);
    if (paramString.startsWith("icon/")) {
      return "file:///android_asset/" + paramString;
    }
    return "";
  }
  
  public static String getLocalIconRelativePath(String paramString)
  {
    int i = 0;
    Object localObject = Uri.parse(paramString);
    paramString = ((Uri)localObject).getHost();
    if (paramString == null) {
      return "";
    }
    if ((((Uri)localObject).getPath() != null) && (((Uri)localObject).getPath().equals("/gmail"))) {
      return "icon/gmail.com.png";
    }
    try
    {
      localObject = SunApp.uX().getAssets().list("icon");
      if (localObject != null)
      {
        int j = localObject.length;
        while (i < j)
        {
          String str = localObject[i];
          if ((str != null) && (str.length() > 4) && (paramString.endsWith(str.substring(0, str.length() - 4))))
          {
            paramString = "icon/" + str;
            return paramString;
          }
          i += 1;
        }
      }
      return "";
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return "";
    }
  }
  
  public static String getLocaleString()
  {
    return Locale.getDefault().getLanguage() + "_" + getCountryCode();
  }
  
  public static String getMcc()
  {
    String str = ((TelephonyManager)SunApp.uX().getSystemService("phone")).getSimOperator();
    if ((str != null) && (str.length() > 3)) {
      return str.substring(0, 3);
    }
    return null;
  }
  
  public static String getNetworkType()
  {
    Object localObject1 = ((ConnectivityManager)SunApp.uX().getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localObject1 != null) && (((NetworkInfo)localObject1).isConnected())) {
      if (((NetworkInfo)localObject1).getType() == 1) {
        localObject1 = "network_wifi";
      }
    }
    for (;;)
    {
      Object localObject2 = localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        localObject2 = "network_no";
      }
      return localObject2;
      if (((NetworkInfo)localObject1).getType() == 0)
      {
        localObject2 = ((NetworkInfo)localObject1).getSubtypeName();
        switch (((NetworkInfo)localObject1).getSubtype())
        {
        default: 
          if ((!((String)localObject2).equalsIgnoreCase("TD-SCDMA")) && (!((String)localObject2).equalsIgnoreCase("WCDMA")))
          {
            localObject1 = localObject2;
            if (!((String)localObject2).equalsIgnoreCase("CDMA2000")) {
              continue;
            }
          }
          localObject1 = "network_3g/2g";
          break;
        case 1: 
        case 2: 
        case 4: 
        case 7: 
        case 11: 
          localObject1 = "network_3g/2g";
          break;
        case 3: 
        case 5: 
        case 6: 
        case 8: 
        case 9: 
        case 10: 
        case 12: 
        case 14: 
        case 15: 
          localObject1 = "network_3g/2g";
          break;
        case 13: 
          localObject1 = "network_4g";
          break;
        }
      }
      else
      {
        localObject1 = "";
      }
    }
  }
  
  public static String getRecommendStr()
  {
    return SunApp.uX().getResources().getString(2131755223);
  }
  
  public static boolean getScreenOrientation(Activity paramActivity)
  {
    int i = paramActivity.getResources().getConfiguration().orientation;
    if (i == 2) {
      return true;
    }
    return i != 1;
  }
  
  public static int getVersionCode()
  {
    try
    {
      int i = SunApp.uX().getPackageManager().getPackageInfo(SunApp.uX().getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return -1;
  }
  
  public static String getVersionName()
  {
    try
    {
      String str = SunApp.uX().getPackageManager().getPackageInfo(SunApp.uX().getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return null;
  }
  
  public static boolean hasVersion()
  {
    try
    {
      Class localClass = SunApp.uX().getClassLoader().loadClass("com.proj.sun.utils.VersionUtils");
      Method localMethod = localClass.getDeclaredMethod("hasVersion", new Class[0]);
      localMethod.setAccessible(true);
      bool = ((Boolean)localMethod.invoke(localClass, new Object[0])).booleanValue();
      TLog.i("Remote hasVersion : " + bool, new Object[0]);
      return bool;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        TLog.i("hasVersion exception: " + localException.toString(), new Object[0]);
        boolean bool = false;
      }
    }
  }
  
  public static void hideSystemKeyBoard(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static boolean isAr()
  {
    return Locale.getDefault().getLanguage().contains("ar");
  }
  
  public static boolean isClientAvailable(String paramString)
  {
    try
    {
      List localList = SunApp.uX().getPackageManager().getInstalledPackages(0);
      if (localList != null)
      {
        int i = 0;
        while (i < localList.size())
        {
          boolean bool = ((PackageInfo)localList.get(i)).packageName.equalsIgnoreCase(paramString);
          if (bool) {
            return true;
          }
          i += 1;
        }
      }
      return false;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static boolean isEU()
  {
    int j = SunApp.uX().getResources().getConfiguration().mcc;
    int i = j;
    String str;
    if (j == 0)
    {
      str = SPUtils.getString("country_custom_key", "");
      i = j;
      if (TextUtils.isEmpty(str)) {}
    }
    try
    {
      i = Integer.parseInt(str);
      return bco.contains(Integer.valueOf(i));
    }
    catch (Exception localException)
    {
      for (;;)
      {
        i = 0;
      }
    }
  }
  
  public static boolean isEn()
  {
    return SunApp.uX().getResources().getConfiguration().locale.getLanguage().contains("en");
  }
  
  public static boolean isGoogleRevenueVersion()
  {
    boolean bool2 = false;
    int i = SPUtils.getInt("install_version_code", Integer.valueOf(0)).intValue();
    String str = SystemProperties.get("ro.com.google.clientidbase", null);
    boolean bool1;
    if (i != 0)
    {
      bool1 = bool2;
      if (i != a.getVersionCode()) {}
    }
    else if ((!"android-transsion-tecno-rev1".equals(str)) && (!"android-transsion-tecno-rev2".equals(str)) && (!"android-transsion-infinix-rev1".equals(str)) && (!"android-transsion-infinix-rev2".equals(str)) && (!"android-transsion-itel-rev1".equals(str)) && (!"android-transsion-itel-rev2".equals(str)) && (!"android-transsion-spice-rev1".equals(str)) && (!"android-transsion-spice-rev2".equals(str)) && (!"android-transsion-spbu-rev1".equals(str)))
    {
      bool1 = bool2;
      if (!"android-transsion-spbu-rev2".equals(str)) {}
    }
    else
    {
      bool1 = true;
    }
    return bool1;
  }
  
  public static boolean isHindiLanguage()
  {
    return SunApp.uX().getResources().getConfiguration().locale.getLanguage().contains("hi");
  }
  
  public static boolean isHotWordFlash()
  {
    return SPUtils.getBoolean("hotword_flash", Boolean.valueOf(true)).booleanValue();
  }
  
  public static boolean isIndiaVersion()
  {
    return "IN".equalsIgnoreCase(SystemProperties.get("persist.sys.oobe_country", null));
  }
  
  public static boolean isMainProcess(Context paramContext)
  {
    for (;;)
    {
      try
      {
        int i = Process.myPid();
        localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
        if (((Iterator)localObject).hasNext())
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
          if (localRunningAppProcessInfo.pid != i) {
            continue;
          }
          localObject = localRunningAppProcessInfo.processName;
          boolean bool = ((String)localObject).equals(paramContext.getPackageName());
          return bool;
        }
      }
      catch (Exception paramContext)
      {
        TLog.e(paramContext);
        return false;
      }
      Object localObject = "";
    }
  }
  
  public static boolean isNotchScreen()
  {
    boolean bool = false;
    try
    {
      int i = Integer.parseInt(SystemProperties.get("os.notch.support", String.valueOf(0)));
      if (i > 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean isOpVersion()
  {
    return !com.transsion.api.utils.c.cb(SystemProperties.get("ro.phx.op.type", null));
  }
  
  public static boolean isOutVersion()
  {
    try
    {
      if (SPUtils.getInt("channel_type").intValue() > 0)
      {
        int i = SPUtils.getInt("install_version_code").intValue();
        if (i > 1484) {
          return true;
        }
      }
      return false;
    }
    catch (Exception localException) {}
    return true;
  }
  
  public static boolean isPreInstallIncludeAllVersion()
  {
    return SPUtils.getInt("channel_type").intValue() < 0;
  }
  
  public static boolean isPreInstallVersion()
  {
    boolean bool2 = false;
    int i = SPUtils.getInt("install_version_code", Integer.valueOf(0)).intValue();
    boolean bool1;
    if (i != 0)
    {
      bool1 = bool2;
      if (i != a.getVersionCode()) {}
    }
    else
    {
      bool1 = bool2;
      if (SPUtils.getInt("channel_type").intValue() < 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean isRU()
  {
    boolean bool2 = false;
    int i = SPUtils.getInt("install_version_code", Integer.valueOf(0)).intValue();
    boolean bool1;
    if (i != 0)
    {
      bool1 = bool2;
      if (i != a.getVersionCode()) {}
    }
    else
    {
      bool1 = bool2;
      if (SunApp.uX().getPackageManager().hasSystemFeature("com.google.android.feature.RU")) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean isTablet()
  {
    return (SunApp.uX().getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static boolean isTwoInCountry()
  {
    int i = SunApp.uX().getResources().getConfiguration().mcc;
    if (i == 0)
    {
      str = Locale.getDefault().getCountry();
      if ((!TextUtils.equals("ID", str)) && (!TextUtils.equals("IN", str))) {}
    }
    else
    {
      while ((i == 404) || (i == 405) || (i == 406) || (i == 510))
      {
        String str;
        return true;
      }
    }
    return false;
  }
  
  public static boolean isValidUrl(String paramString)
  {
    try
    {
      new URL(paramString);
      return true;
    }
    catch (MalformedURLException paramString) {}
    return false;
  }
  
  public static void randomList(List paramList)
  {
    if (paramList == null) {
      return;
    }
    try
    {
      Collections.shuffle(paramList);
      return;
    }
    catch (UnsupportedOperationException paramList)
    {
      TLog.e(paramList);
    }
  }
  
  public static void setBrightness(Activity paramActivity, int paramInt)
  {
    WindowManager.LayoutParams localLayoutParams = paramActivity.getWindow().getAttributes();
    if (paramInt <= 0) {}
    for (float f = -1.0F;; f = paramInt / 255.0F)
    {
      localLayoutParams.screenBrightness = f;
      paramActivity.getWindow().setAttributes(localLayoutParams);
      return;
    }
  }
  
  public static void setHotWordFlash(boolean paramBoolean)
  {
    SPUtils.put("hotword_flash", Boolean.valueOf(paramBoolean));
  }
  
  public static void shareDownload(Context paramContext, String paramString1, String paramString2, String paramString3, c.a paramA)
  {
    com.proj.sun.dialog.c.a(paramContext, paramString1, paramString2, paramString3, paramA);
  }
  
  public static void shareImage(Context paramContext, String paramString1, String paramString2)
  {
    com.proj.sun.dialog.c.f(paramContext, paramString1, paramString2);
  }
  
  public static void shareImage(Context paramContext, String paramString1, String paramString2, c.a paramA)
  {
    com.proj.sun.dialog.c.a(paramContext, paramString1, paramString2, paramA);
  }
  
  public static void shareText(Context paramContext, String paramString1, String paramString2)
  {
    com.proj.sun.dialog.c.g(paramContext, paramString1, paramString2);
  }
  
  public static void showSystemKeyBoard(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).toggleSoftInput(0, 2);
  }
}
