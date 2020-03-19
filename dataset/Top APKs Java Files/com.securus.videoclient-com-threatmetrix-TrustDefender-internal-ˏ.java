package com.threatmetrix.TrustDefender.internal;

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
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Date;

public class ˏ
{
  private static final String ॱ = ٴ.ˊ(ˏ.class);
  
  public ˏ() {}
  
  public static final class if
  {
    static final String ʻ;
    static final String ʼ;
    static final String ʽ;
    static final long ˊ;
    static final String ˊॱ;
    static final String ˋ;
    private static Class<?> ˋॱ;
    static final String ˎ;
    static final String ˏ;
    static final String ˏॱ;
    public static final String ͺ;
    static final String ॱ;
    public static final String ॱˊ;
    static final String ॱˋ;
    public static final String ᐝ;
    
    static
    {
      Object localObject1 = ᒽ.ॱ("android.os.Build");
      ˋॱ = (Class)localObject1;
      long l;
      if (ᒽ.ˋ((Class)localObject1, "TIME") != null) {
        l = Build.TIME;
      } else {
        l = Long.MAX_VALUE;
      }
      ˊ = l;
      localObject1 = ᒽ.ˋ(ˋॱ, "TYPE");
      Object localObject2 = null;
      if (localObject1 != null) {
        localObject1 = Build.TYPE;
      } else {
        localObject1 = null;
      }
      ˏ = (String)localObject1;
      if (ᒽ.ˋ(ˋॱ, "TAGS") != null) {
        localObject1 = Build.TAGS;
      } else {
        localObject1 = null;
      }
      ॱ = (String)localObject1;
      if (ᒽ.ˋ(ˋॱ, "HOST") != null) {
        localObject1 = Build.HOST;
      } else {
        localObject1 = null;
      }
      ˋ = (String)localObject1;
      if (ᒽ.ˋ(ˋॱ, "BRAND") != null) {
        localObject1 = Build.BRAND;
      } else {
        localObject1 = null;
      }
      ˎ = (String)localObject1;
      if (ᒽ.ˋ(ˋॱ, "USER") != null) {
        localObject1 = Build.USER;
      } else {
        localObject1 = null;
      }
      ʼ = (String)localObject1;
      if (ᒽ.ˋ(ˋॱ, "ID") != null) {
        localObject1 = Build.ID;
      } else {
        localObject1 = null;
      }
      ˊॱ = (String)localObject1;
      if (ᒽ.ˋ(ˋॱ, "SERIAL") != null) {
        localObject1 = Build.SERIAL;
      } else {
        localObject1 = null;
      }
      ᐝ = (String)localObject1;
      if (ᒽ.ˋ(ˋॱ, "DEVICE") != null) {
        localObject1 = Build.DEVICE;
      } else {
        localObject1 = null;
      }
      ʽ = (String)localObject1;
      if (ᒽ.ˋ(ˋॱ, "MODEL") != null) {
        localObject1 = Build.MODEL;
      } else {
        localObject1 = null;
      }
      ʻ = (String)localObject1;
      if (ᒽ.ˋ(ˋॱ, "DISPLAY") != null) {
        localObject1 = Build.DISPLAY;
      } else {
        localObject1 = null;
      }
      ˏॱ = (String)localObject1;
      if (ᒽ.ˋ(ˋॱ, "PRODUCT") != null) {
        localObject1 = Build.PRODUCT;
      } else {
        localObject1 = null;
      }
      ॱˊ = (String)localObject1;
      if (ᒽ.ˋ(ˋॱ, "MANUFACTURER") != null) {
        localObject1 = Build.MANUFACTURER;
      } else {
        localObject1 = null;
      }
      ॱˋ = (String)localObject1;
      localObject1 = localObject2;
      if (ᒽ.ˋ(ˋॱ, "BOARD") != null) {
        localObject1 = Build.BOARD;
      }
      ͺ = (String)localObject1;
    }
    
    if() {}
    
    public static final class If
    {
      public static final int ˊ;
      public static final String ˋ;
      static final String ॱ;
      
      static
      {
        Class localClass = ᒽ.ॱ("android.os.Build$VERSION");
        Object localObject1 = ᒽ.ˋ(localClass, "RELEASE");
        Object localObject2 = null;
        if (localObject1 != null) {
          localObject1 = Build.VERSION.RELEASE;
        } else {
          localObject1 = null;
        }
        ˋ = (String)localObject1;
        int i;
        if (ᒽ.ˋ(localClass, "SDK_INT") != null) {
          i = Build.VERSION.SDK_INT;
        } else {
          i = -1;
        }
        ˊ = i;
        localObject1 = localObject2;
        if (ᒽ.ˋ(localClass, "CODENAME") != null) {
          localObject1 = Build.VERSION.CODENAME;
        }
        ॱ = (String)localObject1;
      }
      
      If() {}
    }
    
    public static final class ˋ
    {
      static final int ʻ;
      static final int ʻॱ = 25;
      static final int ʼ;
      static final int ʽ;
      static final int ˊ;
      static final int ˊॱ;
      static final int ˋ;
      static final int ˋॱ;
      static final int ˎ;
      static final int ˏ;
      static final int ˏॱ;
      static final int ͺ;
      static final int ॱ;
      public static final int ॱˊ;
      static final int ॱˋ;
      static final int ॱˎ;
      private static Class<?> ॱᐝ;
      static final int ᐝ;
      static final int ι;
      
