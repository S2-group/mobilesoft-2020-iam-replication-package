package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.tapjoy.internal.cs;
import com.tapjoy.internal.em;
import com.tapjoy.internal.em.a;
import com.tapjoy.internal.ep;
import com.tapjoy.internal.ep.a;
import com.tapjoy.internal.fd;
import com.tapjoy.internal.fg;
import com.tapjoy.internal.fj;
import com.tapjoy.internal.fx;
import com.tapjoy.internal.fy;
import com.tapjoy.internal.fz;
import com.tapjoy.internal.gc;
import com.tapjoy.internal.gh;
import com.tapjoy.internal.gk;
import com.tapjoy.internal.gm;
import com.tapjoy.internal.gn;
import com.tapjoy.internal.gn.1;
import com.tapjoy.internal.go;
import com.tapjoy.internal.hh;
import com.tapjoy.internal.hy.a;
import com.tapjoy.internal.hy.l;
import com.tapjoy.internal.hy.n;
import com.tapjoy.internal.hy.z;
import com.tapjoy.internal.z;
import com.tapjoy.mediation.TJMediationNetwork;
import com.tapjoy.mediation.TJMediationSettings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class TapjoyConnectCore
{
  private static String A;
  private static int B = 0;
  private static float C = 0.0F;
  private static int D = 0;
  public static final float DEFAULT_CURRENCY_MULTIPLIER = 1.0F;
  private static String E;
  private static boolean F;
  private static String G;
  private static String H;
  private static String I;
  private static String J;
  private static String K;
  private static String L;
  private static String M;
  private static String N;
  private static String O;
  private static String P;
  private static String Q;
  private static String R;
  private static String S;
  private static float T;
  private static boolean U;
  private static String V;
  private static String W;
  private static String X;
  private static String Y;
  private static String Z;
  protected static int a;
  private static String aA;
  private static String aB;
  private static Set aC;
  private static int aD;
  private static int aE;
  private static int aF;
  private static long aG;
  private static long aH;
  private static long aI;
  private static String aJ;
  private static int aK;
  private static double aL;
  private static double aM;
  private static long aN;
  private static int aO;
  private static int aP;
  private static int aQ;
  private static String aR;
  private static String aS;
  private static String aT;
  private static boolean aU = true;
  private static long ad;
  private static boolean af;
  private static PackageManager ag;
  private static Hashtable aj;
  private static String ak;
  private static Map al;
  private static String am;
  private static String an;
  private static String ao;
  private static String ap;
  private static int aq;
  private static String ar;
  private static String as;
  private static long at;
  private static String au;
  private static int av;
  private static int aw;
  private static String ax;
  private static String ay;
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
  private static TJSetUserIDListener l = null;
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
  private long aa;
  private Timer ab;
  private boolean ac;
  private boolean ae;
  private gn ah;
  private TapjoyGpsHelper ai;
  
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
    P = "";
    Q = "native";
    R = "";
    S = "";
    T = 1.0F;
    U = false;
    V = "";
    W = "";
    X = "";
    Y = "";
    Z = null;
    ad = 0L;
    a = 0;
    b = 0;
    c = "";
    e = "";
    f = "";
    aj = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
    ak = "";
    al = new ConcurrentHashMap();
  }
  
  /* Error */
  public TapjoyConnectCore(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 288	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 290	com/tapjoy/TapjoyConnectCore:aa	J
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 292	com/tapjoy/TapjoyConnectCore:ab	Ljava/util/Timer;
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 294	com/tapjoy/TapjoyConnectCore:ac	Z
    //   19: aload_0
    //   20: iconst_0
    //   21: putfield 296	com/tapjoy/TapjoyConnectCore:ae	Z
    //   24: aload_1
    //   25: putstatic 138	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   28: new 298	com/tapjoy/TapjoyURLConnection
    //   31: dup
    //   32: invokespecial 299	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   35: putstatic 144	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   38: getstatic 138	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   41: invokevirtual 305	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   44: putstatic 307	com/tapjoy/TapjoyConnectCore:ag	Landroid/content/pm/PackageManager;
    //   47: aload_0
    //   48: new 309	com/tapjoy/TapjoyGpsHelper
    //   51: dup
    //   52: getstatic 138	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   55: invokespecial 311	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   58: putfield 313	com/tapjoy/TapjoyConnectCore:ai	Lcom/tapjoy/TapjoyGpsHelper;
    //   61: getstatic 267	com/tapjoy/TapjoyConnectCore:aj	Ljava/util/Hashtable;
    //   64: ifnonnull +13 -> 77
    //   67: new 315	java/util/Hashtable
    //   70: dup
    //   71: invokespecial 316	java/util/Hashtable:<init>	()V
    //   74: putstatic 267	com/tapjoy/TapjoyConnectCore:aj	Ljava/util/Hashtable;
    //   77: ldc_w 318
    //   80: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   83: ifnull +22 -> 105
    //   86: ldc_w 318
    //   89: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   92: ldc_w 324
    //   95: invokevirtual 330	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   98: ifeq +7 -> 105
    //   101: iconst_1
    //   102: invokestatic 336	com/tapjoy/TapjoyLog:setDebugEnabled	(Z)V
    //   105: invokestatic 338	com/tapjoy/TapjoyConnectCore:k	()V
    //   108: getstatic 138	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   111: invokevirtual 342	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   114: ldc_w 344
    //   117: aconst_null
    //   118: getstatic 138	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   121: invokevirtual 348	android/content/Context:getPackageName	()Ljava/lang/String;
    //   124: invokevirtual 354	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   127: istore_2
    //   128: new 356	java/util/Properties
    //   131: dup
    //   132: invokespecial 357	java/util/Properties:<init>	()V
    //   135: astore_1
    //   136: aload_1
    //   137: getstatic 138	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   140: invokevirtual 342	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   143: iload_2
    //   144: invokevirtual 361	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   147: invokevirtual 365	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   150: aload_1
    //   151: invokestatic 368	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   154: ldc_w 370
    //   157: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   160: ldc -86
    //   162: if_acmpne +7 -> 169
    //   165: aload_0
    //   166: invokespecial 372	com/tapjoy/TapjoyConnectCore:l	()V
    //   169: getstatic 138	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   172: invokevirtual 376	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   175: ldc_w 378
    //   178: invokestatic 384	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   181: astore_1
    //   182: aload_1
    //   183: putstatic 172	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   186: aload_1
    //   187: ifnull +12 -> 199
    //   190: getstatic 172	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   193: invokevirtual 387	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   196: putstatic 172	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   199: getstatic 307	com/tapjoy/TapjoyConnectCore:ag	Landroid/content/pm/PackageManager;
    //   202: getstatic 138	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   205: invokevirtual 348	android/content/Context:getPackageName	()Ljava/lang/String;
    //   208: iconst_0
    //   209: invokevirtual 393	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   212: getfield 398	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   215: putstatic 192	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   218: ldc_w 400
    //   221: putstatic 186	com/tapjoy/TapjoyConnectCore:v	Ljava/lang/String;
    //   224: ldc_w 400
    //   227: putstatic 208	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   230: getstatic 405	android/os/Build:MODEL	Ljava/lang/String;
    //   233: putstatic 182	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   236: getstatic 408	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   239: putstatic 184	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   242: getstatic 413	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   245: putstatic 188	com/tapjoy/TapjoyConnectCore:w	Ljava/lang/String;
    //   248: ldc_w 415
    //   251: putstatic 194	com/tapjoy/TapjoyConnectCore:z	Ljava/lang/String;
    //   254: ldc_w 417
    //   257: putstatic 196	com/tapjoy/TapjoyConnectCore:A	Ljava/lang/String;
    //   260: getstatic 420	android/os/Build$VERSION:SDK_INT	I
    //   263: iconst_3
    //   264: if_icmple +35 -> 299
    //   267: new 422	com/tapjoy/TapjoyDisplayMetricsUtil
    //   270: dup
    //   271: getstatic 138	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   274: invokespecial 423	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   277: astore_1
    //   278: aload_1
    //   279: invokevirtual 427	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   282: putstatic 198	com/tapjoy/TapjoyConnectCore:B	I
    //   285: aload_1
    //   286: invokevirtual 431	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   289: putstatic 200	com/tapjoy/TapjoyConnectCore:C	F
    //   292: aload_1
    //   293: invokevirtual 434	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   296: putstatic 202	com/tapjoy/TapjoyConnectCore:D	I
    //   299: ldc_w 436
    //   302: invokestatic 439	com/tapjoy/TapjoyConnectCore:f	(Ljava/lang/String;)Z
    //   305: istore_3
    //   306: iload_3
    //   307: ifeq +914 -> 1221
    //   310: getstatic 138	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   313: ldc_w 441
    //   316: invokevirtual 445	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   319: checkcast 447	android/net/wifi/WifiManager
    //   322: astore_1
    //   323: aload_1
    //   324: ifnull +42 -> 366
    //   327: aload_1
    //   328: invokevirtual 451	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   331: astore_1
    //   332: aload_1
    //   333: ifnull +33 -> 366
    //   336: aload_1
    //   337: invokevirtual 456	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   340: astore_1
    //   341: aload_1
    //   342: putstatic 178	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   345: aload_1
    //   346: ifnull +20 -> 366
    //   349: getstatic 178	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   352: ldc_w 458
    //   355: ldc -86
    //   357: invokevirtual 462	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   360: invokevirtual 387	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   363: putstatic 178	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   366: getstatic 138	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   369: ldc_w 464
    //   372: invokevirtual 445	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   375: checkcast 466	android/telephony/TelephonyManager
    //   378: astore_1
    //   379: aload_1
    //   380: ifnull +239 -> 619
    //   383: aload_1
    //   384: invokevirtual 469	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   387: putstatic 210	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   390: aload_1
    //   391: invokevirtual 472	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   394: putstatic 212	com/tapjoy/TapjoyConnectCore:I	Ljava/lang/String;
    //   397: aload_1
    //   398: invokevirtual 475	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   401: astore 4
    //   403: aload 4
    //   405: ifnull +41 -> 446
    //   408: aload 4
    //   410: invokevirtual 478	java/lang/String:length	()I
    //   413: iconst_5
    //   414: if_icmpeq +13 -> 427
    //   417: aload 4
    //   419: invokevirtual 478	java/lang/String:length	()I
    //   422: bipush 6
    //   424: if_icmpne +22 -> 446
    //   427: aload 4
    //   429: iconst_0
    //   430: iconst_3
    //   431: invokevirtual 482	java/lang/String:substring	(II)Ljava/lang/String;
    //   434: putstatic 214	com/tapjoy/TapjoyConnectCore:J	Ljava/lang/String;
    //   437: aload 4
    //   439: iconst_3
    //   440: invokevirtual 485	java/lang/String:substring	(I)Ljava/lang/String;
    //   443: putstatic 216	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   446: ldc_w 487
    //   449: invokestatic 439	com/tapjoy/TapjoyConnectCore:f	(Ljava/lang/String;)Z
    //   452: istore_3
    //   453: iload_3
    //   454: ifeq +989 -> 1443
    //   457: ldc_w 489
    //   460: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   463: ifnull +770 -> 1233
    //   466: ldc_w 489
    //   469: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   472: invokevirtual 478	java/lang/String:length	()I
    //   475: ifle +758 -> 1233
    //   478: ldc_w 489
    //   481: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   484: putstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   487: ldc_w 491
    //   490: new 493	java/lang/StringBuilder
    //   493: dup
    //   494: ldc_w 495
    //   497: invokespecial 498	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   500: getstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   503: invokevirtual 502	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: invokevirtual 505	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   509: invokestatic 508	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   512: getstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   515: ifnonnull +772 -> 1287
    //   518: ldc_w 491
    //   521: new 510	com/tapjoy/TapjoyErrorMessage
    //   524: dup
    //   525: getstatic 516	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   528: ldc_w 518
    //   531: invokespecial 521	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   534: invokestatic 524	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   537: iconst_0
    //   538: istore_2
    //   539: ldc_w 491
    //   542: new 493	java/lang/StringBuilder
    //   545: dup
    //   546: ldc_w 526
    //   549: invokespecial 498	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   552: getstatic 420	android/os/Build$VERSION:SDK_INT	I
    //   555: invokevirtual 529	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   558: invokevirtual 505	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   561: invokestatic 531	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   564: getstatic 420	android/os/Build$VERSION:SDK_INT	I
    //   567: bipush 9
    //   569: if_icmplt +50 -> 619
    //   572: ldc_w 491
    //   575: ldc_w 533
    //   578: invokestatic 508	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   581: aload_0
    //   582: invokevirtual 536	com/tapjoy/TapjoyConnectCore:getSerial	()Ljava/lang/String;
    //   585: astore_1
    //   586: iload_2
    //   587: ifne +7 -> 594
    //   590: aload_1
    //   591: putstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   594: getstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   597: ifnonnull +764 -> 1361
    //   600: ldc_w 491
    //   603: new 510	com/tapjoy/TapjoyErrorMessage
    //   606: dup
    //   607: getstatic 516	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   610: ldc_w 538
    //   613: invokespecial 521	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   616: invokestatic 524	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   619: getstatic 138	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   622: ldc_w 540
    //   625: iconst_0
    //   626: invokevirtual 544	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   629: astore_1
    //   630: aload_1
    //   631: ldc_w 546
    //   634: ldc -86
    //   636: invokeinterface 551 3 0
    //   641: astore 4
    //   643: aload 4
    //   645: putstatic 180	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   648: aload 4
    //   650: ifnull +14 -> 664
    //   653: getstatic 180	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   656: invokevirtual 478	java/lang/String:length	()I
    //   659: istore_2
    //   660: iload_2
    //   661: ifne +61 -> 722
    //   664: new 493	java/lang/StringBuilder
    //   667: dup
    //   668: invokespecial 552	java/lang/StringBuilder:<init>	()V
    //   671: invokestatic 558	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   674: invokevirtual 559	java/util/UUID:toString	()Ljava/lang/String;
    //   677: invokevirtual 502	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   680: invokestatic 565	java/lang/System:currentTimeMillis	()J
    //   683: invokevirtual 568	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   686: invokevirtual 505	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   689: invokestatic 573	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   692: putstatic 180	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   695: aload_1
    //   696: invokeinterface 577 1 0
    //   701: astore_1
    //   702: aload_1
    //   703: ldc_w 546
    //   706: getstatic 180	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   709: invokeinterface 583 3 0
    //   714: pop
    //   715: aload_1
    //   716: invokeinterface 587 1 0
    //   721: pop
    //   722: ldc_w 589
    //   725: ldc_w 591
    //   728: invokestatic 594	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   731: putstatic 206	com/tapjoy/TapjoyConnectCore:F	Z
    //   734: ldc_w 596
    //   737: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   740: ifnull +71 -> 811
    //   743: ldc_w 596
    //   746: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   749: invokevirtual 478	java/lang/String:length	()I
    //   752: ifle +59 -> 811
    //   755: ldc_w 596
    //   758: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   761: putstatic 222	com/tapjoy/TapjoyConnectCore:N	Ljava/lang/String;
    //   764: new 598	java/util/ArrayList
    //   767: dup
    //   768: getstatic 601	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   771: invokestatic 162	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   774: invokespecial 602	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   777: getstatic 222	com/tapjoy/TapjoyConnectCore:N	Ljava/lang/String;
    //   780: invokevirtual 605	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   783: ifne +28 -> 811
    //   786: ldc_w 491
    //   789: new 493	java/lang/StringBuilder
    //   792: dup
    //   793: ldc_w 607
    //   796: invokespecial 498	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   799: getstatic 222	com/tapjoy/TapjoyConnectCore:N	Ljava/lang/String;
    //   802: invokevirtual 502	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   805: invokevirtual 505	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   808: invokestatic 609	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   811: getstatic 222	com/tapjoy/TapjoyConnectCore:N	Ljava/lang/String;
    //   814: astore_1
    //   815: new 611	android/content/Intent
    //   818: dup
    //   819: ldc_w 613
    //   822: invokespecial 614	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   825: astore 4
    //   827: aload_1
    //   828: invokevirtual 478	java/lang/String:length	()I
    //   831: ifgt +684 -> 1515
    //   834: aload 4
    //   836: ldc_w 616
    //   839: invokestatic 622	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   842: invokevirtual 626	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   845: pop
    //   846: getstatic 307	com/tapjoy/TapjoyConnectCore:ag	Landroid/content/pm/PackageManager;
    //   849: aload 4
    //   851: iconst_0
    //   852: invokevirtual 630	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   855: invokeinterface 635 1 0
    //   860: ifle +735 -> 1595
    //   863: iconst_1
    //   864: istore_3
    //   865: iload_3
    //   866: putstatic 238	com/tapjoy/TapjoyConnectCore:U	Z
    //   869: invokestatic 637	com/tapjoy/TapjoyConnectCore:i	()V
    //   872: ldc_w 639
    //   875: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   878: ifnull +24 -> 902
    //   881: ldc_w 639
    //   884: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   887: invokevirtual 478	java/lang/String:length	()I
    //   890: ifle +12 -> 902
    //   893: ldc_w 639
    //   896: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   899: putstatic 260	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   902: ldc_w 641
    //   905: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   908: ifnull +24 -> 932
    //   911: ldc_w 641
    //   914: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   917: invokevirtual 478	java/lang/String:length	()I
    //   920: ifle +12 -> 932
    //   923: ldc_w 641
    //   926: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   929: putstatic 258	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   932: ldc_w 643
    //   935: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   938: ifnull +53 -> 991
    //   941: ldc_w 643
    //   944: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   947: invokevirtual 478	java/lang/String:length	()I
    //   950: ifle +41 -> 991
    //   953: ldc_w 491
    //   956: new 493	java/lang/StringBuilder
    //   959: dup
    //   960: ldc_w 645
    //   963: invokespecial 498	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   966: ldc_w 643
    //   969: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   972: invokevirtual 502	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   975: invokevirtual 505	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   978: invokestatic 531	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   981: ldc_w 643
    //   984: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   987: aconst_null
    //   988: invokestatic 649	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;Lcom/tapjoy/TJSetUserIDListener;)V
    //   991: ldc_w 651
    //   994: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   997: invokestatic 654	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   1000: putstatic 234	com/tapjoy/TapjoyConnectCore:S	Ljava/lang/String;
    //   1003: new 493	java/lang/StringBuilder
    //   1006: dup
    //   1007: ldc_w 495
    //   1010: invokespecial 498	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1013: getstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1016: invokevirtual 502	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1019: astore 4
    //   1021: ldc_w 489
    //   1024: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1027: ifnull +558 -> 1585
    //   1030: ldc_w 489
    //   1033: invokestatic 322	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1036: invokevirtual 478	java/lang/String:length	()I
    //   1039: ifle +546 -> 1585
    //   1042: ldc_w 656
    //   1045: astore_1
    //   1046: ldc_w 491
    //   1049: aload 4
    //   1051: aload_1
    //   1052: invokevirtual 502	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1055: invokevirtual 505	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1058: invokestatic 508	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1061: getstatic 267	com/tapjoy/TapjoyConnectCore:aj	Ljava/util/Hashtable;
    //   1064: ifnull +6 -> 1070
    //   1067: invokestatic 658	com/tapjoy/TapjoyConnectCore:j	()V
    //   1070: aload_0
    //   1071: invokevirtual 661	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   1074: aload_0
    //   1075: iconst_1
    //   1076: putfield 296	com/tapjoy/TapjoyConnectCore:ae	Z
    //   1079: return
    //   1080: astore_1
    //   1081: new 283	com/tapjoy/TapjoyException
    //   1084: dup
    //   1085: aload_1
    //   1086: invokevirtual 664	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   1089: invokespecial 665	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   1092: athrow
    //   1093: astore_1
    //   1094: ldc_w 491
    //   1097: new 510	com/tapjoy/TapjoyErrorMessage
    //   1100: dup
    //   1101: getstatic 668	com/tapjoy/TapjoyErrorMessage$ErrorType:INTEGRATION_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1104: aload_1
    //   1105: invokevirtual 669	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   1108: invokespecial 521	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1111: invokestatic 524	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1114: invokestatic 671	com/tapjoy/TapjoyConnectCore:f	()V
    //   1117: getstatic 676	com/tapjoy/internal/ep:b	Lcom/tapjoy/internal/ep$a;
    //   1120: getstatic 682	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1123: invokevirtual 688	com/tapjoy/internal/ep$a:notifyObservers	(Ljava/lang/Object;)V
    //   1126: return
    //   1127: astore_1
    //   1128: ldc_w 491
    //   1131: new 493	java/lang/StringBuilder
    //   1134: dup
    //   1135: ldc_w 690
    //   1138: invokespecial 498	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1141: aload_1
    //   1142: invokevirtual 691	java/lang/Exception:toString	()Ljava/lang/String;
    //   1145: invokevirtual 502	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1148: invokevirtual 505	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1151: invokestatic 693	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1154: goto -855 -> 299
    //   1157: astore_1
    //   1158: ldc_w 491
    //   1161: new 510	com/tapjoy/TapjoyErrorMessage
    //   1164: dup
    //   1165: getstatic 516	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1168: aload_1
    //   1169: invokevirtual 694	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   1172: invokespecial 521	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1175: invokestatic 524	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1178: invokestatic 671	com/tapjoy/TapjoyConnectCore:f	()V
    //   1181: getstatic 676	com/tapjoy/internal/ep:b	Lcom/tapjoy/internal/ep$a;
    //   1184: getstatic 682	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1187: invokevirtual 688	com/tapjoy/internal/ep$a:notifyObservers	(Ljava/lang/Object;)V
    //   1190: return
    //   1191: astore_1
    //   1192: ldc_w 491
    //   1195: new 493	java/lang/StringBuilder
    //   1198: dup
    //   1199: ldc_w 696
    //   1202: invokespecial 498	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1205: aload_1
    //   1206: invokevirtual 691	java/lang/Exception:toString	()Ljava/lang/String;
    //   1209: invokevirtual 502	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1212: invokevirtual 505	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1215: invokestatic 693	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1218: goto -852 -> 366
    //   1221: ldc_w 491
    //   1224: ldc_w 698
    //   1227: invokestatic 508	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1230: goto -864 -> 366
    //   1233: aload_1
    //   1234: invokevirtual 701	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1237: putstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1240: goto -753 -> 487
    //   1243: astore_1
    //   1244: ldc_w 491
    //   1247: new 510	com/tapjoy/TapjoyErrorMessage
    //   1250: dup
    //   1251: getstatic 516	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1254: new 493	java/lang/StringBuilder
    //   1257: dup
    //   1258: ldc_w 703
    //   1261: invokespecial 498	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1264: aload_1
    //   1265: invokevirtual 691	java/lang/Exception:toString	()Ljava/lang/String;
    //   1268: invokevirtual 502	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1271: invokevirtual 505	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1274: invokespecial 521	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1277: invokestatic 524	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1280: aconst_null
    //   1281: putstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1284: goto -665 -> 619
    //   1287: getstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1290: invokevirtual 478	java/lang/String:length	()I
    //   1293: ifeq +27 -> 1320
    //   1296: getstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1299: ldc_w 705
    //   1302: invokevirtual 330	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1305: ifne +15 -> 1320
    //   1308: getstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1311: ldc_w 707
    //   1314: invokevirtual 330	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1317: ifeq +27 -> 1344
    //   1320: ldc_w 491
    //   1323: new 510	com/tapjoy/TapjoyErrorMessage
    //   1326: dup
    //   1327: getstatic 516	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1330: ldc_w 709
    //   1333: invokespecial 521	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1336: invokestatic 524	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1339: iconst_0
    //   1340: istore_2
    //   1341: goto -802 -> 539
    //   1344: getstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1347: invokestatic 715	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1350: invokevirtual 718	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1353: putstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1356: iconst_1
    //   1357: istore_2
    //   1358: goto -819 -> 539
    //   1361: getstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1364: invokevirtual 478	java/lang/String:length	()I
    //   1367: ifeq +39 -> 1406
    //   1370: getstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1373: ldc_w 705
    //   1376: invokevirtual 330	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1379: ifne +27 -> 1406
    //   1382: getstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1385: ldc_w 707
    //   1388: invokevirtual 330	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1391: ifne +15 -> 1406
    //   1394: getstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1397: ldc_w 720
    //   1400: invokevirtual 330	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1403: ifeq +25 -> 1428
    //   1406: ldc_w 491
    //   1409: new 510	com/tapjoy/TapjoyErrorMessage
    //   1412: dup
    //   1413: getstatic 516	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1416: ldc_w 722
    //   1419: invokespecial 521	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1422: invokestatic 524	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1425: goto -806 -> 619
    //   1428: getstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1431: invokestatic 715	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1434: invokevirtual 718	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1437: putstatic 176	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1440: goto -821 -> 619
    //   1443: ldc_w 491
    //   1446: ldc_w 724
    //   1449: invokestatic 508	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1452: goto -833 -> 619
    //   1455: astore_1
    //   1456: ldc_w 491
    //   1459: new 493	java/lang/StringBuilder
    //   1462: dup
    //   1463: ldc_w 726
    //   1466: invokespecial 498	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1469: aload_1
    //   1470: invokevirtual 691	java/lang/Exception:toString	()Ljava/lang/String;
    //   1473: invokevirtual 502	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1476: invokevirtual 505	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1479: invokestatic 693	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1482: goto -760 -> 722
    //   1485: astore_1
    //   1486: ldc_w 491
    //   1489: new 493	java/lang/StringBuilder
    //   1492: dup
    //   1493: ldc_w 728
    //   1496: invokespecial 498	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1499: aload_1
    //   1500: invokevirtual 691	java/lang/Exception:toString	()Ljava/lang/String;
    //   1503: invokevirtual 502	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1506: invokevirtual 505	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1509: invokestatic 693	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1512: goto -778 -> 734
    //   1515: aload_1
    //   1516: ldc_w 730
    //   1519: invokevirtual 330	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1522: ifeq +13 -> 1535
    //   1525: ldc_w 732
    //   1528: invokestatic 734	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   1531: istore_3
    //   1532: goto -667 -> 865
    //   1535: aload_1
    //   1536: ldc_w 736
    //   1539: invokevirtual 330	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1542: ifeq +53 -> 1595
    //   1545: ldc_w 738
    //   1548: invokestatic 734	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   1551: istore_3
    //   1552: goto -687 -> 865
    //   1555: astore_1
    //   1556: ldc_w 491
    //   1559: new 493	java/lang/StringBuilder
    //   1562: dup
    //   1563: ldc_w 740
    //   1566: invokespecial 498	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1569: aload_1
    //   1570: invokevirtual 691	java/lang/Exception:toString	()Ljava/lang/String;
    //   1573: invokevirtual 502	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1576: invokevirtual 505	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1579: invokestatic 693	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1582: goto -713 -> 869
    //   1585: ldc -86
    //   1587: astore_1
    //   1588: goto -542 -> 1046
    //   1591: astore_1
    //   1592: goto -1438 -> 154
    //   1595: iconst_0
    //   1596: istore_3
    //   1597: goto -732 -> 865
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1600	0	this	TapjoyConnectCore
    //   0	1600	1	paramContext	Context
    //   127	1231	2	i1	int
    //   305	1292	3	bool	boolean
    //   401	649	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   199	218	1080	android/content/pm/PackageManager$NameNotFoundException
    //   61	77	1093	com/tapjoy/TapjoyIntegrationException
    //   77	105	1093	com/tapjoy/TapjoyIntegrationException
    //   105	136	1093	com/tapjoy/TapjoyIntegrationException
    //   136	154	1093	com/tapjoy/TapjoyIntegrationException
    //   154	169	1093	com/tapjoy/TapjoyIntegrationException
    //   169	186	1093	com/tapjoy/TapjoyIntegrationException
    //   190	199	1093	com/tapjoy/TapjoyIntegrationException
    //   199	218	1093	com/tapjoy/TapjoyIntegrationException
    //   218	260	1093	com/tapjoy/TapjoyIntegrationException
    //   260	299	1093	com/tapjoy/TapjoyIntegrationException
    //   299	306	1093	com/tapjoy/TapjoyIntegrationException
    //   310	323	1093	com/tapjoy/TapjoyIntegrationException
    //   327	332	1093	com/tapjoy/TapjoyIntegrationException
    //   336	345	1093	com/tapjoy/TapjoyIntegrationException
    //   349	366	1093	com/tapjoy/TapjoyIntegrationException
    //   366	379	1093	com/tapjoy/TapjoyIntegrationException
    //   383	403	1093	com/tapjoy/TapjoyIntegrationException
    //   408	427	1093	com/tapjoy/TapjoyIntegrationException
    //   427	446	1093	com/tapjoy/TapjoyIntegrationException
    //   446	453	1093	com/tapjoy/TapjoyIntegrationException
    //   457	487	1093	com/tapjoy/TapjoyIntegrationException
    //   487	537	1093	com/tapjoy/TapjoyIntegrationException
    //   539	586	1093	com/tapjoy/TapjoyIntegrationException
    //   590	594	1093	com/tapjoy/TapjoyIntegrationException
    //   594	619	1093	com/tapjoy/TapjoyIntegrationException
    //   619	648	1093	com/tapjoy/TapjoyIntegrationException
    //   653	660	1093	com/tapjoy/TapjoyIntegrationException
    //   664	722	1093	com/tapjoy/TapjoyIntegrationException
    //   722	734	1093	com/tapjoy/TapjoyIntegrationException
    //   734	811	1093	com/tapjoy/TapjoyIntegrationException
    //   811	863	1093	com/tapjoy/TapjoyIntegrationException
    //   865	869	1093	com/tapjoy/TapjoyIntegrationException
    //   869	902	1093	com/tapjoy/TapjoyIntegrationException
    //   902	932	1093	com/tapjoy/TapjoyIntegrationException
    //   932	991	1093	com/tapjoy/TapjoyIntegrationException
    //   991	1042	1093	com/tapjoy/TapjoyIntegrationException
    //   1046	1070	1093	com/tapjoy/TapjoyIntegrationException
    //   1070	1079	1093	com/tapjoy/TapjoyIntegrationException
    //   1081	1093	1093	com/tapjoy/TapjoyIntegrationException
    //   1128	1154	1093	com/tapjoy/TapjoyIntegrationException
    //   1192	1218	1093	com/tapjoy/TapjoyIntegrationException
    //   1221	1230	1093	com/tapjoy/TapjoyIntegrationException
    //   1233	1240	1093	com/tapjoy/TapjoyIntegrationException
    //   1244	1284	1093	com/tapjoy/TapjoyIntegrationException
    //   1287	1320	1093	com/tapjoy/TapjoyIntegrationException
    //   1320	1339	1093	com/tapjoy/TapjoyIntegrationException
    //   1344	1356	1093	com/tapjoy/TapjoyIntegrationException
    //   1361	1406	1093	com/tapjoy/TapjoyIntegrationException
    //   1406	1425	1093	com/tapjoy/TapjoyIntegrationException
    //   1428	1440	1093	com/tapjoy/TapjoyIntegrationException
    //   1443	1452	1093	com/tapjoy/TapjoyIntegrationException
    //   1456	1482	1093	com/tapjoy/TapjoyIntegrationException
    //   1486	1512	1093	com/tapjoy/TapjoyIntegrationException
    //   1515	1532	1093	com/tapjoy/TapjoyIntegrationException
    //   1535	1552	1093	com/tapjoy/TapjoyIntegrationException
    //   1556	1582	1093	com/tapjoy/TapjoyIntegrationException
    //   260	299	1127	java/lang/Exception
    //   61	77	1157	com/tapjoy/TapjoyException
    //   77	105	1157	com/tapjoy/TapjoyException
    //   105	136	1157	com/tapjoy/TapjoyException
    //   136	154	1157	com/tapjoy/TapjoyException
    //   154	169	1157	com/tapjoy/TapjoyException
    //   169	186	1157	com/tapjoy/TapjoyException
    //   190	199	1157	com/tapjoy/TapjoyException
    //   199	218	1157	com/tapjoy/TapjoyException
    //   218	260	1157	com/tapjoy/TapjoyException
    //   260	299	1157	com/tapjoy/TapjoyException
    //   299	306	1157	com/tapjoy/TapjoyException
    //   310	323	1157	com/tapjoy/TapjoyException
    //   327	332	1157	com/tapjoy/TapjoyException
    //   336	345	1157	com/tapjoy/TapjoyException
    //   349	366	1157	com/tapjoy/TapjoyException
    //   366	379	1157	com/tapjoy/TapjoyException
    //   383	403	1157	com/tapjoy/TapjoyException
    //   408	427	1157	com/tapjoy/TapjoyException
    //   427	446	1157	com/tapjoy/TapjoyException
    //   446	453	1157	com/tapjoy/TapjoyException
    //   457	487	1157	com/tapjoy/TapjoyException
    //   487	537	1157	com/tapjoy/TapjoyException
    //   539	586	1157	com/tapjoy/TapjoyException
    //   590	594	1157	com/tapjoy/TapjoyException
    //   594	619	1157	com/tapjoy/TapjoyException
    //   619	648	1157	com/tapjoy/TapjoyException
    //   653	660	1157	com/tapjoy/TapjoyException
    //   664	722	1157	com/tapjoy/TapjoyException
    //   722	734	1157	com/tapjoy/TapjoyException
    //   734	811	1157	com/tapjoy/TapjoyException
    //   811	863	1157	com/tapjoy/TapjoyException
    //   865	869	1157	com/tapjoy/TapjoyException
    //   869	902	1157	com/tapjoy/TapjoyException
    //   902	932	1157	com/tapjoy/TapjoyException
    //   932	991	1157	com/tapjoy/TapjoyException
    //   991	1042	1157	com/tapjoy/TapjoyException
    //   1046	1070	1157	com/tapjoy/TapjoyException
    //   1070	1079	1157	com/tapjoy/TapjoyException
    //   1081	1093	1157	com/tapjoy/TapjoyException
    //   1128	1154	1157	com/tapjoy/TapjoyException
    //   1192	1218	1157	com/tapjoy/TapjoyException
    //   1221	1230	1157	com/tapjoy/TapjoyException
    //   1233	1240	1157	com/tapjoy/TapjoyException
    //   1244	1284	1157	com/tapjoy/TapjoyException
    //   1287	1320	1157	com/tapjoy/TapjoyException
    //   1320	1339	1157	com/tapjoy/TapjoyException
    //   1344	1356	1157	com/tapjoy/TapjoyException
    //   1361	1406	1157	com/tapjoy/TapjoyException
    //   1406	1425	1157	com/tapjoy/TapjoyException
    //   1428	1440	1157	com/tapjoy/TapjoyException
    //   1443	1452	1157	com/tapjoy/TapjoyException
    //   1456	1482	1157	com/tapjoy/TapjoyException
    //   1486	1512	1157	com/tapjoy/TapjoyException
    //   1515	1532	1157	com/tapjoy/TapjoyException
    //   1535	1552	1157	com/tapjoy/TapjoyException
    //   1556	1582	1157	com/tapjoy/TapjoyException
    //   310	323	1191	java/lang/Exception
    //   327	332	1191	java/lang/Exception
    //   336	345	1191	java/lang/Exception
    //   349	366	1191	java/lang/Exception
    //   457	487	1243	java/lang/Exception
    //   487	537	1243	java/lang/Exception
    //   539	586	1243	java/lang/Exception
    //   590	594	1243	java/lang/Exception
    //   594	619	1243	java/lang/Exception
    //   1233	1240	1243	java/lang/Exception
    //   1287	1320	1243	java/lang/Exception
    //   1320	1339	1243	java/lang/Exception
    //   1344	1356	1243	java/lang/Exception
    //   1361	1406	1243	java/lang/Exception
    //   1406	1425	1243	java/lang/Exception
    //   1428	1440	1243	java/lang/Exception
    //   664	722	1455	java/lang/Exception
    //   722	734	1485	java/lang/Exception
    //   811	863	1555	java/lang/Exception
    //   865	869	1555	java/lang/Exception
    //   1515	1532	1555	java/lang/Exception
    //   1535	1552	1555	java/lang/Exception
    //   136	154	1591	java/lang/Exception
  }
  
  private static String a(long paramLong)
  {
    try
    {
      String str = TapjoyUtil.SHA256(x + ":" + q() + ":" + paramLong + ":" + O);
      return str;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error in computing verifier value -- " + localException.toString()));
    }
    return "";
  }
  
  private static String a(long paramLong, String paramString)
  {
    try
    {
      paramString = TapjoyUtil.SHA256(x + ":" + q() + ":" + paramLong + ":" + O + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error in computing packageNamesVerifier -- " + paramString.toString()));
    }
    return "";
  }
  
  private static void a(List paramList)
  {
    try
    {
      ak = "";
      Iterator localIterator = ag.getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (((localApplicationInfo.flags & 0x1) != 1) && (paramList.contains(localApplicationInfo.packageName)))
        {
          TapjoyLog.d("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
          if (ak.length() > 0) {
            ak += ",";
          }
          ak += localApplicationInfo.packageName;
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
    boolean bool2 = false;
    FeatureInfo[] arrayOfFeatureInfo = ag.getSystemAvailableFeatures();
    int i2 = arrayOfFeatureInfo.length;
    int i1 = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i1 < i2)
      {
        if (!arrayOfFeatureInfo[i1].name.matches(paramString1)) {
          break label66;
        }
        bool1 = bool2;
        if (ag.checkPermission(paramString2, g.getPackageName()) == 0) {
          bool1 = true;
        }
      }
      return bool1;
      label66:
      i1 += 1;
    }
  }
  
  /* Error */
  private static boolean a(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 848	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   4: astore 8
    //   6: aload 8
    //   8: astore 9
    //   10: aload 8
    //   12: invokevirtual 851	com/tapjoy/internal/bs:d	()Ljava/util/Map;
    //   15: astore 12
    //   17: aload 8
    //   19: astore 9
    //   21: aload 12
    //   23: ldc_w 853
    //   26: invokeinterface 856 2 0
    //   31: checkcast 326	java/lang/String
    //   34: invokestatic 860	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   37: astore 10
    //   39: aload 8
    //   41: astore 9
    //   43: aload 12
    //   45: ldc_w 862
    //   48: invokeinterface 856 2 0
    //   53: checkcast 326	java/lang/String
    //   56: invokestatic 860	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   59: astore 14
    //   61: aload 8
    //   63: astore 9
    //   65: aload 12
    //   67: ldc_w 864
    //   70: invokeinterface 856 2 0
    //   75: checkcast 326	java/lang/String
    //   78: invokestatic 860	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   81: astore 15
    //   83: aload 8
    //   85: astore 9
    //   87: aload 12
    //   89: ldc_w 866
    //   92: invokeinterface 856 2 0
    //   97: checkcast 326	java/lang/String
    //   100: invokestatic 860	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   103: astore 16
    //   105: aload 8
    //   107: astore 9
    //   109: aload 12
    //   111: ldc_w 868
    //   114: invokeinterface 856 2 0
    //   119: checkcast 326	java/lang/String
    //   122: invokestatic 860	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   125: astore 13
    //   127: aload 8
    //   129: astore 9
    //   131: aload 12
    //   133: ldc_w 870
    //   136: invokeinterface 856 2 0
    //   141: checkcast 678	java/lang/Boolean
    //   144: astore 11
    //   146: aload 8
    //   148: astore 9
    //   150: aload 12
    //   152: ldc_w 872
    //   155: invokeinterface 856 2 0
    //   160: astore 12
    //   162: aload 11
    //   164: ifnull +15 -> 179
    //   167: aload 8
    //   169: astore 9
    //   171: aload 11
    //   173: invokevirtual 875	java/lang/Boolean:booleanValue	()Z
    //   176: putstatic 277	com/tapjoy/TapjoyConnectCore:aU	Z
    //   179: aload 8
    //   181: astore 9
    //   183: new 877	com/tapjoy/internal/em
    //   186: dup
    //   187: aload 15
    //   189: invokespecial 878	com/tapjoy/internal/em:<init>	(Ljava/lang/String;)V
    //   192: astore 17
    //   194: aload 8
    //   196: astore 9
    //   198: aload 17
    //   200: getfield 881	com/tapjoy/internal/em:a	Lcom/tapjoy/internal/em$a;
    //   203: getstatic 886	com/tapjoy/internal/em$a:RPC_ANALYTICS	Lcom/tapjoy/internal/em$a;
    //   206: if_acmpeq +44 -> 250
    //   209: aload 8
    //   211: astore 9
    //   213: new 839	java/io/IOException
    //   216: dup
    //   217: ldc_w 888
    //   220: invokespecial 889	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   223: athrow
    //   224: astore 9
    //   226: aload 8
    //   228: astore_0
    //   229: aload 9
    //   231: astore 8
    //   233: ldc_w 491
    //   236: aload 8
    //   238: invokevirtual 890	java/io/IOException:getMessage	()Ljava/lang/String;
    //   241: invokestatic 892	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   244: aload_0
    //   245: invokestatic 897	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   248: iconst_0
    //   249: ireturn
    //   250: aload 8
    //   252: astore 9
    //   254: aload 17
    //   256: getfield 899	com/tapjoy/internal/em:b	Ljava/lang/String;
    //   259: astore 11
    //   261: aload 8
    //   263: astore 9
    //   265: aload 11
    //   267: bipush 13
    //   269: ldc_w 901
    //   272: iconst_0
    //   273: bipush 11
    //   275: invokevirtual 905	java/lang/String:regionMatches	(ILjava/lang/String;II)Z
    //   278: ifeq +234 -> 512
    //   281: aload 8
    //   283: astore 9
    //   285: new 907	java/lang/StringBuffer
    //   288: dup
    //   289: invokespecial 908	java/lang/StringBuffer:<init>	()V
    //   292: aload 11
    //   294: iconst_0
    //   295: bipush 8
    //   297: invokevirtual 482	java/lang/String:substring	(II)Ljava/lang/String;
    //   300: invokevirtual 911	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   303: aload 11
    //   305: bipush 24
    //   307: bipush 30
    //   309: invokevirtual 482	java/lang/String:substring	(II)Ljava/lang/String;
    //   312: invokevirtual 911	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   315: aload 11
    //   317: bipush 9
    //   319: bipush 13
    //   321: invokevirtual 482	java/lang/String:substring	(II)Ljava/lang/String;
    //   324: invokevirtual 911	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   327: aload 11
    //   329: bipush 30
    //   331: invokevirtual 485	java/lang/String:substring	(I)Ljava/lang/String;
    //   334: invokevirtual 911	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   337: invokevirtual 912	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   340: astore 11
    //   342: aload 8
    //   344: astore 9
    //   346: aload 17
    //   348: getfield 913	com/tapjoy/internal/em:c	Ljava/lang/String;
    //   351: astore 17
    //   353: aload 10
    //   355: ifnonnull +384 -> 739
    //   358: aload 11
    //   360: astore 10
    //   362: aload 8
    //   364: astore 9
    //   366: invokestatic 918	com/tapjoy/internal/hh:a	()Lcom/tapjoy/internal/hh;
    //   369: getstatic 138	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   372: aload 15
    //   374: ldc_w 415
    //   377: ldc_w 920
    //   380: aload 11
    //   382: aload 17
    //   384: invokevirtual 923	com/tapjoy/internal/hh:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   387: aload 8
    //   389: astore 9
    //   391: aload 10
    //   393: putstatic 240	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   396: aload 8
    //   398: astore 9
    //   400: aload 14
    //   402: putstatic 242	com/tapjoy/TapjoyConnectCore:W	Ljava/lang/String;
    //   405: aload 8
    //   407: astore 9
    //   409: aload 15
    //   411: putstatic 244	com/tapjoy/TapjoyConnectCore:X	Ljava/lang/String;
    //   414: aload 8
    //   416: astore 9
    //   418: aload 16
    //   420: putstatic 246	com/tapjoy/TapjoyConnectCore:Y	Ljava/lang/String;
    //   423: aload 8
    //   425: astore 9
    //   427: new 598	java/util/ArrayList
    //   430: dup
    //   431: invokespecial 924	java/util/ArrayList:<init>	()V
    //   434: astore 10
    //   436: aload 13
    //   438: ifnull +112 -> 550
    //   441: aload 8
    //   443: astore 9
    //   445: aload 13
    //   447: ldc_w 784
    //   450: invokevirtual 928	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   453: astore 11
    //   455: aload 8
    //   457: astore 9
    //   459: aload 11
    //   461: arraylength
    //   462: istore_3
    //   463: iconst_0
    //   464: istore_2
    //   465: iload_2
    //   466: iload_3
    //   467: if_icmpge +83 -> 550
    //   470: aload 8
    //   472: astore 9
    //   474: aload 11
    //   476: iload_2
    //   477: aaload
    //   478: invokevirtual 931	java/lang/String:trim	()Ljava/lang/String;
    //   481: astore 13
    //   483: aload 8
    //   485: astore 9
    //   487: aload 13
    //   489: invokevirtual 478	java/lang/String:length	()I
    //   492: ifle +250 -> 742
    //   495: aload 8
    //   497: astore 9
    //   499: aload 10
    //   501: aload 13
    //   503: invokeinterface 934 2 0
    //   508: pop
    //   509: goto +233 -> 742
    //   512: aload 8
    //   514: astore 9
    //   516: new 936	java/lang/IllegalArgumentException
    //   519: dup
    //   520: ldc_w 938
    //   523: invokespecial 939	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   526: athrow
    //   527: astore_0
    //   528: aload 8
    //   530: astore 9
    //   532: ldc_w 491
    //   535: aload_0
    //   536: invokevirtual 940	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   539: invokestatic 892	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   542: aload 8
    //   544: invokestatic 897	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   547: goto -299 -> 248
    //   550: aload 8
    //   552: astore 9
    //   554: aload 10
    //   556: invokeinterface 943 1 0
    //   561: ifne +12 -> 573
    //   564: aload 8
    //   566: astore 9
    //   568: aload 10
    //   570: invokestatic 945	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   573: aload 8
    //   575: astore 9
    //   577: aload 8
    //   579: invokevirtual 948	com/tapjoy/internal/bs:close	()V
    //   582: iload_1
    //   583: ifne +42 -> 625
    //   586: lconst_0
    //   587: lstore 6
    //   589: aload 12
    //   591: instanceof 326
    //   594: istore_1
    //   595: iload_1
    //   596: ifeq +35 -> 631
    //   599: aload 12
    //   601: checkcast 326	java/lang/String
    //   604: invokevirtual 931	java/lang/String:trim	()Ljava/lang/String;
    //   607: invokestatic 954	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   610: lstore 4
    //   612: lload 4
    //   614: lconst_0
    //   615: lcmp
    //   616: ifgt +42 -> 658
    //   619: invokestatic 960	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   622: invokevirtual 963	com/tapjoy/TapjoyAppSettings:removeConnectResult	()V
    //   625: aconst_null
    //   626: invokestatic 897	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   629: iconst_1
    //   630: ireturn
    //   631: aload 12
    //   633: instanceof 965
    //   636: istore_1
    //   637: lload 6
    //   639: lstore 4
    //   641: iload_1
    //   642: ifeq -30 -> 612
    //   645: aload 12
    //   647: checkcast 965	java/lang/Number
    //   650: invokevirtual 968	java/lang/Number:longValue	()J
    //   653: lstore 4
    //   655: goto -43 -> 612
    //   658: invokestatic 960	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   661: aload_0
    //   662: invokestatic 970	com/tapjoy/TapjoyConnectCore:s	()Ljava/lang/String;
    //   665: lload 4
    //   667: ldc2_w 971
    //   670: lmul
    //   671: invokestatic 976	com/tapjoy/internal/z:b	()J
    //   674: ladd
    //   675: invokevirtual 980	com/tapjoy/TapjoyAppSettings:saveConnectResultAndParams	(Ljava/lang/String;Ljava/lang/String;J)V
    //   678: goto -53 -> 625
    //   681: aconst_null
    //   682: astore_0
    //   683: astore 8
    //   685: goto -452 -> 233
    //   688: astore_0
    //   689: aconst_null
    //   690: astore 9
    //   692: aload 9
    //   694: invokestatic 897	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   697: aload_0
    //   698: athrow
    //   699: astore_0
    //   700: goto -8 -> 692
    //   703: astore 8
    //   705: aload_0
    //   706: astore 9
    //   708: aload 8
    //   710: astore_0
    //   711: goto -19 -> 692
    //   714: astore_0
    //   715: aconst_null
    //   716: astore 8
    //   718: goto -190 -> 528
    //   721: astore 8
    //   723: lload 6
    //   725: lstore 4
    //   727: goto -115 -> 612
    //   730: astore 8
    //   732: lload 6
    //   734: lstore 4
    //   736: goto -124 -> 612
    //   739: goto -377 -> 362
    //   742: iload_2
    //   743: iconst_1
    //   744: iadd
    //   745: istore_2
    //   746: goto -281 -> 465
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	749	0	paramString	String
    //   0	749	1	paramBoolean	boolean
    //   464	282	2	i1	int
    //   462	6	3	i2	int
    //   610	125	4	l1	long
    //   587	146	6	l2	long
    //   4	574	8	localObject1	Object
    //   683	1	8	localIOException1	java.io.IOException
    //   703	6	8	localObject2	Object
    //   716	1	8	localObject3	Object
    //   721	1	8	localNumberFormatException1	NumberFormatException
    //   730	1	8	localNumberFormatException2	NumberFormatException
    //   8	204	9	localObject4	Object
    //   224	6	9	localIOException2	java.io.IOException
    //   252	455	9	localObject5	Object
    //   37	532	10	localObject6	Object
    //   144	331	11	localObject7	Object
    //   15	631	12	localObject8	Object
    //   125	377	13	str1	String
    //   59	342	14	str2	String
    //   81	329	15	str3	String
    //   103	316	16	str4	String
    //   192	191	17	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   10	17	224	java/io/IOException
    //   21	39	224	java/io/IOException
    //   43	61	224	java/io/IOException
    //   65	83	224	java/io/IOException
    //   87	105	224	java/io/IOException
    //   109	127	224	java/io/IOException
    //   131	146	224	java/io/IOException
    //   150	162	224	java/io/IOException
    //   171	179	224	java/io/IOException
    //   183	194	224	java/io/IOException
    //   198	209	224	java/io/IOException
    //   213	224	224	java/io/IOException
    //   254	261	224	java/io/IOException
    //   265	281	224	java/io/IOException
    //   285	342	224	java/io/IOException
    //   346	353	224	java/io/IOException
    //   366	387	224	java/io/IOException
    //   391	396	224	java/io/IOException
    //   400	405	224	java/io/IOException
    //   409	414	224	java/io/IOException
    //   418	423	224	java/io/IOException
    //   427	436	224	java/io/IOException
    //   445	455	224	java/io/IOException
    //   459	463	224	java/io/IOException
    //   474	483	224	java/io/IOException
    //   487	495	224	java/io/IOException
    //   499	509	224	java/io/IOException
    //   516	527	224	java/io/IOException
    //   554	564	224	java/io/IOException
    //   568	573	224	java/io/IOException
    //   577	582	224	java/io/IOException
    //   10	17	527	java/lang/RuntimeException
    //   21	39	527	java/lang/RuntimeException
    //   43	61	527	java/lang/RuntimeException
    //   65	83	527	java/lang/RuntimeException
    //   87	105	527	java/lang/RuntimeException
    //   109	127	527	java/lang/RuntimeException
    //   131	146	527	java/lang/RuntimeException
    //   150	162	527	java/lang/RuntimeException
    //   171	179	527	java/lang/RuntimeException
    //   183	194	527	java/lang/RuntimeException
    //   198	209	527	java/lang/RuntimeException
    //   213	224	527	java/lang/RuntimeException
    //   254	261	527	java/lang/RuntimeException
    //   265	281	527	java/lang/RuntimeException
    //   285	342	527	java/lang/RuntimeException
    //   346	353	527	java/lang/RuntimeException
    //   366	387	527	java/lang/RuntimeException
    //   391	396	527	java/lang/RuntimeException
    //   400	405	527	java/lang/RuntimeException
    //   409	414	527	java/lang/RuntimeException
    //   418	423	527	java/lang/RuntimeException
    //   427	436	527	java/lang/RuntimeException
    //   445	455	527	java/lang/RuntimeException
    //   459	463	527	java/lang/RuntimeException
    //   474	483	527	java/lang/RuntimeException
    //   487	495	527	java/lang/RuntimeException
    //   499	509	527	java/lang/RuntimeException
    //   516	527	527	java/lang/RuntimeException
    //   554	564	527	java/lang/RuntimeException
    //   568	573	527	java/lang/RuntimeException
    //   577	582	527	java/lang/RuntimeException
    //   0	6	681	java/io/IOException
    //   589	595	681	java/io/IOException
    //   599	612	681	java/io/IOException
    //   619	625	681	java/io/IOException
    //   631	637	681	java/io/IOException
    //   645	655	681	java/io/IOException
    //   658	678	681	java/io/IOException
    //   0	6	688	finally
    //   589	595	688	finally
    //   599	612	688	finally
    //   619	625	688	finally
    //   631	637	688	finally
    //   645	655	688	finally
    //   658	678	688	finally
    //   10	17	699	finally
    //   21	39	699	finally
    //   43	61	699	finally
    //   65	83	699	finally
    //   87	105	699	finally
    //   109	127	699	finally
    //   131	146	699	finally
    //   150	162	699	finally
    //   171	179	699	finally
    //   183	194	699	finally
    //   198	209	699	finally
    //   213	224	699	finally
    //   254	261	699	finally
    //   265	281	699	finally
    //   285	342	699	finally
    //   346	353	699	finally
    //   366	387	699	finally
    //   391	396	699	finally
    //   400	405	699	finally
    //   409	414	699	finally
    //   418	423	699	finally
    //   427	436	699	finally
    //   445	455	699	finally
    //   459	463	699	finally
    //   474	483	699	finally
    //   487	495	699	finally
    //   499	509	699	finally
    //   516	527	699	finally
    //   532	542	699	finally
    //   554	564	699	finally
    //   568	573	699	finally
    //   577	582	699	finally
    //   233	244	703	finally
    //   0	6	714	java/lang/RuntimeException
    //   589	595	714	java/lang/RuntimeException
    //   599	612	714	java/lang/RuntimeException
    //   619	625	714	java/lang/RuntimeException
    //   631	637	714	java/lang/RuntimeException
    //   645	655	714	java/lang/RuntimeException
    //   658	678	714	java/lang/RuntimeException
    //   645	655	721	java/lang/NumberFormatException
    //   599	612	730	java/lang/NumberFormatException
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
    aj.put(paramString1, str);
  }
  
  /* Error */
  private static boolean d(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 848	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 1026	com/tapjoy/internal/bs:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 1028	com/tapjoy/internal/bs:s	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 491
    //   25: ldc_w 1030
    //   28: invokestatic 508	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 948	com/tapjoy/internal/bs:close	()V
    //   37: aconst_null
    //   38: invokestatic 897	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 948	com/tapjoy/internal/bs:close	()V
    //   49: aconst_null
    //   50: invokestatic 897	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   53: ldc_w 491
    //   56: new 510	com/tapjoy/TapjoyErrorMessage
    //   59: dup
    //   60: getstatic 516	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   63: ldc_w 1032
    //   66: invokespecial 521	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   69: invokestatic 524	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   72: iconst_0
    //   73: ireturn
    //   74: astore_2
    //   75: aconst_null
    //   76: astore_1
    //   77: aload_1
    //   78: astore_0
    //   79: ldc_w 491
    //   82: aload_2
    //   83: invokevirtual 890	java/io/IOException:getMessage	()Ljava/lang/String;
    //   86: invokestatic 892	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload_1
    //   90: invokestatic 897	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   93: goto -40 -> 53
    //   96: astore_2
    //   97: aconst_null
    //   98: astore_1
    //   99: aload_1
    //   100: astore_0
    //   101: ldc_w 491
    //   104: aload_2
    //   105: invokevirtual 940	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   108: invokestatic 892	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   111: aload_1
    //   112: invokestatic 897	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   115: goto -62 -> 53
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_0
    //   121: aload_0
    //   122: invokestatic 897	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   125: aload_1
    //   126: athrow
    //   127: astore_1
    //   128: goto -7 -> 121
    //   131: astore_2
    //   132: goto -33 -> 99
    //   135: astore_2
    //   136: goto -59 -> 77
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	paramString	String
    //   4	108	1	localBs	com.tapjoy.internal.bs
    //   118	8	1	localObject1	Object
    //   127	1	1	localObject2	Object
    //   74	9	2	localIOException1	java.io.IOException
    //   96	9	2	localRuntimeException1	RuntimeException
    //   131	1	2	localRuntimeException2	RuntimeException
    //   135	1	2	localIOException2	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   0	5	74	java/io/IOException
    //   0	5	96	java/lang/RuntimeException
    //   0	5	118	finally
    //   7	14	127	finally
    //   16	20	127	finally
    //   22	31	127	finally
    //   33	37	127	finally
    //   45	49	127	finally
    //   79	89	127	finally
    //   101	111	127	finally
    //   7	14	131	java/lang/RuntimeException
    //   16	20	131	java/lang/RuntimeException
    //   22	31	131	java/lang/RuntimeException
    //   33	37	131	java/lang/RuntimeException
    //   45	49	131	java/lang/RuntimeException
    //   7	14	135	java/io/IOException
    //   16	20	135	java/io/IOException
    //   22	31	135	java/io/IOException
    //   33	37	135	java/io/IOException
    //   45	49	135	java/io/IOException
  }
  
  private static boolean e(String paramString)
  {
    Iterator localIterator = ag.getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.startsWith(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private static void f()
  {
    if (!cs.c(P)) {
      hh.a().a(g, h, "11.7.0", "https://rpc.tapjoy.com/", P, O);
    }
    if (k != null) {
      k.onConnectFailure();
    }
  }
  
  private static boolean f(String paramString)
  {
    return ag.checkPermission(paramString, g.getPackageName()) == 0;
  }
  
  private static Map g()
  {
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "plugin", Q, true);
    TapjoyUtil.safePut(localHashMap3, "sdk_type", R, true);
    TapjoyUtil.safePut(localHashMap3, "app_id", x, true);
    TapjoyUtil.safePut(localHashMap3, "library_version", z, true);
    TapjoyUtil.safePut(localHashMap3, "library_revision", "99b668c", true);
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
    TapjoyUtil.safePut(localHashMap3, "store_view", String.valueOf(U), true);
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
    if (n())
    {
      TapjoyUtil.safePut(localHashMap3, "advertising_id", c, true);
      TapjoyUtil.safePut(localHashMap3, "ad_tracking_enabled", String.valueOf(d), true);
    }
    if (!o())
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
    if ((o == null) || (o.length() == 0) || (System.currentTimeMillis() - ad > 1800000L)) {
      o = p();
    }
    for (;;)
    {
      TapjoyUtil.safePut(localHashMap3, "session_id", o, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "app_group_id", V, true);
      TapjoyUtil.safePut(localHashMap3, "store", W, true);
      TapjoyUtil.safePut(localHashMap3, "analytics_api_key", X, true);
      TapjoyUtil.safePut(localHashMap3, "managed_device_id", Y, true);
      localHashMap2.putAll(localHashMap3);
      if ((TapjoyCache.getInstance() != null) && (TapjoyCache.getInstance().getCachedOfferIDs() != null) && (TapjoyCache.getInstance().getCachedOfferIDs().length() > 0)) {
        TapjoyUtil.safePut(localHashMap2, "cached_ids", TapjoyCache.getInstance().getCachedOfferIDs(), true);
      }
      TapjoyUtil.safePut(localHashMap2, "display_multiplier", Float.toString(T), true);
      localHashMap1.putAll(localHashMap2);
      localHashMap2 = new HashMap();
      i();
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "analytics_id", am, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_id", an, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_sign", ao, true);
      TapjoyUtil.safePut(localHashMap3, "display_d", aO, true);
      TapjoyUtil.safePut(localHashMap3, "display_w", aP, true);
      TapjoyUtil.safePut(localHashMap3, "display_h", aQ, true);
      TapjoyUtil.safePut(localHashMap3, "country_sim", aR, true);
      TapjoyUtil.safePut(localHashMap3, "timezone", aS, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "pkg_ver", ap, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_rev", aq, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_data_ver", ar, true);
      TapjoyUtil.safePut(localHashMap3, "installer", as, true);
      if (cs.c(N)) {
        TapjoyUtil.safePut(localHashMap3, "store_name", aT, true);
      }
      localHashMap2.putAll(localHashMap3);
      localHashMap2.putAll(h());
      localHashMap1.putAll(localHashMap2);
      return localHashMap1;
      ad = System.currentTimeMillis();
    }
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
      paramString = TapjoyUtil.SHA256(x + ":" + q() + ":" + paramLong + ":" + O + ":" + paramInt + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error in computing awardCurrencyVerifier -- " + paramString.toString()));
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
    if (aj != null)
    {
      str1 = str2;
      if (aj.get(paramString) != null) {
        str1 = aj.get(paramString).toString();
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
        TapjoyLog.d("TapjoyConnect", "connection_sub_type: " + (String)localObject);
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
          TapjoyLog.d("TapjoyConnect", "connectivity: " + localConnectivityManager.getActiveNetworkInfo().getType());
          str3 = str1;
          TapjoyLog.d("TapjoyConnect", "connection_type: " + str1);
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
    Map localMap = g();
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
  
  public static String getPlacementURL()
  {
    return getConnectFlagValue("TJC_OPTION_PLACEMENT_SERVICE_URL");
  }
  
  public static String getRedirectDomain()
  {
    return S;
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
  
  private static Map h()
  {
    HashMap localHashMap = new HashMap();
    TapjoyUtil.safePut(localHashMap, "installed", at, true);
    TapjoyUtil.safePut(localHashMap, "referrer", au, true);
    TapjoyUtil.safePut(localHashMap, "user_level", av, true);
    TapjoyUtil.safePut(localHashMap, "friend_count", aw, true);
    TapjoyUtil.safePut(localHashMap, "uv1", ax, true);
    TapjoyUtil.safePut(localHashMap, "uv2", ay, true);
    TapjoyUtil.safePut(localHashMap, "uv3", az, true);
    TapjoyUtil.safePut(localHashMap, "uv4", aA, true);
    TapjoyUtil.safePut(localHashMap, "uv5", aB, true);
    Iterator localIterator = aC.iterator();
    int i1 = 0;
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      TapjoyUtil.safePut(localHashMap, "user_tags[" + i1 + "]", str, true);
      i1 += 1;
    }
    TapjoyUtil.safePut(localHashMap, "fq7", aD, true);
    TapjoyUtil.safePut(localHashMap, "fq30", aE, true);
    TapjoyUtil.safePut(localHashMap, "session_total_count", aF, true);
    TapjoyUtil.safePut(localHashMap, "session_total_length", aG, true);
    TapjoyUtil.safePut(localHashMap, "session_last_at", aH, true);
    TapjoyUtil.safePut(localHashMap, "session_last_length", aI, true);
    TapjoyUtil.safePut(localHashMap, "purchase_currency", aJ, true);
    TapjoyUtil.safePut(localHashMap, "purchase_total_count", aK, true);
    TapjoyUtil.safePut(localHashMap, "purchase_total_price", aL, true);
    TapjoyUtil.safePut(localHashMap, "purchase_last_price", aM, true);
    TapjoyUtil.safePut(localHashMap, "purchase_last_at", aN, true);
    return localHashMap;
  }
  
  private static void i()
  {
    hy.n localN = hh.a(g).a(true);
    am = localN.c.h();
    an = localN.c.y();
    ao = localN.c.A();
    aO = localN.c.c;
    aP = localN.c.d;
    aQ = localN.c.e;
    aR = localN.c.E();
    aS = localN.c.w();
    ap = localN.d.f();
    aq = localN.d.c;
    ar = localN.d.i();
    as = localN.d.k();
    aT = localN.d.m();
    at = localN.e.c;
    au = localN.e.g();
    av = localN.e.p;
    aw = localN.e.q;
    ax = localN.e.C();
    ay = localN.e.E();
    az = localN.e.G();
    aA = localN.e.I();
    aB = localN.e.K();
    aC = new HashSet(localN.e.r);
    aD = localN.e.d;
    aE = localN.e.e;
    aF = localN.e.g;
    aG = localN.e.h;
    aH = localN.e.i;
    aI = localN.e.j;
    aJ = localN.e.p();
    aK = localN.e.k;
    aL = localN.e.l;
    aM = localN.e.n;
    aN = localN.e.m;
  }
  
  public static boolean isConnected()
  {
    return af;
  }
  
  public static boolean isFullScreenViewOpen()
  {
    Iterator localIterator = al.values().iterator();
    while (localIterator.hasNext()) {
      switch (((Integer)localIterator.next()).intValue())
      {
      default: 
        break;
      case 1: 
      case 2: 
        return true;
      }
    }
    return false;
  }
  
  public static boolean isUnitTestMode()
  {
    return getConnectFlagValue("unit_test_mode") == "true";
  }
  
  public static boolean isViewOpen()
  {
    return !al.isEmpty();
  }
  
  private static void j()
  {
    TapjoyLog.i("TapjoyConnect", "Connect Flags:");
    TapjoyLog.i("TapjoyConnect", "--------------------");
    Iterator localIterator = aj.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      TapjoyLog.i("TapjoyConnect", "key: " + (String)localEntry.getKey() + ", value: " + Uri.encode(localEntry.getValue().toString()));
    }
    TapjoyLog.i("TapjoyConnect", "hostURL: [" + getConnectFlagValue("TJC_OPTION_SERVICE_URL") + "]");
    TapjoyLog.i("TapjoyConnect", "redirectDomain: [" + S + "]");
    TapjoyLog.i("TapjoyConnect", "--------------------");
  }
  
  private static void k()
  {
    for (;;)
    {
      int i1;
      try
      {
        if (ag == null) {
          break;
        }
        ApplicationInfo localApplicationInfo = ag.getApplicationInfo(g.getPackageName(), 128);
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
              TapjoyLog.d("TapjoyConnect", "Found manifest flag: " + str1 + ", " + str2);
              b(str1, str2);
            }
          }
          else
          {
            TapjoyLog.d("TapjoyConnect", "Metadata successfully loaded");
          }
        }
        else
        {
          TapjoyLog.d("TapjoyConnect", "No metadata present.");
          return;
        }
      }
      catch (Exception localException)
      {
        TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error reading manifest meta-data -- " + localException.toString()));
        return;
      }
      i1 += 1;
    }
  }
  
  private void l()
  {
    Object localObject3;
    for (;;)
    {
      int i1;
      try
      {
        Object localObject1 = Arrays.asList(ag.getPackageInfo(g.getPackageName(), 1).activities);
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
      if ((Build.VERSION.SDK_INT >= 11) && (((ActivityInfo)localObject3).name.equals("com.tapjoy.TJAdUnitActivity")) && ((((ActivityInfo)localObject3).flags & 0x200) != 512)) {
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
    m();
    try
    {
      Object localObject2 = Class.forName("com.tapjoy.TJAdUnitJSBridge");
      if (getConnectFlagValue("TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK") == null) {
        break label665;
      }
    }
    catch (ClassNotFoundException localClassNotFoundException2)
    {
      try
      {
        ((Class)localObject2).getMethod("closeRequested", new Class[] { Boolean.class });
        localObject3 = (String)TapjoyUtil.getResource("mraid.js");
        localObject2 = localObject3;
        if (localObject3 == null) {
          localObject2 = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", g);
        }
        if (localObject2 != null) {
          break label631;
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
    label631:
    if (getConnectFlagValue("TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK").equals("true"))
    {
      TapjoyLog.i("TapjoyConnect", "Skipping integration check for Google Play Services and Advertising ID. Do this only if you do not have access to Google Play Services.");
      return;
    }
    label665:
    this.ai.checkGooglePlayIntegration();
  }
  
  private static void m()
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
      if (!f(str)) {
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
      if (!f(str)) {
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
  
  private static boolean n()
  {
    return (c != null) && (c.length() > 0);
  }
  
  private static boolean o()
  {
    return (n()) && (getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS") != null) && (getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS").equals("true"));
  }
  
  private static String p()
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
        ad = System.currentTimeMillis();
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
  
  private static String q()
  {
    int i2 = 1;
    if (o()) {
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
    if (n()) {
      return c;
    }
    if ((n != null) && (n.length() > 0)) {}
    for (i1 = i2; i1 != 0; i1 = 0) {
      return n;
    }
    TapjoyLog.e("TapjoyConnect", "Error -- no valid device identifier");
    return null;
  }
  
  private void r()
  {
    TapjoyLog.d("TapjoyConnect", "Initializing mediation params");
    try
    {
      Object localObject = (ArrayList)aj.get("TJC_OPTION_MEDIATION_CONFIGS");
      if (localObject != null)
      {
        TapjoyLog.i("TapjoyConnect", "Initializing mediation partners with configs");
        localObject = ((ArrayList)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          new Thread()
          {
            public final void run()
            {
              if (this.a != null)
              {
                this.a.init();
                return;
              }
              TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Failed to cast mediation connect flag into TJMediationNetwork type! Make sure to pass in an ArrayList<TJMediationNetwork> type as your mediation configs."));
            }
          }.run();
        }
      }
      String str;
      return;
    }
    catch (ClassCastException localClassCastException)
    {
      TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Invalid type! Make sure to pass in an ArrayList<TJMediationNetwork> type as your mediation configs."));
      str = getConnectFlagValue("TJC_OPTION_MEDIATION_TIMEOUT");
      if ((str != null) && (!str.isEmpty()))
      {
        TJMediationSettings.getInstance().setTimeout(str);
        TapjoyLog.i("TapjoyConnect", "Setting mediation timeout to " + str + "s");
      }
    }
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString)
  {
    requestTapjoyConnect(paramContext, paramString, null);
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString, Hashtable paramHashtable)
  {
    requestTapjoyConnect(paramContext, paramString, paramHashtable, null);
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString, Hashtable paramHashtable, TJConnectListener paramTJConnectListener)
  {
    em localEm;
    try
    {
      localEm = new em(paramString);
      if (localEm.a != em.a.SDK_ANDROID) {
        throw new IllegalArgumentException("The given API key was not for Android.");
      }
    }
    catch (IllegalArgumentException paramContext)
    {
      throw new TapjoyIntegrationException(paramContext.getMessage());
    }
    h = paramString;
    x = localEm.b;
    O = localEm.c;
    P = localEm.d;
    hh.a(paramContext).j = paramString;
    if (paramHashtable != null) {
      aj.putAll(paramHashtable);
    }
    k = paramTJConnectListener;
    i = new TapjoyConnectCore(paramContext);
  }
  
  private static String s()
  {
    String str1 = x + y + z + c + q + s;
    try
    {
      String str2 = TapjoyUtil.SHA1(str1);
      return str2;
    }
    catch (Exception localException) {}
    return str1;
  }
  
  public static void setPlugin(String paramString)
  {
    Q = paramString;
  }
  
  public static void setSDKType(String paramString)
  {
    R = paramString;
  }
  
  public static void setUserID(String paramString, TJSetUserIDListener paramTJSetUserIDListener)
  {
    E = paramString;
    l = paramTJSetUserIDListener;
    TapjoyLog.d("TapjoyConnect", "URL parameters: " + getURLParams());
    new Thread(new Runnable()
    {
      public final void run()
      {
        TapjoyLog.i("TapjoyConnect", "Setting userID to " + TapjoyConnectCore.d());
        TapjoyHttpURLResponse localTapjoyHttpURLResponse = TapjoyConnectCore.e().getResponseFromURL(TapjoyConnectCore.getHostURL() + "set_publisher_user_id?", TapjoyConnectCore.getURLParams());
        boolean bool = false;
        if (localTapjoyHttpURLResponse.response != null) {
          bool = TapjoyConnectCore.b(localTapjoyHttpURLResponse.response);
        }
        TapjoyConnectCore.a(bool);
      }
    }).start();
  }
  
  public static void setViewShowing(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      al.put("", Integer.valueOf(1));
      return;
    }
    al.clear();
  }
  
  public static void viewDidClose(String paramString)
  {
    al.remove(paramString);
    ep.e.notifyObservers();
  }
  
  public static void viewWillOpen(String paramString, int paramInt)
  {
    al.put(paramString, Integer.valueOf(paramInt));
  }
  
  public void actionComplete(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "actionComplete: " + paramString);
    Map localMap = g();
    TapjoyUtil.safePut(localMap, "app_id", paramString, true);
    localMap.putAll(getTimeStampAndVerifierParams());
    TapjoyLog.d("TapjoyConnect", "PPA URL parameters: " + localMap);
    new Thread(new PPAThread(localMap)).start();
  }
  
  public void appPause()
  {
    this.ac = true;
  }
  
  public void appResume()
  {
    if (this.ac)
    {
      p();
      this.ac = false;
    }
  }
  
  public void callConnect()
  {
    fetchAdvertisingID();
  }
  
  public void completeConnectCall()
  {
    TapjoyLog.d("TapjoyConnect", "starting connect call...");
    Object localObject1 = "https://connect.tapjoy.com/";
    if (getHostURL() != "https://ws.tapjoyads.com/") {
      localObject1 = getHostURL();
    }
    Object localObject3;
    if (!isConnected())
    {
      localObject3 = TapjoyAppSettings.getInstance().getConnectResult(s(), z.b());
      if ((localObject3 != null) && (a((String)localObject3, true)))
      {
        TapjoyLog.i("TapjoyConnect", "Connect using stored connect result");
        af = true;
        r();
        if (k != null) {
          k.onConnectSuccess();
        }
        ep.a.notifyObservers();
      }
    }
    for (int i1 = 1;; i1 = 0)
    {
      localObject1 = j.getResponseFromURL((String)localObject1 + "api/connect/v3.json?", null, null, getURLParams());
      if ((localObject1 != null) && (((TapjoyHttpURLResponse)localObject1).statusCode == 200))
      {
        if (a(((TapjoyHttpURLResponse)localObject1).response, false))
        {
          TapjoyLog.i("TapjoyConnect", "Successfully connected to Tapjoy");
          af = true;
          if (aU) {
            if (Build.VERSION.SDK_INT <= 8) {}
          }
          for (;;)
          {
            try
            {
              doProfileAsync();
              r();
              localObject1 = getGenericURLParams().entrySet().iterator();
              if (!((Iterator)localObject1).hasNext()) {
                break;
              }
              localObject3 = (Map.Entry)((Iterator)localObject1).next();
              TapjoyLog.d("TapjoyConnect", (String)((Map.Entry)localObject3).getKey() + ": " + (String)((Map.Entry)localObject3).getValue());
              continue;
            }
            catch (Exception localException)
            {
              TapjoyLog.d("TapjoyConnect", "Error building Threatmetrix profile: " + localException.toString());
              continue;
            }
            TapjoyLog.d("TapjoyConnect", "TM disabled");
          }
          if (i1 == 0)
          {
            if (k != null) {
              k.onConnectSuccess();
            }
            ep.a.notifyObservers();
          }
          ep.b.notifyObservers(Boolean.TRUE);
        }
        for (;;)
        {
          if (ak.length() > 0)
          {
            Object localObject2 = getGenericURLParams();
            TapjoyUtil.safePut((Map)localObject2, "package_names", ak, true);
            long l1 = System.currentTimeMillis() / 1000L;
            localObject3 = a(l1, ak);
            TapjoyUtil.safePut((Map)localObject2, "timestamp", String.valueOf(l1), true);
            TapjoyUtil.safePut((Map)localObject2, "verifier", (String)localObject3, true);
            localObject2 = new TapjoyURLConnection().getResponseFromURL(getHostURL() + "apps_installed?", (Map)localObject2);
            if ((localObject2 != null) && (((TapjoyHttpURLResponse)localObject2).statusCode == 200)) {
              TapjoyLog.d("TapjoyConnect", "Successfully pinged sdkless api.");
            }
          }
          return;
          if (i1 == 0) {
            f();
          }
          ep.b.notifyObservers(Boolean.FALSE);
        }
      }
      if (i1 == 0) {
        f();
      }
      ep.b.notifyObservers(Boolean.FALSE);
      return;
    }
  }
  
  public void doProfileAsync()
  {
    boolean bool = true;
    if (g == null)
    {
      TapjoyLog.d("TapjoyConnect", "Error retrieving Threatmetrix sesson ID. Context is null");
      return;
    }
    gn localGn;
    fg localFg;
    try
    {
      TapjoyLog.d("TapjoyConnect", "Initializing Threatmetrix: 3.2-100");
      this.ah = new gn("rrx68giz");
      localGn = this.ah;
      localFg = new fg();
      localFg.a = 10;
      localFg.i = g;
      localFg.q = true;
      localFg.t = false;
      localFg.s = "content-js.tapjoy.com";
      localFg.c = new fj()
      {
        public final void a(fz paramAnonymousFz)
        {
          if (paramAnonymousFz.b == gm.b) {
            TapjoyConnectCore.a(paramAnonymousFz.a);
          }
          for (;;)
          {
            paramAnonymousFz = TapjoyConnectCore.a(TapjoyConnectCore.this);
            paramAnonymousFz.a();
            paramAnonymousFz.t.a();
            paramAnonymousFz.d.a(paramAnonymousFz.g);
            if (paramAnonymousFz.e != null)
            {
              paramAnonymousFz.e.a(gn.b);
              paramAnonymousFz.e = null;
            }
            if (paramAnonymousFz.r != null) {
              paramAnonymousFz.r.a(false);
            }
            gc.b();
            paramAnonymousFz = paramAnonymousFz.d;
            paramAnonymousFz.a.writeLock().lock();
            try
            {
              paramAnonymousFz.b = false;
              paramAnonymousFz.c = false;
              paramAnonymousFz.f = false;
              paramAnonymousFz.e = false;
              paramAnonymousFz.d = false;
              return;
            }
            finally
            {
              paramAnonymousFz.a.writeLock().unlock();
            }
            TapjoyLog.d("TapjoyConnect", "Error retrieving Threatmetrix session ID. Status=" + paramAnonymousFz.b);
          }
        }
      };
      if (localFg.i == null) {
        throw new IllegalArgumentException("Invalid Context");
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      TapjoyLog.d("TapjoyConnect", "Exception caught from running Threatmetrix. -- " + localIllegalArgumentException.getMessage());
      return;
    }
    Object localObject1;
    go localGo;
    if (localGn.d.d())
    {
      localGn.n = localFg.i.getApplicationContext();
      localGn.c.J = localGn.n;
      localObject1 = localGn.f;
      int i2 = localFg.h;
      int i1 = i2;
      if (localFg.o) {
        i1 = i2 & 0xFFFFFFD9;
      }
      i2 = i1;
      if (localFg.p) {
        i2 = i1 & 0xCFFF;
      }
      ((AtomicLong)localObject1).set(i2);
      localGn.c.O = localGn.f.get();
      localGn.g = (localFg.a * 1000);
      localGn.c.z = localFg.b;
      localGn.q = localFg.c;
      if (!localGn.c.a(localFg.s))
      {
        localGn.d.e();
        throw new IllegalArgumentException("Invalid Profile Server");
      }
      if (localFg.d)
      {
        localObject1 = localGn.t;
        localObject2 = localGn.n;
        long l1 = localFg.e;
        long l2 = localFg.f;
        i1 = localFg.g;
        ((gk)localObject1).f = ((Context)localObject2);
        ((gk)localObject1).c = l1;
        ((gk)localObject1).d = l2;
        ((gk)localObject1).e = i1;
        ((gk)localObject1).b();
      }
      localGn.k = localFg.j;
      localGn.j = localFg.k;
      localGn.i = localFg.m;
      localGn.h = localFg.l;
      localGn.m = localFg.n;
      if (localFg.r) {
        break label645;
      }
      localGn.l = bool;
      Object localObject2 = localGn.n.getPackageName();
      localObject1 = localGn.c.w;
      if (localGn.o == null) {
        localGn.o = ((String)localObject2 + "TDM" + (String)localObject1);
      }
      localGo = localGn.c;
      if (localObject2 == null) {
        break label650;
      }
      localObject1 = localObject2;
      if (((String)localObject2).isEmpty()) {
        break label650;
      }
    }
    for (;;)
    {
      localGo.t = ("http://" + (String)localObject1);
      localGo.p = ("http://" + (String)localObject1 + "/mobile");
      new Thread(new gn.1(localGn, localGn, localFg)).start();
      this.ah.a(new fy());
      return;
      label645:
      bool = false;
      break;
      label650:
      localObject1 = "TrustDefenderMobileSDK";
    }
  }
  
  public void enablePaidAppWithActionID(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "enablePaidAppWithActionID: " + paramString);
    Z = paramString;
    this.aa = g.getSharedPreferences("tjcPrefrences", 0).getLong("tapjoy_elapsed_time", 0L);
    TapjoyLog.d("TapjoyConnect", "paidApp elapsed: " + this.aa);
    if (this.aa >= 900000L) {
      if ((Z != null) && (Z.length() > 0))
      {
        TapjoyLog.d("TapjoyConnect", "Calling PPA actionComplete...");
        actionComplete(Z);
      }
    }
    while (this.ab != null) {
      return;
    }
    this.ab = new Timer();
    this.ab.schedule(new a((byte)0), 10000L, 10000L);
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
    return T;
  }
  
  /* Error */
  public String getSerial()
  {
    // Byte code:
    //   0: ldc_w 1998
    //   3: invokestatic 1575	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 2000
    //   9: invokevirtual 2004	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 2009	java/lang/reflect/Field:isAccessible	()Z
    //   17: ifne +8 -> 25
    //   20: aload_1
    //   21: iconst_1
    //   22: invokevirtual 2012	java/lang/reflect/Field:setAccessible	(Z)V
    //   25: aload_1
    //   26: ldc_w 402
    //   29: invokevirtual 2013	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: invokevirtual 1236	java/lang/Object:toString	()Ljava/lang/String;
    //   35: astore_1
    //   36: ldc_w 491
    //   39: new 493	java/lang/StringBuilder
    //   42: dup
    //   43: ldc_w 2015
    //   46: invokespecial 498	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   49: aload_1
    //   50: invokevirtual 502	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 505	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 508	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload_1
    //   60: areturn
    //   61: astore_2
    //   62: aconst_null
    //   63: astore_1
    //   64: ldc_w 491
    //   67: aload_2
    //   68: invokevirtual 691	java/lang/Exception:toString	()Ljava/lang/String;
    //   71: invokestatic 693	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
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
    return this.ae;
  }
  
  public void release()
  {
    i = null;
    j = null;
    TapjoyLog.d("TapjoyConnect", "Releasing core static instance.");
  }
  
  public void setCurrencyMultiplier(float paramFloat)
  {
    TapjoyLog.i("TapjoyConnect", "setVirtualCurrencyMultiplier: " + paramFloat);
    T = paramFloat;
  }
  
  public class PPAThread
    implements Runnable
  {
    private Map b;
    
    public PPAThread(Map paramMap)
    {
      this.b = paramMap;
    }
    
    public void run()
    {
      TapjoyHttpURLResponse localTapjoyHttpURLResponse = TapjoyConnectCore.e().getResponseFromURL(TapjoyConnectCore.getHostURL() + "api/connect/v3.json?", null, null, this.b);
      if (localTapjoyHttpURLResponse.response != null) {
        TapjoyConnectCore.c(localTapjoyHttpURLResponse.response);
      }
    }
  }
  
  final class a
    extends TimerTask
  {
    private a() {}
    
    public final void run()
    {
      TapjoyConnectCore.c(TapjoyConnectCore.this);
      TapjoyLog.d("TapjoyConnect", "elapsed_time: " + TapjoyConnectCore.d(TapjoyConnectCore.this) + " (" + TapjoyConnectCore.d(TapjoyConnectCore.this) / 1000L / 60L + "m " + TapjoyConnectCore.d(TapjoyConnectCore.this) / 1000L % 60L + "s)");
      SharedPreferences.Editor localEditor = TapjoyConnectCore.b().getSharedPreferences("tjcPrefrences", 0).edit();
      localEditor.putLong("tapjoy_elapsed_time", TapjoyConnectCore.d(TapjoyConnectCore.this));
      localEditor.commit();
      if (TapjoyConnectCore.d(TapjoyConnectCore.this) >= 900000L)
      {
        TapjoyLog.d("TapjoyConnect", "timer done...");
        if ((TapjoyConnectCore.c() != null) && (TapjoyConnectCore.c().length() > 0))
        {
          TapjoyLog.d("TapjoyConnect", "Calling PPA actionComplete...");
          TapjoyConnectCore.this.actionComplete(TapjoyConnectCore.c());
        }
        cancel();
      }
    }
  }
}
