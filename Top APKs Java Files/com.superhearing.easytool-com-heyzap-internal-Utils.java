package com.heyzap.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.util.Log;
import com.heyzap.a.c.d;
import com.heyzap.sdk.ads.HeyzapAds;
import com.heyzap.sdk.ads.HeyzapAds.AdsConfig;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils
{
  public static Pattern a = Pattern.compile("^([0-9]+)\\.([0-9]+)\\.([0-9]+)(?:-([0-9A-Za-z-]+(?:\\.[0-9A-Za-z-]+)*))?(?:\\+[0-9A-Za-z-]+)?$");
  private static float b = -1.0F;
  private static Boolean c;
  private static String d = "unknown";
  private static String e = "unknown";
  private static HashMap<String, String> f;
  private static final Object g = new Object();
  private static Future<Boolean> h;
  private static String i = null;
  private static Boolean j = Boolean.valueOf(false);
  private static final Object k = new Object();
  public static String packageName = "unknown";
  
  public static int a(Context paramContext, int paramInt)
  {
    float f1;
    if (b > 0.0F) {
      f1 = b;
    } else {
      f1 = paramContext.getResources().getDisplayMetrics().density;
    }
    b = f1;
    return (int)(paramInt * b + 0.5F);
  }
  
  public static Boolean a(Activity paramActivity)
  {
    Iterator localIterator = paramActivity.getPackageManager().getInstalledPackages(128).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.equals(b(paramActivity)))
      {
        paramActivity = localPackageInfo.applicationInfo;
        if (paramActivity != null)
        {
          paramActivity = paramActivity.metaData;
          if ((paramActivity != null) && (paramActivity.containsKey("com.google.android.gms.version"))) {
            return Boolean.valueOf(true);
          }
        }
        return Boolean.valueOf(false);
      }
    }
    return Boolean.valueOf(false);
  }
  
  public static Boolean a(Long paramLong, Integer paramInteger)
  {
    if (paramInteger.intValue() < System.currentTimeMillis() - paramLong.longValue()) {}
    for (boolean bool = true;; bool = false) {
      return Boolean.valueOf(bool);
    }
  }
  
  public static Boolean a(String paramString1, String paramString2)
  {
    if (b(paramString1).booleanValue()) {}
    try
    {
      paramString1 = Class.forName(paramString1).getMethods();
    }
    catch (ClassNotFoundException paramString1)
    {
      int m;
      for (;;) {}
    }
    paramString1 = new Method[0];
    m = 0;
    while (m < paramString1.length)
    {
      if (paramString2.equals(paramString1[m].getName())) {
        return Boolean.valueOf(true);
      }
      m += 1;
    }
    return Boolean.valueOf(false);
  }
  
  private static Double a(int paramInt1, int paramInt2, int paramInt3)
  {
    double d1 = paramInt1;
    double d2 = Math.pow(1000.0D, 2.0D);
    Double.isNaN(d1);
    double d3 = paramInt2 * 1000;
    Double.isNaN(d3);
    double d4 = paramInt3;
    Double.isNaN(d4);
    return Double.valueOf(d1 * d2 + d3 + d4);
  }
  
  public static String a(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (paramString != null)) {
      return String.format("%s/%s", new Object[] { h(paramContext), paramString });
    }
    return null;
  }
  
  private static String a(Class paramClass, String paramString1, String paramString2)
  {
    try
    {
      paramClass = paramClass.getDeclaredField(paramString1).get(null).toString();
      return paramClass;
    }
    catch (NoSuchFieldException|IllegalAccessException paramClass)
    {
      for (;;) {}
    }
    return paramString2;
  }
  
  public static String a(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramString1 = a(Class.forName(paramString1), paramString2, paramString3);
      return paramString1;
    }
    catch (ClassNotFoundException paramString1) {}
    return paramString3;
  }
  
  public static String a(Map<String, String> paramMap)
  {
    if (paramMap == null) {
      return null;
    }
    localJSONObject = new JSONObject();
    try
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if ((localEntry != null) && (localEntry.getKey() != null))
        {
          String str = (String)localEntry.getValue();
          paramMap = str;
          if (str == null) {
            paramMap = JSONObject.NULL;
          }
          localJSONObject.put((String)localEntry.getKey(), paramMap);
        }
      }
      return localJSONObject.toString();
    }
    catch (JSONException paramMap)
    {
      paramMap.printStackTrace();
    }
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = String.format("%032x", new Object[] { new BigInteger(1, MessageDigest.getInstance("MD5").digest(paramArrayOfByte)) });
      return paramArrayOfByte;
    }
    catch (NoSuchAlgorithmException paramArrayOfByte)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static void a(Context paramContext)
  {
    j(paramContext);
    k(paramContext);
  }
  
  public static void a(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int n = arrayOfFile.length;
      int m = 0;
      while (m < n)
      {
        a(arrayOfFile[m]);
        m += 1;
      }
    }
    paramFile.delete();
  }
  
  public static void a(Boolean paramBoolean)
  {
    j = paramBoolean;
  }
  
  public static void a(String paramString)
  {
    i = paramString;
  }
  
  public static boolean a()
  {
    return (Build.MANUFACTURER.equals("Amazon")) || (HeyzapAds.config.store.equals("amazon"));
  }
  
  public static boolean a(Activity paramActivity, Class paramClass)
  {
    return new Intent(paramActivity, paramClass).resolveActivityInfo(paramActivity.getPackageManager(), 0) != null;
  }
  
  public static boolean a(Activity paramActivity, String paramString)
  {
    try
    {
      boolean bool = a(paramActivity, Class.forName(paramString));
      return bool;
    }
    catch (ClassNotFoundException paramActivity)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean a(Activity paramActivity, ArrayList<String> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      if (!b(paramActivity, (String)paramArrayList.next())) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean a(HeyzapAds.AdsConfig paramAdsConfig)
  {
    return (paramAdsConfig.flags & 0x8) != 0;
  }
  
  public static boolean a(HeyzapAds.AdsConfig paramAdsConfig, Constants.AdUnit paramAdUnit)
  {
    return (b(paramAdsConfig)) && (paramAdUnit != Constants.AdUnit.BANNER);
  }
  
  public static boolean a(String paramString, Context paramContext)
  {
    boolean bool2 = false;
    try
    {
      paramContext = paramContext.getPackageManager();
      paramString = paramContext.getLaunchIntentForPackage(paramString);
      boolean bool1 = bool2;
      if (paramString != null)
      {
        int m = paramContext.queryIntentActivities(paramString, 65536).size();
        bool1 = bool2;
        if (m > 0) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static int b()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static int b(Context paramContext, int paramInt)
  {
    float f1 = paramInt;
    if (b <= 0.0F) {
      b = paramContext.getResources().getDisplayMetrics().density;
    }
    return (int)(b * f1);
  }
  
  public static Boolean b(String paramString)
  {
    try
    {
      Class.forName(paramString);
      return Boolean.valueOf(true);
    }
    catch (ClassNotFoundException paramString)
    {
      for (;;) {}
    }
    return Boolean.valueOf(false);
  }
  
  public static String b(Context paramContext)
  {
    if ((packageName.equals("unknown")) && (paramContext != null))
    {
      String str = paramContext.getPackageName();
      paramContext = str;
      if (str.endsWith(".debug")) {
        paramContext = str.substring(0, str.length() - 6);
      }
      packageName = paramContext;
    }
    return packageName;
  }
  
  public static boolean b(Activity paramActivity, String paramString)
  {
    return paramActivity.getPackageManager().checkPermission(paramString, paramActivity.getPackageName()) == 0;
  }
  
  public static boolean b(HeyzapAds.AdsConfig paramAdsConfig)
  {
    return (paramAdsConfig.flags & 0x1) == 0;
  }
  
  public static boolean b(String paramString1, String paramString2)
  {
    try
    {
      Object localObject2 = a.matcher(paramString1);
      Object localObject1 = a.matcher(paramString2);
      if ((((Matcher)localObject2).matches()) && (((Matcher)localObject1).matches()))
      {
        paramString1 = Integer.valueOf(((Matcher)localObject2).group(1));
        paramString2 = Integer.valueOf(((Matcher)localObject2).group(2));
        localObject2 = Integer.valueOf(((Matcher)localObject2).group(3));
        Integer localInteger1 = Integer.valueOf(((Matcher)localObject1).group(1));
        Integer localInteger2 = Integer.valueOf(((Matcher)localObject1).group(2));
        localObject1 = Integer.valueOf(((Matcher)localObject1).group(3));
        int m = a(paramString1.intValue(), paramString2.intValue(), ((Integer)localObject2).intValue()).compareTo(a(localInteger1.intValue(), localInteger2.intValue(), ((Integer)localObject1).intValue()));
        if (m == 0) {
          break label188;
        }
        if (m == 1) {
          return true;
        }
      }
      else
      {
        localObject1 = new StringBuilder("Non semantic version provided: ");
        ((StringBuilder)localObject1).append(paramString1);
        ((StringBuilder)localObject1).append(" - ");
        ((StringBuilder)localObject1).append(paramString2);
        throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
      }
    }
    catch (IllegalArgumentException paramString1)
    {
      return false;
    }
    return false;
    label188:
    return true;
  }
  
  public static int c(Context paramContext, int paramInt)
  {
    float f1 = paramInt;
    if (b <= 0.0F) {
      b = paramContext.getResources().getDisplayMetrics().density;
    }
    return (int)(f1 / b);
  }
  
  public static Boolean c(Activity paramActivity, String paramString)
  {
    Iterator localIterator = paramActivity.getPackageManager().getInstalledPackages(2).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.equals(b(paramActivity)))
      {
        paramActivity = localPackageInfo.receivers;
        if (paramActivity != null)
        {
          int n = paramActivity.length;
          int m = 0;
          while (m < n)
          {
            if (paramActivity[m].name.equals(paramString)) {
              return Boolean.valueOf(true);
            }
            m += 1;
          }
        }
        return Boolean.valueOf(false);
      }
    }
    return Boolean.valueOf(false);
  }
  
  public static String c(Context paramContext)
  {
    try
    {
      if (h == null) {
        k(paramContext);
      }
      if (h != null) {
        h.get();
      }
      paramContext = i;
      return paramContext;
    }
    catch (InterruptedException|ExecutionException paramContext)
    {
      Logger.trace(paramContext);
    }
    return i;
  }
  
  public static Map<String, String> c(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    Object localObject1 = new HashMap();
    try
    {
      localObject2 = new JSONObject(paramString);
      Iterator localIterator = ((JSONObject)localObject2).keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        ((Map)localObject1).put(str, ((JSONObject)localObject2).getString(str));
      }
      return localObject1;
    }
    catch (JSONException localJSONException)
    {
      Object localObject2;
      for (;;) {}
    }
    localObject1 = Logger.TAG;
    localObject2 = new StringBuilder("unable to convert gdpr consent data: ");
    ((StringBuilder)localObject2).append(paramString);
    Log.e((String)localObject1, ((StringBuilder)localObject2).toString());
    return null;
  }
  
  public static boolean c(HeyzapAds.AdsConfig paramAdsConfig)
  {
    return (paramAdsConfig.flags & 0x40) != 0;
  }
  
  public static int d(Context paramContext, int paramInt)
  {
    if (paramInt <= 0) {
      return paramInt;
    }
    if (b <= 0.0F) {
      b = paramContext.getResources().getDisplayMetrics().density;
    }
    return (int)(b * paramInt);
  }
  
  public static Boolean d(Context paramContext)
  {
    try
    {
      if (h == null) {
        k(paramContext);
      }
      h.get();
      paramContext = j;
      return paramContext;
    }
    catch (InterruptedException|ExecutionException paramContext)
    {
      Logger.trace(paramContext);
    }
    return j;
  }
  
  public static boolean d(HeyzapAds.AdsConfig paramAdsConfig)
  {
    return (paramAdsConfig.flags & 0x20) != 0;
  }
  
  public static HashMap<String, String> e(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    for (;;)
    {
      synchronized (g)
      {
        if (f == null)
        {
          f = new HashMap();
          try
          {
            Integer localInteger = Integer.valueOf(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode);
          }
          catch (Exception localException1)
          {
            Logger.trace(localException1);
            localObject1 = Integer.valueOf(0);
          }
          f.put("app_version", String.valueOf(localObject1));
          paramContext.getResources().getDisplayMetrics();
          f.put("sdk_version", "9.52.0");
          f.put("android_version", Build.VERSION.SDK);
          f.put("external_package", b(paramContext));
          f.put("game_package", b(paramContext));
          localObject1 = f;
          if ((e.equals("unknown")) && (paramContext != null)) {
            e = paramContext.getPackageManager().getApplicationLabel(paramContext.getApplicationInfo()).toString();
          }
          ((HashMap)localObject1).put("app_name", e);
          if (!g(paramContext)) {
            break label982;
          }
          localObject1 = "tablet";
          f.put("device_form_factor", localObject1);
          f.put("device_model", Build.MODEL);
          f.put("device_type", Build.DEVICE);
          localObject1 = HeyzapAds.config.publisherId;
          if (localObject1 != null) {
            f.put("publisher_sdk_key", localObject1);
          }
          if (a())
          {
            f.put("platform", "amazon");
            localObject1 = f;
            str2 = "amazon";
            ((HashMap)localObject1).put("sdk_platform", str2);
          }
          else
          {
            f.put("platform", "android");
            localObject1 = f;
            str2 = "android";
            continue;
          }
        }
        localHashMap.putAll(f);
        if ((c(paramContext) != null) && (!a()))
        {
          localHashMap.put("device_id", c(paramContext));
          localHashMap.put("advertising_id", c(paramContext));
          str2 = "tracking_enabled";
          if (!d(paramContext).booleanValue()) {
            localObject1 = "1";
          } else {
            localObject1 = "0";
          }
        }
        else
        {
          str2 = "device_id";
          if ((d.equals("unknown")) && (paramContext != null))
          {
            localObject1 = Build.PRODUCT;
            ??? = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
            if ((localObject1 != null) && (??? != null))
            {
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append((String)localObject1);
              localStringBuilder.append("_");
              localStringBuilder.append((String)???);
              d = localStringBuilder.toString();
            }
          }
          localObject1 = d;
        }
        localHashMap.put(str2, localObject1);
        if (HeyzapAds.mediator != null) {
          localHashMap.put("sdk_mediator", HeyzapAds.mediator);
        }
        if (HeyzapAds.framework != null) {
          localHashMap.put("sdk_framework", HeyzapAds.framework);
        }
        try
        {
          localObject1 = new StatFs(Environment.getExternalStorageDirectory().getPath());
          localHashMap.put("device_free_bytes", Long.toString(((StatFs)localObject1).getBlockSize() * ((StatFs)localObject1).getAvailableBlocks()));
        }
        catch (Exception localException2)
        {
          int m;
          int n;
          continue;
        }
        localHashMap.put("device_free_bytes", "0");
        Object localObject1 = paramContext.getResources().getConfiguration().locale;
        if (localObject1 != null)
        {
          localHashMap.put("locale_country", ((Locale)localObject1).getCountry().toLowerCase(Locale.US));
          localHashMap.put("locale_lang", ((Locale)localObject1).getLanguage().toLowerCase(Locale.US));
        }
        ??? = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        String str2 = null;
        localObject1 = str2;
        if (??? != null)
        {
          m = ((NetworkInfo)???).getType();
          n = ((NetworkInfo)???).getSubtype();
          if (m == 1)
          {
            localObject1 = "wifi";
          }
          else
          {
            localObject1 = str2;
            if (m == 0) {
              switch (n)
              {
              default: 
                localObject1 = str2;
                break;
              case 15: 
                localObject1 = "hspap";
                break;
              case 14: 
                localObject1 = "ehrpd";
                break;
              case 13: 
                localObject1 = "lte";
                break;
              case 12: 
                localObject1 = "evdo_b";
                break;
              case 11: 
                localObject1 = "iden";
                break;
              case 10: 
                localObject1 = "hspa";
                break;
              case 9: 
                localObject1 = "hsupa";
                break;
              case 8: 
                localObject1 = "hsdpa";
                break;
              case 7: 
                localObject1 = "rtt";
                break;
              case 5: 
              case 6: 
                localObject1 = "evdo";
                break;
              case 4: 
                localObject1 = "cdma";
                break;
              case 3: 
                localObject1 = "umts";
                break;
              case 2: 
                localObject1 = "edge";
                break;
              case 1: 
                localObject1 = "gprs";
              }
            }
          }
        }
        localHashMap.put("connection_type", localObject1);
        paramContext = paramContext.getResources().getDisplayMetrics();
        localHashMap.put("device_dpi", Float.toString(paramContext.density));
        if (!localHashMap.containsKey("device_width")) {
          localHashMap.put("device_width", String.valueOf(paramContext.widthPixels));
        }
        if (!localHashMap.containsKey("device_height")) {
          localHashMap.put("device_height", String.valueOf(paramContext.heightPixels));
        }
        return localHashMap;
      }
      label982:
      String str1 = "phone";
    }
  }
  
  public static boolean f(Context paramContext)
  {
    Object localObject = (ActivityManager)paramContext.getSystemService("activity");
    paramContext = paramContext.getApplicationContext().getPackageName();
    try
    {
      localObject = ((ActivityManager)localObject).getRunningAppProcesses();
      int m = 0;
      while (m < ((List)localObject).size())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((List)localObject).get(m);
        if (localRunningAppProcessInfo.processName.equals(paramContext))
        {
          int n = localRunningAppProcessInfo.importance;
          if (n == 100) {
            return true;
          }
        }
        m += 1;
      }
      return false;
    }
    catch (Exception paramContext)
    {
      Logger.trace(paramContext);
    }
  }
  
  public static boolean g(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static String h(Context paramContext)
  {
    if (paramContext != null) {
      return String.format("%s/%s", new Object[] { paramContext.getCacheDir(), "com.heyzap.sdk" });
    }
    return null;
  }
  
  public static String i(Context paramContext)
  {
    if (paramContext != null) {
      return String.format("%s/%s", new Object[] { paramContext.getCacheDir(), "com.heyzap.sdk.images" });
    }
    return null;
  }
  
  public static Boolean isDebug(Context paramContext)
  {
    if (c == null) {
      setDebug(a(Constants.SNAKE_PACKAGE, paramContext));
    }
    return c;
  }
  
  public static void j(Context paramContext)
  {
    paramContext = new File(h(paramContext));
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
  }
  
  private static void k(Context paramContext)
  {
    synchronized (k)
    {
      if ((h == null) || (h.isDone()))
      {
        paramContext = new a(paramContext.getApplicationContext());
        h = d.a().submit(paramContext);
      }
      return;
    }
  }
  
  public static void setDebug(boolean paramBoolean)
  {
    c = Boolean.valueOf(paramBoolean);
    if (paramBoolean) {
      HeyzapAds.setThirdPartyVerboseLogging(true);
    }
  }
}
