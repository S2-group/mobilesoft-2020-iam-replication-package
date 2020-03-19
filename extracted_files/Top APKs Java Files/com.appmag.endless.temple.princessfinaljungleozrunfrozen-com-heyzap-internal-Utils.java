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
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.util.DisplayMetrics;
import android.view.TouchDelegate;
import android.view.View;
import com.heyzap.sdk.ads.HeyzapAds;
import com.heyzap.sdk.ads.HeyzapAds.AdsConfig;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Utils
{
  private static final String CACHE_DIR = "com.heyzap.sdk";
  public static final String HEYZAP_PACKAGE = "com.heyzap.android";
  private static final String IMAGE_CACHE_DIR = "com.heyzap.sdk.images";
  private static Object advertiserIdLock = new Object();
  private static String advertisingId;
  private static Future<Boolean> advertisingIdAvailable;
  private static HashMap<String, String> cachedParams;
  private static float density = -1.0F;
  private static String deviceId = "unknown";
  private static Boolean limitAdTrackingEnabled;
  private static String packageName = "unknown";
  private static Object paramLock = new Object();
  ExecutorService executorPool = Executors.newCachedThreadPool();
  
  static
  {
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
  
  public static boolean androidVersionSupported()
  {
    try
    {
      int i = Integer.parseInt(Build.VERSION.SDK);
      return i >= 7;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static String capitalize(String paramString)
  {
    return paramString.substring(0, 1).toUpperCase(Locale.US) + paramString.substring(1);
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
  
  public static void clickWrap(Context paramContext, View paramView1, View paramView2)
  {
    clickWrap(paramContext, paramView1, paramView2, 0);
  }
  
  public static void clickWrap(Context paramContext, View paramView1, View paramView2, int paramInt)
  {
    clickWrap(paramContext, paramView1, paramView2, paramInt, paramInt, paramInt, paramInt);
  }
  
  public static void clickWrap(final Context paramContext, View paramView1, final View paramView2, final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4)
  {
    if (paramView1 != null) {
      paramView1.post(new Runnable()
      {
        public void run()
        {
          Rect localRect = new Rect();
          this.val$wrapper.getHitRect(localRect);
          localRect.top -= Utils.getScaledSize(paramContext, paramInt1);
          localRect.right += Utils.getScaledSize(paramContext, paramInt2);
          localRect.bottom += Utils.getScaledSize(paramContext, paramInt3);
          localRect.left -= Utils.getScaledSize(paramContext, paramInt4);
          this.val$wrapper.setTouchDelegate(new TouchDelegate(localRect, paramView2));
        }
      });
    }
  }
  
  private static String convertStreamToString(InputStream paramInputStream)
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      for (;;)
      {
        String str = localBufferedReader.readLine();
        if (str == null) {
          break;
        }
        localStringBuilder.append(str + "\n");
      }
      try
      {
        paramInputStream.close();
        throw localObject;
      }
      catch (IOException paramInputStream)
      {
        for (;;)
        {
          Logger.trace(paramInputStream);
        }
      }
    }
    catch (IOException localIOException)
    {
      localIOException = localIOException;
      Logger.trace(localIOException);
      try
      {
        paramInputStream.close();
        for (;;)
        {
          return localStringBuilder.toString();
          try
          {
            paramInputStream.close();
          }
          catch (IOException paramInputStream)
          {
            Logger.trace(paramInputStream);
          }
        }
      }
      catch (IOException paramInputStream)
      {
        for (;;)
        {
          Logger.trace(paramInputStream);
        }
      }
    }
    finally {}
  }
  
  public static void createCacheDir(Context paramContext)
  {
    paramContext = new File(getCacheDirAbsolutePath(paramContext));
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
  }
  
  public static int daysBetween(Date paramDate1, Date paramDate2)
  {
    return Math.abs((int)((paramDate1.getTime() - paramDate2.getTime()) / 86400000L));
  }
  
  public static void deleteDirectory(File paramFile)
    throws IOException
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
  
  public static Boolean externalStorageIsAvailableAndWritable()
  {
    boolean bool = true;
    String str = Environment.getExternalStorageState();
    int i;
    int j;
    if ("mounted".equals(str))
    {
      i = 1;
      j = 1;
      if ((j == 0) || (i == 0)) {
        break label57;
      }
    }
    for (;;)
    {
      return Boolean.valueOf(bool);
      if ("mounted_ro".equals(str))
      {
        i = 0;
        j = 1;
        break;
      }
      i = 0;
      j = 0;
      break;
      label57:
      bool = false;
    }
  }
  
  /* Error */
  static HashMap<String, String> extraParams(Context paramContext)
  {
    // Byte code:
    //   0: new 285	java/util/HashMap
    //   3: dup
    //   4: invokespecial 286	java/util/HashMap:<init>	()V
    //   7: astore 5
    //   9: getstatic 55	com/heyzap/internal/Utils:paramLock	Ljava/lang/Object;
    //   12: astore 6
    //   14: aload 6
    //   16: monitorenter
    //   17: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   20: ifnonnull +219 -> 239
    //   23: new 285	java/util/HashMap
    //   26: dup
    //   27: invokespecial 286	java/util/HashMap:<init>	()V
    //   30: putstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   33: aload_0
    //   34: invokevirtual 289	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   37: aload_0
    //   38: invokevirtual 292	android/content/Context:getPackageName	()Ljava/lang/String;
    //   41: iconst_0
    //   42: invokevirtual 298	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   45: getfield 304	android/content/pm/PackageInfo:versionCode	I
    //   48: istore_1
    //   49: iload_1
    //   50: invokestatic 307	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   53: astore 4
    //   55: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   58: ldc_w 309
    //   61: aload 4
    //   63: invokestatic 312	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   66: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   69: pop
    //   70: aload_0
    //   71: invokevirtual 257	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   74: invokevirtual 263	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   77: pop
    //   78: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   81: ldc_w 318
    //   84: ldc_w 320
    //   87: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   90: pop
    //   91: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   94: ldc_w 322
    //   97: getstatic 113	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   100: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   103: pop
    //   104: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   107: ldc_w 324
    //   110: aload_0
    //   111: invokestatic 326	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   114: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   117: pop
    //   118: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   121: ldc_w 328
    //   124: aload_0
    //   125: invokestatic 326	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   128: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   131: pop
    //   132: aload_0
    //   133: invokestatic 332	com/heyzap/internal/Utils:isTablet	(Landroid/content/Context;)Z
    //   136: ifeq +500 -> 636
    //   139: ldc_w 334
    //   142: astore 4
    //   144: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   147: ldc_w 336
    //   150: aload 4
    //   152: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   155: pop
    //   156: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   159: ldc_w 338
    //   162: getstatic 343	android/os/Build:MODEL	Ljava/lang/String;
    //   165: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   168: pop
    //   169: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   172: ldc_w 345
    //   175: getstatic 348	android/os/Build:DEVICE	Ljava/lang/String;
    //   178: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   181: pop
    //   182: getstatic 354	com/heyzap/sdk/ads/HeyzapAds:config	Lcom/heyzap/sdk/ads/HeyzapAds$AdsConfig;
    //   185: getfield 359	com/heyzap/sdk/ads/HeyzapAds$AdsConfig:publisherId	Ljava/lang/String;
    //   188: astore 4
    //   190: aload 4
    //   192: ifnull +15 -> 207
    //   195: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   198: ldc_w 361
    //   201: aload 4
    //   203: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   206: pop
    //   207: invokestatic 364	com/heyzap/internal/Utils:isAmazon	()Z
    //   210: ifeq +350 -> 560
    //   213: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   216: ldc_w 366
    //   219: ldc_w 368
    //   222: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   225: pop
    //   226: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   229: ldc_w 370
    //   232: ldc_w 368
    //   235: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   238: pop
    //   239: aload 5
    //   241: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   244: invokevirtual 374	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   247: aload 6
    //   249: monitorexit
    //   250: aload_0
    //   251: invokestatic 377	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   254: ifnull +349 -> 603
    //   257: invokestatic 364	com/heyzap/internal/Utils:isAmazon	()Z
    //   260: ifne +343 -> 603
    //   263: aload 5
    //   265: ldc_w 379
    //   268: aload_0
    //   269: invokestatic 377	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   272: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   275: pop
    //   276: aload 5
    //   278: ldc_w 381
    //   281: aload_0
    //   282: invokestatic 377	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   285: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   288: pop
    //   289: aload_0
    //   290: invokestatic 385	com/heyzap/internal/Utils:getLimitAdTrackingEnabled	(Landroid/content/Context;)Ljava/lang/Boolean;
    //   293: invokevirtual 388	java/lang/Boolean:booleanValue	()Z
    //   296: ifne +299 -> 595
    //   299: ldc_w 390
    //   302: astore 4
    //   304: aload 5
    //   306: ldc_w 392
    //   309: aload 4
    //   311: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   314: pop
    //   315: getstatic 395	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   318: ifnull +15 -> 333
    //   321: aload 5
    //   323: ldc_w 397
    //   326: getstatic 395	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   329: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   332: pop
    //   333: getstatic 400	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   336: ifnull +15 -> 351
    //   339: aload 5
    //   341: ldc_w 402
    //   344: getstatic 400	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   347: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   350: pop
    //   351: new 404	android/os/StatFs
    //   354: dup
    //   355: invokestatic 408	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   358: invokevirtual 411	java/io/File:getPath	()Ljava/lang/String;
    //   361: invokespecial 412	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   364: astore 4
    //   366: aload 4
    //   368: invokevirtual 416	android/os/StatFs:getBlockSize	()I
    //   371: i2l
    //   372: lstore_2
    //   373: aload 5
    //   375: ldc_w 418
    //   378: aload 4
    //   380: invokevirtual 421	android/os/StatFs:getAvailableBlocks	()I
    //   383: i2l
    //   384: lload_2
    //   385: lmul
    //   386: invokestatic 426	java/lang/Long:toString	(J)Ljava/lang/String;
    //   389: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   392: pop
    //   393: aload_0
    //   394: invokevirtual 257	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   397: invokevirtual 430	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   400: getfield 435	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   403: astore 4
    //   405: aload 4
    //   407: ifnull +43 -> 450
    //   410: aload 5
    //   412: ldc_w 437
    //   415: aload 4
    //   417: invokevirtual 440	java/util/Locale:getCountry	()Ljava/lang/String;
    //   420: getstatic 136	java/util/Locale:US	Ljava/util/Locale;
    //   423: invokevirtual 443	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   426: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   429: pop
    //   430: aload 5
    //   432: ldc_w 445
    //   435: aload 4
    //   437: invokevirtual 448	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   440: getstatic 136	java/util/Locale:US	Ljava/util/Locale;
    //   443: invokevirtual 443	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   446: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   449: pop
    //   450: aload 5
    //   452: ldc_w 450
    //   455: aload_0
    //   456: invokestatic 455	com/heyzap/internal/Connectivity:connectionType	(Landroid/content/Context;)Ljava/lang/String;
    //   459: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   462: pop
    //   463: aload_0
    //   464: invokevirtual 257	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   467: invokevirtual 263	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   470: astore_0
    //   471: aload 5
    //   473: ldc_w 457
    //   476: aload_0
    //   477: getfield 266	android/util/DisplayMetrics:density	F
    //   480: invokestatic 462	java/lang/Float:toString	(F)Ljava/lang/String;
    //   483: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   486: pop
    //   487: aload 5
    //   489: ldc_w 464
    //   492: invokevirtual 467	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   495: ifne +19 -> 514
    //   498: aload 5
    //   500: ldc_w 464
    //   503: aload_0
    //   504: getfield 470	android/util/DisplayMetrics:widthPixels	I
    //   507: invokestatic 472	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   510: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   513: pop
    //   514: aload 5
    //   516: ldc_w 474
    //   519: invokevirtual 467	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   522: ifne +19 -> 541
    //   525: aload 5
    //   527: ldc_w 474
    //   530: aload_0
    //   531: getfield 477	android/util/DisplayMetrics:heightPixels	I
    //   534: invokestatic 472	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   537: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   540: pop
    //   541: aload 5
    //   543: areturn
    //   544: astore 4
    //   546: aload 4
    //   548: invokestatic 195	com/heyzap/internal/Logger:trace	(Ljava/lang/Throwable;)V
    //   551: iconst_0
    //   552: invokestatic 307	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   555: astore 4
    //   557: goto -502 -> 55
    //   560: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   563: ldc_w 366
    //   566: ldc_w 479
    //   569: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   572: pop
    //   573: getstatic 288	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   576: ldc_w 370
    //   579: ldc_w 479
    //   582: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   585: pop
    //   586: goto -347 -> 239
    //   589: astore_0
    //   590: aload 6
    //   592: monitorexit
    //   593: aload_0
    //   594: athrow
    //   595: ldc_w 481
    //   598: astore 4
    //   600: goto -296 -> 304
    //   603: aload 5
    //   605: ldc_w 379
    //   608: aload_0
    //   609: invokestatic 484	com/heyzap/internal/Utils:getDeviceId	(Landroid/content/Context;)Ljava/lang/String;
    //   612: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   615: pop
    //   616: goto -301 -> 315
    //   619: astore 4
    //   621: aload 5
    //   623: ldc_w 418
    //   626: ldc_w 481
    //   629: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   632: pop
    //   633: goto -240 -> 393
    //   636: ldc_w 486
    //   639: astore 4
    //   641: goto -497 -> 144
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	644	0	paramContext	Context
    //   48	2	1	i	int
    //   372	13	2	l	long
    //   53	383	4	localObject1	Object
    //   544	3	4	localException1	Exception
    //   555	44	4	localObject2	Object
    //   619	1	4	localException2	Exception
    //   639	1	4	str	String
    //   7	615	5	localHashMap	HashMap
    //   12	579	6	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   33	49	544	java/lang/Exception
    //   17	33	589	finally
    //   33	49	589	finally
    //   55	139	589	finally
    //   144	190	589	finally
    //   195	207	589	finally
    //   207	239	589	finally
    //   239	250	589	finally
    //   546	557	589	finally
    //   560	586	589	finally
    //   590	593	589	finally
    //   351	393	619	java/lang/Exception
  }
  
  public static String getAbsolutePath(Context paramContext, String paramString)
  {
    return String.format("%s/files/%s", new Object[] { paramContext.getCacheDir().getAbsolutePath(), paramString });
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
      for (;;)
      {
        Logger.trace(paramContext);
      }
    }
  }
  
  public static String getAppLabel(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationLabel(paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0));
      if (paramContext == null) {
        return null;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
    return paramContext.toString();
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
  
  public static String getDeviceId(Context paramContext)
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
  
  public static int getInverseScaledSize(Context paramContext, float paramFloat)
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
      for (;;)
      {
        Logger.trace(paramContext);
      }
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
  
  public static int getScaledSize(Context paramContext, float paramFloat)
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
  
  public static int getSdkVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String getSignatureHash(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures[0].toByteArray();
      paramContext = Base64.encodeToString(MessageDigest.getInstance("SHA-1").digest(paramContext), 2);
      return paramContext;
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      Logger.trace(paramContext);
      return "";
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Logger.trace(paramContext);
    }
    return "";
  }
  
  public static int getStatusBarHeight(Context paramContext)
  {
    int i = 0;
    int j = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (j > 0) {
      i = paramContext.getResources().getDimensionPixelSize(j);
    }
    return i;
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
  
  public static String hexString()
  {
    Random localRandom = new Random();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < 20)
    {
      localStringBuilder.append("ABCDEF1234567890".charAt(localRandom.nextInt("ABCDEF1234567890".length())));
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static boolean heyzapIsInstalled(Context paramContext)
  {
    return packageIsInstalled("com.heyzap.android", paramContext);
  }
  
  private static boolean isAirplaneModeOn(Context paramContext)
  {
    boolean bool = false;
    if (Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) != 0) {
      bool = true;
    }
    return bool;
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
    return Boolean.valueOf(packageIsInstalled(Constants.SNAKE_PACKAGE, paramContext));
  }
  
  public static Boolean isExpired(Long paramLong, Integer paramInteger)
  {
    if (paramInteger.intValue() < System.currentTimeMillis() - paramLong.longValue()) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  public static boolean isFroyo()
  {
    return getSdkVersion() >= 8;
  }
  
  public static boolean isGingerbread()
  {
    return getSdkVersion() >= 9;
  }
  
  public static boolean isHoneycomb()
  {
    return getSdkVersion() >= 11;
  }
  
  public static boolean isHoneycombTablet(Context paramContext)
  {
    return (isHoneycomb()) && (isTablet(paramContext));
  }
  
  public static Boolean isOnMainThread()
  {
    if (Looper.getMainLooper() == Looper.myLooper()) {}
    for (boolean bool = true;; bool = false) {
      return Boolean.valueOf(bool);
    }
  }
  
  public static boolean isOnline(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    return (localConnectivityManager.getActiveNetworkInfo() != null) && (localConnectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()) && (!isAirplaneModeOn(paramContext));
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
  
  public static boolean marketInstalled(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.heyzap.android"));
      boolean bool = paramContext.getPackageManager().queryIntentActivities(localIntent, 65536).isEmpty();
      return !bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static String md5Hex(String paramString)
  {
    try
    {
      paramString = hex(MessageDigest.getInstance("MD5").digest(paramString.getBytes("CP1252")));
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      return null;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      for (;;) {}
    }
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
  
  public static void runOnMainThreadAndBlock(final Activity paramActivity, Integer paramInteger, Runnable paramRunnable)
    throws TimeoutException, ExecutionException, InterruptedException
  {
    if ((Looper.myLooper() != null) && (Looper.getMainLooper() == Looper.myLooper())) {
      paramRunnable.run();
    }
    paramActivity = SettableFuture.create();
    paramRunnable = new Runnable()
    {
      public void run()
      {
        try
        {
          this.val$runnable.run();
          return;
        }
        catch (Exception localException)
        {
          paramActivity.setException(localException);
          return;
        }
        finally
        {
          paramActivity.set(Boolean.valueOf(true));
        }
      }
    };
    new Handler(Looper.getMainLooper()).post(paramRunnable);
    paramActivity.get(paramInteger.intValue(), TimeUnit.MILLISECONDS);
  }
  
  public static String saveBitmapToLocalFile(Context paramContext, String paramString, Bitmap paramBitmap)
  {
    if (paramString == null) {
      try
      {
        throw new Exception("No filename.");
      }
      catch (Exception paramContext)
      {
        Logger.trace(paramContext);
        return null;
      }
    }
    if (paramBitmap == null) {
      throw new Exception("No image.");
    }
    String str = getAbsolutePath(paramContext, paramString);
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 0, localByteArrayOutputStream);
    paramContext = paramContext.openFileOutput(paramString, 3);
    if (paramContext != null)
    {
      paramContext.write(localByteArrayOutputStream.toByteArray());
      return str;
    }
    throw new Exception("Unable to open output file stream.");
  }
  
  public static void setAdvertisingId(String paramString)
  {
    advertisingId = paramString;
  }
  
  public static void setLimitAdTracking(Boolean paramBoolean)
  {
    limitAdTrackingEnabled = paramBoolean;
  }
  
  public static String truncate(String paramString, int paramInt)
  {
    String str = paramString;
    if (paramString.length() > paramInt) {
      str = paramString.substring(0, paramInt) + "...";
    }
    return str;
  }
  
  public ExecutorService getExecutorPool()
  {
    return this.executorPool;
  }
  
  static abstract interface ResponseHandler
  {
    public abstract void onFailure(Throwable paramThrowable);
    
    public abstract void onSuccess(String paramString);
  }
}
