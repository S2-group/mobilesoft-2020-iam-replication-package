package uk.co.ravensoft.product;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import java.util.Iterator;
import java.util.List;
import uk.co.ravensoft.ravlib.platform.c;
import uk.co.ravensoft.ravlib.platform.inapp.j;

public final class b
{
  public static boolean a;
  private static b f = null;
  private static int h;
  private static int m;
  protected String b = "SDK build";
  protected MapConfiguration c = MapConfiguration.c;
  protected InAppConfiguration d = InAppConfiguration.c;
  protected boolean e = false;
  private String g = "";
  private String i = "";
  private String j = "";
  private String k = "";
  private j l = null;
  private String n = "";
  
  static
  {
    if ((uk.co.ravensoft.ravlib.platform.ui.a.a() != null) && (c.a().endsWith(".trunk"))) {
      h = 5;
    }
    for (boolean bool = true;; bool = false)
    {
      a = bool;
      h = 1;
      m = 0;
      return;
      h = 1;
    }
  }
  
  private b() {}
  
  @SuppressLint({"NewApi"})
  public static int a()
  {
    int i1;
    if (m == 0)
    {
      if (uk.co.ravensoft.ravlib.platform.ui.a.a() == null) {
        break label89;
      }
      if ((uk.co.ravensoft.ravlib.platform.ui.a.a().getApplicationInfo().flags & 0x100000) != 1048576) {
        break label64;
      }
      i1 = 1;
      if ((i1 == 0) || (Build.VERSION.SDK_INT < 11)) {
        break label69;
      }
      m = ((ActivityManager)uk.co.ravensoft.ravlib.platform.ui.a.a().getSystemService("activity")).getLargeMemoryClass();
    }
    for (;;)
    {
      return m;
      label64:
      i1 = 0;
      break;
      label69:
      m = ((ActivityManager)uk.co.ravensoft.ravlib.platform.ui.a.a().getSystemService("activity")).getMemoryClass();
      continue;
      label89:
      uk.co.ravensoft.ravlib.common.a.a.c("RuntimeConfiguration", "Trying to check memory class without context");
    }
  }
  
  public static void a(int paramInt)
  {
    p();
    h = 1;
  }
  
  public static void a(String paramString)
  {
    p().g = paramString;
  }
  
  public static void a(InAppConfiguration paramInAppConfiguration)
  {
    p().d = paramInAppConfiguration;
  }
  
  public static void a(MapConfiguration paramMapConfiguration)
  {
    p().c = paramMapConfiguration;
  }
  
  public static void a(j paramJ)
  {
    p().l = paramJ;
  }
  
  public static void a(boolean paramBoolean)
  {
    p().e = true;
  }
  
  private static boolean a(String[] paramArrayOfString)
  {
    Iterator localIterator = uk.co.ravensoft.ravlib.platform.ui.a.a().getPackageManager().getInstalledPackages(0).iterator();
    boolean bool = false;
    label79:
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      int i2 = paramArrayOfString.length;
      int i1 = 0;
      for (;;)
      {
        if (i1 >= i2) {
          break label79;
        }
        String str = paramArrayOfString[i1];
        if (localPackageInfo.packageName.equals(str))
        {
          bool = true;
          break;
        }
        i1 += 1;
      }
    }
    return bool;
  }
  
  public static String b()
  {
    return p().g;
  }
  
  public static void b(String paramString)
  {
    p().i = paramString;
  }
  
  public static int c()
  {
    p();
    return h;
  }
  
  public static void c(String paramString)
  {
    p().j = paramString;
  }
  
  public static j d()
  {
    return p().l;
  }
  
  public static void d(String paramString)
  {
    p().k = paramString;
  }
  
  public static String e()
  {
    return p().i;
  }
  
  public static void e(String paramString)
  {
    p().n = paramString;
  }
  
  public static void f(String paramString)
  {
    p().b = paramString;
  }
  
  public static boolean f()
  {
    return false;
  }
  
  public static String g()
  {
    return p().j;
  }
  
  public static String h()
  {
    return p().k;
  }
  
  public static String i()
  {
    return p().n;
  }
  
  public static boolean j()
  {
    switch (c.a[p().d.ordinal()])
    {
    default: 
      return false;
    case 1: 
      return m();
    }
    return a(new String[] { "com.amazon.venezia" });
  }
  
  public static boolean k()
  {
    switch (c.b[p().c.ordinal()])
    {
    default: 
      return false;
    }
    return true;
  }
  
  public static MapConfiguration l()
  {
    return p().c;
  }
  
  public static boolean m()
  {
    return (a(new String[] { "com.google.market", "com.android.vending" })) && (Build.VERSION.SDK_INT >= 8);
  }
  
  public static boolean n()
  {
    return p().e;
  }
  
  public static String o()
  {
    return "UA-7707808-7";
  }
  
  private static b p()
  {
    if (f == null) {
      f = new b();
    }
    return f;
  }
}