      static
      {
        Class localClass = ᒽ.ॱ("android.os.Build$VERSION_CODES");
        ॱᐝ = localClass;
        ᒽ.ˋ(localClass, "FROYO");
        ˋ = 8;
        ᒽ.ˋ(ॱᐝ, "GINGERBREAD");
        ˎ = 9;
        ᒽ.ˋ(ॱᐝ, "GINGERBREAD_MR1");
        ˏ = 10;
        ᒽ.ˋ(ॱᐝ, "HONEYCOMB");
        ॱ = 11;
        ᒽ.ˋ(ॱᐝ, "HONEYCOMB_MR1");
        ˊ = 12;
        ᒽ.ˋ(ॱᐝ, "HONEYCOMB_MR2");
        ʽ = 13;
        ᒽ.ˋ(ॱᐝ, "ICE_CREAM_SANDWICH");
        ʻ = 14;
        ᒽ.ˋ(ॱᐝ, "ICE_CREAM_SANDWICH_MR1");
        ᐝ = 15;
        ᒽ.ˋ(ॱᐝ, "JELLY_BEAN");
        ʼ = 16;
        ᒽ.ˋ(ॱᐝ, "JELLY_BEAN_MR1");
        ˊॱ = 17;
        ᒽ.ˋ(ॱᐝ, "JELLY_BEAN_MR2");
        ͺ = 18;
        ᒽ.ˋ(ॱᐝ, "KITKAT");
        ॱˋ = 19;
        ᒽ.ˋ(ॱᐝ, "KITKAT_WATCH");
        ॱˊ = 20;
        ᒽ.ˋ(ॱᐝ, "LOLLIPOP");
        ˏॱ = 21;
        ᒽ.ˋ(ॱᐝ, "LOLLIPOP_MR1");
        ˋॱ = 22;
        ᒽ.ˋ(ॱᐝ, "M");
        ॱˎ = 23;
        ᒽ.ˋ(ॱᐝ, "N");
        ι = 24;
        ᒽ.ˋ(ॱᐝ, "N_MR1");
      }
      
      ˋ() {}
    }
  }
  
  static final class ʻ
  {
    private static final boolean ʻ;
    private static final boolean ʼ;
    private static final boolean ʽ;
    static final int ˊ = 1;
    private static final boolean ˊॱ;
    static final int ˋ = 128;
    private static final boolean ˎ;
    private static final boolean ˏ;
    private static final boolean ॱ;
    
