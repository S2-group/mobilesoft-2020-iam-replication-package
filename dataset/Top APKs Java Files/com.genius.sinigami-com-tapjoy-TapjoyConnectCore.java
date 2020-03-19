package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
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
import java.util.UUID;
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
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(v);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(p());
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
      ((StringBuilder)localObject).append(p());
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
  
  private static boolean a(Context paramContext)
  {
    g = paramContext;
    ac = paramContext.getPackageManager();
    gi.a().a(paramContext);
    ge.a().a(paramContext);
    ad = new TapjoyGpsHelper(g);
    if (j == null) {
      j = new TapjoyURLConnection();
    }
    if (ae == null) {
      ae = new Hashtable();
    }
    j();
    int i1 = g.getResources().getIdentifier("raw/tapjoy_config", null, g.getPackageName());
    paramContext = new Properties();
    try
    {
      paramContext.load(g.getResources().openRawResource(i1));
      a(paramContext);
      if (ju.c(getConnectFlagValue("unit_test_mode"))) {
        k();
      }
      paramContext = Settings.Secure.getString(g.getContentResolver(), "android_id");
      n = paramContext;
      if (paramContext != null) {
        n = n.toLowerCase();
      }
      try
      {
        paramContext = ac;
        Object localObject = g.getPackageName();
        boolean bool = false;
        w = paramContext.getPackageInfo((String)localObject, 0).versionName;
        t = "android";
        D = "android";
        r = Build.MODEL;
        s = Build.MANUFACTURER;
        u = Build.VERSION.RELEASE;
        x = "12.2.0";
        y = "1.0.12";
        try
        {
          if (Build.VERSION.SDK_INT > 3)
          {
            paramContext = new TapjoyDisplayMetricsUtil(g);
            z = paramContext.getScreenDensityDPI();
            A = paramContext.getScreenDensityScale();
            B = paramContext.getScreenLayoutSize();
          }
        }
        catch (Exception paramContext)
        {
          localObject = new StringBuilder("Error getting screen density/dimensions/layout: ");
          ((StringBuilder)localObject).append(paramContext.toString());
          TapjoyLog.e("TapjoyConnect", ((StringBuilder)localObject).toString());
        }
        if (f("android.permission.ACCESS_WIFI_STATE")) {
          try
          {
            paramContext = (WifiManager)g.getSystemService("wifi");
            if (paramContext == null) {
              break label415;
            }
            paramContext = paramContext.getConnectionInfo();
            if (paramContext == null) {
              break label415;
            }
            paramContext = paramContext.getMacAddress();
            p = paramContext;
            if (paramContext == null) {
              break label415;
            }
            p = p.replace(":", "").toLowerCase();
          }
          catch (Exception paramContext)
          {
            localObject = new StringBuilder("Error getting device mac address: ");
            ((StringBuilder)localObject).append(paramContext.toString());
            TapjoyLog.e("TapjoyConnect", ((StringBuilder)localObject).toString());
          }
        } else {
          TapjoyLog.d("TapjoyConnect", "*** ignore macAddress");
        }
        label415:
        paramContext = (TelephonyManager)g.getSystemService("phone");
        if (paramContext != null)
        {
          E = paramContext.getNetworkOperatorName();
          F = paramContext.getNetworkCountryIso();
          paramContext = paramContext.getNetworkOperator();
          if ((paramContext != null) && ((paramContext.length() == 5) || (paramContext.length() == 6)))
          {
            G = paramContext.substring(0, 3);
            H = paramContext.substring(3);
          }
        }
        paramContext = g.getSharedPreferences("tjcPrefrences", 0);
        localObject = paramContext.getString("tapjoyInstallId", "");
        q = (String)localObject;
        if ((localObject == null) || (q.length() == 0)) {
          try
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(UUID.randomUUID().toString());
            ((StringBuilder)localObject).append(System.currentTimeMillis());
            q = TapjoyUtil.SHA256(((StringBuilder)localObject).toString());
            paramContext = paramContext.edit();
            paramContext.putString("tapjoyInstallId", q);
            paramContext.apply();
          }
          catch (Exception paramContext)
          {
            localObject = new StringBuilder("Error generating install id: ");
            ((StringBuilder)localObject).append(paramContext.toString());
            TapjoyLog.e("TapjoyConnect", ((StringBuilder)localObject).toString());
          }
        }
        if ((getConnectFlagValue("TJC_OPTION_STORE_NAME") != null) && (getConnectFlagValue("TJC_OPTION_STORE_NAME").length() > 0))
        {
          K = getConnectFlagValue("TJC_OPTION_STORE_NAME");
          if (!new ArrayList(Arrays.asList(TapjoyConnectFlag.STORE_ARRAY)).contains(K))
          {
            paramContext = new StringBuilder("Warning -- undefined STORE_NAME: ");
            paramContext.append(K);
            TapjoyLog.w("TapjoyConnect", paramContext.toString());
          }
        }
        try
        {
          paramContext = K;
          localObject = new Intent("android.intent.action.VIEW");
          if (paramContext.length() <= 0)
          {
            ((Intent)localObject).setData(Uri.parse("market://details"));
            if (ac.queryIntentActivities((Intent)localObject, 0).size() > 0) {
              bool = true;
            }
          }
          else if (paramContext.equals("gfan"))
          {
            bool = e("com.mappn.gfan");
          }
          else if (paramContext.equals("skt"))
          {
            bool = e("com.skt.skaf.TSCINSTALL");
          }
          R = bool;
        }
        catch (Exception paramContext)
        {
          localObject = new StringBuilder("Error trying to detect store intent on devicee: ");
          ((StringBuilder)localObject).append(paramContext.toString());
          TapjoyLog.e("TapjoyConnect", ((StringBuilder)localObject).toString());
        }
        h();
        if ((getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS") != null) && (getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS").length() > 0)) {
          f = getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS");
        }
        if ((getConnectFlagValue("TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK") != null) && (getConnectFlagValue("TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK").length() > 0)) {
          e = getConnectFlagValue("TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK");
        }
        if ((getConnectFlagValue("TJC_OPTION_USER_ID") != null) && (getConnectFlagValue("TJC_OPTION_USER_ID").length() > 0))
        {
          paramContext = new StringBuilder("Setting userID to: ");
          paramContext.append(getConnectFlagValue("TJC_OPTION_USER_ID"));
          TapjoyLog.i("TapjoyConnect", paramContext.toString());
          setUserID(getConnectFlagValue("TJC_OPTION_USER_ID"), null);
        }
        P = TapjoyUtil.getRedirectDomain(getConnectFlagValue("TJC_OPTION_SERVICE_URL"));
        if (ae != null) {
          i();
        }
        return true;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        throw new TapjoyException(paramContext.getMessage());
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
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
    //   14: invokestatic 873	com/tapjoy/internal/bq:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bq;
    //   17: astore 7
    //   19: aload 7
    //   21: invokevirtual 876	com/tapjoy/internal/bq:d	()Ljava/util/Map;
    //   24: astore 13
    //   26: aload 13
    //   28: ldc_w 878
    //   31: invokeinterface 881 2 0
    //   36: checkcast 421	java/lang/String
    //   39: invokestatic 883	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   42: astore 12
    //   44: aload 13
    //   46: ldc_w 885
    //   49: invokeinterface 881 2 0
    //   54: checkcast 421	java/lang/String
    //   57: invokestatic 883	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   60: astore 16
    //   62: aload 13
    //   64: ldc_w 887
    //   67: invokeinterface 881 2 0
    //   72: checkcast 421	java/lang/String
    //   75: invokestatic 883	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   78: astore 17
    //   80: aload 13
    //   82: ldc_w 889
    //   85: invokeinterface 881 2 0
    //   90: checkcast 421	java/lang/String
    //   93: invokestatic 883	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   96: astore 18
    //   98: aload 13
    //   100: ldc_w 891
    //   103: invokeinterface 881 2 0
    //   108: checkcast 421	java/lang/String
    //   111: invokestatic 883	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   114: astore 15
    //   116: aload 13
    //   118: ldc_w 893
    //   121: invokeinterface 881 2 0
    //   126: astore 14
    //   128: new 895	com/tapjoy/internal/fs
    //   131: dup
    //   132: aload 17
    //   134: invokespecial 896	com/tapjoy/internal/fs:<init>	(Ljava/lang/String;)V
    //   137: astore 6
    //   139: aload 6
    //   141: getfield 899	com/tapjoy/internal/fs:a	Lcom/tapjoy/internal/fs$a;
    //   144: getstatic 904	com/tapjoy/internal/fs$a:RPC_ANALYTICS	Lcom/tapjoy/internal/fs$a;
    //   147: if_acmpne +420 -> 567
    //   150: aload 6
    //   152: getfield 906	com/tapjoy/internal/fs:b	Ljava/lang/String;
    //   155: invokestatic 907	com/tapjoy/internal/fs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   158: astore 11
    //   160: aload 6
    //   162: getfield 908	com/tapjoy/internal/fs:c	Ljava/lang/String;
    //   165: astore 19
    //   167: aload 12
    //   169: astore 6
    //   171: aload 12
    //   173: ifnonnull +7 -> 180
    //   176: aload 11
    //   178: astore 6
    //   180: invokestatic 365	com/tapjoy/internal/hd:a	()Lcom/tapjoy/internal/hd;
    //   183: getstatic 538	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   186: aload 17
    //   188: ldc_w 642
    //   191: ldc_w 910
    //   194: aload 11
    //   196: aload 19
    //   198: invokevirtual 913	com/tapjoy/internal/hd:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   201: aload 6
    //   203: putstatic 221	com/tapjoy/TapjoyConnectCore:S	Ljava/lang/String;
    //   206: aload 16
    //   208: putstatic 223	com/tapjoy/TapjoyConnectCore:T	Ljava/lang/String;
    //   211: aload 17
    //   213: putstatic 225	com/tapjoy/TapjoyConnectCore:U	Ljava/lang/String;
    //   216: aload 18
    //   218: putstatic 227	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   221: new 756	java/util/ArrayList
    //   224: dup
    //   225: invokespecial 914	java/util/ArrayList:<init>	()V
    //   228: astore 6
    //   230: aload 15
    //   232: ifnull +54 -> 286
    //   235: aload 15
    //   237: ldc_w 492
    //   240: invokevirtual 918	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   243: astore 11
    //   245: aload 11
    //   247: arraylength
    //   248: istore_3
    //   249: iconst_0
    //   250: istore_2
    //   251: iload_2
    //   252: iload_3
    //   253: if_icmpge +33 -> 286
    //   256: aload 11
    //   258: iload_2
    //   259: aaload
    //   260: invokevirtual 852	java/lang/String:trim	()Ljava/lang/String;
    //   263: astore 12
    //   265: aload 12
    //   267: invokevirtual 490	java/lang/String:length	()I
    //   270: ifle +402 -> 672
    //   273: aload 6
    //   275: aload 12
    //   277: invokeinterface 919 2 0
    //   282: pop
    //   283: goto +389 -> 672
    //   286: aload 6
    //   288: invokeinterface 922 1 0
    //   293: ifne +8 -> 301
    //   296: aload 6
    //   298: invokestatic 857	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   301: aload 7
    //   303: invokevirtual 925	com/tapjoy/internal/bq:close	()V
    //   306: iload_1
    //   307: ifne +254 -> 561
    //   310: aload 10
    //   312: astore 6
    //   314: aload 14
    //   316: instanceof 421
    //   319: istore_1
    //   320: iload_1
    //   321: ifeq +23 -> 344
    //   324: aload 10
    //   326: astore 6
    //   328: aload 14
    //   330: checkcast 421	java/lang/String
    //   333: invokevirtual 852	java/lang/String:trim	()Ljava/lang/String;
    //   336: invokestatic 931	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   339: lstore 4
    //   341: goto +37 -> 378
    //   344: aload 10
    //   346: astore 6
    //   348: aload 14
    //   350: instanceof 933
    //   353: istore_1
    //   354: iload_1
    //   355: ifeq +20 -> 375
    //   358: aload 10
    //   360: astore 6
    //   362: aload 14
    //   364: checkcast 933	java/lang/Number
    //   367: invokevirtual 936	java/lang/Number:longValue	()J
    //   370: lstore 4
    //   372: goto +6 -> 378
    //   375: lconst_0
    //   376: lstore 4
    //   378: lload 4
    //   380: lconst_0
    //   381: lcmp
    //   382: ifgt +16 -> 398
    //   385: aload 10
    //   387: astore 6
    //   389: invokestatic 942	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   392: invokevirtual 945	com/tapjoy/TapjoyAppSettings:removeConnectResult	()V
    //   395: goto +27 -> 422
    //   398: aload 10
    //   400: astore 6
    //   402: invokestatic 942	com/tapjoy/TapjoyAppSettings:getInstance	()Lcom/tapjoy/TapjoyAppSettings;
    //   405: aload_0
    //   406: invokestatic 947	com/tapjoy/TapjoyConnectCore:q	()Ljava/lang/String;
    //   409: lload 4
    //   411: ldc2_w 948
    //   414: lmul
    //   415: invokestatic 953	com/tapjoy/internal/w:b	()J
    //   418: ladd
    //   419: invokevirtual 957	com/tapjoy/TapjoyAppSettings:saveConnectResultAndParams	(Ljava/lang/String;Ljava/lang/String;J)V
    //   422: aload 10
    //   424: astore 6
    //   426: invokestatic 557	com/tapjoy/internal/ge:a	()Lcom/tapjoy/internal/ge;
    //   429: astore_0
    //   430: aload 10
    //   432: astore 6
    //   434: aload 13
    //   436: ldc_w 959
    //   439: invokeinterface 881 2 0
    //   444: astore 7
    //   446: aload 10
    //   448: astore 6
    //   450: aload 7
    //   452: instanceof 880
    //   455: istore_1
    //   456: iload_1
    //   457: ifeq +61 -> 518
    //   460: aload 10
    //   462: astore 6
    //   464: aload_0
    //   465: getfield 962	com/tapjoy/internal/ge:a	Lcom/tapjoy/internal/gc;
    //   468: aload 7
    //   470: checkcast 880	java/util/Map
    //   473: invokevirtual 967	com/tapjoy/internal/gc:a	(Ljava/util/Map;)V
    //   476: aload 10
    //   478: astore 6
    //   480: aload 7
    //   482: invokestatic 972	com/tapjoy/internal/bk:a	(Ljava/lang/Object;)Ljava/lang/String;
    //   485: astore 7
    //   487: aload 10
    //   489: astore 6
    //   491: aload_0
    //   492: invokevirtual 975	com/tapjoy/internal/ge:c	()Landroid/content/SharedPreferences;
    //   495: invokeinterface 741 1 0
    //   500: ldc_w 959
    //   503: aload 7
    //   505: invokeinterface 747 3 0
    //   510: invokeinterface 750 1 0
    //   515: goto +46 -> 561
    //   518: aload 7
    //   520: ifnonnull +41 -> 561
    //   523: aload 10
    //   525: astore 6
    //   527: aload_0
    //   528: getfield 962	com/tapjoy/internal/ge:a	Lcom/tapjoy/internal/gc;
    //   531: aconst_null
    //   532: invokevirtual 967	com/tapjoy/internal/gc:a	(Ljava/util/Map;)V
    //   535: aload 10
    //   537: astore 6
    //   539: aload_0
    //   540: invokevirtual 975	com/tapjoy/internal/ge:c	()Landroid/content/SharedPreferences;
    //   543: invokeinterface 741 1 0
    //   548: ldc_w 959
    //   551: invokeinterface 979 2 0
    //   556: invokeinterface 750 1 0
    //   561: aconst_null
    //   562: invokestatic 984	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   565: iconst_1
    //   566: ireturn
    //   567: new 864	java/io/IOException
    //   570: dup
    //   571: ldc_w 986
    //   574: invokespecial 987	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   577: athrow
    //   578: astore_0
    //   579: aload 7
    //   581: astore 6
    //   583: goto +73 -> 656
    //   586: astore 6
    //   588: aload 7
    //   590: astore_0
    //   591: aload 6
    //   593: astore 7
    //   595: goto +24 -> 619
    //   598: astore 6
    //   600: aload 7
    //   602: astore_0
    //   603: aload 6
    //   605: astore 7
    //   607: goto +29 -> 636
    //   610: astore_0
    //   611: goto +45 -> 656
    //   614: astore 7
    //   616: aload 9
    //   618: astore_0
    //   619: aload_0
    //   620: astore 6
    //   622: ldc_w 317
    //   625: aload 7
    //   627: invokevirtual 988	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   630: invokestatic 990	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   633: goto +17 -> 650
    //   636: aload_0
    //   637: astore 6
    //   639: ldc_w 317
    //   642: aload 7
    //   644: invokevirtual 991	java/io/IOException:getMessage	()Ljava/lang/String;
    //   647: invokestatic 990	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   650: aload_0
    //   651: invokestatic 984	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   654: iconst_0
    //   655: ireturn
    //   656: aload 6
    //   658: invokestatic 984	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   661: aload_0
    //   662: athrow
    //   663: astore 6
    //   665: goto -290 -> 375
    //   668: astore_0
    //   669: goto -108 -> 561
    //   672: iload_2
    //   673: iconst_1
    //   674: iadd
    //   675: istore_2
    //   676: goto -425 -> 251
    //   679: astore 7
    //   681: aload 8
    //   683: astore_0
    //   684: goto -48 -> 636
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	687	0	paramString	String
    //   0	687	1	paramBoolean	boolean
    //   250	426	2	i1	int
    //   248	6	3	i2	int
    //   339	71	4	l1	long
    //   11	571	6	localObject1	Object
    //   586	6	6	localRuntimeException1	RuntimeException
    //   598	6	6	localIOException1	java.io.IOException
    //   620	37	6	str1	String
    //   663	1	6	localNumberFormatException	NumberFormatException
    //   17	589	7	localObject2	Object
    //   614	29	7	localRuntimeException2	RuntimeException
    //   679	1	7	localIOException2	java.io.IOException
    //   4	678	8	localObject3	Object
    //   1	616	9	localObject4	Object
    //   7	529	10	localObject5	Object
    //   158	99	11	localObject6	Object
    //   42	234	12	str2	String
    //   24	411	13	localMap	Map
    //   126	237	14	localObject7	Object
    //   114	122	15	str3	String
    //   60	147	16	str4	String
    //   78	134	17	str5	String
    //   96	121	18	str6	String
    //   165	32	19	str7	String
    // Exception table:
    //   from	to	target	type
    //   19	167	578	finally
    //   180	230	578	finally
    //   235	249	578	finally
    //   256	283	578	finally
    //   286	301	578	finally
    //   301	306	578	finally
    //   567	578	578	finally
    //   19	167	586	java/lang/RuntimeException
    //   180	230	586	java/lang/RuntimeException
    //   235	249	586	java/lang/RuntimeException
    //   256	283	586	java/lang/RuntimeException
    //   286	301	586	java/lang/RuntimeException
    //   301	306	586	java/lang/RuntimeException
    //   567	578	586	java/lang/RuntimeException
    //   19	167	598	java/io/IOException
    //   180	230	598	java/io/IOException
    //   235	249	598	java/io/IOException
    //   256	283	598	java/io/IOException
    //   286	301	598	java/io/IOException
    //   301	306	598	java/io/IOException
    //   567	578	598	java/io/IOException
    //   13	19	610	finally
    //   314	320	610	finally
    //   328	341	610	finally
    //   348	354	610	finally
    //   362	372	610	finally
    //   389	395	610	finally
    //   402	422	610	finally
    //   426	430	610	finally
    //   434	446	610	finally
    //   450	456	610	finally
    //   464	476	610	finally
    //   480	487	610	finally
    //   491	515	610	finally
    //   527	535	610	finally
    //   539	561	610	finally
    //   622	633	610	finally
    //   639	650	610	finally
    //   13	19	614	java/lang/RuntimeException
    //   314	320	614	java/lang/RuntimeException
    //   328	341	614	java/lang/RuntimeException
    //   348	354	614	java/lang/RuntimeException
    //   362	372	614	java/lang/RuntimeException
    //   389	395	614	java/lang/RuntimeException
    //   402	422	614	java/lang/RuntimeException
    //   426	430	614	java/lang/RuntimeException
    //   434	446	614	java/lang/RuntimeException
    //   450	456	614	java/lang/RuntimeException
    //   464	476	614	java/lang/RuntimeException
    //   480	487	614	java/lang/RuntimeException
    //   491	515	614	java/lang/RuntimeException
    //   527	535	614	java/lang/RuntimeException
    //   539	561	614	java/lang/RuntimeException
    //   328	341	663	java/lang/NumberFormatException
    //   362	372	663	java/lang/NumberFormatException
    //   464	476	668	java/lang/Exception
    //   480	487	668	java/lang/Exception
    //   491	515	668	java/lang/Exception
    //   13	19	679	java/io/IOException
    //   314	320	679	java/io/IOException
    //   328	341	679	java/io/IOException
    //   348	354	679	java/io/IOException
    //   362	372	679	java/io/IOException
    //   389	395	679	java/io/IOException
    //   402	422	679	java/io/IOException
    //   426	430	679	java/io/IOException
    //   434	446	679	java/io/IOException
    //   450	456	679	java/io/IOException
    //   464	476	679	java/io/IOException
    //   480	487	679	java/io/IOException
    //   491	515	679	java/io/IOException
    //   527	535	679	java/io/IOException
    //   539	561	679	java/io/IOException
  }
  
  private static String b(long paramLong)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(aP);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(p());
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(paramLong);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(aQ);
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
  
  /* Error */
  private static boolean c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 873	com/tapjoy/internal/bq:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bq;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 876	com/tapjoy/internal/bq:d	()Ljava/util/Map;
    //   11: astore 4
    //   13: aload_1
    //   14: astore_0
    //   15: aload 4
    //   17: ldc_w 878
    //   20: invokeinterface 881 2 0
    //   25: checkcast 421	java/lang/String
    //   28: invokestatic 883	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   31: astore_3
    //   32: aload_1
    //   33: astore_0
    //   34: aload 4
    //   36: ldc_w 885
    //   39: invokeinterface 881 2 0
    //   44: checkcast 421	java/lang/String
    //   47: invokestatic 883	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   50: astore 5
    //   52: aload_1
    //   53: astore_0
    //   54: aload 4
    //   56: ldc_w 887
    //   59: invokeinterface 881 2 0
    //   64: checkcast 421	java/lang/String
    //   67: invokestatic 883	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   70: astore_2
    //   71: aload_1
    //   72: astore_0
    //   73: aload 4
    //   75: ldc_w 889
    //   78: invokeinterface 881 2 0
    //   83: checkcast 421	java/lang/String
    //   86: invokestatic 883	com/tapjoy/internal/ju:a	(Ljava/lang/String;)Ljava/lang/String;
    //   89: astore 6
    //   91: aload_1
    //   92: astore_0
    //   93: new 895	com/tapjoy/internal/fs
    //   96: dup
    //   97: aload_2
    //   98: invokespecial 896	com/tapjoy/internal/fs:<init>	(Ljava/lang/String;)V
    //   101: astore_2
    //   102: aload_1
    //   103: astore_0
    //   104: aload_2
    //   105: getfield 899	com/tapjoy/internal/fs:a	Lcom/tapjoy/internal/fs$a;
    //   108: getstatic 904	com/tapjoy/internal/fs$a:RPC_ANALYTICS	Lcom/tapjoy/internal/fs$a;
    //   111: if_acmpne +55 -> 166
    //   114: aload_1
    //   115: astore_0
    //   116: aload_2
    //   117: getfield 906	com/tapjoy/internal/fs:b	Ljava/lang/String;
    //   120: invokestatic 907	com/tapjoy/internal/fs:a	(Ljava/lang/String;)Ljava/lang/String;
    //   123: astore 4
    //   125: aload_3
    //   126: astore_2
    //   127: aload_3
    //   128: ifnonnull +6 -> 134
    //   131: aload 4
    //   133: astore_2
    //   134: aload_1
    //   135: astore_0
    //   136: aload_2
    //   137: putstatic 262	com/tapjoy/TapjoyConnectCore:aR	Ljava/lang/String;
    //   140: aload_1
    //   141: astore_0
    //   142: aload 5
    //   144: putstatic 223	com/tapjoy/TapjoyConnectCore:T	Ljava/lang/String;
    //   147: aload_1
    //   148: astore_0
    //   149: aload 6
    //   151: putstatic 227	com/tapjoy/TapjoyConnectCore:V	Ljava/lang/String;
    //   154: aload_1
    //   155: astore_0
    //   156: aload_1
    //   157: invokevirtual 925	com/tapjoy/internal/bq:close	()V
    //   160: aconst_null
    //   161: invokestatic 984	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   164: iconst_1
    //   165: ireturn
    //   166: aload_1
    //   167: astore_0
    //   168: new 864	java/io/IOException
    //   171: dup
    //   172: ldc_w 986
    //   175: invokespecial 987	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   178: athrow
    //   179: astore_1
    //   180: goto +53 -> 233
    //   183: astore_2
    //   184: goto +16 -> 200
    //   187: astore_2
    //   188: goto +27 -> 215
    //   191: astore_1
    //   192: aconst_null
    //   193: astore_0
    //   194: goto +39 -> 233
    //   197: astore_2
    //   198: aconst_null
    //   199: astore_1
    //   200: aload_1
    //   201: astore_0
    //   202: ldc_w 317
    //   205: aload_2
    //   206: invokevirtual 988	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   209: invokestatic 990	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   212: goto +15 -> 227
    //   215: aload_1
    //   216: astore_0
    //   217: ldc_w 317
    //   220: aload_2
    //   221: invokevirtual 991	java/io/IOException:getMessage	()Ljava/lang/String;
    //   224: invokestatic 990	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   227: aload_1
    //   228: invokestatic 984	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   231: iconst_0
    //   232: ireturn
    //   233: aload_0
    //   234: invokestatic 984	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   237: aload_1
    //   238: athrow
    //   239: astore_2
    //   240: aconst_null
    //   241: astore_1
    //   242: goto -27 -> 215
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	245	0	paramString	String
    //   4	163	1	localBq	com.tapjoy.internal.bq
    //   179	1	1	localObject1	Object
    //   191	1	1	localObject2	Object
    //   199	43	1	localCloseable	java.io.Closeable
    //   70	67	2	localObject3	Object
    //   183	1	2	localRuntimeException1	RuntimeException
    //   187	1	2	localIOException1	java.io.IOException
    //   197	24	2	localRuntimeException2	RuntimeException
    //   239	1	2	localIOException2	java.io.IOException
    //   31	97	3	str1	String
    //   11	121	4	localObject4	Object
    //   50	93	5	str2	String
    //   89	61	6	str3	String
    // Exception table:
    //   from	to	target	type
    //   7	13	179	finally
    //   15	32	179	finally
    //   34	52	179	finally
    //   54	71	179	finally
    //   73	91	179	finally
    //   93	102	179	finally
    //   104	114	179	finally
    //   116	125	179	finally
    //   136	140	179	finally
    //   142	147	179	finally
    //   149	154	179	finally
    //   156	160	179	finally
    //   168	179	179	finally
    //   202	212	179	finally
    //   217	227	179	finally
    //   7	13	183	java/lang/RuntimeException
    //   15	32	183	java/lang/RuntimeException
    //   34	52	183	java/lang/RuntimeException
    //   54	71	183	java/lang/RuntimeException
    //   73	91	183	java/lang/RuntimeException
    //   93	102	183	java/lang/RuntimeException
    //   104	114	183	java/lang/RuntimeException
    //   116	125	183	java/lang/RuntimeException
    //   136	140	183	java/lang/RuntimeException
    //   142	147	183	java/lang/RuntimeException
    //   149	154	183	java/lang/RuntimeException
    //   156	160	183	java/lang/RuntimeException
    //   168	179	183	java/lang/RuntimeException
    //   7	13	187	java/io/IOException
    //   15	32	187	java/io/IOException
    //   34	52	187	java/io/IOException
    //   54	71	187	java/io/IOException
    //   73	91	187	java/io/IOException
    //   93	102	187	java/io/IOException
    //   104	114	187	java/io/IOException
    //   116	125	187	java/io/IOException
    //   136	140	187	java/io/IOException
    //   142	147	187	java/io/IOException
    //   149	154	187	java/io/IOException
    //   156	160	187	java/io/IOException
    //   168	179	187	java/io/IOException
    //   0	5	191	finally
    //   0	5	197	java/lang/RuntimeException
    //   0	5	239	java/io/IOException
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
    //   1: invokestatic 873	com/tapjoy/internal/bq:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bq;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 1005	com/tapjoy/internal/bq:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 1007	com/tapjoy/internal/bq:s	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 317
    //   25: ldc_w 1009
    //   28: invokestatic 487	com/tapjoy/TapjoyLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 925	com/tapjoy/internal/bq:close	()V
    //   37: aconst_null
    //   38: invokestatic 984	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 925	com/tapjoy/internal/bq:close	()V
    //   49: aconst_null
    //   50: invokestatic 984	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
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
    //   79: ldc_w 317
    //   82: aload_2
    //   83: invokevirtual 988	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   86: invokestatic 990	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: goto +15 -> 104
    //   92: aload_1
    //   93: astore_0
    //   94: ldc_w 317
    //   97: aload_2
    //   98: invokevirtual 991	java/io/IOException:getMessage	()Ljava/lang/String;
    //   101: invokestatic 990	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   104: aload_1
    //   105: invokestatic 984	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   108: ldc_w 317
    //   111: new 319	com/tapjoy/TapjoyErrorMessage
    //   114: dup
    //   115: getstatic 309	com/tapjoy/TapjoyErrorMessage$ErrorType:SDK_ERROR	Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
    //   118: ldc_w 1011
    //   121: invokespecial 322	com/tapjoy/TapjoyErrorMessage:<init>	(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
    //   124: invokestatic 327	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
    //   127: iconst_0
    //   128: ireturn
    //   129: aload_0
    //   130: invokestatic 984	com/tapjoy/internal/kd:a	(Ljava/io/Closeable;)V
    //   133: aload_1
    //   134: athrow
    //   135: astore_2
    //   136: aconst_null
    //   137: astore_1
    //   138: goto -46 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	paramString	String
    //   4	42	1	localBq	com.tapjoy.internal.bq
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
    Object localObject1 = new HashMap();
    TapjoyUtil.safePut((Map)localObject1, "plugin", N, true);
    TapjoyUtil.safePut((Map)localObject1, "sdk_type", O, true);
    TapjoyUtil.safePut((Map)localObject1, "app_id", v, true);
    TapjoyUtil.safePut((Map)localObject1, "library_version", x, true);
    TapjoyUtil.safePut((Map)localObject1, "library_revision", "c33e80e11", true);
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
    if (m())
    {
      TapjoyUtil.safePut((Map)localObject1, "advertising_id", c, true);
      TapjoyUtil.safePut((Map)localObject1, "ad_tracking_enabled", String.valueOf(d), true);
    }
    if (!n())
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
      o = o();
    }
    TapjoyUtil.safePut((Map)localObject1, "session_id", o, true);
    localHashMap2.putAll((Map)localObject1);
    localObject1 = new HashMap();
    TapjoyUtil.safePut((Map)localObject1, "app_group_id", S, true);
    TapjoyUtil.safePut((Map)localObject1, "store", T, true);
    TapjoyUtil.safePut((Map)localObject1, "analytics_api_key", U, true);
    TapjoyUtil.safePut((Map)localObject1, "managed_device_id", V, true);
    localHashMap2.putAll((Map)localObject1);
    localObject2 = gi.a();
    HashMap localHashMap3 = new HashMap();
    if (((gi)localObject2).a != null)
    {
      if (((gi)localObject2).a.booleanValue()) {
        localObject1 = "1";
      } else {
        localObject1 = "0";
      }
      TapjoyUtil.safePut(localHashMap3, "gdpr", (String)localObject1, true);
    }
    if (!ao.a(((gi)localObject2).b)) {
      TapjoyUtil.safePut(localHashMap3, "cgdpr", ((gi)localObject2).b, true);
    }
    localHashMap2.putAll(localHashMap3);
    if ((TapjoyCache.getInstance() != null) && (TapjoyCache.getInstance().getCachedOfferIDs() != null) && (TapjoyCache.getInstance().getCachedOfferIDs().length() > 0)) {
      TapjoyUtil.safePut(localHashMap2, "cached_ids", TapjoyCache.getInstance().getCachedOfferIDs(), true);
    }
    TapjoyUtil.safePut(localHashMap2, "display_multiplier", Float.toString(Q), true);
    localHashMap1.putAll(localHashMap2);
    localObject1 = new HashMap();
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
    ((Map)localObject1).putAll(localHashMap2);
    localHashMap2 = new HashMap();
    TapjoyUtil.safePut(localHashMap2, "pkg_ver", ak, true);
    TapjoyUtil.safePut(localHashMap2, "pkg_rev", al);
    TapjoyUtil.safePut(localHashMap2, "pkg_data_ver", am, true);
    TapjoyUtil.safePut(localHashMap2, "installer", an, true);
    if (ju.c(K)) {
      TapjoyUtil.safePut(localHashMap2, "store_name", aO, true);
    }
    ((Map)localObject1).putAll(localHashMap2);
    ((Map)localObject1).putAll(g());
    localHashMap1.putAll((Map)localObject1);
    return localHashMap1;
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
      ((StringBuilder)localObject).append(p());
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
    StringBuilder localStringBuilder = new StringBuilder("isViewOpen: ");
    localStringBuilder.append(ag.size());
    TapjoyLog.d("TapjoyConnect", localStringBuilder.toString());
    return !ag.isEmpty();
  }
  
  private static void j()
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
  
  /* Error */
  private static void k()
  {
    // Byte code:
    //   0: getstatic 451	com/tapjoy/TapjoyConnectCore:ac	Landroid/content/pm/PackageManager;
    //   3: getstatic 538	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   6: invokevirtual 577	android/content/Context:getPackageName	()Ljava/lang/String;
    //   9: iconst_1
    //   10: invokevirtual 620	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   13: getfield 1574	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
    //   16: invokestatic 147	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   19: astore_1
    //   20: aload_1
    //   21: ifnull +419 -> 440
    //   24: aload_1
    //   25: invokeinterface 463 1 0
    //   30: astore_2
    //   31: aload_2
    //   32: invokeinterface 468 1 0
    //   37: ifeq +403 -> 440
    //   40: aload_2
    //   41: invokeinterface 472 1 0
    //   46: checkcast 1576	android/content/pm/ActivityInfo
    //   49: astore_3
    //   50: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   53: aload_3
    //   54: getfield 1579	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   57: invokevirtual 1580	java/util/Vector:contains	(Ljava/lang/Object;)Z
    //   60: ifeq -29 -> 31
    //   63: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   66: aload_3
    //   67: getfield 1579	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   70: invokevirtual 1583	java/util/Vector:indexOf	(Ljava/lang/Object;)I
    //   73: istore_0
    //   74: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   77: iload_0
    //   78: invokevirtual 1586	java/util/Vector:get	(I)Ljava/lang/Object;
    //   81: checkcast 421	java/lang/String
    //   84: invokestatic 1592	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   87: pop
    //   88: new 135	java/util/Vector
    //   91: dup
    //   92: invokespecial 843	java/util/Vector:<init>	()V
    //   95: astore_1
    //   96: aload_3
    //   97: getfield 1595	android/content/pm/ActivityInfo:configChanges	I
    //   100: sipush 128
    //   103: iand
    //   104: sipush 128
    //   107: if_icmpeq +11 -> 118
    //   110: aload_1
    //   111: ldc_w 1597
    //   114: invokevirtual 855	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   117: pop
    //   118: aload_3
    //   119: getfield 1595	android/content/pm/ActivityInfo:configChanges	I
    //   122: bipush 32
    //   124: iand
    //   125: bipush 32
    //   127: if_icmpeq +11 -> 138
    //   130: aload_1
    //   131: ldc_w 1599
    //   134: invokevirtual 855	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   137: pop
    //   138: aload_1
    //   139: invokevirtual 1600	java/util/Vector:size	()I
    //   142: ifeq +115 -> 257
    //   145: aload_1
    //   146: invokevirtual 1600	java/util/Vector:size	()I
    //   149: iconst_1
    //   150: if_icmpne +55 -> 205
    //   153: new 281	java/lang/StringBuilder
    //   156: dup
    //   157: invokespecial 282	java/lang/StringBuilder:<init>	()V
    //   160: astore_2
    //   161: aload_2
    //   162: aload_1
    //   163: invokevirtual 1601	java/util/Vector:toString	()Ljava/lang/String;
    //   166: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: aload_2
    //   171: ldc_w 1603
    //   174: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: pop
    //   178: aload_2
    //   179: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   182: iload_0
    //   183: invokevirtual 1586	java/util/Vector:get	(I)Ljava/lang/Object;
    //   186: checkcast 421	java/lang/String
    //   189: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: pop
    //   193: new 1605	com/tapjoy/TapjoyIntegrationException
    //   196: dup
    //   197: aload_2
    //   198: invokevirtual 297	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   201: invokespecial 1606	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   204: athrow
    //   205: new 281	java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial 282	java/lang/StringBuilder:<init>	()V
    //   212: astore_2
    //   213: aload_2
    //   214: aload_1
    //   215: invokevirtual 1601	java/util/Vector:toString	()Ljava/lang/String;
    //   218: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: pop
    //   222: aload_2
    //   223: ldc_w 1608
    //   226: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: pop
    //   230: aload_2
    //   231: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   234: iload_0
    //   235: invokevirtual 1586	java/util/Vector:get	(I)Ljava/lang/Object;
    //   238: checkcast 421	java/lang/String
    //   241: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: pop
    //   245: new 1605	com/tapjoy/TapjoyIntegrationException
    //   248: dup
    //   249: aload_2
    //   250: invokevirtual 297	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   253: invokespecial 1606	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   256: athrow
    //   257: getstatic 647	android/os/Build$VERSION:SDK_INT	I
    //   260: bipush 13
    //   262: if_icmplt +53 -> 315
    //   265: aload_3
    //   266: getfield 1595	android/content/pm/ActivityInfo:configChanges	I
    //   269: sipush 1024
    //   272: iand
    //   273: sipush 1024
    //   276: if_icmpeq +39 -> 315
    //   279: new 281	java/lang/StringBuilder
    //   282: dup
    //   283: ldc_w 1610
    //   286: invokespecial 314	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   289: astore_1
    //   290: aload_1
    //   291: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   294: iload_0
    //   295: invokevirtual 1586	java/util/Vector:get	(I)Ljava/lang/Object;
    //   298: checkcast 421	java/lang/String
    //   301: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: pop
    //   305: ldc_w 317
    //   308: aload_1
    //   309: invokevirtual 297	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   312: invokestatic 765	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   315: getstatic 647	android/os/Build$VERSION:SDK_INT	I
    //   318: bipush 11
    //   320: if_icmplt +71 -> 391
    //   323: aload_3
    //   324: getfield 1579	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   327: ldc_w 1612
    //   330: invokevirtual 425	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   333: ifeq +58 -> 391
    //   336: aload_3
    //   337: getfield 1613	android/content/pm/ActivityInfo:flags	I
    //   340: sipush 512
    //   343: iand
    //   344: sipush 512
    //   347: if_icmpne +6 -> 353
    //   350: goto +41 -> 391
    //   353: new 281	java/lang/StringBuilder
    //   356: dup
    //   357: ldc_w 1615
    //   360: invokespecial 314	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   363: astore_1
    //   364: aload_1
    //   365: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   368: iload_0
    //   369: invokevirtual 1586	java/util/Vector:get	(I)Ljava/lang/Object;
    //   372: checkcast 421	java/lang/String
    //   375: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   378: pop
    //   379: new 1605	com/tapjoy/TapjoyIntegrationException
    //   382: dup
    //   383: aload_1
    //   384: invokevirtual 297	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   387: invokespecial 1606	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   390: athrow
    //   391: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   394: iload_0
    //   395: invokevirtual 1617	java/util/Vector:remove	(I)Ljava/lang/Object;
    //   398: pop
    //   399: goto -368 -> 31
    //   402: new 281	java/lang/StringBuilder
    //   405: dup
    //   406: ldc_w 1619
    //   409: invokespecial 314	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   412: astore_1
    //   413: aload_1
    //   414: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   417: iload_0
    //   418: invokevirtual 1586	java/util/Vector:get	(I)Ljava/lang/Object;
    //   421: checkcast 421	java/lang/String
    //   424: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   427: pop
    //   428: new 1605	com/tapjoy/TapjoyIntegrationException
    //   431: dup
    //   432: aload_1
    //   433: invokevirtual 297	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   436: invokespecial 1606	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   439: athrow
    //   440: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   443: invokevirtual 1600	java/util/Vector:size	()I
    //   446: ifeq +119 -> 565
    //   449: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   452: invokevirtual 1600	java/util/Vector:size	()I
    //   455: iconst_1
    //   456: if_icmpne +56 -> 512
    //   459: new 281	java/lang/StringBuilder
    //   462: dup
    //   463: ldc_w 1621
    //   466: invokespecial 314	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   469: astore_1
    //   470: aload_1
    //   471: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   474: invokevirtual 1600	java/util/Vector:size	()I
    //   477: invokevirtual 1053	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   480: pop
    //   481: aload_1
    //   482: ldc_w 1623
    //   485: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   488: pop
    //   489: aload_1
    //   490: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   493: invokevirtual 1601	java/util/Vector:toString	()Ljava/lang/String;
    //   496: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   499: pop
    //   500: new 1605	com/tapjoy/TapjoyIntegrationException
    //   503: dup
    //   504: aload_1
    //   505: invokevirtual 297	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   508: invokespecial 1606	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   511: athrow
    //   512: new 281	java/lang/StringBuilder
    //   515: dup
    //   516: ldc_w 1621
    //   519: invokespecial 314	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   522: astore_1
    //   523: aload_1
    //   524: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   527: invokevirtual 1600	java/util/Vector:size	()I
    //   530: invokevirtual 1053	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   533: pop
    //   534: aload_1
    //   535: ldc_w 1625
    //   538: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   541: pop
    //   542: aload_1
    //   543: getstatic 153	com/tapjoy/TapjoyConnectCore:m	Ljava/util/Vector;
    //   546: invokevirtual 1601	java/util/Vector:toString	()Ljava/lang/String;
    //   549: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   552: pop
    //   553: new 1605	com/tapjoy/TapjoyIntegrationException
    //   556: dup
    //   557: aload_1
    //   558: invokevirtual 297	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   561: invokespecial 1606	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   564: athrow
    //   565: invokestatic 1627	com/tapjoy/TapjoyConnectCore:l	()V
    //   568: ldc_w 1629
    //   571: invokestatic 1592	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   574: astore_1
    //   575: aload_1
    //   576: ldc_w 1631
    //   579: iconst_1
    //   580: anewarray 1588	java/lang/Class
    //   583: dup
    //   584: iconst_0
    //   585: ldc_w 392
    //   588: aastore
    //   589: invokevirtual 1635	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   592: pop
    //   593: ldc_w 1637
    //   596: invokestatic 1640	com/tapjoy/TapjoyUtil:getResource	(Ljava/lang/String;)Ljava/lang/Object;
    //   599: checkcast 421	java/lang/String
    //   602: astore_2
    //   603: aload_2
    //   604: astore_1
    //   605: aload_2
    //   606: ifnonnull +13 -> 619
    //   609: ldc_w 1642
    //   612: getstatic 538	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   615: invokestatic 1646	com/tapjoy/TapjoyUtil:copyTextFromJarIntoString	(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   618: astore_1
    //   619: aload_1
    //   620: astore_2
    //   621: aload_1
    //   622: ifnonnull +65 -> 687
    //   625: getstatic 538	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   628: invokevirtual 572	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   631: ldc_w 1648
    //   634: ldc_w 1650
    //   637: getstatic 538	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   640: invokevirtual 577	android/content/Context:getPackageName	()Ljava/lang/String;
    //   643: invokevirtual 583	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   646: istore_0
    //   647: getstatic 538	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   650: invokevirtual 572	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   653: iload_0
    //   654: invokevirtual 588	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   657: astore_2
    //   658: aload_2
    //   659: invokevirtual 1655	java/io/InputStream:available	()I
    //   662: newarray byte
    //   664: astore_3
    //   665: aload_2
    //   666: aload_3
    //   667: invokevirtual 1659	java/io/InputStream:read	([B)I
    //   670: pop
    //   671: new 421	java/lang/String
    //   674: dup
    //   675: aload_3
    //   676: invokespecial 1662	java/lang/String:<init>	([B)V
    //   679: astore_2
    //   680: ldc_w 1637
    //   683: aload_2
    //   684: invokestatic 1666	com/tapjoy/TapjoyUtil:setResource	(Ljava/lang/String;Ljava/lang/Object;)V
    //   687: aload_2
    //   688: ifnull +44 -> 732
    //   691: ldc_w 807
    //   694: invokestatic 599	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   697: ifnull +28 -> 725
    //   700: ldc_w 807
    //   703: invokestatic 599	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   706: ldc_w 861
    //   709: invokevirtual 425	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   712: ifeq +13 -> 725
    //   715: ldc_w 317
    //   718: ldc_w 1668
    //   721: invokestatic 433	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   724: return
    //   725: getstatic 332	com/tapjoy/TapjoyConnectCore:ad	Lcom/tapjoy/TapjoyGpsHelper;
    //   728: invokevirtual 1671	com/tapjoy/TapjoyGpsHelper:checkGooglePlayIntegration	()V
    //   731: return
    //   732: new 1605	com/tapjoy/TapjoyIntegrationException
    //   735: dup
    //   736: ldc_w 1673
    //   739: invokespecial 1606	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   742: athrow
    //   743: new 1605	com/tapjoy/TapjoyIntegrationException
    //   746: dup
    //   747: ldc_w 1675
    //   750: invokespecial 1606	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   753: athrow
    //   754: new 1605	com/tapjoy/TapjoyIntegrationException
    //   757: dup
    //   758: ldc_w 1677
    //   761: invokespecial 1606	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   764: athrow
    //   765: new 1605	com/tapjoy/TapjoyIntegrationException
    //   768: dup
    //   769: ldc_w 1679
    //   772: invokespecial 1606	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   775: athrow
    //   776: astore_1
    //   777: goto -12 -> 765
    //   780: astore_1
    //   781: goto -379 -> 402
    //   784: astore_1
    //   785: goto -31 -> 754
    //   788: astore_1
    //   789: goto -46 -> 743
    //   792: astore_2
    //   793: aload_1
    //   794: astore_2
    //   795: goto -108 -> 687
    //   798: astore_1
    //   799: goto -112 -> 687
    // Local variable table:
    //   start	length	slot	name	signature
    //   73	581	0	i1	int
    //   19	603	1	localObject1	Object
    //   776	1	1	localNameNotFoundException	PackageManager.NameNotFoundException
    //   780	1	1	localClassNotFoundException1	ClassNotFoundException
    //   784	1	1	localClassNotFoundException2	ClassNotFoundException
    //   788	6	1	localNoSuchMethodException1	NoSuchMethodException
    //   798	1	1	localException1	Exception
    //   30	658	2	localObject2	Object
    //   792	1	2	localException2	Exception
    //   794	1	2	localNoSuchMethodException2	NoSuchMethodException
    //   49	627	3	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   0	20	776	android/content/pm/PackageManager$NameNotFoundException
    //   24	31	776	android/content/pm/PackageManager$NameNotFoundException
    //   31	74	776	android/content/pm/PackageManager$NameNotFoundException
    //   74	118	776	android/content/pm/PackageManager$NameNotFoundException
    //   118	138	776	android/content/pm/PackageManager$NameNotFoundException
    //   138	205	776	android/content/pm/PackageManager$NameNotFoundException
    //   205	257	776	android/content/pm/PackageManager$NameNotFoundException
    //   257	315	776	android/content/pm/PackageManager$NameNotFoundException
    //   315	350	776	android/content/pm/PackageManager$NameNotFoundException
    //   353	391	776	android/content/pm/PackageManager$NameNotFoundException
    //   391	399	776	android/content/pm/PackageManager$NameNotFoundException
    //   402	440	776	android/content/pm/PackageManager$NameNotFoundException
    //   74	118	780	java/lang/ClassNotFoundException
    //   118	138	780	java/lang/ClassNotFoundException
    //   138	205	780	java/lang/ClassNotFoundException
    //   205	257	780	java/lang/ClassNotFoundException
    //   257	315	780	java/lang/ClassNotFoundException
    //   315	350	780	java/lang/ClassNotFoundException
    //   353	391	780	java/lang/ClassNotFoundException
    //   391	399	780	java/lang/ClassNotFoundException
    //   568	575	784	java/lang/ClassNotFoundException
    //   575	593	788	java/lang/NoSuchMethodException
    //   625	680	792	java/lang/Exception
    //   680	687	798	java/lang/Exception
  }
  
  private static void l()
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
      if (!f(str)) {
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
      if (!f(str)) {
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
  
  private static String p()
  {
    if (n()) {
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
    if (m()) {
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
  
  private static String q()
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
  
  public static void requestLimitedTapjoyConnect(Context paramContext, String paramString, TJConnectListener paramTJConnectListener)
  {
    try
    {
      paramString = new fs(paramString);
      fs.a localA1 = paramString.a;
      fs.a localA2 = fs.a.SDK_ANDROID;
      if (localA1 == localA2)
      {
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
          new Thread(new Runnable()
          {
            public final void run()
            {
              TapjoyConnectCore.a();
              TapjoyConnectCore.this.completeLimitedConnectCall();
            }
          }).start();
        }
        catch (TapjoyException paramContext)
        {
          TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, paramContext.getMessage()));
          e();
          fw.b.notifyObservers(Boolean.FALSE);
        }
        catch (TapjoyIntegrationException paramContext)
        {
          TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, paramContext.getMessage()));
          e();
          fw.b.notifyObservers(Boolean.FALSE);
        }
        TapjoyLog.d("TapjoyConnect", "requestTapjoyConnect function complete");
        return;
      }
      throw new IllegalArgumentException("The given API key was not for Android.");
    }
    catch (IllegalArgumentException paramContext)
    {
      TapjoyLog.d("TapjoyConnect", paramContext.getMessage());
      throw new TapjoyIntegrationException(paramContext.getMessage());
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
      fs localFs = new fs(paramString);
      fs.a localA1 = localFs.a;
      fs.a localA2 = fs.a.SDK_ANDROID;
      if (localA1 == localA2)
      {
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
          new Thread(new Runnable()
          {
            public final void run()
            {
              TapjoyConnectCore.a();
              TapjoyConnectCore.this.completeConnectCall();
            }
          }).start();
          paramString.aa = true;
          return;
        }
        catch (TapjoyException paramContext)
        {
          TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, paramContext.getMessage()));
          d();
          fw.b.notifyObservers(Boolean.FALSE);
          return;
        }
        catch (TapjoyIntegrationException paramContext)
        {
          TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, paramContext.getMessage()));
          d();
          fw.b.notifyObservers(Boolean.FALSE);
          return;
        }
      }
      throw new IllegalArgumentException("The given API key was not for Android.");
    }
    catch (IllegalArgumentException paramContext)
    {
      throw new TapjoyIntegrationException(paramContext.getMessage());
    }
  }
  
  public static void setAdTrackingEnabled()
  {
    if (gi.a() != null)
    {
      gi localGi = gi.a();
      boolean bool;
      if (localGi.c == null) {
        bool = false;
      } else {
        bool = localGi.c.booleanValue();
      }
      if (bool)
      {
        d = false;
        return;
      }
    }
    if (ad != null) {
      d = ad.isAdTrackingEnabled();
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
    fw.e.notifyObservers();
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
    localObject = f();
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
          fw.a.notifyObservers();
        }
        fw.b.notifyObservers(Boolean.TRUE);
      }
      else
      {
        if (i1 == 0) {
          d();
        }
        fw.b.notifyObservers(Boolean.FALSE);
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
      fw.b.notifyObservers(Boolean.FALSE);
    }
  }
  
  public void completeLimitedConnectCall()
  {
    Object localObject1 = "https://connect.tapjoy.com/";
    if (getHostURL() != "https://ws.tapjoyads.com/") {
      localObject1 = getHostURL();
    }
    Object localObject2 = getLimitedURLParams();
    Object localObject3 = j;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append("api/connect/v3.json?");
    localObject1 = ((TapjoyURLConnection)localObject3).getResponseFromURL(localStringBuilder.toString(), null, null, (Map)localObject2);
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
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append((String)((Map.Entry)localObject2).getKey());
          ((StringBuilder)localObject3).append(": ");
          ((StringBuilder)localObject3).append((String)((Map.Entry)localObject2).getValue());
          TapjoyLog.d("TapjoyConnect", ((StringBuilder)localObject3).toString());
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
