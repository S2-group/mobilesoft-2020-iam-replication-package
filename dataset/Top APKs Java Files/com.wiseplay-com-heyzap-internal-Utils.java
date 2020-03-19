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

public class Utils
{
  private static final String CACHE_DIR = "com.heyzap.sdk";
  private static final String IMAGE_CACHE_DIR = "com.heyzap.sdk.images";
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
    //   0: new 148	java/util/HashMap
    //   3: dup
    //   4: invokespecial 149	java/util/HashMap:<init>	()V
    //   7: astore 5
    //   9: getstatic 49	com/heyzap/internal/Utils:paramLock	Ljava/lang/Object;
    //   12: astore 6
    //   14: aload 6
    //   16: monitorenter
    //   17: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   20: ifnonnull +217 -> 237
    //   23: new 148	java/util/HashMap
    //   26: dup
    //   27: invokespecial 149	java/util/HashMap:<init>	()V
    //   30: putstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   33: aload_0
    //   34: invokevirtual 152	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   37: aload_0
    //   38: invokevirtual 156	android/content/Context:getPackageName	()Ljava/lang/String;
    //   41: iconst_0
    //   42: invokevirtual 162	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   45: getfield 168	android/content/pm/PackageInfo:versionCode	I
    //   48: istore_1
    //   49: iload_1
    //   50: invokestatic 173	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   53: astore 4
    //   55: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   58: ldc -81
    //   60: aload 4
    //   62: invokestatic 180	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   65: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   68: pop
    //   69: aload_0
    //   70: invokevirtual 133	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   73: invokevirtual 139	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   76: pop
    //   77: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   80: ldc -70
    //   82: ldc -68
    //   84: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   87: pop
    //   88: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   91: ldc -66
    //   93: getstatic 195	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   96: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   99: pop
    //   100: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   103: ldc -59
    //   105: aload_0
    //   106: invokestatic 199	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   109: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   112: pop
    //   113: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   116: ldc -55
    //   118: aload_0
    //   119: invokestatic 199	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   122: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   125: pop
    //   126: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   129: ldc -53
    //   131: aload_0
    //   132: invokestatic 206	com/heyzap/internal/Utils:getAppName	(Landroid/content/Context;)Ljava/lang/String;
    //   135: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   138: pop
    //   139: aload_0
    //   140: invokestatic 210	com/heyzap/internal/Utils:isTablet	(Landroid/content/Context;)Z
    //   143: ifeq +489 -> 632
    //   146: ldc -44
    //   148: astore 4
    //   150: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   153: ldc -42
    //   155: aload 4
    //   157: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   160: pop
    //   161: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   164: ldc -40
    //   166: getstatic 221	android/os/Build:MODEL	Ljava/lang/String;
    //   169: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   172: pop
    //   173: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   176: ldc -33
    //   178: getstatic 226	android/os/Build:DEVICE	Ljava/lang/String;
    //   181: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   184: pop
    //   185: getstatic 232	com/heyzap/sdk/ads/HeyzapAds:config	Lcom/heyzap/sdk/ads/HeyzapAds$AdsConfig;
    //   188: getfield 237	com/heyzap/sdk/ads/HeyzapAds$AdsConfig:publisherId	Ljava/lang/String;
    //   191: astore 4
    //   193: aload 4
    //   195: ifnull +14 -> 209
    //   198: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   201: ldc -17
    //   203: aload 4
    //   205: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   208: pop
    //   209: invokestatic 242	com/heyzap/internal/Utils:isAmazon	()Z
    //   212: ifeq +346 -> 558
    //   215: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   218: ldc -12
    //   220: ldc -10
    //   222: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   225: pop
    //   226: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   229: ldc -8
    //   231: ldc -10
    //   233: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   236: pop
    //   237: aload 5
    //   239: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   242: invokevirtual 252	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   245: aload 6
    //   247: monitorexit
    //   248: aload_0
    //   249: invokestatic 255	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   252: ifnull +347 -> 599
    //   255: invokestatic 242	com/heyzap/internal/Utils:isAmazon	()Z
    //   258: ifne +341 -> 599
    //   261: aload 5
    //   263: ldc_w 257
    //   266: aload_0
    //   267: invokestatic 255	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   270: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   273: pop
    //   274: aload 5
    //   276: ldc_w 259
    //   279: aload_0
    //   280: invokestatic 255	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   283: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   286: pop
    //   287: aload_0
    //   288: invokestatic 263	com/heyzap/internal/Utils:getLimitAdTrackingEnabled	(Landroid/content/Context;)Ljava/lang/Boolean;
    //   291: invokevirtual 266	java/lang/Boolean:booleanValue	()Z
    //   294: ifne +297 -> 591
    //   297: ldc_w 268
    //   300: astore 4
    //   302: aload 5
    //   304: ldc_w 270
    //   307: aload 4
    //   309: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   312: pop
    //   313: getstatic 273	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   316: ifnull +15 -> 331
    //   319: aload 5
    //   321: ldc_w 275
    //   324: getstatic 273	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   327: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   330: pop
    //   331: getstatic 278	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   334: ifnull +15 -> 349
    //   337: aload 5
    //   339: ldc_w 280
    //   342: getstatic 278	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   345: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   348: pop
    //   349: new 282	android/os/StatFs
    //   352: dup
    //   353: invokestatic 288	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   356: invokevirtual 291	java/io/File:getPath	()Ljava/lang/String;
    //   359: invokespecial 292	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   362: astore 4
    //   364: aload 4
    //   366: invokevirtual 296	android/os/StatFs:getBlockSize	()I
    //   369: i2l
    //   370: lstore_2
    //   371: aload 5
    //   373: ldc_w 298
    //   376: aload 4
    //   378: invokevirtual 301	android/os/StatFs:getAvailableBlocks	()I
    //   381: i2l
    //   382: lload_2
    //   383: lmul
    //   384: invokestatic 307	java/lang/Long:toString	(J)Ljava/lang/String;
    //   387: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   390: pop
    //   391: aload_0
    //   392: invokevirtual 133	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   395: invokevirtual 311	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   398: getfield 317	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   401: astore 4
    //   403: aload 4
    //   405: ifnull +43 -> 448
    //   408: aload 5
    //   410: ldc_w 319
    //   413: aload 4
    //   415: invokevirtual 324	java/util/Locale:getCountry	()Ljava/lang/String;
    //   418: getstatic 327	java/util/Locale:US	Ljava/util/Locale;
    //   421: invokevirtual 331	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   424: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   427: pop
    //   428: aload 5
    //   430: ldc_w 333
    //   433: aload 4
    //   435: invokevirtual 336	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   438: getstatic 327	java/util/Locale:US	Ljava/util/Locale;
    //   441: invokevirtual 331	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   444: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   447: pop
    //   448: aload 5
    //   450: ldc_w 338
    //   453: aload_0
    //   454: invokestatic 343	com/heyzap/common/net/Connectivity:connectionType	(Landroid/content/Context;)Ljava/lang/String;
    //   457: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   460: pop
    //   461: aload_0
    //   462: invokevirtual 133	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   465: invokevirtual 139	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   468: astore_0
    //   469: aload 5
    //   471: ldc_w 345
    //   474: aload_0
    //   475: getfield 142	android/util/DisplayMetrics:density	F
    //   478: invokestatic 350	java/lang/Float:toString	(F)Ljava/lang/String;
    //   481: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   484: pop
    //   485: aload 5
    //   487: ldc_w 352
    //   490: invokevirtual 356	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   493: ifne +19 -> 512
    //   496: aload 5
    //   498: ldc_w 352
    //   501: aload_0
    //   502: getfield 359	android/util/DisplayMetrics:widthPixels	I
    //   505: invokestatic 362	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   508: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   511: pop
    //   512: aload 5
    //   514: ldc_w 364
    //   517: invokevirtual 356	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   520: ifne +19 -> 539
    //   523: aload 5
    //   525: ldc_w 364
    //   528: aload_0
    //   529: getfield 367	android/util/DisplayMetrics:heightPixels	I
    //   532: invokestatic 362	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   535: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   538: pop
    //   539: aload 5
    //   541: areturn
    //   542: astore 4
    //   544: aload 4
    //   546: invokestatic 373	com/heyzap/internal/Logger:trace	(Ljava/lang/Throwable;)V
    //   549: iconst_0
    //   550: invokestatic 173	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   553: astore 4
    //   555: goto -500 -> 55
    //   558: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   561: ldc -12
    //   563: ldc_w 375
    //   566: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   569: pop
    //   570: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   573: ldc -8
    //   575: ldc_w 375
    //   578: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   581: pop
    //   582: goto -345 -> 237
    //   585: astore_0
    //   586: aload 6
    //   588: monitorexit
    //   589: aload_0
    //   590: athrow
    //   591: ldc_w 377
    //   594: astore 4
    //   596: goto -294 -> 302
    //   599: aload 5
    //   601: ldc_w 257
    //   604: aload_0
    //   605: invokestatic 380	com/heyzap/internal/Utils:getDeviceId	(Landroid/content/Context;)Ljava/lang/String;
    //   608: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   611: pop
    //   612: goto -299 -> 313
    //   615: astore 4
    //   617: aload 5
    //   619: ldc_w 298
    //   622: ldc_w 377
    //   625: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   628: pop
    //   629: goto -238 -> 391
    //   632: ldc_w 382
    //   635: astore 4
    //   637: goto -487 -> 150
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	640	0	paramContext	Context
    //   48	2	1	i	int
    //   370	13	2	l	long
    //   53	381	4	localObject1	Object
    //   542	3	4	localException1	Exception
    //   553	42	4	localObject2	Object
    //   615	1	4	localException2	Exception
    //   635	1	4	str	String
    //   7	611	5	localHashMap	HashMap
    //   12	575	6	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   33	49	542	java/lang/Exception
    //   17	33	585	finally
    //   33	49	585	finally
    //   55	146	585	finally
    //   150	193	585	finally
    //   198	209	585	finally
    //   209	237	585	finally
    //   237	248	585	finally
    //   544	555	585	finally
    //   558	582	585	finally
    //   586	589	585	finally
    //   349	391	615	java/lang/Exception
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
      debug = Boolean.valueOf(packageIsInstalled(Constants.SNAKE_PACKAGE, paramContext));
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
        paramContext = new a(paramContext.getApplicationContext());
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
  }
  
  public static void setLimitAdTracking(Boolean paramBoolean)
  {
    limitAdTrackingEnabled = paramBoolean;
  }
}
