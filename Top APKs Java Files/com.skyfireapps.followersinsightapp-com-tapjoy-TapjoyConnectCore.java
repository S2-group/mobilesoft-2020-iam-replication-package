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
import com.tapjoy.internal.fc;
import com.tapjoy.internal.fv;
import com.tapjoy.internal.gl.a;
import com.tapjoy.internal.gl.l;
import com.tapjoy.internal.gl.n;
import com.tapjoy.internal.gl.x;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
  private static int B = 0;
  private static float C = 0.0F;
  private static int D = 0;
  public static final float DEFAULT_CURRENCY_MULTIPLIER = 1.0F;
  private static String E;
  private static boolean F = false;
  private static String G;
  private static String H;
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
  private static float S;
  private static boolean T;
  private static boolean U;
  private static boolean V;
  private static boolean W;
  private static boolean X;
  private static String Y;
  private static String Z;
  protected static int a;
  private static String aA;
  private static String aB;
  private static String aC;
  private static String aD;
  private static String aE;
  private static int aF;
  private static int aG;
  private static int aH;
  private static long aI;
  private static long aJ;
  private static long aK;
  private static String aL;
  private static int aM;
  private static double aN;
  private static double aO;
  private static long aP;
  private static int aQ;
  private static int aR;
  private static int aS;
  private static String aT;
  private static String aU;
  private static boolean aV = false;
  private static boolean aW = true;
  private static String aa;
  private static String ab;
  private static String ac;
  private static long ag;
  private static boolean ai;
  private static PackageManager aj;
  private static Hashtable am;
  private static String an;
  private static boolean ao;
  private static String ap;
  private static String aq;
  private static String ar;
  private static String as;
  private static int at;
  private static String au;
  private static String av;
  private static long aw;
  private static String ax;
  private static int ay;
  private static int az;
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
  private long ad;
  private Timer ae;
  private boolean af;
  private boolean ah;
  private fc ak;
  private TapjoyGpsHelper al;
  
  static
  {
    A = "";
    B = 1;
    C = 1.0F;
    D = 1;
    E = "";
    F = false;
    G = "";
    H = "";
    I = "";
    J = "";
    K = "";
    L = "";
    M = "";
    N = "";
    O = "";
    P = "native";
    Q = "";
    R = "";
    S = 1.0F;
    T = false;
    U = false;
    V = false;
    W = false;
    X = false;
    Y = "";
    Z = "";
    aa = "";
    ab = "";
    ac = null;
    ag = 0L;
    a = 0;
    b = 0;
    c = "";
    e = "";
    f = "";
    am = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
    an = "";
    ao = false;
  }
  
  /* Error */
  public TapjoyConnectCore(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 282	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 284	com/tapjoy/TapjoyConnectCore:ad	J
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 286	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Timer;
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 288	com/tapjoy/TapjoyConnectCore:af	Z
    //   19: aload_0
    //   20: iconst_0
    //   21: putfield 290	com/tapjoy/TapjoyConnectCore:ah	Z
    //   24: aload_1
    //   25: putstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   28: new 292	com/tapjoy/TapjoyURLConnection
    //   31: dup
    //   32: invokespecial 293	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   35: putstatic 133	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   38: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   41: invokevirtual 299	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   44: putstatic 301	com/tapjoy/TapjoyConnectCore:aj	Landroid/content/pm/PackageManager;
    //   47: aload_0
    //   48: new 303	com/tapjoy/TapjoyGpsHelper
    //   51: dup
    //   52: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   55: invokespecial 305	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   58: putfield 307	com/tapjoy/TapjoyConnectCore:al	Lcom/tapjoy/TapjoyGpsHelper;
    //   61: getstatic 262	com/tapjoy/TapjoyConnectCore:am	Ljava/util/Hashtable;
    //   64: ifnonnull +13 -> 77
    //   67: new 309	java/util/Hashtable
    //   70: dup
    //   71: invokespecial 310	java/util/Hashtable:<init>	()V
    //   74: putstatic 262	com/tapjoy/TapjoyConnectCore:am	Ljava/util/Hashtable;
    //   77: ldc_w 312
    //   80: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   83: ifnull +22 -> 105
    //   86: ldc_w 312
    //   89: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   92: ldc_w 318
    //   95: invokevirtual 324	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   98: ifeq +7 -> 105
    //   101: iconst_1
    //   102: invokestatic 330	com/tapjoy/TapjoyLog:setDebugEnabled	(Z)V
    //   105: invokestatic 332	com/tapjoy/TapjoyConnectCore:h	()V
    //   108: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   111: invokevirtual 336	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   114: ldc_w 338
    //   117: aconst_null
    //   118: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   121: invokevirtual 342	android/content/Context:getPackageName	()Ljava/lang/String;
    //   124: invokevirtual 348	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   127: istore_2
    //   128: new 350	java/util/Properties
    //   131: dup
    //   132: invokespecial 351	java/util/Properties:<init>	()V
    //   135: astore_1
    //   136: aload_1
    //   137: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   140: invokevirtual 336	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   143: iload_2
    //   144: invokevirtual 355	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   147: invokevirtual 359	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   150: aload_1
    //   151: invokestatic 362	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   154: ldc_w 364
    //   157: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   160: ldc -97
    //   162: if_acmpne +7 -> 169
    //   165: aload_0
    //   166: invokespecial 366	com/tapjoy/TapjoyConnectCore:i	()V
    //   169: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   172: invokevirtual 370	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   175: ldc_w 372
    //   178: invokestatic 378	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   181: astore_1
    //   182: aload_1
    //   183: putstatic 161	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   186: aload_1
    //   187: ifnull +12 -> 199
    //   190: getstatic 161	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   193: invokevirtual 381	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   196: putstatic 161	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   199: getstatic 301	com/tapjoy/TapjoyConnectCore:aj	Landroid/content/pm/PackageManager;
    //   202: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   205: invokevirtual 342	android/content/Context:getPackageName	()Ljava/lang/String;
    //   208: iconst_0
    //   209: invokevirtual 387	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   212: getfield 392	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   215: putstatic 181	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   218: ldc_w 394
    //   221: putstatic 175	com/tapjoy/TapjoyConnectCore:v	Ljava/lang/String;
    //   224: ldc_w 394
    //   227: putstatic 197	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   230: getstatic 399	android/os/Build:MODEL	Ljava/lang/String;
    //   233: putstatic 171	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   236: getstatic 402	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   239: putstatic 173	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   242: getstatic 407	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   245: putstatic 177	com/tapjoy/TapjoyConnectCore:w	Ljava/lang/String;
    //   248: ldc_w 409
    //   251: putstatic 183	com/tapjoy/TapjoyConnectCore:z	Ljava/lang/String;
    //   254: ldc_w 411
    //   257: putstatic 185	com/tapjoy/TapjoyConnectCore:A	Ljava/lang/String;
    //   260: getstatic 414	android/os/Build$VERSION:SDK_INT	I
    //   263: iconst_3
    //   264: if_icmple +35 -> 299
    //   267: new 416	com/tapjoy/TapjoyDisplayMetricsUtil
    //   270: dup
    //   271: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   274: invokespecial 417	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   277: astore_1
    //   278: aload_1
    //   279: invokevirtual 421	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   282: putstatic 187	com/tapjoy/TapjoyConnectCore:B	I
    //   285: aload_1
    //   286: invokevirtual 425	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   289: putstatic 189	com/tapjoy/TapjoyConnectCore:C	F
    //   292: aload_1
    //   293: invokevirtual 428	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   296: putstatic 191	com/tapjoy/TapjoyConnectCore:D	I
    //   299: ldc_w 430
    //   302: invokestatic 433	com/tapjoy/TapjoyConnectCore:h	(Ljava/lang/String;)Z
    //   305: istore_3
    //   306: iload_3
    //   307: ifeq +951 -> 1258
    //   310: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   313: ldc_w 435
    //   316: invokevirtual 439	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   319: checkcast 441	android/net/wifi/WifiManager
    //   322: astore_1
    //   323: aload_1
    //   324: ifnull +42 -> 366
    //   327: aload_1
    //   328: invokevirtual 445	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   331: astore_1
    //   332: aload_1
    //   333: ifnull +33 -> 366
    //   336: aload_1
    //   337: invokevirtual 450	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   340: astore_1
    //   341: aload_1
    //   342: putstatic 167	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   345: aload_1
    //   346: ifnull +20 -> 366
    //   349: getstatic 167	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   352: ldc_w 452
    //   355: ldc -97
    //   357: invokevirtual 456	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   360: invokevirtual 381	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   363: putstatic 167	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   366: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   369: ldc_w 458
    //   372: invokevirtual 439	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   375: checkcast 460	android/telephony/TelephonyManager
    //   378: astore_1
    //   379: aload_1
    //   380: ifnull +223 -> 603
    //   383: aload_1
    //   384: invokevirtual 463	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   387: putstatic 199	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   390: aload_1
    //   391: invokevirtual 466	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   394: putstatic 201	com/tapjoy/TapjoyConnectCore:I	Ljava/lang/String;
    //   397: aload_1
    //   398: invokevirtual 469	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   401: ifnull +49 -> 450
    //   404: aload_1
    //   405: invokevirtual 469	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   408: invokevirtual 472	java/lang/String:length	()I
    //   411: iconst_5
    //   412: if_icmpeq +15 -> 427
    //   415: aload_1
    //   416: invokevirtual 469	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   419: invokevirtual 472	java/lang/String:length	()I
    //   422: bipush 6
    //   424: if_icmpne +26 -> 450
    //   427: aload_1
    //   428: invokevirtual 469	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   431: iconst_0
    //   432: iconst_3
    //   433: invokevirtual 476	java/lang/String:substring	(II)Ljava/lang/String;
    //   436: putstatic 203	com/tapjoy/TapjoyConnectCore:J	Ljava/lang/String;
    //   439: aload_1
    //   440: invokevirtual 469	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   443: iconst_3
    //   444: invokevirtual 479	java/lang/String:substring	(I)Ljava/lang/String;
    //   447: putstatic 205	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   450: ldc_w 481
    //   453: invokestatic 433	com/tapjoy/TapjoyConnectCore:h	(Ljava/lang/String;)Z
    //   456: istore_3
    //   457: iload_3
    //   458: ifeq +992 -> 1450
    //   461: ldc_w 483
    //   464: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   467: ifnull +803 -> 1270
    //   470: ldc_w 483
    //   473: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   476: invokevirtual 472	java/lang/String:length	()I
    //   479: ifle +791 -> 1270
    //   482: ldc_w 483
    //   485: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   488: putstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   491: ldc_w 485
    //   494: new 487	java/lang/StringBuilder
    //   497: dup
    //   498: ldc_w 489
    //   501: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   504: getstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   507: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   510: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   513: invokestatic 502	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   516: getstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   519: ifnonnull +795 -> 1314
    //   522: ldc_w 485
    //   525: ldc_w 504
    //   528: invokestatic 506	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   531: iconst_0
    //   532: istore_2
    //   533: ldc_w 485
    //   536: new 487	java/lang/StringBuilder
    //   539: dup
    //   540: ldc_w 508
    //   543: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   546: getstatic 414	android/os/Build$VERSION:SDK_INT	I
    //   549: invokevirtual 511	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   552: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   555: invokestatic 502	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   558: getstatic 414	android/os/Build$VERSION:SDK_INT	I
    //   561: bipush 9
    //   563: if_icmplt +40 -> 603
    //   566: ldc_w 485
    //   569: ldc_w 513
    //   572: invokestatic 502	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   575: aload_0
    //   576: invokevirtual 516	com/tapjoy/TapjoyConnectCore:getSerial	()Ljava/lang/String;
    //   579: astore_1
    //   580: iload_2
    //   581: ifne +7 -> 588
    //   584: aload_1
    //   585: putstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   588: getstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   591: ifnonnull +787 -> 1378
    //   594: ldc_w 485
    //   597: ldc_w 518
    //   600: invokestatic 506	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   603: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   606: ldc_w 520
    //   609: iconst_0
    //   610: invokevirtual 524	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   613: astore_1
    //   614: aload_1
    //   615: ldc_w 526
    //   618: ldc -97
    //   620: invokeinterface 531 3 0
    //   625: astore 4
    //   627: aload 4
    //   629: putstatic 169	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   632: aload 4
    //   634: ifnull +14 -> 648
    //   637: getstatic 169	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   640: invokevirtual 472	java/lang/String:length	()I
    //   643: istore_2
    //   644: iload_2
    //   645: ifne +61 -> 706
    //   648: new 487	java/lang/StringBuilder
    //   651: dup
    //   652: invokespecial 532	java/lang/StringBuilder:<init>	()V
    //   655: invokestatic 538	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   658: invokevirtual 539	java/util/UUID:toString	()Ljava/lang/String;
    //   661: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   664: invokestatic 545	java/lang/System:currentTimeMillis	()J
    //   667: invokevirtual 548	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   670: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   673: invokestatic 553	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   676: putstatic 169	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   679: aload_1
    //   680: invokeinterface 557 1 0
    //   685: astore_1
    //   686: aload_1
    //   687: ldc_w 526
    //   690: getstatic 169	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   693: invokeinterface 563 3 0
    //   698: pop
    //   699: aload_1
    //   700: invokeinterface 567 1 0
    //   705: pop
    //   706: ldc_w 569
    //   709: ldc_w 571
    //   712: invokestatic 574	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   715: putstatic 195	com/tapjoy/TapjoyConnectCore:F	Z
    //   718: ldc_w 576
    //   721: invokestatic 578	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   724: putstatic 225	com/tapjoy/TapjoyConnectCore:T	Z
    //   727: ldc_w 580
    //   730: invokestatic 578	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   733: putstatic 227	com/tapjoy/TapjoyConnectCore:U	Z
    //   736: ldc_w 582
    //   739: invokestatic 578	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   742: putstatic 229	com/tapjoy/TapjoyConnectCore:V	Z
    //   745: ldc_w 584
    //   748: invokestatic 578	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   751: putstatic 231	com/tapjoy/TapjoyConnectCore:W	Z
    //   754: ldc_w 586
    //   757: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   760: ifnull +71 -> 831
    //   763: ldc_w 586
    //   766: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   769: invokevirtual 472	java/lang/String:length	()I
    //   772: ifle +59 -> 831
    //   775: ldc_w 586
    //   778: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   781: putstatic 211	com/tapjoy/TapjoyConnectCore:N	Ljava/lang/String;
    //   784: new 588	java/util/ArrayList
    //   787: dup
    //   788: getstatic 591	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   791: invokestatic 151	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   794: invokespecial 592	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   797: getstatic 211	com/tapjoy/TapjoyConnectCore:N	Ljava/lang/String;
    //   800: invokevirtual 595	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   803: ifne +28 -> 831
    //   806: ldc_w 485
    //   809: new 487	java/lang/StringBuilder
    //   812: dup
    //   813: ldc_w 597
    //   816: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   819: getstatic 211	com/tapjoy/TapjoyConnectCore:N	Ljava/lang/String;
    //   822: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   825: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   828: invokestatic 599	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   831: getstatic 211	com/tapjoy/TapjoyConnectCore:N	Ljava/lang/String;
    //   834: astore_1
    //   835: new 601	android/content/Intent
    //   838: dup
    //   839: ldc_w 603
    //   842: invokespecial 604	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   845: astore 4
    //   847: aload_1
    //   848: invokevirtual 472	java/lang/String:length	()I
    //   851: ifgt +701 -> 1552
    //   854: aload 4
    //   856: ldc_w 606
    //   859: invokestatic 612	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   862: invokevirtual 616	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   865: pop
    //   866: getstatic 301	com/tapjoy/TapjoyConnectCore:aj	Landroid/content/pm/PackageManager;
    //   869: aload 4
    //   871: iconst_0
    //   872: invokevirtual 620	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   875: invokeinterface 625 1 0
    //   880: ifle +752 -> 1632
    //   883: iconst_1
    //   884: istore_3
    //   885: iload_3
    //   886: putstatic 233	com/tapjoy/TapjoyConnectCore:X	Z
    //   889: invokestatic 627	com/tapjoy/TapjoyConnectCore:f	()V
    //   892: ldc_w 629
    //   895: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   898: ifnull +24 -> 922
    //   901: ldc_w 629
    //   904: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   907: invokevirtual 472	java/lang/String:length	()I
    //   910: ifle +12 -> 922
    //   913: ldc_w 629
    //   916: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   919: putstatic 255	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   922: ldc_w 631
    //   925: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   928: ifnull +24 -> 952
    //   931: ldc_w 631
    //   934: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   937: invokevirtual 472	java/lang/String:length	()I
    //   940: ifle +12 -> 952
    //   943: ldc_w 631
    //   946: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   949: putstatic 253	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   952: ldc_w 633
    //   955: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   958: ifnull +52 -> 1010
    //   961: ldc_w 633
    //   964: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   967: invokevirtual 472	java/lang/String:length	()I
    //   970: ifle +40 -> 1010
    //   973: ldc_w 485
    //   976: new 487	java/lang/StringBuilder
    //   979: dup
    //   980: ldc_w 635
    //   983: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   986: ldc_w 633
    //   989: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   992: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   995: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   998: invokestatic 502	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1001: ldc_w 633
    //   1004: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1007: invokestatic 638	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;)V
    //   1010: ldc_w 640
    //   1013: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1016: invokestatic 643	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   1019: putstatic 221	com/tapjoy/TapjoyConnectCore:R	Ljava/lang/String;
    //   1022: new 487	java/lang/StringBuilder
    //   1025: dup
    //   1026: ldc_w 489
    //   1029: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1032: getstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1035: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1038: astore 4
    //   1040: ldc_w 483
    //   1043: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1046: ifnull +576 -> 1622
    //   1049: ldc_w 483
    //   1052: invokestatic 316	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1055: invokevirtual 472	java/lang/String:length	()I
    //   1058: ifle +564 -> 1622
    //   1061: ldc_w 645
    //   1064: astore_1
    //   1065: ldc_w 485
    //   1068: aload 4
    //   1070: aload_1
    //   1071: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1074: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1077: invokestatic 502	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1080: getstatic 262	com/tapjoy/TapjoyConnectCore:am	Ljava/util/Hashtable;
    //   1083: ifnull +6 -> 1089
    //   1086: invokestatic 647	com/tapjoy/TapjoyConnectCore:g	()V
    //   1089: aload_0
    //   1090: invokevirtual 650	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   1093: aload_0
    //   1094: iconst_1
    //   1095: putfield 290	com/tapjoy/TapjoyConnectCore:ah	Z
    //   1098: return
    //   1099: astore_1
    //   1100: new 276	com/tapjoy/TapjoyException
    //   1103: dup
    //   1104: aload_1
    //   1105: invokevirtual 653	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   1108: invokespecial 654	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   1111: athrow
    //   1112: astore_1
    //   1113: ldc_w 485
    //   1116: new 487	java/lang/StringBuilder
    //   1119: dup
    //   1120: ldc_w 656
    //   1123: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1126: aload_1
    //   1127: invokevirtual 657	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   1130: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1133: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1136: invokestatic 662	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1139: pop
    //   1140: getstatic 135	com/tapjoy/TapjoyConnectCore:k	Lcom/tapjoy/TJConnectListener;
    //   1143: ifnull +494 -> 1637
    //   1146: getstatic 135	com/tapjoy/TapjoyConnectCore:k	Lcom/tapjoy/TJConnectListener;
    //   1149: invokeinterface 667 1 0
    //   1154: return
    //   1155: astore_1
    //   1156: ldc_w 485
    //   1159: new 487	java/lang/StringBuilder
    //   1162: dup
    //   1163: ldc_w 669
    //   1166: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1169: aload_1
    //   1170: invokevirtual 670	java/lang/Exception:toString	()Ljava/lang/String;
    //   1173: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1176: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1179: invokestatic 506	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1182: goto -883 -> 299
    //   1185: astore_1
    //   1186: ldc_w 485
    //   1189: new 487	java/lang/StringBuilder
    //   1192: dup
    //   1193: ldc_w 672
    //   1196: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1199: aload_1
    //   1200: invokevirtual 673	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   1203: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1206: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1209: invokestatic 662	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1212: pop
    //   1213: getstatic 135	com/tapjoy/TapjoyConnectCore:k	Lcom/tapjoy/TJConnectListener;
    //   1216: ifnull +421 -> 1637
    //   1219: getstatic 135	com/tapjoy/TapjoyConnectCore:k	Lcom/tapjoy/TJConnectListener;
    //   1222: invokeinterface 667 1 0
    //   1227: return
    //   1228: astore_1
    //   1229: ldc_w 485
    //   1232: new 487	java/lang/StringBuilder
    //   1235: dup
    //   1236: ldc_w 675
    //   1239: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1242: aload_1
    //   1243: invokevirtual 670	java/lang/Exception:toString	()Ljava/lang/String;
    //   1246: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1249: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1252: invokestatic 506	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1255: goto -889 -> 366
    //   1258: ldc_w 485
    //   1261: ldc_w 677
    //   1264: invokestatic 502	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1267: goto -901 -> 366
    //   1270: aload_1
    //   1271: invokevirtual 680	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1274: putstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1277: goto -786 -> 491
    //   1280: astore_1
    //   1281: ldc_w 485
    //   1284: new 487	java/lang/StringBuilder
    //   1287: dup
    //   1288: ldc_w 682
    //   1291: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1294: aload_1
    //   1295: invokevirtual 670	java/lang/Exception:toString	()Ljava/lang/String;
    //   1298: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1301: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1304: invokestatic 506	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1307: aconst_null
    //   1308: putstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1311: goto -708 -> 603
    //   1314: getstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1317: invokevirtual 472	java/lang/String:length	()I
    //   1320: ifeq +27 -> 1347
    //   1323: getstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1326: ldc_w 684
    //   1329: invokevirtual 324	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1332: ifne +15 -> 1347
    //   1335: getstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1338: ldc_w 686
    //   1341: invokevirtual 324	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1344: ifeq +17 -> 1361
    //   1347: ldc_w 485
    //   1350: ldc_w 688
    //   1353: invokestatic 506	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1356: iconst_0
    //   1357: istore_2
    //   1358: goto -825 -> 533
    //   1361: getstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1364: invokestatic 694	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1367: invokevirtual 697	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1370: putstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1373: iconst_1
    //   1374: istore_2
    //   1375: goto -842 -> 533
    //   1378: getstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1381: invokevirtual 472	java/lang/String:length	()I
    //   1384: ifeq +39 -> 1423
    //   1387: getstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1390: ldc_w 684
    //   1393: invokevirtual 324	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1396: ifne +27 -> 1423
    //   1399: getstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1402: ldc_w 686
    //   1405: invokevirtual 324	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1408: ifne +15 -> 1423
    //   1411: getstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1414: ldc_w 699
    //   1417: invokevirtual 324	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1420: ifeq +15 -> 1435
    //   1423: ldc_w 485
    //   1426: ldc_w 701
    //   1429: invokestatic 506	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1432: goto -829 -> 603
    //   1435: getstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1438: invokestatic 694	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1441: invokevirtual 697	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1444: putstatic 165	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1447: goto -844 -> 603
    //   1450: ldc_w 485
    //   1453: ldc_w 703
    //   1456: invokestatic 502	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1459: goto -856 -> 603
    //   1462: astore_1
    //   1463: ldc_w 485
    //   1466: new 487	java/lang/StringBuilder
    //   1469: dup
    //   1470: ldc_w 705
    //   1473: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1476: aload_1
    //   1477: invokevirtual 670	java/lang/Exception:toString	()Ljava/lang/String;
    //   1480: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1483: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1486: invokestatic 506	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1489: goto -783 -> 706
    //   1492: astore_1
    //   1493: ldc_w 485
    //   1496: new 487	java/lang/StringBuilder
    //   1499: dup
    //   1500: ldc_w 707
    //   1503: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1506: aload_1
    //   1507: invokevirtual 670	java/lang/Exception:toString	()Ljava/lang/String;
    //   1510: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1513: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1516: invokestatic 506	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1519: goto -801 -> 718
    //   1522: astore_1
    //   1523: ldc_w 485
    //   1526: new 487	java/lang/StringBuilder
    //   1529: dup
    //   1530: ldc_w 709
    //   1533: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1536: aload_1
    //   1537: invokevirtual 670	java/lang/Exception:toString	()Ljava/lang/String;
    //   1540: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1543: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1546: invokestatic 506	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1549: goto -795 -> 754
    //   1552: aload_1
    //   1553: ldc_w 711
    //   1556: invokevirtual 324	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1559: ifeq +13 -> 1572
    //   1562: ldc_w 713
    //   1565: invokestatic 715	com/tapjoy/TapjoyConnectCore:f	(Ljava/lang/String;)Z
    //   1568: istore_3
    //   1569: goto -684 -> 885
    //   1572: aload_1
    //   1573: ldc_w 717
    //   1576: invokevirtual 324	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1579: ifeq +53 -> 1632
    //   1582: ldc_w 719
    //   1585: invokestatic 715	com/tapjoy/TapjoyConnectCore:f	(Ljava/lang/String;)Z
    //   1588: istore_3
    //   1589: goto -704 -> 885
    //   1592: astore_1
    //   1593: ldc_w 485
    //   1596: new 487	java/lang/StringBuilder
    //   1599: dup
    //   1600: ldc_w 721
    //   1603: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1606: aload_1
    //   1607: invokevirtual 670	java/lang/Exception:toString	()Ljava/lang/String;
    //   1610: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1613: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1616: invokestatic 506	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1619: goto -730 -> 889
    //   1622: ldc -97
    //   1624: astore_1
    //   1625: goto -560 -> 1065
    //   1628: astore_1
    //   1629: goto -1475 -> 154
    //   1632: iconst_0
    //   1633: istore_3
    //   1634: goto -749 -> 885
    //   1637: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1638	0	this	TapjoyConnectCore
    //   0	1638	1	paramContext	Context
    //   127	1248	2	i1	int
    //   305	1329	3	bool	boolean
    //   625	444	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   199	218	1099	android/content/pm/PackageManager$NameNotFoundException
    //   61	77	1112	com/tapjoy/TapjoyIntegrationException
    //   77	105	1112	com/tapjoy/TapjoyIntegrationException
    //   105	136	1112	com/tapjoy/TapjoyIntegrationException
    //   136	154	1112	com/tapjoy/TapjoyIntegrationException
    //   154	169	1112	com/tapjoy/TapjoyIntegrationException
    //   169	186	1112	com/tapjoy/TapjoyIntegrationException
    //   190	199	1112	com/tapjoy/TapjoyIntegrationException
    //   199	218	1112	com/tapjoy/TapjoyIntegrationException
    //   218	260	1112	com/tapjoy/TapjoyIntegrationException
    //   260	299	1112	com/tapjoy/TapjoyIntegrationException
    //   299	306	1112	com/tapjoy/TapjoyIntegrationException
    //   310	323	1112	com/tapjoy/TapjoyIntegrationException
    //   327	332	1112	com/tapjoy/TapjoyIntegrationException
    //   336	345	1112	com/tapjoy/TapjoyIntegrationException
    //   349	366	1112	com/tapjoy/TapjoyIntegrationException
    //   366	379	1112	com/tapjoy/TapjoyIntegrationException
    //   383	427	1112	com/tapjoy/TapjoyIntegrationException
    //   427	450	1112	com/tapjoy/TapjoyIntegrationException
    //   450	457	1112	com/tapjoy/TapjoyIntegrationException
    //   461	491	1112	com/tapjoy/TapjoyIntegrationException
    //   491	531	1112	com/tapjoy/TapjoyIntegrationException
    //   533	580	1112	com/tapjoy/TapjoyIntegrationException
    //   584	588	1112	com/tapjoy/TapjoyIntegrationException
    //   588	603	1112	com/tapjoy/TapjoyIntegrationException
    //   603	632	1112	com/tapjoy/TapjoyIntegrationException
    //   637	644	1112	com/tapjoy/TapjoyIntegrationException
    //   648	706	1112	com/tapjoy/TapjoyIntegrationException
    //   706	718	1112	com/tapjoy/TapjoyIntegrationException
    //   718	754	1112	com/tapjoy/TapjoyIntegrationException
    //   754	831	1112	com/tapjoy/TapjoyIntegrationException
    //   831	883	1112	com/tapjoy/TapjoyIntegrationException
    //   885	889	1112	com/tapjoy/TapjoyIntegrationException
    //   889	922	1112	com/tapjoy/TapjoyIntegrationException
    //   922	952	1112	com/tapjoy/TapjoyIntegrationException
    //   952	1010	1112	com/tapjoy/TapjoyIntegrationException
    //   1010	1061	1112	com/tapjoy/TapjoyIntegrationException
    //   1065	1089	1112	com/tapjoy/TapjoyIntegrationException
    //   1089	1098	1112	com/tapjoy/TapjoyIntegrationException
    //   1100	1112	1112	com/tapjoy/TapjoyIntegrationException
    //   1156	1182	1112	com/tapjoy/TapjoyIntegrationException
    //   1229	1255	1112	com/tapjoy/TapjoyIntegrationException
    //   1258	1267	1112	com/tapjoy/TapjoyIntegrationException
    //   1270	1277	1112	com/tapjoy/TapjoyIntegrationException
    //   1281	1311	1112	com/tapjoy/TapjoyIntegrationException
    //   1314	1347	1112	com/tapjoy/TapjoyIntegrationException
    //   1347	1356	1112	com/tapjoy/TapjoyIntegrationException
    //   1361	1373	1112	com/tapjoy/TapjoyIntegrationException
    //   1378	1423	1112	com/tapjoy/TapjoyIntegrationException
    //   1423	1432	1112	com/tapjoy/TapjoyIntegrationException
    //   1435	1447	1112	com/tapjoy/TapjoyIntegrationException
    //   1450	1459	1112	com/tapjoy/TapjoyIntegrationException
    //   1463	1489	1112	com/tapjoy/TapjoyIntegrationException
    //   1493	1519	1112	com/tapjoy/TapjoyIntegrationException
    //   1523	1549	1112	com/tapjoy/TapjoyIntegrationException
    //   1552	1569	1112	com/tapjoy/TapjoyIntegrationException
    //   1572	1589	1112	com/tapjoy/TapjoyIntegrationException
    //   1593	1619	1112	com/tapjoy/TapjoyIntegrationException
    //   260	299	1155	java/lang/Exception
    //   61	77	1185	com/tapjoy/TapjoyException
    //   77	105	1185	com/tapjoy/TapjoyException
    //   105	136	1185	com/tapjoy/TapjoyException
    //   136	154	1185	com/tapjoy/TapjoyException
    //   154	169	1185	com/tapjoy/TapjoyException
    //   169	186	1185	com/tapjoy/TapjoyException
    //   190	199	1185	com/tapjoy/TapjoyException
    //   199	218	1185	com/tapjoy/TapjoyException
    //   218	260	1185	com/tapjoy/TapjoyException
    //   260	299	1185	com/tapjoy/TapjoyException
    //   299	306	1185	com/tapjoy/TapjoyException
    //   310	323	1185	com/tapjoy/TapjoyException
    //   327	332	1185	com/tapjoy/TapjoyException
    //   336	345	1185	com/tapjoy/TapjoyException
    //   349	366	1185	com/tapjoy/TapjoyException
    //   366	379	1185	com/tapjoy/TapjoyException
    //   383	427	1185	com/tapjoy/TapjoyException
    //   427	450	1185	com/tapjoy/TapjoyException
    //   450	457	1185	com/tapjoy/TapjoyException
    //   461	491	1185	com/tapjoy/TapjoyException
    //   491	531	1185	com/tapjoy/TapjoyException
    //   533	580	1185	com/tapjoy/TapjoyException
    //   584	588	1185	com/tapjoy/TapjoyException
    //   588	603	1185	com/tapjoy/TapjoyException
    //   603	632	1185	com/tapjoy/TapjoyException
    //   637	644	1185	com/tapjoy/TapjoyException
    //   648	706	1185	com/tapjoy/TapjoyException
    //   706	718	1185	com/tapjoy/TapjoyException
    //   718	754	1185	com/tapjoy/TapjoyException
    //   754	831	1185	com/tapjoy/TapjoyException
    //   831	883	1185	com/tapjoy/TapjoyException
    //   885	889	1185	com/tapjoy/TapjoyException
    //   889	922	1185	com/tapjoy/TapjoyException
    //   922	952	1185	com/tapjoy/TapjoyException
    //   952	1010	1185	com/tapjoy/TapjoyException
    //   1010	1061	1185	com/tapjoy/TapjoyException
    //   1065	1089	1185	com/tapjoy/TapjoyException
    //   1089	1098	1185	com/tapjoy/TapjoyException
    //   1100	1112	1185	com/tapjoy/TapjoyException
    //   1156	1182	1185	com/tapjoy/TapjoyException
    //   1229	1255	1185	com/tapjoy/TapjoyException
    //   1258	1267	1185	com/tapjoy/TapjoyException
    //   1270	1277	1185	com/tapjoy/TapjoyException
    //   1281	1311	1185	com/tapjoy/TapjoyException
    //   1314	1347	1185	com/tapjoy/TapjoyException
    //   1347	1356	1185	com/tapjoy/TapjoyException
    //   1361	1373	1185	com/tapjoy/TapjoyException
    //   1378	1423	1185	com/tapjoy/TapjoyException
    //   1423	1432	1185	com/tapjoy/TapjoyException
    //   1435	1447	1185	com/tapjoy/TapjoyException
    //   1450	1459	1185	com/tapjoy/TapjoyException
    //   1463	1489	1185	com/tapjoy/TapjoyException
    //   1493	1519	1185	com/tapjoy/TapjoyException
    //   1523	1549	1185	com/tapjoy/TapjoyException
    //   1552	1569	1185	com/tapjoy/TapjoyException
    //   1572	1589	1185	com/tapjoy/TapjoyException
    //   1593	1619	1185	com/tapjoy/TapjoyException
    //   310	323	1228	java/lang/Exception
    //   327	332	1228	java/lang/Exception
    //   336	345	1228	java/lang/Exception
    //   349	366	1228	java/lang/Exception
    //   461	491	1280	java/lang/Exception
    //   491	531	1280	java/lang/Exception
    //   533	580	1280	java/lang/Exception
    //   584	588	1280	java/lang/Exception
    //   588	603	1280	java/lang/Exception
    //   1270	1277	1280	java/lang/Exception
    //   1314	1347	1280	java/lang/Exception
    //   1347	1356	1280	java/lang/Exception
    //   1361	1373	1280	java/lang/Exception
    //   1378	1423	1280	java/lang/Exception
    //   1423	1432	1280	java/lang/Exception
    //   1435	1447	1280	java/lang/Exception
    //   648	706	1462	java/lang/Exception
    //   706	718	1492	java/lang/Exception
    //   718	754	1522	java/lang/Exception
    //   831	883	1592	java/lang/Exception
    //   885	889	1592	java/lang/Exception
    //   1552	1569	1592	java/lang/Exception
    //   1572	1589	1592	java/lang/Exception
    //   136	154	1628	java/lang/Exception
  }
  
  private static String a(long paramLong)
  {
    try
    {
      String str = TapjoyUtil.SHA256(x + ":" + n() + ":" + paramLong + ":" + O);
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
      paramString = TapjoyUtil.SHA256(x + ":" + n() + ":" + paramLong + ":" + O + ":" + paramString);
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
      an = "";
      Iterator localIterator = aj.getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (((localApplicationInfo.flags & 0x1) != 1) && (paramList.contains(localApplicationInfo.packageName)))
        {
          TapjoyLog.i("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
          if (an.length() > 0) {
            an += ",";
          }
          an += localApplicationInfo.packageName;
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
    FeatureInfo[] arrayOfFeatureInfo = aj.getSystemAvailableFeatures();
    int i2 = arrayOfFeatureInfo.length;
    int i1 = 0;
    while (i1 < i2)
    {
      if (arrayOfFeatureInfo[i1].name.matches(paramString1))
      {
        if (paramString2 == null) {}
        while (aj.checkPermission(paramString2, g.getPackageName()) == 0) {
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
    am.put(paramString1, str);
  }
  
  /* Error */
  private static boolean d(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 863	com/tapjoy/internal/bt:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bt;
    //   4: astore_0
    //   5: aload_0
    //   6: astore_3
    //   7: aload_0
    //   8: invokevirtual 866	com/tapjoy/internal/bt:e	()Ljava/util/Map;
    //   11: astore 5
    //   13: aload_0
    //   14: astore_3
    //   15: aload 5
    //   17: ldc_w 868
    //   20: invokeinterface 871 2 0
    //   25: checkcast 320	java/lang/String
    //   28: invokestatic 875	com/tapjoy/internal/cv:a	(Ljava/lang/String;)Ljava/lang/String;
    //   31: astore 4
    //   33: aload_0
    //   34: astore_3
    //   35: aload 5
    //   37: ldc_w 877
    //   40: invokeinterface 871 2 0
    //   45: checkcast 320	java/lang/String
    //   48: invokestatic 875	com/tapjoy/internal/cv:a	(Ljava/lang/String;)Ljava/lang/String;
    //   51: astore 7
    //   53: aload_0
    //   54: astore_3
    //   55: aload 5
    //   57: ldc_w 879
    //   60: invokeinterface 871 2 0
    //   65: checkcast 320	java/lang/String
    //   68: invokestatic 875	com/tapjoy/internal/cv:a	(Ljava/lang/String;)Ljava/lang/String;
    //   71: astore 8
    //   73: aload_0
    //   74: astore_3
    //   75: aload 5
    //   77: ldc_w 881
    //   80: invokeinterface 871 2 0
    //   85: checkcast 320	java/lang/String
    //   88: invokestatic 875	com/tapjoy/internal/cv:a	(Ljava/lang/String;)Ljava/lang/String;
    //   91: astore 9
    //   93: aload_0
    //   94: astore_3
    //   95: aload 5
    //   97: ldc_w 883
    //   100: invokeinterface 871 2 0
    //   105: checkcast 320	java/lang/String
    //   108: invokestatic 875	com/tapjoy/internal/cv:a	(Ljava/lang/String;)Ljava/lang/String;
    //   111: astore 6
    //   113: aload_0
    //   114: astore_3
    //   115: aload 5
    //   117: ldc_w 885
    //   120: invokeinterface 871 2 0
    //   125: checkcast 887	java/lang/Boolean
    //   128: astore 5
    //   130: aload 5
    //   132: ifnull +13 -> 145
    //   135: aload_0
    //   136: astore_3
    //   137: aload 5
    //   139: invokevirtual 890	java/lang/Boolean:booleanValue	()Z
    //   142: putstatic 270	com/tapjoy/TapjoyConnectCore:aW	Z
    //   145: aload_0
    //   146: astore_3
    //   147: new 892	com/tapjoy/internal/ApiKeyDecoded
    //   150: dup
    //   151: aload 8
    //   153: invokespecial 893	com/tapjoy/internal/ApiKeyDecoded:<init>	(Ljava/lang/String;)V
    //   156: astore 10
    //   158: aload_0
    //   159: astore_3
    //   160: aload 10
    //   162: invokevirtual 897	com/tapjoy/internal/ApiKeyDecoded:getKeyUsage	()Lcom/tapjoy/internal/ApiKeyDecoded$KeyUsage;
    //   165: getstatic 903	com/tapjoy/internal/ApiKeyDecoded$KeyUsage:RPC_ANALYTICS	Lcom/tapjoy/internal/ApiKeyDecoded$KeyUsage;
    //   168: if_acmpeq +33 -> 201
    //   171: aload_0
    //   172: astore_3
    //   173: new 856	java/io/IOException
    //   176: dup
    //   177: ldc_w 905
    //   180: invokespecial 906	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   183: athrow
    //   184: astore_3
    //   185: ldc_w 485
    //   188: aload_3
    //   189: invokevirtual 907	java/io/IOException:getMessage	()Ljava/lang/String;
    //   192: invokestatic 909	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   195: aload_0
    //   196: invokestatic 914	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   199: iconst_0
    //   200: ireturn
    //   201: aload_0
    //   202: astore_3
    //   203: aload 10
    //   205: invokevirtual 917	com/tapjoy/internal/ApiKeyDecoded:getAppId	()Ljava/lang/String;
    //   208: invokestatic 920	com/tapjoy/internal/ApiKeyDecoded:get5RocksAppId	(Ljava/lang/String;)Ljava/lang/String;
    //   211: astore 5
    //   213: aload_0
    //   214: astore_3
    //   215: aload 10
    //   217: invokevirtual 923	com/tapjoy/internal/ApiKeyDecoded:getSecretKey	()Ljava/lang/String;
    //   220: astore 10
    //   222: aload 4
    //   224: ifnonnull +224 -> 448
    //   227: aload 5
    //   229: astore 4
    //   231: aload_0
    //   232: astore_3
    //   233: invokestatic 928	com/tapjoy/internal/fv:a	()Lcom/tapjoy/internal/fv;
    //   236: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   239: aload 8
    //   241: ldc_w 409
    //   244: ldc_w 930
    //   247: aload 5
    //   249: aload 10
    //   251: invokevirtual 933	com/tapjoy/internal/fv:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   254: aload_0
    //   255: astore_3
    //   256: aload 4
    //   258: putstatic 235	com/tapjoy/TapjoyConnectCore:Y	Ljava/lang/String;
    //   261: aload_0
    //   262: astore_3
    //   263: aload 7
    //   265: putstatic 237	com/tapjoy/TapjoyConnectCore:Z	Ljava/lang/String;
    //   268: aload_0
    //   269: astore_3
    //   270: aload 8
    //   272: putstatic 239	com/tapjoy/TapjoyConnectCore:aa	Ljava/lang/String;
    //   275: aload_0
    //   276: astore_3
    //   277: aload 9
    //   279: putstatic 241	com/tapjoy/TapjoyConnectCore:ab	Ljava/lang/String;
    //   282: aload_0
    //   283: astore_3
    //   284: new 588	java/util/ArrayList
    //   287: dup
    //   288: invokespecial 934	java/util/ArrayList:<init>	()V
    //   291: astore 4
    //   293: aload 6
    //   295: ifnull +64 -> 359
    //   298: aload_0
    //   299: astore_3
    //   300: aload 6
    //   302: ldc_w 763
    //   305: invokevirtual 938	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
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
    //   329: invokevirtual 841	java/lang/String:trim	()Ljava/lang/String;
    //   332: astore 6
    //   334: aload_0
    //   335: astore_3
    //   336: aload 6
    //   338: invokevirtual 472	java/lang/String:length	()I
    //   341: ifle +110 -> 451
    //   344: aload_0
    //   345: astore_3
    //   346: aload 4
    //   348: aload 6
    //   350: invokeinterface 939 2 0
    //   355: pop
    //   356: goto +95 -> 451
    //   359: aload_0
    //   360: astore_3
    //   361: aload 4
    //   363: invokeinterface 942 1 0
    //   368: ifne +10 -> 378
    //   371: aload_0
    //   372: astore_3
    //   373: aload 4
    //   375: invokestatic 846	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   378: aload_0
    //   379: astore_3
    //   380: aload_0
    //   381: invokevirtual 945	com/tapjoy/internal/bt:close	()V
    //   384: aconst_null
    //   385: invokestatic 914	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   388: iconst_1
    //   389: ireturn
    //   390: astore 4
    //   392: aconst_null
    //   393: astore_0
    //   394: aload_0
    //   395: astore_3
    //   396: ldc_w 485
    //   399: aload 4
    //   401: invokevirtual 946	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   404: invokestatic 909	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   407: aload_0
    //   408: invokestatic 914	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   411: goto -212 -> 199
    //   414: astore_0
    //   415: aconst_null
    //   416: astore_3
    //   417: aload_3
    //   418: invokestatic 914	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
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
    TapjoyUtil.safePut(localHashMap3, "plugin", P, true);
    TapjoyUtil.safePut(localHashMap3, "sdk_type", Q, true);
    TapjoyUtil.safePut(localHashMap3, "app_id", x, true);
    TapjoyUtil.safePut(localHashMap3, "library_version", z, true);
    TapjoyUtil.safePut(localHashMap3, "library_revision", "4f501ba", true);
    TapjoyUtil.safePut(localHashMap3, "bridge_version", A, true);
    TapjoyUtil.safePut(localHashMap3, "app_version", y, true);
    localHashMap2.putAll(localHashMap3);
    localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "device_name", t, true);
    TapjoyUtil.safePut(localHashMap3, "platform", G, true);
    TapjoyUtil.safePut(localHashMap3, "os_version", w, true);
    TapjoyUtil.safePut(localHashMap3, "device_manufacturer", u, true);
    TapjoyUtil.safePut(localHashMap3, "device_type", v, true);
    TapjoyUtil.safePut(localHashMap3, "screen_layout_size", D, true);
    TapjoyUtil.safePut(localHashMap3, "device_location", String.valueOf(F), true);
    TapjoyUtil.safePut(localHashMap3, "store_name", N, true);
    TapjoyUtil.safePut(localHashMap3, "store_view", String.valueOf(X), true);
    TapjoyUtil.safePut(localHashMap3, "carrier_name", H, true);
    TapjoyUtil.safePut(localHashMap3, "carrier_country_code", I, true);
    TapjoyUtil.safePut(localHashMap3, "mobile_network_code", K, true);
    TapjoyUtil.safePut(localHashMap3, "mobile_country_code", J, true);
    TapjoyUtil.safePut(localHashMap3, "country_code", Locale.getDefault().getCountry(), true);
    TapjoyUtil.safePut(localHashMap3, "language_code", Locale.getDefault().getLanguage(), true);
    L = getConnectionType();
    TapjoyUtil.safePut(localHashMap3, "connection_type", L, true);
    M = getConnectionSubType();
    TapjoyUtil.safePut(localHashMap3, "connection_subtype", M, true);
    TapjoyUtil.safePut(localHashMap3, "screen_density", B, true);
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
    TapjoyUtil.safePut(localHashMap3, "publisher_user_id", E, true);
    TapjoyUtil.safePut(localHashMap3, "ad_id_check_disabled", e, true);
    TapjoyUtil.safePut(localHashMap3, "persistent_ids_disabled", f, true);
    if (a != 0) {
      TapjoyUtil.safePut(localHashMap3, "packaged_gps_version", Integer.toString(a), true);
    }
    if (b != 0) {
      TapjoyUtil.safePut(localHashMap3, "device_gps_version", Integer.toString(b), true);
    }
    if ((o == null) || (o.length() == 0) || (System.currentTimeMillis() - ag > 1800000L)) {
      o = m();
    }
    for (;;)
    {
      TapjoyUtil.safePut(localHashMap3, "session_id", o, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "app_group_id", Y, true);
      TapjoyUtil.safePut(localHashMap3, "store", Z, true);
      TapjoyUtil.safePut(localHashMap3, "analytics_api_key", aa, true);
      TapjoyUtil.safePut(localHashMap3, "managed_device_id", ab, true);
      localHashMap2.putAll(localHashMap3);
      if ((TapjoyCache.getInstance() != null) && (TapjoyCache.getInstance().getCachedOfferIDs() != null) && (TapjoyCache.getInstance().getCachedOfferIDs().length() > 0)) {
        TapjoyUtil.safePut(localHashMap2, "cached_ids", TapjoyCache.getInstance().getCachedOfferIDs(), true);
      }
      TapjoyUtil.safePut(localHashMap2, "display_multiplier", Float.toString(S), true);
      localHashMap1.putAll(localHashMap2);
      localHashMap2 = new HashMap();
      f();
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "analytics_id", ap, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_id", aq, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_sign", ar, true);
      TapjoyUtil.safePut(localHashMap3, "display_d", aQ, true);
      TapjoyUtil.safePut(localHashMap3, "display_w", aR, true);
      TapjoyUtil.safePut(localHashMap3, "display_h", aS, true);
      TapjoyUtil.safePut(localHashMap3, "country_sim", aT, true);
      TapjoyUtil.safePut(localHashMap3, "timezone", aU, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "pkg_ver", as, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_rev", at, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_data_ver", au, true);
      TapjoyUtil.safePut(localHashMap3, "installer", av, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "installed", aw, true);
      TapjoyUtil.safePut(localHashMap3, "referrer", ax, true);
      TapjoyUtil.safePut(localHashMap3, "user_level", ay, true);
      TapjoyUtil.safePut(localHashMap3, "friend_count", az, true);
      TapjoyUtil.safePut(localHashMap3, "uv1", aA, true);
      TapjoyUtil.safePut(localHashMap3, "uv2", aB, true);
      TapjoyUtil.safePut(localHashMap3, "uv3", aC, true);
      TapjoyUtil.safePut(localHashMap3, "uv4", aD, true);
      TapjoyUtil.safePut(localHashMap3, "uv5", aE, true);
      TapjoyUtil.safePut(localHashMap3, "fq7", aF, true);
      TapjoyUtil.safePut(localHashMap3, "fq30", aG, true);
      TapjoyUtil.safePut(localHashMap3, "session_total_count", aH, true);
      TapjoyUtil.safePut(localHashMap3, "session_total_length", aI, true);
      TapjoyUtil.safePut(localHashMap3, "session_last_at", aJ, true);
      TapjoyUtil.safePut(localHashMap3, "session_last_length", aK, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_currency", aL, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_total_count", aM, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_total_price", aN, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_last_price", aO, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_last_at", aP, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap1.putAll(localHashMap2);
      return localHashMap1;
      ag = System.currentTimeMillis();
    }
  }
  
  /* Error */
  private static boolean e(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 863	com/tapjoy/internal/bt:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bt;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 1212	com/tapjoy/internal/bt:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 1214	com/tapjoy/internal/bt:t	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 485
    //   25: ldc_w 1216
    //   28: invokestatic 502	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 945	com/tapjoy/internal/bt:close	()V
    //   37: aconst_null
    //   38: invokestatic 914	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 945	com/tapjoy/internal/bt:close	()V
    //   49: aconst_null
    //   50: invokestatic 914	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   53: ldc_w 485
    //   56: ldc_w 1218
    //   59: invokestatic 506	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   62: iconst_0
    //   63: ireturn
    //   64: astore_2
    //   65: aconst_null
    //   66: astore_1
    //   67: aload_1
    //   68: astore_0
    //   69: ldc_w 485
    //   72: aload_2
    //   73: invokevirtual 907	java/io/IOException:getMessage	()Ljava/lang/String;
    //   76: invokestatic 909	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   79: aload_1
    //   80: invokestatic 914	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   83: goto -30 -> 53
    //   86: astore_2
    //   87: aconst_null
    //   88: astore_1
    //   89: aload_1
    //   90: astore_0
    //   91: ldc_w 485
    //   94: aload_2
    //   95: invokevirtual 946	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   98: invokestatic 909	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   101: aload_1
    //   102: invokestatic 914	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
    //   105: goto -52 -> 53
    //   108: astore_1
    //   109: aconst_null
    //   110: astore_0
    //   111: aload_0
    //   112: invokestatic 914	com/tapjoy/internal/dd:a	(Ljava/io/Closeable;)V
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
    gl.n localN = fv.a(g).b(true);
    ap = localN.f().h();
    aq = localN.f().B();
    ar = localN.f().D();
    aQ = localN.f().r();
    aR = localN.f().t();
    aS = localN.f().v();
    aT = localN.f().H();
    aU = localN.f().z();
    as = localN.h().f();
    at = localN.h().h();
    au = localN.h().j();
    av = localN.h().l();
    aw = localN.j().f();
    ax = localN.j().h();
    ay = localN.j().P();
    az = localN.j().R();
    aA = localN.j().T();
    aB = localN.j().V();
    aC = localN.j().X();
    aD = localN.j().Z();
    aE = localN.j().ab();
    aF = localN.j().j();
    aG = localN.j().l();
    aH = localN.j().p();
    aI = localN.j().r();
    aJ = localN.j().t();
    aK = localN.j().v();
    aL = localN.j().x();
    aM = localN.j().z();
    aN = localN.j().B();
    aO = localN.j().F();
    aP = localN.j().D();
  }
  
  private static boolean f(String paramString)
  {
    Iterator localIterator = aj.getInstalledApplications(0).iterator();
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
    Iterator localIterator = am.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      TapjoyLog.i("TapjoyConnect", "key: " + (String)localEntry.getKey() + ", value: " + Uri.encode(localEntry.getValue().toString()));
    }
    TapjoyLog.i("TapjoyConnect", "hostURL: [" + getConnectFlagValue("TJC_OPTION_SERVICE_URL") + "]");
    TapjoyLog.i("TapjoyConnect", "redirectDomain: [" + R + "]");
    TapjoyLog.i("TapjoyConnect", "--------------------");
  }
  
  private static boolean g(String paramString)
  {
    Object localObject = new Intent("android.intent.action.SEND");
    ((Intent)localObject).setType("text/plain");
    localObject = aj.queryIntentActivities((Intent)localObject, 0).iterator();
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
    return x;
  }
  
  public static String getAwardCurrencyVerifier(long paramLong, int paramInt, String paramString)
  {
    try
    {
      paramString = TapjoyUtil.SHA256(x + ":" + n() + ":" + paramLong + ":" + O + ":" + paramInt + ":" + paramString);
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
    return H;
  }
  
  public static String getConnectFlagValue(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    if (am != null)
    {
      str1 = str2;
      if (am.get(paramString) != null) {
        str1 = am.get(paramString).toString();
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
    return C;
  }
  
  public static Map getGenericURLParams()
  {
    Map localMap = e();
    TapjoyUtil.safePut(localMap, "app_id", x, true);
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
    return R;
  }
  
  public static String getSecretKey()
  {
    return O;
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
    return E;
  }
  
  private static void h()
  {
    for (;;)
    {
      int i1;
      try
      {
        if (aj == null) {
          break;
        }
        ApplicationInfo localApplicationInfo = aj.getApplicationInfo(g.getPackageName(), 128);
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
    return aj.checkPermission(paramString, g.getPackageName()) == 0;
  }
  
  private void i()
  {
    Object localObject3;
    for (;;)
    {
      int i1;
      try
      {
        Object localObject1 = Arrays.asList(aj.getPackageInfo(g.getPackageName(), 1).activities);
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
    this.al.checkGooglePlayIntegration();
  }
  
  public static boolean isAutoSessionTrackingStarted()
  {
    return aV;
  }
  
  public static boolean isConnected()
  {
    return ai;
  }
  
  public static boolean isViewOpen()
  {
    return ao;
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
      str = TapjoyUtil.SHA256(System.currentTimeMillis() / 1000L + x + q);
      TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + localException1.toString());
    }
    catch (Exception localException1)
    {
      try
      {
        ag = System.currentTimeMillis();
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
    x = localApiKeyDecoded.getAppId();
    O = localApiKeyDecoded.getSecretKey();
    fv.a().a(paramString);
    if (paramHashtable != null) {
      am.putAll(paramHashtable);
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
    new Thread(new TapjoyConnectCore.4()).start();
  }
  
  public static void setAutoSessionTrackingStarted(boolean paramBoolean)
  {
    aV = paramBoolean;
  }
  
  public static void setPlugin(String paramString)
  {
    P = paramString;
  }
  
  public static void setSDKType(String paramString)
  {
    Q = paramString;
  }
  
  public static void setUserID(String paramString)
  {
    E = paramString;
    TapjoyLog.i("TapjoyConnect", "URL parameters: " + getURLParams());
    new Thread(new TapjoyConnectCore.3()).start();
  }
  
  public static void setViewShowing(boolean paramBoolean)
  {
    ao = paramBoolean;
  }
  
  public static void viewDidClose(int paramInt)
  {
    ao = false;
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
    ao = true;
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
    this.af = true;
  }
  
  public void appResume()
  {
    if (this.af)
    {
      m();
      this.af = false;
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
        if (aW) {
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
        ai = true;
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
        if (an.length() > 0)
        {
          Object localObject2 = getGenericURLParams();
          TapjoyUtil.safePut((Map)localObject2, "package_names", an, true);
          long l1 = System.currentTimeMillis() / 1000L;
          localObject3 = a(l1, an);
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
    this.ak = new fc();
    try
    {
      this.ak.a(new TapjoyConnectCore.1(this));
      this.ak.a();
      this.ak.a(g, "rrx68giz", "h.online-metrix.net", "http://content-js.tapjoy.com");
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
    ac = paramString;
    this.ad = g.getSharedPreferences("tjcPrefrences", 0).getLong("tapjoy_elapsed_time", 0L);
    TapjoyLog.i("TapjoyConnect", "paidApp elapsed: " + this.ad);
    if (this.ad >= 900000L) {
      if ((ac != null) && (ac.length() > 0))
      {
        TapjoyLog.i("TapjoyConnect", "Calling PPA actionComplete...");
        actionComplete(ac);
      }
    }
    while (this.ae != null) {
      return;
    }
    this.ae = new Timer();
    this.ae.schedule(new TapjoyConnectCore.a(this, (byte)0), 10000L, 10000L);
  }
  
  public void fetchAdvertisingID()
  {
    new Thread(new TapjoyConnectCore.2(this)).start();
  }
  
  public float getCurrencyMultiplier()
  {
    return S;
  }
  
  /* Error */
  public String getSerial()
  {
    // Byte code:
    //   0: ldc_w 1791
    //   3: invokestatic 1483	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 1793
    //   9: invokevirtual 1797	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 1802	java/lang/reflect/Field:isAccessible	()Z
    //   17: ifne +8 -> 25
    //   20: aload_1
    //   21: iconst_1
    //   22: invokevirtual 1805	java/lang/reflect/Field:setAccessible	(Z)V
    //   25: aload_1
    //   26: ldc_w 396
    //   29: invokevirtual 1806	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: invokevirtual 1332	java/lang/Object:toString	()Ljava/lang/String;
    //   35: astore_1
    //   36: ldc_w 485
    //   39: new 487	java/lang/StringBuilder
    //   42: dup
    //   43: ldc_w 1808
    //   46: invokespecial 492	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   49: aload_1
    //   50: invokevirtual 496	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 502	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload_1
    //   60: areturn
    //   61: astore_2
    //   62: aconst_null
    //   63: astore_1
    //   64: ldc_w 485
    //   67: aload_2
    //   68: invokevirtual 670	java/lang/Exception:toString	()Ljava/lang/String;
    //   71: invokestatic 506	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
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
    return this.ah;
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
    S = paramFloat;
  }
  
  public void setTapjoyViewListener(TJViewListener paramTJViewListener)
  {
    l = paramTJViewListener;
  }
}
