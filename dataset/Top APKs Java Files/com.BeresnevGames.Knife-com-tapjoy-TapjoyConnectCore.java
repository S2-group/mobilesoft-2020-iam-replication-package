package com.tapjoy;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import com.tapjoy.internal.aq;
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
import com.tapjoy.internal.fh;
import com.tapjoy.internal.gc;
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
    //   1: invokespecial 266	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 268	com/tapjoy/TapjoyConnectCore:X	J
    //   9: aload_0
    //   10: iconst_0
    //   11: putfield 270	com/tapjoy/TapjoyConnectCore:Y	Z
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 272	com/tapjoy/TapjoyConnectCore:aa	Z
    //   19: aload_1
    //   20: putstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   23: invokestatic 277	com/tapjoy/internal/fh:a	()Lcom/tapjoy/internal/fh;
    //   26: aload_1
    //   27: invokevirtual 279	com/tapjoy/internal/fh:a	(Landroid/content/Context;)V
    //   30: invokestatic 284	com/tapjoy/internal/fd:a	()Lcom/tapjoy/internal/fd;
    //   33: aload_1
    //   34: invokevirtual 285	com/tapjoy/internal/fd:a	(Landroid/content/Context;)V
    //   37: new 287	com/tapjoy/TapjoyURLConnection
    //   40: dup
    //   41: invokespecial 288	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   44: putstatic 128	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   47: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   50: invokevirtual 294	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   53: putstatic 296	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   56: aload_0
    //   57: new 298	com/tapjoy/TapjoyGpsHelper
    //   60: dup
    //   61: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   64: invokespecial 300	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   67: putfield 302	com/tapjoy/TapjoyConnectCore:ad	Lcom/tapjoy/TapjoyGpsHelper;
    //   70: getstatic 247	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Hashtable;
    //   73: ifnonnull +13 -> 86
    //   76: new 304	java/util/Hashtable
    //   79: dup
    //   80: invokespecial 305	java/util/Hashtable:<init>	()V
    //   83: putstatic 247	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Hashtable;
    //   86: invokestatic 307	com/tapjoy/TapjoyConnectCore:i	()V
    //   89: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   92: invokevirtual 311	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   95: ldc_w 313
    //   98: aconst_null
    //   99: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   102: invokevirtual 317	android/content/Context:getPackageName	()Ljava/lang/String;
    //   105: invokevirtual 323	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   108: istore_2
    //   109: new 325	java/util/Properties
    //   112: dup
    //   113: invokespecial 326	java/util/Properties:<init>	()V
    //   116: astore_1
    //   117: aload_1
    //   118: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   121: invokevirtual 311	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   124: iload_2
    //   125: invokevirtual 330	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   128: invokevirtual 334	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   131: aload_1
    //   132: invokestatic 337	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   135: ldc_w 339
    //   138: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   141: invokestatic 348	com/tapjoy/internal/ct:c	(Ljava/lang/String;)Z
    //   144: ifeq +7 -> 151
    //   147: aload_0
    //   148: invokespecial 350	com/tapjoy/TapjoyConnectCore:j	()V
    //   151: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   154: invokevirtual 354	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   157: ldc_w 356
    //   160: invokestatic 362	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   163: astore_1
    //   164: aload_1
    //   165: putstatic 156	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   168: aload_1
    //   169: ifnull +12 -> 181
    //   172: getstatic 156	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   175: invokevirtual 367	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   178: putstatic 156	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   181: getstatic 296	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   184: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   187: invokevirtual 317	android/content/Context:getPackageName	()Ljava/lang/String;
    //   190: iconst_0
    //   191: invokevirtual 373	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   194: getfield 378	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   197: putstatic 174	com/tapjoy/TapjoyConnectCore:w	Ljava/lang/String;
    //   200: ldc_w 380
    //   203: putstatic 168	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   206: ldc_w 380
    //   209: putstatic 188	com/tapjoy/TapjoyConnectCore:D	Ljava/lang/String;
    //   212: getstatic 385	android/os/Build:MODEL	Ljava/lang/String;
    //   215: putstatic 164	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   218: getstatic 388	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   221: putstatic 166	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   224: getstatic 393	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   227: putstatic 170	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   230: ldc_w 395
    //   233: putstatic 176	com/tapjoy/TapjoyConnectCore:x	Ljava/lang/String;
    //   236: ldc_w 397
    //   239: putstatic 178	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   242: getstatic 400	android/os/Build$VERSION:SDK_INT	I
    //   245: iconst_3
    //   246: if_icmple +35 -> 281
    //   249: new 402	com/tapjoy/TapjoyDisplayMetricsUtil
    //   252: dup
    //   253: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   256: invokespecial 403	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   259: astore_1
    //   260: aload_1
    //   261: invokevirtual 407	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   264: putstatic 180	com/tapjoy/TapjoyConnectCore:z	I
    //   267: aload_1
    //   268: invokevirtual 411	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   271: putstatic 182	com/tapjoy/TapjoyConnectCore:A	F
    //   274: aload_1
    //   275: invokevirtual 414	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   278: putstatic 184	com/tapjoy/TapjoyConnectCore:B	I
    //   281: ldc_w 416
    //   284: invokestatic 418	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   287: istore_3
    //   288: iload_3
    //   289: ifeq +665 -> 954
    //   292: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   295: ldc_w 420
    //   298: invokevirtual 424	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   301: checkcast 426	android/net/wifi/WifiManager
    //   304: astore_1
    //   305: aload_1
    //   306: ifnull +42 -> 348
    //   309: aload_1
    //   310: invokevirtual 430	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   313: astore_1
    //   314: aload_1
    //   315: ifnull +33 -> 348
    //   318: aload_1
    //   319: invokevirtual 435	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   322: astore_1
    //   323: aload_1
    //   324: putstatic 160	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   327: aload_1
    //   328: ifnull +20 -> 348
    //   331: getstatic 160	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   334: ldc_w 437
    //   337: ldc -102
    //   339: invokevirtual 441	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   342: invokevirtual 367	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   345: putstatic 160	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   348: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   351: ldc_w 443
    //   354: invokevirtual 424	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   357: checkcast 445	android/telephony/TelephonyManager
    //   360: astore_1
    //   361: aload_1
    //   362: ifnull +60 -> 422
    //   365: aload_1
    //   366: invokevirtual 448	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   369: putstatic 190	com/tapjoy/TapjoyConnectCore:E	Ljava/lang/String;
    //   372: aload_1
    //   373: invokevirtual 451	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   376: putstatic 192	com/tapjoy/TapjoyConnectCore:F	Ljava/lang/String;
    //   379: aload_1
    //   380: invokevirtual 454	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   383: astore_1
    //   384: aload_1
    //   385: ifnull +37 -> 422
    //   388: aload_1
    //   389: invokevirtual 457	java/lang/String:length	()I
    //   392: iconst_5
    //   393: if_icmpeq +12 -> 405
    //   396: aload_1
    //   397: invokevirtual 457	java/lang/String:length	()I
    //   400: bipush 6
    //   402: if_icmpne +20 -> 422
    //   405: aload_1
    //   406: iconst_0
    //   407: iconst_3
    //   408: invokevirtual 461	java/lang/String:substring	(II)Ljava/lang/String;
    //   411: putstatic 194	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   414: aload_1
    //   415: iconst_3
    //   416: invokevirtual 464	java/lang/String:substring	(I)Ljava/lang/String;
    //   419: putstatic 196	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   422: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   425: ldc_w 466
    //   428: iconst_0
    //   429: invokevirtual 470	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   432: astore_1
    //   433: aload_1
    //   434: ldc_w 472
    //   437: ldc -102
    //   439: invokeinterface 477 3 0
    //   444: astore 4
    //   446: aload 4
    //   448: putstatic 162	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   451: aload 4
    //   453: ifnull +14 -> 467
    //   456: getstatic 162	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   459: invokevirtual 457	java/lang/String:length	()I
    //   462: istore_2
    //   463: iload_2
    //   464: ifne +61 -> 525
    //   467: new 479	java/lang/StringBuilder
    //   470: dup
    //   471: invokespecial 480	java/lang/StringBuilder:<init>	()V
    //   474: invokestatic 486	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   477: invokevirtual 489	java/util/UUID:toString	()Ljava/lang/String;
    //   480: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   483: invokestatic 499	java/lang/System:currentTimeMillis	()J
    //   486: invokevirtual 502	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   489: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   492: invokestatic 508	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   495: putstatic 162	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   498: aload_1
    //   499: invokeinterface 512 1 0
    //   504: astore_1
    //   505: aload_1
    //   506: ldc_w 472
    //   509: getstatic 162	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   512: invokeinterface 518 3 0
    //   517: pop
    //   518: aload_1
    //   519: invokeinterface 522 1 0
    //   524: pop
    //   525: ldc_w 524
    //   528: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   531: ifnull +71 -> 602
    //   534: ldc_w 524
    //   537: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   540: invokevirtual 457	java/lang/String:length	()I
    //   543: ifle +59 -> 602
    //   546: ldc_w 524
    //   549: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   552: putstatic 202	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   555: new 526	java/util/ArrayList
    //   558: dup
    //   559: getstatic 529	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   562: invokestatic 146	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   565: invokespecial 530	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   568: getstatic 202	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   571: invokevirtual 534	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   574: ifne +28 -> 602
    //   577: ldc_w 536
    //   580: new 479	java/lang/StringBuilder
    //   583: dup
    //   584: ldc_w 538
    //   587: invokespecial 541	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   590: getstatic 202	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   593: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   596: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   599: invokestatic 546	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   602: getstatic 202	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   605: astore_1
    //   606: new 548	android/content/Intent
    //   609: dup
    //   610: ldc_w 550
    //   613: invokespecial 551	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   616: astore 4
    //   618: aload_1
    //   619: invokevirtual 457	java/lang/String:length	()I
    //   622: ifgt +374 -> 996
    //   625: aload 4
    //   627: ldc_w 553
    //   630: invokestatic 559	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   633: invokevirtual 563	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   636: pop
    //   637: getstatic 296	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   640: aload 4
    //   642: iconst_0
    //   643: invokevirtual 567	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   646: invokeinterface 572 1 0
    //   651: ifle +419 -> 1070
    //   654: iconst_1
    //   655: istore_3
    //   656: iload_3
    //   657: putstatic 218	com/tapjoy/TapjoyConnectCore:R	Z
    //   660: invokestatic 574	com/tapjoy/TapjoyConnectCore:g	()V
    //   663: ldc_w 576
    //   666: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   669: ifnull +24 -> 693
    //   672: ldc_w 576
    //   675: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   678: invokevirtual 457	java/lang/String:length	()I
    //   681: ifle +12 -> 693
    //   684: ldc_w 576
    //   687: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   690: putstatic 240	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   693: ldc_w 578
    //   696: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   699: ifnull +24 -> 723
    //   702: ldc_w 578
    //   705: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   708: invokevirtual 457	java/lang/String:length	()I
    //   711: ifle +12 -> 723
    //   714: ldc_w 578
    //   717: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   720: putstatic 238	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   723: ldc_w 580
    //   726: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   729: ifnull +53 -> 782
    //   732: ldc_w 580
    //   735: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   738: invokevirtual 457	java/lang/String:length	()I
    //   741: ifle +41 -> 782
    //   744: ldc_w 536
    //   747: new 479	java/lang/StringBuilder
    //   750: dup
    //   751: ldc_w 582
    //   754: invokespecial 541	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   757: ldc_w 580
    //   760: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   763: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   766: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   769: invokestatic 584	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   772: ldc_w 580
    //   775: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   778: aconst_null
    //   779: invokestatic 588	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;Lcom/tapjoy/TJSetUserIDListener;)V
    //   782: ldc_w 590
    //   785: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   788: invokestatic 593	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   791: putstatic 214	com/tapjoy/TapjoyConnectCore:P	Ljava/lang/String;
    //   794: getstatic 247	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Hashtable;
    //   797: ifnull +6 -> 803
    //   800: invokestatic 595	com/tapjoy/TapjoyConnectCore:h	()V
    //   803: aload_0
    //   804: invokevirtual 598	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   807: aload_0
    //   808: iconst_1
    //   809: putfield 272	com/tapjoy/TapjoyConnectCore:aa	Z
    //   812: return
    //   813: astore_1
    //   814: new 261	com/tapjoy/TapjoyException
    //   817: dup
    //   818: aload_1
    //   819: invokevirtual 601	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   822: invokespecial 602	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   825: athrow
    //   826: astore_1
    //   827: ldc_w 536
    //   830: new 604	com/tapjoy/TapjoyErrorMessage
    //   833: dup
    //   834: getstatic 610	com/tapjoy/TapjoyErrorMessage$ErrorType:INTEGRATION_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   837: aload_1
    //   838: invokevirtual 611	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   841: invokespecial 614	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   844: invokestatic 617	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   847: invokestatic 619	com/tapjoy/TapjoyConnectCore:d	()V
    //   850: getstatic 624	com/tapjoy/internal/ev:b	Lcom/tapjoy/internal/ev$a;
    //   853: getstatic 630	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   856: invokevirtual 636	com/tapjoy/internal/ev$a:notifyObservers	(Ljava/lang/Object;)V
    //   859: return
    //   860: astore_1
    //   861: ldc_w 536
    //   864: new 479	java/lang/StringBuilder
    //   867: dup
    //   868: ldc_w 638
    //   871: invokespecial 541	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   874: aload_1
    //   875: invokevirtual 639	java/lang/Exception:toString	()Ljava/lang/String;
    //   878: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   881: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   884: invokestatic 641	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   887: goto -606 -> 281
    //   890: astore_1
    //   891: ldc_w 536
    //   894: new 604	com/tapjoy/TapjoyErrorMessage
    //   897: dup
    //   898: getstatic 644	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   901: aload_1
    //   902: invokevirtual 645	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   905: invokespecial 614	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   908: invokestatic 617	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   911: invokestatic 619	com/tapjoy/TapjoyConnectCore:d	()V
    //   914: getstatic 624	com/tapjoy/internal/ev:b	Lcom/tapjoy/internal/ev$a;
    //   917: getstatic 630	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   920: invokevirtual 636	com/tapjoy/internal/ev$a:notifyObservers	(Ljava/lang/Object;)V
    //   923: return
    //   924: astore_1
    //   925: ldc_w 536
    //   928: new 479	java/lang/StringBuilder
    //   931: dup
    //   932: ldc_w 647
    //   935: invokespecial 541	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   938: aload_1
    //   939: invokevirtual 639	java/lang/Exception:toString	()Ljava/lang/String;
    //   942: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   945: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   948: invokestatic 641	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   951: goto -603 -> 348
    //   954: ldc_w 536
    //   957: ldc_w 649
    //   960: invokestatic 651	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   963: goto -615 -> 348
    //   966: astore_1
    //   967: ldc_w 536
    //   970: new 479	java/lang/StringBuilder
    //   973: dup
    //   974: ldc_w 653
    //   977: invokespecial 541	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   980: aload_1
    //   981: invokevirtual 639	java/lang/Exception:toString	()Ljava/lang/String;
    //   984: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   987: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   990: invokestatic 641	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   993: goto -468 -> 525
    //   996: aload_1
    //   997: ldc_w 655
    //   1000: invokevirtual 658	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1003: ifeq +13 -> 1016
    //   1006: ldc_w 660
    //   1009: invokestatic 662	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1012: istore_3
    //   1013: goto -357 -> 656
    //   1016: aload_1
    //   1017: ldc_w 664
    //   1020: invokevirtual 658	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1023: ifeq +47 -> 1070
    //   1026: ldc_w 666
    //   1029: invokestatic 662	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1032: istore_3
    //   1033: goto -377 -> 656
    //   1036: astore_1
    //   1037: ldc_w 536
    //   1040: new 479	java/lang/StringBuilder
    //   1043: dup
    //   1044: ldc_w 668
    //   1047: invokespecial 541	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1050: aload_1
    //   1051: invokevirtual 639	java/lang/Exception:toString	()Ljava/lang/String;
    //   1054: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1057: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1060: invokestatic 641	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1063: goto -403 -> 660
    //   1066: astore_1
    //   1067: goto -932 -> 135
    //   1070: iconst_0
    //   1071: istore_3
    //   1072: goto -416 -> 656
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1075	0	this	TapjoyConnectCore
    //   0	1075	1	paramContext	Context
    //   108	356	2	i1	int
    //   287	785	3	bool	boolean
    //   444	197	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   181	200	813	android/content/pm/PackageManager$NameNotFoundException
    //   70	86	826	com/tapjoy/TapjoyIntegrationException
    //   86	117	826	com/tapjoy/TapjoyIntegrationException
    //   117	135	826	com/tapjoy/TapjoyIntegrationException
    //   135	151	826	com/tapjoy/TapjoyIntegrationException
    //   151	168	826	com/tapjoy/TapjoyIntegrationException
    //   172	181	826	com/tapjoy/TapjoyIntegrationException
    //   181	200	826	com/tapjoy/TapjoyIntegrationException
    //   200	242	826	com/tapjoy/TapjoyIntegrationException
    //   242	281	826	com/tapjoy/TapjoyIntegrationException
    //   281	288	826	com/tapjoy/TapjoyIntegrationException
    //   292	305	826	com/tapjoy/TapjoyIntegrationException
    //   309	314	826	com/tapjoy/TapjoyIntegrationException
    //   318	327	826	com/tapjoy/TapjoyIntegrationException
    //   331	348	826	com/tapjoy/TapjoyIntegrationException
    //   348	361	826	com/tapjoy/TapjoyIntegrationException
    //   365	384	826	com/tapjoy/TapjoyIntegrationException
    //   388	405	826	com/tapjoy/TapjoyIntegrationException
    //   405	422	826	com/tapjoy/TapjoyIntegrationException
    //   422	451	826	com/tapjoy/TapjoyIntegrationException
    //   456	463	826	com/tapjoy/TapjoyIntegrationException
    //   467	525	826	com/tapjoy/TapjoyIntegrationException
    //   525	602	826	com/tapjoy/TapjoyIntegrationException
    //   602	654	826	com/tapjoy/TapjoyIntegrationException
    //   656	660	826	com/tapjoy/TapjoyIntegrationException
    //   660	693	826	com/tapjoy/TapjoyIntegrationException
    //   693	723	826	com/tapjoy/TapjoyIntegrationException
    //   723	782	826	com/tapjoy/TapjoyIntegrationException
    //   782	803	826	com/tapjoy/TapjoyIntegrationException
    //   803	812	826	com/tapjoy/TapjoyIntegrationException
    //   814	826	826	com/tapjoy/TapjoyIntegrationException
    //   861	887	826	com/tapjoy/TapjoyIntegrationException
    //   925	951	826	com/tapjoy/TapjoyIntegrationException
    //   954	963	826	com/tapjoy/TapjoyIntegrationException
    //   967	993	826	com/tapjoy/TapjoyIntegrationException
    //   996	1013	826	com/tapjoy/TapjoyIntegrationException
    //   1016	1033	826	com/tapjoy/TapjoyIntegrationException
    //   1037	1063	826	com/tapjoy/TapjoyIntegrationException
    //   242	281	860	java/lang/Exception
    //   70	86	890	com/tapjoy/TapjoyException
    //   86	117	890	com/tapjoy/TapjoyException
    //   117	135	890	com/tapjoy/TapjoyException
    //   135	151	890	com/tapjoy/TapjoyException
    //   151	168	890	com/tapjoy/TapjoyException
    //   172	181	890	com/tapjoy/TapjoyException
    //   181	200	890	com/tapjoy/TapjoyException
    //   200	242	890	com/tapjoy/TapjoyException
    //   242	281	890	com/tapjoy/TapjoyException
    //   281	288	890	com/tapjoy/TapjoyException
    //   292	305	890	com/tapjoy/TapjoyException
    //   309	314	890	com/tapjoy/TapjoyException
    //   318	327	890	com/tapjoy/TapjoyException
    //   331	348	890	com/tapjoy/TapjoyException
    //   348	361	890	com/tapjoy/TapjoyException
    //   365	384	890	com/tapjoy/TapjoyException
    //   388	405	890	com/tapjoy/TapjoyException
    //   405	422	890	com/tapjoy/TapjoyException
    //   422	451	890	com/tapjoy/TapjoyException
    //   456	463	890	com/tapjoy/TapjoyException
    //   467	525	890	com/tapjoy/TapjoyException
    //   525	602	890	com/tapjoy/TapjoyException
    //   602	654	890	com/tapjoy/TapjoyException
    //   656	660	890	com/tapjoy/TapjoyException
    //   660	693	890	com/tapjoy/TapjoyException
    //   693	723	890	com/tapjoy/TapjoyException
    //   723	782	890	com/tapjoy/TapjoyException
    //   782	803	890	com/tapjoy/TapjoyException
    //   803	812	890	com/tapjoy/TapjoyException
    //   814	826	890	com/tapjoy/TapjoyException
    //   861	887	890	com/tapjoy/TapjoyException
    //   925	951	890	com/tapjoy/TapjoyException
    //   954	963	890	com/tapjoy/TapjoyException
    //   967	993	890	com/tapjoy/TapjoyException
    //   996	1013	890	com/tapjoy/TapjoyException
    //   1016	1033	890	com/tapjoy/TapjoyException
    //   1037	1063	890	com/tapjoy/TapjoyException
    //   292	305	924	java/lang/Exception
    //   309	314	924	java/lang/Exception
    //   318	327	924	java/lang/Exception
    //   331	348	924	java/lang/Exception
    //   467	525	966	java/lang/Exception
    //   602	654	1036	java/lang/Exception
    //   656	660	1036	java/lang/Exception
    //   996	1013	1036	java/lang/Exception
    //   1016	1033	1036	java/lang/Exception
    //   117	135	1066	java/lang/Exception
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
    //   4: invokestatic 803	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   7: astore 6
    //   9: aload 6
    //   11: astore 7
    //   13: aload 6
    //   15: invokevirtual 806	com/tapjoy/internal/bs:d	()Ljava/util/Map;
    //   18: astore 10
    //   20: aload 6
    //   22: astore 7
    //   24: aload 10
    //   26: ldc_w 808
    //   29: invokeinterface 811 2 0
    //   34: checkcast 364	java/lang/String
    //   37: invokestatic 813	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   40: astore 8
    //   42: aload 6
    //   44: astore 7
    //   46: aload 10
    //   48: ldc_w 815
    //   51: invokeinterface 811 2 0
    //   56: checkcast 364	java/lang/String
    //   59: invokestatic 813	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   62: astore 13
    //   64: aload 6
    //   66: astore 7
    //   68: aload 10
    //   70: ldc_w 817
    //   73: invokeinterface 811 2 0
    //   78: checkcast 364	java/lang/String
    //   81: invokestatic 813	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   84: astore 14
    //   86: aload 6
    //   88: astore 7
    //   90: aload 10
    //   92: ldc_w 819
    //   95: invokeinterface 811 2 0
    //   100: checkcast 364	java/lang/String
    //   103: invokestatic 813	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   106: astore 15
    //   108: aload 6
    //   110: astore 7
    //   112: aload 10
    //   114: ldc_w 821
    //   117: invokeinterface 811 2 0
    //   122: checkcast 364	java/lang/String
    //   125: invokestatic 813	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   128: astore 12
    //   130: aload 6
    //   132: astore 7
    //   134: aload 10
    //   136: ldc_w 823
    //   139: invokeinterface 811 2 0
    //   144: astore 11
    //   146: aload 6
    //   148: astore 7
    //   150: new 825	com/tapjoy/internal/er
    //   153: dup
    //   154: aload 14
    //   156: invokespecial 826	com/tapjoy/internal/er:<init>	(Ljava/lang/String;)V
    //   159: astore 16
    //   161: aload 6
    //   163: astore 7
    //   165: aload 16
    //   167: getfield 829	com/tapjoy/internal/er:a	Lcom/tapjoy/internal/er$a;
    //   170: getstatic 834	com/tapjoy/internal/er$a:RPC_ANALYTICS	Lcom/tapjoy/internal/er$a;
    //   173: if_acmpeq +44 -> 217
    //   176: aload 6
    //   178: astore 7
    //   180: new 794	java/io/IOException
    //   183: dup
    //   184: ldc_w 836
    //   187: invokespecial 837	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   190: athrow
    //   191: astore 7
    //   193: aload 6
    //   195: astore_0
    //   196: aload 7
    //   198: astore 6
    //   200: ldc_w 536
    //   203: aload 6
    //   205: invokevirtual 838	java/io/IOException:getMessage	()Ljava/lang/String;
    //   208: invokestatic 840	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   211: aload_0
    //   212: invokestatic 845	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   215: iconst_0
    //   216: ireturn
    //   217: aload 6
    //   219: astore 7
    //   221: aload 16
    //   223: getfield 847	com/tapjoy/internal/er:b	Ljava/lang/String;
    //   226: astore 9
    //   228: aload 6
    //   230: astore 7
    //   232: aload 9
    //   234: bipush 13
    //   236: ldc_w 849
    //   239: iconst_0
    //   240: bipush 11
    //   242: invokevirtual 853	java/lang/String:regionMatches	(ILjava/lang/String;II)Z
    //   245: ifeq +234 -> 479
    //   248: aload 6
    //   250: astore 7
    //   252: new 855	java/lang/StringBuffer
    //   255: dup
    //   256: invokespecial 856	java/lang/StringBuffer:<init>	()V
    //   259: aload 9
    //   261: iconst_0
    //   262: bipush 8
    //   264: invokevirtual 461	java/lang/String:substring	(II)Ljava/lang/String;
    //   267: invokevirtual 859	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   270: aload 9
    //   272: bipush 24
    //   274: bipush 30
    //   276: invokevirtual 461	java/lang/String:substring	(II)Ljava/lang/String;
    //   279: invokevirtual 859	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   282: aload 9
    //   284: bipush 9
    //   286: bipush 13
    //   288: invokevirtual 461	java/lang/String:substring	(II)Ljava/lang/String;
    //   291: invokevirtual 859	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   294: aload 9
    //   296: bipush 30
    //   298: invokevirtual 464	java/lang/String:substring	(I)Ljava/lang/String;
    //   301: invokevirtual 859	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   304: invokevirtual 860	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   307: astore 9
    //   309: aload 6
    //   311: astore 7
    //   313: aload 16
    //   315: getfield 861	com/tapjoy/internal/er:c	Ljava/lang/String;
    //   318: astore 16
    //   320: aload 8
    //   322: ifnonnull +505 -> 827
    //   325: aload 9
    //   327: astore 8
    //   329: aload 6
    //   331: astore 7
    //   333: invokestatic 866	com/tapjoy/internal/gc:a	()Lcom/tapjoy/internal/gc;
    //   336: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   339: aload 14
    //   341: ldc_w 395
    //   344: ldc_w 868
    //   347: aload 9
    //   349: aload 16
    //   351: invokevirtual 871	com/tapjoy/internal/gc:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   354: aload 6
    //   356: astore 7
    //   358: aload 8
    //   360: putstatic 220	com/tapjoy/TapjoyConnectCore:S	Ljava/lang/String;
    //   363: aload 6
    //   365: astore 7
    //   367: aload 13
    //   369: putstatic 222	com/tapjoy/TapjoyConnectCore:T	Ljava/lang/String;
    //   372: aload 6
    //   374: astore 7
    //   376: aload 14
    //   378: putstatic 224	com/tapjoy/TapjoyConnectCore:U	Ljava/lang/String;
    //   381: aload 6
    //   383: astore 7
    //   385: aload 15
    //   387: putstatic 226	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   390: aload 6
    //   392: astore 7
    //   394: new 526	java/util/ArrayList
    //   397: dup
    //   398: invokespecial 872	java/util/ArrayList:<init>	()V
    //   401: astore 8
    //   403: aload 12
    //   405: ifnull +112 -> 517
    //   408: aload 6
    //   410: astore 7
    //   412: aload 12
    //   414: ldc_w 719
    //   417: invokevirtual 876	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
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
    //   445: invokevirtual 782	java/lang/String:trim	()Ljava/lang/String;
    //   448: astore 12
    //   450: aload 6
    //   452: astore 7
    //   454: aload 12
    //   456: invokevirtual 457	java/lang/String:length	()I
    //   459: ifle +371 -> 830
    //   462: aload 6
    //   464: astore 7
    //   466: aload 8
    //   468: aload 12
    //   470: invokeinterface 877 2 0
    //   475: pop
    //   476: goto +354 -> 830
    //   479: aload 6
    //   481: astore 7
    //   483: new 879	java/lang/IllegalArgumentException
    //   486: dup
    //   487: ldc_w 881
    //   490: invokespecial 882	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   493: athrow
    //   494: astore_0
    //   495: aload 6
    //   497: astore 7
    //   499: ldc_w 536
    //   502: aload_0
    //   503: invokevirtual 883	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   506: invokestatic 840	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   509: aload 6
    //   511: invokestatic 845	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   514: goto -299 -> 215
    //   517: aload 6
    //   519: astore 7
    //   521: aload 8
    //   523: invokeinterface 886 1 0
    //   528: ifne +12 -> 540
    //   531: aload 6
    //   533: astore 7
    //   535: aload 8
    //   537: invokestatic 787	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   540: aload 6
    //   542: astore 7
    //   544: aload 6
    //   546: invokevirtual 889	com/tapjoy/internal/bs:close	()V
    //   549: iload_1
    //   550: ifne +108 -> 658
    //   553: aload 11
    //   555: instanceof 364
    //   558: istore_1
    //   559: iload_1
    //   560: ifeq +112 -> 672
    //   563: aload 11
    //   565: checkcast 364	java/lang/String
    //   568: invokevirtual 782	java/lang/String:trim	()Ljava/lang/String;
    //   571: invokestatic 895	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   574: lstore 4
    //   576: lload 4
    //   578: lconst_0
    //   579: lcmp
    //   580: ifgt +123 -> 703
    //   583: invokestatic 901	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   586: invokevirtual 904	com/tapjoy/TapjoyAppSettings:removeConnectResult	()V
    //   589: invokestatic 284	com/tapjoy/internal/fd:a	()Lcom/tapjoy/internal/fd;
    //   592: astore_0
    //   593: aload 10
    //   595: ldc_w 906
    //   598: invokeinterface 811 2 0
    //   603: astore 6
    //   605: aload 6
    //   607: instanceof 810
    //   610: istore_1
    //   611: iload_1
    //   612: ifeq +114 -> 726
    //   615: aload_0
    //   616: getfield 909	com/tapjoy/internal/fd:a	Lcom/tapjoy/internal/fb;
    //   619: aload 6
    //   621: checkcast 810	java/util/Map
    //   624: invokevirtual 914	com/tapjoy/internal/fb:a	(Ljava/util/Map;)V
    //   627: aload 6
    //   629: invokestatic 919	com/tapjoy/internal/bm:a	(Ljava/lang/Object;)Ljava/lang/String;
    //   632: astore 6
    //   634: aload_0
    //   635: invokevirtual 922	com/tapjoy/internal/fd:c	()Landroid/content/SharedPreferences;
    //   638: invokeinterface 512 1 0
    //   643: ldc_w 906
    //   646: aload 6
    //   648: invokeinterface 518 3 0
    //   653: invokeinterface 925 1 0
    //   658: aconst_null
    //   659: invokestatic 845	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   662: iconst_1
    //   663: ireturn
    //   664: astore 6
    //   666: lconst_0
    //   667: lstore 4
    //   669: goto -93 -> 576
    //   672: aload 11
    //   674: instanceof 927
    //   677: istore_1
    //   678: iload_1
    //   679: ifeq +18 -> 697
    //   682: aload 11
    //   684: checkcast 927	java/lang/Number
    //   687: invokevirtual 930	java/lang/Number:longValue	()J
    //   690: lstore 4
    //   692: goto -116 -> 576
    //   695: astore 6
    //   697: lconst_0
    //   698: lstore 4
    //   700: goto -124 -> 576
    //   703: invokestatic 901	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   706: aload_0
    //   707: invokestatic 932	com/tapjoy/TapjoyConnectCore:p	()Ljava/lang/String;
    //   710: lload 4
    //   712: ldc2_w 933
    //   715: lmul
    //   716: invokestatic 938	com/tapjoy/internal/y:b	()J
    //   719: ladd
    //   720: invokevirtual 942	com/tapjoy/TapjoyAppSettings:saveConnectResultAndParams	(Ljava/lang/String;Ljava/lang/String;J)V
    //   723: goto -134 -> 589
    //   726: aload 6
    //   728: ifnonnull -70 -> 658
    //   731: aload_0
    //   732: getfield 909	com/tapjoy/internal/fd:a	Lcom/tapjoy/internal/fb;
    //   735: aconst_null
    //   736: invokevirtual 914	com/tapjoy/internal/fb:a	(Ljava/util/Map;)V
    //   739: aload_0
    //   740: invokevirtual 922	com/tapjoy/internal/fd:c	()Landroid/content/SharedPreferences;
    //   743: invokeinterface 512 1 0
    //   748: ldc_w 906
    //   751: invokeinterface 946 2 0
    //   756: invokeinterface 925 1 0
    //   761: goto -103 -> 658
    //   764: astore_0
    //   765: aconst_null
    //   766: astore 6
    //   768: goto -273 -> 495
    //   771: astore_0
    //   772: aconst_null
    //   773: astore 6
    //   775: aload 6
    //   777: invokestatic 845	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
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
    //   1: invokestatic 803	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 950	com/tapjoy/internal/bs:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 952	com/tapjoy/internal/bs:s	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 536
    //   25: ldc_w 954
    //   28: invokestatic 651	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 889	com/tapjoy/internal/bs:close	()V
    //   37: aconst_null
    //   38: invokestatic 845	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 889	com/tapjoy/internal/bs:close	()V
    //   49: aconst_null
    //   50: invokestatic 845	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   53: ldc_w 536
    //   56: new 604	com/tapjoy/TapjoyErrorMessage
    //   59: dup
    //   60: getstatic 644	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   63: ldc_w 956
    //   66: invokespecial 614	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   69: invokestatic 617	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   72: iconst_0
    //   73: ireturn
    //   74: astore_2
    //   75: aconst_null
    //   76: astore_1
    //   77: aload_1
    //   78: astore_0
    //   79: ldc_w 536
    //   82: aload_2
    //   83: invokevirtual 838	java/io/IOException:getMessage	()Ljava/lang/String;
    //   86: invokestatic 840	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload_1
    //   90: invokestatic 845	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   93: goto -40 -> 53
    //   96: astore_2
    //   97: aconst_null
    //   98: astore_1
    //   99: aload_1
    //   100: astore_0
    //   101: ldc_w 536
    //   104: aload_2
    //   105: invokevirtual 883	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   108: invokestatic 840	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   111: aload_1
    //   112: invokestatic 845	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   115: goto -62 -> 53
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_0
    //   121: aload_0
    //   122: invokestatic 845	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
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
      gc.a().a(g, h, "11.12.2", "https://rpc.tapjoy.com/", M, L);
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
    Object localObject = new HashMap();
    TapjoyUtil.safePut((Map)localObject, "plugin", N, true);
    TapjoyUtil.safePut((Map)localObject, "sdk_type", O, true);
    TapjoyUtil.safePut((Map)localObject, "app_id", v, true);
    TapjoyUtil.safePut((Map)localObject, "library_version", x, true);
    TapjoyUtil.safePut((Map)localObject, "library_revision", "0859594bd", true);
    TapjoyUtil.safePut((Map)localObject, "bridge_version", y, true);
    TapjoyUtil.safePut((Map)localObject, "app_version", w, true);
    localHashMap2.putAll((Map)localObject);
    localObject = new HashMap();
    TapjoyUtil.safePut((Map)localObject, "device_name", r, true);
    TapjoyUtil.safePut((Map)localObject, "platform", D, true);
    TapjoyUtil.safePut((Map)localObject, "os_version", u, true);
    TapjoyUtil.safePut((Map)localObject, "device_manufacturer", s, true);
    TapjoyUtil.safePut((Map)localObject, "device_type", t, true);
    TapjoyUtil.safePut((Map)localObject, "screen_layout_size", B, true);
    TapjoyUtil.safePut((Map)localObject, "store_name", K, true);
    TapjoyUtil.safePut((Map)localObject, "store_view", String.valueOf(R), true);
    TapjoyUtil.safePut((Map)localObject, "carrier_name", E, true);
    TapjoyUtil.safePut((Map)localObject, "carrier_country_code", F, true);
    TapjoyUtil.safePut((Map)localObject, "mobile_network_code", H, true);
    TapjoyUtil.safePut((Map)localObject, "mobile_country_code", G, true);
    TapjoyUtil.safePut((Map)localObject, "country_code", Locale.getDefault().getCountry(), true);
    TapjoyUtil.safePut((Map)localObject, "language_code", Locale.getDefault().getLanguage(), true);
    I = getConnectionType();
    TapjoyUtil.safePut((Map)localObject, "connection_type", I, true);
    J = getConnectionSubType();
    TapjoyUtil.safePut((Map)localObject, "connection_subtype", J, true);
    TapjoyUtil.safePut((Map)localObject, "screen_density", z, true);
    localHashMap2.putAll((Map)localObject);
    localObject = new HashMap();
    if (l())
    {
      TapjoyUtil.safePut((Map)localObject, "advertising_id", c, true);
      TapjoyUtil.safePut((Map)localObject, "ad_tracking_enabled", String.valueOf(d), true);
    }
    if (!m())
    {
      TapjoyUtil.safePut((Map)localObject, "android_id", n, true);
      TapjoyUtil.safePut((Map)localObject, "mac_address", p, true);
    }
    TapjoyUtil.safePut((Map)localObject, "install_id", q, true);
    TapjoyUtil.safePut((Map)localObject, "publisher_user_id", C, true);
    TapjoyUtil.safePut((Map)localObject, "ad_id_check_disabled", e, true);
    TapjoyUtil.safePut((Map)localObject, "persistent_ids_disabled", f, true);
    if (a != 0) {
      TapjoyUtil.safePut((Map)localObject, "packaged_gps_version", Integer.toString(a), true);
    }
    if (b != 0) {
      TapjoyUtil.safePut((Map)localObject, "device_gps_version", Integer.toString(b), true);
    }
    fh localFh;
    HashMap localHashMap3;
    if ((o == null) || (o.length() == 0) || (System.currentTimeMillis() - Z > 1800000L))
    {
      o = n();
      TapjoyUtil.safePut((Map)localObject, "session_id", o, true);
      localHashMap2.putAll((Map)localObject);
      localObject = new HashMap();
      TapjoyUtil.safePut((Map)localObject, "app_group_id", S, true);
      TapjoyUtil.safePut((Map)localObject, "store", T, true);
      TapjoyUtil.safePut((Map)localObject, "analytics_api_key", U, true);
      TapjoyUtil.safePut((Map)localObject, "managed_device_id", V, true);
      localHashMap2.putAll((Map)localObject);
      localFh = fh.a();
      localHashMap3 = new HashMap();
      if (localFh.a != null) {
        if (!localFh.a.booleanValue()) {
          break label973;
        }
      }
    }
    label973:
    for (localObject = "1";; localObject = "0")
    {
      TapjoyUtil.safePut(localHashMap3, "gdpr", (String)localObject, true);
      if (!aq.a(localFh.b)) {
        TapjoyUtil.safePut(localHashMap3, "cgdpr", localFh.b, true);
      }
      localHashMap2.putAll(localHashMap3);
      if ((TapjoyCache.getInstance() != null) && (TapjoyCache.getInstance().getCachedOfferIDs() != null) && (TapjoyCache.getInstance().getCachedOfferIDs().length() > 0)) {
        TapjoyUtil.safePut(localHashMap2, "cached_ids", TapjoyCache.getInstance().getCachedOfferIDs(), true);
      }
      TapjoyUtil.safePut(localHashMap2, "display_multiplier", Float.toString(Q), true);
      localHashMap1.putAll(localHashMap2);
      localObject = new HashMap();
      g();
      localHashMap2 = new HashMap();
      TapjoyUtil.safePut(localHashMap2, "analytics_id", ah, true);
      TapjoyUtil.safePut(localHashMap2, "pkg_id", ai, true);
      TapjoyUtil.safePut(localHashMap2, "pkg_sign", aj, true);
      TapjoyUtil.safePut(localHashMap2, "display_d", aJ);
      TapjoyUtil.safePut(localHashMap2, "display_w", aK);
      TapjoyUtil.safePut(localHashMap2, "display_h", aL);
      TapjoyUtil.safePut(localHashMap2, "country_sim", aM, true);
      TapjoyUtil.safePut(localHashMap2, "timezone", aN, true);
      ((Map)localObject).putAll(localHashMap2);
      localHashMap2 = new HashMap();
      TapjoyUtil.safePut(localHashMap2, "pkg_ver", ak, true);
      TapjoyUtil.safePut(localHashMap2, "pkg_rev", al);
      TapjoyUtil.safePut(localHashMap2, "pkg_data_ver", am, true);
      TapjoyUtil.safePut(localHashMap2, "installer", an, true);
      if (ct.c(K)) {
        TapjoyUtil.safePut(localHashMap2, "store_name", aO, true);
      }
      ((Map)localObject).putAll(localHashMap2);
      ((Map)localObject).putAll(f());
      localHashMap1.putAll((Map)localObject);
      return localHashMap1;
      Z = System.currentTimeMillis();
      break;
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
    Object localObject1 = gc.a(g).a(true);
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
    //   0: getstatic 296	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   3: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   6: invokevirtual 317	android/content/Context:getPackageName	()Ljava/lang/String;
    //   9: iconst_1
    //   10: invokevirtual 373	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   13: getfield 1511	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
    //   16: invokestatic 146	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   19: astore_2
    //   20: aload_2
    //   21: ifnull +408 -> 429
    //   24: aload_2
    //   25: invokeinterface 697 1 0
    //   30: astore_2
    //   31: aload_2
    //   32: invokeinterface 702 1 0
    //   37: ifeq +392 -> 429
    //   40: aload_2
    //   41: invokeinterface 706 1 0
    //   46: checkcast 1513	android/content/pm/ActivityInfo
    //   49: astore_3
    //   50: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   53: aload_3
    //   54: getfield 1516	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   57: invokevirtual 1517	java/util/Vector:contains	(Ljava/lang/Object;)Z
    //   60: ifeq -29 -> 31
    //   63: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   66: aload_3
    //   67: getfield 1516	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   70: invokevirtual 1520	java/util/Vector:indexOf	(Ljava/lang/Object;)I
    //   73: istore_1
    //   74: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   77: iload_1
    //   78: invokevirtual 1523	java/util/Vector:get	(I)Ljava/lang/Object;
    //   81: checkcast 364	java/lang/String
    //   84: invokestatic 1529	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   87: pop
    //   88: new 134	java/util/Vector
    //   91: dup
    //   92: invokespecial 773	java/util/Vector:<init>	()V
    //   95: astore 4
    //   97: aload_3
    //   98: getfield 1532	android/content/pm/ActivityInfo:configChanges	I
    //   101: sipush 128
    //   104: iand
    //   105: sipush 128
    //   108: if_icmpeq +12 -> 120
    //   111: aload 4
    //   113: ldc_w 1534
    //   116: invokevirtual 785	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   119: pop
    //   120: aload_3
    //   121: getfield 1532	android/content/pm/ActivityInfo:configChanges	I
    //   124: bipush 32
    //   126: iand
    //   127: bipush 32
    //   129: if_icmpeq +12 -> 141
    //   132: aload 4
    //   134: ldc_w 1536
    //   137: invokevirtual 785	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   140: pop
    //   141: aload 4
    //   143: invokevirtual 1537	java/util/Vector:size	()I
    //   146: ifeq +149 -> 295
    //   149: aload 4
    //   151: invokevirtual 1537	java/util/Vector:size	()I
    //   154: iconst_1
    //   155: if_icmpne +95 -> 250
    //   158: new 259	com/tapjoy/TapjoyIntegrationException
    //   161: dup
    //   162: new 479	java/lang/StringBuilder
    //   165: dup
    //   166: invokespecial 480	java/lang/StringBuilder:<init>	()V
    //   169: aload 4
    //   171: invokevirtual 1538	java/util/Vector:toString	()Ljava/lang/String;
    //   174: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: ldc_w 1540
    //   180: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   186: iload_1
    //   187: invokevirtual 1523	java/util/Vector:get	(I)Ljava/lang/Object;
    //   190: checkcast 364	java/lang/String
    //   193: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: invokespecial 1541	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   202: athrow
    //   203: astore_2
    //   204: new 259	com/tapjoy/TapjoyIntegrationException
    //   207: dup
    //   208: new 479	java/lang/StringBuilder
    //   211: dup
    //   212: ldc_w 1543
    //   215: invokespecial 541	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   218: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   221: iload_1
    //   222: invokevirtual 1523	java/util/Vector:get	(I)Ljava/lang/Object;
    //   225: checkcast 364	java/lang/String
    //   228: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   234: invokespecial 1541	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   237: athrow
    //   238: astore_2
    //   239: new 259	com/tapjoy/TapjoyIntegrationException
    //   242: dup
    //   243: ldc_w 1545
    //   246: invokespecial 1541	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   249: athrow
    //   250: new 259	com/tapjoy/TapjoyIntegrationException
    //   253: dup
    //   254: new 479	java/lang/StringBuilder
    //   257: dup
    //   258: invokespecial 480	java/lang/StringBuilder:<init>	()V
    //   261: aload 4
    //   263: invokevirtual 1538	java/util/Vector:toString	()Ljava/lang/String;
    //   266: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: ldc_w 1547
    //   272: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   278: iload_1
    //   279: invokevirtual 1523	java/util/Vector:get	(I)Ljava/lang/Object;
    //   282: checkcast 364	java/lang/String
    //   285: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   291: invokespecial 1541	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   294: athrow
    //   295: getstatic 400	android/os/Build$VERSION:SDK_INT	I
    //   298: bipush 13
    //   300: if_icmplt +49 -> 349
    //   303: aload_3
    //   304: getfield 1532	android/content/pm/ActivityInfo:configChanges	I
    //   307: sipush 1024
    //   310: iand
    //   311: sipush 1024
    //   314: if_icmpeq +35 -> 349
    //   317: ldc_w 536
    //   320: new 479	java/lang/StringBuilder
    //   323: dup
    //   324: ldc_w 1549
    //   327: invokespecial 541	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   330: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   333: iload_1
    //   334: invokevirtual 1523	java/util/Vector:get	(I)Ljava/lang/Object;
    //   337: checkcast 364	java/lang/String
    //   340: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   346: invokestatic 546	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   349: getstatic 400	android/os/Build$VERSION:SDK_INT	I
    //   352: bipush 11
    //   354: if_icmplt +64 -> 418
    //   357: aload_3
    //   358: getfield 1516	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   361: ldc_w 1551
    //   364: invokevirtual 658	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   367: ifeq +51 -> 418
    //   370: aload_3
    //   371: getfield 1552	android/content/pm/ActivityInfo:flags	I
    //   374: sipush 512
    //   377: iand
    //   378: sipush 512
    //   381: if_icmpeq +37 -> 418
    //   384: new 259	com/tapjoy/TapjoyIntegrationException
    //   387: dup
    //   388: new 479	java/lang/StringBuilder
    //   391: dup
    //   392: ldc_w 1554
    //   395: invokespecial 541	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   398: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   401: iload_1
    //   402: invokevirtual 1523	java/util/Vector:get	(I)Ljava/lang/Object;
    //   405: checkcast 364	java/lang/String
    //   408: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   414: invokespecial 1541	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   417: athrow
    //   418: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   421: iload_1
    //   422: invokevirtual 1556	java/util/Vector:remove	(I)Ljava/lang/Object;
    //   425: pop
    //   426: goto -395 -> 31
    //   429: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   432: invokevirtual 1537	java/util/Vector:size	()I
    //   435: ifeq +103 -> 538
    //   438: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   441: invokevirtual 1537	java/util/Vector:size	()I
    //   444: iconst_1
    //   445: if_icmpne +48 -> 493
    //   448: new 259	com/tapjoy/TapjoyIntegrationException
    //   451: dup
    //   452: new 479	java/lang/StringBuilder
    //   455: dup
    //   456: ldc_w 1558
    //   459: invokespecial 541	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   462: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   465: invokevirtual 1537	java/util/Vector:size	()I
    //   468: invokevirtual 1005	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   471: ldc_w 1560
    //   474: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   477: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   480: invokevirtual 1538	java/util/Vector:toString	()Ljava/lang/String;
    //   483: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   489: invokespecial 1541	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   492: athrow
    //   493: new 259	com/tapjoy/TapjoyIntegrationException
    //   496: dup
    //   497: new 479	java/lang/StringBuilder
    //   500: dup
    //   501: ldc_w 1558
    //   504: invokespecial 541	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   507: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   510: invokevirtual 1537	java/util/Vector:size	()I
    //   513: invokevirtual 1005	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   516: ldc_w 1562
    //   519: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   522: getstatic 152	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   525: invokevirtual 1538	java/util/Vector:toString	()Ljava/lang/String;
    //   528: invokevirtual 493	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   534: invokespecial 1541	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   537: athrow
    //   538: invokestatic 1564	com/tapjoy/TapjoyConnectCore:k	()V
    //   541: ldc_w 1566
    //   544: invokestatic 1529	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   547: astore_2
    //   548: aload_2
    //   549: ldc_w 1568
    //   552: iconst_1
    //   553: anewarray 1525	java/lang/Class
    //   556: dup
    //   557: iconst_0
    //   558: ldc_w 626
    //   561: aastore
    //   562: invokevirtual 1572	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   565: pop
    //   566: ldc_w 1574
    //   569: invokestatic 1577	com/tapjoy/TapjoyUtil:getResource	(Ljava/lang/String;)Ljava/lang/Object;
    //   572: checkcast 364	java/lang/String
    //   575: astore_2
    //   576: aload_2
    //   577: ifnonnull +178 -> 755
    //   580: ldc_w 1579
    //   583: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   586: invokestatic 1583	com/tapjoy/TapjoyUtil:copyTextFromJarIntoString	(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   589: astore_2
    //   590: aload_2
    //   591: ifnonnull +161 -> 752
    //   594: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   597: invokevirtual 311	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   600: ldc_w 1585
    //   603: ldc_w 1587
    //   606: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   609: invokevirtual 317	android/content/Context:getPackageName	()Ljava/lang/String;
    //   612: invokevirtual 323	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   615: istore_1
    //   616: getstatic 122	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   619: invokevirtual 311	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   622: iload_1
    //   623: invokevirtual 330	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   626: astore_3
    //   627: aload_3
    //   628: invokevirtual 1592	java/io/InputStream:available	()I
    //   631: newarray byte
    //   633: astore 4
    //   635: aload_3
    //   636: aload 4
    //   638: invokevirtual 1596	java/io/InputStream:read	([B)I
    //   641: pop
    //   642: new 364	java/lang/String
    //   645: dup
    //   646: aload 4
    //   648: invokespecial 1599	java/lang/String:<init>	([B)V
    //   651: astore_3
    //   652: ldc_w 1574
    //   655: aload_3
    //   656: invokestatic 1603	com/tapjoy/TapjoyUtil:setResource	(Ljava/lang/String;Ljava/lang/Object;)V
    //   659: aload_3
    //   660: astore_2
    //   661: aload_2
    //   662: ifnonnull +38 -> 700
    //   665: new 259	com/tapjoy/TapjoyIntegrationException
    //   668: dup
    //   669: ldc_w 1605
    //   672: invokespecial 1541	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   675: athrow
    //   676: astore_2
    //   677: new 259	com/tapjoy/TapjoyIntegrationException
    //   680: dup
    //   681: ldc_w 1607
    //   684: invokespecial 1541	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   687: athrow
    //   688: astore_2
    //   689: new 259	com/tapjoy/TapjoyIntegrationException
    //   692: dup
    //   693: ldc_w 1609
    //   696: invokespecial 1541	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   699: athrow
    //   700: ldc_w 578
    //   703: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   706: ifnull +28 -> 734
    //   709: ldc_w 578
    //   712: invokestatic 343	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   715: ldc_w 791
    //   718: invokevirtual 658	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   721: ifeq +13 -> 734
    //   724: ldc_w 536
    //   727: ldc_w 1611
    //   730: invokestatic 584	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   733: return
    //   734: aload_0
    //   735: getfield 302	com/tapjoy/TapjoyConnectCore:ad	Lcom/tapjoy/TapjoyGpsHelper;
    //   738: invokevirtual 1614	com/tapjoy/TapjoyGpsHelper:checkGooglePlayIntegration	()V
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
    gc.a(paramContext).j = paramString;
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
    new Thread(new TapjoyConnectCore.2()).start();
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
    new Thread(new TapjoyConnectCore.PPAThread(this, localMap)).start();
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
    new Thread(new TapjoyConnectCore.1(this)).start();
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
}
