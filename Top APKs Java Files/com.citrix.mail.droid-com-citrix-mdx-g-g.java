package com.citrix.mdx.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.citrix.MAM.Android.ManagedApp.RHelper;
import com.citrix.mdx.e.i;
import com.citrix.mdx.h.k.a;
import com.citrix.mdx.h.l;
import com.citrix.mdx.h.n;
import com.citrix.mdx.h.p;
import com.citrix.mdx.lib.IntuneUtils;
import com.citrix.mdx.lib.MAMProviderClient;
import com.citrix.mdx.lib.PolicyParser;
import com.citrix.mdx.plugins.m.g;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class g
  extends com.citrix.mdx.h.k
{
  public static boolean a = false;
  public static String b = null;
  public static String c = null;
  public static com.citrix.mdx.h.o d = null;
  private static g e = null;
  @SuppressLint({"UseSparseArrays"})
  private static PolicyParser f = new PolicyParser((String)null);
  private static long g = w;
  private static long h = 0L;
  private static long i = TimeUnit.SECONDS.toMillis(60L);
  
  private g()
  {
    super(k.a.a, m.g.class);
  }
  
  private static boolean A()
  {
    return f.isNetworkTunneled();
  }
  
  private static double B()
  {
    try
    {
      double d1 = e("GeofenceLongitude");
      return d1;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      com.citrix.mdx.plugins.k.getPlugin().Error("MDX-PolicyManager", "Error: Trying to read GeofenceCenterLongitude as double, value = " + a("GeofenceLongitude"));
    }
    return 0.0D;
  }
  
  private static double C()
  {
    try
    {
      double d1 = e("GeofenceLatitude");
      return d1;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      com.citrix.mdx.plugins.k.getPlugin().Error("MDX-PolicyManager", "Error: Trying to read GeofenceCenterLongitude as double, value = " + a("GeofenceLatitude"));
    }
    return 0.0D;
  }
  
  private static double D()
  {
    try
    {
      double d1 = e("GeofenceRadius");
      return d1;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      com.citrix.mdx.plugins.k.getPlugin().Error("MDX-PolicyManager", "Error: Trying to read GeofenceCenterLongitude as double, value = " + a("GeofenceRadius"));
    }
    return 0.0D;
  }
  
  private static Location E()
  {
    Location localLocation = new Location("gps");
    localLocation.setLatitude(C());
    localLocation.setLongitude(B());
    return localLocation;
  }
  
  private l a(Context paramContext, com.citrix.mdx.h.k[] paramArrayOfK)
  {
    int k = 0;
    int m = paramArrayOfK.length;
    int j = 0;
    while (j < m)
    {
      localObject1 = paramArrayOfK[j];
      if (localObject1 != null) {
        ((com.citrix.mdx.h.k)localObject1).a(paramContext);
      }
      j += 1;
    }
    List localList = b.d();
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("ErrorCodes = ");
    if (localList.size() == 0)
    {
      ((StringBuilder)localObject1).append(" none.");
      com.citrix.mdx.plugins.k.getPlugin().Info("MDX-PolicyManager", ((StringBuilder)localObject1).toString());
      long l = System.currentTimeMillis() / i;
      if (l != h)
      {
        h = l;
        h.g();
        f.printPolicies();
      }
      m = paramArrayOfK.length;
      localObject1 = null;
      j = k;
    }
    for (;;)
    {
      Object localObject2 = localObject1;
      if (j < m)
      {
        localObject2 = paramArrayOfK[j];
        if (localObject2 != null)
        {
          localObject2 = ((com.citrix.mdx.h.k)localObject2).a(paramContext, localList);
          localObject1 = localObject2;
          if (localObject2 == null) {}
        }
      }
      else
      {
        return localObject2;
        localObject2 = localList.iterator();
        j = 0;
        Object localObject3;
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (Integer)((Iterator)localObject2).next();
          if (j != 0) {
            ((StringBuilder)localObject1).append(", ");
          }
          j = 1;
          ((StringBuilder)localObject1).append(localObject3);
        }
        ((StringBuilder)localObject1).append('\n');
        localObject2 = localList.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (Integer)((Iterator)localObject2).next();
          ((StringBuilder)localObject1).append("  ").append(localObject3);
          k.a localA = com.citrix.mdx.h.m.a(((Integer)localObject3).intValue());
          if (localA != null)
          {
            j = com.citrix.mdx.h.m.b(((Integer)localObject3).intValue());
            ((StringBuilder)localObject1).append(" = ").append(localA.name()).append(':');
            localObject3 = com.citrix.mdx.h.k.a(localA);
            if (localObject3 != null)
            {
              localObject3 = (Enum[])((com.citrix.mdx.h.k)localObject3).t.getEnumConstants();
              if ((localObject3 != null) && (localObject3.length > j)) {
                ((StringBuilder)localObject1).append(localObject3[j].name());
              }
            }
          }
          ((StringBuilder)localObject1).append('\n');
        }
        break;
      }
      j += 1;
    }
  }
  
  public static String a(String paramString)
  {
    return f.getString(paramString);
  }
  
  public static void a() {}
  
  public static void a(Context paramContext, String paramString)
  {
    f = p.a(paramContext, f, paramString);
    h.a(f);
    com.citrix.mdx.plugins.b.getInstance().checkEncryptionPoliciesAndSetErrorCode(paramContext);
    a();
  }
  
  public static void a(PolicyParser paramPolicyParser)
  {
    if (paramPolicyParser != null) {
      f = paramPolicyParser;
    }
  }
  
  public static void a(String paramString1, String paramString2)
  {
    f.setPolicyValue(paramString1, paramString2);
  }
  
  public static boolean a(Location paramLocation)
  {
    double d2 = D();
    double d1 = d2;
    if (d2 < 0.0D) {
      d1 = d2 * -1.0D;
    }
    com.citrix.mdx.plugins.k.getPlugin().Debug1("MDX-PolicyManager", " Radius =  " + d1 + " Center = " + E() + "Current Location = " + paramLocation + " distance = " + paramLocation.distanceTo(E()));
    return paramLocation.distanceTo(E()) >= d1;
  }
  
  public static boolean a(String paramString, boolean paramBoolean)
  {
    paramString = a(paramString);
    if (TextUtils.isEmpty(paramString)) {
      return paramBoolean;
    }
    if (!"false".equalsIgnoreCase(paramString)) {}
    for (paramBoolean = true;; paramBoolean = false) {
      return paramBoolean;
    }
  }
  
  public static boolean a(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (("true".equalsIgnoreCase(a("Disable" + paramString))) || ("NetworkAccessBlocked".equalsIgnoreCase(a(paramString)))) {}
    for (boolean bool = true;; bool = false)
    {
      if (bool)
      {
        if (paramBoolean1) {
          com.citrix.mdx.f.b.a(paramString);
        }
        if (paramBoolean2) {
          com.citrix.mdx.f.b.b(paramString);
        }
      }
      return bool;
    }
  }
  
  public static PolicyParser b()
  {
    return f;
  }
  
  public static boolean b(String paramString)
  {
    return !"false".equalsIgnoreCase(a(paramString));
  }
  
  public static String[] b(String paramString1, String paramString2)
  {
    return f.getList(paramString1, paramString2);
  }
  
  public static int c(String paramString)
  {
    String str = a(paramString);
    int j = 0;
    if (!TextUtils.isEmpty(str)) {}
    try
    {
      j = Integer.valueOf(str).intValue();
      return j;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      com.citrix.mdx.plugins.k.getPlugin().Error("MDX-PolicyManager", "Trying to read " + paramString + " as an integer, value = " + str, localNumberFormatException);
    }
    return 0;
  }
  
  public static g c()
  {
    try
    {
      if (e == null) {
        e = new g();
      }
      g localG = e;
      return localG;
    }
    finally {}
  }
  
  public static long d(String paramString)
  {
    String str = a(paramString);
    long l = 0L;
    if (!TextUtils.isEmpty(str)) {}
    try
    {
      l = Long.valueOf(str).longValue();
      return l;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      com.citrix.mdx.plugins.k.getPlugin().Error("MDX-PolicyManager", "Trying to read " + paramString + " as a long, value = " + str, localNumberFormatException);
    }
    return 0L;
  }
  
  public static String d(Context paramContext)
  {
    if (D() < 0.0D) {
      return null;
    }
    b = null;
    a = false;
    paramContext = paramContext.getPackageManager();
    Iterator localIterator = paramContext.getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (p.a().contains(localApplicationInfo.packageName))
      {
        a = true;
        g(localApplicationInfo.packageName);
        f(localApplicationInfo.loadLabel(paramContext).toString());
        return b;
      }
    }
    a = false;
    return b;
  }
  
  public static boolean d()
  {
    return f.isSTAConfigured();
  }
  
  public static double e(String paramString)
  {
    double d1 = 0.0D;
    String str = a(paramString);
    if (!TextUtils.isEmpty(str)) {}
    try
    {
      d1 = Double.parseDouble(str);
      return d1;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      com.citrix.mdx.plugins.k.getPlugin().Error("MDX-PolicyManager", "Trying to read " + paramString + " as an double, value = " + str);
    }
    return 0.0D;
  }
  
  public static boolean e()
  {
    boolean bool = false;
    if ((a("MvpnSessionRequired", false)) || (a("OnlineSessionRequired", false))) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean e(Context paramContext)
  {
    if (D() < 0.0D) {}
    while ("0".equals(Settings.Secure.getString(paramContext.getContentResolver(), "mock_location"))) {
      return false;
    }
    return true;
  }
  
  public static void f(String paramString)
  {
    b = paramString;
  }
  
  public static boolean f()
  {
    if ((f.mvpnPoliciesSetToDefault()) && (f.legacyNetworkPoliciesAvailable())) {}
    for (boolean bool = z(); (A()) && (bool) && (!d()); bool = a("MvpnRedirectWebTrafficWithSSO", true)) {
      return true;
    }
    return false;
  }
  
  public static boolean f(Context paramContext)
  {
    return MAMProviderClient.isLocationServicesEnabled(paramContext);
  }
  
  public static String g(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getApplicationContext().getPackageManager();
    try
    {
      paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
      if (paramContext != null)
      {
        paramContext = localPackageManager.getApplicationLabel(paramContext);
        return (String)paramContext;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
        continue;
        paramContext = Integer.valueOf(RHelper.get_resource("CITRIX_MAM_UNKNOWN"));
      }
    }
  }
  
  private static void g(String paramString)
  {
    c = paramString;
  }
  
  public static boolean g()
  {
    return f.isVpnEnabled();
  }
  
  public static long h()
  {
    long l2 = 1L;
    long l1 = 0L;
    try
    {
      long l3 = d("MaxOfflinePeriod");
      l1 = l3;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        com.citrix.mdx.plugins.k.getPlugin().Error("MDX-PolicyManager", "Error: Trying to read MaxOfflinePeriod as integer, value = " + a("MaxOfflinePeriod"));
      }
    }
    if (l1 < 1L)
    {
      l1 = l2;
      return l1 * g;
    }
  }
  
  public static boolean i()
  {
    return e("GeofenceRadius") != 0.0D;
  }
  
  public static boolean j()
  {
    return Build.VERSION.SDK_INT >= 23;
  }
  
  public static String k()
  {
    return c;
  }
  
  public static String l()
  {
    return b;
  }
  
  public static long m()
  {
    long l1 = 0L;
    try
    {
      long l2 = d("UpgradeGracePeriod");
      l1 = l2;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        com.citrix.mdx.plugins.k.getPlugin().Error("MDX-PolicyManager", "Error: Trying to read UpgradeGracePeriod as long, value = " + a("UpgradeGracePeriod"));
      }
    }
    return l1 * w;
  }
  
  public static long n()
  {
    long l1 = 0L;
    try
    {
      long l2 = d("ActivePollPeriod");
      l1 = l2;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        com.citrix.mdx.plugins.k.getPlugin().Error("MDX-PolicyManager", "Error: Trying to read ActivePollPeriod as long, value = " + a("ActivePollPeriod"));
      }
    }
    return l1 * u;
  }
  
  public static boolean o()
  {
    return !b("DisableLocalhostConnections");
  }
  
  public static boolean p()
  {
    return ("Disabled".equalsIgnoreCase(a("PrivateFileEncryptionEnum"))) && ("Disabled".equalsIgnoreCase(a("PublicFileEncryptionEnum")));
  }
  
  public static boolean q()
  {
    return "Blocked".equalsIgnoreCase(a("CutAndCopy"));
  }
  
  public static boolean r()
  {
    return "Blocked".equalsIgnoreCase(a("Paste"));
  }
  
  public static boolean s()
  {
    return "Blocked".equalsIgnoreCase(a("DocumentExchange"));
  }
  
  public static boolean t()
  {
    return "NetworkAccessBlocked".equalsIgnoreCase(a("NetworkAccess"));
  }
  
  public static void u()
  {
    String str = a("MvpnGatewayAddress");
    if (!TextUtils.isEmpty(str))
    {
      com.citrix.mdx.plugins.k.getPlugin().Detail("MDX-PolicyManager", "Policy Netscaler gateway set to " + str);
      IntuneUtils.gatewayURL = str;
    }
    try
    {
      IntuneUtils.gatewayHost = new URL(IntuneUtils.gatewayURL).getHost();
      if (!TextUtils.isEmpty(IntuneUtils.gatewayURL)) {
        com.citrix.mdx.plugins.o.putGatewayFQDN(IntuneUtils.gatewayURL);
      }
      com.citrix.mdx.plugins.k.getPlugin().Info("MDX-PolicyManager", "mVPN gateway = " + IntuneUtils.gatewayURL);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        com.citrix.mdx.plugins.k.getPlugin().Warning("MDX-PolicyManager", "Failed to convert MvpnGatewayAddress to value URL = " + IntuneUtils.gatewayURL);
      }
    }
  }
  
  private static boolean z()
  {
    return f.isLegacySecureBrowsePolicyEnabled();
  }
  
  public void a(Context paramContext, byte[] paramArrayOfByte)
  {
    f = p.a(paramContext, paramArrayOfByte);
    h.a(f);
    d = com.citrix.mdx.h.o.a(paramContext);
  }
  
  public void a(final n paramN, l paramL)
  {
    switch (4.b[m.g.a(paramL.a()).ordinal()])
    {
    default: 
      paramL.a(paramN);
      return;
    case 1: 
      paramN.i();
      return;
    case 2: 
      paramL = new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          com.citrix.mdx.plugins.m.onClickOptIn(paramN);
        }
      };
      DialogInterface.OnClickListener local2 = new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          com.citrix.mdx.plugins.m.onClickOptOut(paramN);
        }
      };
      b.c(com.citrix.mdx.plugins.m.ERROR_CODE_OPT_IN_TO_MANAGEMENT);
      paramN.a(new a(paramN.m(), RHelper.get_resource("CITRIX_MAM_OPT_IN_MSG"), RHelper.get_resource("ctxmam_dontaskagain"), RHelper.get_resource("CITRIX_MAM_AlertButtonOPTIN"), paramL, RHelper.get_resource("CITRIX_MAM_AlertButtonOPTOUT"), local2));
      return;
    }
    paramL = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        com.citrix.mdx.plugins.h.getPlugin().updatePolicies(paramN.m(), g.v());
        paramN.k();
      }
    };
    paramN.a(new a(paramN.m(), RHelper.get_resource("CITRIX_MAM_RESTART_APP_MSG"), RHelper.get_resource("CITRIX_MAM_AlertButtonOK"), paramL, 0, null));
  }
  
  public l b(Context paramContext)
  {
    if (i.k) {
      return null;
    }
    return c(paramContext);
  }
  
  public l c(Context paramContext)
  {
    Object localObject1 = com.citrix.mdx.plugins.m.getManagementState();
    if (b.a(com.citrix.mdx.plugins.m.ERROR_CODE_QUITTING_APP))
    {
      b.c(com.citrix.mdx.plugins.m.ERROR_CODE_QUITTING_APP);
      com.citrix.mdx.plugins.m.setQuitTime(SystemClock.elapsedRealtime());
    }
    com.citrix.mdx.plugins.k.getPlugin().Info("MDX-PolicyManager", "Checking " + localObject1 + " policies");
    Object localObject2;
    switch (4.a[localObject1.ordinal()])
    {
    default: 
      com.citrix.mdx.plugins.k.getPlugin().Error("MDX-PolicyManager", "Management State not handled = " + localObject1);
      localObject1 = null;
      localObject2 = null;
    }
    for (;;)
    {
      if (localObject2 != null) {
        localObject1 = a(paramContext, (com.citrix.mdx.h.k[])localObject2);
      }
      return localObject1;
      if (!TextUtils.isEmpty(com.citrix.mdx.plugins.m.getOptInProfileHash()))
      {
        localObject1 = new l(this, this.s, m.g.c);
        localObject2 = null;
      }
      else
      {
        localObject2 = com.citrix.mdx.h.k.a(k.a.d);
        com.citrix.mdx.h.k localK1 = com.citrix.mdx.h.k.a(k.a.e);
        localObject1 = null;
        localObject2 = new com.citrix.mdx.h.k[] { localObject2, localK1 };
        continue;
        localObject2 = com.citrix.mdx.h.k.a(k.a.e);
        localK1 = com.citrix.mdx.h.k.a(k.a.o);
        com.citrix.mdx.h.k localK2 = com.citrix.mdx.h.k.a(k.a.l);
        localObject1 = null;
        localObject2 = new com.citrix.mdx.h.k[] { localObject2, localK1, localK2 };
        continue;
        localObject1 = null;
        localObject2 = null;
        continue;
        localObject2 = com.citrix.mdx.h.k.a(k.a.d);
        localK1 = com.citrix.mdx.h.k.a(k.a.e);
        localK2 = com.citrix.mdx.h.k.a(k.a.l);
        localObject1 = null;
        localObject2 = new com.citrix.mdx.h.k[] { localObject2, localK1, localK2 };
        continue;
        localObject2 = com.citrix.mdx.h.k.a(k.a.a);
        localK1 = com.citrix.mdx.h.k.a(k.a.j);
        localK2 = com.citrix.mdx.h.k.a(k.a.h);
        com.citrix.mdx.h.k localK3 = com.citrix.mdx.h.k.a(k.a.e);
        com.citrix.mdx.h.k localK4 = com.citrix.mdx.h.k.a(k.a.p);
        com.citrix.mdx.h.k localK5 = com.citrix.mdx.h.k.a(k.a.l);
        com.citrix.mdx.h.k localK6 = com.citrix.mdx.h.k.a(k.a.m);
        localObject1 = null;
        localObject2 = new com.citrix.mdx.h.k[] { localObject2, localK1, localK2, localK3, localK4, localK5, localK6 };
        continue;
        localObject2 = com.citrix.mdx.h.k.a(k.a.b);
        localK1 = com.citrix.mdx.h.k.a(k.a.c);
        localK2 = com.citrix.mdx.h.k.a(k.a.f);
        localObject1 = null;
        localObject2 = new com.citrix.mdx.h.k[] { localObject2, localK1, localK2 };
        continue;
        localObject2 = com.citrix.mdx.h.k.a(k.a.a);
        localK1 = com.citrix.mdx.h.k.a(k.a.b);
        localK2 = com.citrix.mdx.h.k.a(k.a.i);
        localK3 = com.citrix.mdx.h.k.a(k.a.c);
        localK4 = com.citrix.mdx.h.k.a(k.a.d);
        localK5 = com.citrix.mdx.h.k.a(k.a.e);
        localK6 = com.citrix.mdx.h.k.a(k.a.h);
        com.citrix.mdx.h.k localK7 = com.citrix.mdx.h.k.a(k.a.g);
        com.citrix.mdx.h.k localK8 = com.citrix.mdx.h.k.a(k.a.m);
        com.citrix.mdx.h.k localK9 = com.citrix.mdx.h.k.a(k.a.j);
        com.citrix.mdx.h.k localK10 = com.citrix.mdx.h.k.a(k.a.k);
        com.citrix.mdx.h.k localK11 = com.citrix.mdx.h.k.a(k.a.l);
        com.citrix.mdx.h.k localK12 = com.citrix.mdx.h.k.a(k.a.n);
        localObject1 = null;
        localObject2 = new com.citrix.mdx.h.k[] { localObject2, localK1, localK2, localK3, localK4, localK5, localK6, localK7, localK8, localK9, localK10, localK11, localK12 };
      }
    }
  }
}