    static
    {
      Class localClass1 = ᒽ.ॱ("android.content.pm.PackageManager");
      boolean bool2 = false;
      if (localClass1 != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ॱ = bool1;
      Class localClass2 = ᒽ.ॱ("android.content.pm.PackageInfo");
      if (localClass2 != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ʼ = bool1;
      if (ᒽ.ॱ(localClass1, "checkPermission", new Class[] { String.class, String.class }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ˊॱ = bool1;
      if (ᒽ.ˋ(localClass2, "versionCode") != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ʻ = bool1;
      if (ᒽ.ˋ(localClass2, "versionName") != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ʽ = bool1;
      if (ᒽ.ॱ("android.content.pm.ApplicationInfo") != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ˏ = bool1;
      boolean bool1 = bool2;
      if (ᒽ.ॱ("android.content.pm.PackageItemInfo") != null) {
        bool1 = true;
      }
      ˎ = bool1;
    }
    
    ʻ() {}
  }
  
  static final class ʼ
  {
    private static final boolean ˊ;
    private static final boolean ˋ;
    private static final boolean ˎ;
    private static final boolean ॱ;
    
    static
    {
      Class localClass1 = ᒽ.ॱ("android.net.ConnectivityManager");
      Class localClass2 = ᒽ.ॱ("android.net.NetworkInfo");
      Class localClass3 = ᒽ.ॱ("android.net.wifi.WifiInfo");
      Class localClass4 = ᒽ.ॱ("android.net.wifi.WifiManager");
      Class localClass5 = ᒽ.ॱ("android.net.NetworkInfo$State");
      int i;
      if (ᒽ.ॱ(localClass1, "getActiveNetworkInfo", new Class[0]) != null) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if (ᒽ.ॱ(localClass2, "getState", new Class[0]) != null) {
        j = 1;
      } else {
        j = 0;
      }
      int k;
      if (ᒽ.ॱ(localClass2, "getType", new Class[0]) != null) {
        k = 1;
      } else {
        k = 0;
      }
      int m;
      if (ᒽ.ॱ(localClass2, "getExtraInfo", new Class[0]) != null) {
        m = 1;
      } else {
        m = 0;
      }
      int n;
      if (ᒽ.ॱ(localClass3, "getBSSID", new Class[0]) != null) {
        n = 1;
      } else {
        n = 0;
      }
      int i1;
      if (ᒽ.ॱ(localClass3, "getSSID", new Class[0]) != null) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      int i2;
      if (ᒽ.ॱ(localClass3, "getRssi", new Class[0]) != null) {
        i2 = 1;
      } else {
        i2 = 0;
      }
      int i3;
      if (ᒽ.ॱ(localClass4, "getConnectionInfo", new Class[0]) != null) {
        i3 = 1;
      } else {
        i3 = 0;
      }
      int i4;
      if (ᒽ.ॱ(localClass2, "isConnectedOrConnecting", new Class[0]) != null) {
        i4 = 1;
      } else {
        i4 = 0;
      }
      int i5;
      if (ᒽ.ˋ(localClass1, "CONNECTIVITY_ACTION") != null) {
        i5 = 1;
      } else {
        i5 = 0;
      }
      int i6;
      if (ᒽ.ˋ(localClass1, "TYPE_MOBILE") != null) {
        i6 = 1;
      } else {
        i6 = 0;
      }
      int i7;
      if (ᒽ.ˋ(localClass1, "TYPE_WIFI") != null) {
        i7 = 1;
      } else {
        i7 = 0;
      }
      int i8;
      if (ᒽ.ˋ(localClass1, "TYPE_BLUETOOTH") != null) {
        i8 = 1;
      } else {
        i8 = 0;
      }
      int i9;
      if (ᒽ.ˋ(localClass1, "TYPE_ETHERNET") != null) {
        i9 = 1;
      } else {
        i9 = 0;
      }
      int i10;
      if (ᒽ.ˋ(localClass4, "NETWORK_STATE_CHANGED_ACTION") != null) {
        i10 = 1;
      } else {
        i10 = 0;
      }
      int i11;
      if (ᒽ.ˋ(localClass5, "CONNECTED") != null) {
        i11 = 1;
      } else {
        i11 = 0;
      }
      boolean bool;
      if ((i != 0) && (i4 != 0)) {
        bool = true;
      } else {
        bool = false;
      }
      ˎ = bool;
      if ((i5 != 0) && (i11 != 0) && (j != 0) && (m != 0) && (k != 0) && (i6 != 0) && (i7 != 0) && ((ˏ.if.If.ˊ < ˏ.if.ˋ.ʽ) || ((i9 != 0) && (i8 != 0)))) {
        bool = true;
      } else {
        bool = false;
      }
      ॱ = bool;
      if ((i10 != 0) && (i11 != 0) && (n != 0) && (i1 != 0) && (i2 != 0) && (j != 0) && (m != 0)) {
        bool = true;
      } else {
        bool = false;
      }
      ˋ = bool;
      if ((i3 != 0) && (n != 0) && (i1 != 0) && (i2 != 0)) {
        bool = true;
      } else {
        bool = false;
      }
      ˊ = bool;
    }
    
    ʼ() {}
    
    static boolean ˊ()
    {
      return ˋ;
    }
    
    static boolean ˋ()
    {
      return ˊ;
    }
    
    static boolean ˏ()
    {
      return ˎ;
    }
    
    static boolean ॱ()
    {
      return ॱ;
    }
  }
  
  final class ʽ
  {
    PackageInfo ˊ = null;
    
    ʽ(Context paramContext, String paramString, int paramInt)
    {
      if ((ˏ.ʻ.ˋ()) && (ˏ.ʻ.ˏ())) {}
      try
      {
        this.ˊ = paramContext.getPackageManager().getPackageInfo(paramString, paramInt);
        return;
      }
      catch (Exception this$1)
      {
        ٴ.ˊ(ˏ.ˋ(), ˏ.this.toString());
        return;
        ˏ.ˋ();
        return;
        ˏ.ˋ();
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
  
  public final class ʾ
  {
    private PackageManager ˎ = null;
    
    public ʾ(Context paramContext)
    {
      if (ˏ.ʻ.ˏ()) {}
      try
      {
        this.ˎ = paramContext.getPackageManager();
        return;
      }
      catch (Exception this$1)
      {
        ٴ.ˊ(ˏ.ˋ(), ˏ.this.toString());
        return;
        ˏ.ˋ();
        return;
        return;
      }
      catch (SecurityException this$1)
      {
        for (;;) {}
      }
    }
    
    /* Error */
    public final java.util.ArrayList<String> ˋ()
    {
      // Byte code:
      //   0: new 52	java/util/ArrayList
      //   3: dup
      //   4: invokespecial 53	java/util/ArrayList:<init>	()V
      //   7: astore_1
      //   8: invokestatic 30	com/threatmetrix/TrustDefender/internal/ˏ$ʻ:ˏ	()Z
      //   11: ifeq +103 -> 114
      //   14: invokestatic 56	com/threatmetrix/TrustDefender/internal/ˏ$ʻ:ʽ	()Z
      //   17: ifeq +97 -> 114
      //   20: aload_0
      //   21: getfield 24	com/threatmetrix/TrustDefender/internal/ˏ$ʾ:ˎ	Landroid/content/pm/PackageManager;
      //   24: ifnull +90 -> 114
      //   27: aload_0
      //   28: getfield 24	com/threatmetrix/TrustDefender/internal/ˏ$ʾ:ˎ	Landroid/content/pm/PackageManager;
      //   31: iconst_0
      //   32: invokevirtual 62	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   35: invokeinterface 68 1 0
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
      //   97: invokestatic 40	com/threatmetrix/TrustDefender/internal/ˏ:ˋ	()Ljava/lang/String;
      //   100: aload_2
      //   101: invokevirtual 43	java/lang/Exception:toString	()Ljava/lang/String;
      //   104: invokestatic 48	com/threatmetrix/TrustDefender/internal/ٴ:ˊ	(Ljava/lang/String;Ljava/lang/String;)V
      //   107: goto +7 -> 114
      //   110: invokestatic 40	com/threatmetrix/TrustDefender/internal/ˏ:ˋ	()Ljava/lang/String;
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
      //   0	134	0	this	ʾ
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
    
    final boolean ˋ(String paramString, int paramInt)
    {
      if ((ˏ.ʻ.ˏ()) && (ˏ.ʻ.ˋ()) && (this.ˎ != null)) {}
      try
      {
        this.ˎ.getPackageInfo(paramString, paramInt);
        return true;
      }
      catch (Exception paramString)
      {
        ٴ.ˊ(ˏ.ˋ(), paramString.toString());
        break label56;
        ˏ.ˋ();
        break label56;
        ˏ.ˋ();
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
    
    public final boolean ˎ(String paramString1, String paramString2)
    {
      if ((ˏ.ʻ.ॱ()) && (this.ˎ != null)) {}
      try
      {
        int i = this.ˎ.checkPermission(paramString1, paramString2);
        return i == 0;
      }
      catch (Exception paramString1)
      {
        ٴ.ˊ(ˏ.ˋ(), paramString1.toString());
        return false;
        ˏ.ˋ();
        return false;
      }
      catch (SecurityException paramString1)
      {
        for (;;) {}
      }
    }
  }
  
  static final class ʿ
  {
    private static final boolean ˊ;
    private static final boolean ˏ;
    
    static
    {
      Class localClass = ᒽ.ॱ("android.os.SystemClock");
      boolean bool2 = false;
      if (ᒽ.ॱ(localClass, "uptimeMillis", new Class[0]) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ˊ = bool1;
      boolean bool1 = bool2;
      if (ᒽ.ॱ(localClass, "elapsedRealtime", new Class[0]) != null) {
        bool1 = true;
      }
      ˏ = bool1;
    }
    
    ʿ() {}
    
    static long ˎ()
    {
      if (ˊ) {
        return SystemClock.uptimeMillis();
      }
      return 0L;
    }
    
    static long ˏ()
    {
      if (ˏ) {
        return SystemClock.elapsedRealtime();
      }
      return 0L;
    }
  }
  
  static final class ˈ
  {
    private static final Class<?> ʻ;
    private static final boolean ʼ;
    private static final boolean ʽ;
    private static final Class<?> ˊ;
    private static final Class<?> ˊॱ;
    private static final Class<?> ˋ = ᒽ.ॱ("android.telephony.TelephonyManager");
    private static final Class<?> ˎ;
    private static final Class<?> ˏ;
    private static final boolean ͺ;
    private static final Class<?> ॱ;
    private static final boolean ॱˊ;
    private static final boolean ॱˋ;
    private static final Class<?> ᐝ;
    
    static
    {
      ʼ = ˋ("android.telephony.CellInfoCdma", "android.telephony.CellIdentityCdma");
      ʽ = ˋ("android.telephony.CellInfoGsm", "android.telephony.CellIdentityGsm");
      ॱˋ = ˋ("android.telephony.CellInfoLte", "android.telephony.CellIdentityLte");
      ͺ = ˋ("android.telephony.CellInfoWcdma", "android.telephony.CellIdentityWcdma");
      ˏ = ᒽ.ॱ("android.telephony.CellInfo");
      ˎ = ᒽ.ॱ("android.telephony.CellSignalStrength");
      ˊ = ᒽ.ॱ("android.telephony.NeighboringCellInfo");
      ॱ = ᒽ.ॱ("android.telephony.SubscriptionInfo");
      ʻ = ᒽ.ॱ("android.telephony.SubscriptionManager");
      ˊॱ = ᒽ.ॱ("android.telephony.cdma.CdmaCellLocation");
      ᐝ = ᒽ.ॱ("android.telephony.gsm.GsmCellLocation");
      Class localClass = ᒽ.ॱ("android.telephony.CellLocation");
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (localClass != null)
      {
        bool1 = bool2;
        if (ᒽ.ॱ(ˋ, "getCellLocation", new Class[0]) != null) {
          bool1 = true;
        }
      }
      ॱˊ = bool1;
    }
    
    ˈ() {}
    
    static boolean ʻ()
    {
      return ʽ;
    }
    
    static boolean ʼ()
    {
      return ͺ;
    }
    
    static boolean ʽ()
    {
      return ʼ;
    }
    
    static boolean ˊ()
    {
      return (ॱˊ) && (ᒽ.ॱ(ᐝ, "getCid", new Class[0]) != null) && (ᒽ.ॱ(ᐝ, "getLac", new Class[0]) != null) && (ᒽ.ॱ(ᐝ, "getPsc", new Class[0]) != null);
    }
    
    static boolean ˊॱ()
    {
      return ॱˋ;
    }
    
    static boolean ˋ()
    {
      return (ˋ != null) && (ᒽ.ॱ(ˋ, "getNetworkOperator", new Class[0]) != null) && (ᒽ.ॱ(ˋ, "getNetworkCountryIso", new Class[0]) != null) && (ᒽ.ˋ(ˋ, "getNetworkOperatorName", new Class[0]) != null);
    }
    
    private static boolean ˋ(String paramString1, String paramString2)
    {
      paramString1 = ᒽ.ॱ(paramString1);
      return (ᒽ.ॱ(paramString2) != null) && (ᒽ.ˋ(paramString1, "getCellSignalStrength", new Class[0]) != null) && (ᒽ.ˋ(paramString1, "getCellIdentity", new Class[0]) != null);
    }
    
    static boolean ˎ()
    {
      return (ˊ != null) && (ᒽ.ॱ(ˊ, "getCid", new Class[0]) != null) && (ᒽ.ॱ(ˊ, "getRssi", new Class[0]) != null);
    }
    
    static boolean ˏ()
    {
      return (ˋ != null) && (ˎ != null) && (ˏ != null) && (ᒽ.ॱ(ˏ, "isRegistered", new Class[0]) != null) && (ᒽ.ॱ(ˋ, "getAllCellInfo", new Class[0]) != null);
    }
    
    static boolean ॱ()
    {
      return (ॱˊ) && (ᒽ.ॱ(ˊॱ, "getSystemId", new Class[0]) != null) && (ᒽ.ॱ(ˊॱ, "getBaseStationId", new Class[0]) != null) && (ᒽ.ॱ(ˊॱ, "getBaseStationLatitude", new Class[0]) != null) && (ᒽ.ॱ(ˊॱ, "getBaseStationLongitude", new Class[0]) != null);
    }
    
    static boolean ॱˋ()
    {
      return (ˋ != null) && (ᒽ.ॱ(ˋ, "getDataState", new Class[0]) != null) && (ᒽ.ˋ(ˋ, "DATA_CONNECTED") != null) && (ᒽ.ˋ(ˋ, "DATA_CONNECTING") != null) && (ᒽ.ˋ(ˋ, "DATA_SUSPENDED") != null);
    }
    
    static boolean ᐝ()
    {
      return (ʻ != null) && (ॱ != null) && (ᒽ.ॱ(ॱ, "getSimSlotIndex", new Class[0]) != null) && (ᒽ.ॱ(ॱ, "getCarrierName", new Class[0]) != null) && (ᒽ.ॱ(ॱ, "getDisplayName", new Class[0]) != null) && (ᒽ.ॱ(ॱ, "getIccId", new Class[0]) != null) && (ᒽ.ॱ(ॱ, "getNumber", new Class[0]) != null) && (ᒽ.ॱ(ॱ, "getCountryIso", new Class[0]) != null) && (ᒽ.ॱ(ॱ, "getDataRoaming", new Class[0]) != null) && (ᒽ.ॱ(ʻ, "getActiveSubscriptionInfoList", new Class[0]) != null);
    }
  }
  
  public final class ˉ
  {
    SharedPreferences.Editor ˊ = null;
    public SharedPreferences ˏ = null;
    
    public ˉ(Context paramContext, String paramString)
    {
      if (ˏ.ˏ.ˋ()) {
        this.ˏ = paramContext.getSharedPreferences(paramString, 0);
      }
      if (ˏ.ˏ.ˊ()) {
        this.ˊ = this.ˏ.edit();
      }
    }
  }
  
  static final class ˊ
  {
    static final boolean ˋ;
    
    static
    {
      Class localClass = ᒽ.ॱ("android.app.admin.DevicePolicyManager");
      boolean bool = false;
      if (ᒽ.ॱ(localClass, "getStorageEncryptionStatus", new Class[0]) != null) {
        bool = true;
      }
      ˋ = bool;
    }
    
    ˊ() {}
  }
  
  public final class ˋ
  {
    public ApplicationInfo ˋ = null;
    
    public ˋ(Context paramContext)
    {
      if ((ˏ.ʻ.ʽ()) && (ˏ.ʻ.ʻ())) {
        this.ˋ = paramContext.getApplicationInfo();
      }
    }
  }
  
  public static final class ˌ
  {
    private static final boolean ˊ;
    private static final boolean ˎ;
    private static final boolean ॱ;
    
    static
    {
      Class localClass1 = ᒽ.ॱ("javax.crypto.spec.SecretKeySpec");
      Class localClass2 = ᒽ.ॱ("javax.crypto.Cipher");
      Class localClass3 = ᒽ.ॱ("java.security.Key");
      Class localClass4 = ᒽ.ॱ("java.security.PrivateKey");
      Class localClass5 = ᒽ.ॱ("java.security.PublicKey");
      Class localClass6 = ᒽ.ॱ("java.security.KeyFactory");
      Class localClass7 = ᒽ.ॱ("java.security.spec.KeySpec");
      Class localClass8 = ᒽ.ॱ("java.security.spec.PKCS8EncodedKeySpec");
      Class localClass9 = ᒽ.ॱ("java.security.spec.X509EncodedKeySpec");
      Class localClass10 = ᒽ.ॱ("java.security.spec.ECGenParameterSpec");
      Class localClass11 = ᒽ.ॱ("java.security.spec.RSAKeyGenParameterSpec");
      Class localClass12 = ᒽ.ॱ("java.security.KeyPair");
      Class localClass13 = ᒽ.ॱ("java.security.KeyPairGenerator");
      Class localClass14 = ᒽ.ॱ("java.security.spec.AlgorithmParameterSpec");
      Class localClass15 = ᒽ.ॱ("java.security.Signature");
      int i;
      if (ᒽ.ॱ(localClass2, "getInstance", new Class[] { String.class, String.class }) != null) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if (ᒽ.ॱ(localClass2, "init", new Class[] { Integer.TYPE, localClass3 }) != null) {
        j = 1;
      } else {
        j = 0;
      }
      int k;
      if (ᒽ.ॱ(localClass2, "doFinal", new Class[] { [B.class }) != null) {
        k = 1;
      } else {
        k = 0;
      }
      int m;
      if (ᒽ.ॱ(localClass6, "getInstance", new Class[] { String.class }) != null) {
        m = 1;
      } else {
        m = 0;
      }
      int n;
      if (ᒽ.ॱ(localClass6, "generatePrivate", new Class[] { localClass7 }) != null) {
        n = 1;
      } else {
        n = 0;
      }
      int i1;
      if (ᒽ.ॱ(localClass6, "generatePublic", new Class[] { localClass7 }) != null) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      int i2;
      if (ᒽ.ॱ(localClass12, "getPrivate", new Class[0]) != null) {
        i2 = 1;
      } else {
        i2 = 0;
      }
      int i3;
      if (ᒽ.ॱ(localClass12, "getPublic", new Class[0]) != null) {
        i3 = 1;
      } else {
        i3 = 0;
      }
      int i4;
      if (ᒽ.ॱ(localClass13, "getInstance", new Class[] { String.class, String.class }) != null) {
        i4 = 1;
      } else {
        i4 = 0;
      }
      boolean bool2 = false;
      int i5;
      if (ᒽ.ॱ(localClass13, "initialize", new Class[] { localClass14 }) != null) {
        i5 = 1;
      } else {
        i5 = 0;
      }
      int i6;
      if (ᒽ.ॱ(localClass13, "generateKeyPair", new Class[0]) != null) {
        i6 = 1;
      } else {
        i6 = 0;
      }
      int i7;
      if (ᒽ.ॱ(localClass15, "getInstance", new Class[] { String.class }) != null) {
        i7 = 1;
      } else {
        i7 = 0;
      }
      int i8;
      if (ᒽ.ॱ(localClass15, "initSign", new Class[] { localClass4 }) != null) {
        i8 = 1;
      } else {
        i8 = 0;
      }
      int i9;
      if (ᒽ.ॱ(localClass15, "update", new Class[] { [B.class }) != null) {
        i9 = 1;
      } else {
        i9 = 0;
      }
      int i10;
      if (ᒽ.ॱ(localClass15, "sign", new Class[0]) != null) {
        i10 = 1;
      } else {
        i10 = 0;
      }
      int i11;
      if (ᒽ.ˋ(localClass11, "F0") != null) {
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
      ॱ = bool1;
      if ((localClass1 != null) && (localClass8 != null) && (localClass9 != null) && (i != 0) && (j != 0) && (k != 0) && (m != 0) && (n != 0) && (i1 != 0)) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ˎ = bool1;
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
      ˊ = bool1;
    }
    
    ˌ() {}
    
    public static boolean ˋ()
    {
      return ˎ;
    }
    
    public static boolean ˎ()
    {
      return ˊ;
    }
    
    public static boolean ˏ()
    {
      return ॱ;
    }
  }
  
  public static final class ˍ
  {
    private static final boolean ˋ;
    
    static
    {
      Class localClass1 = ᒽ.ॱ("android.webkit.WebView");
      Class localClass2 = ᒽ.ॱ("android.webkit.WebViewClient");
      Class localClass3 = ᒽ.ॱ("android.webkit.WebSettings");
      Class localClass4 = ᒽ.ॱ("android.webkit.WebSettings$PluginState");
      Class localClass5 = ᒽ.ॱ("android.webkit.WebChromeClient");
      Method localMethod = ᒽ.ॱ(ᒽ.ॱ("android.webkit.JsResult"), "confirm", new Class[0]);
      boolean bool = true;
      int i;
      if (localMethod != null) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if (ᒽ.ॱ(localClass1, "destroy", new Class[0]) != null) {
        j = 1;
      } else {
        j = 0;
      }
      int k;
      if (ᒽ.ॱ(localClass1, "loadUrl", new Class[] { String.class }) != null) {
        k = 1;
      } else {
        k = 0;
      }
      int m;
      if (ᒽ.ॱ(localClass1, "loadData", new Class[] { String.class, String.class, String.class }) != null) {
        m = 1;
      } else {
        m = 0;
      }
      int n;
      if (ᒽ.ॱ(localClass1, "getSettings", new Class[0]) != null) {
        n = 1;
      } else {
        n = 0;
      }
      int i1;
      if (ᒽ.ॱ(localClass1, "setWebViewClient", new Class[] { localClass2 }) != null) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      int i2;
      if (ᒽ.ॱ(localClass1, "setWebChromeClient", new Class[] { localClass5 }) != null) {
        i2 = 1;
      } else {
        i2 = 0;
      }
      int i3;
      if (ᒽ.ॱ(localClass3, "getUserAgentString", new Class[0]) != null) {
        i3 = 1;
      } else {
        i3 = 0;
      }
      int i4;
      if (ᒽ.ॱ(localClass3, "setJavaScriptEnabled", new Class[] { Boolean.TYPE }) != null) {
        i4 = 1;
      } else {
        i4 = 0;
      }
      int i5;
      if (ᒽ.ˋ(localClass4, "ON") != null) {
        i5 = 1;
      } else {
        i5 = 0;
      }
      if ((localClass2 == null) || (localClass5 == null) || (i == 0) || (j == 0) || (k == 0) || (m == 0) || (n == 0) || (i1 == 0) || (i2 == 0) || (i3 == 0) || (i4 == 0) || (i5 == 0)) {
        bool = false;
      }
      ˋ = bool;
    }
    
    ˍ() {}
    
    public static boolean ˊ()
    {
      return ˋ;
    }
  }
  
  public static final class ˎ
  {
    private static boolean ˎ;
    private static boolean ॱ;
    
    static
    {
      Class localClass1 = ᒽ.ॱ("android.location.Criteria");
      Class localClass2 = ᒽ.ॱ("android.location.Location");
      Class localClass3 = ᒽ.ॱ("android.location.LocationProvider");
      Class localClass4 = ᒽ.ॱ("android.location.LocationListener");
      int i;
      if (ᒽ.ॱ(localClass1, "setAccuracy", new Class[] { Integer.TYPE }) != null) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if (ᒽ.ॱ(localClass1, "setAltitudeRequired", new Class[] { Boolean.TYPE }) != null) {
        j = 1;
      } else {
        j = 0;
      }
      int k;
      if (ᒽ.ॱ(localClass1, "setBearingAccuracy", new Class[] { Integer.TYPE }) != null) {
        k = 1;
      } else {
        k = 0;
      }
      int m;
      if (ᒽ.ॱ(localClass1, "setCostAllowed", new Class[] { Boolean.TYPE }) != null) {
        m = 1;
      } else {
        m = 0;
      }
      int n;
      if (ᒽ.ॱ(localClass1, "setSpeedAccuracy", new Class[] { Integer.TYPE }) != null) {
        n = 1;
      } else {
        n = 0;
      }
      int i1;
      if (ᒽ.ॱ(localClass1, "setSpeedRequired", new Class[] { Boolean.TYPE }) != null) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      int i2;
      if (ᒽ.ॱ(localClass1, "setVerticalAccuracy", new Class[] { Integer.TYPE }) != null) {
        i2 = 1;
      } else {
        i2 = 0;
      }
      int i3;
      if (ᒽ.ॱ(localClass1, "setPowerRequirement", new Class[] { Integer.TYPE }) != null) {
        i3 = 1;
      } else {
        i3 = 0;
      }
      int i4;
      if (ᒽ.ॱ(localClass2, "getTime", new Class[0]) != null) {
        i4 = 1;
      } else {
        i4 = 0;
      }
      int i5;
      if (ᒽ.ॱ(localClass2, "getProvider", new Class[0]) != null) {
        i5 = 1;
      } else {
        i5 = 0;
      }
      ᒽ.ॱ(localClass2, "getAccuracy", new Class[0]);
      int i6;
      if (ᒽ.ॱ(localClass2, "getLatitude", new Class[0]) != null) {
        i6 = 1;
      } else {
        i6 = 0;
      }
      int i7;
      if (ᒽ.ॱ(localClass2, "getLongitude", new Class[0]) != null) {
        i7 = 1;
      } else {
        i7 = 0;
      }
      int i8;
      if (ᒽ.ˋ(localClass1, "NO_REQUIREMENT") != null) {
        i8 = 1;
      } else {
        i8 = 0;
      }
      int i9;
      if (ᒽ.ˋ(localClass1, "POWER_LOW") != null) {
        i9 = 1;
      } else {
        i9 = 0;
      }
      int i10;
      if (ᒽ.ˋ(localClass1, "ACCURACY_LOW") != null) {
        i10 = 1;
      } else {
        i10 = 0;
      }
      int i11;
      if (ᒽ.ˋ(localClass1, "ACCURACY_COARSE") != null) {
        i11 = 1;
      } else {
        i11 = 0;
      }
      int i12;
      if (ᒽ.ˋ(localClass3, "AVAILABLE") != null) {
        i12 = 1;
      } else {
        i12 = 0;
      }
      int i13;
      if (ᒽ.ˋ(localClass3, "TEMPORARILY_UNAVAILABLE") != null) {
        i13 = 1;
      } else {
        i13 = 0;
      }
      int i14;
      if (ᒽ.ˋ(localClass3, "OUT_OF_SERVICE") != null) {
        i14 = 1;
      } else {
        i14 = 0;
      }
      boolean bool;
      if ((i != 0) && (j != 0) && (k != 0) && (m != 0) && (n != 0) && (i1 != 0) && (i2 != 0) && (i3 != 0) && (i8 != 0) && (i9 != 0) && (i10 != 0) && (i11 != 0)) {
        bool = true;
      } else {
        bool = false;
      }
      ॱ = bool;
      if ((localClass4 != null) && (i4 != 0) && (i5 != 0) && (i6 != 0) && (i7 != 0) && (i12 != 0) && (i13 != 0) && (i14 != 0)) {
        bool = true;
      } else {
        bool = false;
      }
      ˎ = bool;
    }
    
    ˎ() {}
    
    public static boolean ˊ()
    {
      return ˎ;
    }
    
    public static boolean ˋ()
    {
      return ॱ;
    }
  }
  
  public static final class ˏ
  {
    private static final boolean ʻ;
    private static final boolean ʽ;
    private static final boolean ˊ;
    private static final boolean ˊॱ;
    private static final boolean ˋ;
    private static final boolean ˎ;
    private static final boolean ˏ;
    private static final boolean ॱ;
    private static final boolean ᐝ;
    
    static
    {
      Class localClass1 = ᒽ.ॱ("android.content.SharedPreferences");
      boolean bool2 = true;
      boolean bool1;
      if (localClass1 != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ˋ = bool1;
      Class localClass2 = ᒽ.ॱ("android.content.SharedPreferences$Editor");
      if (localClass2 != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ˎ = bool1;
      if (ᒽ.ॱ(localClass1, "getInt", new Class[] { String.class, Integer.TYPE }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ˏ = bool1;
      if (ᒽ.ॱ(localClass1, "getLong", new Class[] { String.class, Long.TYPE }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ˊ = bool1;
      if (ᒽ.ॱ(localClass1, "getString", new Class[] { String.class, String.class }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ॱ = bool1;
      if (ᒽ.ॱ(localClass2, "putInt", new Class[] { String.class, Integer.TYPE }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ᐝ = bool1;
      if (ᒽ.ॱ(localClass2, "putLong", new Class[] { String.class, Long.TYPE }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ˊॱ = bool1;
      if (ᒽ.ॱ(localClass2, "putString", new Class[] { String.class, String.class }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ʻ = bool1;
      if (ᒽ.ॱ(localClass2, "apply", new Class[0]) != null) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
      ʽ = bool1;
    }
    
    ˏ() {}
  }
  
  public static final class ͺ
  {
    private static final boolean ˊ;
    private static final boolean ˋ;
    
    static
    {
      Class localClass = ᒽ.ॱ("android.os.PowerManager");
      boolean bool2 = false;
      if (ᒽ.ॱ(localClass, "isInteractive", new Class[0]) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ˋ = bool1;
      boolean bool1 = bool2;
      if (ᒽ.ॱ(localClass, "isScreenOn", new Class[0]) != null) {
        bool1 = true;
      }
      ˊ = bool1;
    }
    
    ͺ() {}
    
    static boolean ˎ()
    {
      return ˋ;
    }
    
    public static boolean ˏ()
    {
      return ˊ;
    }
  }
  
  static final class ᐝ
  {
    private static final boolean ˋ;
    private static final boolean ˎ;
    private static final boolean ˏ;
    
    static
    {
      Class localClass1 = ᒽ.ॱ("java.security.cert.Certificate");
      Class localClass2 = ᒽ.ॱ("java.security.KeyPair");
      Class localClass3 = ᒽ.ॱ("java.security.Key");
      Class localClass4 = ᒽ.ॱ("java.security.KeyStore");
      Class localClass5 = ᒽ.ॱ("java.security.KeyStore$LoadStoreParameter");
      Class localClass6 = ᒽ.ॱ("java.security.KeyStore$ProtectionParameter");
      Class localClass7 = ᒽ.ॱ("java.security.KeyStore$Entry");
      Class localClass8 = ᒽ.ॱ("java.security.KeyStore$PrivateKeyEntry");
      Class localClass9 = ᒽ.ॱ("java.security.PrivateKey");
      Class localClass10 = ᒽ.ॱ("java.security.KeyPairGenerator");
      Class localClass11 = ᒽ.ॱ("java.security.spec.AlgorithmParameterSpec");
      Class localClass12 = ᒽ.ॱ("android.security.KeyChain");
      Class localClass13 = ᒽ.ॱ("java.security.Signature");
      Class localClass14 = ᒽ.ॱ("android.security.KeyPairGeneratorSpec");
      Class localClass15 = ᒽ.ॱ("android.security.KeyPairGeneratorSpec$Builder");
      Class localClass16 = ᒽ.ॱ("javax.security.auth.x500.X500Principal");
      Class localClass17 = ᒽ.ॱ("android.security.keystore.KeyGenParameterSpec");
      Class localClass18 = ᒽ.ॱ("android.security.keystore.KeyGenParameterSpec$Builder");
      Class localClass19 = ᒽ.ॱ("java.security.KeyFactory");
      Class localClass20 = ᒽ.ॱ("android.security.keystore.KeyInfo");
      int i;
      if (ᒽ.ॱ(localClass4, "getInstance", new Class[] { String.class }) != null) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if (ᒽ.ॱ(localClass4, "load", new Class[] { localClass5 }) != null) {
        j = 1;
      } else {
        j = 0;
      }
      int k;
      if (ᒽ.ॱ(localClass4, "getEntry", new Class[] { String.class, localClass6 }) != null) {
        k = 1;
      } else {
        k = 0;
      }
      int m;
      if (ᒽ.ॱ(localClass4, "getCertificate", new Class[] { String.class }) != null) {
        m = 1;
      } else {
        m = 0;
      }
      int n;
      if (ᒽ.ॱ(localClass4, "getCreationDate", new Class[] { String.class }) != null) {
        n = 1;
      } else {
        n = 0;
      }
      int i1;
      if (ᒽ.ॱ(localClass8, "getPrivateKey", new Class[0]) != null) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      int i2;
      if (ᒽ.ॱ(localClass3, "getAlgorithm", new Class[0]) != null) {
        i2 = 1;
      } else {
        i2 = 0;
      }
      int i3;
      if (ᒽ.ॱ(localClass2, "getPrivate", new Class[0]) != null) {
        i3 = 1;
      } else {
        i3 = 0;
      }
      int i4;
      if (ᒽ.ॱ(localClass2, "getPublic", new Class[0]) != null) {
        i4 = 1;
      } else {
        i4 = 0;
      }
      int i5;
      if (ᒽ.ॱ(localClass1, "getPublicKey", new Class[0]) != null) {
        i5 = 1;
      } else {
        i5 = 0;
      }
      int i6;
      if (ᒽ.ॱ(localClass10, "generateKeyPair", new Class[0]) != null) {
        i6 = 1;
      } else {
        i6 = 0;
      }
      int i7;
      if (ᒽ.ॱ(localClass10, "getInstance", new Class[] { String.class, String.class }) != null) {
        i7 = 1;
      } else {
        i7 = 0;
      }
      int i9;
      if (ᒽ.ॱ(localClass10, "initialize", new Class[] { localClass11 }) != null) {
        i9 = 1;
      } else {
        i9 = 0;
      }
      int i8;
      if (ᒽ.ॱ(localClass13, "getInstance", new Class[] { String.class }) != null) {
        i8 = 1;
      } else {
        i8 = 0;
      }
      int i10;
      if (ᒽ.ॱ(localClass13, "initSign", new Class[] { localClass9 }) != null) {
        i10 = 1;
      } else {
        i10 = 0;
      }
      int i11;
      if (ᒽ.ॱ(localClass13, "update", new Class[] { [B.class }) != null) {
        i11 = 1;
      } else {
        i11 = 0;
      }
      int i12;
      if (ᒽ.ॱ(localClass13, "sign", new Class[0]) != null) {
        i12 = 1;
      } else {
        i12 = 0;
      }
      int i14;
      if (ᒽ.ॱ(localClass12, "isKeyAlgorithmSupported", new Class[] { String.class }) != null) {
        i14 = 1;
      } else {
        i14 = 0;
      }
      int i13;
      if (ᒽ.ॱ(localClass15, "setAlias", new Class[] { String.class }) != null) {
        i13 = 1;
      } else {
        i13 = 0;
      }
      int i15;
      if (ᒽ.ॱ(localClass15, "setSubject", new Class[] { localClass16 }) != null) {
        i15 = 1;
      } else {
        i15 = 0;
      }
      int i16;
      if (ᒽ.ॱ(localClass15, "setSerialNumber", new Class[] { BigInteger.class }) != null) {
        i16 = 1;
      } else {
        i16 = 0;
      }
      int i17;
      if (ᒽ.ॱ(localClass15, "setStartDate", new Class[] { Date.class }) != null) {
        i17 = 1;
      } else {
        i17 = 0;
      }
      int i18;
      if (ᒽ.ॱ(localClass15, "setEndDate", new Class[] { Date.class }) != null) {
        i18 = 1;
      } else {
        i18 = 0;
      }
      int i19;
      if (ᒽ.ॱ(localClass15, "setKeyType", new Class[] { String.class }) != null) {
        i19 = 1;
      } else {
        i19 = 0;
      }
      int i20;
      if (ᒽ.ॱ(localClass12, "isBoundKeyAlgorithm", new Class[] { String.class }) != null) {
        i20 = 1;
      } else {
        i20 = 0;
      }
      int i21;
      if (ᒽ.ॱ(localClass18, "setDigests", new Class[] { [Ljava.lang.String.class }) != null) {
        i21 = 1;
      } else {
        i21 = 0;
      }
      int i22;
      if (ᒽ.ॱ(localClass18, "setSignaturePaddings", new Class[] { [Ljava.lang.String.class }) != null) {
        i22 = 1;
      } else {
        i22 = 0;
      }
      boolean bool2 = true;
      int i23;
      if (ᒽ.ॱ(localClass19, "getInstance", new Class[] { String.class, String.class }) != null) {
        i23 = 1;
      } else {
        i23 = 0;
      }
      int i24;
      if (ᒽ.ॱ(localClass19, "getKeySpec", new Class[] { localClass3, Class.class }) != null) {
        i24 = 1;
      } else {
        i24 = 0;
      }
      int i25;
      if (ᒽ.ॱ(localClass20, "isInsideSecureHardware", new Class[0]) != null) {
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
      ˏ = bool1;
      if ((ˏ.if.If.ˊ >= 18) && (i != 0) && (localClass14 != null) && (i13 != 0) && (i15 != 0) && (i16 != 0) && (i17 != 0) && (i18 != 0) && (i19 != 0) && (i20 != 0)) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ˋ = bool1;
      if ((ˏ.if.If.ˊ >= 23) && (i != 0) && (localClass17 != null) && (i21 != 0) && (i22 != 0) && (i23 != 0) && (i24 != 0) && (i25 != 0)) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
      ˎ = bool1;
    }
    
    ᐝ() {}
    
    static boolean ˊ()
    {
      return (ˋ) || (ˎ);
    }
    
    static boolean ˋ()
    {
      return ˋ;
    }
    
    static boolean ˎ()
    {
      return ˏ;
    }
    
    static boolean ˏ()
    {
      return ˎ;
    }
  }
  
  static final class ι
  {
    private static final boolean ˊ;
    private static final boolean ˋ;
    private static final boolean ˎ;
    
    static
    {
      Class localClass = ᒽ.ॱ("android.provider.Settings$Secure");
      boolean bool2 = false;
      if (ᒽ.ॱ(localClass, "getString", new Class[] { ContentResolver.class, String.class }) != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ˋ = bool1;
      if (ᒽ.ˋ(localClass, "ANDROID_ID") != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ˎ = bool1;
      boolean bool1 = bool2;
      if (ᒽ.ˋ(localClass, "ALLOW_MOCK_LOCATION") != null) {
        bool1 = true;
      }
      ˊ = bool1;
    }
    
    ι() {}
    
    static String ˎ(ContentResolver paramContentResolver, String paramString)
    {
      if ((paramContentResolver != null) && (!ˆ.ˊ(paramString))) {
        if (!ˋ) {
          return null;
        }
      }
      try
      {
        if (("ANDROID_ID".equals(paramString)) && (ˎ)) {
          return Settings.Secure.getString(paramContentResolver, "android_id");
        }
        if (("ALLOW_MOCK_LOCATION".equals(paramString)) && (ˊ))
        {
          paramContentResolver = Settings.Secure.getString(paramContentResolver, "mock_location");
          return paramContentResolver;
        }
      }
      catch (Exception paramContentResolver)
      {
        ٴ.ˊ(ˏ.ˋ(), paramContentResolver.toString());
        return null;
        ˏ.ˋ();
        return null;
        return null;
      }
      catch (SecurityException paramContentResolver)
      {
        for (;;) {}
      }
    }
  }
}
