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
import com.tapjoy.internal.ht;
import com.tapjoy.internal.if;
import com.tapjoy.internal.iz;
import com.tapjoy.internal.jq.a;
import com.tapjoy.internal.jq.l;
import com.tapjoy.internal.jq.n;
import com.tapjoy.internal.jq.z;
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
import java.util.concurrent.locks.ReentrantLock;

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
  private if ak;
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
    //   1: invokespecial 289	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 291	com/tapjoy/TapjoyConnectCore:ad	J
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 293	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Timer;
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 295	com/tapjoy/TapjoyConnectCore:af	Z
    //   19: aload_0
    //   20: iconst_0
    //   21: putfield 297	com/tapjoy/TapjoyConnectCore:ah	Z
    //   24: aload_1
    //   25: putstatic 136	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   28: new 299	com/tapjoy/TapjoyURLConnection
    //   31: dup
    //   32: invokespecial 300	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   35: putstatic 142	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   38: getstatic 136	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   41: invokevirtual 306	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   44: putstatic 308	com/tapjoy/TapjoyConnectCore:aj	Landroid/content/pm/PackageManager;
    //   47: aload_0
    //   48: new 310	com/tapjoy/TapjoyGpsHelper
    //   51: dup
    //   52: getstatic 136	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   55: invokespecial 312	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   58: putfield 314	com/tapjoy/TapjoyConnectCore:al	Lcom/tapjoy/TapjoyGpsHelper;
    //   61: getstatic 271	com/tapjoy/TapjoyConnectCore:am	Ljava/util/Hashtable;
    //   64: ifnonnull +13 -> 77
    //   67: new 316	java/util/Hashtable
    //   70: dup
    //   71: invokespecial 317	java/util/Hashtable:<init>	()V
    //   74: putstatic 271	com/tapjoy/TapjoyConnectCore:am	Ljava/util/Hashtable;
    //   77: ldc_w 319
    //   80: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   83: ifnull +22 -> 105
    //   86: ldc_w 319
    //   89: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   92: ldc_w 325
    //   95: invokevirtual 331	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   98: ifeq +7 -> 105
    //   101: iconst_1
    //   102: invokestatic 337	com/tapjoy/TapjoyLog:setDebugEnabled	(Z)V
    //   105: invokestatic 339	com/tapjoy/TapjoyConnectCore:j	()V
    //   108: getstatic 136	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   111: invokevirtual 343	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   114: ldc_w 345
    //   117: aconst_null
    //   118: getstatic 136	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   121: invokevirtual 349	android/content/Context:getPackageName	()Ljava/lang/String;
    //   124: invokevirtual 355	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   127: istore_2
    //   128: new 357	java/util/Properties
    //   131: dup
    //   132: invokespecial 358	java/util/Properties:<init>	()V
    //   135: astore_1
    //   136: aload_1
    //   137: getstatic 136	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   140: invokevirtual 343	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   143: iload_2
    //   144: invokevirtual 362	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   147: invokevirtual 366	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   150: aload_1
    //   151: invokestatic 369	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   154: ldc_w 371
    //   157: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   160: ldc -90
    //   162: if_acmpne +7 -> 169
    //   165: aload_0
    //   166: invokespecial 373	com/tapjoy/TapjoyConnectCore:k	()V
    //   169: getstatic 136	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   172: invokevirtual 377	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   175: ldc_w 379
    //   178: invokestatic 385	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   181: astore_1
    //   182: aload_1
    //   183: putstatic 168	com/tapjoy/TapjoyConnectCore:m	Ljava/lang/String;
    //   186: aload_1
    //   187: ifnull +12 -> 199
    //   190: getstatic 168	com/tapjoy/TapjoyConnectCore:m	Ljava/lang/String;
    //   193: invokevirtual 388	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   196: putstatic 168	com/tapjoy/TapjoyConnectCore:m	Ljava/lang/String;
    //   199: getstatic 308	com/tapjoy/TapjoyConnectCore:aj	Landroid/content/pm/PackageManager;
    //   202: getstatic 136	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   205: invokevirtual 349	android/content/Context:getPackageName	()Ljava/lang/String;
    //   208: iconst_0
    //   209: invokevirtual 394	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   212: getfield 399	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   215: putstatic 188	com/tapjoy/TapjoyConnectCore:x	Ljava/lang/String;
    //   218: ldc_w 401
    //   221: putstatic 182	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   224: ldc_w 401
    //   227: putstatic 204	com/tapjoy/TapjoyConnectCore:F	Ljava/lang/String;
    //   230: getstatic 406	android/os/Build:MODEL	Ljava/lang/String;
    //   233: putstatic 178	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   236: getstatic 409	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   239: putstatic 180	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   242: getstatic 414	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   245: putstatic 184	com/tapjoy/TapjoyConnectCore:v	Ljava/lang/String;
    //   248: ldc_w 416
    //   251: putstatic 190	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   254: ldc_w 418
    //   257: putstatic 192	com/tapjoy/TapjoyConnectCore:z	Ljava/lang/String;
    //   260: getstatic 421	android/os/Build$VERSION:SDK_INT	I
    //   263: iconst_3
    //   264: if_icmple +35 -> 299
    //   267: new 423	com/tapjoy/TapjoyDisplayMetricsUtil
    //   270: dup
    //   271: getstatic 136	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   274: invokespecial 424	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   277: astore_1
    //   278: aload_1
    //   279: invokevirtual 428	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   282: putstatic 194	com/tapjoy/TapjoyConnectCore:A	I
    //   285: aload_1
    //   286: invokevirtual 432	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   289: putstatic 196	com/tapjoy/TapjoyConnectCore:B	F
    //   292: aload_1
    //   293: invokevirtual 435	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   296: putstatic 198	com/tapjoy/TapjoyConnectCore:C	I
    //   299: ldc_w 437
    //   302: invokestatic 440	com/tapjoy/TapjoyConnectCore:h	(Ljava/lang/String;)Z
    //   305: istore_3
    //   306: iload_3
    //   307: ifeq +935 -> 1242
    //   310: getstatic 136	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   313: ldc_w 442
    //   316: invokevirtual 446	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   319: checkcast 448	android/net/wifi/WifiManager
    //   322: astore_1
    //   323: aload_1
    //   324: ifnull +42 -> 366
    //   327: aload_1
    //   328: invokevirtual 452	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   331: astore_1
    //   332: aload_1
    //   333: ifnull +33 -> 366
    //   336: aload_1
    //   337: invokevirtual 457	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   340: astore_1
    //   341: aload_1
    //   342: putstatic 174	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   345: aload_1
    //   346: ifnull +20 -> 366
    //   349: getstatic 174	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   352: ldc_w 459
    //   355: ldc -90
    //   357: invokevirtual 463	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   360: invokevirtual 388	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   363: putstatic 174	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   366: getstatic 136	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   369: ldc_w 465
    //   372: invokevirtual 446	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   375: checkcast 467	android/telephony/TelephonyManager
    //   378: astore_1
    //   379: aload_1
    //   380: ifnull +243 -> 623
    //   383: aload_1
    //   384: invokevirtual 470	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   387: putstatic 206	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   390: aload_1
    //   391: invokevirtual 473	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   394: putstatic 208	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   397: aload_1
    //   398: invokevirtual 476	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   401: ifnull +49 -> 450
    //   404: aload_1
    //   405: invokevirtual 476	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   408: invokevirtual 479	java/lang/String:length	()I
    //   411: iconst_5
    //   412: if_icmpeq +15 -> 427
    //   415: aload_1
    //   416: invokevirtual 476	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   419: invokevirtual 479	java/lang/String:length	()I
    //   422: bipush 6
    //   424: if_icmpne +26 -> 450
    //   427: aload_1
    //   428: invokevirtual 476	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   431: iconst_0
    //   432: iconst_3
    //   433: invokevirtual 483	java/lang/String:substring	(II)Ljava/lang/String;
    //   436: putstatic 210	com/tapjoy/TapjoyConnectCore:I	Ljava/lang/String;
    //   439: aload_1
    //   440: invokevirtual 476	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   443: iconst_3
    //   444: invokevirtual 486	java/lang/String:substring	(I)Ljava/lang/String;
    //   447: putstatic 212	com/tapjoy/TapjoyConnectCore:J	Ljava/lang/String;
    //   450: ldc_w 488
    //   453: invokestatic 440	com/tapjoy/TapjoyConnectCore:h	(Ljava/lang/String;)Z
    //   456: istore_3
    //   457: iload_3
    //   458: ifeq +1006 -> 1464
    //   461: ldc_w 490
    //   464: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   467: ifnull +787 -> 1254
    //   470: ldc_w 490
    //   473: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   476: invokevirtual 479	java/lang/String:length	()I
    //   479: ifle +775 -> 1254
    //   482: ldc_w 490
    //   485: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   488: putstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   491: ldc_w 492
    //   494: new 494	java/lang/StringBuilder
    //   497: dup
    //   498: ldc_w 496
    //   501: invokespecial 499	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   504: getstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   507: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   510: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   513: invokestatic 509	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   516: getstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   519: ifnonnull +789 -> 1308
    //   522: ldc_w 492
    //   525: new 511	com/tapjoy/TapjoyErrorMessage
    //   528: dup
    //   529: getstatic 517	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   532: ldc_w 519
    //   535: invokespecial 522	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   538: invokestatic 525	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   541: iconst_0
    //   542: istore_2
    //   543: ldc_w 492
    //   546: new 494	java/lang/StringBuilder
    //   549: dup
    //   550: ldc_w 527
    //   553: invokespecial 499	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   556: getstatic 421	android/os/Build$VERSION:SDK_INT	I
    //   559: invokevirtual 530	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   562: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   565: invokestatic 532	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   568: getstatic 421	android/os/Build$VERSION:SDK_INT	I
    //   571: bipush 9
    //   573: if_icmplt +50 -> 623
    //   576: ldc_w 492
    //   579: ldc_w 534
    //   582: invokestatic 509	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   585: aload_0
    //   586: invokevirtual 537	com/tapjoy/TapjoyConnectCore:getSerial	()Ljava/lang/String;
    //   589: astore_1
    //   590: iload_2
    //   591: ifne +7 -> 598
    //   594: aload_1
    //   595: putstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   598: getstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   601: ifnonnull +781 -> 1382
    //   604: ldc_w 492
    //   607: new 511	com/tapjoy/TapjoyErrorMessage
    //   610: dup
    //   611: getstatic 517	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   614: ldc_w 539
    //   617: invokespecial 522	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   620: invokestatic 525	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   623: getstatic 136	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   626: ldc_w 541
    //   629: iconst_0
    //   630: invokevirtual 545	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   633: astore_1
    //   634: aload_1
    //   635: ldc_w 547
    //   638: ldc -90
    //   640: invokeinterface 552 3 0
    //   645: astore 4
    //   647: aload 4
    //   649: putstatic 176	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   652: aload 4
    //   654: ifnull +14 -> 668
    //   657: getstatic 176	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   660: invokevirtual 479	java/lang/String:length	()I
    //   663: istore_2
    //   664: iload_2
    //   665: ifne +61 -> 726
    //   668: new 494	java/lang/StringBuilder
    //   671: dup
    //   672: invokespecial 553	java/lang/StringBuilder:<init>	()V
    //   675: invokestatic 559	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   678: invokevirtual 560	java/util/UUID:toString	()Ljava/lang/String;
    //   681: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   684: invokestatic 566	java/lang/System:currentTimeMillis	()J
    //   687: invokevirtual 569	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   690: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   693: invokestatic 574	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   696: putstatic 176	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   699: aload_1
    //   700: invokeinterface 578 1 0
    //   705: astore_1
    //   706: aload_1
    //   707: ldc_w 547
    //   710: getstatic 176	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   713: invokeinterface 584 3 0
    //   718: pop
    //   719: aload_1
    //   720: invokeinterface 588 1 0
    //   725: pop
    //   726: ldc_w 590
    //   729: ldc_w 592
    //   732: invokestatic 595	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   735: putstatic 202	com/tapjoy/TapjoyConnectCore:E	Z
    //   738: ldc_w 597
    //   741: invokestatic 599	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   744: putstatic 234	com/tapjoy/TapjoyConnectCore:T	Z
    //   747: ldc_w 601
    //   750: invokestatic 599	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   753: putstatic 236	com/tapjoy/TapjoyConnectCore:U	Z
    //   756: ldc_w 603
    //   759: invokestatic 599	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   762: putstatic 238	com/tapjoy/TapjoyConnectCore:V	Z
    //   765: ldc_w 605
    //   768: invokestatic 599	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   771: putstatic 240	com/tapjoy/TapjoyConnectCore:W	Z
    //   774: ldc_w 607
    //   777: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   780: ifnull +71 -> 851
    //   783: ldc_w 607
    //   786: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   789: invokevirtual 479	java/lang/String:length	()I
    //   792: ifle +59 -> 851
    //   795: ldc_w 607
    //   798: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   801: putstatic 218	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   804: new 609	java/util/ArrayList
    //   807: dup
    //   808: getstatic 612	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   811: invokestatic 158	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   814: invokespecial 613	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   817: getstatic 218	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   820: invokevirtual 616	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   823: ifne +28 -> 851
    //   826: ldc_w 492
    //   829: new 494	java/lang/StringBuilder
    //   832: dup
    //   833: ldc_w 618
    //   836: invokespecial 499	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   839: getstatic 218	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   842: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   845: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   848: invokestatic 620	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   851: getstatic 218	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   854: astore_1
    //   855: new 622	android/content/Intent
    //   858: dup
    //   859: ldc_w 624
    //   862: invokespecial 625	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   865: astore 4
    //   867: aload_1
    //   868: invokevirtual 479	java/lang/String:length	()I
    //   871: ifgt +695 -> 1566
    //   874: aload 4
    //   876: ldc_w 627
    //   879: invokestatic 633	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   882: invokevirtual 637	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   885: pop
    //   886: getstatic 308	com/tapjoy/TapjoyConnectCore:aj	Landroid/content/pm/PackageManager;
    //   889: aload 4
    //   891: iconst_0
    //   892: invokevirtual 641	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   895: invokeinterface 646 1 0
    //   900: ifle +746 -> 1646
    //   903: iconst_1
    //   904: istore_3
    //   905: iload_3
    //   906: putstatic 242	com/tapjoy/TapjoyConnectCore:X	Z
    //   909: invokestatic 648	com/tapjoy/TapjoyConnectCore:h	()V
    //   912: ldc_w 650
    //   915: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   918: ifnull +24 -> 942
    //   921: ldc_w 650
    //   924: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   927: invokevirtual 479	java/lang/String:length	()I
    //   930: ifle +12 -> 942
    //   933: ldc_w 650
    //   936: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   939: putstatic 264	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   942: ldc_w 652
    //   945: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   948: ifnull +24 -> 972
    //   951: ldc_w 652
    //   954: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   957: invokevirtual 479	java/lang/String:length	()I
    //   960: ifle +12 -> 972
    //   963: ldc_w 652
    //   966: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   969: putstatic 262	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   972: ldc_w 654
    //   975: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   978: ifnull +52 -> 1030
    //   981: ldc_w 654
    //   984: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   987: invokevirtual 479	java/lang/String:length	()I
    //   990: ifle +40 -> 1030
    //   993: ldc_w 492
    //   996: new 494	java/lang/StringBuilder
    //   999: dup
    //   1000: ldc_w 656
    //   1003: invokespecial 499	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1006: ldc_w 654
    //   1009: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1012: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1015: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1018: invokestatic 532	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1021: ldc_w 654
    //   1024: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1027: invokestatic 659	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;)V
    //   1030: ldc_w 661
    //   1033: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1036: invokestatic 664	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   1039: putstatic 230	com/tapjoy/TapjoyConnectCore:R	Ljava/lang/String;
    //   1042: new 494	java/lang/StringBuilder
    //   1045: dup
    //   1046: ldc_w 496
    //   1049: invokespecial 499	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1052: getstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1055: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1058: astore 4
    //   1060: ldc_w 490
    //   1063: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1066: ifnull +570 -> 1636
    //   1069: ldc_w 490
    //   1072: invokestatic 323	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1075: invokevirtual 479	java/lang/String:length	()I
    //   1078: ifle +558 -> 1636
    //   1081: ldc_w 666
    //   1084: astore_1
    //   1085: ldc_w 492
    //   1088: aload 4
    //   1090: aload_1
    //   1091: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1094: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1097: invokestatic 509	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1100: getstatic 271	com/tapjoy/TapjoyConnectCore:am	Ljava/util/Hashtable;
    //   1103: ifnull +6 -> 1109
    //   1106: invokestatic 668	com/tapjoy/TapjoyConnectCore:i	()V
    //   1109: aload_0
    //   1110: invokevirtual 671	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   1113: aload_0
    //   1114: iconst_1
    //   1115: putfield 297	com/tapjoy/TapjoyConnectCore:ah	Z
    //   1118: return
    //   1119: astore_1
    //   1120: new 283	com/tapjoy/TapjoyException
    //   1123: dup
    //   1124: aload_1
    //   1125: invokevirtual 674	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   1128: invokespecial 675	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   1131: athrow
    //   1132: astore_1
    //   1133: ldc_w 492
    //   1136: new 511	com/tapjoy/TapjoyErrorMessage
    //   1139: dup
    //   1140: getstatic 678	com/tapjoy/TapjoyErrorMessage$ErrorType:INTEGRATION_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1143: aload_1
    //   1144: invokevirtual 679	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   1147: invokespecial 522	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1150: invokestatic 525	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1153: invokestatic 681	com/tapjoy/TapjoyConnectCore:e	()V
    //   1156: return
    //   1157: astore_1
    //   1158: ldc_w 492
    //   1161: new 494	java/lang/StringBuilder
    //   1164: dup
    //   1165: ldc_w 683
    //   1168: invokespecial 499	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1171: aload_1
    //   1172: invokevirtual 684	java/lang/Exception:toString	()Ljava/lang/String;
    //   1175: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1178: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1181: invokestatic 686	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1184: goto -885 -> 299
    //   1187: astore_1
    //   1188: ldc_w 492
    //   1191: new 511	com/tapjoy/TapjoyErrorMessage
    //   1194: dup
    //   1195: getstatic 517	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1198: aload_1
    //   1199: invokevirtual 687	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   1202: invokespecial 522	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1205: invokestatic 525	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1208: invokestatic 681	com/tapjoy/TapjoyConnectCore:e	()V
    //   1211: return
    //   1212: astore_1
    //   1213: ldc_w 492
    //   1216: new 494	java/lang/StringBuilder
    //   1219: dup
    //   1220: ldc_w 689
    //   1223: invokespecial 499	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1226: aload_1
    //   1227: invokevirtual 684	java/lang/Exception:toString	()Ljava/lang/String;
    //   1230: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1233: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1236: invokestatic 686	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1239: goto -873 -> 366
    //   1242: ldc_w 492
    //   1245: ldc_w 691
    //   1248: invokestatic 509	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1251: goto -885 -> 366
    //   1254: aload_1
    //   1255: invokevirtual 694	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1258: putstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1261: goto -770 -> 491
    //   1264: astore_1
    //   1265: ldc_w 492
    //   1268: new 511	com/tapjoy/TapjoyErrorMessage
    //   1271: dup
    //   1272: getstatic 517	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1275: new 494	java/lang/StringBuilder
    //   1278: dup
    //   1279: ldc_w 696
    //   1282: invokespecial 499	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1285: aload_1
    //   1286: invokevirtual 684	java/lang/Exception:toString	()Ljava/lang/String;
    //   1289: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1292: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1295: invokespecial 522	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1298: invokestatic 525	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1301: aconst_null
    //   1302: putstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1305: goto -682 -> 623
    //   1308: getstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1311: invokevirtual 479	java/lang/String:length	()I
    //   1314: ifeq +27 -> 1341
    //   1317: getstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1320: ldc_w 698
    //   1323: invokevirtual 331	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1326: ifne +15 -> 1341
    //   1329: getstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1332: ldc_w 700
    //   1335: invokevirtual 331	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1338: ifeq +27 -> 1365
    //   1341: ldc_w 492
    //   1344: new 511	com/tapjoy/TapjoyErrorMessage
    //   1347: dup
    //   1348: getstatic 517	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1351: ldc_w 702
    //   1354: invokespecial 522	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1357: invokestatic 525	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1360: iconst_0
    //   1361: istore_2
    //   1362: goto -819 -> 543
    //   1365: getstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1368: invokestatic 708	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1371: invokevirtual 711	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1374: putstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1377: iconst_1
    //   1378: istore_2
    //   1379: goto -836 -> 543
    //   1382: getstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1385: invokevirtual 479	java/lang/String:length	()I
    //   1388: ifeq +39 -> 1427
    //   1391: getstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1394: ldc_w 698
    //   1397: invokevirtual 331	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1400: ifne +27 -> 1427
    //   1403: getstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1406: ldc_w 700
    //   1409: invokevirtual 331	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1412: ifne +15 -> 1427
    //   1415: getstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1418: ldc_w 713
    //   1421: invokevirtual 331	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1424: ifeq +25 -> 1449
    //   1427: ldc_w 492
    //   1430: new 511	com/tapjoy/TapjoyErrorMessage
    //   1433: dup
    //   1434: getstatic 517	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1437: ldc_w 715
    //   1440: invokespecial 522	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1443: invokestatic 525	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1446: goto -823 -> 623
    //   1449: getstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1452: invokestatic 708	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1455: invokevirtual 711	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1458: putstatic 172	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1461: goto -838 -> 623
    //   1464: ldc_w 492
    //   1467: ldc_w 717
    //   1470: invokestatic 509	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1473: goto -850 -> 623
    //   1476: astore_1
    //   1477: ldc_w 492
    //   1480: new 494	java/lang/StringBuilder
    //   1483: dup
    //   1484: ldc_w 719
    //   1487: invokespecial 499	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1490: aload_1
    //   1491: invokevirtual 684	java/lang/Exception:toString	()Ljava/lang/String;
    //   1494: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1497: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1500: invokestatic 686	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1503: goto -777 -> 726
    //   1506: astore_1
    //   1507: ldc_w 492
    //   1510: new 494	java/lang/StringBuilder
    //   1513: dup
    //   1514: ldc_w 721
    //   1517: invokespecial 499	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1520: aload_1
    //   1521: invokevirtual 684	java/lang/Exception:toString	()Ljava/lang/String;
    //   1524: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1527: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1530: invokestatic 686	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1533: goto -795 -> 738
    //   1536: astore_1
    //   1537: ldc_w 492
    //   1540: new 494	java/lang/StringBuilder
    //   1543: dup
    //   1544: ldc_w 723
    //   1547: invokespecial 499	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1550: aload_1
    //   1551: invokevirtual 684	java/lang/Exception:toString	()Ljava/lang/String;
    //   1554: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1557: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1560: invokestatic 686	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1563: goto -789 -> 774
    //   1566: aload_1
    //   1567: ldc_w 725
    //   1570: invokevirtual 331	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1573: ifeq +13 -> 1586
    //   1576: ldc_w 727
    //   1579: invokestatic 729	com/tapjoy/TapjoyConnectCore:f	(Ljava/lang/String;)Z
    //   1582: istore_3
    //   1583: goto -678 -> 905
    //   1586: aload_1
    //   1587: ldc_w 731
    //   1590: invokevirtual 331	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1593: ifeq +53 -> 1646
    //   1596: ldc_w 733
    //   1599: invokestatic 729	com/tapjoy/TapjoyConnectCore:f	(Ljava/lang/String;)Z
    //   1602: istore_3
    //   1603: goto -698 -> 905
    //   1606: astore_1
    //   1607: ldc_w 492
    //   1610: new 494	java/lang/StringBuilder
    //   1613: dup
    //   1614: ldc_w 735
    //   1617: invokespecial 499	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1620: aload_1
    //   1621: invokevirtual 684	java/lang/Exception:toString	()Ljava/lang/String;
    //   1624: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1627: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1630: invokestatic 686	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1633: goto -724 -> 909
    //   1636: ldc -90
    //   1638: astore_1
    //   1639: goto -554 -> 1085
    //   1642: astore_1
    //   1643: goto -1489 -> 154
    //   1646: iconst_0
    //   1647: istore_3
    //   1648: goto -743 -> 905
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1651	0	this	TapjoyConnectCore
    //   0	1651	1	paramContext	Context
    //   127	1252	2	i1	int
    //   305	1343	3	bool	boolean
    //   645	444	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   199	218	1119	android/content/pm/PackageManager$NameNotFoundException
    //   61	77	1132	com/tapjoy/TapjoyIntegrationException
    //   77	105	1132	com/tapjoy/TapjoyIntegrationException
    //   105	136	1132	com/tapjoy/TapjoyIntegrationException
    //   136	154	1132	com/tapjoy/TapjoyIntegrationException
    //   154	169	1132	com/tapjoy/TapjoyIntegrationException
    //   169	186	1132	com/tapjoy/TapjoyIntegrationException
    //   190	199	1132	com/tapjoy/TapjoyIntegrationException
    //   199	218	1132	com/tapjoy/TapjoyIntegrationException
    //   218	260	1132	com/tapjoy/TapjoyIntegrationException
    //   260	299	1132	com/tapjoy/TapjoyIntegrationException
    //   299	306	1132	com/tapjoy/TapjoyIntegrationException
    //   310	323	1132	com/tapjoy/TapjoyIntegrationException
    //   327	332	1132	com/tapjoy/TapjoyIntegrationException
    //   336	345	1132	com/tapjoy/TapjoyIntegrationException
    //   349	366	1132	com/tapjoy/TapjoyIntegrationException
    //   366	379	1132	com/tapjoy/TapjoyIntegrationException
    //   383	427	1132	com/tapjoy/TapjoyIntegrationException
    //   427	450	1132	com/tapjoy/TapjoyIntegrationException
    //   450	457	1132	com/tapjoy/TapjoyIntegrationException
    //   461	491	1132	com/tapjoy/TapjoyIntegrationException
    //   491	541	1132	com/tapjoy/TapjoyIntegrationException
    //   543	590	1132	com/tapjoy/TapjoyIntegrationException
    //   594	598	1132	com/tapjoy/TapjoyIntegrationException
    //   598	623	1132	com/tapjoy/TapjoyIntegrationException
    //   623	652	1132	com/tapjoy/TapjoyIntegrationException
    //   657	664	1132	com/tapjoy/TapjoyIntegrationException
    //   668	726	1132	com/tapjoy/TapjoyIntegrationException
    //   726	738	1132	com/tapjoy/TapjoyIntegrationException
    //   738	774	1132	com/tapjoy/TapjoyIntegrationException
    //   774	851	1132	com/tapjoy/TapjoyIntegrationException
    //   851	903	1132	com/tapjoy/TapjoyIntegrationException
    //   905	909	1132	com/tapjoy/TapjoyIntegrationException
    //   909	942	1132	com/tapjoy/TapjoyIntegrationException
    //   942	972	1132	com/tapjoy/TapjoyIntegrationException
    //   972	1030	1132	com/tapjoy/TapjoyIntegrationException
    //   1030	1081	1132	com/tapjoy/TapjoyIntegrationException
    //   1085	1109	1132	com/tapjoy/TapjoyIntegrationException
    //   1109	1118	1132	com/tapjoy/TapjoyIntegrationException
    //   1120	1132	1132	com/tapjoy/TapjoyIntegrationException
    //   1158	1184	1132	com/tapjoy/TapjoyIntegrationException
    //   1213	1239	1132	com/tapjoy/TapjoyIntegrationException
    //   1242	1251	1132	com/tapjoy/TapjoyIntegrationException
    //   1254	1261	1132	com/tapjoy/TapjoyIntegrationException
    //   1265	1305	1132	com/tapjoy/TapjoyIntegrationException
    //   1308	1341	1132	com/tapjoy/TapjoyIntegrationException
    //   1341	1360	1132	com/tapjoy/TapjoyIntegrationException
    //   1365	1377	1132	com/tapjoy/TapjoyIntegrationException
    //   1382	1427	1132	com/tapjoy/TapjoyIntegrationException
    //   1427	1446	1132	com/tapjoy/TapjoyIntegrationException
    //   1449	1461	1132	com/tapjoy/TapjoyIntegrationException
    //   1464	1473	1132	com/tapjoy/TapjoyIntegrationException
    //   1477	1503	1132	com/tapjoy/TapjoyIntegrationException
    //   1507	1533	1132	com/tapjoy/TapjoyIntegrationException
    //   1537	1563	1132	com/tapjoy/TapjoyIntegrationException
    //   1566	1583	1132	com/tapjoy/TapjoyIntegrationException
    //   1586	1603	1132	com/tapjoy/TapjoyIntegrationException
    //   1607	1633	1132	com/tapjoy/TapjoyIntegrationException
    //   260	299	1157	java/lang/Exception
    //   61	77	1187	com/tapjoy/TapjoyException
    //   77	105	1187	com/tapjoy/TapjoyException
    //   105	136	1187	com/tapjoy/TapjoyException
    //   136	154	1187	com/tapjoy/TapjoyException
    //   154	169	1187	com/tapjoy/TapjoyException
    //   169	186	1187	com/tapjoy/TapjoyException
    //   190	199	1187	com/tapjoy/TapjoyException
    //   199	218	1187	com/tapjoy/TapjoyException
    //   218	260	1187	com/tapjoy/TapjoyException
    //   260	299	1187	com/tapjoy/TapjoyException
    //   299	306	1187	com/tapjoy/TapjoyException
    //   310	323	1187	com/tapjoy/TapjoyException
    //   327	332	1187	com/tapjoy/TapjoyException
    //   336	345	1187	com/tapjoy/TapjoyException
    //   349	366	1187	com/tapjoy/TapjoyException
    //   366	379	1187	com/tapjoy/TapjoyException
    //   383	427	1187	com/tapjoy/TapjoyException
    //   427	450	1187	com/tapjoy/TapjoyException
    //   450	457	1187	com/tapjoy/TapjoyException
    //   461	491	1187	com/tapjoy/TapjoyException
    //   491	541	1187	com/tapjoy/TapjoyException
    //   543	590	1187	com/tapjoy/TapjoyException
    //   594	598	1187	com/tapjoy/TapjoyException
    //   598	623	1187	com/tapjoy/TapjoyException
    //   623	652	1187	com/tapjoy/TapjoyException
    //   657	664	1187	com/tapjoy/TapjoyException
    //   668	726	1187	com/tapjoy/TapjoyException
    //   726	738	1187	com/tapjoy/TapjoyException
    //   738	774	1187	com/tapjoy/TapjoyException
    //   774	851	1187	com/tapjoy/TapjoyException
    //   851	903	1187	com/tapjoy/TapjoyException
    //   905	909	1187	com/tapjoy/TapjoyException
    //   909	942	1187	com/tapjoy/TapjoyException
    //   942	972	1187	com/tapjoy/TapjoyException
    //   972	1030	1187	com/tapjoy/TapjoyException
    //   1030	1081	1187	com/tapjoy/TapjoyException
    //   1085	1109	1187	com/tapjoy/TapjoyException
    //   1109	1118	1187	com/tapjoy/TapjoyException
    //   1120	1132	1187	com/tapjoy/TapjoyException
    //   1158	1184	1187	com/tapjoy/TapjoyException
    //   1213	1239	1187	com/tapjoy/TapjoyException
    //   1242	1251	1187	com/tapjoy/TapjoyException
    //   1254	1261	1187	com/tapjoy/TapjoyException
    //   1265	1305	1187	com/tapjoy/TapjoyException
    //   1308	1341	1187	com/tapjoy/TapjoyException
    //   1341	1360	1187	com/tapjoy/TapjoyException
    //   1365	1377	1187	com/tapjoy/TapjoyException
    //   1382	1427	1187	com/tapjoy/TapjoyException
    //   1427	1446	1187	com/tapjoy/TapjoyException
    //   1449	1461	1187	com/tapjoy/TapjoyException
    //   1464	1473	1187	com/tapjoy/TapjoyException
    //   1477	1503	1187	com/tapjoy/TapjoyException
    //   1507	1533	1187	com/tapjoy/TapjoyException
    //   1537	1563	1187	com/tapjoy/TapjoyException
    //   1566	1583	1187	com/tapjoy/TapjoyException
    //   1586	1603	1187	com/tapjoy/TapjoyException
    //   1607	1633	1187	com/tapjoy/TapjoyException
    //   310	323	1212	java/lang/Exception
    //   327	332	1212	java/lang/Exception
    //   336	345	1212	java/lang/Exception
    //   349	366	1212	java/lang/Exception
    //   461	491	1264	java/lang/Exception
    //   491	541	1264	java/lang/Exception
    //   543	590	1264	java/lang/Exception
    //   594	598	1264	java/lang/Exception
    //   598	623	1264	java/lang/Exception
    //   1254	1261	1264	java/lang/Exception
    //   1308	1341	1264	java/lang/Exception
    //   1341	1360	1264	java/lang/Exception
    //   1365	1377	1264	java/lang/Exception
    //   1382	1427	1264	java/lang/Exception
    //   1427	1446	1264	java/lang/Exception
    //   1449	1461	1264	java/lang/Exception
    //   668	726	1476	java/lang/Exception
    //   726	738	1506	java/lang/Exception
    //   738	774	1536	java/lang/Exception
    //   851	903	1606	java/lang/Exception
    //   905	909	1606	java/lang/Exception
    //   1566	1583	1606	java/lang/Exception
    //   1586	1603	1606	java/lang/Exception
    //   136	154	1642	java/lang/Exception
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
    //   1: invokestatic 880	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   4: astore_0
    //   5: aload_0
    //   6: astore_3
    //   7: aload_0
    //   8: invokevirtual 883	com/tapjoy/internal/bs:d	()Ljava/util/Map;
    //   11: astore 5
    //   13: aload_0
    //   14: astore_3
    //   15: aload 5
    //   17: ldc_w 885
    //   20: invokeinterface 888 2 0
    //   25: checkcast 327	java/lang/String
    //   28: invokestatic 892	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   31: astore 4
    //   33: aload_0
    //   34: astore_3
    //   35: aload 5
    //   37: ldc_w 894
    //   40: invokeinterface 888 2 0
    //   45: checkcast 327	java/lang/String
    //   48: invokestatic 892	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   51: astore 7
    //   53: aload_0
    //   54: astore_3
    //   55: aload 5
    //   57: ldc_w 896
    //   60: invokeinterface 888 2 0
    //   65: checkcast 327	java/lang/String
    //   68: invokestatic 892	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   71: astore 8
    //   73: aload_0
    //   74: astore_3
    //   75: aload 5
    //   77: ldc_w 898
    //   80: invokeinterface 888 2 0
    //   85: checkcast 327	java/lang/String
    //   88: invokestatic 892	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   91: astore 9
    //   93: aload_0
    //   94: astore_3
    //   95: aload 5
    //   97: ldc_w 900
    //   100: invokeinterface 888 2 0
    //   105: checkcast 327	java/lang/String
    //   108: invokestatic 892	com/tapjoy/internal/cs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   111: astore 6
    //   113: aload_0
    //   114: astore_3
    //   115: aload 5
    //   117: ldc_w 902
    //   120: invokeinterface 888 2 0
    //   125: checkcast 904	java/lang/Boolean
    //   128: astore 5
    //   130: aload 5
    //   132: ifnull +13 -> 145
    //   135: aload_0
    //   136: astore_3
    //   137: aload 5
    //   139: invokevirtual 907	java/lang/Boolean:booleanValue	()Z
    //   142: putstatic 277	com/tapjoy/TapjoyConnectCore:aX	Z
    //   145: aload_0
    //   146: astore_3
    //   147: new 909	com/tapjoy/internal/ApiKeyDecoded
    //   150: dup
    //   151: aload 8
    //   153: invokespecial 910	com/tapjoy/internal/ApiKeyDecoded:<init>	(Ljava/lang/String;)V
    //   156: astore 10
    //   158: aload_0
    //   159: astore_3
    //   160: aload 10
    //   162: invokevirtual 914	com/tapjoy/internal/ApiKeyDecoded:getKeyUsage	()Lcom/tapjoy/internal/ApiKeyDecoded$KeyUsage;
    //   165: getstatic 920	com/tapjoy/internal/ApiKeyDecoded$KeyUsage:RPC_ANALYTICS	Lcom/tapjoy/internal/ApiKeyDecoded$KeyUsage;
    //   168: if_acmpeq +33 -> 201
    //   171: aload_0
    //   172: astore_3
    //   173: new 873	java/io/IOException
    //   176: dup
    //   177: ldc_w 922
    //   180: invokespecial 923	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   183: athrow
    //   184: astore_3
    //   185: ldc_w 492
    //   188: aload_3
    //   189: invokevirtual 924	java/io/IOException:getMessage	()Ljava/lang/String;
    //   192: invokestatic 926	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   195: aload_0
    //   196: invokestatic 931	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   199: iconst_0
    //   200: ireturn
    //   201: aload_0
    //   202: astore_3
    //   203: aload 10
    //   205: invokevirtual 934	com/tapjoy/internal/ApiKeyDecoded:getAppId	()Ljava/lang/String;
    //   208: invokestatic 937	com/tapjoy/internal/ApiKeyDecoded:get5RocksAppId	(Ljava/lang/String;)Ljava/lang/String;
    //   211: astore 5
    //   213: aload_0
    //   214: astore_3
    //   215: aload 10
    //   217: invokevirtual 940	com/tapjoy/internal/ApiKeyDecoded:getSecretKey	()Ljava/lang/String;
    //   220: astore 10
    //   222: aload 4
    //   224: ifnonnull +224 -> 448
    //   227: aload 5
    //   229: astore 4
    //   231: aload_0
    //   232: astore_3
    //   233: invokestatic 945	com/tapjoy/internal/iz:a	()Lcom/tapjoy/internal/iz;
    //   236: getstatic 136	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   239: aload 8
    //   241: ldc_w 416
    //   244: ldc_w 947
    //   247: aload 5
    //   249: aload 10
    //   251: invokevirtual 950	com/tapjoy/internal/iz:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   254: aload_0
    //   255: astore_3
    //   256: aload 4
    //   258: putstatic 244	com/tapjoy/TapjoyConnectCore:Y	Ljava/lang/String;
    //   261: aload_0
    //   262: astore_3
    //   263: aload 7
    //   265: putstatic 246	com/tapjoy/TapjoyConnectCore:Z	Ljava/lang/String;
    //   268: aload_0
    //   269: astore_3
    //   270: aload 8
    //   272: putstatic 248	com/tapjoy/TapjoyConnectCore:aa	Ljava/lang/String;
    //   275: aload_0
    //   276: astore_3
    //   277: aload 9
    //   279: putstatic 250	com/tapjoy/TapjoyConnectCore:ab	Ljava/lang/String;
    //   282: aload_0
    //   283: astore_3
    //   284: new 609	java/util/ArrayList
    //   287: dup
    //   288: invokespecial 951	java/util/ArrayList:<init>	()V
    //   291: astore 4
    //   293: aload 6
    //   295: ifnull +64 -> 359
    //   298: aload_0
    //   299: astore_3
    //   300: aload 6
    //   302: ldc_w 779
    //   305: invokevirtual 955	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
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
    //   329: invokevirtual 858	java/lang/String:trim	()Ljava/lang/String;
    //   332: astore 6
    //   334: aload_0
    //   335: astore_3
    //   336: aload 6
    //   338: invokevirtual 479	java/lang/String:length	()I
    //   341: ifle +110 -> 451
    //   344: aload_0
    //   345: astore_3
    //   346: aload 4
    //   348: aload 6
    //   350: invokeinterface 956 2 0
    //   355: pop
    //   356: goto +95 -> 451
    //   359: aload_0
    //   360: astore_3
    //   361: aload 4
    //   363: invokeinterface 959 1 0
    //   368: ifne +10 -> 378
    //   371: aload_0
    //   372: astore_3
    //   373: aload 4
    //   375: invokestatic 863	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   378: aload_0
    //   379: astore_3
    //   380: aload_0
    //   381: invokevirtual 962	com/tapjoy/internal/bs:close	()V
    //   384: aconst_null
    //   385: invokestatic 931	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   388: iconst_1
    //   389: ireturn
    //   390: astore 4
    //   392: aconst_null
    //   393: astore_0
    //   394: aload_0
    //   395: astore_3
    //   396: ldc_w 492
    //   399: aload 4
    //   401: invokevirtual 963	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   404: invokestatic 926	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   407: aload_0
    //   408: invokestatic 931	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   411: goto -212 -> 199
    //   414: astore_0
    //   415: aconst_null
    //   416: astore_3
    //   417: aload_3
    //   418: invokestatic 931	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
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
      iz.a().a(g, h, "11.5.0", "https://rpc.tapjoy.com/", O, N);
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
    //   1: invokestatic 880	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 972	com/tapjoy/internal/bs:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 974	com/tapjoy/internal/bs:s	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 492
    //   25: ldc_w 976
    //   28: invokestatic 509	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 962	com/tapjoy/internal/bs:close	()V
    //   37: aconst_null
    //   38: invokestatic 931	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 962	com/tapjoy/internal/bs:close	()V
    //   49: aconst_null
    //   50: invokestatic 931	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   53: ldc_w 492
    //   56: new 511	com/tapjoy/TapjoyErrorMessage
    //   59: dup
    //   60: getstatic 517	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   63: ldc_w 978
    //   66: invokespecial 522	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   69: invokestatic 525	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   72: iconst_0
    //   73: ireturn
    //   74: astore_2
    //   75: aconst_null
    //   76: astore_1
    //   77: aload_1
    //   78: astore_0
    //   79: ldc_w 492
    //   82: aload_2
    //   83: invokevirtual 924	java/io/IOException:getMessage	()Ljava/lang/String;
    //   86: invokestatic 926	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload_1
    //   90: invokestatic 931	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   93: goto -40 -> 53
    //   96: astore_2
    //   97: aconst_null
    //   98: astore_1
    //   99: aload_1
    //   100: astore_0
    //   101: ldc_w 492
    //   104: aload_2
    //   105: invokevirtual 963	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   108: invokestatic 926	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   111: aload_1
    //   112: invokestatic 931	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
    //   115: goto -62 -> 53
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_0
    //   121: aload_0
    //   122: invokestatic 931	com/tapjoy/internal/db:a	(Ljava/io/Closeable;)V
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
    TapjoyUtil.safePut(localHashMap3, "library_revision", "6b6d8c3", true);
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
    jq.n localN = iz.a(g).a(true);
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
    iz.a(paramContext).j = paramString;
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
              TapjoyLog.d("TapjoyConnect", "sending offline log: " + localEntry.getValue());
              localTapjoyURLConnection.getResponseFromURL((String)localEntry.getValue() + "&" + TapjoyUtil.convertURLParams(TapjoyConnectCore.getTimeStampAndVerifierParams(), false), "");
              TapjoyConnectCore.removeOfflineLog((String)localEntry.getKey());
            }
            catch (Exception localException)
            {
              for (;;)
              {
                TapjoyLog.d("TapjoyConnect", "error sending offline log");
              }
            }
          }
        }
      }
    }).start();
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
    new Thread(new Runnable()
    {
      public final void run()
      {
        TapjoyLog.i("TapjoyConnect", "setUserID...");
        TapjoyHttpURLResponse localTapjoyHttpURLResponse = TapjoyConnectCore.d().getResponseFromURL(TapjoyConnectCore.getHostURL() + "set_publisher_user_id?", TapjoyConnectCore.getURLParams());
        if (localTapjoyHttpURLResponse.response != null)
        {
          TapjoyConnectCore.b(localTapjoyHttpURLResponse.response);
          TapjoyLog.d("TapjoyConnect", "setUserID successful...");
        }
      }
    }).start();
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
        if (aX) {
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
        ai = true;
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
    TapjoyLog.d("TapjoyConnect", "Initializing Threatmetrix: 2.5-16");
    this.ak = new if();
    for (;;)
    {
      try
      {
        localIf = this.ak;
        local1 = new ht()
        {
          /* Error */
          public final void a()
          {
            // Byte code:
            //   0: aload_0
            //   1: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
            //   4: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/if;
            //   7: getfield 30	com/tapjoy/internal/if:a	Lcom/tapjoy/internal/ig;
            //   10: invokevirtual 35	com/tapjoy/internal/ig:a	()Lcom/tapjoy/internal/hp;
            //   13: invokestatic 40	com/tapjoy/internal/if$c:a	(Lcom/tapjoy/internal/hp;)Lcom/tapjoy/internal/if$c;
            //   16: getstatic 44	com/tapjoy/internal/if$c:b	Lcom/tapjoy/internal/if$c;
            //   19: if_acmpne +31 -> 50
            //   22: aload_0
            //   23: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
            //   26: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/if;
            //   29: getfield 30	com/tapjoy/internal/if:a	Lcom/tapjoy/internal/ig;
            //   32: getfield 48	com/tapjoy/internal/ig:c	Ljava/lang/String;
            //   35: invokestatic 51	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;)Ljava/lang/String;
            //   38: pop
            //   39: aload_0
            //   40: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
            //   43: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/if;
            //   46: invokevirtual 53	com/tapjoy/internal/if:a	()V
            //   49: return
            //   50: ldc 55
            //   52: ldc 57
            //   54: invokestatic 63	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
            //   57: goto -18 -> 39
            //   60: astore_1
            //   61: ldc 55
            //   63: new 65	java/lang/StringBuilder
            //   66: dup
            //   67: ldc 67
            //   69: invokespecial 70	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
            //   72: aload_1
            //   73: invokevirtual 74	java/lang/Exception:toString	()Ljava/lang/String;
            //   76: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   79: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   82: invokestatic 63	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
            //   85: aload_0
            //   86: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
            //   89: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/if;
            //   92: invokevirtual 53	com/tapjoy/internal/if:a	()V
            //   95: return
            //   96: astore_1
            //   97: aload_0
            //   98: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
            //   101: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/if;
            //   104: invokevirtual 53	com/tapjoy/internal/if:a	()V
            //   107: aload_1
            //   108: athrow
            // Local variable table:
            //   start	length	slot	name	signature
            //   0	109	0	this	1
            //   60	13	1	localException	Exception
            //   96	12	1	localObject	Object
            // Exception table:
            //   from	to	target	type
            //   0	39	60	java/lang/Exception
            //   50	57	60	java/lang/Exception
            //   0	39	96	finally
            //   50	57	96	finally
            //   61	85	96	finally
          }
        };
      }
      catch (InterruptedException localInterruptedException)
      {
        if localIf;
        ht local1;
        localInterruptedException.printStackTrace();
        continue;
      }
      try
      {
        localIf.b.lockInterruptibly();
        localIf.c = local1;
        if (localIf.b.isHeldByCurrentThread()) {
          localIf.b.unlock();
        }
        this.ak.d = 10000;
        if (g != null) {
          this.ak.a(g, "rrx68giz", "h.online-metrix.net", "http://content-js.tapjoy.com");
        }
        return;
      }
      finally
      {
        if (localIf.b.isHeldByCurrentThread()) {
          localIf.b.unlock();
        }
      }
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
    return S;
  }
  
  /* Error */
  public String getSerial()
  {
    // Byte code:
    //   0: ldc_w 1833
    //   3: invokestatic 1524	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 1835
    //   9: invokevirtual 1839	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 1844	java/lang/reflect/Field:isAccessible	()Z
    //   17: ifne +8 -> 25
    //   20: aload_1
    //   21: iconst_1
    //   22: invokevirtual 1847	java/lang/reflect/Field:setAccessible	(Z)V
    //   25: aload_1
    //   26: ldc_w 403
    //   29: invokevirtual 1848	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: invokevirtual 1284	java/lang/Object:toString	()Ljava/lang/String;
    //   35: astore_1
    //   36: ldc_w 492
    //   39: new 494	java/lang/StringBuilder
    //   42: dup
    //   43: ldc_w 1850
    //   46: invokespecial 499	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   49: aload_1
    //   50: invokevirtual 503	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 506	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 509	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload_1
    //   60: areturn
    //   61: astore_2
    //   62: aconst_null
    //   63: astore_1
    //   64: ldc_w 492
    //   67: aload_2
    //   68: invokevirtual 684	java/lang/Exception:toString	()Ljava/lang/String;
    //   71: invokestatic 686	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
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
