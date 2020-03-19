package com.threatmetrix.TrustDefender.internal;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Date;

@SuppressLint({"HardwareIds"})
public class a
{
  private static final String a = as.a(a.class);
  
  a() {}
  
  final class a
  {
    PackageInfo a = null;
    
    a(Context paramContext, String paramString, int paramInt)
    {
      if ((a.i.a()) && (a.i.b())) {}
      try
      {
        this.a = paramContext.getPackageManager().getPackageInfo(paramString, paramInt);
        return;
      }
      catch (PackageManager.NameNotFoundException this$1)
      {
        a.a();
        return;
      }
      catch (SecurityException this$1)
      {
        a.a();
        return;
      }
      catch (Exception this$1)
      {
        paramContext = a.a();
        a.this.toString();
        as.d(paramContext);
      }
    }
  }
  
  final class b
  {
    private PackageManager b = null;
    
    b(Context paramContext)
    {
      if (a.i.b()) {}
      try
      {
        this.b = paramContext.getPackageManager();
        return;
      }
      catch (SecurityException this$1)
      {
        a.a();
        return;
      }
      catch (Exception this$1)
      {
        paramContext = a.a();
        a.this.toString();
        as.d(paramContext);
      }
    }
    
    /* Error */
    final java.util.ArrayList<String> a()
    {
      // Byte code:
      //   0: new 50	java/util/ArrayList
      //   3: dup
      //   4: invokespecial 51	java/util/ArrayList:<init>	()V
      //   7: astore_1
      //   8: invokestatic 28	com/threatmetrix/TrustDefender/internal/a$i:b	()Z
      //   11: ifeq +90 -> 101
      //   14: invokestatic 54	com/threatmetrix/TrustDefender/internal/a$i:f	()Z
      //   17: ifeq +84 -> 101
      //   20: aload_0
      //   21: getfield 23	com/threatmetrix/TrustDefender/internal/a$b:b	Landroid/content/pm/PackageManager;
      //   24: ifnull +77 -> 101
      //   27: aload_0
      //   28: getfield 23	com/threatmetrix/TrustDefender/internal/a$b:b	Landroid/content/pm/PackageManager;
      //   31: iconst_0
      //   32: invokevirtual 60	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   35: invokeinterface 66 1 0
      //   40: astore_2
      //   41: aload_2
      //   42: invokeinterface 71 1 0
      //   47: ifeq +54 -> 101
      //   50: aload_2
      //   51: invokeinterface 75 1 0
      //   56: checkcast 77	android/content/pm/ApplicationInfo
      //   59: astore_3
      //   60: aload_3
      //   61: getfield 81	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
      //   64: ldc 83
      //   66: invokevirtual 89	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   69: ifne -28 -> 41
      //   72: aload_3
      //   73: getfield 81	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
      //   76: ldc 91
      //   78: invokevirtual 89	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   81: ifne -40 -> 41
      //   84: aload_1
      //   85: aload_3
      //   86: getfield 81	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
      //   89: invokevirtual 95	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   92: pop
      //   93: goto -52 -> 41
      //   96: astore_2
      //   97: invokestatic 37	com/threatmetrix/TrustDefender/internal/a:a	()Ljava/lang/String;
      //   100: pop
      //   101: aload_1
      //   102: ldc 83
      //   104: invokevirtual 95	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   107: pop
      //   108: aload_1
      //   109: ldc 91
      //   111: invokevirtual 95	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   114: pop
      //   115: aload_1
      //   116: areturn
      //   117: astore_2
      //   118: invokestatic 37	com/threatmetrix/TrustDefender/internal/a:a	()Ljava/lang/String;
      //   121: astore_3
      //   122: aload_2
      //   123: invokevirtual 40	java/lang/Exception:toString	()Ljava/lang/String;
      //   126: pop
      //   127: aload_3
      //   128: invokestatic 46	com/threatmetrix/TrustDefender/internal/as:d	(Ljava/lang/String;)V
      //   131: goto -30 -> 101
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	134	0	this	b
      //   7	109	1	localArrayList	java.util.ArrayList
      //   40	11	2	localIterator	java.util.Iterator
      //   96	1	2	localSecurityException	SecurityException
      //   117	6	2	localException	Exception
      //   59	69	3	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   27	41	96	java/lang/SecurityException
      //   41	93	96	java/lang/SecurityException
      //   27	41	117	java/lang/Exception
      //   41	93	117	java/lang/Exception
    }
    
    final boolean a(String paramString, int paramInt)
    {
      if ((a.i.b()) && (a.i.a()) && (this.b != null)) {}
      try
      {
        this.b.getPackageInfo(paramString, paramInt);
        return true;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        a.a();
        return false;
      }
      catch (SecurityException paramString)
      {
        for (;;)
        {
          a.a();
        }
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          String str = a.a();
          paramString.toString();
          as.d(str);
        }
      }
    }
    
