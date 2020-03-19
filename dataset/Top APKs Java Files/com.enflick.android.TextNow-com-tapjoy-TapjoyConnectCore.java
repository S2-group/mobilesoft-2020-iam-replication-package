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
import com.tapjoy.internal.ApiKeyDecoded;
import com.tapjoy.internal.ApiKeyDecoded.KeyUsage;
import com.tapjoy.internal.InternalObservables;
import com.tapjoy.internal.InternalObservables.SimpleObservable;
import com.tapjoy.internal.cs;
import com.tapjoy.internal.ho;
import com.tapjoy.internal.ie;
import com.tapjoy.internal.if;
import com.tapjoy.internal.ir;
import com.tapjoy.internal.iu;
import com.tapjoy.internal.iu.1;
import com.tapjoy.internal.iv;
import com.tapjoy.internal.jo;
import com.tapjoy.internal.kf.a;
import com.tapjoy.internal.kf.l;
import com.tapjoy.internal.kf.n;
import com.tapjoy.internal.kf.z;
import com.tapjoy.mediation.TJMediationNetwork;
import java.util.ArrayList;
import java.util.Arrays;
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
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

public class TapjoyConnectCore
{
  private static int A = 0;
  private static float B = 0.0F;
  private static int C = 0;
  private static String D;
  public static final float DEFAULT_CURRENCY_MULTIPLIER = 1.0F;
  private static boolean E = false;
  private static String F;
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
  private static Set aF;
  private static int aG;
  private static int aH;
  private static int aI;
  private static long aJ;
  private static long aK;
  private static long aL;
  private static String aM;
  private static int aN;
  private static double aO;
  private static double aP;
  private static long aQ;
  private static int aR;
  private static int aS;
  private static int aT;
  private static String aU;
  private static String aV;
  private static String aW;
  private static boolean aX = true;
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
  private static Vector l = new Vector(Arrays.asList(TapjoyConstants.dependencyClassNames));
  private static String m = "";
  private static String n = "";
  private static String o;
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
  private long ad;
  private Timer ae;
  private boolean af;
  private boolean ah;
  private iu ak;
  private TapjoyGpsHelper al;
  
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
    //   1: invokespecial 281	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 283	com/tapjoy/TapjoyConnectCore:ad	J
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 285	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Timer;
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 287	com/tapjoy/TapjoyConnectCore:af	Z
    //   19: aload_0
    //   20: iconst_0
    //   21: putfield 289	com/tapjoy/TapjoyConnectCore:ah	Z
    //   24: aload_1
    //   25: putstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   28: new 291	com/tapjoy/TapjoyURLConnection
    //   31: dup
    //   32: invokespecial 292	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   35: putstatic 134	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   38: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   41: invokevirtual 298	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   44: putstatic 300	com/tapjoy/TapjoyConnectCore:aj	Landroid/content/pm/PackageManager;
    //   47: aload_0
    //   48: new 302	com/tapjoy/TapjoyGpsHelper
    //   51: dup
    //   52: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   55: invokespecial 304	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   58: putfield 306	com/tapjoy/TapjoyConnectCore:al	Lcom/tapjoy/TapjoyGpsHelper;
    //   61: getstatic 263	com/tapjoy/TapjoyConnectCore:am	Ljava/util/Hashtable;
    //   64: ifnonnull +13 -> 77
    //   67: new 308	java/util/Hashtable
    //   70: dup
    //   71: invokespecial 309	java/util/Hashtable:<init>	()V
    //   74: putstatic 263	com/tapjoy/TapjoyConnectCore:am	Ljava/util/Hashtable;
    //   77: ldc_w 311
    //   80: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   83: ifnull +22 -> 105
    //   86: ldc_w 311
    //   89: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   92: ldc_w 317
    //   95: invokevirtual 323	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   98: ifeq +7 -> 105
    //   101: iconst_1
    //   102: invokestatic 329	com/tapjoy/TapjoyLog:setDebugEnabled	(Z)V
    //   105: invokestatic 331	com/tapjoy/TapjoyConnectCore:j	()V
    //   108: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   111: invokevirtual 335	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   114: ldc_w 337
    //   117: aconst_null
    //   118: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   121: invokevirtual 341	android/content/Context:getPackageName	()Ljava/lang/String;
    //   124: invokevirtual 347	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   127: istore_2
    //   128: new 349	java/util/Properties
    //   131: dup
    //   132: invokespecial 350	java/util/Properties:<init>	()V
    //   135: astore_1
    //   136: aload_1
    //   137: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   140: invokevirtual 335	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   143: iload_2
    //   144: invokevirtual 354	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   147: invokevirtual 358	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   150: aload_1
    //   151: invokestatic 361	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   154: ldc_w 363
    //   157: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   160: ldc -98
    //   162: if_acmpne +7 -> 169
    //   165: aload_0
    //   166: invokespecial 365	com/tapjoy/TapjoyConnectCore:k	()V
    //   169: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   172: invokevirtual 369	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   175: ldc_w 371
    //   178: invokestatic 377	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   181: astore_1
    //   182: aload_1
    //   183: putstatic 160	com/tapjoy/TapjoyConnectCore:m	Ljava/lang/String;
    //   186: aload_1
    //   187: ifnull +12 -> 199
    //   190: getstatic 160	com/tapjoy/TapjoyConnectCore:m	Ljava/lang/String;
    //   193: invokevirtual 380	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   196: putstatic 160	com/tapjoy/TapjoyConnectCore:m	Ljava/lang/String;
    //   199: getstatic 300	com/tapjoy/TapjoyConnectCore:aj	Landroid/content/pm/PackageManager;
    //   202: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   205: invokevirtual 341	android/content/Context:getPackageName	()Ljava/lang/String;
    //   208: iconst_0
    //   209: invokevirtual 386	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   212: getfield 391	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   215: putstatic 180	com/tapjoy/TapjoyConnectCore:x	Ljava/lang/String;
    //   218: ldc_w 393
    //   221: putstatic 174	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   224: ldc_w 393
    //   227: putstatic 196	com/tapjoy/TapjoyConnectCore:F	Ljava/lang/String;
    //   230: getstatic 398	android/os/Build:MODEL	Ljava/lang/String;
    //   233: putstatic 170	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   236: getstatic 401	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   239: putstatic 172	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   242: getstatic 406	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   245: putstatic 176	com/tapjoy/TapjoyConnectCore:v	Ljava/lang/String;
    //   248: ldc_w 408
    //   251: putstatic 182	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   254: ldc_w 410
    //   257: putstatic 184	com/tapjoy/TapjoyConnectCore:z	Ljava/lang/String;
    //   260: getstatic 413	android/os/Build$VERSION:SDK_INT	I
    //   263: iconst_3
    //   264: if_icmple +35 -> 299
    //   267: new 415	com/tapjoy/TapjoyDisplayMetricsUtil
    //   270: dup
    //   271: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   274: invokespecial 416	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   277: astore_1
    //   278: aload_1
    //   279: invokevirtual 420	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   282: putstatic 186	com/tapjoy/TapjoyConnectCore:A	I
    //   285: aload_1
    //   286: invokevirtual 424	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   289: putstatic 188	com/tapjoy/TapjoyConnectCore:B	F
    //   292: aload_1
    //   293: invokevirtual 427	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   296: putstatic 190	com/tapjoy/TapjoyConnectCore:C	I
    //   299: ldc_w 429
    //   302: invokestatic 432	com/tapjoy/TapjoyConnectCore:h	(Ljava/lang/String;)Z
    //   305: istore_3
    //   306: iload_3
    //   307: ifeq +931 -> 1238
    //   310: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   313: ldc_w 434
    //   316: invokevirtual 438	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   319: checkcast 440	android/net/wifi/WifiManager
    //   322: astore_1
    //   323: aload_1
    //   324: ifnull +42 -> 366
    //   327: aload_1
    //   328: invokevirtual 444	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   331: astore_1
    //   332: aload_1
    //   333: ifnull +33 -> 366
    //   336: aload_1
    //   337: invokevirtual 449	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   340: astore_1
    //   341: aload_1
    //   342: putstatic 166	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   345: aload_1
    //   346: ifnull +20 -> 366
    //   349: getstatic 166	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   352: ldc_w 451
    //   355: ldc -98
    //   357: invokevirtual 455	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   360: invokevirtual 380	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   363: putstatic 166	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   366: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   369: ldc_w 457
    //   372: invokevirtual 438	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   375: checkcast 459	android/telephony/TelephonyManager
    //   378: astore_1
    //   379: aload_1
    //   380: ifnull +239 -> 619
    //   383: aload_1
    //   384: invokevirtual 462	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   387: putstatic 198	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   390: aload_1
    //   391: invokevirtual 465	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   394: putstatic 200	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   397: aload_1
    //   398: invokevirtual 468	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   401: astore 4
    //   403: aload 4
    //   405: ifnull +41 -> 446
    //   408: aload 4
    //   410: invokevirtual 471	java/lang/String:length	()I
    //   413: iconst_5
    //   414: if_icmpeq +13 -> 427
    //   417: aload 4
    //   419: invokevirtual 471	java/lang/String:length	()I
    //   422: bipush 6
    //   424: if_icmpne +22 -> 446
    //   427: aload 4
    //   429: iconst_0
    //   430: iconst_3
    //   431: invokevirtual 475	java/lang/String:substring	(II)Ljava/lang/String;
    //   434: putstatic 202	com/tapjoy/TapjoyConnectCore:I	Ljava/lang/String;
    //   437: aload 4
    //   439: iconst_3
    //   440: invokevirtual 478	java/lang/String:substring	(I)Ljava/lang/String;
    //   443: putstatic 204	com/tapjoy/TapjoyConnectCore:J	Ljava/lang/String;
    //   446: ldc_w 480
    //   449: invokestatic 432	com/tapjoy/TapjoyConnectCore:h	(Ljava/lang/String;)Z
    //   452: istore_3
    //   453: iload_3
    //   454: ifeq +1006 -> 1460
    //   457: ldc_w 482
    //   460: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   463: ifnull +787 -> 1250
    //   466: ldc_w 482
    //   469: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   472: invokevirtual 471	java/lang/String:length	()I
    //   475: ifle +775 -> 1250
    //   478: ldc_w 482
    //   481: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   484: putstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   487: ldc_w 484
    //   490: new 486	java/lang/StringBuilder
    //   493: dup
    //   494: ldc_w 488
    //   497: invokespecial 491	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   500: getstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   503: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   509: invokestatic 501	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   512: getstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   515: ifnonnull +789 -> 1304
    //   518: ldc_w 484
    //   521: new 503	com/tapjoy/TapjoyErrorMessage
    //   524: dup
    //   525: getstatic 509	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   528: ldc_w 511
    //   531: invokespecial 514	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   534: invokestatic 517	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   537: iconst_0
    //   538: istore_2
    //   539: ldc_w 484
    //   542: new 486	java/lang/StringBuilder
    //   545: dup
    //   546: ldc_w 519
    //   549: invokespecial 491	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   552: getstatic 413	android/os/Build$VERSION:SDK_INT	I
    //   555: invokevirtual 522	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   558: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   561: invokestatic 524	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   564: getstatic 413	android/os/Build$VERSION:SDK_INT	I
    //   567: bipush 9
    //   569: if_icmplt +50 -> 619
    //   572: ldc_w 484
    //   575: ldc_w 526
    //   578: invokestatic 501	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   581: aload_0
    //   582: invokevirtual 529	com/tapjoy/TapjoyConnectCore:getSerial	()Ljava/lang/String;
    //   585: astore_1
    //   586: iload_2
    //   587: ifne +7 -> 594
    //   590: aload_1
    //   591: putstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   594: getstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   597: ifnonnull +781 -> 1378
    //   600: ldc_w 484
    //   603: new 503	com/tapjoy/TapjoyErrorMessage
    //   606: dup
    //   607: getstatic 509	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   610: ldc_w 531
    //   613: invokespecial 514	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   616: invokestatic 517	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   619: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   622: ldc_w 533
    //   625: iconst_0
    //   626: invokevirtual 537	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   629: astore_1
    //   630: aload_1
    //   631: ldc_w 539
    //   634: ldc -98
    //   636: invokeinterface 544 3 0
    //   641: astore 4
    //   643: aload 4
    //   645: putstatic 168	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   648: aload 4
    //   650: ifnull +14 -> 664
    //   653: getstatic 168	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   656: invokevirtual 471	java/lang/String:length	()I
    //   659: istore_2
    //   660: iload_2
    //   661: ifne +61 -> 722
    //   664: new 486	java/lang/StringBuilder
    //   667: dup
    //   668: invokespecial 545	java/lang/StringBuilder:<init>	()V
    //   671: invokestatic 551	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   674: invokevirtual 552	java/util/UUID:toString	()Ljava/lang/String;
    //   677: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   680: invokestatic 558	java/lang/System:currentTimeMillis	()J
    //   683: invokevirtual 561	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   686: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   689: invokestatic 566	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   692: putstatic 168	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   695: aload_1
    //   696: invokeinterface 570 1 0
    //   701: astore_1
    //   702: aload_1
    //   703: ldc_w 539
    //   706: getstatic 168	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   709: invokeinterface 576 3 0
    //   714: pop
    //   715: aload_1
    //   716: invokeinterface 580 1 0
    //   721: pop
    //   722: ldc_w 582
    //   725: ldc_w 584
    //   728: invokestatic 587	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   731: putstatic 194	com/tapjoy/TapjoyConnectCore:E	Z
    //   734: ldc_w 589
    //   737: invokestatic 591	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   740: putstatic 226	com/tapjoy/TapjoyConnectCore:T	Z
    //   743: ldc_w 593
    //   746: invokestatic 591	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   749: putstatic 228	com/tapjoy/TapjoyConnectCore:U	Z
    //   752: ldc_w 595
    //   755: invokestatic 591	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   758: putstatic 230	com/tapjoy/TapjoyConnectCore:V	Z
    //   761: ldc_w 597
    //   764: invokestatic 591	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   767: putstatic 232	com/tapjoy/TapjoyConnectCore:W	Z
    //   770: ldc_w 599
    //   773: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   776: ifnull +71 -> 847
    //   779: ldc_w 599
    //   782: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   785: invokevirtual 471	java/lang/String:length	()I
    //   788: ifle +59 -> 847
    //   791: ldc_w 599
    //   794: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   797: putstatic 210	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   800: new 601	java/util/ArrayList
    //   803: dup
    //   804: getstatic 604	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   807: invokestatic 150	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   810: invokespecial 605	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   813: getstatic 210	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   816: invokevirtual 608	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   819: ifne +28 -> 847
    //   822: ldc_w 484
    //   825: new 486	java/lang/StringBuilder
    //   828: dup
    //   829: ldc_w 610
    //   832: invokespecial 491	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   835: getstatic 210	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   838: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   841: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   844: invokestatic 612	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   847: getstatic 210	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   850: astore_1
    //   851: new 614	android/content/Intent
    //   854: dup
    //   855: ldc_w 616
    //   858: invokespecial 617	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   861: astore 4
    //   863: aload_1
    //   864: invokevirtual 471	java/lang/String:length	()I
    //   867: ifgt +695 -> 1562
    //   870: aload 4
    //   872: ldc_w 619
    //   875: invokestatic 625	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   878: invokevirtual 629	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   881: pop
    //   882: getstatic 300	com/tapjoy/TapjoyConnectCore:aj	Landroid/content/pm/PackageManager;
    //   885: aload 4
    //   887: iconst_0
    //   888: invokevirtual 633	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   891: invokeinterface 638 1 0
    //   896: ifle +746 -> 1642
    //   899: iconst_1
    //   900: istore_3
    //   901: iload_3
    //   902: putstatic 234	com/tapjoy/TapjoyConnectCore:X	Z
    //   905: invokestatic 640	com/tapjoy/TapjoyConnectCore:h	()V
    //   908: ldc_w 642
    //   911: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   914: ifnull +24 -> 938
    //   917: ldc_w 642
    //   920: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   923: invokevirtual 471	java/lang/String:length	()I
    //   926: ifle +12 -> 938
    //   929: ldc_w 642
    //   932: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   935: putstatic 256	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   938: ldc_w 644
    //   941: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   944: ifnull +24 -> 968
    //   947: ldc_w 644
    //   950: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   953: invokevirtual 471	java/lang/String:length	()I
    //   956: ifle +12 -> 968
    //   959: ldc_w 644
    //   962: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   965: putstatic 254	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   968: ldc_w 646
    //   971: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   974: ifnull +52 -> 1026
    //   977: ldc_w 646
    //   980: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   983: invokevirtual 471	java/lang/String:length	()I
    //   986: ifle +40 -> 1026
    //   989: ldc_w 484
    //   992: new 486	java/lang/StringBuilder
    //   995: dup
    //   996: ldc_w 648
    //   999: invokespecial 491	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1002: ldc_w 646
    //   1005: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1008: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1011: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1014: invokestatic 524	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1017: ldc_w 646
    //   1020: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1023: invokestatic 651	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;)V
    //   1026: ldc_w 653
    //   1029: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1032: invokestatic 656	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   1035: putstatic 222	com/tapjoy/TapjoyConnectCore:R	Ljava/lang/String;
    //   1038: new 486	java/lang/StringBuilder
    //   1041: dup
    //   1042: ldc_w 488
    //   1045: invokespecial 491	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1048: getstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1051: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1054: astore 4
    //   1056: ldc_w 482
    //   1059: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1062: ifnull +570 -> 1632
    //   1065: ldc_w 482
    //   1068: invokestatic 315	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1071: invokevirtual 471	java/lang/String:length	()I
    //   1074: ifle +558 -> 1632
    //   1077: ldc_w 658
    //   1080: astore_1
    //   1081: ldc_w 484
    //   1084: aload 4
    //   1086: aload_1
    //   1087: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1090: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1093: invokestatic 501	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1096: getstatic 263	com/tapjoy/TapjoyConnectCore:am	Ljava/util/Hashtable;
    //   1099: ifnull +6 -> 1105
    //   1102: invokestatic 660	com/tapjoy/TapjoyConnectCore:i	()V
    //   1105: aload_0
    //   1106: invokevirtual 663	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   1109: aload_0
    //   1110: iconst_1
    //   1111: putfield 289	com/tapjoy/TapjoyConnectCore:ah	Z
    //   1114: return
    //   1115: astore_1
    //   1116: new 275	com/tapjoy/TapjoyException
    //   1119: dup
    //   1120: aload_1
    //   1121: invokevirtual 666	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   1124: invokespecial 667	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   1127: athrow
    //   1128: astore_1
    //   1129: ldc_w 484
    //   1132: new 503	com/tapjoy/TapjoyErrorMessage
    //   1135: dup
    //   1136: getstatic 670	com/tapjoy/TapjoyErrorMessage$ErrorType:INTEGRATION_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1139: aload_1
    //   1140: invokevirtual 671	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   1143: invokespecial 514	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1146: invokestatic 517	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1149: invokestatic 673	com/tapjoy/TapjoyConnectCore:e	()V
    //   1152: return
    //   1153: astore_1
    //   1154: ldc_w 484
    //   1157: new 486	java/lang/StringBuilder
    //   1160: dup
    //   1161: ldc_w 675
    //   1164: invokespecial 491	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1167: aload_1
    //   1168: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   1171: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1174: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1177: invokestatic 678	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1180: goto -881 -> 299
    //   1183: astore_1
    //   1184: ldc_w 484
    //   1187: new 503	com/tapjoy/TapjoyErrorMessage
    //   1190: dup
    //   1191: getstatic 509	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1194: aload_1
    //   1195: invokevirtual 679	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   1198: invokespecial 514	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1201: invokestatic 517	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1204: invokestatic 673	com/tapjoy/TapjoyConnectCore:e	()V
    //   1207: return
    //   1208: astore_1
    //   1209: ldc_w 484
    //   1212: new 486	java/lang/StringBuilder
    //   1215: dup
    //   1216: ldc_w 681
    //   1219: invokespecial 491	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1222: aload_1
    //   1223: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   1226: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1229: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1232: invokestatic 678	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1235: goto -869 -> 366
    //   1238: ldc_w 484
    //   1241: ldc_w 683
    //   1244: invokestatic 501	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1247: goto -881 -> 366
    //   1250: aload_1
    //   1251: invokevirtual 686	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1254: putstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1257: goto -770 -> 487
    //   1260: astore_1
    //   1261: ldc_w 484
    //   1264: new 503	com/tapjoy/TapjoyErrorMessage
    //   1267: dup
    //   1268: getstatic 509	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1271: new 486	java/lang/StringBuilder
    //   1274: dup
    //   1275: ldc_w 688
    //   1278: invokespecial 491	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1281: aload_1
    //   1282: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   1285: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1288: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1291: invokespecial 514	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1294: invokestatic 517	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1297: aconst_null
    //   1298: putstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1301: goto -682 -> 619
    //   1304: getstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1307: invokevirtual 471	java/lang/String:length	()I
    //   1310: ifeq +27 -> 1337
    //   1313: getstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1316: ldc_w 690
    //   1319: invokevirtual 323	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1322: ifne +15 -> 1337
    //   1325: getstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1328: ldc_w 692
    //   1331: invokevirtual 323	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1334: ifeq +27 -> 1361
    //   1337: ldc_w 484
    //   1340: new 503	com/tapjoy/TapjoyErrorMessage
    //   1343: dup
    //   1344: getstatic 509	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1347: ldc_w 694
    //   1350: invokespecial 514	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1353: invokestatic 517	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1356: iconst_0
    //   1357: istore_2
    //   1358: goto -819 -> 539
    //   1361: getstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1364: invokestatic 700	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1367: invokevirtual 703	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1370: putstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1373: iconst_1
    //   1374: istore_2
    //   1375: goto -836 -> 539
    //   1378: getstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1381: invokevirtual 471	java/lang/String:length	()I
    //   1384: ifeq +39 -> 1423
    //   1387: getstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1390: ldc_w 690
    //   1393: invokevirtual 323	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1396: ifne +27 -> 1423
    //   1399: getstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1402: ldc_w 692
    //   1405: invokevirtual 323	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1408: ifne +15 -> 1423
    //   1411: getstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1414: ldc_w 705
    //   1417: invokevirtual 323	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1420: ifeq +25 -> 1445
    //   1423: ldc_w 484
    //   1426: new 503	com/tapjoy/TapjoyErrorMessage
    //   1429: dup
    //   1430: getstatic 509	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1433: ldc_w 707
    //   1436: invokespecial 514	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1439: invokestatic 517	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1442: goto -823 -> 619
    //   1445: getstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1448: invokestatic 700	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1451: invokevirtual 703	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1454: putstatic 164	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1457: goto -838 -> 619
    //   1460: ldc_w 484
    //   1463: ldc_w 709
    //   1466: invokestatic 501	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1469: goto -850 -> 619
    //   1472: astore_1
    //   1473: ldc_w 484
    //   1476: new 486	java/lang/StringBuilder
    //   1479: dup
    //   1480: ldc_w 711
    //   1483: invokespecial 491	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1486: aload_1
    //   1487: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   1490: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1493: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1496: invokestatic 678	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1499: goto -777 -> 722
    //   1502: astore_1
    //   1503: ldc_w 484
    //   1506: new 486	java/lang/StringBuilder
    //   1509: dup
    //   1510: ldc_w 713
    //   1513: invokespecial 491	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1516: aload_1
    //   1517: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   1520: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1523: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1526: invokestatic 678	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1529: goto -795 -> 734
    //   1532: astore_1
    //   1533: ldc_w 484
    //   1536: new 486	java/lang/StringBuilder
    //   1539: dup
    //   1540: ldc_w 715
    //   1543: invokespecial 491	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1546: aload_1
    //   1547: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   1550: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1553: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1556: invokestatic 678	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1559: goto -789 -> 770
    //   1562: aload_1
    //   1563: ldc_w 717
    //   1566: invokevirtual 323	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1569: ifeq +13 -> 1582
    //   1572: ldc_w 719
    //   1575: invokestatic 721	com/tapjoy/TapjoyConnectCore:f	(Ljava/lang/String;)Z
    //   1578: istore_3
    //   1579: goto -678 -> 901
    //   1582: aload_1
    //   1583: ldc_w 723
    //   1586: invokevirtual 323	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1589: ifeq +53 -> 1642
    //   1592: ldc_w 725
    //   1595: invokestatic 721	com/tapjoy/TapjoyConnectCore:f	(Ljava/lang/String;)Z
    //   1598: istore_3
    //   1599: goto -698 -> 901
    //   1602: astore_1
    //   1603: ldc_w 484
    //   1606: new 486	java/lang/StringBuilder
    //   1609: dup
    //   1610: ldc_w 727
    //   1613: invokespecial 491	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1616: aload_1
    //   1617: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   1620: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1623: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1626: invokestatic 678	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1629: goto -724 -> 905
    //   1632: ldc -98
    //   1634: astore_1
    //   1635: goto -554 -> 1081
    //   1638: astore_1
    //   1639: goto -1485 -> 154
    //   1642: iconst_0
    //   1643: istore_3
    //   1644: goto -743 -> 901
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1647	0	this	TapjoyConnectCore
    //   0	1647	1	paramContext	Context
    //   127	1248	2	i1	int
    //   305	1339	3	bool	boolean
    //   401	684	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   199	218	1115	android/content/pm/PackageManager$NameNotFoundException
    //   61	77	1128	com/tapjoy/TapjoyIntegrationException
    //   77	105	1128	com/tapjoy/TapjoyIntegrationException
    //   105	136	1128	com/tapjoy/TapjoyIntegrationException
    //   136	154	1128	com/tapjoy/TapjoyIntegrationException
    //   154	169	1128	com/tapjoy/TapjoyIntegrationException
    //   169	186	1128	com/tapjoy/TapjoyIntegrationException
    //   190	199	1128	com/tapjoy/TapjoyIntegrationException
    //   199	218	1128	com/tapjoy/TapjoyIntegrationException
    //   218	260	1128	com/tapjoy/TapjoyIntegrationException
    //   260	299	1128	com/tapjoy/TapjoyIntegrationException
    //   299	306	1128	com/tapjoy/TapjoyIntegrationException
    //   310	323	1128	com/tapjoy/TapjoyIntegrationException
    //   327	332	1128	com/tapjoy/TapjoyIntegrationException
    //   336	345	1128	com/tapjoy/TapjoyIntegrationException
    //   349	366	1128	com/tapjoy/TapjoyIntegrationException
    //   366	379	1128	com/tapjoy/TapjoyIntegrationException
    //   383	403	1128	com/tapjoy/TapjoyIntegrationException
    //   408	427	1128	com/tapjoy/TapjoyIntegrationException
    //   427	446	1128	com/tapjoy/TapjoyIntegrationException
    //   446	453	1128	com/tapjoy/TapjoyIntegrationException
    //   457	487	1128	com/tapjoy/TapjoyIntegrationException
    //   487	537	1128	com/tapjoy/TapjoyIntegrationException
    //   539	586	1128	com/tapjoy/TapjoyIntegrationException
    //   590	594	1128	com/tapjoy/TapjoyIntegrationException
    //   594	619	1128	com/tapjoy/TapjoyIntegrationException
    //   619	648	1128	com/tapjoy/TapjoyIntegrationException
    //   653	660	1128	com/tapjoy/TapjoyIntegrationException
    //   664	722	1128	com/tapjoy/TapjoyIntegrationException
    //   722	734	1128	com/tapjoy/TapjoyIntegrationException
    //   734	770	1128	com/tapjoy/TapjoyIntegrationException
    //   770	847	1128	com/tapjoy/TapjoyIntegrationException
    //   847	899	1128	com/tapjoy/TapjoyIntegrationException
    //   901	905	1128	com/tapjoy/TapjoyIntegrationException
    //   905	938	1128	com/tapjoy/TapjoyIntegrationException
    //   938	968	1128	com/tapjoy/TapjoyIntegrationException
    //   968	1026	1128	com/tapjoy/TapjoyIntegrationException
    //   1026	1077	1128	com/tapjoy/TapjoyIntegrationException
    //   1081	1105	1128	com/tapjoy/TapjoyIntegrationException
    //   1105	1114	1128	com/tapjoy/TapjoyIntegrationException
    //   1116	1128	1128	com/tapjoy/TapjoyIntegrationException
    //   1154	1180	1128	com/tapjoy/TapjoyIntegrationException
    //   1209	1235	1128	com/tapjoy/TapjoyIntegrationException
    //   1238	1247	1128	com/tapjoy/TapjoyIntegrationException
    //   1250	1257	1128	com/tapjoy/TapjoyIntegrationException
    //   1261	1301	1128	com/tapjoy/TapjoyIntegrationException
    //   1304	1337	1128	com/tapjoy/TapjoyIntegrationException
    //   1337	1356	1128	com/tapjoy/TapjoyIntegrationException
    //   1361	1373	1128	com/tapjoy/TapjoyIntegrationException
    //   1378	1423	1128	com/tapjoy/TapjoyIntegrationException
    //   1423	1442	1128	com/tapjoy/TapjoyIntegrationException
    //   1445	1457	1128	com/tapjoy/TapjoyIntegrationException
    //   1460	1469	1128	com/tapjoy/TapjoyIntegrationException
    //   1473	1499	1128	com/tapjoy/TapjoyIntegrationException
    //   1503	1529	1128	com/tapjoy/TapjoyIntegrationException
    //   1533	1559	1128	com/tapjoy/TapjoyIntegrationException
    //   1562	1579	1128	com/tapjoy/TapjoyIntegrationException
    //   1582	1599	1128	com/tapjoy/TapjoyIntegrationException
    //   1603	1629	1128	com/tapjoy/TapjoyIntegrationException
    //   260	299	1153	java/lang/Exception
    //   61	77	1183	com/tapjoy/TapjoyException
    //   77	105	1183	com/tapjoy/TapjoyException
    //   105	136	1183	com/tapjoy/TapjoyException
    //   136	154	1183	com/tapjoy/TapjoyException
    //   154	169	1183	com/tapjoy/TapjoyException
    //   169	186	1183	com/tapjoy/TapjoyException
    //   190	199	1183	com/tapjoy/TapjoyException
    //   199	218	1183	com/tapjoy/TapjoyException
    //   218	260	1183	com/tapjoy/TapjoyException
    //   260	299	1183	com/tapjoy/TapjoyException
    //   299	306	1183	com/tapjoy/TapjoyException
    //   310	323	1183	com/tapjoy/TapjoyException
    //   327	332	1183	com/tapjoy/TapjoyException
    //   336	345	1183	com/tapjoy/TapjoyException
    //   349	366	1183	com/tapjoy/TapjoyException
    //   366	379	1183	com/tapjoy/TapjoyException
    //   383	403	1183	com/tapjoy/TapjoyException
    //   408	427	1183	com/tapjoy/TapjoyException
    //   427	446	1183	com/tapjoy/TapjoyException
    //   446	453	1183	com/tapjoy/TapjoyException
    //   457	487	1183	com/tapjoy/TapjoyException
    //   487	537	1183	com/tapjoy/TapjoyException
    //   539	586	1183	com/tapjoy/TapjoyException
    //   590	594	1183	com/tapjoy/TapjoyException
    //   594	619	1183	com/tapjoy/TapjoyException
    //   619	648	1183	com/tapjoy/TapjoyException
    //   653	660	1183	com/tapjoy/TapjoyException
    //   664	722	1183	com/tapjoy/TapjoyException
    //   722	734	1183	com/tapjoy/TapjoyException
    //   734	770	1183	com/tapjoy/TapjoyException
    //   770	847	1183	com/tapjoy/TapjoyException
    //   847	899	1183	com/tapjoy/TapjoyException
    //   901	905	1183	com/tapjoy/TapjoyException
    //   905	938	1183	com/tapjoy/TapjoyException
    //   938	968	1183	com/tapjoy/TapjoyException
    //   968	1026	1183	com/tapjoy/TapjoyException
    //   1026	1077	1183	com/tapjoy/TapjoyException
    //   1081	1105	1183	com/tapjoy/TapjoyException
    //   1105	1114	1183	com/tapjoy/TapjoyException
    //   1116	1128	1183	com/tapjoy/TapjoyException
    //   1154	1180	1183	com/tapjoy/TapjoyException
    //   1209	1235	1183	com/tapjoy/TapjoyException
    //   1238	1247	1183	com/tapjoy/TapjoyException
    //   1250	1257	1183	com/tapjoy/TapjoyException
    //   1261	1301	1183	com/tapjoy/TapjoyException
    //   1304	1337	1183	com/tapjoy/TapjoyException
    //   1337	1356	1183	com/tapjoy/TapjoyException
    //   1361	1373	1183	com/tapjoy/TapjoyException
    //   1378	1423	1183	com/tapjoy/TapjoyException
    //   1423	1442	1183	com/tapjoy/TapjoyException
    //   1445	1457	1183	com/tapjoy/TapjoyException
    //   1460	1469	1183	com/tapjoy/TapjoyException
    //   1473	1499	1183	com/tapjoy/TapjoyException
    //   1503	1529	1183	com/tapjoy/TapjoyException
    //   1533	1559	1183	com/tapjoy/TapjoyException
    //   1562	1579	1183	com/tapjoy/TapjoyException
    //   1582	1599	1183	com/tapjoy/TapjoyException
    //   1603	1629	1183	com/tapjoy/TapjoyException
    //   310	323	1208	java/lang/Exception
    //   327	332	1208	java/lang/Exception
    //   336	345	1208	java/lang/Exception
    //   349	366	1208	java/lang/Exception
    //   457	487	1260	java/lang/Exception
    //   487	537	1260	java/lang/Exception
    //   539	586	1260	java/lang/Exception
    //   590	594	1260	java/lang/Exception
    //   594	619	1260	java/lang/Exception
    //   1250	1257	1260	java/lang/Exception
    //   1304	1337	1260	java/lang/Exception
    //   1337	1356	1260	java/lang/Exception
    //   1361	1373	1260	java/lang/Exception
    //   1378	1423	1260	java/lang/Exception
    //   1423	1442	1260	java/lang/Exception
    //   1445	1457	1260	java/lang/Exception
    //   664	722	1472	java/lang/Exception
    //   722	734	1502	java/lang/Exception
    //   734	770	1532	java/lang/Exception
    //   847	899	1602	java/lang/Exception
    //   901	905	1602	java/lang/Exception
    //   1562	1579	1602	java/lang/Exception
    //   1582	1599	1602	java/lang/Exception
    //   136	154	1638	java/lang/Exception
  }
  
  private static String a(long paramLong)
  {
    try
    {
      String str = TapjoyUtil.SHA256(w + ":" + p() + ":" + paramLong + ":" + N);
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
      paramString = TapjoyUtil.SHA256(w + ":" + p() + ":" + paramLong + ":" + N + ":" + paramString);
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
      an = "";
      Iterator localIterator = aj.getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (((localApplicationInfo.flags & 0x1) != 1) && (paramList.contains(localApplicationInfo.packageName)))
        {
          TapjoyLog.d("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
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
    boolean bool2 = false;
    FeatureInfo[] arrayOfFeatureInfo = aj.getSystemAvailableFeatures();
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
        if (aj.checkPermission(paramString2, g.getPackageName()) == 0) {
          bool1 = true;
        }
      }
      return bool1;
      label66:
      i1 += 1;
    }
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
    //   1: invokestatic 872	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   4: astore_0
    //   5: aload_0
    //   6: astore_3
    //   7: aload_0
    //   8: invokevirtual 875	com/tapjoy/internal/bs:d	()Ljava/util/Map;
    //   11: astore 5
    //   13: aload_0
    //   14: astore_3
    //   15: aload 5
    //   17: ldc_w 877
    //   20: invokeinterface 880 2 0
    //   25: checkcast 319	java/lang/String
    //   28: invokestatic 884	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   31: astore 4
    //   33: aload_0
    //   34: astore_3
    //   35: aload 5
    //   37: ldc_w 886
    //   40: invokeinterface 880 2 0
    //   45: checkcast 319	java/lang/String
    //   48: invokestatic 884	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   51: astore 7
    //   53: aload_0
    //   54: astore_3
    //   55: aload 5
    //   57: ldc_w 888
    //   60: invokeinterface 880 2 0
    //   65: checkcast 319	java/lang/String
    //   68: invokestatic 884	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   71: astore 8
    //   73: aload_0
    //   74: astore_3
    //   75: aload 5
    //   77: ldc_w 890
    //   80: invokeinterface 880 2 0
    //   85: checkcast 319	java/lang/String
    //   88: invokestatic 884	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   91: astore 9
    //   93: aload_0
    //   94: astore_3
    //   95: aload 5
    //   97: ldc_w 892
    //   100: invokeinterface 880 2 0
    //   105: checkcast 319	java/lang/String
    //   108: invokestatic 884	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   111: astore 6
    //   113: aload_0
    //   114: astore_3
    //   115: aload 5
    //   117: ldc_w 894
    //   120: invokeinterface 880 2 0
    //   125: checkcast 896	java/lang/Boolean
    //   128: astore 5
    //   130: aload 5
    //   132: ifnull +13 -> 145
    //   135: aload_0
    //   136: astore_3
    //   137: aload 5
    //   139: invokevirtual 899	java/lang/Boolean:booleanValue	()Z
    //   142: putstatic 269	com/tapjoy/TapjoyConnectCore:aX	Z
    //   145: aload_0
    //   146: astore_3
    //   147: new 901	com/tapjoy/internal/ApiKeyDecoded
    //   150: dup
    //   151: aload 8
    //   153: invokespecial 902	com/tapjoy/internal/ApiKeyDecoded:<init>	(Ljava/lang/String;)V
    //   156: astore 10
    //   158: aload_0
    //   159: astore_3
    //   160: aload 10
    //   162: invokevirtual 906	com/tapjoy/internal/ApiKeyDecoded:getKeyUsage	()Lcom/tapjoy/internal/ApiKeyDecoded$KeyUsage;
    //   165: getstatic 912	com/tapjoy/internal/ApiKeyDecoded$KeyUsage:RPC_ANALYTICS	Lcom/tapjoy/internal/ApiKeyDecoded$KeyUsage;
    //   168: if_acmpeq +33 -> 201
    //   171: aload_0
    //   172: astore_3
    //   173: new 865	java/io/IOException
    //   176: dup
    //   177: ldc_w 914
    //   180: invokespecial 915	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   183: athrow
    //   184: astore_3
    //   185: ldc_w 484
    //   188: aload_3
    //   189: invokevirtual 916	java/io/IOException:getMessage	()Ljava/lang/String;
    //   192: invokestatic 918	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   195: aload_0
    //   196: invokestatic 923	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   199: iconst_0
    //   200: ireturn
    //   201: aload_0
    //   202: astore_3
    //   203: aload 10
    //   205: invokevirtual 926	com/tapjoy/internal/ApiKeyDecoded:getAppId	()Ljava/lang/String;
    //   208: invokestatic 929	com/tapjoy/internal/ApiKeyDecoded:get5RocksAppId	(Ljava/lang/String;)Ljava/lang/String;
    //   211: astore 5
    //   213: aload_0
    //   214: astore_3
    //   215: aload 10
    //   217: invokevirtual 932	com/tapjoy/internal/ApiKeyDecoded:getSecretKey	()Ljava/lang/String;
    //   220: astore 10
    //   222: aload 4
    //   224: ifnonnull +224 -> 448
    //   227: aload 5
    //   229: astore 4
    //   231: aload_0
    //   232: astore_3
    //   233: invokestatic 937	com/tapjoy/internal/jo:a	()Lcom/tapjoy/internal/jo;
    //   236: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   239: aload 8
    //   241: ldc_w 408
    //   244: ldc_w 939
    //   247: aload 5
    //   249: aload 10
    //   251: invokevirtual 942	com/tapjoy/internal/jo:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   254: aload_0
    //   255: astore_3
    //   256: aload 4
    //   258: putstatic 236	com/tapjoy/TapjoyConnectCore:Y	Ljava/lang/String;
    //   261: aload_0
    //   262: astore_3
    //   263: aload 7
    //   265: putstatic 238	com/tapjoy/TapjoyConnectCore:Z	Ljava/lang/String;
    //   268: aload_0
    //   269: astore_3
    //   270: aload 8
    //   272: putstatic 240	com/tapjoy/TapjoyConnectCore:aa	Ljava/lang/String;
    //   275: aload_0
    //   276: astore_3
    //   277: aload 9
    //   279: putstatic 242	com/tapjoy/TapjoyConnectCore:ab	Ljava/lang/String;
    //   282: aload_0
    //   283: astore_3
    //   284: new 601	java/util/ArrayList
    //   287: dup
    //   288: invokespecial 943	java/util/ArrayList:<init>	()V
    //   291: astore 4
    //   293: aload 6
    //   295: ifnull +64 -> 359
    //   298: aload_0
    //   299: astore_3
    //   300: aload 6
    //   302: ldc_w 771
    //   305: invokevirtual 947	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
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
    //   329: invokevirtual 850	java/lang/String:trim	()Ljava/lang/String;
    //   332: astore 6
    //   334: aload_0
    //   335: astore_3
    //   336: aload 6
    //   338: invokevirtual 471	java/lang/String:length	()I
    //   341: ifle +110 -> 451
    //   344: aload_0
    //   345: astore_3
    //   346: aload 4
    //   348: aload 6
    //   350: invokeinterface 948 2 0
    //   355: pop
    //   356: goto +95 -> 451
    //   359: aload_0
    //   360: astore_3
    //   361: aload 4
    //   363: invokeinterface 951 1 0
    //   368: ifne +10 -> 378
    //   371: aload_0
    //   372: astore_3
    //   373: aload 4
    //   375: invokestatic 855	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   378: aload_0
    //   379: astore_3
    //   380: aload_0
    //   381: invokevirtual 954	com/tapjoy/internal/bs:close	()V
    //   384: aconst_null
    //   385: invokestatic 923	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   388: iconst_1
    //   389: ireturn
    //   390: astore 4
    //   392: aconst_null
    //   393: astore_0
    //   394: aload_0
    //   395: astore_3
    //   396: ldc_w 484
    //   399: aload 4
    //   401: invokevirtual 955	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   404: invokestatic 918	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   407: aload_0
    //   408: invokestatic 923	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   411: goto -212 -> 199
    //   414: astore_0
    //   415: aconst_null
    //   416: astore_3
    //   417: aload_3
    //   418: invokestatic 923	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
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
  
  private static void e()
  {
    if (!cs.c(O)) {
      jo.a().a(g, h, "11.6.0", "https://rpc.tapjoy.com/", O, N);
    }
    if (k != null) {
      k.onConnectFailure();
    }
  }
  
  /* Error */
  private static boolean e(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 872	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 964	com/tapjoy/internal/bs:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 966	com/tapjoy/internal/bs:s	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 484
    //   25: ldc_w 968
    //   28: invokestatic 501	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 954	com/tapjoy/internal/bs:close	()V
    //   37: aconst_null
    //   38: invokestatic 923	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 954	com/tapjoy/internal/bs:close	()V
    //   49: aconst_null
    //   50: invokestatic 923	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   53: ldc_w 484
    //   56: new 503	com/tapjoy/TapjoyErrorMessage
    //   59: dup
    //   60: getstatic 509	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   63: ldc_w 970
    //   66: invokespecial 514	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   69: invokestatic 517	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   72: iconst_0
    //   73: ireturn
    //   74: astore_2
    //   75: aconst_null
    //   76: astore_1
    //   77: aload_1
    //   78: astore_0
    //   79: ldc_w 484
    //   82: aload_2
    //   83: invokevirtual 916	java/io/IOException:getMessage	()Ljava/lang/String;
    //   86: invokestatic 918	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload_1
    //   90: invokestatic 923	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   93: goto -40 -> 53
    //   96: astore_2
    //   97: aconst_null
    //   98: astore_1
    //   99: aload_1
    //   100: astore_0
    //   101: ldc_w 484
    //   104: aload_2
    //   105: invokevirtual 955	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   108: invokestatic 918	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   111: aload_1
    //   112: invokestatic 923	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   115: goto -62 -> 53
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_0
    //   121: aload_0
    //   122: invokestatic 923	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
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
  
  private static Map f()
  {
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "plugin", P, true);
    TapjoyUtil.safePut(localHashMap3, "sdk_type", Q, true);
    TapjoyUtil.safePut(localHashMap3, "app_id", w, true);
    TapjoyUtil.safePut(localHashMap3, "library_version", y, true);
    TapjoyUtil.safePut(localHashMap3, "library_revision", "9aef39c", true);
    TapjoyUtil.safePut(localHashMap3, "bridge_version", z, true);
    TapjoyUtil.safePut(localHashMap3, "app_version", x, true);
    localHashMap2.putAll(localHashMap3);
    localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "device_name", s, true);
    TapjoyUtil.safePut(localHashMap3, "platform", F, true);
    TapjoyUtil.safePut(localHashMap3, "os_version", v, true);
    TapjoyUtil.safePut(localHashMap3, "device_manufacturer", t, true);
    TapjoyUtil.safePut(localHashMap3, "device_type", u, true);
    TapjoyUtil.safePut(localHashMap3, "screen_layout_size", C, true);
    TapjoyUtil.safePut(localHashMap3, "device_location", String.valueOf(E), true);
    TapjoyUtil.safePut(localHashMap3, "store_name", M, true);
    TapjoyUtil.safePut(localHashMap3, "store_view", String.valueOf(X), true);
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
    TapjoyUtil.safePut(localHashMap3, "screen_density", A, true);
    localHashMap2.putAll(localHashMap3);
    localHashMap3 = new HashMap();
    if (m())
    {
      TapjoyUtil.safePut(localHashMap3, "advertising_id", c, true);
      TapjoyUtil.safePut(localHashMap3, "ad_tracking_enabled", String.valueOf(d), true);
    }
    if (!n())
    {
      TapjoyUtil.safePut(localHashMap3, "android_id", m, true);
      TapjoyUtil.safePut(localHashMap3, "udid", p, true);
      TapjoyUtil.safePut(localHashMap3, "mac_address", q, true);
    }
    TapjoyUtil.safePut(localHashMap3, "threatmetrix_session_id", o, true);
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
    if ((n == null) || (n.length() == 0) || (System.currentTimeMillis() - ag > 1800000L)) {
      n = o();
    }
    for (;;)
    {
      TapjoyUtil.safePut(localHashMap3, "session_id", n, true);
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
      h();
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "analytics_id", ap, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_id", aq, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_sign", ar, true);
      TapjoyUtil.safePut(localHashMap3, "display_d", aR, true);
      TapjoyUtil.safePut(localHashMap3, "display_w", aS, true);
      TapjoyUtil.safePut(localHashMap3, "display_h", aT, true);
      TapjoyUtil.safePut(localHashMap3, "country_sim", aU, true);
      TapjoyUtil.safePut(localHashMap3, "timezone", aV, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "pkg_ver", as, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_rev", at, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_data_ver", au, true);
      TapjoyUtil.safePut(localHashMap3, "installer", av, true);
      if (cs.c(M)) {
        TapjoyUtil.safePut(localHashMap3, "store_name", aW, true);
      }
      localHashMap2.putAll(localHashMap3);
      localHashMap2.putAll(g());
      localHashMap1.putAll(localHashMap2);
      return localHashMap1;
      ag = System.currentTimeMillis();
    }
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
  
  private static Map g()
  {
    HashMap localHashMap = new HashMap();
    TapjoyUtil.safePut(localHashMap, "installed", aw, true);
    TapjoyUtil.safePut(localHashMap, "referrer", ax, true);
    TapjoyUtil.safePut(localHashMap, "user_level", ay, true);
    TapjoyUtil.safePut(localHashMap, "friend_count", az, true);
    TapjoyUtil.safePut(localHashMap, "uv1", aA, true);
    TapjoyUtil.safePut(localHashMap, "uv2", aB, true);
    TapjoyUtil.safePut(localHashMap, "uv3", aC, true);
    TapjoyUtil.safePut(localHashMap, "uv4", aD, true);
    TapjoyUtil.safePut(localHashMap, "uv5", aE, true);
    Iterator localIterator = aF.iterator();
    int i1 = 0;
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      TapjoyUtil.safePut(localHashMap, "user_tags[" + i1 + "]", str, true);
      i1 += 1;
    }
    TapjoyUtil.safePut(localHashMap, "fq7", aG, true);
    TapjoyUtil.safePut(localHashMap, "fq30", aH, true);
    TapjoyUtil.safePut(localHashMap, "session_total_count", aI, true);
    TapjoyUtil.safePut(localHashMap, "session_total_length", aJ, true);
    TapjoyUtil.safePut(localHashMap, "session_last_at", aK, true);
    TapjoyUtil.safePut(localHashMap, "session_last_length", aL, true);
    TapjoyUtil.safePut(localHashMap, "purchase_currency", aM, true);
    TapjoyUtil.safePut(localHashMap, "purchase_total_count", aN, true);
    TapjoyUtil.safePut(localHashMap, "purchase_total_price", aO, true);
    TapjoyUtil.safePut(localHashMap, "purchase_last_price", aP, true);
    TapjoyUtil.safePut(localHashMap, "purchase_last_at", aQ, true);
    return localHashMap;
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
    return m;
  }
  
  public static String getAppID()
  {
    return w;
  }
  
  public static String getAwardCurrencyVerifier(long paramLong, int paramInt, String paramString)
  {
    try
    {
      paramString = TapjoyUtil.SHA256(w + ":" + p() + ":" + paramLong + ":" + N + ":" + paramInt + ":" + paramString);
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
    return G;
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
    return p;
  }
  
  public static float getDeviceScreenDensityScale()
  {
    return B;
  }
  
  public static Map getGenericURLParams()
  {
    Map localMap = f();
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
    return D;
  }
  
  private static void h()
  {
    kf.n localN = jo.a(g).a(true);
    ap = localN.c.h();
    aq = localN.c.y();
    ar = localN.c.A();
    aR = localN.c.c;
    aS = localN.c.d;
    aT = localN.c.e;
    aU = localN.c.E();
    aV = localN.c.w();
    as = localN.d.f();
    at = localN.d.c;
    au = localN.d.i();
    av = localN.d.k();
    aW = localN.d.m();
    aw = localN.e.c;
    ax = localN.e.g();
    ay = localN.e.p;
    az = localN.e.q;
    aA = localN.e.C();
    aB = localN.e.E();
    aC = localN.e.G();
    aD = localN.e.I();
    aE = localN.e.K();
    aF = new HashSet(localN.e.r);
    aG = localN.e.d;
    aH = localN.e.e;
    aI = localN.e.g;
    aJ = localN.e.h;
    aK = localN.e.i;
    aL = localN.e.j;
    aM = localN.e.p();
    aN = localN.e.k;
    aO = localN.e.l;
    aP = localN.e.n;
    aQ = localN.e.m;
  }
  
  private static boolean h(String paramString)
  {
    return aj.checkPermission(paramString, g.getPackageName()) == 0;
  }
  
  private static void i()
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
  
  public static boolean isConnected()
  {
    return ai;
  }
  
  public static boolean isUnitTestMode()
  {
    return getConnectFlagValue("unit_test_mode") == "true";
  }
  
  public static boolean isViewOpen()
  {
    return ao;
  }
  
  private static void j()
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
  
  private void k()
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
        if (!l.contains(((ActivityInfo)localObject3).name)) {
          continue;
        }
        i1 = l.indexOf(((ActivityInfo)localObject3).name);
        Vector localVector;
        try
        {
          Class.forName((String)l.get(i1));
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
            throw new TapjoyIntegrationException(localVector.toString() + " property is not specified in manifest configChanges for " + (String)l.get(i1));
          }
        }
        catch (ClassNotFoundException localClassNotFoundException1)
        {
          throw new TapjoyIntegrationException("[ClassNotFoundException] Could not find dependency class " + (String)l.get(i1));
        }
        throw new TapjoyIntegrationException(localVector.toString() + " properties are not specified in manifest configChanges for " + (String)l.get(i1));
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        throw new TapjoyIntegrationException("NameNotFoundException: Could not find package.");
      }
      label295:
      if ((Build.VERSION.SDK_INT >= 13) && ((((ActivityInfo)localObject3).configChanges & 0x400) != 1024)) {
        TapjoyLog.w("TapjoyConnect", "WARNING -- screenSize property is not specified in manifest configChanges for " + (String)l.get(i1));
      }
      if ((Build.VERSION.SDK_INT >= 11) && (((ActivityInfo)localObject3).name.equals("com.tapjoy.TJAdUnitActivity")) && ((((ActivityInfo)localObject3).flags & 0x200) != 512)) {
        throw new TapjoyIntegrationException("'hardwareAccelerated' property not specified in manifest for " + (String)l.get(i1));
      }
      l.remove(i1);
    }
    if (l.size() != 0)
    {
      if (l.size() == 1) {
        throw new TapjoyIntegrationException("Missing " + l.size() + " dependency class in manifest: " + l.toString());
      }
      throw new TapjoyIntegrationException("Missing " + l.size() + " dependency classes in manifest: " + l.toString());
    }
    l();
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
    this.al.checkGooglePlayIntegration();
  }
  
  private static void l()
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
  
  private static boolean m()
  {
    return (c != null) && (c.length() > 0);
  }
  
  private static boolean n()
  {
    return (m()) && (getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS") != null) && (getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS").equals("true"));
  }
  
  private static String o()
  {
    TapjoyLog.i("TapjoyConnect", "generating sessionID...");
    try
    {
      str = TapjoyUtil.SHA256(System.currentTimeMillis() / 1000L + w + p);
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
  
  private static String p()
  {
    int i2 = 1;
    if (n()) {
      return c;
    }
    if ((p != null) && (p.length() > 0)) {}
    for (int i1 = 1; i1 != 0; i1 = 0) {
      return p;
    }
    if ((q != null) && (q.length() > 0)) {}
    for (i1 = 1; i1 != 0; i1 = 0) {
      return q;
    }
    if (m()) {
      return c;
    }
    if ((m != null) && (m.length() > 0)) {}
    for (i1 = i2; i1 != 0; i1 = 0) {
      return m;
    }
    TapjoyLog.e("TapjoyConnect", "Error -- no valid device identifier");
    return null;
  }
  
  private void q()
  {
    TapjoyLog.i("TapjoyConnect", "Initializing mediation partners with configs");
    try
    {
      Object localObject = (ArrayList)am.get("TJC_OPTION_MEDIATION_CONFIGS");
      if (localObject != null)
      {
        localObject = ((ArrayList)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          new TapjoyConnectCore.5(this, (TJMediationNetwork)((Iterator)localObject).next()).run();
        }
      }
      return;
    }
    catch (ClassCastException localClassCastException)
    {
      TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Invalid type! Make sure to pass in an ArrayList<TJMediationNetwork> type as your mediation configs."));
    }
  }
  
  public static void removeOfflineLog(String paramString)
  {
    SharedPreferences.Editor localEditor = g.getSharedPreferences("tapjoyOfflineLog", 0).edit();
    localEditor.remove(paramString);
    localEditor.commit();
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
    w = localApiKeyDecoded.getAppId();
    N = localApiKeyDecoded.getSecretKey();
    O = localApiKeyDecoded.getAnalyticsId();
    jo.a(paramContext).j = paramString;
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
    D = paramString;
    TapjoyLog.d("TapjoyConnect", "URL parameters: " + getURLParams());
    new Thread(new TapjoyConnectCore.3()).start();
  }
  
  public static void setViewShowing(boolean paramBoolean)
  {
    ao = paramBoolean;
  }
  
  public static void viewDidClose()
  {
    ao = false;
    InternalObservables.onPlacementContentDismissed.notifyObservers();
  }
  
  public static void viewWillOpen()
  {
    ao = true;
  }
  
  public void actionComplete(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "actionComplete: " + paramString);
    Map localMap = f();
    TapjoyUtil.safePut(localMap, "app_id", paramString, true);
    localMap.putAll(getTimeStampAndVerifierParams());
    TapjoyLog.d("TapjoyConnect", "PPA URL parameters: " + localMap);
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
      o();
      this.af = false;
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
    localObject1 = j.getResponseFromURL((String)localObject1 + "api/connect/v3.json?", null, null, getURLParams());
    if ((localObject1 != null) && (((TapjoyHttpURLResponse)localObject1).statusCode == 200))
    {
      Object localObject3;
      if (d(((TapjoyHttpURLResponse)localObject1).response))
      {
        TapjoyLog.i("TapjoyConnect", "Successfully connected to Tapjoy");
        ai = true;
        if (aX) {
          if (Build.VERSION.SDK_INT <= 8) {}
        }
        for (;;)
        {
          try
          {
            doProfileAsync();
            q();
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
        sendOfflineLogs();
        if (k != null) {
          k.onConnectSuccess();
        }
        InternalObservables.onConnectSucceeded.notifyObservers();
      }
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
            TapjoyLog.d("TapjoyConnect", "Successfully pinged sdkless api.");
          }
        }
        return;
        e();
      }
    }
    e();
  }
  
  public void doProfileAsync()
  {
    boolean bool = true;
    if (g == null)
    {
      TapjoyLog.d("TapjoyConnect", "Error retrieving Threatmetrix sesson ID. Context is null");
      return;
    }
    iu localIu;
    ho localHo;
    try
    {
      TapjoyLog.d("TapjoyConnect", "Initializing Threatmetrix: 3.2-100");
      this.ak = new iu("rrx68giz");
      localIu = this.ak;
      localHo = new ho();
      localHo.a = 10;
      localHo.i = g;
      localHo.q = true;
      localHo.s = "content-js.tapjoy.com";
      localHo.c = new TapjoyConnectCore.1(this);
      if (localHo.i == null) {
        throw new IllegalArgumentException("Invalid Context");
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      TapjoyLog.d("TapjoyConnect", "Exception caught from running Threatmetrix. -- " + localIllegalArgumentException.getMessage());
      return;
    }
    Object localObject1;
    iv localIv;
    if (localIu.d.d())
    {
      localIu.n = localHo.i.getApplicationContext();
      localIu.c.J = localIu.n;
      localObject1 = localIu.f;
      int i2 = localHo.h;
      int i1 = i2;
      if (localHo.o) {
        i1 = i2 & 0xFFFFFFD9;
      }
      i2 = i1;
      if (localHo.p) {
        i2 = i1 & 0xCFFF;
      }
      ((AtomicLong)localObject1).set(i2);
      localIu.c.O = localIu.f.get();
      localIu.g = (localHo.a * 1000);
      localIu.c.z = localHo.b;
      localIu.q = localHo.c;
      if (!localIu.c.a(localHo.s))
      {
        localIu.d.e();
        throw new IllegalArgumentException("Invalid Profile Server");
      }
      if (localHo.d)
      {
        localObject1 = localIu.t;
        localObject2 = localIu.n;
        long l1 = localHo.e;
        long l2 = localHo.f;
        i1 = localHo.g;
        ((ir)localObject1).f = ((Context)localObject2);
        ((ir)localObject1).c = l1;
        ((ir)localObject1).d = l2;
        ((ir)localObject1).e = i1;
        ((ir)localObject1).b();
      }
      localIu.k = localHo.j;
      localIu.j = localHo.k;
      localIu.i = localHo.m;
      localIu.h = localHo.l;
      localIu.m = localHo.n;
      if (localHo.r) {
        break label639;
      }
      localIu.l = bool;
      Object localObject2 = localIu.n.getPackageName();
      localObject1 = localIu.c.w;
      if (localIu.o == null) {
        localIu.o = ((String)localObject2 + "TDM" + (String)localObject1);
      }
      localIv = localIu.c;
      if (localObject2 == null) {
        break label644;
      }
      localObject1 = localObject2;
      if (((String)localObject2).isEmpty()) {
        break label644;
      }
    }
    for (;;)
    {
      localIv.t = ("http://" + (String)localObject1);
      localIv.p = ("http://" + (String)localObject1 + "/mobile");
      new Thread(new iu.1(localIu, localIu, localHo)).start();
      this.ak.a(new if());
      return;
      label639:
      bool = false;
      break;
      label644:
      localObject1 = "TrustDefenderMobileSDK";
    }
  }
  
  public void enablePaidAppWithActionID(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "enablePaidAppWithActionID: " + paramString);
    ac = paramString;
    this.ad = g.getSharedPreferences("tjcPrefrences", 0).getLong("tapjoy_elapsed_time", 0L);
    TapjoyLog.d("TapjoyConnect", "paidApp elapsed: " + this.ad);
    if (this.ad >= 900000L) {
      if ((ac != null) && (ac.length() > 0))
      {
        TapjoyLog.d("TapjoyConnect", "Calling PPA actionComplete...");
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
    //   0: ldc_w 1954
    //   3: invokestatic 1516	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 1956
    //   9: invokevirtual 1960	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 1965	java/lang/reflect/Field:isAccessible	()Z
    //   17: ifne +8 -> 25
    //   20: aload_1
    //   21: iconst_1
    //   22: invokevirtual 1968	java/lang/reflect/Field:setAccessible	(Z)V
    //   25: aload_1
    //   26: ldc_w 395
    //   29: invokevirtual 1969	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: invokevirtual 1276	java/lang/Object:toString	()Ljava/lang/String;
    //   35: astore_1
    //   36: ldc_w 484
    //   39: new 486	java/lang/StringBuilder
    //   42: dup
    //   43: ldc_w 1971
    //   46: invokespecial 491	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   49: aload_1
    //   50: invokevirtual 495	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 498	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 501	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload_1
    //   60: areturn
    //   61: astore_2
    //   62: aconst_null
    //   63: astore_1
    //   64: ldc_w 484
    //   67: aload_2
    //   68: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   71: invokestatic 678	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
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
    TapjoyLog.d("TapjoyConnect", "Releasing core static instance.");
  }
  
  public void setCurrencyMultiplier(float paramFloat)
  {
    TapjoyLog.i("TapjoyConnect", "setVirtualCurrencyMultiplier: " + paramFloat);
    S = paramFloat;
  }
}
