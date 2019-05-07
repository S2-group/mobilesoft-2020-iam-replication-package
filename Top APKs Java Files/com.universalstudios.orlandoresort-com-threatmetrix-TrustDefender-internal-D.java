package com.threatmetrix.TrustDefender.internal;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
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
public class D
{
  private static final String jdField_if = PH.jdMethod_do(D.class);
  
  D() {}
  
  final class A
  {
    PackageInfo jdField_if = null;
    
    A(Context paramContext, String paramString, int paramInt)
    {
      if ((D.I.jdMethod_for()) && (D.I.jdMethod_do())) {}
      try
      {
        this.jdField_if = paramContext.getPackageManager().getPackageInfo(paramString, paramInt);
        return;
      }
      catch (Exception this$1)
      {
        PH.jdMethod_if(D.jdMethod_int(), D.this.toString());
        return;
        D.jdMethod_int();
        return;
        D.jdMethod_int();
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
  }
  
  static final class B
  {
    private static final boolean jdField_byte;
    private static final boolean jdField_case;
    private static final boolean jdField_char;
    private static final boolean jdField_do;
    private static final boolean jdField_for;
    private static final boolean jdField_if;
    private static final boolean jdField_int;
    private static final boolean jdField_new;
    private static final boolean jdField_try;
    
    static
    {
      Class localClass1 = KF.jdMethod_int(KF.E.E);
      boolean bool2 = true;
      boolean bool1;
      if (localClass1 != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      new = bool1;
      Class localClass2 = KF.jdMethod_int(KF.E.B);
      if (localClass2 != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      if = bool1;
      if (KF.jdMethod_for(localClass1, "getInt", new Class[] { String.class, Integer.TYPE }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      for = bool1;
      if (KF.jdMethod_for(localClass1, "getLong", new Class[] { String.class, Long.TYPE }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      do = bool1;
      if (KF.jdMethod_for(localClass1, "getString", new Class[] { String.class, String.class }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      int = bool1;
      if (KF.jdMethod_for(localClass2, "putInt", new Class[] { String.class, Integer.TYPE }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      case = bool1;
      if (KF.jdMethod_for(localClass2, "putLong", new Class[] { String.class, Long.TYPE }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      char = bool1;
      if (KF.jdMethod_for(localClass2, "putString", new Class[] { String.class, String.class }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      byte = bool1;
      if (KF.jdMethod_for(localClass2, "apply", new Class[0]) != null) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
      try = bool1;
    }
  }
  
  public static final class C
  {
    private static final boolean jdField_int;
    
    static
    {
      Class localClass1 = KF.jdMethod_int(KF.E.S);
      Class localClass2 = KF.jdMethod_int(KF.E.R);
      Class localClass3 = KF.jdMethod_int(KF.E.Q);
      Class localClass4 = KF.jdMethod_int(KF.E.U);
      Class localClass5 = KF.jdMethod_int(KF.E.O);
      Method localMethod = KF.jdMethod_for(KF.jdMethod_int(KF.E.interface), "confirm", new Class[0]);
      boolean bool = true;
      int i;
      if (localMethod != null) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if (KF.jdMethod_for(localClass1, "destroy", new Class[0]) != null) {
        j = 1;
      } else {
        j = 0;
      }
      int k;
      if (KF.jdMethod_for(localClass1, "loadUrl", new Class[] { String.class }) != null) {
        k = 1;
      } else {
        k = 0;
      }
      int m;
      if (KF.jdMethod_for(localClass1, "loadData", new Class[] { String.class, String.class, String.class }) != null) {
        m = 1;
      } else {
        m = 0;
      }
      int n;
      if (KF.jdMethod_for(localClass1, "getSettings", new Class[0]) != null) {
        n = 1;
      } else {
        n = 0;
      }
      int i1;
      if (KF.jdMethod_for(localClass1, "setWebViewClient", new Class[] { localClass2 }) != null) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      int i2;
      if (KF.jdMethod_for(localClass1, "setWebChromeClient", new Class[] { localClass5 }) != null) {
        i2 = 1;
      } else {
        i2 = 0;
      }
      int i3;
      if (KF.jdMethod_for(localClass3, "getUserAgentString", new Class[0]) != null) {
        i3 = 1;
      } else {
        i3 = 0;
      }
      int i4;
      if (KF.jdMethod_for(localClass3, "setJavaScriptEnabled", new Class[] { Boolean.TYPE }) != null) {
        i4 = 1;
      } else {
        i4 = 0;
      }
      int i5;
      if (KF.jdMethod_int(localClass4, "ON") != null) {
        i5 = 1;
      } else {
        i5 = 0;
      }
      if ((localClass2 == null) || (localClass5 == null) || (i == 0) || (j == 0) || (k == 0) || (m == 0) || (n == 0) || (i1 == 0) || (i2 == 0) || (i3 == 0) || (i4 == 0) || (i5 == 0)) {
        bool = false;
      }
      int = bool;
    }
    
    C() {}
    
    public static boolean jdMethod_do()
    {
      return int;
    }
  }
  
  static final class D
  {
    private static final boolean jdField_byte;
    private static final boolean jdField_do;
    private static final boolean jdField_else;
    private static final boolean jdField_for;
    private static final boolean jdField_if;
    private static final boolean jdField_int;
    private static final boolean jdField_new;
    private static final boolean jdField_try;
    
    static
    {
      Class localClass = KF.jdMethod_int(KF.E.A);
      boolean bool2 = false;
      if (KF.jdMethod_for(localClass, "getString", new Class[] { ContentResolver.class, String.class }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      int = bool1;
      if (KF.jdMethod_int(localClass, "ANDROID_ID") != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      if = bool1;
      if (KF.jdMethod_int(localClass, "ALLOW_MOCK_LOCATION") != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      for = bool1;
      if (KF.jdMethod_int(localClass, "ADB_ENABLED") != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      new = bool1;
      if (KF.jdMethod_int(localClass, "DEVELOPMENT_SETTINGS_ENABLED") != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      do = bool1;
      localClass = KF.jdMethod_int(KF.E.F);
      if (KF.jdMethod_for(localClass, "getString", new Class[] { ContentResolver.class, String.class }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      try = bool1;
      if (KF.jdMethod_int(localClass, "ADB_ENABLED") != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      else = bool1;
      boolean bool1 = bool2;
      if (KF.jdMethod_int(localClass, "DEVELOPMENT_SETTINGS_ENABLED") != null) {
        bool1 = true;
      }
      byte = bool1;
    }
    
    D() {}
    
    static String jdMethod_do(ContentResolver paramContentResolver, String paramString)
    {
      if ((paramContentResolver != null) && (!NK.jdMethod_if(paramString))) {
        if (!try) {
          return null;
        }
      }
      try
      {
        if (("adb_enabled".equals(paramString)) && (else)) {
          return Settings.Global.getString(paramContentResolver, "adb_enabled");
        }
        if (("development_settings_enabled".equals(paramString)) && (byte))
        {
          paramContentResolver = Settings.Global.getString(paramContentResolver, "development_settings_enabled");
          return paramContentResolver;
        }
      }
      catch (Exception paramContentResolver)
      {
        PH.jdMethod_if(D.jdMethod_int(), paramContentResolver.toString());
        return null;
        D.jdMethod_int();
        return null;
        return null;
      }
      catch (SecurityException paramContentResolver)
      {
        for (;;) {}
      }
    }
    
    static String jdMethod_new(ContentResolver paramContentResolver, String paramString)
    {
      if ((paramContentResolver != null) && (!NK.jdMethod_if(paramString))) {
        if (!int) {
          return null;
        }
      }
      try
      {
        String str = Settings.Secure.getString(paramContentResolver, paramString);
        if (str != null) {
          return str;
        }
        if (("android_id".equals(paramString)) && (if)) {
          return Settings.Secure.getString(paramContentResolver, "android_id");
        }
        if (("mock_location".equals(paramString)) && (for)) {
          return Settings.Secure.getString(paramContentResolver, "mock_location");
        }
        if (("adb_enabled".equals(paramString)) && (new)) {
          return Settings.Secure.getString(paramContentResolver, "adb_enabled");
        }
        if (("development_settings_enabled".equals(paramString)) && (do) && (D.O.E.if >= D.O.L.case))
        {
          paramContentResolver = Settings.Secure.getString(paramContentResolver, "development_settings_enabled");
          return paramContentResolver;
        }
      }
      catch (Exception paramContentResolver)
      {
        PH.jdMethod_if(D.jdMethod_int(), paramContentResolver.toString());
        return null;
        D.jdMethod_int();
        return null;
        return null;
      }
      catch (SecurityException paramContentResolver)
      {
        for (;;) {}
      }
    }
  }
  
  static final class E
  {
    ApplicationInfo jdField_new = null;
    
    E(V.E paramE)
    {
      if ((D.I.jdMethod_byte()) && (D.I.jdMethod_case())) {
        this.jdField_new = paramE.jdField_do.getApplicationInfo();
      }
    }
  }
  
  static final class I
  {
    private static final boolean jdField_byte;
    private static final boolean jdField_case;
    private static final boolean jdField_char;
    static final int jdField_do = 1;
    private static final boolean jdField_else;
    private static final boolean jdField_for;
    private static final boolean jdField_if;
    private static final boolean jdField_int;
    static final int jdField_new = 128;
    
    static
    {
      Class localClass1 = KF.jdMethod_int(KF.E.o);
      boolean bool2 = false;
      if (localClass1 != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      for = bool1;
      Class localClass2 = KF.jdMethod_int(KF.E.n);
      if (localClass2 != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      byte = bool1;
      if (KF.jdMethod_for(localClass1, "checkPermission", new Class[] { String.class, String.class }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      char = bool1;
      if (KF.jdMethod_int(localClass2, "versionCode") != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      case = bool1;
      if (KF.jdMethod_int(localClass2, "versionName") != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      else = bool1;
      if (KF.jdMethod_int(KF.E.int) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      if = bool1;
      boolean bool1 = bool2;
      if (KF.jdMethod_int(KF.E.v) != null) {
        bool1 = true;
      }
      int = bool1;
    }
    
    I() {}
  }
  
  static final class K
  {
    private static final boolean jdField_char;
    private static final boolean jdField_do;
    private static final boolean jdField_for;
    private static final boolean jdField_if;
    private static final boolean jdField_int;
    private static final boolean jdField_new;
    
    static
    {
      Class localClass1 = KF.jdMethod_int(KF.E.catch);
      Class localClass2 = KF.jdMethod_int(KF.E.p);
      Class localClass3 = KF.jdMethod_int(KF.E.T);
      Class localClass4 = KF.jdMethod_int(KF.E.Y);
      Class localClass5 = KF.jdMethod_int(KF.E.J);
      int j;
      if (KF.jdMethod_for(localClass1, "getActiveNetworkInfo", new Class[0]) != null) {
        j = 1;
      } else {
        j = 0;
      }
      int k;
      if (KF.jdMethod_for(localClass2, "getState", new Class[0]) != null) {
        k = 1;
      } else {
        k = 0;
      }
      int m;
      if (KF.jdMethod_for(localClass2, "getType", new Class[0]) != null) {
        m = 1;
      } else {
        m = 0;
      }
      int n;
      if (KF.jdMethod_for(localClass2, "getExtraInfo", new Class[0]) != null) {
        n = 1;
      } else {
        n = 0;
      }
      int i1;
      if (KF.jdMethod_for(localClass3, "getBSSID", new Class[0]) != null) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      int i2;
      if (KF.jdMethod_for(localClass3, "getSSID", new Class[0]) != null) {
        i2 = 1;
      } else {
        i2 = 0;
      }
      int i;
      if (KF.jdMethod_for(localClass3, "getRssi", new Class[0]) != null) {
        i = 1;
      } else {
        i = 0;
      }
      int i3;
      if (KF.jdMethod_for(localClass4, "getConnectionInfo", new Class[0]) != null) {
        i3 = 1;
      } else {
        i3 = 0;
      }
      int i4;
      if (KF.jdMethod_for(localClass2, "isConnectedOrConnecting", new Class[0]) != null) {
        i4 = 1;
      } else {
        i4 = 0;
      }
      int i5;
      if (KF.jdMethod_int(localClass1, "CONNECTIVITY_ACTION") != null) {
        i5 = 1;
      } else {
        i5 = 0;
      }
      int i6;
      if (KF.jdMethod_int(localClass1, "TYPE_MOBILE") != null) {
        i6 = 1;
      } else {
        i6 = 0;
      }
      int i7;
      if (KF.jdMethod_int(localClass1, "TYPE_WIFI") != null) {
        i7 = 1;
      } else {
        i7 = 0;
      }
      int i8;
      if (KF.jdMethod_int(localClass1, "TYPE_BLUETOOTH") != null) {
        i8 = 1;
      } else {
        i8 = 0;
      }
      int i9;
      if (KF.jdMethod_int(localClass1, "TYPE_ETHERNET") != null) {
        i9 = 1;
      } else {
        i9 = 0;
      }
      int i10;
      if (KF.jdMethod_int(localClass4, "NETWORK_STATE_CHANGED_ACTION") != null) {
        i10 = 1;
      } else {
        i10 = 0;
      }
      int i11 = i;
      if (KF.jdMethod_int(localClass5, "CONNECTED") != null) {
        i = 1;
      } else {
        i = 0;
      }
      boolean bool;
      if ((j != 0) && (i4 != 0)) {
        bool = true;
      } else {
        bool = false;
      }
      for = bool;
      if (KF.jdMethod_for(localClass4, "getScanResults", new Class[0]) != null) {
        bool = true;
      } else {
        bool = false;
      }
      if = bool;
      if ((bool) && (KF.jdMethod_for(localClass4, "startScan", new Class[0]) != null)) {
        bool = true;
      } else {
        bool = false;
      }
      char = bool;
      if ((i5 != 0) && (i != 0) && (k != 0) && (n != 0) && (m != 0) && (i6 != 0) && (i7 != 0) && ((D.O.E.if < D.O.L.try) || ((i9 != 0) && (i8 != 0)))) {
        bool = true;
      } else {
        bool = false;
      }
      do = bool;
      if ((i10 != 0) && (i != 0) && (i1 != 0) && (i2 != 0) && (i11 != 0) && (k != 0) && (n != 0)) {
        bool = true;
      } else {
        bool = false;
      }
      int = bool;
      if ((i3 != 0) && (i1 != 0) && (i2 != 0) && (i11 != 0)) {
        bool = true;
      } else {
        bool = false;
      }
      new = bool;
    }
    
    K() {}
    
    static boolean jdMethod_do()
    {
      return if;
    }
    
    static boolean jdMethod_else()
    {
      return char;
    }
    
    static boolean jdMethod_for()
    {
      return new;
    }
    
    static boolean jdMethod_if()
    {
      return do;
    }
    
    static boolean jdMethod_int()
    {
      return int;
    }
    
    static boolean jdMethod_new()
    {
      return for;
    }
  }
  
  static final class L
  {
    static final boolean jdField_if;
    
    static
    {
      Class localClass = KF.jdMethod_int(KF.E.class);
      boolean bool = false;
      if (KF.jdMethod_for(localClass, "getStorageEncryptionStatus", new Class[0]) != null) {
        bool = true;
      }
      if = bool;
    }
    
    L() {}
  }
  
  static final class N
  {
    private static final boolean jdField_do;
    private static final boolean jdField_for;
    
    static
    {
      Class localClass = KF.jdMethod_int(KF.E.G);
      boolean bool2 = false;
      if (KF.jdMethod_for(localClass, "uptimeMillis", new Class[0]) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      do = bool1;
      boolean bool1 = bool2;
      if (KF.jdMethod_for(localClass, "elapsedRealtime", new Class[0]) != null) {
        bool1 = true;
      }
      for = bool1;
    }
    
    N() {}
    
    static long jdMethod_if()
    {
      if (for) {
        return SystemClock.elapsedRealtime();
      }
      return 0L;
    }
    
    static long jdMethod_new()
    {
      if (do) {
        return SystemClock.uptimeMillis();
      }
      return 0L;
    }
  }
  
  static final class O
  {
    static final String jdField_break;
    static final String jdField_byte;
    static final String jdField_case;
    static final String jdField_char;
    private static final Class<?> const;
    static final String jdField_do;
    static final String jdField_else;
    static final boolean jdField_float;
    static final String jdField_for;
    static final String jdField_goto;
    static final String jdField_if;
    static final long jdField_int;
    static final String jdField_long;
    static final String jdField_new;
    static final String jdField_this;
    static final String jdField_try;
    static final Method jdField_void;
    
    static
    {
      Object localObject1 = KF.jdMethod_int(KF.E.for);
      const = (Class)localObject1;
      long l;
      if (KF.jdMethod_int((Class)localObject1, "TIME") != null) {
        l = Build.TIME;
      } else {
        l = Long.MAX_VALUE;
      }
      int = l;
      localObject1 = KF.jdMethod_int(const, "TYPE");
      Object localObject2 = null;
      if (localObject1 != null) {
        localObject1 = Build.TYPE;
      } else {
        localObject1 = null;
      }
      for = (String)localObject1;
      if (KF.jdMethod_int(const, "TAGS") != null) {
        localObject1 = Build.TAGS;
      } else {
        localObject1 = null;
      }
      new = (String)localObject1;
      if (KF.jdMethod_int(const, "HOST") != null) {
        localObject1 = Build.HOST;
      } else {
        localObject1 = null;
      }
      if = (String)localObject1;
      if (KF.jdMethod_int(const, "BRAND") != null) {
        localObject1 = Build.BRAND;
      } else {
        localObject1 = null;
      }
      do = (String)localObject1;
      if (KF.jdMethod_int(const, "USER") != null) {
        localObject1 = Build.USER;
      } else {
        localObject1 = null;
      }
      case = (String)localObject1;
      if (KF.jdMethod_int(const, "ID") != null) {
        localObject1 = Build.ID;
      } else {
        localObject1 = null;
      }
      else = (String)localObject1;
      if (KF.jdMethod_int(const, "SERIAL") != null) {
        localObject1 = Build.SERIAL;
      } else {
        localObject1 = null;
      }
      try = (String)localObject1;
      if (KF.jdMethod_int(const, "DEVICE") != null) {
        localObject1 = Build.DEVICE;
      } else {
        localObject1 = null;
      }
      char = (String)localObject1;
      if (KF.jdMethod_int(const, "MODEL") != null) {
        localObject1 = Build.MODEL;
      } else {
        localObject1 = null;
      }
      byte = (String)localObject1;
      if (KF.jdMethod_int(const, "DISPLAY") != null) {
        localObject1 = Build.DISPLAY;
      } else {
        localObject1 = null;
      }
      goto = (String)localObject1;
      if (KF.jdMethod_int(const, "PRODUCT") != null) {
        localObject1 = Build.PRODUCT;
      } else {
        localObject1 = null;
      }
      long = (String)localObject1;
      if (KF.jdMethod_int(const, "MANUFACTURER") != null) {
        localObject1 = Build.MANUFACTURER;
      } else {
        localObject1 = null;
      }
      this = (String)localObject1;
      localObject1 = localObject2;
      if (KF.jdMethod_int(const, "BOARD") != null) {
        localObject1 = Build.BOARD;
      }
      break = (String)localObject1;
      localObject1 = const;
      boolean bool2 = false;
      void = KF.jdMethod_for((Class)localObject1, "getSerial", new Class[0]);
      boolean bool1 = bool2;
      if (E.if >= L.case)
      {
        bool1 = bool2;
        if (E.if <= L.this) {
          bool1 = true;
        }
      }
      float = bool1;
    }
    
    O() {}
    
    static String jdMethod_for()
    {
      if (void != null)
      {
        Object localObject = KF.jdMethod_int(null, void, new Object[0]);
        if (localObject != null) {
          return (String)localObject;
        }
      }
      return null;
    }
    
    static final class E
    {
      static final String jdField_for;
      static final int jdField_if;
      static final String jdField_int;
      
      static
      {
        Class localClass = KF.jdMethod_int(KF.E.N);
        Object localObject1 = KF.jdMethod_int(localClass, "RELEASE");
        Object localObject2 = null;
        if (localObject1 != null) {
          localObject1 = Build.VERSION.RELEASE;
        } else {
          localObject1 = null;
        }
        for = (String)localObject1;
        int i;
        if (KF.jdMethod_int(localClass, "SDK_INT") != null) {
          i = Build.VERSION.SDK_INT;
        } else {
          i = -1;
        }
        if = i;
        localObject1 = localObject2;
        if (KF.jdMethod_int(localClass, "CODENAME") != null) {
          localObject1 = Build.VERSION.CODENAME;
        }
        int = (String)localObject1;
      }
      
      E() {}
    }
    
    static final class L
    {
      static final int jdField_break;
      static final int jdField_byte;
      static final int jdField_case;
      static final int jdField_char;
      static final int jdField_class;
      static final int const;
      static final int jdField_do;
      static final int jdField_else;
      private static final Class<?> jdField_final;
      static final int jdField_float = 25;
      static final int jdField_for;
      static final int jdField_goto;
      static final int jdField_if;
      static final int jdField_int;
      static final int jdField_long;
      static final int jdField_new;
      static final int jdField_this;
      static final int jdField_try;
      static final int jdField_void;
      
      static
      {
        Class localClass = KF.jdMethod_int(KF.E.L);
        final = localClass;
        KF.jdMethod_int(localClass, "FROYO");
        for = 8;
        KF.jdMethod_int(final, "GINGERBREAD");
        if = 9;
        KF.jdMethod_int(final, "GINGERBREAD_MR1");
        do = 10;
        KF.jdMethod_int(final, "HONEYCOMB");
        new = 11;
        KF.jdMethod_int(final, "HONEYCOMB_MR1");
        int = 12;
        KF.jdMethod_int(final, "HONEYCOMB_MR2");
        try = 13;
        KF.jdMethod_int(final, "ICE_CREAM_SANDWICH");
        else = 14;
        KF.jdMethod_int(final, "ICE_CREAM_SANDWICH_MR1");
        byte = 15;
        KF.jdMethod_int(final, "JELLY_BEAN");
        case = 16;
        KF.jdMethod_int(final, "JELLY_BEAN_MR1");
        char = 17;
        KF.jdMethod_int(final, "JELLY_BEAN_MR2");
        goto = 18;
        KF.jdMethod_int(final, "KITKAT");
        this = 19;
        KF.jdMethod_int(final, "KITKAT_WATCH");
        void = 20;
        KF.jdMethod_int(final, "LOLLIPOP");
        break = 21;
        KF.jdMethod_int(final, "LOLLIPOP_MR1");
        long = 22;
        KF.jdMethod_int(final, "M");
        class = 23;
        KF.jdMethod_int(final, "N");
        const = 24;
        KF.jdMethod_int(final, "N_MR1");
      }
      
      L() {}
    }
  }
  
  final class R
  {
    private PackageManager jdField_do = null;
    
    R(Context paramContext)
    {
      if (D.I.jdMethod_do()) {}
      try
      {
        this.jdField_do = paramContext.getPackageManager();
        return;
      }
      catch (Exception this$1)
      {
        PH.jdMethod_if(D.jdMethod_int(), D.this.toString());
        return;
        D.jdMethod_int();
        return;
        return;
      }
      catch (SecurityException this$1)
      {
        for (;;) {}
      }
    }
    
    /* Error */
    final java.util.ArrayList<String> jdMethod_do()
    {
      // Byte code:
      //   0: new 51	java/util/ArrayList
      //   3: dup
      //   4: invokespecial 52	java/util/ArrayList:<init>	()V
      //   7: astore_1
      //   8: invokestatic 29	com/threatmetrix/TrustDefender/internal/D$I:do	()Z
      //   11: ifeq +103 -> 114
      //   14: invokestatic 55	com/threatmetrix/TrustDefender/internal/D$I:byte	()Z
      //   17: ifeq +97 -> 114
      //   20: aload_0
      //   21: getfield 24	com/threatmetrix/TrustDefender/internal/D$R:do	Landroid/content/pm/PackageManager;
      //   24: ifnull +90 -> 114
      //   27: aload_0
      //   28: getfield 24	com/threatmetrix/TrustDefender/internal/D$R:do	Landroid/content/pm/PackageManager;
      //   31: iconst_0
      //   32: invokevirtual 61	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   35: invokeinterface 67 1 0
      //   40: astore_2
      //   41: aload_2
      //   42: invokeinterface 72 1 0
      //   47: ifeq +67 -> 114
      //   50: aload_2
      //   51: invokeinterface 76 1 0
      //   56: checkcast 78	android/content/pm/ApplicationInfo
      //   59: astore_3
      //   60: aload_3
      //   61: getfield 82	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
      //   64: ldc 84
      //   66: invokevirtual 90	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   69: ifne -28 -> 41
      //   72: aload_3
      //   73: getfield 82	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
      //   76: ldc 92
      //   78: invokevirtual 90	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   81: ifne -40 -> 41
      //   84: aload_1
      //   85: aload_3
      //   86: getfield 82	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
      //   89: invokevirtual 98	java/util/AbstractCollection:add	(Ljava/lang/Object;)Z
      //   92: pop
      //   93: goto -52 -> 41
      //   96: astore_2
      //   97: invokestatic 38	com/threatmetrix/TrustDefender/internal/D:int	()Ljava/lang/String;
      //   100: aload_2
      //   101: invokevirtual 41	java/lang/Object:toString	()Ljava/lang/String;
      //   104: invokestatic 47	com/threatmetrix/TrustDefender/internal/PH:if	(Ljava/lang/String;Ljava/lang/String;)V
      //   107: goto +7 -> 114
      //   110: invokestatic 38	com/threatmetrix/TrustDefender/internal/D:int	()Ljava/lang/String;
      //   113: pop
      //   114: aload_1
      //   115: ldc 84
      //   117: invokevirtual 98	java/util/AbstractCollection:add	(Ljava/lang/Object;)Z
      //   120: pop
      //   121: aload_1
      //   122: ldc 92
      //   124: invokevirtual 98	java/util/AbstractCollection:add	(Ljava/lang/Object;)Z
      //   127: pop
      //   128: aload_1
      //   129: areturn
      //   130: astore_2
      //   131: goto -21 -> 110
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	134	0	this	R
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
    
    final boolean jdMethod_do(String paramString1, String paramString2)
    {
      if ((D.I.jdMethod_if()) && (this.jdField_do != null)) {}
      try
      {
        int i = this.jdField_do.checkPermission(paramString1, paramString2);
        return i == 0;
      }
      catch (Exception paramString1)
      {
        PH.jdMethod_if(D.jdMethod_int(), paramString1.toString());
        return false;
        D.jdMethod_int();
        return false;
      }
      catch (SecurityException paramString1)
      {
        for (;;) {}
      }
    }
    
    final boolean jdMethod_int(String paramString, int paramInt)
    {
      if ((D.I.jdMethod_do()) && (D.I.jdMethod_for()) && (this.jdField_do != null)) {}
      try
      {
        this.jdField_do.getPackageInfo(paramString, paramInt);
        return true;
      }
      catch (Exception paramString)
      {
        PH.jdMethod_if(D.jdMethod_int(), paramString.toString());
        break label56;
        D.jdMethod_int();
        break label56;
        D.jdMethod_int();
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
  }
  
  static final class S
  {
    private static final boolean jdField_if;
    private static final boolean jdField_int;
    
    static
    {
      Class localClass = KF.jdMethod_int(KF.E.s);
      boolean bool2 = false;
      if (KF.jdMethod_for(localClass, "isInteractive", new Class[0]) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      if = bool1;
      boolean bool1 = bool2;
      if (KF.jdMethod_for(localClass, "isScreenOn", new Class[0]) != null) {
        bool1 = true;
      }
      int = bool1;
    }
    
    S() {}
    
    static boolean jdMethod_do()
    {
      return int;
    }
    
    static boolean jdMethod_int()
    {
      return if;
    }
  }
  
  public static final class T
  {
    private static final boolean jdField_for;
    private static final boolean jdField_int;
    
    static
    {
      Class localClass1 = KF.jdMethod_int(KF.E.float);
      Class localClass2 = KF.jdMethod_int(KF.E.l);
      Class localClass3 = KF.jdMethod_int(KF.E.j);
      Class localClass4 = KF.jdMethod_int(KF.E.i);
      int i;
      if (KF.jdMethod_for(localClass1, "setAccuracy", new Class[] { Integer.TYPE }) != null) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if (KF.jdMethod_for(localClass1, "setAltitudeRequired", new Class[] { Boolean.TYPE }) != null) {
        j = 1;
      } else {
        j = 0;
      }
      int k;
      if (KF.jdMethod_for(localClass1, "setBearingAccuracy", new Class[] { Integer.TYPE }) != null) {
        k = 1;
      } else {
        k = 0;
      }
      int m;
      if (KF.jdMethod_for(localClass1, "setCostAllowed", new Class[] { Boolean.TYPE }) != null) {
        m = 1;
      } else {
        m = 0;
      }
      int n;
      if (KF.jdMethod_for(localClass1, "setSpeedAccuracy", new Class[] { Integer.TYPE }) != null) {
        n = 1;
      } else {
        n = 0;
      }
      int i1;
      if (KF.jdMethod_for(localClass1, "setSpeedRequired", new Class[] { Boolean.TYPE }) != null) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      int i2;
      if (KF.jdMethod_for(localClass1, "setVerticalAccuracy", new Class[] { Integer.TYPE }) != null) {
        i2 = 1;
      } else {
        i2 = 0;
      }
      int i3;
      if (KF.jdMethod_for(localClass1, "setPowerRequirement", new Class[] { Integer.TYPE }) != null) {
        i3 = 1;
      } else {
        i3 = 0;
      }
      int i4;
      if (KF.jdMethod_for(localClass2, "getTime", new Class[0]) != null) {
        i4 = 1;
      } else {
        i4 = 0;
      }
      int i5;
      if (KF.jdMethod_for(localClass2, "getProvider", new Class[0]) != null) {
        i5 = 1;
      } else {
        i5 = 0;
      }
      int i6;
      if (KF.jdMethod_for(localClass2, "getAccuracy", new Class[0]) != null) {
        i6 = 1;
      } else {
        i6 = 0;
      }
      int i7;
      if (KF.jdMethod_for(localClass2, "getLatitude", new Class[0]) != null) {
        i7 = 1;
      } else {
        i7 = 0;
      }
      int i8;
      if (KF.jdMethod_for(localClass2, "getLongitude", new Class[0]) != null) {
        i8 = 1;
      } else {
        i8 = 0;
      }
      int i9;
      if (KF.jdMethod_int(localClass1, "NO_REQUIREMENT") != null) {
        i9 = 1;
      } else {
        i9 = 0;
      }
      int i10;
      if (KF.jdMethod_int(localClass1, "POWER_LOW") != null) {
        i10 = 1;
      } else {
        i10 = 0;
      }
      int i11;
      if (KF.jdMethod_int(localClass1, "ACCURACY_LOW") != null) {
        i11 = 1;
      } else {
        i11 = 0;
      }
      int i12;
      if (KF.jdMethod_int(localClass1, "ACCURACY_COARSE") != null) {
        i12 = 1;
      } else {
        i12 = 0;
      }
      int i13;
      if (KF.jdMethod_int(localClass3, "AVAILABLE") != null) {
        i13 = 1;
      } else {
        i13 = 0;
      }
      int i14;
      if (KF.jdMethod_int(localClass3, "TEMPORARILY_UNAVAILABLE") != null) {
        i14 = 1;
      } else {
        i14 = 0;
      }
      int i15;
      if (KF.jdMethod_int(localClass3, "OUT_OF_SERVICE") != null) {
        i15 = 1;
      } else {
        i15 = 0;
      }
      boolean bool;
      if ((i != 0) && (j != 0) && (k != 0) && (m != 0) && (n != 0) && (i1 != 0) && (i2 != 0) && (i3 != 0) && (i9 != 0) && (i10 != 0) && (i11 != 0) && (i12 != 0)) {
        bool = true;
      } else {
        bool = false;
      }
      for = bool;
      if ((localClass4 != null) && (i4 != 0) && (i5 != 0) && (i7 != 0) && (i8 != 0) && (i6 != 0) && (i13 != 0) && (i14 != 0) && (i15 != 0)) {
        bool = true;
      } else {
        bool = false;
      }
      int = bool;
    }
    
    T() {}
    
    static boolean jdMethod_for()
    {
      return for;
    }
    
    public static boolean jdMethod_if()
    {
      return int;
    }
  }
  
  static final class W
  {
    private static final boolean jdField_do;
    private static final boolean jdField_for;
    private static final boolean jdField_if;
    
    static
    {
      Class localClass1 = KF.jdMethod_int(KF.E.const);
      Class localClass2 = KF.jdMethod_int(KF.E.implements);
      Class localClass3 = KF.jdMethod_int(KF.E.strictfp);
      Class localClass4 = KF.jdMethod_int(KF.E.d);
      Class localClass5 = KF.jdMethod_int(KF.E.k);
      Class localClass6 = KF.jdMethod_int(KF.E.y);
      Class localClass7 = KF.jdMethod_int(KF.E.b);
      Class localClass8 = KF.jdMethod_int(KF.E.w);
      Class localClass9 = KF.jdMethod_int(KF.E.r);
      Class localClass10 = KF.jdMethod_int(KF.E.f);
      Class localClass11 = KF.jdMethod_int(KF.E.new);
      Class localClass12 = KF.jdMethod_int(KF.E.transient);
      Class localClass13 = KF.jdMethod_int(KF.E.C);
      Class localClass14 = KF.jdMethod_int(KF.E.g);
      Class localClass15 = KF.jdMethod_int(KF.E.c);
      Class localClass16 = KF.jdMethod_int(KF.E.Z);
      Class localClass17 = KF.jdMethod_int(KF.E.a);
      Class localClass18 = KF.jdMethod_int(KF.E.synchronized);
      Class localClass19 = KF.jdMethod_int(KF.E.protected);
      Class localClass20 = KF.jdMethod_int(KF.E.instanceof);
      int i;
      if (KF.jdMethod_for(localClass4, "getInstance", new Class[] { String.class }) != null) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if (KF.jdMethod_for(localClass4, "load", new Class[] { localClass5 }) != null) {
        j = 1;
      } else {
        j = 0;
      }
      int k;
      if (KF.jdMethod_for(localClass4, "getEntry", new Class[] { String.class, localClass6 }) != null) {
        k = 1;
      } else {
        k = 0;
      }
      int m;
      if (KF.jdMethod_for(localClass4, "getCertificate", new Class[] { String.class }) != null) {
        m = 1;
      } else {
        m = 0;
      }
      int n;
      if (KF.jdMethod_for(localClass4, "getCreationDate", new Class[] { String.class }) != null) {
        n = 1;
      } else {
        n = 0;
      }
      int i1;
      if (KF.jdMethod_for(localClass8, "getPrivateKey", new Class[0]) != null) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      int i2;
      if (KF.jdMethod_for(localClass3, "getAlgorithm", new Class[0]) != null) {
        i2 = 1;
      } else {
        i2 = 0;
      }
      int i3;
      if (KF.jdMethod_for(localClass2, "getPrivate", new Class[0]) != null) {
        i3 = 1;
      } else {
        i3 = 0;
      }
      int i4;
      if (KF.jdMethod_for(localClass2, "getPublic", new Class[0]) != null) {
        i4 = 1;
      } else {
        i4 = 0;
      }
      int i5;
      if (KF.jdMethod_for(localClass1, "getPublicKey", new Class[0]) != null) {
        i5 = 1;
      } else {
        i5 = 0;
      }
      int i6;
      if (KF.jdMethod_for(localClass10, "generateKeyPair", new Class[0]) != null) {
        i6 = 1;
      } else {
        i6 = 0;
      }
      int i7;
      if (KF.jdMethod_for(localClass10, "getInstance", new Class[] { String.class, String.class }) != null) {
        i7 = 1;
      } else {
        i7 = 0;
      }
      int i9;
      if (KF.jdMethod_for(localClass10, "initialize", new Class[] { localClass11 }) != null) {
        i9 = 1;
      } else {
        i9 = 0;
      }
      int i8;
      if (KF.jdMethod_for(localClass13, "getInstance", new Class[] { String.class }) != null) {
        i8 = 1;
      } else {
        i8 = 0;
      }
      int i10;
      if (KF.jdMethod_for(localClass13, "initSign", new Class[] { localClass9 }) != null) {
        i10 = 1;
      } else {
        i10 = 0;
      }
      int i11;
      if (KF.jdMethod_for(localClass13, "update", new Class[] { [B.class }) != null) {
        i11 = 1;
      } else {
        i11 = 0;
      }
      int i12;
      if (KF.jdMethod_for(localClass13, "sign", new Class[0]) != null) {
        i12 = 1;
      } else {
        i12 = 0;
      }
      int i14;
      if (KF.jdMethod_for(localClass12, "isKeyAlgorithmSupported", new Class[] { String.class }) != null) {
        i14 = 1;
      } else {
        i14 = 0;
      }
      int i13;
      if (KF.jdMethod_for(localClass15, "setAlias", new Class[] { String.class }) != null) {
        i13 = 1;
      } else {
        i13 = 0;
      }
      int i15;
      if (KF.jdMethod_for(localClass15, "setSubject", new Class[] { localClass16 }) != null) {
        i15 = 1;
      } else {
        i15 = 0;
      }
      int i16;
      if (KF.jdMethod_for(localClass15, "setSerialNumber", new Class[] { BigInteger.class }) != null) {
        i16 = 1;
      } else {
        i16 = 0;
      }
      int i17;
      if (KF.jdMethod_for(localClass15, "setStartDate", new Class[] { Date.class }) != null) {
        i17 = 1;
      } else {
        i17 = 0;
      }
      int i18;
      if (KF.jdMethod_for(localClass15, "setEndDate", new Class[] { Date.class }) != null) {
        i18 = 1;
      } else {
        i18 = 0;
      }
      int i19;
      if (KF.jdMethod_for(localClass15, "setKeyType", new Class[] { String.class }) != null) {
        i19 = 1;
      } else {
        i19 = 0;
      }
      int i20;
      if (KF.jdMethod_for(localClass12, "isBoundKeyAlgorithm", new Class[] { String.class }) != null) {
        i20 = 1;
      } else {
        i20 = 0;
      }
      int i21;
      if (KF.jdMethod_for(localClass18, "setDigests", new Class[] { [Ljava.lang.String.class }) != null) {
        i21 = 1;
      } else {
        i21 = 0;
      }
      int i22;
      if (KF.jdMethod_for(localClass18, "setSignaturePaddings", new Class[] { [Ljava.lang.String.class }) != null) {
        i22 = 1;
      } else {
        i22 = 0;
      }
      boolean bool2 = true;
      int i23;
      if (KF.jdMethod_for(localClass19, "getInstance", new Class[] { String.class, String.class }) != null) {
        i23 = 1;
      } else {
        i23 = 0;
      }
      int i24;
      if (KF.jdMethod_for(localClass19, "getKeySpec", new Class[] { localClass3, Class.class }) != null) {
        i24 = 1;
      } else {
        i24 = 0;
      }
      int i25;
      if (KF.jdMethod_for(localClass20, "isInsideSecureHardware", new Class[0]) != null) {
        i25 = 1;
      } else {
        i25 = 0;
      }
      if ((localClass7 != null) && (localClass8 != null) && (localClass9 != null) && (i != 0) && (j != 0) && (k != 0) && (i1 != 0) && (m != 0) && (n != 0) && (i2 != 0) && (i3 != 0) && (i4 != 0) && (i5 != 0) && (i6 != 0) && (i7 != 0) && (i9 != 0) && (i14 != 0)) {
        i = 1;
      } else {
        i = 0;
      }
      boolean bool1;
      if ((i8 != 0) && (i10 != 0) && (i11 != 0) && (i12 != 0)) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      if = bool1;
      if ((D.O.E.if >= 18) && (i != 0) && (localClass14 != null) && (i13 != 0) && (i15 != 0) && (i16 != 0) && (i17 != 0) && (i18 != 0) && (i19 != 0) && (i20 != 0)) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      do = bool1;
      if ((D.O.E.if >= 23) && (i != 0) && (localClass17 != null) && (i21 != 0) && (i22 != 0) && (i23 != 0) && (i24 != 0) && (i25 != 0)) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
      for = bool1;
    }
    
    W() {}
    
    static boolean jdMethod_do()
    {
      return (do) || (for);
    }
    
    static boolean jdMethod_for()
    {
      return do;
    }
    
    static boolean jdMethod_if()
    {
      return if;
    }
    
    static boolean jdMethod_new()
    {
      return for;
    }
  }
  
  public static final class Y
  {
    private static final boolean jdField_do;
    private static final boolean jdField_int;
    private static final boolean jdField_new;
    
    static
    {
      Class localClass1 = KF.jdMethod_int(KF.E.D);
      Class localClass2 = KF.jdMethod_int(KF.E.final);
      Class localClass3 = KF.jdMethod_int(KF.E.strictfp);
      Class localClass4 = KF.jdMethod_int(KF.E.r);
      Class localClass5 = KF.jdMethod_int(KF.E.x);
      Class localClass6 = KF.jdMethod_int(KF.E.protected);
      Class localClass7 = KF.jdMethod_int(KF.E.e);
      Class localClass8 = KF.jdMethod_int(KF.E.u);
      Class localClass9 = KF.jdMethod_int(KF.E.V);
      Class localClass10 = KF.jdMethod_int(KF.E.super);
      Class localClass11 = KF.jdMethod_int(KF.E.z);
      Class localClass12 = KF.jdMethod_int(KF.E.implements);
      Class localClass13 = KF.jdMethod_int(KF.E.f);
      Class localClass14 = KF.jdMethod_int(KF.E.new);
      Class localClass15 = KF.jdMethod_int(KF.E.C);
      int i;
      if (KF.jdMethod_for(localClass2, "getInstance", new Class[] { String.class, String.class }) != null) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if (KF.jdMethod_for(localClass2, "init", new Class[] { Integer.TYPE, localClass3 }) != null) {
        j = 1;
      } else {
        j = 0;
      }
      int k;
      if (KF.jdMethod_for(localClass2, "doFinal", new Class[] { [B.class }) != null) {
        k = 1;
      } else {
        k = 0;
      }
      int m;
      if (KF.jdMethod_for(localClass6, "getInstance", new Class[] { String.class }) != null) {
        m = 1;
      } else {
        m = 0;
      }
      int n;
      if (KF.jdMethod_for(localClass6, "generatePrivate", new Class[] { localClass7 }) != null) {
        n = 1;
      } else {
        n = 0;
      }
      int i1;
      if (KF.jdMethod_for(localClass6, "generatePublic", new Class[] { localClass7 }) != null) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      int i2;
      if (KF.jdMethod_for(localClass12, "getPrivate", new Class[0]) != null) {
        i2 = 1;
      } else {
        i2 = 0;
      }
      int i3;
      if (KF.jdMethod_for(localClass12, "getPublic", new Class[0]) != null) {
        i3 = 1;
      } else {
        i3 = 0;
      }
      int i4;
      if (KF.jdMethod_for(localClass13, "getInstance", new Class[] { String.class, String.class }) != null) {
        i4 = 1;
      } else {
        i4 = 0;
      }
      boolean bool2 = false;
      int i5;
      if (KF.jdMethod_for(localClass13, "initialize", new Class[] { localClass14 }) != null) {
        i5 = 1;
      } else {
        i5 = 0;
      }
      int i6;
      if (KF.jdMethod_for(localClass13, "generateKeyPair", new Class[0]) != null) {
        i6 = 1;
      } else {
        i6 = 0;
      }
      int i7;
      if (KF.jdMethod_for(localClass15, "getInstance", new Class[] { String.class }) != null) {
        i7 = 1;
      } else {
        i7 = 0;
      }
      int i8;
      if (KF.jdMethod_for(localClass15, "initSign", new Class[] { localClass4 }) != null) {
        i8 = 1;
      } else {
        i8 = 0;
      }
      int i9;
      if (KF.jdMethod_for(localClass15, "update", new Class[] { [B.class }) != null) {
        i9 = 1;
      } else {
        i9 = 0;
      }
      int i10;
      if (KF.jdMethod_for(localClass15, "sign", new Class[0]) != null) {
        i10 = 1;
      } else {
        i10 = 0;
      }
      int i11;
      if (KF.jdMethod_int(localClass11, "F0") != null) {
        i11 = 1;
      } else {
        i11 = 0;
      }
      boolean bool1;
      if ((i7 != 0) && (i8 != 0) && (i9 != 0) && (i10 != 0)) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      do = bool1;
      if ((localClass1 != null) && (localClass8 != null) && (localClass9 != null) && (i != 0) && (j != 0) && (k != 0) && (m != 0) && (n != 0) && (i1 != 0)) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      new = bool1;
      if (localClass10 == null)
      {
        bool1 = bool2;
        if (localClass11 != null)
        {
          bool1 = bool2;
          if (i11 == 0) {}
        }
      }
      else
      {
        bool1 = bool2;
        if (localClass4 != null)
        {
          bool1 = bool2;
          if (localClass5 != null)
          {
            bool1 = bool2;
            if (i4 != 0)
            {
              bool1 = bool2;
              if (i5 != 0)
              {
                bool1 = bool2;
                if (i6 != 0)
                {
                  bool1 = bool2;
                  if (i2 != 0)
                  {
                    bool1 = bool2;
                    if (i3 != 0)
                    {
                      bool1 = bool2;
                      if (j != 0)
                      {
                        bool1 = bool2;
                        if (k != 0) {
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
      int = bool1;
    }
    
    Y() {}
    
    public static boolean jdMethod_do()
    {
      return int;
    }
    
    public static boolean jdMethod_for()
    {
      return do;
    }
    
    public static boolean jdMethod_int()
    {
      return new;
    }
  }
  
  static final class Z
  {
    private static final boolean jdField_break;
    private static final Class<?> jdField_byte;
    private static final Class<?> jdField_case;
    private static final boolean jdField_char;
    private static final Class<?> jdField_do = KF.jdMethod_int(KF.E.M);
    private static final boolean jdField_else;
    private static final Class<?> jdField_for;
    private static final Class<?> jdField_if;
    private static final Class<?> jdField_int;
    private static final Class<?> jdField_new;
    private static final boolean jdField_this;
    private static final Class<?> jdField_try;
    private static final boolean jdField_void;
    
    static
    {
      char = jdMethod_if(KF.E.long, KF.E.char);
      else = jdMethod_if(KF.E.this, KF.E.case);
      void = jdMethod_if(KF.E.goto, KF.E.byte);
      break = jdMethod_if(KF.E.break, KF.E.else);
      int = KF.jdMethod_int(KF.E.try);
      for = KF.jdMethod_int(KF.E.void);
      if = KF.jdMethod_int(KF.E.h);
      new = KF.jdMethod_int(KF.E.K);
      try = KF.jdMethod_int(KF.E.I);
      case = KF.jdMethod_int(KF.E.do);
      byte = KF.jdMethod_int(KF.E.volatile);
      Class localClass = KF.jdMethod_int(KF.E.if);
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (localClass != null)
      {
        bool1 = bool2;
        if (KF.jdMethod_for(do, "getCellLocation", new Class[0]) != null) {
          bool1 = true;
        }
      }
      this = bool1;
    }
    
    Z() {}
    
    static boolean jdMethod_byte()
    {
      return (try != null) && (new != null) && (KF.jdMethod_for(new, "getSimSlotIndex", new Class[0]) != null) && (KF.jdMethod_for(new, "getCarrierName", new Class[0]) != null) && (KF.jdMethod_for(new, "getDisplayName", new Class[0]) != null) && (KF.jdMethod_for(new, "getIccId", new Class[0]) != null) && (KF.jdMethod_for(new, "getNumber", new Class[0]) != null) && (KF.jdMethod_for(new, "getCountryIso", new Class[0]) != null) && (KF.jdMethod_for(new, "getDataRoaming", new Class[0]) != null) && (KF.jdMethod_for(try, "getActiveSubscriptionInfoList", new Class[0]) != null);
    }
    
    static boolean jdMethod_case()
    {
      return char;
    }
    
    static boolean jdMethod_char()
    {
      return break;
    }
    
    static boolean jdMethod_do()
    {
      return (this) && (KF.jdMethod_for(case, "getSystemId", new Class[0]) != null) && (KF.jdMethod_for(case, "getBaseStationId", new Class[0]) != null) && (KF.jdMethod_for(case, "getBaseStationLatitude", new Class[0]) != null) && (KF.jdMethod_for(case, "getBaseStationLongitude", new Class[0]) != null);
    }
    
    static boolean jdMethod_else()
    {
      return else;
    }
    
    static boolean jdMethod_for()
    {
      return (this) && (KF.jdMethod_for(byte, "getCid", new Class[0]) != null) && (KF.jdMethod_for(byte, "getLac", new Class[0]) != null) && (KF.jdMethod_for(byte, "getPsc", new Class[0]) != null);
    }
    
    static boolean jdMethod_goto()
    {
      return (do != null) && (KF.jdMethod_for(do, "getDataState", new Class[0]) != null) && (KF.jdMethod_int(do, "DATA_CONNECTED") != null) && (KF.jdMethod_int(do, "DATA_CONNECTING") != null) && (KF.jdMethod_int(do, "DATA_SUSPENDED") != null);
    }
    
    static boolean jdMethod_if()
    {
      return (if != null) && (KF.jdMethod_for(if, "getCid", new Class[0]) != null) && (KF.jdMethod_for(if, "getRssi", new Class[0]) != null);
    }
    
    private static boolean jdMethod_if(KF.E paramE1, KF.E paramE2)
    {
      paramE1 = KF.jdMethod_int(paramE1);
      return (KF.jdMethod_int(paramE2) != null) && (KF.jdMethod_int(paramE1, "getCellSignalStrength", new Class[0]) != null) && (KF.jdMethod_int(paramE1, "getCellIdentity", new Class[0]) != null);
    }
    
    static boolean jdMethod_int()
    {
      return (do != null) && (KF.jdMethod_for(do, "getNetworkOperator", new Class[0]) != null) && (KF.jdMethod_for(do, "getNetworkCountryIso", new Class[0]) != null) && (KF.jdMethod_int(do, "getNetworkOperatorName", new Class[0]) != null);
    }
    
    static boolean jdMethod_new()
    {
      return (do != null) && (for != null) && (int != null) && (KF.jdMethod_for(int, "isRegistered", new Class[0]) != null) && (KF.jdMethod_for(do, "getAllCellInfo", new Class[0]) != null);
    }
    
    static boolean jdMethod_try()
    {
      return void;
    }
  }
}
