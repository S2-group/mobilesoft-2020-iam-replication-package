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
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import com.heyzap.a.c.c;
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
  private static Boolean c = null;
  private static String d = "unknown";
  private static String e;
  private static HashMap<String, String> f;
  private static final Object g;
  private static Future<Boolean> h;
  private static String i;
  private static Boolean j;
  private static final Object k = new Object();
  public static String packageName = "unknown";
  
  static
  {
    e = "unknown";
    g = new Object();
    i = null;
    j = Boolean.valueOf(false);
  }
  
  public static int a(Context paramContext, int paramInt)
  {
    if (b > 0.0F) {}
    for (float f1 = b;; f1 = paramContext.getResources().getDisplayMetrics().density)
    {
      b = f1;
      return (int)(paramInt * b + 0.5F);
    }
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
    if (paramInteger.intValue() < System.currentTimeMillis() - paramLong.longValue()) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  public static Boolean a(@NonNull String paramString1, @NonNull String paramString2)
  {
    Method[] arrayOfMethod;
    if (b(paramString1).booleanValue()) {
      arrayOfMethod = new Method[0];
    }
    try
    {
      paramString1 = Class.forName(paramString1).getMethods();
      int m = 0;
      while (m < paramString1.length)
      {
        if (paramString2.equals(paramString1[m].getName())) {
          return Boolean.valueOf(true);
        }
        m += 1;
      }
      return Boolean.valueOf(false);
    }
    catch (ClassNotFoundException paramString1)
    {
      for (;;)
      {
        paramString1 = arrayOfMethod;
      }
    }
  }
  
  private static Double a(int paramInt1, int paramInt2, int paramInt3)
  {
    return Double.valueOf(paramInt1 * Math.pow(1000.0D, 2.0D) + paramInt2 * 1000 + paramInt3);
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
    catch (IllegalAccessException paramClass)
    {
      return paramString2;
    }
    catch (NoSuchFieldException paramClass) {}
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
    catch (NoSuchAlgorithmException paramArrayOfByte) {}
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
    boolean bool = false;
    if (new Intent(paramActivity, paramClass).resolveActivityInfo(paramActivity.getPackageManager(), 0) != null) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean a(Activity paramActivity, String paramString)
  {
    try
    {
      boolean bool = a(paramActivity, Class.forName(paramString));
      return bool;
    }
    catch (ClassNotFoundException paramActivity) {}
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
    return (int)(f1 * b);
  }
  
  public static Boolean b(String paramString)
  {
    try
    {
      Class.forName(paramString);
      return Boolean.valueOf(true);
    }
    catch (ClassNotFoundException paramString) {}
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
      if ((!((Matcher)localObject2).matches()) || (!((Matcher)localObject1).matches())) {
        throw new IllegalArgumentException("Non semantic version provided: " + paramString1 + " - " + paramString2);
      }
      paramString1 = Integer.valueOf(((Matcher)localObject2).group(1));
      paramString2 = Integer.valueOf(((Matcher)localObject2).group(2));
      localObject2 = Integer.valueOf(((Matcher)localObject2).group(3));
      Integer localInteger1 = Integer.valueOf(((Matcher)localObject1).group(1));
      Integer localInteger2 = Integer.valueOf(((Matcher)localObject1).group(2));
      localObject1 = Integer.valueOf(((Matcher)localObject1).group(3));
      int m = a(paramString1.intValue(), paramString2.intValue(), ((Integer)localObject2).intValue()).compareTo(a(localInteger1.intValue(), localInteger2.intValue(), ((Integer)localObject1).intValue()));
      if ((m == 0) || (m == 1)) {
        return true;
      }
    }
    catch (IllegalArgumentException paramString1) {}
    return false;
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
    catch (InterruptedException paramContext)
    {
      Logger.trace(paramContext);
      return i;
    }
    catch (ExecutionException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static Map<String, String> c(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    HashMap localHashMap = new HashMap();
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      Iterator localIterator = localJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localHashMap.put(str, localJSONObject.getString(str));
      }
      return localJSONException;
    }
    catch (JSONException localJSONException)
    {
      Log.e(Logger.TAG, "unable to convert gdpr consent data: " + paramString);
      return null;
    }
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
    catch (InterruptedException paramContext)
    {
      Logger.trace(paramContext);
      return j;
    }
    catch (ExecutionException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static boolean d(HeyzapAds.AdsConfig paramAdsConfig)
  {
    return (paramAdsConfig.flags & 0x20) != 0;
  }
  
  /* Error */
  public static HashMap<String, String> e(Context paramContext)
  {
    // Byte code:
    //   0: new 480	java/util/HashMap
    //   3: dup
    //   4: invokespecial 481	java/util/HashMap:<init>	()V
    //   7: astore 4
    //   9: getstatic 45	com/heyzap/internal/Utils:g	Ljava/lang/Object;
    //   12: astore 5
    //   14: aload 5
    //   16: monitorenter
    //   17: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   20: ifnonnull +261 -> 281
    //   23: new 480	java/util/HashMap
    //   26: dup
    //   27: invokespecial 481	java/util/HashMap:<init>	()V
    //   30: putstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   33: aload_0
    //   34: invokevirtual 375	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   37: aload_0
    //   38: invokevirtual 395	android/content/Context:getPackageName	()Ljava/lang/String;
    //   41: iconst_0
    //   42: invokevirtual 510	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   45: getfield 513	android/content/pm/PackageInfo:versionCode	I
    //   48: istore_1
    //   49: iload_1
    //   50: invokestatic 516	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   53: astore_3
    //   54: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   57: ldc_w 518
    //   60: aload_3
    //   61: invokestatic 521	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   64: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   67: pop
    //   68: aload_0
    //   69: invokevirtual 76	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   72: invokevirtual 82	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   75: pop
    //   76: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   79: ldc_w 524
    //   82: ldc_w 526
    //   85: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   88: pop
    //   89: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   92: ldc_w 528
    //   95: getstatic 531	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   98: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   101: pop
    //   102: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   105: ldc_w 533
    //   108: aload_0
    //   109: invokestatic 122	com/heyzap/internal/Utils:b	(Landroid/content/Context;)Ljava/lang/String;
    //   112: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   115: pop
    //   116: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   119: ldc_w 535
    //   122: aload_0
    //   123: invokestatic 122	com/heyzap/internal/Utils:b	(Landroid/content/Context;)Ljava/lang/String;
    //   126: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   129: pop
    //   130: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   133: astore_3
    //   134: getstatic 40	com/heyzap/internal/Utils:e	Ljava/lang/String;
    //   137: ldc 34
    //   139: invokevirtual 128	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   142: ifeq +26 -> 168
    //   145: aload_0
    //   146: ifnull +22 -> 168
    //   149: aload_0
    //   150: invokevirtual 375	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   153: aload_0
    //   154: invokevirtual 539	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   157: invokevirtual 543	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   160: invokeinterface 546 1 0
    //   165: putstatic 40	com/heyzap/internal/Utils:e	Ljava/lang/String;
    //   168: aload_3
    //   169: ldc_w 548
    //   172: getstatic 40	com/heyzap/internal/Utils:e	Ljava/lang/String;
    //   175: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   178: pop
    //   179: aload_0
    //   180: invokestatic 551	com/heyzap/internal/Utils:g	(Landroid/content/Context;)Z
    //   183: ifeq +769 -> 952
    //   186: ldc_w 553
    //   189: astore_3
    //   190: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   193: ldc_w 555
    //   196: aload_3
    //   197: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   200: pop
    //   201: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   204: ldc_w 557
    //   207: getstatic 560	android/os/Build:MODEL	Ljava/lang/String;
    //   210: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   213: pop
    //   214: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   217: ldc_w 562
    //   220: getstatic 565	android/os/Build:DEVICE	Ljava/lang/String;
    //   223: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   226: pop
    //   227: getstatic 330	com/heyzap/sdk/ads/HeyzapAds:config	Lcom/heyzap/sdk/ads/HeyzapAds$AdsConfig;
    //   230: getfield 568	com/heyzap/sdk/ads/HeyzapAds$AdsConfig:publisherId	Ljava/lang/String;
    //   233: astore_3
    //   234: aload_3
    //   235: ifnull +14 -> 249
    //   238: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   241: ldc_w 570
    //   244: aload_3
    //   245: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   248: pop
    //   249: invokestatic 572	com/heyzap/internal/Utils:a	()Z
    //   252: ifeq +370 -> 622
    //   255: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   258: ldc_w 574
    //   261: ldc_w 337
    //   264: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   267: pop
    //   268: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   271: ldc_w 576
    //   274: ldc_w 337
    //   277: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   280: pop
    //   281: aload 4
    //   283: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   286: invokevirtual 580	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   289: aload 5
    //   291: monitorexit
    //   292: aload_0
    //   293: invokestatic 582	com/heyzap/internal/Utils:c	(Landroid/content/Context;)Ljava/lang/String;
    //   296: ifnull +368 -> 664
    //   299: invokestatic 572	com/heyzap/internal/Utils:a	()Z
    //   302: ifne +362 -> 664
    //   305: aload 4
    //   307: ldc_w 584
    //   310: aload_0
    //   311: invokestatic 582	com/heyzap/internal/Utils:c	(Landroid/content/Context;)Ljava/lang/String;
    //   314: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   317: pop
    //   318: aload 4
    //   320: ldc_w 586
    //   323: aload_0
    //   324: invokestatic 582	com/heyzap/internal/Utils:c	(Landroid/content/Context;)Ljava/lang/String;
    //   327: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   330: pop
    //   331: aload_0
    //   332: invokestatic 588	com/heyzap/internal/Utils:d	(Landroid/content/Context;)Ljava/lang/Boolean;
    //   335: invokevirtual 174	java/lang/Boolean:booleanValue	()Z
    //   338: ifne +319 -> 657
    //   341: ldc_w 590
    //   344: astore_3
    //   345: aload 4
    //   347: ldc_w 592
    //   350: aload_3
    //   351: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   354: pop
    //   355: getstatic 595	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   358: ifnull +15 -> 373
    //   361: aload 4
    //   363: ldc_w 597
    //   366: getstatic 595	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   369: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   372: pop
    //   373: getstatic 600	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   376: ifnull +15 -> 391
    //   379: aload 4
    //   381: ldc_w 602
    //   384: getstatic 600	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   387: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   390: pop
    //   391: new 604	android/os/StatFs
    //   394: dup
    //   395: invokestatic 610	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   398: invokevirtual 613	java/io/File:getPath	()Ljava/lang/String;
    //   401: invokespecial 614	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   404: astore_3
    //   405: aload 4
    //   407: ldc_w 616
    //   410: aload_3
    //   411: invokevirtual 619	android/os/StatFs:getBlockSize	()I
    //   414: i2l
    //   415: aload_3
    //   416: invokevirtual 622	android/os/StatFs:getAvailableBlocks	()I
    //   419: i2l
    //   420: lmul
    //   421: invokestatic 625	java/lang/Long:toString	(J)Ljava/lang/String;
    //   424: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   427: pop
    //   428: aload_0
    //   429: invokevirtual 76	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   432: invokevirtual 629	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   435: getfield 635	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   438: astore_3
    //   439: aload_3
    //   440: ifnull +41 -> 481
    //   443: aload 4
    //   445: ldc_w 637
    //   448: aload_3
    //   449: invokevirtual 642	java/util/Locale:getCountry	()Ljava/lang/String;
    //   452: getstatic 645	java/util/Locale:US	Ljava/util/Locale;
    //   455: invokevirtual 649	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   458: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   461: pop
    //   462: aload 4
    //   464: ldc_w 651
    //   467: aload_3
    //   468: invokevirtual 654	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   471: getstatic 645	java/util/Locale:US	Ljava/util/Locale;
    //   474: invokevirtual 649	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   477: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   480: pop
    //   481: aload_0
    //   482: ldc_w 656
    //   485: invokevirtual 660	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   488: checkcast 662	android/net/ConnectivityManager
    //   491: invokevirtual 666	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   494: astore_3
    //   495: aload_3
    //   496: ifnull +451 -> 947
    //   499: aload_3
    //   500: invokevirtual 671	android/net/NetworkInfo:getType	()I
    //   503: istore_1
    //   504: aload_3
    //   505: invokevirtual 674	android/net/NetworkInfo:getSubtype	()I
    //   508: istore_2
    //   509: iload_1
    //   510: iconst_1
    //   511: if_icmpne +252 -> 763
    //   514: ldc_w 676
    //   517: astore_3
    //   518: aload 4
    //   520: ldc_w 678
    //   523: aload_3
    //   524: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   527: pop
    //   528: aload_0
    //   529: invokevirtual 76	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   532: invokevirtual 82	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   535: astore_0
    //   536: aload 4
    //   538: ldc_w 680
    //   541: aload_0
    //   542: getfield 87	android/util/DisplayMetrics:density	F
    //   545: invokestatic 685	java/lang/Float:toString	(F)Ljava/lang/String;
    //   548: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   551: pop
    //   552: aload 4
    //   554: ldc_w 687
    //   557: invokevirtual 689	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   560: ifne +19 -> 579
    //   563: aload 4
    //   565: ldc_w 687
    //   568: aload_0
    //   569: getfield 692	android/util/DisplayMetrics:widthPixels	I
    //   572: invokestatic 694	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   575: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   578: pop
    //   579: aload 4
    //   581: ldc_w 696
    //   584: invokevirtual 689	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   587: ifne +19 -> 606
    //   590: aload 4
    //   592: ldc_w 696
    //   595: aload_0
    //   596: getfield 699	android/util/DisplayMetrics:heightPixels	I
    //   599: invokestatic 694	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   602: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   605: pop
    //   606: aload 4
    //   608: areturn
    //   609: astore_3
    //   610: aload_3
    //   611: invokestatic 477	com/heyzap/internal/Logger:trace	(Ljava/lang/Throwable;)V
    //   614: iconst_0
    //   615: invokestatic 516	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   618: astore_3
    //   619: goto -565 -> 54
    //   622: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   625: ldc_w 574
    //   628: ldc_w 701
    //   631: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   634: pop
    //   635: getstatic 506	com/heyzap/internal/Utils:f	Ljava/util/HashMap;
    //   638: ldc_w 576
    //   641: ldc_w 701
    //   644: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   647: pop
    //   648: goto -367 -> 281
    //   651: astore_0
    //   652: aload 5
    //   654: monitorexit
    //   655: aload_0
    //   656: athrow
    //   657: ldc_w 703
    //   660: astore_3
    //   661: goto -316 -> 345
    //   664: getstatic 36	com/heyzap/internal/Utils:d	Ljava/lang/String;
    //   667: ldc 34
    //   669: invokevirtual 128	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   672: ifeq +60 -> 732
    //   675: aload_0
    //   676: ifnull +56 -> 732
    //   679: getstatic 706	android/os/Build:PRODUCT	Ljava/lang/String;
    //   682: astore_3
    //   683: aload_0
    //   684: invokevirtual 710	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   687: ldc_w 712
    //   690: invokestatic 717	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   693: astore 5
    //   695: aload_3
    //   696: ifnull +36 -> 732
    //   699: aload 5
    //   701: ifnull +31 -> 732
    //   704: new 426	java/lang/StringBuilder
    //   707: dup
    //   708: invokespecial 718	java/lang/StringBuilder:<init>	()V
    //   711: aload_3
    //   712: invokevirtual 434	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   715: ldc_w 720
    //   718: invokevirtual 434	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   721: aload 5
    //   723: invokevirtual 434	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   726: invokevirtual 437	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   729: putstatic 36	com/heyzap/internal/Utils:d	Ljava/lang/String;
    //   732: aload 4
    //   734: ldc_w 584
    //   737: getstatic 36	com/heyzap/internal/Utils:d	Ljava/lang/String;
    //   740: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   743: pop
    //   744: goto -389 -> 355
    //   747: astore_3
    //   748: aload 4
    //   750: ldc_w 616
    //   753: ldc_w 703
    //   756: invokevirtual 522	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   759: pop
    //   760: goto -332 -> 428
    //   763: iload_1
    //   764: ifne +183 -> 947
    //   767: iload_2
    //   768: tableswitch	default:+76->844, 1:+109->877, 2:+95->863, 3:+137->905, 4:+88->856, 5:+102->870, 6:+102->870, 7:+81->849, 8:+116->884, 9:+130->898, 10:+123->891, 11:+165->933, 12:+151->919, 13:+172->940, 14:+144->912, 15:+158->926
    //   844: aconst_null
    //   845: astore_3
    //   846: goto -328 -> 518
    //   849: ldc_w 722
    //   852: astore_3
    //   853: goto -335 -> 518
    //   856: ldc_w 724
    //   859: astore_3
    //   860: goto -342 -> 518
    //   863: ldc_w 726
    //   866: astore_3
    //   867: goto -349 -> 518
    //   870: ldc_w 728
    //   873: astore_3
    //   874: goto -356 -> 518
    //   877: ldc_w 730
    //   880: astore_3
    //   881: goto -363 -> 518
    //   884: ldc_w 732
    //   887: astore_3
    //   888: goto -370 -> 518
    //   891: ldc_w 734
    //   894: astore_3
    //   895: goto -377 -> 518
    //   898: ldc_w 736
    //   901: astore_3
    //   902: goto -384 -> 518
    //   905: ldc_w 738
    //   908: astore_3
    //   909: goto -391 -> 518
    //   912: ldc_w 740
    //   915: astore_3
    //   916: goto -398 -> 518
    //   919: ldc_w 742
    //   922: astore_3
    //   923: goto -405 -> 518
    //   926: ldc_w 744
    //   929: astore_3
    //   930: goto -412 -> 518
    //   933: ldc_w 746
    //   936: astore_3
    //   937: goto -419 -> 518
    //   940: ldc_w 748
    //   943: astore_3
    //   944: goto -426 -> 518
    //   947: aconst_null
    //   948: astore_3
    //   949: goto -431 -> 518
    //   952: ldc_w 750
    //   955: astore_3
    //   956: goto -766 -> 190
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	959	0	paramContext	Context
    //   48	716	1	m	int
    //   508	260	2	n	int
    //   53	471	3	localObject1	Object
    //   609	2	3	localException1	Exception
    //   618	94	3	localObject2	Object
    //   747	1	3	localException2	Exception
    //   845	111	3	str	String
    //   7	742	4	localHashMap	HashMap
    //   12	710	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   33	49	609	java/lang/Exception
    //   17	33	651	finally
    //   33	49	651	finally
    //   54	145	651	finally
    //   149	168	651	finally
    //   168	186	651	finally
    //   190	234	651	finally
    //   238	249	651	finally
    //   249	281	651	finally
    //   281	292	651	finally
    //   610	619	651	finally
    //   622	648	651	finally
    //   652	655	651	finally
    //   391	428	747	java/lang/Exception
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
        h = c.a().submit(paramContext);
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
