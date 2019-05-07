package com.tapjoy;

import android.content.Context;
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
    //   1: invokespecial 279	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 281	com/tapjoy/TapjoyConnectCore:Z	J
    //   9: aload_0
    //   10: iconst_0
    //   11: putfield 283	com/tapjoy/TapjoyConnectCore:aa	Z
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 285	com/tapjoy/TapjoyConnectCore:ac	Z
    //   19: aload_1
    //   20: putstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   23: invokestatic 290	com/tapjoy/internal/fd:a	()Lcom/tapjoy/internal/fd;
    //   26: aload_1
    //   27: invokevirtual 292	com/tapjoy/internal/fd:a	(Landroid/content/Context;)V
    //   30: new 294	com/tapjoy/TapjoyURLConnection
    //   33: dup
    //   34: invokespecial 295	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   37: putstatic 137	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   40: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   43: invokevirtual 301	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   46: putstatic 303	com/tapjoy/TapjoyConnectCore:ae	Landroid/content/pm/PackageManager;
    //   49: aload_0
    //   50: new 305	com/tapjoy/TapjoyGpsHelper
    //   53: dup
    //   54: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   57: invokespecial 307	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   60: putfield 309	com/tapjoy/TapjoyConnectCore:af	Lcom/tapjoy/TapjoyGpsHelper;
    //   63: getstatic 260	com/tapjoy/TapjoyConnectCore:ag	Ljava/util/Hashtable;
    //   66: ifnonnull +13 -> 79
    //   69: new 311	java/util/Hashtable
    //   72: dup
    //   73: invokespecial 312	java/util/Hashtable:<init>	()V
    //   76: putstatic 260	com/tapjoy/TapjoyConnectCore:ag	Ljava/util/Hashtable;
    //   79: invokestatic 314	com/tapjoy/TapjoyConnectCore:i	()V
    //   82: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   85: invokevirtual 318	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   88: ldc_w 320
    //   91: aconst_null
    //   92: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   95: invokevirtual 324	android/content/Context:getPackageName	()Ljava/lang/String;
    //   98: invokevirtual 330	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   101: istore_2
    //   102: new 332	java/util/Properties
    //   105: dup
    //   106: invokespecial 333	java/util/Properties:<init>	()V
    //   109: astore_1
    //   110: aload_1
    //   111: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   114: invokevirtual 318	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   117: iload_2
    //   118: invokevirtual 337	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   121: invokevirtual 341	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   124: aload_1
    //   125: invokestatic 344	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   128: ldc_w 346
    //   131: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   134: ldc -93
    //   136: if_acmpne +7 -> 143
    //   139: aload_0
    //   140: invokespecial 352	com/tapjoy/TapjoyConnectCore:j	()V
    //   143: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   146: invokevirtual 356	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   149: ldc_w 358
    //   152: invokestatic 364	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   155: astore_1
    //   156: aload_1
    //   157: putstatic 165	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   160: aload_1
    //   161: ifnull +12 -> 173
    //   164: getstatic 165	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   167: invokevirtual 369	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   170: putstatic 165	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   173: getstatic 303	com/tapjoy/TapjoyConnectCore:ae	Landroid/content/pm/PackageManager;
    //   176: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   179: invokevirtual 324	android/content/Context:getPackageName	()Ljava/lang/String;
    //   182: iconst_0
    //   183: invokevirtual 375	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   186: getfield 380	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   189: putstatic 185	com/tapjoy/TapjoyConnectCore:x	Ljava/lang/String;
    //   192: ldc_w 382
    //   195: putstatic 179	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   198: ldc_w 382
    //   201: putstatic 201	com/tapjoy/TapjoyConnectCore:F	Ljava/lang/String;
    //   204: getstatic 387	android/os/Build:MODEL	Ljava/lang/String;
    //   207: putstatic 175	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   210: getstatic 390	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   213: putstatic 177	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   216: getstatic 395	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   219: putstatic 181	com/tapjoy/TapjoyConnectCore:v	Ljava/lang/String;
    //   222: ldc_w 397
    //   225: putstatic 187	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   228: ldc_w 399
    //   231: putstatic 189	com/tapjoy/TapjoyConnectCore:z	Ljava/lang/String;
    //   234: getstatic 402	android/os/Build$VERSION:SDK_INT	I
    //   237: iconst_3
    //   238: if_icmple +35 -> 273
    //   241: new 404	com/tapjoy/TapjoyDisplayMetricsUtil
    //   244: dup
    //   245: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   248: invokespecial 405	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   251: astore_1
    //   252: aload_1
    //   253: invokevirtual 409	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   256: putstatic 191	com/tapjoy/TapjoyConnectCore:A	I
    //   259: aload_1
    //   260: invokevirtual 413	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   263: putstatic 193	com/tapjoy/TapjoyConnectCore:B	F
    //   266: aload_1
    //   267: invokevirtual 416	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   270: putstatic 195	com/tapjoy/TapjoyConnectCore:C	I
    //   273: ldc_w 418
    //   276: invokestatic 421	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   279: istore_3
    //   280: iload_3
    //   281: ifeq +914 -> 1195
    //   284: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   287: ldc_w 423
    //   290: invokevirtual 427	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   293: checkcast 429	android/net/wifi/WifiManager
    //   296: astore_1
    //   297: aload_1
    //   298: ifnull +42 -> 340
    //   301: aload_1
    //   302: invokevirtual 433	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   305: astore_1
    //   306: aload_1
    //   307: ifnull +33 -> 340
    //   310: aload_1
    //   311: invokevirtual 438	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   314: astore_1
    //   315: aload_1
    //   316: putstatic 171	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   319: aload_1
    //   320: ifnull +20 -> 340
    //   323: getstatic 171	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   326: ldc_w 440
    //   329: ldc -93
    //   331: invokevirtual 444	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   334: invokevirtual 369	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   337: putstatic 171	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   340: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   343: ldc_w 446
    //   346: invokevirtual 427	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   349: checkcast 448	android/telephony/TelephonyManager
    //   352: astore_1
    //   353: aload_1
    //   354: ifnull +239 -> 593
    //   357: aload_1
    //   358: invokevirtual 451	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   361: putstatic 203	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   364: aload_1
    //   365: invokevirtual 454	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   368: putstatic 205	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   371: aload_1
    //   372: invokevirtual 457	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   375: astore 4
    //   377: aload 4
    //   379: ifnull +41 -> 420
    //   382: aload 4
    //   384: invokevirtual 460	java/lang/String:length	()I
    //   387: iconst_5
    //   388: if_icmpeq +13 -> 401
    //   391: aload 4
    //   393: invokevirtual 460	java/lang/String:length	()I
    //   396: bipush 6
    //   398: if_icmpne +22 -> 420
    //   401: aload 4
    //   403: iconst_0
    //   404: iconst_3
    //   405: invokevirtual 464	java/lang/String:substring	(II)Ljava/lang/String;
    //   408: putstatic 207	com/tapjoy/TapjoyConnectCore:I	Ljava/lang/String;
    //   411: aload 4
    //   413: iconst_3
    //   414: invokevirtual 467	java/lang/String:substring	(I)Ljava/lang/String;
    //   417: putstatic 209	com/tapjoy/TapjoyConnectCore:J	Ljava/lang/String;
    //   420: ldc_w 469
    //   423: invokestatic 421	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   426: istore_3
    //   427: iload_3
    //   428: ifeq +989 -> 1417
    //   431: ldc_w 471
    //   434: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   437: ifnull +770 -> 1207
    //   440: ldc_w 471
    //   443: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   446: invokevirtual 460	java/lang/String:length	()I
    //   449: ifle +758 -> 1207
    //   452: ldc_w 471
    //   455: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   458: putstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   461: ldc_w 473
    //   464: new 475	java/lang/StringBuilder
    //   467: dup
    //   468: ldc_w 477
    //   471: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   474: getstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   477: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   480: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   483: invokestatic 492	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   486: getstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   489: ifnonnull +772 -> 1261
    //   492: ldc_w 473
    //   495: new 494	com/tapjoy/TapjoyErrorMessage
    //   498: dup
    //   499: getstatic 500	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   502: ldc_w 502
    //   505: invokespecial 505	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   508: invokestatic 508	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   511: iconst_0
    //   512: istore_2
    //   513: ldc_w 473
    //   516: new 475	java/lang/StringBuilder
    //   519: dup
    //   520: ldc_w 510
    //   523: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   526: getstatic 402	android/os/Build$VERSION:SDK_INT	I
    //   529: invokevirtual 513	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   532: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   535: invokestatic 515	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   538: getstatic 402	android/os/Build$VERSION:SDK_INT	I
    //   541: bipush 9
    //   543: if_icmplt +50 -> 593
    //   546: ldc_w 473
    //   549: ldc_w 517
    //   552: invokestatic 492	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   555: aload_0
    //   556: invokevirtual 520	com/tapjoy/TapjoyConnectCore:getSerial	()Ljava/lang/String;
    //   559: astore_1
    //   560: iload_2
    //   561: ifne +7 -> 568
    //   564: aload_1
    //   565: putstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   568: getstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   571: ifnonnull +764 -> 1335
    //   574: ldc_w 473
    //   577: new 494	com/tapjoy/TapjoyErrorMessage
    //   580: dup
    //   581: getstatic 500	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   584: ldc_w 522
    //   587: invokespecial 505	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   590: invokestatic 508	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   593: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   596: ldc_w 524
    //   599: iconst_0
    //   600: invokevirtual 528	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   603: astore_1
    //   604: aload_1
    //   605: ldc_w 530
    //   608: ldc -93
    //   610: invokeinterface 535 3 0
    //   615: astore 4
    //   617: aload 4
    //   619: putstatic 173	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   622: aload 4
    //   624: ifnull +14 -> 638
    //   627: getstatic 173	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   630: invokevirtual 460	java/lang/String:length	()I
    //   633: istore_2
    //   634: iload_2
    //   635: ifne +61 -> 696
    //   638: new 475	java/lang/StringBuilder
    //   641: dup
    //   642: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   645: invokestatic 542	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   648: invokevirtual 543	java/util/UUID:toString	()Ljava/lang/String;
    //   651: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   654: invokestatic 549	java/lang/System:currentTimeMillis	()J
    //   657: invokevirtual 552	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   660: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   663: invokestatic 557	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   666: putstatic 173	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   669: aload_1
    //   670: invokeinterface 561 1 0
    //   675: astore_1
    //   676: aload_1
    //   677: ldc_w 530
    //   680: getstatic 173	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   683: invokeinterface 567 3 0
    //   688: pop
    //   689: aload_1
    //   690: invokeinterface 571 1 0
    //   695: pop
    //   696: ldc_w 573
    //   699: ldc_w 575
    //   702: invokestatic 578	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   705: putstatic 199	com/tapjoy/TapjoyConnectCore:E	Z
    //   708: ldc_w 580
    //   711: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   714: ifnull +71 -> 785
    //   717: ldc_w 580
    //   720: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   723: invokevirtual 460	java/lang/String:length	()I
    //   726: ifle +59 -> 785
    //   729: ldc_w 580
    //   732: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   735: putstatic 215	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   738: new 582	java/util/ArrayList
    //   741: dup
    //   742: getstatic 585	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   745: invokestatic 155	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   748: invokespecial 586	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   751: getstatic 215	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   754: invokevirtual 590	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   757: ifne +28 -> 785
    //   760: ldc_w 473
    //   763: new 475	java/lang/StringBuilder
    //   766: dup
    //   767: ldc_w 592
    //   770: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   773: getstatic 215	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   776: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   779: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   782: invokestatic 594	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   785: getstatic 215	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   788: astore_1
    //   789: new 596	android/content/Intent
    //   792: dup
    //   793: ldc_w 598
    //   796: invokespecial 599	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   799: astore 4
    //   801: aload_1
    //   802: invokevirtual 460	java/lang/String:length	()I
    //   805: ifgt +684 -> 1489
    //   808: aload 4
    //   810: ldc_w 601
    //   813: invokestatic 607	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   816: invokevirtual 611	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   819: pop
    //   820: getstatic 303	com/tapjoy/TapjoyConnectCore:ae	Landroid/content/pm/PackageManager;
    //   823: aload 4
    //   825: iconst_0
    //   826: invokevirtual 615	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   829: invokeinterface 620 1 0
    //   834: ifle +735 -> 1569
    //   837: iconst_1
    //   838: istore_3
    //   839: iload_3
    //   840: putstatic 231	com/tapjoy/TapjoyConnectCore:T	Z
    //   843: invokestatic 622	com/tapjoy/TapjoyConnectCore:g	()V
    //   846: ldc_w 624
    //   849: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   852: ifnull +24 -> 876
    //   855: ldc_w 624
    //   858: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   861: invokevirtual 460	java/lang/String:length	()I
    //   864: ifle +12 -> 876
    //   867: ldc_w 624
    //   870: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   873: putstatic 253	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   876: ldc_w 626
    //   879: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   882: ifnull +24 -> 906
    //   885: ldc_w 626
    //   888: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   891: invokevirtual 460	java/lang/String:length	()I
    //   894: ifle +12 -> 906
    //   897: ldc_w 626
    //   900: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   903: putstatic 251	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   906: ldc_w 628
    //   909: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   912: ifnull +53 -> 965
    //   915: ldc_w 628
    //   918: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   921: invokevirtual 460	java/lang/String:length	()I
    //   924: ifle +41 -> 965
    //   927: ldc_w 473
    //   930: new 475	java/lang/StringBuilder
    //   933: dup
    //   934: ldc_w 630
    //   937: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   940: ldc_w 628
    //   943: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   946: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   949: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   952: invokestatic 515	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   955: ldc_w 628
    //   958: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   961: aconst_null
    //   962: invokestatic 634	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;Lcom/tapjoy/TJSetUserIDListener;)V
    //   965: ldc_w 636
    //   968: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   971: invokestatic 639	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   974: putstatic 227	com/tapjoy/TapjoyConnectCore:R	Ljava/lang/String;
    //   977: new 475	java/lang/StringBuilder
    //   980: dup
    //   981: ldc_w 477
    //   984: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   987: getstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   990: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   993: astore 4
    //   995: ldc_w 471
    //   998: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1001: ifnull +558 -> 1559
    //   1004: ldc_w 471
    //   1007: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1010: invokevirtual 460	java/lang/String:length	()I
    //   1013: ifle +546 -> 1559
    //   1016: ldc_w 641
    //   1019: astore_1
    //   1020: ldc_w 473
    //   1023: aload 4
    //   1025: aload_1
    //   1026: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1029: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1032: invokestatic 492	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1035: getstatic 260	com/tapjoy/TapjoyConnectCore:ag	Ljava/util/Hashtable;
    //   1038: ifnull +6 -> 1044
    //   1041: invokestatic 643	com/tapjoy/TapjoyConnectCore:h	()V
    //   1044: aload_0
    //   1045: invokevirtual 646	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   1048: aload_0
    //   1049: iconst_1
    //   1050: putfield 285	com/tapjoy/TapjoyConnectCore:ac	Z
    //   1053: return
    //   1054: astore_1
    //   1055: new 274	com/tapjoy/TapjoyException
    //   1058: dup
    //   1059: aload_1
    //   1060: invokevirtual 649	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   1063: invokespecial 650	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   1066: athrow
    //   1067: astore_1
    //   1068: ldc_w 473
    //   1071: new 494	com/tapjoy/TapjoyErrorMessage
    //   1074: dup
    //   1075: getstatic 653	com/tapjoy/TapjoyErrorMessage$ErrorType:INTEGRATION_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1078: aload_1
    //   1079: invokevirtual 654	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   1082: invokespecial 505	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1085: invokestatic 508	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1088: invokestatic 656	com/tapjoy/TapjoyConnectCore:d	()V
    //   1091: getstatic 661	com/tapjoy/internal/ev:b	Lcom/tapjoy/internal/ev$a;
    //   1094: getstatic 667	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1097: invokevirtual 673	com/tapjoy/internal/ev$a:notifyObservers	(Ljava/lang/Object;)V
    //   1100: return
    //   1101: astore_1
    //   1102: ldc_w 473
    //   1105: new 475	java/lang/StringBuilder
    //   1108: dup
    //   1109: ldc_w 675
    //   1112: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1115: aload_1
    //   1116: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   1119: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1122: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1125: invokestatic 678	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1128: goto -855 -> 273
    //   1131: astore_1
    //   1132: ldc_w 473
    //   1135: new 494	com/tapjoy/TapjoyErrorMessage
    //   1138: dup
    //   1139: getstatic 500	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1142: aload_1
    //   1143: invokevirtual 679	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   1146: invokespecial 505	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1149: invokestatic 508	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1152: invokestatic 656	com/tapjoy/TapjoyConnectCore:d	()V
    //   1155: getstatic 661	com/tapjoy/internal/ev:b	Lcom/tapjoy/internal/ev$a;
    //   1158: getstatic 667	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1161: invokevirtual 673	com/tapjoy/internal/ev$a:notifyObservers	(Ljava/lang/Object;)V
    //   1164: return
    //   1165: astore_1
    //   1166: ldc_w 473
    //   1169: new 475	java/lang/StringBuilder
    //   1172: dup
    //   1173: ldc_w 681
    //   1176: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1179: aload_1
    //   1180: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   1183: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1186: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1189: invokestatic 678	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1192: goto -852 -> 340
    //   1195: ldc_w 473
    //   1198: ldc_w 683
    //   1201: invokestatic 492	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1204: goto -864 -> 340
    //   1207: aload_1
    //   1208: invokevirtual 686	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1211: putstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1214: goto -753 -> 461
    //   1217: astore_1
    //   1218: ldc_w 473
    //   1221: new 494	com/tapjoy/TapjoyErrorMessage
    //   1224: dup
    //   1225: getstatic 500	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1228: new 475	java/lang/StringBuilder
    //   1231: dup
    //   1232: ldc_w 688
    //   1235: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1238: aload_1
    //   1239: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   1242: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1245: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1248: invokespecial 505	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1251: invokestatic 508	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1254: aconst_null
    //   1255: putstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1258: goto -665 -> 593
    //   1261: getstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1264: invokevirtual 460	java/lang/String:length	()I
    //   1267: ifeq +27 -> 1294
    //   1270: getstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1273: ldc_w 690
    //   1276: invokevirtual 693	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1279: ifne +15 -> 1294
    //   1282: getstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1285: ldc_w 695
    //   1288: invokevirtual 693	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1291: ifeq +27 -> 1318
    //   1294: ldc_w 473
    //   1297: new 494	com/tapjoy/TapjoyErrorMessage
    //   1300: dup
    //   1301: getstatic 500	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1304: ldc_w 697
    //   1307: invokespecial 505	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1310: invokestatic 508	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1313: iconst_0
    //   1314: istore_2
    //   1315: goto -802 -> 513
    //   1318: getstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1321: invokestatic 703	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1324: invokevirtual 706	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1327: putstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1330: iconst_1
    //   1331: istore_2
    //   1332: goto -819 -> 513
    //   1335: getstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1338: invokevirtual 460	java/lang/String:length	()I
    //   1341: ifeq +39 -> 1380
    //   1344: getstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1347: ldc_w 690
    //   1350: invokevirtual 693	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1353: ifne +27 -> 1380
    //   1356: getstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1359: ldc_w 695
    //   1362: invokevirtual 693	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1365: ifne +15 -> 1380
    //   1368: getstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1371: ldc_w 708
    //   1374: invokevirtual 693	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1377: ifeq +25 -> 1402
    //   1380: ldc_w 473
    //   1383: new 494	com/tapjoy/TapjoyErrorMessage
    //   1386: dup
    //   1387: getstatic 500	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1390: ldc_w 710
    //   1393: invokespecial 505	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1396: invokestatic 508	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1399: goto -806 -> 593
    //   1402: getstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1405: invokestatic 703	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1408: invokevirtual 706	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1411: putstatic 169	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1414: goto -821 -> 593
    //   1417: ldc_w 473
    //   1420: ldc_w 712
    //   1423: invokestatic 492	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1426: goto -833 -> 593
    //   1429: astore_1
    //   1430: ldc_w 473
    //   1433: new 475	java/lang/StringBuilder
    //   1436: dup
    //   1437: ldc_w 714
    //   1440: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1443: aload_1
    //   1444: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   1447: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1450: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1453: invokestatic 678	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1456: goto -760 -> 696
    //   1459: astore_1
    //   1460: ldc_w 473
    //   1463: new 475	java/lang/StringBuilder
    //   1466: dup
    //   1467: ldc_w 716
    //   1470: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1473: aload_1
    //   1474: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   1477: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1480: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1483: invokestatic 678	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1486: goto -778 -> 708
    //   1489: aload_1
    //   1490: ldc_w 718
    //   1493: invokevirtual 693	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1496: ifeq +13 -> 1509
    //   1499: ldc_w 720
    //   1502: invokestatic 722	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1505: istore_3
    //   1506: goto -667 -> 839
    //   1509: aload_1
    //   1510: ldc_w 724
    //   1513: invokevirtual 693	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1516: ifeq +53 -> 1569
    //   1519: ldc_w 726
    //   1522: invokestatic 722	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1525: istore_3
    //   1526: goto -687 -> 839
    //   1529: astore_1
    //   1530: ldc_w 473
    //   1533: new 475	java/lang/StringBuilder
    //   1536: dup
    //   1537: ldc_w 728
    //   1540: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1543: aload_1
    //   1544: invokevirtual 676	java/lang/Exception:toString	()Ljava/lang/String;
    //   1547: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1550: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1553: invokestatic 678	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1556: goto -713 -> 843
    //   1559: ldc -93
    //   1561: astore_1
    //   1562: goto -542 -> 1020
    //   1565: astore_1
    //   1566: goto -1438 -> 128
    //   1569: iconst_0
    //   1570: istore_3
    //   1571: goto -732 -> 839
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1574	0	this	TapjoyConnectCore
    //   0	1574	1	paramContext	Context
    //   101	1231	2	i1	int
    //   279	1292	3	bool	boolean
    //   375	649	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   173	192	1054	android/content/pm/PackageManager$NameNotFoundException
    //   63	79	1067	com/tapjoy/TapjoyIntegrationException
    //   79	110	1067	com/tapjoy/TapjoyIntegrationException
    //   110	128	1067	com/tapjoy/TapjoyIntegrationException
    //   128	143	1067	com/tapjoy/TapjoyIntegrationException
    //   143	160	1067	com/tapjoy/TapjoyIntegrationException
    //   164	173	1067	com/tapjoy/TapjoyIntegrationException
    //   173	192	1067	com/tapjoy/TapjoyIntegrationException
    //   192	234	1067	com/tapjoy/TapjoyIntegrationException
    //   234	273	1067	com/tapjoy/TapjoyIntegrationException
    //   273	280	1067	com/tapjoy/TapjoyIntegrationException
    //   284	297	1067	com/tapjoy/TapjoyIntegrationException
    //   301	306	1067	com/tapjoy/TapjoyIntegrationException
    //   310	319	1067	com/tapjoy/TapjoyIntegrationException
    //   323	340	1067	com/tapjoy/TapjoyIntegrationException
    //   340	353	1067	com/tapjoy/TapjoyIntegrationException
    //   357	377	1067	com/tapjoy/TapjoyIntegrationException
    //   382	401	1067	com/tapjoy/TapjoyIntegrationException
    //   401	420	1067	com/tapjoy/TapjoyIntegrationException
    //   420	427	1067	com/tapjoy/TapjoyIntegrationException
    //   431	461	1067	com/tapjoy/TapjoyIntegrationException
    //   461	511	1067	com/tapjoy/TapjoyIntegrationException
    //   513	560	1067	com/tapjoy/TapjoyIntegrationException
    //   564	568	1067	com/tapjoy/TapjoyIntegrationException
    //   568	593	1067	com/tapjoy/TapjoyIntegrationException
    //   593	622	1067	com/tapjoy/TapjoyIntegrationException
    //   627	634	1067	com/tapjoy/TapjoyIntegrationException
    //   638	696	1067	com/tapjoy/TapjoyIntegrationException
    //   696	708	1067	com/tapjoy/TapjoyIntegrationException
    //   708	785	1067	com/tapjoy/TapjoyIntegrationException
    //   785	837	1067	com/tapjoy/TapjoyIntegrationException
    //   839	843	1067	com/tapjoy/TapjoyIntegrationException
    //   843	876	1067	com/tapjoy/TapjoyIntegrationException
    //   876	906	1067	com/tapjoy/TapjoyIntegrationException
    //   906	965	1067	com/tapjoy/TapjoyIntegrationException
    //   965	1016	1067	com/tapjoy/TapjoyIntegrationException
    //   1020	1044	1067	com/tapjoy/TapjoyIntegrationException
    //   1044	1053	1067	com/tapjoy/TapjoyIntegrationException
    //   1055	1067	1067	com/tapjoy/TapjoyIntegrationException
    //   1102	1128	1067	com/tapjoy/TapjoyIntegrationException
    //   1166	1192	1067	com/tapjoy/TapjoyIntegrationException
    //   1195	1204	1067	com/tapjoy/TapjoyIntegrationException
    //   1207	1214	1067	com/tapjoy/TapjoyIntegrationException
    //   1218	1258	1067	com/tapjoy/TapjoyIntegrationException
    //   1261	1294	1067	com/tapjoy/TapjoyIntegrationException
    //   1294	1313	1067	com/tapjoy/TapjoyIntegrationException
    //   1318	1330	1067	com/tapjoy/TapjoyIntegrationException
    //   1335	1380	1067	com/tapjoy/TapjoyIntegrationException
    //   1380	1399	1067	com/tapjoy/TapjoyIntegrationException
    //   1402	1414	1067	com/tapjoy/TapjoyIntegrationException
    //   1417	1426	1067	com/tapjoy/TapjoyIntegrationException
    //   1430	1456	1067	com/tapjoy/TapjoyIntegrationException
    //   1460	1486	1067	com/tapjoy/TapjoyIntegrationException
    //   1489	1506	1067	com/tapjoy/TapjoyIntegrationException
    //   1509	1526	1067	com/tapjoy/TapjoyIntegrationException
    //   1530	1556	1067	com/tapjoy/TapjoyIntegrationException
    //   234	273	1101	java/lang/Exception
    //   63	79	1131	com/tapjoy/TapjoyException
    //   79	110	1131	com/tapjoy/TapjoyException
    //   110	128	1131	com/tapjoy/TapjoyException
    //   128	143	1131	com/tapjoy/TapjoyException
    //   143	160	1131	com/tapjoy/TapjoyException
    //   164	173	1131	com/tapjoy/TapjoyException
    //   173	192	1131	com/tapjoy/TapjoyException
    //   192	234	1131	com/tapjoy/TapjoyException
    //   234	273	1131	com/tapjoy/TapjoyException
    //   273	280	1131	com/tapjoy/TapjoyException
    //   284	297	1131	com/tapjoy/TapjoyException
    //   301	306	1131	com/tapjoy/TapjoyException
    //   310	319	1131	com/tapjoy/TapjoyException
    //   323	340	1131	com/tapjoy/TapjoyException
    //   340	353	1131	com/tapjoy/TapjoyException
    //   357	377	1131	com/tapjoy/TapjoyException
    //   382	401	1131	com/tapjoy/TapjoyException
    //   401	420	1131	com/tapjoy/TapjoyException
    //   420	427	1131	com/tapjoy/TapjoyException
    //   431	461	1131	com/tapjoy/TapjoyException
    //   461	511	1131	com/tapjoy/TapjoyException
    //   513	560	1131	com/tapjoy/TapjoyException
    //   564	568	1131	com/tapjoy/TapjoyException
    //   568	593	1131	com/tapjoy/TapjoyException
    //   593	622	1131	com/tapjoy/TapjoyException
    //   627	634	1131	com/tapjoy/TapjoyException
    //   638	696	1131	com/tapjoy/TapjoyException
    //   696	708	1131	com/tapjoy/TapjoyException
    //   708	785	1131	com/tapjoy/TapjoyException
    //   785	837	1131	com/tapjoy/TapjoyException
    //   839	843	1131	com/tapjoy/TapjoyException
    //   843	876	1131	com/tapjoy/TapjoyException
    //   876	906	1131	com/tapjoy/TapjoyException
    //   906	965	1131	com/tapjoy/TapjoyException
    //   965	1016	1131	com/tapjoy/TapjoyException
    //   1020	1044	1131	com/tapjoy/TapjoyException
    //   1044	1053	1131	com/tapjoy/TapjoyException
    //   1055	1067	1131	com/tapjoy/TapjoyException
    //   1102	1128	1131	com/tapjoy/TapjoyException
    //   1166	1192	1131	com/tapjoy/TapjoyException
    //   1195	1204	1131	com/tapjoy/TapjoyException
    //   1207	1214	1131	com/tapjoy/TapjoyException
    //   1218	1258	1131	com/tapjoy/TapjoyException
    //   1261	1294	1131	com/tapjoy/TapjoyException
    //   1294	1313	1131	com/tapjoy/TapjoyException
    //   1318	1330	1131	com/tapjoy/TapjoyException
    //   1335	1380	1131	com/tapjoy/TapjoyException
    //   1380	1399	1131	com/tapjoy/TapjoyException
    //   1402	1414	1131	com/tapjoy/TapjoyException
    //   1417	1426	1131	com/tapjoy/TapjoyException
    //   1430	1456	1131	com/tapjoy/TapjoyException
    //   1460	1486	1131	com/tapjoy/TapjoyException
    //   1489	1506	1131	com/tapjoy/TapjoyException
    //   1509	1526	1131	com/tapjoy/TapjoyException
    //   1530	1556	1131	com/tapjoy/TapjoyException
    //   284	297	1165	java/lang/Exception
    //   301	306	1165	java/lang/Exception
    //   310	319	1165	java/lang/Exception
    //   323	340	1165	java/lang/Exception
    //   431	461	1217	java/lang/Exception
    //   461	511	1217	java/lang/Exception
    //   513	560	1217	java/lang/Exception
    //   564	568	1217	java/lang/Exception
    //   568	593	1217	java/lang/Exception
    //   1207	1214	1217	java/lang/Exception
    //   1261	1294	1217	java/lang/Exception
    //   1294	1313	1217	java/lang/Exception
    //   1318	1330	1217	java/lang/Exception
    //   1335	1380	1217	java/lang/Exception
    //   1380	1399	1217	java/lang/Exception
    //   1402	1414	1217	java/lang/Exception
    //   638	696	1429	java/lang/Exception
    //   696	708	1459	java/lang/Exception
    //   785	837	1529	java/lang/Exception
    //   839	843	1529	java/lang/Exception
    //   1489	1506	1529	java/lang/Exception
    //   1509	1526	1529	java/lang/Exception
    //   110	128	1565	java/lang/Exception
  }
  
  private static String a(long paramLong)
  {
    try
    {
      String str = TapjoyUtil.SHA256(w + ":" + o() + ":" + paramLong + ":" + N);
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
      paramString = TapjoyUtil.SHA256(w + ":" + o() + ":" + paramLong + ":" + N + ":" + paramString);
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
      ah = "";
      Iterator localIterator = ae.getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (((localApplicationInfo.flags & 0x1) != 1) && (paramList.contains(localApplicationInfo.packageName)))
        {
          TapjoyLog.d("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
          if (ah.length() > 0) {
            ah += ",";
          }
          ah += localApplicationInfo.packageName;
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
    FeatureInfo[] arrayOfFeatureInfo = ae.getSystemAvailableFeatures();
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
        if (ae.checkPermission(paramString2, g.getPackageName()) == 0) {
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
    //   34: checkcast 366	java/lang/String
    //   37: invokestatic 880	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   40: astore 8
    //   42: aload 6
    //   44: astore 7
    //   46: aload 10
    //   48: ldc_w 882
    //   51: invokeinterface 876 2 0
    //   56: checkcast 366	java/lang/String
    //   59: invokestatic 880	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   62: astore 13
    //   64: aload 6
    //   66: astore 7
    //   68: aload 10
    //   70: ldc_w 884
    //   73: invokeinterface 876 2 0
    //   78: checkcast 366	java/lang/String
    //   81: invokestatic 880	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   84: astore 14
    //   86: aload 6
    //   88: astore 7
    //   90: aload 10
    //   92: ldc_w 886
    //   95: invokeinterface 876 2 0
    //   100: checkcast 366	java/lang/String
    //   103: invokestatic 880	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   106: astore 15
    //   108: aload 6
    //   110: astore 7
    //   112: aload 10
    //   114: ldc_w 888
    //   117: invokeinterface 876 2 0
    //   122: checkcast 366	java/lang/String
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
    //   150: new 892	com/tapjoy/internal/er
    //   153: dup
    //   154: aload 14
    //   156: invokespecial 893	com/tapjoy/internal/er:<init>	(Ljava/lang/String;)V
    //   159: astore 16
    //   161: aload 6
    //   163: astore 7
    //   165: aload 16
    //   167: getfield 896	com/tapjoy/internal/er:a	Lcom/tapjoy/internal/er$a;
    //   170: getstatic 901	com/tapjoy/internal/er$a:RPC_ANALYTICS	Lcom/tapjoy/internal/er$a;
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
    //   200: ldc_w 473
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
    //   223: getfield 914	com/tapjoy/internal/er:b	Ljava/lang/String;
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
    //   264: invokevirtual 464	java/lang/String:substring	(II)Ljava/lang/String;
    //   267: invokevirtual 926	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   270: aload 9
    //   272: bipush 24
    //   274: bipush 30
    //   276: invokevirtual 464	java/lang/String:substring	(II)Ljava/lang/String;
    //   279: invokevirtual 926	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   282: aload 9
    //   284: bipush 9
    //   286: bipush 13
    //   288: invokevirtual 464	java/lang/String:substring	(II)Ljava/lang/String;
    //   291: invokevirtual 926	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   294: aload 9
    //   296: bipush 30
    //   298: invokevirtual 467	java/lang/String:substring	(I)Ljava/lang/String;
    //   301: invokevirtual 926	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   304: invokevirtual 927	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   307: astore 9
    //   309: aload 6
    //   311: astore 7
    //   313: aload 16
    //   315: getfield 928	com/tapjoy/internal/er:c	Ljava/lang/String;
    //   318: astore 16
    //   320: aload 8
    //   322: ifnonnull +505 -> 827
    //   325: aload 9
    //   327: astore 8
    //   329: aload 6
    //   331: astore 7
    //   333: invokestatic 933	com/tapjoy/internal/gd:a	()Lcom/tapjoy/internal/gd;
    //   336: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   339: aload 14
    //   341: ldc_w 397
    //   344: ldc_w 935
    //   347: aload 9
    //   349: aload 16
    //   351: invokevirtual 938	com/tapjoy/internal/gd:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   354: aload 6
    //   356: astore 7
    //   358: aload 8
    //   360: putstatic 233	com/tapjoy/TapjoyConnectCore:U	Ljava/lang/String;
    //   363: aload 6
    //   365: astore 7
    //   367: aload 13
    //   369: putstatic 235	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   372: aload 6
    //   374: astore 7
    //   376: aload 14
    //   378: putstatic 237	com/tapjoy/TapjoyConnectCore:W	Ljava/lang/String;
    //   381: aload 6
    //   383: astore 7
    //   385: aload 15
    //   387: putstatic 239	com/tapjoy/TapjoyConnectCore:X	Ljava/lang/String;
    //   390: aload 6
    //   392: astore 7
    //   394: new 582	java/util/ArrayList
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
    //   456: invokevirtual 460	java/lang/String:length	()I
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
    //   499: ldc_w 473
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
    //   555: instanceof 366
    //   558: istore_1
    //   559: iload_1
    //   560: ifeq +112 -> 672
    //   563: aload 11
    //   565: checkcast 366	java/lang/String
    //   568: invokevirtual 831	java/lang/String:trim	()Ljava/lang/String;
    //   571: invokestatic 962	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   574: lstore 4
    //   576: lload 4
    //   578: lconst_0
    //   579: lcmp
    //   580: ifgt +123 -> 703
    //   583: invokestatic 968	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   586: invokevirtual 971	com/tapjoy/TapjoyAppSettings:removeConnectResult	()V
    //   589: invokestatic 290	com/tapjoy/internal/fd:a	()Lcom/tapjoy/internal/fd;
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
    //   616: getfield 976	com/tapjoy/internal/fd:a	Lcom/tapjoy/internal/fb;
    //   619: aload 6
    //   621: checkcast 875	java/util/Map
    //   624: invokevirtual 981	com/tapjoy/internal/fb:a	(Ljava/util/Map;)V
    //   627: aload 6
    //   629: invokestatic 986	com/tapjoy/internal/bm:a	(Ljava/lang/Object;)Ljava/lang/String;
    //   632: astore 6
    //   634: aload_0
    //   635: invokevirtual 989	com/tapjoy/internal/fd:c	()Landroid/content/SharedPreferences;
    //   638: invokeinterface 561 1 0
    //   643: ldc_w 973
    //   646: aload 6
    //   648: invokeinterface 567 3 0
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
    //   707: invokestatic 999	com/tapjoy/TapjoyConnectCore:q	()Ljava/lang/String;
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
    //   732: getfield 976	com/tapjoy/internal/fd:a	Lcom/tapjoy/internal/fb;
    //   735: aconst_null
    //   736: invokevirtual 981	com/tapjoy/internal/fb:a	(Ljava/util/Map;)V
    //   739: aload_0
    //   740: invokevirtual 989	com/tapjoy/internal/fd:c	()Landroid/content/SharedPreferences;
    //   743: invokeinterface 561 1 0
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
    ag.put(paramString1, str);
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
    //   8: invokevirtual 1029	com/tapjoy/internal/bs:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 1031	com/tapjoy/internal/bs:s	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 473
    //   25: ldc_w 1033
    //   28: invokestatic 492	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
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
    //   53: ldc_w 473
    //   56: new 494	com/tapjoy/TapjoyErrorMessage
    //   59: dup
    //   60: getstatic 500	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   63: ldc_w 1035
    //   66: invokespecial 505	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   69: invokestatic 508	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   72: iconst_0
    //   73: ireturn
    //   74: astore_2
    //   75: aconst_null
    //   76: astore_1
    //   77: aload_1
    //   78: astore_0
    //   79: ldc_w 473
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
    //   101: ldc_w 473
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
    if ((o == null) || (o.length() == 0) || (System.currentTimeMillis() - ab > 1800000L)) {
      o = n();
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
      ab = System.currentTimeMillis();
    }
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
      TapjoyUtil.safePut(localHashMap, "user_tags[" + i1 + "]", str, true);
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
      paramString = TapjoyUtil.SHA256(w + ":" + o() + ":" + paramLong + ":" + N + ":" + paramInt + ":" + paramString);
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
  
  private static void h()
  {
    TapjoyLog.i("TapjoyConnect", "Connect Flags:");
    TapjoyLog.i("TapjoyConnect", "--------------------");
    Iterator localIterator = ag.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      TapjoyLog.i("TapjoyConnect", "key: " + (String)localEntry.getKey() + ", value: " + Uri.encode(localEntry.getValue().toString()));
    }
    TapjoyLog.i("TapjoyConnect", "hostURL: [" + getConnectFlagValue("TJC_OPTION_SERVICE_URL") + "]");
    TapjoyLog.i("TapjoyConnect", "redirectDomain: [" + R + "]");
    TapjoyLog.i("TapjoyConnect", "--------------------");
  }
  
  private static void i()
  {
    for (;;)
    {
      int i1;
      try
      {
        if (ae == null) {
          break;
        }
        ApplicationInfo localApplicationInfo = ae.getApplicationInfo(g.getPackageName(), 128);
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
    TapjoyLog.d("TapjoyConnect", "isViewOpen: " + ai.size());
    return !ai.isEmpty();
  }
  
  /* Error */
  private void j()
  {
    // Byte code:
    //   0: getstatic 303	com/tapjoy/TapjoyConnectCore:ae	Landroid/content/pm/PackageManager;
    //   3: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   6: invokevirtual 324	android/content/Context:getPackageName	()Ljava/lang/String;
    //   9: iconst_1
    //   10: invokevirtual 375	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   13: getfield 1564	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
    //   16: invokestatic 155	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
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
    //   46: checkcast 1566	android/content/pm/ActivityInfo
    //   49: astore_3
    //   50: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   53: aload_3
    //   54: getfield 1567	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   57: invokevirtual 1568	java/util/Vector:contains	(Ljava/lang/Object;)Z
    //   60: ifeq -29 -> 31
    //   63: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   66: aload_3
    //   67: getfield 1567	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   70: invokevirtual 1571	java/util/Vector:indexOf	(Ljava/lang/Object;)I
    //   73: istore_1
    //   74: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   77: iload_1
    //   78: invokevirtual 1574	java/util/Vector:get	(I)Ljava/lang/Object;
    //   81: checkcast 366	java/lang/String
    //   84: invokestatic 1580	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   87: pop
    //   88: new 143	java/util/Vector
    //   91: dup
    //   92: invokespecial 822	java/util/Vector:<init>	()V
    //   95: astore 4
    //   97: aload_3
    //   98: getfield 1583	android/content/pm/ActivityInfo:configChanges	I
    //   101: sipush 128
    //   104: iand
    //   105: sipush 128
    //   108: if_icmpeq +12 -> 120
    //   111: aload 4
    //   113: ldc_w 1585
    //   116: invokevirtual 834	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   119: pop
    //   120: aload_3
    //   121: getfield 1583	android/content/pm/ActivityInfo:configChanges	I
    //   124: bipush 32
    //   126: iand
    //   127: bipush 32
    //   129: if_icmpeq +12 -> 141
    //   132: aload 4
    //   134: ldc_w 1587
    //   137: invokevirtual 834	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   140: pop
    //   141: aload 4
    //   143: invokevirtual 1588	java/util/Vector:size	()I
    //   146: ifeq +149 -> 295
    //   149: aload 4
    //   151: invokevirtual 1588	java/util/Vector:size	()I
    //   154: iconst_1
    //   155: if_icmpne +95 -> 250
    //   158: new 272	com/tapjoy/TapjoyIntegrationException
    //   161: dup
    //   162: new 475	java/lang/StringBuilder
    //   165: dup
    //   166: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   169: aload 4
    //   171: invokevirtual 1589	java/util/Vector:toString	()Ljava/lang/String;
    //   174: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: ldc_w 1591
    //   180: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   186: iload_1
    //   187: invokevirtual 1574	java/util/Vector:get	(I)Ljava/lang/Object;
    //   190: checkcast 366	java/lang/String
    //   193: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: invokespecial 1592	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   202: athrow
    //   203: astore_2
    //   204: new 272	com/tapjoy/TapjoyIntegrationException
    //   207: dup
    //   208: new 475	java/lang/StringBuilder
    //   211: dup
    //   212: ldc_w 1594
    //   215: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   218: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   221: iload_1
    //   222: invokevirtual 1574	java/util/Vector:get	(I)Ljava/lang/Object;
    //   225: checkcast 366	java/lang/String
    //   228: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   234: invokespecial 1592	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   237: athrow
    //   238: astore_2
    //   239: new 272	com/tapjoy/TapjoyIntegrationException
    //   242: dup
    //   243: ldc_w 1596
    //   246: invokespecial 1592	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   249: athrow
    //   250: new 272	com/tapjoy/TapjoyIntegrationException
    //   253: dup
    //   254: new 475	java/lang/StringBuilder
    //   257: dup
    //   258: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   261: aload 4
    //   263: invokevirtual 1589	java/util/Vector:toString	()Ljava/lang/String;
    //   266: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: ldc_w 1598
    //   272: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   278: iload_1
    //   279: invokevirtual 1574	java/util/Vector:get	(I)Ljava/lang/Object;
    //   282: checkcast 366	java/lang/String
    //   285: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   291: invokespecial 1592	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   294: athrow
    //   295: getstatic 402	android/os/Build$VERSION:SDK_INT	I
    //   298: bipush 13
    //   300: if_icmplt +49 -> 349
    //   303: aload_3
    //   304: getfield 1583	android/content/pm/ActivityInfo:configChanges	I
    //   307: sipush 1024
    //   310: iand
    //   311: sipush 1024
    //   314: if_icmpeq +35 -> 349
    //   317: ldc_w 473
    //   320: new 475	java/lang/StringBuilder
    //   323: dup
    //   324: ldc_w 1600
    //   327: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   330: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   333: iload_1
    //   334: invokevirtual 1574	java/util/Vector:get	(I)Ljava/lang/Object;
    //   337: checkcast 366	java/lang/String
    //   340: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   346: invokestatic 594	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   349: getstatic 402	android/os/Build$VERSION:SDK_INT	I
    //   352: bipush 11
    //   354: if_icmplt +64 -> 418
    //   357: aload_3
    //   358: getfield 1567	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   361: ldc_w 1602
    //   364: invokevirtual 693	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   367: ifeq +51 -> 418
    //   370: aload_3
    //   371: getfield 1603	android/content/pm/ActivityInfo:flags	I
    //   374: sipush 512
    //   377: iand
    //   378: sipush 512
    //   381: if_icmpeq +37 -> 418
    //   384: new 272	com/tapjoy/TapjoyIntegrationException
    //   387: dup
    //   388: new 475	java/lang/StringBuilder
    //   391: dup
    //   392: ldc_w 1605
    //   395: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   398: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   401: iload_1
    //   402: invokevirtual 1574	java/util/Vector:get	(I)Ljava/lang/Object;
    //   405: checkcast 366	java/lang/String
    //   408: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   414: invokespecial 1592	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   417: athrow
    //   418: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   421: iload_1
    //   422: invokevirtual 1607	java/util/Vector:remove	(I)Ljava/lang/Object;
    //   425: pop
    //   426: goto -395 -> 31
    //   429: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   432: invokevirtual 1588	java/util/Vector:size	()I
    //   435: ifeq +103 -> 538
    //   438: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   441: invokevirtual 1588	java/util/Vector:size	()I
    //   444: iconst_1
    //   445: if_icmpne +48 -> 493
    //   448: new 272	com/tapjoy/TapjoyIntegrationException
    //   451: dup
    //   452: new 475	java/lang/StringBuilder
    //   455: dup
    //   456: ldc_w 1609
    //   459: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   462: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   465: invokevirtual 1588	java/util/Vector:size	()I
    //   468: invokevirtual 513	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   471: ldc_w 1611
    //   474: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   477: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   480: invokevirtual 1589	java/util/Vector:toString	()Ljava/lang/String;
    //   483: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   489: invokespecial 1592	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   492: athrow
    //   493: new 272	com/tapjoy/TapjoyIntegrationException
    //   496: dup
    //   497: new 475	java/lang/StringBuilder
    //   500: dup
    //   501: ldc_w 1609
    //   504: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   507: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   510: invokevirtual 1588	java/util/Vector:size	()I
    //   513: invokevirtual 513	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   516: ldc_w 1613
    //   519: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   522: getstatic 161	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   525: invokevirtual 1589	java/util/Vector:toString	()Ljava/lang/String;
    //   528: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   534: invokespecial 1592	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   537: athrow
    //   538: invokestatic 1615	com/tapjoy/TapjoyConnectCore:k	()V
    //   541: ldc_w 1617
    //   544: invokestatic 1580	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   547: astore_2
    //   548: aload_2
    //   549: ldc_w 1619
    //   552: iconst_1
    //   553: anewarray 1576	java/lang/Class
    //   556: dup
    //   557: iconst_0
    //   558: ldc_w 663
    //   561: aastore
    //   562: invokevirtual 1623	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   565: pop
    //   566: ldc_w 1625
    //   569: invokestatic 1628	com/tapjoy/TapjoyUtil:getResource	(Ljava/lang/String;)Ljava/lang/Object;
    //   572: checkcast 366	java/lang/String
    //   575: astore_2
    //   576: aload_2
    //   577: ifnonnull +178 -> 755
    //   580: ldc_w 1630
    //   583: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   586: invokestatic 1634	com/tapjoy/TapjoyUtil:copyTextFromJarIntoString	(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   589: astore_2
    //   590: aload_2
    //   591: ifnonnull +161 -> 752
    //   594: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   597: invokevirtual 318	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   600: ldc_w 1636
    //   603: ldc_w 1638
    //   606: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   609: invokevirtual 324	android/content/Context:getPackageName	()Ljava/lang/String;
    //   612: invokevirtual 330	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   615: istore_1
    //   616: getstatic 131	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   619: invokevirtual 318	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   622: iload_1
    //   623: invokevirtual 337	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   626: astore_3
    //   627: aload_3
    //   628: invokevirtual 1643	java/io/InputStream:available	()I
    //   631: newarray byte
    //   633: astore 4
    //   635: aload_3
    //   636: aload 4
    //   638: invokevirtual 1647	java/io/InputStream:read	([B)I
    //   641: pop
    //   642: new 366	java/lang/String
    //   645: dup
    //   646: aload 4
    //   648: invokespecial 1650	java/lang/String:<init>	([B)V
    //   651: astore_3
    //   652: ldc_w 1625
    //   655: aload_3
    //   656: invokestatic 1654	com/tapjoy/TapjoyUtil:setResource	(Ljava/lang/String;Ljava/lang/Object;)V
    //   659: aload_3
    //   660: astore_2
    //   661: aload_2
    //   662: ifnonnull +38 -> 700
    //   665: new 272	com/tapjoy/TapjoyIntegrationException
    //   668: dup
    //   669: ldc_w 1656
    //   672: invokespecial 1592	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   675: athrow
    //   676: astore_2
    //   677: new 272	com/tapjoy/TapjoyIntegrationException
    //   680: dup
    //   681: ldc_w 1658
    //   684: invokespecial 1592	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   687: athrow
    //   688: astore_2
    //   689: new 272	com/tapjoy/TapjoyIntegrationException
    //   692: dup
    //   693: ldc_w 1660
    //   696: invokespecial 1592	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   699: athrow
    //   700: ldc_w 626
    //   703: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   706: ifnull +28 -> 734
    //   709: ldc_w 626
    //   712: invokestatic 350	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   715: ldc_w 840
    //   718: invokevirtual 693	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   721: ifeq +13 -> 734
    //   724: ldc_w 473
    //   727: ldc_w 1662
    //   730: invokestatic 515	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   733: return
    //   734: aload_0
    //   735: getfield 309	com/tapjoy/TapjoyConnectCore:af	Lcom/tapjoy/TapjoyGpsHelper;
    //   738: invokevirtual 1665	com/tapjoy/TapjoyGpsHelper:checkGooglePlayIntegration	()V
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
      str = TapjoyUtil.SHA256(System.currentTimeMillis() / 1000L + w + p);
      TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + localException1.toString());
    }
    catch (Exception localException1)
    {
      try
      {
        ab = System.currentTimeMillis();
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
    if ((q != null) && (q.length() > 0)) {}
    for (i1 = 1; i1 != 0; i1 = 0) {
      return q;
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
  
  private void p()
  {
    TapjoyLog.d("TapjoyConnect", "Initializing mediation params");
    try
    {
      Object localObject = (ArrayList)ag.get("TJC_OPTION_MEDIATION_CONFIGS");
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
  
  private static String q()
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
      ai.put("", Integer.valueOf(1));
      return;
    }
    ai.clear();
  }
  
  public static void viewDidClose(String paramString)
  {
    TapjoyLog.d("TapjoyConnect", "viewDidClose: " + paramString);
    ai.remove(paramString);
    ev.e.notifyObservers();
  }
  
  public static void viewWillOpen(String paramString, int paramInt)
  {
    TapjoyLog.d("TapjoyConnect", "viewWillOpen: " + paramString);
    ai.put(paramString, Integer.valueOf(paramInt));
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
    Object localObject2;
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
          ad = true;
          p();
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
          if (ah.length() > 0)
          {
            localObject1 = getGenericURLParams();
            TapjoyUtil.safePut((Map)localObject1, "package_names", ah, true);
            long l1 = System.currentTimeMillis() / 1000L;
            localObject2 = a(l1, ah);
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
    //   0: ldc_w 1855
    //   3: invokestatic 1580	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 1857
    //   9: invokevirtual 1861	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 1866	java/lang/reflect/Field:isAccessible	()Z
    //   17: ifne +8 -> 25
    //   20: aload_1
    //   21: iconst_1
    //   22: invokevirtual 1869	java/lang/reflect/Field:setAccessible	(Z)V
    //   25: aload_1
    //   26: ldc_w 384
    //   29: invokevirtual 1870	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: invokevirtual 1408	java/lang/Object:toString	()Ljava/lang/String;
    //   35: astore_1
    //   36: ldc_w 473
    //   39: new 475	java/lang/StringBuilder
    //   42: dup
    //   43: ldc_w 1872
    //   46: invokespecial 480	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   49: aload_1
    //   50: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 492	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload_1
    //   60: areturn
    //   61: astore_2
    //   62: aconst_null
    //   63: astore_1
    //   64: ldc_w 473
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
      TapjoyHttpURLResponse localTapjoyHttpURLResponse = TapjoyConnectCore.c().getResponseFromURL(TapjoyConnectCore.getHostURL() + "api/connect/v3.json?", null, null, this.b);
      if (localTapjoyHttpURLResponse.response != null) {
        TapjoyConnectCore.b(localTapjoyHttpURLResponse.response);
      }
    }
  }
}
