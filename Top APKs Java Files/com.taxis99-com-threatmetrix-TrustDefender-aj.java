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

class aj
{
  private static final String a = az.a(aj.class);
  
  aj() {}
  
  final class a
  {
    private ApplicationInfo b = null;
    
    a(Context paramContext)
    {
      if ((aj.d.f() != null) && (aj.d.g() != null)) {
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
    static String b = null;
    static String c = null;
    static String d = null;
    static String e = null;
    static String f = null;
    static String g = null;
    static String h = null;
    static String i = null;
    static String j = null;
    static String k = null;
    static String l = null;
    static String m = null;
    private static Class<?> n;
    
    static
    {
      Class localClass = ad.a("android.os.Build");
      n = localClass;
      if (ad.a(localClass, "TIME") != null) {
        a = Build.TIME;
      }
      if (ad.a(n, "TYPE") != null) {
        b = Build.TYPE;
      }
      if (ad.a(n, "TAGS") != null) {
        c = Build.TAGS;
      }
      if (ad.a(n, "HOST") != null) {
        d = Build.HOST;
      }
      if (ad.a(n, "BRAND") != null) {
        e = Build.BRAND;
      }
      if (ad.a(n, "USER") != null) {
        f = Build.USER;
      }
      if (ad.a(n, "ID") != null) {
        g = Build.ID;
      }
      if (ad.a(n, "SERIAL") != null) {
        h = Build.SERIAL;
      }
      if (ad.a(n, "DEVICE") != null) {
        i = Build.DEVICE;
      }
      if (ad.a(n, "MODEL") != null) {
        j = Build.MODEL;
      }
      if (ad.a(n, "DISPLAY") != null) {
        k = Build.DISPLAY;
      }
      if (ad.a(n, "PRODUCT") != null) {
        l = Build.PRODUCT;
      }
      if (ad.a(n, "MANUFACTURER") != null) {
        m = Build.MANUFACTURER;
      }
    }
    
    static final class a
    {
      static String a = null;
      static String b = null;
      static int c = -1;
      private static Class<?> d;
      
      static
      {
        Class localClass = ad.a("android.os.Build$VERSION");
        d = localClass;
        if (ad.a(localClass, "RELEASE") != null) {
          a = Build.VERSION.RELEASE;
        }
        if (ad.a(d, "SDK_INT") != null) {
          c = Build.VERSION.SDK_INT;
        }
        if (ad.a(d, "CODENAME") != null) {
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
        Class localClass = ad.a("android.os.Build$VERSION_CODES");
        p = localClass;
        if (ad.a(localClass, "FROYO") != null) {
          a = 8;
        }
        if (ad.a(p, "GINGERBREAD") != null) {
          b = 9;
        }
        if (ad.a(p, "GINGERBREAD_MR1") != null) {
          c = 10;
        }
        if (ad.a(p, "HONEYCOMB") != null) {
          d = 11;
        }
        if (ad.a(p, "HONEYCOMB_MR1") != null) {
          e = 12;
        }
        if (ad.a(p, "HONEYCOMB_MR2") != null) {
          f = 13;
        }
        if (ad.a(p, "ICE_CREAM_SANDWICH") != null) {
          g = 14;
        }
        if (ad.a(p, "ICE_CREAM_SANDWICH_MR1") != null) {
          h = 15;
        }
        if (ad.a(p, "JELLY_BEAN") != null) {
          i = 16;
        }
        if (ad.a(p, "JELLY_BEAN_MR1") != null) {
          j = 17;
        }
        if (ad.a(p, "JELLY_BEAN_MR2") != null) {
          k = 18;
        }
        if (ad.a(p, "KITKAT") != null) {
          l = 19;
        }
        if (ad.a(p, "KITKAT_WATCH") != null) {
          m = 20;
        }
        if (ad.a(p, "LOLLIPOP") != null) {
          n = 21;
        }
        if (ad.a(p, "LOLLIPOP_MR1") != null) {
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
      Class localClass = ad.a("android.app.admin.DevicePolicyManager");
      b = localClass;
      a = ad.a(localClass, "getStorageEncryptionStatus", new Class[0]);
    }
  }
  
  static final class d
  {
    static final int a = 1;
    static final int b = 128;
    private static Class<?> c;
    private static Class<?> d;
    private static Class<?> e = ad.a("android.content.pm.PackageManager");
    private static Class<?> f = ad.a("android.content.pm.PackageInfo");
    private static Method g = ad.a(e, "checkPermission", new Class[] { String.class, String.class });
    private static Field h = ad.a(f, "versionCode");
    private static Field i = ad.a(f, "versionName");
    
    static
    {
      c = ad.a("android.content.pm.ApplicationInfo");
      d = ad.a("android.content.pm.PackageItemInfo");
      Class localClass = e;
    }
  }
  
  static final class e
  {
    private static Class<?> a;
    private static Class<?> b = ad.a("android.content.SharedPreferences");
    private static Method c;
    private static Method d;
    private static Method e;
    private static Method f = ad.a(a, "putString", new Class[] { String.class, String.class });
    private static Method g;
    private static Method h;
    private static Method i = ad.a(a, "apply", new Class[0]);
    
    static
    {
      a = ad.a("android.content.SharedPreferences$Editor");
      d = ad.a(b, "getInt", new Class[] { String.class, Integer.TYPE });
      e = ad.a(b, "getLong", new Class[] { String.class, Long.TYPE });
      c = ad.a(b, "getString", new Class[] { String.class, String.class });
      h = ad.a(a, "putInt", new Class[] { String.class, Integer.TYPE });
      g = ad.a(a, "putLong", new Class[] { String.class, Long.TYPE });
    }
  }
  
  static final class f
  {
    private static Class<?> a = ad.a("android.location.Criteria");
    private static Class<?> b = ad.a("android.location.Location");
    private static Class<?> c = ad.a("android.location.LocationProvider");
    private static Class<?> d = ad.a("android.location.LocationListener");
    private static Method e = ad.a(a, "setAccuracy", new Class[] { Integer.TYPE });
    private static Method f = ad.a(a, "setAltitudeRequired", new Class[] { Boolean.TYPE });
    private static Method g = ad.a(a, "setBearingAccuracy", new Class[] { Integer.TYPE });
    private static Method h = ad.a(a, "setCostAllowed", new Class[] { Boolean.TYPE });
    private static Method i = ad.a(a, "setSpeedAccuracy", new Class[] { Integer.TYPE });
    private static Method j = ad.a(a, "setSpeedRequired", new Class[] { Boolean.TYPE });
    private static Method k = ad.a(a, "setVerticalAccuracy", new Class[] { Integer.TYPE });
    private static Method l = ad.a(a, "setPowerRequirement", new Class[] { Integer.TYPE });
    private static Method m = ad.a(b, "getTime", new Class[0]);
    private static Method n = ad.a(b, "getProvider", new Class[0]);
    private static Method o = ad.a(b, "getAccuracy", new Class[0]);
    private static Method p = ad.a(b, "getLatitude", new Class[0]);
    private static Method q = ad.a(b, "getLongitude", new Class[0]);
    private static Field r = ad.a(a, "NO_REQUIREMENT");
    private static Field s = ad.a(a, "POWER_LOW");
    private static Field t = ad.a(a, "ACCURACY_LOW");
    private static Field u = ad.a(a, "ACCURACY_COARSE");
    private static Field v = ad.a(c, "AVAILABLE");
    private static Field w = ad.a(c, "TEMPORARILY_UNAVAILABLE");
    private static Field x = ad.a(c, "OUT_OF_SERVICE");
    
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
    private static Class<?> a = ad.a("android.net.ConnectivityManager");
    private static Class<?> b = ad.a("android.net.NetworkInfo");
    private static Class<?> c;
    private static Class<?> d = ad.a("android.net.wifi.WifiInfo");
    private static Class<?> e = ad.a("android.net.wifi.WifiManager");
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
    private static Field t = ad.a(c, "CONNECTED");
    private static Field u;
    
    static
    {
      c = ad.a("android.net.NetworkInfo$State");
      f = ad.a(a, "getActiveNetworkInfo", new Class[0]);
      h = ad.a(b, "getState", new Class[0]);
      i = ad.a(b, "getType", new Class[0]);
      j = ad.a(b, "getExtraInfo", new Class[0]);
      k = ad.a(d, "getBSSID", new Class[0]);
      l = ad.a(d, "getSSID", new Class[0]);
      m = ad.a(d, "getRssi", new Class[0]);
      n = ad.a(e, "getConnectionInfo", new Class[0]);
      g = ad.a(b, "isConnectedOrConnecting", new Class[0]);
      o = ad.a(a, "CONNECTIVITY_ACTION");
      p = ad.a(a, "TYPE_MOBILE");
      q = ad.a(a, "TYPE_WIFI");
      r = ad.a(a, "TYPE_BLUETOOTH");
      s = ad.a(a, "TYPE_ETHERNET");
      u = ad.a(e, "NETWORK_STATE_CHANGED_ACTION");
    }
    
    static boolean a()
    {
      return (f != null) && (g != null);
    }
    
    static boolean b()
    {
      boolean bool;
      if ((o != null) && (t != null) && (h != null) && (j != null) && (i != null) && (p != null) && (q != null)) {
        bool = true;
      }
      while (aj.b.a.c >= aj.b.b.f) {
        if ((bool) && (s != null) && (r != null))
        {
          return true;
          bool = false;
        }
        else
        {
          return false;
        }
      }
      return bool;
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
      if ((aj.d.a() != null) && (aj.d.b() != null)) {}
      try
      {
        this.b = paramContext.getPackageManager().getPackageInfo(paramString, paramInt);
        return;
      }
      catch (PackageManager.NameNotFoundException this$1)
      {
        aj.a();
        return;
      }
      catch (SecurityException this$1)
      {
        aj.a();
        return;
      }
      catch (Exception this$1)
      {
        az.c(aj.a(), aj.this.getMessage());
      }
    }
    
    final int a()
    {
      if ((aj.d.c() != null) && (this.b != null)) {
        return this.b.versionCode;
      }
      return -1;
    }
    
    final String b()
    {
      if ((aj.d.d() != null) && (this.b != null)) {
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
      if (aj.d.b() != null) {}
      try
      {
        this.b = paramContext.getPackageManager();
        return;
      }
      catch (SecurityException this$1)
      {
        aj.a();
        return;
      }
      catch (Exception this$1)
      {
        az.c(aj.a(), aj.this.getMessage());
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
      //   8: invokestatic 29	com/threatmetrix/TrustDefender/aj$d:b	()Ljava/lang/Class;
      //   11: ifnull +90 -> 101
      //   14: invokestatic 55	com/threatmetrix/TrustDefender/aj$d:f	()Ljava/lang/Class;
      //   17: ifnull +84 -> 101
      //   20: aload_0
      //   21: getfield 24	com/threatmetrix/TrustDefender/aj$i:b	Landroid/content/pm/PackageManager;
      //   24: ifnull +77 -> 101
      //   27: aload_0
      //   28: getfield 24	com/threatmetrix/TrustDefender/aj$i:b	Landroid/content/pm/PackageManager;
      //   31: iconst_0
      //   32: invokevirtual 61	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   35: invokeinterface 67 1 0
      //   40: astore_2
      //   41: aload_2
      //   42: invokeinterface 73 1 0
      //   47: ifeq +54 -> 101
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
      //   97: invokestatic 38	com/threatmetrix/TrustDefender/aj:a	()Ljava/lang/String;
      //   100: pop
      //   101: aload_1
      //   102: ldc 85
      //   104: invokevirtual 97	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   107: pop
      //   108: aload_1
      //   109: ldc 93
      //   111: invokevirtual 97	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   114: pop
      //   115: aload_1
      //   116: areturn
      //   117: astore_2
      //   118: invokestatic 38	com/threatmetrix/TrustDefender/aj:a	()Ljava/lang/String;
      //   121: aload_2
      //   122: invokevirtual 41	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   125: invokestatic 47	com/threatmetrix/TrustDefender/az:c	(Ljava/lang/String;Ljava/lang/String;)V
      //   128: goto -27 -> 101
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	131	0	this	i
      //   7	109	1	localArrayList	java.util.ArrayList
      //   40	11	2	localIterator	java.util.Iterator
      //   96	1	2	localSecurityException	SecurityException
      //   117	5	2	localException	Exception
      //   59	27	3	localApplicationInfo	ApplicationInfo
      // Exception table:
      //   from	to	target	type
      //   27	41	96	java/lang/SecurityException
      //   41	93	96	java/lang/SecurityException
      //   27	41	117	java/lang/Exception
      //   41	93	117	java/lang/Exception
    }
    
    final boolean a(String paramString, int paramInt)
    {
      if ((aj.d.b() != null) && (aj.d.a() != null) && (this.b != null)) {}
      try
      {
        this.b.getPackageInfo(paramString, paramInt);
        return true;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        aj.a();
        return false;
      }
      catch (SecurityException paramString)
      {
        for (;;)
        {
          aj.a();
        }
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          az.c(aj.a(), paramString.getMessage());
        }
      }
    }
    
    final boolean a(String paramString1, String paramString2)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (aj.d.e() != null)
      {
        bool1 = bool2;
        if (this.b == null) {}
      }
      try
      {
        int i = this.b.checkPermission(paramString1, paramString2);
        bool1 = bool2;
        if (i == 0) {
          bool1 = true;
        }
        return bool1;
      }
      catch (SecurityException paramString1)
      {
        aj.a();
        return false;
      }
      catch (Exception paramString1)
      {
        az.c(aj.a(), paramString1.getMessage());
      }
      return false;
    }
  }
  
  static final class j
  {
    private static Class<?> a;
    private static Method b;
    private static Method c = ad.a(a, "isScreenOn", new Class[0]);
    
    static
    {
      Class localClass = ad.a("android.os.PowerManager");
      a = localClass;
      b = ad.a(localClass, "isInteractive", new Class[0]);
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
    private static Field c = ad.a(a, "ANDROID_ID");
    private static Field d = ad.a(a, "ALLOW_MOCK_LOCATION");
    
    static
    {
      Class localClass = ad.a("android.provider.Settings$Secure");
      a = localClass;
      b = ad.a(localClass, "getString", new Class[] { ContentResolver.class, String.class });
    }
    
    static String a(ContentResolver paramContentResolver, String paramString)
    {
      if ((paramContentResolver == null) || (s.e(paramString)) || (b == null)) {}
      for (;;)
      {
        return null;
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
        catch (SecurityException paramContentResolver)
        {
          aj.a();
          return null;
        }
        catch (Exception paramContentResolver)
        {
          az.c(aj.a(), paramContentResolver.getMessage());
        }
      }
      return null;
    }
  }
  
  final class l
  {
    private SharedPreferences b = null;
    private SharedPreferences.Editor c = null;
    
    l(Context paramContext, String paramString, int paramInt)
    {
      if (aj.e.a() != null) {
        this.b = paramContext.getSharedPreferences(paramString, 0);
      }
      if (aj.e.b() != null) {
        this.c = this.b.edit();
      }
    }
    
    final int a(String paramString, int paramInt)
    {
      int i = 0;
      paramInt = i;
      if (aj.e.c() != null)
      {
        paramInt = i;
        if (this.b != null) {
          paramInt = this.b.getInt(paramString, 0);
        }
      }
      return paramInt;
    }
    
    final long a(String paramString, long paramLong)
    {
      long l = 0L;
      paramLong = l;
      if (aj.e.d() != null)
      {
        paramLong = l;
        if (this.b != null) {
          paramLong = this.b.getLong(paramString, 0L);
        }
      }
      return paramLong;
    }
    
    final String a(String paramString1, String paramString2)
    {
      String str = paramString2;
      if (aj.e.e() != null)
      {
        str = paramString2;
        if (this.b != null) {
          str = this.b.getString(paramString1, paramString2);
        }
      }
      return str;
    }
    
    final void a()
    {
      if ((aj.e.i() != null) && (this.c != null)) {
        this.c.apply();
      }
    }
    
    final void b(String paramString, int paramInt)
    {
      if ((aj.e.f() != null) && (this.c != null)) {
        this.c.putInt(paramString, paramInt);
      }
    }
    
    final void b(String paramString, long paramLong)
    {
      if ((aj.e.g() != null) && (this.c != null)) {
        this.c.putLong(paramString, paramLong);
      }
    }
    
    final void b(String paramString1, String paramString2)
    {
      if ((aj.e.h() != null) && (this.c != null)) {
        this.c.putString(paramString1, paramString2);
      }
    }
  }
  
  static final class m
  {
    private static Class<?> a;
    private static Method b;
    private static Method c = ad.a(a, "elapsedRealtime", new Class[0]);
    
    static
    {
      Class localClass = ad.a("android.os.SystemClock");
      a = localClass;
      b = ad.a(localClass, "uptimeMillis", new Class[0]);
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
    private static Class<?> a = ad.a("android.telephony.TelephonyManager");
    private static Class<?> b = ad.a("android.telephony.CellIdentityCdma");
    private static Class<?> c = ad.a("android.telephony.CellIdentityGsm");
    private static Class<?> d = ad.a("android.telephony.CellIdentityLte");
    private static Class<?> e = ad.a("android.telephony.CellIdentityWcdma");
    private static Class<?> f = ad.a("android.telephony.CellInfo");
    private static Class<?> g = ad.a("android.telephony.CellInfoCdma");
    private static Class<?> h = ad.a("android.telephony.CellInfoGsm");
    private static Class<?> i = ad.a("android.telephony.CellInfoLte");
    private static Class<?> j = ad.a("android.telephony.CellInfoWcdma");
    private static Class<?> k = ad.a("android.telephony.CellSignalStrength");
    private static Class<?> l = ad.a("android.telephony.NeighboringCellInfo");
    private static Class<?> m = ad.a("android.telephony.CellLocation");
    private static Class<?> n = ad.a("android.telephony.SubscriptionInfo");
    private static Class<?> o = ad.a("android.telephony.SubscriptionManager");
    private static Class<?> p = ad.a("android.telephony.cdma.CdmaCellLocation");
    private static Class<?> q = ad.a("android.telephony.gsm.GsmCellLocation");
    private static Method r;
    private static Method s;
    private static Method t;
    private static Method u;
    private static Method v = ad.a(a, "getCellLocation", new Class[0]);
    private static Method w;
    private static Method x;
    private static Method y;
    private static Method z;
    
    static boolean a()
    {
      boolean bool2 = false;
      s = ad.a(a, "getAllCellInfo", new Class[0]);
      V = ad.a(f, "isRegistered", new Class[0]);
      boolean bool1 = bool2;
      if (a != null)
      {
        bool1 = bool2;
        if (k != null)
        {
          bool1 = bool2;
          if (f != null)
          {
            bool1 = bool2;
            if (V != null)
            {
              bool1 = bool2;
              if (s != null) {
                bool1 = true;
              }
            }
          }
        }
      }
      return bool1;
    }
    
    static boolean b()
    {
      boolean bool2 = false;
      r = ad.a(a, "getNetworkOperator", new Class[0]);
      t = ad.a(a, "getNetworkCountryIso", new Class[0]);
      u = ad.b(a, "getNetworkOperatorName", new Class[0]);
      boolean bool1 = bool2;
      if (a != null)
      {
        bool1 = bool2;
        if (r != null)
        {
          bool1 = bool2;
          if (t != null)
          {
            bool1 = bool2;
            if (u != null) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    
    static boolean c()
    {
      boolean bool2 = false;
      B = ad.a(p, "getSystemId", new Class[0]);
      C = ad.a(p, "getBaseStationId", new Class[0]);
      D = ad.a(p, "getBaseStationLatitude", new Class[0]);
      E = ad.a(p, "getBaseStationLongitude", new Class[0]);
      boolean bool1 = bool2;
      if (m != null)
      {
        bool1 = bool2;
        if (v != null)
        {
          bool1 = bool2;
          if (B != null)
          {
            bool1 = bool2;
            if (C != null)
            {
              bool1 = bool2;
              if (D != null)
              {
                bool1 = bool2;
                if (E != null) {
                  bool1 = true;
                }
              }
            }
          }
        }
      }
      return bool1;
    }
    
    static boolean d()
    {
      boolean bool2 = false;
      w = ad.a(q, "getLac", new Class[0]);
      x = ad.a(q, "getCid", new Class[0]);
      y = ad.a(q, "getPsc", new Class[0]);
      boolean bool1 = bool2;
      if (m != null)
      {
        bool1 = bool2;
        if (v != null)
        {
          bool1 = bool2;
          if (x != null)
          {
            bool1 = bool2;
            if (w != null)
            {
              bool1 = bool2;
              if (y != null) {
                bool1 = true;
              }
            }
          }
        }
      }
      return bool1;
    }
    
    static boolean e()
    {
      boolean bool2 = false;
      z = ad.a(l, "getCid", new Class[0]);
      A = ad.a(l, "getRssi", new Class[0]);
      boolean bool1 = bool2;
      if (l != null)
      {
        bool1 = bool2;
        if (z != null)
        {
          bool1 = bool2;
          if (A != null) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    
    static boolean f()
    {
      boolean bool2 = false;
      G = ad.a(n, "getSimSlotIndex", new Class[0]);
      H = ad.a(n, "getCarrierName", new Class[0]);
      I = ad.a(n, "getDisplayName", new Class[0]);
      J = ad.a(n, "getIccId", new Class[0]);
      K = ad.a(n, "getNumber", new Class[0]);
      L = ad.a(n, "getCountryIso", new Class[0]);
      M = ad.a(n, "getDataRoaming", new Class[0]);
      F = ad.a(o, "getActiveSubscriptionInfoList", new Class[0]);
      boolean bool1 = bool2;
      if (o != null)
      {
        bool1 = bool2;
        if (n != null)
        {
          bool1 = bool2;
          if (G != null)
          {
            bool1 = bool2;
            if (H != null)
            {
              bool1 = bool2;
              if (I != null)
              {
                bool1 = bool2;
                if (J != null)
                {
                  bool1 = bool2;
                  if (K != null)
                  {
                    bool1 = bool2;
                    if (L != null)
                    {
                      bool1 = bool2;
                      if (M != null)
                      {
                        bool1 = bool2;
                        if (F != null) {
                          bool1 = true;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      return bool1;
    }
    
    static boolean g()
    {
      boolean bool2 = false;
      T = ad.b(j, "getCellSignalStrength", new Class[0]);
      U = ad.b(j, "getCellIdentity", new Class[0]);
      boolean bool1 = bool2;
      if (e != null)
      {
        bool1 = bool2;
        if (T != null)
        {
          bool1 = bool2;
          if (U != null) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    
    static boolean h()
    {
      boolean bool2 = false;
      P = ad.b(h, "getCellSignalStrength", new Class[0]);
      Q = ad.b(h, "getCellIdentity", new Class[0]);
      boolean bool1 = bool2;
      if (c != null)
      {
        bool1 = bool2;
        if (P != null)
        {
          bool1 = bool2;
          if (Q != null) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    
    static boolean i()
    {
      boolean bool2 = false;
      R = ad.b(i, "getCellSignalStrength", new Class[0]);
      S = ad.b(i, "getCellIdentity", new Class[0]);
      boolean bool1 = bool2;
      if (d != null)
      {
        bool1 = bool2;
        if (R != null)
        {
          bool1 = bool2;
          if (S != null) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    
    static boolean j()
    {
      boolean bool2 = false;
      N = ad.b(g, "getCellSignalStrength", new Class[0]);
      O = ad.b(g, "getCellIdentity", new Class[0]);
      boolean bool1 = bool2;
      if (b != null)
      {
        bool1 = bool2;
        if (N != null)
        {
          bool1 = bool2;
          if (O != null) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    
    static boolean k()
    {
      boolean bool2 = false;
      W = ad.a(a, "getDataState", new Class[0]);
      X = ad.a(a, "DATA_CONNECTED");
      Y = ad.a(a, "DATA_CONNECTING");
      Z = ad.a(a, "DATA_SUSPENDED");
      boolean bool1 = bool2;
      if (a != null)
      {
        bool1 = bool2;
        if (W != null)
        {
          bool1 = bool2;
          if (X != null)
          {
            bool1 = bool2;
            if (Y != null)
            {
              bool1 = bool2;
              if (Z != null) {
                bool1 = true;
              }
            }
          }
        }
      }
      return bool1;
    }
  }
  
  static final class o
  {
    private static Class<?> a = ad.a("android.webkit.WebView");
    private static Class<?> b = ad.a("android.webkit.WebViewClient");
    private static Class<?> c = ad.a("android.webkit.WebSettings");
    private static Class<?> d = ad.a("android.webkit.WebSettings$PluginState");
    private static Class<?> e = ad.a("android.webkit.WebChromeClient");
    private static Class<?> f;
    private static Method g;
    private static Method h = ad.a(a, "destroy", new Class[0]);
    private static Method i = ad.a(a, "loadUrl", new Class[] { String.class });
    private static Method j = ad.a(a, "loadData", new Class[] { String.class, String.class, String.class });
    private static Method k = ad.a(a, "getSettings", new Class[0]);
    private static Method l = ad.a(a, "setWebViewClient", new Class[] { b });
    private static Method m = ad.a(a, "setWebChromeClient", new Class[] { e });
    private static Method n = ad.a(c, "getUserAgentString", new Class[0]);
    private static Method o = ad.a(c, "setJavaScriptEnabled", new Class[] { Boolean.TYPE });
    private static Field p = ad.a(d, "ON");
    
    static
    {
      Class localClass = ad.a("android.webkit.JsResult");
      f = localClass;
      g = ad.a(localClass, "confirm", new Class[0]);
    }
    
    static boolean a()
    {
      return (b != null) && (e != null) && (g != null) && (h != null) && (i != null) && (j != null) && (k != null) && (l != null) && (m != null) && (n != null) && (o != null) && (p != null);
    }
  }
}