    final boolean a(String paramString1, String paramString2)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (a.i.e())
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
        a.a();
        return false;
      }
      catch (Exception paramString1)
      {
        paramString2 = a.a();
        paramString1.toString();
        as.d(paramString2);
      }
      return false;
    }
  }
  
  static final class c
  {
    private static final Class<?> a;
    private static final Class<?> b;
    private static final Class<?> c;
    private static final Class<?> d;
    private static final Class<?> e;
    private static final Class<?> f;
    private static final Class<?> g;
    private static final Class<?> h;
    private static final boolean i;
    private static final boolean j;
    private static final boolean k;
    private static final boolean l;
    private static final boolean m;
    
    static
    {
      boolean bool2 = false;
      a = XZ.a(XZ.E.N);
      i = a(XZ.E.this, XZ.E.char);
      j = a(XZ.E.goto, XZ.E.else);
      k = a(XZ.E.long, XZ.E.case);
      l = a(XZ.E.break, XZ.E.byte);
      b = XZ.a(XZ.E.try);
      c = XZ.a(XZ.E.void);
      d = XZ.a(XZ.E.h);
      e = XZ.a(XZ.E.I);
      f = XZ.a(XZ.E.G);
      g = XZ.a(XZ.E.new);
      h = XZ.a(XZ.E.strictfp);
      boolean bool1 = bool2;
      if (XZ.a(XZ.E.for) != null)
      {
        bool1 = bool2;
        if (XZ.a(a, "getCellLocation", new Class[0]) != null) {
          bool1 = true;
        }
      }
      m = bool1;
    }
    
    static boolean a()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (a != null)
      {
        bool1 = bool2;
        if (c != null)
        {
          bool1 = bool2;
          if (b != null)
          {
            bool1 = bool2;
            if (XZ.a(b, "isRegistered", new Class[0]) != null)
            {
              bool1 = bool2;
              if (XZ.a(a, "getAllCellInfo", new Class[0]) != null) {
                bool1 = true;
              }
            }
          }
        }
      }
      return bool1;
    }
    
    private static boolean a(XZ.E paramE1, XZ.E paramE2)
    {
      boolean bool2 = false;
      paramE1 = XZ.a(paramE1);
      boolean bool1 = bool2;
      if (XZ.a(paramE2) != null)
      {
        bool1 = bool2;
        if (XZ.b(paramE1, "getCellSignalStrength", new Class[0]) != null)
        {
          bool1 = bool2;
          if (XZ.b(paramE1, "getCellIdentity", new Class[0]) != null) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    
    static boolean b()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (a != null)
      {
        bool1 = bool2;
        if (XZ.a(a, "getNetworkOperator", new Class[0]) != null)
        {
          bool1 = bool2;
          if (XZ.a(a, "getNetworkCountryIso", new Class[0]) != null)
          {
            bool1 = bool2;
            if (XZ.b(a, "getNetworkOperatorName", new Class[0]) != null) {
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
      boolean bool1 = bool2;
      if (m)
      {
        bool1 = bool2;
        if (XZ.a(g, "getSystemId", new Class[0]) != null)
        {
          bool1 = bool2;
          if (XZ.a(g, "getBaseStationId", new Class[0]) != null)
          {
            bool1 = bool2;
            if (XZ.a(g, "getBaseStationLatitude", new Class[0]) != null)
            {
              bool1 = bool2;
              if (XZ.a(g, "getBaseStationLongitude", new Class[0]) != null) {
                bool1 = true;
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
      boolean bool1 = bool2;
      if (m)
      {
        bool1 = bool2;
        if (XZ.a(h, "getCid", new Class[0]) != null)
        {
          bool1 = bool2;
          if (XZ.a(h, "getLac", new Class[0]) != null)
          {
            bool1 = bool2;
            if (XZ.a(h, "getPsc", new Class[0]) != null) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    
    static boolean e()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (d != null)
      {
        bool1 = bool2;
        if (XZ.a(d, "getCid", new Class[0]) != null)
        {
          bool1 = bool2;
          if (XZ.a(d, "getRssi", new Class[0]) != null) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    
    static boolean f()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (f != null)
      {
        bool1 = bool2;
        if (e != null)
        {
          bool1 = bool2;
          if (XZ.a(e, "getSimSlotIndex", new Class[0]) != null)
          {
            bool1 = bool2;
            if (XZ.a(e, "getCarrierName", new Class[0]) != null)
            {
              bool1 = bool2;
              if (XZ.a(e, "getDisplayName", new Class[0]) != null)
              {
                bool1 = bool2;
                if (XZ.a(e, "getIccId", new Class[0]) != null)
                {
                  bool1 = bool2;
                  if (XZ.a(e, "getNumber", new Class[0]) != null)
                  {
                    bool1 = bool2;
                    if (XZ.a(e, "getCountryIso", new Class[0]) != null)
                    {
                      bool1 = bool2;
                      if (XZ.a(e, "getDataRoaming", new Class[0]) != null)
                      {
                        bool1 = bool2;
                        if (XZ.a(f, "getActiveSubscriptionInfoList", new Class[0]) != null) {
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
      return l;
    }
    
    static boolean h()
    {
      return j;
    }
    
    static boolean i()
    {
      return k;
    }
    
    static boolean j()
    {
      return i;
    }
    
    static boolean k()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (a != null)
      {
        bool1 = bool2;
        if (XZ.a(a, "getDataState", new Class[0]) != null)
        {
          bool1 = bool2;
          if (XZ.a(a, "DATA_CONNECTED") != null)
          {
            bool1 = bool2;
            if (XZ.a(a, "DATA_CONNECTING") != null)
            {
              bool1 = bool2;
              if (XZ.a(a, "DATA_SUSPENDED") != null) {
                bool1 = true;
              }
            }
          }
        }
      }
      return bool1;
    }
  }
  
  static final class d
  {
    private static final boolean a;
    private static final boolean b;
    private static final boolean c;
    private static final boolean d;
    private static final boolean e;
    private static final boolean f;
    private static final boolean g;
    private static final boolean h;
    
    static
    {
      boolean bool2 = true;
      Class localClass = XZ.a(XZ.E.z);
      if (XZ.a(localClass, "getString", new Class[] { ContentResolver.class, String.class }) != null)
      {
        bool1 = true;
        a = bool1;
        if (XZ.a(localClass, "ANDROID_ID") == null) {
          break label170;
        }
        bool1 = true;
        label49:
        b = bool1;
        if (XZ.a(localClass, "ALLOW_MOCK_LOCATION") == null) {
          break label175;
        }
        bool1 = true;
        label64:
        c = bool1;
        if (XZ.a(localClass, "ADB_ENABLED") == null) {
          break label180;
        }
        bool1 = true;
        label79:
        d = bool1;
        if (XZ.a(localClass, "DEVELOPMENT_SETTINGS_ENABLED") == null) {
          break label185;
        }
        bool1 = true;
        label94:
        e = bool1;
        localClass = XZ.a(XZ.E.F);
        if (XZ.a(localClass, "getString", new Class[] { ContentResolver.class, String.class }) == null) {
          break label190;
        }
        bool1 = true;
        label130:
        f = bool1;
        if (XZ.a(localClass, "ADB_ENABLED") == null) {
          break label195;
        }
        bool1 = true;
        label145:
        g = bool1;
        if (XZ.a(localClass, "DEVELOPMENT_SETTINGS_ENABLED") == null) {
          break label200;
        }
      }
      label170:
      label175:
      label180:
      label185:
      label190:
      label195:
      label200:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        h = bool1;
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label49;
        bool1 = false;
        break label64;
        bool1 = false;
        break label79;
        bool1 = false;
        break label94;
        bool1 = false;
        break label130;
        bool1 = false;
        break label145;
      }
    }
    
    static String a(ContentResolver paramContentResolver, String paramString)
    {
      Object localObject;
      if ((paramContentResolver == null) || (h.c(paramString)) || (!a)) {
        localObject = null;
      }
      for (;;)
      {
        return localObject;
        try
        {
          String str = Settings.Secure.getString(paramContentResolver, paramString);
          localObject = str;
          if (str != null) {
            continue;
          }
          if (("android_id".equals(paramString)) && (b)) {
            return Settings.Secure.getString(paramContentResolver, "android_id");
          }
          if (("mock_location".equals(paramString)) && (c)) {
            return Settings.Secure.getString(paramContentResolver, "mock_location");
          }
          if (("adb_enabled".equals(paramString)) && (d)) {
            return Settings.Secure.getString(paramContentResolver, "adb_enabled");
          }
          if (("development_settings_enabled".equals(paramString)) && (e) && (a.f.a.c >= a.f.b.i))
          {
            paramContentResolver = Settings.Secure.getString(paramContentResolver, "development_settings_enabled");
            return paramContentResolver;
          }
        }
        catch (SecurityException paramContentResolver)
        {
          a.a();
          return null;
        }
        catch (Exception paramContentResolver)
        {
          for (;;)
          {
            paramString = a.a();
            paramContentResolver.toString();
            as.d(paramString);
          }
        }
      }
    }
    
    static String b(ContentResolver paramContentResolver, String paramString)
    {
      if ((paramContentResolver == null) || (h.c(paramString)) || (!f)) {}
      for (;;)
      {
        return null;
        try
        {
          if (("adb_enabled".equals(paramString)) && (g)) {
            return Settings.Global.getString(paramContentResolver, "adb_enabled");
          }
          if (("development_settings_enabled".equals(paramString)) && (h))
          {
            paramContentResolver = Settings.Global.getString(paramContentResolver, "development_settings_enabled");
            return paramContentResolver;
          }
        }
        catch (SecurityException paramContentResolver)
        {
          a.a();
          return null;
        }
        catch (Exception paramContentResolver)
        {
          paramString = a.a();
          paramContentResolver.toString();
          as.d(paramString);
        }
      }
      return null;
    }
  }
  
  static final class e
  {
    ApplicationInfo a = null;
    
    e(X.b paramB)
    {
      if ((a.i.f()) && (a.i.g())) {
        this.a = paramB.a.getApplicationInfo();
      }
    }
  }
  
  static final class f
  {
    static final long a;
    static final String b;
    static final String c;
    static final String d;
    static final String e;
    static final String f;
    static final String g;
    static final String h;
    static final String i;
    static final String j;
    static final String k;
    static final String l;
    static final String m;
    static final String n;
    static final Method o;
    static final boolean p;
    private static final Class<?> q;
    
    static
    {
      Object localObject2 = null;
      Object localObject1 = XZ.a(XZ.E.do);
      q = (Class)localObject1;
      long l1;
      if (XZ.a((Class)localObject1, "TIME") != null)
      {
        l1 = Build.TIME;
        a = l1;
        if (XZ.a(q, "TYPE") == null) {
          break label328;
        }
        localObject1 = Build.TYPE;
        label46:
        b = (String)localObject1;
        if (XZ.a(q, "TAGS") == null) {
          break label333;
        }
        localObject1 = Build.TAGS;
        label65:
        c = (String)localObject1;
        if (XZ.a(q, "HOST") == null) {
          break label338;
        }
        localObject1 = Build.HOST;
        label84:
        d = (String)localObject1;
        if (XZ.a(q, "BRAND") == null) {
          break label343;
        }
        localObject1 = Build.BRAND;
        label103:
        e = (String)localObject1;
        if (XZ.a(q, "USER") == null) {
          break label348;
        }
        localObject1 = Build.USER;
        label122:
        f = (String)localObject1;
        if (XZ.a(q, "ID") == null) {
          break label353;
        }
        localObject1 = Build.ID;
        label141:
        g = (String)localObject1;
        if (XZ.a(q, "SERIAL") == null) {
          break label358;
        }
        localObject1 = Build.SERIAL;
        label160:
        h = (String)localObject1;
        if (XZ.a(q, "DEVICE") == null) {
          break label363;
        }
        localObject1 = Build.DEVICE;
        label179:
        i = (String)localObject1;
        if (XZ.a(q, "MODEL") == null) {
          break label368;
        }
        localObject1 = Build.MODEL;
        label198:
        j = (String)localObject1;
        if (XZ.a(q, "DISPLAY") == null) {
          break label373;
        }
        localObject1 = Build.DISPLAY;
        label217:
        k = (String)localObject1;
        if (XZ.a(q, "PRODUCT") == null) {
          break label378;
        }
        localObject1 = Build.PRODUCT;
        label236:
        l = (String)localObject1;
        if (XZ.a(q, "MANUFACTURER") == null) {
          break label383;
        }
        localObject1 = Build.MANUFACTURER;
        label255:
        m = (String)localObject1;
        localObject1 = localObject2;
        if (XZ.a(q, "BOARD") != null) {
          localObject1 = Build.BOARD;
        }
        n = (String)localObject1;
        o = XZ.a(q, "getSerial", new Class[0]);
        if ((a.c < b.i) || (a.c > b.l)) {
          break label388;
        }
      }
      label328:
      label333:
      label338:
      label343:
      label348:
      label353:
      label358:
      label363:
      label368:
      label373:
      label378:
      label383:
      label388:
      for (boolean bool = true;; bool = false)
      {
        p = bool;
        return;
        l1 = Long.MAX_VALUE;
        break;
        localObject1 = null;
        break label46;
        localObject1 = null;
        break label65;
        localObject1 = null;
        break label84;
        localObject1 = null;
        break label103;
        localObject1 = null;
        break label122;
        localObject1 = null;
        break label141;
        localObject1 = null;
        break label160;
        localObject1 = null;
        break label179;
        localObject1 = null;
        break label198;
        localObject1 = null;
        break label217;
        localObject1 = null;
        break label236;
        localObject1 = null;
        break label255;
      }
    }
    
    static String a()
    {
      if (o != null)
      {
        Object localObject = XZ.a(null, o, new Object[0]);
        if (localObject != null) {
          return (String)localObject;
        }
      }
      return null;
    }
    
    static final class a
    {
      static final String a;
      static final String b;
      static final int c;
      
      static
      {
        Object localObject2 = null;
        Class localClass = XZ.a(XZ.E.M);
        Object localObject1;
        if (XZ.a(localClass, "RELEASE") != null)
        {
          localObject1 = Build.VERSION.RELEASE;
          a = (String)localObject1;
          if (XZ.a(localClass, "SDK_INT") == null) {
            break label68;
          }
        }
        label68:
        for (int i = Build.VERSION.SDK_INT;; i = -1)
        {
          c = i;
          localObject1 = localObject2;
          if (XZ.a(localClass, "CODENAME") != null) {
            localObject1 = Build.VERSION.CODENAME;
          }
          b = (String)localObject1;
          return;
          localObject1 = null;
          break;
        }
      }
    }
    
    static final class b
    {
      static final int a;
      static final int b;
      static final int c;
      static final int d;
      static final int e;
      static final int f;
      static final int g;
      static final int h;
      static final int i;
      static final int j;
      static final int k;
      static final int l;
      static final int m;
      static final int n;
      static final int o;
      static final int p;
      static final int q;
      static final int r = 25;
      private static final Class<?> s;
      
      static
      {
        Class localClass = XZ.a(XZ.E.P);
        s = localClass;
        XZ.a(localClass, "FROYO");
        a = 8;
        XZ.a(s, "GINGERBREAD");
        b = 9;
        XZ.a(s, "GINGERBREAD_MR1");
        c = 10;
        XZ.a(s, "HONEYCOMB");
        d = 11;
        XZ.a(s, "HONEYCOMB_MR1");
        e = 12;
        XZ.a(s, "HONEYCOMB_MR2");
        f = 13;
        XZ.a(s, "ICE_CREAM_SANDWICH");
        g = 14;
        XZ.a(s, "ICE_CREAM_SANDWICH_MR1");
        h = 15;
        XZ.a(s, "JELLY_BEAN");
        i = 16;
        XZ.a(s, "JELLY_BEAN_MR1");
        j = 17;
        XZ.a(s, "JELLY_BEAN_MR2");
        k = 18;
        XZ.a(s, "KITKAT");
        l = 19;
        XZ.a(s, "KITKAT_WATCH");
        m = 20;
        XZ.a(s, "LOLLIPOP");
        n = 21;
        XZ.a(s, "LOLLIPOP_MR1");
        o = 22;
        XZ.a(s, "M");
        p = 23;
        XZ.a(s, "N");
        q = 24;
        XZ.a(s, "N_MR1");
      }
    }
  }
  
  public static final class g
  {
    private static final boolean a;
    
    static
    {
      Class localClass1 = XZ.a(XZ.E.S);
      Class localClass2 = XZ.a(XZ.E.U);
      Class localClass3 = XZ.a(XZ.E.T);
      Class localClass4 = XZ.a(XZ.E.Q);
      Class localClass5 = XZ.a(XZ.E.O);
      int i;
      int j;
      label76:
      int k;
      label97:
      int m;
      label128:
      int n;
      label145:
      int i1;
      label167:
      int i2;
      label189:
      int i3;
      label206:
      int i4;
      label229:
      int i5;
      if (XZ.a(XZ.a(XZ.E.volatile), "confirm", new Class[0]) != null)
      {
        i = 1;
        if (XZ.a(localClass1, "destroy", new Class[0]) == null) {
          break label312;
        }
        j = 1;
        if (XZ.a(localClass1, "loadUrl", new Class[] { String.class }) == null) {
          break label317;
        }
        k = 1;
        if (XZ.a(localClass1, "loadData", new Class[] { String.class, String.class, String.class }) == null) {
          break label322;
        }
        m = 1;
        if (XZ.a(localClass1, "getSettings", new Class[0]) == null) {
          break label327;
        }
        n = 1;
        if (XZ.a(localClass1, "setWebViewClient", new Class[] { localClass2 }) == null) {
          break label333;
        }
        i1 = 1;
        if (XZ.a(localClass1, "setWebChromeClient", new Class[] { localClass5 }) == null) {
          break label339;
        }
        i2 = 1;
        if (XZ.a(localClass3, "getUserAgentString", new Class[0]) == null) {
          break label345;
        }
        i3 = 1;
        if (XZ.a(localClass3, "setJavaScriptEnabled", new Class[] { Boolean.TYPE }) == null) {
          break label351;
        }
        i4 = 1;
        if (XZ.a(localClass4, "ON") == null) {
          break label357;
        }
        i5 = 1;
        label242:
        if ((localClass2 == null) || (localClass5 == null) || (i == 0) || (j == 0) || (k == 0) || (m == 0) || (n == 0) || (i1 == 0) || (i2 == 0) || (i3 == 0) || (i4 == 0) || (i5 == 0)) {
          break label363;
        }
      }
      label312:
      label317:
      label322:
      label327:
      label333:
      label339:
      label345:
      label351:
      label357:
      label363:
      for (boolean bool = true;; bool = false)
      {
        a = bool;
        return;
        i = 0;
        break;
        j = 0;
        break label76;
        k = 0;
        break label97;
        m = 0;
        break label128;
        n = 0;
        break label145;
        i1 = 0;
        break label167;
        i2 = 0;
        break label189;
        i3 = 0;
        break label206;
        i4 = 0;
        break label229;
        i5 = 0;
        break label242;
      }
    }
    
    public static boolean a()
    {
      return a;
    }
  }
  
  public static final class h
  {
    private static final boolean a;
    private static final boolean b;
    
    static
    {
      Class localClass1 = XZ.a(XZ.E.final);
      Class localClass2 = XZ.a(XZ.E.j);
      Class localClass3 = XZ.a(XZ.E.i);
      Class localClass4 = XZ.a(XZ.E.k);
      int i;
      int j;
      label76:
      int k;
      label98:
      int m;
      label120:
      int n;
      label143:
      int i1;
      label166:
      int i2;
      label189:
      int i3;
      label212:
      int i4;
      label229:
      int i5;
      label246:
      int i6;
      label263:
      int i7;
      label280:
      int i8;
      label297:
      int i9;
      label310:
      int i10;
      label323:
      int i11;
      label336:
      int i12;
      label349:
      int i13;
      label362:
      int i14;
      label375:
      int i15;
      if (XZ.a(localClass1, "setAccuracy", new Class[] { Integer.TYPE }) != null)
      {
        i = 1;
        if (XZ.a(localClass1, "setAltitudeRequired", new Class[] { Boolean.TYPE }) == null) {
          break label511;
        }
        j = 1;
        if (XZ.a(localClass1, "setBearingAccuracy", new Class[] { Integer.TYPE }) == null) {
          break label516;
        }
        k = 1;
        if (XZ.a(localClass1, "setCostAllowed", new Class[] { Boolean.TYPE }) == null) {
          break label521;
        }
        m = 1;
        if (XZ.a(localClass1, "setSpeedAccuracy", new Class[] { Integer.TYPE }) == null) {
          break label526;
        }
        n = 1;
        if (XZ.a(localClass1, "setSpeedRequired", new Class[] { Boolean.TYPE }) == null) {
          break label532;
        }
        i1 = 1;
        if (XZ.a(localClass1, "setVerticalAccuracy", new Class[] { Integer.TYPE }) == null) {
          break label538;
        }
        i2 = 1;
        if (XZ.a(localClass1, "setPowerRequirement", new Class[] { Integer.TYPE }) == null) {
          break label544;
        }
        i3 = 1;
        if (XZ.a(localClass2, "getTime", new Class[0]) == null) {
          break label550;
        }
        i4 = 1;
        if (XZ.a(localClass2, "getProvider", new Class[0]) == null) {
          break label556;
        }
        i5 = 1;
        if (XZ.a(localClass2, "getAccuracy", new Class[0]) == null) {
          break label562;
        }
        i6 = 1;
        if (XZ.a(localClass2, "getLatitude", new Class[0]) == null) {
          break label568;
        }
        i7 = 1;
        if (XZ.a(localClass2, "getLongitude", new Class[0]) == null) {
          break label574;
        }
        i8 = 1;
        if (XZ.a(localClass1, "NO_REQUIREMENT") == null) {
          break label580;
        }
        i9 = 1;
        if (XZ.a(localClass1, "POWER_LOW") == null) {
          break label586;
        }
        i10 = 1;
        if (XZ.a(localClass1, "ACCURACY_LOW") == null) {
          break label592;
        }
        i11 = 1;
        if (XZ.a(localClass1, "ACCURACY_COARSE") == null) {
          break label598;
        }
        i12 = 1;
        if (XZ.a(localClass3, "AVAILABLE") == null) {
          break label604;
        }
        i13 = 1;
        if (XZ.a(localClass3, "TEMPORARILY_UNAVAILABLE") == null) {
          break label610;
        }
        i14 = 1;
        if (XZ.a(localClass3, "OUT_OF_SERVICE") == null) {
          break label616;
        }
        i15 = 1;
        label388:
        if ((i == 0) || (j == 0) || (k == 0) || (m == 0) || (n == 0) || (i1 == 0) || (i2 == 0) || (i3 == 0) || (i9 == 0) || (i10 == 0) || (i11 == 0) || (i12 == 0)) {
          break label622;
        }
        bool = true;
        label447:
        a = bool;
        if ((localClass4 == null) || (i4 == 0) || (i5 == 0) || (i7 == 0) || (i8 == 0) || (i6 == 0) || (i13 == 0) || (i14 == 0) || (i15 == 0)) {
          break label628;
        }
      }
      label511:
      label516:
      label521:
      label526:
      label532:
      label538:
      label544:
      label550:
      label556:
      label562:
      label568:
      label574:
      label580:
      label586:
      label592:
      label598:
      label604:
      label610:
      label616:
      label622:
      label628:
      for (boolean bool = true;; bool = false)
      {
        b = bool;
        return;
        i = 0;
        break;
        j = 0;
        break label76;
        k = 0;
        break label98;
        m = 0;
        break label120;
        n = 0;
        break label143;
        i1 = 0;
        break label166;
        i2 = 0;
        break label189;
        i3 = 0;
        break label212;
        i4 = 0;
        break label229;
        i5 = 0;
        break label246;
        i6 = 0;
        break label263;
        i7 = 0;
        break label280;
        i8 = 0;
        break label297;
        i9 = 0;
        break label310;
        i10 = 0;
        break label323;
        i11 = 0;
        break label336;
        i12 = 0;
        break label349;
        i13 = 0;
        break label362;
        i14 = 0;
        break label375;
        i15 = 0;
        break label388;
        bool = false;
        break label447;
      }
    }
    
    static boolean a()
    {
      return a;
    }
    
    public static boolean b()
    {
      return b;
    }
  }
  
  static final class i
  {
    static final int a;
    static final int b;
    private static final boolean c;
    private static final boolean d;
    private static final boolean e;
    private static final boolean f;
    private static final boolean g;
    private static final boolean h;
    private static final boolean i;
    
    static
    {
      boolean bool2 = false;
      Class localClass1 = XZ.a(XZ.E.n);
      if (localClass1 != null)
      {
        bool1 = true;
        e = bool1;
        Class localClass2 = XZ.a(XZ.E.o);
        if (localClass2 == null) {
          break label143;
        }
        bool1 = true;
        label32:
        f = bool1;
        if (XZ.a(localClass1, "checkPermission", new Class[] { String.class, String.class }) == null) {
          break label148;
        }
        bool1 = true;
        label61:
        g = bool1;
        if (XZ.a(localClass2, "versionCode") == null) {
          break label153;
        }
        bool1 = true;
        label76:
        h = bool1;
        if (XZ.a(localClass2, "versionName") == null) {
          break label158;
        }
        bool1 = true;
        label91:
        i = bool1;
        if (XZ.a(XZ.E.if) == null) {
          break label163;
        }
      }
      label143:
      label148:
      label153:
      label158:
      label163:
      for (boolean bool1 = true;; bool1 = false)
      {
        c = bool1;
        bool1 = bool2;
        if (XZ.a(XZ.E.u) != null) {
          bool1 = true;
        }
        d = bool1;
        a = 1;
        b = 128;
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label32;
        bool1 = false;
        break label61;
        bool1 = false;
        break label76;
        bool1 = false;
        break label91;
      }
    }
  }
  
  static final class j
  {
    private static final boolean a;
    private static final boolean b;
    private static final boolean c;
    
    static
    {
      Class localClass1 = XZ.a(XZ.E.class);
      Class localClass2 = XZ.a(XZ.E.b);
      Class localClass3 = XZ.a(XZ.E.transient);
      Class localClass4 = XZ.a(XZ.E.d);
      Class localClass5 = XZ.a(XZ.E.l);
      Class localClass6 = XZ.a(XZ.E.w);
      Class localClass7 = XZ.a(XZ.E.a);
      Class localClass8 = XZ.a(XZ.E.A);
      Class localClass9 = XZ.a(XZ.E.s);
      Class localClass10 = XZ.a(XZ.E.g);
      Class localClass11 = XZ.a(XZ.E.int);
      Class localClass12 = XZ.a(XZ.E.interface);
      Class localClass13 = XZ.a(XZ.E.C);
      Class localClass14 = XZ.a(XZ.E.c);
      Class localClass15 = XZ.a(XZ.E.f);
      Class localClass16 = XZ.a(XZ.E.Z);
      Class localClass17 = XZ.a(XZ.E.implements);
      Class localClass18 = XZ.a(XZ.E.instanceof);
      Class localClass19 = XZ.a(XZ.E.protected);
      Class localClass20 = XZ.a(XZ.E.synchronized);
      int i;
      int j;
      label202:
      int k;
      label228:
      int m;
      label249:
      int n;
      label271:
      int i1;
      label288:
      int i2;
      label305:
      int i3;
      label322:
      int i4;
      label339:
      int i5;
      label356:
      int i6;
      label373:
      int i7;
      label400:
      int i9;
      label422:
      int i8;
      label444:
      int i10;
      label466:
      int i11;
      label488:
      int i12;
      label505:
      int i14;
      label527:
      int i13;
      label549:
      int i15;
      label571:
      int i16;
      label593:
      int i17;
      label615:
      int i18;
      label637:
      int i19;
      label659:
      int i20;
      label681:
      int i21;
      label703:
      int i22;
      label725:
      int i23;
      label752:
      int i24;
      label779:
      int i25;
      if (XZ.a(localClass4, "getInstance", new Class[] { String.class }) != null)
      {
        i = 1;
        if (XZ.a(localClass4, "load", new Class[] { localClass5 }) == null) {
          break label1023;
        }
        j = 1;
        if (XZ.a(localClass4, "getEntry", new Class[] { String.class, localClass6 }) == null) {
          break label1028;
        }
        k = 1;
        if (XZ.a(localClass4, "getCertificate", new Class[] { String.class }) == null) {
          break label1033;
        }
        m = 1;
        if (XZ.a(localClass4, "getCreationDate", new Class[] { String.class }) == null) {
          break label1038;
        }
        n = 1;
        if (XZ.a(localClass8, "getPrivateKey", new Class[0]) == null) {
          break label1044;
        }
        i1 = 1;
        if (XZ.a(localClass3, "getAlgorithm", new Class[0]) == null) {
          break label1050;
        }
        i2 = 1;
        if (XZ.a(localClass2, "getPrivate", new Class[0]) == null) {
          break label1056;
        }
        i3 = 1;
        if (XZ.a(localClass2, "getPublic", new Class[0]) == null) {
          break label1062;
        }
        i4 = 1;
        if (XZ.a(localClass1, "getPublicKey", new Class[0]) == null) {
          break label1068;
        }
        i5 = 1;
        if (XZ.a(localClass10, "generateKeyPair", new Class[0]) == null) {
          break label1074;
        }
        i6 = 1;
        if (XZ.a(localClass10, "getInstance", new Class[] { String.class, String.class }) == null) {
          break label1080;
        }
        i7 = 1;
        if (XZ.a(localClass10, "initialize", new Class[] { localClass11 }) == null) {
          break label1086;
        }
        i9 = 1;
        if (XZ.a(localClass13, "getInstance", new Class[] { String.class }) == null) {
          break label1092;
        }
        i8 = 1;
        if (XZ.a(localClass13, "initSign", new Class[] { localClass9 }) == null) {
          break label1098;
        }
        i10 = 1;
        if (XZ.a(localClass13, "update", new Class[] { [B.class }) == null) {
          break label1104;
        }
        i11 = 1;
        if (XZ.a(localClass13, "sign", new Class[0]) == null) {
          break label1110;
        }
        i12 = 1;
        if (XZ.a(localClass12, "isKeyAlgorithmSupported", new Class[] { String.class }) == null) {
          break label1116;
        }
        i14 = 1;
        if (XZ.a(localClass15, "setAlias", new Class[] { String.class }) == null) {
          break label1122;
        }
        i13 = 1;
        if (XZ.a(localClass15, "setSubject", new Class[] { localClass16 }) == null) {
          break label1128;
        }
        i15 = 1;
        if (XZ.a(localClass15, "setSerialNumber", new Class[] { BigInteger.class }) == null) {
          break label1134;
        }
        i16 = 1;
        if (XZ.a(localClass15, "setStartDate", new Class[] { Date.class }) == null) {
          break label1140;
        }
        i17 = 1;
        if (XZ.a(localClass15, "setEndDate", new Class[] { Date.class }) == null) {
          break label1146;
        }
        i18 = 1;
        if (XZ.a(localClass15, "setKeyType", new Class[] { String.class }) == null) {
          break label1152;
        }
        i19 = 1;
        if (XZ.a(localClass12, "isBoundKeyAlgorithm", new Class[] { String.class }) == null) {
          break label1158;
        }
        i20 = 1;
        if (XZ.a(localClass18, "setDigests", new Class[] { [Ljava.lang.String.class }) == null) {
          break label1164;
        }
        i21 = 1;
        if (XZ.a(localClass18, "setSignaturePaddings", new Class[] { [Ljava.lang.String.class }) == null) {
          break label1170;
        }
        i22 = 1;
        if (XZ.a(localClass19, "getInstance", new Class[] { String.class, String.class }) == null) {
          break label1176;
        }
        i23 = 1;
        if (XZ.a(localClass19, "getKeySpec", new Class[] { localClass3, Class.class }) == null) {
          break label1182;
        }
        i24 = 1;
        if (XZ.a(localClass20, "isInsideSecureHardware", new Class[0]) == null) {
          break label1188;
        }
        i25 = 1;
        label796:
        if ((localClass7 == null) || (localClass8 == null) || (localClass9 == null) || (i == 0) || (j == 0) || (k == 0) || (i1 == 0) || (m == 0) || (n == 0) || (i2 == 0) || (i3 == 0) || (i4 == 0) || (i5 == 0) || (i6 == 0) || (i7 == 0) || (i9 == 0) || (i14 == 0)) {
          break label1194;
        }
        i = 1;
        label879:
        if ((i8 == 0) || (i10 == 0) || (i11 == 0) || (i12 == 0)) {
          break label1199;
        }
        bool = true;
        label902:
        c = bool;
        if ((a.f.a.c < 18) || (i == 0) || (localClass14 == null) || (i13 == 0) || (i15 == 0) || (i16 == 0) || (i17 == 0) || (i18 == 0) || (i19 == 0) || (i20 == 0)) {
          break label1205;
        }
        bool = true;
        label962:
        a = bool;
        if ((a.f.a.c < 23) || (i == 0) || (localClass17 == null) || (i21 == 0) || (i22 == 0) || (i23 == 0) || (i24 == 0) || (i25 == 0)) {
          break label1211;
        }
      }
      label1023:
      label1028:
      label1033:
      label1038:
      label1044:
      label1050:
      label1056:
      label1062:
      label1068:
      label1074:
      label1080:
      label1086:
      label1092:
      label1098:
      label1104:
      label1110:
      label1116:
      label1122:
      label1128:
      label1134:
      label1140:
      label1146:
      label1152:
      label1158:
      label1164:
      label1170:
      label1176:
      label1182:
      label1188:
      label1194:
      label1199:
      label1205:
      label1211:
      for (boolean bool = true;; bool = false)
      {
        b = bool;
        return;
        i = 0;
        break;
        j = 0;
        break label202;
        k = 0;
        break label228;
        m = 0;
        break label249;
        n = 0;
        break label271;
        i1 = 0;
        break label288;
        i2 = 0;
        break label305;
        i3 = 0;
        break label322;
        i4 = 0;
        break label339;
        i5 = 0;
        break label356;
        i6 = 0;
        break label373;
        i7 = 0;
        break label400;
        i9 = 0;
        break label422;
        i8 = 0;
        break label444;
        i10 = 0;
        break label466;
        i11 = 0;
        break label488;
        i12 = 0;
        break label505;
        i14 = 0;
        break label527;
        i13 = 0;
        break label549;
        i15 = 0;
        break label571;
        i16 = 0;
        break label593;
        i17 = 0;
        break label615;
        i18 = 0;
        break label637;
        i19 = 0;
        break label659;
        i20 = 0;
        break label681;
        i21 = 0;
        break label703;
        i22 = 0;
        break label725;
        i23 = 0;
        break label752;
        i24 = 0;
        break label779;
        i25 = 0;
        break label796;
        i = 0;
        break label879;
        bool = false;
        break label902;
        bool = false;
        break label962;
      }
    }
    
    static boolean a()
    {
      return (a) || (b);
    }
    
    static boolean b()
    {
      return a;
    }
    
    static boolean c()
    {
      return b;
    }
    
    static boolean d()
    {
      return c;
    }
  }
  
  private static final class k
  {
    private static final boolean a;
    private static final boolean b;
    private static final boolean c;
    private static final boolean d;
    private static final boolean e;
    private static final boolean f;
    private static final boolean g;
    private static final boolean h;
    private static final boolean i;
    
    static
    {
      boolean bool2 = true;
      Class localClass1 = XZ.a(XZ.E.D);
      if (localClass1 != null)
      {
        bool1 = true;
        a = bool1;
        Class localClass2 = XZ.a(XZ.E.E);
        if (localClass2 == null) {
          break label239;
        }
        bool1 = true;
        label32:
        b = bool1;
        if (XZ.a(localClass1, "getInt", new Class[] { String.class, Integer.TYPE }) == null) {
          break label244;
        }
        bool1 = true;
        label62:
        d = bool1;
        if (XZ.a(localClass1, "getLong", new Class[] { String.class, Long.TYPE }) == null) {
          break label249;
        }
        bool1 = true;
        label92:
        e = bool1;
        if (XZ.a(localClass1, "getString", new Class[] { String.class, String.class }) == null) {
          break label254;
        }
        bool1 = true;
        label121:
        c = bool1;
        if (XZ.a(localClass2, "putInt", new Class[] { String.class, Integer.TYPE }) == null) {
          break label259;
        }
        bool1 = true;
        label151:
        h = bool1;
        if (XZ.a(localClass2, "putLong", new Class[] { String.class, Long.TYPE }) == null) {
          break label264;
        }
        bool1 = true;
        label181:
        g = bool1;
        if (XZ.a(localClass2, "putString", new Class[] { String.class, String.class }) == null) {
          break label269;
        }
        bool1 = true;
        label210:
        f = bool1;
        if (XZ.a(localClass2, "apply", new Class[0]) == null) {
          break label274;
        }
      }
      label239:
      label244:
      label249:
      label254:
      label259:
      label264:
      label269:
      label274:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        i = bool1;
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label32;
        bool1 = false;
        break label62;
        bool1 = false;
        break label92;
        bool1 = false;
        break label121;
        bool1 = false;
        break label151;
        bool1 = false;
        break label181;
        bool1 = false;
        break label210;
      }
    }
  }
  
  static final class l
  {
    final SharedPreferences a;
    
    l(Context paramContext, String paramString)
    {
      if (a.k.a())
      {
        this.a = paramContext.getSharedPreferences(paramString, 0);
        return;
      }
      this.a = null;
    }
  }
  
  static final class m
  {
    private static final boolean a;
    private static final boolean b;
    private static final boolean c;
    private static final boolean d;
    private static final boolean e;
    private static final boolean f;
    
    static
    {
      Class localClass1 = XZ.a(XZ.E.catch);
      Class localClass2 = XZ.a(XZ.E.q);
      Class localClass3 = XZ.a(XZ.E.R);
      Class localClass4 = XZ.a(XZ.E.X);
      Class localClass5 = XZ.a(XZ.E.J);
      int i;
      int j;
      label72:
      int k;
      label88:
      int m;
      label104:
      int n;
      label121:
      int i1;
      label138:
      int i2;
      label155:
      int i3;
      label172:
      int i4;
      label189:
      int i5;
      label202:
      int i6;
      label215:
      int i7;
      label228:
      int i8;
      label241:
      int i9;
      label254:
      int i10;
      label267:
      int i11;
      if (XZ.a(localClass1, "getActiveNetworkInfo", new Class[0]) != null)
      {
        i = 1;
        if (XZ.a(localClass2, "getState", new Class[0]) == null) {
          break label480;
        }
        j = 1;
        if (XZ.a(localClass2, "getType", new Class[0]) == null) {
          break label485;
        }
        k = 1;
        if (XZ.a(localClass2, "getExtraInfo", new Class[0]) == null) {
          break label490;
        }
        m = 1;
        if (XZ.a(localClass3, "getBSSID", new Class[0]) == null) {
          break label495;
        }
        n = 1;
        if (XZ.a(localClass3, "getSSID", new Class[0]) == null) {
          break label501;
        }
        i1 = 1;
        if (XZ.a(localClass3, "getRssi", new Class[0]) == null) {
          break label507;
        }
        i2 = 1;
        if (XZ.a(localClass4, "getConnectionInfo", new Class[0]) == null) {
          break label513;
        }
        i3 = 1;
        if (XZ.a(localClass2, "isConnectedOrConnecting", new Class[0]) == null) {
          break label519;
        }
        i4 = 1;
        if (XZ.a(localClass1, "CONNECTIVITY_ACTION") == null) {
          break label525;
        }
        i5 = 1;
        if (XZ.a(localClass1, "TYPE_MOBILE") == null) {
          break label531;
        }
        i6 = 1;
        if (XZ.a(localClass1, "TYPE_WIFI") == null) {
          break label537;
        }
        i7 = 1;
        if (XZ.a(localClass1, "TYPE_BLUETOOTH") == null) {
          break label543;
        }
        i8 = 1;
        if (XZ.a(localClass1, "TYPE_ETHERNET") == null) {
          break label549;
        }
        i9 = 1;
        if (XZ.a(localClass4, "NETWORK_STATE_CHANGED_ACTION") == null) {
          break label555;
        }
        i10 = 1;
        if (XZ.a(localClass5, "CONNECTED") == null) {
          break label561;
        }
        i11 = 1;
        label280:
        if ((i == 0) || (i4 == 0)) {
          break label567;
        }
        bool = true;
        label292:
        a = bool;
        if (XZ.a(localClass4, "getScanResults", new Class[0]) == null) {
          break label573;
        }
        bool = true;
        label314:
        e = bool;
        if ((!bool) || (XZ.a(localClass4, "startScan", new Class[0]) == null)) {
          break label579;
        }
        bool = true;
        label341:
        f = bool;
        if ((i5 == 0) || (i11 == 0) || (j == 0) || (m == 0) || (k == 0) || (i6 == 0) || (i7 == 0) || ((a.f.a.c >= a.f.b.f) && ((i9 == 0) || (i8 == 0)))) {
          break label585;
        }
        bool = true;
        label400:
        b = bool;
        if ((i10 == 0) || (i11 == 0) || (n == 0) || (i1 == 0) || (i2 == 0) || (j == 0) || (m == 0)) {
          break label591;
        }
        bool = true;
        label441:
        c = bool;
        if ((i3 == 0) || (n == 0) || (i1 == 0) || (i2 == 0)) {
          break label597;
        }
      }
      label480:
      label485:
      label490:
      label495:
      label501:
      label507:
      label513:
      label519:
      label525:
      label531:
      label537:
      label543:
      label549:
      label555:
      label561:
      label567:
      label573:
      label579:
      label585:
      label591:
      label597:
      for (boolean bool = true;; bool = false)
      {
        d = bool;
        return;
        i = 0;
        break;
        j = 0;
        break label72;
        k = 0;
        break label88;
        m = 0;
        break label104;
        n = 0;
        break label121;
        i1 = 0;
        break label138;
        i2 = 0;
        break label155;
        i3 = 0;
        break label172;
        i4 = 0;
        break label189;
        i5 = 0;
        break label202;
        i6 = 0;
        break label215;
        i7 = 0;
        break label228;
        i8 = 0;
        break label241;
        i9 = 0;
        break label254;
        i10 = 0;
        break label267;
        i11 = 0;
        break label280;
        bool = false;
        break label292;
        bool = false;
        break label314;
        bool = false;
        break label341;
        bool = false;
        break label400;
        bool = false;
        break label441;
      }
    }
    
    static boolean a()
    {
      return a;
    }
    
    static boolean b()
    {
      return b;
    }
    
    static boolean c()
    {
      return c;
    }
    
    static boolean d()
    {
      return d;
    }
    
    static boolean e()
    {
      return e;
    }
    
    static boolean f()
    {
      return f;
    }
  }
  
  static final class n
  {
    static final boolean a;
    
    static
    {
      boolean bool = false;
      if (XZ.a(XZ.a(XZ.E.float), "getStorageEncryptionStatus", new Class[0]) != null) {
        bool = true;
      }
      a = bool;
    }
  }
  
  static final class o
  {
    private static final boolean a;
    private static final boolean b;
    
    static
    {
      boolean bool2 = true;
      Class localClass = XZ.a(XZ.E.H);
      if (XZ.a(localClass, "uptimeMillis", new Class[0]) != null)
      {
        bool1 = true;
        a = bool1;
        if (XZ.a(localClass, "elapsedRealtime", new Class[0]) == null) {
          break label53;
        }
      }
      label53:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        b = bool1;
        return;
        bool1 = false;
        break;
      }
    }
    
    static long a()
    {
      if (a) {
        return SystemClock.uptimeMillis();
      }
      return 0L;
    }
    
    static long b()
    {
      if (b) {
        return SystemClock.elapsedRealtime();
      }
      return 0L;
    }
  }
  
  static final class p
  {
    private static final boolean a;
    private static final boolean b;
    
    static
    {
      boolean bool2 = true;
      Class localClass = XZ.a(XZ.E.t);
      if (XZ.a(localClass, "isInteractive", new Class[0]) != null)
      {
        bool1 = true;
        a = bool1;
        if (XZ.a(localClass, "isScreenOn", new Class[0]) == null) {
          break label53;
        }
      }
      label53:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        b = bool1;
        return;
        bool1 = false;
        break;
      }
    }
    
    static boolean a()
    {
      return b;
    }
    
    static boolean b()
    {
      return a;
    }
  }
}
