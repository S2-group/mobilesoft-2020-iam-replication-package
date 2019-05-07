package com.tapjoy;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.tapjoy.internal.ct;
import com.tapjoy.internal.dx;
import com.tapjoy.internal.ed;
import com.tapjoy.internal.ee;
import com.tapjoy.internal.ek;
import com.tapjoy.internal.er;
import com.tapjoy.internal.er.a;
import com.tapjoy.internal.ev;
import com.tapjoy.internal.ev.a;
import com.tapjoy.internal.fb;
import com.tapjoy.internal.fd;
import com.tapjoy.internal.gd;
import com.tapjoy.internal.y;
import com.tapjoy.mediation.TJMediationNetwork;
import com.tapjoy.mediation.TJMediationSettings;
import java.lang.reflect.Field;
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
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class TapjoyConnectCore
{
  private static int A = 0;
  private static float B = 0.0F;
  private static int C = 0;
  private static String D;
  public static final float DEFAULT_CURRENCY_MULTIPLIER = 1.0F;
  private static boolean E;
  private static String F;
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
  private static float S;
  private static boolean T;
  private static String U;
  private static String V;
  private static String W;
  private static String X;
  private static String Y;
  protected static int a;
  private static Integer aA;
  private static Integer aB;
  private static Integer aC;
  private static Long aD;
  private static Long aE;
  private static Long aF;
  private static String aG;
  private static Integer aH;
  private static Double aI;
  private static Double aJ;
  private static Long aK;
  private static Integer aL;
  private static Integer aM;
  private static Integer aN;
  private static String aO;
  private static String aP;
  private static String aQ;
  private static long ab;
  private static boolean ad;
  private static PackageManager ae;
  private static Hashtable ag = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
  private static String ah = "";
  private static Map ai = new ConcurrentHashMap();
  private static String aj;
  private static String ak;
  private static String al;
  private static String am;
  private static Integer an;
  private static String ao;
  private static String ap;
  private static Long aq;
  private static String ar;
  private static Integer as;
  private static Integer at;
  private static String au;
  private static String av;
  private static String aw;
  private static String ax;
  private static String ay;
  private static Set az;
  protected static int b;
  protected static String c;
  protected static boolean d;
  protected static String e;
  protected static String f;
  private static Context g;
  private static String h;
  private static TapjoyConnectCore i;
  private static TapjoyURLConnection j;
  private static TJConnectListener k;
  private static TJSetUserIDListener l;
  private static Vector m = new Vector(Arrays.asList(TapjoyConstants.dependencyClassNames));
  private static String n = "";
  private static String o = "";
  private static String p = "";
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
  private long Z;
  private boolean aa;
  private boolean ac;
  private TapjoyGpsHelper af;
  
  static
  {
    A = 1;
    B = 1.0F;
    C = 1;
    D = "";
    E = false;
    F = "";
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
    U = "";
    V = "";
    W = "";
    X = "";
    Y = null;
    ab = 0L;
    a = 0;
    b = 0;
    c = "";
    e = "";
    f = "";
  }
  
  /* Error */
  public TapjoyConnectCore(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 258	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 260	com/tapjoy/TapjoyConnectCore:Z	J
    //   9: iconst_0
    //   10: istore_3
    //   11: aload_0
    //   12: iconst_0
    //   13: putfield 262	com/tapjoy/TapjoyConnectCore:aa	Z
    //   16: aload_0
    //   17: iconst_0
    //   18: putfield 264	com/tapjoy/TapjoyConnectCore:ac	Z
    //   21: aload_1
    //   22: putstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   25: invokestatic 271	com/tapjoy/internal/fd:a	()Lcom/tapjoy/internal/fd;
    //   28: aload_1
    //   29: invokevirtual 273	com/tapjoy/internal/fd:a	(Landroid/content/Context;)V
    //   32: new 275	com/tapjoy/TapjoyURLConnection
    //   35: dup
    //   36: invokespecial 276	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   39: putstatic 278	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   42: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   45: invokevirtual 284	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   48: putstatic 286	com/tapjoy/TapjoyConnectCore:ae	Landroid/content/pm/PackageManager;
    //   51: aload_0
    //   52: new 288	com/tapjoy/TapjoyGpsHelper
    //   55: dup
    //   56: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   59: invokespecial 290	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   62: putfield 292	com/tapjoy/TapjoyConnectCore:af	Lcom/tapjoy/TapjoyGpsHelper;
    //   65: getstatic 239	com/tapjoy/TapjoyConnectCore:ag	Ljava/util/Hashtable;
    //   68: ifnonnull +13 -> 81
    //   71: new 294	java/util/Hashtable
    //   74: dup
    //   75: invokespecial 295	java/util/Hashtable:<init>	()V
    //   78: putstatic 239	com/tapjoy/TapjoyConnectCore:ag	Ljava/util/Hashtable;
    //   81: invokestatic 297	com/tapjoy/TapjoyConnectCore:i	()V
    //   84: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   87: invokevirtual 301	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   90: ldc_w 303
    //   93: aconst_null
    //   94: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   97: invokevirtual 307	android/content/Context:getPackageName	()Ljava/lang/String;
    //   100: invokevirtual 313	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   103: istore_2
    //   104: new 315	java/util/Properties
    //   107: dup
    //   108: invokespecial 316	java/util/Properties:<init>	()V
    //   111: astore_1
    //   112: aload_1
    //   113: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   116: invokevirtual 301	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   119: iload_2
    //   120: invokevirtual 320	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   123: invokevirtual 324	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   126: aload_1
    //   127: invokestatic 327	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   130: ldc_w 329
    //   133: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   136: ldc -114
    //   138: if_acmpne +7 -> 145
    //   141: aload_0
    //   142: invokespecial 335	com/tapjoy/TapjoyConnectCore:j	()V
    //   145: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   148: invokevirtual 339	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   151: ldc_w 341
    //   154: invokestatic 347	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   157: astore_1
    //   158: aload_1
    //   159: putstatic 144	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   162: aload_1
    //   163: ifnull +12 -> 175
    //   166: getstatic 144	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   169: invokevirtual 352	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   172: putstatic 144	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   175: getstatic 286	com/tapjoy/TapjoyConnectCore:ae	Landroid/content/pm/PackageManager;
    //   178: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   181: invokevirtual 307	android/content/Context:getPackageName	()Ljava/lang/String;
    //   184: iconst_0
    //   185: invokevirtual 358	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   188: getfield 363	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   191: putstatic 164	com/tapjoy/TapjoyConnectCore:x	Ljava/lang/String;
    //   194: ldc_w 365
    //   197: putstatic 158	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   200: ldc_w 365
    //   203: putstatic 180	com/tapjoy/TapjoyConnectCore:F	Ljava/lang/String;
    //   206: getstatic 370	android/os/Build:MODEL	Ljava/lang/String;
    //   209: putstatic 154	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   212: getstatic 373	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   215: putstatic 156	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   218: getstatic 378	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   221: putstatic 160	com/tapjoy/TapjoyConnectCore:v	Ljava/lang/String;
    //   224: ldc_w 380
    //   227: putstatic 166	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   230: ldc_w 382
    //   233: putstatic 168	com/tapjoy/TapjoyConnectCore:z	Ljava/lang/String;
    //   236: getstatic 385	android/os/Build$VERSION:SDK_INT	I
    //   239: iconst_3
    //   240: if_icmple +72 -> 312
    //   243: new 387	com/tapjoy/TapjoyDisplayMetricsUtil
    //   246: dup
    //   247: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   250: invokespecial 388	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   253: astore_1
    //   254: aload_1
    //   255: invokevirtual 392	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   258: putstatic 170	com/tapjoy/TapjoyConnectCore:A	I
    //   261: aload_1
    //   262: invokevirtual 396	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   265: putstatic 172	com/tapjoy/TapjoyConnectCore:B	F
    //   268: aload_1
    //   269: invokevirtual 399	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   272: putstatic 174	com/tapjoy/TapjoyConnectCore:C	I
    //   275: goto +37 -> 312
    //   278: astore_1
    //   279: new 401	java/lang/StringBuilder
    //   282: dup
    //   283: ldc_w 403
    //   286: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   289: astore 5
    //   291: aload 5
    //   293: aload_1
    //   294: invokevirtual 409	java/lang/Exception:toString	()Ljava/lang/String;
    //   297: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: pop
    //   301: ldc_w 415
    //   304: aload 5
    //   306: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   309: invokestatic 421	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   312: ldc_w 423
    //   315: invokestatic 426	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   318: istore 4
    //   320: iload 4
    //   322: ifeq +99 -> 421
    //   325: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   328: ldc_w 428
    //   331: invokevirtual 432	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   334: checkcast 434	android/net/wifi/WifiManager
    //   337: astore_1
    //   338: aload_1
    //   339: ifnull +91 -> 430
    //   342: aload_1
    //   343: invokevirtual 438	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   346: astore_1
    //   347: aload_1
    //   348: ifnull +82 -> 430
    //   351: aload_1
    //   352: invokevirtual 443	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   355: astore_1
    //   356: aload_1
    //   357: putstatic 150	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   360: aload_1
    //   361: ifnull +69 -> 430
    //   364: getstatic 150	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   367: ldc_w 445
    //   370: ldc -114
    //   372: invokevirtual 449	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   375: invokevirtual 352	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   378: putstatic 150	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   381: goto +49 -> 430
    //   384: astore_1
    //   385: new 401	java/lang/StringBuilder
    //   388: dup
    //   389: ldc_w 451
    //   392: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   395: astore 5
    //   397: aload 5
    //   399: aload_1
    //   400: invokevirtual 409	java/lang/Exception:toString	()Ljava/lang/String;
    //   403: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   406: pop
    //   407: ldc_w 415
    //   410: aload 5
    //   412: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   415: invokestatic 421	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   418: goto +12 -> 430
    //   421: ldc_w 415
    //   424: ldc_w 453
    //   427: invokestatic 455	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   430: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   433: ldc_w 457
    //   436: invokevirtual 432	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   439: checkcast 459	android/telephony/TelephonyManager
    //   442: astore_1
    //   443: aload_1
    //   444: ifnull +487 -> 931
    //   447: aload_1
    //   448: invokevirtual 462	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   451: putstatic 182	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   454: aload_1
    //   455: invokevirtual 465	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   458: putstatic 184	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   461: aload_1
    //   462: invokevirtual 468	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   465: astore 5
    //   467: aload 5
    //   469: ifnull +41 -> 510
    //   472: aload 5
    //   474: invokevirtual 471	java/lang/String:length	()I
    //   477: iconst_5
    //   478: if_icmpeq +13 -> 491
    //   481: aload 5
    //   483: invokevirtual 471	java/lang/String:length	()I
    //   486: bipush 6
    //   488: if_icmpne +22 -> 510
    //   491: aload 5
    //   493: iconst_0
    //   494: iconst_3
    //   495: invokevirtual 475	java/lang/String:substring	(II)Ljava/lang/String;
    //   498: putstatic 186	com/tapjoy/TapjoyConnectCore:I	Ljava/lang/String;
    //   501: aload 5
    //   503: iconst_3
    //   504: invokevirtual 478	java/lang/String:substring	(I)Ljava/lang/String;
    //   507: putstatic 188	com/tapjoy/TapjoyConnectCore:J	Ljava/lang/String;
    //   510: ldc_w 480
    //   513: invokestatic 426	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   516: istore 4
    //   518: iload 4
    //   520: ifeq +402 -> 922
    //   523: ldc_w 482
    //   526: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   529: ifnull +27 -> 556
    //   532: ldc_w 482
    //   535: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   538: invokevirtual 471	java/lang/String:length	()I
    //   541: ifle +15 -> 556
    //   544: ldc_w 482
    //   547: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   550: putstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   553: goto +10 -> 563
    //   556: aload_1
    //   557: invokevirtual 485	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   560: putstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   563: new 401	java/lang/StringBuilder
    //   566: dup
    //   567: ldc_w 487
    //   570: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   573: astore_1
    //   574: aload_1
    //   575: getstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   578: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   581: pop
    //   582: ldc_w 415
    //   585: aload_1
    //   586: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   589: invokestatic 455	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   592: getstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   595: ifnonnull +25 -> 620
    //   598: ldc_w 415
    //   601: new 489	com/tapjoy/TapjoyErrorMessage
    //   604: dup
    //   605: getstatic 495	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   608: ldc_w 497
    //   611: invokespecial 500	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   614: invokestatic 503	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   617: goto +1038 -> 1655
    //   620: getstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   623: invokevirtual 471	java/lang/String:length	()I
    //   626: ifeq +47 -> 673
    //   629: getstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   632: ldc_w 505
    //   635: invokevirtual 509	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   638: ifne +35 -> 673
    //   641: getstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   644: ldc_w 511
    //   647: invokevirtual 509	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   650: ifeq +6 -> 656
    //   653: goto +20 -> 673
    //   656: getstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   659: invokestatic 517	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   662: invokevirtual 520	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   665: putstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   668: iconst_1
    //   669: istore_2
    //   670: goto +25 -> 695
    //   673: ldc_w 415
    //   676: new 489	com/tapjoy/TapjoyErrorMessage
    //   679: dup
    //   680: getstatic 495	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   683: ldc_w 522
    //   686: invokespecial 500	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   689: invokestatic 503	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   692: goto +963 -> 1655
    //   695: new 401	java/lang/StringBuilder
    //   698: dup
    //   699: ldc_w 524
    //   702: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   705: astore_1
    //   706: aload_1
    //   707: getstatic 385	android/os/Build$VERSION:SDK_INT	I
    //   710: invokevirtual 527	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   713: pop
    //   714: ldc_w 415
    //   717: aload_1
    //   718: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   721: invokestatic 529	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   724: getstatic 385	android/os/Build$VERSION:SDK_INT	I
    //   727: bipush 9
    //   729: if_icmplt +202 -> 931
    //   732: ldc_w 415
    //   735: ldc_w 531
    //   738: invokestatic 455	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   741: aload_0
    //   742: invokevirtual 534	com/tapjoy/TapjoyConnectCore:getSerial	()Ljava/lang/String;
    //   745: astore_1
    //   746: iload_2
    //   747: ifne +7 -> 754
    //   750: aload_1
    //   751: putstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   754: getstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   757: ifnonnull +25 -> 782
    //   760: ldc_w 415
    //   763: new 489	com/tapjoy/TapjoyErrorMessage
    //   766: dup
    //   767: getstatic 495	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   770: ldc_w 536
    //   773: invokespecial 500	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   776: invokestatic 503	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   779: goto +152 -> 931
    //   782: getstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   785: invokevirtual 471	java/lang/String:length	()I
    //   788: ifeq +57 -> 845
    //   791: getstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   794: ldc_w 505
    //   797: invokevirtual 509	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   800: ifne +45 -> 845
    //   803: getstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   806: ldc_w 511
    //   809: invokevirtual 509	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   812: ifne +33 -> 845
    //   815: getstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   818: ldc_w 538
    //   821: invokevirtual 509	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   824: ifeq +6 -> 830
    //   827: goto +18 -> 845
    //   830: getstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   833: invokestatic 517	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   836: invokevirtual 520	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   839: putstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   842: goto +89 -> 931
    //   845: ldc_w 415
    //   848: new 489	com/tapjoy/TapjoyErrorMessage
    //   851: dup
    //   852: getstatic 495	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   855: ldc_w 540
    //   858: invokespecial 500	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   861: invokestatic 503	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   864: goto +67 -> 931
    //   867: astore_1
    //   868: getstatic 495	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   871: astore 5
    //   873: new 401	java/lang/StringBuilder
    //   876: dup
    //   877: ldc_w 542
    //   880: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   883: astore 6
    //   885: aload 6
    //   887: aload_1
    //   888: invokevirtual 409	java/lang/Exception:toString	()Ljava/lang/String;
    //   891: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   894: pop
    //   895: ldc_w 415
    //   898: new 489	com/tapjoy/TapjoyErrorMessage
    //   901: dup
    //   902: aload 5
    //   904: aload 6
    //   906: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   909: invokespecial 500	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   912: invokestatic 503	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   915: aconst_null
    //   916: putstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   919: goto +12 -> 931
    //   922: ldc_w 415
    //   925: ldc_w 544
    //   928: invokestatic 455	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   931: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   934: ldc_w 546
    //   937: iconst_0
    //   938: invokevirtual 550	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   941: astore_1
    //   942: aload_1
    //   943: ldc_w 552
    //   946: ldc -114
    //   948: invokeinterface 557 3 0
    //   953: astore 5
    //   955: aload 5
    //   957: putstatic 152	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   960: aload 5
    //   962: ifnull +14 -> 976
    //   965: getstatic 152	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   968: invokevirtual 471	java/lang/String:length	()I
    //   971: istore_2
    //   972: iload_2
    //   973: ifne +108 -> 1081
    //   976: new 401	java/lang/StringBuilder
    //   979: dup
    //   980: invokespecial 558	java/lang/StringBuilder:<init>	()V
    //   983: astore 5
    //   985: aload 5
    //   987: invokestatic 564	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   990: invokevirtual 565	java/util/UUID:toString	()Ljava/lang/String;
    //   993: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   996: pop
    //   997: aload 5
    //   999: invokestatic 571	java/lang/System:currentTimeMillis	()J
    //   1002: invokevirtual 574	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1005: pop
    //   1006: aload 5
    //   1008: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1011: invokestatic 579	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   1014: putstatic 152	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   1017: aload_1
    //   1018: invokeinterface 583 1 0
    //   1023: astore_1
    //   1024: aload_1
    //   1025: ldc_w 552
    //   1028: getstatic 152	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   1031: invokeinterface 589 3 0
    //   1036: pop
    //   1037: aload_1
    //   1038: invokeinterface 593 1 0
    //   1043: pop
    //   1044: goto +37 -> 1081
    //   1047: astore_1
    //   1048: new 401	java/lang/StringBuilder
    //   1051: dup
    //   1052: ldc_w 595
    //   1055: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1058: astore 5
    //   1060: aload 5
    //   1062: aload_1
    //   1063: invokevirtual 409	java/lang/Exception:toString	()Ljava/lang/String;
    //   1066: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1069: pop
    //   1070: ldc_w 415
    //   1073: aload 5
    //   1075: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1078: invokestatic 421	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1081: ldc_w 597
    //   1084: ldc_w 599
    //   1087: invokestatic 602	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   1090: putstatic 178	com/tapjoy/TapjoyConnectCore:E	Z
    //   1093: goto +37 -> 1130
    //   1096: astore_1
    //   1097: new 401	java/lang/StringBuilder
    //   1100: dup
    //   1101: ldc_w 604
    //   1104: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1107: astore 5
    //   1109: aload 5
    //   1111: aload_1
    //   1112: invokevirtual 409	java/lang/Exception:toString	()Ljava/lang/String;
    //   1115: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1118: pop
    //   1119: ldc_w 415
    //   1122: aload 5
    //   1124: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1127: invokestatic 421	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1130: ldc_w 606
    //   1133: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1136: ifnull +75 -> 1211
    //   1139: ldc_w 606
    //   1142: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1145: invokevirtual 471	java/lang/String:length	()I
    //   1148: ifle +63 -> 1211
    //   1151: ldc_w 606
    //   1154: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1157: putstatic 194	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   1160: new 608	java/util/ArrayList
    //   1163: dup
    //   1164: getstatic 611	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   1167: invokestatic 134	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   1170: invokespecial 612	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   1173: getstatic 194	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   1176: invokevirtual 615	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   1179: ifne +32 -> 1211
    //   1182: new 401	java/lang/StringBuilder
    //   1185: dup
    //   1186: ldc_w 617
    //   1189: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1192: astore_1
    //   1193: aload_1
    //   1194: getstatic 194	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   1197: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1200: pop
    //   1201: ldc_w 415
    //   1204: aload_1
    //   1205: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1208: invokestatic 619	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   1211: getstatic 194	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   1214: astore_1
    //   1215: new 621	android/content/Intent
    //   1218: dup
    //   1219: ldc_w 623
    //   1222: invokespecial 624	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   1225: astore 5
    //   1227: aload_1
    //   1228: invokevirtual 471	java/lang/String:length	()I
    //   1231: ifgt +37 -> 1268
    //   1234: aload 5
    //   1236: ldc_w 626
    //   1239: invokestatic 632	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   1242: invokevirtual 636	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   1245: pop
    //   1246: getstatic 286	com/tapjoy/TapjoyConnectCore:ae	Landroid/content/pm/PackageManager;
    //   1249: aload 5
    //   1251: iconst_0
    //   1252: invokevirtual 640	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   1255: invokeinterface 645 1 0
    //   1260: ifle +45 -> 1305
    //   1263: iconst_1
    //   1264: istore_3
    //   1265: goto +40 -> 1305
    //   1268: aload_1
    //   1269: ldc_w 647
    //   1272: invokevirtual 509	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1275: ifeq +13 -> 1288
    //   1278: ldc_w 649
    //   1281: invokestatic 651	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1284: istore_3
    //   1285: goto +20 -> 1305
    //   1288: aload_1
    //   1289: ldc_w 653
    //   1292: invokevirtual 509	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1295: ifeq +10 -> 1305
    //   1298: ldc_w 655
    //   1301: invokestatic 651	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1304: istore_3
    //   1305: iload_3
    //   1306: putstatic 210	com/tapjoy/TapjoyConnectCore:T	Z
    //   1309: goto +37 -> 1346
    //   1312: astore_1
    //   1313: new 401	java/lang/StringBuilder
    //   1316: dup
    //   1317: ldc_w 657
    //   1320: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1323: astore 5
    //   1325: aload 5
    //   1327: aload_1
    //   1328: invokevirtual 409	java/lang/Exception:toString	()Ljava/lang/String;
    //   1331: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1334: pop
    //   1335: ldc_w 415
    //   1338: aload 5
    //   1340: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1343: invokestatic 421	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1346: invokestatic 659	com/tapjoy/TapjoyConnectCore:g	()V
    //   1349: ldc_w 661
    //   1352: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1355: ifnull +24 -> 1379
    //   1358: ldc_w 661
    //   1361: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1364: invokevirtual 471	java/lang/String:length	()I
    //   1367: ifle +12 -> 1379
    //   1370: ldc_w 661
    //   1373: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1376: putstatic 232	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   1379: ldc_w 663
    //   1382: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1385: ifnull +24 -> 1409
    //   1388: ldc_w 663
    //   1391: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1394: invokevirtual 471	java/lang/String:length	()I
    //   1397: ifle +12 -> 1409
    //   1400: ldc_w 663
    //   1403: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1406: putstatic 230	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   1409: ldc_w 665
    //   1412: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1415: ifnull +57 -> 1472
    //   1418: ldc_w 665
    //   1421: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1424: invokevirtual 471	java/lang/String:length	()I
    //   1427: ifle +45 -> 1472
    //   1430: new 401	java/lang/StringBuilder
    //   1433: dup
    //   1434: ldc_w 667
    //   1437: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1440: astore_1
    //   1441: aload_1
    //   1442: ldc_w 665
    //   1445: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1448: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1451: pop
    //   1452: ldc_w 415
    //   1455: aload_1
    //   1456: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1459: invokestatic 529	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1462: ldc_w 665
    //   1465: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1468: aconst_null
    //   1469: invokestatic 671	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;Lcom/tapjoy/TJSetUserIDListener;)V
    //   1472: ldc_w 673
    //   1475: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1478: invokestatic 676	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   1481: putstatic 206	com/tapjoy/TapjoyConnectCore:R	Ljava/lang/String;
    //   1484: new 401	java/lang/StringBuilder
    //   1487: dup
    //   1488: ldc_w 487
    //   1491: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1494: astore 5
    //   1496: aload 5
    //   1498: getstatic 148	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1501: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1504: pop
    //   1505: ldc_w 482
    //   1508: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1511: ifnull +149 -> 1660
    //   1514: ldc_w 482
    //   1517: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1520: invokevirtual 471	java/lang/String:length	()I
    //   1523: ifle +137 -> 1660
    //   1526: ldc_w 678
    //   1529: astore_1
    //   1530: goto +3 -> 1533
    //   1533: aload 5
    //   1535: aload_1
    //   1536: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1539: pop
    //   1540: ldc_w 415
    //   1543: aload 5
    //   1545: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1548: invokestatic 455	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1551: getstatic 239	com/tapjoy/TapjoyConnectCore:ag	Ljava/util/Hashtable;
    //   1554: ifnull +6 -> 1560
    //   1557: invokestatic 680	com/tapjoy/TapjoyConnectCore:h	()V
    //   1560: aload_0
    //   1561: invokevirtual 683	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   1564: aload_0
    //   1565: iconst_1
    //   1566: putfield 264	com/tapjoy/TapjoyConnectCore:ac	Z
    //   1569: return
    //   1570: astore_1
    //   1571: new 253	com/tapjoy/TapjoyException
    //   1574: dup
    //   1575: aload_1
    //   1576: invokevirtual 686	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   1579: invokespecial 687	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   1582: athrow
    //   1583: astore_1
    //   1584: ldc_w 415
    //   1587: new 489	com/tapjoy/TapjoyErrorMessage
    //   1590: dup
    //   1591: getstatic 495	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1594: aload_1
    //   1595: invokevirtual 688	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   1598: invokespecial 500	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1601: invokestatic 503	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1604: invokestatic 690	com/tapjoy/TapjoyConnectCore:d	()V
    //   1607: getstatic 695	com/tapjoy/internal/ev:b	Lcom/tapjoy/internal/ev$a;
    //   1610: getstatic 701	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1613: invokevirtual 707	com/tapjoy/internal/ev$a:notifyObservers	(Ljava/lang/Object;)V
    //   1616: return
    //   1617: astore_1
    //   1618: ldc_w 415
    //   1621: new 489	com/tapjoy/TapjoyErrorMessage
    //   1624: dup
    //   1625: getstatic 710	com/tapjoy/TapjoyErrorMessage$ErrorType:INTEGRATION_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1628: aload_1
    //   1629: invokevirtual 711	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   1632: invokespecial 500	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1635: invokestatic 503	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1638: invokestatic 690	com/tapjoy/TapjoyConnectCore:d	()V
    //   1641: getstatic 695	com/tapjoy/internal/ev:b	Lcom/tapjoy/internal/ev$a;
    //   1644: getstatic 701	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1647: invokevirtual 707	com/tapjoy/internal/ev$a:notifyObservers	(Ljava/lang/Object;)V
    //   1650: return
    //   1651: astore_1
    //   1652: goto -1522 -> 130
    //   1655: iconst_0
    //   1656: istore_2
    //   1657: goto -962 -> 695
    //   1660: ldc -114
    //   1662: astore_1
    //   1663: goto -130 -> 1533
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1666	0	this	TapjoyConnectCore
    //   0	1666	1	paramContext	Context
    //   103	1554	2	i1	int
    //   10	1296	3	bool1	boolean
    //   318	201	4	bool2	boolean
    //   289	1255	5	localObject	Object
    //   883	22	6	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   236	275	278	java/lang/Exception
    //   325	338	384	java/lang/Exception
    //   342	347	384	java/lang/Exception
    //   351	360	384	java/lang/Exception
    //   364	381	384	java/lang/Exception
    //   523	553	867	java/lang/Exception
    //   556	563	867	java/lang/Exception
    //   563	617	867	java/lang/Exception
    //   620	653	867	java/lang/Exception
    //   656	668	867	java/lang/Exception
    //   673	692	867	java/lang/Exception
    //   695	746	867	java/lang/Exception
    //   750	754	867	java/lang/Exception
    //   754	779	867	java/lang/Exception
    //   782	827	867	java/lang/Exception
    //   830	842	867	java/lang/Exception
    //   845	864	867	java/lang/Exception
    //   976	1044	1047	java/lang/Exception
    //   1081	1093	1096	java/lang/Exception
    //   1211	1246	1312	java/lang/Exception
    //   1246	1263	1312	java/lang/Exception
    //   1268	1285	1312	java/lang/Exception
    //   1288	1305	1312	java/lang/Exception
    //   1305	1309	1312	java/lang/Exception
    //   175	194	1570	android/content/pm/PackageManager$NameNotFoundException
    //   65	81	1583	com/tapjoy/TapjoyException
    //   81	112	1583	com/tapjoy/TapjoyException
    //   112	130	1583	com/tapjoy/TapjoyException
    //   130	145	1583	com/tapjoy/TapjoyException
    //   145	162	1583	com/tapjoy/TapjoyException
    //   166	175	1583	com/tapjoy/TapjoyException
    //   175	194	1583	com/tapjoy/TapjoyException
    //   194	236	1583	com/tapjoy/TapjoyException
    //   236	275	1583	com/tapjoy/TapjoyException
    //   279	312	1583	com/tapjoy/TapjoyException
    //   312	320	1583	com/tapjoy/TapjoyException
    //   325	338	1583	com/tapjoy/TapjoyException
    //   342	347	1583	com/tapjoy/TapjoyException
    //   351	360	1583	com/tapjoy/TapjoyException
    //   364	381	1583	com/tapjoy/TapjoyException
    //   385	418	1583	com/tapjoy/TapjoyException
    //   421	430	1583	com/tapjoy/TapjoyException
    //   430	443	1583	com/tapjoy/TapjoyException
    //   447	467	1583	com/tapjoy/TapjoyException
    //   472	491	1583	com/tapjoy/TapjoyException
    //   491	510	1583	com/tapjoy/TapjoyException
    //   510	518	1583	com/tapjoy/TapjoyException
    //   523	553	1583	com/tapjoy/TapjoyException
    //   556	563	1583	com/tapjoy/TapjoyException
    //   563	617	1583	com/tapjoy/TapjoyException
    //   620	653	1583	com/tapjoy/TapjoyException
    //   656	668	1583	com/tapjoy/TapjoyException
    //   673	692	1583	com/tapjoy/TapjoyException
    //   695	746	1583	com/tapjoy/TapjoyException
    //   750	754	1583	com/tapjoy/TapjoyException
    //   754	779	1583	com/tapjoy/TapjoyException
    //   782	827	1583	com/tapjoy/TapjoyException
    //   830	842	1583	com/tapjoy/TapjoyException
    //   845	864	1583	com/tapjoy/TapjoyException
    //   868	919	1583	com/tapjoy/TapjoyException
    //   922	931	1583	com/tapjoy/TapjoyException
    //   931	960	1583	com/tapjoy/TapjoyException
    //   965	972	1583	com/tapjoy/TapjoyException
    //   976	1044	1583	com/tapjoy/TapjoyException
    //   1048	1081	1583	com/tapjoy/TapjoyException
    //   1081	1093	1583	com/tapjoy/TapjoyException
    //   1097	1130	1583	com/tapjoy/TapjoyException
    //   1130	1211	1583	com/tapjoy/TapjoyException
    //   1211	1246	1583	com/tapjoy/TapjoyException
    //   1246	1263	1583	com/tapjoy/TapjoyException
    //   1268	1285	1583	com/tapjoy/TapjoyException
    //   1288	1305	1583	com/tapjoy/TapjoyException
    //   1305	1309	1583	com/tapjoy/TapjoyException
    //   1313	1346	1583	com/tapjoy/TapjoyException
    //   1346	1379	1583	com/tapjoy/TapjoyException
    //   1379	1409	1583	com/tapjoy/TapjoyException
    //   1409	1472	1583	com/tapjoy/TapjoyException
    //   1472	1526	1583	com/tapjoy/TapjoyException
    //   1533	1560	1583	com/tapjoy/TapjoyException
    //   1560	1569	1583	com/tapjoy/TapjoyException
    //   1571	1583	1583	com/tapjoy/TapjoyException
    //   65	81	1617	com/tapjoy/TapjoyIntegrationException
    //   81	112	1617	com/tapjoy/TapjoyIntegrationException
    //   112	130	1617	com/tapjoy/TapjoyIntegrationException
    //   130	145	1617	com/tapjoy/TapjoyIntegrationException
    //   145	162	1617	com/tapjoy/TapjoyIntegrationException
    //   166	175	1617	com/tapjoy/TapjoyIntegrationException
    //   175	194	1617	com/tapjoy/TapjoyIntegrationException
    //   194	236	1617	com/tapjoy/TapjoyIntegrationException
    //   236	275	1617	com/tapjoy/TapjoyIntegrationException
    //   279	312	1617	com/tapjoy/TapjoyIntegrationException
    //   312	320	1617	com/tapjoy/TapjoyIntegrationException
    //   325	338	1617	com/tapjoy/TapjoyIntegrationException
    //   342	347	1617	com/tapjoy/TapjoyIntegrationException
    //   351	360	1617	com/tapjoy/TapjoyIntegrationException
    //   364	381	1617	com/tapjoy/TapjoyIntegrationException
    //   385	418	1617	com/tapjoy/TapjoyIntegrationException
    //   421	430	1617	com/tapjoy/TapjoyIntegrationException
    //   430	443	1617	com/tapjoy/TapjoyIntegrationException
    //   447	467	1617	com/tapjoy/TapjoyIntegrationException
    //   472	491	1617	com/tapjoy/TapjoyIntegrationException
    //   491	510	1617	com/tapjoy/TapjoyIntegrationException
    //   510	518	1617	com/tapjoy/TapjoyIntegrationException
    //   523	553	1617	com/tapjoy/TapjoyIntegrationException
    //   556	563	1617	com/tapjoy/TapjoyIntegrationException
    //   563	617	1617	com/tapjoy/TapjoyIntegrationException
    //   620	653	1617	com/tapjoy/TapjoyIntegrationException
    //   656	668	1617	com/tapjoy/TapjoyIntegrationException
    //   673	692	1617	com/tapjoy/TapjoyIntegrationException
    //   695	746	1617	com/tapjoy/TapjoyIntegrationException
    //   750	754	1617	com/tapjoy/TapjoyIntegrationException
    //   754	779	1617	com/tapjoy/TapjoyIntegrationException
    //   782	827	1617	com/tapjoy/TapjoyIntegrationException
    //   830	842	1617	com/tapjoy/TapjoyIntegrationException
    //   845	864	1617	com/tapjoy/TapjoyIntegrationException
    //   868	919	1617	com/tapjoy/TapjoyIntegrationException
    //   922	931	1617	com/tapjoy/TapjoyIntegrationException
    //   931	960	1617	com/tapjoy/TapjoyIntegrationException
    //   965	972	1617	com/tapjoy/TapjoyIntegrationException
    //   976	1044	1617	com/tapjoy/TapjoyIntegrationException
    //   1048	1081	1617	com/tapjoy/TapjoyIntegrationException
    //   1081	1093	1617	com/tapjoy/TapjoyIntegrationException
    //   1097	1130	1617	com/tapjoy/TapjoyIntegrationException
    //   1130	1211	1617	com/tapjoy/TapjoyIntegrationException
    //   1211	1246	1617	com/tapjoy/TapjoyIntegrationException
    //   1246	1263	1617	com/tapjoy/TapjoyIntegrationException
    //   1268	1285	1617	com/tapjoy/TapjoyIntegrationException
    //   1288	1305	1617	com/tapjoy/TapjoyIntegrationException
    //   1305	1309	1617	com/tapjoy/TapjoyIntegrationException
    //   1313	1346	1617	com/tapjoy/TapjoyIntegrationException
    //   1346	1379	1617	com/tapjoy/TapjoyIntegrationException
    //   1379	1409	1617	com/tapjoy/TapjoyIntegrationException
    //   1409	1472	1617	com/tapjoy/TapjoyIntegrationException
    //   1472	1526	1617	com/tapjoy/TapjoyIntegrationException
    //   1533	1560	1617	com/tapjoy/TapjoyIntegrationException
    //   1560	1569	1617	com/tapjoy/TapjoyIntegrationException
    //   1571	1583	1617	com/tapjoy/TapjoyIntegrationException
    //   112	130	1651	java/lang/Exception
  }
  
  private static String a(long paramLong)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(w);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(o());
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(paramLong);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(N);
      localObject = TapjoyUtil.SHA256(((StringBuilder)localObject).toString());
      return localObject;
    }
    catch (Exception localException)
    {
      TapjoyErrorMessage.ErrorType localErrorType = TapjoyErrorMessage.ErrorType.SDK_ERROR;
      StringBuilder localStringBuilder = new StringBuilder("Error in computing verifier value -- ");
      localStringBuilder.append(localException.toString());
      TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(localErrorType, localStringBuilder.toString()));
    }
    return "";
  }
  
  private static String a(long paramLong, String paramString)
  {
    try
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(w);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(o());
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(paramLong);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(N);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(paramString);
      paramString = TapjoyUtil.SHA256(((StringBuilder)localObject).toString());
      return paramString;
    }
    catch (Exception paramString)
    {
      Object localObject = TapjoyErrorMessage.ErrorType.SDK_ERROR;
      StringBuilder localStringBuilder = new StringBuilder("Error in computing packageNamesVerifier -- ");
      localStringBuilder.append(paramString.toString());
      TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage((TapjoyErrorMessage.ErrorType)localObject, localStringBuilder.toString()));
    }
    return "";
  }
  
  private static void a(List paramList)
  {
    try
    {
      ah = "";
      Iterator localIterator = ae.getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (((localApplicationInfo.flags & 0x1) != 1) && (paramList.contains(localApplicationInfo.packageName)))
        {
          StringBuilder localStringBuilder = new StringBuilder("MATCH: installed packageName: ");
          localStringBuilder.append(localApplicationInfo.packageName);
          TapjoyLog.d("TapjoyConnect", localStringBuilder.toString());
          if (ah.length() > 0)
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(ah);
            localStringBuilder.append(",");
            ah = localStringBuilder.toString();
          }
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(ah);
          localStringBuilder.append(localApplicationInfo.packageName);
          ah = localStringBuilder.toString();
        }
      }
      return;
    }
    finally {}
  }
  
  private static void a(Properties paramProperties)
  {
    Enumeration localEnumeration = paramProperties.keys();
    while (localEnumeration.hasMoreElements())
    {
      try
      {
        String str = (String)localEnumeration.nextElement();
        b(str, (String)paramProperties.get(str));
      }
      catch (ClassCastException localClassCastException)
      {
        for (;;) {}
      }
      TapjoyLog.e("TapjoyConnect", "Error parsing configuration properties in tapjoy_config.txt");
    }
  }
  
  private static boolean a(String paramString1, String paramString2)
  {
    FeatureInfo[] arrayOfFeatureInfo = ae.getSystemAvailableFeatures();
    int i2 = arrayOfFeatureInfo.length;
    int i1 = 0;
    while (i1 < i2)
    {
      if (arrayOfFeatureInfo[i1].name.matches(paramString1)) {
        return ae.checkPermission(paramString2, g.getPackageName()) == 0;
      }
      i1 += 1;
    }
    return false;
  }
  
  /* Error */
  private static boolean a(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 9
    //   3: aconst_null
    //   4: astore 8
    //   6: aconst_null
    //   7: astore 10
    //   9: aload 10
    //   11: astore 6
    //   13: aload_0
    //   14: invokestatic 853	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   17: astore 7
    //   19: aload 7
    //   21: invokevirtual 856	com/tapjoy/internal/bs:d	()Ljava/util/Map;
    //   24: astore 13
    //   26: aload 13
    //   28: ldc_w 858
    //   31: invokeinterface 861 2 0
    //   36: checkcast 349	java/lang/String
    //   39: invokestatic 865	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   42: astore 11
    //   44: aload 13
    //   46: ldc_w 867
    //   49: invokeinterface 861 2 0
    //   54: checkcast 349	java/lang/String
    //   57: invokestatic 865	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   60: astore 16
    //   62: aload 13
    //   64: ldc_w 869
    //   67: invokeinterface 861 2 0
    //   72: checkcast 349	java/lang/String
    //   75: invokestatic 865	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   78: astore 17
    //   80: aload 13
    //   82: ldc_w 871
    //   85: invokeinterface 861 2 0
    //   90: checkcast 349	java/lang/String
    //   93: invokestatic 865	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   96: astore 18
    //   98: aload 13
    //   100: ldc_w 873
    //   103: invokeinterface 861 2 0
    //   108: checkcast 349	java/lang/String
    //   111: invokestatic 865	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   114: astore 15
    //   116: aload 13
    //   118: ldc_w 875
    //   121: invokeinterface 861 2 0
    //   126: astore 14
    //   128: new 877	com/tapjoy/internal/er
    //   131: dup
    //   132: aload 17
    //   134: invokespecial 878	com/tapjoy/internal/er:<init>	(Ljava/lang/String;)V
    //   137: astore 6
    //   139: aload 6
    //   141: getfield 881	com/tapjoy/internal/er:a	Lcom/tapjoy/internal/er$a;
    //   144: getstatic 886	com/tapjoy/internal/er$a:RPC_ANALYTICS	Lcom/tapjoy/internal/er$a;
    //   147: if_acmpne +517 -> 664
    //   150: aload 6
    //   152: getfield 888	com/tapjoy/internal/er:b	Ljava/lang/String;
    //   155: astore 12
    //   157: aload 12
    //   159: bipush 13
    //   161: ldc_w 890
    //   164: iconst_0
    //   165: bipush 11
    //   167: invokevirtual 894	java/lang/String:regionMatches	(ILjava/lang/String;II)Z
    //   170: ifeq +483 -> 653
    //   173: new 896	java/lang/StringBuffer
    //   176: dup
    //   177: invokespecial 897	java/lang/StringBuffer:<init>	()V
    //   180: astore 19
    //   182: aload 19
    //   184: aload 12
    //   186: iconst_0
    //   187: bipush 8
    //   189: invokevirtual 475	java/lang/String:substring	(II)Ljava/lang/String;
    //   192: invokevirtual 900	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   195: pop
    //   196: aload 19
    //   198: aload 12
    //   200: bipush 24
    //   202: bipush 30
    //   204: invokevirtual 475	java/lang/String:substring	(II)Ljava/lang/String;
    //   207: invokevirtual 900	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   210: pop
    //   211: aload 19
    //   213: aload 12
    //   215: bipush 9
    //   217: bipush 13
    //   219: invokevirtual 475	java/lang/String:substring	(II)Ljava/lang/String;
    //   222: invokevirtual 900	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   225: pop
    //   226: aload 19
    //   228: aload 12
    //   230: bipush 30
    //   232: invokevirtual 478	java/lang/String:substring	(I)Ljava/lang/String;
    //   235: invokevirtual 900	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   238: pop
    //   239: aload 19
    //   241: invokevirtual 901	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   244: astore 12
    //   246: aload 6
    //   248: getfield 902	com/tapjoy/internal/er:c	Ljava/lang/String;
    //   251: astore 19
    //   253: aload 11
    //   255: astore 6
    //   257: aload 11
    //   259: ifnonnull +7 -> 266
    //   262: aload 12
    //   264: astore 6
    //   266: invokestatic 907	com/tapjoy/internal/gd:a	()Lcom/tapjoy/internal/gd;
    //   269: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   272: aload 17
    //   274: ldc_w 380
    //   277: ldc_w 909
    //   280: aload 12
    //   282: aload 19
    //   284: invokevirtual 912	com/tapjoy/internal/gd:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   287: aload 6
    //   289: putstatic 212	com/tapjoy/TapjoyConnectCore:U	Ljava/lang/String;
    //   292: aload 16
    //   294: putstatic 214	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   297: aload 17
    //   299: putstatic 216	com/tapjoy/TapjoyConnectCore:W	Ljava/lang/String;
    //   302: aload 18
    //   304: putstatic 218	com/tapjoy/TapjoyConnectCore:X	Ljava/lang/String;
    //   307: new 608	java/util/ArrayList
    //   310: dup
    //   311: invokespecial 913	java/util/ArrayList:<init>	()V
    //   314: astore 6
    //   316: aload 15
    //   318: ifnull +54 -> 372
    //   321: aload 15
    //   323: ldc_w 751
    //   326: invokevirtual 917	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   329: astore 11
    //   331: aload 11
    //   333: arraylength
    //   334: istore_3
    //   335: iconst_0
    //   336: istore_2
    //   337: iload_2
    //   338: iload_3
    //   339: if_icmpge +33 -> 372
    //   342: aload 11
    //   344: iload_2
    //   345: aaload
    //   346: invokevirtual 816	java/lang/String:trim	()Ljava/lang/String;
    //   349: astore 12
    //   351: aload 12
    //   353: invokevirtual 471	java/lang/String:length	()I
    //   356: ifle +413 -> 769
    //   359: aload 6
    //   361: aload 12
    //   363: invokeinterface 918 2 0
    //   368: pop
    //   369: goto +400 -> 769
    //   372: aload 6
    //   374: invokeinterface 921 1 0
    //   379: ifne +8 -> 387
    //   382: aload 6
    //   384: invokestatic 821	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   387: aload 7
    //   389: invokevirtual 924	com/tapjoy/internal/bs:close	()V
    //   392: iload_1
    //   393: ifne +254 -> 647
    //   396: aload 10
    //   398: astore 6
    //   400: aload 14
    //   402: instanceof 349
    //   405: istore_1
    //   406: iload_1
    //   407: ifeq +23 -> 430
    //   410: aload 10
    //   412: astore 6
    //   414: aload 14
    //   416: checkcast 349	java/lang/String
    //   419: invokevirtual 816	java/lang/String:trim	()Ljava/lang/String;
    //   422: invokestatic 930	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   425: lstore 4
    //   427: goto +37 -> 464
    //   430: aload 10
    //   432: astore 6
    //   434: aload 14
    //   436: instanceof 932
    //   439: istore_1
    //   440: iload_1
    //   441: ifeq +20 -> 461
    //   444: aload 10
    //   446: astore 6
    //   448: aload 14
    //   450: checkcast 932	java/lang/Number
    //   453: invokevirtual 935	java/lang/Number:longValue	()J
    //   456: lstore 4
    //   458: goto +6 -> 464
    //   461: lconst_0
    //   462: lstore 4
    //   464: lload 4
    //   466: lconst_0
    //   467: lcmp
    //   468: ifgt +16 -> 484
    //   471: aload 10
    //   473: astore 6
    //   475: invokestatic 941	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   478: invokevirtual 944	com/tapjoy/TapjoyAppSettings:removeConnectResult	()V
    //   481: goto +27 -> 508
    //   484: aload 10
    //   486: astore 6
    //   488: invokestatic 941	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   491: aload_0
    //   492: invokestatic 946	com/tapjoy/TapjoyConnectCore:q	()Ljava/lang/String;
    //   495: lload 4
    //   497: ldc2_w 947
    //   500: lmul
    //   501: invokestatic 952	com/tapjoy/internal/y:b	()J
    //   504: ladd
    //   505: invokevirtual 956	com/tapjoy/TapjoyAppSettings:saveConnectResultAndParams	(Ljava/lang/String;Ljava/lang/String;J)V
    //   508: aload 10
    //   510: astore 6
    //   512: invokestatic 271	com/tapjoy/internal/fd:a	()Lcom/tapjoy/internal/fd;
    //   515: astore_0
    //   516: aload 10
    //   518: astore 6
    //   520: aload 13
    //   522: ldc_w 958
    //   525: invokeinterface 861 2 0
    //   530: astore 7
    //   532: aload 10
    //   534: astore 6
    //   536: aload 7
    //   538: instanceof 860
    //   541: istore_1
    //   542: iload_1
    //   543: ifeq +61 -> 604
    //   546: aload 10
    //   548: astore 6
    //   550: aload_0
    //   551: getfield 961	com/tapjoy/internal/fd:a	Lcom/tapjoy/internal/fb;
    //   554: aload 7
    //   556: checkcast 860	java/util/Map
    //   559: invokevirtual 966	com/tapjoy/internal/fb:a	(Ljava/util/Map;)V
    //   562: aload 10
    //   564: astore 6
    //   566: aload 7
    //   568: invokestatic 971	com/tapjoy/internal/bm:a	(Ljava/lang/Object;)Ljava/lang/String;
    //   571: astore 7
    //   573: aload 10
    //   575: astore 6
    //   577: aload_0
    //   578: invokevirtual 974	com/tapjoy/internal/fd:c	()Landroid/content/SharedPreferences;
    //   581: invokeinterface 583 1 0
    //   586: ldc_w 958
    //   589: aload 7
    //   591: invokeinterface 589 3 0
    //   596: invokeinterface 977 1 0
    //   601: goto +46 -> 647
    //   604: aload 7
    //   606: ifnonnull +41 -> 647
    //   609: aload 10
    //   611: astore 6
    //   613: aload_0
    //   614: getfield 961	com/tapjoy/internal/fd:a	Lcom/tapjoy/internal/fb;
    //   617: aconst_null
    //   618: invokevirtual 966	com/tapjoy/internal/fb:a	(Ljava/util/Map;)V
    //   621: aload 10
    //   623: astore 6
    //   625: aload_0
    //   626: invokevirtual 974	com/tapjoy/internal/fd:c	()Landroid/content/SharedPreferences;
    //   629: invokeinterface 583 1 0
    //   634: ldc_w 958
    //   637: invokeinterface 981 2 0
    //   642: invokeinterface 977 1 0
    //   647: aconst_null
    //   648: invokestatic 986	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   651: iconst_1
    //   652: ireturn
    //   653: new 988	java/lang/IllegalArgumentException
    //   656: dup
    //   657: ldc_w 990
    //   660: invokespecial 991	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   663: athrow
    //   664: new 844	java/io/IOException
    //   667: dup
    //   668: ldc_w 993
    //   671: invokespecial 994	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   674: athrow
    //   675: astore_0
    //   676: aload 7
    //   678: astore 6
    //   680: goto +73 -> 753
    //   683: astore 6
    //   685: aload 7
    //   687: astore_0
    //   688: aload 6
    //   690: astore 7
    //   692: goto +24 -> 716
    //   695: astore 6
    //   697: aload 7
    //   699: astore_0
    //   700: aload 6
    //   702: astore 7
    //   704: goto +29 -> 733
    //   707: astore_0
    //   708: goto +45 -> 753
    //   711: astore 7
    //   713: aload 9
    //   715: astore_0
    //   716: aload_0
    //   717: astore 6
    //   719: ldc_w 415
    //   722: aload 7
    //   724: invokevirtual 995	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   727: invokestatic 997	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   730: goto +17 -> 747
    //   733: aload_0
    //   734: astore 6
    //   736: ldc_w 415
    //   739: aload 7
    //   741: invokevirtual 998	java/io/IOException:getMessage	()Ljava/lang/String;
    //   744: invokestatic 997	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   747: aload_0
    //   748: invokestatic 986	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   751: iconst_0
    //   752: ireturn
    //   753: aload 6
    //   755: invokestatic 986	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   758: aload_0
    //   759: athrow
    //   760: astore 6
    //   762: goto -301 -> 461
    //   765: astore_0
    //   766: goto -119 -> 647
    //   769: iload_2
    //   770: iconst_1
    //   771: iadd
    //   772: istore_2
    //   773: goto -436 -> 337
    //   776: astore 7
    //   778: aload 8
    //   780: astore_0
    //   781: goto -48 -> 733
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	784	0	paramString	String
    //   0	784	1	paramBoolean	boolean
    //   336	437	2	i1	int
    //   334	6	3	i2	int
    //   425	71	4	l1	long
    //   11	668	6	localObject1	Object
    //   683	6	6	localRuntimeException1	RuntimeException
    //   695	6	6	localIOException1	java.io.IOException
    //   717	37	6	str1	String
    //   760	1	6	localNumberFormatException	NumberFormatException
    //   17	686	7	localObject2	Object
    //   711	29	7	localRuntimeException2	RuntimeException
    //   776	1	7	localIOException2	java.io.IOException
    //   4	775	8	localObject3	Object
    //   1	713	9	localObject4	Object
    //   7	615	10	localObject5	Object
    //   42	301	11	localObject6	Object
    //   155	207	12	str2	String
    //   24	497	13	localMap	Map
    //   126	323	14	localObject7	Object
    //   114	208	15	str3	String
    //   60	233	16	str4	String
    //   78	220	17	str5	String
    //   96	207	18	str6	String
    //   180	103	19	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   19	253	675	finally
    //   266	316	675	finally
    //   321	335	675	finally
    //   342	369	675	finally
    //   372	387	675	finally
    //   387	392	675	finally
    //   653	664	675	finally
    //   664	675	675	finally
    //   19	253	683	java/lang/RuntimeException
    //   266	316	683	java/lang/RuntimeException
    //   321	335	683	java/lang/RuntimeException
    //   342	369	683	java/lang/RuntimeException
    //   372	387	683	java/lang/RuntimeException
    //   387	392	683	java/lang/RuntimeException
    //   653	664	683	java/lang/RuntimeException
    //   664	675	683	java/lang/RuntimeException
    //   19	253	695	java/io/IOException
    //   266	316	695	java/io/IOException
    //   321	335	695	java/io/IOException
    //   342	369	695	java/io/IOException
    //   372	387	695	java/io/IOException
    //   387	392	695	java/io/IOException
    //   653	664	695	java/io/IOException
    //   664	675	695	java/io/IOException
    //   13	19	707	finally
    //   400	406	707	finally
    //   414	427	707	finally
    //   434	440	707	finally
    //   448	458	707	finally
    //   475	481	707	finally
    //   488	508	707	finally
    //   512	516	707	finally
    //   520	532	707	finally
    //   536	542	707	finally
    //   550	562	707	finally
    //   566	573	707	finally
    //   577	601	707	finally
    //   613	621	707	finally
    //   625	647	707	finally
    //   719	730	707	finally
    //   736	747	707	finally
    //   13	19	711	java/lang/RuntimeException
    //   400	406	711	java/lang/RuntimeException
    //   414	427	711	java/lang/RuntimeException
    //   434	440	711	java/lang/RuntimeException
    //   448	458	711	java/lang/RuntimeException
    //   475	481	711	java/lang/RuntimeException
    //   488	508	711	java/lang/RuntimeException
    //   512	516	711	java/lang/RuntimeException
    //   520	532	711	java/lang/RuntimeException
    //   536	542	711	java/lang/RuntimeException
    //   550	562	711	java/lang/RuntimeException
    //   566	573	711	java/lang/RuntimeException
    //   577	601	711	java/lang/RuntimeException
    //   613	621	711	java/lang/RuntimeException
    //   625	647	711	java/lang/RuntimeException
    //   414	427	760	java/lang/NumberFormatException
    //   448	458	760	java/lang/NumberFormatException
    //   550	562	765	java/lang/Exception
    //   566	573	765	java/lang/Exception
    //   577	601	765	java/lang/Exception
    //   13	19	776	java/io/IOException
    //   400	406	776	java/io/IOException
    //   414	427	776	java/io/IOException
    //   434	440	776	java/io/IOException
    //   448	458	776	java/io/IOException
    //   475	481	776	java/io/IOException
    //   488	508	776	java/io/IOException
    //   512	516	776	java/io/IOException
    //   520	532	776	java/io/IOException
    //   536	542	776	java/io/IOException
    //   550	562	776	java/io/IOException
    //   566	573	776	java/io/IOException
    //   577	601	776	java/io/IOException
    //   613	621	776	java/io/IOException
    //   625	647	776	java/io/IOException
  }
  
  private static void b(String paramString1, String paramString2)
  {
    Object localObject;
    if (!paramString1.equals("TJC_OPTION_SERVICE_URL"))
    {
      localObject = paramString2;
      if (!paramString1.equals("TJC_OPTION_PLACEMENT_SERVICE_URL")) {}
    }
    else
    {
      localObject = paramString2;
      if (!paramString2.endsWith("/"))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramString2);
        ((StringBuilder)localObject).append("/");
        localObject = ((StringBuilder)localObject).toString();
      }
    }
    ag.put(paramString1, localObject);
  }
  
  /* Error */
  private static boolean c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 853	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 1014	com/tapjoy/internal/bs:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 1016	com/tapjoy/internal/bs:s	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 415
    //   25: ldc_w 1018
    //   28: invokestatic 455	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 924	com/tapjoy/internal/bs:close	()V
    //   37: aconst_null
    //   38: invokestatic 986	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 924	com/tapjoy/internal/bs:close	()V
    //   49: aconst_null
    //   50: invokestatic 986	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   53: goto +55 -> 108
    //   56: astore_1
    //   57: goto +72 -> 129
    //   60: astore_2
    //   61: goto +16 -> 77
    //   64: astore_2
    //   65: goto +27 -> 92
    //   68: astore_1
    //   69: aconst_null
    //   70: astore_0
    //   71: goto +58 -> 129
    //   74: astore_2
    //   75: aconst_null
    //   76: astore_1
    //   77: aload_1
    //   78: astore_0
    //   79: ldc_w 415
    //   82: aload_2
    //   83: invokevirtual 995	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   86: invokestatic 997	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: goto +15 -> 104
    //   92: aload_1
    //   93: astore_0
    //   94: ldc_w 415
    //   97: aload_2
    //   98: invokevirtual 998	java/io/IOException:getMessage	()Ljava/lang/String;
    //   101: invokestatic 997	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   104: aload_1
    //   105: invokestatic 986	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   108: ldc_w 415
    //   111: new 489	com/tapjoy/TapjoyErrorMessage
    //   114: dup
    //   115: getstatic 495	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   118: ldc_w 1020
    //   121: invokespecial 500	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   124: invokestatic 503	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   127: iconst_0
    //   128: ireturn
    //   129: aload_0
    //   130: invokestatic 986	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   133: aload_1
    //   134: athrow
    //   135: astore_2
    //   136: aconst_null
    //   137: astore_1
    //   138: goto -46 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	paramString	String
    //   4	42	1	localBs	com.tapjoy.internal.bs
    //   56	1	1	localObject1	Object
    //   68	1	1	localObject2	Object
    //   76	62	1	localCloseable	java.io.Closeable
    //   60	1	2	localRuntimeException1	RuntimeException
    //   64	1	2	localIOException1	java.io.IOException
    //   74	24	2	localRuntimeException2	RuntimeException
    //   135	1	2	localIOException2	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   7	14	56	finally
    //   16	20	56	finally
    //   22	31	56	finally
    //   33	37	56	finally
    //   45	49	56	finally
    //   79	89	56	finally
    //   94	104	56	finally
    //   7	14	60	java/lang/RuntimeException
    //   16	20	60	java/lang/RuntimeException
    //   22	31	60	java/lang/RuntimeException
    //   33	37	60	java/lang/RuntimeException
    //   45	49	60	java/lang/RuntimeException
    //   7	14	64	java/io/IOException
    //   16	20	64	java/io/IOException
    //   22	31	64	java/io/IOException
    //   33	37	64	java/io/IOException
    //   45	49	64	java/io/IOException
    //   0	5	68	finally
    //   0	5	74	java/lang/RuntimeException
    //   0	5	135	java/io/IOException
  }
  
  private static void d()
  {
    if (!ct.c(O)) {
      gd.a().a(g, h, "11.11.0", "https://rpc.tapjoy.com/", O, N);
    }
    if (k != null) {
      k.onConnectFailure();
    }
  }
  
  private static boolean d(String paramString)
  {
    Iterator localIterator = ae.getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.startsWith(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private static Map e()
  {
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "plugin", P, true);
    TapjoyUtil.safePut(localHashMap3, "sdk_type", Q, true);
    TapjoyUtil.safePut(localHashMap3, "app_id", w, true);
    TapjoyUtil.safePut(localHashMap3, "library_version", y, true);
    TapjoyUtil.safePut(localHashMap3, "library_revision", "30ef4dc", true);
    TapjoyUtil.safePut(localHashMap3, "bridge_version", z, true);
    TapjoyUtil.safePut(localHashMap3, "app_version", x, true);
    localHashMap2.putAll(localHashMap3);
    localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "device_name", s, true);
    TapjoyUtil.safePut(localHashMap3, "platform", F, true);
    TapjoyUtil.safePut(localHashMap3, "os_version", v, true);
    TapjoyUtil.safePut(localHashMap3, "device_manufacturer", t, true);
    TapjoyUtil.safePut(localHashMap3, "device_type", u, true);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(C);
    TapjoyUtil.safePut(localHashMap3, "screen_layout_size", localStringBuilder.toString(), true);
    TapjoyUtil.safePut(localHashMap3, "device_location", String.valueOf(E), true);
    TapjoyUtil.safePut(localHashMap3, "store_name", M, true);
    TapjoyUtil.safePut(localHashMap3, "store_view", String.valueOf(T), true);
    TapjoyUtil.safePut(localHashMap3, "carrier_name", G, true);
    TapjoyUtil.safePut(localHashMap3, "carrier_country_code", H, true);
    TapjoyUtil.safePut(localHashMap3, "mobile_network_code", J, true);
    TapjoyUtil.safePut(localHashMap3, "mobile_country_code", I, true);
    TapjoyUtil.safePut(localHashMap3, "country_code", Locale.getDefault().getCountry(), true);
    TapjoyUtil.safePut(localHashMap3, "language_code", Locale.getDefault().getLanguage(), true);
    K = getConnectionType();
    TapjoyUtil.safePut(localHashMap3, "connection_type", K, true);
    L = getConnectionSubType();
    TapjoyUtil.safePut(localHashMap3, "connection_subtype", L, true);
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(A);
    TapjoyUtil.safePut(localHashMap3, "screen_density", localStringBuilder.toString(), true);
    localHashMap2.putAll(localHashMap3);
    localHashMap3 = new HashMap();
    if (l())
    {
      TapjoyUtil.safePut(localHashMap3, "advertising_id", c, true);
      TapjoyUtil.safePut(localHashMap3, "ad_tracking_enabled", String.valueOf(d), true);
    }
    if (!m())
    {
      TapjoyUtil.safePut(localHashMap3, "android_id", n, true);
      TapjoyUtil.safePut(localHashMap3, "udid", p, true);
      TapjoyUtil.safePut(localHashMap3, "mac_address", q, true);
    }
    TapjoyUtil.safePut(localHashMap3, "install_id", r, true);
    TapjoyUtil.safePut(localHashMap3, "publisher_user_id", D, true);
    TapjoyUtil.safePut(localHashMap3, "ad_id_check_disabled", e, true);
    TapjoyUtil.safePut(localHashMap3, "persistent_ids_disabled", f, true);
    if (a != 0) {
      TapjoyUtil.safePut(localHashMap3, "packaged_gps_version", Integer.toString(a), true);
    }
    if (b != 0) {
      TapjoyUtil.safePut(localHashMap3, "device_gps_version", Integer.toString(b), true);
    }
    if ((o != null) && (o.length() != 0) && (System.currentTimeMillis() - ab <= 1800000L)) {
      ab = System.currentTimeMillis();
    } else {
      o = n();
    }
    TapjoyUtil.safePut(localHashMap3, "session_id", o, true);
    localHashMap2.putAll(localHashMap3);
    localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "app_group_id", U, true);
    TapjoyUtil.safePut(localHashMap3, "store", V, true);
    TapjoyUtil.safePut(localHashMap3, "analytics_api_key", W, true);
    TapjoyUtil.safePut(localHashMap3, "managed_device_id", X, true);
    localHashMap2.putAll(localHashMap3);
    if ((TapjoyCache.getInstance() != null) && (TapjoyCache.getInstance().getCachedOfferIDs() != null) && (TapjoyCache.getInstance().getCachedOfferIDs().length() > 0)) {
      TapjoyUtil.safePut(localHashMap2, "cached_ids", TapjoyCache.getInstance().getCachedOfferIDs(), true);
    }
    TapjoyUtil.safePut(localHashMap2, "display_multiplier", Float.toString(S), true);
    localHashMap1.putAll(localHashMap2);
    localHashMap2 = new HashMap();
    g();
    localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "analytics_id", aj, true);
    TapjoyUtil.safePut(localHashMap3, "pkg_id", ak, true);
    TapjoyUtil.safePut(localHashMap3, "pkg_sign", al, true);
    TapjoyUtil.safePut(localHashMap3, "display_d", aL);
    TapjoyUtil.safePut(localHashMap3, "display_w", aM);
    TapjoyUtil.safePut(localHashMap3, "display_h", aN);
    TapjoyUtil.safePut(localHashMap3, "country_sim", aO, true);
    TapjoyUtil.safePut(localHashMap3, "timezone", aP, true);
    localHashMap2.putAll(localHashMap3);
    localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "pkg_ver", am, true);
    TapjoyUtil.safePut(localHashMap3, "pkg_rev", an);
    TapjoyUtil.safePut(localHashMap3, "pkg_data_ver", ao, true);
    TapjoyUtil.safePut(localHashMap3, "installer", ap, true);
    if (ct.c(M)) {
      TapjoyUtil.safePut(localHashMap3, "store_name", aQ, true);
    }
    localHashMap2.putAll(localHashMap3);
    localHashMap2.putAll(f());
    localHashMap1.putAll(localHashMap2);
    return localHashMap1;
  }
  
  private static boolean e(String paramString)
  {
    return ae.checkPermission(paramString, g.getPackageName()) == 0;
  }
  
  private static Map f()
  {
    HashMap localHashMap = new HashMap();
    TapjoyUtil.safePut(localHashMap, "installed", aq);
    TapjoyUtil.safePut(localHashMap, "referrer", ar, true);
    TapjoyUtil.safePut(localHashMap, "user_level", as);
    TapjoyUtil.safePut(localHashMap, "friend_count", at);
    TapjoyUtil.safePut(localHashMap, "uv1", au, true);
    TapjoyUtil.safePut(localHashMap, "uv2", av, true);
    TapjoyUtil.safePut(localHashMap, "uv3", aw, true);
    TapjoyUtil.safePut(localHashMap, "uv4", ax, true);
    TapjoyUtil.safePut(localHashMap, "uv5", ay, true);
    Iterator localIterator = az.iterator();
    int i1 = 0;
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      StringBuilder localStringBuilder = new StringBuilder("user_tags[");
      localStringBuilder.append(i1);
      localStringBuilder.append("]");
      TapjoyUtil.safePut(localHashMap, localStringBuilder.toString(), str, true);
      i1 += 1;
    }
    TapjoyUtil.safePut(localHashMap, "fq7", aA);
    TapjoyUtil.safePut(localHashMap, "fq30", aB);
    TapjoyUtil.safePut(localHashMap, "session_total_count", aC);
    TapjoyUtil.safePut(localHashMap, "session_total_length", aD);
    TapjoyUtil.safePut(localHashMap, "session_last_at", aE);
    TapjoyUtil.safePut(localHashMap, "session_last_length", aF);
    TapjoyUtil.safePut(localHashMap, "purchase_currency", aG, true);
    TapjoyUtil.safePut(localHashMap, "purchase_total_count", aH);
    TapjoyUtil.safePut(localHashMap, "purchase_total_price", aI);
    TapjoyUtil.safePut(localHashMap, "purchase_last_price", aJ);
    TapjoyUtil.safePut(localHashMap, "purchase_last_at", aK);
    return localHashMap;
  }
  
  private static void g()
  {
    Object localObject1 = gd.a(g).a(true);
    Object localObject2 = ((ee)localObject1).d;
    aj = ((ed)localObject2).h;
    ak = ((ed)localObject2).r;
    al = ((ed)localObject2).s;
    aL = ((ed)localObject2).m;
    aM = ((ed)localObject2).n;
    aN = ((ed)localObject2).o;
    aO = ((ed)localObject2).u;
    aP = ((ed)localObject2).q;
    localObject2 = ((ee)localObject1).e;
    am = ((dx)localObject2).e;
    an = ((dx)localObject2).f;
    ao = ((dx)localObject2).g;
    ap = ((dx)localObject2).h;
    aQ = ((dx)localObject2).i;
    localObject1 = ((ee)localObject1).f;
    aq = ((ek)localObject1).s;
    ar = ((ek)localObject1).t;
    as = ((ek)localObject1).J;
    at = ((ek)localObject1).K;
    au = ((ek)localObject1).L;
    av = ((ek)localObject1).M;
    aw = ((ek)localObject1).N;
    ax = ((ek)localObject1).O;
    ay = ((ek)localObject1).P;
    az = new HashSet(((ek)localObject1).Q);
    aA = ((ek)localObject1).u;
    aB = ((ek)localObject1).v;
    aC = ((ek)localObject1).x;
    aD = ((ek)localObject1).y;
    aE = ((ek)localObject1).z;
    aF = ((ek)localObject1).A;
    aG = ((ek)localObject1).B;
    aH = ((ek)localObject1).C;
    aI = ((ek)localObject1).D;
    aJ = ((ek)localObject1).F;
    aK = ((ek)localObject1).E;
  }
  
  public static String getAndroidID()
  {
    return n;
  }
  
  public static String getAppID()
  {
    return w;
  }
  
  public static String getAwardCurrencyVerifier(long paramLong, int paramInt, String paramString)
  {
    try
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(w);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(o());
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(paramLong);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(N);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(paramInt);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(paramString);
      paramString = TapjoyUtil.SHA256(((StringBuilder)localObject).toString());
      return paramString;
    }
    catch (Exception paramString)
    {
      Object localObject = TapjoyErrorMessage.ErrorType.SDK_ERROR;
      StringBuilder localStringBuilder = new StringBuilder("Error in computing awardCurrencyVerifier -- ");
      localStringBuilder.append(paramString.toString());
      TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage((TapjoyErrorMessage.ErrorType)localObject, localStringBuilder.toString()));
    }
    return "";
  }
  
  public static String getCarrierName()
  {
    return G;
  }
  
  public static String getConnectFlagValue(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    if (ag != null)
    {
      str1 = str2;
      if (ag.get(paramString) != null) {
        str1 = ag.get(paramString).toString();
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
    Object localObject1 = "";
    Object localObject4;
    try
    {
      localObject4 = (ConnectivityManager)g.getSystemService("connectivity");
      Object localObject2 = localObject1;
      if (localObject4 == null) {
        return ???;
      }
      localObject4 = ((ConnectivityManager)localObject4).getActiveNetworkInfo().getSubtypeName();
      try
      {
        TapjoyLog.d("TapjoyConnect", "connection_sub_type: ".concat(String.valueOf(localObject4)));
        return localObject4;
      }
      catch (Exception localException1)
      {
        localObject1 = localObject4;
      }
      localObject4 = new StringBuilder("getConnectionSubType error: ");
    }
    catch (Exception localException2) {}
    ((StringBuilder)localObject4).append(localException2.toString());
    TapjoyLog.e("TapjoyConnect", ((StringBuilder)localObject4).toString());
    Object localObject3 = localObject1;
    return localObject3;
  }
  
  public static String getConnectionType()
  {
    String str2 = "";
    String str1 = str2;
    try
    {
      localConnectivityManager = (ConnectivityManager)g.getSystemService("connectivity");
      localObject2 = str2;
      if (localConnectivityManager == null) {
        break label160;
      }
      str1 = str2;
      localObject2 = str2;
      if (localConnectivityManager.getActiveNetworkInfo() == null) {
        break label160;
      }
      str1 = str2;
      int i1 = localConnectivityManager.getActiveNetworkInfo().getType();
      if ((i1 == 1) || (i1 == 6)) {
        break label167;
      }
      str1 = "mobile";
    }
    catch (Exception localException)
    {
      ConnectivityManager localConnectivityManager;
      localObject2 = new StringBuilder("getConnectionType error: ");
      ((StringBuilder)localObject2).append(localException.toString());
      TapjoyLog.e("TapjoyConnect", ((StringBuilder)localObject2).toString());
      localObject2 = str1;
    }
    str1 = str2;
    Object localObject2 = new StringBuilder("connectivity: ");
    str1 = str2;
    ((StringBuilder)localObject2).append(localConnectivityManager.getActiveNetworkInfo().getType());
    str1 = str2;
    TapjoyLog.d("TapjoyConnect", ((StringBuilder)localObject2).toString());
    str1 = str2;
    TapjoyLog.d("TapjoyConnect", "connection_type: ".concat(String.valueOf(str2)));
    return str2;
    label160:
    return localObject2;
    for (;;)
    {
      Object localObject1 = str1;
      break;
      label167:
      str1 = "wifi";
    }
  }
  
  public static Context getContext()
  {
    return g;
  }
  
  public static String getDeviceID()
  {
    return p;
  }
  
  public static float getDeviceScreenDensityScale()
  {
    return B;
  }
  
  public static Map getGenericURLParams()
  {
    Map localMap = e();
    TapjoyUtil.safePut(localMap, "app_id", w, true);
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
    return q;
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
    return N;
  }
  
  public static String getSha1MacAddress()
  {
    try
    {
      String str = TapjoyUtil.SHA1(q);
      return str;
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder = new StringBuilder("Error generating sha1 of macAddress: ");
      localStringBuilder.append(localException.toString());
      TapjoyLog.e("TapjoyConnect", localStringBuilder.toString());
    }
    return null;
  }
  
  public static String getSupportURL(String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = w;
    }
    paramString = new StringBuilder();
    paramString.append(getHostURL());
    paramString.append("support_requests/new?currency_id=");
    paramString.append(str);
    paramString.append("&app_id=");
    paramString.append(w);
    paramString.append("&udid=");
    paramString.append(X);
    paramString.append("&language_code=");
    paramString.append(Locale.getDefault().getLanguage());
    return paramString.toString();
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
    return D;
  }
  
  private static void h()
  {
    TapjoyLog.i("TapjoyConnect", "Connect Flags:");
    TapjoyLog.i("TapjoyConnect", "--------------------");
    Object localObject = ag.entrySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      StringBuilder localStringBuilder = new StringBuilder("key: ");
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append(", value: ");
      localStringBuilder.append(Uri.encode(localEntry.getValue().toString()));
      TapjoyLog.i("TapjoyConnect", localStringBuilder.toString());
    }
    localObject = new StringBuilder("hostURL: [");
    ((StringBuilder)localObject).append(getConnectFlagValue("TJC_OPTION_SERVICE_URL"));
    ((StringBuilder)localObject).append("]");
    TapjoyLog.i("TapjoyConnect", ((StringBuilder)localObject).toString());
    localObject = new StringBuilder("redirectDomain: [");
    ((StringBuilder)localObject).append(R);
    ((StringBuilder)localObject).append("]");
    TapjoyLog.i("TapjoyConnect", ((StringBuilder)localObject).toString());
    TapjoyLog.i("TapjoyConnect", "--------------------");
  }
  
  private static void i()
  {
    for (;;)
    {
      int i1;
      try
      {
        if (ae != null)
        {
          ApplicationInfo localApplicationInfo = ae.getApplicationInfo(g.getPackageName(), 128);
          if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null))
          {
            localObject1 = TapjoyConnectFlag.FLAG_ARRAY;
            int i2 = localObject1.length;
            i1 = 0;
            if (i1 < i2)
            {
              localObject2 = localObject1[i1];
              String str = localApplicationInfo.metaData.getString("tapjoy.".concat(String.valueOf(localObject2)));
              if (str != null)
              {
                StringBuilder localStringBuilder = new StringBuilder("Found manifest flag: ");
                localStringBuilder.append((String)localObject2);
                localStringBuilder.append(", ");
                localStringBuilder.append(str);
                TapjoyLog.d("TapjoyConnect", localStringBuilder.toString());
                b((String)localObject2, str);
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
          }
        }
        else
        {
          return;
        }
      }
      catch (Exception localException)
      {
        Object localObject1 = TapjoyErrorMessage.ErrorType.SDK_ERROR;
        Object localObject2 = new StringBuilder("Error reading manifest meta-data -- ");
        ((StringBuilder)localObject2).append(localException.toString());
        TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage((TapjoyErrorMessage.ErrorType)localObject1, ((StringBuilder)localObject2).toString()));
        return;
      }
      i1 += 1;
    }
  }
  
  public static boolean isConnected()
  {
    return ad;
  }
  
  public static boolean isFullScreenViewOpen()
  {
    Iterator localIterator = ai.values().iterator();
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
    StringBuilder localStringBuilder = new StringBuilder("isViewOpen: ");
    localStringBuilder.append(ai.size());
    TapjoyLog.d("TapjoyConnect", localStringBuilder.toString());
    return !ai.isEmpty();
  }
  
  /* Error */
  private void j()
  {
    // Byte code:
    //   0: getstatic 286	com/tapjoy/TapjoyConnectCore:ae	Landroid/content/pm/PackageManager;
    //   3: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   6: invokevirtual 307	android/content/Context:getPackageName	()Ljava/lang/String;
    //   9: iconst_1
    //   10: invokevirtual 358	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   13: getfield 1560	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
    //   16: invokestatic 134	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   19: astore_2
    //   20: aload_2
    //   21: ifnull +427 -> 448
    //   24: aload_2
    //   25: invokeinterface 729 1 0
    //   30: astore_3
    //   31: aload_3
    //   32: invokeinterface 734 1 0
    //   37: ifeq +411 -> 448
    //   40: aload_3
    //   41: invokeinterface 738 1 0
    //   46: checkcast 1562	android/content/pm/ActivityInfo
    //   49: astore 4
    //   51: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   54: aload 4
    //   56: getfield 1563	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   59: invokevirtual 1564	java/util/Vector:contains	(Ljava/lang/Object;)Z
    //   62: ifeq -31 -> 31
    //   65: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   68: aload 4
    //   70: getfield 1563	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   73: invokevirtual 1567	java/util/Vector:indexOf	(Ljava/lang/Object;)I
    //   76: istore_1
    //   77: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   80: iload_1
    //   81: invokevirtual 1570	java/util/Vector:get	(I)Ljava/lang/Object;
    //   84: checkcast 349	java/lang/String
    //   87: invokestatic 1576	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   90: pop
    //   91: new 122	java/util/Vector
    //   94: dup
    //   95: invokespecial 807	java/util/Vector:<init>	()V
    //   98: astore_2
    //   99: aload 4
    //   101: getfield 1579	android/content/pm/ActivityInfo:configChanges	I
    //   104: sipush 128
    //   107: iand
    //   108: sipush 128
    //   111: if_icmpeq +11 -> 122
    //   114: aload_2
    //   115: ldc_w 1581
    //   118: invokevirtual 819	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   121: pop
    //   122: aload 4
    //   124: getfield 1579	android/content/pm/ActivityInfo:configChanges	I
    //   127: bipush 32
    //   129: iand
    //   130: bipush 32
    //   132: if_icmpeq +11 -> 143
    //   135: aload_2
    //   136: ldc_w 1583
    //   139: invokevirtual 819	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   142: pop
    //   143: aload_2
    //   144: invokevirtual 1584	java/util/Vector:size	()I
    //   147: ifeq +115 -> 262
    //   150: aload_2
    //   151: invokevirtual 1584	java/util/Vector:size	()I
    //   154: iconst_1
    //   155: if_icmpne +55 -> 210
    //   158: new 401	java/lang/StringBuilder
    //   161: dup
    //   162: invokespecial 558	java/lang/StringBuilder:<init>	()V
    //   165: astore_3
    //   166: aload_3
    //   167: aload_2
    //   168: invokevirtual 1585	java/util/Vector:toString	()Ljava/lang/String;
    //   171: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: pop
    //   175: aload_3
    //   176: ldc_w 1587
    //   179: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: pop
    //   183: aload_3
    //   184: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   187: iload_1
    //   188: invokevirtual 1570	java/util/Vector:get	(I)Ljava/lang/Object;
    //   191: checkcast 349	java/lang/String
    //   194: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: pop
    //   198: new 251	com/tapjoy/TapjoyIntegrationException
    //   201: dup
    //   202: aload_3
    //   203: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   206: invokespecial 1588	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   209: athrow
    //   210: new 401	java/lang/StringBuilder
    //   213: dup
    //   214: invokespecial 558	java/lang/StringBuilder:<init>	()V
    //   217: astore_3
    //   218: aload_3
    //   219: aload_2
    //   220: invokevirtual 1585	java/util/Vector:toString	()Ljava/lang/String;
    //   223: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: pop
    //   227: aload_3
    //   228: ldc_w 1590
    //   231: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: pop
    //   235: aload_3
    //   236: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   239: iload_1
    //   240: invokevirtual 1570	java/util/Vector:get	(I)Ljava/lang/Object;
    //   243: checkcast 349	java/lang/String
    //   246: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   249: pop
    //   250: new 251	com/tapjoy/TapjoyIntegrationException
    //   253: dup
    //   254: aload_3
    //   255: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   258: invokespecial 1588	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   261: athrow
    //   262: getstatic 385	android/os/Build$VERSION:SDK_INT	I
    //   265: bipush 13
    //   267: if_icmplt +54 -> 321
    //   270: aload 4
    //   272: getfield 1579	android/content/pm/ActivityInfo:configChanges	I
    //   275: sipush 1024
    //   278: iand
    //   279: sipush 1024
    //   282: if_icmpeq +39 -> 321
    //   285: new 401	java/lang/StringBuilder
    //   288: dup
    //   289: ldc_w 1592
    //   292: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   295: astore_2
    //   296: aload_2
    //   297: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   300: iload_1
    //   301: invokevirtual 1570	java/util/Vector:get	(I)Ljava/lang/Object;
    //   304: checkcast 349	java/lang/String
    //   307: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: pop
    //   311: ldc_w 415
    //   314: aload_2
    //   315: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   318: invokestatic 619	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   321: getstatic 385	android/os/Build$VERSION:SDK_INT	I
    //   324: bipush 11
    //   326: if_icmplt +73 -> 399
    //   329: aload 4
    //   331: getfield 1563	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   334: ldc_w 1594
    //   337: invokevirtual 509	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   340: ifeq +59 -> 399
    //   343: aload 4
    //   345: getfield 1595	android/content/pm/ActivityInfo:flags	I
    //   348: sipush 512
    //   351: iand
    //   352: sipush 512
    //   355: if_icmpne +6 -> 361
    //   358: goto +41 -> 399
    //   361: new 401	java/lang/StringBuilder
    //   364: dup
    //   365: ldc_w 1597
    //   368: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   371: astore_2
    //   372: aload_2
    //   373: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   376: iload_1
    //   377: invokevirtual 1570	java/util/Vector:get	(I)Ljava/lang/Object;
    //   380: checkcast 349	java/lang/String
    //   383: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   386: pop
    //   387: new 251	com/tapjoy/TapjoyIntegrationException
    //   390: dup
    //   391: aload_2
    //   392: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   395: invokespecial 1588	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   398: athrow
    //   399: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   402: iload_1
    //   403: invokevirtual 1599	java/util/Vector:remove	(I)Ljava/lang/Object;
    //   406: pop
    //   407: goto -376 -> 31
    //   410: new 401	java/lang/StringBuilder
    //   413: dup
    //   414: ldc_w 1601
    //   417: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   420: astore_2
    //   421: aload_2
    //   422: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   425: iload_1
    //   426: invokevirtual 1570	java/util/Vector:get	(I)Ljava/lang/Object;
    //   429: checkcast 349	java/lang/String
    //   432: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: pop
    //   436: new 251	com/tapjoy/TapjoyIntegrationException
    //   439: dup
    //   440: aload_2
    //   441: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   444: invokespecial 1588	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   447: athrow
    //   448: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   451: invokevirtual 1584	java/util/Vector:size	()I
    //   454: ifeq +119 -> 573
    //   457: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   460: invokevirtual 1584	java/util/Vector:size	()I
    //   463: iconst_1
    //   464: if_icmpne +56 -> 520
    //   467: new 401	java/lang/StringBuilder
    //   470: dup
    //   471: ldc_w 1603
    //   474: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   477: astore_2
    //   478: aload_2
    //   479: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   482: invokevirtual 1584	java/util/Vector:size	()I
    //   485: invokevirtual 527	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   488: pop
    //   489: aload_2
    //   490: ldc_w 1605
    //   493: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   496: pop
    //   497: aload_2
    //   498: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   501: invokevirtual 1585	java/util/Vector:toString	()Ljava/lang/String;
    //   504: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   507: pop
    //   508: new 251	com/tapjoy/TapjoyIntegrationException
    //   511: dup
    //   512: aload_2
    //   513: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   516: invokespecial 1588	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   519: athrow
    //   520: new 401	java/lang/StringBuilder
    //   523: dup
    //   524: ldc_w 1603
    //   527: invokespecial 406	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   530: astore_2
    //   531: aload_2
    //   532: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   535: invokevirtual 1584	java/util/Vector:size	()I
    //   538: invokevirtual 527	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   541: pop
    //   542: aload_2
    //   543: ldc_w 1607
    //   546: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   549: pop
    //   550: aload_2
    //   551: getstatic 140	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   554: invokevirtual 1585	java/util/Vector:toString	()Ljava/lang/String;
    //   557: invokevirtual 413	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: pop
    //   561: new 251	com/tapjoy/TapjoyIntegrationException
    //   564: dup
    //   565: aload_2
    //   566: invokevirtual 416	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   569: invokespecial 1588	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   572: athrow
    //   573: invokestatic 1609	com/tapjoy/TapjoyConnectCore:k	()V
    //   576: ldc_w 1611
    //   579: invokestatic 1576	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   582: astore_2
    //   583: aload_2
    //   584: ldc_w 1613
    //   587: iconst_1
    //   588: anewarray 1572	java/lang/Class
    //   591: dup
    //   592: iconst_0
    //   593: ldc_w 697
    //   596: aastore
    //   597: invokevirtual 1617	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   600: pop
    //   601: ldc_w 1619
    //   604: invokestatic 1622	com/tapjoy/TapjoyUtil:getResource	(Ljava/lang/String;)Ljava/lang/Object;
    //   607: checkcast 349	java/lang/String
    //   610: astore_3
    //   611: aload_3
    //   612: astore_2
    //   613: aload_3
    //   614: ifnonnull +13 -> 627
    //   617: ldc_w 1624
    //   620: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   623: invokestatic 1628	com/tapjoy/TapjoyUtil:copyTextFromJarIntoString	(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   626: astore_2
    //   627: aload_2
    //   628: astore_3
    //   629: aload_2
    //   630: ifnonnull +71 -> 701
    //   633: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   636: invokevirtual 301	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   639: ldc_w 1630
    //   642: ldc_w 1632
    //   645: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   648: invokevirtual 307	android/content/Context:getPackageName	()Ljava/lang/String;
    //   651: invokevirtual 313	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   654: istore_1
    //   655: getstatic 266	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   658: invokevirtual 301	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   661: iload_1
    //   662: invokevirtual 320	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   665: astore_3
    //   666: aload_3
    //   667: invokevirtual 1637	java/io/InputStream:available	()I
    //   670: newarray byte
    //   672: astore 4
    //   674: aload_3
    //   675: aload 4
    //   677: invokevirtual 1641	java/io/InputStream:read	([B)I
    //   680: pop
    //   681: new 349	java/lang/String
    //   684: dup
    //   685: aload 4
    //   687: invokespecial 1644	java/lang/String:<init>	([B)V
    //   690: astore_3
    //   691: ldc_w 1619
    //   694: aload_3
    //   695: invokestatic 1648	com/tapjoy/TapjoyUtil:setResource	(Ljava/lang/String;Ljava/lang/Object;)V
    //   698: goto +3 -> 701
    //   701: aload_3
    //   702: ifnull +45 -> 747
    //   705: ldc_w 663
    //   708: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   711: ifnull +28 -> 739
    //   714: ldc_w 663
    //   717: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   720: ldc_w 825
    //   723: invokevirtual 509	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   726: ifeq +13 -> 739
    //   729: ldc_w 415
    //   732: ldc_w 1650
    //   735: invokestatic 529	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   738: return
    //   739: aload_0
    //   740: getfield 292	com/tapjoy/TapjoyConnectCore:af	Lcom/tapjoy/TapjoyGpsHelper;
    //   743: invokevirtual 1653	com/tapjoy/TapjoyGpsHelper:checkGooglePlayIntegration	()V
    //   746: return
    //   747: new 251	com/tapjoy/TapjoyIntegrationException
    //   750: dup
    //   751: ldc_w 1655
    //   754: invokespecial 1588	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   757: athrow
    //   758: new 251	com/tapjoy/TapjoyIntegrationException
    //   761: dup
    //   762: ldc_w 1657
    //   765: invokespecial 1588	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   768: athrow
    //   769: new 251	com/tapjoy/TapjoyIntegrationException
    //   772: dup
    //   773: ldc_w 1659
    //   776: invokespecial 1588	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   779: athrow
    //   780: new 251	com/tapjoy/TapjoyIntegrationException
    //   783: dup
    //   784: ldc_w 1661
    //   787: invokespecial 1588	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   790: athrow
    //   791: astore_2
    //   792: goto -12 -> 780
    //   795: astore_2
    //   796: goto -386 -> 410
    //   799: astore_2
    //   800: goto -31 -> 769
    //   803: astore_2
    //   804: goto -46 -> 758
    //   807: astore_3
    //   808: aload_2
    //   809: astore_3
    //   810: goto -109 -> 701
    //   813: astore_2
    //   814: goto -116 -> 698
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	817	0	this	TapjoyConnectCore
    //   76	586	1	i1	int
    //   19	611	2	localObject1	Object
    //   791	1	2	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   795	1	2	localClassNotFoundException1	ClassNotFoundException
    //   799	1	2	localClassNotFoundException2	ClassNotFoundException
    //   803	6	2	localNoSuchMethodException1	NoSuchMethodException
    //   813	1	2	localException1	Exception
    //   30	672	3	localObject2	Object
    //   807	1	3	localException2	Exception
    //   809	1	3	localNoSuchMethodException2	NoSuchMethodException
    //   49	637	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   0	20	791	android/content/pm/PackageManager$NameNotFoundException
    //   24	31	791	android/content/pm/PackageManager$NameNotFoundException
    //   31	77	791	android/content/pm/PackageManager$NameNotFoundException
    //   77	122	791	android/content/pm/PackageManager$NameNotFoundException
    //   122	143	791	android/content/pm/PackageManager$NameNotFoundException
    //   143	210	791	android/content/pm/PackageManager$NameNotFoundException
    //   210	262	791	android/content/pm/PackageManager$NameNotFoundException
    //   262	321	791	android/content/pm/PackageManager$NameNotFoundException
    //   321	358	791	android/content/pm/PackageManager$NameNotFoundException
    //   361	399	791	android/content/pm/PackageManager$NameNotFoundException
    //   399	407	791	android/content/pm/PackageManager$NameNotFoundException
    //   410	448	791	android/content/pm/PackageManager$NameNotFoundException
    //   77	122	795	java/lang/ClassNotFoundException
    //   122	143	795	java/lang/ClassNotFoundException
    //   143	210	795	java/lang/ClassNotFoundException
    //   210	262	795	java/lang/ClassNotFoundException
    //   262	321	795	java/lang/ClassNotFoundException
    //   321	358	795	java/lang/ClassNotFoundException
    //   361	399	795	java/lang/ClassNotFoundException
    //   399	407	795	java/lang/ClassNotFoundException
    //   576	583	799	java/lang/ClassNotFoundException
    //   583	601	803	java/lang/NoSuchMethodException
    //   633	691	807	java/lang/Exception
    //   691	698	813	java/lang/Exception
  }
  
  private static void k()
  {
    Vector localVector = new Vector();
    Object localObject = TapjoyConstants.dependencyPermissions;
    int i3 = localObject.length;
    int i2 = 0;
    int i1 = 0;
    String str;
    while (i1 < i3)
    {
      str = localObject[i1];
      if (!e(str)) {
        localVector.add(str);
      }
      i1 += 1;
    }
    if (localVector.size() != 0)
    {
      if (localVector.size() == 1)
      {
        localObject = new StringBuilder("Missing 1 permission in manifest: ");
        ((StringBuilder)localObject).append(localVector.toString());
        throw new TapjoyIntegrationException(((StringBuilder)localObject).toString());
      }
      localObject = new StringBuilder("Missing ");
      ((StringBuilder)localObject).append(localVector.size());
      ((StringBuilder)localObject).append(" permissions in manifest: ");
      ((StringBuilder)localObject).append(localVector.toString());
      throw new TapjoyIntegrationException(((StringBuilder)localObject).toString());
    }
    localVector = new Vector();
    localObject = TapjoyConstants.optionalPermissions;
    i3 = localObject.length;
    i1 = i2;
    while (i1 < i3)
    {
      str = localObject[i1];
      if (!e(str)) {
        localVector.add(str);
      }
      i1 += 1;
    }
    if (localVector.size() != 0)
    {
      if (localVector.size() == 1)
      {
        localObject = new StringBuilder("WARNING -- ");
        ((StringBuilder)localObject).append(localVector.toString());
        ((StringBuilder)localObject).append(" permission was not found in manifest. The exclusion of this permission could cause problems.");
        TapjoyLog.w("TapjoyConnect", ((StringBuilder)localObject).toString());
        return;
      }
      localObject = new StringBuilder("WARNING -- ");
      ((StringBuilder)localObject).append(localVector.toString());
      ((StringBuilder)localObject).append(" permissions were not found in manifest. The exclusion of these permissions could cause problems.");
      TapjoyLog.w("TapjoyConnect", ((StringBuilder)localObject).toString());
    }
  }
  
  private static boolean l()
  {
    return (c != null) && (c.length() > 0);
  }
  
  private static boolean m()
  {
    return (l()) && (getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS") != null) && (getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS").equals("true"));
  }
  
  private static String n()
  {
    TapjoyLog.i("TapjoyConnect", "generating sessionID...");
    Object localObject1 = null;
    Object localObject2;
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(System.currentTimeMillis() / 1000L);
      localStringBuilder.append(w);
      localStringBuilder.append(p);
      localObject2 = TapjoyUtil.SHA256(localStringBuilder.toString());
      try
      {
        ab = System.currentTimeMillis();
        return localObject2;
      }
      catch (Exception localException1)
      {
        localObject1 = localObject2;
      }
      localObject2 = new StringBuilder("unable to generate session id: ");
    }
    catch (Exception localException2) {}
    ((StringBuilder)localObject2).append(localException2.toString());
    TapjoyLog.e("TapjoyConnect", ((StringBuilder)localObject2).toString());
    return localObject1;
  }
  
  private static String o()
  {
    if (m()) {
      return c;
    }
    String str = p;
    int i2 = 1;
    int i1;
    if ((str != null) && (p.length() > 0)) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    if (i1 != 0) {
      return p;
    }
    if ((q != null) && (q.length() > 0)) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    if (i1 != 0) {
      return q;
    }
    if (l()) {
      return c;
    }
    if ((n != null) && (n.length() > 0)) {
      i1 = i2;
    } else {
      i1 = 0;
    }
    if (i1 != 0) {
      return n;
    }
    TapjoyLog.e("TapjoyConnect", "Error -- no valid device identifier");
    return null;
  }
  
  private void p()
  {
    TapjoyLog.d("TapjoyConnect", "Initializing mediation params");
    try
    {
      localObject = (ArrayList)ag.get("TJC_OPTION_MEDIATION_CONFIGS");
      if (localObject == null) {
        break label91;
      }
      TapjoyLog.i("TapjoyConnect", "Initializing mediation partners with configs");
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        new TapjoyConnectCore.3(this, (TJMediationNetwork)((Iterator)localObject).next()).run();
      }
    }
    catch (ClassCastException localClassCastException)
    {
      Object localObject;
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Invalid type! Make sure to pass in an ArrayList<TJMediationNetwork> type as your mediation configs."));
    label91:
    localObject = getConnectFlagValue("TJC_OPTION_MEDIATION_TIMEOUT");
    if ((localObject != null) && (!((String)localObject).isEmpty()))
    {
      TJMediationSettings.getInstance().setTimeout((String)localObject);
      localStringBuilder = new StringBuilder("Setting mediation timeout to ");
      localStringBuilder.append((String)localObject);
      localStringBuilder.append("s");
      TapjoyLog.i("TapjoyConnect", localStringBuilder.toString());
    }
  }
  
  private static String q()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(w);
    ((StringBuilder)localObject).append(x);
    ((StringBuilder)localObject).append(y);
    ((StringBuilder)localObject).append(c);
    ((StringBuilder)localObject).append(p);
    ((StringBuilder)localObject).append(r);
    localObject = ((StringBuilder)localObject).toString();
    try
    {
      String str = TapjoyUtil.SHA1((String)localObject);
      return str;
    }
    catch (Exception localException) {}
    return localObject;
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
    try
    {
      er localEr = new er(paramString);
      er.a localA1 = localEr.a;
      er.a localA2 = er.a.SDK_ANDROID;
      if (localA1 == localA2)
      {
        h = paramString;
        w = localEr.b;
        N = localEr.c;
        O = localEr.d;
        gd.a(paramContext).j = paramString;
        if (paramHashtable != null)
        {
          ag.putAll(paramHashtable);
          fd.b().a(paramHashtable);
        }
        k = paramTJConnectListener;
        i = new TapjoyConnectCore(paramContext);
        return;
      }
      throw new IllegalArgumentException("The given API key was not for Android.");
    }
    catch (IllegalArgumentException paramContext)
    {
      throw new TapjoyIntegrationException(paramContext.getMessage());
    }
  }
  
  public static void setPlugin(String paramString)
  {
    P = paramString;
  }
  
  public static void setSDKType(String paramString)
  {
    Q = paramString;
  }
  
  public static void setUserID(String paramString, TJSetUserIDListener paramTJSetUserIDListener)
  {
    D = paramString;
    l = paramTJSetUserIDListener;
    paramString = new StringBuilder("URL parameters: ");
    paramString.append(getURLParams());
    TapjoyLog.d("TapjoyConnect", paramString.toString());
    new Thread(new TapjoyConnectCore.2()).start();
  }
  
  public static void setViewShowing(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      ai.put("", Integer.valueOf(1));
      return;
    }
    ai.clear();
  }
  
  public static void viewDidClose(String paramString)
  {
    TapjoyLog.d("TapjoyConnect", "viewDidClose: ".concat(String.valueOf(paramString)));
    ai.remove(paramString);
    ev.e.notifyObservers();
  }
  
  public static void viewWillOpen(String paramString, int paramInt)
  {
    TapjoyLog.d("TapjoyConnect", "viewWillOpen: ".concat(String.valueOf(paramString)));
    ai.put(paramString, Integer.valueOf(paramInt));
  }
  
  public void actionComplete(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "actionComplete: ".concat(String.valueOf(paramString)));
    Map localMap = e();
    TapjoyUtil.safePut(localMap, "app_id", paramString, true);
    localMap.putAll(getTimeStampAndVerifierParams());
    TapjoyLog.d("TapjoyConnect", "PPA URL parameters: ".concat(String.valueOf(localMap)));
    new Thread(new TapjoyConnectCore.PPAThread(this, localMap)).start();
  }
  
  public void appPause()
  {
    this.aa = true;
  }
  
  public void appResume()
  {
    if (this.aa)
    {
      n();
      this.aa = false;
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
    if (!isConnected())
    {
      localObject2 = TapjoyAppSettings.getInstance().getConnectResult(q(), y.b());
      if ((localObject2 != null) && (a((String)localObject2, true)))
      {
        TapjoyLog.i("TapjoyConnect", "Connect using stored connect result");
        ad = true;
        p();
        if (k != null) {
          k.onConnectSuccess();
        }
        ev.a.notifyObservers();
        i1 = 1;
        break label106;
      }
    }
    int i1 = 0;
    label106:
    Object localObject2 = j;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append("api/connect/v3.json?");
    localObject1 = ((TapjoyURLConnection)localObject2).getResponseFromURL(localStringBuilder.toString(), null, null, getURLParams());
    if ((localObject1 != null) && (((TapjoyHttpURLResponse)localObject1).statusCode == 200))
    {
      if (a(((TapjoyHttpURLResponse)localObject1).response, false))
      {
        TapjoyLog.i("TapjoyConnect", "Successfully connected to Tapjoy");
        ad = true;
        p();
        localObject1 = getGenericURLParams().entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          localStringBuilder = new StringBuilder();
          localStringBuilder.append((String)((Map.Entry)localObject2).getKey());
          localStringBuilder.append(": ");
          localStringBuilder.append((String)((Map.Entry)localObject2).getValue());
          TapjoyLog.d("TapjoyConnect", localStringBuilder.toString());
        }
        if (i1 == 0)
        {
          if (k != null) {
            k.onConnectSuccess();
          }
          ev.a.notifyObservers();
        }
        ev.b.notifyObservers(Boolean.TRUE);
      }
      else
      {
        if (i1 == 0) {
          d();
        }
        ev.b.notifyObservers(Boolean.FALSE);
      }
      if (ah.length() > 0)
      {
        localObject1 = getGenericURLParams();
        TapjoyUtil.safePut((Map)localObject1, "package_names", ah, true);
        long l1 = System.currentTimeMillis() / 1000L;
        localObject2 = a(l1, ah);
        TapjoyUtil.safePut((Map)localObject1, "timestamp", String.valueOf(l1), true);
        TapjoyUtil.safePut((Map)localObject1, "verifier", (String)localObject2, true);
        localObject2 = new TapjoyURLConnection();
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(getHostURL());
        localStringBuilder.append("apps_installed?");
        localObject1 = ((TapjoyURLConnection)localObject2).getResponseFromURL(localStringBuilder.toString(), (Map)localObject1);
        if ((localObject1 != null) && (((TapjoyHttpURLResponse)localObject1).statusCode == 200)) {
          TapjoyLog.d("TapjoyConnect", "Successfully pinged sdkless api.");
        }
      }
    }
    else
    {
      if (i1 == 0) {
        d();
      }
      ev.b.notifyObservers(Boolean.FALSE);
    }
  }
  
  public void fetchAdvertisingID()
  {
    new Thread(new TapjoyConnectCore.1(this)).start();
  }
  
  public float getCurrencyMultiplier()
  {
    return S;
  }
  
  public String getSerial()
  {
    Object localObject = null;
    try
    {
      Field localField = Class.forName("android.os.Build").getDeclaredField("SERIAL");
      if (!localField.isAccessible()) {
        localField.setAccessible(true);
      }
      String str = localField.get(Build.class).toString();
      try
      {
        TapjoyLog.d("TapjoyConnect", "serial: ".concat(String.valueOf(str)));
        return str;
      }
      catch (Exception localException1)
      {
        localObject = str;
      }
      TapjoyLog.e("TapjoyConnect", localException2.toString());
    }
    catch (Exception localException2) {}
    return localObject;
  }
  
  public boolean isInitialized()
  {
    return this.ac;
  }
  
  public void release()
  {
    i = null;
    j = null;
    TapjoyLog.d("TapjoyConnect", "Releasing core static instance.");
  }
  
  public void setCurrencyMultiplier(float paramFloat)
  {
    TapjoyLog.i("TapjoyConnect", "setVirtualCurrencyMultiplier: ".concat(String.valueOf(paramFloat)));
    S = paramFloat;
  }
}
