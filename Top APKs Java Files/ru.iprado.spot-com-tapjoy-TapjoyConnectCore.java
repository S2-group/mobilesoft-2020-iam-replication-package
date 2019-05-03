package com.tapjoy;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
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
import com.tapjoy.internal.gb;
import com.tapjoy.internal.ge;
import com.tapjoy.internal.gl;
import com.tapjoy.internal.gp;
import com.tapjoy.internal.j;
import com.tapjoy.internal.q;
import com.tapjoy.internal.y;
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
  private static float A = 0.0F;
  private static int B = 0;
  private static String C;
  private static String D;
  public static final float DEFAULT_CURRENCY_MULTIPLIER = 1.0F;
  private static String E;
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
  private static float Q;
  private static boolean R;
  private static String S;
  private static String T;
  private static String U;
  private static String V;
  private static String W;
  private static long Z;
  protected static int a;
  private static Integer aA;
  private static Long aB;
  private static Long aC;
  private static Long aD;
  private static String aE;
  private static Integer aF;
  private static Double aG;
  private static Double aH;
  private static Long aI;
  private static Integer aJ;
  private static Integer aK;
  private static Integer aL;
  private static String aM;
  private static String aN;
  private static String aO;
  private static boolean ab;
  private static PackageManager ac;
  private static Hashtable ae = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
  private static String af = "";
  private static Map ag = new ConcurrentHashMap();
  private static String ah;
  private static String ai;
  private static String aj;
  private static String ak;
  private static Integer al;
  private static String am;
  private static String an;
  private static Long ao;
  private static String ap;
  private static Integer aq;
  private static Integer ar;
  private static String as;
  private static String at;
  private static String au;
  private static String av;
  private static String aw;
  private static Set ax;
  private static Integer ay;
  private static Integer az;
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
  private static int z = 1;
  private long X;
  private boolean Y;
  private boolean aa;
  private TapjoyGpsHelper ad;
  
  static
  {
    A = 1.0F;
    B = 1;
    C = "";
    D = "";
    E = "";
    F = "";
    G = "";
    H = "";
    I = "";
    J = "";
    K = "";
    L = "";
    M = "";
    N = "native";
    O = "";
    P = "";
    Q = 1.0F;
    R = false;
    S = "";
    T = "";
    U = "";
    V = "";
    W = null;
    Z = 0L;
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
    //   1: invokespecial 271	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 273	com/tapjoy/TapjoyConnectCore:X	J
    //   9: aload_0
    //   10: iconst_0
    //   11: putfield 275	com/tapjoy/TapjoyConnectCore:Y	Z
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 277	com/tapjoy/TapjoyConnectCore:aa	Z
    //   19: aload_1
    //   20: putstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   23: invokestatic 282	com/tapjoy/internal/fd:a	()Lcom/tapjoy/internal/fd;
    //   26: aload_1
    //   27: invokevirtual 284	com/tapjoy/internal/fd:a	(Landroid/content/Context;)V
    //   30: new 286	com/tapjoy/TapjoyURLConnection
    //   33: dup
    //   34: invokespecial 287	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   37: putstatic 133	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   40: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   43: invokevirtual 293	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   46: putstatic 295	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   49: aload_0
    //   50: new 297	com/tapjoy/TapjoyGpsHelper
    //   53: dup
    //   54: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   57: invokespecial 299	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   60: putfield 301	com/tapjoy/TapjoyConnectCore:ad	Lcom/tapjoy/TapjoyGpsHelper;
    //   63: getstatic 252	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Hashtable;
    //   66: ifnonnull +13 -> 79
    //   69: new 303	java/util/Hashtable
    //   72: dup
    //   73: invokespecial 304	java/util/Hashtable:<init>	()V
    //   76: putstatic 252	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Hashtable;
    //   79: invokestatic 306	com/tapjoy/TapjoyConnectCore:i	()V
    //   82: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   85: invokevirtual 310	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   88: ldc_w 312
    //   91: aconst_null
    //   92: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   95: invokevirtual 316	android/content/Context:getPackageName	()Ljava/lang/String;
    //   98: invokevirtual 322	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   101: istore_2
    //   102: new 324	java/util/Properties
    //   105: dup
    //   106: invokespecial 325	java/util/Properties:<init>	()V
    //   109: astore_1
    //   110: aload_1
    //   111: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   114: invokevirtual 310	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   117: iload_2
    //   118: invokevirtual 329	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   121: invokevirtual 333	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   124: aload_1
    //   125: invokestatic 336	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   128: ldc_w 338
    //   131: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   134: invokestatic 347	com/tapjoy/internal/ct:c	(Ljava/lang/String;)Z
    //   137: ifeq +7 -> 144
    //   140: aload_0
    //   141: invokespecial 349	com/tapjoy/TapjoyConnectCore:j	()V
    //   144: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   147: invokevirtual 353	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   150: ldc_w 355
    //   153: invokestatic 361	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   156: astore_1
    //   157: aload_1
    //   158: putstatic 161	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   161: aload_1
    //   162: ifnull +12 -> 174
    //   165: getstatic 161	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   168: invokevirtual 366	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   171: putstatic 161	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   174: getstatic 295	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   177: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   180: invokevirtual 316	android/content/Context:getPackageName	()Ljava/lang/String;
    //   183: iconst_0
    //   184: invokevirtual 372	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   187: getfield 377	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   190: putstatic 179	com/tapjoy/TapjoyConnectCore:w	Ljava/lang/String;
    //   193: ldc_w 379
    //   196: putstatic 173	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   199: ldc_w 379
    //   202: putstatic 193	com/tapjoy/TapjoyConnectCore:D	Ljava/lang/String;
    //   205: getstatic 384	android/os/Build:MODEL	Ljava/lang/String;
    //   208: putstatic 169	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   211: getstatic 387	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   214: putstatic 171	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   217: getstatic 392	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   220: putstatic 175	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   223: ldc_w 394
    //   226: putstatic 181	com/tapjoy/TapjoyConnectCore:x	Ljava/lang/String;
    //   229: ldc_w 396
    //   232: putstatic 183	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   235: getstatic 399	android/os/Build$VERSION:SDK_INT	I
    //   238: iconst_3
    //   239: if_icmple +35 -> 274
    //   242: new 401	com/tapjoy/TapjoyDisplayMetricsUtil
    //   245: dup
    //   246: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   249: invokespecial 402	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   252: astore_1
    //   253: aload_1
    //   254: invokevirtual 406	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   257: putstatic 185	com/tapjoy/TapjoyConnectCore:z	I
    //   260: aload_1
    //   261: invokevirtual 410	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   264: putstatic 187	com/tapjoy/TapjoyConnectCore:A	F
    //   267: aload_1
    //   268: invokevirtual 413	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   271: putstatic 189	com/tapjoy/TapjoyConnectCore:B	I
    //   274: ldc_w 415
    //   277: invokestatic 417	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   280: istore_3
    //   281: iload_3
    //   282: ifeq +665 -> 947
    //   285: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   288: ldc_w 419
    //   291: invokevirtual 423	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   294: checkcast 425	android/net/wifi/WifiManager
    //   297: astore_1
    //   298: aload_1
    //   299: ifnull +42 -> 341
    //   302: aload_1
    //   303: invokevirtual 429	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   306: astore_1
    //   307: aload_1
    //   308: ifnull +33 -> 341
    //   311: aload_1
    //   312: invokevirtual 434	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   315: astore_1
    //   316: aload_1
    //   317: putstatic 165	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   320: aload_1
    //   321: ifnull +20 -> 341
    //   324: getstatic 165	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   327: ldc_w 436
    //   330: ldc -97
    //   332: invokevirtual 440	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   335: invokevirtual 366	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   338: putstatic 165	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   341: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   344: ldc_w 442
    //   347: invokevirtual 423	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   350: checkcast 444	android/telephony/TelephonyManager
    //   353: astore_1
    //   354: aload_1
    //   355: ifnull +60 -> 415
    //   358: aload_1
    //   359: invokevirtual 447	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   362: putstatic 195	com/tapjoy/TapjoyConnectCore:E	Ljava/lang/String;
    //   365: aload_1
    //   366: invokevirtual 450	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   369: putstatic 197	com/tapjoy/TapjoyConnectCore:F	Ljava/lang/String;
    //   372: aload_1
    //   373: invokevirtual 453	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   376: astore_1
    //   377: aload_1
    //   378: ifnull +37 -> 415
    //   381: aload_1
    //   382: invokevirtual 456	java/lang/String:length	()I
    //   385: iconst_5
    //   386: if_icmpeq +12 -> 398
    //   389: aload_1
    //   390: invokevirtual 456	java/lang/String:length	()I
    //   393: bipush 6
    //   395: if_icmpne +20 -> 415
    //   398: aload_1
    //   399: iconst_0
    //   400: iconst_3
    //   401: invokevirtual 460	java/lang/String:substring	(II)Ljava/lang/String;
    //   404: putstatic 199	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   407: aload_1
    //   408: iconst_3
    //   409: invokevirtual 463	java/lang/String:substring	(I)Ljava/lang/String;
    //   412: putstatic 201	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   415: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   418: ldc_w 465
    //   421: iconst_0
    //   422: invokevirtual 469	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   425: astore_1
    //   426: aload_1
    //   427: ldc_w 471
    //   430: ldc -97
    //   432: invokeinterface 476 3 0
    //   437: astore 4
    //   439: aload 4
    //   441: putstatic 167	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   444: aload 4
    //   446: ifnull +14 -> 460
    //   449: getstatic 167	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   452: invokevirtual 456	java/lang/String:length	()I
    //   455: istore_2
    //   456: iload_2
    //   457: ifne +61 -> 518
    //   460: new 478	java/lang/StringBuilder
    //   463: dup
    //   464: invokespecial 479	java/lang/StringBuilder:<init>	()V
    //   467: invokestatic 485	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   470: invokevirtual 488	java/util/UUID:toString	()Ljava/lang/String;
    //   473: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   476: invokestatic 498	java/lang/System:currentTimeMillis	()J
    //   479: invokevirtual 501	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   482: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   485: invokestatic 507	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   488: putstatic 167	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   491: aload_1
    //   492: invokeinterface 511 1 0
    //   497: astore_1
    //   498: aload_1
    //   499: ldc_w 471
    //   502: getstatic 167	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   505: invokeinterface 517 3 0
    //   510: pop
    //   511: aload_1
    //   512: invokeinterface 521 1 0
    //   517: pop
    //   518: ldc_w 523
    //   521: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   524: ifnull +71 -> 595
    //   527: ldc_w 523
    //   530: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   533: invokevirtual 456	java/lang/String:length	()I
    //   536: ifle +59 -> 595
    //   539: ldc_w 523
    //   542: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   545: putstatic 207	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   548: new 525	java/util/ArrayList
    //   551: dup
    //   552: getstatic 528	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   555: invokestatic 151	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   558: invokespecial 529	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   561: getstatic 207	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   564: invokevirtual 533	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   567: ifne +28 -> 595
    //   570: ldc_w 535
    //   573: new 478	java/lang/StringBuilder
    //   576: dup
    //   577: ldc_w 537
    //   580: invokespecial 540	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   583: getstatic 207	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   586: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   589: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   592: invokestatic 545	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   595: getstatic 207	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   598: astore_1
    //   599: new 547	android/content/Intent
    //   602: dup
    //   603: ldc_w 549
    //   606: invokespecial 550	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   609: astore 4
    //   611: aload_1
    //   612: invokevirtual 456	java/lang/String:length	()I
    //   615: ifgt +374 -> 989
    //   618: aload 4
    //   620: ldc_w 552
    //   623: invokestatic 558	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   626: invokevirtual 562	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   629: pop
    //   630: getstatic 295	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   633: aload 4
    //   635: iconst_0
    //   636: invokevirtual 566	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   639: invokeinterface 571 1 0
    //   644: ifle +419 -> 1063
    //   647: iconst_1
    //   648: istore_3
    //   649: iload_3
    //   650: putstatic 223	com/tapjoy/TapjoyConnectCore:R	Z
    //   653: invokestatic 573	com/tapjoy/TapjoyConnectCore:g	()V
    //   656: ldc_w 575
    //   659: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   662: ifnull +24 -> 686
    //   665: ldc_w 575
    //   668: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   671: invokevirtual 456	java/lang/String:length	()I
    //   674: ifle +12 -> 686
    //   677: ldc_w 575
    //   680: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   683: putstatic 245	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   686: ldc_w 577
    //   689: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   692: ifnull +24 -> 716
    //   695: ldc_w 577
    //   698: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   701: invokevirtual 456	java/lang/String:length	()I
    //   704: ifle +12 -> 716
    //   707: ldc_w 577
    //   710: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   713: putstatic 243	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   716: ldc_w 579
    //   719: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   722: ifnull +53 -> 775
    //   725: ldc_w 579
    //   728: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   731: invokevirtual 456	java/lang/String:length	()I
    //   734: ifle +41 -> 775
    //   737: ldc_w 535
    //   740: new 478	java/lang/StringBuilder
    //   743: dup
    //   744: ldc_w 581
    //   747: invokespecial 540	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   750: ldc_w 579
    //   753: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   756: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   759: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   762: invokestatic 583	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   765: ldc_w 579
    //   768: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   771: aconst_null
    //   772: invokestatic 587	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;Lcom/tapjoy/TJSetUserIDListener;)V
    //   775: ldc_w 589
    //   778: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   781: invokestatic 592	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   784: putstatic 219	com/tapjoy/TapjoyConnectCore:P	Ljava/lang/String;
    //   787: getstatic 252	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Hashtable;
    //   790: ifnull +6 -> 796
    //   793: invokestatic 594	com/tapjoy/TapjoyConnectCore:h	()V
    //   796: aload_0
    //   797: invokevirtual 597	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   800: aload_0
    //   801: iconst_1
    //   802: putfield 277	com/tapjoy/TapjoyConnectCore:aa	Z
    //   805: return
    //   806: astore_1
    //   807: new 266	com/tapjoy/TapjoyException
    //   810: dup
    //   811: aload_1
    //   812: invokevirtual 600	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   815: invokespecial 601	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   818: athrow
    //   819: astore_1
    //   820: ldc_w 535
    //   823: new 603	com/tapjoy/TapjoyErrorMessage
    //   826: dup
    //   827: getstatic 609	com/tapjoy/TapjoyErrorMessage$ErrorType:INTEGRATION_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   830: aload_1
    //   831: invokevirtual 610	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   834: invokespecial 613	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   837: invokestatic 616	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   840: invokestatic 618	com/tapjoy/TapjoyConnectCore:d	()V
    //   843: getstatic 623	com/tapjoy/internal/ev:b	Lcom/tapjoy/internal/ev$a;
    //   846: getstatic 629	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   849: invokevirtual 635	com/tapjoy/internal/ev$a:notifyObservers	(Ljava/lang/Object;)V
    //   852: return
    //   853: astore_1
    //   854: ldc_w 535
    //   857: new 478	java/lang/StringBuilder
    //   860: dup
    //   861: ldc_w 637
    //   864: invokespecial 540	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   867: aload_1
    //   868: invokevirtual 638	java/lang/Exception:toString	()Ljava/lang/String;
    //   871: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   874: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   877: invokestatic 640	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   880: goto -606 -> 274
    //   883: astore_1
    //   884: ldc_w 535
    //   887: new 603	com/tapjoy/TapjoyErrorMessage
    //   890: dup
    //   891: getstatic 643	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   894: aload_1
    //   895: invokevirtual 644	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   898: invokespecial 613	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   901: invokestatic 616	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   904: invokestatic 618	com/tapjoy/TapjoyConnectCore:d	()V
    //   907: getstatic 623	com/tapjoy/internal/ev:b	Lcom/tapjoy/internal/ev$a;
    //   910: getstatic 629	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   913: invokevirtual 635	com/tapjoy/internal/ev$a:notifyObservers	(Ljava/lang/Object;)V
    //   916: return
    //   917: astore_1
    //   918: ldc_w 535
    //   921: new 478	java/lang/StringBuilder
    //   924: dup
    //   925: ldc_w 646
    //   928: invokespecial 540	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   931: aload_1
    //   932: invokevirtual 638	java/lang/Exception:toString	()Ljava/lang/String;
    //   935: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   938: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   941: invokestatic 640	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   944: goto -603 -> 341
    //   947: ldc_w 535
    //   950: ldc_w 648
    //   953: invokestatic 650	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   956: goto -615 -> 341
    //   959: astore_1
    //   960: ldc_w 535
    //   963: new 478	java/lang/StringBuilder
    //   966: dup
    //   967: ldc_w 652
    //   970: invokespecial 540	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   973: aload_1
    //   974: invokevirtual 638	java/lang/Exception:toString	()Ljava/lang/String;
    //   977: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   980: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   983: invokestatic 640	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   986: goto -468 -> 518
    //   989: aload_1
    //   990: ldc_w 654
    //   993: invokevirtual 657	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   996: ifeq +13 -> 1009
    //   999: ldc_w 659
    //   1002: invokestatic 661	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1005: istore_3
    //   1006: goto -357 -> 649
    //   1009: aload_1
    //   1010: ldc_w 663
    //   1013: invokevirtual 657	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1016: ifeq +47 -> 1063
    //   1019: ldc_w 665
    //   1022: invokestatic 661	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1025: istore_3
    //   1026: goto -377 -> 649
    //   1029: astore_1
    //   1030: ldc_w 535
    //   1033: new 478	java/lang/StringBuilder
    //   1036: dup
    //   1037: ldc_w 667
    //   1040: invokespecial 540	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1043: aload_1
    //   1044: invokevirtual 638	java/lang/Exception:toString	()Ljava/lang/String;
    //   1047: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1050: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1053: invokestatic 640	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1056: goto -403 -> 653
    //   1059: astore_1
    //   1060: goto -932 -> 128
    //   1063: iconst_0
    //   1064: istore_3
    //   1065: goto -416 -> 649
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1068	0	this	TapjoyConnectCore
    //   0	1068	1	paramContext	Context
    //   101	356	2	i1	int
    //   280	785	3	bool	boolean
    //   437	197	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   174	193	806	android/content/pm/PackageManager$NameNotFoundException
    //   63	79	819	com/tapjoy/TapjoyIntegrationException
    //   79	110	819	com/tapjoy/TapjoyIntegrationException
    //   110	128	819	com/tapjoy/TapjoyIntegrationException
    //   128	144	819	com/tapjoy/TapjoyIntegrationException
    //   144	161	819	com/tapjoy/TapjoyIntegrationException
    //   165	174	819	com/tapjoy/TapjoyIntegrationException
    //   174	193	819	com/tapjoy/TapjoyIntegrationException
    //   193	235	819	com/tapjoy/TapjoyIntegrationException
    //   235	274	819	com/tapjoy/TapjoyIntegrationException
    //   274	281	819	com/tapjoy/TapjoyIntegrationException
    //   285	298	819	com/tapjoy/TapjoyIntegrationException
    //   302	307	819	com/tapjoy/TapjoyIntegrationException
    //   311	320	819	com/tapjoy/TapjoyIntegrationException
    //   324	341	819	com/tapjoy/TapjoyIntegrationException
    //   341	354	819	com/tapjoy/TapjoyIntegrationException
    //   358	377	819	com/tapjoy/TapjoyIntegrationException
    //   381	398	819	com/tapjoy/TapjoyIntegrationException
    //   398	415	819	com/tapjoy/TapjoyIntegrationException
    //   415	444	819	com/tapjoy/TapjoyIntegrationException
    //   449	456	819	com/tapjoy/TapjoyIntegrationException
    //   460	518	819	com/tapjoy/TapjoyIntegrationException
    //   518	595	819	com/tapjoy/TapjoyIntegrationException
    //   595	647	819	com/tapjoy/TapjoyIntegrationException
    //   649	653	819	com/tapjoy/TapjoyIntegrationException
    //   653	686	819	com/tapjoy/TapjoyIntegrationException
    //   686	716	819	com/tapjoy/TapjoyIntegrationException
    //   716	775	819	com/tapjoy/TapjoyIntegrationException
    //   775	796	819	com/tapjoy/TapjoyIntegrationException
    //   796	805	819	com/tapjoy/TapjoyIntegrationException
    //   807	819	819	com/tapjoy/TapjoyIntegrationException
    //   854	880	819	com/tapjoy/TapjoyIntegrationException
    //   918	944	819	com/tapjoy/TapjoyIntegrationException
    //   947	956	819	com/tapjoy/TapjoyIntegrationException
    //   960	986	819	com/tapjoy/TapjoyIntegrationException
    //   989	1006	819	com/tapjoy/TapjoyIntegrationException
    //   1009	1026	819	com/tapjoy/TapjoyIntegrationException
    //   1030	1056	819	com/tapjoy/TapjoyIntegrationException
    //   235	274	853	java/lang/Exception
    //   63	79	883	com/tapjoy/TapjoyException
    //   79	110	883	com/tapjoy/TapjoyException
    //   110	128	883	com/tapjoy/TapjoyException
    //   128	144	883	com/tapjoy/TapjoyException
    //   144	161	883	com/tapjoy/TapjoyException
    //   165	174	883	com/tapjoy/TapjoyException
    //   174	193	883	com/tapjoy/TapjoyException
    //   193	235	883	com/tapjoy/TapjoyException
    //   235	274	883	com/tapjoy/TapjoyException
    //   274	281	883	com/tapjoy/TapjoyException
    //   285	298	883	com/tapjoy/TapjoyException
    //   302	307	883	com/tapjoy/TapjoyException
    //   311	320	883	com/tapjoy/TapjoyException
    //   324	341	883	com/tapjoy/TapjoyException
    //   341	354	883	com/tapjoy/TapjoyException
    //   358	377	883	com/tapjoy/TapjoyException
    //   381	398	883	com/tapjoy/TapjoyException
    //   398	415	883	com/tapjoy/TapjoyException
    //   415	444	883	com/tapjoy/TapjoyException
    //   449	456	883	com/tapjoy/TapjoyException
    //   460	518	883	com/tapjoy/TapjoyException
    //   518	595	883	com/tapjoy/TapjoyException
    //   595	647	883	com/tapjoy/TapjoyException
    //   649	653	883	com/tapjoy/TapjoyException
    //   653	686	883	com/tapjoy/TapjoyException
    //   686	716	883	com/tapjoy/TapjoyException
    //   716	775	883	com/tapjoy/TapjoyException
    //   775	796	883	com/tapjoy/TapjoyException
    //   796	805	883	com/tapjoy/TapjoyException
    //   807	819	883	com/tapjoy/TapjoyException
    //   854	880	883	com/tapjoy/TapjoyException
    //   918	944	883	com/tapjoy/TapjoyException
    //   947	956	883	com/tapjoy/TapjoyException
    //   960	986	883	com/tapjoy/TapjoyException
    //   989	1006	883	com/tapjoy/TapjoyException
    //   1009	1026	883	com/tapjoy/TapjoyException
    //   1030	1056	883	com/tapjoy/TapjoyException
    //   285	298	917	java/lang/Exception
    //   302	307	917	java/lang/Exception
    //   311	320	917	java/lang/Exception
    //   324	341	917	java/lang/Exception
    //   460	518	959	java/lang/Exception
    //   595	647	1029	java/lang/Exception
    //   649	653	1029	java/lang/Exception
    //   989	1006	1029	java/lang/Exception
    //   1009	1026	1029	java/lang/Exception
    //   110	128	1059	java/lang/Exception
  }
  
  private static String a(long paramLong)
  {
    try
    {
      String str = TapjoyUtil.SHA256(v + ":" + o() + ":" + paramLong + ":" + L);
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
      paramString = TapjoyUtil.SHA256(v + ":" + o() + ":" + paramLong + ":" + L + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error in computing packageNamesVerifier -- " + paramString.toString()));
    }
    return "";
  }
  
  private static void a(String paramString1, String paramString2)
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
    ae.put(paramString1, str);
  }
  
  private static void a(List paramList)
  {
    try
    {
      af = "";
      Iterator localIterator = ac.getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (((localApplicationInfo.flags & 0x1) != 1) && (paramList.contains(localApplicationInfo.packageName)))
        {
          TapjoyLog.d("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
          if (af.length() > 0) {
            af += ",";
          }
          af += localApplicationInfo.packageName;
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
        a(str, (String)paramProperties.get(str));
      }
      catch (ClassCastException localClassCastException)
      {
        TapjoyLog.e("TapjoyConnect", "Error parsing configuration properties in tapjoy_config.txt");
      }
    }
  }
  
  /* Error */
  private static boolean a(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aload_0
    //   4: invokestatic 802	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   7: astore 6
    //   9: aload 6
    //   11: astore 7
    //   13: aload 6
    //   15: invokevirtual 805	com/tapjoy/internal/bs:d	()Ljava/util/Map;
    //   18: astore 10
    //   20: aload 6
    //   22: astore 7
    //   24: aload 10
    //   26: ldc_w 807
    //   29: invokeinterface 810 2 0
    //   34: checkcast 363	java/lang/String
    //   37: invokestatic 812	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   40: astore 8
    //   42: aload 6
    //   44: astore 7
    //   46: aload 10
    //   48: ldc_w 814
    //   51: invokeinterface 810 2 0
    //   56: checkcast 363	java/lang/String
    //   59: invokestatic 812	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   62: astore 13
    //   64: aload 6
    //   66: astore 7
    //   68: aload 10
    //   70: ldc_w 816
    //   73: invokeinterface 810 2 0
    //   78: checkcast 363	java/lang/String
    //   81: invokestatic 812	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   84: astore 14
    //   86: aload 6
    //   88: astore 7
    //   90: aload 10
    //   92: ldc_w 818
    //   95: invokeinterface 810 2 0
    //   100: checkcast 363	java/lang/String
    //   103: invokestatic 812	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   106: astore 15
    //   108: aload 6
    //   110: astore 7
    //   112: aload 10
    //   114: ldc_w 820
    //   117: invokeinterface 810 2 0
    //   122: checkcast 363	java/lang/String
    //   125: invokestatic 812	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   128: astore 12
    //   130: aload 6
    //   132: astore 7
    //   134: aload 10
    //   136: ldc_w 822
    //   139: invokeinterface 810 2 0
    //   144: astore 11
    //   146: aload 6
    //   148: astore 7
    //   150: new 824	com/tapjoy/internal/er
    //   153: dup
    //   154: aload 14
    //   156: invokespecial 825	com/tapjoy/internal/er:<init>	(Ljava/lang/String;)V
    //   159: astore 16
    //   161: aload 6
    //   163: astore 7
    //   165: aload 16
    //   167: getfield 828	com/tapjoy/internal/er:a	Lcom/tapjoy/internal/er$a;
    //   170: getstatic 833	com/tapjoy/internal/er$a:RPC_ANALYTICS	Lcom/tapjoy/internal/er$a;
    //   173: if_acmpeq +44 -> 217
    //   176: aload 6
    //   178: astore 7
    //   180: new 793	java/io/IOException
    //   183: dup
    //   184: ldc_w 835
    //   187: invokespecial 836	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   190: athrow
    //   191: astore 7
    //   193: aload 6
    //   195: astore_0
    //   196: aload 7
    //   198: astore 6
    //   200: ldc_w 535
    //   203: aload 6
    //   205: invokevirtual 837	java/io/IOException:getMessage	()Ljava/lang/String;
    //   208: invokestatic 839	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   211: aload_0
    //   212: invokestatic 844	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   215: iconst_0
    //   216: ireturn
    //   217: aload 6
    //   219: astore 7
    //   221: aload 16
    //   223: getfield 846	com/tapjoy/internal/er:b	Ljava/lang/String;
    //   226: astore 9
    //   228: aload 6
    //   230: astore 7
    //   232: aload 9
    //   234: bipush 13
    //   236: ldc_w 848
    //   239: iconst_0
    //   240: bipush 11
    //   242: invokevirtual 852	java/lang/String:regionMatches	(ILjava/lang/String;II)Z
    //   245: ifeq +234 -> 479
    //   248: aload 6
    //   250: astore 7
    //   252: new 854	java/lang/StringBuffer
    //   255: dup
    //   256: invokespecial 855	java/lang/StringBuffer:<init>	()V
    //   259: aload 9
    //   261: iconst_0
    //   262: bipush 8
    //   264: invokevirtual 460	java/lang/String:substring	(II)Ljava/lang/String;
    //   267: invokevirtual 858	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   270: aload 9
    //   272: bipush 24
    //   274: bipush 30
    //   276: invokevirtual 460	java/lang/String:substring	(II)Ljava/lang/String;
    //   279: invokevirtual 858	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   282: aload 9
    //   284: bipush 9
    //   286: bipush 13
    //   288: invokevirtual 460	java/lang/String:substring	(II)Ljava/lang/String;
    //   291: invokevirtual 858	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   294: aload 9
    //   296: bipush 30
    //   298: invokevirtual 463	java/lang/String:substring	(I)Ljava/lang/String;
    //   301: invokevirtual 858	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   304: invokevirtual 859	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   307: astore 9
    //   309: aload 6
    //   311: astore 7
    //   313: aload 16
    //   315: getfield 860	com/tapjoy/internal/er:c	Ljava/lang/String;
    //   318: astore 16
    //   320: aload 8
    //   322: ifnonnull +505 -> 827
    //   325: aload 9
    //   327: astore 8
    //   329: aload 6
    //   331: astore 7
    //   333: invokestatic 865	com/tapjoy/internal/gb:a	()Lcom/tapjoy/internal/gb;
    //   336: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   339: aload 14
    //   341: ldc_w 394
    //   344: ldc_w 867
    //   347: aload 9
    //   349: aload 16
    //   351: invokevirtual 870	com/tapjoy/internal/gb:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   354: aload 6
    //   356: astore 7
    //   358: aload 8
    //   360: putstatic 225	com/tapjoy/TapjoyConnectCore:S	Ljava/lang/String;
    //   363: aload 6
    //   365: astore 7
    //   367: aload 13
    //   369: putstatic 227	com/tapjoy/TapjoyConnectCore:T	Ljava/lang/String;
    //   372: aload 6
    //   374: astore 7
    //   376: aload 14
    //   378: putstatic 229	com/tapjoy/TapjoyConnectCore:U	Ljava/lang/String;
    //   381: aload 6
    //   383: astore 7
    //   385: aload 15
    //   387: putstatic 231	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   390: aload 6
    //   392: astore 7
    //   394: new 525	java/util/ArrayList
    //   397: dup
    //   398: invokespecial 871	java/util/ArrayList:<init>	()V
    //   401: astore 8
    //   403: aload 12
    //   405: ifnull +112 -> 517
    //   408: aload 6
    //   410: astore 7
    //   412: aload 12
    //   414: ldc_w 718
    //   417: invokevirtual 875	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   420: astore 9
    //   422: aload 6
    //   424: astore 7
    //   426: aload 9
    //   428: arraylength
    //   429: istore_3
    //   430: iconst_0
    //   431: istore_2
    //   432: iload_2
    //   433: iload_3
    //   434: if_icmpge +83 -> 517
    //   437: aload 6
    //   439: astore 7
    //   441: aload 9
    //   443: iload_2
    //   444: aaload
    //   445: invokevirtual 781	java/lang/String:trim	()Ljava/lang/String;
    //   448: astore 12
    //   450: aload 6
    //   452: astore 7
    //   454: aload 12
    //   456: invokevirtual 456	java/lang/String:length	()I
    //   459: ifle +371 -> 830
    //   462: aload 6
    //   464: astore 7
    //   466: aload 8
    //   468: aload 12
    //   470: invokeinterface 876 2 0
    //   475: pop
    //   476: goto +354 -> 830
    //   479: aload 6
    //   481: astore 7
    //   483: new 878	java/lang/IllegalArgumentException
    //   486: dup
    //   487: ldc_w 880
    //   490: invokespecial 881	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   493: athrow
    //   494: astore_0
    //   495: aload 6
    //   497: astore 7
    //   499: ldc_w 535
    //   502: aload_0
    //   503: invokevirtual 882	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   506: invokestatic 839	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   509: aload 6
    //   511: invokestatic 844	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   514: goto -299 -> 215
    //   517: aload 6
    //   519: astore 7
    //   521: aload 8
    //   523: invokeinterface 885 1 0
    //   528: ifne +12 -> 540
    //   531: aload 6
    //   533: astore 7
    //   535: aload 8
    //   537: invokestatic 786	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   540: aload 6
    //   542: astore 7
    //   544: aload 6
    //   546: invokevirtual 888	com/tapjoy/internal/bs:close	()V
    //   549: iload_1
    //   550: ifne +108 -> 658
    //   553: aload 11
    //   555: instanceof 363
    //   558: istore_1
    //   559: iload_1
    //   560: ifeq +112 -> 672
    //   563: aload 11
    //   565: checkcast 363	java/lang/String
    //   568: invokevirtual 781	java/lang/String:trim	()Ljava/lang/String;
    //   571: invokestatic 894	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   574: lstore 4
    //   576: lload 4
    //   578: lconst_0
    //   579: lcmp
    //   580: ifgt +123 -> 703
    //   583: invokestatic 900	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   586: invokevirtual 903	com/tapjoy/TapjoyAppSettings:removeConnectResult	()V
    //   589: invokestatic 282	com/tapjoy/internal/fd:a	()Lcom/tapjoy/internal/fd;
    //   592: astore_0
    //   593: aload 10
    //   595: ldc_w 905
    //   598: invokeinterface 810 2 0
    //   603: astore 6
    //   605: aload 6
    //   607: instanceof 809
    //   610: istore_1
    //   611: iload_1
    //   612: ifeq +114 -> 726
    //   615: aload_0
    //   616: getfield 908	com/tapjoy/internal/fd:a	Lcom/tapjoy/internal/fb;
    //   619: aload 6
    //   621: checkcast 809	java/util/Map
    //   624: invokevirtual 913	com/tapjoy/internal/fb:a	(Ljava/util/Map;)V
    //   627: aload 6
    //   629: invokestatic 918	com/tapjoy/internal/bm:a	(Ljava/lang/Object;)Ljava/lang/String;
    //   632: astore 6
    //   634: aload_0
    //   635: invokevirtual 921	com/tapjoy/internal/fd:c	()Landroid/content/SharedPreferences;
    //   638: invokeinterface 511 1 0
    //   643: ldc_w 905
    //   646: aload 6
    //   648: invokeinterface 517 3 0
    //   653: invokeinterface 924 1 0
    //   658: aconst_null
    //   659: invokestatic 844	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   662: iconst_1
    //   663: ireturn
    //   664: astore 6
    //   666: lconst_0
    //   667: lstore 4
    //   669: goto -93 -> 576
    //   672: aload 11
    //   674: instanceof 926
    //   677: istore_1
    //   678: iload_1
    //   679: ifeq +18 -> 697
    //   682: aload 11
    //   684: checkcast 926	java/lang/Number
    //   687: invokevirtual 929	java/lang/Number:longValue	()J
    //   690: lstore 4
    //   692: goto -116 -> 576
    //   695: astore 6
    //   697: lconst_0
    //   698: lstore 4
    //   700: goto -124 -> 576
    //   703: invokestatic 900	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   706: aload_0
    //   707: invokestatic 931	com/tapjoy/TapjoyConnectCore:p	()Ljava/lang/String;
    //   710: lload 4
    //   712: ldc2_w 932
    //   715: lmul
    //   716: invokestatic 937	com/tapjoy/internal/y:b	()J
    //   719: ladd
    //   720: invokevirtual 941	com/tapjoy/TapjoyAppSettings:saveConnectResultAndParams	(Ljava/lang/String;Ljava/lang/String;J)V
    //   723: goto -134 -> 589
    //   726: aload 6
    //   728: ifnonnull -70 -> 658
    //   731: aload_0
    //   732: getfield 908	com/tapjoy/internal/fd:a	Lcom/tapjoy/internal/fb;
    //   735: aconst_null
    //   736: invokevirtual 913	com/tapjoy/internal/fb:a	(Ljava/util/Map;)V
    //   739: aload_0
    //   740: invokevirtual 921	com/tapjoy/internal/fd:c	()Landroid/content/SharedPreferences;
    //   743: invokeinterface 511 1 0
    //   748: ldc_w 905
    //   751: invokeinterface 945 2 0
    //   756: invokeinterface 924 1 0
    //   761: goto -103 -> 658
    //   764: astore_0
    //   765: aconst_null
    //   766: astore 6
    //   768: goto -273 -> 495
    //   771: astore_0
    //   772: aconst_null
    //   773: astore 6
    //   775: aload 6
    //   777: invokestatic 844	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   780: aload_0
    //   781: athrow
    //   782: astore_0
    //   783: aload 7
    //   785: astore 6
    //   787: goto -12 -> 775
    //   790: astore_0
    //   791: aconst_null
    //   792: astore 6
    //   794: goto -19 -> 775
    //   797: astore 7
    //   799: aload_0
    //   800: astore 6
    //   802: aload 7
    //   804: astore_0
    //   805: goto -30 -> 775
    //   808: astore_0
    //   809: aconst_null
    //   810: astore 6
    //   812: goto -317 -> 495
    //   815: astore 6
    //   817: aload 7
    //   819: astore_0
    //   820: goto -620 -> 200
    //   823: astore_0
    //   824: goto -166 -> 658
    //   827: goto -498 -> 329
    //   830: iload_2
    //   831: iconst_1
    //   832: iadd
    //   833: istore_2
    //   834: goto -402 -> 432
    //   837: astore 6
    //   839: aconst_null
    //   840: astore_0
    //   841: goto -641 -> 200
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	844	0	paramString	String
    //   0	844	1	paramBoolean	boolean
    //   431	403	2	i1	int
    //   429	6	3	i2	int
    //   574	137	4	l1	long
    //   7	640	6	localObject1	Object
    //   664	1	6	localNumberFormatException1	NumberFormatException
    //   695	32	6	localNumberFormatException2	NumberFormatException
    //   766	45	6	localObject2	Object
    //   815	1	6	localIOException1	java.io.IOException
    //   837	1	6	localIOException2	java.io.IOException
    //   1	178	7	localObject3	Object
    //   191	6	7	localIOException3	java.io.IOException
    //   219	565	7	localObject4	Object
    //   797	21	7	localObject5	Object
    //   40	496	8	localObject6	Object
    //   226	216	9	localObject7	Object
    //   18	576	10	localMap	Map
    //   144	539	11	localObject8	Object
    //   128	341	12	str1	String
    //   62	306	13	str2	String
    //   84	293	14	str3	String
    //   106	280	15	str4	String
    //   159	191	16	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   13	20	191	java/io/IOException
    //   24	42	191	java/io/IOException
    //   46	64	191	java/io/IOException
    //   68	86	191	java/io/IOException
    //   90	108	191	java/io/IOException
    //   112	130	191	java/io/IOException
    //   134	146	191	java/io/IOException
    //   150	161	191	java/io/IOException
    //   165	176	191	java/io/IOException
    //   180	191	191	java/io/IOException
    //   221	228	191	java/io/IOException
    //   232	248	191	java/io/IOException
    //   252	309	191	java/io/IOException
    //   313	320	191	java/io/IOException
    //   333	354	191	java/io/IOException
    //   358	363	191	java/io/IOException
    //   367	372	191	java/io/IOException
    //   376	381	191	java/io/IOException
    //   385	390	191	java/io/IOException
    //   394	403	191	java/io/IOException
    //   412	422	191	java/io/IOException
    //   426	430	191	java/io/IOException
    //   441	450	191	java/io/IOException
    //   454	462	191	java/io/IOException
    //   466	476	191	java/io/IOException
    //   483	494	191	java/io/IOException
    //   521	531	191	java/io/IOException
    //   535	540	191	java/io/IOException
    //   544	549	191	java/io/IOException
    //   13	20	494	java/lang/RuntimeException
    //   24	42	494	java/lang/RuntimeException
    //   46	64	494	java/lang/RuntimeException
    //   68	86	494	java/lang/RuntimeException
    //   90	108	494	java/lang/RuntimeException
    //   112	130	494	java/lang/RuntimeException
    //   134	146	494	java/lang/RuntimeException
    //   150	161	494	java/lang/RuntimeException
    //   165	176	494	java/lang/RuntimeException
    //   180	191	494	java/lang/RuntimeException
    //   221	228	494	java/lang/RuntimeException
    //   232	248	494	java/lang/RuntimeException
    //   252	309	494	java/lang/RuntimeException
    //   313	320	494	java/lang/RuntimeException
    //   333	354	494	java/lang/RuntimeException
    //   358	363	494	java/lang/RuntimeException
    //   367	372	494	java/lang/RuntimeException
    //   376	381	494	java/lang/RuntimeException
    //   385	390	494	java/lang/RuntimeException
    //   394	403	494	java/lang/RuntimeException
    //   412	422	494	java/lang/RuntimeException
    //   426	430	494	java/lang/RuntimeException
    //   441	450	494	java/lang/RuntimeException
    //   454	462	494	java/lang/RuntimeException
    //   466	476	494	java/lang/RuntimeException
    //   483	494	494	java/lang/RuntimeException
    //   521	531	494	java/lang/RuntimeException
    //   535	540	494	java/lang/RuntimeException
    //   544	549	494	java/lang/RuntimeException
    //   563	576	664	java/lang/NumberFormatException
    //   682	692	695	java/lang/NumberFormatException
    //   553	559	764	java/lang/RuntimeException
    //   563	576	764	java/lang/RuntimeException
    //   583	589	764	java/lang/RuntimeException
    //   589	611	764	java/lang/RuntimeException
    //   615	658	764	java/lang/RuntimeException
    //   672	678	764	java/lang/RuntimeException
    //   682	692	764	java/lang/RuntimeException
    //   703	723	764	java/lang/RuntimeException
    //   731	761	764	java/lang/RuntimeException
    //   3	9	771	finally
    //   13	20	782	finally
    //   24	42	782	finally
    //   46	64	782	finally
    //   68	86	782	finally
    //   90	108	782	finally
    //   112	130	782	finally
    //   134	146	782	finally
    //   150	161	782	finally
    //   165	176	782	finally
    //   180	191	782	finally
    //   221	228	782	finally
    //   232	248	782	finally
    //   252	309	782	finally
    //   313	320	782	finally
    //   333	354	782	finally
    //   358	363	782	finally
    //   367	372	782	finally
    //   376	381	782	finally
    //   385	390	782	finally
    //   394	403	782	finally
    //   412	422	782	finally
    //   426	430	782	finally
    //   441	450	782	finally
    //   454	462	782	finally
    //   466	476	782	finally
    //   483	494	782	finally
    //   499	509	782	finally
    //   521	531	782	finally
    //   535	540	782	finally
    //   544	549	782	finally
    //   553	559	790	finally
    //   563	576	790	finally
    //   583	589	790	finally
    //   589	611	790	finally
    //   615	658	790	finally
    //   672	678	790	finally
    //   682	692	790	finally
    //   703	723	790	finally
    //   731	761	790	finally
    //   200	211	797	finally
    //   3	9	808	java/lang/RuntimeException
    //   3	9	815	java/io/IOException
    //   615	658	823	java/lang/Exception
    //   553	559	837	java/io/IOException
    //   563	576	837	java/io/IOException
    //   583	589	837	java/io/IOException
    //   589	611	837	java/io/IOException
    //   615	658	837	java/io/IOException
    //   672	678	837	java/io/IOException
    //   682	692	837	java/io/IOException
    //   703	723	837	java/io/IOException
    //   731	761	837	java/io/IOException
  }
  
  /* Error */
  private static boolean c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 802	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 949	com/tapjoy/internal/bs:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 951	com/tapjoy/internal/bs:s	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 535
    //   25: ldc_w 953
    //   28: invokestatic 650	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 888	com/tapjoy/internal/bs:close	()V
    //   37: aconst_null
    //   38: invokestatic 844	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 888	com/tapjoy/internal/bs:close	()V
    //   49: aconst_null
    //   50: invokestatic 844	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   53: ldc_w 535
    //   56: new 603	com/tapjoy/TapjoyErrorMessage
    //   59: dup
    //   60: getstatic 643	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   63: ldc_w 955
    //   66: invokespecial 613	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   69: invokestatic 616	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   72: iconst_0
    //   73: ireturn
    //   74: astore_2
    //   75: aconst_null
    //   76: astore_1
    //   77: aload_1
    //   78: astore_0
    //   79: ldc_w 535
    //   82: aload_2
    //   83: invokevirtual 837	java/io/IOException:getMessage	()Ljava/lang/String;
    //   86: invokestatic 839	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload_1
    //   90: invokestatic 844	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   93: goto -40 -> 53
    //   96: astore_2
    //   97: aconst_null
    //   98: astore_1
    //   99: aload_1
    //   100: astore_0
    //   101: ldc_w 535
    //   104: aload_2
    //   105: invokevirtual 882	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   108: invokestatic 839	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   111: aload_1
    //   112: invokestatic 844	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   115: goto -62 -> 53
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_0
    //   121: aload_0
    //   122: invokestatic 844	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
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
  
  private static void d()
  {
    if (!ct.c(M)) {
      gb.a().a(g, h, "11.12.1", "https://rpc.tapjoy.com/", M, L);
    }
    if (k != null) {
      k.onConnectFailure();
    }
  }
  
  private static boolean d(String paramString)
  {
    Iterator localIterator = ac.getInstalledApplications(0).iterator();
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
    TapjoyUtil.safePut(localHashMap3, "plugin", N, true);
    TapjoyUtil.safePut(localHashMap3, "sdk_type", O, true);
    TapjoyUtil.safePut(localHashMap3, "app_id", v, true);
    TapjoyUtil.safePut(localHashMap3, "library_version", x, true);
    TapjoyUtil.safePut(localHashMap3, "library_revision", "5423f379f", true);
    TapjoyUtil.safePut(localHashMap3, "bridge_version", y, true);
    TapjoyUtil.safePut(localHashMap3, "app_version", w, true);
    localHashMap2.putAll(localHashMap3);
    localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "device_name", r, true);
    TapjoyUtil.safePut(localHashMap3, "platform", D, true);
    TapjoyUtil.safePut(localHashMap3, "os_version", u, true);
    TapjoyUtil.safePut(localHashMap3, "device_manufacturer", s, true);
    TapjoyUtil.safePut(localHashMap3, "device_type", t, true);
    TapjoyUtil.safePut(localHashMap3, "screen_layout_size", B, true);
    TapjoyUtil.safePut(localHashMap3, "store_name", K, true);
    TapjoyUtil.safePut(localHashMap3, "store_view", String.valueOf(R), true);
    TapjoyUtil.safePut(localHashMap3, "carrier_name", E, true);
    TapjoyUtil.safePut(localHashMap3, "carrier_country_code", F, true);
    TapjoyUtil.safePut(localHashMap3, "mobile_network_code", H, true);
    TapjoyUtil.safePut(localHashMap3, "mobile_country_code", G, true);
    TapjoyUtil.safePut(localHashMap3, "country_code", Locale.getDefault().getCountry(), true);
    TapjoyUtil.safePut(localHashMap3, "language_code", Locale.getDefault().getLanguage(), true);
    I = getConnectionType();
    TapjoyUtil.safePut(localHashMap3, "connection_type", I, true);
    J = getConnectionSubType();
    TapjoyUtil.safePut(localHashMap3, "connection_subtype", J, true);
    TapjoyUtil.safePut(localHashMap3, "screen_density", z, true);
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
      TapjoyUtil.safePut(localHashMap3, "mac_address", p, true);
    }
    TapjoyUtil.safePut(localHashMap3, "install_id", q, true);
    TapjoyUtil.safePut(localHashMap3, "publisher_user_id", C, true);
    TapjoyUtil.safePut(localHashMap3, "ad_id_check_disabled", e, true);
    TapjoyUtil.safePut(localHashMap3, "persistent_ids_disabled", f, true);
    if (a != 0) {
      TapjoyUtil.safePut(localHashMap3, "packaged_gps_version", Integer.toString(a), true);
    }
    if (b != 0) {
      TapjoyUtil.safePut(localHashMap3, "device_gps_version", Integer.toString(b), true);
    }
    if ((o == null) || (o.length() == 0) || (System.currentTimeMillis() - Z > 1800000L)) {
      o = n();
    }
    for (;;)
    {
      TapjoyUtil.safePut(localHashMap3, "session_id", o, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "app_group_id", S, true);
      TapjoyUtil.safePut(localHashMap3, "store", T, true);
      TapjoyUtil.safePut(localHashMap3, "analytics_api_key", U, true);
      TapjoyUtil.safePut(localHashMap3, "managed_device_id", V, true);
      localHashMap2.putAll(localHashMap3);
      if ((TapjoyCache.getInstance() != null) && (TapjoyCache.getInstance().getCachedOfferIDs() != null) && (TapjoyCache.getInstance().getCachedOfferIDs().length() > 0)) {
        TapjoyUtil.safePut(localHashMap2, "cached_ids", TapjoyCache.getInstance().getCachedOfferIDs(), true);
      }
      TapjoyUtil.safePut(localHashMap2, "display_multiplier", Float.toString(Q), true);
      localHashMap1.putAll(localHashMap2);
      localHashMap2 = new HashMap();
      g();
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "analytics_id", ah, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_id", ai, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_sign", aj, true);
      TapjoyUtil.safePut(localHashMap3, "display_d", aJ);
      TapjoyUtil.safePut(localHashMap3, "display_w", aK);
      TapjoyUtil.safePut(localHashMap3, "display_h", aL);
      TapjoyUtil.safePut(localHashMap3, "country_sim", aM, true);
      TapjoyUtil.safePut(localHashMap3, "timezone", aN, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "pkg_ver", ak, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_rev", al);
      TapjoyUtil.safePut(localHashMap3, "pkg_data_ver", am, true);
      TapjoyUtil.safePut(localHashMap3, "installer", an, true);
      if (ct.c(K)) {
        TapjoyUtil.safePut(localHashMap3, "store_name", aO, true);
      }
      localHashMap2.putAll(localHashMap3);
      localHashMap2.putAll(f());
      localHashMap1.putAll(localHashMap2);
      return localHashMap1;
      Z = System.currentTimeMillis();
    }
  }
  
  private static boolean e(String paramString)
  {
    return ac.checkPermission(paramString, g.getPackageName()) == 0;
  }
  
  private static Map f()
  {
    HashMap localHashMap = new HashMap();
    TapjoyUtil.safePut(localHashMap, "installed", ao);
    TapjoyUtil.safePut(localHashMap, "referrer", ap, true);
    TapjoyUtil.safePut(localHashMap, "user_level", aq);
    TapjoyUtil.safePut(localHashMap, "friend_count", ar);
    TapjoyUtil.safePut(localHashMap, "uv1", as, true);
    TapjoyUtil.safePut(localHashMap, "uv2", at, true);
    TapjoyUtil.safePut(localHashMap, "uv3", au, true);
    TapjoyUtil.safePut(localHashMap, "uv4", av, true);
    TapjoyUtil.safePut(localHashMap, "uv5", aw, true);
    Iterator localIterator = ax.iterator();
    int i1 = 0;
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      TapjoyUtil.safePut(localHashMap, "user_tags[" + i1 + "]", str, true);
      i1 += 1;
    }
    TapjoyUtil.safePut(localHashMap, "fq7", ay);
    TapjoyUtil.safePut(localHashMap, "fq30", az);
    TapjoyUtil.safePut(localHashMap, "session_total_count", aA);
    TapjoyUtil.safePut(localHashMap, "session_total_length", aB);
    TapjoyUtil.safePut(localHashMap, "session_last_at", aC);
    TapjoyUtil.safePut(localHashMap, "session_last_length", aD);
    TapjoyUtil.safePut(localHashMap, "purchase_currency", aE, true);
    TapjoyUtil.safePut(localHashMap, "purchase_total_count", aF);
    TapjoyUtil.safePut(localHashMap, "purchase_total_price", aG);
    TapjoyUtil.safePut(localHashMap, "purchase_last_price", aH);
    TapjoyUtil.safePut(localHashMap, "purchase_last_at", aI);
    return localHashMap;
  }
  
  private static void g()
  {
    Object localObject1 = gb.a(g).a(true);
    Object localObject2 = ((ee)localObject1).d;
    ah = ((ed)localObject2).h;
    ai = ((ed)localObject2).r;
    aj = ((ed)localObject2).s;
    aJ = ((ed)localObject2).m;
    aK = ((ed)localObject2).n;
    aL = ((ed)localObject2).o;
    aM = ((ed)localObject2).u;
    aN = ((ed)localObject2).q;
    localObject2 = ((ee)localObject1).e;
    ak = ((dx)localObject2).e;
    al = ((dx)localObject2).f;
    am = ((dx)localObject2).g;
    an = ((dx)localObject2).h;
    aO = ((dx)localObject2).i;
    localObject1 = ((ee)localObject1).f;
    ao = ((ek)localObject1).s;
    ap = ((ek)localObject1).t;
    aq = ((ek)localObject1).J;
    ar = ((ek)localObject1).K;
    as = ((ek)localObject1).L;
    at = ((ek)localObject1).M;
    au = ((ek)localObject1).N;
    av = ((ek)localObject1).O;
    aw = ((ek)localObject1).P;
    ax = new HashSet(((ek)localObject1).Q);
    ay = ((ek)localObject1).u;
    az = ((ek)localObject1).v;
    aA = ((ek)localObject1).x;
    aB = ((ek)localObject1).y;
    aC = ((ek)localObject1).z;
    aD = ((ek)localObject1).A;
    aE = ((ek)localObject1).B;
    aF = ((ek)localObject1).C;
    aG = ((ek)localObject1).D;
    aH = ((ek)localObject1).F;
    aI = ((ek)localObject1).E;
  }
  
  public static String getAndroidID()
  {
    return n;
  }
  
  public static String getAppID()
  {
    return v;
  }
  
  public static String getAwardCurrencyVerifier(long paramLong, int paramInt, String paramString)
  {
    try
    {
      paramString = TapjoyUtil.SHA256(v + ":" + o() + ":" + paramLong + ":" + L + ":" + paramInt + ":" + paramString);
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
    return E;
  }
  
  public static String getConnectFlagValue(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    if (ae != null)
    {
      str1 = str2;
      if (ae.get(paramString) != null) {
        str1 = ae.get(paramString).toString();
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
  
  public static float getDeviceScreenDensityScale()
  {
    return A;
  }
  
  public static Map getGenericURLParams()
  {
    Map localMap = e();
    TapjoyUtil.safePut(localMap, "app_id", v, true);
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
    return p;
  }
  
  public static String getPlacementURL()
  {
    return getConnectFlagValue("TJC_OPTION_PLACEMENT_SERVICE_URL");
  }
  
  public static String getRedirectDomain()
  {
    return P;
  }
  
  public static String getSecretKey()
  {
    return L;
  }
  
  public static String getSha1MacAddress()
  {
    try
    {
      String str = TapjoyUtil.SHA1(p);
      return str;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", "Error generating sha1 of macAddress: " + localException.toString());
    }
    return null;
  }
  
  public static String getSupportURL(String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = v;
    }
    return getHostURL() + "support_requests/new?currency_id=" + str + "&app_id=" + v + "&udid=" + V + "&language_code=" + Locale.getDefault().getLanguage();
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
    return C;
  }
  
  private static void h()
  {
    TapjoyLog.i("TapjoyConnect", "Connect Flags:");
    TapjoyLog.i("TapjoyConnect", "--------------------");
    Iterator localIterator = ae.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      TapjoyLog.i("TapjoyConnect", "key: " + (String)localEntry.getKey() + ", value: " + Uri.encode(localEntry.getValue().toString()));
    }
    TapjoyLog.i("TapjoyConnect", "hostURL: [" + getConnectFlagValue("TJC_OPTION_SERVICE_URL") + "]");
    TapjoyLog.i("TapjoyConnect", "redirectDomain: [" + P + "]");
    TapjoyLog.i("TapjoyConnect", "--------------------");
  }
  
  private static void i()
  {
    for (;;)
    {
      int i1;
      try
      {
        if (ac == null) {
          break;
        }
        ApplicationInfo localApplicationInfo = ac.getApplicationInfo(g.getPackageName(), 128);
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
              a(str1, str2);
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
  
  public static boolean isConnected()
  {
    return ab;
  }
  
  public static boolean isFullScreenViewOpen()
  {
    Iterator localIterator = ag.values().iterator();
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
    TapjoyLog.d("TapjoyConnect", "isViewOpen: " + ag.size());
    return !ag.isEmpty();
  }
  
  /* Error */
  private void j()
  {
    // Byte code:
    //   0: getstatic 295	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   3: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   6: invokevirtual 316	android/content/Context:getPackageName	()Ljava/lang/String;
    //   9: iconst_1
    //   10: invokevirtual 372	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   13: getfield 1491	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
    //   16: invokestatic 151	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   19: astore_2
    //   20: aload_2
    //   21: ifnull +408 -> 429
    //   24: aload_2
    //   25: invokeinterface 696 1 0
    //   30: astore_2
    //   31: aload_2
    //   32: invokeinterface 701 1 0
    //   37: ifeq +392 -> 429
    //   40: aload_2
    //   41: invokeinterface 705 1 0
    //   46: checkcast 1493	android/content/pm/ActivityInfo
    //   49: astore_3
    //   50: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   53: aload_3
    //   54: getfield 1496	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   57: invokevirtual 1497	java/util/Vector:contains	(Ljava/lang/Object;)Z
    //   60: ifeq -29 -> 31
    //   63: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   66: aload_3
    //   67: getfield 1496	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   70: invokevirtual 1500	java/util/Vector:indexOf	(Ljava/lang/Object;)I
    //   73: istore_1
    //   74: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   77: iload_1
    //   78: invokevirtual 1503	java/util/Vector:get	(I)Ljava/lang/Object;
    //   81: checkcast 363	java/lang/String
    //   84: invokestatic 1509	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   87: pop
    //   88: new 139	java/util/Vector
    //   91: dup
    //   92: invokespecial 772	java/util/Vector:<init>	()V
    //   95: astore 4
    //   97: aload_3
    //   98: getfield 1512	android/content/pm/ActivityInfo:configChanges	I
    //   101: sipush 128
    //   104: iand
    //   105: sipush 128
    //   108: if_icmpeq +12 -> 120
    //   111: aload 4
    //   113: ldc_w 1514
    //   116: invokevirtual 784	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   119: pop
    //   120: aload_3
    //   121: getfield 1512	android/content/pm/ActivityInfo:configChanges	I
    //   124: bipush 32
    //   126: iand
    //   127: bipush 32
    //   129: if_icmpeq +12 -> 141
    //   132: aload 4
    //   134: ldc_w 1516
    //   137: invokevirtual 784	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   140: pop
    //   141: aload 4
    //   143: invokevirtual 1517	java/util/Vector:size	()I
    //   146: ifeq +149 -> 295
    //   149: aload 4
    //   151: invokevirtual 1517	java/util/Vector:size	()I
    //   154: iconst_1
    //   155: if_icmpne +95 -> 250
    //   158: new 264	com/tapjoy/TapjoyIntegrationException
    //   161: dup
    //   162: new 478	java/lang/StringBuilder
    //   165: dup
    //   166: invokespecial 479	java/lang/StringBuilder:<init>	()V
    //   169: aload 4
    //   171: invokevirtual 1518	java/util/Vector:toString	()Ljava/lang/String;
    //   174: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: ldc_w 1520
    //   180: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   186: iload_1
    //   187: invokevirtual 1503	java/util/Vector:get	(I)Ljava/lang/Object;
    //   190: checkcast 363	java/lang/String
    //   193: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: invokespecial 1521	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   202: athrow
    //   203: astore_2
    //   204: new 264	com/tapjoy/TapjoyIntegrationException
    //   207: dup
    //   208: new 478	java/lang/StringBuilder
    //   211: dup
    //   212: ldc_w 1523
    //   215: invokespecial 540	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   218: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   221: iload_1
    //   222: invokevirtual 1503	java/util/Vector:get	(I)Ljava/lang/Object;
    //   225: checkcast 363	java/lang/String
    //   228: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   234: invokespecial 1521	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   237: athrow
    //   238: astore_2
    //   239: new 264	com/tapjoy/TapjoyIntegrationException
    //   242: dup
    //   243: ldc_w 1525
    //   246: invokespecial 1521	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   249: athrow
    //   250: new 264	com/tapjoy/TapjoyIntegrationException
    //   253: dup
    //   254: new 478	java/lang/StringBuilder
    //   257: dup
    //   258: invokespecial 479	java/lang/StringBuilder:<init>	()V
    //   261: aload 4
    //   263: invokevirtual 1518	java/util/Vector:toString	()Ljava/lang/String;
    //   266: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: ldc_w 1527
    //   272: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   278: iload_1
    //   279: invokevirtual 1503	java/util/Vector:get	(I)Ljava/lang/Object;
    //   282: checkcast 363	java/lang/String
    //   285: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   291: invokespecial 1521	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   294: athrow
    //   295: getstatic 399	android/os/Build$VERSION:SDK_INT	I
    //   298: bipush 13
    //   300: if_icmplt +49 -> 349
    //   303: aload_3
    //   304: getfield 1512	android/content/pm/ActivityInfo:configChanges	I
    //   307: sipush 1024
    //   310: iand
    //   311: sipush 1024
    //   314: if_icmpeq +35 -> 349
    //   317: ldc_w 535
    //   320: new 478	java/lang/StringBuilder
    //   323: dup
    //   324: ldc_w 1529
    //   327: invokespecial 540	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   330: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   333: iload_1
    //   334: invokevirtual 1503	java/util/Vector:get	(I)Ljava/lang/Object;
    //   337: checkcast 363	java/lang/String
    //   340: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   346: invokestatic 545	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   349: getstatic 399	android/os/Build$VERSION:SDK_INT	I
    //   352: bipush 11
    //   354: if_icmplt +64 -> 418
    //   357: aload_3
    //   358: getfield 1496	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   361: ldc_w 1531
    //   364: invokevirtual 657	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   367: ifeq +51 -> 418
    //   370: aload_3
    //   371: getfield 1532	android/content/pm/ActivityInfo:flags	I
    //   374: sipush 512
    //   377: iand
    //   378: sipush 512
    //   381: if_icmpeq +37 -> 418
    //   384: new 264	com/tapjoy/TapjoyIntegrationException
    //   387: dup
    //   388: new 478	java/lang/StringBuilder
    //   391: dup
    //   392: ldc_w 1534
    //   395: invokespecial 540	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   398: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   401: iload_1
    //   402: invokevirtual 1503	java/util/Vector:get	(I)Ljava/lang/Object;
    //   405: checkcast 363	java/lang/String
    //   408: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   414: invokespecial 1521	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   417: athrow
    //   418: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   421: iload_1
    //   422: invokevirtual 1536	java/util/Vector:remove	(I)Ljava/lang/Object;
    //   425: pop
    //   426: goto -395 -> 31
    //   429: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   432: invokevirtual 1517	java/util/Vector:size	()I
    //   435: ifeq +103 -> 538
    //   438: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   441: invokevirtual 1517	java/util/Vector:size	()I
    //   444: iconst_1
    //   445: if_icmpne +48 -> 493
    //   448: new 264	com/tapjoy/TapjoyIntegrationException
    //   451: dup
    //   452: new 478	java/lang/StringBuilder
    //   455: dup
    //   456: ldc_w 1538
    //   459: invokespecial 540	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   462: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   465: invokevirtual 1517	java/util/Vector:size	()I
    //   468: invokevirtual 1004	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   471: ldc_w 1540
    //   474: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   477: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   480: invokevirtual 1518	java/util/Vector:toString	()Ljava/lang/String;
    //   483: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   489: invokespecial 1521	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   492: athrow
    //   493: new 264	com/tapjoy/TapjoyIntegrationException
    //   496: dup
    //   497: new 478	java/lang/StringBuilder
    //   500: dup
    //   501: ldc_w 1538
    //   504: invokespecial 540	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   507: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   510: invokevirtual 1517	java/util/Vector:size	()I
    //   513: invokevirtual 1004	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   516: ldc_w 1542
    //   519: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   522: getstatic 157	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   525: invokevirtual 1518	java/util/Vector:toString	()Ljava/lang/String;
    //   528: invokevirtual 492	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: invokevirtual 502	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   534: invokespecial 1521	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   537: athrow
    //   538: invokestatic 1544	com/tapjoy/TapjoyConnectCore:k	()V
    //   541: ldc_w 1546
    //   544: invokestatic 1509	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   547: astore_2
    //   548: aload_2
    //   549: ldc_w 1548
    //   552: iconst_1
    //   553: anewarray 1505	java/lang/Class
    //   556: dup
    //   557: iconst_0
    //   558: ldc_w 625
    //   561: aastore
    //   562: invokevirtual 1552	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   565: pop
    //   566: ldc_w 1554
    //   569: invokestatic 1557	com/tapjoy/TapjoyUtil:getResource	(Ljava/lang/String;)Ljava/lang/Object;
    //   572: checkcast 363	java/lang/String
    //   575: astore_2
    //   576: aload_2
    //   577: ifnonnull +178 -> 755
    //   580: ldc_w 1559
    //   583: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   586: invokestatic 1563	com/tapjoy/TapjoyUtil:copyTextFromJarIntoString	(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   589: astore_2
    //   590: aload_2
    //   591: ifnonnull +161 -> 752
    //   594: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   597: invokevirtual 310	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   600: ldc_w 1565
    //   603: ldc_w 1567
    //   606: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   609: invokevirtual 316	android/content/Context:getPackageName	()Ljava/lang/String;
    //   612: invokevirtual 322	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   615: istore_1
    //   616: getstatic 127	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   619: invokevirtual 310	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   622: iload_1
    //   623: invokevirtual 329	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   626: astore_3
    //   627: aload_3
    //   628: invokevirtual 1572	java/io/InputStream:available	()I
    //   631: newarray byte
    //   633: astore 4
    //   635: aload_3
    //   636: aload 4
    //   638: invokevirtual 1576	java/io/InputStream:read	([B)I
    //   641: pop
    //   642: new 363	java/lang/String
    //   645: dup
    //   646: aload 4
    //   648: invokespecial 1579	java/lang/String:<init>	([B)V
    //   651: astore_3
    //   652: ldc_w 1554
    //   655: aload_3
    //   656: invokestatic 1583	com/tapjoy/TapjoyUtil:setResource	(Ljava/lang/String;Ljava/lang/Object;)V
    //   659: aload_3
    //   660: astore_2
    //   661: aload_2
    //   662: ifnonnull +38 -> 700
    //   665: new 264	com/tapjoy/TapjoyIntegrationException
    //   668: dup
    //   669: ldc_w 1585
    //   672: invokespecial 1521	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   675: athrow
    //   676: astore_2
    //   677: new 264	com/tapjoy/TapjoyIntegrationException
    //   680: dup
    //   681: ldc_w 1587
    //   684: invokespecial 1521	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   687: athrow
    //   688: astore_2
    //   689: new 264	com/tapjoy/TapjoyIntegrationException
    //   692: dup
    //   693: ldc_w 1589
    //   696: invokespecial 1521	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   699: athrow
    //   700: ldc_w 577
    //   703: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   706: ifnull +28 -> 734
    //   709: ldc_w 577
    //   712: invokestatic 342	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   715: ldc_w 790
    //   718: invokevirtual 657	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   721: ifeq +13 -> 734
    //   724: ldc_w 535
    //   727: ldc_w 1591
    //   730: invokestatic 583	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   733: return
    //   734: aload_0
    //   735: getfield 301	com/tapjoy/TapjoyConnectCore:ad	Lcom/tapjoy/TapjoyGpsHelper;
    //   738: invokevirtual 1594	com/tapjoy/TapjoyGpsHelper:checkGooglePlayIntegration	()V
    //   741: return
    //   742: astore_3
    //   743: goto -82 -> 661
    //   746: astore_2
    //   747: aload_3
    //   748: astore_2
    //   749: goto -88 -> 661
    //   752: goto -91 -> 661
    //   755: goto -165 -> 590
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	758	0	this	TapjoyConnectCore
    //   73	550	1	i1	int
    //   19	22	2	localObject1	Object
    //   203	1	2	localClassNotFoundException1	ClassNotFoundException
    //   238	1	2	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   547	115	2	localObject2	Object
    //   676	1	2	localClassNotFoundException2	ClassNotFoundException
    //   688	1	2	localNoSuchMethodException	NoSuchMethodException
    //   746	1	2	localException1	Exception
    //   748	1	2	localObject3	Object
    //   49	611	3	localObject4	Object
    //   742	6	3	localException2	Exception
    //   95	552	4	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   74	120	203	java/lang/ClassNotFoundException
    //   120	141	203	java/lang/ClassNotFoundException
    //   141	203	203	java/lang/ClassNotFoundException
    //   250	295	203	java/lang/ClassNotFoundException
    //   295	349	203	java/lang/ClassNotFoundException
    //   349	418	203	java/lang/ClassNotFoundException
    //   418	426	203	java/lang/ClassNotFoundException
    //   0	20	238	android/content/pm/PackageManager$NameNotFoundException
    //   24	31	238	android/content/pm/PackageManager$NameNotFoundException
    //   31	74	238	android/content/pm/PackageManager$NameNotFoundException
    //   74	120	238	android/content/pm/PackageManager$NameNotFoundException
    //   120	141	238	android/content/pm/PackageManager$NameNotFoundException
    //   141	203	238	android/content/pm/PackageManager$NameNotFoundException
    //   204	238	238	android/content/pm/PackageManager$NameNotFoundException
    //   250	295	238	android/content/pm/PackageManager$NameNotFoundException
    //   295	349	238	android/content/pm/PackageManager$NameNotFoundException
    //   349	418	238	android/content/pm/PackageManager$NameNotFoundException
    //   418	426	238	android/content/pm/PackageManager$NameNotFoundException
    //   541	548	676	java/lang/ClassNotFoundException
    //   548	566	688	java/lang/NoSuchMethodException
    //   594	652	742	java/lang/Exception
    //   652	659	746	java/lang/Exception
  }
  
  private static void k()
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
      if (!e(str)) {
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
      if (!e(str)) {
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
    try
    {
      str = TapjoyUtil.SHA256(System.currentTimeMillis() / 1000L + v);
      TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + localException1.toString());
    }
    catch (Exception localException1)
    {
      try
      {
        Z = System.currentTimeMillis();
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
  
  private static String o()
  {
    int i2 = 1;
    if (m()) {
      return c;
    }
    if ((p != null) && (p.length() > 0)) {}
    for (int i1 = 1; i1 != 0; i1 = 0) {
      return p;
    }
    if (l()) {
      return c;
    }
    if ((n != null) && (n.length() > 0)) {}
    for (i1 = i2; i1 != 0; i1 = 0) {
      return n;
    }
    TapjoyLog.e("TapjoyConnect", "Error -- no valid device identifier");
    return null;
  }
  
  private static String p()
  {
    String str1 = v + w + x + c + q;
    try
    {
      String str2 = TapjoyUtil.SHA1(str1);
      return str2;
    }
    catch (Exception localException) {}
    return str1;
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
    er localEr;
    try
    {
      localEr = new er(paramString);
      if (localEr.a != er.a.SDK_ANDROID) {
        throw new IllegalArgumentException("The given API key was not for Android.");
      }
    }
    catch (IllegalArgumentException paramContext)
    {
      throw new TapjoyIntegrationException(paramContext.getMessage());
    }
    h = paramString;
    v = localEr.b;
    L = localEr.c;
    M = localEr.d;
    if (paramHashtable != null)
    {
      ae.putAll(paramHashtable);
      fd.b().a(paramHashtable);
    }
    gb.a(paramContext).j = paramString;
    k = paramTJConnectListener;
    i = new TapjoyConnectCore(paramContext);
  }
  
  public static void setPlugin(String paramString)
  {
    N = paramString;
  }
  
  public static void setSDKType(String paramString)
  {
    O = paramString;
  }
  
  public static void setUserID(String paramString, TJSetUserIDListener paramTJSetUserIDListener)
  {
    C = paramString;
    l = paramTJSetUserIDListener;
    TapjoyLog.d("TapjoyConnect", "URL parameters: " + getURLParams());
    new Thread(new Runnable()
    {
      public final void run()
      {
        TapjoyLog.i("TapjoyConnect", "Setting userID to " + TapjoyConnectCore.b());
        TapjoyHttpURLResponse localTapjoyHttpURLResponse = TapjoyConnectCore.c().getResponseFromURL(TapjoyConnectCore.getHostURL() + "set_publisher_user_id?", TapjoyConnectCore.getURLParams());
        boolean bool = false;
        if (localTapjoyHttpURLResponse.response != null) {
          bool = TapjoyConnectCore.a(localTapjoyHttpURLResponse.response);
        }
        TapjoyConnectCore.a(bool);
      }
    }).start();
  }
  
  public static void setViewShowing(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      ag.put("", Integer.valueOf(1));
      return;
    }
    ag.clear();
  }
  
  public static void viewDidClose(String paramString)
  {
    TapjoyLog.d("TapjoyConnect", "viewDidClose: " + paramString);
    ag.remove(paramString);
    ev.e.notifyObservers();
  }
  
  public static void viewWillOpen(String paramString, int paramInt)
  {
    TapjoyLog.d("TapjoyConnect", "viewWillOpen: " + paramString);
    ag.put(paramString, Integer.valueOf(paramInt));
  }
  
  public void actionComplete(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "actionComplete: " + paramString);
    Map localMap = e();
    TapjoyUtil.safePut(localMap, "app_id", paramString, true);
    localMap.putAll(getTimeStampAndVerifierParams());
    TapjoyLog.d("TapjoyConnect", "PPA URL parameters: " + localMap);
    new Thread(new PPAThread(localMap)).start();
  }
  
  public void appPause()
  {
    this.Y = true;
  }
  
  public void appResume()
  {
    if (this.Y)
    {
      n();
      this.Y = false;
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
    Object localObject2;
    if (!isConnected())
    {
      localObject2 = TapjoyAppSettings.getInstance().getConnectResult(p(), y.b());
      if ((localObject2 != null) && (a((String)localObject2, true)))
      {
        TapjoyLog.i("TapjoyConnect", "Connect using stored connect result");
        ab = true;
        if (k != null) {
          k.onConnectSuccess();
        }
        ev.a.notifyObservers();
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
          ab = true;
          localObject1 = getGenericURLParams().entrySet().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (Map.Entry)((Iterator)localObject1).next();
            TapjoyLog.d("TapjoyConnect", (String)((Map.Entry)localObject2).getKey() + ": " + (String)((Map.Entry)localObject2).getValue());
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
        for (;;)
        {
          if (af.length() > 0)
          {
            localObject1 = getGenericURLParams();
            TapjoyUtil.safePut((Map)localObject1, "package_names", af, true);
            long l1 = System.currentTimeMillis() / 1000L;
            localObject2 = a(l1, af);
            TapjoyUtil.safePut((Map)localObject1, "timestamp", String.valueOf(l1), true);
            TapjoyUtil.safePut((Map)localObject1, "verifier", (String)localObject2, true);
            localObject1 = new TapjoyURLConnection().getResponseFromURL(getHostURL() + "apps_installed?", (Map)localObject1);
            if ((localObject1 != null) && (((TapjoyHttpURLResponse)localObject1).statusCode == 200)) {
              TapjoyLog.d("TapjoyConnect", "Successfully pinged sdkless api.");
            }
          }
          return;
          if (i1 == 0) {
            d();
          }
          ev.b.notifyObservers(Boolean.FALSE);
        }
      }
      if (i1 == 0) {
        d();
      }
      ev.b.notifyObservers(Boolean.FALSE);
      return;
    }
  }
  
  public void fetchAdvertisingID()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        TapjoyConnectCore.a(TapjoyConnectCore.this).loadAdvertisingId();
        if ((TapjoyConnectCore.a(TapjoyConnectCore.this).isGooglePlayServicesAvailable()) && (TapjoyConnectCore.a(TapjoyConnectCore.this).isGooglePlayManifestConfigured()))
        {
          TapjoyConnectCore.b = TapjoyConnectCore.a(TapjoyConnectCore.this).getDeviceGooglePlayServicesVersion();
          TapjoyConnectCore.a = TapjoyConnectCore.a(TapjoyConnectCore.this).getPackagedGooglePlayServicesVersion();
        }
        Object localObject;
        String str1;
        if (TapjoyConnectCore.a(TapjoyConnectCore.this).isAdIdAvailable())
        {
          TapjoyConnectCore.d = TapjoyConnectCore.a(TapjoyConnectCore.this).isAdTrackingEnabled();
          TapjoyConnectCore.c = TapjoyConnectCore.a(TapjoyConnectCore.this).getAdvertisingId();
          localObject = gb.a();
          str1 = TapjoyConnectCore.c;
          if (TapjoyConnectCore.d) {
            break label226;
          }
        }
        label226:
        for (boolean bool = true;; bool = false)
        {
          localObject = ((gb)localObject).f;
          String str2 = ((ge)localObject).c.A.a();
          ((ge)localObject).b.q = str1;
          ((ge)localObject).b.r = Boolean.valueOf(bool);
          ((ge)localObject).c.A.a(str1);
          ((ge)localObject).c.B.a(bool);
          gp.a(str1, bool);
          if ((!ct.c(str2)) && (!str1.equals(str2))) {
            ((ge)localObject).c.a(false);
          }
          if (TapjoyConnectCore.a()) {
            TapjoyLog.i("TapjoyConnect", "Disabling persistent IDs. Do this only if you are not using Tapjoy to manage currency.");
          }
          TapjoyConnectCore.this.completeConnectCall();
          return;
        }
      }
    }).start();
  }
  
  public float getCurrencyMultiplier()
  {
    return Q;
  }
  
  public boolean isInitialized()
  {
    return this.aa;
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
    Q = paramFloat;
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
      TapjoyHttpURLResponse localTapjoyHttpURLResponse = TapjoyConnectCore.c().getResponseFromURL(TapjoyConnectCore.getHostURL() + "api/connect/v3.json?", null, null, this.b);
      if (localTapjoyHttpURLResponse.response != null) {
        TapjoyConnectCore.b(localTapjoyHttpURLResponse.response);
      }
    }
  }
}
