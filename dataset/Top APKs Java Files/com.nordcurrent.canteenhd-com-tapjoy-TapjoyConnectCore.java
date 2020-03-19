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
import android.os.Build;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class TapjoyConnectCore
{
  private static int A = 1;
  private static float B = 1.0F;
  private static int C = 1;
  private static String D = "";
  public static final float DEFAULT_CURRENCY_MULTIPLIER = 1.0F;
  private static boolean E = false;
  private static String F = "";
  private static String G = "";
  private static String H = "";
  private static String I = "";
  private static String J = "";
  private static String K = "";
  private static String L = "";
  private static String M = "";
  private static String N = "";
  private static String O = "";
  private static String P = "native";
  private static String Q = "";
  private static String R = "";
  private static float S = 1.0F;
  private static boolean T = false;
  private static String U = "";
  private static String V = "";
  private static String W = "";
  private static String X = "";
  private static String Y;
  protected static int a = 0;
  private static Set aA;
  private static int aB = 0;
  private static int aC = 0;
  private static int aD = 0;
  private static long aE = 0L;
  private static long aF = 0L;
  private static long aG = 0L;
  private static String aH;
  private static int aI = 0;
  private static double aJ = 0.0D;
  private static double aK = 0.0D;
  private static long aL = 0L;
  private static int aM = 0;
  private static int aN = 0;
  private static int aO = 0;
  private static String aP;
  private static String aQ;
  private static String aR;
  private static long ac = 0L;
  private static boolean ae = false;
  private static PackageManager af;
  private static Hashtable ah = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
  private static String ai = "";
  private static Map aj = new ConcurrentHashMap();
  private static String ak;
  private static String al;
  private static String am;
  private static String an;
  private static int ao = 0;
  private static String ap;
  private static String aq;
  private static long ar = 0L;
  private static String as;
  private static int at = 0;
  private static int au = 0;
  private static String av;
  private static String aw;
  private static String ax;
  private static String ay;
  private static String az;
  protected static int b = 0;
  protected static String c = "";
  protected static boolean d = false;
  protected static String e = "";
  protected static String f = "";
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
  private Timer aa;
  private boolean ab;
  private boolean ad;
  private TapjoyGpsHelper ag;
  
  /* Error */
  public TapjoyConnectCore(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 182	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 184	com/tapjoy/TapjoyConnectCore:Z	J
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 186	com/tapjoy/TapjoyConnectCore:aa	Ljava/util/Timer;
    //   14: iconst_0
    //   15: istore_3
    //   16: aload_0
    //   17: iconst_0
    //   18: putfield 188	com/tapjoy/TapjoyConnectCore:ab	Z
    //   21: aload_0
    //   22: iconst_0
    //   23: putfield 190	com/tapjoy/TapjoyConnectCore:ad	Z
    //   26: aload_1
    //   27: putstatic 192	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   30: new 194	com/tapjoy/TapjoyURLConnection
    //   33: dup
    //   34: invokespecial 195	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   37: putstatic 197	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   40: getstatic 192	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   43: invokevirtual 203	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   46: putstatic 205	com/tapjoy/TapjoyConnectCore:af	Landroid/content/pm/PackageManager;
    //   49: aload_0
    //   50: new 207	com/tapjoy/TapjoyGpsHelper
    //   53: dup
    //   54: getstatic 192	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   57: invokespecial 209	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   60: putfield 211	com/tapjoy/TapjoyConnectCore:ag	Lcom/tapjoy/TapjoyGpsHelper;
    //   63: getstatic 165	com/tapjoy/TapjoyConnectCore:ah	Ljava/util/Hashtable;
    //   66: ifnonnull +13 -> 79
    //   69: new 213	java/util/Hashtable
    //   72: dup
    //   73: invokespecial 214	java/util/Hashtable:<init>	()V
    //   76: putstatic 165	com/tapjoy/TapjoyConnectCore:ah	Ljava/util/Hashtable;
    //   79: ldc -40
    //   81: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   84: ifnull +20 -> 104
    //   87: ldc -40
    //   89: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   92: ldc -34
    //   94: invokevirtual 228	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   97: ifeq +7 -> 104
    //   100: iconst_1
    //   101: invokestatic 234	com/tapjoy/TapjoyLog:setDebugEnabled	(Z)V
    //   104: invokestatic 236	com/tapjoy/TapjoyConnectCore:k	()V
    //   107: getstatic 192	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   110: invokevirtual 240	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   113: ldc -14
    //   115: aconst_null
    //   116: getstatic 192	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   119: invokevirtual 246	android/content/Context:getPackageName	()Ljava/lang/String;
    //   122: invokevirtual 252	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   125: istore_2
    //   126: new 254	java/util/Properties
    //   129: dup
    //   130: invokespecial 255	java/util/Properties:<init>	()V
    //   133: astore_1
    //   134: aload_1
    //   135: getstatic 192	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   138: invokevirtual 240	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   141: iload_2
    //   142: invokevirtual 259	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   145: invokevirtual 263	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   148: aload_1
    //   149: invokestatic 266	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   152: ldc_w 268
    //   155: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   158: ldc 27
    //   160: if_acmpne +7 -> 167
    //   163: aload_0
    //   164: invokespecial 270	com/tapjoy/TapjoyConnectCore:l	()V
    //   167: getstatic 192	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   170: invokevirtual 274	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   173: ldc_w 276
    //   176: invokestatic 282	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   179: astore_1
    //   180: aload_1
    //   181: putstatic 284	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   184: aload_1
    //   185: ifnull +12 -> 197
    //   188: getstatic 284	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   191: invokevirtual 287	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   194: putstatic 284	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   197: getstatic 205	com/tapjoy/TapjoyConnectCore:af	Landroid/content/pm/PackageManager;
    //   200: getstatic 192	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   203: invokevirtual 246	android/content/Context:getPackageName	()Ljava/lang/String;
    //   206: iconst_0
    //   207: invokevirtual 293	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   210: getfield 298	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   213: putstatic 300	com/tapjoy/TapjoyConnectCore:x	Ljava/lang/String;
    //   216: ldc_w 302
    //   219: putstatic 304	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   222: ldc_w 302
    //   225: putstatic 306	com/tapjoy/TapjoyConnectCore:F	Ljava/lang/String;
    //   228: getstatic 311	android/os/Build:MODEL	Ljava/lang/String;
    //   231: putstatic 313	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   234: getstatic 316	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   237: putstatic 318	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   240: getstatic 323	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   243: putstatic 325	com/tapjoy/TapjoyConnectCore:v	Ljava/lang/String;
    //   246: ldc_w 327
    //   249: putstatic 329	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   252: ldc_w 331
    //   255: putstatic 333	com/tapjoy/TapjoyConnectCore:z	Ljava/lang/String;
    //   258: getstatic 336	android/os/Build$VERSION:SDK_INT	I
    //   261: iconst_3
    //   262: if_icmple +72 -> 334
    //   265: new 338	com/tapjoy/TapjoyDisplayMetricsUtil
    //   268: dup
    //   269: getstatic 192	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   272: invokespecial 339	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   275: astore_1
    //   276: aload_1
    //   277: invokevirtual 343	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   280: putstatic 345	com/tapjoy/TapjoyConnectCore:A	I
    //   283: aload_1
    //   284: invokevirtual 349	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   287: putstatic 351	com/tapjoy/TapjoyConnectCore:B	F
    //   290: aload_1
    //   291: invokevirtual 354	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   294: putstatic 356	com/tapjoy/TapjoyConnectCore:C	I
    //   297: goto +37 -> 334
    //   300: astore_1
    //   301: new 358	java/lang/StringBuilder
    //   304: dup
    //   305: ldc_w 360
    //   308: invokespecial 363	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   311: astore 5
    //   313: aload 5
    //   315: aload_1
    //   316: invokevirtual 366	java/lang/Exception:toString	()Ljava/lang/String;
    //   319: invokevirtual 370	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   322: pop
    //   323: ldc_w 372
    //   326: aload 5
    //   328: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   331: invokestatic 376	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   334: ldc_w 378
    //   337: invokestatic 381	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   340: istore 4
    //   342: iload 4
    //   344: ifeq +99 -> 443
    //   347: getstatic 192	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   350: ldc_w 383
    //   353: invokevirtual 387	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   356: checkcast 389	android/net/wifi/WifiManager
    //   359: astore_1
    //   360: aload_1
    //   361: ifnull +91 -> 452
    //   364: aload_1
    //   365: invokevirtual 393	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   368: astore_1
    //   369: aload_1
    //   370: ifnull +82 -> 452
    //   373: aload_1
    //   374: invokevirtual 398	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   377: astore_1
    //   378: aload_1
    //   379: putstatic 400	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   382: aload_1
    //   383: ifnull +69 -> 452
    //   386: getstatic 400	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   389: ldc_w 402
    //   392: ldc 27
    //   394: invokevirtual 406	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   397: invokevirtual 287	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   400: putstatic 400	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   403: goto +49 -> 452
    //   406: astore_1
    //   407: new 358	java/lang/StringBuilder
    //   410: dup
    //   411: ldc_w 408
    //   414: invokespecial 363	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   417: astore 5
    //   419: aload 5
    //   421: aload_1
    //   422: invokevirtual 366	java/lang/Exception:toString	()Ljava/lang/String;
    //   425: invokevirtual 370	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   428: pop
    //   429: ldc_w 372
    //   432: aload 5
    //   434: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   437: invokestatic 376	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   440: goto +12 -> 452
    //   443: ldc_w 372
    //   446: ldc_w 410
    //   449: invokestatic 412	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   452: getstatic 192	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   455: ldc_w 414
    //   458: invokevirtual 387	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   461: checkcast 416	android/telephony/TelephonyManager
    //   464: astore_1
    //   465: aload_1
    //   466: ifnull +487 -> 953
    //   469: aload_1
    //   470: invokevirtual 419	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   473: putstatic 421	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   476: aload_1
    //   477: invokevirtual 424	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   480: putstatic 426	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   483: aload_1
    //   484: invokevirtual 429	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   487: astore 5
    //   489: aload 5
    //   491: ifnull +41 -> 532
    //   494: aload 5
    //   496: invokevirtual 432	java/lang/String:length	()I
    //   499: iconst_5
    //   500: if_icmpeq +13 -> 513
    //   503: aload 5
    //   505: invokevirtual 432	java/lang/String:length	()I
    //   508: bipush 6
    //   510: if_icmpne +22 -> 532
    //   513: aload 5
    //   515: iconst_0
    //   516: iconst_3
    //   517: invokevirtual 436	java/lang/String:substring	(II)Ljava/lang/String;
    //   520: putstatic 438	com/tapjoy/TapjoyConnectCore:I	Ljava/lang/String;
    //   523: aload 5
    //   525: iconst_3
    //   526: invokevirtual 441	java/lang/String:substring	(I)Ljava/lang/String;
    //   529: putstatic 443	com/tapjoy/TapjoyConnectCore:J	Ljava/lang/String;
    //   532: ldc_w 445
    //   535: invokestatic 381	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   538: istore 4
    //   540: iload 4
    //   542: ifeq +402 -> 944
    //   545: ldc_w 447
    //   548: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   551: ifnull +27 -> 578
    //   554: ldc_w 447
    //   557: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   560: invokevirtual 432	java/lang/String:length	()I
    //   563: ifle +15 -> 578
    //   566: ldc_w 447
    //   569: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   572: putstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   575: goto +10 -> 585
    //   578: aload_1
    //   579: invokevirtual 452	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   582: putstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   585: new 358	java/lang/StringBuilder
    //   588: dup
    //   589: ldc_w 454
    //   592: invokespecial 363	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   595: astore_1
    //   596: aload_1
    //   597: getstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   600: invokevirtual 370	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   603: pop
    //   604: ldc_w 372
    //   607: aload_1
    //   608: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   611: invokestatic 412	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   614: getstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   617: ifnonnull +25 -> 642
    //   620: ldc_w 372
    //   623: new 456	com/tapjoy/TapjoyErrorMessage
    //   626: dup
    //   627: getstatic 462	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   630: ldc_w 464
    //   633: invokespecial 467	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   636: invokestatic 470	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   639: goto +1038 -> 1677
    //   642: getstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   645: invokevirtual 432	java/lang/String:length	()I
    //   648: ifeq +47 -> 695
    //   651: getstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   654: ldc_w 472
    //   657: invokevirtual 228	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   660: ifne +35 -> 695
    //   663: getstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   666: ldc_w 474
    //   669: invokevirtual 228	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   672: ifeq +6 -> 678
    //   675: goto +20 -> 695
    //   678: getstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   681: invokestatic 480	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   684: invokevirtual 483	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   687: putstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   690: iconst_1
    //   691: istore_2
    //   692: goto +25 -> 717
    //   695: ldc_w 372
    //   698: new 456	com/tapjoy/TapjoyErrorMessage
    //   701: dup
    //   702: getstatic 462	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   705: ldc_w 485
    //   708: invokespecial 467	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   711: invokestatic 470	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   714: goto +963 -> 1677
    //   717: new 358	java/lang/StringBuilder
    //   720: dup
    //   721: ldc_w 487
    //   724: invokespecial 363	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   727: astore_1
    //   728: aload_1
    //   729: getstatic 336	android/os/Build$VERSION:SDK_INT	I
    //   732: invokevirtual 490	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   735: pop
    //   736: ldc_w 372
    //   739: aload_1
    //   740: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   743: invokestatic 492	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   746: getstatic 336	android/os/Build$VERSION:SDK_INT	I
    //   749: bipush 9
    //   751: if_icmplt +202 -> 953
    //   754: ldc_w 372
    //   757: ldc_w 494
    //   760: invokestatic 412	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   763: aload_0
    //   764: invokevirtual 497	com/tapjoy/TapjoyConnectCore:getSerial	()Ljava/lang/String;
    //   767: astore_1
    //   768: iload_2
    //   769: ifne +7 -> 776
    //   772: aload_1
    //   773: putstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   776: getstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   779: ifnonnull +25 -> 804
    //   782: ldc_w 372
    //   785: new 456	com/tapjoy/TapjoyErrorMessage
    //   788: dup
    //   789: getstatic 462	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   792: ldc_w 499
    //   795: invokespecial 467	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   798: invokestatic 470	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   801: goto +152 -> 953
    //   804: getstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   807: invokevirtual 432	java/lang/String:length	()I
    //   810: ifeq +57 -> 867
    //   813: getstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   816: ldc_w 472
    //   819: invokevirtual 228	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   822: ifne +45 -> 867
    //   825: getstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   828: ldc_w 474
    //   831: invokevirtual 228	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   834: ifne +33 -> 867
    //   837: getstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   840: ldc_w 501
    //   843: invokevirtual 228	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   846: ifeq +6 -> 852
    //   849: goto +18 -> 867
    //   852: getstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   855: invokestatic 480	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   858: invokevirtual 483	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   861: putstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   864: goto +89 -> 953
    //   867: ldc_w 372
    //   870: new 456	com/tapjoy/TapjoyErrorMessage
    //   873: dup
    //   874: getstatic 462	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   877: ldc_w 503
    //   880: invokespecial 467	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   883: invokestatic 470	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   886: goto +67 -> 953
    //   889: astore_1
    //   890: getstatic 462	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   893: astore 5
    //   895: new 358	java/lang/StringBuilder
    //   898: dup
    //   899: ldc_w 505
    //   902: invokespecial 363	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   905: astore 6
    //   907: aload 6
    //   909: aload_1
    //   910: invokevirtual 366	java/lang/Exception:toString	()Ljava/lang/String;
    //   913: invokevirtual 370	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   916: pop
    //   917: ldc_w 372
    //   920: new 456	com/tapjoy/TapjoyErrorMessage
    //   923: dup
    //   924: aload 5
    //   926: aload 6
    //   928: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   931: invokespecial 467	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   934: invokestatic 470	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   937: aconst_null
    //   938: putstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   941: goto +12 -> 953
    //   944: ldc_w 372
    //   947: ldc_w 507
    //   950: invokestatic 412	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   953: getstatic 192	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   956: ldc_w 509
    //   959: iconst_0
    //   960: invokevirtual 513	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   963: astore_1
    //   964: aload_1
    //   965: ldc_w 515
    //   968: ldc 27
    //   970: invokeinterface 520 3 0
    //   975: astore 5
    //   977: aload 5
    //   979: putstatic 522	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   982: aload 5
    //   984: ifnull +14 -> 998
    //   987: getstatic 522	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   990: invokevirtual 432	java/lang/String:length	()I
    //   993: istore_2
    //   994: iload_2
    //   995: ifne +108 -> 1103
    //   998: new 358	java/lang/StringBuilder
    //   1001: dup
    //   1002: invokespecial 523	java/lang/StringBuilder:<init>	()V
    //   1005: astore 5
    //   1007: aload 5
    //   1009: invokestatic 529	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   1012: invokevirtual 530	java/util/UUID:toString	()Ljava/lang/String;
    //   1015: invokevirtual 370	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1018: pop
    //   1019: aload 5
    //   1021: invokestatic 536	java/lang/System:currentTimeMillis	()J
    //   1024: invokevirtual 539	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1027: pop
    //   1028: aload 5
    //   1030: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1033: invokestatic 544	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   1036: putstatic 522	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   1039: aload_1
    //   1040: invokeinterface 548 1 0
    //   1045: astore_1
    //   1046: aload_1
    //   1047: ldc_w 515
    //   1050: getstatic 522	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   1053: invokeinterface 554 3 0
    //   1058: pop
    //   1059: aload_1
    //   1060: invokeinterface 558 1 0
    //   1065: pop
    //   1066: goto +37 -> 1103
    //   1069: astore_1
    //   1070: new 358	java/lang/StringBuilder
    //   1073: dup
    //   1074: ldc_w 560
    //   1077: invokespecial 363	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1080: astore 5
    //   1082: aload 5
    //   1084: aload_1
    //   1085: invokevirtual 366	java/lang/Exception:toString	()Ljava/lang/String;
    //   1088: invokevirtual 370	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1091: pop
    //   1092: ldc_w 372
    //   1095: aload 5
    //   1097: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1100: invokestatic 376	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1103: ldc_w 562
    //   1106: ldc_w 564
    //   1109: invokestatic 567	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   1112: putstatic 569	com/tapjoy/TapjoyConnectCore:E	Z
    //   1115: goto +37 -> 1152
    //   1118: astore_1
    //   1119: new 358	java/lang/StringBuilder
    //   1122: dup
    //   1123: ldc_w 571
    //   1126: invokespecial 363	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1129: astore 5
    //   1131: aload 5
    //   1133: aload_1
    //   1134: invokevirtual 366	java/lang/Exception:toString	()Ljava/lang/String;
    //   1137: invokevirtual 370	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1140: pop
    //   1141: ldc_w 372
    //   1144: aload 5
    //   1146: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1149: invokestatic 376	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1152: ldc_w 573
    //   1155: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1158: ifnull +75 -> 1233
    //   1161: ldc_w 573
    //   1164: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1167: invokevirtual 432	java/lang/String:length	()I
    //   1170: ifle +63 -> 1233
    //   1173: ldc_w 573
    //   1176: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1179: putstatic 575	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   1182: new 577	java/util/ArrayList
    //   1185: dup
    //   1186: getstatic 580	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   1189: invokestatic 152	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   1192: invokespecial 581	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   1195: getstatic 575	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   1198: invokevirtual 584	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   1201: ifne +32 -> 1233
    //   1204: new 358	java/lang/StringBuilder
    //   1207: dup
    //   1208: ldc_w 586
    //   1211: invokespecial 363	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1214: astore_1
    //   1215: aload_1
    //   1216: getstatic 575	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   1219: invokevirtual 370	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1222: pop
    //   1223: ldc_w 372
    //   1226: aload_1
    //   1227: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1230: invokestatic 588	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   1233: getstatic 575	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   1236: astore_1
    //   1237: new 590	android/content/Intent
    //   1240: dup
    //   1241: ldc_w 592
    //   1244: invokespecial 593	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   1247: astore 5
    //   1249: aload_1
    //   1250: invokevirtual 432	java/lang/String:length	()I
    //   1253: ifgt +37 -> 1290
    //   1256: aload 5
    //   1258: ldc_w 595
    //   1261: invokestatic 601	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   1264: invokevirtual 605	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   1267: pop
    //   1268: getstatic 205	com/tapjoy/TapjoyConnectCore:af	Landroid/content/pm/PackageManager;
    //   1271: aload 5
    //   1273: iconst_0
    //   1274: invokevirtual 609	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   1277: invokeinterface 614 1 0
    //   1282: ifle +45 -> 1327
    //   1285: iconst_1
    //   1286: istore_3
    //   1287: goto +40 -> 1327
    //   1290: aload_1
    //   1291: ldc_w 616
    //   1294: invokevirtual 228	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1297: ifeq +13 -> 1310
    //   1300: ldc_w 618
    //   1303: invokestatic 620	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1306: istore_3
    //   1307: goto +20 -> 1327
    //   1310: aload_1
    //   1311: ldc_w 622
    //   1314: invokevirtual 228	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1317: ifeq +10 -> 1327
    //   1320: ldc_w 624
    //   1323: invokestatic 620	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1326: istore_3
    //   1327: iload_3
    //   1328: putstatic 626	com/tapjoy/TapjoyConnectCore:T	Z
    //   1331: goto +37 -> 1368
    //   1334: astore_1
    //   1335: new 358	java/lang/StringBuilder
    //   1338: dup
    //   1339: ldc_w 628
    //   1342: invokespecial 363	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1345: astore 5
    //   1347: aload 5
    //   1349: aload_1
    //   1350: invokevirtual 366	java/lang/Exception:toString	()Ljava/lang/String;
    //   1353: invokevirtual 370	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1356: pop
    //   1357: ldc_w 372
    //   1360: aload 5
    //   1362: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1365: invokestatic 376	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1368: invokestatic 630	com/tapjoy/TapjoyConnectCore:i	()V
    //   1371: ldc_w 632
    //   1374: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1377: ifnull +24 -> 1401
    //   1380: ldc_w 632
    //   1383: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1386: invokevirtual 432	java/lang/String:length	()I
    //   1389: ifle +12 -> 1401
    //   1392: ldc_w 632
    //   1395: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1398: putstatic 634	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   1401: ldc_w 636
    //   1404: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1407: ifnull +24 -> 1431
    //   1410: ldc_w 636
    //   1413: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1416: invokevirtual 432	java/lang/String:length	()I
    //   1419: ifle +12 -> 1431
    //   1422: ldc_w 636
    //   1425: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1428: putstatic 638	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   1431: ldc_w 640
    //   1434: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1437: ifnull +57 -> 1494
    //   1440: ldc_w 640
    //   1443: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1446: invokevirtual 432	java/lang/String:length	()I
    //   1449: ifle +45 -> 1494
    //   1452: new 358	java/lang/StringBuilder
    //   1455: dup
    //   1456: ldc_w 642
    //   1459: invokespecial 363	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1462: astore_1
    //   1463: aload_1
    //   1464: ldc_w 640
    //   1467: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1470: invokevirtual 370	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1473: pop
    //   1474: ldc_w 372
    //   1477: aload_1
    //   1478: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1481: invokestatic 492	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1484: ldc_w 640
    //   1487: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1490: aconst_null
    //   1491: invokestatic 646	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;Lcom/tapjoy/TJSetUserIDListener;)V
    //   1494: ldc_w 648
    //   1497: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1500: invokestatic 651	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   1503: putstatic 653	com/tapjoy/TapjoyConnectCore:R	Ljava/lang/String;
    //   1506: new 358	java/lang/StringBuilder
    //   1509: dup
    //   1510: ldc_w 454
    //   1513: invokespecial 363	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1516: astore 5
    //   1518: aload 5
    //   1520: getstatic 449	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1523: invokevirtual 370	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1526: pop
    //   1527: ldc_w 447
    //   1530: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1533: ifnull +149 -> 1682
    //   1536: ldc_w 447
    //   1539: invokestatic 220	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1542: invokevirtual 432	java/lang/String:length	()I
    //   1545: ifle +137 -> 1682
    //   1548: ldc_w 655
    //   1551: astore_1
    //   1552: goto +3 -> 1555
    //   1555: aload 5
    //   1557: aload_1
    //   1558: invokevirtual 370	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1561: pop
    //   1562: ldc_w 372
    //   1565: aload 5
    //   1567: invokevirtual 373	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1570: invokestatic 412	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1573: getstatic 165	com/tapjoy/TapjoyConnectCore:ah	Ljava/util/Hashtable;
    //   1576: ifnull +6 -> 1582
    //   1579: invokestatic 657	com/tapjoy/TapjoyConnectCore:j	()V
    //   1582: aload_0
    //   1583: invokevirtual 660	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   1586: aload_0
    //   1587: iconst_1
    //   1588: putfield 190	com/tapjoy/TapjoyConnectCore:ad	Z
    //   1591: return
    //   1592: astore_1
    //   1593: new 177	com/tapjoy/TapjoyException
    //   1596: dup
    //   1597: aload_1
    //   1598: invokevirtual 663	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   1601: invokespecial 664	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   1604: athrow
    //   1605: astore_1
    //   1606: ldc_w 372
    //   1609: new 456	com/tapjoy/TapjoyErrorMessage
    //   1612: dup
    //   1613: getstatic 462	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1616: aload_1
    //   1617: invokevirtual 665	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   1620: invokespecial 467	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1623: invokestatic 470	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1626: invokestatic 667	com/tapjoy/TapjoyConnectCore:f	()V
    //   1629: getstatic 672	com/tapjoy/internal/eo:b	Lcom/tapjoy/internal/eo$a;
    //   1632: getstatic 678	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1635: invokevirtual 684	com/tapjoy/internal/eo$a:notifyObservers	(Ljava/lang/Object;)V
    //   1638: return
    //   1639: astore_1
    //   1640: ldc_w 372
    //   1643: new 456	com/tapjoy/TapjoyErrorMessage
    //   1646: dup
    //   1647: getstatic 687	com/tapjoy/TapjoyErrorMessage$ErrorType:INTEGRATION_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1650: aload_1
    //   1651: invokevirtual 688	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   1654: invokespecial 467	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1657: invokestatic 470	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1660: invokestatic 667	com/tapjoy/TapjoyConnectCore:f	()V
    //   1663: getstatic 672	com/tapjoy/internal/eo:b	Lcom/tapjoy/internal/eo$a;
    //   1666: getstatic 678	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1669: invokevirtual 684	com/tapjoy/internal/eo$a:notifyObservers	(Ljava/lang/Object;)V
    //   1672: return
    //   1673: astore_1
    //   1674: goto -1522 -> 152
    //   1677: iconst_0
    //   1678: istore_2
    //   1679: goto -962 -> 717
    //   1682: ldc 27
    //   1684: astore_1
    //   1685: goto -130 -> 1555
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1688	0	this	TapjoyConnectCore
    //   0	1688	1	paramContext	Context
    //   125	1554	2	i1	int
    //   15	1313	3	bool1	boolean
    //   340	201	4	bool2	boolean
    //   311	1255	5	localObject	Object
    //   905	22	6	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   258	297	300	java/lang/Exception
    //   347	360	406	java/lang/Exception
    //   364	369	406	java/lang/Exception
    //   373	382	406	java/lang/Exception
    //   386	403	406	java/lang/Exception
    //   545	575	889	java/lang/Exception
    //   578	585	889	java/lang/Exception
    //   585	639	889	java/lang/Exception
    //   642	675	889	java/lang/Exception
    //   678	690	889	java/lang/Exception
    //   695	714	889	java/lang/Exception
    //   717	768	889	java/lang/Exception
    //   772	776	889	java/lang/Exception
    //   776	801	889	java/lang/Exception
    //   804	849	889	java/lang/Exception
    //   852	864	889	java/lang/Exception
    //   867	886	889	java/lang/Exception
    //   998	1066	1069	java/lang/Exception
    //   1103	1115	1118	java/lang/Exception
    //   1233	1268	1334	java/lang/Exception
    //   1268	1285	1334	java/lang/Exception
    //   1290	1307	1334	java/lang/Exception
    //   1310	1327	1334	java/lang/Exception
    //   1327	1331	1334	java/lang/Exception
    //   197	216	1592	android/content/pm/PackageManager$NameNotFoundException
    //   63	79	1605	com/tapjoy/TapjoyException
    //   79	104	1605	com/tapjoy/TapjoyException
    //   104	134	1605	com/tapjoy/TapjoyException
    //   134	152	1605	com/tapjoy/TapjoyException
    //   152	167	1605	com/tapjoy/TapjoyException
    //   167	184	1605	com/tapjoy/TapjoyException
    //   188	197	1605	com/tapjoy/TapjoyException
    //   197	216	1605	com/tapjoy/TapjoyException
    //   216	258	1605	com/tapjoy/TapjoyException
    //   258	297	1605	com/tapjoy/TapjoyException
    //   301	334	1605	com/tapjoy/TapjoyException
    //   334	342	1605	com/tapjoy/TapjoyException
    //   347	360	1605	com/tapjoy/TapjoyException
    //   364	369	1605	com/tapjoy/TapjoyException
    //   373	382	1605	com/tapjoy/TapjoyException
    //   386	403	1605	com/tapjoy/TapjoyException
    //   407	440	1605	com/tapjoy/TapjoyException
    //   443	452	1605	com/tapjoy/TapjoyException
    //   452	465	1605	com/tapjoy/TapjoyException
    //   469	489	1605	com/tapjoy/TapjoyException
    //   494	513	1605	com/tapjoy/TapjoyException
    //   513	532	1605	com/tapjoy/TapjoyException
    //   532	540	1605	com/tapjoy/TapjoyException
    //   545	575	1605	com/tapjoy/TapjoyException
    //   578	585	1605	com/tapjoy/TapjoyException
    //   585	639	1605	com/tapjoy/TapjoyException
    //   642	675	1605	com/tapjoy/TapjoyException
    //   678	690	1605	com/tapjoy/TapjoyException
    //   695	714	1605	com/tapjoy/TapjoyException
    //   717	768	1605	com/tapjoy/TapjoyException
    //   772	776	1605	com/tapjoy/TapjoyException
    //   776	801	1605	com/tapjoy/TapjoyException
    //   804	849	1605	com/tapjoy/TapjoyException
    //   852	864	1605	com/tapjoy/TapjoyException
    //   867	886	1605	com/tapjoy/TapjoyException
    //   890	941	1605	com/tapjoy/TapjoyException
    //   944	953	1605	com/tapjoy/TapjoyException
    //   953	982	1605	com/tapjoy/TapjoyException
    //   987	994	1605	com/tapjoy/TapjoyException
    //   998	1066	1605	com/tapjoy/TapjoyException
    //   1070	1103	1605	com/tapjoy/TapjoyException
    //   1103	1115	1605	com/tapjoy/TapjoyException
    //   1119	1152	1605	com/tapjoy/TapjoyException
    //   1152	1233	1605	com/tapjoy/TapjoyException
    //   1233	1268	1605	com/tapjoy/TapjoyException
    //   1268	1285	1605	com/tapjoy/TapjoyException
    //   1290	1307	1605	com/tapjoy/TapjoyException
    //   1310	1327	1605	com/tapjoy/TapjoyException
    //   1327	1331	1605	com/tapjoy/TapjoyException
    //   1335	1368	1605	com/tapjoy/TapjoyException
    //   1368	1401	1605	com/tapjoy/TapjoyException
    //   1401	1431	1605	com/tapjoy/TapjoyException
    //   1431	1494	1605	com/tapjoy/TapjoyException
    //   1494	1548	1605	com/tapjoy/TapjoyException
    //   1555	1582	1605	com/tapjoy/TapjoyException
    //   1582	1591	1605	com/tapjoy/TapjoyException
    //   1593	1605	1605	com/tapjoy/TapjoyException
    //   63	79	1639	com/tapjoy/TapjoyIntegrationException
    //   79	104	1639	com/tapjoy/TapjoyIntegrationException
    //   104	134	1639	com/tapjoy/TapjoyIntegrationException
    //   134	152	1639	com/tapjoy/TapjoyIntegrationException
    //   152	167	1639	com/tapjoy/TapjoyIntegrationException
    //   167	184	1639	com/tapjoy/TapjoyIntegrationException
    //   188	197	1639	com/tapjoy/TapjoyIntegrationException
    //   197	216	1639	com/tapjoy/TapjoyIntegrationException
    //   216	258	1639	com/tapjoy/TapjoyIntegrationException
    //   258	297	1639	com/tapjoy/TapjoyIntegrationException
    //   301	334	1639	com/tapjoy/TapjoyIntegrationException
    //   334	342	1639	com/tapjoy/TapjoyIntegrationException
    //   347	360	1639	com/tapjoy/TapjoyIntegrationException
    //   364	369	1639	com/tapjoy/TapjoyIntegrationException
    //   373	382	1639	com/tapjoy/TapjoyIntegrationException
    //   386	403	1639	com/tapjoy/TapjoyIntegrationException
    //   407	440	1639	com/tapjoy/TapjoyIntegrationException
    //   443	452	1639	com/tapjoy/TapjoyIntegrationException
    //   452	465	1639	com/tapjoy/TapjoyIntegrationException
    //   469	489	1639	com/tapjoy/TapjoyIntegrationException
    //   494	513	1639	com/tapjoy/TapjoyIntegrationException
    //   513	532	1639	com/tapjoy/TapjoyIntegrationException
    //   532	540	1639	com/tapjoy/TapjoyIntegrationException
    //   545	575	1639	com/tapjoy/TapjoyIntegrationException
    //   578	585	1639	com/tapjoy/TapjoyIntegrationException
    //   585	639	1639	com/tapjoy/TapjoyIntegrationException
    //   642	675	1639	com/tapjoy/TapjoyIntegrationException
    //   678	690	1639	com/tapjoy/TapjoyIntegrationException
    //   695	714	1639	com/tapjoy/TapjoyIntegrationException
    //   717	768	1639	com/tapjoy/TapjoyIntegrationException
    //   772	776	1639	com/tapjoy/TapjoyIntegrationException
    //   776	801	1639	com/tapjoy/TapjoyIntegrationException
    //   804	849	1639	com/tapjoy/TapjoyIntegrationException
    //   852	864	1639	com/tapjoy/TapjoyIntegrationException
    //   867	886	1639	com/tapjoy/TapjoyIntegrationException
    //   890	941	1639	com/tapjoy/TapjoyIntegrationException
    //   944	953	1639	com/tapjoy/TapjoyIntegrationException
    //   953	982	1639	com/tapjoy/TapjoyIntegrationException
    //   987	994	1639	com/tapjoy/TapjoyIntegrationException
    //   998	1066	1639	com/tapjoy/TapjoyIntegrationException
    //   1070	1103	1639	com/tapjoy/TapjoyIntegrationException
    //   1103	1115	1639	com/tapjoy/TapjoyIntegrationException
    //   1119	1152	1639	com/tapjoy/TapjoyIntegrationException
    //   1152	1233	1639	com/tapjoy/TapjoyIntegrationException
    //   1233	1268	1639	com/tapjoy/TapjoyIntegrationException
    //   1268	1285	1639	com/tapjoy/TapjoyIntegrationException
    //   1290	1307	1639	com/tapjoy/TapjoyIntegrationException
    //   1310	1327	1639	com/tapjoy/TapjoyIntegrationException
    //   1327	1331	1639	com/tapjoy/TapjoyIntegrationException
    //   1335	1368	1639	com/tapjoy/TapjoyIntegrationException
    //   1368	1401	1639	com/tapjoy/TapjoyIntegrationException
    //   1401	1431	1639	com/tapjoy/TapjoyIntegrationException
    //   1431	1494	1639	com/tapjoy/TapjoyIntegrationException
    //   1494	1548	1639	com/tapjoy/TapjoyIntegrationException
    //   1555	1582	1639	com/tapjoy/TapjoyIntegrationException
    //   1582	1591	1639	com/tapjoy/TapjoyIntegrationException
    //   1593	1605	1639	com/tapjoy/TapjoyIntegrationException
    //   134	152	1673	java/lang/Exception
  }
  
  private static String a(long paramLong)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(w);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(q());
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
      ((StringBuilder)localObject).append(q());
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
      ai = "";
      Iterator localIterator = af.getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (((localApplicationInfo.flags & 0x1) != 1) && (paramList.contains(localApplicationInfo.packageName)))
        {
          StringBuilder localStringBuilder = new StringBuilder("MATCH: installed packageName: ");
          localStringBuilder.append(localApplicationInfo.packageName);
          TapjoyLog.d("TapjoyConnect", localStringBuilder.toString());
          if (ai.length() > 0)
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(ai);
            localStringBuilder.append(",");
            ai = localStringBuilder.toString();
          }
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(ai);
          localStringBuilder.append(localApplicationInfo.packageName);
          ai = localStringBuilder.toString();
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
    FeatureInfo[] arrayOfFeatureInfo = af.getSystemAvailableFeatures();
    int i2 = arrayOfFeatureInfo.length;
    int i1 = 0;
    while (i1 < i2)
    {
      if (arrayOfFeatureInfo[i1].name.matches(paramString1)) {
        return af.checkPermission(paramString2, g.getPackageName()) == 0;
      }
      i1 += 1;
    }
    return false;
  }
  
  /* Error */
  private static boolean a(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 833	com/tapjoy/internal/br:b	(Ljava/lang/String;)Lcom/tapjoy/internal/br;
    //   4: astore 7
    //   6: aload 7
    //   8: astore 6
    //   10: aload 7
    //   12: invokevirtual 836	com/tapjoy/internal/br:d	()Ljava/util/Map;
    //   15: astore 8
    //   17: aload 7
    //   19: astore 6
    //   21: aload 8
    //   23: ldc_w 838
    //   26: invokeinterface 841 2 0
    //   31: checkcast 224	java/lang/String
    //   34: invokestatic 845	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   37: astore 9
    //   39: aload 7
    //   41: astore 6
    //   43: aload 8
    //   45: ldc_w 847
    //   48: invokeinterface 841 2 0
    //   53: checkcast 224	java/lang/String
    //   56: invokestatic 845	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   59: astore 13
    //   61: aload 7
    //   63: astore 6
    //   65: aload 8
    //   67: ldc_w 849
    //   70: invokeinterface 841 2 0
    //   75: checkcast 224	java/lang/String
    //   78: invokestatic 845	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   81: astore 14
    //   83: aload 7
    //   85: astore 6
    //   87: aload 8
    //   89: ldc_w 851
    //   92: invokeinterface 841 2 0
    //   97: checkcast 224	java/lang/String
    //   100: invokestatic 845	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   103: astore 15
    //   105: aload 7
    //   107: astore 6
    //   109: aload 8
    //   111: ldc_w 853
    //   114: invokeinterface 841 2 0
    //   119: checkcast 224	java/lang/String
    //   122: invokestatic 845	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   125: astore 12
    //   127: aload 7
    //   129: astore 6
    //   131: aload 8
    //   133: ldc_w 855
    //   136: invokeinterface 841 2 0
    //   141: astore 11
    //   143: aload 7
    //   145: astore 6
    //   147: new 857	com/tapjoy/internal/el
    //   150: dup
    //   151: aload 14
    //   153: invokespecial 858	com/tapjoy/internal/el:<init>	(Ljava/lang/String;)V
    //   156: astore 8
    //   158: aload 7
    //   160: astore 6
    //   162: aload 8
    //   164: getfield 861	com/tapjoy/internal/el:a	Lcom/tapjoy/internal/el$a;
    //   167: getstatic 866	com/tapjoy/internal/el$a:RPC_ANALYTICS	Lcom/tapjoy/internal/el$a;
    //   170: if_acmpeq +18 -> 188
    //   173: aload 7
    //   175: astore 6
    //   177: new 824	java/io/IOException
    //   180: dup
    //   181: ldc_w 868
    //   184: invokespecial 869	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   187: athrow
    //   188: aload 7
    //   190: astore 6
    //   192: aload 8
    //   194: getfield 871	com/tapjoy/internal/el:b	Ljava/lang/String;
    //   197: astore 10
    //   199: aload 7
    //   201: astore 6
    //   203: aload 10
    //   205: bipush 13
    //   207: ldc_w 873
    //   210: iconst_0
    //   211: bipush 11
    //   213: invokevirtual 877	java/lang/String:regionMatches	(ILjava/lang/String;II)Z
    //   216: ifeq +404 -> 620
    //   219: aload 7
    //   221: astore 6
    //   223: new 879	java/lang/StringBuffer
    //   226: dup
    //   227: invokespecial 880	java/lang/StringBuffer:<init>	()V
    //   230: astore 16
    //   232: aload 7
    //   234: astore 6
    //   236: aload 16
    //   238: aload 10
    //   240: iconst_0
    //   241: bipush 8
    //   243: invokevirtual 436	java/lang/String:substring	(II)Ljava/lang/String;
    //   246: invokevirtual 883	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   249: pop
    //   250: aload 7
    //   252: astore 6
    //   254: aload 16
    //   256: aload 10
    //   258: bipush 24
    //   260: bipush 30
    //   262: invokevirtual 436	java/lang/String:substring	(II)Ljava/lang/String;
    //   265: invokevirtual 883	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   268: pop
    //   269: aload 7
    //   271: astore 6
    //   273: aload 16
    //   275: aload 10
    //   277: bipush 9
    //   279: bipush 13
    //   281: invokevirtual 436	java/lang/String:substring	(II)Ljava/lang/String;
    //   284: invokevirtual 883	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   287: pop
    //   288: aload 7
    //   290: astore 6
    //   292: aload 16
    //   294: aload 10
    //   296: bipush 30
    //   298: invokevirtual 441	java/lang/String:substring	(I)Ljava/lang/String;
    //   301: invokevirtual 883	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   304: pop
    //   305: aload 7
    //   307: astore 6
    //   309: aload 16
    //   311: invokevirtual 884	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   314: astore 10
    //   316: aload 7
    //   318: astore 6
    //   320: aload 8
    //   322: getfield 886	com/tapjoy/internal/el:c	Ljava/lang/String;
    //   325: astore 16
    //   327: aload 9
    //   329: astore 8
    //   331: aload 9
    //   333: ifnonnull +7 -> 340
    //   336: aload 10
    //   338: astore 8
    //   340: aload 7
    //   342: astore 6
    //   344: invokestatic 891	com/tapjoy/internal/fm:a	()Lcom/tapjoy/internal/fm;
    //   347: getstatic 192	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   350: aload 14
    //   352: ldc_w 327
    //   355: ldc_w 893
    //   358: aload 10
    //   360: aload 16
    //   362: invokevirtual 896	com/tapjoy/internal/fm:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   365: aload 7
    //   367: astore 6
    //   369: aload 8
    //   371: putstatic 898	com/tapjoy/TapjoyConnectCore:U	Ljava/lang/String;
    //   374: aload 7
    //   376: astore 6
    //   378: aload 13
    //   380: putstatic 900	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   383: aload 7
    //   385: astore 6
    //   387: aload 14
    //   389: putstatic 902	com/tapjoy/TapjoyConnectCore:W	Ljava/lang/String;
    //   392: aload 7
    //   394: astore 6
    //   396: aload 15
    //   398: putstatic 904	com/tapjoy/TapjoyConnectCore:X	Ljava/lang/String;
    //   401: aload 7
    //   403: astore 6
    //   405: new 577	java/util/ArrayList
    //   408: dup
    //   409: invokespecial 905	java/util/ArrayList:<init>	()V
    //   412: astore 8
    //   414: aload 12
    //   416: ifnull +74 -> 490
    //   419: aload 7
    //   421: astore 6
    //   423: aload 12
    //   425: ldc_w 734
    //   428: invokevirtual 909	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   431: astore 9
    //   433: aload 7
    //   435: astore 6
    //   437: aload 9
    //   439: arraylength
    //   440: istore_3
    //   441: iconst_0
    //   442: istore_2
    //   443: iload_2
    //   444: iload_3
    //   445: if_icmpge +45 -> 490
    //   448: aload 7
    //   450: astore 6
    //   452: aload 9
    //   454: iload_2
    //   455: aaload
    //   456: invokevirtual 798	java/lang/String:trim	()Ljava/lang/String;
    //   459: astore 10
    //   461: aload 7
    //   463: astore 6
    //   465: aload 10
    //   467: invokevirtual 432	java/lang/String:length	()I
    //   470: ifle +245 -> 715
    //   473: aload 7
    //   475: astore 6
    //   477: aload 8
    //   479: aload 10
    //   481: invokeinterface 910 2 0
    //   486: pop
    //   487: goto +228 -> 715
    //   490: aload 7
    //   492: astore 6
    //   494: aload 8
    //   496: invokeinterface 913 1 0
    //   501: ifne +12 -> 513
    //   504: aload 7
    //   506: astore 6
    //   508: aload 8
    //   510: invokestatic 803	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   513: aload 7
    //   515: astore 6
    //   517: aload 7
    //   519: invokevirtual 916	com/tapjoy/internal/br:close	()V
    //   522: iload_1
    //   523: ifne +91 -> 614
    //   526: aload 11
    //   528: instanceof 224
    //   531: istore_1
    //   532: iload_1
    //   533: ifeq +19 -> 552
    //   536: aload 11
    //   538: checkcast 224	java/lang/String
    //   541: invokevirtual 798	java/lang/String:trim	()Ljava/lang/String;
    //   544: invokestatic 922	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   547: lstore 4
    //   549: goto +29 -> 578
    //   552: aload 11
    //   554: instanceof 924
    //   557: istore_1
    //   558: iload_1
    //   559: ifeq +16 -> 575
    //   562: aload 11
    //   564: checkcast 924	java/lang/Number
    //   567: invokevirtual 927	java/lang/Number:longValue	()J
    //   570: lstore 4
    //   572: goto +6 -> 578
    //   575: lconst_0
    //   576: lstore 4
    //   578: lload 4
    //   580: lconst_0
    //   581: lcmp
    //   582: ifgt +12 -> 594
    //   585: invokestatic 933	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   588: invokevirtual 936	com/tapjoy/TapjoyAppSettings:removeConnectResult	()V
    //   591: goto +23 -> 614
    //   594: invokestatic 933	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   597: aload_0
    //   598: invokestatic 938	com/tapjoy/TapjoyConnectCore:s	()Ljava/lang/String;
    //   601: lload 4
    //   603: ldc2_w 939
    //   606: lmul
    //   607: invokestatic 944	com/tapjoy/internal/y:b	()J
    //   610: ladd
    //   611: invokevirtual 948	com/tapjoy/TapjoyAppSettings:saveConnectResultAndParams	(Ljava/lang/String;Ljava/lang/String;J)V
    //   614: aconst_null
    //   615: invokestatic 953	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   618: iconst_1
    //   619: ireturn
    //   620: aload 7
    //   622: astore 6
    //   624: new 955	java/lang/IllegalArgumentException
    //   627: dup
    //   628: ldc_w 957
    //   631: invokespecial 958	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   634: athrow
    //   635: astore_0
    //   636: goto +23 -> 659
    //   639: astore 8
    //   641: aload 7
    //   643: astore_0
    //   644: goto +22 -> 666
    //   647: astore 8
    //   649: aload 7
    //   651: astore_0
    //   652: goto +31 -> 683
    //   655: astore_0
    //   656: aconst_null
    //   657: astore 6
    //   659: goto +44 -> 703
    //   662: astore 8
    //   664: aconst_null
    //   665: astore_0
    //   666: aload_0
    //   667: astore 6
    //   669: ldc_w 372
    //   672: aload 8
    //   674: invokevirtual 959	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   677: invokestatic 961	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   680: goto +17 -> 697
    //   683: aload_0
    //   684: astore 6
    //   686: ldc_w 372
    //   689: aload 8
    //   691: invokevirtual 962	java/io/IOException:getMessage	()Ljava/lang/String;
    //   694: invokestatic 961	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   697: aload_0
    //   698: invokestatic 953	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   701: iconst_0
    //   702: ireturn
    //   703: aload 6
    //   705: invokestatic 953	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   708: aload_0
    //   709: athrow
    //   710: astore 6
    //   712: goto -137 -> 575
    //   715: iload_2
    //   716: iconst_1
    //   717: iadd
    //   718: istore_2
    //   719: goto -276 -> 443
    //   722: astore 8
    //   724: aconst_null
    //   725: astore_0
    //   726: goto -43 -> 683
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	729	0	paramString	String
    //   0	729	1	paramBoolean	boolean
    //   442	277	2	i1	int
    //   440	6	3	i2	int
    //   547	55	4	l1	long
    //   8	696	6	localObject1	Object
    //   710	1	6	localNumberFormatException	NumberFormatException
    //   4	646	7	localBr	com.tapjoy.internal.br
    //   15	494	8	localObject2	Object
    //   639	1	8	localRuntimeException1	RuntimeException
    //   647	1	8	localIOException1	java.io.IOException
    //   662	28	8	localRuntimeException2	RuntimeException
    //   722	1	8	localIOException2	java.io.IOException
    //   37	416	9	localObject3	Object
    //   197	283	10	str1	String
    //   141	422	11	localObject4	Object
    //   125	299	12	str2	String
    //   59	320	13	str3	String
    //   81	307	14	str4	String
    //   103	294	15	str5	String
    //   230	131	16	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   10	17	635	finally
    //   21	39	635	finally
    //   43	61	635	finally
    //   65	83	635	finally
    //   87	105	635	finally
    //   109	127	635	finally
    //   131	143	635	finally
    //   147	158	635	finally
    //   162	173	635	finally
    //   177	188	635	finally
    //   192	199	635	finally
    //   203	219	635	finally
    //   223	232	635	finally
    //   236	250	635	finally
    //   254	269	635	finally
    //   273	288	635	finally
    //   292	305	635	finally
    //   309	316	635	finally
    //   320	327	635	finally
    //   344	365	635	finally
    //   369	374	635	finally
    //   378	383	635	finally
    //   387	392	635	finally
    //   396	401	635	finally
    //   405	414	635	finally
    //   423	433	635	finally
    //   437	441	635	finally
    //   452	461	635	finally
    //   465	473	635	finally
    //   477	487	635	finally
    //   494	504	635	finally
    //   508	513	635	finally
    //   517	522	635	finally
    //   624	635	635	finally
    //   669	680	635	finally
    //   686	697	635	finally
    //   10	17	639	java/lang/RuntimeException
    //   21	39	639	java/lang/RuntimeException
    //   43	61	639	java/lang/RuntimeException
    //   65	83	639	java/lang/RuntimeException
    //   87	105	639	java/lang/RuntimeException
    //   109	127	639	java/lang/RuntimeException
    //   131	143	639	java/lang/RuntimeException
    //   147	158	639	java/lang/RuntimeException
    //   162	173	639	java/lang/RuntimeException
    //   177	188	639	java/lang/RuntimeException
    //   192	199	639	java/lang/RuntimeException
    //   203	219	639	java/lang/RuntimeException
    //   223	232	639	java/lang/RuntimeException
    //   236	250	639	java/lang/RuntimeException
    //   254	269	639	java/lang/RuntimeException
    //   273	288	639	java/lang/RuntimeException
    //   292	305	639	java/lang/RuntimeException
    //   309	316	639	java/lang/RuntimeException
    //   320	327	639	java/lang/RuntimeException
    //   344	365	639	java/lang/RuntimeException
    //   369	374	639	java/lang/RuntimeException
    //   378	383	639	java/lang/RuntimeException
    //   387	392	639	java/lang/RuntimeException
    //   396	401	639	java/lang/RuntimeException
    //   405	414	639	java/lang/RuntimeException
    //   423	433	639	java/lang/RuntimeException
    //   437	441	639	java/lang/RuntimeException
    //   452	461	639	java/lang/RuntimeException
    //   465	473	639	java/lang/RuntimeException
    //   477	487	639	java/lang/RuntimeException
    //   494	504	639	java/lang/RuntimeException
    //   508	513	639	java/lang/RuntimeException
    //   517	522	639	java/lang/RuntimeException
    //   624	635	639	java/lang/RuntimeException
    //   10	17	647	java/io/IOException
    //   21	39	647	java/io/IOException
    //   43	61	647	java/io/IOException
    //   65	83	647	java/io/IOException
    //   87	105	647	java/io/IOException
    //   109	127	647	java/io/IOException
    //   131	143	647	java/io/IOException
    //   147	158	647	java/io/IOException
    //   162	173	647	java/io/IOException
    //   177	188	647	java/io/IOException
    //   192	199	647	java/io/IOException
    //   203	219	647	java/io/IOException
    //   223	232	647	java/io/IOException
    //   236	250	647	java/io/IOException
    //   254	269	647	java/io/IOException
    //   273	288	647	java/io/IOException
    //   292	305	647	java/io/IOException
    //   309	316	647	java/io/IOException
    //   320	327	647	java/io/IOException
    //   344	365	647	java/io/IOException
    //   369	374	647	java/io/IOException
    //   378	383	647	java/io/IOException
    //   387	392	647	java/io/IOException
    //   396	401	647	java/io/IOException
    //   405	414	647	java/io/IOException
    //   423	433	647	java/io/IOException
    //   437	441	647	java/io/IOException
    //   452	461	647	java/io/IOException
    //   465	473	647	java/io/IOException
    //   477	487	647	java/io/IOException
    //   494	504	647	java/io/IOException
    //   508	513	647	java/io/IOException
    //   517	522	647	java/io/IOException
    //   624	635	647	java/io/IOException
    //   0	6	655	finally
    //   526	532	655	finally
    //   536	549	655	finally
    //   552	558	655	finally
    //   562	572	655	finally
    //   585	591	655	finally
    //   594	614	655	finally
    //   0	6	662	java/lang/RuntimeException
    //   526	532	662	java/lang/RuntimeException
    //   536	549	662	java/lang/RuntimeException
    //   552	558	662	java/lang/RuntimeException
    //   562	572	662	java/lang/RuntimeException
    //   585	591	662	java/lang/RuntimeException
    //   594	614	662	java/lang/RuntimeException
    //   536	549	710	java/lang/NumberFormatException
    //   562	572	710	java/lang/NumberFormatException
    //   0	6	722	java/io/IOException
    //   526	532	722	java/io/IOException
    //   536	549	722	java/io/IOException
    //   552	558	722	java/io/IOException
    //   562	572	722	java/io/IOException
    //   585	591	722	java/io/IOException
    //   594	614	722	java/io/IOException
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
    ah.put(paramString1, localObject);
  }
  
  /* Error */
  private static boolean c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 833	com/tapjoy/internal/br:b	(Ljava/lang/String;)Lcom/tapjoy/internal/br;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 983	com/tapjoy/internal/br:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 985	com/tapjoy/internal/br:s	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 372
    //   25: ldc_w 987
    //   28: invokestatic 412	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 916	com/tapjoy/internal/br:close	()V
    //   37: aconst_null
    //   38: invokestatic 953	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 916	com/tapjoy/internal/br:close	()V
    //   49: aconst_null
    //   50: invokestatic 953	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
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
    //   79: ldc_w 372
    //   82: aload_2
    //   83: invokevirtual 959	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   86: invokestatic 961	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: goto +15 -> 104
    //   92: aload_1
    //   93: astore_0
    //   94: ldc_w 372
    //   97: aload_2
    //   98: invokevirtual 962	java/io/IOException:getMessage	()Ljava/lang/String;
    //   101: invokestatic 961	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   104: aload_1
    //   105: invokestatic 953	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   108: ldc_w 372
    //   111: new 456	com/tapjoy/TapjoyErrorMessage
    //   114: dup
    //   115: getstatic 462	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   118: ldc_w 989
    //   121: invokespecial 467	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   124: invokestatic 470	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   127: iconst_0
    //   128: ireturn
    //   129: aload_0
    //   130: invokestatic 953	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   133: aload_1
    //   134: athrow
    //   135: astore_2
    //   136: aconst_null
    //   137: astore_1
    //   138: goto -46 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	paramString	String
    //   4	42	1	localBr	com.tapjoy.internal.br
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
      fm.a().a(g, h, "11.8.1", "https://rpc.tapjoy.com/", O, N);
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
    TapjoyUtil.safePut(localHashMap3, "library_revision", "76c44ce", true);
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
    if ((o != null) && (o.length() != 0) && (System.currentTimeMillis() - ac <= 1800000L)) {
      ac = System.currentTimeMillis();
    } else {
      o = p();
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
    i();
    localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "analytics_id", ak, true);
    TapjoyUtil.safePut(localHashMap3, "pkg_id", al, true);
    TapjoyUtil.safePut(localHashMap3, "pkg_sign", am, true);
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(aM);
    TapjoyUtil.safePut(localHashMap3, "display_d", localStringBuilder.toString(), true);
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(aN);
    TapjoyUtil.safePut(localHashMap3, "display_w", localStringBuilder.toString(), true);
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(aO);
    TapjoyUtil.safePut(localHashMap3, "display_h", localStringBuilder.toString(), true);
    TapjoyUtil.safePut(localHashMap3, "country_sim", aP, true);
    TapjoyUtil.safePut(localHashMap3, "timezone", aQ, true);
    localHashMap2.putAll(localHashMap3);
    localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "pkg_ver", an, true);
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(ao);
    TapjoyUtil.safePut(localHashMap3, "pkg_rev", localStringBuilder.toString(), true);
    TapjoyUtil.safePut(localHashMap3, "pkg_data_ver", ap, true);
    TapjoyUtil.safePut(localHashMap3, "installer", aq, true);
    if (cr.c(M)) {
      TapjoyUtil.safePut(localHashMap3, "store_name", aR, true);
    }
    localHashMap2.putAll(localHashMap3);
    localHashMap2.putAll(h());
    localHashMap1.putAll(localHashMap2);
    return localHashMap1;
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
      ((StringBuilder)localObject).append(q());
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
      StringBuilder localStringBuilder = new StringBuilder("Error generating sha1 of macAddress: ");
      localStringBuilder.append(localException.toString());
      TapjoyLog.e("TapjoyConnect", localStringBuilder.toString());
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
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(ar);
    TapjoyUtil.safePut(localHashMap, "installed", ((StringBuilder)localObject).toString(), true);
    TapjoyUtil.safePut(localHashMap, "referrer", as, true);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(at);
    TapjoyUtil.safePut(localHashMap, "user_level", ((StringBuilder)localObject).toString(), true);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(au);
    TapjoyUtil.safePut(localHashMap, "friend_count", ((StringBuilder)localObject).toString(), true);
    TapjoyUtil.safePut(localHashMap, "uv1", av, true);
    TapjoyUtil.safePut(localHashMap, "uv2", aw, true);
    TapjoyUtil.safePut(localHashMap, "uv3", ax, true);
    TapjoyUtil.safePut(localHashMap, "uv4", ay, true);
    TapjoyUtil.safePut(localHashMap, "uv5", az, true);
    localObject = aA.iterator();
    int i1 = 0;
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      StringBuilder localStringBuilder = new StringBuilder("user_tags[");
      localStringBuilder.append(i1);
      localStringBuilder.append("]");
      TapjoyUtil.safePut(localHashMap, localStringBuilder.toString(), str, true);
      i1 += 1;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(aB);
    TapjoyUtil.safePut(localHashMap, "fq7", ((StringBuilder)localObject).toString(), true);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(aC);
    TapjoyUtil.safePut(localHashMap, "fq30", ((StringBuilder)localObject).toString(), true);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(aD);
    TapjoyUtil.safePut(localHashMap, "session_total_count", ((StringBuilder)localObject).toString(), true);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(aE);
    TapjoyUtil.safePut(localHashMap, "session_total_length", ((StringBuilder)localObject).toString(), true);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(aF);
    TapjoyUtil.safePut(localHashMap, "session_last_at", ((StringBuilder)localObject).toString(), true);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(aG);
    TapjoyUtil.safePut(localHashMap, "session_last_length", ((StringBuilder)localObject).toString(), true);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(aH);
    TapjoyUtil.safePut(localHashMap, "purchase_currency", ((StringBuilder)localObject).toString(), true);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(aI);
    TapjoyUtil.safePut(localHashMap, "purchase_total_count", ((StringBuilder)localObject).toString(), true);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(aJ);
    TapjoyUtil.safePut(localHashMap, "purchase_total_price", ((StringBuilder)localObject).toString(), true);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(aK);
    TapjoyUtil.safePut(localHashMap, "purchase_last_price", ((StringBuilder)localObject).toString(), true);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(aL);
    TapjoyUtil.safePut(localHashMap, "purchase_last_at", ((StringBuilder)localObject).toString(), true);
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
    Object localObject = ah.entrySet().iterator();
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
  
  private static void k()
  {
    for (;;)
    {
      int i1;
      try
      {
        if (af != null)
        {
          ApplicationInfo localApplicationInfo = af.getApplicationInfo(g.getPackageName(), 128);
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
                b((String)localObject2, (String)localObject3);
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
  
  private void l()
  {
    for (;;)
    {
      try
      {
        localObject1 = Arrays.asList(af.getPackageInfo(g.getPackageName(), 1).activities);
        if (localObject1 != null)
        {
          localObject2 = ((List)localObject1).iterator();
          if (((Iterator)localObject2).hasNext())
          {
            localActivityInfo = (ActivityInfo)((Iterator)localObject2).next();
            if (!m.contains(localActivityInfo.name)) {
              continue;
            }
            i1 = m.indexOf(localActivityInfo.name);
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Object localObject1;
        Object localObject2;
        ActivityInfo localActivityInfo;
        int i1;
        continue;
      }
      try
      {
        Class.forName((String)m.get(i1));
        localObject1 = new Vector();
        if ((localActivityInfo.configChanges & 0x80) != 128) {
          ((Vector)localObject1).add("orientation");
        }
        if ((localActivityInfo.configChanges & 0x20) != 32) {
          ((Vector)localObject1).add("keyboardHidden");
        }
        if (((Vector)localObject1).size() != 0)
        {
          if (((Vector)localObject1).size() == 1)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(((Vector)localObject1).toString());
            ((StringBuilder)localObject2).append(" property is not specified in manifest configChanges for ");
            ((StringBuilder)localObject2).append((String)m.get(i1));
            throw new TapjoyIntegrationException(((StringBuilder)localObject2).toString());
          }
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(((Vector)localObject1).toString());
          ((StringBuilder)localObject2).append(" properties are not specified in manifest configChanges for ");
          ((StringBuilder)localObject2).append((String)m.get(i1));
          throw new TapjoyIntegrationException(((StringBuilder)localObject2).toString());
        }
        if ((Build.VERSION.SDK_INT >= 13) && ((localActivityInfo.configChanges & 0x400) != 1024))
        {
          localObject1 = new StringBuilder("WARNING -- screenSize property is not specified in manifest configChanges for ");
          ((StringBuilder)localObject1).append((String)m.get(i1));
          TapjoyLog.w("TapjoyConnect", ((StringBuilder)localObject1).toString());
        }
        if ((Build.VERSION.SDK_INT >= 11) && (localActivityInfo.name.equals("com.tapjoy.TJAdUnitActivity")) && ((localActivityInfo.flags & 0x200) != 512))
        {
          localObject1 = new StringBuilder("'hardwareAccelerated' property not specified in manifest for ");
          ((StringBuilder)localObject1).append((String)m.get(i1));
          throw new TapjoyIntegrationException(((StringBuilder)localObject1).toString());
        }
        m.remove(i1);
      }
      catch (ClassNotFoundException localClassNotFoundException1) {}
    }
    localObject1 = new StringBuilder("[ClassNotFoundException] Could not find dependency class ");
    ((StringBuilder)localObject1).append((String)m.get(i1));
    throw new TapjoyIntegrationException(((StringBuilder)localObject1).toString());
    if (m.size() != 0)
    {
      if (m.size() == 1)
      {
        localObject1 = new StringBuilder("Missing ");
        ((StringBuilder)localObject1).append(m.size());
        ((StringBuilder)localObject1).append(" dependency class in manifest: ");
        ((StringBuilder)localObject1).append(m.toString());
        throw new TapjoyIntegrationException(((StringBuilder)localObject1).toString());
      }
      localObject1 = new StringBuilder("Missing ");
      ((StringBuilder)localObject1).append(m.size());
      ((StringBuilder)localObject1).append(" dependency classes in manifest: ");
      ((StringBuilder)localObject1).append(m.toString());
      throw new TapjoyIntegrationException(((StringBuilder)localObject1).toString());
    }
    m();
    for (;;)
    {
      try
      {
        localObject1 = Class.forName("com.tapjoy.TJAdUnitJSBridge");
      }
      catch (ClassNotFoundException localClassNotFoundException2)
      {
        continue;
      }
      try
      {
        ((Class)localObject1).getMethod("closeRequested", new Class[] { Boolean.class });
        localObject2 = (String)TapjoyUtil.getResource("mraid.js");
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", g);
        }
        if (localObject1 == null) {
          throw new TapjoyIntegrationException("ClassNotFoundException: mraid.js was not found.");
        }
        if ((getConnectFlagValue("TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK") != null) && (getConnectFlagValue("TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK").equals("true")))
        {
          TapjoyLog.i("TapjoyConnect", "Skipping integration check for Google Play Services and Advertising ID. Do this only if you do not have access to Google Play Services.");
          return;
        }
        this.ag.checkGooglePlayIntegration();
        return;
      }
      catch (NoSuchMethodException localNoSuchMethodException) {}
    }
    throw new TapjoyIntegrationException("Try configuring Proguard or other code obfuscators to ignore com.tapjoy classes. Visit http://dev.tapjoy.comfor more information.");
    throw new TapjoyIntegrationException("ClassNotFoundException: com.tapjoy.TJAdUnitJSBridge was not found.");
    throw new TapjoyIntegrationException("NameNotFoundException: Could not find package.");
  }
  
  private static void m()
  {
    Vector localVector = new Vector();
    Object localObject = TapjoyConstants.dependencyPermissions;
    int i2 = 0;
    int i3 = localObject.length;
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
        ac = System.currentTimeMillis();
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
  
  private static String q()
  {
    if (o()) {
      return c;
    }
    String str = p;
    int i2 = 0;
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
    if (n()) {
      return c;
    }
    int i1 = i2;
    if (n != null)
    {
      i1 = i2;
      if (n.length() > 0) {
        i1 = 1;
      }
    }
    if (i1 != 0) {
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
      localObject = (ArrayList)ah.get("TJC_OPTION_MEDIATION_CONFIGS");
      if (localObject == null) {
        break label91;
      }
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
      el localEl = new el(paramString);
      if (localEl.a != el.a.SDK_ANDROID) {
        throw new IllegalArgumentException("The given API key was not for Android.");
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
      return;
    }
    catch (IllegalArgumentException paramContext)
    {
      throw new TapjoyIntegrationException(paramContext.getMessage());
    }
  }
  
  private static String s()
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
    new Thread(new Runnable()
    {
      public final void run()
      {
        Object localObject = new StringBuilder("Setting userID to ");
        ((StringBuilder)localObject).append(TapjoyConnectCore.d());
        TapjoyLog.i("TapjoyConnect", ((StringBuilder)localObject).toString());
        localObject = TapjoyConnectCore.e();
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
    Object localObject = new StringBuilder("actionComplete: ");
    ((StringBuilder)localObject).append(paramString);
    TapjoyLog.i("TapjoyConnect", ((StringBuilder)localObject).toString());
    localObject = g();
    TapjoyUtil.safePut((Map)localObject, "app_id", paramString, true);
    ((Map)localObject).putAll(getTimeStampAndVerifierParams());
    paramString = new StringBuilder("PPA URL parameters: ");
    paramString.append(localObject);
    TapjoyLog.d("TapjoyConnect", paramString.toString());
    new Thread(new PPAThread((Map)localObject)).start();
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
        ae = true;
        r();
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
          eo.a.notifyObservers();
        }
        eo.b.notifyObservers(Boolean.TRUE);
      }
      else
      {
        if (i1 == 0) {
          f();
        }
        eo.b.notifyObservers(Boolean.FALSE);
      }
      if (ai.length() > 0)
      {
        localObject1 = getGenericURLParams();
        TapjoyUtil.safePut((Map)localObject1, "package_names", ai, true);
        long l1 = System.currentTimeMillis() / 1000L;
        localObject2 = a(l1, ai);
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
        f();
      }
      eo.b.notifyObservers(Boolean.FALSE);
    }
  }
  
  public void enablePaidAppWithActionID(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder("enablePaidAppWithActionID: ");
    localStringBuilder.append(paramString);
    TapjoyLog.i("TapjoyConnect", localStringBuilder.toString());
    Y = paramString;
    this.Z = g.getSharedPreferences("tjcPrefrences", 0).getLong("tapjoy_elapsed_time", 0L);
    paramString = new StringBuilder("paidApp elapsed: ");
    paramString.append(this.Z);
    TapjoyLog.d("TapjoyConnect", paramString.toString());
    if (this.Z >= 900000L)
    {
      if ((Y != null) && (Y.length() > 0))
      {
        TapjoyLog.d("TapjoyConnect", "Calling PPA actionComplete...");
        actionComplete(Y);
      }
    }
    else if (this.aa == null)
    {
      this.aa = new Timer();
      this.aa.schedule(new a((byte)0), 10000L, 10000L);
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
  
  public String getSerial()
  {
    Object localObject2 = null;
    try
    {
      Object localObject1 = Class.forName("android.os.Build").getDeclaredField("SERIAL");
      if (!((Field)localObject1).isAccessible()) {
        ((Field)localObject1).setAccessible(true);
      }
      String str = ((Field)localObject1).get(Build.class).toString();
      try
      {
        localObject1 = new StringBuilder("serial: ");
        ((StringBuilder)localObject1).append(str);
        TapjoyLog.d("TapjoyConnect", ((StringBuilder)localObject1).toString());
        return str;
      }
      catch (Exception localException1)
      {
        localObject2 = str;
      }
      TapjoyLog.e("TapjoyConnect", localException2.toString());
    }
    catch (Exception localException2) {}
    return localObject2;
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
    StringBuilder localStringBuilder = new StringBuilder("setVirtualCurrencyMultiplier: ");
    localStringBuilder.append(paramFloat);
    TapjoyLog.i("TapjoyConnect", localStringBuilder.toString());
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
      Object localObject = TapjoyConnectCore.e();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(TapjoyConnectCore.getHostURL());
      localStringBuilder.append("api/connect/v3.json?");
      localObject = ((TapjoyURLConnection)localObject).getResponseFromURL(localStringBuilder.toString(), null, null, this.b);
      if (((TapjoyHttpURLResponse)localObject).response != null) {
        TapjoyConnectCore.b(((TapjoyHttpURLResponse)localObject).response);
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
      Object localObject = new StringBuilder("elapsed_time: ");
      ((StringBuilder)localObject).append(TapjoyConnectCore.c(TapjoyConnectCore.this));
      ((StringBuilder)localObject).append(" (");
      ((StringBuilder)localObject).append(TapjoyConnectCore.c(TapjoyConnectCore.this) / 1000L / 60L);
      ((StringBuilder)localObject).append("m ");
      ((StringBuilder)localObject).append(TapjoyConnectCore.c(TapjoyConnectCore.this) / 1000L % 60L);
      ((StringBuilder)localObject).append("s)");
      TapjoyLog.d("TapjoyConnect", ((StringBuilder)localObject).toString());
      localObject = TapjoyConnectCore.b().getSharedPreferences("tjcPrefrences", 0).edit();
      ((SharedPreferences.Editor)localObject).putLong("tapjoy_elapsed_time", TapjoyConnectCore.c(TapjoyConnectCore.this));
      ((SharedPreferences.Editor)localObject).commit();
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
