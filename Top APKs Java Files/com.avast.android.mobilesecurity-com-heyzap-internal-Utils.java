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
          this.a.getHitRect(localRect);
          localRect.top -= Utils.getScaledSize(paramContext, paramInt1);
          localRect.right += Utils.getScaledSize(paramContext, paramInt2);
          localRect.bottom += Utils.getScaledSize(paramContext, paramInt3);
          localRect.left -= Utils.getScaledSize(paramContext, paramInt4);
          this.a.setTouchDelegate(new TouchDelegate(localRect, paramView2));
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
  public static HashMap<String, String> extraParams(Context paramContext)
  {
    // Byte code:
    //   0: new 288	java/util/HashMap
    //   3: dup
    //   4: invokespecial 289	java/util/HashMap:<init>	()V
    //   7: astore 5
    //   9: getstatic 58	com/heyzap/internal/Utils:paramLock	Ljava/lang/Object;
    //   12: astore 6
    //   14: aload 6
    //   16: monitorenter
    //   17: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   20: ifnonnull +233 -> 253
    //   23: new 288	java/util/HashMap
    //   26: dup
    //   27: invokespecial 289	java/util/HashMap:<init>	()V
    //   30: putstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   33: aload_0
    //   34: invokevirtual 292	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   37: aload_0
    //   38: invokevirtual 295	android/content/Context:getPackageName	()Ljava/lang/String;
    //   41: iconst_0
    //   42: invokevirtual 301	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   45: getfield 307	android/content/pm/PackageInfo:versionCode	I
    //   48: istore_1
    //   49: iload_1
    //   50: invokestatic 310	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   53: astore 4
    //   55: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   58: ldc_w 312
    //   61: aload 4
    //   63: invokestatic 315	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   66: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   69: pop
    //   70: aload_0
    //   71: invokevirtual 260	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   74: invokevirtual 266	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   77: pop
    //   78: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   81: ldc_w 321
    //   84: ldc_w 323
    //   87: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   90: pop
    //   91: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   94: ldc_w 325
    //   97: getstatic 116	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   100: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   103: pop
    //   104: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   107: ldc_w 327
    //   110: aload_0
    //   111: invokestatic 329	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   114: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   117: pop
    //   118: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   121: ldc_w 331
    //   124: aload_0
    //   125: invokestatic 329	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   128: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   131: pop
    //   132: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   135: ldc_w 333
    //   138: aload_0
    //   139: invokestatic 336	com/heyzap/internal/Utils:getAppName	(Landroid/content/Context;)Ljava/lang/String;
    //   142: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   145: pop
    //   146: aload_0
    //   147: invokestatic 340	com/heyzap/internal/Utils:isTablet	(Landroid/content/Context;)Z
    //   150: ifeq +500 -> 650
    //   153: ldc_w 342
    //   156: astore 4
    //   158: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   161: ldc_w 344
    //   164: aload 4
    //   166: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   169: pop
    //   170: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   173: ldc_w 346
    //   176: getstatic 351	android/os/Build:MODEL	Ljava/lang/String;
    //   179: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   182: pop
    //   183: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   186: ldc_w 353
    //   189: getstatic 356	android/os/Build:DEVICE	Ljava/lang/String;
    //   192: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   195: pop
    //   196: getstatic 362	com/heyzap/sdk/ads/HeyzapAds:config	Lcom/heyzap/sdk/ads/HeyzapAds$AdsConfig;
    //   199: getfield 367	com/heyzap/sdk/ads/HeyzapAds$AdsConfig:publisherId	Ljava/lang/String;
    //   202: astore 4
    //   204: aload 4
    //   206: ifnull +15 -> 221
    //   209: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   212: ldc_w 369
    //   215: aload 4
    //   217: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   220: pop
    //   221: invokestatic 372	com/heyzap/internal/Utils:isAmazon	()Z
    //   224: ifeq +350 -> 574
    //   227: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   230: ldc_w 374
    //   233: ldc_w 376
    //   236: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   239: pop
    //   240: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   243: ldc_w 378
    //   246: ldc_w 376
    //   249: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   252: pop
    //   253: aload 5
    //   255: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   258: invokevirtual 382	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   261: aload 6
    //   263: monitorexit
    //   264: aload_0
    //   265: invokestatic 385	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   268: ifnull +349 -> 617
    //   271: invokestatic 372	com/heyzap/internal/Utils:isAmazon	()Z
    //   274: ifne +343 -> 617
    //   277: aload 5
    //   279: ldc_w 387
    //   282: aload_0
    //   283: invokestatic 385	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   286: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   289: pop
    //   290: aload 5
    //   292: ldc_w 389
    //   295: aload_0
    //   296: invokestatic 385	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   299: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   302: pop
    //   303: aload_0
    //   304: invokestatic 393	com/heyzap/internal/Utils:getLimitAdTrackingEnabled	(Landroid/content/Context;)Ljava/lang/Boolean;
    //   307: invokevirtual 396	java/lang/Boolean:booleanValue	()Z
    //   310: ifne +299 -> 609
    //   313: ldc_w 398
    //   316: astore 4
    //   318: aload 5
    //   320: ldc_w 400
    //   323: aload 4
    //   325: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   328: pop
    //   329: getstatic 403	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   332: ifnull +15 -> 347
    //   335: aload 5
    //   337: ldc_w 405
    //   340: getstatic 403	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   343: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   346: pop
    //   347: getstatic 408	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   350: ifnull +15 -> 365
    //   353: aload 5
    //   355: ldc_w 410
    //   358: getstatic 408	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   361: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   364: pop
    //   365: new 412	android/os/StatFs
    //   368: dup
    //   369: invokestatic 416	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   372: invokevirtual 419	java/io/File:getPath	()Ljava/lang/String;
    //   375: invokespecial 420	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   378: astore 4
    //   380: aload 4
    //   382: invokevirtual 424	android/os/StatFs:getBlockSize	()I
    //   385: i2l
    //   386: lstore_2
    //   387: aload 5
    //   389: ldc_w 426
    //   392: aload 4
    //   394: invokevirtual 429	android/os/StatFs:getAvailableBlocks	()I
    //   397: i2l
    //   398: lload_2
    //   399: lmul
    //   400: invokestatic 434	java/lang/Long:toString	(J)Ljava/lang/String;
    //   403: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   406: pop
    //   407: aload_0
    //   408: invokevirtual 260	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   411: invokevirtual 438	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   414: getfield 443	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   417: astore 4
    //   419: aload 4
    //   421: ifnull +43 -> 464
    //   424: aload 5
    //   426: ldc_w 445
    //   429: aload 4
    //   431: invokevirtual 448	java/util/Locale:getCountry	()Ljava/lang/String;
    //   434: getstatic 139	java/util/Locale:US	Ljava/util/Locale;
    //   437: invokevirtual 451	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   440: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   443: pop
    //   444: aload 5
    //   446: ldc_w 453
    //   449: aload 4
    //   451: invokevirtual 456	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   454: getstatic 139	java/util/Locale:US	Ljava/util/Locale;
    //   457: invokevirtual 451	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   460: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   463: pop
    //   464: aload 5
    //   466: ldc_w 458
    //   469: aload_0
    //   470: invokestatic 463	com/heyzap/common/net/Connectivity:connectionType	(Landroid/content/Context;)Ljava/lang/String;
    //   473: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   476: pop
    //   477: aload_0
    //   478: invokevirtual 260	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   481: invokevirtual 266	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   484: astore_0
    //   485: aload 5
    //   487: ldc_w 465
    //   490: aload_0
    //   491: getfield 269	android/util/DisplayMetrics:density	F
    //   494: invokestatic 470	java/lang/Float:toString	(F)Ljava/lang/String;
    //   497: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   500: pop
    //   501: aload 5
    //   503: ldc_w 472
    //   506: invokevirtual 475	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   509: ifne +19 -> 528
    //   512: aload 5
    //   514: ldc_w 472
    //   517: aload_0
    //   518: getfield 478	android/util/DisplayMetrics:widthPixels	I
    //   521: invokestatic 480	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   524: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   527: pop
    //   528: aload 5
    //   530: ldc_w 482
    //   533: invokevirtual 475	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   536: ifne +19 -> 555
    //   539: aload 5
    //   541: ldc_w 482
    //   544: aload_0
    //   545: getfield 485	android/util/DisplayMetrics:heightPixels	I
    //   548: invokestatic 480	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   551: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   554: pop
    //   555: aload 5
    //   557: areturn
    //   558: astore 4
    //   560: aload 4
    //   562: invokestatic 198	com/heyzap/internal/Logger:trace	(Ljava/lang/Throwable;)V
    //   565: iconst_0
    //   566: invokestatic 310	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   569: astore 4
    //   571: goto -516 -> 55
    //   574: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   577: ldc_w 374
    //   580: ldc_w 487
    //   583: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   586: pop
    //   587: getstatic 291	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   590: ldc_w 378
    //   593: ldc_w 487
    //   596: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   599: pop
    //   600: goto -347 -> 253
    //   603: astore_0
    //   604: aload 6
    //   606: monitorexit
    //   607: aload_0
    //   608: athrow
    //   609: ldc_w 489
    //   612: astore 4
    //   614: goto -296 -> 318
    //   617: aload 5
    //   619: ldc_w 387
    //   622: aload_0
    //   623: invokestatic 492	com/heyzap/internal/Utils:getDeviceId	(Landroid/content/Context;)Ljava/lang/String;
    //   626: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   629: pop
    //   630: goto -301 -> 329
    //   633: astore 4
    //   635: aload 5
    //   637: ldc_w 426
    //   640: ldc_w 489
    //   643: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   646: pop
    //   647: goto -240 -> 407
    //   650: ldc_w 494
    //   653: astore 4
    //   655: goto -497 -> 158
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	658	0	paramContext	Context
    //   48	2	1	i	int
    //   386	13	2	l	long
    //   53	397	4	localObject1	Object
    //   558	3	4	localException1	Exception
    //   569	44	4	localObject2	Object
    //   633	1	4	localException2	Exception
    //   653	1	4	str	String
    //   7	629	5	localHashMap	HashMap
    //   12	593	6	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   33	49	558	java/lang/Exception
    //   17	33	603	finally
    //   33	49	603	finally
    //   55	153	603	finally
    //   158	204	603	finally
    //   209	221	603	finally
    //   221	253	603	finally
    //   253	264	603	finally
    //   560	571	603	finally
    //   574	600	603	finally
    //   604	607	603	finally
    //   365	407	633	java/lang/Exception
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
        paramContext = new a(paramContext.getApplicationContext());
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
          this.a.run();
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
}
