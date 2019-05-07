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
    //   7: astore_3
    //   8: getstatic 49	com/heyzap/internal/Utils:paramLock	Ljava/lang/Object;
    //   11: astore 4
    //   13: aload 4
    //   15: monitorenter
    //   16: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   19: ifnonnull +215 -> 234
    //   22: new 148	java/util/HashMap
    //   25: dup
    //   26: invokespecial 149	java/util/HashMap:<init>	()V
    //   29: putstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   32: iconst_0
    //   33: invokestatic 156	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   36: astore_2
    //   37: aload_0
    //   38: invokevirtual 157	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   41: aload_0
    //   42: invokevirtual 161	android/content/Context:getPackageName	()Ljava/lang/String;
    //   45: iconst_0
    //   46: invokevirtual 167	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   49: getfield 173	android/content/pm/PackageInfo:versionCode	I
    //   52: istore_1
    //   53: iload_1
    //   54: invokestatic 156	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   57: astore_2
    //   58: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   61: ldc -81
    //   63: aload_2
    //   64: invokestatic 180	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   67: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   70: pop
    //   71: aload_0
    //   72: invokevirtual 133	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   75: invokevirtual 139	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   78: pop
    //   79: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   82: ldc -70
    //   84: ldc -68
    //   86: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   89: pop
    //   90: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   93: ldc -66
    //   95: getstatic 195	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   98: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   101: pop
    //   102: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   105: ldc -59
    //   107: aload_0
    //   108: invokestatic 199	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   111: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: pop
    //   115: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   118: ldc -55
    //   120: aload_0
    //   121: invokestatic 199	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   124: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   127: pop
    //   128: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   131: ldc -53
    //   133: aload_0
    //   134: invokestatic 206	com/heyzap/internal/Utils:getAppName	(Landroid/content/Context;)Ljava/lang/String;
    //   137: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   140: pop
    //   141: aload_0
    //   142: invokestatic 210	com/heyzap/internal/Utils:isTablet	(Landroid/content/Context;)Z
    //   145: ifeq +383 -> 528
    //   148: ldc -44
    //   150: astore_2
    //   151: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   154: ldc -42
    //   156: aload_2
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
    //   191: astore_2
    //   192: aload_2
    //   193: ifnull +13 -> 206
    //   196: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   199: ldc -17
    //   201: aload_2
    //   202: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   205: pop
    //   206: invokestatic 242	com/heyzap/internal/Utils:isAmazon	()Z
    //   209: ifeq +326 -> 535
    //   212: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   215: ldc -12
    //   217: ldc -10
    //   219: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   222: pop
    //   223: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   226: ldc -8
    //   228: ldc -10
    //   230: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   233: pop
    //   234: aload_3
    //   235: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   238: invokevirtual 252	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   241: aload 4
    //   243: monitorexit
    //   244: aload_0
    //   245: invokestatic 255	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   248: ifnull +321 -> 569
    //   251: invokestatic 242	com/heyzap/internal/Utils:isAmazon	()Z
    //   254: ifne +315 -> 569
    //   257: aload_3
    //   258: ldc_w 257
    //   261: aload_0
    //   262: invokestatic 255	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   265: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   268: pop
    //   269: aload_3
    //   270: ldc_w 259
    //   273: aload_0
    //   274: invokestatic 255	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   277: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   280: pop
    //   281: aload_0
    //   282: invokestatic 263	com/heyzap/internal/Utils:getLimitAdTrackingEnabled	(Landroid/content/Context;)Ljava/lang/Boolean;
    //   285: invokevirtual 266	java/lang/Boolean:booleanValue	()Z
    //   288: ifne +274 -> 562
    //   291: ldc_w 268
    //   294: astore_2
    //   295: aload_3
    //   296: ldc_w 270
    //   299: aload_2
    //   300: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   303: pop
    //   304: getstatic 273	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   307: ifnull +14 -> 321
    //   310: aload_3
    //   311: ldc_w 275
    //   314: getstatic 273	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   317: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   320: pop
    //   321: getstatic 278	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   324: ifnull +14 -> 338
    //   327: aload_3
    //   328: ldc_w 280
    //   331: getstatic 278	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   334: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   337: pop
    //   338: new 282	android/os/StatFs
    //   341: dup
    //   342: invokestatic 288	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   345: invokevirtual 291	java/io/File:getPath	()Ljava/lang/String;
    //   348: invokespecial 292	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   351: astore_2
    //   352: aload_3
    //   353: ldc_w 294
    //   356: aload_2
    //   357: invokevirtual 298	android/os/StatFs:getBlockSize	()I
    //   360: i2l
    //   361: aload_2
    //   362: invokevirtual 301	android/os/StatFs:getAvailableBlocks	()I
    //   365: i2l
    //   366: lmul
    //   367: invokestatic 307	java/lang/Long:toString	(J)Ljava/lang/String;
    //   370: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   373: pop
    //   374: aload_0
    //   375: invokevirtual 133	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   378: invokevirtual 311	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   381: getfield 317	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   384: astore_2
    //   385: aload_2
    //   386: ifnull +39 -> 425
    //   389: aload_3
    //   390: ldc_w 319
    //   393: aload_2
    //   394: invokevirtual 324	java/util/Locale:getCountry	()Ljava/lang/String;
    //   397: getstatic 327	java/util/Locale:US	Ljava/util/Locale;
    //   400: invokevirtual 331	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   403: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   406: pop
    //   407: aload_3
    //   408: ldc_w 333
    //   411: aload_2
    //   412: invokevirtual 336	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   415: getstatic 327	java/util/Locale:US	Ljava/util/Locale;
    //   418: invokevirtual 331	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   421: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   424: pop
    //   425: aload_3
    //   426: ldc_w 338
    //   429: aload_0
    //   430: invokestatic 343	com/heyzap/common/net/Connectivity:connectionType	(Landroid/content/Context;)Ljava/lang/String;
    //   433: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   436: pop
    //   437: aload_0
    //   438: invokevirtual 133	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   441: invokevirtual 139	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   444: astore_0
    //   445: aload_3
    //   446: ldc_w 345
    //   449: aload_0
    //   450: getfield 142	android/util/DisplayMetrics:density	F
    //   453: invokestatic 350	java/lang/Float:toString	(F)Ljava/lang/String;
    //   456: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   459: pop
    //   460: aload_3
    //   461: ldc_w 352
    //   464: invokevirtual 356	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   467: ifne +18 -> 485
    //   470: aload_3
    //   471: ldc_w 352
    //   474: aload_0
    //   475: getfield 359	android/util/DisplayMetrics:widthPixels	I
    //   478: invokestatic 362	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   481: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   484: pop
    //   485: aload_3
    //   486: ldc_w 364
    //   489: invokevirtual 356	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   492: ifne +18 -> 510
    //   495: aload_3
    //   496: ldc_w 364
    //   499: aload_0
    //   500: getfield 367	android/util/DisplayMetrics:heightPixels	I
    //   503: invokestatic 362	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   506: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   509: pop
    //   510: aload_3
    //   511: areturn
    //   512: astore 5
    //   514: aload 5
    //   516: invokestatic 373	com/heyzap/internal/Logger:trace	(Ljava/lang/Throwable;)V
    //   519: goto -461 -> 58
    //   522: astore_0
    //   523: aload 4
    //   525: monitorexit
    //   526: aload_0
    //   527: athrow
    //   528: ldc_w 375
    //   531: astore_2
    //   532: goto -381 -> 151
    //   535: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   538: ldc -12
    //   540: ldc_w 377
    //   543: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   546: pop
    //   547: getstatic 151	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   550: ldc -8
    //   552: ldc_w 377
    //   555: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   558: pop
    //   559: goto -325 -> 234
    //   562: ldc_w 379
    //   565: astore_2
    //   566: goto -271 -> 295
    //   569: aload_3
    //   570: ldc_w 257
    //   573: aload_0
    //   574: invokestatic 382	com/heyzap/internal/Utils:getDeviceId	(Landroid/content/Context;)Ljava/lang/String;
    //   577: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   580: pop
    //   581: goto -277 -> 304
    //   584: astore_2
    //   585: aload_3
    //   586: ldc_w 294
    //   589: ldc_w 379
    //   592: invokevirtual 184	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   595: pop
    //   596: goto -222 -> 374
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	599	0	paramContext	Context
    //   52	2	1	i	int
    //   36	530	2	localObject1	Object
    //   584	1	2	localException1	Exception
    //   7	579	3	localHashMap	HashMap
    //   11	513	4	localObject2	Object
    //   512	3	5	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   37	53	512	java/lang/Exception
    //   16	32	522	finally
    //   37	53	522	finally
    //   58	148	522	finally
    //   151	192	522	finally
    //   196	206	522	finally
    //   206	234	522	finally
    //   234	244	522	finally
    //   514	519	522	finally
    //   523	526	522	finally
    //   535	559	522	finally
    //   338	374	584	java/lang/Exception
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
