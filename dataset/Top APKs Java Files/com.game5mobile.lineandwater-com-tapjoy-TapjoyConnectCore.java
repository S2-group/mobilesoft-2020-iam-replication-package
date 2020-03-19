package com.tapjoy;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import com.tapjoy.internal.ao;
import com.tapjoy.internal.ey;
import com.tapjoy.internal.fe;
import com.tapjoy.internal.ff;
import com.tapjoy.internal.fl;
import com.tapjoy.internal.fs;
import com.tapjoy.internal.fs.a;
import com.tapjoy.internal.fw;
import com.tapjoy.internal.fw.a;
import com.tapjoy.internal.gc;
import com.tapjoy.internal.ge;
import com.tapjoy.internal.gi;
import com.tapjoy.internal.hd;
import com.tapjoy.internal.ju;
import com.tapjoy.internal.w;
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
  private static String aP = "";
  private static String aQ = "";
  private static String aR = "";
  private static boolean aS = false;
  private static TJConnectListener aT = null;
  private static boolean aU = false;
  private static boolean ab;
  private static PackageManager ac;
  private static TapjoyGpsHelper ad;
  private static Hashtable ae;
  private static String af;
  private static Map ag;
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
  private long X = 0L;
  private boolean Y = false;
  private boolean aa = false;
  
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
    ae = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
    af = "";
    ag = new ConcurrentHashMap();
  }
  
  public TapjoyConnectCore() {}
  
  private static String a(long paramLong)
  {
    try
    {
      String str = TapjoyUtil.SHA256(v + ":" + p() + ":" + paramLong + ":" + L);
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
      paramString = TapjoyUtil.SHA256(v + ":" + p() + ":" + paramLong + ":" + L + ":" + paramString);
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
  private static boolean a(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: putstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   4: aload_0
    //   5: invokevirtual 545	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   8: putstatic 456	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   11: invokestatic 550	com/tapjoy/internal/gi:a	()Lcom/tapjoy/internal/gi;
    //   14: aload_0
    //   15: invokevirtual 553	com/tapjoy/internal/gi:a	(Landroid/content/Context;)V
    //   18: invokestatic 558	com/tapjoy/internal/ge:a	()Lcom/tapjoy/internal/ge;
    //   21: aload_0
    //   22: invokevirtual 559	com/tapjoy/internal/ge:a	(Landroid/content/Context;)V
    //   25: new 339	com/tapjoy/TapjoyGpsHelper
    //   28: dup
    //   29: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   32: invokespecial 561	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   35: putstatic 337	com/tapjoy/TapjoyConnectCore:ad	Lcom/tapjoy/TapjoyGpsHelper;
    //   38: getstatic 134	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   41: ifnonnull +13 -> 54
    //   44: new 563	com/tapjoy/TapjoyURLConnection
    //   47: dup
    //   48: invokespecial 564	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   51: putstatic 134	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   54: getstatic 253	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Hashtable;
    //   57: ifnonnull +13 -> 70
    //   60: new 449	java/util/Hashtable
    //   63: dup
    //   64: invokespecial 565	java/util/Hashtable:<init>	()V
    //   67: putstatic 253	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Hashtable;
    //   70: invokestatic 567	com/tapjoy/TapjoyConnectCore:j	()V
    //   73: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   76: invokevirtual 571	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   79: ldc_w 573
    //   82: aconst_null
    //   83: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   86: invokevirtual 576	android/content/Context:getPackageName	()Ljava/lang/String;
    //   89: invokevirtual 582	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   92: istore_1
    //   93: new 502	java/util/Properties
    //   96: dup
    //   97: invokespecial 583	java/util/Properties:<init>	()V
    //   100: astore_0
    //   101: aload_0
    //   102: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   105: invokevirtual 571	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   108: iload_1
    //   109: invokevirtual 587	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   112: invokevirtual 591	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   115: aload_0
    //   116: invokestatic 593	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   119: ldc_w 595
    //   122: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   125: invokestatic 424	com/tapjoy/internal/ju:c	(Ljava/lang/String;)Z
    //   128: ifeq +6 -> 134
    //   131: invokestatic 600	com/tapjoy/TapjoyConnectCore:k	()V
    //   134: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   137: invokevirtual 604	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   140: ldc_w 606
    //   143: invokestatic 612	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   146: astore_0
    //   147: aload_0
    //   148: putstatic 162	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   151: aload_0
    //   152: ifnull +12 -> 164
    //   155: getstatic 162	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   158: invokevirtual 615	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   161: putstatic 162	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   164: getstatic 456	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   167: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   170: invokevirtual 576	android/content/Context:getPackageName	()Ljava/lang/String;
    //   173: iconst_0
    //   174: invokevirtual 619	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   177: getfield 624	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   180: putstatic 180	com/tapjoy/TapjoyConnectCore:w	Ljava/lang/String;
    //   183: ldc_w 626
    //   186: putstatic 174	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   189: ldc_w 626
    //   192: putstatic 194	com/tapjoy/TapjoyConnectCore:D	Ljava/lang/String;
    //   195: getstatic 631	android/os/Build:MODEL	Ljava/lang/String;
    //   198: putstatic 170	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   201: getstatic 634	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   204: putstatic 172	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   207: getstatic 639	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   210: putstatic 176	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   213: ldc_w 641
    //   216: putstatic 182	com/tapjoy/TapjoyConnectCore:x	Ljava/lang/String;
    //   219: ldc_w 643
    //   222: putstatic 184	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   225: getstatic 646	android/os/Build$VERSION:SDK_INT	I
    //   228: iconst_3
    //   229: if_icmple +35 -> 264
    //   232: new 648	com/tapjoy/TapjoyDisplayMetricsUtil
    //   235: dup
    //   236: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   239: invokespecial 649	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   242: astore_0
    //   243: aload_0
    //   244: invokevirtual 652	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   247: putstatic 186	com/tapjoy/TapjoyConnectCore:z	I
    //   250: aload_0
    //   251: invokevirtual 656	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   254: putstatic 188	com/tapjoy/TapjoyConnectCore:A	F
    //   257: aload_0
    //   258: invokevirtual 659	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   261: putstatic 190	com/tapjoy/TapjoyConnectCore:B	I
    //   264: ldc_w 661
    //   267: invokestatic 663	com/tapjoy/TapjoyConnectCore:f	(Ljava/lang/String;)Z
    //   270: ifeq +580 -> 850
    //   273: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   276: ldc_w 665
    //   279: invokevirtual 669	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   282: checkcast 671	android/net/wifi/WifiManager
    //   285: astore_0
    //   286: aload_0
    //   287: ifnull +42 -> 329
    //   290: aload_0
    //   291: invokevirtual 675	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   294: astore_0
    //   295: aload_0
    //   296: ifnull +33 -> 329
    //   299: aload_0
    //   300: invokevirtual 680	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   303: astore_0
    //   304: aload_0
    //   305: putstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   308: aload_0
    //   309: ifnull +20 -> 329
    //   312: getstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   315: ldc_w 293
    //   318: ldc -96
    //   320: invokevirtual 684	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   323: invokevirtual 615	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   326: putstatic 166	com/tapjoy/TapjoyConnectCore:p	Ljava/lang/String;
    //   329: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   332: ldc_w 686
    //   335: invokevirtual 669	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   338: checkcast 688	android/telephony/TelephonyManager
    //   341: astore_0
    //   342: aload_0
    //   343: ifnull +60 -> 403
    //   346: aload_0
    //   347: invokevirtual 691	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   350: putstatic 196	com/tapjoy/TapjoyConnectCore:E	Ljava/lang/String;
    //   353: aload_0
    //   354: invokevirtual 694	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   357: putstatic 198	com/tapjoy/TapjoyConnectCore:F	Ljava/lang/String;
    //   360: aload_0
    //   361: invokevirtual 697	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   364: astore_0
    //   365: aload_0
    //   366: ifnull +37 -> 403
    //   369: aload_0
    //   370: invokevirtual 495	java/lang/String:length	()I
    //   373: iconst_5
    //   374: if_icmpeq +12 -> 386
    //   377: aload_0
    //   378: invokevirtual 495	java/lang/String:length	()I
    //   381: bipush 6
    //   383: if_icmpne +20 -> 403
    //   386: aload_0
    //   387: iconst_0
    //   388: iconst_3
    //   389: invokevirtual 701	java/lang/String:substring	(II)Ljava/lang/String;
    //   392: putstatic 200	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   395: aload_0
    //   396: iconst_3
    //   397: invokevirtual 704	java/lang/String:substring	(I)Ljava/lang/String;
    //   400: putstatic 202	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   403: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   406: ldc_w 706
    //   409: iconst_0
    //   410: invokevirtual 710	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   413: astore_0
    //   414: aload_0
    //   415: ldc_w 712
    //   418: ldc -96
    //   420: invokeinterface 717 3 0
    //   425: astore_3
    //   426: aload_3
    //   427: putstatic 168	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   430: aload_3
    //   431: ifnull +12 -> 443
    //   434: getstatic 168	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   437: invokevirtual 495	java/lang/String:length	()I
    //   440: ifne +60 -> 500
    //   443: new 286	java/lang/StringBuilder
    //   446: dup
    //   447: invokespecial 287	java/lang/StringBuilder:<init>	()V
    //   450: invokestatic 723	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   453: invokevirtual 724	java/util/UUID:toString	()Ljava/lang/String;
    //   456: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: invokestatic 730	java/lang/System:currentTimeMillis	()J
    //   462: invokevirtual 299	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   465: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   468: invokestatic 308	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   471: putstatic 168	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   474: aload_0
    //   475: invokeinterface 734 1 0
    //   480: astore_0
    //   481: aload_0
    //   482: ldc_w 712
    //   485: getstatic 168	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   488: invokeinterface 740 3 0
    //   493: pop
    //   494: aload_0
    //   495: invokeinterface 743 1 0
    //   500: ldc_w 745
    //   503: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   506: ifnull +71 -> 577
    //   509: ldc_w 745
    //   512: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   515: invokevirtual 495	java/lang/String:length	()I
    //   518: ifle +59 -> 577
    //   521: ldc_w 745
    //   524: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   527: putstatic 208	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   530: new 747	java/util/ArrayList
    //   533: dup
    //   534: getstatic 750	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   537: invokestatic 152	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   540: invokespecial 751	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   543: getstatic 208	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   546: invokevirtual 752	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   549: ifne +28 -> 577
    //   552: ldc_w 310
    //   555: new 286	java/lang/StringBuilder
    //   558: dup
    //   559: ldc_w 754
    //   562: invokespecial 323	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   565: getstatic 208	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   568: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   574: invokestatic 756	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   577: getstatic 208	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   580: astore_0
    //   581: new 758	android/content/Intent
    //   584: dup
    //   585: ldc_w 760
    //   588: invokespecial 761	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   591: astore_3
    //   592: aload_0
    //   593: invokevirtual 495	java/lang/String:length	()I
    //   596: ifgt +296 -> 892
    //   599: aload_3
    //   600: ldc_w 763
    //   603: invokestatic 769	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   606: invokevirtual 773	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   609: pop
    //   610: getstatic 456	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   613: aload_3
    //   614: iconst_0
    //   615: invokevirtual 777	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   618: invokeinterface 780 1 0
    //   623: ifle +343 -> 966
    //   626: iconst_1
    //   627: istore_2
    //   628: iload_2
    //   629: putstatic 224	com/tapjoy/TapjoyConnectCore:R	Z
    //   632: invokestatic 782	com/tapjoy/TapjoyConnectCore:h	()V
    //   635: ldc_w 784
    //   638: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   641: ifnull +24 -> 665
    //   644: ldc_w 784
    //   647: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   650: invokevirtual 495	java/lang/String:length	()I
    //   653: ifle +12 -> 665
    //   656: ldc_w 784
    //   659: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   662: putstatic 246	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   665: ldc_w 786
    //   668: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   671: ifnull +24 -> 695
    //   674: ldc_w 786
    //   677: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   680: invokevirtual 495	java/lang/String:length	()I
    //   683: ifle +12 -> 695
    //   686: ldc_w 786
    //   689: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   692: putstatic 244	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   695: ldc_w 788
    //   698: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   701: ifnull +53 -> 754
    //   704: ldc_w 788
    //   707: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   710: invokevirtual 495	java/lang/String:length	()I
    //   713: ifle +41 -> 754
    //   716: ldc_w 310
    //   719: new 286	java/lang/StringBuilder
    //   722: dup
    //   723: ldc_w 790
    //   726: invokespecial 323	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   729: ldc_w 788
    //   732: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   735: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   738: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   741: invokestatic 438	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   744: ldc_w 788
    //   747: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   750: aconst_null
    //   751: invokestatic 794	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;Lcom/tapjoy/TJSetUserIDListener;)V
    //   754: ldc_w 440
    //   757: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   760: invokestatic 797	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   763: putstatic 220	com/tapjoy/TapjoyConnectCore:P	Ljava/lang/String;
    //   766: getstatic 253	com/tapjoy/TapjoyConnectCore:ae	Ljava/util/Hashtable;
    //   769: ifnull +6 -> 775
    //   772: invokestatic 799	com/tapjoy/TapjoyConnectCore:i	()V
    //   775: iconst_1
    //   776: ireturn
    //   777: astore_0
    //   778: new 801	com/tapjoy/TapjoyException
    //   781: dup
    //   782: aload_0
    //   783: invokevirtual 804	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   786: invokespecial 805	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   789: athrow
    //   790: astore_0
    //   791: ldc_w 310
    //   794: new 286	java/lang/StringBuilder
    //   797: dup
    //   798: ldc_w 807
    //   801: invokespecial 323	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   804: aload_0
    //   805: invokevirtual 324	java/lang/Exception:toString	()Ljava/lang/String;
    //   808: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   811: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   814: invokestatic 524	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   817: goto -553 -> 264
    //   820: astore_0
    //   821: ldc_w 310
    //   824: new 286	java/lang/StringBuilder
    //   827: dup
    //   828: ldc_w 809
    //   831: invokespecial 323	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   834: aload_0
    //   835: invokevirtual 324	java/lang/Exception:toString	()Ljava/lang/String;
    //   838: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   841: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   844: invokestatic 524	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   847: goto -518 -> 329
    //   850: ldc_w 310
    //   853: ldc_w 811
    //   856: invokestatic 492	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   859: goto -530 -> 329
    //   862: astore_0
    //   863: ldc_w 310
    //   866: new 286	java/lang/StringBuilder
    //   869: dup
    //   870: ldc_w 813
    //   873: invokespecial 323	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   876: aload_0
    //   877: invokevirtual 324	java/lang/Exception:toString	()Ljava/lang/String;
    //   880: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   883: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   886: invokestatic 524	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   889: goto -389 -> 500
    //   892: aload_0
    //   893: ldc_w 815
    //   896: invokevirtual 430	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   899: ifeq +13 -> 912
    //   902: ldc_w 817
    //   905: invokestatic 819	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   908: istore_2
    //   909: goto -281 -> 628
    //   912: aload_0
    //   913: ldc_w 821
    //   916: invokevirtual 430	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   919: ifeq +47 -> 966
    //   922: ldc_w 823
    //   925: invokestatic 819	com/tapjoy/TapjoyConnectCore:e	(Ljava/lang/String;)Z
    //   928: istore_2
    //   929: goto -301 -> 628
    //   932: astore_0
    //   933: ldc_w 310
    //   936: new 286	java/lang/StringBuilder
    //   939: dup
    //   940: ldc_w 825
    //   943: invokespecial 323	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   946: aload_0
    //   947: invokevirtual 324	java/lang/Exception:toString	()Ljava/lang/String;
    //   950: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   953: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   956: invokestatic 524	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   959: goto -327 -> 632
    //   962: astore_0
    //   963: goto -844 -> 119
    //   966: iconst_0
    //   967: istore_2
    //   968: goto -340 -> 628
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	971	0	paramContext	Context
    //   92	17	1	i1	int
    //   627	341	2	bool	boolean
    //   425	189	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   164	183	777	android/content/pm/PackageManager$NameNotFoundException
    //   225	264	790	java/lang/Exception
    //   273	286	820	java/lang/Exception
    //   290	295	820	java/lang/Exception
    //   299	308	820	java/lang/Exception
    //   312	329	820	java/lang/Exception
    //   443	500	862	java/lang/Exception
    //   577	626	932	java/lang/Exception
    //   628	632	932	java/lang/Exception
    //   892	909	932	java/lang/Exception
    //   912	929	932	java/lang/Exception
    //   101	119	962	java/lang/Exception
  }
  
  /* Error */
  private static boolean a(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aload_0
    //   4: invokestatic 872	com/tapjoy/internal/bq:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bq;
    //   7: astore 6
    //   9: aload 6
    //   11: astore 7
    //   13: aload 6
    //   15: invokevirtual 875	com/tapjoy/internal/bq:d	()Ljava/util/Map;
    //   18: astore 10
    //   20: aload 6
    //   22: astore 7
    //   24: aload 10
    //   26: ldc_w 877
    //   29: invokeinterface 880 2 0
    //   34: checkcast 426	java/lang/String
    //   37: invokestatic 882	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   40: astore 8
    //   42: aload 6
    //   44: astore 7
    //   46: aload 10
    //   48: ldc_w 884
    //   51: invokeinterface 880 2 0
    //   56: checkcast 426	java/lang/String
    //   59: invokestatic 882	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   62: astore 13
    //   64: aload 6
    //   66: astore 7
    //   68: aload 10
    //   70: ldc_w 886
    //   73: invokeinterface 880 2 0
    //   78: checkcast 426	java/lang/String
    //   81: invokestatic 882	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   84: astore 14
    //   86: aload 6
    //   88: astore 7
    //   90: aload 10
    //   92: ldc_w 888
    //   95: invokeinterface 880 2 0
    //   100: checkcast 426	java/lang/String
    //   103: invokestatic 882	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   106: astore 15
    //   108: aload 6
    //   110: astore 7
    //   112: aload 10
    //   114: ldc_w 890
    //   117: invokeinterface 880 2 0
    //   122: checkcast 426	java/lang/String
    //   125: invokestatic 882	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   128: astore 12
    //   130: aload 6
    //   132: astore 7
    //   134: aload 10
    //   136: ldc_w 892
    //   139: invokeinterface 880 2 0
    //   144: astore 11
    //   146: aload 6
    //   148: astore 7
    //   150: new 894	com/tapjoy/internal/fs
    //   153: dup
    //   154: aload 14
    //   156: invokespecial 895	com/tapjoy/internal/fs:<init>	(Ljava/lang/String;)V
    //   159: astore 16
    //   161: aload 6
    //   163: astore 7
    //   165: aload 16
    //   167: getfield 898	com/tapjoy/internal/fs:a	Lcom/tapjoy/internal/fs$a;
    //   170: getstatic 903	com/tapjoy/internal/fs$a:RPC_ANALYTICS	Lcom/tapjoy/internal/fs$a;
    //   173: if_acmpeq +44 -> 217
    //   176: aload 6
    //   178: astore 7
    //   180: new 863	java/io/IOException
    //   183: dup
    //   184: ldc_w 905
    //   187: invokespecial 906	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   190: athrow
    //   191: astore 7
    //   193: aload 6
    //   195: astore_0
    //   196: aload 7
    //   198: astore 6
    //   200: ldc_w 310
    //   203: aload 6
    //   205: invokevirtual 907	java/io/IOException:getMessage	()Ljava/lang/String;
    //   208: invokestatic 909	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   211: aload_0
    //   212: invokestatic 914	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   215: iconst_0
    //   216: ireturn
    //   217: aload 6
    //   219: astore 7
    //   221: aload 16
    //   223: getfield 916	com/tapjoy/internal/fs:b	Ljava/lang/String;
    //   226: invokestatic 917	com/tapjoy/internal/fs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   229: astore 9
    //   231: aload 6
    //   233: astore 7
    //   235: aload 16
    //   237: getfield 918	com/tapjoy/internal/fs:c	Ljava/lang/String;
    //   240: astore 16
    //   242: aload 8
    //   244: ifnonnull +490 -> 734
    //   247: aload 9
    //   249: astore 8
    //   251: aload 6
    //   253: astore 7
    //   255: invokestatic 370	com/tapjoy/internal/hd:a	()Lcom/tapjoy/internal/hd;
    //   258: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   261: aload 14
    //   263: ldc_w 641
    //   266: ldc_w 920
    //   269: aload 9
    //   271: aload 16
    //   273: invokevirtual 923	com/tapjoy/internal/hd:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   276: aload 6
    //   278: astore 7
    //   280: aload 8
    //   282: putstatic 226	com/tapjoy/TapjoyConnectCore:S	Ljava/lang/String;
    //   285: aload 6
    //   287: astore 7
    //   289: aload 13
    //   291: putstatic 228	com/tapjoy/TapjoyConnectCore:T	Ljava/lang/String;
    //   294: aload 6
    //   296: astore 7
    //   298: aload 14
    //   300: putstatic 230	com/tapjoy/TapjoyConnectCore:U	Ljava/lang/String;
    //   303: aload 6
    //   305: astore 7
    //   307: aload 15
    //   309: putstatic 232	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   312: aload 6
    //   314: astore 7
    //   316: new 747	java/util/ArrayList
    //   319: dup
    //   320: invokespecial 924	java/util/ArrayList:<init>	()V
    //   323: astore 8
    //   325: aload 12
    //   327: ifnull +74 -> 401
    //   330: aload 6
    //   332: astore 7
    //   334: aload 12
    //   336: ldc_w 497
    //   339: invokevirtual 928	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   342: astore 9
    //   344: aload 6
    //   346: astore 7
    //   348: aload 9
    //   350: arraylength
    //   351: istore_3
    //   352: iconst_0
    //   353: istore_2
    //   354: iload_2
    //   355: iload_3
    //   356: if_icmpge +45 -> 401
    //   359: aload 6
    //   361: astore 7
    //   363: aload 9
    //   365: iload_2
    //   366: aaload
    //   367: invokevirtual 851	java/lang/String:trim	()Ljava/lang/String;
    //   370: astore 12
    //   372: aload 6
    //   374: astore 7
    //   376: aload 12
    //   378: invokevirtual 495	java/lang/String:length	()I
    //   381: ifle +356 -> 737
    //   384: aload 6
    //   386: astore 7
    //   388: aload 8
    //   390: aload 12
    //   392: invokeinterface 929 2 0
    //   397: pop
    //   398: goto +339 -> 737
    //   401: aload 6
    //   403: astore 7
    //   405: aload 8
    //   407: invokeinterface 932 1 0
    //   412: ifne +12 -> 424
    //   415: aload 6
    //   417: astore 7
    //   419: aload 8
    //   421: invokestatic 856	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   424: aload 6
    //   426: astore 7
    //   428: aload 6
    //   430: invokevirtual 935	com/tapjoy/internal/bq:close	()V
    //   433: iload_1
    //   434: ifne +108 -> 542
    //   437: aload 11
    //   439: instanceof 426
    //   442: istore_1
    //   443: iload_1
    //   444: ifeq +112 -> 556
    //   447: aload 11
    //   449: checkcast 426	java/lang/String
    //   452: invokevirtual 851	java/lang/String:trim	()Ljava/lang/String;
    //   455: invokestatic 941	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   458: lstore 4
    //   460: lload 4
    //   462: lconst_0
    //   463: lcmp
    //   464: ifgt +123 -> 587
    //   467: invokestatic 947	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   470: invokevirtual 950	com/tapjoy/TapjoyAppSettings:removeConnectResult	()V
    //   473: invokestatic 558	com/tapjoy/internal/ge:a	()Lcom/tapjoy/internal/ge;
    //   476: astore_0
    //   477: aload 10
    //   479: ldc_w 952
    //   482: invokeinterface 880 2 0
    //   487: astore 6
    //   489: aload 6
    //   491: instanceof 879
    //   494: istore_1
    //   495: iload_1
    //   496: ifeq +114 -> 610
    //   499: aload_0
    //   500: getfield 955	com/tapjoy/internal/ge:a	Lcom/tapjoy/internal/gc;
    //   503: aload 6
    //   505: checkcast 879	java/util/Map
    //   508: invokevirtual 960	com/tapjoy/internal/gc:a	(Ljava/util/Map;)V
    //   511: aload 6
    //   513: invokestatic 965	com/tapjoy/internal/bk:a	(Ljava/lang/Object;)Ljava/lang/String;
    //   516: astore 6
    //   518: aload_0
    //   519: invokevirtual 968	com/tapjoy/internal/ge:c	()Landroid/content/SharedPreferences;
    //   522: invokeinterface 734 1 0
    //   527: ldc_w 952
    //   530: aload 6
    //   532: invokeinterface 740 3 0
    //   537: invokeinterface 743 1 0
    //   542: aconst_null
    //   543: invokestatic 914	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   546: iconst_1
    //   547: ireturn
    //   548: astore 6
    //   550: lconst_0
    //   551: lstore 4
    //   553: goto -93 -> 460
    //   556: aload 11
    //   558: instanceof 970
    //   561: istore_1
    //   562: iload_1
    //   563: ifeq +18 -> 581
    //   566: aload 11
    //   568: checkcast 970	java/lang/Number
    //   571: invokevirtual 973	java/lang/Number:longValue	()J
    //   574: lstore 4
    //   576: goto -116 -> 460
    //   579: astore 6
    //   581: lconst_0
    //   582: lstore 4
    //   584: goto -124 -> 460
    //   587: invokestatic 947	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   590: aload_0
    //   591: invokestatic 975	com/tapjoy/TapjoyConnectCore:q	()Ljava/lang/String;
    //   594: lload 4
    //   596: ldc2_w 976
    //   599: lmul
    //   600: invokestatic 981	com/tapjoy/internal/w:b	()J
    //   603: ladd
    //   604: invokevirtual 985	com/tapjoy/TapjoyAppSettings:saveConnectResultAndParams	(Ljava/lang/String;Ljava/lang/String;J)V
    //   607: goto -134 -> 473
    //   610: aload 6
    //   612: ifnonnull -70 -> 542
    //   615: aload_0
    //   616: getfield 955	com/tapjoy/internal/ge:a	Lcom/tapjoy/internal/gc;
    //   619: aconst_null
    //   620: invokevirtual 960	com/tapjoy/internal/gc:a	(Ljava/util/Map;)V
    //   623: aload_0
    //   624: invokevirtual 968	com/tapjoy/internal/ge:c	()Landroid/content/SharedPreferences;
    //   627: invokeinterface 734 1 0
    //   632: ldc_w 952
    //   635: invokeinterface 989 2 0
    //   640: invokeinterface 743 1 0
    //   645: goto -103 -> 542
    //   648: astore_0
    //   649: aconst_null
    //   650: astore 6
    //   652: aload 6
    //   654: astore 7
    //   656: ldc_w 310
    //   659: aload_0
    //   660: invokevirtual 990	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   663: invokestatic 909	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   666: aload 6
    //   668: invokestatic 914	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   671: goto -456 -> 215
    //   674: astore_0
    //   675: aconst_null
    //   676: astore 6
    //   678: aload 6
    //   680: invokestatic 914	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   683: aload_0
    //   684: athrow
    //   685: astore_0
    //   686: aload 7
    //   688: astore 6
    //   690: goto -12 -> 678
    //   693: astore_0
    //   694: aconst_null
    //   695: astore 6
    //   697: goto -19 -> 678
    //   700: astore 7
    //   702: aload_0
    //   703: astore 6
    //   705: aload 7
    //   707: astore_0
    //   708: goto -30 -> 678
    //   711: astore_0
    //   712: aconst_null
    //   713: astore 6
    //   715: goto -63 -> 652
    //   718: astore_0
    //   719: goto -67 -> 652
    //   722: astore 6
    //   724: aload 7
    //   726: astore_0
    //   727: goto -527 -> 200
    //   730: astore_0
    //   731: goto -189 -> 542
    //   734: goto -483 -> 251
    //   737: iload_2
    //   738: iconst_1
    //   739: iadd
    //   740: istore_2
    //   741: goto -387 -> 354
    //   744: astore 6
    //   746: aconst_null
    //   747: astore_0
    //   748: goto -548 -> 200
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	751	0	paramString	String
    //   0	751	1	paramBoolean	boolean
    //   353	388	2	i1	int
    //   351	6	3	i2	int
    //   458	137	4	l1	long
    //   7	524	6	localObject1	Object
    //   548	1	6	localNumberFormatException1	NumberFormatException
    //   579	32	6	localNumberFormatException2	NumberFormatException
    //   650	64	6	localObject2	Object
    //   722	1	6	localIOException1	java.io.IOException
    //   744	1	6	localIOException2	java.io.IOException
    //   1	178	7	localObject3	Object
    //   191	6	7	localIOException3	java.io.IOException
    //   219	468	7	localObject4	Object
    //   700	25	7	localObject5	Object
    //   40	380	8	localObject6	Object
    //   229	135	9	localObject7	Object
    //   18	460	10	localMap	Map
    //   144	423	11	localObject8	Object
    //   128	263	12	str1	String
    //   62	228	13	str2	String
    //   84	215	14	str3	String
    //   106	202	15	str4	String
    //   159	113	16	localObject9	Object
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
    //   221	231	191	java/io/IOException
    //   235	242	191	java/io/IOException
    //   255	276	191	java/io/IOException
    //   280	285	191	java/io/IOException
    //   289	294	191	java/io/IOException
    //   298	303	191	java/io/IOException
    //   307	312	191	java/io/IOException
    //   316	325	191	java/io/IOException
    //   334	344	191	java/io/IOException
    //   348	352	191	java/io/IOException
    //   363	372	191	java/io/IOException
    //   376	384	191	java/io/IOException
    //   388	398	191	java/io/IOException
    //   405	415	191	java/io/IOException
    //   419	424	191	java/io/IOException
    //   428	433	191	java/io/IOException
    //   447	460	548	java/lang/NumberFormatException
    //   566	576	579	java/lang/NumberFormatException
    //   437	443	648	java/lang/RuntimeException
    //   447	460	648	java/lang/RuntimeException
    //   467	473	648	java/lang/RuntimeException
    //   473	495	648	java/lang/RuntimeException
    //   499	542	648	java/lang/RuntimeException
    //   556	562	648	java/lang/RuntimeException
    //   566	576	648	java/lang/RuntimeException
    //   587	607	648	java/lang/RuntimeException
    //   615	645	648	java/lang/RuntimeException
    //   3	9	674	finally
    //   13	20	685	finally
    //   24	42	685	finally
    //   46	64	685	finally
    //   68	86	685	finally
    //   90	108	685	finally
    //   112	130	685	finally
    //   134	146	685	finally
    //   150	161	685	finally
    //   165	176	685	finally
    //   180	191	685	finally
    //   221	231	685	finally
    //   235	242	685	finally
    //   255	276	685	finally
    //   280	285	685	finally
    //   289	294	685	finally
    //   298	303	685	finally
    //   307	312	685	finally
    //   316	325	685	finally
    //   334	344	685	finally
    //   348	352	685	finally
    //   363	372	685	finally
    //   376	384	685	finally
    //   388	398	685	finally
    //   405	415	685	finally
    //   419	424	685	finally
    //   428	433	685	finally
    //   656	666	685	finally
    //   437	443	693	finally
    //   447	460	693	finally
    //   467	473	693	finally
    //   473	495	693	finally
    //   499	542	693	finally
    //   556	562	693	finally
    //   566	576	693	finally
    //   587	607	693	finally
    //   615	645	693	finally
    //   200	211	700	finally
    //   3	9	711	java/lang/RuntimeException
    //   13	20	718	java/lang/RuntimeException
    //   24	42	718	java/lang/RuntimeException
    //   46	64	718	java/lang/RuntimeException
    //   68	86	718	java/lang/RuntimeException
    //   90	108	718	java/lang/RuntimeException
    //   112	130	718	java/lang/RuntimeException
    //   134	146	718	java/lang/RuntimeException
    //   150	161	718	java/lang/RuntimeException
    //   165	176	718	java/lang/RuntimeException
    //   180	191	718	java/lang/RuntimeException
    //   221	231	718	java/lang/RuntimeException
    //   235	242	718	java/lang/RuntimeException
    //   255	276	718	java/lang/RuntimeException
    //   280	285	718	java/lang/RuntimeException
    //   289	294	718	java/lang/RuntimeException
    //   298	303	718	java/lang/RuntimeException
    //   307	312	718	java/lang/RuntimeException
    //   316	325	718	java/lang/RuntimeException
    //   334	344	718	java/lang/RuntimeException
    //   348	352	718	java/lang/RuntimeException
    //   363	372	718	java/lang/RuntimeException
    //   376	384	718	java/lang/RuntimeException
    //   388	398	718	java/lang/RuntimeException
    //   405	415	718	java/lang/RuntimeException
    //   419	424	718	java/lang/RuntimeException
    //   428	433	718	java/lang/RuntimeException
    //   3	9	722	java/io/IOException
    //   499	542	730	java/lang/Exception
    //   437	443	744	java/io/IOException
    //   447	460	744	java/io/IOException
    //   467	473	744	java/io/IOException
    //   473	495	744	java/io/IOException
    //   499	542	744	java/io/IOException
    //   556	562	744	java/io/IOException
    //   566	576	744	java/io/IOException
    //   587	607	744	java/io/IOException
    //   615	645	744	java/io/IOException
  }
  
  private static String b(long paramLong)
  {
    try
    {
      String str = TapjoyUtil.SHA256(aP + ":" + p() + ":" + paramLong + ":" + aQ);
      return str;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error in computing verifier value -- " + localException.toString()));
    }
    return "";
  }
  
  /* Error */
  private static boolean c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 872	com/tapjoy/internal/bq:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bq;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 875	com/tapjoy/internal/bq:d	()Ljava/util/Map;
    //   11: astore 5
    //   13: aload_1
    //   14: astore_0
    //   15: aload 5
    //   17: ldc_w 877
    //   20: invokeinterface 880 2 0
    //   25: checkcast 426	java/lang/String
    //   28: invokestatic 882	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   31: astore_2
    //   32: aload_1
    //   33: astore_0
    //   34: aload 5
    //   36: ldc_w 884
    //   39: invokeinterface 880 2 0
    //   44: checkcast 426	java/lang/String
    //   47: invokestatic 882	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   50: astore 4
    //   52: aload_1
    //   53: astore_0
    //   54: aload 5
    //   56: ldc_w 886
    //   59: invokeinterface 880 2 0
    //   64: checkcast 426	java/lang/String
    //   67: invokestatic 882	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   70: astore_3
    //   71: aload_1
    //   72: astore_0
    //   73: aload 5
    //   75: ldc_w 888
    //   78: invokeinterface 880 2 0
    //   83: checkcast 426	java/lang/String
    //   86: invokestatic 882	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   89: astore 5
    //   91: aload_1
    //   92: astore_0
    //   93: new 894	com/tapjoy/internal/fs
    //   96: dup
    //   97: aload_3
    //   98: invokespecial 895	com/tapjoy/internal/fs:<init>	(Ljava/lang/String;)V
    //   101: astore_3
    //   102: aload_1
    //   103: astore_0
    //   104: aload_3
    //   105: getfield 898	com/tapjoy/internal/fs:a	Lcom/tapjoy/internal/fs$a;
    //   108: getstatic 903	com/tapjoy/internal/fs$a:RPC_ANALYTICS	Lcom/tapjoy/internal/fs$a;
    //   111: if_acmpeq +35 -> 146
    //   114: aload_1
    //   115: astore_0
    //   116: new 863	java/io/IOException
    //   119: dup
    //   120: ldc_w 905
    //   123: invokespecial 906	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   126: athrow
    //   127: astore_2
    //   128: aload_1
    //   129: astore_0
    //   130: ldc_w 310
    //   133: aload_2
    //   134: invokevirtual 907	java/io/IOException:getMessage	()Ljava/lang/String;
    //   137: invokestatic 909	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   140: aload_1
    //   141: invokestatic 914	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   144: iconst_0
    //   145: ireturn
    //   146: aload_1
    //   147: astore_0
    //   148: aload_3
    //   149: getfield 916	com/tapjoy/internal/fs:b	Ljava/lang/String;
    //   152: invokestatic 917	com/tapjoy/internal/fs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   155: astore_3
    //   156: aload_2
    //   157: ifnonnull +82 -> 239
    //   160: aload_3
    //   161: astore_2
    //   162: aload_1
    //   163: astore_0
    //   164: aload_2
    //   165: putstatic 267	com/tapjoy/TapjoyConnectCore:aR	Ljava/lang/String;
    //   168: aload_1
    //   169: astore_0
    //   170: aload 4
    //   172: putstatic 228	com/tapjoy/TapjoyConnectCore:T	Ljava/lang/String;
    //   175: aload_1
    //   176: astore_0
    //   177: aload 5
    //   179: putstatic 232	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   182: aload_1
    //   183: astore_0
    //   184: aload_1
    //   185: invokevirtual 935	com/tapjoy/internal/bq:close	()V
    //   188: aconst_null
    //   189: invokestatic 914	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   192: iconst_1
    //   193: ireturn
    //   194: astore_2
    //   195: aconst_null
    //   196: astore_1
    //   197: aload_1
    //   198: astore_0
    //   199: ldc_w 310
    //   202: aload_2
    //   203: invokevirtual 990	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   206: invokestatic 909	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   209: aload_1
    //   210: invokestatic 914	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   213: goto -69 -> 144
    //   216: astore_1
    //   217: aconst_null
    //   218: astore_0
    //   219: aload_0
    //   220: invokestatic 914	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   223: aload_1
    //   224: athrow
    //   225: astore_1
    //   226: goto -7 -> 219
    //   229: astore_2
    //   230: goto -33 -> 197
    //   233: astore_2
    //   234: aconst_null
    //   235: astore_1
    //   236: goto -108 -> 128
    //   239: goto -77 -> 162
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	242	0	paramString	String
    //   4	206	1	localBq	com.tapjoy.internal.bq
    //   216	8	1	localObject1	Object
    //   225	1	1	localObject2	Object
    //   235	1	1	localObject3	Object
    //   31	1	2	str1	String
    //   127	30	2	localIOException1	java.io.IOException
    //   161	4	2	localObject4	Object
    //   194	9	2	localRuntimeException1	RuntimeException
    //   229	1	2	localRuntimeException2	RuntimeException
    //   233	1	2	localIOException2	java.io.IOException
    //   70	91	3	localObject5	Object
    //   50	121	4	str2	String
    //   11	167	5	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   7	13	127	java/io/IOException
    //   15	32	127	java/io/IOException
    //   34	52	127	java/io/IOException
    //   54	71	127	java/io/IOException
    //   73	91	127	java/io/IOException
    //   93	102	127	java/io/IOException
    //   104	114	127	java/io/IOException
    //   116	127	127	java/io/IOException
    //   148	156	127	java/io/IOException
    //   164	168	127	java/io/IOException
    //   170	175	127	java/io/IOException
    //   177	182	127	java/io/IOException
    //   184	188	127	java/io/IOException
    //   0	5	194	java/lang/RuntimeException
    //   0	5	216	finally
    //   7	13	225	finally
    //   15	32	225	finally
    //   34	52	225	finally
    //   54	71	225	finally
    //   73	91	225	finally
    //   93	102	225	finally
    //   104	114	225	finally
    //   116	127	225	finally
    //   130	140	225	finally
    //   148	156	225	finally
    //   164	168	225	finally
    //   170	175	225	finally
    //   177	182	225	finally
    //   184	188	225	finally
    //   199	209	225	finally
    //   7	13	229	java/lang/RuntimeException
    //   15	32	229	java/lang/RuntimeException
    //   34	52	229	java/lang/RuntimeException
    //   54	71	229	java/lang/RuntimeException
    //   73	91	229	java/lang/RuntimeException
    //   93	102	229	java/lang/RuntimeException
    //   104	114	229	java/lang/RuntimeException
    //   116	127	229	java/lang/RuntimeException
    //   148	156	229	java/lang/RuntimeException
    //   164	168	229	java/lang/RuntimeException
    //   170	175	229	java/lang/RuntimeException
    //   177	182	229	java/lang/RuntimeException
    //   184	188	229	java/lang/RuntimeException
    //   0	5	233	java/io/IOException
  }
  
  private static void d()
  {
    if (!ju.c(M)) {
      hd.a().a(g, h, "12.2.0", "https://rpc.tapjoy.com/", M, L);
    }
    if (k != null) {
      k.onConnectFailure();
    }
  }
  
  /* Error */
  private static boolean d(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 872	com/tapjoy/internal/bq:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bq;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 1000	com/tapjoy/internal/bq:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 1002	com/tapjoy/internal/bq:s	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 310
    //   25: ldc_w 1004
    //   28: invokestatic 492	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 935	com/tapjoy/internal/bq:close	()V
    //   37: aconst_null
    //   38: invokestatic 914	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 935	com/tapjoy/internal/bq:close	()V
    //   49: aconst_null
    //   50: invokestatic 914	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   53: ldc_w 310
    //   56: new 312	com/tapjoy/TapjoyErrorMessage
    //   59: dup
    //   60: getstatic 318	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   63: ldc_w 1006
    //   66: invokespecial 327	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   69: invokestatic 332	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   72: iconst_0
    //   73: ireturn
    //   74: astore_2
    //   75: aconst_null
    //   76: astore_1
    //   77: aload_1
    //   78: astore_0
    //   79: ldc_w 310
    //   82: aload_2
    //   83: invokevirtual 907	java/io/IOException:getMessage	()Ljava/lang/String;
    //   86: invokestatic 909	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload_1
    //   90: invokestatic 914	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   93: goto -40 -> 53
    //   96: astore_2
    //   97: aconst_null
    //   98: astore_1
    //   99: aload_1
    //   100: astore_0
    //   101: ldc_w 310
    //   104: aload_2
    //   105: invokevirtual 990	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   108: invokestatic 909	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   111: aload_1
    //   112: invokestatic 914	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   115: goto -62 -> 53
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_0
    //   121: aload_0
    //   122: invokestatic 914	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
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
    //   4	108	1	localBq	com.tapjoy.internal.bq
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
  
  private static void e()
  {
    if (aT != null) {
      aT.onConnectFailure();
    }
  }
  
  private static boolean e(String paramString)
  {
    Iterator localIterator = ac.getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.startsWith(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private static Map f()
  {
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    Object localObject = new HashMap();
    TapjoyUtil.safePut((Map)localObject, "plugin", N, true);
    TapjoyUtil.safePut((Map)localObject, "sdk_type", O, true);
    TapjoyUtil.safePut((Map)localObject, "app_id", v, true);
    TapjoyUtil.safePut((Map)localObject, "library_version", x, true);
    TapjoyUtil.safePut((Map)localObject, "library_revision", "c33e80e11", true);
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
    if (m())
    {
      TapjoyUtil.safePut((Map)localObject, "advertising_id", c, true);
      TapjoyUtil.safePut((Map)localObject, "ad_tracking_enabled", String.valueOf(d), true);
    }
    if (!n())
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
    gi localGi;
    HashMap localHashMap3;
    if ((o == null) || (o.length() == 0) || (System.currentTimeMillis() - Z > 1800000L))
    {
      o = o();
      TapjoyUtil.safePut((Map)localObject, "session_id", o, true);
      localHashMap2.putAll((Map)localObject);
      localObject = new HashMap();
      TapjoyUtil.safePut((Map)localObject, "app_group_id", S, true);
      TapjoyUtil.safePut((Map)localObject, "store", T, true);
      TapjoyUtil.safePut((Map)localObject, "analytics_api_key", U, true);
      TapjoyUtil.safePut((Map)localObject, "managed_device_id", V, true);
      localHashMap2.putAll((Map)localObject);
      localGi = gi.a();
      localHashMap3 = new HashMap();
      if (localGi.a != null) {
        if (!localGi.a.booleanValue()) {
          break label973;
        }
      }
    }
    label973:
    for (localObject = "1";; localObject = "0")
    {
      TapjoyUtil.safePut(localHashMap3, "gdpr", (String)localObject, true);
      if (!ao.a(localGi.b)) {
        TapjoyUtil.safePut(localHashMap3, "cgdpr", localGi.b, true);
      }
      localHashMap2.putAll(localHashMap3);
      if ((TapjoyCache.getInstance() != null) && (TapjoyCache.getInstance().getCachedOfferIDs() != null) && (TapjoyCache.getInstance().getCachedOfferIDs().length() > 0)) {
        TapjoyUtil.safePut(localHashMap2, "cached_ids", TapjoyCache.getInstance().getCachedOfferIDs(), true);
      }
      TapjoyUtil.safePut(localHashMap2, "display_multiplier", Float.toString(Q), true);
      localHashMap1.putAll(localHashMap2);
      localObject = new HashMap();
      h();
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
      if (ju.c(K)) {
        TapjoyUtil.safePut(localHashMap2, "store_name", aO, true);
      }
      ((Map)localObject).putAll(localHashMap2);
      ((Map)localObject).putAll(g());
      localHashMap1.putAll((Map)localObject);
      return localHashMap1;
      Z = System.currentTimeMillis();
      break;
    }
  }
  
  private static boolean f(String paramString)
  {
    return ac.checkPermission(paramString, g.getPackageName()) == 0;
  }
  
  private static Map g()
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
      paramString = TapjoyUtil.SHA256(v + ":" + p() + ":" + paramLong + ":" + L + ":" + paramInt + ":" + paramString);
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
    Map localMap = f();
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
  
  public static String getLimitedAppID()
  {
    return aP;
  }
  
  public static Map getLimitedGenericURLParams()
  {
    Map localMap = f();
    TapjoyUtil.safePut(localMap, "app_id", aP, true);
    TapjoyUtil.safePut(localMap, "app_group_id", aR, true);
    TapjoyUtil.safePut(localMap, "lmtd", "true", true);
    return localMap;
  }
  
  public static Map getLimitedTimeStampAndVerifierParams()
  {
    long l1 = System.currentTimeMillis() / 1000L;
    String str = b(l1);
    HashMap localHashMap = new HashMap();
    TapjoyUtil.safePut(localHashMap, "timestamp", String.valueOf(l1), true);
    TapjoyUtil.safePut(localHashMap, "verifier", str, true);
    return localHashMap;
  }
  
  public static Map getLimitedURLParams()
  {
    Map localMap = getLimitedGenericURLParams();
    localMap.putAll(getLimitedTimeStampAndVerifierParams());
    return localMap;
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
  
  public static String getUserToken()
  {
    return V;
  }
  
  private static void h()
  {
    Object localObject1 = hd.a(g).a(true);
    Object localObject2 = ((ff)localObject1).d;
    ah = ((fe)localObject2).h;
    ai = ((fe)localObject2).r;
    aj = ((fe)localObject2).s;
    aJ = ((fe)localObject2).m;
    aK = ((fe)localObject2).n;
    aL = ((fe)localObject2).o;
    aM = ((fe)localObject2).u;
    aN = ((fe)localObject2).q;
    localObject2 = ((ff)localObject1).e;
    ak = ((ey)localObject2).e;
    al = ((ey)localObject2).f;
    am = ((ey)localObject2).g;
    an = ((ey)localObject2).h;
    aO = ((ey)localObject2).i;
    localObject1 = ((ff)localObject1).f;
    ao = ((fl)localObject1).s;
    ap = ((fl)localObject1).t;
    aq = ((fl)localObject1).J;
    ar = ((fl)localObject1).K;
    as = ((fl)localObject1).L;
    at = ((fl)localObject1).M;
    au = ((fl)localObject1).N;
    av = ((fl)localObject1).O;
    aw = ((fl)localObject1).P;
    ax = new HashSet(((fl)localObject1).Q);
    ay = ((fl)localObject1).u;
    az = ((fl)localObject1).v;
    aA = ((fl)localObject1).x;
    aB = ((fl)localObject1).y;
    aC = ((fl)localObject1).z;
    aD = ((fl)localObject1).A;
    aE = ((fl)localObject1).B;
    aF = ((fl)localObject1).C;
    aG = ((fl)localObject1).D;
    aH = ((fl)localObject1).F;
    aI = ((fl)localObject1).E;
  }
  
  private static void i()
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
  
  public static boolean isLimitedConnected()
  {
    return aS;
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
  
  private static void j()
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
  
  /* Error */
  private static void k()
  {
    // Byte code:
    //   0: getstatic 456	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   3: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   6: invokevirtual 576	android/content/Context:getPackageName	()Ljava/lang/String;
    //   9: iconst_1
    //   10: invokevirtual 619	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   13: getfield 1567	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
    //   16: invokestatic 152	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   19: astore_1
    //   20: aload_1
    //   21: ifnull +401 -> 422
    //   24: aload_1
    //   25: invokeinterface 468 1 0
    //   30: astore_1
    //   31: aload_1
    //   32: invokeinterface 473 1 0
    //   37: ifeq +385 -> 422
    //   40: aload_1
    //   41: invokeinterface 477 1 0
    //   46: checkcast 1569	android/content/pm/ActivityInfo
    //   49: astore_2
    //   50: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   53: aload_2
    //   54: getfield 1572	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   57: invokevirtual 1573	java/util/Vector:contains	(Ljava/lang/Object;)Z
    //   60: ifeq -29 -> 31
    //   63: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   66: aload_2
    //   67: getfield 1572	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   70: invokevirtual 1576	java/util/Vector:indexOf	(Ljava/lang/Object;)I
    //   73: istore_0
    //   74: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   77: iload_0
    //   78: invokevirtual 1579	java/util/Vector:get	(I)Ljava/lang/Object;
    //   81: checkcast 426	java/lang/String
    //   84: invokestatic 1585	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   87: pop
    //   88: new 140	java/util/Vector
    //   91: dup
    //   92: invokespecial 842	java/util/Vector:<init>	()V
    //   95: astore_3
    //   96: aload_2
    //   97: getfield 1588	android/content/pm/ActivityInfo:configChanges	I
    //   100: sipush 128
    //   103: iand
    //   104: sipush 128
    //   107: if_icmpeq +11 -> 118
    //   110: aload_3
    //   111: ldc_w 1590
    //   114: invokevirtual 854	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   117: pop
    //   118: aload_2
    //   119: getfield 1588	android/content/pm/ActivityInfo:configChanges	I
    //   122: bipush 32
    //   124: iand
    //   125: bipush 32
    //   127: if_icmpeq +11 -> 138
    //   130: aload_3
    //   131: ldc_w 1592
    //   134: invokevirtual 854	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   137: pop
    //   138: aload_3
    //   139: invokevirtual 1593	java/util/Vector:size	()I
    //   142: ifeq +146 -> 288
    //   145: aload_3
    //   146: invokevirtual 1593	java/util/Vector:size	()I
    //   149: iconst_1
    //   150: if_icmpne +94 -> 244
    //   153: new 1595	com/tapjoy/TapjoyIntegrationException
    //   156: dup
    //   157: new 286	java/lang/StringBuilder
    //   160: dup
    //   161: invokespecial 287	java/lang/StringBuilder:<init>	()V
    //   164: aload_3
    //   165: invokevirtual 1596	java/util/Vector:toString	()Ljava/lang/String;
    //   168: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: ldc_w 1598
    //   174: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   180: iload_0
    //   181: invokevirtual 1579	java/util/Vector:get	(I)Ljava/lang/Object;
    //   184: checkcast 426	java/lang/String
    //   187: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: invokespecial 1599	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   196: athrow
    //   197: astore_1
    //   198: new 1595	com/tapjoy/TapjoyIntegrationException
    //   201: dup
    //   202: new 286	java/lang/StringBuilder
    //   205: dup
    //   206: ldc_w 1601
    //   209: invokespecial 323	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   212: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   215: iload_0
    //   216: invokevirtual 1579	java/util/Vector:get	(I)Ljava/lang/Object;
    //   219: checkcast 426	java/lang/String
    //   222: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   228: invokespecial 1599	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   231: athrow
    //   232: astore_1
    //   233: new 1595	com/tapjoy/TapjoyIntegrationException
    //   236: dup
    //   237: ldc_w 1603
    //   240: invokespecial 1599	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   243: athrow
    //   244: new 1595	com/tapjoy/TapjoyIntegrationException
    //   247: dup
    //   248: new 286	java/lang/StringBuilder
    //   251: dup
    //   252: invokespecial 287	java/lang/StringBuilder:<init>	()V
    //   255: aload_3
    //   256: invokevirtual 1596	java/util/Vector:toString	()Ljava/lang/String;
    //   259: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: ldc_w 1605
    //   265: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   268: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   271: iload_0
    //   272: invokevirtual 1579	java/util/Vector:get	(I)Ljava/lang/Object;
    //   275: checkcast 426	java/lang/String
    //   278: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   281: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   284: invokespecial 1599	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   287: athrow
    //   288: getstatic 646	android/os/Build$VERSION:SDK_INT	I
    //   291: bipush 13
    //   293: if_icmplt +49 -> 342
    //   296: aload_2
    //   297: getfield 1588	android/content/pm/ActivityInfo:configChanges	I
    //   300: sipush 1024
    //   303: iand
    //   304: sipush 1024
    //   307: if_icmpeq +35 -> 342
    //   310: ldc_w 310
    //   313: new 286	java/lang/StringBuilder
    //   316: dup
    //   317: ldc_w 1607
    //   320: invokespecial 323	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   323: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   326: iload_0
    //   327: invokevirtual 1579	java/util/Vector:get	(I)Ljava/lang/Object;
    //   330: checkcast 426	java/lang/String
    //   333: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   336: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   339: invokestatic 756	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   342: getstatic 646	android/os/Build$VERSION:SDK_INT	I
    //   345: bipush 11
    //   347: if_icmplt +64 -> 411
    //   350: aload_2
    //   351: getfield 1572	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   354: ldc_w 1609
    //   357: invokevirtual 430	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   360: ifeq +51 -> 411
    //   363: aload_2
    //   364: getfield 1610	android/content/pm/ActivityInfo:flags	I
    //   367: sipush 512
    //   370: iand
    //   371: sipush 512
    //   374: if_icmpeq +37 -> 411
    //   377: new 1595	com/tapjoy/TapjoyIntegrationException
    //   380: dup
    //   381: new 286	java/lang/StringBuilder
    //   384: dup
    //   385: ldc_w 1612
    //   388: invokespecial 323	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   391: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   394: iload_0
    //   395: invokevirtual 1579	java/util/Vector:get	(I)Ljava/lang/Object;
    //   398: checkcast 426	java/lang/String
    //   401: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   404: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   407: invokespecial 1599	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   410: athrow
    //   411: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   414: iload_0
    //   415: invokevirtual 1614	java/util/Vector:remove	(I)Ljava/lang/Object;
    //   418: pop
    //   419: goto -388 -> 31
    //   422: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   425: invokevirtual 1593	java/util/Vector:size	()I
    //   428: ifeq +103 -> 531
    //   431: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   434: invokevirtual 1593	java/util/Vector:size	()I
    //   437: iconst_1
    //   438: if_icmpne +48 -> 486
    //   441: new 1595	com/tapjoy/TapjoyIntegrationException
    //   444: dup
    //   445: new 286	java/lang/StringBuilder
    //   448: dup
    //   449: ldc_w 1616
    //   452: invokespecial 323	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   455: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   458: invokevirtual 1593	java/util/Vector:size	()I
    //   461: invokevirtual 1050	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   464: ldc_w 1618
    //   467: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   470: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   473: invokevirtual 1596	java/util/Vector:toString	()Ljava/lang/String;
    //   476: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   479: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   482: invokespecial 1599	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   485: athrow
    //   486: new 1595	com/tapjoy/TapjoyIntegrationException
    //   489: dup
    //   490: new 286	java/lang/StringBuilder
    //   493: dup
    //   494: ldc_w 1616
    //   497: invokespecial 323	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   500: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   503: invokevirtual 1593	java/util/Vector:size	()I
    //   506: invokevirtual 1050	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   509: ldc_w 1620
    //   512: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   515: getstatic 158	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   518: invokevirtual 1596	java/util/Vector:toString	()Ljava/lang/String;
    //   521: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   524: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   527: invokespecial 1599	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   530: athrow
    //   531: invokestatic 1622	com/tapjoy/TapjoyConnectCore:l	()V
    //   534: ldc_w 1624
    //   537: invokestatic 1585	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   540: astore_1
    //   541: aload_1
    //   542: ldc_w 1626
    //   545: iconst_1
    //   546: anewarray 1581	java/lang/Class
    //   549: dup
    //   550: iconst_0
    //   551: ldc_w 397
    //   554: aastore
    //   555: invokevirtual 1630	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   558: pop
    //   559: ldc_w 1632
    //   562: invokestatic 1635	com/tapjoy/TapjoyUtil:getResource	(Ljava/lang/String;)Ljava/lang/Object;
    //   565: checkcast 426	java/lang/String
    //   568: astore_1
    //   569: aload_1
    //   570: ifnonnull +174 -> 744
    //   573: ldc_w 1637
    //   576: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   579: invokestatic 1641	com/tapjoy/TapjoyUtil:copyTextFromJarIntoString	(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   582: astore_1
    //   583: aload_1
    //   584: ifnonnull +157 -> 741
    //   587: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   590: invokevirtual 571	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   593: ldc_w 1643
    //   596: ldc_w 1645
    //   599: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   602: invokevirtual 576	android/content/Context:getPackageName	()Ljava/lang/String;
    //   605: invokevirtual 582	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   608: istore_0
    //   609: getstatic 128	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   612: invokevirtual 571	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   615: iload_0
    //   616: invokevirtual 587	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   619: astore_2
    //   620: aload_2
    //   621: invokevirtual 1650	java/io/InputStream:available	()I
    //   624: newarray byte
    //   626: astore_3
    //   627: aload_2
    //   628: aload_3
    //   629: invokevirtual 1654	java/io/InputStream:read	([B)I
    //   632: pop
    //   633: new 426	java/lang/String
    //   636: dup
    //   637: aload_3
    //   638: invokespecial 1657	java/lang/String:<init>	([B)V
    //   641: astore_2
    //   642: ldc_w 1632
    //   645: aload_2
    //   646: invokestatic 1661	com/tapjoy/TapjoyUtil:setResource	(Ljava/lang/String;Ljava/lang/Object;)V
    //   649: aload_2
    //   650: astore_1
    //   651: aload_1
    //   652: ifnonnull +38 -> 690
    //   655: new 1595	com/tapjoy/TapjoyIntegrationException
    //   658: dup
    //   659: ldc_w 1663
    //   662: invokespecial 1599	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   665: athrow
    //   666: astore_1
    //   667: new 1595	com/tapjoy/TapjoyIntegrationException
    //   670: dup
    //   671: ldc_w 1665
    //   674: invokespecial 1599	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   677: athrow
    //   678: astore_1
    //   679: new 1595	com/tapjoy/TapjoyIntegrationException
    //   682: dup
    //   683: ldc_w 1667
    //   686: invokespecial 1599	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   689: athrow
    //   690: ldc_w 786
    //   693: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   696: ifnull +28 -> 724
    //   699: ldc_w 786
    //   702: invokestatic 598	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   705: ldc_w 860
    //   708: invokevirtual 430	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   711: ifeq +13 -> 724
    //   714: ldc_w 310
    //   717: ldc_w 1669
    //   720: invokestatic 438	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   723: return
    //   724: getstatic 337	com/tapjoy/TapjoyConnectCore:ad	Lcom/tapjoy/TapjoyGpsHelper;
    //   727: invokevirtual 1672	com/tapjoy/TapjoyGpsHelper:checkGooglePlayIntegration	()V
    //   730: return
    //   731: astore_2
    //   732: goto -81 -> 651
    //   735: astore_1
    //   736: aload_2
    //   737: astore_1
    //   738: goto -87 -> 651
    //   741: goto -90 -> 651
    //   744: goto -161 -> 583
    // Local variable table:
    //   start	length	slot	name	signature
    //   73	543	0	i1	int
    //   19	22	1	localObject1	Object
    //   197	1	1	localClassNotFoundException1	ClassNotFoundException
    //   232	1	1	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   540	112	1	localObject2	Object
    //   666	1	1	localClassNotFoundException2	ClassNotFoundException
    //   678	1	1	localNoSuchMethodException	NoSuchMethodException
    //   735	1	1	localException1	Exception
    //   737	1	1	localObject3	Object
    //   49	601	2	localObject4	Object
    //   731	6	2	localException2	Exception
    //   95	543	3	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   74	118	197	java/lang/ClassNotFoundException
    //   118	138	197	java/lang/ClassNotFoundException
    //   138	197	197	java/lang/ClassNotFoundException
    //   244	288	197	java/lang/ClassNotFoundException
    //   288	342	197	java/lang/ClassNotFoundException
    //   342	411	197	java/lang/ClassNotFoundException
    //   411	419	197	java/lang/ClassNotFoundException
    //   0	20	232	android/content/pm/PackageManager$NameNotFoundException
    //   24	31	232	android/content/pm/PackageManager$NameNotFoundException
    //   31	74	232	android/content/pm/PackageManager$NameNotFoundException
    //   74	118	232	android/content/pm/PackageManager$NameNotFoundException
    //   118	138	232	android/content/pm/PackageManager$NameNotFoundException
    //   138	197	232	android/content/pm/PackageManager$NameNotFoundException
    //   198	232	232	android/content/pm/PackageManager$NameNotFoundException
    //   244	288	232	android/content/pm/PackageManager$NameNotFoundException
    //   288	342	232	android/content/pm/PackageManager$NameNotFoundException
    //   342	411	232	android/content/pm/PackageManager$NameNotFoundException
    //   411	419	232	android/content/pm/PackageManager$NameNotFoundException
    //   534	541	666	java/lang/ClassNotFoundException
    //   541	559	678	java/lang/NoSuchMethodException
    //   587	642	731	java/lang/Exception
    //   642	649	735	java/lang/Exception
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
      if (!f(str)) {
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
      if (!f(str)) {
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
    if (m()) {
      return c;
    }
    if ((n != null) && (n.length() > 0)) {}
    for (i1 = i2; i1 != 0; i1 = 0) {
      return n;
    }
    TapjoyLog.e("TapjoyConnect", "Error -- no valid device identifier");
    return null;
  }
  
  private static String q()
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
  
  public static void requestLimitedTapjoyConnect(Context paramContext, String paramString, TJConnectListener paramTJConnectListener)
  {
    try
    {
      paramString = new fs(paramString);
      if (paramString.a != fs.a.SDK_ANDROID) {
        throw new IllegalArgumentException("The given API key was not for Android.");
      }
    }
    catch (IllegalArgumentException paramContext)
    {
      TapjoyLog.d("TapjoyConnect", paramContext.getMessage());
      throw new TapjoyIntegrationException(paramContext.getMessage());
    }
    aP = paramString.b;
    aQ = paramString.c;
    if (i == null) {
      i = new TapjoyConnectCore();
    }
    aT = paramTJConnectListener;
    paramString = i;
    try
    {
      a(paramContext);
      new Thread(new TapjoyConnectCore.2(paramString)).start();
      TapjoyLog.d("TapjoyConnect", "requestTapjoyConnect function complete");
      return;
    }
    catch (TapjoyIntegrationException paramContext)
    {
      for (;;)
      {
        TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, paramContext.getMessage()));
        e();
        fw.b.notifyObservers(Boolean.FALSE);
      }
    }
    catch (TapjoyException paramContext)
    {
      for (;;)
      {
        TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, paramContext.getMessage()));
        e();
        fw.b.notifyObservers(Boolean.FALSE);
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
    fs localFs;
    try
    {
      localFs = new fs(paramString);
      if (localFs.a != fs.a.SDK_ANDROID) {
        throw new IllegalArgumentException("The given API key was not for Android.");
      }
    }
    catch (IllegalArgumentException paramContext)
    {
      throw new TapjoyIntegrationException(paramContext.getMessage());
    }
    h = paramString;
    v = localFs.b;
    L = localFs.c;
    M = localFs.d;
    if (paramHashtable != null)
    {
      ae.putAll(paramHashtable);
      ge.b().a(paramHashtable);
    }
    hd.a(paramContext).j = paramString;
    k = paramTJConnectListener;
    if (i == null) {
      i = new TapjoyConnectCore();
    }
    paramString = i;
    try
    {
      a(paramContext);
      new Thread(new TapjoyConnectCore.1(paramString)).start();
      paramString.aa = true;
      return;
    }
    catch (TapjoyIntegrationException paramContext)
    {
      TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, paramContext.getMessage()));
      d();
      fw.b.notifyObservers(Boolean.FALSE);
      return;
    }
    catch (TapjoyException paramContext)
    {
      TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, paramContext.getMessage()));
      d();
      fw.b.notifyObservers(Boolean.FALSE);
    }
  }
  
  public static void setAdTrackingEnabled()
  {
    gi localGi;
    boolean bool;
    if (gi.a() != null)
    {
      localGi = gi.a();
      if (localGi.c == null)
      {
        bool = false;
        if (!bool) {
          break label39;
        }
        d = false;
      }
    }
    label39:
    while (ad == null)
    {
      return;
      bool = localGi.c.booleanValue();
      break;
    }
    d = ad.isAdTrackingEnabled();
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
    new Thread(new TapjoyConnectCore.3()).start();
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
    fw.e.notifyObservers();
  }
  
  public static void viewWillOpen(String paramString, int paramInt)
  {
    TapjoyLog.d("TapjoyConnect", "viewWillOpen: " + paramString);
    ag.put(paramString, Integer.valueOf(paramInt));
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
    this.Y = true;
  }
  
  public void appResume()
  {
    if (this.Y)
    {
      o();
      this.Y = false;
    }
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
      localObject2 = TapjoyAppSettings.getInstance().getConnectResult(q(), w.b());
      if ((localObject2 != null) && (a((String)localObject2, true)))
      {
        TapjoyLog.i("TapjoyConnect", "Connect using stored connect result");
        ab = true;
        if (k != null) {
          k.onConnectSuccess();
        }
        fw.a.notifyObservers();
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
            fw.a.notifyObservers();
          }
          fw.b.notifyObservers(Boolean.TRUE);
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
          fw.b.notifyObservers(Boolean.FALSE);
        }
      }
      if (i1 == 0) {
        d();
      }
      fw.b.notifyObservers(Boolean.FALSE);
      return;
    }
  }
  
  public void completeLimitedConnectCall()
  {
    Object localObject1 = "https://connect.tapjoy.com/";
    if (getHostURL() != "https://ws.tapjoyads.com/") {
      localObject1 = getHostURL();
    }
    Object localObject2 = getLimitedURLParams();
    localObject1 = j.getResponseFromURL((String)localObject1 + "api/connect/v3.json?", null, null, (Map)localObject2);
    if ((localObject1 != null) && (((TapjoyHttpURLResponse)localObject1).statusCode == 200))
    {
      if (c(((TapjoyHttpURLResponse)localObject1).response))
      {
        TapjoyLog.i("TapjoyConnect", "Successfully connected to Tapjoy");
        aS = true;
        localObject1 = getLimitedGenericURLParams().entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          TapjoyLog.d("TapjoyConnect", (String)((Map.Entry)localObject2).getKey() + ": " + (String)((Map.Entry)localObject2).getValue());
        }
        if (aT != null) {
          aT.onConnectSuccess();
        }
        fw.a.notifyObservers();
        fw.b.notifyObservers(Boolean.TRUE);
        return;
      }
      e();
      fw.b.notifyObservers(Boolean.FALSE);
      return;
    }
    e();
    fw.b.notifyObservers(Boolean.FALSE);
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
