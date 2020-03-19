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
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import com.heyzap.common.concurrency.ExecutorPool;
import com.heyzap.common.net.Connectivity;
import com.heyzap.internal.function.Function;
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
  private static final String CACHE_DIR = "com.heyzap.sdk";
  private static final String IMAGE_CACHE_DIR = "com.heyzap.sdk.images";
  public static Pattern SEMVER_PATTERN = Pattern.compile("^([0-9]+)\\.([0-9]+)\\.([0-9]+)(?:-([0-9A-Za-z-]+(?:\\.[0-9A-Za-z-]+)*))?(?:\\+[0-9A-Za-z-]+)?$");
  private static final Object advertiserIdLock = new Object();
  private static String advertisingId;
  private static Future<Boolean> advertisingIdAvailable;
  private static HashMap<String, String> cachedParams;
  private static Boolean debug;
  private static float density = -1.0F;
  private static String deviceId = "unknown";
  private static String gameName = "unknown";
  private static Boolean limitAdTrackingEnabled;
  public static String packageName = "unknown";
  private static final Object paramLock = new Object();
  
  static
  {
    advertisingId = null;
    limitAdTrackingEnabled = Boolean.valueOf(false);
  }
  
  public Utils() {}
  
  public static boolean activityExistsInPackage(Activity paramActivity, Class paramClass)
  {
    paramClass = new Intent(paramActivity, paramClass);
    paramActivity = paramActivity.getPackageManager();
    boolean bool = false;
    if (paramClass.resolveActivityInfo(paramActivity, 0) != null) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean activityExistsInPackage(Activity paramActivity, String paramString)
  {
    try
    {
      boolean bool = activityExistsInPackage(paramActivity, Class.forName(paramString));
      return bool;
    }
    catch (ClassNotFoundException paramActivity)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static Boolean classExists(String paramString)
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
  
  private static int compareVersions(String paramString1, String paramString2)
    throws IllegalArgumentException
  {
    Object localObject2 = SEMVER_PATTERN.matcher(paramString1);
    Object localObject1 = SEMVER_PATTERN.matcher(paramString2);
    if ((((Matcher)localObject2).matches()) && (((Matcher)localObject1).matches()))
    {
      paramString1 = Integer.valueOf(((Matcher)localObject2).group(1));
      paramString2 = Integer.valueOf(((Matcher)localObject2).group(2));
      localObject2 = Integer.valueOf(((Matcher)localObject2).group(3));
      Integer localInteger1 = Integer.valueOf(((Matcher)localObject1).group(1));
      Integer localInteger2 = Integer.valueOf(((Matcher)localObject1).group(2));
      localObject1 = Integer.valueOf(((Matcher)localObject1).group(3));
      return getPseudoVersionCode(paramString1.intValue(), paramString2.intValue(), ((Integer)localObject2).intValue()).compareTo(getPseudoVersionCode(localInteger1.intValue(), localInteger2.intValue(), ((Integer)localObject1).intValue()));
    }
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("Non semantic version provided: ");
    ((StringBuilder)localObject1).append(paramString1);
    ((StringBuilder)localObject1).append(" - ");
    ((StringBuilder)localObject1).append(paramString2);
    throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
  }
  
  public static void createCacheDir(Context paramContext)
  {
    paramContext = new File(getCacheDirAbsolutePath(paramContext));
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
  }
  
  public static void deleteDirectory(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        deleteDirectory(arrayOfFile[i]);
        i += 1;
      }
    }
    paramFile.delete();
  }
  
  public static int dpToPx(Context paramContext, int paramInt)
  {
    float f;
    if (density > 0.0F) {
      f = density;
    } else {
      f = paramContext.getResources().getDisplayMetrics().density;
    }
    density = f;
    return (int)(paramInt * density + 0.5F);
  }
  
  public static HashMap<String, String> extraParams(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    for (;;)
    {
      synchronized (paramLock)
      {
        if (cachedParams == null)
        {
          cachedParams = new HashMap();
          try
          {
            Integer localInteger = Integer.valueOf(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode);
          }
          catch (Exception localException1)
          {
            Logger.trace(localException1);
            localObject1 = Integer.valueOf(0);
          }
          cachedParams.put("app_version", String.valueOf(localObject1));
          paramContext.getResources().getDisplayMetrics();
          cachedParams.put("sdk_version", "9.22.0");
          cachedParams.put("android_version", Build.VERSION.SDK);
          cachedParams.put("external_package", getPackageName(paramContext));
          cachedParams.put("game_package", getPackageName(paramContext));
          cachedParams.put("app_name", getAppName(paramContext));
          if (!isTablet(paramContext)) {
            break label604;
          }
          localObject1 = "tablet";
          cachedParams.put("device_form_factor", localObject1);
          cachedParams.put("device_model", Build.MODEL);
          cachedParams.put("device_type", Build.DEVICE);
          localObject1 = HeyzapAds.config.publisherId;
          if (localObject1 != null) {
            cachedParams.put("publisher_sdk_key", localObject1);
          }
          if (isAmazon())
          {
            cachedParams.put("platform", "amazon");
            cachedParams.put("sdk_platform", "amazon");
          }
          else
          {
            cachedParams.put("platform", "android");
            cachedParams.put("sdk_platform", "android");
          }
        }
        localHashMap.putAll(cachedParams);
        if ((getAdvertisingId(paramContext) != null) && (!isAmazon()))
        {
          localHashMap.put("device_id", getAdvertisingId(paramContext));
          localHashMap.put("advertising_id", getAdvertisingId(paramContext));
          if (!getLimitAdTrackingEnabled(paramContext).booleanValue()) {
            localObject1 = "1";
          } else {
            localObject1 = "0";
          }
          localHashMap.put("tracking_enabled", localObject1);
        }
        else
        {
          localHashMap.put("device_id", getDeviceId(paramContext));
        }
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
          continue;
        }
        localHashMap.put("device_free_bytes", "0");
        Object localObject1 = paramContext.getResources().getConfiguration().locale;
        if (localObject1 != null)
        {
          localHashMap.put("locale_country", ((Locale)localObject1).getCountry().toLowerCase(Locale.US));
          localHashMap.put("locale_lang", ((Locale)localObject1).getLanguage().toLowerCase(Locale.US));
        }
        localHashMap.put("connection_type", Connectivity.connectionType(paramContext));
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
      label604:
      String str = "phone";
    }
  }
  
  public static String getAdvertisingId(Context paramContext)
  {
    try
    {
      if (advertisingIdAvailable == null) {
        loadAdvertisingId(paramContext);
      }
      if (advertisingIdAvailable != null) {
        paramContext = (Boolean)advertisingIdAvailable.get();
      }
      paramContext = advertisingId;
      return paramContext;
    }
    catch (InterruptedException|ExecutionException paramContext)
    {
      Logger.trace(paramContext);
    }
    return advertisingId;
  }
  
  private static String getAppName(Context paramContext)
  {
    if ((gameName.equals("unknown")) && (paramContext != null)) {
      gameName = paramContext.getPackageManager().getApplicationLabel(paramContext.getApplicationInfo()).toString();
    }
    return gameName;
  }
  
  public static String getCacheDirAbsolutePath(Context paramContext)
  {
    if (paramContext != null) {
      return String.format("%s/%s", new Object[] { paramContext.getCacheDir(), "com.heyzap.sdk" });
    }
    return null;
  }
  
  public static String getCachePath(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (paramString != null)) {
      return String.format("%s/%s", new Object[] { getCacheDirAbsolutePath(paramContext), paramString });
    }
    return null;
  }
  
  public static Map<String, String> getConsentDataFromJsonString(String paramString)
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
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("unable to convert gdpr consent data: ");
    ((StringBuilder)localObject2).append(paramString);
    Log.e((String)localObject1, ((StringBuilder)localObject2).toString());
    return null;
  }
  
  private static String getDeviceId(Context paramContext)
  {
    if ((deviceId.equals("unknown")) && (paramContext != null))
    {
      String str = Build.PRODUCT;
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      if ((str != null) && (paramContext != null))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(str);
        localStringBuilder.append("_");
        localStringBuilder.append(paramContext);
        deviceId = localStringBuilder.toString();
      }
    }
    return deviceId;
  }
  
  public static String getGdprConsentDataAsJsonString(Map<String, String> paramMap)
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
  
  public static String getImageCacheDirAbsolutePath(Context paramContext)
  {
    if (paramContext != null) {
      return String.format("%s/%s", new Object[] { paramContext.getCacheDir(), "com.heyzap.sdk.images" });
    }
    return null;
  }
  
  private static int getInverseScaledSize(Context paramContext, float paramFloat)
  {
    if (density <= 0.0F) {
      density = paramContext.getResources().getDisplayMetrics().density;
    }
    return (int)(paramFloat / density);
  }
  
  public static int getInverseScaledSize(Context paramContext, int paramInt)
  {
    return getInverseScaledSize(paramContext, paramInt);
  }
  
  public static Boolean getLimitAdTrackingEnabled(Context paramContext)
  {
    try
    {
      if (advertisingIdAvailable == null) {
        loadAdvertisingId(paramContext);
      }
      paramContext = (Boolean)advertisingIdAvailable.get();
      paramContext = limitAdTrackingEnabled;
      return paramContext;
    }
    catch (InterruptedException|ExecutionException paramContext)
    {
      Logger.trace(paramContext);
    }
    return limitAdTrackingEnabled;
  }
  
  public static String getPackageName(Context paramContext)
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
  
  public static Integer getPackageVersion(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return Integer.valueOf(i);
    }
    catch (Exception paramContext)
    {
      Logger.trace(paramContext);
    }
    return Integer.valueOf(0);
  }
  
  private static Double getPseudoVersionCode(int paramInt1, int paramInt2, int paramInt3)
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
  
  private static int getScaledSize(Context paramContext, float paramFloat)
  {
    if (density <= 0.0F) {
      density = paramContext.getResources().getDisplayMetrics().density;
    }
    return (int)(density * paramFloat);
  }
  
  public static int getScaledSize(Context paramContext, int paramInt)
  {
    return getScaledSize(paramContext, paramInt);
  }
  
  public static int getScaledSizeWithRelativeFlags(Context paramContext, int paramInt)
  {
    if (paramInt <= 0) {
      return paramInt;
    }
    if (density <= 0.0F) {
      density = paramContext.getResources().getDisplayMetrics().density;
    }
    return (int)(density * paramInt);
  }
  
  public static int getSdkVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String getValueWithoutInlining(Class paramClass, String paramString1, String paramString2)
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
  
  public static String getValueWithoutInlining(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramString1 = getValueWithoutInlining(Class.forName(paramString1), paramString2, paramString3);
      return paramString1;
    }
    catch (ClassNotFoundException paramString1) {}
    return paramString3;
  }
  
  public static String hex(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      localStringBuffer.append(Integer.toHexString(paramArrayOfByte[i] & 0xFF | 0x100).substring(1, 3));
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static boolean isAmazon()
  {
    return (Build.MANUFACTURER.equals("Amazon")) || (HeyzapAds.config.store.equals("amazon"));
  }
  
  public static boolean isApplicationOnTop(Context paramContext)
  {
    Object localObject = (ActivityManager)paramContext.getSystemService("activity");
    paramContext = paramContext.getApplicationContext().getPackageName();
    try
    {
      localObject = ((ActivityManager)localObject).getRunningAppProcesses();
      int i = 0;
      while (i < ((List)localObject).size())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((List)localObject).get(i);
        if (localRunningAppProcessInfo.processName.equals(paramContext))
        {
          int j = localRunningAppProcessInfo.importance;
          if (j == 100) {
            return true;
          }
        }
        i += 1;
      }
      return false;
    }
    catch (Exception paramContext)
    {
      Logger.trace(paramContext);
    }
  }
  
  public static Boolean isDebug(Context paramContext)
  {
    if (debug == null) {
      setDebug(packageIsInstalled(Constants.SNAKE_PACKAGE, paramContext));
    }
    return debug;
  }
  
  public static Boolean isExpired(Long paramLong, Integer paramInteger)
  {
    if (paramInteger.intValue() < System.currentTimeMillis() - paramLong.longValue()) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  public static boolean isSemVersionEqualOrGreaterThan(String paramString1, String paramString2)
  {
    try
    {
      int i = compareVersions(paramString1, paramString2);
      boolean bool = true;
      if (i != 0)
      {
        if (i == 1) {
          return true;
        }
        bool = false;
      }
      return bool;
    }
    catch (IllegalArgumentException paramString1) {}
    return false;
  }
  
  public static boolean isTablet(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static void load(Context paramContext)
  {
    createCacheDir(paramContext);
    loadAdvertisingId(paramContext);
  }
  
  private static void loadAdvertisingId(Context paramContext)
  {
    synchronized (advertiserIdLock)
    {
      if ((advertisingIdAvailable == null) || (advertisingIdAvailable.isDone()))
      {
        paramContext = new AdvertisingIdCallable(paramContext.getApplicationContext());
        advertisingIdAvailable = ExecutorPool.getInstance().submit(paramContext);
      }
      return;
    }
  }
  
  public static String md5(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      localObject = ((MessageDigest)localObject).digest();
      StringBuilder localStringBuilder1 = new StringBuilder();
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        StringBuilder localStringBuilder2;
        for (paramString = Integer.toHexString(localObject[i] & 0xFF); paramString.length() < 2; paramString = localStringBuilder2.toString())
        {
          localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("0");
          localStringBuilder2.append(paramString);
        }
        localStringBuilder1.append(paramString);
        i += 1;
      }
      paramString = localStringBuilder1.toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static String md5Hex(byte[] paramArrayOfByte)
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
  
  public static Boolean methodExists(@NonNull String paramString1, @NonNull String paramString2, @Nullable Function<Class[], Boolean> paramFunction, @Nullable Function<Class, Boolean> paramFunction1)
  {
    if (classExists(paramString1).booleanValue()) {}
    try
    {
      paramString1 = Class.forName(paramString1).getMethods();
    }
    catch (ClassNotFoundException paramString1)
    {
      int i;
      for (;;) {}
    }
    paramString1 = new Method[0];
    i = 0;
    while (i < paramString1.length)
    {
      Object localObject = paramString1[i];
      if ((paramString2.equals(localObject.getName())) && ((paramFunction == null) || (((Boolean)paramFunction.apply(localObject.getParameterTypes())).booleanValue())) && ((paramFunction1 == null) || (((Boolean)paramFunction1.apply(localObject.getReturnType())).booleanValue()))) {
        return Boolean.valueOf(true);
      }
      i += 1;
    }
    return Boolean.valueOf(false);
  }
  
  public static boolean packageHasPermission(Activity paramActivity, String paramString)
  {
    return paramActivity.getPackageManager().checkPermission(paramString, paramActivity.getPackageName()) == 0;
  }
  
  public static boolean packageHasPermissions(Activity paramActivity, ArrayList<String> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      if (!packageHasPermission(paramActivity, (String)paramArrayList.next())) {
        return false;
      }
    }
    return true;
  }
  
  public static Boolean packageHasReceiver(Activity paramActivity, String paramString)
  {
    Iterator localIterator = paramActivity.getPackageManager().getInstalledPackages(2).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.equals(getPackageName(paramActivity)))
      {
        paramActivity = localPackageInfo.receivers;
        if (paramActivity != null)
        {
          int j = paramActivity.length;
          int i = 0;
          while (i < j)
          {
            if (paramActivity[i].name.equals(paramString)) {
              return Boolean.valueOf(true);
            }
            i += 1;
          }
        }
        return Boolean.valueOf(false);
      }
    }
    return Boolean.valueOf(false);
  }
  
  public static boolean packageIsInstalled(String paramString, Context paramContext)
  {
    boolean bool2 = false;
    try
    {
      paramContext = paramContext.getPackageManager();
      paramString = paramContext.getLaunchIntentForPackage(paramString);
      boolean bool1 = bool2;
      if (paramString != null)
      {
        int i = paramContext.queryIntentActivities(paramString, 65536).size();
        bool1 = bool2;
        if (i > 0) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static Boolean probablyHasGooglePlayServices(Activity paramActivity)
  {
    Iterator localIterator = paramActivity.getPackageManager().getInstalledPackages(128).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.equals(getPackageName(paramActivity)))
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
  
  public static void setAdvertisingId(String paramString)
  {
    advertisingId = paramString;
  }
  
  public static void setDebug(boolean paramBoolean)
  {
    debug = Boolean.valueOf(paramBoolean);
    if (paramBoolean) {
      HeyzapAds.setThirdPartyVerboseLogging(true);
    }
  }
  
  public static void setLimitAdTracking(Boolean paramBoolean)
  {
    limitAdTrackingEnabled = paramBoolean;
  }
}
