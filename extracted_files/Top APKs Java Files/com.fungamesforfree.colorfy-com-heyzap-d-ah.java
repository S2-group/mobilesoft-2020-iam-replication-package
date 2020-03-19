package com.heyzap.d;

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
import com.heyzap.a.c.e;
import com.heyzap.sdk.ads.b;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ah
{
  public static String a = "unknown";
  public static String b = "unknown";
  private static float c = -1.0F;
  private static Boolean d = null;
  private static String e = "unknown";
  private static HashMap<String, String> f;
  private static Object g = new Object();
  private static Future<Boolean> h;
  private static String i = null;
  private static Boolean j = Boolean.valueOf(false);
  private static Object k = new Object();
  
  public static int a(Context paramContext, float paramFloat)
  {
    if (c <= 0.0F) {
      c = paramContext.getResources().getDisplayMetrics().density;
    }
    return (int)(c * paramFloat);
  }
  
  public static int a(Context paramContext, int paramInt)
  {
    if (c > 0.0F) {}
    for (float f1 = c;; f1 = paramContext.getResources().getDisplayMetrics().density)
    {
      c = f1;
      return (int)(paramInt * c + 0.5F);
    }
  }
  
  public static Boolean a(Activity paramActivity)
  {
    Iterator localIterator = paramActivity.getPackageManager().getInstalledPackages(128).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.equals(c(paramActivity)))
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
  
  public static Boolean a(Context paramContext)
  {
    if (d == null) {
      d = Boolean.valueOf(a(j.f, paramContext));
    }
    return d;
  }
  
  public static Boolean a(Long paramLong, Integer paramInteger)
  {
    if (paramInteger.intValue() < System.currentTimeMillis() - paramLong.longValue()) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  public static String a(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (paramString != null)) {
      return String.format("%s/%s", new Object[] { k(paramContext), paramString });
    }
    return null;
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int m = 0;
    while (m < paramArrayOfByte.length)
    {
      localStringBuffer.append(Integer.toHexString(paramArrayOfByte[m] & 0xFF | 0x100).substring(1, 3));
      m += 1;
    }
    return localStringBuffer.toString();
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
  
  public static void a(boolean paramBoolean)
  {
    d = Boolean.valueOf(paramBoolean);
  }
  
  public static boolean a()
  {
    return (Build.MANUFACTURER.equals("Amazon")) || (com.heyzap.sdk.ads.a.c.c.equals("amazon"));
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
    return a(paramContext, paramInt);
  }
  
  public static void b(Context paramContext)
  {
    m(paramContext);
    n(paramContext);
  }
  
  public static boolean b(Activity paramActivity, String paramString)
  {
    return paramActivity.getPackageManager().checkPermission(paramString, paramActivity.getPackageName()) == 0;
  }
  
  public static Boolean c(Activity paramActivity, String paramString)
  {
    Iterator localIterator = paramActivity.getPackageManager().getInstalledPackages(2).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.equals(c(paramActivity)))
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
    if ((a.equals("unknown")) && (paramContext != null))
    {
      String str = paramContext.getPackageName();
      paramContext = str;
      if (str.endsWith(".debug")) {
        paramContext = str.substring(0, str.length() - 6);
      }
      a = paramContext;
    }
    return a;
  }
  
  public static String d(Context paramContext)
  {
    if ((b.equals("unknown")) && (paramContext != null)) {
      b = paramContext.getPackageManager().getApplicationLabel(paramContext.getApplicationInfo()).toString();
    }
    return b;
  }
  
  public static String e(Context paramContext)
  {
    if ((e.equals("unknown")) && (paramContext != null))
    {
      String str = Build.PRODUCT;
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      if ((str != null) && (paramContext != null)) {
        e = str + "_" + paramContext;
      }
    }
    return e;
  }
  
  public static String f(Context paramContext)
  {
    try
    {
      if (h == null) {
        n(paramContext);
      }
      if (h != null) {
        paramContext = (Boolean)h.get();
      }
      paramContext = i;
      return paramContext;
    }
    catch (InterruptedException paramContext)
    {
      w.a(paramContext);
      return i;
    }
    catch (ExecutionException paramContext)
    {
      for (;;)
      {
        w.a(paramContext);
      }
    }
  }
  
  public static Boolean g(Context paramContext)
  {
    try
    {
      if (h == null) {
        n(paramContext);
      }
      paramContext = (Boolean)h.get();
      paramContext = j;
      return paramContext;
    }
    catch (InterruptedException paramContext)
    {
      w.a(paramContext);
      return j;
    }
    catch (ExecutionException paramContext)
    {
      for (;;)
      {
        w.a(paramContext);
      }
    }
  }
  
  /* Error */
  public static HashMap<String, String> h(Context paramContext)
  {
    // Byte code:
    //   0: new 365	java/util/HashMap
    //   3: dup
    //   4: invokespecial 366	java/util/HashMap:<init>	()V
    //   7: astore 5
    //   9: getstatic 43	com/heyzap/d/ah:g	Ljava/lang/Object;
    //   12: astore 6
    //   14: aload 6
    //   16: monitorenter
    //   17: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   20: ifnonnull +231 -> 251
    //   23: new 365	java/util/HashMap
    //   26: dup
    //   27: invokespecial 366	java/util/HashMap:<init>	()V
    //   30: putstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   33: aload_0
    //   34: invokevirtual 260	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   37: aload_0
    //   38: invokevirtual 305	android/content/Context:getPackageName	()Ljava/lang/String;
    //   41: iconst_0
    //   42: invokevirtual 372	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   45: getfield 375	android/content/pm/PackageInfo:versionCode	I
    //   48: istore_1
    //   49: iload_1
    //   50: invokestatic 378	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   53: astore 4
    //   55: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   58: ldc_w 380
    //   61: aload 4
    //   63: invokestatic 383	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   66: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   69: pop
    //   70: aload_0
    //   71: invokevirtual 63	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   74: invokevirtual 69	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   77: pop
    //   78: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   81: ldc_w 389
    //   84: ldc_w 391
    //   87: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   90: pop
    //   91: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   94: ldc_w 393
    //   97: getstatic 396	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   100: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   103: pop
    //   104: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   107: ldc_w 398
    //   110: aload_0
    //   111: invokestatic 113	com/heyzap/d/ah:c	(Landroid/content/Context;)Ljava/lang/String;
    //   114: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   117: pop
    //   118: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   121: ldc_w 400
    //   124: aload_0
    //   125: invokestatic 113	com/heyzap/d/ah:c	(Landroid/content/Context;)Ljava/lang/String;
    //   128: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   131: pop
    //   132: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   135: ldc_w 402
    //   138: aload_0
    //   139: invokestatic 404	com/heyzap/d/ah:d	(Landroid/content/Context;)Ljava/lang/String;
    //   142: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   145: pop
    //   146: aload_0
    //   147: invokestatic 407	com/heyzap/d/ah:j	(Landroid/content/Context;)Z
    //   150: ifeq +498 -> 648
    //   153: ldc_w 409
    //   156: astore 4
    //   158: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   161: ldc_w 411
    //   164: aload 4
    //   166: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   169: pop
    //   170: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   173: ldc_w 413
    //   176: getstatic 416	android/os/Build:MODEL	Ljava/lang/String;
    //   179: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   182: pop
    //   183: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   186: ldc_w 418
    //   189: getstatic 421	android/os/Build:DEVICE	Ljava/lang/String;
    //   192: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   195: pop
    //   196: getstatic 222	com/heyzap/sdk/ads/a:c	Lcom/heyzap/sdk/ads/b;
    //   199: getfield 422	com/heyzap/sdk/ads/b:b	Ljava/lang/String;
    //   202: astore 4
    //   204: aload 4
    //   206: ifnull +15 -> 221
    //   209: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   212: ldc_w 424
    //   215: aload 4
    //   217: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   220: pop
    //   221: invokestatic 426	com/heyzap/d/ah:a	()Z
    //   224: ifeq +348 -> 572
    //   227: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   230: ldc_w 428
    //   233: ldc -28
    //   235: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   238: pop
    //   239: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   242: ldc_w 430
    //   245: ldc -28
    //   247: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   250: pop
    //   251: aload 5
    //   253: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   256: invokevirtual 434	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   259: aload 6
    //   261: monitorexit
    //   262: aload_0
    //   263: invokestatic 436	com/heyzap/d/ah:f	(Landroid/content/Context;)Ljava/lang/String;
    //   266: ifnull +349 -> 615
    //   269: invokestatic 426	com/heyzap/d/ah:a	()Z
    //   272: ifne +343 -> 615
    //   275: aload 5
    //   277: ldc_w 438
    //   280: aload_0
    //   281: invokestatic 436	com/heyzap/d/ah:f	(Landroid/content/Context;)Ljava/lang/String;
    //   284: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   287: pop
    //   288: aload 5
    //   290: ldc_w 440
    //   293: aload_0
    //   294: invokestatic 436	com/heyzap/d/ah:f	(Landroid/content/Context;)Ljava/lang/String;
    //   297: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   300: pop
    //   301: aload_0
    //   302: invokestatic 442	com/heyzap/d/ah:g	(Landroid/content/Context;)Ljava/lang/Boolean;
    //   305: invokevirtual 445	java/lang/Boolean:booleanValue	()Z
    //   308: ifne +299 -> 607
    //   311: ldc_w 447
    //   314: astore 4
    //   316: aload 5
    //   318: ldc_w 449
    //   321: aload 4
    //   323: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   326: pop
    //   327: getstatic 450	com/heyzap/sdk/ads/a:b	Ljava/lang/String;
    //   330: ifnull +15 -> 345
    //   333: aload 5
    //   335: ldc_w 452
    //   338: getstatic 450	com/heyzap/sdk/ads/a:b	Ljava/lang/String;
    //   341: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   344: pop
    //   345: getstatic 453	com/heyzap/sdk/ads/a:a	Ljava/lang/String;
    //   348: ifnull +15 -> 363
    //   351: aload 5
    //   353: ldc_w 455
    //   356: getstatic 453	com/heyzap/sdk/ads/a:a	Ljava/lang/String;
    //   359: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   362: pop
    //   363: new 457	android/os/StatFs
    //   366: dup
    //   367: invokestatic 463	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   370: invokevirtual 466	java/io/File:getPath	()Ljava/lang/String;
    //   373: invokespecial 468	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   376: astore 4
    //   378: aload 4
    //   380: invokevirtual 471	android/os/StatFs:getBlockSize	()I
    //   383: i2l
    //   384: lstore_2
    //   385: aload 5
    //   387: ldc_w 473
    //   390: aload 4
    //   392: invokevirtual 476	android/os/StatFs:getAvailableBlocks	()I
    //   395: i2l
    //   396: lload_2
    //   397: lmul
    //   398: invokestatic 479	java/lang/Long:toString	(J)Ljava/lang/String;
    //   401: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   404: pop
    //   405: aload_0
    //   406: invokevirtual 63	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   409: invokevirtual 483	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   412: getfield 489	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   415: astore 4
    //   417: aload 4
    //   419: ifnull +43 -> 462
    //   422: aload 5
    //   424: ldc_w 491
    //   427: aload 4
    //   429: invokevirtual 496	java/util/Locale:getCountry	()Ljava/lang/String;
    //   432: getstatic 499	java/util/Locale:US	Ljava/util/Locale;
    //   435: invokevirtual 503	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   438: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   441: pop
    //   442: aload 5
    //   444: ldc_w 505
    //   447: aload 4
    //   449: invokevirtual 508	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   452: getstatic 499	java/util/Locale:US	Ljava/util/Locale;
    //   455: invokevirtual 503	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   458: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   461: pop
    //   462: aload 5
    //   464: ldc_w 510
    //   467: aload_0
    //   468: invokestatic 514	com/heyzap/a/e/d:b	(Landroid/content/Context;)Ljava/lang/String;
    //   471: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   474: pop
    //   475: aload_0
    //   476: invokevirtual 63	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   479: invokevirtual 69	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   482: astore_0
    //   483: aload 5
    //   485: ldc_w 516
    //   488: aload_0
    //   489: getfield 74	android/util/DisplayMetrics:density	F
    //   492: invokestatic 521	java/lang/Float:toString	(F)Ljava/lang/String;
    //   495: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   498: pop
    //   499: aload 5
    //   501: ldc_w 523
    //   504: invokevirtual 525	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   507: ifne +19 -> 526
    //   510: aload 5
    //   512: ldc_w 523
    //   515: aload_0
    //   516: getfield 528	android/util/DisplayMetrics:widthPixels	I
    //   519: invokestatic 530	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   522: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   525: pop
    //   526: aload 5
    //   528: ldc_w 532
    //   531: invokevirtual 525	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   534: ifne +19 -> 553
    //   537: aload 5
    //   539: ldc_w 532
    //   542: aload_0
    //   543: getfield 535	android/util/DisplayMetrics:heightPixels	I
    //   546: invokestatic 530	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   549: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   552: pop
    //   553: aload 5
    //   555: areturn
    //   556: astore 4
    //   558: aload 4
    //   560: invokestatic 362	com/heyzap/d/w:a	(Ljava/lang/Throwable;)V
    //   563: iconst_0
    //   564: invokestatic 378	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   567: astore 4
    //   569: goto -514 -> 55
    //   572: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   575: ldc_w 428
    //   578: ldc_w 537
    //   581: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   584: pop
    //   585: getstatic 368	com/heyzap/d/ah:f	Ljava/util/HashMap;
    //   588: ldc_w 430
    //   591: ldc_w 537
    //   594: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   597: pop
    //   598: goto -347 -> 251
    //   601: astore_0
    //   602: aload 6
    //   604: monitorexit
    //   605: aload_0
    //   606: athrow
    //   607: ldc_w 539
    //   610: astore 4
    //   612: goto -296 -> 316
    //   615: aload 5
    //   617: ldc_w 438
    //   620: aload_0
    //   621: invokestatic 541	com/heyzap/d/ah:e	(Landroid/content/Context;)Ljava/lang/String;
    //   624: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   627: pop
    //   628: goto -301 -> 327
    //   631: astore 4
    //   633: aload 5
    //   635: ldc_w 473
    //   638: ldc_w 539
    //   641: invokevirtual 387	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   644: pop
    //   645: goto -240 -> 405
    //   648: ldc_w 543
    //   651: astore 4
    //   653: goto -495 -> 158
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	656	0	paramContext	Context
    //   48	2	1	m	int
    //   384	13	2	l	long
    //   53	395	4	localObject1	Object
    //   556	3	4	localException1	Exception
    //   567	44	4	localObject2	Object
    //   631	1	4	localException2	Exception
    //   651	1	4	str	String
    //   7	627	5	localHashMap	HashMap
    //   12	591	6	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   33	49	556	java/lang/Exception
    //   17	33	601	finally
    //   33	49	601	finally
    //   55	153	601	finally
    //   158	204	601	finally
    //   209	221	601	finally
    //   221	251	601	finally
    //   251	262	601	finally
    //   558	569	601	finally
    //   572	598	601	finally
    //   602	605	601	finally
    //   363	405	631	java/lang/Exception
  }
  
  public static boolean i(Context paramContext)
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
      w.a(paramContext);
    }
  }
  
  public static boolean j(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static String k(Context paramContext)
  {
    if (paramContext != null) {
      return String.format("%s/%s", new Object[] { paramContext.getCacheDir(), "com.heyzap.sdk" });
    }
    return null;
  }
  
  public static String l(Context paramContext)
  {
    if (paramContext != null) {
      return String.format("%s/%s", new Object[] { paramContext.getCacheDir(), "com.heyzap.sdk.images" });
    }
    return null;
  }
  
  public static void m(Context paramContext)
  {
    paramContext = new File(k(paramContext));
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
  }
  
  private static void n(Context paramContext)
  {
    synchronized (k)
    {
      if ((h == null) || (h.isDone()))
      {
        paramContext = new a(paramContext.getApplicationContext());
        h = e.a().submit(paramContext);
      }
      return;
    }
  }
}
