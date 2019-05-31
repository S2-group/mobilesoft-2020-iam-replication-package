package com.threatmetrix.TrustDefender;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class g
{
  private static final String a = w.a(g.class);
  
  g() {}
  
  final class a
  {
    private ApplicationInfo b = null;
    
    a(Context paramContext)
    {
      if ((g.d.f() != null) && (g.d.g() != null)) {
        this.b = paramContext.getApplicationInfo();
      }
    }
    
    final String a()
    {
      if (this.b != null) {
        return this.b.packageName;
      }
      return "";
    }
    
    final String b()
    {
      if (this.b != null) {
        return this.b.sourceDir;
      }
      return "";
    }
    
    final String c()
    {
      if (this.b != null) {
        return this.b.nativeLibraryDir;
      }
      return "";
    }
  }
  
  static final class b
  {
    static long a = Long.MAX_VALUE;
    static String b;
    static String c;
    static String d;
    static String e;
    static String f;
    static String g;
    static String h;
    static String i;
    static String j;
    static String k;
    static String l;
    static String m;
    private static Class<?> n;
    
    static
    {
      Class localClass = at.b("android.os.Build");
      n = localClass;
      if (at.a(localClass, "TIME") != null) {
        a = Build.TIME;
      }
      if (at.a(n, "TYPE") != null) {
        b = Build.TYPE;
      }
      if (at.a(n, "TAGS") != null) {
        c = Build.TAGS;
      }
      if (at.a(n, "HOST") != null) {
        d = Build.HOST;
      }
      if (at.a(n, "BRAND") != null) {
        e = Build.BRAND;
      }
      if (at.a(n, "USER") != null) {
        f = Build.USER;
      }
      if (at.a(n, "ID") != null) {
        g = Build.ID;
      }
      if (at.a(n, "SERIAL") != null) {
        h = Build.SERIAL;
      }
      if (at.a(n, "DEVICE") != null) {
        i = Build.DEVICE;
      }
      if (at.a(n, "MODEL") != null) {
        j = Build.MODEL;
      }
      if (at.a(n, "DISPLAY") != null) {
        k = Build.DISPLAY;
      }
      if (at.a(n, "PRODUCT") != null) {
        l = Build.PRODUCT;
      }
      if (at.a(n, "MANUFACTURER") != null) {
        m = Build.MANUFACTURER;
      }
    }
    
    static final class a
    {
      static String a;
      static String b;
      static int c = -1;
      private static Class<?> d;
      
      static
      {
        Class localClass = at.b("android.os.Build$VERSION");
        d = localClass;
        if (at.a(localClass, "RELEASE") != null) {
          a = Build.VERSION.RELEASE;
        }
        if (at.a(d, "SDK_INT") != null) {
          c = Build.VERSION.SDK_INT;
        }
        if (at.a(d, "CODENAME") != null) {
          b = Build.VERSION.CODENAME;
        }
      }
    }
    
    static final class b
    {
      static int a = 8;
      static int b = 9;
      static int c = 10;
      static int d = 11;
      static int e = 12;
      static int f = 13;
      static int g = 14;
      static int h = 15;
      static int i = 16;
      static int j = 17;
      static int k = 18;
      static int l = 19;
      static int m = 20;
      static int n = 21;
      static int o = 22;
      private static Class<?> p;
      
      static
      {
        Class localClass = at.b("android.os.Build$VERSION_CODES");
        p = localClass;
        if (at.a(localClass, "FROYO") != null) {
          a = 8;
        }
        if (at.a(p, "GINGERBREAD") != null) {
          b = 9;
        }
        if (at.a(p, "GINGERBREAD_MR1") != null) {
          c = 10;
        }
        if (at.a(p, "HONEYCOMB") != null) {
          d = 11;
        }
        if (at.a(p, "HONEYCOMB_MR1") != null) {
          e = 12;
        }
        if (at.a(p, "HONEYCOMB_MR2") != null) {
          f = 13;
        }
        if (at.a(p, "ICE_CREAM_SANDWICH") != null) {
          g = 14;
        }
        if (at.a(p, "ICE_CREAM_SANDWICH_MR1") != null) {
          h = 15;
        }
        if (at.a(p, "JELLY_BEAN") != null) {
          i = 16;
        }
        if (at.a(p, "JELLY_BEAN_MR1") != null) {
          j = 17;
        }
        if (at.a(p, "JELLY_BEAN_MR2") != null) {
          k = 18;
        }
        if (at.a(p, "KITKAT") != null) {
          l = 19;
        }
        if (at.a(p, "KITKAT_WATCH") != null) {
          m = 20;
        }
        if (at.a(p, "LOLLIPOP") != null) {
          n = 21;
        }
        if (at.a(p, "LOLLIPOP_MR1") != null) {
          o = 22;
        }
      }
    }
  }
  
  static final class c
  {
    static Method a;
    private static Class<?> b;
    
    static
    {
      Class localClass = at.b("android.app.admin.DevicePolicyManager");
      b = localClass;
      a = at.a(localClass, "getStorageEncryptionStatus", new Class[0]);
    }
  }
  
  static final class d
  {
    static final int a = 1;
    static final int b = 128;
    private static Class<?> c;
    private static Class<?> d;
    private static Class<?> e = at.b("android.content.pm.PackageManager");
    private static Class<?> f = at.b("android.content.pm.PackageInfo");
    private static Method g = at.a(e, "checkPermission", new Class[] { String.class, String.class });
    private static Field h = at.a(f, "versionCode");
    private static Field i = at.a(f, "versionName");
    
    static
    {
      c = at.b("android.content.pm.ApplicationInfo");
      d = at.b("android.content.pm.PackageItemInfo");
      Class localClass = e;
    }
  }
  
  static final class e
  {
    private static Class<?> a;
    private static Class<?> b = at.b("android.content.SharedPreferences");
    private static Method c;
    private static Method d;
    private static Method e;
    private static Method f = at.a(a, "putString", new Class[] { String.class, String.class });
    private static Method g;
    private static Method h;
    private static Method i = at.a(a, "apply", new Class[0]);
    
    static
    {
      a = at.b("android.content.SharedPreferences$Editor");
      d = at.a(b, "getInt", new Class[] { String.class, Integer.TYPE });
      e = at.a(b, "getLong", new Class[] { String.class, Long.TYPE });
      c = at.a(b, "getString", new Class[] { String.class, String.class });
      h = at.a(a, "putInt", new Class[] { String.class, Integer.TYPE });
      g = at.a(a, "putLong", new Class[] { String.class, Long.TYPE });
    }
  }
  
  static final class f
  {
    private static Class<?> a = at.b("android.location.Criteria");
    private static Class<?> b = at.b("android.location.Location");
    private static Class<?> c = at.b("android.location.LocationProvider");
    private static Class<?> d = at.b("android.location.LocationListener");
    private static Method e = at.a(a, "setAccuracy", new Class[] { Integer.TYPE });
    private static Method f = at.a(a, "setAltitudeRequired", new Class[] { Boolean.TYPE });
    private static Method g = at.a(a, "setBearingAccuracy", new Class[] { Integer.TYPE });
    private static Method h = at.a(a, "setCostAllowed", new Class[] { Boolean.TYPE });
    private static Method i = at.a(a, "setSpeedAccuracy", new Class[] { Integer.TYPE });
    private static Method j = at.a(a, "setSpeedRequired", new Class[] { Boolean.TYPE });
    private static Method k = at.a(a, "setVerticalAccuracy", new Class[] { Integer.TYPE });
    private static Method l = at.a(a, "setPowerRequirement", new Class[] { Integer.TYPE });
    private static Method m = at.a(b, "getTime", new Class[0]);
    private static Method n = at.a(b, "getProvider", new Class[0]);
    private static Method o = at.a(b, "getAccuracy", new Class[0]);
    private static Method p = at.a(b, "getLatitude", new Class[0]);
    private static Method q = at.a(b, "getLongitude", new Class[0]);
    private static Field r = at.a(a, "NO_REQUIREMENT");
    private static Field s = at.a(a, "POWER_LOW");
    private static Field t = at.a(a, "ACCURACY_LOW");
    private static Field u = at.a(a, "ACCURACY_COARSE");
    private static Field v = at.a(c, "AVAILABLE");
    private static Field w = at.a(c, "TEMPORARILY_UNAVAILABLE");
    private static Field x = at.a(c, "OUT_OF_SERVICE");
    
    static boolean a()
    {
      return (e != null) && (f != null) && (g != null) && (h != null) && (i != null) && (j != null) && (k != null) && (l != null) && (r != null) && (s != null) && (t != null) && (u != null);
    }
    
    static boolean b()
    {
      return (d != null) && (m != null) && (n != null) && (p != null) && (q != null) && (v != null) && (w != null) && (x != null);
    }
  }
  
  static final class g
  {
    private static Class<?> a = at.b("android.net.ConnectivityManager");
    private static Class<?> b = at.b("android.net.NetworkInfo");
    private static Class<?> c;
    private static Class<?> d = at.b("android.net.wifi.WifiInfo");
    private static Class<?> e = at.b("android.net.wifi.WifiManager");
    private static Method f;
    private static Method g;
    private static Method h;
    private static Method i;
    private static Method j;
    private static Method k;
    private static Method l;
    private static Method m;
    private static Method n;
    private static Field o;
    private static Field p;
    private static Field q;
    private static Field r;
    private static Field s;
    private static Field t = at.a(c, "CONNECTED");
    private static Field u;
    
    static
    {
      c = at.b("android.net.NetworkInfo$State");
      f = at.a(a, "getActiveNetworkInfo", new Class[0]);
      h = at.a(b, "getState", new Class[0]);
      i = at.a(b, "getType", new Class[0]);
      j = at.a(b, "getExtraInfo", new Class[0]);
      k = at.a(d, "getBSSID", new Class[0]);
      l = at.a(d, "getSSID", new Class[0]);
      m = at.a(d, "getRssi", new Class[0]);
      n = at.a(e, "getConnectionInfo", new Class[0]);
      g = at.a(b, "isConnectedOrConnecting", new Class[0]);
      o = at.a(a, "CONNECTIVITY_ACTION");
      p = at.a(a, "TYPE_MOBILE");
      q = at.a(a, "TYPE_WIFI");
      r = at.a(a, "TYPE_BLUETOOTH");
      s = at.a(a, "TYPE_ETHERNET");
      u = at.a(e, "NETWORK_STATE_CHANGED_ACTION");
    }
    
    static boolean a()
    {
      return (f != null) && (g != null);
    }
    
    static boolean b()
    {
      boolean bool1;
      if ((o != null) && (t != null) && (h != null) && (j != null) && (i != null) && (p != null) && (q != null)) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      boolean bool2 = bool1;
      if (g.b.a.c >= g.b.b.f)
      {
        if ((bool1) && (s != null) && (r != null)) {
          return true;
        }
        bool2 = false;
      }
      return bool2;
    }
    
    static boolean c()
    {
      return (u != null) && (t != null) && (k != null) && (l != null) && (m != null) && (h != null) && (j != null);
    }
    
    static boolean d()
    {
      return (n != null) && (k != null) && (l != null) && (m != null);
    }
  }
  
  final class h
  {
    private PackageInfo b = null;
    
    h(Context paramContext, String paramString, int paramInt)
    {
      if ((g.d.a() != null) && (g.d.b() != null)) {}
      try
      {
        this.b = paramContext.getPackageManager().getPackageInfo(paramString, paramInt);
        return;
      }
      catch (Exception this$1)
      {
        w.c(g.a(), g.this.getMessage());
        return;
        g.a();
        return;
        g.a();
        return;
        return;
      }
      catch (PackageManager.NameNotFoundException this$1)
      {
        for (;;) {}
      }
      catch (SecurityException this$1)
      {
        for (;;) {}
      }
    }
    
    final int a()
    {
      if ((g.d.c() != null) && (this.b != null)) {
        return this.b.versionCode;
      }
      return -1;
    }
    
    final String b()
    {
      if ((g.d.d() != null) && (this.b != null)) {
        return this.b.versionName;
      }
      return null;
    }
  }
  
  final class i
  {
    private PackageManager b = null;
    
    i(Context paramContext)
    {
      if (g.d.b() != null) {}
      try
      {
        this.b = paramContext.getPackageManager();
        return;
      }
      catch (Exception this$1)
      {
        w.c(g.a(), g.this.getMessage());
        return;
        g.a();
        return;
        return;
      }
      catch (SecurityException this$1)
      {
        for (;;) {}
      }
    }
    
    /* Error */
    final java.util.ArrayList<String> a()
    {
      // Byte code:
      //   0: new 51	java/util/ArrayList
      //   3: dup
      //   4: invokespecial 52	java/util/ArrayList:<init>	()V
      //   7: astore_1
      //   8: invokestatic 29	com/threatmetrix/TrustDefender/g$d:b	()Ljava/lang/Class;
      //   11: ifnull +103 -> 114
      //   14: invokestatic 55	com/threatmetrix/TrustDefender/g$d:f	()Ljava/lang/Class;
      //   17: ifnull +97 -> 114
      //   20: aload_0
      //   21: getfield 24	com/threatmetrix/TrustDefender/g$i:b	Landroid/content/pm/PackageManager;
      //   24: ifnull +90 -> 114
      //   27: aload_0
      //   28: getfield 24	com/threatmetrix/TrustDefender/g$i:b	Landroid/content/pm/PackageManager;
      //   31: iconst_0
      //   32: invokevirtual 61	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   35: invokeinterface 67 1 0
      //   40: astore_2
      //   41: aload_2
      //   42: invokeinterface 73 1 0
      //   47: ifeq +67 -> 114
      //   50: aload_2
      //   51: invokeinterface 77 1 0
      //   56: checkcast 79	android/content/pm/ApplicationInfo
      //   59: astore_3
      //   60: aload_3
      //   61: getfield 83	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
      //   64: ldc 85
      //   66: invokevirtual 91	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   69: ifne -28 -> 41
      //   72: aload_3
      //   73: getfield 83	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
      //   76: ldc 93
      //   78: invokevirtual 91	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   81: ifne -40 -> 41
      //   84: aload_1
      //   85: aload_3
      //   86: getfield 83	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
      //   89: invokevirtual 97	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   92: pop
      //   93: goto -52 -> 41
      //   96: astore_2
      //   97: invokestatic 38	com/threatmetrix/TrustDefender/g:a	()Ljava/lang/String;
      //   100: aload_2
      //   101: invokevirtual 41	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   104: invokestatic 47	com/threatmetrix/TrustDefender/w:c	(Ljava/lang/String;Ljava/lang/String;)V
      //   107: goto +7 -> 114
      //   110: invokestatic 38	com/threatmetrix/TrustDefender/g:a	()Ljava/lang/String;
      //   113: pop
      //   114: aload_1
      //   115: ldc 85
      //   117: invokevirtual 97	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   120: pop
      //   121: aload_1
      //   122: ldc 93
      //   124: invokevirtual 97	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   127: pop
      //   128: aload_1
      //   129: areturn
      //   130: astore_2
      //   131: goto -21 -> 110
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	134	0	this	i
      //   7	122	1	localArrayList	java.util.ArrayList
      //   40	11	2	localIterator	java.util.Iterator
      //   96	5	2	localException	Exception
      //   130	1	2	localSecurityException	SecurityException
      //   59	27	3	localApplicationInfo	ApplicationInfo
      // Exception table:
      //   from	to	target	type
      //   27	41	96	java/lang/Exception
      //   41	93	96	java/lang/Exception
      //   27	41	130	java/lang/SecurityException
      //   41	93	130	java/lang/SecurityException
    }
    
    final boolean a(String paramString, int paramInt)
    {
      if ((g.d.b() != null) && (g.d.a() != null) && (this.b != null)) {}
      try
      {
        this.b.getPackageInfo(paramString, paramInt);
        return true;
      }
      catch (Exception paramString)
      {
        w.c(g.a(), paramString.getMessage());
        break label56;
        g.a();
        break label56;
        g.a();
        return false;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        for (;;) {}
      }
      catch (SecurityException paramString)
      {
        label56:
        for (;;) {}
      }
    }
    
    final boolean a(String paramString1, String paramString2)
    {
      if ((g.d.e() != null) && (this.b != null)) {}
      try
      {
        int i = this.b.checkPermission(paramString1, paramString2);
        return i == 0;
      }
      catch (Exception paramString1)
      {
        w.c(g.a(), paramString1.getMessage());
        return false;
        g.a();
        return false;
      }
      catch (SecurityException paramString1)
      {
        for (;;) {}
      }
    }
  }
  
  static final class j
  {
    private static Class<?> a;
    private static Method b;
    private static Method c = at.a(a, "isScreenOn", new Class[0]);
    
    static
    {
      Class localClass = at.b("android.os.PowerManager");
      a = localClass;
      b = at.a(localClass, "isInteractive", new Class[0]);
    }
    
    static boolean a()
    {
      return (a != null) && (c != null);
    }
  }
  
  static final class k
  {
    private static Class<?> a;
    private static Method b;
    private static Field c = at.a(a, "ANDROID_ID");
    private static Field d = at.a(a, "ALLOW_MOCK_LOCATION");
    
    static
    {
      Class localClass = at.b("android.provider.Settings$Secure");
      a = localClass;
      b = at.a(localClass, "getString", new Class[] { ContentResolver.class, String.class });
    }
    
    static String a(ContentResolver paramContentResolver, String paramString)
    {
      if ((paramContentResolver != null) && (!ai.e(paramString))) {
        if (b == null) {
          return null;
        }
      }
      try
      {
        if (("ANDROID_ID".equals(paramString)) && (c != null)) {
          return Settings.Secure.getString(paramContentResolver, "android_id");
        }
        if (("ALLOW_MOCK_LOCATION".equals(paramString)) && (d != null))
        {
          paramContentResolver = Settings.Secure.getString(paramContentResolver, "mock_location");
          return paramContentResolver;
        }
      }
      catch (Exception paramContentResolver)
      {
        w.c(g.a(), paramContentResolver.getMessage());
        return null;
        g.a();
        return null;
        return null;
      }
      catch (SecurityException paramContentResolver)
      {
        for (;;) {}
      }
    }
  }
  
  final class l
  {
    private SharedPreferences b = null;
    private SharedPreferences.Editor c = null;
    
    l(Context paramContext, String paramString, int paramInt)
    {
      if (g.e.a() != null) {
        this.b = paramContext.getSharedPreferences(paramString, 0);
      }
      if (g.e.b() != null) {
        this.c = this.b.edit();
      }
    }
    
    final int a(String paramString, int paramInt)
    {
      if ((g.e.c() != null) && (this.b != null)) {
        return this.b.getInt(paramString, 0);
      }
      return 0;
    }
    
    final long a(String paramString, long paramLong)
    {
      if ((g.e.d() != null) && (this.b != null)) {
        return this.b.getLong(paramString, 0L);
      }
      return 0L;
    }
    
    final String a(String paramString1, String paramString2)
    {
      if ((g.e.e() != null) && (this.b != null)) {
        return this.b.getString(paramString1, paramString2);
      }
      return paramString2;
    }
    
    final void a()
    {
      if ((g.e.i() != null) && (this.c != null)) {
        this.c.apply();
      }
    }
    
    final void b(String paramString, int paramInt)
    {
      if ((g.e.f() != null) && (this.c != null)) {
        this.c.putInt(paramString, paramInt);
      }
    }
    
    final void b(String paramString, long paramLong)
    {
      if ((g.e.g() != null) && (this.c != null)) {
        this.c.putLong(paramString, paramLong);
      }
    }
    
    final void b(String paramString1, String paramString2)
    {
      if ((g.e.h() != null) && (this.c != null)) {
        this.c.putString(paramString1, paramString2);
      }
    }
  }
  
  static final class m
  {
    private static Class<?> a;
    private static Method b;
    private static Method c = at.a(a, "elapsedRealtime", new Class[0]);
    
    static
    {
      Class localClass = at.b("android.os.SystemClock");
      a = localClass;
      b = at.a(localClass, "uptimeMillis", new Class[0]);
    }
    
    static long a()
    {
      if (b != null) {
        return SystemClock.uptimeMillis();
      }
      return 0L;
    }
    
    static long b()
    {
      if (c != null) {
        return SystemClock.elapsedRealtime();
      }
      return 0L;
    }
  }
  
  static final class n
  {
    private static Method A;
    private static Method B;
    private static Method C;
    private static Method D;
    private static Method E;
    private static Method F;
    private static Method G;
    private static Method H;
    private static Method I;
    private static Method J;
    private static Method K;
    private static Method L;
    private static Method M;
    private static Method N;
    private static Method O;
    private static Method P;
    private static Method Q;
    private static Method R;
    private static Method S;
    private static Method T;
    private static Method U;
    private static Method V;
    private static Method W;
    private static Field X;
    private static Field Y;
    private static Field Z;
    private static Class<?> a = at.b("android.telephony.TelephonyManager");
    private static Class<?> b = at.b("android.telephony.CellIdentityCdma");
    private static Class<?> c = at.b("android.telephony.CellIdentityGsm");
    private static Class<?> d = at.b("android.telephony.CellIdentityLte");
    private static Class<?> e = at.b("android.telephony.CellIdentityWcdma");
    private static Class<?> f = at.b("android.telephony.CellInfo");
    private static Class<?> g = at.b("android.telephony.CellInfoCdma");
    private static Class<?> h = at.b("android.telephony.CellInfoGsm");
    private static Class<?> i = at.b("android.telephony.CellInfoLte");
    private static Class<?> j = at.b("android.telephony.CellInfoWcdma");
    private static Class<?> k = at.b("android.telephony.CellSignalStrength");
    private static Class<?> l = at.b("android.telephony.NeighboringCellInfo");
    private static Class<?> m = at.b("android.telephony.CellLocation");
    private static Class<?> n = at.b("android.telephony.SubscriptionInfo");
    private static Class<?> o = at.b("android.telephony.SubscriptionManager");
    private static Class<?> p = at.b("android.telephony.cdma.CdmaCellLocation");
    private static Class<?> q = at.b("android.telephony.gsm.GsmCellLocation");
    private static Method r;
    private static Method s;
    private static Method t;
    private static Method u;
    private static Method v = at.a(a, "getCellLocation", new Class[0]);
    private static Method w;
    private static Method x;
    private static Method y;
    private static Method z;
    
    static boolean a()
    {
      s = at.a(a, "getAllCellInfo", new Class[0]);
      V = at.a(f, "isRegistered", new Class[0]);
      return (a != null) && (k != null) && (f != null) && (V != null) && (s != null);
    }
    
    static boolean b()
    {
      r = at.a(a, "getNetworkOperator", new Class[0]);
      t = at.a(a, "getNetworkCountryIso", new Class[0]);
      u = at.b(a, "getNetworkOperatorName", new Class[0]);
      return (a != null) && (r != null) && (t != null) && (u != null);
    }
    
    static boolean c()
    {
      B = at.a(p, "getSystemId", new Class[0]);
      C = at.a(p, "getBaseStationId", new Class[0]);
      D = at.a(p, "getBaseStationLatitude", new Class[0]);
      E = at.a(p, "getBaseStationLongitude", new Class[0]);
      return (m != null) && (v != null) && (B != null) && (C != null) && (D != null) && (E != null);
    }
    
    static boolean d()
    {
      w = at.a(q, "getLac", new Class[0]);
      x = at.a(q, "getCid", new Class[0]);
      y = at.a(q, "getPsc", new Class[0]);
      return (m != null) && (v != null) && (x != null) && (w != null) && (y != null);
    }
    
    static boolean e()
    {
      z = at.a(l, "getCid", new Class[0]);
      A = at.a(l, "getRssi", new Class[0]);
      return (l != null) && (z != null) && (A != null);
    }
    
    static boolean f()
    {
      G = at.a(n, "getSimSlotIndex", new Class[0]);
      H = at.a(n, "getCarrierName", new Class[0]);
      I = at.a(n, "getDisplayName", new Class[0]);
      J = at.a(n, "getIccId", new Class[0]);
      K = at.a(n, "getNumber", new Class[0]);
      L = at.a(n, "getCountryIso", new Class[0]);
      M = at.a(n, "getDataRoaming", new Class[0]);
      F = at.a(o, "getActiveSubscriptionInfoList", new Class[0]);
      return (o != null) && (n != null) && (G != null) && (H != null) && (I != null) && (J != null) && (K != null) && (L != null) && (M != null) && (F != null);
    }
    
    static boolean g()
    {
      T = at.b(j, "getCellSignalStrength", new Class[0]);
      U = at.b(j, "getCellIdentity", new Class[0]);
      return (e != null) && (T != null) && (U != null);
    }
    
    static boolean h()
    {
      P = at.b(h, "getCellSignalStrength", new Class[0]);
      Q = at.b(h, "getCellIdentity", new Class[0]);
      return (c != null) && (P != null) && (Q != null);
    }
    
    static boolean i()
    {
      R = at.b(i, "getCellSignalStrength", new Class[0]);
      S = at.b(i, "getCellIdentity", new Class[0]);
      return (d != null) && (R != null) && (S != null);
    }
    
    static boolean j()
    {
      N = at.b(g, "getCellSignalStrength", new Class[0]);
      O = at.b(g, "getCellIdentity", new Class[0]);
      return (b != null) && (N != null) && (O != null);
    }
    
    static boolean k()
    {
      W = at.a(a, "getDataState", new Class[0]);
      X = at.a(a, "DATA_CONNECTED");
      Y = at.a(a, "DATA_CONNECTING");
      Z = at.a(a, "DATA_SUSPENDED");
      return (a != null) && (W != null) && (X != null) && (Y != null) && (Z != null);
    }
  }
  
  static final class o
  {
    private static Class<?> a = at.b("android.webkit.WebView");
    private static Class<?> b = at.b("android.webkit.WebViewClient");
    private static Class<?> c = at.b("android.webkit.WebSettings");
    private static Class<?> d = at.b("android.webkit.WebSettings$PluginState");
    private static Class<?> e = at.b("android.webkit.WebChromeClient");
    private static Class<?> f;
    private static Method g;
    private static Method h = at.a(a, "destroy", new Class[0]);
    private static Method i = at.a(a, "loadUrl", new Class[] { String.class });
    private static Method j = at.a(a, "loadData", new Class[] { String.class, String.class, String.class });
    private static Method k = at.a(a, "getSettings", new Class[0]);
    private static Method l = at.a(a, "setWebViewClient", new Class[] { b });
    private static Method m = at.a(a, "setWebChromeClient", new Class[] { e });
    private static Method n = at.a(c, "getUserAgentString", new Class[0]);
    private static Method o = at.a(c, "setJavaScriptEnabled", new Class[] { Boolean.TYPE });
    private static Field p = at.a(d, "ON");
    
    static
    {
      Class localClass = at.b("android.webkit.JsResult");
      f = localClass;
      g = at.a(localClass, "confirm", new Class[0]);
    }
    
    static boolean a()
    {
      return (b != null) && (e != null) && (g != null) && (h != null) && (i != null) && (j != null) && (k != null) && (l != null) && (m != null) && (n != null) && (o != null) && (p != null);
    }
  }
}