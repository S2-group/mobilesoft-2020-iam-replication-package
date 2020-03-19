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
import com.tapjoy.internal.cr;
import com.tapjoy.internal.el;
import com.tapjoy.internal.el.a;
import com.tapjoy.internal.eo;
import com.tapjoy.internal.eo.a;
import com.tapjoy.internal.fm;
import com.tapjoy.internal.gd.a;
import com.tapjoy.internal.gd.l;
import com.tapjoy.internal.gd.n;
import com.tapjoy.internal.gd.z;
import com.tapjoy.internal.y;
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
  private static Set aA;
  private static int aB;
  private static int aC;
  private static int aD;
  private static long aE;
  private static long aF;
  private static long aG;
  private static String aH;
  private static int aI;
  private static double aJ;
  private static double aK;
  private static long aL;
  private static int aM;
  private static int aN;
  private static int aO;
  private static String aP;
  private static String aQ;
  private static String aR;
  private static long ac;
  private static boolean ae;
  private static PackageManager af;
  private static Hashtable ah = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
  private static String ai = "";
  private static Map aj = new ConcurrentHashMap();
  private static String ak;
  private static String al;
  private static String am;
  private static String an;
  private static int ao;
  private static String ap;
  private static String aq;
  private static long ar;
  private static String as;
  private static int at;
  private static int au;
  private static String av;
  private static String aw;
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
  private Timer aa;
  private boolean ab;
  private boolean ad;
  private TapjoyGpsHelper ag;
  
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
    ac = 0L;
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
    //   1: invokespecial 280	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 282	com/tapjoy/TapjoyConnectCore:Z	J
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 284	com/tapjoy/TapjoyConnectCore:aa	Ljava/util/Timer;
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 286	com/tapjoy/TapjoyConnectCore:ab	Z
    //   19: aload_0
    //   20: iconst_0
    //   21: putfield 288	com/tapjoy/TapjoyConnectCore:ad	Z
    //   24: aload_1
    //   25: putstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   28: new 290	com/tapjoy/TapjoyURLConnection
    //   31: dup
    //   32: invokespecial 291	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   35: putstatic 138	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   38: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   41: invokevirtual 297	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   44: putstatic 299	com/tapjoy/TapjoyConnectCore:af	Landroid/content/pm/PackageManager;
    //   47: aload_0
    //   48: new 301	com/tapjoy/TapjoyGpsHelper
    //   51: dup
    //   52: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   55: invokespecial 303	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   58: putfield 305	com/tapjoy/TapjoyConnectCore:ag	Lcom/tapjoy/TapjoyGpsHelper;
    //   61: getstatic 261	com/tapjoy/TapjoyConnectCore:ah	Ljava/util/Hashtable;
    //   64: ifnonnull +13 -> 77
    //   67: new 307	java/util/Hashtable
    //   70: dup
    //   71: invokespecial 308	java/util/Hashtable:<init>	()V
    //   74: putstatic 261	com/tapjoy/TapjoyConnectCore:ah	Ljava/util/Hashtable;
    //   77: ldc_w 310
    //   80: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   83: ifnull +22 -> 105
    //   86: ldc_w 310
    //   89: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   92: ldc_w 316
    //   95: invokevirtual 322	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   98: ifeq +7 -> 105
    //   101: iconst_1
    //   102: invokestatic 328	com/tapjoy/TapjoyLog:setDebugEnabled	(Z)V
    //   105: invokestatic 330	com/tapjoy/TapjoyConnectCore:k	()V
    //   108: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   111: invokevirtual 334	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   114: ldc_w 336
    //   117: aconst_null
    //   118: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   121: invokevirtual 340	android/content/Context:getPackageName	()Ljava/lang/String;
    //   124: invokevirtual 346	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   127: istore_2
    //   128: new 348	java/util/Properties
    //   131: dup
    //   132: invokespecial 349	java/util/Properties:<init>	()V
    //   135: astore_1
    //   136: aload_1
    //   137: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   140: invokevirtual 334	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   143: iload_2
    //   144: invokevirtual 353	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   147: invokevirtual 357	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   150: aload_1
    //   151: invokestatic 360	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   154: ldc_w 362
    //   157: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   160: ldc -92
    //   162: if_acmpne +7 -> 169
    //   165: aload_0
    //   166: invokespecial 364	com/tapjoy/TapjoyConnectCore:l	()V
    //   169: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   172: invokevirtual 368	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   175: ldc_w 370
    //   178: invokestatic 376	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   181: astore_1
    //   182: aload_1
    //   183: putstatic 166	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   186: aload_1
    //   187: ifnull +12 -> 199
    //   190: getstatic 166	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   193: invokevirtual 379	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   196: putstatic 166	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   199: getstatic 299	com/tapjoy/TapjoyConnectCore:af	Landroid/content/pm/PackageManager;
    //   202: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   205: invokevirtual 340	android/content/Context:getPackageName	()Ljava/lang/String;
    //   208: iconst_0
    //   209: invokevirtual 385	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   212: getfield 390	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   215: putstatic 186	com/tapjoy/TapjoyConnectCore:x	Ljava/lang/String;
    //   218: ldc_w 392
    //   221: putstatic 180	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   224: ldc_w 392
    //   227: putstatic 202	com/tapjoy/TapjoyConnectCore:F	Ljava/lang/String;
    //   230: getstatic 397	android/os/Build:MODEL	Ljava/lang/String;
    //   233: putstatic 176	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   236: getstatic 400	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   239: putstatic 178	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   242: getstatic 405	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   245: putstatic 182	com/tapjoy/TapjoyConnectCore:v	Ljava/lang/String;
    //   248: ldc_w 407
    //   251: putstatic 188	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   254: ldc_w 409
    //   257: putstatic 190	com/tapjoy/TapjoyConnectCore:z	Ljava/lang/String;
    //   260: getstatic 412	android/os/Build$VERSION:SDK_INT	I
    //   263: iconst_3
    //   264: if_icmple +35 -> 299
    //   267: new 414	com/tapjoy/TapjoyDisplayMetricsUtil
    //   270: dup
    //   271: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   274: invokespecial 415	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   277: astore_1
    //   278: aload_1
    //   279: invokevirtual 419	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   282: putstatic 192	com/tapjoy/TapjoyConnectCore:A	I
    //   285: aload_1
    //   286: invokevirtual 423	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   289: putstatic 194	com/tapjoy/TapjoyConnectCore:B	F
    //   292: aload_1
    //   293: invokevirtual 426	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   296: putstatic 196	com/tapjoy/TapjoyConnectCore:C	I
    //   299: ldc_w 428
    //   302: invokestatic 431	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   305: istore_3
    //   306: iload_3
    //   307: ifeq +914 -> 1221
    //   310: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   313: ldc_w 433
    //   316: invokevirtual 437	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   319: checkcast 439	android/net/wifi/WifiManager
    //   322: astore_1
    //   323: aload_1
    //   324: ifnull +42 -> 366
    //   327: aload_1
    //   328: invokevirtual 443	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   331: astore_1
    //   332: aload_1
    //   333: ifnull +33 -> 366
    //   336: aload_1
    //   337: invokevirtual 448	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   340: astore_1
    //   341: aload_1
    //   342: putstatic 172	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   345: aload_1
    //   346: ifnull +20 -> 366
    //   349: getstatic 172	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   352: ldc_w 450
    //   355: ldc -92
    //   357: invokevirtual 454	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   360: invokevirtual 379	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   363: putstatic 172	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   366: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   369: ldc_w 456
    //   372: invokevirtual 437	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   375: checkcast 458	android/telephony/TelephonyManager
    //   378: astore_1
    //   379: aload_1
    //   380: ifnull +239 -> 619
    //   383: aload_1
    //   384: invokevirtual 461	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   387: putstatic 204	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   390: aload_1
    //   391: invokevirtual 464	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   394: putstatic 206	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   397: aload_1
    //   398: invokevirtual 467	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   401: astore 4
    //   403: aload 4
    //   405: ifnull +41 -> 446
    //   408: aload 4
    //   410: invokevirtual 470	java/lang/String:length	()I
    //   413: iconst_5
    //   414: if_icmpeq +13 -> 427
    //   417: aload 4
    //   419: invokevirtual 470	java/lang/String:length	()I
    //   422: bipush 6
    //   424: if_icmpne +22 -> 446
    //   427: aload 4
    //   429: iconst_0
    //   430: iconst_3
    //   431: invokevirtual 474	java/lang/String:substring	(II)Ljava/lang/String;
    //   434: putstatic 208	com/tapjoy/TapjoyConnectCore:I	Ljava/lang/String;
    //   437: aload 4
    //   439: iconst_3
    //   440: invokevirtual 477	java/lang/String:substring	(I)Ljava/lang/String;
    //   443: putstatic 210	com/tapjoy/TapjoyConnectCore:J	Ljava/lang/String;
    //   446: ldc_w 479
    //   449: invokestatic 431	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   452: istore_3
    //   453: iload_3
    //   454: ifeq +989 -> 1443
    //   457: ldc_w 481
    //   460: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   463: ifnull +770 -> 1233
    //   466: ldc_w 481
    //   469: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   472: invokevirtual 470	java/lang/String:length	()I
    //   475: ifle +758 -> 1233
    //   478: ldc_w 481
    //   481: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   484: putstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   487: ldc_w 483
    //   490: new 485	java/lang/StringBuilder
    //   493: dup
    //   494: ldc_w 487
    //   497: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   500: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   503: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: invokevirtual 497	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   509: invokestatic 500	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   512: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   515: ifnonnull +772 -> 1287
    //   518: ldc_w 483
    //   521: new 502	com/tapjoy/TapjoyErrorMessage
    //   524: dup
    //   525: getstatic 508	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   528: ldc_w 510
    //   531: invokespecial 513	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   534: invokestatic 516	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   537: iconst_0
    //   538: istore_2
    //   539: ldc_w 483
    //   542: new 485	java/lang/StringBuilder
    //   545: dup
    //   546: ldc_w 518
    //   549: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   552: getstatic 412	android/os/Build$VERSION:SDK_INT	I
    //   555: invokevirtual 521	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   558: invokevirtual 497	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   561: invokestatic 523	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   564: getstatic 412	android/os/Build$VERSION:SDK_INT	I
    //   567: bipush 9
    //   569: if_icmplt +50 -> 619
    //   572: ldc_w 483
    //   575: ldc_w 525
    //   578: invokestatic 500	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   581: aload_0
    //   582: invokevirtual 528	com/tapjoy/TapjoyConnectCore:getSerial	()Ljava/lang/String;
    //   585: astore_1
    //   586: iload_2
    //   587: ifne +7 -> 594
    //   590: aload_1
    //   591: putstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   594: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   597: ifnonnull +764 -> 1361
    //   600: ldc_w 483
    //   603: new 502	com/tapjoy/TapjoyErrorMessage
    //   606: dup
    //   607: getstatic 508	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   610: ldc_w 530
    //   613: invokespecial 513	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   616: invokestatic 516	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   619: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   622: ldc_w 532
    //   625: iconst_0
    //   626: invokevirtual 536	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   629: astore_1
    //   630: aload_1
    //   631: ldc_w 538
    //   634: ldc -92
    //   636: invokeinterface 543 3 0
    //   641: astore 4
    //   643: aload 4
    //   645: putstatic 174	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   648: aload 4
    //   650: ifnull +14 -> 664
    //   653: getstatic 174	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   656: invokevirtual 470	java/lang/String:length	()I
    //   659: istore_2
    //   660: iload_2
    //   661: ifne +61 -> 722
    //   664: new 485	java/lang/StringBuilder
    //   667: dup
    //   668: invokespecial 544	java/lang/StringBuilder:<init>	()V
    //   671: invokestatic 550	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   674: invokevirtual 551	java/util/UUID:toString	()Ljava/lang/String;
    //   677: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   680: invokestatic 557	java/lang/System:currentTimeMillis	()J
    //   683: invokevirtual 560	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   686: invokevirtual 497	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   689: invokestatic 565	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   692: putstatic 174	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   695: aload_1
    //   696: invokeinterface 569 1 0
    //   701: astore_1
    //   702: aload_1
    //   703: ldc_w 538
    //   706: getstatic 174	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   709: invokeinterface 575 3 0
    //   714: pop
    //   715: aload_1
    //   716: invokeinterface 579 1 0
    //   721: pop
    //   722: ldc_w 581
    //   725: ldc_w 583
    //   728: invokestatic 586	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   731: putstatic 200	com/tapjoy/TapjoyConnectCore:E	Z
    //   734: ldc_w 588
    //   737: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   740: ifnull +71 -> 811
    //   743: ldc_w 588
    //   746: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   749: invokevirtual 470	java/lang/String:length	()I
    //   752: ifle +59 -> 811
    //   755: ldc_w 588
    //   758: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   761: putstatic 216	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   764: new 590	java/util/ArrayList
    //   767: dup
    //   768: getstatic 593	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   771: invokestatic 156	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   774: invokespecial 594	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   777: getstatic 216	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   780: invokevirtual 597	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   783: ifne +28 -> 811
    //   786: ldc_w 483
    //   789: new 485	java/lang/StringBuilder
    //   792: dup
    //   793: ldc_w 599
    //   796: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   799: getstatic 216	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   802: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   805: invokevirtual 497	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   808: invokestatic 601	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   811: getstatic 216	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   814: astore_1
    //   815: new 603	android/content/Intent
    //   818: dup
    //   819: ldc_w 605
    //   822: invokespecial 606	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   825: astore 4
    //   827: aload_1
    //   828: invokevirtual 470	java/lang/String:length	()I
    //   831: ifgt +684 -> 1515
    //   834: aload 4
    //   836: ldc_w 608
    //   839: invokestatic 614	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   842: invokevirtual 618	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   845: pop
    //   846: getstatic 299	com/tapjoy/TapjoyConnectCore:af	Landroid/content/pm/PackageManager;
    //   849: aload 4
    //   851: iconst_0
    //   852: invokevirtual 622	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   855: invokeinterface 627 1 0
    //   860: ifle +735 -> 1595
    //   863: iconst_1
    //   864: istore_3
    //   865: iload_3
    //   866: putstatic 232	com/tapjoy/TapjoyConnectCore:T	Z
    //   869: invokestatic 629	com/tapjoy/TapjoyConnectCore:i	()V
    //   872: ldc_w 631
    //   875: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   878: ifnull +24 -> 902
    //   881: ldc_w 631
    //   884: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   887: invokevirtual 470	java/lang/String:length	()I
    //   890: ifle +12 -> 902
    //   893: ldc_w 631
    //   896: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   899: putstatic 254	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   902: ldc_w 633
    //   905: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   908: ifnull +24 -> 932
    //   911: ldc_w 633
    //   914: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   917: invokevirtual 470	java/lang/String:length	()I
    //   920: ifle +12 -> 932
    //   923: ldc_w 633
    //   926: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   929: putstatic 252	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   932: ldc_w 635
    //   935: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   938: ifnull +53 -> 991
    //   941: ldc_w 635
    //   944: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   947: invokevirtual 470	java/lang/String:length	()I
    //   950: ifle +41 -> 991
    //   953: ldc_w 483
    //   956: new 485	java/lang/StringBuilder
    //   959: dup
    //   960: ldc_w 637
    //   963: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   966: ldc_w 635
    //   969: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   972: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   975: invokevirtual 497	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   978: invokestatic 523	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   981: ldc_w 635
    //   984: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   987: aconst_null
    //   988: invokestatic 641	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;Lcom/tapjoy/TJSetUserIDListener;)V
    //   991: ldc_w 643
    //   994: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   997: invokestatic 646	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   1000: putstatic 228	com/tapjoy/TapjoyConnectCore:R	Ljava/lang/String;
    //   1003: new 485	java/lang/StringBuilder
    //   1006: dup
    //   1007: ldc_w 487
    //   1010: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1013: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1016: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1019: astore 4
    //   1021: ldc_w 481
    //   1024: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1027: ifnull +558 -> 1585
    //   1030: ldc_w 481
    //   1033: invokestatic 314	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1036: invokevirtual 470	java/lang/String:length	()I
    //   1039: ifle +546 -> 1585
    //   1042: ldc_w 648
    //   1045: astore_1
    //   1046: ldc_w 483
    //   1049: aload 4
    //   1051: aload_1
    //   1052: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1055: invokevirtual 497	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1058: invokestatic 500	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1061: getstatic 261	com/tapjoy/TapjoyConnectCore:ah	Ljava/util/Hashtable;
    //   1064: ifnull +6 -> 1070
    //   1067: invokestatic 650	com/tapjoy/TapjoyConnectCore:j	()V
    //   1070: aload_0
    //   1071: invokevirtual 653	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   1074: aload_0
    //   1075: iconst_1
    //   1076: putfield 288	com/tapjoy/TapjoyConnectCore:ad	Z
    //   1079: return
    //   1080: astore_1
    //   1081: new 275	com/tapjoy/TapjoyException
    //   1084: dup
    //   1085: aload_1
    //   1086: invokevirtual 656	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   1089: invokespecial 657	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   1092: athrow
    //   1093: astore_1
    //   1094: ldc_w 483
    //   1097: new 502	com/tapjoy/TapjoyErrorMessage
    //   1100: dup
    //   1101: getstatic 660	com/tapjoy/TapjoyErrorMessage$ErrorType:INTEGRATION_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1104: aload_1
    //   1105: invokevirtual 661	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   1108: invokespecial 513	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1111: invokestatic 516	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1114: invokestatic 663	com/tapjoy/TapjoyConnectCore:f	()V
    //   1117: getstatic 668	com/tapjoy/internal/eo:b	Lcom/tapjoy/internal/eo$a;
    //   1120: getstatic 674	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1123: invokevirtual 680	com/tapjoy/internal/eo$a:notifyObservers	(Ljava/lang/Object;)V
    //   1126: return
    //   1127: astore_1
    //   1128: ldc_w 483
    //   1131: new 485	java/lang/StringBuilder
    //   1134: dup
    //   1135: ldc_w 682
    //   1138: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1141: aload_1
    //   1142: invokevirtual 683	java/lang/Exception:toString	()Ljava/lang/String;
    //   1145: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1148: invokevirtual 497	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1151: invokestatic 685	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1154: goto -855 -> 299
    //   1157: astore_1
    //   1158: ldc_w 483
    //   1161: new 502	com/tapjoy/TapjoyErrorMessage
    //   1164: dup
    //   1165: getstatic 508	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1168: aload_1
    //   1169: invokevirtual 686	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   1172: invokespecial 513	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1175: invokestatic 516	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1178: invokestatic 663	com/tapjoy/TapjoyConnectCore:f	()V
    //   1181: getstatic 668	com/tapjoy/internal/eo:b	Lcom/tapjoy/internal/eo$a;
    //   1184: getstatic 674	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1187: invokevirtual 680	com/tapjoy/internal/eo$a:notifyObservers	(Ljava/lang/Object;)V
    //   1190: return
    //   1191: astore_1
    //   1192: ldc_w 483
    //   1195: new 485	java/lang/StringBuilder
    //   1198: dup
    //   1199: ldc_w 688
    //   1202: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1205: aload_1
    //   1206: invokevirtual 683	java/lang/Exception:toString	()Ljava/lang/String;
    //   1209: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1212: invokevirtual 497	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1215: invokestatic 685	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1218: goto -852 -> 366
    //   1221: ldc_w 483
    //   1224: ldc_w 690
    //   1227: invokestatic 500	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1230: goto -864 -> 366
    //   1233: aload_1
    //   1234: invokevirtual 693	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1237: putstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1240: goto -753 -> 487
    //   1243: astore_1
    //   1244: ldc_w 483
    //   1247: new 502	com/tapjoy/TapjoyErrorMessage
    //   1250: dup
    //   1251: getstatic 508	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1254: new 485	java/lang/StringBuilder
    //   1257: dup
    //   1258: ldc_w 695
    //   1261: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1264: aload_1
    //   1265: invokevirtual 683	java/lang/Exception:toString	()Ljava/lang/String;
    //   1268: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1271: invokevirtual 497	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1274: invokespecial 513	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1277: invokestatic 516	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1280: aconst_null
    //   1281: putstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1284: goto -665 -> 619
    //   1287: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1290: invokevirtual 470	java/lang/String:length	()I
    //   1293: ifeq +27 -> 1320
    //   1296: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1299: ldc_w 697
    //   1302: invokevirtual 322	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1305: ifne +15 -> 1320
    //   1308: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1311: ldc_w 699
    //   1314: invokevirtual 322	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1317: ifeq +27 -> 1344
    //   1320: ldc_w 483
    //   1323: new 502	com/tapjoy/TapjoyErrorMessage
    //   1326: dup
    //   1327: getstatic 508	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1330: ldc_w 701
    //   1333: invokespecial 513	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1336: invokestatic 516	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1339: iconst_0
    //   1340: istore_2
    //   1341: goto -802 -> 539
    //   1344: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1347: invokestatic 707	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1350: invokevirtual 710	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1353: putstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1356: iconst_1
    //   1357: istore_2
    //   1358: goto -819 -> 539
    //   1361: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1364: invokevirtual 470	java/lang/String:length	()I
    //   1367: ifeq +39 -> 1406
    //   1370: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1373: ldc_w 697
    //   1376: invokevirtual 322	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1379: ifne +27 -> 1406
    //   1382: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1385: ldc_w 699
    //   1388: invokevirtual 322	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1391: ifne +15 -> 1406
    //   1394: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1397: ldc_w 712
    //   1400: invokevirtual 322	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1403: ifeq +25 -> 1428
    //   1406: ldc_w 483
    //   1409: new 502	com/tapjoy/TapjoyErrorMessage
    //   1412: dup
    //   1413: getstatic 508	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1416: ldc_w 714
    //   1419: invokespecial 513	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1422: invokestatic 516	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1425: goto -806 -> 619
    //   1428: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1431: invokestatic 707	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1434: invokevirtual 710	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1437: putstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1440: goto -821 -> 619
    //   1443: ldc_w 483
    //   1446: ldc_w 716
    //   1449: invokestatic 500	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1452: goto -833 -> 619
    //   1455: astore_1
    //   1456: ldc_w 483
    //   1459: new 485	java/lang/StringBuilder
    //   1462: dup
    //   1463: ldc_w 718
    //   1466: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1469: aload_1
    //   1470: invokevirtual 683	java/lang/Exception:toString	()Ljava/lang/String;
    //   1473: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1476: invokevirtual 497	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1479: invokestatic 685	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1482: goto -760 -> 722
    //   1485: astore_1
    //   1486: ldc_w 483
    //   1489: new 485	java/lang/StringBuilder
    //   1492: dup
    //   1493: ldc_w 720
    //   1496: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1499: aload_1
    //   1500: invokevirtual 683	java/lang/Exception:toString	()Ljava/lang/String;
    //   1503: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1506: invokevirtual 497	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1509: invokestatic 685	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1512: goto -778 -> 734
    //   1515: aload_1
    //   1516: ldc_w 722
    //   1519: invokevirtual 322	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1522: ifeq +13 -> 1535
    //   1525: ldc_w 724
    //   1528: invokestatic 726	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1531: istore_3
    //   1532: goto -667 -> 865
    //   1535: aload_1
    //   1536: ldc_w 728
    //   1539: invokevirtual 322	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1542: ifeq +53 -> 1595
    //   1545: ldc_w 730
    //   1548: invokestatic 726	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1551: istore_3
    //   1552: goto -687 -> 865
    //   1555: astore_1
    //   1556: ldc_w 483
    //   1559: new 485	java/lang/StringBuilder
    //   1562: dup
    //   1563: ldc_w 732
    //   1566: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1569: aload_1
    //   1570: invokevirtual 683	java/lang/Exception:toString	()Ljava/lang/String;
    //   1573: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1576: invokevirtual 497	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1579: invokestatic 685	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1582: goto -713 -> 869
    //   1585: ldc -92
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
      String str = TapjoyUtil.SHA256(w + ":" + q() + ":" + paramLong + ":" + N);
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
      paramString = TapjoyUtil.SHA256(w + ":" + q() + ":" + paramLong + ":" + N + ":" + paramString);
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
      ai = "";
      Iterator localIterator = af.getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (((localApplicationInfo.flags & 0x1) != 1) && (paramList.contains(localApplicationInfo.packageName)))
        {
          TapjoyLog.d("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
          if (ai.length() > 0) {
            ai += ",";
          }
          ai += localApplicationInfo.packageName;
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
    FeatureInfo[] arrayOfFeatureInfo = af.getSystemAvailableFeatures();
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
        if (af.checkPermission(paramString2, g.getPackageName()) == 0) {
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
    //   1: invokestatic 869	com/tapjoy/internal/br:b	(Ljava/lang/String;)Lcom/tapjoy/internal/br;
    //   4: astore 8
    //   6: aload 8
    //   8: astore 9
    //   10: aload 8
    //   12: invokevirtual 872	com/tapjoy/internal/br:d	()Ljava/util/Map;
    //   15: astore 11
    //   17: aload 8
    //   19: astore 9
    //   21: aload 11
    //   23: ldc_w 874
    //   26: invokeinterface 877 2 0
    //   31: checkcast 318	java/lang/String
    //   34: invokestatic 881	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   37: astore 10
    //   39: aload 8
    //   41: astore 9
    //   43: aload 11
    //   45: ldc_w 883
    //   48: invokeinterface 877 2 0
    //   53: checkcast 318	java/lang/String
    //   56: invokestatic 881	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   59: astore 14
    //   61: aload 8
    //   63: astore 9
    //   65: aload 11
    //   67: ldc_w 885
    //   70: invokeinterface 877 2 0
    //   75: checkcast 318	java/lang/String
    //   78: invokestatic 881	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   81: astore 15
    //   83: aload 8
    //   85: astore 9
    //   87: aload 11
    //   89: ldc_w 887
    //   92: invokeinterface 877 2 0
    //   97: checkcast 318	java/lang/String
    //   100: invokestatic 881	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   103: astore 16
    //   105: aload 8
    //   107: astore 9
    //   109: aload 11
    //   111: ldc_w 889
    //   114: invokeinterface 877 2 0
    //   119: checkcast 318	java/lang/String
    //   122: invokestatic 881	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   125: astore 13
    //   127: aload 8
    //   129: astore 9
    //   131: aload 11
    //   133: ldc_w 891
    //   136: invokeinterface 877 2 0
    //   141: astore 12
    //   143: aload 8
    //   145: astore 9
    //   147: new 893	com/tapjoy/internal/el
    //   150: dup
    //   151: aload 15
    //   153: invokespecial 894	com/tapjoy/internal/el:<init>	(Ljava/lang/String;)V
    //   156: astore 17
    //   158: aload 8
    //   160: astore 9
    //   162: aload 17
    //   164: getfield 897	com/tapjoy/internal/el:a	Lcom/tapjoy/internal/el$a;
    //   167: getstatic 902	com/tapjoy/internal/el$a:RPC_ANALYTICS	Lcom/tapjoy/internal/el$a;
    //   170: if_acmpeq +44 -> 214
    //   173: aload 8
    //   175: astore 9
    //   177: new 860	java/io/IOException
    //   180: dup
    //   181: ldc_w 904
    //   184: invokespecial 905	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   187: athrow
    //   188: astore 9
    //   190: aload 8
    //   192: astore_0
    //   193: aload 9
    //   195: astore 8
    //   197: ldc_w 483
    //   200: aload 8
    //   202: invokevirtual 906	java/io/IOException:getMessage	()Ljava/lang/String;
    //   205: invokestatic 908	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   208: aload_0
    //   209: invokestatic 913	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   212: iconst_0
    //   213: ireturn
    //   214: aload 8
    //   216: astore 9
    //   218: aload 17
    //   220: getfield 915	com/tapjoy/internal/el:b	Ljava/lang/String;
    //   223: astore 11
    //   225: aload 8
    //   227: astore 9
    //   229: aload 11
    //   231: bipush 13
    //   233: ldc_w 917
    //   236: iconst_0
    //   237: bipush 11
    //   239: invokevirtual 921	java/lang/String:regionMatches	(ILjava/lang/String;II)Z
    //   242: ifeq +234 -> 476
    //   245: aload 8
    //   247: astore 9
    //   249: new 923	java/lang/StringBuffer
    //   252: dup
    //   253: invokespecial 924	java/lang/StringBuffer:<init>	()V
    //   256: aload 11
    //   258: iconst_0
    //   259: bipush 8
    //   261: invokevirtual 474	java/lang/String:substring	(II)Ljava/lang/String;
    //   264: invokevirtual 927	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   267: aload 11
    //   269: bipush 24
    //   271: bipush 30
    //   273: invokevirtual 474	java/lang/String:substring	(II)Ljava/lang/String;
    //   276: invokevirtual 927	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   279: aload 11
    //   281: bipush 9
    //   283: bipush 13
    //   285: invokevirtual 474	java/lang/String:substring	(II)Ljava/lang/String;
    //   288: invokevirtual 927	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   291: aload 11
    //   293: bipush 30
    //   295: invokevirtual 477	java/lang/String:substring	(I)Ljava/lang/String;
    //   298: invokevirtual 927	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   301: invokevirtual 928	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   304: astore 11
    //   306: aload 8
    //   308: astore 9
    //   310: aload 17
    //   312: getfield 929	com/tapjoy/internal/el:c	Ljava/lang/String;
    //   315: astore 17
    //   317: aload 10
    //   319: ifnonnull +384 -> 703
    //   322: aload 11
    //   324: astore 10
    //   326: aload 8
    //   328: astore 9
    //   330: invokestatic 934	com/tapjoy/internal/fm:a	()Lcom/tapjoy/internal/fm;
    //   333: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   336: aload 15
    //   338: ldc_w 407
    //   341: ldc_w 936
    //   344: aload 11
    //   346: aload 17
    //   348: invokevirtual 939	com/tapjoy/internal/fm:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   351: aload 8
    //   353: astore 9
    //   355: aload 10
    //   357: putstatic 234	com/tapjoy/TapjoyConnectCore:U	Ljava/lang/String;
    //   360: aload 8
    //   362: astore 9
    //   364: aload 14
    //   366: putstatic 236	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   369: aload 8
    //   371: astore 9
    //   373: aload 15
    //   375: putstatic 238	com/tapjoy/TapjoyConnectCore:W	Ljava/lang/String;
    //   378: aload 8
    //   380: astore 9
    //   382: aload 16
    //   384: putstatic 240	com/tapjoy/TapjoyConnectCore:X	Ljava/lang/String;
    //   387: aload 8
    //   389: astore 9
    //   391: new 590	java/util/ArrayList
    //   394: dup
    //   395: invokespecial 940	java/util/ArrayList:<init>	()V
    //   398: astore 10
    //   400: aload 13
    //   402: ifnull +112 -> 514
    //   405: aload 8
    //   407: astore 9
    //   409: aload 13
    //   411: ldc_w 772
    //   414: invokevirtual 944	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   417: astore 11
    //   419: aload 8
    //   421: astore 9
    //   423: aload 11
    //   425: arraylength
    //   426: istore_3
    //   427: iconst_0
    //   428: istore_2
    //   429: iload_2
    //   430: iload_3
    //   431: if_icmpge +83 -> 514
    //   434: aload 8
    //   436: astore 9
    //   438: aload 11
    //   440: iload_2
    //   441: aaload
    //   442: invokevirtual 834	java/lang/String:trim	()Ljava/lang/String;
    //   445: astore 13
    //   447: aload 8
    //   449: astore 9
    //   451: aload 13
    //   453: invokevirtual 470	java/lang/String:length	()I
    //   456: ifle +250 -> 706
    //   459: aload 8
    //   461: astore 9
    //   463: aload 10
    //   465: aload 13
    //   467: invokeinterface 945 2 0
    //   472: pop
    //   473: goto +233 -> 706
    //   476: aload 8
    //   478: astore 9
    //   480: new 947	java/lang/IllegalArgumentException
    //   483: dup
    //   484: ldc_w 949
    //   487: invokespecial 950	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   490: athrow
    //   491: astore_0
    //   492: aload 8
    //   494: astore 9
    //   496: ldc_w 483
    //   499: aload_0
    //   500: invokevirtual 951	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   503: invokestatic 908	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   506: aload 8
    //   508: invokestatic 913	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   511: goto -299 -> 212
    //   514: aload 8
    //   516: astore 9
    //   518: aload 10
    //   520: invokeinterface 954 1 0
    //   525: ifne +12 -> 537
    //   528: aload 8
    //   530: astore 9
    //   532: aload 10
    //   534: invokestatic 839	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   537: aload 8
    //   539: astore 9
    //   541: aload 8
    //   543: invokevirtual 957	com/tapjoy/internal/br:close	()V
    //   546: iload_1
    //   547: ifne +42 -> 589
    //   550: lconst_0
    //   551: lstore 6
    //   553: aload 12
    //   555: instanceof 318
    //   558: istore_1
    //   559: iload_1
    //   560: ifeq +35 -> 595
    //   563: aload 12
    //   565: checkcast 318	java/lang/String
    //   568: invokevirtual 834	java/lang/String:trim	()Ljava/lang/String;
    //   571: invokestatic 963	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   574: lstore 4
    //   576: lload 4
    //   578: lconst_0
    //   579: lcmp
    //   580: ifgt +42 -> 622
    //   583: invokestatic 969	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   586: invokevirtual 972	com/tapjoy/TapjoyAppSettings:removeConnectResult	()V
    //   589: aconst_null
    //   590: invokestatic 913	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   593: iconst_1
    //   594: ireturn
    //   595: aload 12
    //   597: instanceof 974
    //   600: istore_1
    //   601: lload 6
    //   603: lstore 4
    //   605: iload_1
    //   606: ifeq -30 -> 576
    //   609: aload 12
    //   611: checkcast 974	java/lang/Number
    //   614: invokevirtual 977	java/lang/Number:longValue	()J
    //   617: lstore 4
    //   619: goto -43 -> 576
    //   622: invokestatic 969	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   625: aload_0
    //   626: invokestatic 979	com/tapjoy/TapjoyConnectCore:s	()Ljava/lang/String;
    //   629: lload 4
    //   631: ldc2_w 980
    //   634: lmul
    //   635: invokestatic 985	com/tapjoy/internal/y:b	()J
    //   638: ladd
    //   639: invokevirtual 989	com/tapjoy/TapjoyAppSettings:saveConnectResultAndParams	(Ljava/lang/String;Ljava/lang/String;J)V
    //   642: goto -53 -> 589
    //   645: aconst_null
    //   646: astore_0
    //   647: astore 8
    //   649: goto -452 -> 197
    //   652: astore_0
    //   653: aconst_null
    //   654: astore 9
    //   656: aload 9
    //   658: invokestatic 913	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   661: aload_0
    //   662: athrow
    //   663: astore_0
    //   664: goto -8 -> 656
    //   667: astore 8
    //   669: aload_0
    //   670: astore 9
    //   672: aload 8
    //   674: astore_0
    //   675: goto -19 -> 656
    //   678: astore_0
    //   679: aconst_null
    //   680: astore 8
    //   682: goto -190 -> 492
    //   685: astore 8
    //   687: lload 6
    //   689: lstore 4
    //   691: goto -115 -> 576
    //   694: astore 8
    //   696: lload 6
    //   698: lstore 4
    //   700: goto -124 -> 576
    //   703: goto -377 -> 326
    //   706: iload_2
    //   707: iconst_1
    //   708: iadd
    //   709: istore_2
    //   710: goto -281 -> 429
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	713	0	paramString	String
    //   0	713	1	paramBoolean	boolean
    //   428	282	2	i1	int
    //   426	6	3	i2	int
    //   574	125	4	l1	long
    //   551	146	6	l2	long
    //   4	538	8	localObject1	Object
    //   647	1	8	localIOException1	java.io.IOException
    //   667	6	8	localObject2	Object
    //   680	1	8	localObject3	Object
    //   685	1	8	localNumberFormatException1	NumberFormatException
    //   694	1	8	localNumberFormatException2	NumberFormatException
    //   8	168	9	localObject4	Object
    //   188	6	9	localIOException2	java.io.IOException
    //   216	455	9	localObject5	Object
    //   37	496	10	localObject6	Object
    //   15	424	11	localObject7	Object
    //   141	469	12	localObject8	Object
    //   125	341	13	str1	String
    //   59	306	14	str2	String
    //   81	293	15	str3	String
    //   103	280	16	str4	String
    //   156	191	17	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   10	17	188	java/io/IOException
    //   21	39	188	java/io/IOException
    //   43	61	188	java/io/IOException
    //   65	83	188	java/io/IOException
    //   87	105	188	java/io/IOException
    //   109	127	188	java/io/IOException
    //   131	143	188	java/io/IOException
    //   147	158	188	java/io/IOException
    //   162	173	188	java/io/IOException
    //   177	188	188	java/io/IOException
    //   218	225	188	java/io/IOException
    //   229	245	188	java/io/IOException
    //   249	306	188	java/io/IOException
    //   310	317	188	java/io/IOException
    //   330	351	188	java/io/IOException
    //   355	360	188	java/io/IOException
    //   364	369	188	java/io/IOException
    //   373	378	188	java/io/IOException
    //   382	387	188	java/io/IOException
    //   391	400	188	java/io/IOException
    //   409	419	188	java/io/IOException
    //   423	427	188	java/io/IOException
    //   438	447	188	java/io/IOException
    //   451	459	188	java/io/IOException
    //   463	473	188	java/io/IOException
    //   480	491	188	java/io/IOException
    //   518	528	188	java/io/IOException
    //   532	537	188	java/io/IOException
    //   541	546	188	java/io/IOException
    //   10	17	491	java/lang/RuntimeException
    //   21	39	491	java/lang/RuntimeException
    //   43	61	491	java/lang/RuntimeException
    //   65	83	491	java/lang/RuntimeException
    //   87	105	491	java/lang/RuntimeException
    //   109	127	491	java/lang/RuntimeException
    //   131	143	491	java/lang/RuntimeException
    //   147	158	491	java/lang/RuntimeException
    //   162	173	491	java/lang/RuntimeException
    //   177	188	491	java/lang/RuntimeException
    //   218	225	491	java/lang/RuntimeException
    //   229	245	491	java/lang/RuntimeException
    //   249	306	491	java/lang/RuntimeException
    //   310	317	491	java/lang/RuntimeException
    //   330	351	491	java/lang/RuntimeException
    //   355	360	491	java/lang/RuntimeException
    //   364	369	491	java/lang/RuntimeException
    //   373	378	491	java/lang/RuntimeException
    //   382	387	491	java/lang/RuntimeException
    //   391	400	491	java/lang/RuntimeException
    //   409	419	491	java/lang/RuntimeException
    //   423	427	491	java/lang/RuntimeException
    //   438	447	491	java/lang/RuntimeException
    //   451	459	491	java/lang/RuntimeException
    //   463	473	491	java/lang/RuntimeException
    //   480	491	491	java/lang/RuntimeException
    //   518	528	491	java/lang/RuntimeException
    //   532	537	491	java/lang/RuntimeException
    //   541	546	491	java/lang/RuntimeException
    //   0	6	645	java/io/IOException
    //   553	559	645	java/io/IOException
    //   563	576	645	java/io/IOException
    //   583	589	645	java/io/IOException
    //   595	601	645	java/io/IOException
    //   609	619	645	java/io/IOException
    //   622	642	645	java/io/IOException
    //   0	6	652	finally
    //   553	559	652	finally
    //   563	576	652	finally
    //   583	589	652	finally
    //   595	601	652	finally
    //   609	619	652	finally
    //   622	642	652	finally
    //   10	17	663	finally
    //   21	39	663	finally
    //   43	61	663	finally
    //   65	83	663	finally
    //   87	105	663	finally
    //   109	127	663	finally
    //   131	143	663	finally
    //   147	158	663	finally
    //   162	173	663	finally
    //   177	188	663	finally
    //   218	225	663	finally
    //   229	245	663	finally
    //   249	306	663	finally
    //   310	317	663	finally
    //   330	351	663	finally
    //   355	360	663	finally
    //   364	369	663	finally
    //   373	378	663	finally
    //   382	387	663	finally
    //   391	400	663	finally
    //   409	419	663	finally
    //   423	427	663	finally
    //   438	447	663	finally
    //   451	459	663	finally
    //   463	473	663	finally
    //   480	491	663	finally
    //   496	506	663	finally
    //   518	528	663	finally
    //   532	537	663	finally
    //   541	546	663	finally
    //   197	208	667	finally
    //   0	6	678	java/lang/RuntimeException
    //   553	559	678	java/lang/RuntimeException
    //   563	576	678	java/lang/RuntimeException
    //   583	589	678	java/lang/RuntimeException
    //   595	601	678	java/lang/RuntimeException
    //   609	619	678	java/lang/RuntimeException
    //   622	642	678	java/lang/RuntimeException
    //   609	619	685	java/lang/NumberFormatException
    //   563	576	694	java/lang/NumberFormatException
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
    ah.put(paramString1, str);
  }
  
  /* Error */
  private static boolean c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 869	com/tapjoy/internal/br:b	(Ljava/lang/String;)Lcom/tapjoy/internal/br;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 1008	com/tapjoy/internal/br:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 1010	com/tapjoy/internal/br:s	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 483
    //   25: ldc_w 1012
    //   28: invokestatic 500	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 957	com/tapjoy/internal/br:close	()V
    //   37: aconst_null
    //   38: invokestatic 913	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 957	com/tapjoy/internal/br:close	()V
    //   49: aconst_null
    //   50: invokestatic 913	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   53: ldc_w 483
    //   56: new 502	com/tapjoy/TapjoyErrorMessage
    //   59: dup
    //   60: getstatic 508	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   63: ldc_w 1014
    //   66: invokespecial 513	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   69: invokestatic 516	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   72: iconst_0
    //   73: ireturn
    //   74: astore_2
    //   75: aconst_null
    //   76: astore_1
    //   77: aload_1
    //   78: astore_0
    //   79: ldc_w 483
    //   82: aload_2
    //   83: invokevirtual 906	java/io/IOException:getMessage	()Ljava/lang/String;
    //   86: invokestatic 908	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload_1
    //   90: invokestatic 913	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   93: goto -40 -> 53
    //   96: astore_2
    //   97: aconst_null
    //   98: astore_1
    //   99: aload_1
    //   100: astore_0
    //   101: ldc_w 483
    //   104: aload_2
    //   105: invokevirtual 951	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   108: invokestatic 908	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   111: aload_1
    //   112: invokestatic 913	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   115: goto -62 -> 53
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_0
    //   121: aload_0
    //   122: invokestatic 913	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
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
    //   4	108	1	localBr	com.tapjoy.internal.br
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
  
  private static boolean d(String paramString)
  {
    Iterator localIterator = af.getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.startsWith(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean e(String paramString)
  {
    return af.checkPermission(paramString, g.getPackageName()) == 0;
  }
  
  private static void f()
  {
    if (!cr.c(O)) {
      fm.a().a(g, h, "11.8.2", "https://rpc.tapjoy.com/", O, N);
    }
    if (k != null) {
      k.onConnectFailure();
    }
  }
  
  private static Map g()
  {
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "plugin", P, true);
    TapjoyUtil.safePut(localHashMap3, "sdk_type", Q, true);
    TapjoyUtil.safePut(localHashMap3, "app_id", w, true);
    TapjoyUtil.safePut(localHashMap3, "library_version", y, true);
    TapjoyUtil.safePut(localHashMap3, "library_revision", "4b56f04", true);
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
    TapjoyUtil.safePut(localHashMap3, "screen_density", A, true);
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
    if ((o == null) || (o.length() == 0) || (System.currentTimeMillis() - ac > 1800000L)) {
      o = p();
    }
    for (;;)
    {
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
      i();
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "analytics_id", ak, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_id", al, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_sign", am, true);
      TapjoyUtil.safePut(localHashMap3, "display_d", aM, true);
      TapjoyUtil.safePut(localHashMap3, "display_w", aN, true);
      TapjoyUtil.safePut(localHashMap3, "display_h", aO, true);
      TapjoyUtil.safePut(localHashMap3, "country_sim", aP, true);
      TapjoyUtil.safePut(localHashMap3, "timezone", aQ, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "pkg_ver", an, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_rev", ao, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_data_ver", ap, true);
      TapjoyUtil.safePut(localHashMap3, "installer", aq, true);
      if (cr.c(M)) {
        TapjoyUtil.safePut(localHashMap3, "store_name", aR, true);
      }
      localHashMap2.putAll(localHashMap3);
      localHashMap2.putAll(h());
      localHashMap1.putAll(localHashMap2);
      return localHashMap1;
      ac = System.currentTimeMillis();
    }
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
      paramString = TapjoyUtil.SHA256(w + ":" + q() + ":" + paramLong + ":" + N + ":" + paramInt + ":" + paramString);
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
    if (ah != null)
    {
      str1 = str2;
      if (ah.get(paramString) != null) {
        str1 = ah.get(paramString).toString();
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
    Map localMap = g();
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
  
  private static Map h()
  {
    HashMap localHashMap = new HashMap();
    TapjoyUtil.safePut(localHashMap, "installed", ar, true);
    TapjoyUtil.safePut(localHashMap, "referrer", as, true);
    TapjoyUtil.safePut(localHashMap, "user_level", at, true);
    TapjoyUtil.safePut(localHashMap, "friend_count", au, true);
    TapjoyUtil.safePut(localHashMap, "uv1", av, true);
    TapjoyUtil.safePut(localHashMap, "uv2", aw, true);
    TapjoyUtil.safePut(localHashMap, "uv3", ax, true);
    TapjoyUtil.safePut(localHashMap, "uv4", ay, true);
    TapjoyUtil.safePut(localHashMap, "uv5", az, true);
    Iterator localIterator = aA.iterator();
    int i1 = 0;
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      TapjoyUtil.safePut(localHashMap, "user_tags[" + i1 + "]", str, true);
      i1 += 1;
    }
    TapjoyUtil.safePut(localHashMap, "fq7", aB, true);
    TapjoyUtil.safePut(localHashMap, "fq30", aC, true);
    TapjoyUtil.safePut(localHashMap, "session_total_count", aD, true);
    TapjoyUtil.safePut(localHashMap, "session_total_length", aE, true);
    TapjoyUtil.safePut(localHashMap, "session_last_at", aF, true);
    TapjoyUtil.safePut(localHashMap, "session_last_length", aG, true);
    TapjoyUtil.safePut(localHashMap, "purchase_currency", aH, true);
    TapjoyUtil.safePut(localHashMap, "purchase_total_count", aI, true);
    TapjoyUtil.safePut(localHashMap, "purchase_total_price", aJ, true);
    TapjoyUtil.safePut(localHashMap, "purchase_last_price", aK, true);
    TapjoyUtil.safePut(localHashMap, "purchase_last_at", aL, true);
    return localHashMap;
  }
  
  private static void i()
  {
    gd.n localN = fm.a(g).a(true);
    ak = localN.c.h();
    al = localN.c.y();
    am = localN.c.A();
    aM = localN.c.c;
    aN = localN.c.d;
    aO = localN.c.e;
    aP = localN.c.E();
    aQ = localN.c.w();
    an = localN.d.f();
    ao = localN.d.c;
    ap = localN.d.i();
    aq = localN.d.k();
    aR = localN.d.m();
    ar = localN.e.c;
    as = localN.e.g();
    at = localN.e.p;
    au = localN.e.q;
    av = localN.e.C();
    aw = localN.e.E();
    ax = localN.e.G();
    ay = localN.e.I();
    az = localN.e.K();
    aA = new HashSet(localN.e.r);
    aB = localN.e.d;
    aC = localN.e.e;
    aD = localN.e.g;
    aE = localN.e.h;
    aF = localN.e.i;
    aG = localN.e.j;
    aH = localN.e.p();
    aI = localN.e.k;
    aJ = localN.e.l;
    aK = localN.e.n;
    aL = localN.e.m;
  }
  
  public static boolean isConnected()
  {
    return ae;
  }
  
  public static boolean isFullScreenViewOpen()
  {
    Iterator localIterator = aj.values().iterator();
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
    return !aj.isEmpty();
  }
  
  private static void j()
  {
    TapjoyLog.i("TapjoyConnect", "Connect Flags:");
    TapjoyLog.i("TapjoyConnect", "--------------------");
    Iterator localIterator = ah.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      TapjoyLog.i("TapjoyConnect", "key: " + (String)localEntry.getKey() + ", value: " + Uri.encode(localEntry.getValue().toString()));
    }
    TapjoyLog.i("TapjoyConnect", "hostURL: [" + getConnectFlagValue("TJC_OPTION_SERVICE_URL") + "]");
    TapjoyLog.i("TapjoyConnect", "redirectDomain: [" + R + "]");
    TapjoyLog.i("TapjoyConnect", "--------------------");
  }
  
  private static void k()
  {
    for (;;)
    {
      int i1;
      try
      {
        if (af == null) {
          break;
        }
        ApplicationInfo localApplicationInfo = af.getApplicationInfo(g.getPackageName(), 128);
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
        Object localObject1 = Arrays.asList(af.getPackageInfo(g.getPackageName(), 1).activities);
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
    this.ag.checkGooglePlayIntegration();
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
      str = TapjoyUtil.SHA256(System.currentTimeMillis() / 1000L + w + p);
      TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + localException1.toString());
    }
    catch (Exception localException1)
    {
      try
      {
        ac = System.currentTimeMillis();
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
    if ((p != null) && (p.length() > 0)) {}
    for (int i1 = 1; i1 != 0; i1 = 0) {
      return p;
    }
    if ((q != null) && (q.length() > 0)) {}
    for (i1 = 1; i1 != 0; i1 = 0) {
      return q;
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
      Object localObject = (ArrayList)ah.get("TJC_OPTION_MEDIATION_CONFIGS");
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
    el localEl;
    try
    {
      localEl = new el(paramString);
      if (localEl.a != el.a.SDK_ANDROID) {
        throw new IllegalArgumentException("The given API key was not for Android.");
      }
    }
    catch (IllegalArgumentException paramContext)
    {
      throw new TapjoyIntegrationException(paramContext.getMessage());
    }
    h = paramString;
    w = localEl.b;
    N = localEl.c;
    O = localEl.d;
    fm.a(paramContext).j = paramString;
    if (paramHashtable != null) {
      ah.putAll(paramHashtable);
    }
    k = paramTJConnectListener;
    i = new TapjoyConnectCore(paramContext);
  }
  
  private static String s()
  {
    String str1 = w + x + y + c + p + r;
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
    TapjoyLog.d("TapjoyConnect", "URL parameters: " + getURLParams());
    new Thread(new Runnable()
    {
      public final void run()
      {
        TapjoyLog.i("TapjoyConnect", "Setting userID to " + TapjoyConnectCore.d());
        TapjoyHttpURLResponse localTapjoyHttpURLResponse = TapjoyConnectCore.e().getResponseFromURL(TapjoyConnectCore.getHostURL() + "set_publisher_user_id?", TapjoyConnectCore.getURLParams());
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
      aj.put("", Integer.valueOf(1));
      return;
    }
    aj.clear();
  }
  
  public static void viewDidClose(String paramString)
  {
    aj.remove(paramString);
    eo.e.notifyObservers();
  }
  
  public static void viewWillOpen(String paramString, int paramInt)
  {
    aj.put(paramString, Integer.valueOf(paramInt));
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
    this.ab = true;
  }
  
  public void appResume()
  {
    if (this.ab)
    {
      p();
      this.ab = false;
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
      localObject2 = TapjoyAppSettings.getInstance().getConnectResult(s(), y.b());
      if ((localObject2 != null) && (a((String)localObject2, true)))
      {
        TapjoyLog.i("TapjoyConnect", "Connect using stored connect result");
        ae = true;
        r();
        if (k != null) {
          k.onConnectSuccess();
        }
        eo.a.notifyObservers();
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
          ae = true;
          r();
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
            eo.a.notifyObservers();
          }
          eo.b.notifyObservers(Boolean.TRUE);
        }
        for (;;)
        {
          if (ai.length() > 0)
          {
            localObject1 = getGenericURLParams();
            TapjoyUtil.safePut((Map)localObject1, "package_names", ai, true);
            long l1 = System.currentTimeMillis() / 1000L;
            localObject2 = a(l1, ai);
            TapjoyUtil.safePut((Map)localObject1, "timestamp", String.valueOf(l1), true);
            TapjoyUtil.safePut((Map)localObject1, "verifier", (String)localObject2, true);
            localObject1 = new TapjoyURLConnection().getResponseFromURL(getHostURL() + "apps_installed?", (Map)localObject1);
            if ((localObject1 != null) && (((TapjoyHttpURLResponse)localObject1).statusCode == 200)) {
              TapjoyLog.d("TapjoyConnect", "Successfully pinged sdkless api.");
            }
          }
          return;
          if (i1 == 0) {
            f();
          }
          eo.b.notifyObservers(Boolean.FALSE);
        }
      }
      if (i1 == 0) {
        f();
      }
      eo.b.notifyObservers(Boolean.FALSE);
      return;
    }
  }
  
  public void enablePaidAppWithActionID(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "enablePaidAppWithActionID: " + paramString);
    Y = paramString;
    this.Z = g.getSharedPreferences("tjcPrefrences", 0).getLong("tapjoy_elapsed_time", 0L);
    TapjoyLog.d("TapjoyConnect", "paidApp elapsed: " + this.Z);
    if (this.Z >= 900000L) {
      if ((Y != null) && (Y.length() > 0))
      {
        TapjoyLog.d("TapjoyConnect", "Calling PPA actionComplete...");
        actionComplete(Y);
      }
    }
    while (this.aa != null) {
      return;
    }
    this.aa = new Timer();
    this.aa.schedule(new a((byte)0), 10000L, 10000L);
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
        if (TapjoyConnectCore.a(TapjoyConnectCore.this).isAdIdAvailable())
        {
          TapjoyConnectCore.d = TapjoyConnectCore.a(TapjoyConnectCore.this).isAdTrackingEnabled();
          TapjoyConnectCore.c = TapjoyConnectCore.a(TapjoyConnectCore.this).getAdvertisingId();
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
    //   0: ldc_w 1829
    //   3: invokestatic 1554	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 1831
    //   9: invokevirtual 1835	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 1840	java/lang/reflect/Field:isAccessible	()Z
    //   17: ifne +8 -> 25
    //   20: aload_1
    //   21: iconst_1
    //   22: invokevirtual 1843	java/lang/reflect/Field:setAccessible	(Z)V
    //   25: aload_1
    //   26: ldc_w 394
    //   29: invokevirtual 1844	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: invokevirtual 1215	java/lang/Object:toString	()Ljava/lang/String;
    //   35: astore_1
    //   36: ldc_w 483
    //   39: new 485	java/lang/StringBuilder
    //   42: dup
    //   43: ldc_w 1846
    //   46: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   49: aload_1
    //   50: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 497	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 500	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload_1
    //   60: areturn
    //   61: astore_2
    //   62: aconst_null
    //   63: astore_1
    //   64: ldc_w 483
    //   67: aload_2
    //   68: invokevirtual 683	java/lang/Exception:toString	()Ljava/lang/String;
    //   71: invokestatic 685	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
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
    return this.ad;
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
        TapjoyConnectCore.b(localTapjoyHttpURLResponse.response);
      }
    }
  }
  
  final class a
    extends TimerTask
  {
    private a() {}
    
    public final void run()
    {
      TapjoyConnectCore.b(TapjoyConnectCore.this);
      TapjoyLog.d("TapjoyConnect", "elapsed_time: " + TapjoyConnectCore.c(TapjoyConnectCore.this) + " (" + TapjoyConnectCore.c(TapjoyConnectCore.this) / 1000L / 60L + "m " + TapjoyConnectCore.c(TapjoyConnectCore.this) / 1000L % 60L + "s)");
      SharedPreferences.Editor localEditor = TapjoyConnectCore.b().getSharedPreferences("tjcPrefrences", 0).edit();
      localEditor.putLong("tapjoy_elapsed_time", TapjoyConnectCore.c(TapjoyConnectCore.this));
      localEditor.commit();
      if (TapjoyConnectCore.c(TapjoyConnectCore.this) >= 900000L)
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
