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
import com.tapjoy.internal.es;
import com.tapjoy.internal.eu;
import com.tapjoy.internal.fu;
import com.tapjoy.internal.gl.a;
import com.tapjoy.internal.gl.l;
import com.tapjoy.internal.gl.n;
import com.tapjoy.internal.gl.z;
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
    //   28: invokestatic 293	com/tapjoy/internal/eu:a	()Lcom/tapjoy/internal/eu;
    //   31: aload_1
    //   32: invokevirtual 295	com/tapjoy/internal/eu:a	(Landroid/content/Context;)V
    //   35: new 297	com/tapjoy/TapjoyURLConnection
    //   38: dup
    //   39: invokespecial 298	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   42: putstatic 138	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   45: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   48: invokevirtual 304	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   51: putstatic 306	com/tapjoy/TapjoyConnectCore:af	Landroid/content/pm/PackageManager;
    //   54: aload_0
    //   55: new 308	com/tapjoy/TapjoyGpsHelper
    //   58: dup
    //   59: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   62: invokespecial 310	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   65: putfield 312	com/tapjoy/TapjoyConnectCore:ag	Lcom/tapjoy/TapjoyGpsHelper;
    //   68: getstatic 261	com/tapjoy/TapjoyConnectCore:ah	Ljava/util/Hashtable;
    //   71: ifnonnull +13 -> 84
    //   74: new 314	java/util/Hashtable
    //   77: dup
    //   78: invokespecial 315	java/util/Hashtable:<init>	()V
    //   81: putstatic 261	com/tapjoy/TapjoyConnectCore:ah	Ljava/util/Hashtable;
    //   84: ldc_w 317
    //   87: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   90: ifnull +22 -> 112
    //   93: ldc_w 317
    //   96: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   99: ldc_w 323
    //   102: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   105: ifeq +7 -> 112
    //   108: iconst_1
    //   109: invokestatic 335	com/tapjoy/TapjoyLog:setDebugEnabled	(Z)V
    //   112: invokestatic 337	com/tapjoy/TapjoyConnectCore:k	()V
    //   115: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   118: invokevirtual 341	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   121: ldc_w 343
    //   124: aconst_null
    //   125: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   128: invokevirtual 347	android/content/Context:getPackageName	()Ljava/lang/String;
    //   131: invokevirtual 353	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   134: istore_2
    //   135: new 355	java/util/Properties
    //   138: dup
    //   139: invokespecial 356	java/util/Properties:<init>	()V
    //   142: astore_1
    //   143: aload_1
    //   144: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   147: invokevirtual 341	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   150: iload_2
    //   151: invokevirtual 360	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   154: invokevirtual 364	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   157: aload_1
    //   158: invokestatic 367	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   161: ldc_w 369
    //   164: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   167: ldc -92
    //   169: if_acmpne +7 -> 176
    //   172: aload_0
    //   173: invokespecial 371	com/tapjoy/TapjoyConnectCore:l	()V
    //   176: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   179: invokevirtual 375	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   182: ldc_w 377
    //   185: invokestatic 383	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   188: astore_1
    //   189: aload_1
    //   190: putstatic 166	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   193: aload_1
    //   194: ifnull +12 -> 206
    //   197: getstatic 166	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   200: invokevirtual 386	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   203: putstatic 166	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   206: getstatic 306	com/tapjoy/TapjoyConnectCore:af	Landroid/content/pm/PackageManager;
    //   209: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   212: invokevirtual 347	android/content/Context:getPackageName	()Ljava/lang/String;
    //   215: iconst_0
    //   216: invokevirtual 392	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   219: getfield 397	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   222: putstatic 186	com/tapjoy/TapjoyConnectCore:x	Ljava/lang/String;
    //   225: ldc_w 399
    //   228: putstatic 180	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   231: ldc_w 399
    //   234: putstatic 202	com/tapjoy/TapjoyConnectCore:F	Ljava/lang/String;
    //   237: getstatic 404	android/os/Build:MODEL	Ljava/lang/String;
    //   240: putstatic 176	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   243: getstatic 407	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   246: putstatic 178	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   249: getstatic 412	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   252: putstatic 182	com/tapjoy/TapjoyConnectCore:v	Ljava/lang/String;
    //   255: ldc_w 414
    //   258: putstatic 188	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   261: ldc_w 416
    //   264: putstatic 190	com/tapjoy/TapjoyConnectCore:z	Ljava/lang/String;
    //   267: getstatic 419	android/os/Build$VERSION:SDK_INT	I
    //   270: iconst_3
    //   271: if_icmple +35 -> 306
    //   274: new 421	com/tapjoy/TapjoyDisplayMetricsUtil
    //   277: dup
    //   278: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   281: invokespecial 422	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   284: astore_1
    //   285: aload_1
    //   286: invokevirtual 426	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   289: putstatic 192	com/tapjoy/TapjoyConnectCore:A	I
    //   292: aload_1
    //   293: invokevirtual 430	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   296: putstatic 194	com/tapjoy/TapjoyConnectCore:B	F
    //   299: aload_1
    //   300: invokevirtual 433	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   303: putstatic 196	com/tapjoy/TapjoyConnectCore:C	I
    //   306: ldc_w 435
    //   309: invokestatic 438	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   312: istore_3
    //   313: iload_3
    //   314: ifeq +914 -> 1228
    //   317: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   320: ldc_w 440
    //   323: invokevirtual 444	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   326: checkcast 446	android/net/wifi/WifiManager
    //   329: astore_1
    //   330: aload_1
    //   331: ifnull +42 -> 373
    //   334: aload_1
    //   335: invokevirtual 450	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   338: astore_1
    //   339: aload_1
    //   340: ifnull +33 -> 373
    //   343: aload_1
    //   344: invokevirtual 455	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   347: astore_1
    //   348: aload_1
    //   349: putstatic 172	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   352: aload_1
    //   353: ifnull +20 -> 373
    //   356: getstatic 172	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   359: ldc_w 457
    //   362: ldc -92
    //   364: invokevirtual 461	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   367: invokevirtual 386	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   370: putstatic 172	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   373: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   376: ldc_w 463
    //   379: invokevirtual 444	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   382: checkcast 465	android/telephony/TelephonyManager
    //   385: astore_1
    //   386: aload_1
    //   387: ifnull +239 -> 626
    //   390: aload_1
    //   391: invokevirtual 468	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   394: putstatic 204	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   397: aload_1
    //   398: invokevirtual 471	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   401: putstatic 206	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   404: aload_1
    //   405: invokevirtual 474	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   408: astore 4
    //   410: aload 4
    //   412: ifnull +41 -> 453
    //   415: aload 4
    //   417: invokevirtual 477	java/lang/String:length	()I
    //   420: iconst_5
    //   421: if_icmpeq +13 -> 434
    //   424: aload 4
    //   426: invokevirtual 477	java/lang/String:length	()I
    //   429: bipush 6
    //   431: if_icmpne +22 -> 453
    //   434: aload 4
    //   436: iconst_0
    //   437: iconst_3
    //   438: invokevirtual 481	java/lang/String:substring	(II)Ljava/lang/String;
    //   441: putstatic 208	com/tapjoy/TapjoyConnectCore:I	Ljava/lang/String;
    //   444: aload 4
    //   446: iconst_3
    //   447: invokevirtual 484	java/lang/String:substring	(I)Ljava/lang/String;
    //   450: putstatic 210	com/tapjoy/TapjoyConnectCore:J	Ljava/lang/String;
    //   453: ldc_w 486
    //   456: invokestatic 438	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   459: istore_3
    //   460: iload_3
    //   461: ifeq +989 -> 1450
    //   464: ldc_w 488
    //   467: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   470: ifnull +770 -> 1240
    //   473: ldc_w 488
    //   476: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   479: invokevirtual 477	java/lang/String:length	()I
    //   482: ifle +758 -> 1240
    //   485: ldc_w 488
    //   488: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   491: putstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   494: ldc_w 490
    //   497: new 492	java/lang/StringBuilder
    //   500: dup
    //   501: ldc_w 494
    //   504: invokespecial 497	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   507: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   510: invokevirtual 501	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   513: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   516: invokestatic 507	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   519: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   522: ifnonnull +772 -> 1294
    //   525: ldc_w 490
    //   528: new 509	com/tapjoy/TapjoyErrorMessage
    //   531: dup
    //   532: getstatic 515	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   535: ldc_w 517
    //   538: invokespecial 520	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   541: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   544: iconst_0
    //   545: istore_2
    //   546: ldc_w 490
    //   549: new 492	java/lang/StringBuilder
    //   552: dup
    //   553: ldc_w 525
    //   556: invokespecial 497	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   559: getstatic 419	android/os/Build$VERSION:SDK_INT	I
    //   562: invokevirtual 528	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   565: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   568: invokestatic 530	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   571: getstatic 419	android/os/Build$VERSION:SDK_INT	I
    //   574: bipush 9
    //   576: if_icmplt +50 -> 626
    //   579: ldc_w 490
    //   582: ldc_w 532
    //   585: invokestatic 507	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   588: aload_0
    //   589: invokevirtual 535	com/tapjoy/TapjoyConnectCore:getSerial	()Ljava/lang/String;
    //   592: astore_1
    //   593: iload_2
    //   594: ifne +7 -> 601
    //   597: aload_1
    //   598: putstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   601: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   604: ifnonnull +764 -> 1368
    //   607: ldc_w 490
    //   610: new 509	com/tapjoy/TapjoyErrorMessage
    //   613: dup
    //   614: getstatic 515	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   617: ldc_w 537
    //   620: invokespecial 520	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   623: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   626: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   629: ldc_w 539
    //   632: iconst_0
    //   633: invokevirtual 543	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   636: astore_1
    //   637: aload_1
    //   638: ldc_w 545
    //   641: ldc -92
    //   643: invokeinterface 550 3 0
    //   648: astore 4
    //   650: aload 4
    //   652: putstatic 174	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   655: aload 4
    //   657: ifnull +14 -> 671
    //   660: getstatic 174	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   663: invokevirtual 477	java/lang/String:length	()I
    //   666: istore_2
    //   667: iload_2
    //   668: ifne +61 -> 729
    //   671: new 492	java/lang/StringBuilder
    //   674: dup
    //   675: invokespecial 551	java/lang/StringBuilder:<init>	()V
    //   678: invokestatic 557	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   681: invokevirtual 558	java/util/UUID:toString	()Ljava/lang/String;
    //   684: invokevirtual 501	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   687: invokestatic 564	java/lang/System:currentTimeMillis	()J
    //   690: invokevirtual 567	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   693: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   696: invokestatic 572	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   699: putstatic 174	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   702: aload_1
    //   703: invokeinterface 576 1 0
    //   708: astore_1
    //   709: aload_1
    //   710: ldc_w 545
    //   713: getstatic 174	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   716: invokeinterface 582 3 0
    //   721: pop
    //   722: aload_1
    //   723: invokeinterface 586 1 0
    //   728: pop
    //   729: ldc_w 588
    //   732: ldc_w 590
    //   735: invokestatic 593	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   738: putstatic 200	com/tapjoy/TapjoyConnectCore:E	Z
    //   741: ldc_w 595
    //   744: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   747: ifnull +71 -> 818
    //   750: ldc_w 595
    //   753: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   756: invokevirtual 477	java/lang/String:length	()I
    //   759: ifle +59 -> 818
    //   762: ldc_w 595
    //   765: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   768: putstatic 216	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   771: new 597	java/util/ArrayList
    //   774: dup
    //   775: getstatic 600	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   778: invokestatic 156	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   781: invokespecial 601	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   784: getstatic 216	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   787: invokevirtual 604	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   790: ifne +28 -> 818
    //   793: ldc_w 490
    //   796: new 492	java/lang/StringBuilder
    //   799: dup
    //   800: ldc_w 606
    //   803: invokespecial 497	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   806: getstatic 216	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   809: invokevirtual 501	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   812: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   815: invokestatic 608	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   818: getstatic 216	com/tapjoy/TapjoyConnectCore:M	Ljava/lang/String;
    //   821: astore_1
    //   822: new 610	android/content/Intent
    //   825: dup
    //   826: ldc_w 612
    //   829: invokespecial 613	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   832: astore 4
    //   834: aload_1
    //   835: invokevirtual 477	java/lang/String:length	()I
    //   838: ifgt +684 -> 1522
    //   841: aload 4
    //   843: ldc_w 615
    //   846: invokestatic 621	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   849: invokevirtual 625	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   852: pop
    //   853: getstatic 306	com/tapjoy/TapjoyConnectCore:af	Landroid/content/pm/PackageManager;
    //   856: aload 4
    //   858: iconst_0
    //   859: invokevirtual 629	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   862: invokeinterface 634 1 0
    //   867: ifle +735 -> 1602
    //   870: iconst_1
    //   871: istore_3
    //   872: iload_3
    //   873: putstatic 232	com/tapjoy/TapjoyConnectCore:T	Z
    //   876: invokestatic 636	com/tapjoy/TapjoyConnectCore:i	()V
    //   879: ldc_w 638
    //   882: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   885: ifnull +24 -> 909
    //   888: ldc_w 638
    //   891: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   894: invokevirtual 477	java/lang/String:length	()I
    //   897: ifle +12 -> 909
    //   900: ldc_w 638
    //   903: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   906: putstatic 254	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   909: ldc_w 640
    //   912: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   915: ifnull +24 -> 939
    //   918: ldc_w 640
    //   921: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   924: invokevirtual 477	java/lang/String:length	()I
    //   927: ifle +12 -> 939
    //   930: ldc_w 640
    //   933: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   936: putstatic 252	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   939: ldc_w 642
    //   942: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   945: ifnull +53 -> 998
    //   948: ldc_w 642
    //   951: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   954: invokevirtual 477	java/lang/String:length	()I
    //   957: ifle +41 -> 998
    //   960: ldc_w 490
    //   963: new 492	java/lang/StringBuilder
    //   966: dup
    //   967: ldc_w 644
    //   970: invokespecial 497	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   973: ldc_w 642
    //   976: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   979: invokevirtual 501	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   982: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   985: invokestatic 530	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   988: ldc_w 642
    //   991: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   994: aconst_null
    //   995: invokestatic 648	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;Lcom/tapjoy/TJSetUserIDListener;)V
    //   998: ldc_w 650
    //   1001: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1004: invokestatic 653	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   1007: putstatic 228	com/tapjoy/TapjoyConnectCore:R	Ljava/lang/String;
    //   1010: new 492	java/lang/StringBuilder
    //   1013: dup
    //   1014: ldc_w 494
    //   1017: invokespecial 497	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1020: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1023: invokevirtual 501	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1026: astore 4
    //   1028: ldc_w 488
    //   1031: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1034: ifnull +558 -> 1592
    //   1037: ldc_w 488
    //   1040: invokestatic 321	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1043: invokevirtual 477	java/lang/String:length	()I
    //   1046: ifle +546 -> 1592
    //   1049: ldc_w 655
    //   1052: astore_1
    //   1053: ldc_w 490
    //   1056: aload 4
    //   1058: aload_1
    //   1059: invokevirtual 501	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1062: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1065: invokestatic 507	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1068: getstatic 261	com/tapjoy/TapjoyConnectCore:ah	Ljava/util/Hashtable;
    //   1071: ifnull +6 -> 1077
    //   1074: invokestatic 657	com/tapjoy/TapjoyConnectCore:j	()V
    //   1077: aload_0
    //   1078: invokevirtual 660	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   1081: aload_0
    //   1082: iconst_1
    //   1083: putfield 288	com/tapjoy/TapjoyConnectCore:ad	Z
    //   1086: return
    //   1087: astore_1
    //   1088: new 275	com/tapjoy/TapjoyException
    //   1091: dup
    //   1092: aload_1
    //   1093: invokevirtual 663	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   1096: invokespecial 664	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   1099: athrow
    //   1100: astore_1
    //   1101: ldc_w 490
    //   1104: new 509	com/tapjoy/TapjoyErrorMessage
    //   1107: dup
    //   1108: getstatic 667	com/tapjoy/TapjoyErrorMessage$ErrorType:INTEGRATION_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1111: aload_1
    //   1112: invokevirtual 668	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   1115: invokespecial 520	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1118: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1121: invokestatic 670	com/tapjoy/TapjoyConnectCore:f	()V
    //   1124: getstatic 675	com/tapjoy/internal/eo:b	Lcom/tapjoy/internal/eo$a;
    //   1127: getstatic 681	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1130: invokevirtual 687	com/tapjoy/internal/eo$a:notifyObservers	(Ljava/lang/Object;)V
    //   1133: return
    //   1134: astore_1
    //   1135: ldc_w 490
    //   1138: new 492	java/lang/StringBuilder
    //   1141: dup
    //   1142: ldc_w 689
    //   1145: invokespecial 497	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1148: aload_1
    //   1149: invokevirtual 690	java/lang/Exception:toString	()Ljava/lang/String;
    //   1152: invokevirtual 501	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1155: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1158: invokestatic 692	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1161: goto -855 -> 306
    //   1164: astore_1
    //   1165: ldc_w 490
    //   1168: new 509	com/tapjoy/TapjoyErrorMessage
    //   1171: dup
    //   1172: getstatic 515	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1175: aload_1
    //   1176: invokevirtual 693	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   1179: invokespecial 520	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1182: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1185: invokestatic 670	com/tapjoy/TapjoyConnectCore:f	()V
    //   1188: getstatic 675	com/tapjoy/internal/eo:b	Lcom/tapjoy/internal/eo$a;
    //   1191: getstatic 681	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   1194: invokevirtual 687	com/tapjoy/internal/eo$a:notifyObservers	(Ljava/lang/Object;)V
    //   1197: return
    //   1198: astore_1
    //   1199: ldc_w 490
    //   1202: new 492	java/lang/StringBuilder
    //   1205: dup
    //   1206: ldc_w 695
    //   1209: invokespecial 497	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1212: aload_1
    //   1213: invokevirtual 690	java/lang/Exception:toString	()Ljava/lang/String;
    //   1216: invokevirtual 501	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1219: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1222: invokestatic 692	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1225: goto -852 -> 373
    //   1228: ldc_w 490
    //   1231: ldc_w 697
    //   1234: invokestatic 507	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1237: goto -864 -> 373
    //   1240: aload_1
    //   1241: invokevirtual 700	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1244: putstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1247: goto -753 -> 494
    //   1250: astore_1
    //   1251: ldc_w 490
    //   1254: new 509	com/tapjoy/TapjoyErrorMessage
    //   1257: dup
    //   1258: getstatic 515	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1261: new 492	java/lang/StringBuilder
    //   1264: dup
    //   1265: ldc_w 702
    //   1268: invokespecial 497	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1271: aload_1
    //   1272: invokevirtual 690	java/lang/Exception:toString	()Ljava/lang/String;
    //   1275: invokevirtual 501	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1278: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1281: invokespecial 520	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1284: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1287: aconst_null
    //   1288: putstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1291: goto -665 -> 626
    //   1294: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1297: invokevirtual 477	java/lang/String:length	()I
    //   1300: ifeq +27 -> 1327
    //   1303: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1306: ldc_w 704
    //   1309: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1312: ifne +15 -> 1327
    //   1315: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1318: ldc_w 706
    //   1321: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1324: ifeq +27 -> 1351
    //   1327: ldc_w 490
    //   1330: new 509	com/tapjoy/TapjoyErrorMessage
    //   1333: dup
    //   1334: getstatic 515	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1337: ldc_w 708
    //   1340: invokespecial 520	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1343: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1346: iconst_0
    //   1347: istore_2
    //   1348: goto -802 -> 546
    //   1351: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1354: invokestatic 714	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1357: invokevirtual 717	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1360: putstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1363: iconst_1
    //   1364: istore_2
    //   1365: goto -819 -> 546
    //   1368: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1371: invokevirtual 477	java/lang/String:length	()I
    //   1374: ifeq +39 -> 1413
    //   1377: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1380: ldc_w 704
    //   1383: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1386: ifne +27 -> 1413
    //   1389: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1392: ldc_w 706
    //   1395: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1398: ifne +15 -> 1413
    //   1401: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1404: ldc_w 719
    //   1407: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1410: ifeq +25 -> 1435
    //   1413: ldc_w 490
    //   1416: new 509	com/tapjoy/TapjoyErrorMessage
    //   1419: dup
    //   1420: getstatic 515	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   1423: ldc_w 721
    //   1426: invokespecial 520	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   1429: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   1432: goto -806 -> 626
    //   1435: getstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1438: invokestatic 714	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1441: invokevirtual 717	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1444: putstatic 170	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   1447: goto -821 -> 626
    //   1450: ldc_w 490
    //   1453: ldc_w 723
    //   1456: invokestatic 507	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1459: goto -833 -> 626
    //   1462: astore_1
    //   1463: ldc_w 490
    //   1466: new 492	java/lang/StringBuilder
    //   1469: dup
    //   1470: ldc_w 725
    //   1473: invokespecial 497	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1476: aload_1
    //   1477: invokevirtual 690	java/lang/Exception:toString	()Ljava/lang/String;
    //   1480: invokevirtual 501	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1483: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1486: invokestatic 692	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1489: goto -760 -> 729
    //   1492: astore_1
    //   1493: ldc_w 490
    //   1496: new 492	java/lang/StringBuilder
    //   1499: dup
    //   1500: ldc_w 727
    //   1503: invokespecial 497	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1506: aload_1
    //   1507: invokevirtual 690	java/lang/Exception:toString	()Ljava/lang/String;
    //   1510: invokevirtual 501	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1513: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1516: invokestatic 692	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1519: goto -778 -> 741
    //   1522: aload_1
    //   1523: ldc_w 729
    //   1526: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1529: ifeq +13 -> 1542
    //   1532: ldc_w 731
    //   1535: invokestatic 733	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1538: istore_3
    //   1539: goto -667 -> 872
    //   1542: aload_1
    //   1543: ldc_w 735
    //   1546: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1549: ifeq +53 -> 1602
    //   1552: ldc_w 737
    //   1555: invokestatic 733	com/tapjoy/TapjoyConnectCore:d	(Ljava/lang/String;)Z
    //   1558: istore_3
    //   1559: goto -687 -> 872
    //   1562: astore_1
    //   1563: ldc_w 490
    //   1566: new 492	java/lang/StringBuilder
    //   1569: dup
    //   1570: ldc_w 739
    //   1573: invokespecial 497	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1576: aload_1
    //   1577: invokevirtual 690	java/lang/Exception:toString	()Ljava/lang/String;
    //   1580: invokevirtual 501	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1583: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1586: invokestatic 692	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1589: goto -713 -> 876
    //   1592: ldc -92
    //   1594: astore_1
    //   1595: goto -542 -> 1053
    //   1598: astore_1
    //   1599: goto -1438 -> 161
    //   1602: iconst_0
    //   1603: istore_3
    //   1604: goto -732 -> 872
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1607	0	this	TapjoyConnectCore
    //   0	1607	1	paramContext	Context
    //   134	1231	2	i1	int
    //   312	1292	3	bool	boolean
    //   408	649	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   206	225	1087	android/content/pm/PackageManager$NameNotFoundException
    //   68	84	1100	com/tapjoy/TapjoyIntegrationException
    //   84	112	1100	com/tapjoy/TapjoyIntegrationException
    //   112	143	1100	com/tapjoy/TapjoyIntegrationException
    //   143	161	1100	com/tapjoy/TapjoyIntegrationException
    //   161	176	1100	com/tapjoy/TapjoyIntegrationException
    //   176	193	1100	com/tapjoy/TapjoyIntegrationException
    //   197	206	1100	com/tapjoy/TapjoyIntegrationException
    //   206	225	1100	com/tapjoy/TapjoyIntegrationException
    //   225	267	1100	com/tapjoy/TapjoyIntegrationException
    //   267	306	1100	com/tapjoy/TapjoyIntegrationException
    //   306	313	1100	com/tapjoy/TapjoyIntegrationException
    //   317	330	1100	com/tapjoy/TapjoyIntegrationException
    //   334	339	1100	com/tapjoy/TapjoyIntegrationException
    //   343	352	1100	com/tapjoy/TapjoyIntegrationException
    //   356	373	1100	com/tapjoy/TapjoyIntegrationException
    //   373	386	1100	com/tapjoy/TapjoyIntegrationException
    //   390	410	1100	com/tapjoy/TapjoyIntegrationException
    //   415	434	1100	com/tapjoy/TapjoyIntegrationException
    //   434	453	1100	com/tapjoy/TapjoyIntegrationException
    //   453	460	1100	com/tapjoy/TapjoyIntegrationException
    //   464	494	1100	com/tapjoy/TapjoyIntegrationException
    //   494	544	1100	com/tapjoy/TapjoyIntegrationException
    //   546	593	1100	com/tapjoy/TapjoyIntegrationException
    //   597	601	1100	com/tapjoy/TapjoyIntegrationException
    //   601	626	1100	com/tapjoy/TapjoyIntegrationException
    //   626	655	1100	com/tapjoy/TapjoyIntegrationException
    //   660	667	1100	com/tapjoy/TapjoyIntegrationException
    //   671	729	1100	com/tapjoy/TapjoyIntegrationException
    //   729	741	1100	com/tapjoy/TapjoyIntegrationException
    //   741	818	1100	com/tapjoy/TapjoyIntegrationException
    //   818	870	1100	com/tapjoy/TapjoyIntegrationException
    //   872	876	1100	com/tapjoy/TapjoyIntegrationException
    //   876	909	1100	com/tapjoy/TapjoyIntegrationException
    //   909	939	1100	com/tapjoy/TapjoyIntegrationException
    //   939	998	1100	com/tapjoy/TapjoyIntegrationException
    //   998	1049	1100	com/tapjoy/TapjoyIntegrationException
    //   1053	1077	1100	com/tapjoy/TapjoyIntegrationException
    //   1077	1086	1100	com/tapjoy/TapjoyIntegrationException
    //   1088	1100	1100	com/tapjoy/TapjoyIntegrationException
    //   1135	1161	1100	com/tapjoy/TapjoyIntegrationException
    //   1199	1225	1100	com/tapjoy/TapjoyIntegrationException
    //   1228	1237	1100	com/tapjoy/TapjoyIntegrationException
    //   1240	1247	1100	com/tapjoy/TapjoyIntegrationException
    //   1251	1291	1100	com/tapjoy/TapjoyIntegrationException
    //   1294	1327	1100	com/tapjoy/TapjoyIntegrationException
    //   1327	1346	1100	com/tapjoy/TapjoyIntegrationException
    //   1351	1363	1100	com/tapjoy/TapjoyIntegrationException
    //   1368	1413	1100	com/tapjoy/TapjoyIntegrationException
    //   1413	1432	1100	com/tapjoy/TapjoyIntegrationException
    //   1435	1447	1100	com/tapjoy/TapjoyIntegrationException
    //   1450	1459	1100	com/tapjoy/TapjoyIntegrationException
    //   1463	1489	1100	com/tapjoy/TapjoyIntegrationException
    //   1493	1519	1100	com/tapjoy/TapjoyIntegrationException
    //   1522	1539	1100	com/tapjoy/TapjoyIntegrationException
    //   1542	1559	1100	com/tapjoy/TapjoyIntegrationException
    //   1563	1589	1100	com/tapjoy/TapjoyIntegrationException
    //   267	306	1134	java/lang/Exception
    //   68	84	1164	com/tapjoy/TapjoyException
    //   84	112	1164	com/tapjoy/TapjoyException
    //   112	143	1164	com/tapjoy/TapjoyException
    //   143	161	1164	com/tapjoy/TapjoyException
    //   161	176	1164	com/tapjoy/TapjoyException
    //   176	193	1164	com/tapjoy/TapjoyException
    //   197	206	1164	com/tapjoy/TapjoyException
    //   206	225	1164	com/tapjoy/TapjoyException
    //   225	267	1164	com/tapjoy/TapjoyException
    //   267	306	1164	com/tapjoy/TapjoyException
    //   306	313	1164	com/tapjoy/TapjoyException
    //   317	330	1164	com/tapjoy/TapjoyException
    //   334	339	1164	com/tapjoy/TapjoyException
    //   343	352	1164	com/tapjoy/TapjoyException
    //   356	373	1164	com/tapjoy/TapjoyException
    //   373	386	1164	com/tapjoy/TapjoyException
    //   390	410	1164	com/tapjoy/TapjoyException
    //   415	434	1164	com/tapjoy/TapjoyException
    //   434	453	1164	com/tapjoy/TapjoyException
    //   453	460	1164	com/tapjoy/TapjoyException
    //   464	494	1164	com/tapjoy/TapjoyException
    //   494	544	1164	com/tapjoy/TapjoyException
    //   546	593	1164	com/tapjoy/TapjoyException
    //   597	601	1164	com/tapjoy/TapjoyException
    //   601	626	1164	com/tapjoy/TapjoyException
    //   626	655	1164	com/tapjoy/TapjoyException
    //   660	667	1164	com/tapjoy/TapjoyException
    //   671	729	1164	com/tapjoy/TapjoyException
    //   729	741	1164	com/tapjoy/TapjoyException
    //   741	818	1164	com/tapjoy/TapjoyException
    //   818	870	1164	com/tapjoy/TapjoyException
    //   872	876	1164	com/tapjoy/TapjoyException
    //   876	909	1164	com/tapjoy/TapjoyException
    //   909	939	1164	com/tapjoy/TapjoyException
    //   939	998	1164	com/tapjoy/TapjoyException
    //   998	1049	1164	com/tapjoy/TapjoyException
    //   1053	1077	1164	com/tapjoy/TapjoyException
    //   1077	1086	1164	com/tapjoy/TapjoyException
    //   1088	1100	1164	com/tapjoy/TapjoyException
    //   1135	1161	1164	com/tapjoy/TapjoyException
    //   1199	1225	1164	com/tapjoy/TapjoyException
    //   1228	1237	1164	com/tapjoy/TapjoyException
    //   1240	1247	1164	com/tapjoy/TapjoyException
    //   1251	1291	1164	com/tapjoy/TapjoyException
    //   1294	1327	1164	com/tapjoy/TapjoyException
    //   1327	1346	1164	com/tapjoy/TapjoyException
    //   1351	1363	1164	com/tapjoy/TapjoyException
    //   1368	1413	1164	com/tapjoy/TapjoyException
    //   1413	1432	1164	com/tapjoy/TapjoyException
    //   1435	1447	1164	com/tapjoy/TapjoyException
    //   1450	1459	1164	com/tapjoy/TapjoyException
    //   1463	1489	1164	com/tapjoy/TapjoyException
    //   1493	1519	1164	com/tapjoy/TapjoyException
    //   1522	1539	1164	com/tapjoy/TapjoyException
    //   1542	1559	1164	com/tapjoy/TapjoyException
    //   1563	1589	1164	com/tapjoy/TapjoyException
    //   317	330	1198	java/lang/Exception
    //   334	339	1198	java/lang/Exception
    //   343	352	1198	java/lang/Exception
    //   356	373	1198	java/lang/Exception
    //   464	494	1250	java/lang/Exception
    //   494	544	1250	java/lang/Exception
    //   546	593	1250	java/lang/Exception
    //   597	601	1250	java/lang/Exception
    //   601	626	1250	java/lang/Exception
    //   1240	1247	1250	java/lang/Exception
    //   1294	1327	1250	java/lang/Exception
    //   1327	1346	1250	java/lang/Exception
    //   1351	1363	1250	java/lang/Exception
    //   1368	1413	1250	java/lang/Exception
    //   1413	1432	1250	java/lang/Exception
    //   1435	1447	1250	java/lang/Exception
    //   671	729	1462	java/lang/Exception
    //   729	741	1492	java/lang/Exception
    //   818	870	1562	java/lang/Exception
    //   872	876	1562	java/lang/Exception
    //   1522	1539	1562	java/lang/Exception
    //   1542	1559	1562	java/lang/Exception
    //   143	161	1598	java/lang/Exception
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
    //   4: invokestatic 877	com/tapjoy/internal/br:b	(Ljava/lang/String;)Lcom/tapjoy/internal/br;
    //   7: astore 6
    //   9: aload 6
    //   11: astore 7
    //   13: aload 6
    //   15: invokevirtual 880	com/tapjoy/internal/br:d	()Ljava/util/Map;
    //   18: astore 10
    //   20: aload 6
    //   22: astore 7
    //   24: aload 10
    //   26: ldc_w 882
    //   29: invokeinterface 885 2 0
    //   34: checkcast 325	java/lang/String
    //   37: invokestatic 889	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   40: astore 8
    //   42: aload 6
    //   44: astore 7
    //   46: aload 10
    //   48: ldc_w 891
    //   51: invokeinterface 885 2 0
    //   56: checkcast 325	java/lang/String
    //   59: invokestatic 889	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   62: astore 13
    //   64: aload 6
    //   66: astore 7
    //   68: aload 10
    //   70: ldc_w 893
    //   73: invokeinterface 885 2 0
    //   78: checkcast 325	java/lang/String
    //   81: invokestatic 889	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   84: astore 14
    //   86: aload 6
    //   88: astore 7
    //   90: aload 10
    //   92: ldc_w 895
    //   95: invokeinterface 885 2 0
    //   100: checkcast 325	java/lang/String
    //   103: invokestatic 889	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   106: astore 15
    //   108: aload 6
    //   110: astore 7
    //   112: aload 10
    //   114: ldc_w 897
    //   117: invokeinterface 885 2 0
    //   122: checkcast 325	java/lang/String
    //   125: invokestatic 889	com/tapjoy/internal/cr:a	(Ljava/lang/String;)Ljava/lang/String;
    //   128: astore 12
    //   130: aload 6
    //   132: astore 7
    //   134: aload 10
    //   136: ldc_w 899
    //   139: invokeinterface 885 2 0
    //   144: astore 11
    //   146: aload 6
    //   148: astore 7
    //   150: new 901	com/tapjoy/internal/el
    //   153: dup
    //   154: aload 14
    //   156: invokespecial 902	com/tapjoy/internal/el:<init>	(Ljava/lang/String;)V
    //   159: astore 16
    //   161: aload 6
    //   163: astore 7
    //   165: aload 16
    //   167: getfield 905	com/tapjoy/internal/el:a	Lcom/tapjoy/internal/el$a;
    //   170: getstatic 910	com/tapjoy/internal/el$a:RPC_ANALYTICS	Lcom/tapjoy/internal/el$a;
    //   173: if_acmpeq +44 -> 217
    //   176: aload 6
    //   178: astore 7
    //   180: new 868	java/io/IOException
    //   183: dup
    //   184: ldc_w 912
    //   187: invokespecial 913	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   190: athrow
    //   191: astore 7
    //   193: aload 6
    //   195: astore_0
    //   196: aload 7
    //   198: astore 6
    //   200: ldc_w 490
    //   203: aload 6
    //   205: invokevirtual 914	java/io/IOException:getMessage	()Ljava/lang/String;
    //   208: invokestatic 916	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   211: aload_0
    //   212: invokestatic 921	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   215: iconst_0
    //   216: ireturn
    //   217: aload 6
    //   219: astore 7
    //   221: aload 16
    //   223: getfield 923	com/tapjoy/internal/el:b	Ljava/lang/String;
    //   226: astore 9
    //   228: aload 6
    //   230: astore 7
    //   232: aload 9
    //   234: bipush 13
    //   236: ldc_w 925
    //   239: iconst_0
    //   240: bipush 11
    //   242: invokevirtual 929	java/lang/String:regionMatches	(ILjava/lang/String;II)Z
    //   245: ifeq +234 -> 479
    //   248: aload 6
    //   250: astore 7
    //   252: new 931	java/lang/StringBuffer
    //   255: dup
    //   256: invokespecial 932	java/lang/StringBuffer:<init>	()V
    //   259: aload 9
    //   261: iconst_0
    //   262: bipush 8
    //   264: invokevirtual 481	java/lang/String:substring	(II)Ljava/lang/String;
    //   267: invokevirtual 935	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   270: aload 9
    //   272: bipush 24
    //   274: bipush 30
    //   276: invokevirtual 481	java/lang/String:substring	(II)Ljava/lang/String;
    //   279: invokevirtual 935	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   282: aload 9
    //   284: bipush 9
    //   286: bipush 13
    //   288: invokevirtual 481	java/lang/String:substring	(II)Ljava/lang/String;
    //   291: invokevirtual 935	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   294: aload 9
    //   296: bipush 30
    //   298: invokevirtual 484	java/lang/String:substring	(I)Ljava/lang/String;
    //   301: invokevirtual 935	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   304: invokevirtual 936	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   307: astore 9
    //   309: aload 6
    //   311: astore 7
    //   313: aload 16
    //   315: getfield 937	com/tapjoy/internal/el:c	Ljava/lang/String;
    //   318: astore 16
    //   320: aload 8
    //   322: ifnonnull +505 -> 827
    //   325: aload 9
    //   327: astore 8
    //   329: aload 6
    //   331: astore 7
    //   333: invokestatic 942	com/tapjoy/internal/fu:a	()Lcom/tapjoy/internal/fu;
    //   336: getstatic 132	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   339: aload 14
    //   341: ldc_w 414
    //   344: ldc_w 944
    //   347: aload 9
    //   349: aload 16
    //   351: invokevirtual 947	com/tapjoy/internal/fu:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   354: aload 6
    //   356: astore 7
    //   358: aload 8
    //   360: putstatic 234	com/tapjoy/TapjoyConnectCore:U	Ljava/lang/String;
    //   363: aload 6
    //   365: astore 7
    //   367: aload 13
    //   369: putstatic 236	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   372: aload 6
    //   374: astore 7
    //   376: aload 14
    //   378: putstatic 238	com/tapjoy/TapjoyConnectCore:W	Ljava/lang/String;
    //   381: aload 6
    //   383: astore 7
    //   385: aload 15
    //   387: putstatic 240	com/tapjoy/TapjoyConnectCore:X	Ljava/lang/String;
    //   390: aload 6
    //   392: astore 7
    //   394: new 597	java/util/ArrayList
    //   397: dup
    //   398: invokespecial 948	java/util/ArrayList:<init>	()V
    //   401: astore 8
    //   403: aload 12
    //   405: ifnull +112 -> 517
    //   408: aload 6
    //   410: astore 7
    //   412: aload 12
    //   414: ldc_w 780
    //   417: invokevirtual 952	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
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
    //   445: invokevirtual 842	java/lang/String:trim	()Ljava/lang/String;
    //   448: astore 12
    //   450: aload 6
    //   452: astore 7
    //   454: aload 12
    //   456: invokevirtual 477	java/lang/String:length	()I
    //   459: ifle +371 -> 830
    //   462: aload 6
    //   464: astore 7
    //   466: aload 8
    //   468: aload 12
    //   470: invokeinterface 953 2 0
    //   475: pop
    //   476: goto +354 -> 830
    //   479: aload 6
    //   481: astore 7
    //   483: new 955	java/lang/IllegalArgumentException
    //   486: dup
    //   487: ldc_w 957
    //   490: invokespecial 958	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   493: athrow
    //   494: astore_0
    //   495: aload 6
    //   497: astore 7
    //   499: ldc_w 490
    //   502: aload_0
    //   503: invokevirtual 959	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   506: invokestatic 916	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   509: aload 6
    //   511: invokestatic 921	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   514: goto -299 -> 215
    //   517: aload 6
    //   519: astore 7
    //   521: aload 8
    //   523: invokeinterface 962 1 0
    //   528: ifne +12 -> 540
    //   531: aload 6
    //   533: astore 7
    //   535: aload 8
    //   537: invokestatic 847	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   540: aload 6
    //   542: astore 7
    //   544: aload 6
    //   546: invokevirtual 965	com/tapjoy/internal/br:close	()V
    //   549: iload_1
    //   550: ifne +108 -> 658
    //   553: aload 11
    //   555: instanceof 325
    //   558: istore_1
    //   559: iload_1
    //   560: ifeq +112 -> 672
    //   563: aload 11
    //   565: checkcast 325	java/lang/String
    //   568: invokevirtual 842	java/lang/String:trim	()Ljava/lang/String;
    //   571: invokestatic 971	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   574: lstore 4
    //   576: lload 4
    //   578: lconst_0
    //   579: lcmp
    //   580: ifgt +123 -> 703
    //   583: invokestatic 977	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   586: invokevirtual 980	com/tapjoy/TapjoyAppSettings:removeConnectResult	()V
    //   589: invokestatic 293	com/tapjoy/internal/eu:a	()Lcom/tapjoy/internal/eu;
    //   592: astore_0
    //   593: aload 10
    //   595: ldc_w 982
    //   598: invokeinterface 885 2 0
    //   603: astore 6
    //   605: aload 6
    //   607: instanceof 884
    //   610: istore_1
    //   611: iload_1
    //   612: ifeq +114 -> 726
    //   615: aload_0
    //   616: getfield 985	com/tapjoy/internal/eu:a	Lcom/tapjoy/internal/es;
    //   619: aload 6
    //   621: checkcast 884	java/util/Map
    //   624: invokevirtual 990	com/tapjoy/internal/es:a	(Ljava/util/Map;)V
    //   627: aload 6
    //   629: invokestatic 995	com/tapjoy/internal/bl:a	(Ljava/lang/Object;)Ljava/lang/String;
    //   632: astore 6
    //   634: aload_0
    //   635: invokevirtual 998	com/tapjoy/internal/eu:c	()Landroid/content/SharedPreferences;
    //   638: invokeinterface 576 1 0
    //   643: ldc_w 982
    //   646: aload 6
    //   648: invokeinterface 582 3 0
    //   653: invokeinterface 1001 1 0
    //   658: aconst_null
    //   659: invokestatic 921	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   662: iconst_1
    //   663: ireturn
    //   664: astore 6
    //   666: lconst_0
    //   667: lstore 4
    //   669: goto -93 -> 576
    //   672: aload 11
    //   674: instanceof 1003
    //   677: istore_1
    //   678: iload_1
    //   679: ifeq +18 -> 697
    //   682: aload 11
    //   684: checkcast 1003	java/lang/Number
    //   687: invokevirtual 1006	java/lang/Number:longValue	()J
    //   690: lstore 4
    //   692: goto -116 -> 576
    //   695: astore 6
    //   697: lconst_0
    //   698: lstore 4
    //   700: goto -124 -> 576
    //   703: invokestatic 977	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   706: aload_0
    //   707: invokestatic 1008	com/tapjoy/TapjoyConnectCore:s	()Ljava/lang/String;
    //   710: lload 4
    //   712: ldc2_w 1009
    //   715: lmul
    //   716: invokestatic 1014	com/tapjoy/internal/y:b	()J
    //   719: ladd
    //   720: invokevirtual 1018	com/tapjoy/TapjoyAppSettings:saveConnectResultAndParams	(Ljava/lang/String;Ljava/lang/String;J)V
    //   723: goto -134 -> 589
    //   726: aload 6
    //   728: ifnonnull -70 -> 658
    //   731: aload_0
    //   732: getfield 985	com/tapjoy/internal/eu:a	Lcom/tapjoy/internal/es;
    //   735: aconst_null
    //   736: invokevirtual 990	com/tapjoy/internal/es:a	(Ljava/util/Map;)V
    //   739: aload_0
    //   740: invokevirtual 998	com/tapjoy/internal/eu:c	()Landroid/content/SharedPreferences;
    //   743: invokeinterface 576 1 0
    //   748: ldc_w 982
    //   751: invokeinterface 1022 2 0
    //   756: invokeinterface 1001 1 0
    //   761: goto -103 -> 658
    //   764: astore_0
    //   765: aconst_null
    //   766: astore 6
    //   768: goto -273 -> 495
    //   771: astore_0
    //   772: aconst_null
    //   773: astore 6
    //   775: aload 6
    //   777: invokestatic 921	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
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
    //   1: invokestatic 877	com/tapjoy/internal/br:b	(Ljava/lang/String;)Lcom/tapjoy/internal/br;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 1039	com/tapjoy/internal/br:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 1041	com/tapjoy/internal/br:s	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 490
    //   25: ldc_w 1043
    //   28: invokestatic 507	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 965	com/tapjoy/internal/br:close	()V
    //   37: aconst_null
    //   38: invokestatic 921	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 965	com/tapjoy/internal/br:close	()V
    //   49: aconst_null
    //   50: invokestatic 921	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   53: ldc_w 490
    //   56: new 509	com/tapjoy/TapjoyErrorMessage
    //   59: dup
    //   60: getstatic 515	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   63: ldc_w 1045
    //   66: invokespecial 520	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   69: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   72: iconst_0
    //   73: ireturn
    //   74: astore_2
    //   75: aconst_null
    //   76: astore_1
    //   77: aload_1
    //   78: astore_0
    //   79: ldc_w 490
    //   82: aload_2
    //   83: invokevirtual 914	java/io/IOException:getMessage	()Ljava/lang/String;
    //   86: invokestatic 916	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload_1
    //   90: invokestatic 921	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   93: goto -40 -> 53
    //   96: astore_2
    //   97: aconst_null
    //   98: astore_1
    //   99: aload_1
    //   100: astore_0
    //   101: ldc_w 490
    //   104: aload_2
    //   105: invokevirtual 959	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   108: invokestatic 916	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   111: aload_1
    //   112: invokestatic 921	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
    //   115: goto -62 -> 53
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_0
    //   121: aload_0
    //   122: invokestatic 921	com/tapjoy/internal/da:a	(Ljava/io/Closeable;)V
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
      fu.a().a(g, h, "11.9.0", "https://rpc.tapjoy.com/", O, N);
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
    TapjoyUtil.safePut(localHashMap3, "library_revision", "524f4e9", true);
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
    gl.n localN = fu.a(g).a(true);
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
    fu.a(paramContext).j = paramString;
    if (paramHashtable != null)
    {
      ah.putAll(paramHashtable);
      eu.b().a(paramHashtable);
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
    TapjoyLog.d("TapjoyConnect", "viewDidClose: " + paramString);
    aj.remove(paramString);
    eo.e.notifyObservers();
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
    //   0: ldc_w 1873
    //   3: invokestatic 1587	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 1875
    //   9: invokevirtual 1879	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 1884	java/lang/reflect/Field:isAccessible	()Z
    //   17: ifne +8 -> 25
    //   20: aload_1
    //   21: iconst_1
    //   22: invokevirtual 1887	java/lang/reflect/Field:setAccessible	(Z)V
    //   25: aload_1
    //   26: ldc_w 401
    //   29: invokevirtual 1888	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: invokevirtual 1245	java/lang/Object:toString	()Ljava/lang/String;
    //   35: astore_1
    //   36: ldc_w 490
    //   39: new 492	java/lang/StringBuilder
    //   42: dup
    //   43: ldc_w 1890
    //   46: invokespecial 497	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   49: aload_1
    //   50: invokevirtual 501	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 504	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 507	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload_1
    //   60: areturn
    //   61: astore_2
    //   62: aconst_null
    //   63: astore_1
    //   64: ldc_w 490
    //   67: aload_2
    //   68: invokevirtual 690	java/lang/Exception:toString	()Ljava/lang/String;
    //   71: invokestatic 692	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
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
      TapjoyConnectCore.a(TapjoyConnectCore.this, TapjoyConnectCore.b(TapjoyConnectCore.this) + 10000L);
      TapjoyLog.d("TapjoyConnect", "elapsed_time: " + TapjoyConnectCore.b(TapjoyConnectCore.this) + " (" + TapjoyConnectCore.b(TapjoyConnectCore.this) / 1000L / 60L + "m " + TapjoyConnectCore.b(TapjoyConnectCore.this) / 1000L % 60L + "s)");
      SharedPreferences.Editor localEditor = TapjoyConnectCore.b().getSharedPreferences("tjcPrefrences", 0).edit();
      localEditor.putLong("tapjoy_elapsed_time", TapjoyConnectCore.b(TapjoyConnectCore.this));
      localEditor.commit();
      if (TapjoyConnectCore.b(TapjoyConnectCore.this) >= 900000L)
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
