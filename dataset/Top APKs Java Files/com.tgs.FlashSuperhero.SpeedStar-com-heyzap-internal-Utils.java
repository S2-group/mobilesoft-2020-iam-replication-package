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
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import com.heyzap.common.concurrency.ExecutorPool;
import com.heyzap.sdk.ads.HeyzapAds;
import com.heyzap.sdk.ads.HeyzapAds.AdsConfig;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.regex.Pattern;

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
  private static String deviceId;
  private static String gameName;
  private static Boolean limitAdTrackingEnabled;
  public static String packageName;
  private static final Object paramLock;
  
  static
  {
    debug = null;
    deviceId = "unknown";
    packageName = "unknown";
    gameName = "unknown";
    paramLock = new Object();
    advertisingId = null;
    limitAdTrackingEnabled = Boolean.valueOf(false);
  }
  
  public Utils() {}
  
  public static boolean activityExistsInPackage(Activity paramActivity, Class paramClass)
  {
    boolean bool = false;
    if (new Intent(paramActivity, paramClass).resolveActivityInfo(paramActivity.getPackageManager(), 0) != null) {
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
    catch (ClassNotFoundException paramActivity) {}
    return false;
  }
  
  public static Boolean classExists(String paramString)
  {
    try
    {
      Class.forName(paramString);
      return Boolean.valueOf(true);
    }
    catch (ClassNotFoundException paramString) {}
    return Boolean.valueOf(false);
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
    if (density > 0.0F) {}
    for (float f = density;; f = paramContext.getResources().getDisplayMetrics().density)
    {
      density = f;
      return (int)(paramInt * density + 0.5F);
    }
  }
  
  /* Error */
  public static HashMap<String, String> extraParams(Context paramContext)
  {
    // Byte code:
    //   0: new 160	java/util/HashMap
    //   3: dup
    //   4: invokespecial 161	java/util/HashMap:<init>	()V
    //   7: astore_3
    //   8: getstatic 51	com/heyzap/internal/Utils:paramLock	Ljava/lang/Object;
    //   11: astore 4
    //   13: aload 4
    //   15: monitorenter
    //   16: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   19: ifnonnull +219 -> 238
    //   22: new 160	java/util/HashMap
    //   25: dup
    //   26: invokespecial 161	java/util/HashMap:<init>	()V
    //   29: putstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   32: iconst_0
    //   33: invokestatic 168	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   36: astore_2
    //   37: aload_0
    //   38: invokevirtual 169	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   41: aload_0
    //   42: invokevirtual 173	android/content/Context:getPackageName	()Ljava/lang/String;
    //   45: iconst_0
    //   46: invokevirtual 179	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   49: getfield 185	android/content/pm/PackageInfo:versionCode	I
    //   52: istore_1
    //   53: iload_1
    //   54: invokestatic 168	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   57: astore_2
    //   58: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   61: ldc -69
    //   63: aload_2
    //   64: invokestatic 192	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   67: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   70: pop
    //   71: aload_0
    //   72: invokevirtual 145	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   75: invokevirtual 151	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   78: pop
    //   79: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   82: ldc -58
    //   84: ldc -56
    //   86: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   89: pop
    //   90: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   93: ldc -54
    //   95: getstatic 207	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   98: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   101: pop
    //   102: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   105: ldc -47
    //   107: aload_0
    //   108: invokestatic 211	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   111: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: pop
    //   115: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   118: ldc -43
    //   120: aload_0
    //   121: invokestatic 211	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   124: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   127: pop
    //   128: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   131: ldc -41
    //   133: aload_0
    //   134: invokestatic 218	com/heyzap/internal/Utils:getAppName	(Landroid/content/Context;)Ljava/lang/String;
    //   137: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   140: pop
    //   141: aload_0
    //   142: invokestatic 222	com/heyzap/internal/Utils:isTablet	(Landroid/content/Context;)Z
    //   145: ifeq +387 -> 532
    //   148: ldc -32
    //   150: astore_2
    //   151: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   154: ldc -30
    //   156: aload_2
    //   157: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   160: pop
    //   161: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   164: ldc -28
    //   166: getstatic 233	android/os/Build:MODEL	Ljava/lang/String;
    //   169: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   172: pop
    //   173: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   176: ldc -21
    //   178: getstatic 238	android/os/Build:DEVICE	Ljava/lang/String;
    //   181: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   184: pop
    //   185: getstatic 244	com/heyzap/sdk/ads/HeyzapAds:config	Lcom/heyzap/sdk/ads/HeyzapAds$AdsConfig;
    //   188: getfield 249	com/heyzap/sdk/ads/HeyzapAds$AdsConfig:publisherId	Ljava/lang/String;
    //   191: astore_2
    //   192: aload_2
    //   193: ifnull +13 -> 206
    //   196: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   199: ldc -5
    //   201: aload_2
    //   202: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   205: pop
    //   206: invokestatic 254	com/heyzap/internal/Utils:isAmazon	()Z
    //   209: ifeq +330 -> 539
    //   212: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   215: ldc_w 256
    //   218: ldc_w 258
    //   221: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   224: pop
    //   225: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   228: ldc_w 260
    //   231: ldc_w 258
    //   234: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   237: pop
    //   238: aload_3
    //   239: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   242: invokevirtual 264	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   245: aload 4
    //   247: monitorexit
    //   248: aload_0
    //   249: invokestatic 267	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   252: ifnull +323 -> 575
    //   255: invokestatic 254	com/heyzap/internal/Utils:isAmazon	()Z
    //   258: ifne +317 -> 575
    //   261: aload_3
    //   262: ldc_w 269
    //   265: aload_0
    //   266: invokestatic 267	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   269: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   272: pop
    //   273: aload_3
    //   274: ldc_w 271
    //   277: aload_0
    //   278: invokestatic 267	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   281: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   284: pop
    //   285: aload_0
    //   286: invokestatic 275	com/heyzap/internal/Utils:getLimitAdTrackingEnabled	(Landroid/content/Context;)Ljava/lang/Boolean;
    //   289: invokevirtual 278	java/lang/Boolean:booleanValue	()Z
    //   292: ifne +276 -> 568
    //   295: ldc_w 280
    //   298: astore_2
    //   299: aload_3
    //   300: ldc_w 282
    //   303: aload_2
    //   304: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   307: pop
    //   308: getstatic 285	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   311: ifnull +14 -> 325
    //   314: aload_3
    //   315: ldc_w 287
    //   318: getstatic 285	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   321: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   324: pop
    //   325: getstatic 290	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   328: ifnull +14 -> 342
    //   331: aload_3
    //   332: ldc_w 292
    //   335: getstatic 290	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   338: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   341: pop
    //   342: new 294	android/os/StatFs
    //   345: dup
    //   346: invokestatic 300	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   349: invokevirtual 303	java/io/File:getPath	()Ljava/lang/String;
    //   352: invokespecial 304	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   355: astore_2
    //   356: aload_3
    //   357: ldc_w 306
    //   360: aload_2
    //   361: invokevirtual 310	android/os/StatFs:getBlockSize	()I
    //   364: i2l
    //   365: aload_2
    //   366: invokevirtual 313	android/os/StatFs:getAvailableBlocks	()I
    //   369: i2l
    //   370: lmul
    //   371: invokestatic 319	java/lang/Long:toString	(J)Ljava/lang/String;
    //   374: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   377: pop
    //   378: aload_0
    //   379: invokevirtual 145	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   382: invokevirtual 323	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   385: getfield 329	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   388: astore_2
    //   389: aload_2
    //   390: ifnull +39 -> 429
    //   393: aload_3
    //   394: ldc_w 331
    //   397: aload_2
    //   398: invokevirtual 336	java/util/Locale:getCountry	()Ljava/lang/String;
    //   401: getstatic 339	java/util/Locale:US	Ljava/util/Locale;
    //   404: invokevirtual 343	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   407: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   410: pop
    //   411: aload_3
    //   412: ldc_w 345
    //   415: aload_2
    //   416: invokevirtual 348	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   419: getstatic 339	java/util/Locale:US	Ljava/util/Locale;
    //   422: invokevirtual 343	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   425: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   428: pop
    //   429: aload_3
    //   430: ldc_w 350
    //   433: aload_0
    //   434: invokestatic 355	com/heyzap/common/net/Connectivity:connectionType	(Landroid/content/Context;)Ljava/lang/String;
    //   437: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   440: pop
    //   441: aload_0
    //   442: invokevirtual 145	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   445: invokevirtual 151	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   448: astore_0
    //   449: aload_3
    //   450: ldc_w 357
    //   453: aload_0
    //   454: getfield 154	android/util/DisplayMetrics:density	F
    //   457: invokestatic 362	java/lang/Float:toString	(F)Ljava/lang/String;
    //   460: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   463: pop
    //   464: aload_3
    //   465: ldc_w 364
    //   468: invokevirtual 368	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   471: ifne +18 -> 489
    //   474: aload_3
    //   475: ldc_w 364
    //   478: aload_0
    //   479: getfield 371	android/util/DisplayMetrics:widthPixels	I
    //   482: invokestatic 374	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   485: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   488: pop
    //   489: aload_3
    //   490: ldc_w 376
    //   493: invokevirtual 368	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   496: ifne +18 -> 514
    //   499: aload_3
    //   500: ldc_w 376
    //   503: aload_0
    //   504: getfield 379	android/util/DisplayMetrics:heightPixels	I
    //   507: invokestatic 374	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   510: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   513: pop
    //   514: aload_3
    //   515: areturn
    //   516: astore 5
    //   518: aload 5
    //   520: invokestatic 385	com/heyzap/internal/Logger:trace	(Ljava/lang/Throwable;)V
    //   523: goto -465 -> 58
    //   526: astore_0
    //   527: aload 4
    //   529: monitorexit
    //   530: aload_0
    //   531: athrow
    //   532: ldc_w 387
    //   535: astore_2
    //   536: goto -385 -> 151
    //   539: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   542: ldc_w 256
    //   545: ldc_w 389
    //   548: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   551: pop
    //   552: getstatic 163	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   555: ldc_w 260
    //   558: ldc_w 389
    //   561: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   564: pop
    //   565: goto -327 -> 238
    //   568: ldc_w 391
    //   571: astore_2
    //   572: goto -273 -> 299
    //   575: aload_3
    //   576: ldc_w 269
    //   579: aload_0
    //   580: invokestatic 394	com/heyzap/internal/Utils:getDeviceId	(Landroid/content/Context;)Ljava/lang/String;
    //   583: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   586: pop
    //   587: goto -279 -> 308
    //   590: astore_2
    //   591: aload_3
    //   592: ldc_w 306
    //   595: ldc_w 391
    //   598: invokevirtual 196	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   601: pop
    //   602: goto -224 -> 378
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	605	0	paramContext	Context
    //   52	2	1	i	int
    //   36	536	2	localObject1	Object
    //   590	1	2	localException1	Exception
    //   7	585	3	localHashMap	HashMap
    //   11	517	4	localObject2	Object
    //   516	3	5	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   37	53	516	java/lang/Exception
    //   16	32	526	finally
    //   37	53	526	finally
    //   58	148	526	finally
    //   151	192	526	finally
    //   196	206	526	finally
    //   206	238	526	finally
    //   238	248	526	finally
    //   518	523	526	finally
    //   527	530	526	finally
    //   539	565	526	finally
    //   342	378	590	java/lang/Exception
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
    catch (InterruptedException paramContext)
    {
      Logger.trace(paramContext);
      return advertisingId;
    }
    catch (ExecutionException paramContext)
    {
      for (;;) {}
    }
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
  
  private static String getDeviceId(Context paramContext)
  {
    if ((deviceId.equals("unknown")) && (paramContext != null))
    {
      String str = Build.PRODUCT;
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      if ((str != null) && (paramContext != null)) {
        deviceId = str + "_" + paramContext;
      }
    }
    return deviceId;
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
    catch (InterruptedException paramContext)
    {
      Logger.trace(paramContext);
      return limitAdTrackingEnabled;
    }
    catch (ExecutionException paramContext)
    {
      for (;;) {}
    }
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
      StringBuilder localStringBuilder = new StringBuilder();
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        for (paramString = Integer.toHexString(localObject[i] & 0xFF); paramString.length() < 2; paramString = "0" + paramString) {}
        localStringBuilder.append(paramString);
        i += 1;
      }
      paramString = localStringBuilder.toString();
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
    catch (NoSuchAlgorithmException paramArrayOfByte) {}
    return null;
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
