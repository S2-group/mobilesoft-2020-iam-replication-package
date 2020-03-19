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
import com.heyzap.common.concurrency.ExecutorPool;
import com.heyzap.common.concurrency.SettableFuture;
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
  private static Boolean debug;
  private static float density = -1.0F;
  private static String deviceId;
  public static String gameName;
  private static Boolean limitAdTrackingEnabled;
  public static String packageName;
  private static Object paramLock;
  ExecutorService executorPool = ExecutorPool.getInstance();
  
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
  
  public static boolean androidVersionSupported()
  {
    try
    {
      int i = Integer.parseInt(Build.VERSION.SDK);
      if (i < 7) {
        return false;
      }
    }
    catch (Exception localException)
    {
      return false;
    }
    return true;
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
    String str = Environment.getExternalStorageState();
    int j;
    int i;
    if ("mounted".equals(str))
    {
      j = 1;
      i = 1;
      if ((i == 0) || (j == 0)) {
        break label57;
      }
    }
    label57:
    for (boolean bool = true;; bool = false)
    {
      return Boolean.valueOf(bool);
      if ("mounted_ro".equals(str))
      {
        i = 1;
        j = 0;
        break;
      }
      j = 0;
      i = 0;
      break;
    }
  }
  
  /* Error */
  public static HashMap<String, String> extraParams(Context paramContext)
  {
    // Byte code:
    //   0: new 291	java/util/HashMap
    //   3: dup
    //   4: invokespecial 292	java/util/HashMap:<init>	()V
    //   7: astore_3
    //   8: getstatic 61	com/heyzap/internal/Utils:paramLock	Ljava/lang/Object;
    //   11: astore 4
    //   13: aload 4
    //   15: monitorenter
    //   16: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   19: ifnonnull +231 -> 250
    //   22: new 291	java/util/HashMap
    //   25: dup
    //   26: invokespecial 292	java/util/HashMap:<init>	()V
    //   29: putstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   32: iconst_0
    //   33: invokestatic 297	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   36: astore_2
    //   37: aload_0
    //   38: invokevirtual 298	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   41: aload_0
    //   42: invokevirtual 301	android/content/Context:getPackageName	()Ljava/lang/String;
    //   45: iconst_0
    //   46: invokevirtual 307	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   49: getfield 313	android/content/pm/PackageInfo:versionCode	I
    //   52: istore_1
    //   53: iload_1
    //   54: invokestatic 297	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   57: astore_2
    //   58: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   61: ldc_w 315
    //   64: aload_2
    //   65: invokestatic 318	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   68: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   71: pop
    //   72: aload_0
    //   73: invokevirtual 263	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   76: invokevirtual 269	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   79: pop
    //   80: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   83: ldc_w 324
    //   86: ldc_w 326
    //   89: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   92: pop
    //   93: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   96: ldc_w 328
    //   99: getstatic 119	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   102: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   105: pop
    //   106: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   109: ldc_w 330
    //   112: aload_0
    //   113: invokestatic 332	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   116: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   119: pop
    //   120: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   123: ldc_w 334
    //   126: aload_0
    //   127: invokestatic 332	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   130: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   133: pop
    //   134: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   137: ldc_w 336
    //   140: aload_0
    //   141: invokestatic 339	com/heyzap/internal/Utils:getAppName	(Landroid/content/Context;)Ljava/lang/String;
    //   144: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   147: pop
    //   148: aload_0
    //   149: invokestatic 343	com/heyzap/internal/Utils:isTablet	(Landroid/content/Context;)Z
    //   152: ifeq +380 -> 532
    //   155: ldc_w 345
    //   158: astore_2
    //   159: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   162: ldc_w 347
    //   165: aload_2
    //   166: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   169: pop
    //   170: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   173: ldc_w 349
    //   176: getstatic 354	android/os/Build:MODEL	Ljava/lang/String;
    //   179: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   182: pop
    //   183: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   186: ldc_w 356
    //   189: getstatic 359	android/os/Build:DEVICE	Ljava/lang/String;
    //   192: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   195: pop
    //   196: getstatic 365	com/heyzap/sdk/ads/HeyzapAds:config	Lcom/heyzap/sdk/ads/HeyzapAds$AdsConfig;
    //   199: getfield 370	com/heyzap/sdk/ads/HeyzapAds$AdsConfig:publisherId	Ljava/lang/String;
    //   202: astore_2
    //   203: aload_2
    //   204: ifnull +14 -> 218
    //   207: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   210: ldc_w 372
    //   213: aload_2
    //   214: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   217: pop
    //   218: invokestatic 375	com/heyzap/internal/Utils:isAmazon	()Z
    //   221: ifeq +318 -> 539
    //   224: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   227: ldc_w 377
    //   230: ldc_w 379
    //   233: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   236: pop
    //   237: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   240: ldc_w 381
    //   243: ldc_w 379
    //   246: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   249: pop
    //   250: aload_3
    //   251: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   254: invokevirtual 385	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   257: aload 4
    //   259: monitorexit
    //   260: aload_0
    //   261: invokestatic 388	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   264: ifnull +311 -> 575
    //   267: invokestatic 375	com/heyzap/internal/Utils:isAmazon	()Z
    //   270: ifne +305 -> 575
    //   273: aload_3
    //   274: ldc_w 390
    //   277: aload_0
    //   278: invokestatic 388	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   281: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   284: pop
    //   285: aload_3
    //   286: ldc_w 392
    //   289: aload_0
    //   290: invokestatic 388	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   293: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   296: pop
    //   297: aload_0
    //   298: invokestatic 396	com/heyzap/internal/Utils:getLimitAdTrackingEnabled	(Landroid/content/Context;)Ljava/lang/Boolean;
    //   301: invokevirtual 399	java/lang/Boolean:booleanValue	()Z
    //   304: ifne +264 -> 568
    //   307: ldc_w 401
    //   310: astore_2
    //   311: aload_3
    //   312: ldc_w 403
    //   315: aload_2
    //   316: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   319: pop
    //   320: getstatic 406	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   323: ifnull +14 -> 337
    //   326: aload_3
    //   327: ldc_w 408
    //   330: getstatic 406	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   333: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   336: pop
    //   337: getstatic 411	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   340: ifnull +14 -> 354
    //   343: aload_3
    //   344: ldc_w 413
    //   347: getstatic 411	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   350: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   353: pop
    //   354: new 415	android/os/StatFs
    //   357: dup
    //   358: invokestatic 419	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   361: invokevirtual 422	java/io/File:getPath	()Ljava/lang/String;
    //   364: invokespecial 423	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   367: astore_2
    //   368: aload_3
    //   369: ldc_w 425
    //   372: aload_2
    //   373: invokevirtual 429	android/os/StatFs:getBlockSize	()I
    //   376: i2l
    //   377: aload_2
    //   378: invokevirtual 432	android/os/StatFs:getAvailableBlocks	()I
    //   381: i2l
    //   382: lmul
    //   383: invokestatic 437	java/lang/Long:toString	(J)Ljava/lang/String;
    //   386: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   389: pop
    //   390: aload_0
    //   391: invokevirtual 263	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   394: invokevirtual 441	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   397: getfield 446	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   400: astore_2
    //   401: aload_2
    //   402: ifnull +39 -> 441
    //   405: aload_3
    //   406: ldc_w 448
    //   409: aload_2
    //   410: invokevirtual 451	java/util/Locale:getCountry	()Ljava/lang/String;
    //   413: getstatic 142	java/util/Locale:US	Ljava/util/Locale;
    //   416: invokevirtual 454	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   419: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   422: pop
    //   423: aload_3
    //   424: ldc_w 456
    //   427: aload_2
    //   428: invokevirtual 459	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   431: getstatic 142	java/util/Locale:US	Ljava/util/Locale;
    //   434: invokevirtual 454	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   437: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   440: pop
    //   441: aload_0
    //   442: invokevirtual 263	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   445: invokevirtual 269	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   448: astore_0
    //   449: aload_3
    //   450: ldc_w 461
    //   453: aload_0
    //   454: getfield 272	android/util/DisplayMetrics:density	F
    //   457: invokestatic 466	java/lang/Float:toString	(F)Ljava/lang/String;
    //   460: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   463: pop
    //   464: aload_3
    //   465: ldc_w 468
    //   468: invokevirtual 471	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   471: ifne +18 -> 489
    //   474: aload_3
    //   475: ldc_w 468
    //   478: aload_0
    //   479: getfield 474	android/util/DisplayMetrics:widthPixels	I
    //   482: invokestatic 476	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   485: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   488: pop
    //   489: aload_3
    //   490: ldc_w 478
    //   493: invokevirtual 471	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   496: ifne +18 -> 514
    //   499: aload_3
    //   500: ldc_w 478
    //   503: aload_0
    //   504: getfield 481	android/util/DisplayMetrics:heightPixels	I
    //   507: invokestatic 476	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   510: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   513: pop
    //   514: aload_3
    //   515: areturn
    //   516: astore 5
    //   518: aload 5
    //   520: invokestatic 201	com/heyzap/internal/Logger:trace	(Ljava/lang/Throwable;)V
    //   523: goto -465 -> 58
    //   526: astore_0
    //   527: aload 4
    //   529: monitorexit
    //   530: aload_0
    //   531: athrow
    //   532: ldc_w 483
    //   535: astore_2
    //   536: goto -377 -> 159
    //   539: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   542: ldc_w 377
    //   545: ldc_w 485
    //   548: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   551: pop
    //   552: getstatic 294	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   555: ldc_w 381
    //   558: ldc_w 485
    //   561: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   564: pop
    //   565: goto -315 -> 250
    //   568: ldc_w 487
    //   571: astore_2
    //   572: goto -261 -> 311
    //   575: aload_3
    //   576: ldc_w 390
    //   579: aload_0
    //   580: invokestatic 490	com/heyzap/internal/Utils:getDeviceId	(Landroid/content/Context;)Ljava/lang/String;
    //   583: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   586: pop
    //   587: goto -267 -> 320
    //   590: astore_2
    //   591: aload_3
    //   592: ldc_w 425
    //   595: ldc_w 487
    //   598: invokevirtual 322	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   601: pop
    //   602: goto -212 -> 390
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
    //   58	155	526	finally
    //   159	203	526	finally
    //   207	218	526	finally
    //   218	250	526	finally
    //   250	260	526	finally
    //   518	523	526	finally
    //   527	530	526	finally
    //   539	565	526	finally
    //   354	390	590	java/lang/Exception
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
    Object localObject = null;
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationLabel(paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0));
      if (paramContext == null) {
        return null;
      }
      return paramContext.toString();
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = localObject;
      }
    }
  }
  
  public static String getAppName(Context paramContext)
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
      if (bool) {
        return false;
      }
    }
    catch (Exception paramContext)
    {
      return false;
    }
    return true;
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
  
  public static void setDebug(boolean paramBoolean)
  {
    debug = Boolean.valueOf(paramBoolean);
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
