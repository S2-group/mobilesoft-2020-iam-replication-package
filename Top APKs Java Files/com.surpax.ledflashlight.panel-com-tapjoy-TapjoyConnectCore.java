package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import com.tapjoy.internal.ApiKeyDecoded;
import com.tapjoy.internal.ApiKeyDecoded.KeyUsage;
import com.tapjoy.internal.eq;
import com.tapjoy.internal.fc;
import com.tapjoy.internal.fv;
import com.tapjoy.internal.gk.a;
import com.tapjoy.internal.gk.l;
import com.tapjoy.internal.gk.n;
import com.tapjoy.internal.gk.x;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TreeMap;
import java.util.Vector;

public class TapjoyConnectCore
{
  private static String A;
  private static String B;
  private static String C;
  private static int D = 0;
  public static final float DEFAULT_CURRENCY_MULTIPLIER = 1.0F;
  private static float E = 0.0F;
  private static int F = 0;
  private static String G;
  private static boolean H = false;
  private static String I;
  private static String J;
  private static String K;
  private static String L;
  private static String M;
  public static final int MAX_NUMBER_OF_OFFLINE_LOGS = 50;
  private static String N;
  private static String O;
  private static String P;
  private static String Q;
  private static String R;
  private static String S;
  private static String T;
  private static float U;
  private static boolean V;
  private static boolean W;
  private static boolean X;
  private static boolean Y;
  private static boolean Z;
  protected static int a;
  private static int aA;
  private static int aB;
  private static String aC;
  private static String aD;
  private static String aE;
  private static String aF;
  private static String aG;
  private static int aH;
  private static int aI;
  private static int aJ;
  private static long aK;
  private static long aL;
  private static long aM;
  private static String aN;
  private static int aO;
  private static double aP;
  private static double aQ;
  private static long aR;
  private static int aS;
  private static int aT;
  private static int aU;
  private static String aV;
  private static String aW;
  private static boolean aX = false;
  private static boolean aY = true;
  private static String aa;
  private static String ab;
  private static String ac;
  private static String ad;
  private static String ae;
  private static long ai;
  private static boolean ak;
  private static PackageManager al;
  private static Hashtable ao;
  private static String ap;
  private static boolean aq;
  private static String ar;
  private static String as;
  private static String at;
  private static String au;
  private static int av;
  private static String aw;
  private static String ax;
  private static long ay;
  private static String az;
  protected static int b;
  protected static String c;
  protected static boolean d;
  protected static String e;
  protected static String f;
  private static Context g = null;
  private static String h = null;
  private static TapjoyConnectCore i = null;
  private static TapjoyURLConnection j = null;
  private static TJConnectListener k = null;
  private static TJViewListener l = null;
  private static Vector m = new Vector(Arrays.asList(TapjoyConstants.dependencyClassNames));
  private static String n = "";
  private static String o = "";
  private static String p;
  private static String q = "";
  private static String r = "";
  private static String s = "";
  private static String t = "";
  private static String u = "";
  private static String v = "";
  private static String w = "";
  private static String x = "";
  private static String y = "";
  private static String z = "";
  private long af;
  private Timer ag;
  private boolean ah;
  private boolean aj;
  private fc am;
  private TapjoyGpsHelper an;
  
  static
  {
    A = "";
    B = "";
    C = "";
    D = 1;
    E = 1.0F;
    F = 1;
    G = "";
    H = false;
    I = "";
    J = "";
    K = "";
    L = "";
    M = "";
    N = "";
    O = "";
    P = "";
    Q = "";
    R = "native";
    S = "";
    T = "";
    U = 1.0F;
    V = false;
    W = false;
    X = false;
    Y = false;
    Z = false;
    aa = "";
    ab = "";
    ac = "";
    ad = "";
    ae = null;
    ai = 0L;
    a = 0;
    b = 0;
    c = "";
    e = "";
    f = "";
    ao = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
    ap = "";
    aq = false;
  }
  
