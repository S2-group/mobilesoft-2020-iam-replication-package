package com.ali.money.shield.mssdk.util;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.ali.money.shield.mssdk.api.AppsRiskInfo;
import com.ali.money.shield.mssdk.bean.AppVirusScanInfo;
import com.ali.money.shield.mssdk.bean.ClientInfo;
import com.ali.money.shield.mssdk.bean.UrlParameter;
import com.ut.device.UTDevice;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import mtopsdk.mtop.domain.EnvModeEnum;

public class a
{
  private static long b;
  
  static
  {
    if (!a.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      a = bool;
      b = 0L;
      return;
    }
  }
  
  public a() {}
  
  public static long a(Context paramContext, AppVirusScanInfo paramAppVirusScanInfo)
  {
    long l2 = -1L;
    long l1 = l2;
    if (paramContext != null)
    {
      l1 = l2;
      if (paramAppVirusScanInfo != null) {
        l1 = com.ali.money.shield.mssdk.a.a.a(paramContext).a(paramAppVirusScanInfo);
      }
    }
    return l1;
  }
  
  public static String a()
  {
    String str2 = Build.MODEL;
    String str1 = str2;
    if (str2.length() <= 0) {
      str1 = "";
    }
    return str1;
  }
  
  public static String a(long paramLong)
  {
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).setTimeInMillis(paramLong);
    Formatter localFormatter = new Formatter(Locale.CHINA);
    localObject = localFormatter.format("%1$tY-%1$tm-%1$td %1$tT", new Object[] { localObject }).toString();
    localFormatter.close();
    return localObject;
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static List<AppsRiskInfo> a(List<AppVirusScanInfo> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramList != null) && (paramList.size() > 0))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        AppVirusScanInfo localAppVirusScanInfo = (AppVirusScanInfo)paramList.next();
        AppsRiskInfo localAppsRiskInfo = new AppsRiskInfo();
        localAppsRiskInfo.a = localAppVirusScanInfo.appName;
        localAppsRiskInfo.b = localAppVirusScanInfo.pkgName;
        localAppsRiskInfo.c = localAppVirusScanInfo.virusName;
        localAppsRiskInfo.d = localAppVirusScanInfo.virusType;
        localAppsRiskInfo.g = localAppVirusScanInfo.genuinePkgName;
        localAppsRiskInfo.h = localAppVirusScanInfo.virusDesc;
        localAppsRiskInfo.e = localAppVirusScanInfo.virusLevel;
        localAppsRiskInfo.f = localAppVirusScanInfo.isCtu;
        localArrayList.add(localAppsRiskInfo);
      }
      return localArrayList;
    }
    return null;
  }
  
  public static List<AppVirusScanInfo> a(Map<String, AppVirusScanInfo> paramMap)
  {
    if ((paramMap == null) || (paramMap.isEmpty())) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      AppVirusScanInfo localAppVirusScanInfo = (AppVirusScanInfo)((Map.Entry)paramMap.next()).getValue();
      if (localAppVirusScanInfo.isVirus)
      {
        localArrayList.add(localAppVirusScanInfo);
        c.c("MS-SDK", "Skip " + localAppVirusScanInfo.pkgName + " check, info: " + localAppVirusScanInfo.toString());
      }
    }
    return localArrayList;
  }
  
  public static void a(Context paramContext, String paramString, List<UrlParameter> paramList)
  {
    if (paramList != null) {
      try
      {
        if (paramList.isEmpty()) {
          return;
        }
        new Thread(new b(paramContext, paramString, paramList)).start();
        return;
      }
      catch (Exception paramContext)
      {
        c.a("MS-SDK", paramContext.getMessage());
      }
    }
  }
  
  public static void a(Context paramContext, Map<String, AppVirusScanInfo> paramMap)
  {
    if ((paramMap != null) && (paramMap.size() > 0)) {
      com.ali.money.shield.mssdk.a.a.a(paramContext).a(paramMap);
    }
  }
  
  public static String b()
  {
    String str2 = Build.BRAND;
    String str1 = str2;
    if (str2.length() <= 0) {
      str1 = "";
    }
    return str1;
  }
  
  public static void b(Context paramContext)
  {
    com.ali.money.shield.mssdk.d.a localA = new com.ali.money.shield.mssdk.d.a();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addCategory("android.intent.category.DEFAULT");
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addDataScheme("package");
    paramContext.registerReceiver(localA, localIntentFilter);
  }
  
  public static String c()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static void c(Context paramContext)
  {
    com.ali.money.shield.mssdk.d.a localA = new com.ali.money.shield.mssdk.d.a();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addCategory("android.intent.category.DEFAULT");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter.addDataScheme("package");
    paramContext.registerReceiver(localA, localIntentFilter);
  }
  
  public static String d()
  {
    return "android";
  }
  
  public static void d(Context paramContext)
  {
    new com.ali.money.shield.mssdk.e.a(paramContext).a();
  }
  
  /* Error */
  public static Map<String, AppVirusScanInfo> e(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: ldc -45
    //   5: ldc_w 308
    //   8: invokestatic 229	com/ali/money/shield/mssdk/util/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   11: aload_0
    //   12: ifnonnull +10 -> 22
    //   15: aconst_null
    //   16: astore_0
    //   17: ldc 2
    //   19: monitorexit
    //   20: aload_0
    //   21: areturn
    //   22: invokestatic 313	java/lang/System:currentTimeMillis	()J
    //   25: lstore 5
    //   27: new 315	java/util/HashMap
    //   30: dup
    //   31: invokespecial 316	java/util/HashMap:<init>	()V
    //   34: astore 15
    //   36: aconst_null
    //   37: astore 10
    //   39: aload_0
    //   40: invokevirtual 93	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   43: astore 16
    //   45: aload 16
    //   47: iconst_0
    //   48: invokevirtual 320	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   51: astore 11
    //   53: aload 11
    //   55: astore 10
    //   57: aload 11
    //   59: astore 12
    //   61: ldc -45
    //   63: new 213	java/lang/StringBuilder
    //   66: dup
    //   67: invokespecial 214	java/lang/StringBuilder:<init>	()V
    //   70: ldc_w 322
    //   73: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: invokestatic 313	java/lang/System:currentTimeMillis	()J
    //   79: lload 5
    //   81: lsub
    //   82: invokevirtual 325	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   85: ldc_w 327
    //   88: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: aload 11
    //   93: invokeinterface 116 1 0
    //   98: invokevirtual 330	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   101: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   104: invokestatic 229	com/ali/money/shield/mssdk/util/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   107: aload 11
    //   109: astore 10
    //   111: aload 10
    //   113: ifnonnull +519 -> 632
    //   116: aconst_null
    //   117: astore_0
    //   118: aload 10
    //   120: ifnull +10 -> 130
    //   123: aload 10
    //   125: invokeinterface 333 1 0
    //   130: iconst_0
    //   131: ifeq -114 -> 17
    //   134: new 335	java/lang/NullPointerException
    //   137: dup
    //   138: invokespecial 336	java/lang/NullPointerException:<init>	()V
    //   141: athrow
    //   142: astore_0
    //   143: ldc 2
    //   145: monitorexit
    //   146: aload_0
    //   147: athrow
    //   148: astore 11
    //   150: aconst_null
    //   151: astore 13
    //   153: invokestatic 342	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   156: ldc_w 344
    //   159: invokevirtual 348	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   162: astore 11
    //   164: new 350	java/io/BufferedReader
    //   167: dup
    //   168: new 352	java/io/InputStreamReader
    //   171: dup
    //   172: aload 11
    //   174: invokevirtual 358	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   177: invokespecial 361	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   180: invokespecial 364	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   183: astore 14
    //   185: aload 14
    //   187: invokevirtual 367	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   190: astore 12
    //   192: aload 12
    //   194: ifnull +139 -> 333
    //   197: aload 10
    //   199: aload 16
    //   201: aload 12
    //   203: aload 12
    //   205: bipush 58
    //   207: invokevirtual 371	java/lang/String:indexOf	(I)I
    //   210: iconst_1
    //   211: iadd
    //   212: invokevirtual 375	java/lang/String:substring	(I)Ljava/lang/String;
    //   215: iconst_0
    //   216: invokevirtual 102	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   219: invokeinterface 209 2 0
    //   224: pop
    //   225: goto -40 -> 185
    //   228: astore 12
    //   230: aload 11
    //   232: astore 13
    //   234: aload 14
    //   236: astore 11
    //   238: aload 11
    //   240: ifnull +12 -> 252
    //   243: aload 10
    //   245: astore 12
    //   247: aload 11
    //   249: invokevirtual 376	java/io/BufferedReader:close	()V
    //   252: aload 13
    //   254: ifnull +12 -> 266
    //   257: aload 10
    //   259: astore 12
    //   261: aload 13
    //   263: invokevirtual 379	java/lang/Process:destroy	()V
    //   266: lconst_0
    //   267: lstore_3
    //   268: aload 10
    //   270: ifnull +16 -> 286
    //   273: aload 10
    //   275: astore 12
    //   277: aload 10
    //   279: invokeinterface 116 1 0
    //   284: i2l
    //   285: lstore_3
    //   286: aload 10
    //   288: astore 12
    //   290: ldc -45
    //   292: new 213	java/lang/StringBuilder
    //   295: dup
    //   296: invokespecial 214	java/lang/StringBuilder:<init>	()V
    //   299: ldc_w 381
    //   302: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   305: invokestatic 313	java/lang/System:currentTimeMillis	()J
    //   308: lload 5
    //   310: lsub
    //   311: invokevirtual 325	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   314: ldc_w 327
    //   317: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: lload_3
    //   321: invokevirtual 325	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   324: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   327: invokestatic 229	com/ali/money/shield/mssdk/util/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   330: goto -219 -> 111
    //   333: aload 11
    //   335: invokevirtual 384	java/lang/Process:waitFor	()I
    //   338: pop
    //   339: aload 14
    //   341: ifnull +12 -> 353
    //   344: aload 10
    //   346: astore 12
    //   348: aload 14
    //   350: invokevirtual 376	java/io/BufferedReader:close	()V
    //   353: aload 11
    //   355: ifnull +12 -> 367
    //   358: aload 10
    //   360: astore 12
    //   362: aload 11
    //   364: invokevirtual 379	java/lang/Process:destroy	()V
    //   367: lconst_0
    //   368: lstore_3
    //   369: aload 10
    //   371: ifnull +16 -> 387
    //   374: aload 10
    //   376: astore 12
    //   378: aload 10
    //   380: invokeinterface 116 1 0
    //   385: i2l
    //   386: lstore_3
    //   387: aload 10
    //   389: astore 12
    //   391: ldc -45
    //   393: new 213	java/lang/StringBuilder
    //   396: dup
    //   397: invokespecial 214	java/lang/StringBuilder:<init>	()V
    //   400: ldc_w 381
    //   403: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   406: invokestatic 313	java/lang/System:currentTimeMillis	()J
    //   409: lload 5
    //   411: lsub
    //   412: invokevirtual 325	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   415: ldc_w 327
    //   418: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   421: lload_3
    //   422: invokevirtual 325	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   425: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   428: invokestatic 229	com/ali/money/shield/mssdk/util/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   431: goto -320 -> 111
    //   434: astore_0
    //   435: aconst_null
    //   436: astore 11
    //   438: aload 13
    //   440: ifnull +12 -> 452
    //   443: aload 10
    //   445: astore 12
    //   447: aload 13
    //   449: invokevirtual 376	java/io/BufferedReader:close	()V
    //   452: aload 11
    //   454: ifnull +12 -> 466
    //   457: aload 10
    //   459: astore 12
    //   461: aload 11
    //   463: invokevirtual 379	java/lang/Process:destroy	()V
    //   466: lconst_0
    //   467: lstore_3
    //   468: aload 10
    //   470: ifnull +16 -> 486
    //   473: aload 10
    //   475: astore 12
    //   477: aload 10
    //   479: invokeinterface 116 1 0
    //   484: i2l
    //   485: lstore_3
    //   486: aload 10
    //   488: astore 12
    //   490: ldc -45
    //   492: new 213	java/lang/StringBuilder
    //   495: dup
    //   496: invokespecial 214	java/lang/StringBuilder:<init>	()V
    //   499: ldc_w 381
    //   502: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   505: invokestatic 313	java/lang/System:currentTimeMillis	()J
    //   508: lload 5
    //   510: lsub
    //   511: invokevirtual 325	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   514: ldc_w 327
    //   517: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   520: lload_3
    //   521: invokevirtual 325	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   524: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   527: invokestatic 229	com/ali/money/shield/mssdk/util/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   530: aload 10
    //   532: astore 12
    //   534: aload_0
    //   535: athrow
    //   536: astore 11
    //   538: aconst_null
    //   539: astore_0
    //   540: ldc -45
    //   542: aload 11
    //   544: invokevirtual 387	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   547: invokestatic 249	com/ali/money/shield/mssdk/util/c:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   550: aload 10
    //   552: ifnull +10 -> 562
    //   555: aload 10
    //   557: invokeinterface 333 1 0
    //   562: aload_0
    //   563: ifnull +9 -> 572
    //   566: aload_0
    //   567: invokeinterface 333 1 0
    //   572: ldc -45
    //   574: ldc_w 389
    //   577: invokestatic 229	com/ali/money/shield/mssdk/util/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   580: ldc -45
    //   582: new 213	java/lang/StringBuilder
    //   585: dup
    //   586: invokespecial 214	java/lang/StringBuilder:<init>	()V
    //   589: ldc_w 391
    //   592: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   595: invokestatic 313	java/lang/System:currentTimeMillis	()J
    //   598: lload 5
    //   600: lsub
    //   601: invokevirtual 325	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   604: ldc_w 327
    //   607: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   610: aload 15
    //   612: invokeinterface 252 1 0
    //   617: invokevirtual 330	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   620: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   623: invokestatic 229	com/ali/money/shield/mssdk/util/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   626: aload 15
    //   628: astore_0
    //   629: goto -612 -> 17
    //   632: aload_0
    //   633: invokestatic 32	com/ali/money/shield/mssdk/a/a:a	(Landroid/content/Context;)Lcom/ali/money/shield/mssdk/a/a;
    //   636: invokevirtual 394	com/ali/money/shield/mssdk/a/a:a	()Ljava/util/List;
    //   639: astore 11
    //   641: invokestatic 313	java/lang/System:currentTimeMillis	()J
    //   644: lstore_3
    //   645: iconst_0
    //   646: istore_1
    //   647: iload_1
    //   648: aload 10
    //   650: invokeinterface 116 1 0
    //   655: if_icmpge +538 -> 1193
    //   658: aload 10
    //   660: iload_1
    //   661: invokeinterface 398 2 0
    //   666: checkcast 104	android/content/pm/PackageInfo
    //   669: astore 13
    //   671: aload 13
    //   673: getfield 402	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   676: getfield 407	android/content/pm/ApplicationInfo:flags	I
    //   679: iconst_1
    //   680: iand
    //   681: ifeq +677 -> 1358
    //   684: goto +667 -> 1351
    //   687: iload_2
    //   688: aload 11
    //   690: invokeinterface 116 1 0
    //   695: if_icmpge +650 -> 1345
    //   698: aload 11
    //   700: iload_2
    //   701: invokeinterface 398 2 0
    //   706: checkcast 131	com/ali/money/shield/mssdk/bean/AppVirusScanInfo
    //   709: astore 12
    //   711: aload 12
    //   713: ifnull +667 -> 1380
    //   716: aload 12
    //   718: getfield 142	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:pkgName	Ljava/lang/String;
    //   721: aload 13
    //   723: getfield 410	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   726: invokevirtual 413	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   729: ifeq +651 -> 1380
    //   732: aload 11
    //   734: iload_2
    //   735: invokeinterface 416 2 0
    //   740: pop
    //   741: aload 13
    //   743: getfield 402	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   746: getfield 419	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   749: astore 14
    //   751: aload 12
    //   753: ifnull +160 -> 913
    //   756: aload 12
    //   758: getfield 422	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:verCode	I
    //   761: aload 13
    //   763: getfield 425	android/content/pm/PackageInfo:versionCode	I
    //   766: if_icmpne +147 -> 913
    //   769: aload 12
    //   771: getfield 428	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:latestUpdateTime	J
    //   774: aload 13
    //   776: getfield 431	android/content/pm/PackageInfo:lastUpdateTime	J
    //   779: lcmp
    //   780: ifne +133 -> 913
    //   783: aload 12
    //   785: getfield 432	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:sourceDir	Ljava/lang/String;
    //   788: aload 14
    //   790: invokevirtual 413	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   793: ifeq +120 -> 913
    //   796: aload_0
    //   797: ldc_w 434
    //   800: ldc2_w 435
    //   803: invokestatic 441	com/ali/money/shield/mssdk/util/KGB:b	(Landroid/content/Context;Ljava/lang/String;J)J
    //   806: lstore 7
    //   808: lload_3
    //   809: aload 12
    //   811: getfield 444	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:checkTime	J
    //   814: lsub
    //   815: lload 7
    //   817: lcmp
    //   818: ifle +27 -> 845
    //   821: aload 12
    //   823: iconst_1
    //   824: putfield 447	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:needUpdate	Z
    //   827: aload 15
    //   829: aload 13
    //   831: getfield 410	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   834: aload 12
    //   836: invokeinterface 451 3 0
    //   841: pop
    //   842: goto +509 -> 1351
    //   845: aload 12
    //   847: getfield 208	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:isVirus	Z
    //   850: ifeq +54 -> 904
    //   853: aload 15
    //   855: aload 13
    //   857: getfield 410	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   860: aload 12
    //   862: invokeinterface 451 3 0
    //   867: pop
    //   868: aload 12
    //   870: iconst_1
    //   871: putfield 447	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:needUpdate	Z
    //   874: goto +477 -> 1351
    //   877: astore_0
    //   878: aload 10
    //   880: ifnull +10 -> 890
    //   883: aload 10
    //   885: invokeinterface 333 1 0
    //   890: aload 11
    //   892: ifnull +10 -> 902
    //   895: aload 11
    //   897: invokeinterface 333 1 0
    //   902: aload_0
    //   903: athrow
    //   904: aload 12
    //   906: iconst_0
    //   907: putfield 447	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:needUpdate	Z
    //   910: goto +441 -> 1351
    //   913: ldc -45
    //   915: aload 13
    //   917: getfield 410	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   920: invokestatic 229	com/ali/money/shield/mssdk/util/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   923: new 453	java/io/File
    //   926: dup
    //   927: aload 14
    //   929: invokespecial 455	java/io/File:<init>	(Ljava/lang/String;)V
    //   932: astore 18
    //   934: aload 18
    //   936: invokevirtual 457	java/io/File:length	()J
    //   939: lstore 7
    //   941: ldc -45
    //   943: new 213	java/lang/StringBuilder
    //   946: dup
    //   947: invokespecial 214	java/lang/StringBuilder:<init>	()V
    //   950: ldc_w 459
    //   953: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   956: lload 7
    //   958: ldc2_w 460
    //   961: ldiv
    //   962: ldc2_w 460
    //   965: ldiv
    //   966: invokevirtual 325	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   969: ldc_w 463
    //   972: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   975: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   978: invokestatic 229	com/ali/money/shield/mssdk/util/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   981: aload_0
    //   982: aload 13
    //   984: getfield 410	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   987: invokestatic 468	com/ali/money/shield/mssdk/util/f:c	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   990: astore 17
    //   992: aload 17
    //   994: ifnull +357 -> 1351
    //   997: aload 17
    //   999: ldc 50
    //   1001: invokevirtual 413	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1004: ifne +347 -> 1351
    //   1007: ldc 50
    //   1009: astore 12
    //   1011: lload 7
    //   1013: ldc2_w 469
    //   1016: lcmp
    //   1017: iflt +147 -> 1164
    //   1020: new 131	com/ali/money/shield/mssdk/bean/AppVirusScanInfo
    //   1023: dup
    //   1024: invokespecial 471	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:<init>	()V
    //   1027: astore 18
    //   1029: aload 18
    //   1031: aload 13
    //   1033: getfield 107	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   1036: putfield 474	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:verName	Ljava/lang/String;
    //   1039: aload 18
    //   1041: aload 13
    //   1043: getfield 410	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   1046: putfield 142	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:pkgName	Ljava/lang/String;
    //   1049: aload 18
    //   1051: aload 13
    //   1053: getfield 402	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   1056: aload 16
    //   1058: invokevirtual 478	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   1061: invokeinterface 481 1 0
    //   1066: putfield 137	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:appName	Ljava/lang/String;
    //   1069: aload 18
    //   1071: aload 14
    //   1073: putfield 432	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:sourceDir	Ljava/lang/String;
    //   1076: aload 18
    //   1078: aload 12
    //   1080: putfield 484	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:fileMD5String	Ljava/lang/String;
    //   1083: aload 18
    //   1085: aload 17
    //   1087: putfield 487	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:sigMD5String	Ljava/lang/String;
    //   1090: aload 18
    //   1092: lload 7
    //   1094: putfield 490	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:fileSize	J
    //   1097: aload 18
    //   1099: aload 13
    //   1101: getfield 425	android/content/pm/PackageInfo:versionCode	I
    //   1104: putfield 422	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:verCode	I
    //   1107: aload 18
    //   1109: aload 13
    //   1111: getfield 493	android/content/pm/PackageInfo:firstInstallTime	J
    //   1114: putfield 494	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:firstInstallTime	J
    //   1117: aload 18
    //   1119: aload 13
    //   1121: getfield 431	android/content/pm/PackageInfo:lastUpdateTime	J
    //   1124: putfield 428	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:latestUpdateTime	J
    //   1127: aload 18
    //   1129: lconst_0
    //   1130: putfield 444	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:checkTime	J
    //   1133: aload 18
    //   1135: iconst_1
    //   1136: putfield 447	com/ali/money/shield/mssdk/bean/AppVirusScanInfo:needUpdate	Z
    //   1139: aload 15
    //   1141: aload 13
    //   1143: getfield 410	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   1146: aload 18
    //   1148: invokeinterface 451 3 0
    //   1153: pop
    //   1154: aload_0
    //   1155: aload 18
    //   1157: invokestatic 496	com/ali/money/shield/mssdk/util/a:a	(Landroid/content/Context;Lcom/ali/money/shield/mssdk/bean/AppVirusScanInfo;)J
    //   1160: pop2
    //   1161: goto +190 -> 1351
    //   1164: aload 18
    //   1166: invokestatic 501	com/ali/money/shield/mssdk/util/d:a	(Ljava/io/File;)Ljava/lang/String;
    //   1169: astore 12
    //   1171: aload 12
    //   1173: ifnull +178 -> 1351
    //   1176: aload 12
    //   1178: ldc 50
    //   1180: invokevirtual 413	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1183: istore 9
    //   1185: iload 9
    //   1187: ifeq -167 -> 1020
    //   1190: goto +161 -> 1351
    //   1193: aload 10
    //   1195: ifnull +10 -> 1205
    //   1198: aload 10
    //   1200: invokeinterface 333 1 0
    //   1205: aload 11
    //   1207: ifnull -635 -> 572
    //   1210: aload 11
    //   1212: invokeinterface 333 1 0
    //   1217: goto -645 -> 572
    //   1220: astore 12
    //   1222: goto -869 -> 353
    //   1225: astore 11
    //   1227: goto -860 -> 367
    //   1230: astore 11
    //   1232: goto -980 -> 252
    //   1235: astore 11
    //   1237: goto -971 -> 266
    //   1240: astore 12
    //   1242: goto -790 -> 452
    //   1245: astore 11
    //   1247: goto -781 -> 466
    //   1250: astore_0
    //   1251: aconst_null
    //   1252: astore 11
    //   1254: aconst_null
    //   1255: astore 10
    //   1257: goto -379 -> 878
    //   1260: astore_0
    //   1261: aconst_null
    //   1262: astore 11
    //   1264: aload 12
    //   1266: astore 10
    //   1268: goto -390 -> 878
    //   1271: astore_0
    //   1272: aconst_null
    //   1273: astore 11
    //   1275: goto -397 -> 878
    //   1278: astore 12
    //   1280: aload_0
    //   1281: astore 11
    //   1283: aload 12
    //   1285: astore_0
    //   1286: goto -408 -> 878
    //   1289: astore 11
    //   1291: aconst_null
    //   1292: astore 10
    //   1294: aconst_null
    //   1295: astore_0
    //   1296: goto -756 -> 540
    //   1299: astore 11
    //   1301: aconst_null
    //   1302: astore_0
    //   1303: goto -763 -> 540
    //   1306: astore_0
    //   1307: goto -869 -> 438
    //   1310: astore_0
    //   1311: aload 14
    //   1313: astore 13
    //   1315: goto -877 -> 438
    //   1318: astore 11
    //   1320: aconst_null
    //   1321: astore 13
    //   1323: aconst_null
    //   1324: astore 11
    //   1326: goto -1088 -> 238
    //   1329: astore 12
    //   1331: aconst_null
    //   1332: astore 12
    //   1334: aload 11
    //   1336: astore 13
    //   1338: aload 12
    //   1340: astore 11
    //   1342: goto -1104 -> 238
    //   1345: aconst_null
    //   1346: astore 12
    //   1348: goto -607 -> 741
    //   1351: iload_1
    //   1352: iconst_1
    //   1353: iadd
    //   1354: istore_1
    //   1355: goto -708 -> 647
    //   1358: aload 11
    //   1360: ifnull -15 -> 1345
    //   1363: iconst_0
    //   1364: istore_2
    //   1365: goto -678 -> 687
    //   1368: astore 12
    //   1370: aload 11
    //   1372: astore_0
    //   1373: aload 12
    //   1375: astore 11
    //   1377: goto -837 -> 540
    //   1380: iload_2
    //   1381: iconst_1
    //   1382: iadd
    //   1383: istore_2
    //   1384: goto -697 -> 687
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1387	0	paramContext	Context
    //   646	709	1	i	int
    //   687	697	2	j	int
    //   267	542	3	l1	long
    //   25	574	5	l2	long
    //   806	287	7	l3	long
    //   1183	3	9	bool	boolean
    //   37	1256	10	localObject1	Object
    //   51	57	11	localList1	List
    //   148	1	11	localThrowable1	Throwable
    //   162	300	11	localObject2	Object
    //   536	7	11	localThrowable2	Throwable
    //   639	572	11	localList2	List
    //   1225	1	11	localThrowable3	Throwable
    //   1230	1	11	localThrowable4	Throwable
    //   1235	1	11	localThrowable5	Throwable
    //   1245	1	11	localThrowable6	Throwable
    //   1252	30	11	localContext	Context
    //   1289	1	11	localThrowable7	Throwable
    //   1299	1	11	localThrowable8	Throwable
    //   1318	1	11	localThrowable9	Throwable
    //   1324	52	11	localObject3	Object
    //   59	145	12	localObject4	Object
    //   228	1	12	localThrowable10	Throwable
    //   245	932	12	localObject5	Object
    //   1220	1	12	localThrowable11	Throwable
    //   1240	25	12	localThrowable12	Throwable
    //   1278	6	12	localObject6	Object
    //   1329	1	12	localThrowable13	Throwable
    //   1332	15	12	localObject7	Object
    //   1368	6	12	localThrowable14	Throwable
    //   151	1186	13	localObject8	Object
    //   183	1129	14	localObject9	Object
    //   34	1106	15	localHashMap	java.util.HashMap
    //   43	1014	16	localPackageManager	PackageManager
    //   990	96	17	str	String
    //   932	233	18	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   3	11	142	finally
    //   22	36	142	finally
    //   123	130	142	finally
    //   134	142	142	finally
    //   555	562	142	finally
    //   566	572	142	finally
    //   572	626	142	finally
    //   883	890	142	finally
    //   895	902	142	finally
    //   902	904	142	finally
    //   1198	1205	142	finally
    //   1210	1217	142	finally
    //   45	53	148	java/lang/Throwable
    //   61	107	148	java/lang/Throwable
    //   185	192	228	java/lang/Throwable
    //   197	225	228	java/lang/Throwable
    //   333	339	228	java/lang/Throwable
    //   153	164	434	finally
    //   277	286	536	java/lang/Throwable
    //   290	330	536	java/lang/Throwable
    //   378	387	536	java/lang/Throwable
    //   391	431	536	java/lang/Throwable
    //   477	486	536	java/lang/Throwable
    //   490	530	536	java/lang/Throwable
    //   534	536	536	java/lang/Throwable
    //   641	645	877	finally
    //   647	684	877	finally
    //   687	711	877	finally
    //   716	741	877	finally
    //   741	751	877	finally
    //   756	842	877	finally
    //   845	874	877	finally
    //   904	910	877	finally
    //   913	992	877	finally
    //   997	1007	877	finally
    //   1020	1161	877	finally
    //   1164	1171	877	finally
    //   1176	1185	877	finally
    //   348	353	1220	java/lang/Throwable
    //   362	367	1225	java/lang/Throwable
    //   247	252	1230	java/lang/Throwable
    //   261	266	1235	java/lang/Throwable
    //   447	452	1240	java/lang/Throwable
    //   461	466	1245	java/lang/Throwable
    //   39	45	1250	finally
    //   45	53	1250	finally
    //   61	107	1260	finally
    //   247	252	1260	finally
    //   261	266	1260	finally
    //   277	286	1260	finally
    //   290	330	1260	finally
    //   348	353	1260	finally
    //   362	367	1260	finally
    //   378	387	1260	finally
    //   391	431	1260	finally
    //   447	452	1260	finally
    //   461	466	1260	finally
    //   477	486	1260	finally
    //   490	530	1260	finally
    //   534	536	1260	finally
    //   632	641	1271	finally
    //   540	550	1278	finally
    //   39	45	1289	java/lang/Throwable
    //   632	641	1299	java/lang/Throwable
    //   164	185	1306	finally
    //   185	192	1310	finally
    //   197	225	1310	finally
    //   333	339	1310	finally
    //   153	164	1318	java/lang/Throwable
    //   164	185	1329	java/lang/Throwable
    //   641	645	1368	java/lang/Throwable
    //   647	684	1368	java/lang/Throwable
    //   687	711	1368	java/lang/Throwable
    //   716	741	1368	java/lang/Throwable
    //   741	751	1368	java/lang/Throwable
    //   756	842	1368	java/lang/Throwable
    //   845	874	1368	java/lang/Throwable
    //   904	910	1368	java/lang/Throwable
    //   913	992	1368	java/lang/Throwable
    //   997	1007	1368	java/lang/Throwable
    //   1020	1161	1368	java/lang/Throwable
    //   1164	1171	1368	java/lang/Throwable
    //   1176	1185	1368	java/lang/Throwable
  }
  
  public static String f(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      if (paramContext == null) {
        return "";
      }
      paramContext = paramContext.trim();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static String g(Context paramContext)
  {
    try
    {
      String str = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
      paramContext = str;
      if (str == null) {
        paramContext = "";
      }
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static String h(Context paramContext)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      paramContext = localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0)).toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      c.a("MS-SDK", paramContext.getLocalizedMessage());
    }
    return "";
  }
  
  public static EnvModeEnum i(Context paramContext)
  {
    EnvModeEnum localEnvModeEnum = EnvModeEnum.ONLINE;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 128).applicationInfo.metaData;
      if ((paramContext != null) && (paramContext.containsKey("ENVMODE")))
      {
        paramContext = paramContext.getString("ENVMODE");
        if (!TextUtils.isEmpty(paramContext))
        {
          if (paramContext.equalsIgnoreCase("DAILY")) {
            return EnvModeEnum.TEST;
          }
          if (paramContext.equalsIgnoreCase("PREONLINE"))
          {
            paramContext = EnvModeEnum.PREPARE;
            return paramContext;
          }
        }
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return localEnvModeEnum;
  }
  
  public static ClientInfo j(Context paramContext)
  {
    ClientInfo localClientInfo = new ClientInfo();
    String str = g.a(paramContext);
    localClientInfo.userId = KGB.a(paramContext, "userid", str);
    localClientInfo.umid = g.a(paramContext, str);
    localClientInfo.imei = f(paramContext);
    localClientInfo.brand = b();
    localClientInfo.imsi = g(paramContext);
    localClientInfo.os = d();
    localClientInfo.osVersion = c();
    localClientInfo.model = a();
    localClientInfo.hostVersion = a(paramContext);
    localClientInfo.hostPackage = paramContext.getPackageName();
    localClientInfo.hostAppName = h(paramContext);
    localClientInfo.sdkVersion = "2.0.2.5";
    localClientInfo.userNick = "";
    localClientInfo.utdid = UTDevice.getUtdid(paramContext);
    return localClientInfo;
  }
}
