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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import com.heyzap.common.concurrency.ExecutorPool;
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
  
  private static int compareVersions(String paramString1, String paramString2)
    throws IllegalArgumentException
  {
    Object localObject2 = SEMVER_PATTERN.matcher(paramString1);
    Object localObject1 = SEMVER_PATTERN.matcher(paramString2);
    if ((!((Matcher)localObject2).matches()) || (!((Matcher)localObject1).matches())) {
      throw new IllegalArgumentException("Non semantic version provided: " + paramString1 + " - " + paramString2);
    }
    paramString1 = Integer.valueOf(((Matcher)localObject2).group(1));
    paramString2 = Integer.valueOf(((Matcher)localObject2).group(2));
    localObject2 = Integer.valueOf(((Matcher)localObject2).group(3));
    Integer localInteger1 = Integer.valueOf(((Matcher)localObject1).group(1));
    Integer localInteger2 = Integer.valueOf(((Matcher)localObject1).group(2));
    localObject1 = Integer.valueOf(((Matcher)localObject1).group(3));
    return getPseudoVersionCode(paramString1.intValue(), paramString2.intValue(), ((Integer)localObject2).intValue()).compareTo(getPseudoVersionCode(localInteger1.intValue(), localInteger2.intValue(), ((Integer)localObject1).intValue()));
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
    //   0: new 213	java/util/HashMap
    //   3: dup
    //   4: invokespecial 214	java/util/HashMap:<init>	()V
    //   7: astore_3
    //   8: getstatic 51	com/heyzap/internal/Utils:paramLock	Ljava/lang/Object;
    //   11: astore 4
    //   13: aload 4
    //   15: monitorenter
    //   16: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   19: ifnonnull +227 -> 246
    //   22: new 213	java/util/HashMap
    //   25: dup
    //   26: invokespecial 214	java/util/HashMap:<init>	()V
    //   29: putstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   32: iconst_0
    //   33: invokestatic 219	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   36: astore_2
    //   37: aload_0
    //   38: invokevirtual 220	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   41: aload_0
    //   42: invokevirtual 223	android/content/Context:getPackageName	()Ljava/lang/String;
    //   45: iconst_0
    //   46: invokevirtual 229	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   49: getfield 235	android/content/pm/PackageInfo:versionCode	I
    //   52: istore_1
    //   53: iload_1
    //   54: invokestatic 219	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   57: astore_2
    //   58: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   61: ldc -19
    //   63: aload_2
    //   64: invokestatic 242	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   67: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   70: pop
    //   71: aload_0
    //   72: invokevirtual 198	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   75: invokevirtual 204	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   78: pop
    //   79: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   82: ldc -8
    //   84: ldc -6
    //   86: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   89: pop
    //   90: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   93: ldc -4
    //   95: getstatic 257	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   98: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   101: pop
    //   102: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   105: ldc_w 259
    //   108: aload_0
    //   109: invokestatic 261	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   112: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   115: pop
    //   116: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   119: ldc_w 263
    //   122: aload_0
    //   123: invokestatic 261	com/heyzap/internal/Utils:getPackageName	(Landroid/content/Context;)Ljava/lang/String;
    //   126: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   129: pop
    //   130: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   133: ldc_w 265
    //   136: aload_0
    //   137: invokestatic 268	com/heyzap/internal/Utils:getAppName	(Landroid/content/Context;)Ljava/lang/String;
    //   140: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   143: pop
    //   144: aload_0
    //   145: invokestatic 272	com/heyzap/internal/Utils:isTablet	(Landroid/content/Context;)Z
    //   148: ifeq +392 -> 540
    //   151: ldc_w 274
    //   154: astore_2
    //   155: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   158: ldc_w 276
    //   161: aload_2
    //   162: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   165: pop
    //   166: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   169: ldc_w 278
    //   172: getstatic 283	android/os/Build:MODEL	Ljava/lang/String;
    //   175: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   178: pop
    //   179: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   182: ldc_w 285
    //   185: getstatic 288	android/os/Build:DEVICE	Ljava/lang/String;
    //   188: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   191: pop
    //   192: getstatic 294	com/heyzap/sdk/ads/HeyzapAds:config	Lcom/heyzap/sdk/ads/HeyzapAds$AdsConfig;
    //   195: getfield 299	com/heyzap/sdk/ads/HeyzapAds$AdsConfig:publisherId	Ljava/lang/String;
    //   198: astore_2
    //   199: aload_2
    //   200: ifnull +14 -> 214
    //   203: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   206: ldc_w 301
    //   209: aload_2
    //   210: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   213: pop
    //   214: invokestatic 304	com/heyzap/internal/Utils:isAmazon	()Z
    //   217: ifeq +330 -> 547
    //   220: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   223: ldc_w 306
    //   226: ldc_w 308
    //   229: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   232: pop
    //   233: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   236: ldc_w 310
    //   239: ldc_w 308
    //   242: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   245: pop
    //   246: aload_3
    //   247: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   250: invokevirtual 314	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   253: aload 4
    //   255: monitorexit
    //   256: aload_0
    //   257: invokestatic 317	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   260: ifnull +323 -> 583
    //   263: invokestatic 304	com/heyzap/internal/Utils:isAmazon	()Z
    //   266: ifne +317 -> 583
    //   269: aload_3
    //   270: ldc_w 319
    //   273: aload_0
    //   274: invokestatic 317	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   277: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   280: pop
    //   281: aload_3
    //   282: ldc_w 321
    //   285: aload_0
    //   286: invokestatic 317	com/heyzap/internal/Utils:getAdvertisingId	(Landroid/content/Context;)Ljava/lang/String;
    //   289: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   292: pop
    //   293: aload_0
    //   294: invokestatic 325	com/heyzap/internal/Utils:getLimitAdTrackingEnabled	(Landroid/content/Context;)Ljava/lang/Boolean;
    //   297: invokevirtual 328	java/lang/Boolean:booleanValue	()Z
    //   300: ifne +276 -> 576
    //   303: ldc_w 330
    //   306: astore_2
    //   307: aload_3
    //   308: ldc_w 332
    //   311: aload_2
    //   312: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   315: pop
    //   316: getstatic 335	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   319: ifnull +14 -> 333
    //   322: aload_3
    //   323: ldc_w 337
    //   326: getstatic 335	com/heyzap/sdk/ads/HeyzapAds:mediator	Ljava/lang/String;
    //   329: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   332: pop
    //   333: getstatic 340	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   336: ifnull +14 -> 350
    //   339: aload_3
    //   340: ldc_w 342
    //   343: getstatic 340	com/heyzap/sdk/ads/HeyzapAds:framework	Ljava/lang/String;
    //   346: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   349: pop
    //   350: new 344	android/os/StatFs
    //   353: dup
    //   354: invokestatic 350	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   357: invokevirtual 353	java/io/File:getPath	()Ljava/lang/String;
    //   360: invokespecial 354	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   363: astore_2
    //   364: aload_3
    //   365: ldc_w 356
    //   368: aload_2
    //   369: invokevirtual 359	android/os/StatFs:getBlockSize	()I
    //   372: i2l
    //   373: aload_2
    //   374: invokevirtual 362	android/os/StatFs:getAvailableBlocks	()I
    //   377: i2l
    //   378: lmul
    //   379: invokestatic 367	java/lang/Long:toString	(J)Ljava/lang/String;
    //   382: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   385: pop
    //   386: aload_0
    //   387: invokevirtual 198	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   390: invokevirtual 371	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   393: getfield 377	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   396: astore_2
    //   397: aload_2
    //   398: ifnull +39 -> 437
    //   401: aload_3
    //   402: ldc_w 379
    //   405: aload_2
    //   406: invokevirtual 384	java/util/Locale:getCountry	()Ljava/lang/String;
    //   409: getstatic 387	java/util/Locale:US	Ljava/util/Locale;
    //   412: invokevirtual 391	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   415: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   418: pop
    //   419: aload_3
    //   420: ldc_w 393
    //   423: aload_2
    //   424: invokevirtual 396	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   427: getstatic 387	java/util/Locale:US	Ljava/util/Locale;
    //   430: invokevirtual 391	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   433: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   436: pop
    //   437: aload_3
    //   438: ldc_w 398
    //   441: aload_0
    //   442: invokestatic 403	com/heyzap/common/net/Connectivity:connectionType	(Landroid/content/Context;)Ljava/lang/String;
    //   445: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   448: pop
    //   449: aload_0
    //   450: invokevirtual 198	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   453: invokevirtual 204	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   456: astore_0
    //   457: aload_3
    //   458: ldc_w 405
    //   461: aload_0
    //   462: getfield 207	android/util/DisplayMetrics:density	F
    //   465: invokestatic 410	java/lang/Float:toString	(F)Ljava/lang/String;
    //   468: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   471: pop
    //   472: aload_3
    //   473: ldc_w 412
    //   476: invokevirtual 416	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   479: ifne +18 -> 497
    //   482: aload_3
    //   483: ldc_w 412
    //   486: aload_0
    //   487: getfield 419	android/util/DisplayMetrics:widthPixels	I
    //   490: invokestatic 421	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   493: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   496: pop
    //   497: aload_3
    //   498: ldc_w 423
    //   501: invokevirtual 416	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   504: ifne +18 -> 522
    //   507: aload_3
    //   508: ldc_w 423
    //   511: aload_0
    //   512: getfield 426	android/util/DisplayMetrics:heightPixels	I
    //   515: invokestatic 421	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   518: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   521: pop
    //   522: aload_3
    //   523: areturn
    //   524: astore 5
    //   526: aload 5
    //   528: invokestatic 432	com/heyzap/internal/Logger:trace	(Ljava/lang/Throwable;)V
    //   531: goto -473 -> 58
    //   534: astore_0
    //   535: aload 4
    //   537: monitorexit
    //   538: aload_0
    //   539: athrow
    //   540: ldc_w 434
    //   543: astore_2
    //   544: goto -389 -> 155
    //   547: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   550: ldc_w 306
    //   553: ldc_w 436
    //   556: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   559: pop
    //   560: getstatic 216	com/heyzap/internal/Utils:cachedParams	Ljava/util/HashMap;
    //   563: ldc_w 310
    //   566: ldc_w 436
    //   569: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   572: pop
    //   573: goto -327 -> 246
    //   576: ldc_w 438
    //   579: astore_2
    //   580: goto -273 -> 307
    //   583: aload_3
    //   584: ldc_w 319
    //   587: aload_0
    //   588: invokestatic 441	com/heyzap/internal/Utils:getDeviceId	(Landroid/content/Context;)Ljava/lang/String;
    //   591: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   594: pop
    //   595: goto -279 -> 316
    //   598: astore_2
    //   599: aload_3
    //   600: ldc_w 356
    //   603: ldc_w 438
    //   606: invokevirtual 246	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   609: pop
    //   610: goto -224 -> 386
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	613	0	paramContext	Context
    //   52	2	1	i	int
    //   36	544	2	localObject1	Object
    //   598	1	2	localException1	Exception
    //   7	593	3	localHashMap	HashMap
    //   11	525	4	localObject2	Object
    //   524	3	5	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   37	53	524	java/lang/Exception
    //   16	32	534	finally
    //   37	53	534	finally
    //   58	151	534	finally
    //   155	199	534	finally
    //   203	214	534	finally
    //   214	246	534	finally
    //   246	256	534	finally
    //   526	531	534	finally
    //   535	538	534	finally
    //   547	573	534	finally
    //   350	386	598	java/lang/Exception
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
  
  public static Map<String, String> getConsentDataFromJsonString(String paramString)
  {
    Object localObject;
    if (paramString == null) {
      localObject = null;
    }
    for (;;)
    {
      return localObject;
      HashMap localHashMap = new HashMap();
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        Iterator localIterator = localJSONObject.keys();
        for (;;)
        {
          localObject = localHashMap;
          if (!localIterator.hasNext()) {
            break;
          }
          localObject = (String)localIterator.next();
          localHashMap.put(localObject, localJSONObject.getString((String)localObject));
        }
        return null;
      }
      catch (JSONException localJSONException)
      {
        Log.e(Logger.TAG, "unable to convert gdpr consent data: " + paramString);
      }
    }
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
  
  public static String getGdprConsentDataAsJsonString(Map<String, String> paramMap)
  {
    if (paramMap == null) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        Iterator localIterator = paramMap.entrySet().iterator();
        if (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          if ((localEntry != null) && (localEntry.getKey() != null))
          {
            paramMap = (String)localEntry.getValue();
            if (paramMap == null)
            {
              paramMap = JSONObject.NULL;
              localJSONObject.put((String)localEntry.getKey(), paramMap);
            }
          }
        }
        else
        {
          return localJSONObject.toString();
        }
      }
      catch (JSONException paramMap)
      {
        paramMap.printStackTrace();
      }
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
  
  private static Double getPseudoVersionCode(int paramInt1, int paramInt2, int paramInt3)
  {
    return Double.valueOf(paramInt1 * Math.pow(1000.0D, 2.0D) + paramInt2 * 1000 + paramInt3);
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
    catch (IllegalAccessException paramClass)
    {
      return paramString2;
    }
    catch (NoSuchFieldException paramClass) {}
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
    boolean bool = false;
    try
    {
      int i = compareVersions(paramString1, paramString2);
      if ((i == 0) || (i == 1)) {
        bool = true;
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
  
  public static Boolean methodExists(@NonNull String paramString1, @NonNull String paramString2, @Nullable Function<Class[], Boolean> paramFunction, @Nullable Function<Class, Boolean> paramFunction1)
  {
    Method[] arrayOfMethod;
    if (classExists(paramString1).booleanValue()) {
      arrayOfMethod = new Method[0];
    }
    try
    {
      paramString1 = Class.forName(paramString1).getMethods();
      int i = 0;
      while (i < paramString1.length)
      {
        arrayOfMethod = paramString1[i];
        if ((paramString2.equals(arrayOfMethod.getName())) && ((paramFunction == null) || (((Boolean)paramFunction.apply(arrayOfMethod.getParameterTypes())).booleanValue())) && ((paramFunction1 == null) || (((Boolean)paramFunction1.apply(arrayOfMethod.getReturnType())).booleanValue()))) {
          return Boolean.valueOf(true);
        }
        i += 1;
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