  /* Error */
  public TapjoyConnectCore(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 296	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 298	com/tapjoy/TapjoyConnectCore:af	J
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 300	com/tapjoy/TapjoyConnectCore:ag	Ljava/util/Timer;
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 302	com/tapjoy/TapjoyConnectCore:ah	Z
    //   19: aload_0
    //   20: iconst_0
    //   21: putfield 304	com/tapjoy/TapjoyConnectCore:aj	Z
    //   24: aload_1
    //   25: putstatic 137	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   28: new 306	com/tapjoy/TapjoyURLConnection
    //   31: dup
    //   32: invokespecial 307	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   35: putstatic 143	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   38: getstatic 137	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   41: invokevirtual 313	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   44: putstatic 315	com/tapjoy/TapjoyConnectCore:al	Landroid/content/pm/PackageManager;
    //   47: aload_0
    //   48: new 317	com/tapjoy/TapjoyGpsHelper
    //   51: dup
    //   52: getstatic 137	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   55: invokespecial 319	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   58: putfield 321	com/tapjoy/TapjoyConnectCore:an	Lcom/tapjoy/TapjoyGpsHelper;
    //   61: getstatic 276	com/tapjoy/TapjoyConnectCore:ao	Ljava/util/Hashtable;
    //   64: ifnonnull +13 -> 77
    //   67: new 323	java/util/Hashtable
    //   70: dup
    //   71: invokespecial 324	java/util/Hashtable:<init>	()V
    //   74: putstatic 276	com/tapjoy/TapjoyConnectCore:ao	Ljava/util/Hashtable;
    //   77: ldc_w 326
    //   80: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   83: ifnull +22 -> 105
    //   86: ldc_w 326
    //   89: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   92: ldc_w 332
    //   95: invokevirtual 338	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   98: ifeq +7 -> 105
    //   101: iconst_1
    //   102: invokestatic 344	com/tapjoy/TapjoyLog:setDebugEnabled	(Z)V
    //   105: invokestatic 346	com/tapjoy/TapjoyConnectCore:h	()V
    //   108: getstatic 137	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   111: invokevirtual 350	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   114: ldc_w 352
    //   117: aconst_null
    //   118: getstatic 137	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   121: invokevirtual 356	android/content/Context:getPackageName	()Ljava/lang/String;
    //   124: invokevirtual 362	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   127: istore_2
    //   128: new 364	java/util/Properties
    //   131: dup
    //   132: invokespecial 365	java/util/Properties:<init>	()V
    //   135: astore_1
    //   136: aload_1
    //   137: getstatic 137	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   140: invokevirtual 350	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   143: iload_2
    //   144: invokevirtual 369	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   147: invokevirtual 373	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   150: aload_1
    //   151: invokestatic 376	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   154: ldc_w 378
    //   157: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   160: ldc -87
    //   162: if_acmpne +7 -> 169
    //   165: aload_0
    //   166: invokespecial 380	com/tapjoy/TapjoyConnectCore:i	()V
    //   169: getstatic 137	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   172: invokevirtual 384	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   175: ldc_w 386
    //   178: invokestatic 392	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   181: astore_1
    //   182: aload_1
    //   183: putstatic 171	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   186: aload_1
    //   187: ifnull +12 -> 199
    //   190: getstatic 171	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   193: invokevirtual 395	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   196: putstatic 171	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   199: getstatic 315	com/tapjoy/TapjoyConnectCore:al	Landroid/content/pm/PackageManager;
    //   202: getstatic 137	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   205: invokevirtual 356	android/content/Context:getPackageName	()Ljava/lang/String;
    //   208: iconst_0
    //   209: invokevirtual 401	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   212: getfield 406	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   215: putstatic 195	com/tapjoy/TapjoyConnectCore:A	Ljava/lang/String;
    //   218: ldc_w 408
    //   221: putstatic 185	com/tapjoy/TapjoyConnectCore:v	Ljava/lang/String;
    //   224: ldc_w 408
    //   227: putstatic 211	com/tapjoy/TapjoyConnectCore:I	Ljava/lang/String;
    //   230: getstatic 413	android/os/Build:MODEL	Ljava/lang/String;
    //   233: putstatic 181	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   236: getstatic 416	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   239: putstatic 183	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   242: getstatic 421	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   245: putstatic 187	com/tapjoy/TapjoyConnectCore:w	Ljava/lang/String;
    //   248: invokestatic 427	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   251: invokevirtual 430	java/util/Locale:getCountry	()Ljava/lang/String;
    //   254: putstatic 189	com/tapjoy/TapjoyConnectCore:x	Ljava/lang/String;
    //   257: invokestatic 427	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   260: invokevirtual 433	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   263: putstatic 191	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   266: ldc_w 435
    //   269: putstatic 197	com/tapjoy/TapjoyConnectCore:B	Ljava/lang/String;
    //   272: ldc_w 437
    //   275: putstatic 199	com/tapjoy/TapjoyConnectCore:C	Ljava/lang/String;
    //   278: getstatic 440	android/os/Build$VERSION:SDK_INT	I
    //   281: iconst_3
    //   282: if_icmple +35 -> 317
    //   285: new 442	com/tapjoy/TapjoyDisplayMetricsUtil
    //   288: dup
    //   289: getstatic 137	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   292: invokespecial 443	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   295: astore_1
    //   296: aload_1
    //   297: invokevirtual 447	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   300: putstatic 201	com/tapjoy/TapjoyConnectCore:D	I
    //   303: aload_1
    //   304: invokevirtual 451	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   307: putstatic 203	com/tapjoy/TapjoyConnectCore:E	F
    //   310: aload_1
    //   311: invokevirtual 454	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   314: putstatic 205	com/tapjoy/TapjoyConnectCore:F	I
    //   317: ldc_w 456
    //   320: invokestatic 459	com/tapjoy/TapjoyConnectCore:h	(Ljava/lang/String;)Z
    //   323: istore_3
    //   324: iload_3
    //   325: ifeq +951 -> 1276
    //   328: getstatic 137	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   331: ldc_w 461
    //   334: invokevirtual 465	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   337: checkcast 467	android/net/wifi/WifiManager
    //   340: astore_1
    //   341: aload_1
    //   342: ifnull +42 -> 384
    //   345: aload_1
    //   346: invokevirtual 471	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   349: astore_1
    //   350: aload_1
    //   351: ifnull +33 -> 384
    //   354: aload_1
    //   355: invokevirtual 476	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   358: astore_1
    //   359: aload_1
    //   360: putstatic 177	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   363: aload_1
    //   364: ifnull +20 -> 384
    //   367: getstatic 177	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   370: ldc_w 478
    //   373: ldc -87
    //   375: invokevirtual 482	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   378: invokevirtual 395	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   381: putstatic 177	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   384: getstatic 137	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   387: ldc_w 484
    //   390: invokevirtual 465	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   393: checkcast 486	android/telephony/TelephonyManager
    //   396: astore_1
    //   397: aload_1
    //   398: ifnull +223 -> 621
    //   401: aload_1
    //   402: invokevirtual 489	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   405: putstatic 213	com/tapjoy/TapjoyConnectCore:J	Ljava/lang/String;
    //   408: aload_1
    //   409: invokevirtual 492	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   412: putstatic 215	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   415: aload_1
    //   416: invokevirtual 495	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   419: ifnull +49 -> 468
    //   422: aload_1
    //   423: invokevirtual 495	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   426: invokevirtual 498	java/lang/String:length	()I
    //   429: iconst_5
    //   430: if_icmpeq +15 -> 445
    //   433: aload_1
    //   434: invokevirtual 495	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   437: invokevirtual 498	java/lang/String:length	()I
    //   440: bipush 6
    //   442: if_icmpne +26 -> 468
    //   445: aload_1
    //   446: invokevirtual 495	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   449: iconst_0
    //   450: iconst_3
    //   451: invokevirtual 502	java/lang/String:substring	(II)Ljava/lang/String;
    //   454: putstatic 217	com/tapjoy/TapjoyConnectCore:L	Ljava/lang/String;
    //   457: aload_1
    //   458: invokevirtual 495	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   461: iconst_3
    //   462: invokevirtual 505	java/lang/String:substring	(I)Ljava/lang/String;
    //   465: putstatic 219	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   468: ldc_w 507
    //   471: invokestatic 459	com/tapjoy/TapjoyConnectCore:h	(Ljava/lang/String;)Z
    //   474: istore_3
    //   475: iload_3
    //   476: ifeq +992 -> 1468
    //   479: ldc_w 509
    //   482: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   485: ifnull +803 -> 1288
    //   488: ldc_w 509
    //   491: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   494: invokevirtual 498	java/lang/String:length	()I
    //   497: ifle +791 -> 1288
    //   500: ldc_w 509
    //   503: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   506: putstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   509: ldc_w 511
    //   512: new 513	java/lang/StringBuilder
    //   515: dup
    //   516: ldc_w 515
    //   519: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   522: getstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   525: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   528: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   531: invokestatic 528	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   534: getstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   537: ifnonnull +795 -> 1332
    //   540: ldc_w 511
    //   543: ldc_w 530
    //   546: invokestatic 532	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   549: iconst_0
    //   550: istore_2
    //   551: ldc_w 511
    //   554: new 513	java/lang/StringBuilder
    //   557: dup
    //   558: ldc_w 534
    //   561: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   564: getstatic 440	android/os/Build$VERSION:SDK_INT	I
    //   567: invokevirtual 537	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   570: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   573: invokestatic 528	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   576: getstatic 440	android/os/Build$VERSION:SDK_INT	I
    //   579: bipush 9
    //   581: if_icmplt +40 -> 621
    //   584: ldc_w 511
    //   587: ldc_w 539
    //   590: invokestatic 528	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   593: aload_0
    //   594: invokevirtual 542	com/tapjoy/TapjoyConnectCore:getSerial	()Ljava/lang/String;
    //   597: astore_1
    //   598: iload_2
    //   599: ifne +7 -> 606
    //   602: aload_1
    //   603: putstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   606: getstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   609: ifnonnull +787 -> 1396
    //   612: ldc_w 511
    //   615: ldc_w 544
    //   618: invokestatic 532	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   621: getstatic 137	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   624: ldc_w 546
    //   627: iconst_0
    //   628: invokevirtual 550	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   631: astore_1
    //   632: aload_1
    //   633: ldc_w 552
    //   636: ldc -87
    //   638: invokeinterface 557 3 0
    //   643: astore 4
    //   645: aload 4
    //   647: putstatic 179	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   650: aload 4
    //   652: ifnull +14 -> 666
    //   655: getstatic 179	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   658: invokevirtual 498	java/lang/String:length	()I
    //   661: istore_2
    //   662: iload_2
    //   663: ifne +61 -> 724
    //   666: new 513	java/lang/StringBuilder
    //   669: dup
    //   670: invokespecial 558	java/lang/StringBuilder:<init>	()V
    //   673: invokestatic 564	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   676: invokevirtual 565	java/util/UUID:toString	()Ljava/lang/String;
    //   679: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   682: invokestatic 571	java/lang/System:currentTimeMillis	()J
    //   685: invokevirtual 574	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   688: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   691: invokestatic 579	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   694: putstatic 179	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   697: aload_1
    //   698: invokeinterface 583 1 0
    //   703: astore_1
    //   704: aload_1
    //   705: ldc_w 552
    //   708: getstatic 179	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   711: invokeinterface 589 3 0
    //   716: pop
    //   717: aload_1
    //   718: invokeinterface 593 1 0
    //   723: pop
    //   724: ldc_w 595
    //   727: ldc_w 597
    //   730: invokestatic 600	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   733: putstatic 209	com/tapjoy/TapjoyConnectCore:H	Z
    //   736: ldc_w 602
    //   739: invokestatic 604	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   742: putstatic 239	com/tapjoy/TapjoyConnectCore:V	Z
    //   745: ldc_w 606
    //   748: invokestatic 604	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   751: putstatic 241	com/tapjoy/TapjoyConnectCore:W	Z
    //   754: ldc_w 608
    //   757: invokestatic 604	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   760: putstatic 243	com/tapjoy/TapjoyConnectCore:X	Z
    //   763: ldc_w 610
    //   766: invokestatic 604	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   769: putstatic 245	com/tapjoy/TapjoyConnectCore:Y	Z
    //   772: ldc_w 612
    //   775: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   778: ifnull +71 -> 849
    //   781: ldc_w 612
    //   784: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   787: invokevirtual 498	java/lang/String:length	()I
    //   790: ifle +59 -> 849
    //   793: ldc_w 612
    //   796: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   799: putstatic 225	com/tapjoy/TapjoyConnectCore:P	Ljava/lang/String;
    //   802: new 614	java/util/ArrayList
    //   805: dup
    //   806: getstatic 617	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   809: invokestatic 161	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   812: invokespecial 618	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   815: getstatic 225	com/tapjoy/TapjoyConnectCore:P	Ljava/lang/String;
    //   818: invokevirtual 621	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   821: ifne +28 -> 849
    //   824: ldc_w 511
    //   827: new 513	java/lang/StringBuilder
    //   830: dup
    //   831: ldc_w 623
    //   834: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   837: getstatic 225	com/tapjoy/TapjoyConnectCore:P	Ljava/lang/String;
    //   840: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   843: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   846: invokestatic 625	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   849: getstatic 225	com/tapjoy/TapjoyConnectCore:P	Ljava/lang/String;
    //   852: astore_1
    //   853: new 627	android/content/Intent
    //   856: dup
    //   857: ldc_w 629
    //   860: invokespecial 630	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   863: astore 4
    //   865: aload_1
    //   866: invokevirtual 498	java/lang/String:length	()I
    //   869: ifgt +701 -> 1570
    //   872: aload 4
    //   874: ldc_w 632
    //   877: invokestatic 638	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   880: invokevirtual 642	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   883: pop
    //   884: getstatic 315	com/tapjoy/TapjoyConnectCore:al	Landroid/content/pm/PackageManager;
    //   887: aload 4
    //   889: iconst_0
    //   890: invokevirtual 646	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   893: invokeinterface 651 1 0
    //   898: ifle +752 -> 1650
    //   901: iconst_1
    //   902: istore_3
    //   903: iload_3
    //   904: putstatic 247	com/tapjoy/TapjoyConnectCore:Z	Z
    //   907: invokestatic 653	com/tapjoy/TapjoyConnectCore:f	()V
    //   910: ldc_w 655
    //   913: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   916: ifnull +24 -> 940
    //   919: ldc_w 655
    //   922: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   925: invokevirtual 498	java/lang/String:length	()I
    //   928: ifle +12 -> 940
    //   931: ldc_w 655
    //   934: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   937: putstatic 269	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   940: ldc_w 657
    //   943: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   946: ifnull +24 -> 970
    //   949: ldc_w 657
    //   952: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   955: invokevirtual 498	java/lang/String:length	()I
    //   958: ifle +12 -> 970
    //   961: ldc_w 657
    //   964: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   967: putstatic 267	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   970: ldc_w 659
    //   973: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   976: ifnull +52 -> 1028
    //   979: ldc_w 659
    //   982: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   985: invokevirtual 498	java/lang/String:length	()I
    //   988: ifle +40 -> 1028
    //   991: ldc_w 511
    //   994: new 513	java/lang/StringBuilder
    //   997: dup
    //   998: ldc_w 661
    //   1001: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1004: ldc_w 659
    //   1007: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1010: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1013: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1016: invokestatic 528	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1019: ldc_w 659
    //   1022: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1025: invokestatic 664	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;)V
    //   1028: ldc_w 666
    //   1031: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1034: invokestatic 669	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   1037: putstatic 235	com/tapjoy/TapjoyConnectCore:T	Ljava/lang/String;
    //   1040: new 513	java/lang/StringBuilder
    //   1043: dup
    //   1044: ldc_w 515
    //   1047: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1050: getstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1053: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1056: astore 4
    //   1058: ldc_w 509
    //   1061: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1064: ifnull +576 -> 1640
    //   1067: ldc_w 509
    //   1070: invokestatic 330	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1073: invokevirtual 498	java/lang/String:length	()I
    //   1076: ifle +564 -> 1640
    //   1079: ldc_w 671
    //   1082: astore_1
    //   1083: ldc_w 511
    //   1086: aload 4
    //   1088: aload_1
    //   1089: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1092: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1095: invokestatic 528	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1098: getstatic 276	com/tapjoy/TapjoyConnectCore:ao	Ljava/util/Hashtable;
    //   1101: ifnull +6 -> 1107
    //   1104: invokestatic 673	com/tapjoy/TapjoyConnectCore:g	()V
    //   1107: aload_0
    //   1108: invokevirtual 676	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   1111: aload_0
    //   1112: iconst_1
    //   1113: putfield 304	com/tapjoy/TapjoyConnectCore:aj	Z
    //   1116: return
    //   1117: astore_1
    //   1118: new 290	com/tapjoy/TapjoyException
    //   1121: dup
    //   1122: aload_1
    //   1123: invokevirtual 679	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   1126: invokespecial 680	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   1129: athrow
    //   1130: astore_1
    //   1131: ldc_w 511
    //   1134: new 513	java/lang/StringBuilder
    //   1137: dup
    //   1138: ldc_w 682
    //   1141: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1144: aload_1
    //   1145: invokevirtual 683	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   1148: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1151: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1154: invokestatic 688	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1157: pop
    //   1158: getstatic 145	com/tapjoy/TapjoyConnectCore:k	Lcom/tapjoy/TJConnectListener;
    //   1161: ifnull +494 -> 1655
    //   1164: getstatic 145	com/tapjoy/TapjoyConnectCore:k	Lcom/tapjoy/TJConnectListener;
    //   1167: invokeinterface 693 1 0
    //   1172: return
    //   1173: astore_1
    //   1174: ldc_w 511
    //   1177: new 513	java/lang/StringBuilder
    //   1180: dup
    //   1181: ldc_w 695
    //   1184: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1187: aload_1
    //   1188: invokevirtual 696	java/lang/Exception:toString	()Ljava/lang/String;
    //   1191: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1194: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1197: invokestatic 532	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1200: goto -883 -> 317
    //   1203: astore_1
    //   1204: ldc_w 511
    //   1207: new 513	java/lang/StringBuilder
    //   1210: dup
    //   1211: ldc_w 698
    //   1214: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1217: aload_1
    //   1218: invokevirtual 699	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   1221: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1224: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1227: invokestatic 688	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1230: pop
    //   1231: getstatic 145	com/tapjoy/TapjoyConnectCore:k	Lcom/tapjoy/TJConnectListener;
    //   1234: ifnull +421 -> 1655
    //   1237: getstatic 145	com/tapjoy/TapjoyConnectCore:k	Lcom/tapjoy/TJConnectListener;
    //   1240: invokeinterface 693 1 0
    //   1245: return
    //   1246: astore_1
    //   1247: ldc_w 511
    //   1250: new 513	java/lang/StringBuilder
    //   1253: dup
    //   1254: ldc_w 701
    //   1257: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1260: aload_1
    //   1261: invokevirtual 696	java/lang/Exception:toString	()Ljava/lang/String;
    //   1264: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1267: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1270: invokestatic 532	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1273: goto -889 -> 384
    //   1276: ldc_w 511
    //   1279: ldc_w 703
    //   1282: invokestatic 528	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1285: goto -901 -> 384
    //   1288: aload_1
    //   1289: invokevirtual 706	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1292: putstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1295: goto -786 -> 509
    //   1298: astore_1
    //   1299: ldc_w 511
    //   1302: new 513	java/lang/StringBuilder
    //   1305: dup
    //   1306: ldc_w 708
    //   1309: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1312: aload_1
    //   1313: invokevirtual 696	java/lang/Exception:toString	()Ljava/lang/String;
    //   1316: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1319: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1322: invokestatic 532	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1325: aconst_null
    //   1326: putstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1329: goto -708 -> 621
    //   1332: getstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1335: invokevirtual 498	java/lang/String:length	()I
    //   1338: ifeq +27 -> 1365
    //   1341: getstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1344: ldc_w 710
    //   1347: invokevirtual 338	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1350: ifne +15 -> 1365
    //   1353: getstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1356: ldc_w 712
    //   1359: invokevirtual 338	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1362: ifeq +17 -> 1379
    //   1365: ldc_w 511
    //   1368: ldc_w 714
    //   1371: invokestatic 532	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1374: iconst_0
    //   1375: istore_2
    //   1376: goto -825 -> 551
    //   1379: getstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1382: invokestatic 427	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1385: invokevirtual 717	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1388: putstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1391: iconst_1
    //   1392: istore_2
    //   1393: goto -842 -> 551
    //   1396: getstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1399: invokevirtual 498	java/lang/String:length	()I
    //   1402: ifeq +39 -> 1441
    //   1405: getstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1408: ldc_w 710
    //   1411: invokevirtual 338	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1414: ifne +27 -> 1441
    //   1417: getstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1420: ldc_w 712
    //   1423: invokevirtual 338	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1426: ifne +15 -> 1441
    //   1429: getstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1432: ldc_w 719
    //   1435: invokevirtual 338	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1438: ifeq +15 -> 1453
    //   1441: ldc_w 511
    //   1444: ldc_w 721
    //   1447: invokestatic 532	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1450: goto -829 -> 621
    //   1453: getstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1456: invokestatic 427	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1459: invokevirtual 717	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1462: putstatic 175	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1465: goto -844 -> 621
    //   1468: ldc_w 511
    //   1471: ldc_w 723
    //   1474: invokestatic 528	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1477: goto -856 -> 621
    //   1480: astore_1
    //   1481: ldc_w 511
    //   1484: new 513	java/lang/StringBuilder
    //   1487: dup
    //   1488: ldc_w 725
    //   1491: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1494: aload_1
    //   1495: invokevirtual 696	java/lang/Exception:toString	()Ljava/lang/String;
    //   1498: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1501: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1504: invokestatic 532	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1507: goto -783 -> 724
    //   1510: astore_1
    //   1511: ldc_w 511
    //   1514: new 513	java/lang/StringBuilder
    //   1517: dup
    //   1518: ldc_w 727
    //   1521: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1524: aload_1
    //   1525: invokevirtual 696	java/lang/Exception:toString	()Ljava/lang/String;
    //   1528: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1531: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1534: invokestatic 532	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1537: goto -801 -> 736
    //   1540: astore_1
    //   1541: ldc_w 511
    //   1544: new 513	java/lang/StringBuilder
    //   1547: dup
    //   1548: ldc_w 729
    //   1551: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1554: aload_1
    //   1555: invokevirtual 696	java/lang/Exception:toString	()Ljava/lang/String;
    //   1558: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1561: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1564: invokestatic 532	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1567: goto -795 -> 772
    //   1570: aload_1
    //   1571: ldc_w 731
    //   1574: invokevirtual 338	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1577: ifeq +13 -> 1590
    //   1580: ldc_w 733
    //   1583: invokestatic 735	com/tapjoy/TapjoyConnectCore:f	(Ljava/lang/String;)Z
    //   1586: istore_3
    //   1587: goto -684 -> 903
    //   1590: aload_1
    //   1591: ldc_w 737
    //   1594: invokevirtual 338	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1597: ifeq +53 -> 1650
    //   1600: ldc_w 739
    //   1603: invokestatic 735	com/tapjoy/TapjoyConnectCore:f	(Ljava/lang/String;)Z
    //   1606: istore_3
    //   1607: goto -704 -> 903
    //   1610: astore_1
    //   1611: ldc_w 511
    //   1614: new 513	java/lang/StringBuilder
    //   1617: dup
    //   1618: ldc_w 741
    //   1621: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1624: aload_1
    //   1625: invokevirtual 696	java/lang/Exception:toString	()Ljava/lang/String;
    //   1628: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1631: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1634: invokestatic 532	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1637: goto -730 -> 907
    //   1640: ldc -87
    //   1642: astore_1
    //   1643: goto -560 -> 1083
    //   1646: astore_1
    //   1647: goto -1493 -> 154
    //   1650: iconst_0
    //   1651: istore_3
    //   1652: goto -749 -> 903
    //   1655: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1656	0	this	TapjoyConnectCore
    //   0	1656	1	paramContext	Context
    //   127	1266	2	i1	int
    //   323	1329	3	bool	boolean
    //   643	444	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   199	218	1117	android/content/pm/PackageManager$NameNotFoundException
    //   61	77	1130	com/tapjoy/TapjoyIntegrationException
    //   77	105	1130	com/tapjoy/TapjoyIntegrationException
    //   105	136	1130	com/tapjoy/TapjoyIntegrationException
    //   136	154	1130	com/tapjoy/TapjoyIntegrationException
    //   154	169	1130	com/tapjoy/TapjoyIntegrationException
    //   169	186	1130	com/tapjoy/TapjoyIntegrationException
    //   190	199	1130	com/tapjoy/TapjoyIntegrationException
    //   199	218	1130	com/tapjoy/TapjoyIntegrationException
    //   218	278	1130	com/tapjoy/TapjoyIntegrationException
    //   278	317	1130	com/tapjoy/TapjoyIntegrationException
    //   317	324	1130	com/tapjoy/TapjoyIntegrationException
    //   328	341	1130	com/tapjoy/TapjoyIntegrationException
    //   345	350	1130	com/tapjoy/TapjoyIntegrationException
    //   354	363	1130	com/tapjoy/TapjoyIntegrationException
    //   367	384	1130	com/tapjoy/TapjoyIntegrationException
    //   384	397	1130	com/tapjoy/TapjoyIntegrationException
    //   401	445	1130	com/tapjoy/TapjoyIntegrationException
    //   445	468	1130	com/tapjoy/TapjoyIntegrationException
    //   468	475	1130	com/tapjoy/TapjoyIntegrationException
    //   479	509	1130	com/tapjoy/TapjoyIntegrationException
    //   509	549	1130	com/tapjoy/TapjoyIntegrationException
    //   551	598	1130	com/tapjoy/TapjoyIntegrationException
    //   602	606	1130	com/tapjoy/TapjoyIntegrationException
    //   606	621	1130	com/tapjoy/TapjoyIntegrationException
    //   621	650	1130	com/tapjoy/TapjoyIntegrationException
    //   655	662	1130	com/tapjoy/TapjoyIntegrationException
    //   666	724	1130	com/tapjoy/TapjoyIntegrationException
    //   724	736	1130	com/tapjoy/TapjoyIntegrationException
    //   736	772	1130	com/tapjoy/TapjoyIntegrationException
    //   772	849	1130	com/tapjoy/TapjoyIntegrationException
    //   849	901	1130	com/tapjoy/TapjoyIntegrationException
    //   903	907	1130	com/tapjoy/TapjoyIntegrationException
    //   907	940	1130	com/tapjoy/TapjoyIntegrationException
    //   940	970	1130	com/tapjoy/TapjoyIntegrationException
    //   970	1028	1130	com/tapjoy/TapjoyIntegrationException
    //   1028	1079	1130	com/tapjoy/TapjoyIntegrationException
    //   1083	1107	1130	com/tapjoy/TapjoyIntegrationException
    //   1107	1116	1130	com/tapjoy/TapjoyIntegrationException
    //   1118	1130	1130	com/tapjoy/TapjoyIntegrationException
    //   1174	1200	1130	com/tapjoy/TapjoyIntegrationException
    //   1247	1273	1130	com/tapjoy/TapjoyIntegrationException
    //   1276	1285	1130	com/tapjoy/TapjoyIntegrationException
    //   1288	1295	1130	com/tapjoy/TapjoyIntegrationException
    //   1299	1329	1130	com/tapjoy/TapjoyIntegrationException
    //   1332	1365	1130	com/tapjoy/TapjoyIntegrationException
    //   1365	1374	1130	com/tapjoy/TapjoyIntegrationException
    //   1379	1391	1130	com/tapjoy/TapjoyIntegrationException
    //   1396	1441	1130	com/tapjoy/TapjoyIntegrationException
    //   1441	1450	1130	com/tapjoy/TapjoyIntegrationException
    //   1453	1465	1130	com/tapjoy/TapjoyIntegrationException
    //   1468	1477	1130	com/tapjoy/TapjoyIntegrationException
    //   1481	1507	1130	com/tapjoy/TapjoyIntegrationException
    //   1511	1537	1130	com/tapjoy/TapjoyIntegrationException
    //   1541	1567	1130	com/tapjoy/TapjoyIntegrationException
    //   1570	1587	1130	com/tapjoy/TapjoyIntegrationException
    //   1590	1607	1130	com/tapjoy/TapjoyIntegrationException
    //   1611	1637	1130	com/tapjoy/TapjoyIntegrationException
    //   278	317	1173	java/lang/Exception
    //   61	77	1203	com/tapjoy/TapjoyException
    //   77	105	1203	com/tapjoy/TapjoyException
    //   105	136	1203	com/tapjoy/TapjoyException
    //   136	154	1203	com/tapjoy/TapjoyException
    //   154	169	1203	com/tapjoy/TapjoyException
    //   169	186	1203	com/tapjoy/TapjoyException
    //   190	199	1203	com/tapjoy/TapjoyException
    //   199	218	1203	com/tapjoy/TapjoyException
    //   218	278	1203	com/tapjoy/TapjoyException
    //   278	317	1203	com/tapjoy/TapjoyException
    //   317	324	1203	com/tapjoy/TapjoyException
    //   328	341	1203	com/tapjoy/TapjoyException
    //   345	350	1203	com/tapjoy/TapjoyException
    //   354	363	1203	com/tapjoy/TapjoyException
    //   367	384	1203	com/tapjoy/TapjoyException
    //   384	397	1203	com/tapjoy/TapjoyException
    //   401	445	1203	com/tapjoy/TapjoyException
    //   445	468	1203	com/tapjoy/TapjoyException
    //   468	475	1203	com/tapjoy/TapjoyException
    //   479	509	1203	com/tapjoy/TapjoyException
    //   509	549	1203	com/tapjoy/TapjoyException
    //   551	598	1203	com/tapjoy/TapjoyException
    //   602	606	1203	com/tapjoy/TapjoyException
    //   606	621	1203	com/tapjoy/TapjoyException
    //   621	650	1203	com/tapjoy/TapjoyException
    //   655	662	1203	com/tapjoy/TapjoyException
    //   666	724	1203	com/tapjoy/TapjoyException
    //   724	736	1203	com/tapjoy/TapjoyException
    //   736	772	1203	com/tapjoy/TapjoyException
    //   772	849	1203	com/tapjoy/TapjoyException
    //   849	901	1203	com/tapjoy/TapjoyException
    //   903	907	1203	com/tapjoy/TapjoyException
    //   907	940	1203	com/tapjoy/TapjoyException
    //   940	970	1203	com/tapjoy/TapjoyException
    //   970	1028	1203	com/tapjoy/TapjoyException
    //   1028	1079	1203	com/tapjoy/TapjoyException
    //   1083	1107	1203	com/tapjoy/TapjoyException
    //   1107	1116	1203	com/tapjoy/TapjoyException
    //   1118	1130	1203	com/tapjoy/TapjoyException
    //   1174	1200	1203	com/tapjoy/TapjoyException
    //   1247	1273	1203	com/tapjoy/TapjoyException
    //   1276	1285	1203	com/tapjoy/TapjoyException
    //   1288	1295	1203	com/tapjoy/TapjoyException
    //   1299	1329	1203	com/tapjoy/TapjoyException
    //   1332	1365	1203	com/tapjoy/TapjoyException
    //   1365	1374	1203	com/tapjoy/TapjoyException
    //   1379	1391	1203	com/tapjoy/TapjoyException
    //   1396	1441	1203	com/tapjoy/TapjoyException
    //   1441	1450	1203	com/tapjoy/TapjoyException
    //   1453	1465	1203	com/tapjoy/TapjoyException
    //   1468	1477	1203	com/tapjoy/TapjoyException
    //   1481	1507	1203	com/tapjoy/TapjoyException
    //   1511	1537	1203	com/tapjoy/TapjoyException
    //   1541	1567	1203	com/tapjoy/TapjoyException
    //   1570	1587	1203	com/tapjoy/TapjoyException
    //   1590	1607	1203	com/tapjoy/TapjoyException
    //   1611	1637	1203	com/tapjoy/TapjoyException
    //   328	341	1246	java/lang/Exception
    //   345	350	1246	java/lang/Exception
    //   354	363	1246	java/lang/Exception
    //   367	384	1246	java/lang/Exception
    //   479	509	1298	java/lang/Exception
    //   509	549	1298	java/lang/Exception
    //   551	598	1298	java/lang/Exception
    //   602	606	1298	java/lang/Exception
    //   606	621	1298	java/lang/Exception
    //   1288	1295	1298	java/lang/Exception
    //   1332	1365	1298	java/lang/Exception
    //   1365	1374	1298	java/lang/Exception
    //   1379	1391	1298	java/lang/Exception
    //   1396	1441	1298	java/lang/Exception
    //   1441	1450	1298	java/lang/Exception
    //   1453	1465	1298	java/lang/Exception
    //   666	724	1480	java/lang/Exception
    //   724	736	1510	java/lang/Exception
    //   736	772	1540	java/lang/Exception
    //   849	901	1610	java/lang/Exception
    //   903	907	1610	java/lang/Exception
    //   1570	1587	1610	java/lang/Exception
    //   1590	1607	1610	java/lang/Exception
    //   136	154	1646	java/lang/Exception
  }
  
