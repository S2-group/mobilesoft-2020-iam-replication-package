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
      Object localObject2 = null;
      Object localObject1 = ᒽ.ॱ("android.os.Build");
      ˋॱ = (Class)localObject1;
      long l;
      if (ᒽ.ˋ((Class)localObject1, "TIME") != null)
      {
        l = Build.TIME;
        ˊ = l;
        if (ᒽ.ˋ(ˋॱ, "TYPE") == null) {
          break label286;
        }
        localObject1 = Build.TYPE;
        label44:
        ˏ = (String)localObject1;
        if (ᒽ.ˋ(ˋॱ, "TAGS") == null) {
          break label291;
        }
        localObject1 = Build.TAGS;
        label63:
        ॱ = (String)localObject1;
        if (ᒽ.ˋ(ˋॱ, "HOST") == null) {
          break label296;
        }
        localObject1 = Build.HOST;
        label82:
        ˋ = (String)localObject1;
        if (ᒽ.ˋ(ˋॱ, "BRAND") == null) {
          break label301;
        }
        localObject1 = Build.BRAND;
        label101:
        ˎ = (String)localObject1;
        if (ᒽ.ˋ(ˋॱ, "USER") == null) {
          break label306;
        }
        localObject1 = Build.USER;
        label120:
        ʼ = (String)localObject1;
        if (ᒽ.ˋ(ˋॱ, "ID") == null) {
          break label311;
        }
        localObject1 = Build.ID;
        label139:
        ˊॱ = (String)localObject1;
        if (ᒽ.ˋ(ˋॱ, "SERIAL") == null) {
          break label316;
        }
        localObject1 = Build.SERIAL;
        label158:
        ᐝ = (String)localObject1;
        if (ᒽ.ˋ(ˋॱ, "DEVICE") == null) {
          break label321;
        }
        localObject1 = Build.DEVICE;
        label177:
        ʽ = (String)localObject1;
        if (ᒽ.ˋ(ˋॱ, "MODEL") == null) {
          break label326;
        }
        localObject1 = Build.MODEL;
        label196:
        ʻ = (String)localObject1;
        if (ᒽ.ˋ(ˋॱ, "DISPLAY") == null) {
          break label331;
        }
        localObject1 = Build.DISPLAY;
        label215:
        ˏॱ = (String)localObject1;
        if (ᒽ.ˋ(ˋॱ, "PRODUCT") == null) {
          break label336;
        }
        localObject1 = Build.PRODUCT;
        label234:
        ॱˊ = (String)localObject1;
        if (ᒽ.ˋ(ˋॱ, "MANUFACTURER") == null) {
          break label341;
        }
      }
      label286:
      label291:
      label296:
      label301:
      label306:
      label311:
      label316:
      label321:
      label326:
      label331:
      label336:
      label341:
      for (localObject1 = Build.MANUFACTURER;; localObject1 = null)
      {
        ॱˋ = (String)localObject1;
        localObject1 = localObject2;
        if (ᒽ.ˋ(ˋॱ, "BOARD") != null) {
          localObject1 = Build.BOARD;
        }
        ͺ = (String)localObject1;
        return;
        l = Long.MAX_VALUE;
        break;
        localObject1 = null;
        break label44;
        localObject1 = null;
        break label63;
        localObject1 = null;
        break label82;
        localObject1 = null;
        break label101;
        localObject1 = null;
        break label120;
        localObject1 = null;
        break label139;
        localObject1 = null;
        break label158;
        localObject1 = null;
        break label177;
        localObject1 = null;
        break label196;
        localObject1 = null;
        break label215;
        localObject1 = null;
        break label234;
      }
    }
    
    if() {}
    
    public static final class If
    {
      public static final int ˊ;
      public static final String ˋ;
      static final String ॱ;
      
      static
      {
        Object localObject2 = null;
        Class localClass = ᒽ.ॱ("android.os.Build$VERSION");
        Object localObject1;
        if (ᒽ.ˋ(localClass, "RELEASE") != null)
        {
          localObject1 = Build.VERSION.RELEASE;
          ˋ = (String)localObject1;
          if (ᒽ.ˋ(localClass, "SDK_INT") == null) {
            break label67;
          }
        }
        label67:
        for (int i = Build.VERSION.SDK_INT;; i = -1)
        {
          ˊ = i;
          localObject1 = localObject2;
          if (ᒽ.ˋ(localClass, "CODENAME") != null) {
            localObject1 = Build.VERSION.CODENAME;
          }
          ॱ = (String)localObject1;
          return;
          localObject1 = null;
          break;
        }
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
    static final int ˊ;
    private static final boolean ˊॱ;
    static final int ˋ;
    private static final boolean ˎ;
    private static final boolean ˏ;
    private static final boolean ॱ;
    
    static
    {
      boolean bool2 = false;
      Class localClass1 = ᒽ.ॱ("android.content.pm.PackageManager");
      if (localClass1 != null)
      {
        bool1 = true;
        ॱ = bool1;
        Class localClass2 = ᒽ.ॱ("android.content.pm.PackageInfo");
        if (localClass2 == null) {
          break label139;
        }
        bool1 = true;
        label30:
        ʼ = bool1;
        if (ᒽ.ॱ(localClass1, "checkPermission", new Class[] { String.class, String.class }) == null) {
          break label144;
        }
        bool1 = true;
        label59:
        ˊॱ = bool1;
        if (ᒽ.ˋ(localClass2, "versionCode") == null) {
          break label149;
        }
        bool1 = true;
        label74:
        ʻ = bool1;
        if (ᒽ.ˋ(localClass2, "versionName") == null) {
          break label154;
        }
        bool1 = true;
        label89:
        ʽ = bool1;
        if (ᒽ.ॱ("android.content.pm.ApplicationInfo") == null) {
          break label159;
        }
      }
      label139:
      label144:
      label149:
      label154:
      label159:
      for (boolean bool1 = true;; bool1 = false)
      {
        ˏ = bool1;
        bool1 = bool2;
        if (ᒽ.ॱ("android.content.pm.PackageItemInfo") != null) {
          bool1 = true;
        }
        ˎ = bool1;
        ˊ = 1;
        ˋ = 128;
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label30;
        bool1 = false;
        break label59;
        bool1 = false;
        break label74;
        bool1 = false;
        break label89;
      }
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
      int j;
      label67:
      int k;
      label83:
      int m;
      label99:
      int n;
      label116:
      int i1;
      label133:
      int i2;
      label150:
      int i3;
      label167:
      int i4;
      label184:
      int i5;
      label197:
      int i6;
      label210:
      int i7;
      label223:
      int i8;
      label236:
      int i9;
      label249:
      int i10;
      label262:
      int i11;
      if (ᒽ.ॱ(localClass1, "getActiveNetworkInfo", new Class[0]) != null)
      {
        i = 1;
        if (ᒽ.ॱ(localClass2, "getState", new Class[0]) == null) {
          break label426;
        }
        j = 1;
        if (ᒽ.ॱ(localClass2, "getType", new Class[0]) == null) {
          break label431;
        }
        k = 1;
        if (ᒽ.ॱ(localClass2, "getExtraInfo", new Class[0]) == null) {
          break label436;
        }
        m = 1;
        if (ᒽ.ॱ(localClass3, "getBSSID", new Class[0]) == null) {
          break label441;
        }
        n = 1;
        if (ᒽ.ॱ(localClass3, "getSSID", new Class[0]) == null) {
          break label447;
        }
        i1 = 1;
        if (ᒽ.ॱ(localClass3, "getRssi", new Class[0]) == null) {
          break label453;
        }
        i2 = 1;
        if (ᒽ.ॱ(localClass4, "getConnectionInfo", new Class[0]) == null) {
          break label459;
        }
        i3 = 1;
        if (ᒽ.ॱ(localClass2, "isConnectedOrConnecting", new Class[0]) == null) {
          break label465;
        }
        i4 = 1;
        if (ᒽ.ˋ(localClass1, "CONNECTIVITY_ACTION") == null) {
          break label471;
        }
        i5 = 1;
        if (ᒽ.ˋ(localClass1, "TYPE_MOBILE") == null) {
          break label477;
        }
        i6 = 1;
        if (ᒽ.ˋ(localClass1, "TYPE_WIFI") == null) {
          break label483;
        }
        i7 = 1;
        if (ᒽ.ˋ(localClass1, "TYPE_BLUETOOTH") == null) {
          break label489;
        }
        i8 = 1;
        if (ᒽ.ˋ(localClass1, "TYPE_ETHERNET") == null) {
          break label495;
        }
        i9 = 1;
        if (ᒽ.ˋ(localClass4, "NETWORK_STATE_CHANGED_ACTION") == null) {
          break label501;
        }
        i10 = 1;
        if (ᒽ.ˋ(localClass5, "CONNECTED") == null) {
          break label507;
        }
        i11 = 1;
        label275:
        if ((i == 0) || (i4 == 0)) {
          break label513;
        }
        bool = true;
        label287:
        ˎ = bool;
        if ((i5 == 0) || (i11 == 0) || (j == 0) || (m == 0) || (k == 0) || (i6 == 0) || (i7 == 0) || ((ˏ.if.If.ˊ >= ˏ.if.ˋ.ʽ) && ((i9 == 0) || (i8 == 0)))) {
          break label519;
        }
        bool = true;
        label346:
        ॱ = bool;
        if ((i10 == 0) || (i11 == 0) || (n == 0) || (i1 == 0) || (i2 == 0) || (j == 0) || (m == 0)) {
          break label525;
        }
        bool = true;
        label387:
        ˋ = bool;
        if ((i3 == 0) || (n == 0) || (i1 == 0) || (i2 == 0)) {
          break label531;
        }
      }
      label426:
      label431:
      label436:
      label441:
      label447:
      label453:
      label459:
      label465:
      label471:
      label477:
      label483:
      label489:
      label495:
      label501:
      label507:
      label513:
      label519:
      label525:
      label531:
      for (boolean bool = true;; bool = false)
      {
        ˊ = bool;
        return;
        i = 0;
        break;
        j = 0;
        break label67;
        k = 0;
        break label83;
        m = 0;
        break label99;
        n = 0;
        break label116;
        i1 = 0;
        break label133;
        i2 = 0;
        break label150;
        i3 = 0;
        break label167;
        i4 = 0;
        break label184;
        i5 = 0;
        break label197;
        i6 = 0;
        break label210;
        i7 = 0;
        break label223;
        i8 = 0;
        break label236;
        i9 = 0;
        break label249;
        i10 = 0;
        break label262;
        i11 = 0;
        break label275;
        bool = false;
        break label287;
        bool = false;
        break label346;
        bool = false;
        break label387;
      }
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
      catch (PackageManager.NameNotFoundException this$1)
      {
        ˏ.ˋ();
        return;
      }
      catch (SecurityException this$1)
      {
        ˏ.ˋ();
        return;
      }
      catch (Exception this$1)
      {
        ٴ.ˊ(ˏ.ˋ(), ˏ.this.toString());
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
      catch (SecurityException this$1)
      {
        ˏ.ˋ();
        return;
      }
      catch (Exception this$1)
      {
        ٴ.ˊ(ˏ.ˋ(), ˏ.this.toString());
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
      //   11: ifeq +90 -> 101
      //   14: invokestatic 56	com/threatmetrix/TrustDefender/internal/ˏ$ʻ:ʽ	()Z
      //   17: ifeq +84 -> 101
      //   20: aload_0
      //   21: getfield 24	com/threatmetrix/TrustDefender/internal/ˏ$ʾ:ˎ	Landroid/content/pm/PackageManager;
      //   24: ifnull +77 -> 101
      //   27: aload_0
      //   28: getfield 24	com/threatmetrix/TrustDefender/internal/ˏ$ʾ:ˎ	Landroid/content/pm/PackageManager;
      //   31: iconst_0
      //   32: invokevirtual 62	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   35: invokeinterface 68 1 0
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
      //   97: invokestatic 40	com/threatmetrix/TrustDefender/internal/ˏ:ˋ	()Ljava/lang/String;
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
      //   118: invokestatic 40	com/threatmetrix/TrustDefender/internal/ˏ:ˋ	()Ljava/lang/String;
      //   121: aload_2
      //   122: invokevirtual 43	java/lang/Exception:toString	()Ljava/lang/String;
      //   125: invokestatic 48	com/threatmetrix/TrustDefender/internal/ٴ:ˊ	(Ljava/lang/String;Ljava/lang/String;)V
      //   128: goto -27 -> 101
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	131	0	this	ʾ
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
    
    final boolean ˋ(String paramString, int paramInt)
    {
      if ((ˏ.ʻ.ˏ()) && (ˏ.ʻ.ˋ()) && (this.ˎ != null)) {}
      try
      {
        this.ˎ.getPackageInfo(paramString, paramInt);
        return true;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        ˏ.ˋ();
        return false;
      }
      catch (SecurityException paramString)
      {
        for (;;)
        {
          ˏ.ˋ();
        }
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          ٴ.ˊ(ˏ.ˋ(), paramString.toString());
        }
      }
    }
    
    public final boolean ˎ(String paramString1, String paramString2)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (ˏ.ʻ.ॱ())
      {
        bool1 = bool2;
        if (this.ˎ == null) {}
      }
      try
      {
        int i = this.ˎ.checkPermission(paramString1, paramString2);
        bool1 = bool2;
        if (i == 0) {
          bool1 = true;
        }
        return bool1;
      }
      catch (SecurityException paramString1)
      {
        ˏ.ˋ();
        return false;
      }
      catch (Exception paramString1)
      {
        ٴ.ˊ(ˏ.ˋ(), paramString1.toString());
      }
      return false;
    }
  }
  
  static final class ʿ
  {
    private static final boolean ˊ;
    private static final boolean ˏ;
    
    static
    {
      boolean bool2 = true;
      Class localClass = ᒽ.ॱ("android.os.SystemClock");
      if (ᒽ.ॱ(localClass, "uptimeMillis", new Class[0]) != null)
      {
        bool1 = true;
        ˊ = bool1;
        if (ᒽ.ॱ(localClass, "elapsedRealtime", new Class[0]) == null) {
          break label52;
        }
      }
      label52:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        ˏ = bool1;
        return;
        bool1 = false;
        break;
      }
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
    private static final Class<?> ˋ;
    private static final Class<?> ˎ;
    private static final Class<?> ˏ;
    private static final boolean ͺ;
    private static final Class<?> ॱ;
    private static final boolean ॱˊ;
    private static final boolean ॱˋ;
    private static final Class<?> ᐝ;
    
    static
    {
      boolean bool2 = false;
      ˋ = ᒽ.ॱ("android.telephony.TelephonyManager");
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
      boolean bool1 = bool2;
      if (ᒽ.ॱ("android.telephony.CellLocation") != null)
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
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (ॱˊ)
      {
        bool1 = bool2;
        if (ᒽ.ॱ(ᐝ, "getCid", new Class[0]) != null)
        {
          bool1 = bool2;
          if (ᒽ.ॱ(ᐝ, "getLac", new Class[0]) != null)
          {
            bool1 = bool2;
            if (ᒽ.ॱ(ᐝ, "getPsc", new Class[0]) != null) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    
    static boolean ˊॱ()
    {
      return ॱˋ;
    }
    
    static boolean ˋ()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (ˋ != null)
      {
        bool1 = bool2;
        if (ᒽ.ॱ(ˋ, "getNetworkOperator", new Class[0]) != null)
        {
          bool1 = bool2;
          if (ᒽ.ॱ(ˋ, "getNetworkCountryIso", new Class[0]) != null)
          {
            bool1 = bool2;
            if (ᒽ.ˋ(ˋ, "getNetworkOperatorName", new Class[0]) != null) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    
    private static boolean ˋ(String paramString1, String paramString2)
    {
      boolean bool2 = false;
      paramString1 = ᒽ.ॱ(paramString1);
      boolean bool1 = bool2;
      if (ᒽ.ॱ(paramString2) != null)
      {
        bool1 = bool2;
        if (ᒽ.ˋ(paramString1, "getCellSignalStrength", new Class[0]) != null)
        {
          bool1 = bool2;
          if (ᒽ.ˋ(paramString1, "getCellIdentity", new Class[0]) != null) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    
    static boolean ˎ()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (ˊ != null)
      {
        bool1 = bool2;
        if (ᒽ.ॱ(ˊ, "getCid", new Class[0]) != null)
        {
          bool1 = bool2;
          if (ᒽ.ॱ(ˊ, "getRssi", new Class[0]) != null) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    
    static boolean ˏ()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (ˋ != null)
      {
        bool1 = bool2;
        if (ˎ != null)
        {
          bool1 = bool2;
          if (ˏ != null)
          {
            bool1 = bool2;
            if (ᒽ.ॱ(ˏ, "isRegistered", new Class[0]) != null)
            {
              bool1 = bool2;
              if (ᒽ.ॱ(ˋ, "getAllCellInfo", new Class[0]) != null) {
                bool1 = true;
              }
            }
          }
        }
      }
      return bool1;
    }
    
    static boolean ॱ()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (ॱˊ)
      {
        bool1 = bool2;
        if (ᒽ.ॱ(ˊॱ, "getSystemId", new Class[0]) != null)
        {
          bool1 = bool2;
          if (ᒽ.ॱ(ˊॱ, "getBaseStationId", new Class[0]) != null)
          {
            bool1 = bool2;
            if (ᒽ.ॱ(ˊॱ, "getBaseStationLatitude", new Class[0]) != null)
            {
              bool1 = bool2;
              if (ᒽ.ॱ(ˊॱ, "getBaseStationLongitude", new Class[0]) != null) {
                bool1 = true;
              }
            }
          }
        }
      }
      return bool1;
    }
    
    static boolean ॱˋ()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (ˋ != null)
      {
        bool1 = bool2;
        if (ᒽ.ॱ(ˋ, "getDataState", new Class[0]) != null)
        {
          bool1 = bool2;
          if (ᒽ.ˋ(ˋ, "DATA_CONNECTED") != null)
          {
            bool1 = bool2;
            if (ᒽ.ˋ(ˋ, "DATA_CONNECTING") != null)
            {
              bool1 = bool2;
              if (ᒽ.ˋ(ˋ, "DATA_SUSPENDED") != null) {
                bool1 = true;
              }
            }
          }
        }
      }
      return bool1;
    }
    
    static boolean ᐝ()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (ʻ != null)
      {
        bool1 = bool2;
        if (ॱ != null)
        {
          bool1 = bool2;
          if (ᒽ.ॱ(ॱ, "getSimSlotIndex", new Class[0]) != null)
          {
            bool1 = bool2;
            if (ᒽ.ॱ(ॱ, "getCarrierName", new Class[0]) != null)
            {
              bool1 = bool2;
              if (ᒽ.ॱ(ॱ, "getDisplayName", new Class[0]) != null)
              {
                bool1 = bool2;
                if (ᒽ.ॱ(ॱ, "getIccId", new Class[0]) != null)
                {
                  bool1 = bool2;
                  if (ᒽ.ॱ(ॱ, "getNumber", new Class[0]) != null)
                  {
                    bool1 = bool2;
                    if (ᒽ.ॱ(ॱ, "getCountryIso", new Class[0]) != null)
                    {
                      bool1 = bool2;
                      if (ᒽ.ॱ(ॱ, "getDataRoaming", new Class[0]) != null)
                      {
                        bool1 = bool2;
                        if (ᒽ.ॱ(ʻ, "getActiveSubscriptionInfoList", new Class[0]) != null) {
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
      boolean bool = false;
      if (ᒽ.ॱ(ᒽ.ॱ("android.app.admin.DevicePolicyManager"), "getStorageEncryptionStatus", new Class[0]) != null) {
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
      int j;
      label158:
      int k;
      label179:
      int m;
      label200:
      int n;
      label222:
      int i1;
      label244:
      int i2;
      label261:
      int i3;
      label278:
      int i4;
      label305:
      int i5;
      label327:
      int i6;
      label344:
      int i7;
      label366:
      int i8;
      label388:
      int i9;
      label410:
      int i10;
      label427:
      int i11;
      if (ᒽ.ॱ(localClass2, "getInstance", new Class[] { String.class, String.class }) != null)
      {
        i = 1;
        if (ᒽ.ॱ(localClass2, "init", new Class[] { Integer.TYPE, localClass3 }) == null) {
          break label589;
        }
        j = 1;
        if (ᒽ.ॱ(localClass2, "doFinal", new Class[] { [B.class }) == null) {
          break label594;
        }
        k = 1;
        if (ᒽ.ॱ(localClass6, "getInstance", new Class[] { String.class }) == null) {
          break label599;
        }
        m = 1;
        if (ᒽ.ॱ(localClass6, "generatePrivate", new Class[] { localClass7 }) == null) {
          break label604;
        }
        n = 1;
        if (ᒽ.ॱ(localClass6, "generatePublic", new Class[] { localClass7 }) == null) {
          break label610;
        }
        i1 = 1;
        if (ᒽ.ॱ(localClass12, "getPrivate", new Class[0]) == null) {
          break label616;
        }
        i2 = 1;
        if (ᒽ.ॱ(localClass12, "getPublic", new Class[0]) == null) {
          break label622;
        }
        i3 = 1;
        if (ᒽ.ॱ(localClass13, "getInstance", new Class[] { String.class, String.class }) == null) {
          break label628;
        }
        i4 = 1;
        if (ᒽ.ॱ(localClass13, "initialize", new Class[] { localClass14 }) == null) {
          break label634;
        }
        i5 = 1;
        if (ᒽ.ॱ(localClass13, "generateKeyPair", new Class[0]) == null) {
          break label640;
        }
        i6 = 1;
        if (ᒽ.ॱ(localClass15, "getInstance", new Class[] { String.class }) == null) {
          break label646;
        }
        i7 = 1;
        if (ᒽ.ॱ(localClass15, "initSign", new Class[] { localClass4 }) == null) {
          break label652;
        }
        i8 = 1;
        if (ᒽ.ॱ(localClass15, "update", new Class[] { [B.class }) == null) {
          break label658;
        }
        i9 = 1;
        if (ᒽ.ॱ(localClass15, "sign", new Class[0]) == null) {
          break label664;
        }
        i10 = 1;
        if (ᒽ.ˋ(localClass11, "F0") == null) {
          break label670;
        }
        i11 = 1;
        label440:
        if ((i7 == 0) || (i8 == 0) || (i9 == 0) || (i10 == 0)) {
          break label676;
        }
        bool = true;
        label463:
        ॱ = bool;
        if ((localClass1 == null) || (localClass8 == null) || (localClass9 == null) || (i == 0) || (j == 0) || (k == 0) || (m == 0) || (n == 0) || (i1 == 0)) {
          break label682;
        }
        bool = true;
        label512:
        ˎ = bool;
        if (((localClass10 == null) && ((localClass11 == null) || (i11 == 0))) || (localClass4 == null) || (localClass5 == null) || (i4 == 0) || (i5 == 0) || (i6 == 0) || (i2 == 0) || (i3 == 0) || (j == 0) || (k == 0)) {
          break label688;
        }
      }
      label589:
      label594:
      label599:
      label604:
      label610:
      label616:
      label622:
      label628:
      label634:
      label640:
      label646:
      label652:
      label658:
      label664:
      label670:
      label676:
      label682:
      label688:
      for (boolean bool = true;; bool = false)
      {
        ˊ = bool;
        return;
        i = 0;
        break;
        j = 0;
        break label158;
        k = 0;
        break label179;
        m = 0;
        break label200;
        n = 0;
        break label222;
        i1 = 0;
        break label244;
        i2 = 0;
        break label261;
        i3 = 0;
        break label278;
        i4 = 0;
        break label305;
        i5 = 0;
        break label327;
        i6 = 0;
        break label344;
        i7 = 0;
        break label366;
        i8 = 0;
        break label388;
        i9 = 0;
        break label410;
        i10 = 0;
        break label427;
        i11 = 0;
        break label440;
        bool = false;
        break label463;
        bool = false;
        break label512;
      }
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
      int i;
      int j;
      label70:
      int k;
      label91:
      int m;
      label122:
      int n;
      label139:
      int i1;
      label161:
      int i2;
      label183:
      int i3;
      label200:
      int i4;
      label223:
      int i5;
      if (ᒽ.ॱ(ᒽ.ॱ("android.webkit.JsResult"), "confirm", new Class[0]) != null)
      {
        i = 1;
        if (ᒽ.ॱ(localClass1, "destroy", new Class[0]) == null) {
          break label306;
        }
        j = 1;
        if (ᒽ.ॱ(localClass1, "loadUrl", new Class[] { String.class }) == null) {
          break label311;
        }
        k = 1;
        if (ᒽ.ॱ(localClass1, "loadData", new Class[] { String.class, String.class, String.class }) == null) {
          break label316;
        }
        m = 1;
        if (ᒽ.ॱ(localClass1, "getSettings", new Class[0]) == null) {
          break label321;
        }
        n = 1;
        if (ᒽ.ॱ(localClass1, "setWebViewClient", new Class[] { localClass2 }) == null) {
          break label327;
        }
        i1 = 1;
        if (ᒽ.ॱ(localClass1, "setWebChromeClient", new Class[] { localClass5 }) == null) {
          break label333;
        }
        i2 = 1;
        if (ᒽ.ॱ(localClass3, "getUserAgentString", new Class[0]) == null) {
          break label339;
        }
        i3 = 1;
        if (ᒽ.ॱ(localClass3, "setJavaScriptEnabled", new Class[] { Boolean.TYPE }) == null) {
          break label345;
        }
        i4 = 1;
        if (ᒽ.ˋ(localClass4, "ON") == null) {
          break label351;
        }
        i5 = 1;
        label236:
        if ((localClass2 == null) || (localClass5 == null) || (i == 0) || (j == 0) || (k == 0) || (m == 0) || (n == 0) || (i1 == 0) || (i2 == 0) || (i3 == 0) || (i4 == 0) || (i5 == 0)) {
          break label357;
        }
      }
      label306:
      label311:
      label316:
      label321:
      label327:
      label333:
      label339:
      label345:
      label351:
      label357:
      for (boolean bool = true;; bool = false)
      {
        ˋ = bool;
        return;
        i = 0;
        break;
        j = 0;
        break label70;
        k = 0;
        break label91;
        m = 0;
        break label122;
        n = 0;
        break label139;
        i1 = 0;
        break label161;
        i2 = 0;
        break label183;
        i3 = 0;
        break label200;
        i4 = 0;
        break label223;
        i5 = 0;
        break label236;
      }
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
      int j;
      label72:
      int k;
      label94:
      int m;
      label116:
      int n;
      label139:
      int i1;
      label162:
      int i2;
      label185:
      int i3;
      label208:
      int i4;
      label225:
      int i5;
      label242:
      int i6;
      label271:
      int i7;
      label288:
      int i8;
      label301:
      int i9;
      label314:
      int i10;
      label327:
      int i11;
      label340:
      int i12;
      label353:
      int i13;
      label366:
      int i14;
      if (ᒽ.ॱ(localClass1, "setAccuracy", new Class[] { Integer.TYPE }) != null)
      {
        i = 1;
        if (ᒽ.ॱ(localClass1, "setAltitudeRequired", new Class[] { Boolean.TYPE }) == null) {
          break label497;
        }
        j = 1;
        if (ᒽ.ॱ(localClass1, "setBearingAccuracy", new Class[] { Integer.TYPE }) == null) {
          break label502;
        }
        k = 1;
        if (ᒽ.ॱ(localClass1, "setCostAllowed", new Class[] { Boolean.TYPE }) == null) {
          break label507;
        }
        m = 1;
        if (ᒽ.ॱ(localClass1, "setSpeedAccuracy", new Class[] { Integer.TYPE }) == null) {
          break label512;
        }
        n = 1;
        if (ᒽ.ॱ(localClass1, "setSpeedRequired", new Class[] { Boolean.TYPE }) == null) {
          break label518;
        }
        i1 = 1;
        if (ᒽ.ॱ(localClass1, "setVerticalAccuracy", new Class[] { Integer.TYPE }) == null) {
          break label524;
        }
        i2 = 1;
        if (ᒽ.ॱ(localClass1, "setPowerRequirement", new Class[] { Integer.TYPE }) == null) {
          break label530;
        }
        i3 = 1;
        if (ᒽ.ॱ(localClass2, "getTime", new Class[0]) == null) {
          break label536;
        }
        i4 = 1;
        if (ᒽ.ॱ(localClass2, "getProvider", new Class[0]) == null) {
          break label542;
        }
        i5 = 1;
        ᒽ.ॱ(localClass2, "getAccuracy", new Class[0]);
        if (ᒽ.ॱ(localClass2, "getLatitude", new Class[0]) == null) {
          break label548;
        }
        i6 = 1;
        if (ᒽ.ॱ(localClass2, "getLongitude", new Class[0]) == null) {
          break label554;
        }
        i7 = 1;
        if (ᒽ.ˋ(localClass1, "NO_REQUIREMENT") == null) {
          break label560;
        }
        i8 = 1;
        if (ᒽ.ˋ(localClass1, "POWER_LOW") == null) {
          break label566;
        }
        i9 = 1;
        if (ᒽ.ˋ(localClass1, "ACCURACY_LOW") == null) {
          break label572;
        }
        i10 = 1;
        if (ᒽ.ˋ(localClass1, "ACCURACY_COARSE") == null) {
          break label578;
        }
        i11 = 1;
        if (ᒽ.ˋ(localClass3, "AVAILABLE") == null) {
          break label584;
        }
        i12 = 1;
        if (ᒽ.ˋ(localClass3, "TEMPORARILY_UNAVAILABLE") == null) {
          break label590;
        }
        i13 = 1;
        if (ᒽ.ˋ(localClass3, "OUT_OF_SERVICE") == null) {
          break label596;
        }
        i14 = 1;
        label379:
        if ((i == 0) || (j == 0) || (k == 0) || (m == 0) || (n == 0) || (i1 == 0) || (i2 == 0) || (i3 == 0) || (i8 == 0) || (i9 == 0) || (i10 == 0) || (i11 == 0)) {
          break label602;
        }
        bool = true;
        label438:
        ॱ = bool;
        if ((localClass4 == null) || (i4 == 0) || (i5 == 0) || (i6 == 0) || (i7 == 0) || (i12 == 0) || (i13 == 0) || (i14 == 0)) {
          break label608;
        }
      }
      label497:
      label502:
      label507:
      label512:
      label518:
      label524:
      label530:
      label536:
      label542:
      label548:
      label554:
      label560:
      label566:
      label572:
      label578:
      label584:
      label590:
      label596:
      label602:
      label608:
      for (boolean bool = true;; bool = false)
      {
        ˎ = bool;
        return;
        i = 0;
        break;
        j = 0;
        break label72;
        k = 0;
        break label94;
        m = 0;
        break label116;
        n = 0;
        break label139;
        i1 = 0;
        break label162;
        i2 = 0;
        break label185;
        i3 = 0;
        break label208;
        i4 = 0;
        break label225;
        i5 = 0;
        break label242;
        i6 = 0;
        break label271;
        i7 = 0;
        break label288;
        i8 = 0;
        break label301;
        i9 = 0;
        break label314;
        i10 = 0;
        break label327;
        i11 = 0;
        break label340;
        i12 = 0;
        break label353;
        i13 = 0;
        break label366;
        i14 = 0;
        break label379;
        bool = false;
        break label438;
      }
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
      boolean bool2 = true;
      Class localClass1 = ᒽ.ॱ("android.content.SharedPreferences");
      if (localClass1 != null)
      {
        bool1 = true;
        ˋ = bool1;
        Class localClass2 = ᒽ.ॱ("android.content.SharedPreferences$Editor");
        if (localClass2 == null) {
          break label237;
        }
        bool1 = true;
        label30:
        ˎ = bool1;
        if (ᒽ.ॱ(localClass1, "getInt", new Class[] { String.class, Integer.TYPE }) == null) {
          break label242;
        }
        bool1 = true;
        label60:
        ˏ = bool1;
        if (ᒽ.ॱ(localClass1, "getLong", new Class[] { String.class, Long.TYPE }) == null) {
          break label247;
        }
        bool1 = true;
        label90:
        ˊ = bool1;
        if (ᒽ.ॱ(localClass1, "getString", new Class[] { String.class, String.class }) == null) {
          break label252;
        }
        bool1 = true;
        label119:
        ॱ = bool1;
        if (ᒽ.ॱ(localClass2, "putInt", new Class[] { String.class, Integer.TYPE }) == null) {
          break label257;
        }
        bool1 = true;
        label149:
        ᐝ = bool1;
        if (ᒽ.ॱ(localClass2, "putLong", new Class[] { String.class, Long.TYPE }) == null) {
          break label262;
        }
        bool1 = true;
        label179:
        ˊॱ = bool1;
        if (ᒽ.ॱ(localClass2, "putString", new Class[] { String.class, String.class }) == null) {
          break label267;
        }
        bool1 = true;
        label208:
        ʻ = bool1;
        if (ᒽ.ॱ(localClass2, "apply", new Class[0]) == null) {
          break label272;
        }
      }
      label237:
      label242:
      label247:
      label252:
      label257:
      label262:
      label267:
      label272:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        ʽ = bool1;
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label30;
        bool1 = false;
        break label60;
        bool1 = false;
        break label90;
        bool1 = false;
        break label119;
        bool1 = false;
        break label149;
        bool1 = false;
        break label179;
        bool1 = false;
        break label208;
      }
    }
    
    ˏ() {}
  }
  
  public static final class ͺ
  {
    private static final boolean ˊ;
    private static final boolean ˋ;
    
    static
    {
      boolean bool2 = true;
      Class localClass = ᒽ.ॱ("android.os.PowerManager");
      if (ᒽ.ॱ(localClass, "isInteractive", new Class[0]) != null)
      {
        bool1 = true;
        ˋ = bool1;
        if (ᒽ.ॱ(localClass, "isScreenOn", new Class[0]) == null) {
          break label52;
        }
      }
      label52:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        ˊ = bool1;
        return;
        bool1 = false;
        break;
      }
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
      int j;
      label182:
      int k;
      label208:
      int m;
      label229:
      int n;
      label251:
      int i1;
      label268:
      int i2;
      label285:
      int i3;
      label302:
      int i4;
      label319:
      int i5;
      label336:
      int i6;
      label353:
      int i7;
      label380:
      int i9;
      label402:
      int i8;
      label424:
      int i10;
      label446:
      int i11;
      label468:
      int i12;
      label485:
      int i14;
      label507:
      int i13;
      label529:
      int i15;
      label551:
      int i16;
      label573:
      int i17;
      label595:
      int i18;
      label617:
      int i19;
      label639:
      int i20;
      label661:
      int i21;
      label683:
      int i22;
      label705:
      int i23;
      label732:
      int i24;
      label759:
      int i25;
      if (ᒽ.ॱ(localClass4, "getInstance", new Class[] { String.class }) != null)
      {
        i = 1;
        if (ᒽ.ॱ(localClass4, "load", new Class[] { localClass5 }) == null) {
          break label1003;
        }
        j = 1;
        if (ᒽ.ॱ(localClass4, "getEntry", new Class[] { String.class, localClass6 }) == null) {
          break label1008;
        }
        k = 1;
        if (ᒽ.ॱ(localClass4, "getCertificate", new Class[] { String.class }) == null) {
          break label1013;
        }
        m = 1;
        if (ᒽ.ॱ(localClass4, "getCreationDate", new Class[] { String.class }) == null) {
          break label1018;
        }
        n = 1;
        if (ᒽ.ॱ(localClass8, "getPrivateKey", new Class[0]) == null) {
          break label1024;
        }
        i1 = 1;
        if (ᒽ.ॱ(localClass3, "getAlgorithm", new Class[0]) == null) {
          break label1030;
        }
        i2 = 1;
        if (ᒽ.ॱ(localClass2, "getPrivate", new Class[0]) == null) {
          break label1036;
        }
        i3 = 1;
        if (ᒽ.ॱ(localClass2, "getPublic", new Class[0]) == null) {
          break label1042;
        }
        i4 = 1;
        if (ᒽ.ॱ(localClass1, "getPublicKey", new Class[0]) == null) {
          break label1048;
        }
        i5 = 1;
        if (ᒽ.ॱ(localClass10, "generateKeyPair", new Class[0]) == null) {
          break label1054;
        }
        i6 = 1;
        if (ᒽ.ॱ(localClass10, "getInstance", new Class[] { String.class, String.class }) == null) {
          break label1060;
        }
        i7 = 1;
        if (ᒽ.ॱ(localClass10, "initialize", new Class[] { localClass11 }) == null) {
          break label1066;
        }
        i9 = 1;
        if (ᒽ.ॱ(localClass13, "getInstance", new Class[] { String.class }) == null) {
          break label1072;
        }
        i8 = 1;
        if (ᒽ.ॱ(localClass13, "initSign", new Class[] { localClass9 }) == null) {
          break label1078;
        }
        i10 = 1;
        if (ᒽ.ॱ(localClass13, "update", new Class[] { [B.class }) == null) {
          break label1084;
        }
        i11 = 1;
        if (ᒽ.ॱ(localClass13, "sign", new Class[0]) == null) {
          break label1090;
        }
        i12 = 1;
        if (ᒽ.ॱ(localClass12, "isKeyAlgorithmSupported", new Class[] { String.class }) == null) {
          break label1096;
        }
        i14 = 1;
        if (ᒽ.ॱ(localClass15, "setAlias", new Class[] { String.class }) == null) {
          break label1102;
        }
        i13 = 1;
        if (ᒽ.ॱ(localClass15, "setSubject", new Class[] { localClass16 }) == null) {
          break label1108;
        }
        i15 = 1;
        if (ᒽ.ॱ(localClass15, "setSerialNumber", new Class[] { BigInteger.class }) == null) {
          break label1114;
        }
        i16 = 1;
        if (ᒽ.ॱ(localClass15, "setStartDate", new Class[] { Date.class }) == null) {
          break label1120;
        }
        i17 = 1;
        if (ᒽ.ॱ(localClass15, "setEndDate", new Class[] { Date.class }) == null) {
          break label1126;
        }
        i18 = 1;
        if (ᒽ.ॱ(localClass15, "setKeyType", new Class[] { String.class }) == null) {
          break label1132;
        }
        i19 = 1;
        if (ᒽ.ॱ(localClass12, "isBoundKeyAlgorithm", new Class[] { String.class }) == null) {
          break label1138;
        }
        i20 = 1;
        if (ᒽ.ॱ(localClass18, "setDigests", new Class[] { [Ljava.lang.String.class }) == null) {
          break label1144;
        }
        i21 = 1;
        if (ᒽ.ॱ(localClass18, "setSignaturePaddings", new Class[] { [Ljava.lang.String.class }) == null) {
          break label1150;
        }
        i22 = 1;
        if (ᒽ.ॱ(localClass19, "getInstance", new Class[] { String.class, String.class }) == null) {
          break label1156;
        }
        i23 = 1;
        if (ᒽ.ॱ(localClass19, "getKeySpec", new Class[] { localClass3, Class.class }) == null) {
          break label1162;
        }
        i24 = 1;
        if (ᒽ.ॱ(localClass20, "isInsideSecureHardware", new Class[0]) == null) {
          break label1168;
        }
        i25 = 1;
        label776:
        if ((localClass7 == null) || (localClass8 == null) || (localClass9 == null) || (i == 0) || (j == 0) || (k == 0) || (i1 == 0) || (m == 0) || (n == 0) || (i2 == 0) || (i3 == 0) || (i4 == 0) || (i5 == 0) || (i6 == 0) || (i7 == 0) || (i9 == 0) || (i14 == 0)) {
          break label1174;
        }
        i = 1;
        label859:
        if ((i8 == 0) || (i10 == 0) || (i11 == 0) || (i12 == 0)) {
          break label1179;
        }
        bool = true;
        label882:
        ˏ = bool;
        if ((ˏ.if.If.ˊ < 18) || (i == 0) || (localClass14 == null) || (i13 == 0) || (i15 == 0) || (i16 == 0) || (i17 == 0) || (i18 == 0) || (i19 == 0) || (i20 == 0)) {
          break label1185;
        }
        bool = true;
        label942:
        ˋ = bool;
        if ((ˏ.if.If.ˊ < 23) || (i == 0) || (localClass17 == null) || (i21 == 0) || (i22 == 0) || (i23 == 0) || (i24 == 0) || (i25 == 0)) {
          break label1191;
        }
      }
      label1003:
      label1008:
      label1013:
      label1018:
      label1024:
      label1030:
      label1036:
      label1042:
      label1048:
      label1054:
      label1060:
      label1066:
      label1072:
      label1078:
      label1084:
      label1090:
      label1096:
      label1102:
      label1108:
      label1114:
      label1120:
      label1126:
      label1132:
      label1138:
      label1144:
      label1150:
      label1156:
      label1162:
      label1168:
      label1174:
      label1179:
      label1185:
      label1191:
      for (boolean bool = true;; bool = false)
      {
        ˎ = bool;
        return;
        i = 0;
        break;
        j = 0;
        break label182;
        k = 0;
        break label208;
        m = 0;
        break label229;
        n = 0;
        break label251;
        i1 = 0;
        break label268;
        i2 = 0;
        break label285;
        i3 = 0;
        break label302;
        i4 = 0;
        break label319;
        i5 = 0;
        break label336;
        i6 = 0;
        break label353;
        i7 = 0;
        break label380;
        i9 = 0;
        break label402;
        i8 = 0;
        break label424;
        i10 = 0;
        break label446;
        i11 = 0;
        break label468;
        i12 = 0;
        break label485;
        i14 = 0;
        break label507;
        i13 = 0;
        break label529;
        i15 = 0;
        break label551;
        i16 = 0;
        break label573;
        i17 = 0;
        break label595;
        i18 = 0;
        break label617;
        i19 = 0;
        break label639;
        i20 = 0;
        break label661;
        i21 = 0;
        break label683;
        i22 = 0;
        break label705;
        i23 = 0;
        break label732;
        i24 = 0;
        break label759;
        i25 = 0;
        break label776;
        i = 0;
        break label859;
        bool = false;
        break label882;
        bool = false;
        break label942;
      }
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
      boolean bool2 = true;
      Class localClass = ᒽ.ॱ("android.provider.Settings$Secure");
      if (ᒽ.ॱ(localClass, "getString", new Class[] { ContentResolver.class, String.class }) != null)
      {
        bool1 = true;
        ˋ = bool1;
        if (ᒽ.ˋ(localClass, "ANDROID_ID") == null) {
          break label73;
        }
        bool1 = true;
        label48:
        ˎ = bool1;
        if (ᒽ.ˋ(localClass, "ALLOW_MOCK_LOCATION") == null) {
          break label78;
        }
      }
      label73:
      label78:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        ˊ = bool1;
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label48;
      }
    }
    
    ι() {}
    
    static String ˎ(ContentResolver paramContentResolver, String paramString)
    {
      if ((paramContentResolver == null) || (ˆ.ˊ(paramString)) || (!ˋ)) {}
      for (;;)
      {
        return null;
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
        catch (SecurityException paramContentResolver)
        {
          ˏ.ˋ();
          return null;
        }
        catch (Exception paramContentResolver)
        {
          ٴ.ˊ(ˏ.ˋ(), paramContentResolver.toString());
        }
      }
      return null;
    }
  }
}
