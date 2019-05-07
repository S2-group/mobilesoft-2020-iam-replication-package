package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
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
import com.tapjoy.internal.eq;
import com.tapjoy.internal.eq.a;
import com.tapjoy.internal.et;
import com.tapjoy.internal.et.a;
import com.tapjoy.internal.ex;
import com.tapjoy.internal.ez;
import com.tapjoy.internal.fz;
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
  private static Integer aB;
  private static Integer aC;
  private static Integer aD;
  private static Long aE;
  private static Long aF;
  private static Long aG;
  private static String aH;
  private static Integer aI;
  private static Double aJ;
  private static Double aK;
  private static Long aL;
  private static Integer aM;
  private static Integer aN;
  private static Integer aO;
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
  private static Integer ao;
  private static String ap;
  private static String aq;
  private static Long ar;
  private static String as;
  private static Integer at;
  private static Integer au;
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
    //   1: invokespecial 276	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 278	com/tapjoy/TapjoyConnectCore:Z	J
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 280	com/tapjoy/TapjoyConnectCore:aa	Ljava/util/Timer;
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 282	com/tapjoy/TapjoyConnectCore:ab	Z
    //   19: aload_0
    //   20: iconst_0
    //   21: putfield 284	com/tapjoy/TapjoyConnectCore:ad	Z
    //   24: aload_1
    //   25: putstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   28: invokestatic 289	com/tapjoy/internal/ez:a	()Lcom/tapjoy/internal/ez;
    //   31: aload_1
    //   32: invokevirtual 291	com/tapjoy/internal/ez:a	(Landroid/content/Context;)V
    //   35: new 293	com/tapjoy/TapjoyURLConnection
    //   38: dup
    //   39: invokespecial 294	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   42: putstatic 134	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   45: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   48: invokevirtual 300	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   51: putstatic 302	com/tapjoy/TapjoyConnectCore:af	Landroid/content/pm/PackageManager;
    //   54: aload_0
    //   55: new 304	com/tapjoy/TapjoyGpsHelper
    //   58: dup
    //   59: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   62: invokespecial 306	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   65: putfield 308	com/tapjoy/TapjoyConnectCore:ag	Lcom/tapjoy/TapjoyGpsHelper;
    //   68: getstatic 257	com/tapjoy/TapjoyConnectCore:ah	Ljava/util/Hashtable;
    //   71: ifnonnull +13 -> 84
    //   74: new 310	java/util/Hashtable
    //   77: dup
    //   78: invokespecial 311	java/util/Hashtable:<init>	()V
    //   81: putstatic 257	com/tapjoy/TapjoyConnectCore:ah	Ljava/util/Hashtable;
    //   84: invokestatic 313	com/tapjoy/TapjoyConnectCore:k	()V
    //   87: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   90: invokevirtual 317	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   93: ldc_w 319
    //   96: aconst_null
    //   97: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   100: invokevirtual 323	android/content/Context:getPackageName	()Ljava/lang/String;
    //   103: invokevirtual 329	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   106: istore_2
    //   107: new 331	java/util/Properties
    //   110: dup
    //   111: invokespecial 332	java/util/Properties:<init>	()V
    //   114: astore_1
    //   115: aload_1
    //   116: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   119: invokevirtual 317	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   122: iload_2
    //   123: invokevirtual 336	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   126: invokevirtual 340	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   129: aload_1
    //   130: invokestatic 343	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   133: ldc_w 345
    //   136: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   139: ldc -96
    //   141: if_acmpne +7 -> 148
    //   144: aload_0
    //   145: invokespecial 351	com/tapjoy/TapjoyConnectCore:l	()V
    //   148: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   151: invokevirtual 355	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   154: ldc_w 357
    //   157: invokestatic 363	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   160: astore_1
    //   161: aload_1
    //   162: putstatic 162	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   165: aload_1
    //   166: ifnull +12 -> 178
    //   169: getstatic 162	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   172: invokevirtual 368	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   175: putstatic 162	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   178: getstatic 302	com/tapjoy/TapjoyConnectCore:af	Landroid/content/pm/PackageManager;
    //   181: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   184: invokevirtual 323	android/content/Context:getPackageName	()Ljava/lang/String;
    //   187: iconst_0
    //   188: invokevirtual 374	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   191: getfield 379	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   194: putstatic 182	com/tapjoy/TapjoyConnectCore:x	Ljava/lang/String;
    //   197: ldc_w 381
    //   200: putstatic 176	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   203: ldc_w 381
    //   206: putstatic 198	com/tapjoy/TapjoyConnectCore:F	Ljava/lang/String;
    //   209: getstatic 386	android/os/Build:MODEL	Ljava/lang/String;
    //   212: putstatic 172	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   215: getstatic 389	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   218: putstatic 174	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   221: getstatic 394	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   224: putstatic 178	com/tapjoy/TapjoyConnectCore:v	Ljava/lang/String;
    //   227: ldc_w 396
    //   230: putstatic 184	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   233: ldc_w 398
    //   236: putstatic 186	com/tapjoy/TapjoyConnectCore:z	Ljava/lang/String;
    //   239: getstatic 401	android/os/Build$VERSION:SDK_INT	I
    //   242: iconst_3
    //   243: if_icmple +35 -> 278
    //   246: new 403	com/tapjoy/TapjoyDisplayMetricsUtil
    //   249: dup
    //   250: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   253: invokespecial 404	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   256: astore_1
    //   257: aload_1
    //   258: invokevirtual 408	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   261: putstatic 188	com/tapjoy/TapjoyConnectCore:A	I
    //   264: aload_1
    //   265: invokevirtual 412	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   268: putstatic 190	com/tapjoy/TapjoyConnectCore:B	F
    //   271: aload_1
    //   272: invokevirtual 415	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   275: putstatic 192	com/tapjoy/TapjoyConnectCore:C	I
    //   278: ldc_w 417
    //   281: invokestatic 420	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   284: istore_3
    //   285: iload_3
    //   286: ifeq +914 -> 1200
    //   289: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   292: ldc_w 422
    //   295: invokevirtual 426	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   298: checkcast 428	android/net/wifi/WifiManager
    //   301: astore_1
    //   302: aload_1
    //   303: ifnull +42 -> 345
    //   306: aload_1
    //   307: invokevirtual 432	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   310: astore_1
    //   311: aload_1
    //   312: ifnull +33 -> 345
    //   315: aload_1
    //   316: invokevirtual 437	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   319: astore_1
    //   320: aload_1
    //   321: putstatic 168	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   324: aload_1
    //   325: ifnull +20 -> 345
    //   328: getstatic 168	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   331: ldc_w 439
    //   334: ldc -96
    //   336: invokevirtual 443	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   339: invokevirtual 368	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   342: putstatic 168	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   345: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   348: ldc_w 445
    //   351: invokevirtual 426	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   354: checkcast 447	android/telephony/TelephonyManager
    //   357: astore_1
    //   358: aload_1
    //   359: ifnull +239 -> 598
    //   362: aload_1
    //   363: invokevirtual 450	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   366: putstatic 200	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   369: aload_1
    //   370: invokevirtual 453	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   373: putstatic 202	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   376: aload_1
    //   377: invokevirtual 456	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   380: astore 4
    //   382: aload 4
    //   384: ifnull +41 -> 425
    //   387: aload 4
    //   389: invokevirtual 459	java/lang/String:length	()I
    //   392: iconst_5
    //   393: if_icmpeq +13 -> 406
    //   396: aload 4
    //   398: invokevirtual 459	java/lang/String:length	()I
    //   401: bipush 6
    //   403: if_icmpne +22 -> 425
    //   406: aload 4
    //   408: iconst_0
    //   409: iconst_3
    //   410: invokevirtual 463	java/lang/String:substring	(II)Ljava/lang/String;
    //   413: putstatic 204	com/tapjoy/TapjoyConnectCore:I	Ljava/lang/String;
    //   416: aload 4
    //   418: iconst_3
    //   419: invokevirtual 466	java/lang/String:substring	(I)Ljava/lang/String;
    //   422: putstatic 206	com/tapjoy/TapjoyConnectCore:J	Ljava/lang/String;
    //   425: ldc_w 468
    //   428: invokestatic 420	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   431: istore_3
    //   432: iload_3
    //   433: ifeq +989 -> 1422
    //   436: ldc_w 470
    //   439: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   442: ifnull +770 -> 1212
    //   445: ldc_w 470
    //   448: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   451: invokevirtual 459	java/lang/String:length	()I
    //   454: ifle +758 -> 1212
    //   457: ldc_w 470
    //   460: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   463: putstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   466: ldc_w 472
    //   469: new 474	java/lang/StringBuilder
    //   472: dup
    //   473: ldc_w 476
    //   476: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   479: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   482: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   485: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   488: invokestatic 491	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   491: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   494: ifnonnull +772 -> 1266
    //   497: ldc_w 472
    //   500: new 493	com/tapjoy/TapjoyErrorMessage
    //   503: dup
    //   504: getstatic 499	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   507: ldc_w 501
    //   510: invokespecial 504	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   513: invokestatic 507	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   516: iconst_0
    //   517: istore_2
    //   518: ldc_w 472
    //   521: new 474	java/lang/StringBuilder
    //   524: dup
    //   525: ldc_w 509
    //   528: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   531: getstatic 401	android/os/Build$VERSION:SDK_INT	I
    //   534: invokevirtual 512	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   537: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   540: invokestatic 514	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   543: getstatic 401	android/os/Build$VERSION:SDK_INT	I
    //   546: bipush 9
    //   548: if_icmplt +50 -> 598
    //   551: ldc_w 472
    //   554: ldc_w 516
    //   557: invokestatic 491	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   560: aload_0
    //   561: invokevirtual 519	com/tapjoy/TapjoyConnectCore:getSerial	()Ljava/lang/String;
    //   564: astore_1
    //   565: iload_2
    //   566: ifne +7 -> 573
    //   569: aload_1
    //   570: putstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   573: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   576: ifnonnull +764 -> 1340
    //   579: ldc_w 472
    //   582: new 493	com/tapjoy/TapjoyErrorMessage
    //   585: dup
    //   586: getstatic 499	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   589: ldc_w 521
    //   592: invokespecial 504	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   595: invokestatic 507	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   598: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   601: ldc_w 523
    //   604: iconst_0
    //   605: invokevirtual 527	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   608: astore_1
    //   609: aload_1
    //   610: ldc_w 529
    //   613: ldc -96
    //   615: invokeinterface 534 3 0
    //   620: astore 4
    //   622: aload 4
    //   624: putstatic 170	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   627: aload 4
    //   629: ifnull +14 -> 643
    //   632: getstatic 170	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   635: invokevirtual 459	java/lang/String:length	()I
    //   638: istore_2
    //   639: iload_2
    //   640: ifne +61 -> 701
    //   643: new 474	java/lang/StringBuilder
    //   646: dup
    //   647: invokespecial 535	java/lang/StringBuilder:<init>	()V
    //   650: invokestatic 541	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   653: invokevirtual 542	java/util/UUID:toString	()Ljava/lang/String;
    //   656: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   659: invokestatic 548	java/lang/System:currentTimeMillis	()J
    //   662: invokevirtual 551	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   665: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   668: invokestatic 556	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   671: putstatic 170	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   674: aload_1
    //   675: invokeinterface 560 1 0
    //   680: astore_1
    //   681: aload_1
    //   682: ldc_w 529
    //   685: getstatic 170	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   688: invokeinterface 566 3 0
    //   693: pop
    //   694: aload_1
    //   695: invokeinterface 570 1 0
    //   700: pop
    //   701: ldc_w 572
    //   704: ldc_w 574
    //   707: invokestatic 577	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   710: putstatic 196	com/tapjoy/TapjoyConnectCore:E	Z
    //   713: ldc_w 579
    //   716: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   719: ifnull +71 -> 790
    //   722: ldc_w 579
    //   725: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   728: invokevirtual 459	java/lang/String:length	()I
    //   731: ifle +59 -> 790
    //   734: ldc_w 579
    //   737: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   740: putstatic 212	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   743: new 581	java/util/ArrayList
    //   746: dup
    //   747: getstatic 584	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   750: invokestatic 152	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   753: invokespecial 585	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   756: getstatic 212	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   759: invokevirtual 589	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   762: ifne +28 -> 790
    //   765: ldc_w 472
    //   768: new 474	java/lang/StringBuilder
    //   771: dup
    //   772: ldc_w 591
    //   775: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   778: getstatic 212	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   781: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   784: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   787: invokestatic 593	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   790: getstatic 212	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   793: astore_1
    //   794: new 595	android/content/Intent
    //   797: dup
    //   798: ldc_w 597
    //   801: invokespecial 598	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   804: astore 4
    //   806: aload_1
    //   807: invokevirtual 459	java/lang/String:length	()I
    //   810: ifgt +684 -> 1494
    //   813: aload 4
    //   815: ldc_w 600
    //   818: invokestatic 606	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   821: invokevirtual 610	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   824: pop
    //   825: getstatic 302	com/tapjoy/TapjoyConnectCore:af	Landroid/content/pm/PackageManager;
    //   828: aload 4
    //   830: iconst_0
    //   831: invokevirtual 614	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   834: invokeinterface 619 1 0
    //   839: ifle +735 -> 1574
    //   842: iconst_1
    //   843: istore_3
    //   844: iload_3
    //   845: putstatic 228	com/tapjoy/TapjoyConnectCore:T	Z
    //   848: invokestatic 621	com/tapjoy/TapjoyConnectCore:i	()V
    //   851: ldc_w 623
    //   854: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   857: ifnull +24 -> 881
    //   860: ldc_w 623
    //   863: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   866: invokevirtual 459	java/lang/String:length	()I
    //   869: ifle +12 -> 881
    //   872: ldc_w 623
    //   875: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   878: putstatic 250	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   881: ldc_w 625
    //   884: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   887: ifnull +24 -> 911
    //   890: ldc_w 625
    //   893: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   896: invokevirtual 459	java/lang/String:length	()I
    //   899: ifle +12 -> 911
    //   902: ldc_w 625
    //   905: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   908: putstatic 248	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   911: ldc_w 627
    //   914: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   917: ifnull +53 -> 970
    //   920: ldc_w 627
    //   923: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   926: invokevirtual 459	java/lang/String:length	()I
    //   929: ifle +41 -> 970
    //   932: ldc_w 472
    //   935: new 474	java/lang/StringBuilder
    //   938: dup
    //   939: ldc_w 629
    //   942: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   945: ldc_w 627
    //   948: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   951: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   954: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   957: invokestatic 514	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   960: ldc_w 627
    //   963: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   966: aconst_null
    //   967: invokestatic 633	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;Lcom/tapjoy/TJSetUserIDListener;)V
    //   970: ldc_w 635
    //   973: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   976: invokestatic 638	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   979: putstatic 224	com/tapjoy/TapjoyConnectCore:R	Ljava/lang/String;
    //   982: new 474	java/lang/StringBuilder
    //   985: dup
    //   986: ldc_w 476
    //   989: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   992: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   995: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   998: astore 4
    //   1000: ldc_w 470
    //   1003: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1006: ifnull +558 -> 1564
    //   1009: ldc_w 470
    //   1012: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1015: invokevirtual 459	java/lang/String:length	()I
    //   1018: ifle +546 -> 1564
    //   1021: ldc_w 640
    //   1024: astore_1
    //   1025: ldc_w 472
    //   1028: aload 4
    //   1030: aload_1
    //   1031: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1034: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1037: invokestatic 491	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1040: getstatic 257	com/tapjoy/TapjoyConnectCore:ah	Ljava/util/Hashtable;
    //   1043: ifnull +6 -> 1049
    //   1046: invokestatic 642	com/tapjoy/TapjoyConnectCore:j	()V
    //   1049: aload_0
    //   1050: invokevirtual 645	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   1053: aload_0
    //   1054: iconst_1
    //   1055: putfield 284	com/tapjoy/TapjoyConnectCore:ad	Z
    //   1058: return
    //   1059: astore_1
    //   1060: new 271	com/tapjoy/TapjoyException
    //   1063: dup
    //   1064: aload_1
    //   1065: invokevirtual 648	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   1068: invokespecial 649	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   1071: athrow
    //   1072: astore_1
    //   1073: ldc_w 472
    //   1076: new 493	com/tapjoy/TapjoyErrorMessage
    //   1079: dup
    //   1080: getstatic 652	com/tapjoy/TapjoyErrorMessage$ErrorType:INTEGRATION_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1083: aload_1
    //   1084: invokevirtual 653	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   1087: invokespecial 504	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1090: invokestatic 507	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1093: invokestatic 655	com/tapjoy/TapjoyConnectCore:f	()V
    //   1096: getstatic 660	com/tapjoy/internal/et:b	Lcom/tapjoy/internal/et$a;
    //   1099: getstatic 666	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1102: invokevirtual 672	com/tapjoy/internal/et$a:notifyObservers	(Ljava/lang/Object;)V
    //   1105: return
    //   1106: astore_1
    //   1107: ldc_w 472
    //   1110: new 474	java/lang/StringBuilder
    //   1113: dup
    //   1114: ldc_w 674
    //   1117: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1120: aload_1
    //   1121: invokevirtual 675	java/lang/Exception:toString	()Ljava/lang/String;
    //   1124: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1127: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1130: invokestatic 677	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1133: goto -855 -> 278
    //   1136: astore_1
    //   1137: ldc_w 472
    //   1140: new 493	com/tapjoy/TapjoyErrorMessage
    //   1143: dup
    //   1144: getstatic 499	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1147: aload_1
    //   1148: invokevirtual 678	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   1151: invokespecial 504	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1154: invokestatic 507	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1157: invokestatic 655	com/tapjoy/TapjoyConnectCore:f	()V
    //   1160: getstatic 660	com/tapjoy/internal/et:b	Lcom/tapjoy/internal/et$a;
    //   1163: getstatic 666	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1166: invokevirtual 672	com/tapjoy/internal/et$a:notifyObservers	(Ljava/lang/Object;)V
    //   1169: return
    //   1170: astore_1
    //   1171: ldc_w 472
    //   1174: new 474	java/lang/StringBuilder
    //   1177: dup
    //   1178: ldc_w 680
    //   1181: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1184: aload_1
    //   1185: invokevirtual 675	java/lang/Exception:toString	()Ljava/lang/String;
    //   1188: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1191: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1194: invokestatic 677	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1197: goto -852 -> 345
    //   1200: ldc_w 472
    //   1203: ldc_w 682
    //   1206: invokestatic 491	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1209: goto -864 -> 345
    //   1212: aload_1
    //   1213: invokevirtual 685	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1216: putstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1219: goto -753 -> 466
    //   1222: astore_1
    //   1223: ldc_w 472
    //   1226: new 493	com/tapjoy/TapjoyErrorMessage
    //   1229: dup
    //   1230: getstatic 499	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1233: new 474	java/lang/StringBuilder
    //   1236: dup
    //   1237: ldc_w 687
    //   1240: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1243: aload_1
    //   1244: invokevirtual 675	java/lang/Exception:toString	()Ljava/lang/String;
    //   1247: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1250: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1253: invokespecial 504	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1256: invokestatic 507	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1259: aconst_null
    //   1260: putstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1263: goto -665 -> 598
    //   1266: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1269: invokevirtual 459	java/lang/String:length	()I
    //   1272: ifeq +27 -> 1299
    //   1275: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1278: ldc_w 689
    //   1281: invokevirtual 692	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1284: ifne +15 -> 1299
    //   1287: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1290: ldc_w 694
    //   1293: invokevirtual 692	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1296: ifeq +27 -> 1323
    //   1299: ldc_w 472
    //   1302: new 493	com/tapjoy/TapjoyErrorMessage
    //   1305: dup
    //   1306: getstatic 499	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1309: ldc_w 696
    //   1312: invokespecial 504	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1315: invokestatic 507	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1318: iconst_0
    //   1319: istore_2
    //   1320: goto -802 -> 518
    //   1323: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1326: invokestatic 702	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1329: invokevirtual 705	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1332: putstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1335: iconst_1
    //   1336: istore_2
    //   1337: goto -819 -> 518
    //   1340: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1343: invokevirtual 459	java/lang/String:length	()I
    //   1346: ifeq +39 -> 1385
    //   1349: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1352: ldc_w 689
    //   1355: invokevirtual 692	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1358: ifne +27 -> 1385
    //   1361: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1364: ldc_w 694
    //   1367: invokevirtual 692	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1370: ifne +15 -> 1385
    //   1373: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1376: ldc_w 707
    //   1379: invokevirtual 692	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1382: ifeq +25 -> 1407
    //   1385: ldc_w 472
    //   1388: new 493	com/tapjoy/TapjoyErrorMessage
    //   1391: dup
    //   1392: getstatic 499	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1395: ldc_w 709
    //   1398: invokespecial 504	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1401: invokestatic 507	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1404: goto -806 -> 598
    //   1407: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1410: invokestatic 702	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1413: invokevirtual 705	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1416: putstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1419: goto -821 -> 598
    //   1422: ldc_w 472
    //   1425: ldc_w 711
    //   1428: invokestatic 491	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1431: goto -833 -> 598
    //   1434: astore_1
    //   1435: ldc_w 472
    //   1438: new 474	java/lang/StringBuilder
    //   1441: dup
    //   1442: ldc_w 713
    //   1445: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1448: aload_1
    //   1449: invokevirtual 675	java/lang/Exception:toString	()Ljava/lang/String;
    //   1452: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1455: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1458: invokestatic 677	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1461: goto -760 -> 701
    //   1464: astore_1
    //   1465: ldc_w 472
    //   1468: new 474	java/lang/StringBuilder
    //   1471: dup
    //   1472: ldc_w 715
    //   1475: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1478: aload_1
    //   1479: invokevirtual 675	java/lang/Exception:toString	()Ljava/lang/String;
    //   1482: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1485: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1488: invokestatic 677	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1491: goto -778 -> 713
    //   1494: aload_1
    //   1495: ldc_w 717
    //   1498: invokevirtual 692	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1501: ifeq +13 -> 1514
    //   1504: ldc_w 719
    //   1507: invokestatic 721	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1510: istore_3
    //   1511: goto -667 -> 844
    //   1514: aload_1
    //   1515: ldc_w 723
    //   1518: invokevirtual 692	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1521: ifeq +53 -> 1574
    //   1524: ldc_w 725
    //   1527: invokestatic 721	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1530: istore_3
    //   1531: goto -687 -> 844
    //   1534: astore_1
    //   1535: ldc_w 472
    //   1538: new 474	java/lang/StringBuilder
    //   1541: dup
    //   1542: ldc_w 727
    //   1545: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1548: aload_1
    //   1549: invokevirtual 675	java/lang/Exception:toString	()Ljava/lang/String;
    //   1552: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1555: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1558: invokestatic 677	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1561: goto -713 -> 848
    //   1564: ldc -96
    //   1566: astore_1
    //   1567: goto -542 -> 1025
    //   1570: astore_1
    //   1571: goto -1438 -> 133
    //   1574: iconst_0
    //   1575: istore_3
    //   1576: goto -732 -> 844
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1579	0	this	TapjoyConnectCore
    //   0	1579	1	paramContext	Context
    //   106	1231	2	i1	int
    //   284	1292	3	bool	boolean
    //   380	649	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   178	197	1059	android/content/pm/PackageManager$NameNotFoundException
    //   68	84	1072	com/tapjoy/TapjoyIntegrationException
    //   84	115	1072	com/tapjoy/TapjoyIntegrationException
    //   115	133	1072	com/tapjoy/TapjoyIntegrationException
    //   133	148	1072	com/tapjoy/TapjoyIntegrationException
    //   148	165	1072	com/tapjoy/TapjoyIntegrationException
    //   169	178	1072	com/tapjoy/TapjoyIntegrationException
    //   178	197	1072	com/tapjoy/TapjoyIntegrationException
    //   197	239	1072	com/tapjoy/TapjoyIntegrationException
    //   239	278	1072	com/tapjoy/TapjoyIntegrationException
    //   278	285	1072	com/tapjoy/TapjoyIntegrationException
    //   289	302	1072	com/tapjoy/TapjoyIntegrationException
    //   306	311	1072	com/tapjoy/TapjoyIntegrationException
    //   315	324	1072	com/tapjoy/TapjoyIntegrationException
    //   328	345	1072	com/tapjoy/TapjoyIntegrationException
    //   345	358	1072	com/tapjoy/TapjoyIntegrationException
    //   362	382	1072	com/tapjoy/TapjoyIntegrationException
    //   387	406	1072	com/tapjoy/TapjoyIntegrationException
    //   406	425	1072	com/tapjoy/TapjoyIntegrationException
    //   425	432	1072	com/tapjoy/TapjoyIntegrationException
    //   436	466	1072	com/tapjoy/TapjoyIntegrationException
    //   466	516	1072	com/tapjoy/TapjoyIntegrationException
    //   518	565	1072	com/tapjoy/TapjoyIntegrationException
    //   569	573	1072	com/tapjoy/TapjoyIntegrationException
    //   573	598	1072	com/tapjoy/TapjoyIntegrationException
    //   598	627	1072	com/tapjoy/TapjoyIntegrationException
    //   632	639	1072	com/tapjoy/TapjoyIntegrationException
    //   643	701	1072	com/tapjoy/TapjoyIntegrationException
    //   701	713	1072	com/tapjoy/TapjoyIntegrationException
    //   713	790	1072	com/tapjoy/TapjoyIntegrationException
    //   790	842	1072	com/tapjoy/TapjoyIntegrationException
    //   844	848	1072	com/tapjoy/TapjoyIntegrationException
    //   848	881	1072	com/tapjoy/TapjoyIntegrationException
    //   881	911	1072	com/tapjoy/TapjoyIntegrationException
    //   911	970	1072	com/tapjoy/TapjoyIntegrationException
    //   970	1021	1072	com/tapjoy/TapjoyIntegrationException
    //   1025	1049	1072	com/tapjoy/TapjoyIntegrationException
    //   1049	1058	1072	com/tapjoy/TapjoyIntegrationException
    //   1060	1072	1072	com/tapjoy/TapjoyIntegrationException
    //   1107	1133	1072	com/tapjoy/TapjoyIntegrationException
    //   1171	1197	1072	com/tapjoy/TapjoyIntegrationException
    //   1200	1209	1072	com/tapjoy/TapjoyIntegrationException
    //   1212	1219	1072	com/tapjoy/TapjoyIntegrationException
    //   1223	1263	1072	com/tapjoy/TapjoyIntegrationException
    //   1266	1299	1072	com/tapjoy/TapjoyIntegrationException
    //   1299	1318	1072	com/tapjoy/TapjoyIntegrationException
    //   1323	1335	1072	com/tapjoy/TapjoyIntegrationException
    //   1340	1385	1072	com/tapjoy/TapjoyIntegrationException
    //   1385	1404	1072	com/tapjoy/TapjoyIntegrationException
    //   1407	1419	1072	com/tapjoy/TapjoyIntegrationException
    //   1422	1431	1072	com/tapjoy/TapjoyIntegrationException
    //   1435	1461	1072	com/tapjoy/TapjoyIntegrationException
    //   1465	1491	1072	com/tapjoy/TapjoyIntegrationException
    //   1494	1511	1072	com/tapjoy/TapjoyIntegrationException
    //   1514	1531	1072	com/tapjoy/TapjoyIntegrationException
    //   1535	1561	1072	com/tapjoy/TapjoyIntegrationException
    //   239	278	1106	java/lang/Exception
    //   68	84	1136	com/tapjoy/TapjoyException
    //   84	115	1136	com/tapjoy/TapjoyException
    //   115	133	1136	com/tapjoy/TapjoyException
    //   133	148	1136	com/tapjoy/TapjoyException
    //   148	165	1136	com/tapjoy/TapjoyException
    //   169	178	1136	com/tapjoy/TapjoyException
    //   178	197	1136	com/tapjoy/TapjoyException
    //   197	239	1136	com/tapjoy/TapjoyException
    //   239	278	1136	com/tapjoy/TapjoyException
    //   278	285	1136	com/tapjoy/TapjoyException
    //   289	302	1136	com/tapjoy/TapjoyException
    //   306	311	1136	com/tapjoy/TapjoyException
    //   315	324	1136	com/tapjoy/TapjoyException
    //   328	345	1136	com/tapjoy/TapjoyException
    //   345	358	1136	com/tapjoy/TapjoyException
    //   362	382	1136	com/tapjoy/TapjoyException
    //   387	406	1136	com/tapjoy/TapjoyException
    //   406	425	1136	com/tapjoy/TapjoyException
    //   425	432	1136	com/tapjoy/TapjoyException
    //   436	466	1136	com/tapjoy/TapjoyException
    //   466	516	1136	com/tapjoy/TapjoyException
    //   518	565	1136	com/tapjoy/TapjoyException
    //   569	573	1136	com/tapjoy/TapjoyException
    //   573	598	1136	com/tapjoy/TapjoyException
    //   598	627	1136	com/tapjoy/TapjoyException
    //   632	639	1136	com/tapjoy/TapjoyException
    //   643	701	1136	com/tapjoy/TapjoyException
    //   701	713	1136	com/tapjoy/TapjoyException
    //   713	790	1136	com/tapjoy/TapjoyException
    //   790	842	1136	com/tapjoy/TapjoyException
    //   844	848	1136	com/tapjoy/TapjoyException
    //   848	881	1136	com/tapjoy/TapjoyException
    //   881	911	1136	com/tapjoy/TapjoyException
    //   911	970	1136	com/tapjoy/TapjoyException
    //   970	1021	1136	com/tapjoy/TapjoyException
    //   1025	1049	1136	com/tapjoy/TapjoyException
    //   1049	1058	1136	com/tapjoy/TapjoyException
    //   1060	1072	1136	com/tapjoy/TapjoyException
    //   1107	1133	1136	com/tapjoy/TapjoyException
    //   1171	1197	1136	com/tapjoy/TapjoyException
    //   1200	1209	1136	com/tapjoy/TapjoyException
    //   1212	1219	1136	com/tapjoy/TapjoyException
    //   1223	1263	1136	com/tapjoy/TapjoyException
    //   1266	1299	1136	com/tapjoy/TapjoyException
    //   1299	1318	1136	com/tapjoy/TapjoyException
    //   1323	1335	1136	com/tapjoy/TapjoyException
    //   1340	1385	1136	com/tapjoy/TapjoyException
    //   1385	1404	1136	com/tapjoy/TapjoyException
    //   1407	1419	1136	com/tapjoy/TapjoyException
    //   1422	1431	1136	com/tapjoy/TapjoyException
    //   1435	1461	1136	com/tapjoy/TapjoyException
    //   1465	1491	1136	com/tapjoy/TapjoyException
    //   1494	1511	1136	com/tapjoy/TapjoyException
    //   1514	1531	1136	com/tapjoy/TapjoyException
    //   1535	1561	1136	com/tapjoy/TapjoyException
    //   289	302	1170	java/lang/Exception
    //   306	311	1170	java/lang/Exception
    //   315	324	1170	java/lang/Exception
    //   328	345	1170	java/lang/Exception
    //   436	466	1222	java/lang/Exception
    //   466	516	1222	java/lang/Exception
    //   518	565	1222	java/lang/Exception
    //   569	573	1222	java/lang/Exception
    //   573	598	1222	java/lang/Exception
    //   1212	1219	1222	java/lang/Exception
    //   1266	1299	1222	java/lang/Exception
    //   1299	1318	1222	java/lang/Exception
    //   1323	1335	1222	java/lang/Exception
    //   1340	1385	1222	java/lang/Exception
    //   1385	1404	1222	java/lang/Exception
    //   1407	1419	1222	java/lang/Exception
    //   643	701	1434	java/lang/Exception
    //   701	713	1464	java/lang/Exception
    //   790	842	1534	java/lang/Exception
    //   844	848	1534	java/lang/Exception
    //   1494	1511	1534	java/lang/Exception
    //   1514	1531	1534	java/lang/Exception
    //   115	133	1570	java/lang/Exception
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
    //   0: aconst_null
    //   1: astore 7
    //   3: aload_0
    //   4: invokestatic 868	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   7: astore 6
    //   9: aload 6
    //   11: astore 7
    //   13: aload 6
    //   15: invokevirtual 871	com/tapjoy/internal/bs:d	()Ljava/util/Map;
    //   18: astore 10
    //   20: aload 6
    //   22: astore 7
    //   24: aload 10
    //   26: ldc_w 873
    //   29: invokeinterface 876 2 0
    //   34: checkcast 365	java/lang/String
    //   37: invokestatic 880	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   40: astore 8
    //   42: aload 6
    //   44: astore 7
    //   46: aload 10
    //   48: ldc_w 882
    //   51: invokeinterface 876 2 0
    //   56: checkcast 365	java/lang/String
    //   59: invokestatic 880	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   62: astore 13
    //   64: aload 6
    //   66: astore 7
    //   68: aload 10
    //   70: ldc_w 884
    //   73: invokeinterface 876 2 0
    //   78: checkcast 365	java/lang/String
    //   81: invokestatic 880	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   84: astore 14
    //   86: aload 6
    //   88: astore 7
    //   90: aload 10
    //   92: ldc_w 886
    //   95: invokeinterface 876 2 0
    //   100: checkcast 365	java/lang/String
    //   103: invokestatic 880	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   106: astore 15
    //   108: aload 6
    //   110: astore 7
    //   112: aload 10
    //   114: ldc_w 888
    //   117: invokeinterface 876 2 0
    //   122: checkcast 365	java/lang/String
    //   125: invokestatic 880	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   128: astore 12
    //   130: aload 6
    //   132: astore 7
    //   134: aload 10
    //   136: ldc_w 890
    //   139: invokeinterface 876 2 0
    //   144: astore 11
    //   146: aload 6
    //   148: astore 7
    //   150: new 892	com/tapjoy/internal/eq
    //   153: dup
    //   154: aload 14
    //   156: invokespecial 893	com/tapjoy/internal/eq:<init>	(Ljava/lang/String;)V
    //   159: astore 16
    //   161: aload 6
    //   163: astore 7
    //   165: aload 16
    //   167: getfield 896	com/tapjoy/internal/eq:a	Lcom/tapjoy/internal/eq$a;
    //   170: getstatic 901	com/tapjoy/internal/eq$a:RPC_ANALYTICS	Lcom/tapjoy/internal/eq$a;
    //   173: if_acmpeq +44 -> 217
    //   176: aload 6
    //   178: astore 7
    //   180: new 859	java/io/IOException
    //   183: dup
    //   184: ldc_w 903
    //   187: invokespecial 904	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   190: athrow
    //   191: astore 7
    //   193: aload 6
    //   195: astore_0
    //   196: aload 7
    //   198: astore 6
    //   200: ldc_w 472
    //   203: aload 6
    //   205: invokevirtual 905	java/io/IOException:getMessage	()Ljava/lang/String;
    //   208: invokestatic 907	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   211: aload_0
    //   212: invokestatic 912	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   215: iconst_0
    //   216: ireturn
    //   217: aload 6
    //   219: astore 7
    //   221: aload 16
    //   223: getfield 914	com/tapjoy/internal/eq:b	Ljava/lang/String;
    //   226: astore 9
    //   228: aload 6
    //   230: astore 7
    //   232: aload 9
    //   234: bipush 13
    //   236: ldc_w 916
    //   239: iconst_0
    //   240: bipush 11
    //   242: invokevirtual 920	java/lang/String:regionMatches	(ILjava/lang/String;II)Z
    //   245: ifeq +234 -> 479
    //   248: aload 6
    //   250: astore 7
    //   252: new 922	java/lang/StringBuffer
    //   255: dup
    //   256: invokespecial 923	java/lang/StringBuffer:<init>	()V
    //   259: aload 9
    //   261: iconst_0
    //   262: bipush 8
    //   264: invokevirtual 463	java/lang/String:substring	(II)Ljava/lang/String;
    //   267: invokevirtual 926	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   270: aload 9
    //   272: bipush 24
    //   274: bipush 30
    //   276: invokevirtual 463	java/lang/String:substring	(II)Ljava/lang/String;
    //   279: invokevirtual 926	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   282: aload 9
    //   284: bipush 9
    //   286: bipush 13
    //   288: invokevirtual 463	java/lang/String:substring	(II)Ljava/lang/String;
    //   291: invokevirtual 926	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   294: aload 9
    //   296: bipush 30
    //   298: invokevirtual 466	java/lang/String:substring	(I)Ljava/lang/String;
    //   301: invokevirtual 926	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   304: invokevirtual 927	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   307: astore 9
    //   309: aload 6
    //   311: astore 7
    //   313: aload 16
    //   315: getfield 928	com/tapjoy/internal/eq:c	Ljava/lang/String;
    //   318: astore 16
    //   320: aload 8
    //   322: ifnonnull +505 -> 827
    //   325: aload 9
    //   327: astore 8
    //   329: aload 6
    //   331: astore 7
    //   333: invokestatic 933	com/tapjoy/internal/fz:a	()Lcom/tapjoy/internal/fz;
    //   336: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   339: aload 14
    //   341: ldc_w 396
    //   344: ldc_w 935
    //   347: aload 9
    //   349: aload 16
    //   351: invokevirtual 938	com/tapjoy/internal/fz:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   354: aload 6
    //   356: astore 7
    //   358: aload 8
    //   360: putstatic 230	com/tapjoy/TapjoyConnectCore:U	Ljava/lang/String;
    //   363: aload 6
    //   365: astore 7
    //   367: aload 13
    //   369: putstatic 232	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   372: aload 6
    //   374: astore 7
    //   376: aload 14
    //   378: putstatic 234	com/tapjoy/TapjoyConnectCore:W	Ljava/lang/String;
    //   381: aload 6
    //   383: astore 7
    //   385: aload 15
    //   387: putstatic 236	com/tapjoy/TapjoyConnectCore:X	Ljava/lang/String;
    //   390: aload 6
    //   392: astore 7
    //   394: new 581	java/util/ArrayList
    //   397: dup
    //   398: invokespecial 939	java/util/ArrayList:<init>	()V
    //   401: astore 8
    //   403: aload 12
    //   405: ifnull +112 -> 517
    //   408: aload 6
    //   410: astore 7
    //   412: aload 12
    //   414: ldc_w 768
    //   417: invokevirtual 943	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
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
    //   445: invokevirtual 831	java/lang/String:trim	()Ljava/lang/String;
    //   448: astore 12
    //   450: aload 6
    //   452: astore 7
    //   454: aload 12
    //   456: invokevirtual 459	java/lang/String:length	()I
    //   459: ifle +371 -> 830
    //   462: aload 6
    //   464: astore 7
    //   466: aload 8
    //   468: aload 12
    //   470: invokeinterface 944 2 0
    //   475: pop
    //   476: goto +354 -> 830
    //   479: aload 6
    //   481: astore 7
    //   483: new 946	java/lang/IllegalArgumentException
    //   486: dup
    //   487: ldc_w 948
    //   490: invokespecial 949	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   493: athrow
    //   494: astore_0
    //   495: aload 6
    //   497: astore 7
    //   499: ldc_w 472
    //   502: aload_0
    //   503: invokevirtual 950	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   506: invokestatic 907	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   509: aload 6
    //   511: invokestatic 912	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   514: goto -299 -> 215
    //   517: aload 6
    //   519: astore 7
    //   521: aload 8
    //   523: invokeinterface 953 1 0
    //   528: ifne +12 -> 540
    //   531: aload 6
    //   533: astore 7
    //   535: aload 8
    //   537: invokestatic 836	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   540: aload 6
    //   542: astore 7
    //   544: aload 6
    //   546: invokevirtual 956	com/tapjoy/internal/bs:close	()V
    //   549: iload_1
    //   550: ifne +108 -> 658
    //   553: aload 11
    //   555: instanceof 365
    //   558: istore_1
    //   559: iload_1
    //   560: ifeq +112 -> 672
    //   563: aload 11
    //   565: checkcast 365	java/lang/String
    //   568: invokevirtual 831	java/lang/String:trim	()Ljava/lang/String;
    //   571: invokestatic 962	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   574: lstore 4
    //   576: lload 4
    //   578: lconst_0
    //   579: lcmp
    //   580: ifgt +123 -> 703
    //   583: invokestatic 968	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   586: invokevirtual 971	com/tapjoy/TapjoyAppSettings:removeConnectResult	()V
    //   589: invokestatic 289	com/tapjoy/internal/ez:a	()Lcom/tapjoy/internal/ez;
    //   592: astore_0
    //   593: aload 10
    //   595: ldc_w 973
    //   598: invokeinterface 876 2 0
    //   603: astore 6
    //   605: aload 6
    //   607: instanceof 875
    //   610: istore_1
    //   611: iload_1
    //   612: ifeq +114 -> 726
    //   615: aload_0
    //   616: getfield 976	com/tapjoy/internal/ez:a	Lcom/tapjoy/internal/ex;
    //   619: aload 6
    //   621: checkcast 875	java/util/Map
    //   624: invokevirtual 981	com/tapjoy/internal/ex:a	(Ljava/util/Map;)V
    //   627: aload 6
    //   629: invokestatic 986	com/tapjoy/internal/bm:a	(Ljava/lang/Object;)Ljava/lang/String;
    //   632: astore 6
    //   634: aload_0
    //   635: invokevirtual 989	com/tapjoy/internal/ez:c	()Landroid/content/SharedPreferences;
    //   638: invokeinterface 560 1 0
    //   643: ldc_w 973
    //   646: aload 6
    //   648: invokeinterface 566 3 0
    //   653: invokeinterface 992 1 0
    //   658: aconst_null
    //   659: invokestatic 912	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   662: iconst_1
    //   663: ireturn
    //   664: astore 6
    //   666: lconst_0
    //   667: lstore 4
    //   669: goto -93 -> 576
    //   672: aload 11
    //   674: instanceof 994
    //   677: istore_1
    //   678: iload_1
    //   679: ifeq +18 -> 697
    //   682: aload 11
    //   684: checkcast 994	java/lang/Number
    //   687: invokevirtual 997	java/lang/Number:longValue	()J
    //   690: lstore 4
    //   692: goto -116 -> 576
    //   695: astore 6
    //   697: lconst_0
    //   698: lstore 4
    //   700: goto -124 -> 576
    //   703: invokestatic 968	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   706: aload_0
    //   707: invokestatic 999	com/tapjoy/TapjoyConnectCore:s	()Ljava/lang/String;
    //   710: lload 4
    //   712: ldc2_w 1000
    //   715: lmul
    //   716: invokestatic 1005	com/tapjoy/internal/y:b	()J
    //   719: ladd
    //   720: invokevirtual 1009	com/tapjoy/TapjoyAppSettings:saveConnectResultAndParams	(Ljava/lang/String;Ljava/lang/String;J)V
    //   723: goto -134 -> 589
    //   726: aload 6
    //   728: ifnonnull -70 -> 658
    //   731: aload_0
    //   732: getfield 976	com/tapjoy/internal/ez:a	Lcom/tapjoy/internal/ex;
    //   735: aconst_null
    //   736: invokevirtual 981	com/tapjoy/internal/ex:a	(Ljava/util/Map;)V
    //   739: aload_0
    //   740: invokevirtual 989	com/tapjoy/internal/ez:c	()Landroid/content/SharedPreferences;
    //   743: invokeinterface 560 1 0
    //   748: ldc_w 973
    //   751: invokeinterface 1013 2 0
    //   756: invokeinterface 992 1 0
    //   761: goto -103 -> 658
    //   764: astore_0
    //   765: aconst_null
    //   766: astore 6
    //   768: goto -273 -> 495
    //   771: astore_0
    //   772: aconst_null
    //   773: astore 6
    //   775: aload 6
    //   777: invokestatic 912	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
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
    //   1: invokestatic 868	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 1030	com/tapjoy/internal/bs:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 1032	com/tapjoy/internal/bs:s	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 472
    //   25: ldc_w 1034
    //   28: invokestatic 491	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 956	com/tapjoy/internal/bs:close	()V
    //   37: aconst_null
    //   38: invokestatic 912	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 956	com/tapjoy/internal/bs:close	()V
    //   49: aconst_null
    //   50: invokestatic 912	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   53: ldc_w 472
    //   56: new 493	com/tapjoy/TapjoyErrorMessage
    //   59: dup
    //   60: getstatic 499	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   63: ldc_w 1036
    //   66: invokespecial 504	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   69: invokestatic 507	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   72: iconst_0
    //   73: ireturn
    //   74: astore_2
    //   75: aconst_null
    //   76: astore_1
    //   77: aload_1
    //   78: astore_0
    //   79: ldc_w 472
    //   82: aload_2
    //   83: invokevirtual 905	java/io/IOException:getMessage	()Ljava/lang/String;
    //   86: invokestatic 907	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload_1
    //   90: invokestatic 912	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   93: goto -40 -> 53
    //   96: astore_2
    //   97: aconst_null
    //   98: astore_1
    //   99: aload_1
    //   100: astore_0
    //   101: ldc_w 472
    //   104: aload_2
    //   105: invokevirtual 950	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   108: invokestatic 907	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   111: aload_1
    //   112: invokestatic 912	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   115: goto -62 -> 53
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_0
    //   121: aload_0
    //   122: invokestatic 912	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
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
    if (!ct.c(O)) {
      fz.a().a(g, h, "11.10.2", "https://rpc.tapjoy.com/", O, N);
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
    TapjoyUtil.safePut(localHashMap3, "library_revision", "eea8580", true);
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
      TapjoyUtil.safePut(localHashMap3, "display_d", aM);
      TapjoyUtil.safePut(localHashMap3, "display_w", aN);
      TapjoyUtil.safePut(localHashMap3, "display_h", aO);
      TapjoyUtil.safePut(localHashMap3, "country_sim", aP, true);
      TapjoyUtil.safePut(localHashMap3, "timezone", aQ, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "pkg_ver", an, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_rev", ao);
      TapjoyUtil.safePut(localHashMap3, "pkg_data_ver", ap, true);
      TapjoyUtil.safePut(localHashMap3, "installer", aq, true);
      if (ct.c(M)) {
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
  
  public static String getSupportURL(String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = w;
    }
    return getHostURL() + "support_requests/new?currency_id=" + str + "&app_id=" + w + "&udid=" + X + "&language_code=" + Locale.getDefault().getLanguage();
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
    TapjoyUtil.safePut(localHashMap, "installed", ar);
    TapjoyUtil.safePut(localHashMap, "referrer", as, true);
    TapjoyUtil.safePut(localHashMap, "user_level", at);
    TapjoyUtil.safePut(localHashMap, "friend_count", au);
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
    TapjoyUtil.safePut(localHashMap, "fq7", aB);
    TapjoyUtil.safePut(localHashMap, "fq30", aC);
    TapjoyUtil.safePut(localHashMap, "session_total_count", aD);
    TapjoyUtil.safePut(localHashMap, "session_total_length", aE);
    TapjoyUtil.safePut(localHashMap, "session_last_at", aF);
    TapjoyUtil.safePut(localHashMap, "session_last_length", aG);
    TapjoyUtil.safePut(localHashMap, "purchase_currency", aH, true);
    TapjoyUtil.safePut(localHashMap, "purchase_total_count", aI);
    TapjoyUtil.safePut(localHashMap, "purchase_total_price", aJ);
    TapjoyUtil.safePut(localHashMap, "purchase_last_price", aK);
    TapjoyUtil.safePut(localHashMap, "purchase_last_at", aL);
    return localHashMap;
  }
  
  private static void i()
  {
    Object localObject1 = fz.a(g).a(true);
    Object localObject2 = ((ee)localObject1).d;
    ak = ((ed)localObject2).h;
    al = ((ed)localObject2).r;
    am = ((ed)localObject2).s;
    aM = ((ed)localObject2).m;
    aN = ((ed)localObject2).n;
    aO = ((ed)localObject2).o;
    aP = ((ed)localObject2).u;
    aQ = ((ed)localObject2).q;
    localObject2 = ((ee)localObject1).e;
    an = ((dx)localObject2).e;
    ao = ((dx)localObject2).f;
    ap = ((dx)localObject2).g;
    aq = ((dx)localObject2).h;
    aR = ((dx)localObject2).i;
    localObject1 = ((ee)localObject1).f;
    ar = ((ek)localObject1).s;
    as = ((ek)localObject1).t;
    at = ((ek)localObject1).J;
    au = ((ek)localObject1).K;
    av = ((ek)localObject1).L;
    aw = ((ek)localObject1).M;
    ax = ((ek)localObject1).N;
    ay = ((ek)localObject1).O;
    az = ((ek)localObject1).P;
    aA = new HashSet(((ek)localObject1).Q);
    aB = ((ek)localObject1).u;
    aC = ((ek)localObject1).v;
    aD = ((ek)localObject1).x;
    aE = ((ek)localObject1).y;
    aF = ((ek)localObject1).z;
    aG = ((ek)localObject1).A;
    aH = ((ek)localObject1).B;
    aI = ((ek)localObject1).C;
    aJ = ((ek)localObject1).D;
    aK = ((ek)localObject1).F;
    aL = ((ek)localObject1).E;
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
    TapjoyLog.d("TapjoyConnect", "isViewOpen: " + aj.size());
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
  
  /* Error */
  private void l()
  {
    // Byte code:
    //   0: getstatic 302	com/tapjoy/TapjoyConnectCore:af	Landroid/content/pm/PackageManager;
    //   3: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   6: invokevirtual 323	android/content/Context:getPackageName	()Ljava/lang/String;
    //   9: iconst_1
    //   10: invokevirtual 374	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   13: getfield 1565	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
    //   16: invokestatic 152	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   19: astore_2
    //   20: aload_2
    //   21: ifnull +408 -> 429
    //   24: aload_2
    //   25: invokeinterface 746 1 0
    //   30: astore_2
    //   31: aload_2
    //   32: invokeinterface 751 1 0
    //   37: ifeq +392 -> 429
    //   40: aload_2
    //   41: invokeinterface 755 1 0
    //   46: checkcast 1567	android/content/pm/ActivityInfo
    //   49: astore_3
    //   50: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   53: aload_3
    //   54: getfield 1568	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   57: invokevirtual 1569	java/util/Vector:contains	(Ljava/lang/Object;)Z
    //   60: ifeq -29 -> 31
    //   63: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   66: aload_3
    //   67: getfield 1568	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   70: invokevirtual 1572	java/util/Vector:indexOf	(Ljava/lang/Object;)I
    //   73: istore_1
    //   74: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   77: iload_1
    //   78: invokevirtual 1575	java/util/Vector:get	(I)Ljava/lang/Object;
    //   81: checkcast 365	java/lang/String
    //   84: invokestatic 1581	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   87: pop
    //   88: new 140	java/util/Vector
    //   91: dup
    //   92: invokespecial 822	java/util/Vector:<init>	()V
    //   95: astore 4
    //   97: aload_3
    //   98: getfield 1584	android/content/pm/ActivityInfo:configChanges	I
    //   101: sipush 128
    //   104: iand
    //   105: sipush 128
    //   108: if_icmpeq +12 -> 120
    //   111: aload 4
    //   113: ldc_w 1586
    //   116: invokevirtual 834	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   119: pop
    //   120: aload_3
    //   121: getfield 1584	android/content/pm/ActivityInfo:configChanges	I
    //   124: bipush 32
    //   126: iand
    //   127: bipush 32
    //   129: if_icmpeq +12 -> 141
    //   132: aload 4
    //   134: ldc_w 1588
    //   137: invokevirtual 834	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   140: pop
    //   141: aload 4
    //   143: invokevirtual 1589	java/util/Vector:size	()I
    //   146: ifeq +149 -> 295
    //   149: aload 4
    //   151: invokevirtual 1589	java/util/Vector:size	()I
    //   154: iconst_1
    //   155: if_icmpne +95 -> 250
    //   158: new 269	com/tapjoy/TapjoyIntegrationException
    //   161: dup
    //   162: new 474	java/lang/StringBuilder
    //   165: dup
    //   166: invokespecial 535	java/lang/StringBuilder:<init>	()V
    //   169: aload 4
    //   171: invokevirtual 1590	java/util/Vector:toString	()Ljava/lang/String;
    //   174: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: ldc_w 1592
    //   180: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   186: iload_1
    //   187: invokevirtual 1575	java/util/Vector:get	(I)Ljava/lang/Object;
    //   190: checkcast 365	java/lang/String
    //   193: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: invokespecial 1593	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   202: athrow
    //   203: astore_2
    //   204: new 269	com/tapjoy/TapjoyIntegrationException
    //   207: dup
    //   208: new 474	java/lang/StringBuilder
    //   211: dup
    //   212: ldc_w 1595
    //   215: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   218: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   221: iload_1
    //   222: invokevirtual 1575	java/util/Vector:get	(I)Ljava/lang/Object;
    //   225: checkcast 365	java/lang/String
    //   228: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   234: invokespecial 1593	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   237: athrow
    //   238: astore_2
    //   239: new 269	com/tapjoy/TapjoyIntegrationException
    //   242: dup
    //   243: ldc_w 1597
    //   246: invokespecial 1593	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   249: athrow
    //   250: new 269	com/tapjoy/TapjoyIntegrationException
    //   253: dup
    //   254: new 474	java/lang/StringBuilder
    //   257: dup
    //   258: invokespecial 535	java/lang/StringBuilder:<init>	()V
    //   261: aload 4
    //   263: invokevirtual 1590	java/util/Vector:toString	()Ljava/lang/String;
    //   266: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: ldc_w 1599
    //   272: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   278: iload_1
    //   279: invokevirtual 1575	java/util/Vector:get	(I)Ljava/lang/Object;
    //   282: checkcast 365	java/lang/String
    //   285: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   291: invokespecial 1593	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   294: athrow
    //   295: getstatic 401	android/os/Build$VERSION:SDK_INT	I
    //   298: bipush 13
    //   300: if_icmplt +49 -> 349
    //   303: aload_3
    //   304: getfield 1584	android/content/pm/ActivityInfo:configChanges	I
    //   307: sipush 1024
    //   310: iand
    //   311: sipush 1024
    //   314: if_icmpeq +35 -> 349
    //   317: ldc_w 472
    //   320: new 474	java/lang/StringBuilder
    //   323: dup
    //   324: ldc_w 1601
    //   327: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   330: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   333: iload_1
    //   334: invokevirtual 1575	java/util/Vector:get	(I)Ljava/lang/Object;
    //   337: checkcast 365	java/lang/String
    //   340: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   346: invokestatic 593	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   349: getstatic 401	android/os/Build$VERSION:SDK_INT	I
    //   352: bipush 11
    //   354: if_icmplt +64 -> 418
    //   357: aload_3
    //   358: getfield 1568	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   361: ldc_w 1603
    //   364: invokevirtual 692	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   367: ifeq +51 -> 418
    //   370: aload_3
    //   371: getfield 1604	android/content/pm/ActivityInfo:flags	I
    //   374: sipush 512
    //   377: iand
    //   378: sipush 512
    //   381: if_icmpeq +37 -> 418
    //   384: new 269	com/tapjoy/TapjoyIntegrationException
    //   387: dup
    //   388: new 474	java/lang/StringBuilder
    //   391: dup
    //   392: ldc_w 1606
    //   395: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   398: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   401: iload_1
    //   402: invokevirtual 1575	java/util/Vector:get	(I)Ljava/lang/Object;
    //   405: checkcast 365	java/lang/String
    //   408: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   414: invokespecial 1593	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   417: athrow
    //   418: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   421: iload_1
    //   422: invokevirtual 1608	java/util/Vector:remove	(I)Ljava/lang/Object;
    //   425: pop
    //   426: goto -395 -> 31
    //   429: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   432: invokevirtual 1589	java/util/Vector:size	()I
    //   435: ifeq +103 -> 538
    //   438: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   441: invokevirtual 1589	java/util/Vector:size	()I
    //   444: iconst_1
    //   445: if_icmpne +48 -> 493
    //   448: new 269	com/tapjoy/TapjoyIntegrationException
    //   451: dup
    //   452: new 474	java/lang/StringBuilder
    //   455: dup
    //   456: ldc_w 1610
    //   459: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   462: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   465: invokevirtual 1589	java/util/Vector:size	()I
    //   468: invokevirtual 512	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   471: ldc_w 1612
    //   474: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   477: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   480: invokevirtual 1590	java/util/Vector:toString	()Ljava/lang/String;
    //   483: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   489: invokespecial 1593	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   492: athrow
    //   493: new 269	com/tapjoy/TapjoyIntegrationException
    //   496: dup
    //   497: new 474	java/lang/StringBuilder
    //   500: dup
    //   501: ldc_w 1610
    //   504: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   507: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   510: invokevirtual 1589	java/util/Vector:size	()I
    //   513: invokevirtual 512	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   516: ldc_w 1614
    //   519: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   522: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   525: invokevirtual 1590	java/util/Vector:toString	()Ljava/lang/String;
    //   528: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   534: invokespecial 1593	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   537: athrow
    //   538: invokestatic 1616	com/tapjoy/TapjoyConnectCore:m	()V
    //   541: ldc_w 1618
    //   544: invokestatic 1581	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   547: astore_2
    //   548: aload_2
    //   549: ldc_w 1620
    //   552: iconst_1
    //   553: anewarray 1577	java/lang/Class
    //   556: dup
    //   557: iconst_0
    //   558: ldc_w 662
    //   561: aastore
    //   562: invokevirtual 1624	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   565: pop
    //   566: ldc_w 1626
    //   569: invokestatic 1629	com/tapjoy/TapjoyUtil:getResource	(Ljava/lang/String;)Ljava/lang/Object;
    //   572: checkcast 365	java/lang/String
    //   575: astore_2
    //   576: aload_2
    //   577: ifnonnull +178 -> 755
    //   580: ldc_w 1631
    //   583: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   586: invokestatic 1635	com/tapjoy/TapjoyUtil:copyTextFromJarIntoString	(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   589: astore_2
    //   590: aload_2
    //   591: ifnonnull +161 -> 752
    //   594: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   597: invokevirtual 317	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   600: ldc_w 1637
    //   603: ldc_w 1639
    //   606: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   609: invokevirtual 323	android/content/Context:getPackageName	()Ljava/lang/String;
    //   612: invokevirtual 329	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   615: istore_1
    //   616: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   619: invokevirtual 317	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   622: iload_1
    //   623: invokevirtual 336	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   626: astore_3
    //   627: aload_3
    //   628: invokevirtual 1644	java/io/InputStream:available	()I
    //   631: newarray byte
    //   633: astore 4
    //   635: aload_3
    //   636: aload 4
    //   638: invokevirtual 1648	java/io/InputStream:read	([B)I
    //   641: pop
    //   642: new 365	java/lang/String
    //   645: dup
    //   646: aload 4
    //   648: invokespecial 1651	java/lang/String:<init>	([B)V
    //   651: astore_3
    //   652: ldc_w 1626
    //   655: aload_3
    //   656: invokestatic 1655	com/tapjoy/TapjoyUtil:setResource	(Ljava/lang/String;Ljava/lang/Object;)V
    //   659: aload_3
    //   660: astore_2
    //   661: aload_2
    //   662: ifnonnull +38 -> 700
    //   665: new 269	com/tapjoy/TapjoyIntegrationException
    //   668: dup
    //   669: ldc_w 1657
    //   672: invokespecial 1593	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   675: athrow
    //   676: astore_2
    //   677: new 269	com/tapjoy/TapjoyIntegrationException
    //   680: dup
    //   681: ldc_w 1659
    //   684: invokespecial 1593	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   687: athrow
    //   688: astore_2
    //   689: new 269	com/tapjoy/TapjoyIntegrationException
    //   692: dup
    //   693: ldc_w 1661
    //   696: invokespecial 1593	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   699: athrow
    //   700: ldc_w 625
    //   703: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   706: ifnull +28 -> 734
    //   709: ldc_w 625
    //   712: invokestatic 349	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   715: ldc_w 840
    //   718: invokevirtual 692	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   721: ifeq +13 -> 734
    //   724: ldc_w 472
    //   727: ldc_w 1663
    //   730: invokestatic 514	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   733: return
    //   734: aload_0
    //   735: getfield 308	com/tapjoy/TapjoyConnectCore:ag	Lcom/tapjoy/TapjoyGpsHelper;
    //   738: invokevirtual 1666	com/tapjoy/TapjoyGpsHelper:checkGooglePlayIntegration	()V
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
          new TapjoyConnectCore.3(this, (TJMediationNetwork)((Iterator)localObject).next()).run();
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
    eq localEq;
    try
    {
      localEq = new eq(paramString);
      if (localEq.a != eq.a.SDK_ANDROID) {
        throw new IllegalArgumentException("The given API key was not for Android.");
      }
    }
    catch (IllegalArgumentException paramContext)
    {
      throw new TapjoyIntegrationException(paramContext.getMessage());
    }
    h = paramString;
    w = localEq.b;
    N = localEq.c;
    O = localEq.d;
    fz.a(paramContext).j = paramString;
    if (paramHashtable != null)
    {
      ah.putAll(paramHashtable);
      ez.b().a(paramHashtable);
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
    new Thread(new TapjoyConnectCore.2()).start();
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
    TapjoyLog.d("TapjoyConnect", "viewDidClose: " + paramString);
    aj.remove(paramString);
    et.e.notifyObservers();
  }
  
  public static void viewWillOpen(String paramString, int paramInt)
  {
    TapjoyLog.d("TapjoyConnect", "viewWillOpen: " + paramString);
    aj.put(paramString, Integer.valueOf(paramInt));
  }
  
  public void actionComplete(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "actionComplete: " + paramString);
    Map localMap = g();
    TapjoyUtil.safePut(localMap, "app_id", paramString, true);
    localMap.putAll(getTimeStampAndVerifierParams());
    TapjoyLog.d("TapjoyConnect", "PPA URL parameters: " + localMap);
    new Thread(new TapjoyConnectCore.PPAThread(this, localMap)).start();
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
        et.a.notifyObservers();
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
            et.a.notifyObservers();
          }
          et.b.notifyObservers(Boolean.TRUE);
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
          et.b.notifyObservers(Boolean.FALSE);
        }
      }
      if (i1 == 0) {
        f();
      }
      et.b.notifyObservers(Boolean.FALSE);
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
    this.aa.schedule(new TapjoyConnectCore.a(this, (byte)0), 10000L, 10000L);
  }
  
  public void fetchAdvertisingID()
  {
    new Thread(new TapjoyConnectCore.1(this)).start();
  }
  
  public float getCurrencyMultiplier()
  {
    return S;
  }
  
  /* Error */
  public String getSerial()
  {
    // Byte code:
    //   0: ldc_w 1891
    //   3: invokestatic 1581	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 1893
    //   9: invokevirtual 1897	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 1902	java/lang/reflect/Field:isAccessible	()Z
    //   17: ifne +8 -> 25
    //   20: aload_1
    //   21: iconst_1
    //   22: invokevirtual 1905	java/lang/reflect/Field:setAccessible	(Z)V
    //   25: aload_1
    //   26: ldc_w 383
    //   29: invokevirtual 1906	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: invokevirtual 1239	java/lang/Object:toString	()Ljava/lang/String;
    //   35: astore_1
    //   36: ldc_w 472
    //   39: new 474	java/lang/StringBuilder
    //   42: dup
    //   43: ldc_w 1908
    //   46: invokespecial 479	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   49: aload_1
    //   50: invokevirtual 483	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 486	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 491	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload_1
    //   60: areturn
    //   61: astore_2
    //   62: aconst_null
    //   63: astore_1
    //   64: ldc_w 472
    //   67: aload_2
    //   68: invokevirtual 675	java/lang/Exception:toString	()Ljava/lang/String;
    //   71: invokestatic 677	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
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
}