  private static String a(long paramLong)
  {
    try
    {
      String str = TapjoyUtil.SHA256(z + ":" + n() + ":" + paramLong + ":" + Q);
      return str;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", "getVerifier ERROR: " + localException.toString());
    }
    return "";
  }
  
  private static String a(long paramLong, String paramString)
  {
    try
    {
      paramString = TapjoyUtil.SHA256(z + ":" + n() + ":" + paramLong + ":" + Q + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      TapjoyLog.e("TapjoyConnect", "getVerifier ERROR: " + paramString.toString());
    }
    return "";
  }
  
  private static void a(List paramList)
  {
    try
    {
      ap = "";
      Iterator localIterator = al.getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (((localApplicationInfo.flags & 0x1) != 1) && (paramList.contains(localApplicationInfo.packageName)))
        {
          TapjoyLog.i("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
          if (ap.length() > 0) {
            ap += ",";
          }
          ap += localApplicationInfo.packageName;
        }
      }
    }
    finally {}
  }
  
  private static void a(Properties paramProperties)
  {
    Enumeration localEnumeration = paramProperties.keys();
    while (localEnumeration.hasMoreElements()) {
      try
      {
        String str = (String)localEnumeration.nextElement();
        b(str, (String)paramProperties.get(str));
      }
      catch (ClassCastException localClassCastException)
      {
        TapjoyLog.e("TapjoyConnect", "Error parsing configuration properties in tapjoy_config.txt");
      }
    }
  }
  
  private static boolean a(String paramString1, String paramString2)
  {
    FeatureInfo[] arrayOfFeatureInfo = al.getSystemAvailableFeatures();
    int i2 = arrayOfFeatureInfo.length;
    int i1 = 0;
    while (i1 < i2)
    {
      if (arrayOfFeatureInfo[i1].name.matches(paramString1))
      {
        if (paramString2 == null) {}
        while (al.checkPermission(paramString2, g.getPackageName()) == 0) {
          return true;
        }
        return false;
      }
      i1 += 1;
    }
    return false;
  }
  
  private static void b(String paramString1, String paramString2)
  {
    String str;
    if (!paramString1.equals("TJC_OPTION_SERVICE_URL"))
    {
      str = paramString2;
      if (!paramString1.equals("TJC_OPTION_PLACEMENT_SERVICE_URL")) {}
    }
    else
    {
      str = paramString2;
      if (!paramString2.endsWith("/")) {
        str = paramString2 + "/";
      }
    }
    ao.put(paramString1, str);
  }
  
  /* Error */
  private static boolean d(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 883	com/tapjoy/internal/bt:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bt;
    //   4: astore_0
    //   5: aload_0
    //   6: astore_3
    //   7: aload_0
    //   8: invokevirtual 886	com/tapjoy/internal/bt:e	()Ljava/util/Map;
    //   11: astore 5
    //   13: aload_0
    //   14: astore_3
    //   15: aload 5
    //   17: ldc_w 888
    //   20: invokeinterface 891 2 0
    //   25: checkcast 334	java/lang/String
    //   28: invokestatic 895	com/tapjoy/internal/cv:a	(Ljava/lang/String;)Ljava/lang/String;
    //   31: astore 4
    //   33: aload_0
    //   34: astore_3
    //   35: aload 5
    //   37: ldc_w 897
    //   40: invokeinterface 891 2 0
    //   45: checkcast 334	java/lang/String
    //   48: invokestatic 895	com/tapjoy/internal/cv:a	(Ljava/lang/String;)Ljava/lang/String;
    //   51: astore 7
    //   53: aload_0
    //   54: astore_3
    //   55: aload 5
    //   57: ldc_w 899
    //   60: invokeinterface 891 2 0
    //   65: checkcast 334	java/lang/String
    //   68: invokestatic 895	com/tapjoy/internal/cv:a	(Ljava/lang/String;)Ljava/lang/String;
    //   71: astore 8
    //   73: aload_0
    //   74: astore_3
    //   75: aload 5
    //   77: ldc_w 901
    //   80: invokeinterface 891 2 0
    //   85: checkcast 334	java/lang/String
    //   88: invokestatic 895	com/tapjoy/internal/cv:a	(Ljava/lang/String;)Ljava/lang/String;
    //   91: astore 9
    //   93: aload_0
    //   94: astore_3
    //   95: aload 5
    //   97: ldc_w 903
    //   100: invokeinterface 891 2 0
    //   105: checkcast 334	java/lang/String
    //   108: invokestatic 895	com/tapjoy/internal/cv:a	(Ljava/lang/String;)Ljava/lang/String;
    //   111: astore 6
    //   113: aload_0
    //   114: astore_3
    //   115: aload 5
    //   117: ldc_w 905
    //   120: invokeinterface 891 2 0
    //   125: checkcast 907	java/lang/Boolean
    //   128: astore 5
    //   130: aload 5
    //   132: ifnull +13 -> 145
    //   135: aload_0
    //   136: astore_3
    //   137: aload 5
    //   139: invokevirtual 910	java/lang/Boolean:booleanValue	()Z
    //   142: putstatic 284	com/tapjoy/TapjoyConnectCore:aY	Z
    //   145: aload_0
    //   146: astore_3
    //   147: new 912	com/tapjoy/internal/ApiKeyDecoded
    //   150: dup
    //   151: aload 8
    //   153: invokespecial 913	com/tapjoy/internal/ApiKeyDecoded:<init>	(Ljava/lang/String;)V
    //   156: astore 10
    //   158: aload_0
    //   159: astore_3
    //   160: aload 10
    //   162: invokevirtual 917	com/tapjoy/internal/ApiKeyDecoded:getKeyUsage	()Lcom/tapjoy/internal/ApiKeyDecoded$KeyUsage;
    //   165: getstatic 923	com/tapjoy/internal/ApiKeyDecoded$KeyUsage:RPC_ANALYTICS	Lcom/tapjoy/internal/ApiKeyDecoded$KeyUsage;
    //   168: if_acmpeq +33 -> 201
    //   171: aload_0
    //   172: astore_3
    //   173: new 876	java/io/IOException
    //   176: dup
    //   177: ldc_w 925
    //   180: invokespecial 926	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   183: athrow
    //   184: astore_3
    //   185: ldc_w 511
    //   188: aload_3
    //   189: invokevirtual 927	java/io/IOException:getMessage	()Ljava/lang/String;
    //   192: invokestatic 929	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   195: aload_0
    //   196: invokestatic 934	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   199: iconst_0
    //   200: ireturn
    //   201: aload_0
    //   202: astore_3
    //   203: aload 10
    //   205: invokevirtual 937	com/tapjoy/internal/ApiKeyDecoded:getAppId	()Ljava/lang/String;
    //   208: invokestatic 940	com/tapjoy/internal/ApiKeyDecoded:get5RocksAppId	(Ljava/lang/String;)Ljava/lang/String;
    //   211: astore 5
    //   213: aload_0
    //   214: astore_3
    //   215: aload 10
    //   217: invokevirtual 943	com/tapjoy/internal/ApiKeyDecoded:getSecretKey	()Ljava/lang/String;
    //   220: astore 10
    //   222: aload 4
    //   224: ifnonnull +224 -> 448
    //   227: aload 5
    //   229: astore 4
    //   231: aload_0
    //   232: astore_3
    //   233: invokestatic 948	com/tapjoy/internal/fv:a	()Lcom/tapjoy/internal/fv;
    //   236: getstatic 137	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   239: aload 8
    //   241: ldc_w 435
    //   244: ldc_w 950
    //   247: aload 5
    //   249: aload 10
    //   251: invokevirtual 953	com/tapjoy/internal/fv:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   254: aload_0
    //   255: astore_3
    //   256: aload 4
    //   258: putstatic 249	com/tapjoy/TapjoyConnectCore:aa	Ljava/lang/String;
    //   261: aload_0
    //   262: astore_3
    //   263: aload 7
    //   265: putstatic 251	com/tapjoy/TapjoyConnectCore:ab	Ljava/lang/String;
    //   268: aload_0
    //   269: astore_3
    //   270: aload 8
    //   272: putstatic 253	com/tapjoy/TapjoyConnectCore:ac	Ljava/lang/String;
    //   275: aload_0
    //   276: astore_3
    //   277: aload 9
    //   279: putstatic 255	com/tapjoy/TapjoyConnectCore:ad	Ljava/lang/String;
    //   282: aload_0
    //   283: astore_3
    //   284: new 614	java/util/ArrayList
    //   287: dup
    //   288: invokespecial 954	java/util/ArrayList:<init>	()V
    //   291: astore 4
    //   293: aload 6
    //   295: ifnull +64 -> 359
    //   298: aload_0
    //   299: astore_3
    //   300: aload 6
    //   302: ldc_w 783
    //   305: invokevirtual 958	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   308: astore 5
    //   310: aload_0
    //   311: astore_3
    //   312: aload 5
    //   314: arraylength
    //   315: istore_2
    //   316: iconst_0
    //   317: istore_1
    //   318: iload_1
    //   319: iload_2
    //   320: if_icmpge +39 -> 359
    //   323: aload_0
    //   324: astore_3
    //   325: aload 5
    //   327: iload_1
    //   328: aaload
    //   329: invokevirtual 861	java/lang/String:trim	()Ljava/lang/String;
    //   332: astore 6
    //   334: aload_0
    //   335: astore_3
    //   336: aload 6
    //   338: invokevirtual 498	java/lang/String:length	()I
    //   341: ifle +110 -> 451
    //   344: aload_0
    //   345: astore_3
    //   346: aload 4
    //   348: aload 6
    //   350: invokeinterface 959 2 0
    //   355: pop
    //   356: goto +95 -> 451
    //   359: aload_0
    //   360: astore_3
    //   361: aload 4
    //   363: invokeinterface 962 1 0
    //   368: ifne +10 -> 378
    //   371: aload_0
    //   372: astore_3
    //   373: aload 4
    //   375: invokestatic 866	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   378: aload_0
    //   379: astore_3
    //   380: aload_0
    //   381: invokevirtual 965	com/tapjoy/internal/bt:close	()V
    //   384: aconst_null
    //   385: invokestatic 934	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   388: iconst_1
    //   389: ireturn
    //   390: astore 4
    //   392: aconst_null
    //   393: astore_0
    //   394: aload_0
    //   395: astore_3
    //   396: ldc_w 511
    //   399: aload 4
    //   401: invokevirtual 966	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   404: invokestatic 929	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   407: aload_0
    //   408: invokestatic 934	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   411: goto -212 -> 199
    //   414: astore_0
    //   415: aconst_null
    //   416: astore_3
    //   417: aload_3
    //   418: invokestatic 934	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   421: aload_0
    //   422: athrow
    //   423: astore_0
    //   424: goto -7 -> 417
    //   427: astore 4
    //   429: aload_0
    //   430: astore_3
    //   431: aload 4
    //   433: astore_0
    //   434: goto -17 -> 417
    //   437: astore 4
    //   439: goto -45 -> 394
    //   442: astore_3
    //   443: aconst_null
    //   444: astore_0
    //   445: goto -260 -> 185
    //   448: goto -217 -> 231
    //   451: iload_1
    //   452: iconst_1
    //   453: iadd
    //   454: istore_1
    //   455: goto -137 -> 318
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	458	0	paramString	String
    //   317	138	1	i1	int
    //   315	6	2	i2	int
    //   6	167	3	str1	String
    //   184	5	3	localIOException1	java.io.IOException
    //   202	229	3	str2	String
    //   442	1	3	localIOException2	java.io.IOException
    //   31	343	4	localObject1	Object
    //   390	10	4	localRuntimeException1	RuntimeException
    //   427	5	4	localObject2	Object
    //   437	1	4	localRuntimeException2	RuntimeException
    //   11	315	5	localObject3	Object
    //   111	238	6	str3	String
    //   51	213	7	str4	String
    //   71	200	8	str5	String
    //   91	187	9	str6	String
    //   156	94	10	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   7	13	184	java/io/IOException
    //   15	33	184	java/io/IOException
    //   35	53	184	java/io/IOException
    //   55	73	184	java/io/IOException
    //   75	93	184	java/io/IOException
    //   95	113	184	java/io/IOException
    //   115	130	184	java/io/IOException
    //   137	145	184	java/io/IOException
    //   147	158	184	java/io/IOException
    //   160	171	184	java/io/IOException
    //   173	184	184	java/io/IOException
    //   203	213	184	java/io/IOException
    //   215	222	184	java/io/IOException
    //   233	254	184	java/io/IOException
    //   256	261	184	java/io/IOException
    //   263	268	184	java/io/IOException
    //   270	275	184	java/io/IOException
    //   277	282	184	java/io/IOException
    //   284	293	184	java/io/IOException
    //   300	310	184	java/io/IOException
    //   312	316	184	java/io/IOException
    //   325	334	184	java/io/IOException
    //   336	344	184	java/io/IOException
    //   346	356	184	java/io/IOException
    //   361	371	184	java/io/IOException
    //   373	378	184	java/io/IOException
    //   380	384	184	java/io/IOException
    //   0	5	390	java/lang/RuntimeException
    //   0	5	414	finally
    //   7	13	423	finally
    //   15	33	423	finally
    //   35	53	423	finally
    //   55	73	423	finally
    //   75	93	423	finally
    //   95	113	423	finally
    //   115	130	423	finally
    //   137	145	423	finally
    //   147	158	423	finally
    //   160	171	423	finally
    //   173	184	423	finally
    //   203	213	423	finally
    //   215	222	423	finally
    //   233	254	423	finally
    //   256	261	423	finally
    //   263	268	423	finally
    //   270	275	423	finally
    //   277	282	423	finally
    //   284	293	423	finally
    //   300	310	423	finally
    //   312	316	423	finally
    //   325	334	423	finally
    //   336	344	423	finally
    //   346	356	423	finally
    //   361	371	423	finally
    //   373	378	423	finally
    //   380	384	423	finally
    //   396	407	423	finally
    //   185	195	427	finally
    //   7	13	437	java/lang/RuntimeException
    //   15	33	437	java/lang/RuntimeException
    //   35	53	437	java/lang/RuntimeException
    //   55	73	437	java/lang/RuntimeException
    //   75	93	437	java/lang/RuntimeException
    //   95	113	437	java/lang/RuntimeException
    //   115	130	437	java/lang/RuntimeException
    //   137	145	437	java/lang/RuntimeException
    //   147	158	437	java/lang/RuntimeException
    //   160	171	437	java/lang/RuntimeException
    //   173	184	437	java/lang/RuntimeException
    //   203	213	437	java/lang/RuntimeException
    //   215	222	437	java/lang/RuntimeException
    //   233	254	437	java/lang/RuntimeException
    //   256	261	437	java/lang/RuntimeException
    //   263	268	437	java/lang/RuntimeException
    //   270	275	437	java/lang/RuntimeException
    //   277	282	437	java/lang/RuntimeException
    //   284	293	437	java/lang/RuntimeException
    //   300	310	437	java/lang/RuntimeException
    //   312	316	437	java/lang/RuntimeException
    //   325	334	437	java/lang/RuntimeException
    //   336	344	437	java/lang/RuntimeException
    //   346	356	437	java/lang/RuntimeException
    //   361	371	437	java/lang/RuntimeException
    //   373	378	437	java/lang/RuntimeException
    //   380	384	437	java/lang/RuntimeException
    //   0	5	442	java/io/IOException
  }
  
  private static Map e()
  {
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "plugin", R, true);
    TapjoyUtil.safePut(localHashMap3, "sdk_type", S, true);
    TapjoyUtil.safePut(localHashMap3, "app_id", z, true);
    TapjoyUtil.safePut(localHashMap3, "library_version", B, true);
    TapjoyUtil.safePut(localHashMap3, "library_revision", "1defb9e", true);
    TapjoyUtil.safePut(localHashMap3, "bridge_version", C, true);
    TapjoyUtil.safePut(localHashMap3, "app_version", A, true);
    localHashMap2.putAll(localHashMap3);
    localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "device_name", t, true);
    TapjoyUtil.safePut(localHashMap3, "platform", I, true);
    TapjoyUtil.safePut(localHashMap3, "os_version", w, true);
    TapjoyUtil.safePut(localHashMap3, "device_manufacturer", u, true);
    TapjoyUtil.safePut(localHashMap3, "device_type", v, true);
    TapjoyUtil.safePut(localHashMap3, "screen_layout_size", F, true);
    TapjoyUtil.safePut(localHashMap3, "device_location", String.valueOf(H), true);
    TapjoyUtil.safePut(localHashMap3, "store_name", P, true);
    TapjoyUtil.safePut(localHashMap3, "store_view", String.valueOf(Z), true);
    TapjoyUtil.safePut(localHashMap3, "country_code", x, true);
    TapjoyUtil.safePut(localHashMap3, "language_code", y, true);
    TapjoyUtil.safePut(localHashMap3, "carrier_name", J, true);
    TapjoyUtil.safePut(localHashMap3, "carrier_country_code", K, true);
    TapjoyUtil.safePut(localHashMap3, "mobile_network_code", M, true);
    TapjoyUtil.safePut(localHashMap3, "mobile_country_code", L, true);
    N = getConnectionType();
    TapjoyUtil.safePut(localHashMap3, "connection_type", N, true);
    O = getConnectionSubType();
    TapjoyUtil.safePut(localHashMap3, "connection_subtype", O, true);
    TapjoyUtil.safePut(localHashMap3, "screen_density", D, true);
    localHashMap2.putAll(localHashMap3);
    localHashMap3 = new HashMap();
    if (k())
    {
      TapjoyUtil.safePut(localHashMap3, "advertising_id", c, true);
      TapjoyUtil.safePut(localHashMap3, "ad_tracking_enabled", String.valueOf(d), true);
    }
    if (!l())
    {
      TapjoyUtil.safePut(localHashMap3, "android_id", n, true);
      TapjoyUtil.safePut(localHashMap3, "udid", q, true);
      TapjoyUtil.safePut(localHashMap3, "mac_address", r, true);
    }
    TapjoyUtil.safePut(localHashMap3, "threatmetrix_session_id", p, true);
    TapjoyUtil.safePut(localHashMap3, "install_id", s, true);
    TapjoyUtil.safePut(localHashMap3, "publisher_user_id", G, true);
    TapjoyUtil.safePut(localHashMap3, "ad_id_check_disabled", e, true);
    TapjoyUtil.safePut(localHashMap3, "persistent_ids_disabled", f, true);
    if (a != 0) {
      TapjoyUtil.safePut(localHashMap3, "packaged_gps_version", Integer.toString(a), true);
    }
    if (b != 0) {
      TapjoyUtil.safePut(localHashMap3, "device_gps_version", Integer.toString(b), true);
    }
    if ((o == null) || (o.length() == 0) || (System.currentTimeMillis() - ai > 1800000L)) {
      o = m();
    }
    for (;;)
    {
      TapjoyUtil.safePut(localHashMap3, "session_id", o, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "app_group_id", aa, true);
      TapjoyUtil.safePut(localHashMap3, "store", ab, true);
      TapjoyUtil.safePut(localHashMap3, "analytics_api_key", ac, true);
      TapjoyUtil.safePut(localHashMap3, "managed_device_id", ad, true);
      localHashMap2.putAll(localHashMap3);
      if ((TapjoyCache.getInstance() != null) && (TapjoyCache.getInstance().getCachedOfferIDs() != null) && (TapjoyCache.getInstance().getCachedOfferIDs().length() > 0)) {
        TapjoyUtil.safePut(localHashMap2, "cached_ids", TapjoyCache.getInstance().getCachedOfferIDs(), true);
      }
      TapjoyUtil.safePut(localHashMap2, "display_multiplier", Float.toString(U), true);
      localHashMap1.putAll(localHashMap2);
      localHashMap2 = new HashMap();
      f();
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "analytics_id", ar, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_id", as, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_sign", at, true);
      TapjoyUtil.safePut(localHashMap3, "display_d", aS, true);
      TapjoyUtil.safePut(localHashMap3, "display_w", aT, true);
      TapjoyUtil.safePut(localHashMap3, "display_h", aU, true);
      TapjoyUtil.safePut(localHashMap3, "country_sim", aV, true);
      TapjoyUtil.safePut(localHashMap3, "timezone", aW, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "pkg_ver", au, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_rev", av, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_data_ver", aw, true);
      TapjoyUtil.safePut(localHashMap3, "installer", ax, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "installed", ay, true);
      TapjoyUtil.safePut(localHashMap3, "referrer", az, true);
      TapjoyUtil.safePut(localHashMap3, "user_level", aA, true);
      TapjoyUtil.safePut(localHashMap3, "friend_count", aB, true);
      TapjoyUtil.safePut(localHashMap3, "uv1", aC, true);
      TapjoyUtil.safePut(localHashMap3, "uv2", aD, true);
      TapjoyUtil.safePut(localHashMap3, "uv3", aE, true);
      TapjoyUtil.safePut(localHashMap3, "uv4", aF, true);
      TapjoyUtil.safePut(localHashMap3, "uv5", aG, true);
      TapjoyUtil.safePut(localHashMap3, "fq7", aH, true);
      TapjoyUtil.safePut(localHashMap3, "fq30", aI, true);
      TapjoyUtil.safePut(localHashMap3, "session_total_count", aJ, true);
      TapjoyUtil.safePut(localHashMap3, "session_total_length", aK, true);
      TapjoyUtil.safePut(localHashMap3, "session_last_at", aL, true);
      TapjoyUtil.safePut(localHashMap3, "session_last_length", aM, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_currency", aN, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_total_count", aO, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_total_price", aP, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_last_price", aQ, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_last_at", aR, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap1.putAll(localHashMap2);
      return localHashMap1;
      ai = System.currentTimeMillis();
    }
  }
  
  /* Error */
  private static boolean e(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 883	com/tapjoy/internal/bt:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bt;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 1226	com/tapjoy/internal/bt:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 1228	com/tapjoy/internal/bt:t	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 511
    //   25: ldc_w 1230
    //   28: invokestatic 528	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 965	com/tapjoy/internal/bt:close	()V
    //   37: aconst_null
    //   38: invokestatic 934	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 965	com/tapjoy/internal/bt:close	()V
    //   49: aconst_null
    //   50: invokestatic 934	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   53: ldc_w 511
    //   56: ldc_w 1232
    //   59: invokestatic 532	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   62: iconst_0
    //   63: ireturn
    //   64: astore_2
    //   65: aconst_null
    //   66: astore_1
    //   67: aload_1
    //   68: astore_0
    //   69: ldc_w 511
    //   72: aload_2
    //   73: invokevirtual 927	java/io/IOException:getMessage	()Ljava/lang/String;
    //   76: invokestatic 929	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   79: aload_1
    //   80: invokestatic 934	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   83: goto -30 -> 53
    //   86: astore_2
    //   87: aconst_null
    //   88: astore_1
    //   89: aload_1
    //   90: astore_0
    //   91: ldc_w 511
    //   94: aload_2
    //   95: invokevirtual 966	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   98: invokestatic 929	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   101: aload_1
    //   102: invokestatic 934	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   105: goto -52 -> 53
    //   108: astore_1
    //   109: aconst_null
    //   110: astore_0
    //   111: aload_0
    //   112: invokestatic 934	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   115: aload_1
    //   116: athrow
    //   117: astore_1
    //   118: goto -7 -> 111
    //   121: astore_2
    //   122: goto -33 -> 89
    //   125: astore_2
    //   126: goto -59 -> 67
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	129	0	paramString	String
    //   4	98	1	localBt	com.tapjoy.internal.bt
    //   108	8	1	localObject1	Object
    //   117	1	1	localObject2	Object
    //   64	9	2	localIOException1	java.io.IOException
    //   86	9	2	localRuntimeException1	RuntimeException
    //   121	1	2	localRuntimeException2	RuntimeException
    //   125	1	2	localIOException2	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   0	5	64	java/io/IOException
    //   0	5	86	java/lang/RuntimeException
    //   0	5	108	finally
    //   7	14	117	finally
    //   16	20	117	finally
    //   22	31	117	finally
    //   33	37	117	finally
    //   45	49	117	finally
    //   69	79	117	finally
    //   91	101	117	finally
    //   7	14	121	java/lang/RuntimeException
    //   16	20	121	java/lang/RuntimeException
    //   22	31	121	java/lang/RuntimeException
    //   33	37	121	java/lang/RuntimeException
    //   45	49	121	java/lang/RuntimeException
    //   7	14	125	java/io/IOException
    //   16	20	125	java/io/IOException
    //   22	31	125	java/io/IOException
    //   33	37	125	java/io/IOException
    //   45	49	125	java/io/IOException
  }
  
  private static void f()
  {
    gk.n localN = fv.a(g).b(true);
    ar = localN.f().h();
    as = localN.f().B();
    at = localN.f().D();
    aS = localN.f().r();
    aT = localN.f().t();
    aU = localN.f().v();
    aV = localN.f().H();
    aW = localN.f().z();
    au = localN.h().f();
    av = localN.h().h();
    aw = localN.h().j();
    ax = localN.h().l();
    ay = localN.j().f();
    az = localN.j().h();
    aA = localN.j().N();
    aB = localN.j().P();
    aC = localN.j().R();
    aD = localN.j().T();
    aE = localN.j().V();
    aF = localN.j().X();
    aG = localN.j().Z();
    aH = localN.j().j();
    aI = localN.j().l();
    aJ = localN.j().p();
    aK = localN.j().r();
    aL = localN.j().t();
    aM = localN.j().v();
    aN = localN.j().x();
    aO = localN.j().z();
    aP = localN.j().B();
    aQ = localN.j().F();
    aR = localN.j().D();
  }
  
  private static boolean f(String paramString)
  {
    Iterator localIterator = al.getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.startsWith(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private static void g()
  {
    TapjoyLog.i("TapjoyConnect", "Connect Flags:");
    TapjoyLog.i("TapjoyConnect", "--------------------");
    Iterator localIterator = ao.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      TapjoyLog.i("TapjoyConnect", "key: " + (String)localEntry.getKey() + ", value: " + Uri.encode(localEntry.getValue().toString()));
    }
    TapjoyLog.i("TapjoyConnect", "hostURL: [" + getConnectFlagValue("TJC_OPTION_SERVICE_URL") + "]");
    TapjoyLog.i("TapjoyConnect", "redirectDomain: [" + T + "]");
    TapjoyLog.i("TapjoyConnect", "--------------------");
  }
  
  private static boolean g(String paramString)
  {
    Object localObject = new Intent("android.intent.action.SEND");
    ((Intent)localObject).setType("text/plain");
    localObject = al.queryIntentActivities((Intent)localObject, 0).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName.startsWith(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static String getAndroidID()
  {
    return n;
  }
  
  public static String getAppID()
  {
    return z;
  }
  
  public static String getAwardCurrencyVerifier(long paramLong, int paramInt, String paramString)
  {
    try
    {
      paramString = TapjoyUtil.SHA256(z + ":" + n() + ":" + paramLong + ":" + Q + ":" + paramInt + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      TapjoyLog.e("TapjoyConnect", "getAwardCurrencyVerifier ERROR: " + paramString.toString());
    }
    return "";
  }
  
  public static String getCarrierName()
  {
    return J;
  }
  
  public static String getConnectFlagValue(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    if (ao != null)
    {
      str1 = str2;
      if (ao.get(paramString) != null) {
        str1 = ao.get(paramString).toString();
      }
    }
    return str1;
  }
  
  public static String getConnectURL()
  {
    return "https://connect.tapjoy.com/";
  }
  
  public static String getConnectionSubType()
  {
    try
    {
      Object localObject = (ConnectivityManager)g.getSystemService("connectivity");
      if (localObject != null) {
        localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo().getSubtypeName();
      }
      return "";
    }
    catch (Exception localException1)
    {
      try
      {
        TapjoyLog.i("TapjoyConnect", "connection_sub_type: " + (String)localObject);
        return localObject;
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
      localException1 = localException1;
      localObject = "";
      TapjoyLog.e("TapjoyConnect", "getConnectionSubType error: " + localException1.toString());
      return localObject;
    }
  }
  
  public static String getConnectionType()
  {
    String str1 = "";
    String str3 = str1;
    String str4;
    for (;;)
    {
      try
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)g.getSystemService("connectivity");
        str4 = str1;
        if (localConnectivityManager == null) {
          break;
        }
        str3 = str1;
        str4 = str1;
        if (localConnectivityManager.getActiveNetworkInfo() == null) {
          break;
        }
        str3 = str1;
        switch (localConnectivityManager.getActiveNetworkInfo().getType())
        {
        case 1: 
          str3 = str1;
          TapjoyLog.i("TapjoyConnect", "connectivity: " + localConnectivityManager.getActiveNetworkInfo().getType());
          str3 = str1;
          TapjoyLog.i("TapjoyConnect", "connection_type: " + str1);
          str4 = str1;
        }
      }
      catch (Exception localException)
      {
        TapjoyLog.e("TapjoyConnect", "getConnectionType error: " + localException.toString());
        return str3;
      }
      str1 = "wifi";
      continue;
      String str2 = "mobile";
    }
    return str4;
  }
  
  public static Context getContext()
  {
    return g;
  }
  
  public static String getDeviceID()
  {
    return q;
  }
  
  public static float getDeviceScreenDensityScale()
  {
    return E;
  }
  
  public static Map getGenericURLParams()
  {
    Map localMap = e();
    TapjoyUtil.safePut(localMap, "app_id", z, true);
    return localMap;
  }
  
  public static String getHostURL()
  {
    return getConnectFlagValue("TJC_OPTION_SERVICE_URL");
  }
  
  public static TapjoyConnectCore getInstance()
  {
    return i;
  }
  
  public static String getMacAddress()
  {
    return r;
  }
  
  public static Map getOfflineLogs()
  {
    return g.getSharedPreferences("tapjoyOfflineLog", 0).getAll();
  }
  
  public static String getPlacementURL()
  {
    return getConnectFlagValue("TJC_OPTION_PLACEMENT_SERVICE_URL");
  }
  
  public static String getRedirectDomain()
  {
    return T;
  }
  
  public static String getSecretKey()
  {
    return Q;
  }
  
  public static String getSha1MacAddress()
  {
    try
    {
      String str = TapjoyUtil.SHA1(r);
      return str;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", "Error generating sha1 of macAddress: " + localException.toString());
    }
    return null;
  }
  
  public static Map getTimeStampAndVerifierParams()
  {
    long l1 = System.currentTimeMillis() / 1000L;
    String str = a(l1);
    HashMap localHashMap = new HashMap();
    TapjoyUtil.safePut(localHashMap, "timestamp", String.valueOf(l1), true);
    TapjoyUtil.safePut(localHashMap, "verifier", str, true);
    return localHashMap;
  }
  
  public static Map getURLParams()
  {
    Map localMap = getGenericURLParams();
    localMap.putAll(getTimeStampAndVerifierParams());
    return localMap;
  }
  
  public static String getUserID()
  {
    return G;
  }
  
  private static void h()
  {
    for (;;)
    {
      int i1;
      try
      {
        if (al == null) {
          break;
        }
        ApplicationInfo localApplicationInfo = al.getApplicationInfo(g.getPackageName(), 128);
        if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null))
        {
          String[] arrayOfString = TapjoyConnectFlag.FLAG_ARRAY;
          int i2 = arrayOfString.length;
          i1 = 0;
          if (i1 < i2)
          {
            String str1 = arrayOfString[i1];
            String str2 = localApplicationInfo.metaData.getString("tapjoy." + str1);
            if (str2 != null)
            {
              TapjoyLog.i("TapjoyConnect", "Found manifest flag: " + str1 + ", " + str2);
              b(str1, str2);
            }
          }
          else
          {
            TapjoyLog.i("TapjoyConnect", "Metadata successfully loaded");
          }
        }
        else
        {
          TapjoyLog.i("TapjoyConnect", "No metadata present.");
          return;
        }
      }
      catch (Exception localException)
      {
        TapjoyLog.e("TapjoyConnect", "Error reading manifest meta-data: " + localException.toString());
        return;
      }
      i1 += 1;
    }
  }
  
  private static boolean h(String paramString)
  {
    return al.checkPermission(paramString, g.getPackageName()) == 0;
  }
  
  private void i()
  {
    Object localObject3;
    for (;;)
    {
      int i1;
      try
      {
        Object localObject1 = Arrays.asList(al.getPackageInfo(g.getPackageName(), 1).activities);
        if (localObject1 == null) {
          break;
        }
        localObject1 = ((List)localObject1).iterator();
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        localObject3 = (ActivityInfo)((Iterator)localObject1).next();
        if (!m.contains(((ActivityInfo)localObject3).name)) {
          continue;
        }
        i1 = m.indexOf(((ActivityInfo)localObject3).name);
        Vector localVector;
        try
        {
          Class.forName((String)m.get(i1));
          localVector = new Vector();
          if ((((ActivityInfo)localObject3).configChanges & 0x80) != 128) {
            localVector.add("orientation");
          }
          if ((((ActivityInfo)localObject3).configChanges & 0x20) != 32) {
            localVector.add("keyboardHidden");
          }
          if (localVector.size() == 0) {
            break label295;
          }
          if (localVector.size() == 1) {
            throw new TapjoyIntegrationException(localVector.toString() + " property is not specified in manifest configChanges for " + (String)m.get(i1));
          }
        }
        catch (ClassNotFoundException localClassNotFoundException1)
        {
          throw new TapjoyIntegrationException("[ClassNotFoundException] Could not find dependency class " + (String)m.get(i1));
        }
        throw new TapjoyIntegrationException(localVector.toString() + " properties are not specified in manifest configChanges for " + (String)m.get(i1));
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        throw new TapjoyIntegrationException("NameNotFoundException: Could not find package.");
      }
      label295:
      if ((Build.VERSION.SDK_INT >= 13) && ((((ActivityInfo)localObject3).configChanges & 0x400) != 1024)) {
        TapjoyLog.w("TapjoyConnect", "WARNING -- screenSize property is not specified in manifest configChanges for " + (String)m.get(i1));
      }
      if ((Build.VERSION.SDK_INT >= 11) && (((ActivityInfo)localObject3).name.equals("com.tapjoy.TJAdUnitView")) && ((((ActivityInfo)localObject3).flags & 0x200) != 512)) {
        throw new TapjoyIntegrationException("'hardwareAccelerated' property not specified in manifest for " + (String)m.get(i1));
      }
      m.remove(i1);
    }
    if (m.size() != 0)
    {
      if (m.size() == 1) {
        throw new TapjoyIntegrationException("Missing " + m.size() + " dependency class in manifest: " + m.toString());
      }
      throw new TapjoyIntegrationException("Missing " + m.size() + " dependency classes in manifest: " + m.toString());
    }
    j();
    try
    {
      Object localObject2 = Class.forName("com.tapjoy.TJAdUnitJSBridge");
      if (getConnectFlagValue("TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK") == null) {
        break label659;
      }
    }
    catch (ClassNotFoundException localClassNotFoundException2)
    {
      try
      {
        ((Class)localObject2).getMethod("closeRequested", new Class[0]);
        localObject3 = (String)TapjoyUtil.getResource("mraid.js");
        localObject2 = localObject3;
        if (localObject3 == null) {
          localObject2 = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", g);
        }
        if (localObject2 != null) {
          break label625;
        }
        throw new TapjoyIntegrationException("ClassNotFoundException: mraid.js was not found.");
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        throw new TapjoyIntegrationException("Try configuring Proguard or other code obfuscators to ignore com.tapjoy classes. Visit http://dev.tapjoy.comfor more information.");
      }
      localClassNotFoundException2 = localClassNotFoundException2;
      throw new TapjoyIntegrationException("ClassNotFoundException: com.tapjoy.TJAdUnitJSBridge was not found.");
    }
    label625:
    if (getConnectFlagValue("TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK").equals("true"))
    {
      TapjoyLog.i("TapjoyConnect", "Skipping integration check for Google Play Services and Advertising ID. Do this only if you do not have access to Google Play Services.");
      return;
    }
    label659:
    this.an.checkGooglePlayIntegration();
  }
  
  public static boolean isAutoSessionTrackingStarted()
  {
    return aX;
  }
  
  public static boolean isConnected()
  {
    return ak;
  }
  
  public static boolean isViewOpen()
  {
    return aq;
  }
  
  private static void j()
  {
    int i2 = 0;
    Vector localVector = new Vector();
    String[] arrayOfString = TapjoyConstants.dependencyPermissions;
    int i3 = arrayOfString.length;
    int i1 = 0;
    String str;
    while (i1 < i3)
    {
      str = arrayOfString[i1];
      if (!h(str)) {
        localVector.add(str);
      }
      i1 += 1;
    }
    if (localVector.size() != 0)
    {
      if (localVector.size() == 1) {
        throw new TapjoyIntegrationException("Missing 1 permission in manifest: " + localVector.toString());
      }
      throw new TapjoyIntegrationException("Missing " + localVector.size() + " permissions in manifest: " + localVector.toString());
    }
    localVector = new Vector();
    arrayOfString = TapjoyConstants.optionalPermissions;
    i3 = arrayOfString.length;
    i1 = i2;
    while (i1 < i3)
    {
      str = arrayOfString[i1];
      if (!h(str)) {
        localVector.add(str);
      }
      i1 += 1;
    }
    if (localVector.size() != 0)
    {
      if (localVector.size() == 1) {
        TapjoyLog.w("TapjoyConnect", "WARNING -- " + localVector.toString() + " permission was not found in manifest. The exclusion of this permission could cause problems.");
      }
    }
    else {
      return;
    }
    TapjoyLog.w("TapjoyConnect", "WARNING -- " + localVector.toString() + " permissions were not found in manifest. The exclusion of these permissions could cause problems.");
  }
  
  private static boolean k()
  {
    return (c != null) && (c.length() > 0);
  }
  
  private static boolean l()
  {
    return (k()) && (getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS") != null) && (getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS").equals("true"));
  }
  
  private static String m()
  {
    TapjoyLog.i("TapjoyConnect", "generating sessionID...");
    try
    {
      str = TapjoyUtil.SHA256(System.currentTimeMillis() / 1000L + z + q);
      TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + localException1.toString());
    }
    catch (Exception localException1)
    {
      try
      {
        ai = System.currentTimeMillis();
        return str;
      }
      catch (Exception localException2)
      {
        String str;
        for (;;) {}
      }
      localException1 = localException1;
      str = null;
    }
    return str;
  }
  
  private static String n()
  {
    int i2 = 1;
    if (l()) {
      return c;
    }
    if ((q != null) && (q.length() > 0)) {}
    for (int i1 = 1; i1 != 0; i1 = 0) {
      return q;
    }
    if ((r != null) && (r.length() > 0)) {}
    for (i1 = 1; i1 != 0; i1 = 0) {
      return r;
    }
    if (k()) {
      return c;
    }
    if ((n != null) && (n.length() > 0)) {}
    for (i1 = i2; i1 != 0; i1 = 0) {
      return n;
    }
    Log.e("TapjoyConnect", "Error -- no valid device identifier");
    return null;
  }
  
  public static void removeOfflineLog(String paramString)
  {
    SharedPreferences.Editor localEditor = g.getSharedPreferences("tapjoyOfflineLog", 0).edit();
    localEditor.remove(paramString);
    localEditor.commit();
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString)
  {
    requestTapjoyConnect(paramContext, paramString, null, null);
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString, Hashtable paramHashtable)
  {
    requestTapjoyConnect(paramContext, paramString, paramHashtable, null);
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString, Hashtable paramHashtable, TJConnectListener paramTJConnectListener)
  {
    ApiKeyDecoded localApiKeyDecoded;
    try
    {
      localApiKeyDecoded = new ApiKeyDecoded(paramString);
      if (localApiKeyDecoded.getKeyUsage() != ApiKeyDecoded.KeyUsage.SDK_ANDROID) {
        throw new IllegalArgumentException("The given API key was not for Android.");
      }
    }
    catch (IllegalArgumentException paramContext)
    {
      throw new TapjoyIntegrationException(paramContext.getMessage());
    }
    h = paramString;
    z = localApiKeyDecoded.getAppId();
    Q = localApiKeyDecoded.getSecretKey();
    fv.a().a(paramString);
    if (paramHashtable != null) {
      ao.putAll(paramHashtable);
    }
    k = paramTJConnectListener;
    i = new TapjoyConnectCore(paramContext);
  }
  
  public static void saveOfflineLog(String paramString)
  {
    SharedPreferences localSharedPreferences = g.getSharedPreferences("tapjoyOfflineLog", 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    if (getOfflineLogs().size() >= 50)
    {
      localEditor.remove((String)new TreeMap(localSharedPreferences.getAll()).firstKey());
      localEditor.commit();
    }
    paramString = paramString + "&original_timestamp=" + System.currentTimeMillis() / 1000L;
    paramString = paramString + "&offline=true";
    localEditor.putString(Long.toString(System.currentTimeMillis()), paramString);
    localEditor.commit();
  }
  
  public static void sendOfflineLogs()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        TapjoyURLConnection localTapjoyURLConnection = new TapjoyURLConnection();
        Iterator localIterator = TapjoyConnectCore.getOfflineLogs().entrySet().iterator();
        for (;;)
        {
          if (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            try
            {
              TapjoyLog.i("TapjoyConnect", "sending offline log: " + localEntry.getValue());
              localTapjoyURLConnection.getResponseFromURL((String)localEntry.getValue() + "&" + TapjoyUtil.convertURLParams(TapjoyConnectCore.getTimeStampAndVerifierParams(), false), "");
              TapjoyConnectCore.removeOfflineLog((String)localEntry.getKey());
            }
            catch (Exception localException)
            {
              for (;;)
              {
                TapjoyLog.i("TapjoyConnect", "error sending offline log");
              }
            }
          }
        }
      }
    }).start();
  }
  
  public static void setAutoSessionTrackingStarted(boolean paramBoolean)
  {
    aX = paramBoolean;
  }
  
  public static void setPlugin(String paramString)
  {
    R = paramString;
  }
  
  public static void setSDKType(String paramString)
  {
    S = paramString;
  }
  
  public static void setUserID(String paramString)
  {
    G = paramString;
    TapjoyLog.i("TapjoyConnect", "URL parameters: " + getURLParams());
    new Thread(new Runnable()
    {
      public final void run()
      {
        TapjoyLog.i("TapjoyConnect", "setUserID...");
        TapjoyHttpURLResponse localTapjoyHttpURLResponse = TapjoyConnectCore.d().getResponseFromURL(TapjoyConnectCore.getHostURL() + "set_publisher_user_id?", TapjoyConnectCore.getURLParams());
        if (localTapjoyHttpURLResponse.response != null)
        {
          TapjoyConnectCore.b(localTapjoyHttpURLResponse.response);
          TapjoyLog.i("TapjoyConnect", "setUserID successful...");
        }
      }
    }).start();
  }
  
  public static void setViewShowing(boolean paramBoolean)
  {
    aq = paramBoolean;
  }
  
  public static void viewDidClose(int paramInt)
  {
    aq = false;
    if (l != null) {
      l.onViewDidClose(paramInt);
    }
  }
  
  public static void viewDidOpen(int paramInt)
  {
    if (l != null) {
      l.onViewDidOpen(paramInt);
    }
  }
  
  public static void viewWillClose(int paramInt)
  {
    if (l != null) {
      l.onViewWillClose(paramInt);
    }
  }
  
  public static void viewWillOpen(int paramInt)
  {
    aq = true;
    if (l != null) {
      l.onViewWillOpen(paramInt);
    }
  }
  
  public void actionComplete(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "actionComplete: " + paramString);
    Map localMap = e();
    TapjoyUtil.safePut(localMap, "app_id", paramString, true);
    localMap.putAll(getTimeStampAndVerifierParams());
    TapjoyLog.i("TapjoyConnect", "PPA URL parameters: " + localMap);
    new Thread(new TapjoyConnectCore.PPAThread(this, localMap)).start();
  }
  
  public void appPause()
  {
    this.ah = true;
  }
  
  public void appResume()
  {
    if (this.ah)
    {
      m();
      this.ah = false;
    }
  }
  
  public void callConnect()
  {
    fetchAdvertisingID();
  }
  
  public void completeConnectCall()
  {
    TapjoyLog.i("TapjoyConnect", "starting connect call...");
    Object localObject1 = "https://connect.tapjoy.com/";
    if (getHostURL() != "https://ws.tapjoyads.com/") {
      localObject1 = getHostURL();
    }
    localObject1 = j.getResponseFromURL((String)localObject1 + "api/connect/v3.json?", null, null, getURLParams());
    if ((localObject1 != null) && (((TapjoyHttpURLResponse)localObject1).statusCode == 200)) {
      if (d(((TapjoyHttpURLResponse)localObject1).response))
      {
        TapjoyLog.i("TapjoyConnect", "Successfully connected to Tapjoy");
        if (aY) {
          if (Build.VERSION.SDK_INT <= 8) {}
        }
        for (;;)
        {
          try
          {
            doProfileAsync();
            localObject1 = getGenericURLParams().entrySet().iterator();
            if (!((Iterator)localObject1).hasNext()) {
              break;
            }
            localObject3 = (Map.Entry)((Iterator)localObject1).next();
            TapjoyLog.i("TapjoyConnect", (String)((Map.Entry)localObject3).getKey() + ": " + (String)((Map.Entry)localObject3).getValue());
            continue;
          }
          catch (Exception localException)
          {
            TapjoyLog.w("TapjoyConnect", "Error building Threatmetrix profile: " + localException.toString());
            continue;
          }
          TapjoyLog.i("TapjoyConnect", "TM disabled");
        }
        sendOfflineLogs();
        ak = true;
        if (k != null) {
          k.onConnectSuccess();
        }
      }
    }
    while (k == null)
    {
      Object localObject3;
      for (;;)
      {
        if (ap.length() > 0)
        {
          Object localObject2 = getGenericURLParams();
          TapjoyUtil.safePut((Map)localObject2, "package_names", ap, true);
          long l1 = System.currentTimeMillis() / 1000L;
          localObject3 = a(l1, ap);
          TapjoyUtil.safePut((Map)localObject2, "timestamp", String.valueOf(l1), true);
          TapjoyUtil.safePut((Map)localObject2, "verifier", (String)localObject3, true);
          localObject2 = new TapjoyURLConnection().getResponseFromURL(getHostURL() + "apps_installed?", (Map)localObject2);
          if ((localObject2 != null) && (((TapjoyHttpURLResponse)localObject2).statusCode == 200)) {
            TapjoyLog.i("TapjoyConnect", "Successfully pinged sdkless api.");
          }
        }
        return;
        if (k != null) {
          k.onConnectFailure();
        }
      }
    }
    k.onConnectFailure();
  }
  
  public void doProfileAsync()
  {
    TapjoyLog.i("TapjoyConnect", "Initializing Threatmetrix: 2.5-16");
    this.am = new fc();
    try
    {
      this.am.a(new eq()
      {
        /* Error */
        public final void a()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
          //   4: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/fc;
          //   7: invokevirtual 31	com/tapjoy/internal/fc:c	()Lcom/tapjoy/internal/fc$c;
          //   10: getstatic 37	com/tapjoy/internal/fc$c:b	Lcom/tapjoy/internal/fc$c;
          //   13: if_acmpne +28 -> 41
          //   16: aload_0
          //   17: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
          //   20: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/fc;
          //   23: invokevirtual 40	com/tapjoy/internal/fc:b	()Ljava/lang/String;
          //   26: invokestatic 43	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;)Ljava/lang/String;
          //   29: pop
          //   30: aload_0
          //   31: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
          //   34: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/fc;
          //   37: invokevirtual 46	com/tapjoy/internal/fc:d	()V
          //   40: return
          //   41: ldc 48
          //   43: ldc 50
          //   45: invokestatic 56	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
          //   48: goto -18 -> 30
          //   51: astore_1
          //   52: ldc 48
          //   54: new 58	java/lang/StringBuilder
          //   57: dup
          //   58: ldc 60
          //   60: invokespecial 63	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
          //   63: aload_1
          //   64: invokevirtual 66	java/lang/Exception:toString	()Ljava/lang/String;
          //   67: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   70: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   73: invokestatic 56	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
          //   76: aload_0
          //   77: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
          //   80: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/fc;
          //   83: invokevirtual 46	com/tapjoy/internal/fc:d	()V
          //   86: return
          //   87: astore_1
          //   88: aload_0
          //   89: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
          //   92: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/fc;
          //   95: invokevirtual 46	com/tapjoy/internal/fc:d	()V
          //   98: aload_1
          //   99: athrow
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	100	0	this	1
          //   51	13	1	localException	Exception
          //   87	12	1	localObject	Object
          // Exception table:
          //   from	to	target	type
          //   0	30	51	java/lang/Exception
          //   41	48	51	java/lang/Exception
          //   0	30	87	finally
          //   41	48	87	finally
          //   52	76	87	finally
        }
      });
      this.am.a();
      this.am.a(g, "rrx68giz", "h.online-metrix.net", "http://content-js.tapjoy.com");
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }
  
  public void enablePaidAppWithActionID(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "enablePaidAppWithActionID: " + paramString);
    ae = paramString;
    this.af = g.getSharedPreferences("tjcPrefrences", 0).getLong("tapjoy_elapsed_time", 0L);
    TapjoyLog.i("TapjoyConnect", "paidApp elapsed: " + this.af);
    if (this.af >= 900000L) {
      if ((ae != null) && (ae.length() > 0))
      {
        TapjoyLog.i("TapjoyConnect", "Calling PPA actionComplete...");
        actionComplete(ae);
      }
    }
    while (this.ag != null) {
      return;
    }
    this.ag = new Timer();
    this.ag.schedule(new TapjoyConnectCore.a(this, (byte)0), 10000L, 10000L);
  }
  
  public void fetchAdvertisingID()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        TapjoyConnectCore.b(TapjoyConnectCore.this).loadAdvertisingId();
        if ((TapjoyConnectCore.b(TapjoyConnectCore.this).isGooglePlayServicesAvailable()) && (TapjoyConnectCore.b(TapjoyConnectCore.this).isGooglePlayManifestConfigured()))
        {
          TapjoyConnectCore.b = TapjoyConnectCore.b(TapjoyConnectCore.this).getDeviceGooglePlayServicesVersion();
          TapjoyConnectCore.a = TapjoyConnectCore.b(TapjoyConnectCore.this).getPackagedGooglePlayServicesVersion();
        }
        if (TapjoyConnectCore.b(TapjoyConnectCore.this).isAdIdAvailable())
        {
          TapjoyConnectCore.d = TapjoyConnectCore.b(TapjoyConnectCore.this).isAdTrackingEnabled();
          TapjoyConnectCore.c = TapjoyConnectCore.b(TapjoyConnectCore.this).getAdvertisingId();
        }
        if (TapjoyConnectCore.a()) {
          TapjoyLog.i("TapjoyConnect", "Disabling persistent IDs. Do this only if you are not using Tapjoy to manage currency.");
        }
        TapjoyConnectCore.this.completeConnectCall();
      }
    }).start();
  }
  
  public float getCurrencyMultiplier()
  {
    return U;
  }
  
  /* Error */
  public String getSerial()
  {
    // Byte code:
    //   0: ldc_w 1797
    //   3: invokestatic 1497	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 1799
    //   9: invokevirtual 1803	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 1808	java/lang/reflect/Field:isAccessible	()Z
    //   17: ifne +8 -> 25
    //   20: aload_1
    //   21: iconst_1
    //   22: invokevirtual 1811	java/lang/reflect/Field:setAccessible	(Z)V
    //   25: aload_1
    //   26: ldc_w 410
    //   29: invokevirtual 1812	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: invokevirtual 1346	java/lang/Object:toString	()Ljava/lang/String;
    //   35: astore_1
    //   36: ldc_w 511
    //   39: new 513	java/lang/StringBuilder
    //   42: dup
    //   43: ldc_w 1814
    //   46: invokespecial 518	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   49: aload_1
    //   50: invokevirtual 522	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 525	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 528	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload_1
    //   60: areturn
    //   61: astore_2
    //   62: aconst_null
    //   63: astore_1
    //   64: ldc_w 511
    //   67: aload_2
    //   68: invokevirtual 696	java/lang/Exception:toString	()Ljava/lang/String;
    //   71: invokestatic 532	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   74: aload_1
    //   75: areturn
    //   76: astore_2
    //   77: goto -13 -> 64
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	80	0	this	TapjoyConnectCore
    //   12	63	1	localObject	Object
    //   61	7	2	localException1	Exception
    //   76	1	2	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	25	61	java/lang/Exception
    //   25	36	61	java/lang/Exception
    //   36	59	76	java/lang/Exception
  }
  
  public boolean isInitialized()
  {
    return this.aj;
  }
  
  public void release()
  {
    i = null;
    j = null;
    TapjoyLog.i("TapjoyConnect", "Releasing core static instance.");
  }
  
  public void setCurrencyMultiplier(float paramFloat)
  {
    TapjoyLog.i("TapjoyConnect", "setVirtualCurrencyMultiplier: " + paramFloat);
    U = paramFloat;
  }
  
  public void setTapjoyViewListener(TJViewListener paramTJViewListener)
  {
    l = paramTJViewListener;
  }
}
