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
import com.tapjoy.internal.gf;
import com.tapjoy.internal.gm;
import com.tapjoy.internal.gq;
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
    //   1: invokespecial 259	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 261	com/tapjoy/TapjoyConnectCore:X	J
    //   9: iconst_0
    //   10: istore_3
    //   11: aload_0
    //   12: iconst_0
    //   13: putfield 263	com/tapjoy/TapjoyConnectCore:Y	Z
    //   16: aload_0
    //   17: iconst_0
    //   18: putfield 265	com/tapjoy/TapjoyConnectCore:aa	Z
    //   21: aload_1
    //   22: putstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   25: invokestatic 272	com/tapjoy/internal/fh:a	()Lcom/tapjoy/internal/fh;
    //   28: aload_1
    //   29: invokevirtual 274	com/tapjoy/internal/fh:a	(Landroid/content/Context;)V
    //   32: invokestatic 279	com/tapjoy/internal/fd:a	()Lcom/tapjoy/internal/fd;
    //   35: aload_1
    //   36: invokevirtual 280	com/tapjoy/internal/fd:a	(Landroid/content/Context;)V
    //   39: new 282	com/tapjoy/TapjoyURLConnection
    //   42: dup
    //   43: invokespecial 283	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   46: putstatic 285	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   49: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   52: invokevirtual 291	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   55: putstatic 293	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   58: aload_0
    //   59: new 295	com/tapjoy/TapjoyGpsHelper
    //   62: dup
    //   63: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   66: invokespecial 297	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   69: putfield 299	com/tapjoy/TapjoyConnectCore:ad	Lcom/tapjoy/TapjoyGpsHelper;
    //   72: getstatic 240	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Hashtable;
    //   75: ifnonnull +13 -> 88
    //   78: new 301	java/util/Hashtable
    //   81: dup
    //   82: invokespecial 302	java/util/Hashtable:<init>	()V
    //   85: putstatic 240	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Hashtable;
    //   88: invokestatic 304	com/tapjoy/TapjoyConnectCore:i	()V
    //   91: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   94: invokevirtual 308	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   97: ldc_w 310
    //   100: aconst_null
    //   101: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   104: invokevirtual 314	android/content/Context:getPackageName	()Ljava/lang/String;
    //   107: invokevirtual 320	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   110: istore_2
    //   111: new 322	java/util/Properties
    //   114: dup
    //   115: invokespecial 323	java/util/Properties:<init>	()V
    //   118: astore_1
    //   119: aload_1
    //   120: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   123: invokevirtual 308	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   126: iload_2
    //   127: invokevirtual 327	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   130: invokevirtual 331	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   133: aload_1
    //   134: invokestatic 334	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   137: ldc_w 336
    //   140: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   143: invokestatic 345	com/tapjoy/internal/ct:c	(Ljava/lang/String;)Z
    //   146: ifeq +7 -> 153
    //   149: aload_0
    //   150: invokespecial 347	com/tapjoy/TapjoyConnectCore:j	()V
    //   153: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   156: invokevirtual 351	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   159: ldc_w 353
    //   162: invokestatic 359	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   165: astore_1
    //   166: aload_1
    //   167: putstatic 149	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   170: aload_1
    //   171: ifnull +12 -> 183
    //   174: getstatic 149	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   177: invokevirtual 364	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   180: putstatic 149	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   183: getstatic 293	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   186: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   189: invokevirtual 314	android/content/Context:getPackageName	()Ljava/lang/String;
    //   192: iconst_0
    //   193: invokevirtual 370	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   196: getfield 375	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   199: putstatic 167	com/tapjoy/TapjoyConnectCore:w	Ljava/lang/String;
    //   202: ldc_w 377
    //   205: putstatic 161	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   208: ldc_w 377
    //   211: putstatic 181	com/tapjoy/TapjoyConnectCore:D	Ljava/lang/String;
    //   214: getstatic 382	android/os/Build:MODEL	Ljava/lang/String;
    //   217: putstatic 157	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   220: getstatic 385	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   223: putstatic 159	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   226: getstatic 390	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   229: putstatic 163	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   232: ldc_w 392
    //   235: putstatic 169	com/tapjoy/TapjoyConnectCore:x	Ljava/lang/String;
    //   238: ldc_w 394
    //   241: putstatic 171	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   244: getstatic 397	android/os/Build$VERSION:SDK_INT	I
    //   247: iconst_3
    //   248: if_icmple +72 -> 320
    //   251: new 399	com/tapjoy/TapjoyDisplayMetricsUtil
    //   254: dup
    //   255: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   258: invokespecial 400	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   261: astore_1
    //   262: aload_1
    //   263: invokevirtual 404	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   266: putstatic 173	com/tapjoy/TapjoyConnectCore:z	I
    //   269: aload_1
    //   270: invokevirtual 408	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   273: putstatic 175	com/tapjoy/TapjoyConnectCore:A	F
    //   276: aload_1
    //   277: invokevirtual 411	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   280: putstatic 177	com/tapjoy/TapjoyConnectCore:B	I
    //   283: goto +37 -> 320
    //   286: astore_1
    //   287: new 413	java/lang/StringBuilder
    //   290: dup
    //   291: ldc_w 415
    //   294: invokespecial 418	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   297: astore 5
    //   299: aload 5
    //   301: aload_1
    //   302: invokevirtual 421	java/lang/Exception:toString	()Ljava/lang/String;
    //   305: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: pop
    //   309: ldc_w 427
    //   312: aload 5
    //   314: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   317: invokestatic 433	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   320: ldc_w 435
    //   323: invokestatic 437	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   326: istore 4
    //   328: iload 4
    //   330: ifeq +99 -> 429
    //   333: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   336: ldc_w 439
    //   339: invokevirtual 443	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   342: checkcast 445	android/net/wifi/WifiManager
    //   345: astore_1
    //   346: aload_1
    //   347: ifnull +91 -> 438
    //   350: aload_1
    //   351: invokevirtual 449	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   354: astore_1
    //   355: aload_1
    //   356: ifnull +82 -> 438
    //   359: aload_1
    //   360: invokevirtual 454	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   363: astore_1
    //   364: aload_1
    //   365: putstatic 153	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   368: aload_1
    //   369: ifnull +69 -> 438
    //   372: getstatic 153	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   375: ldc_w 456
    //   378: ldc -109
    //   380: invokevirtual 460	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   383: invokevirtual 364	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   386: putstatic 153	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   389: goto +49 -> 438
    //   392: astore_1
    //   393: new 413	java/lang/StringBuilder
    //   396: dup
    //   397: ldc_w 462
    //   400: invokespecial 418	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   403: astore 5
    //   405: aload 5
    //   407: aload_1
    //   408: invokevirtual 421	java/lang/Exception:toString	()Ljava/lang/String;
    //   411: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   414: pop
    //   415: ldc_w 427
    //   418: aload 5
    //   420: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   423: invokestatic 433	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   426: goto +12 -> 438
    //   429: ldc_w 427
    //   432: ldc_w 464
    //   435: invokestatic 466	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   438: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   441: ldc_w 468
    //   444: invokevirtual 443	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   447: checkcast 470	android/telephony/TelephonyManager
    //   450: astore_1
    //   451: aload_1
    //   452: ifnull +60 -> 512
    //   455: aload_1
    //   456: invokevirtual 473	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   459: putstatic 183	com/tapjoy/TapjoyConnectCore:E	Ljava/lang/String;
    //   462: aload_1
    //   463: invokevirtual 476	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   466: putstatic 185	com/tapjoy/TapjoyConnectCore:F	Ljava/lang/String;
    //   469: aload_1
    //   470: invokevirtual 479	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   473: astore_1
    //   474: aload_1
    //   475: ifnull +37 -> 512
    //   478: aload_1
    //   479: invokevirtual 482	java/lang/String:length	()I
    //   482: iconst_5
    //   483: if_icmpeq +12 -> 495
    //   486: aload_1
    //   487: invokevirtual 482	java/lang/String:length	()I
    //   490: bipush 6
    //   492: if_icmpne +20 -> 512
    //   495: aload_1
    //   496: iconst_0
    //   497: iconst_3
    //   498: invokevirtual 486	java/lang/String:substring	(II)Ljava/lang/String;
    //   501: putstatic 187	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   504: aload_1
    //   505: iconst_3
    //   506: invokevirtual 489	java/lang/String:substring	(I)Ljava/lang/String;
    //   509: putstatic 189	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   512: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   515: ldc_w 491
    //   518: iconst_0
    //   519: invokevirtual 495	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   522: astore_1
    //   523: aload_1
    //   524: ldc_w 497
    //   527: ldc -109
    //   529: invokeinterface 502 3 0
    //   534: astore 5
    //   536: aload 5
    //   538: putstatic 155	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   541: aload 5
    //   543: ifnull +14 -> 557
    //   546: getstatic 155	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   549: invokevirtual 482	java/lang/String:length	()I
    //   552: istore_2
    //   553: iload_2
    //   554: ifne +108 -> 662
    //   557: new 413	java/lang/StringBuilder
    //   560: dup
    //   561: invokespecial 503	java/lang/StringBuilder:<init>	()V
    //   564: astore 5
    //   566: aload 5
    //   568: invokestatic 509	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   571: invokevirtual 510	java/util/UUID:toString	()Ljava/lang/String;
    //   574: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   577: pop
    //   578: aload 5
    //   580: invokestatic 516	java/lang/System:currentTimeMillis	()J
    //   583: invokevirtual 519	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   586: pop
    //   587: aload 5
    //   589: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   592: invokestatic 524	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   595: putstatic 155	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   598: aload_1
    //   599: invokeinterface 528 1 0
    //   604: astore_1
    //   605: aload_1
    //   606: ldc_w 497
    //   609: getstatic 155	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   612: invokeinterface 534 3 0
    //   617: pop
    //   618: aload_1
    //   619: invokeinterface 538 1 0
    //   624: pop
    //   625: goto +37 -> 662
    //   628: astore_1
    //   629: new 413	java/lang/StringBuilder
    //   632: dup
    //   633: ldc_w 540
    //   636: invokespecial 418	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   639: astore 5
    //   641: aload 5
    //   643: aload_1
    //   644: invokevirtual 421	java/lang/Exception:toString	()Ljava/lang/String;
    //   647: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   650: pop
    //   651: ldc_w 427
    //   654: aload 5
    //   656: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   659: invokestatic 433	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   662: ldc_w 542
    //   665: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   668: ifnull +75 -> 743
    //   671: ldc_w 542
    //   674: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   677: invokevirtual 482	java/lang/String:length	()I
    //   680: ifle +63 -> 743
    //   683: ldc_w 542
    //   686: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   689: putstatic 195	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   692: new 544	java/util/ArrayList
    //   695: dup
    //   696: getstatic 547	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   699: invokestatic 139	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   702: invokespecial 548	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   705: getstatic 195	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   708: invokevirtual 552	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   711: ifne +32 -> 743
    //   714: new 413	java/lang/StringBuilder
    //   717: dup
    //   718: ldc_w 554
    //   721: invokespecial 418	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   724: astore_1
    //   725: aload_1
    //   726: getstatic 195	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   729: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   732: pop
    //   733: ldc_w 427
    //   736: aload_1
    //   737: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   740: invokestatic 556	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   743: getstatic 195	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   746: astore_1
    //   747: new 558	android/content/Intent
    //   750: dup
    //   751: ldc_w 560
    //   754: invokespecial 561	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   757: astore 5
    //   759: aload_1
    //   760: invokevirtual 482	java/lang/String:length	()I
    //   763: ifgt +37 -> 800
    //   766: aload 5
    //   768: ldc_w 563
    //   771: invokestatic 569	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   774: invokevirtual 573	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   777: pop
    //   778: getstatic 293	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   781: aload 5
    //   783: iconst_0
    //   784: invokevirtual 577	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   787: invokeinterface 582 1 0
    //   792: ifle +45 -> 837
    //   795: iconst_1
    //   796: istore_3
    //   797: goto +40 -> 837
    //   800: aload_1
    //   801: ldc_w 584
    //   804: invokevirtual 587	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   807: ifeq +13 -> 820
    //   810: ldc_w 589
    //   813: invokestatic 591	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   816: istore_3
    //   817: goto +20 -> 837
    //   820: aload_1
    //   821: ldc_w 593
    //   824: invokevirtual 587	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   827: ifeq +10 -> 837
    //   830: ldc_w 595
    //   833: invokestatic 591	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   836: istore_3
    //   837: iload_3
    //   838: putstatic 211	com/tapjoy/TapjoyConnectCore:R	Z
    //   841: goto +37 -> 878
    //   844: astore_1
    //   845: new 413	java/lang/StringBuilder
    //   848: dup
    //   849: ldc_w 597
    //   852: invokespecial 418	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   855: astore 5
    //   857: aload 5
    //   859: aload_1
    //   860: invokevirtual 421	java/lang/Exception:toString	()Ljava/lang/String;
    //   863: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   866: pop
    //   867: ldc_w 427
    //   870: aload 5
    //   872: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   875: invokestatic 433	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   878: invokestatic 599	com/tapjoy/TapjoyConnectCore:g	()V
    //   881: ldc_w 601
    //   884: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   887: ifnull +24 -> 911
    //   890: ldc_w 601
    //   893: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   896: invokevirtual 482	java/lang/String:length	()I
    //   899: ifle +12 -> 911
    //   902: ldc_w 601
    //   905: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   908: putstatic 233	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   911: ldc_w 603
    //   914: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   917: ifnull +24 -> 941
    //   920: ldc_w 603
    //   923: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   926: invokevirtual 482	java/lang/String:length	()I
    //   929: ifle +12 -> 941
    //   932: ldc_w 603
    //   935: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   938: putstatic 231	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   941: ldc_w 605
    //   944: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   947: ifnull +57 -> 1004
    //   950: ldc_w 605
    //   953: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   956: invokevirtual 482	java/lang/String:length	()I
    //   959: ifle +45 -> 1004
    //   962: new 413	java/lang/StringBuilder
    //   965: dup
    //   966: ldc_w 607
    //   969: invokespecial 418	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   972: astore_1
    //   973: aload_1
    //   974: ldc_w 605
    //   977: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   980: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   983: pop
    //   984: ldc_w 427
    //   987: aload_1
    //   988: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   991: invokestatic 609	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   994: ldc_w 605
    //   997: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1000: aconst_null
    //   1001: invokestatic 613	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;Lcom/tapjoy/TJSetUserIDListener;)V
    //   1004: ldc_w 615
    //   1007: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1010: invokestatic 618	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   1013: putstatic 207	com/tapjoy/TapjoyConnectCore:P	Ljava/lang/String;
    //   1016: getstatic 240	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Hashtable;
    //   1019: ifnull +6 -> 1025
    //   1022: invokestatic 620	com/tapjoy/TapjoyConnectCore:h	()V
    //   1025: aload_0
    //   1026: invokevirtual 623	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   1029: aload_0
    //   1030: iconst_1
    //   1031: putfield 265	com/tapjoy/TapjoyConnectCore:aa	Z
    //   1034: return
    //   1035: astore_1
    //   1036: new 254	com/tapjoy/TapjoyException
    //   1039: dup
    //   1040: aload_1
    //   1041: invokevirtual 626	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   1044: invokespecial 627	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   1047: athrow
    //   1048: astore_1
    //   1049: ldc_w 427
    //   1052: new 629	com/tapjoy/TapjoyErrorMessage
    //   1055: dup
    //   1056: getstatic 635	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1059: aload_1
    //   1060: invokevirtual 636	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   1063: invokespecial 639	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1066: invokestatic 642	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1069: invokestatic 644	com/tapjoy/TapjoyConnectCore:d	()V
    //   1072: getstatic 649	com/tapjoy/internal/ev:b	Lcom/tapjoy/internal/ev$a;
    //   1075: getstatic 655	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1078: invokevirtual 661	com/tapjoy/internal/ev$a:notifyObservers	(Ljava/lang/Object;)V
    //   1081: return
    //   1082: astore_1
    //   1083: ldc_w 427
    //   1086: new 629	com/tapjoy/TapjoyErrorMessage
    //   1089: dup
    //   1090: getstatic 664	com/tapjoy/TapjoyErrorMessage$ErrorType:INTEGRATION_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1093: aload_1
    //   1094: invokevirtual 665	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   1097: invokespecial 639	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1100: invokestatic 642	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1103: invokestatic 644	com/tapjoy/TapjoyConnectCore:d	()V
    //   1106: getstatic 649	com/tapjoy/internal/ev:b	Lcom/tapjoy/internal/ev$a;
    //   1109: getstatic 655	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1112: invokevirtual 661	com/tapjoy/internal/ev$a:notifyObservers	(Ljava/lang/Object;)V
    //   1115: return
    //   1116: astore_1
    //   1117: goto -980 -> 137
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1120	0	this	TapjoyConnectCore
    //   0	1120	1	paramContext	Context
    //   110	444	2	i1	int
    //   10	828	3	bool1	boolean
    //   326	3	4	bool2	boolean
    //   297	574	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   244	283	286	java/lang/Exception
    //   333	346	392	java/lang/Exception
    //   350	355	392	java/lang/Exception
    //   359	368	392	java/lang/Exception
    //   372	389	392	java/lang/Exception
    //   557	625	628	java/lang/Exception
    //   743	778	844	java/lang/Exception
    //   778	795	844	java/lang/Exception
    //   800	817	844	java/lang/Exception
    //   820	837	844	java/lang/Exception
    //   837	841	844	java/lang/Exception
    //   183	202	1035	android/content/pm/PackageManager$NameNotFoundException
    //   72	88	1048	com/tapjoy/TapjoyException
    //   88	119	1048	com/tapjoy/TapjoyException
    //   119	137	1048	com/tapjoy/TapjoyException
    //   137	153	1048	com/tapjoy/TapjoyException
    //   153	170	1048	com/tapjoy/TapjoyException
    //   174	183	1048	com/tapjoy/TapjoyException
    //   183	202	1048	com/tapjoy/TapjoyException
    //   202	244	1048	com/tapjoy/TapjoyException
    //   244	283	1048	com/tapjoy/TapjoyException
    //   287	320	1048	com/tapjoy/TapjoyException
    //   320	328	1048	com/tapjoy/TapjoyException
    //   333	346	1048	com/tapjoy/TapjoyException
    //   350	355	1048	com/tapjoy/TapjoyException
    //   359	368	1048	com/tapjoy/TapjoyException
    //   372	389	1048	com/tapjoy/TapjoyException
    //   393	426	1048	com/tapjoy/TapjoyException
    //   429	438	1048	com/tapjoy/TapjoyException
    //   438	451	1048	com/tapjoy/TapjoyException
    //   455	474	1048	com/tapjoy/TapjoyException
    //   478	495	1048	com/tapjoy/TapjoyException
    //   495	512	1048	com/tapjoy/TapjoyException
    //   512	541	1048	com/tapjoy/TapjoyException
    //   546	553	1048	com/tapjoy/TapjoyException
    //   557	625	1048	com/tapjoy/TapjoyException
    //   629	662	1048	com/tapjoy/TapjoyException
    //   662	743	1048	com/tapjoy/TapjoyException
    //   743	778	1048	com/tapjoy/TapjoyException
    //   778	795	1048	com/tapjoy/TapjoyException
    //   800	817	1048	com/tapjoy/TapjoyException
    //   820	837	1048	com/tapjoy/TapjoyException
    //   837	841	1048	com/tapjoy/TapjoyException
    //   845	878	1048	com/tapjoy/TapjoyException
    //   878	911	1048	com/tapjoy/TapjoyException
    //   911	941	1048	com/tapjoy/TapjoyException
    //   941	1004	1048	com/tapjoy/TapjoyException
    //   1004	1025	1048	com/tapjoy/TapjoyException
    //   1025	1034	1048	com/tapjoy/TapjoyException
    //   1036	1048	1048	com/tapjoy/TapjoyException
    //   72	88	1082	com/tapjoy/TapjoyIntegrationException
    //   88	119	1082	com/tapjoy/TapjoyIntegrationException
    //   119	137	1082	com/tapjoy/TapjoyIntegrationException
    //   137	153	1082	com/tapjoy/TapjoyIntegrationException
    //   153	170	1082	com/tapjoy/TapjoyIntegrationException
    //   174	183	1082	com/tapjoy/TapjoyIntegrationException
    //   183	202	1082	com/tapjoy/TapjoyIntegrationException
    //   202	244	1082	com/tapjoy/TapjoyIntegrationException
    //   244	283	1082	com/tapjoy/TapjoyIntegrationException
    //   287	320	1082	com/tapjoy/TapjoyIntegrationException
    //   320	328	1082	com/tapjoy/TapjoyIntegrationException
    //   333	346	1082	com/tapjoy/TapjoyIntegrationException
    //   350	355	1082	com/tapjoy/TapjoyIntegrationException
    //   359	368	1082	com/tapjoy/TapjoyIntegrationException
    //   372	389	1082	com/tapjoy/TapjoyIntegrationException
    //   393	426	1082	com/tapjoy/TapjoyIntegrationException
    //   429	438	1082	com/tapjoy/TapjoyIntegrationException
    //   438	451	1082	com/tapjoy/TapjoyIntegrationException
    //   455	474	1082	com/tapjoy/TapjoyIntegrationException
    //   478	495	1082	com/tapjoy/TapjoyIntegrationException
    //   495	512	1082	com/tapjoy/TapjoyIntegrationException
    //   512	541	1082	com/tapjoy/TapjoyIntegrationException
    //   546	553	1082	com/tapjoy/TapjoyIntegrationException
    //   557	625	1082	com/tapjoy/TapjoyIntegrationException
    //   629	662	1082	com/tapjoy/TapjoyIntegrationException
    //   662	743	1082	com/tapjoy/TapjoyIntegrationException
    //   743	778	1082	com/tapjoy/TapjoyIntegrationException
    //   778	795	1082	com/tapjoy/TapjoyIntegrationException
    //   800	817	1082	com/tapjoy/TapjoyIntegrationException
    //   820	837	1082	com/tapjoy/TapjoyIntegrationException
    //   837	841	1082	com/tapjoy/TapjoyIntegrationException
    //   845	878	1082	com/tapjoy/TapjoyIntegrationException
    //   878	911	1082	com/tapjoy/TapjoyIntegrationException
    //   911	941	1082	com/tapjoy/TapjoyIntegrationException
    //   941	1004	1082	com/tapjoy/TapjoyIntegrationException
    //   1004	1025	1082	com/tapjoy/TapjoyIntegrationException
    //   1025	1034	1082	com/tapjoy/TapjoyIntegrationException
    //   1036	1048	1082	com/tapjoy/TapjoyIntegrationException
    //   119	137	1116	java/lang/Exception
  }
  
  private static String a(long paramLong)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(v);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(o());
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(paramLong);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(L);
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
      ((StringBuilder)localObject).append(v);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(o());
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(paramLong);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(L);
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
  
  private static void a(String paramString1, String paramString2)
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
    ae.put(paramString1, localObject);
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
          StringBuilder localStringBuilder = new StringBuilder("MATCH: installed packageName: ");
          localStringBuilder.append(localApplicationInfo.packageName);
          TapjoyLog.d("TapjoyConnect", localStringBuilder.toString());
          if (af.length() > 0)
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(af);
            localStringBuilder.append(",");
            af = localStringBuilder.toString();
          }
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(af);
          localStringBuilder.append(localApplicationInfo.packageName);
          af = localStringBuilder.toString();
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
        a(str, (String)paramProperties.get(str));
      }
      catch (ClassCastException localClassCastException)
      {
        for (;;) {}
      }
      TapjoyLog.e("TapjoyConnect", "Error parsing configuration properties in tapjoy_config.txt");
    }
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
    //   14: invokestatic 802	com/tapjoy/internal/bs:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
    //   17: astore 7
    //   19: aload 7
    //   21: invokevirtual 805	com/tapjoy/internal/bs:d	()Ljava/util/Map;
    //   24: astore 13
    //   26: aload 13
    //   28: ldc_w 807
    //   31: invokeinterface 810 2 0
    //   36: checkcast 361	java/lang/String
    //   39: invokestatic 812	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   42: astore 11
    //   44: aload 13
    //   46: ldc_w 814
    //   49: invokeinterface 810 2 0
    //   54: checkcast 361	java/lang/String
    //   57: invokestatic 812	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   60: astore 16
    //   62: aload 13
    //   64: ldc_w 816
    //   67: invokeinterface 810 2 0
    //   72: checkcast 361	java/lang/String
    //   75: invokestatic 812	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   78: astore 17
    //   80: aload 13
    //   82: ldc_w 818
    //   85: invokeinterface 810 2 0
    //   90: checkcast 361	java/lang/String
    //   93: invokestatic 812	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   96: astore 18
    //   98: aload 13
    //   100: ldc_w 820
    //   103: invokeinterface 810 2 0
    //   108: checkcast 361	java/lang/String
    //   111: invokestatic 812	com/tapjoy/internal/ct:a	(Ljava/lang/String;)Ljava/lang/String;
    //   114: astore 15
    //   116: aload 13
    //   118: ldc_w 822
    //   121: invokeinterface 810 2 0
    //   126: astore 14
    //   128: new 824	com/tapjoy/internal/er
    //   131: dup
    //   132: aload 17
    //   134: invokespecial 825	com/tapjoy/internal/er:<init>	(Ljava/lang/String;)V
    //   137: astore 6
    //   139: aload 6
    //   141: getfield 828	com/tapjoy/internal/er:a	Lcom/tapjoy/internal/er$a;
    //   144: getstatic 833	com/tapjoy/internal/er$a:RPC_ANALYTICS	Lcom/tapjoy/internal/er$a;
    //   147: if_acmpne +517 -> 664
    //   150: aload 6
    //   152: getfield 835	com/tapjoy/internal/er:b	Ljava/lang/String;
    //   155: astore 12
    //   157: aload 12
    //   159: bipush 13
    //   161: ldc_w 837
    //   164: iconst_0
    //   165: bipush 11
    //   167: invokevirtual 841	java/lang/String:regionMatches	(ILjava/lang/String;II)Z
    //   170: ifeq +483 -> 653
    //   173: new 843	java/lang/StringBuffer
    //   176: dup
    //   177: invokespecial 844	java/lang/StringBuffer:<init>	()V
    //   180: astore 19
    //   182: aload 19
    //   184: aload 12
    //   186: iconst_0
    //   187: bipush 8
    //   189: invokevirtual 486	java/lang/String:substring	(II)Ljava/lang/String;
    //   192: invokevirtual 847	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   195: pop
    //   196: aload 19
    //   198: aload 12
    //   200: bipush 24
    //   202: bipush 30
    //   204: invokevirtual 486	java/lang/String:substring	(II)Ljava/lang/String;
    //   207: invokevirtual 847	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   210: pop
    //   211: aload 19
    //   213: aload 12
    //   215: bipush 9
    //   217: bipush 13
    //   219: invokevirtual 486	java/lang/String:substring	(II)Ljava/lang/String;
    //   222: invokevirtual 847	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   225: pop
    //   226: aload 19
    //   228: aload 12
    //   230: bipush 30
    //   232: invokevirtual 489	java/lang/String:substring	(I)Ljava/lang/String;
    //   235: invokevirtual 847	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   238: pop
    //   239: aload 19
    //   241: invokevirtual 848	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   244: astore 12
    //   246: aload 6
    //   248: getfield 849	com/tapjoy/internal/er:c	Ljava/lang/String;
    //   251: astore 19
    //   253: aload 11
    //   255: astore 6
    //   257: aload 11
    //   259: ifnonnull +7 -> 266
    //   262: aload 12
    //   264: astore 6
    //   266: invokestatic 854	com/tapjoy/internal/gc:a	()Lcom/tapjoy/internal/gc;
    //   269: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   272: aload 17
    //   274: ldc_w 392
    //   277: ldc_w 856
    //   280: aload 12
    //   282: aload 19
    //   284: invokevirtual 859	com/tapjoy/internal/gc:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   287: aload 6
    //   289: putstatic 213	com/tapjoy/TapjoyConnectCore:S	Ljava/lang/String;
    //   292: aload 16
    //   294: putstatic 215	com/tapjoy/TapjoyConnectCore:T	Ljava/lang/String;
    //   297: aload 17
    //   299: putstatic 217	com/tapjoy/TapjoyConnectCore:U	Ljava/lang/String;
    //   302: aload 18
    //   304: putstatic 219	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   307: new 544	java/util/ArrayList
    //   310: dup
    //   311: invokespecial 860	java/util/ArrayList:<init>	()V
    //   314: astore 6
    //   316: aload 15
    //   318: ifnull +54 -> 372
    //   321: aload 15
    //   323: ldc_w 716
    //   326: invokevirtual 864	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
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
    //   346: invokevirtual 781	java/lang/String:trim	()Ljava/lang/String;
    //   349: astore 12
    //   351: aload 12
    //   353: invokevirtual 482	java/lang/String:length	()I
    //   356: ifle +413 -> 769
    //   359: aload 6
    //   361: aload 12
    //   363: invokeinterface 865 2 0
    //   368: pop
    //   369: goto +400 -> 769
    //   372: aload 6
    //   374: invokeinterface 868 1 0
    //   379: ifne +8 -> 387
    //   382: aload 6
    //   384: invokestatic 786	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   387: aload 7
    //   389: invokevirtual 871	com/tapjoy/internal/bs:close	()V
    //   392: iload_1
    //   393: ifne +254 -> 647
    //   396: aload 10
    //   398: astore 6
    //   400: aload 14
    //   402: instanceof 361
    //   405: istore_1
    //   406: iload_1
    //   407: ifeq +23 -> 430
    //   410: aload 10
    //   412: astore 6
    //   414: aload 14
    //   416: checkcast 361	java/lang/String
    //   419: invokevirtual 781	java/lang/String:trim	()Ljava/lang/String;
    //   422: invokestatic 877	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   425: lstore 4
    //   427: goto +37 -> 464
    //   430: aload 10
    //   432: astore 6
    //   434: aload 14
    //   436: instanceof 879
    //   439: istore_1
    //   440: iload_1
    //   441: ifeq +20 -> 461
    //   444: aload 10
    //   446: astore 6
    //   448: aload 14
    //   450: checkcast 879	java/lang/Number
    //   453: invokevirtual 882	java/lang/Number:longValue	()J
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
    //   475: invokestatic 888	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   478: invokevirtual 891	com/tapjoy/TapjoyAppSettings:removeConnectResult	()V
    //   481: goto +27 -> 508
    //   484: aload 10
    //   486: astore 6
    //   488: invokestatic 888	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   491: aload_0
    //   492: invokestatic 893	com/tapjoy/TapjoyConnectCore:p	()Ljava/lang/String;
    //   495: lload 4
    //   497: ldc2_w 894
    //   500: lmul
    //   501: invokestatic 899	com/tapjoy/internal/y:b	()J
    //   504: ladd
    //   505: invokevirtual 903	com/tapjoy/TapjoyAppSettings:saveConnectResultAndParams	(Ljava/lang/String;Ljava/lang/String;J)V
    //   508: aload 10
    //   510: astore 6
    //   512: invokestatic 279	com/tapjoy/internal/fd:a	()Lcom/tapjoy/internal/fd;
    //   515: astore_0
    //   516: aload 10
    //   518: astore 6
    //   520: aload 13
    //   522: ldc_w 905
    //   525: invokeinterface 810 2 0
    //   530: astore 7
    //   532: aload 10
    //   534: astore 6
    //   536: aload 7
    //   538: instanceof 809
    //   541: istore_1
    //   542: iload_1
    //   543: ifeq +61 -> 604
    //   546: aload 10
    //   548: astore 6
    //   550: aload_0
    //   551: getfield 908	com/tapjoy/internal/fd:a	Lcom/tapjoy/internal/fb;
    //   554: aload 7
    //   556: checkcast 809	java/util/Map
    //   559: invokevirtual 913	com/tapjoy/internal/fb:a	(Ljava/util/Map;)V
    //   562: aload 10
    //   564: astore 6
    //   566: aload 7
    //   568: invokestatic 918	com/tapjoy/internal/bm:a	(Ljava/lang/Object;)Ljava/lang/String;
    //   571: astore 7
    //   573: aload 10
    //   575: astore 6
    //   577: aload_0
    //   578: invokevirtual 921	com/tapjoy/internal/fd:c	()Landroid/content/SharedPreferences;
    //   581: invokeinterface 528 1 0
    //   586: ldc_w 905
    //   589: aload 7
    //   591: invokeinterface 534 3 0
    //   596: invokeinterface 924 1 0
    //   601: goto +46 -> 647
    //   604: aload 7
    //   606: ifnonnull +41 -> 647
    //   609: aload 10
    //   611: astore 6
    //   613: aload_0
    //   614: getfield 908	com/tapjoy/internal/fd:a	Lcom/tapjoy/internal/fb;
    //   617: aconst_null
    //   618: invokevirtual 913	com/tapjoy/internal/fb:a	(Ljava/util/Map;)V
    //   621: aload 10
    //   623: astore 6
    //   625: aload_0
    //   626: invokevirtual 921	com/tapjoy/internal/fd:c	()Landroid/content/SharedPreferences;
    //   629: invokeinterface 528 1 0
    //   634: ldc_w 905
    //   637: invokeinterface 928 2 0
    //   642: invokeinterface 924 1 0
    //   647: aconst_null
    //   648: invokestatic 933	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   651: iconst_1
    //   652: ireturn
    //   653: new 935	java/lang/IllegalArgumentException
    //   656: dup
    //   657: ldc_w 937
    //   660: invokespecial 938	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   663: athrow
    //   664: new 793	java/io/IOException
    //   667: dup
    //   668: ldc_w 940
    //   671: invokespecial 941	java/io/IOException:<init>	(Ljava/lang/String;)V
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
    //   719: ldc_w 427
    //   722: aload 7
    //   724: invokevirtual 942	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   727: invokestatic 944	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   730: goto +17 -> 747
    //   733: aload_0
    //   734: astore 6
    //   736: ldc_w 427
    //   739: aload 7
    //   741: invokevirtual 945	java/io/IOException:getMessage	()Ljava/lang/String;
    //   744: invokestatic 944	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   747: aload_0
    //   748: invokestatic 933	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   751: iconst_0
    //   752: ireturn
    //   753: aload 6
    //   755: invokestatic 933	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
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
    //   22: ldc_w 427
    //   25: ldc_w 953
    //   28: invokestatic 466	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 871	com/tapjoy/internal/bs:close	()V
    //   37: aconst_null
    //   38: invokestatic 933	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 871	com/tapjoy/internal/bs:close	()V
    //   49: aconst_null
    //   50: invokestatic 933	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
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
    //   79: ldc_w 427
    //   82: aload_2
    //   83: invokevirtual 942	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   86: invokestatic 944	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: goto +15 -> 104
    //   92: aload_1
    //   93: astore_0
    //   94: ldc_w 427
    //   97: aload_2
    //   98: invokevirtual 945	java/io/IOException:getMessage	()Ljava/lang/String;
    //   101: invokestatic 944	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   104: aload_1
    //   105: invokestatic 933	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
    //   108: ldc_w 427
    //   111: new 629	com/tapjoy/TapjoyErrorMessage
    //   114: dup
    //   115: getstatic 635	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   118: ldc_w 955
    //   121: invokespecial 639	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   124: invokestatic 642	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   127: iconst_0
    //   128: ireturn
    //   129: aload_0
    //   130: invokestatic 933	com/tapjoy/internal/dc:a	(Ljava/io/Closeable;)V
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
    Object localObject1 = new HashMap();
    TapjoyUtil.safePut((Map)localObject1, "plugin", N, true);
    TapjoyUtil.safePut((Map)localObject1, "sdk_type", O, true);
    TapjoyUtil.safePut((Map)localObject1, "app_id", v, true);
    TapjoyUtil.safePut((Map)localObject1, "library_version", x, true);
    TapjoyUtil.safePut((Map)localObject1, "library_revision", "0859594bd", true);
    TapjoyUtil.safePut((Map)localObject1, "bridge_version", y, true);
    TapjoyUtil.safePut((Map)localObject1, "app_version", w, true);
    localHashMap2.putAll((Map)localObject1);
    localObject1 = new HashMap();
    TapjoyUtil.safePut((Map)localObject1, "device_name", r, true);
    TapjoyUtil.safePut((Map)localObject1, "platform", D, true);
    TapjoyUtil.safePut((Map)localObject1, "os_version", u, true);
    TapjoyUtil.safePut((Map)localObject1, "device_manufacturer", s, true);
    TapjoyUtil.safePut((Map)localObject1, "device_type", t, true);
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(B);
    TapjoyUtil.safePut((Map)localObject1, "screen_layout_size", ((StringBuilder)localObject2).toString(), true);
    TapjoyUtil.safePut((Map)localObject1, "store_name", K, true);
    TapjoyUtil.safePut((Map)localObject1, "store_view", String.valueOf(R), true);
    TapjoyUtil.safePut((Map)localObject1, "carrier_name", E, true);
    TapjoyUtil.safePut((Map)localObject1, "carrier_country_code", F, true);
    TapjoyUtil.safePut((Map)localObject1, "mobile_network_code", H, true);
    TapjoyUtil.safePut((Map)localObject1, "mobile_country_code", G, true);
    TapjoyUtil.safePut((Map)localObject1, "country_code", Locale.getDefault().getCountry(), true);
    TapjoyUtil.safePut((Map)localObject1, "language_code", Locale.getDefault().getLanguage(), true);
    I = getConnectionType();
    TapjoyUtil.safePut((Map)localObject1, "connection_type", I, true);
    J = getConnectionSubType();
    TapjoyUtil.safePut((Map)localObject1, "connection_subtype", J, true);
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(z);
    TapjoyUtil.safePut((Map)localObject1, "screen_density", ((StringBuilder)localObject2).toString(), true);
    localHashMap2.putAll((Map)localObject1);
    localObject1 = new HashMap();
    if (l())
    {
      TapjoyUtil.safePut((Map)localObject1, "advertising_id", c, true);
      TapjoyUtil.safePut((Map)localObject1, "ad_tracking_enabled", String.valueOf(d), true);
    }
    if (!m())
    {
      TapjoyUtil.safePut((Map)localObject1, "android_id", n, true);
      TapjoyUtil.safePut((Map)localObject1, "mac_address", p, true);
    }
    TapjoyUtil.safePut((Map)localObject1, "install_id", q, true);
    TapjoyUtil.safePut((Map)localObject1, "publisher_user_id", C, true);
    TapjoyUtil.safePut((Map)localObject1, "ad_id_check_disabled", e, true);
    TapjoyUtil.safePut((Map)localObject1, "persistent_ids_disabled", f, true);
    if (a != 0) {
      TapjoyUtil.safePut((Map)localObject1, "packaged_gps_version", Integer.toString(a), true);
    }
    if (b != 0) {
      TapjoyUtil.safePut((Map)localObject1, "device_gps_version", Integer.toString(b), true);
    }
    if ((o != null) && (o.length() != 0) && (System.currentTimeMillis() - Z <= 1800000L)) {
      Z = System.currentTimeMillis();
    } else {
      o = n();
    }
    TapjoyUtil.safePut((Map)localObject1, "session_id", o, true);
    localHashMap2.putAll((Map)localObject1);
    localObject1 = new HashMap();
    TapjoyUtil.safePut((Map)localObject1, "app_group_id", S, true);
    TapjoyUtil.safePut((Map)localObject1, "store", T, true);
    TapjoyUtil.safePut((Map)localObject1, "analytics_api_key", U, true);
    TapjoyUtil.safePut((Map)localObject1, "managed_device_id", V, true);
    localHashMap2.putAll((Map)localObject1);
    localObject2 = fh.a();
    HashMap localHashMap3 = new HashMap();
    if (((fh)localObject2).a != null)
    {
      if (((fh)localObject2).a.booleanValue()) {
        localObject1 = "1";
      } else {
        localObject1 = "0";
      }
      TapjoyUtil.safePut(localHashMap3, "gdpr", (String)localObject1, true);
    }
    if (!aq.a(((fh)localObject2).b)) {
      TapjoyUtil.safePut(localHashMap3, "cgdpr", ((fh)localObject2).b, true);
    }
    localHashMap2.putAll(localHashMap3);
    if ((TapjoyCache.getInstance() != null) && (TapjoyCache.getInstance().getCachedOfferIDs() != null) && (TapjoyCache.getInstance().getCachedOfferIDs().length() > 0)) {
      TapjoyUtil.safePut(localHashMap2, "cached_ids", TapjoyCache.getInstance().getCachedOfferIDs(), true);
    }
    TapjoyUtil.safePut(localHashMap2, "display_multiplier", Float.toString(Q), true);
    localHashMap1.putAll(localHashMap2);
    localObject1 = new HashMap();
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
    ((Map)localObject1).putAll(localHashMap2);
    localHashMap2 = new HashMap();
    TapjoyUtil.safePut(localHashMap2, "pkg_ver", ak, true);
    TapjoyUtil.safePut(localHashMap2, "pkg_rev", al);
    TapjoyUtil.safePut(localHashMap2, "pkg_data_ver", am, true);
    TapjoyUtil.safePut(localHashMap2, "installer", an, true);
    if (ct.c(K)) {
      TapjoyUtil.safePut(localHashMap2, "store_name", aO, true);
    }
    ((Map)localObject1).putAll(localHashMap2);
    ((Map)localObject1).putAll(f());
    localHashMap1.putAll((Map)localObject1);
    return localHashMap1;
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
      StringBuilder localStringBuilder = new StringBuilder("user_tags[");
      localStringBuilder.append(i1);
      localStringBuilder.append("]");
      TapjoyUtil.safePut(localHashMap, localStringBuilder.toString(), str, true);
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
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(v);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(o());
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(paramLong);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(L);
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
    String str = "";
    try
    {
      Object localObject = (ConnectivityManager)g.getSystemService("connectivity");
      if (localObject == null) {
        return str;
      }
      str = ((ConnectivityManager)localObject).getActiveNetworkInfo().getSubtypeName();
      try
      {
        localObject = new StringBuilder("connection_sub_type: ");
        ((StringBuilder)localObject).append(str);
        TapjoyLog.d("TapjoyConnect", ((StringBuilder)localObject).toString());
      }
      catch (Exception localException1) {}
      localStringBuilder = new StringBuilder("getConnectionSubType error: ");
    }
    catch (Exception localException2)
    {
      str = "";
    }
    StringBuilder localStringBuilder;
    localStringBuilder.append(localException2.toString());
    TapjoyLog.e("TapjoyConnect", localStringBuilder.toString());
    return str;
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
        break label175;
      }
      str1 = str2;
      localObject2 = str2;
      if (localConnectivityManager.getActiveNetworkInfo() == null) {
        break label175;
      }
      str1 = str2;
      int i1 = localConnectivityManager.getActiveNetworkInfo().getType();
      if ((i1 == 1) || (i1 == 6)) {
        break label182;
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
    localObject2 = new StringBuilder("connection_type: ");
    str1 = str2;
    ((StringBuilder)localObject2).append(str2);
    str1 = str2;
    TapjoyLog.d("TapjoyConnect", ((StringBuilder)localObject2).toString());
    return str2;
    label175:
    return localObject2;
    for (;;)
    {
      Object localObject1 = str1;
      break;
      label182:
      str1 = "wifi";
    }
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
      str = v;
    }
    paramString = new StringBuilder();
    paramString.append(getHostURL());
    paramString.append("support_requests/new?currency_id=");
    paramString.append(str);
    paramString.append("&app_id=");
    paramString.append(v);
    paramString.append("&udid=");
    paramString.append(V);
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
    return C;
  }
  
  private static void h()
  {
    TapjoyLog.i("TapjoyConnect", "Connect Flags:");
    TapjoyLog.i("TapjoyConnect", "--------------------");
    Object localObject = ae.entrySet().iterator();
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
    ((StringBuilder)localObject).append(P);
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
        if (ac != null)
        {
          ApplicationInfo localApplicationInfo = ac.getApplicationInfo(g.getPackageName(), 128);
          if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null))
          {
            localObject1 = TapjoyConnectFlag.FLAG_ARRAY;
            int i2 = localObject1.length;
            i1 = 0;
            if (i1 < i2)
            {
              localObject2 = localObject1[i1];
              Object localObject3 = localApplicationInfo.metaData;
              StringBuilder localStringBuilder = new StringBuilder("tapjoy.");
              localStringBuilder.append((String)localObject2);
              localObject3 = ((Bundle)localObject3).getString(localStringBuilder.toString());
              if (localObject3 != null)
              {
                localStringBuilder = new StringBuilder("Found manifest flag: ");
                localStringBuilder.append((String)localObject2);
                localStringBuilder.append(", ");
                localStringBuilder.append((String)localObject3);
                TapjoyLog.d("TapjoyConnect", localStringBuilder.toString());
                a((String)localObject2, (String)localObject3);
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
    StringBuilder localStringBuilder = new StringBuilder("isViewOpen: ");
    localStringBuilder.append(ag.size());
    TapjoyLog.d("TapjoyConnect", localStringBuilder.toString());
    return !ag.isEmpty();
  }
  
  /* Error */
  private void j()
  {
    // Byte code:
    //   0: getstatic 293	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   3: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   6: invokevirtual 314	android/content/Context:getPackageName	()Ljava/lang/String;
    //   9: iconst_1
    //   10: invokevirtual 370	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   13: getfield 1516	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
    //   16: invokestatic 139	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   19: astore_2
    //   20: aload_2
    //   21: ifnull +427 -> 448
    //   24: aload_2
    //   25: invokeinterface 694 1 0
    //   30: astore_3
    //   31: aload_3
    //   32: invokeinterface 699 1 0
    //   37: ifeq +411 -> 448
    //   40: aload_3
    //   41: invokeinterface 703 1 0
    //   46: checkcast 1518	android/content/pm/ActivityInfo
    //   49: astore 4
    //   51: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   54: aload 4
    //   56: getfield 1521	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   59: invokevirtual 1522	java/util/Vector:contains	(Ljava/lang/Object;)Z
    //   62: ifeq -31 -> 31
    //   65: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   68: aload 4
    //   70: getfield 1521	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   73: invokevirtual 1525	java/util/Vector:indexOf	(Ljava/lang/Object;)I
    //   76: istore_1
    //   77: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   80: iload_1
    //   81: invokevirtual 1528	java/util/Vector:get	(I)Ljava/lang/Object;
    //   84: checkcast 361	java/lang/String
    //   87: invokestatic 1534	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   90: pop
    //   91: new 127	java/util/Vector
    //   94: dup
    //   95: invokespecial 772	java/util/Vector:<init>	()V
    //   98: astore_2
    //   99: aload 4
    //   101: getfield 1537	android/content/pm/ActivityInfo:configChanges	I
    //   104: sipush 128
    //   107: iand
    //   108: sipush 128
    //   111: if_icmpeq +11 -> 122
    //   114: aload_2
    //   115: ldc_w 1539
    //   118: invokevirtual 784	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   121: pop
    //   122: aload 4
    //   124: getfield 1537	android/content/pm/ActivityInfo:configChanges	I
    //   127: bipush 32
    //   129: iand
    //   130: bipush 32
    //   132: if_icmpeq +11 -> 143
    //   135: aload_2
    //   136: ldc_w 1541
    //   139: invokevirtual 784	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   142: pop
    //   143: aload_2
    //   144: invokevirtual 1542	java/util/Vector:size	()I
    //   147: ifeq +115 -> 262
    //   150: aload_2
    //   151: invokevirtual 1542	java/util/Vector:size	()I
    //   154: iconst_1
    //   155: if_icmpne +55 -> 210
    //   158: new 413	java/lang/StringBuilder
    //   161: dup
    //   162: invokespecial 503	java/lang/StringBuilder:<init>	()V
    //   165: astore_3
    //   166: aload_3
    //   167: aload_2
    //   168: invokevirtual 1543	java/util/Vector:toString	()Ljava/lang/String;
    //   171: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: pop
    //   175: aload_3
    //   176: ldc_w 1545
    //   179: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: pop
    //   183: aload_3
    //   184: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   187: iload_1
    //   188: invokevirtual 1528	java/util/Vector:get	(I)Ljava/lang/Object;
    //   191: checkcast 361	java/lang/String
    //   194: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: pop
    //   198: new 252	com/tapjoy/TapjoyIntegrationException
    //   201: dup
    //   202: aload_3
    //   203: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   206: invokespecial 1546	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   209: athrow
    //   210: new 413	java/lang/StringBuilder
    //   213: dup
    //   214: invokespecial 503	java/lang/StringBuilder:<init>	()V
    //   217: astore_3
    //   218: aload_3
    //   219: aload_2
    //   220: invokevirtual 1543	java/util/Vector:toString	()Ljava/lang/String;
    //   223: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: pop
    //   227: aload_3
    //   228: ldc_w 1548
    //   231: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: pop
    //   235: aload_3
    //   236: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   239: iload_1
    //   240: invokevirtual 1528	java/util/Vector:get	(I)Ljava/lang/Object;
    //   243: checkcast 361	java/lang/String
    //   246: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   249: pop
    //   250: new 252	com/tapjoy/TapjoyIntegrationException
    //   253: dup
    //   254: aload_3
    //   255: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   258: invokespecial 1546	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   261: athrow
    //   262: getstatic 397	android/os/Build$VERSION:SDK_INT	I
    //   265: bipush 13
    //   267: if_icmplt +54 -> 321
    //   270: aload 4
    //   272: getfield 1537	android/content/pm/ActivityInfo:configChanges	I
    //   275: sipush 1024
    //   278: iand
    //   279: sipush 1024
    //   282: if_icmpeq +39 -> 321
    //   285: new 413	java/lang/StringBuilder
    //   288: dup
    //   289: ldc_w 1550
    //   292: invokespecial 418	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   295: astore_2
    //   296: aload_2
    //   297: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   300: iload_1
    //   301: invokevirtual 1528	java/util/Vector:get	(I)Ljava/lang/Object;
    //   304: checkcast 361	java/lang/String
    //   307: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: pop
    //   311: ldc_w 427
    //   314: aload_2
    //   315: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   318: invokestatic 556	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   321: getstatic 397	android/os/Build$VERSION:SDK_INT	I
    //   324: bipush 11
    //   326: if_icmplt +73 -> 399
    //   329: aload 4
    //   331: getfield 1521	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   334: ldc_w 1552
    //   337: invokevirtual 587	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   340: ifeq +59 -> 399
    //   343: aload 4
    //   345: getfield 1553	android/content/pm/ActivityInfo:flags	I
    //   348: sipush 512
    //   351: iand
    //   352: sipush 512
    //   355: if_icmpne +6 -> 361
    //   358: goto +41 -> 399
    //   361: new 413	java/lang/StringBuilder
    //   364: dup
    //   365: ldc_w 1555
    //   368: invokespecial 418	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   371: astore_2
    //   372: aload_2
    //   373: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   376: iload_1
    //   377: invokevirtual 1528	java/util/Vector:get	(I)Ljava/lang/Object;
    //   380: checkcast 361	java/lang/String
    //   383: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   386: pop
    //   387: new 252	com/tapjoy/TapjoyIntegrationException
    //   390: dup
    //   391: aload_2
    //   392: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   395: invokespecial 1546	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   398: athrow
    //   399: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   402: iload_1
    //   403: invokevirtual 1557	java/util/Vector:remove	(I)Ljava/lang/Object;
    //   406: pop
    //   407: goto -376 -> 31
    //   410: new 413	java/lang/StringBuilder
    //   413: dup
    //   414: ldc_w 1559
    //   417: invokespecial 418	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   420: astore_2
    //   421: aload_2
    //   422: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   425: iload_1
    //   426: invokevirtual 1528	java/util/Vector:get	(I)Ljava/lang/Object;
    //   429: checkcast 361	java/lang/String
    //   432: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: pop
    //   436: new 252	com/tapjoy/TapjoyIntegrationException
    //   439: dup
    //   440: aload_2
    //   441: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   444: invokespecial 1546	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   447: athrow
    //   448: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   451: invokevirtual 1542	java/util/Vector:size	()I
    //   454: ifeq +119 -> 573
    //   457: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   460: invokevirtual 1542	java/util/Vector:size	()I
    //   463: iconst_1
    //   464: if_icmpne +56 -> 520
    //   467: new 413	java/lang/StringBuilder
    //   470: dup
    //   471: ldc_w 1561
    //   474: invokespecial 418	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   477: astore_2
    //   478: aload_2
    //   479: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   482: invokevirtual 1542	java/util/Vector:size	()I
    //   485: invokevirtual 1006	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   488: pop
    //   489: aload_2
    //   490: ldc_w 1563
    //   493: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   496: pop
    //   497: aload_2
    //   498: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   501: invokevirtual 1543	java/util/Vector:toString	()Ljava/lang/String;
    //   504: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   507: pop
    //   508: new 252	com/tapjoy/TapjoyIntegrationException
    //   511: dup
    //   512: aload_2
    //   513: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   516: invokespecial 1546	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   519: athrow
    //   520: new 413	java/lang/StringBuilder
    //   523: dup
    //   524: ldc_w 1561
    //   527: invokespecial 418	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   530: astore_2
    //   531: aload_2
    //   532: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   535: invokevirtual 1542	java/util/Vector:size	()I
    //   538: invokevirtual 1006	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   541: pop
    //   542: aload_2
    //   543: ldc_w 1565
    //   546: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   549: pop
    //   550: aload_2
    //   551: getstatic 145	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   554: invokevirtual 1543	java/util/Vector:toString	()Ljava/lang/String;
    //   557: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: pop
    //   561: new 252	com/tapjoy/TapjoyIntegrationException
    //   564: dup
    //   565: aload_2
    //   566: invokevirtual 428	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   569: invokespecial 1546	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   572: athrow
    //   573: invokestatic 1567	com/tapjoy/TapjoyConnectCore:k	()V
    //   576: ldc_w 1569
    //   579: invokestatic 1534	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   582: astore_2
    //   583: aload_2
    //   584: ldc_w 1571
    //   587: iconst_1
    //   588: anewarray 1530	java/lang/Class
    //   591: dup
    //   592: iconst_0
    //   593: ldc_w 651
    //   596: aastore
    //   597: invokevirtual 1575	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   600: pop
    //   601: ldc_w 1577
    //   604: invokestatic 1580	com/tapjoy/TapjoyUtil:getResource	(Ljava/lang/String;)Ljava/lang/Object;
    //   607: checkcast 361	java/lang/String
    //   610: astore_3
    //   611: aload_3
    //   612: astore_2
    //   613: aload_3
    //   614: ifnonnull +13 -> 627
    //   617: ldc_w 1582
    //   620: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   623: invokestatic 1586	com/tapjoy/TapjoyUtil:copyTextFromJarIntoString	(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   626: astore_2
    //   627: aload_2
    //   628: astore_3
    //   629: aload_2
    //   630: ifnonnull +68 -> 698
    //   633: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   636: invokevirtual 308	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   639: ldc_w 1588
    //   642: ldc_w 1590
    //   645: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   648: invokevirtual 314	android/content/Context:getPackageName	()Ljava/lang/String;
    //   651: invokevirtual 320	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   654: istore_1
    //   655: getstatic 267	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   658: invokevirtual 308	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   661: iload_1
    //   662: invokevirtual 327	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   665: astore_3
    //   666: aload_3
    //   667: invokevirtual 1595	java/io/InputStream:available	()I
    //   670: newarray byte
    //   672: astore 4
    //   674: aload_3
    //   675: aload 4
    //   677: invokevirtual 1599	java/io/InputStream:read	([B)I
    //   680: pop
    //   681: new 361	java/lang/String
    //   684: dup
    //   685: aload 4
    //   687: invokespecial 1602	java/lang/String:<init>	([B)V
    //   690: astore_3
    //   691: ldc_w 1577
    //   694: aload_3
    //   695: invokestatic 1606	com/tapjoy/TapjoyUtil:setResource	(Ljava/lang/String;Ljava/lang/Object;)V
    //   698: aload_3
    //   699: ifnull +45 -> 744
    //   702: ldc_w 603
    //   705: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   708: ifnull +28 -> 736
    //   711: ldc_w 603
    //   714: invokestatic 340	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   717: ldc_w 790
    //   720: invokevirtual 587	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   723: ifeq +13 -> 736
    //   726: ldc_w 427
    //   729: ldc_w 1608
    //   732: invokestatic 609	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   735: return
    //   736: aload_0
    //   737: getfield 299	com/tapjoy/TapjoyConnectCore:ad	Lcom/tapjoy/TapjoyGpsHelper;
    //   740: invokevirtual 1611	com/tapjoy/TapjoyGpsHelper:checkGooglePlayIntegration	()V
    //   743: return
    //   744: new 252	com/tapjoy/TapjoyIntegrationException
    //   747: dup
    //   748: ldc_w 1613
    //   751: invokespecial 1546	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   754: athrow
    //   755: new 252	com/tapjoy/TapjoyIntegrationException
    //   758: dup
    //   759: ldc_w 1615
    //   762: invokespecial 1546	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   765: athrow
    //   766: new 252	com/tapjoy/TapjoyIntegrationException
    //   769: dup
    //   770: ldc_w 1617
    //   773: invokespecial 1546	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   776: athrow
    //   777: new 252	com/tapjoy/TapjoyIntegrationException
    //   780: dup
    //   781: ldc_w 1619
    //   784: invokespecial 1546	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   787: athrow
    //   788: astore_2
    //   789: goto -12 -> 777
    //   792: astore_2
    //   793: goto -383 -> 410
    //   796: astore_2
    //   797: goto -31 -> 766
    //   800: astore_2
    //   801: goto -46 -> 755
    //   804: astore_3
    //   805: aload_2
    //   806: astore_3
    //   807: goto -109 -> 698
    //   810: astore_2
    //   811: goto -113 -> 698
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	814	0	this	TapjoyConnectCore
    //   76	586	1	i1	int
    //   19	611	2	localObject1	Object
    //   788	1	2	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   792	1	2	localClassNotFoundException1	ClassNotFoundException
    //   796	1	2	localClassNotFoundException2	ClassNotFoundException
    //   800	6	2	localNoSuchMethodException1	NoSuchMethodException
    //   810	1	2	localException1	Exception
    //   30	669	3	localObject2	Object
    //   804	1	3	localException2	Exception
    //   806	1	3	localNoSuchMethodException2	NoSuchMethodException
    //   49	637	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   0	20	788	android/content/pm/PackageManager$NameNotFoundException
    //   24	31	788	android/content/pm/PackageManager$NameNotFoundException
    //   31	77	788	android/content/pm/PackageManager$NameNotFoundException
    //   77	122	788	android/content/pm/PackageManager$NameNotFoundException
    //   122	143	788	android/content/pm/PackageManager$NameNotFoundException
    //   143	210	788	android/content/pm/PackageManager$NameNotFoundException
    //   210	262	788	android/content/pm/PackageManager$NameNotFoundException
    //   262	321	788	android/content/pm/PackageManager$NameNotFoundException
    //   321	358	788	android/content/pm/PackageManager$NameNotFoundException
    //   361	399	788	android/content/pm/PackageManager$NameNotFoundException
    //   399	407	788	android/content/pm/PackageManager$NameNotFoundException
    //   410	448	788	android/content/pm/PackageManager$NameNotFoundException
    //   77	122	792	java/lang/ClassNotFoundException
    //   122	143	792	java/lang/ClassNotFoundException
    //   143	210	792	java/lang/ClassNotFoundException
    //   210	262	792	java/lang/ClassNotFoundException
    //   262	321	792	java/lang/ClassNotFoundException
    //   321	358	792	java/lang/ClassNotFoundException
    //   361	399	792	java/lang/ClassNotFoundException
    //   399	407	792	java/lang/ClassNotFoundException
    //   576	583	796	java/lang/ClassNotFoundException
    //   583	601	800	java/lang/NoSuchMethodException
    //   633	691	804	java/lang/Exception
    //   691	698	810	java/lang/Exception
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
      localStringBuilder.append(v);
      localObject2 = TapjoyUtil.SHA256(localStringBuilder.toString());
      try
      {
        Z = System.currentTimeMillis();
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
  
  private static String p()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(v);
    ((StringBuilder)localObject).append(w);
    ((StringBuilder)localObject).append(x);
    ((StringBuilder)localObject).append(c);
    ((StringBuilder)localObject).append(q);
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
    paramString = new StringBuilder("URL parameters: ");
    paramString.append(getURLParams());
    TapjoyLog.d("TapjoyConnect", paramString.toString());
    new Thread(new Runnable()
    {
      public final void run()
      {
        Object localObject = new StringBuilder("Setting userID to ");
        ((StringBuilder)localObject).append(TapjoyConnectCore.b());
        TapjoyLog.i("TapjoyConnect", ((StringBuilder)localObject).toString());
        localObject = TapjoyConnectCore.c();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(TapjoyConnectCore.getHostURL());
        localStringBuilder.append("set_publisher_user_id?");
        localObject = ((TapjoyURLConnection)localObject).getResponseFromURL(localStringBuilder.toString(), TapjoyConnectCore.getURLParams());
        boolean bool;
        if (((TapjoyHttpURLResponse)localObject).response != null) {
          bool = TapjoyConnectCore.a(((TapjoyHttpURLResponse)localObject).response);
        } else {
          bool = false;
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
    StringBuilder localStringBuilder = new StringBuilder("viewDidClose: ");
    localStringBuilder.append(paramString);
    TapjoyLog.d("TapjoyConnect", localStringBuilder.toString());
    ag.remove(paramString);
    ev.e.notifyObservers();
  }
  
  public static void viewWillOpen(String paramString, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder("viewWillOpen: ");
    localStringBuilder.append(paramString);
    TapjoyLog.d("TapjoyConnect", localStringBuilder.toString());
    ag.put(paramString, Integer.valueOf(paramInt));
  }
  
  public void actionComplete(String paramString)
  {
    Object localObject = new StringBuilder("actionComplete: ");
    ((StringBuilder)localObject).append(paramString);
    TapjoyLog.i("TapjoyConnect", ((StringBuilder)localObject).toString());
    localObject = e();
    TapjoyUtil.safePut((Map)localObject, "app_id", paramString, true);
    ((Map)localObject).putAll(getTimeStampAndVerifierParams());
    paramString = new StringBuilder("PPA URL parameters: ");
    paramString.append(localObject);
    TapjoyLog.d("TapjoyConnect", paramString.toString());
    new Thread(new PPAThread((Map)localObject)).start();
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
        i1 = 1;
        break label102;
      }
    }
    int i1 = 0;
    label102:
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
        ab = true;
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
      if (af.length() > 0)
      {
        localObject1 = getGenericURLParams();
        TapjoyUtil.safePut((Map)localObject1, "package_names", af, true);
        long l1 = System.currentTimeMillis() / 1000L;
        localObject2 = a(l1, af);
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
          Object localObject = gc.a();
          String str1 = TapjoyConnectCore.c;
          boolean bool = TapjoyConnectCore.d ^ true;
          localObject = ((gc)localObject).f;
          String str2 = ((gf)localObject).c.A.a();
          ((gf)localObject).b.q = str1;
          ((gf)localObject).b.r = Boolean.valueOf(bool);
          ((gf)localObject).c.A.a(str1);
          ((gf)localObject).c.B.a(bool);
          gq.a(str1, bool);
          if ((!ct.c(str2)) && (!str1.equals(str2))) {
            ((gf)localObject).c.a(false);
          }
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
    StringBuilder localStringBuilder = new StringBuilder("setVirtualCurrencyMultiplier: ");
    localStringBuilder.append(paramFloat);
    TapjoyLog.i("TapjoyConnect", localStringBuilder.toString());
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
      Object localObject = TapjoyConnectCore.c();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(TapjoyConnectCore.getHostURL());
      localStringBuilder.append("api/connect/v3.json?");
      localObject = ((TapjoyURLConnection)localObject).getResponseFromURL(localStringBuilder.toString(), null, null, this.b);
      if (((TapjoyHttpURLResponse)localObject).response != null) {
        TapjoyConnectCore.b(((TapjoyHttpURLResponse)localObject).response);
      }
    }
  }
}
