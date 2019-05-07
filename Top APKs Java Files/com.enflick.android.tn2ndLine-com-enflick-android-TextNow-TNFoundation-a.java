package com.enflick.android.TextNow.TNFoundation;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class a
{
  static
  {
    a.z.addAll(a.j);
    a.z.addAll(a.g);
    a.A.addAll(a.p);
    a.B.addAll(a.w);
    a.C.addAll(a.o);
    a.C.addAll(a.p);
    a.C.addAll(a.g);
    a.C.addAll(a.f);
    a.D.addAll(a.h);
    a.D.addAll(a.g);
    a.D.addAll(a.i);
    a.D.addAll(a.d);
    a.D.addAll(a.f);
    a.x.addAll(a.u);
    a.y.addAll(a.u);
    a.y.addAll(a.d);
    a.E.addAll(a.u);
    a.E.addAll(a.d);
    a.E.addAll(a.g);
    a.E.addAll(a.h);
    a.G.put(a.c, null);
    a.G.put(a.d, null);
    a.G.put(a.e, null);
    a.G.put(a.q, null);
    a.G.put(a.r, null);
    a.G.put(a.s, null);
    a.G.put(a.t, Collections.singletonList(Integer.valueOf(19)));
    a.G.put(a.k, null);
    a.G.put(a.m, null);
    a.G.put(a.n, null);
    a.F.addAll(a.b);
    a.F.addAll(a.f);
    a.F.addAll(a.i);
    a.F.addAll(a.l);
  }
  
  public static int a(Context paramContext)
  {
    if ((!c(paramContext)) || (a(a.x))) {
      return 0;
    }
    return 1;
  }
  
  public static boolean a()
  {
    return a(a.z);
  }
  
  public static boolean a(String paramString)
  {
    return Build.MODEL.equalsIgnoreCase(paramString);
  }
  
  public static boolean a(List<String> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      if (Build.MODEL.toUpperCase().startsWith(str.toUpperCase())) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean b()
  {
    return a(a.A);
  }
  
  public static boolean b(Context paramContext)
  {
    return (c(paramContext)) && (a(a.y));
  }
  
  public static boolean b(String paramString)
  {
    return Build.MANUFACTURER.equalsIgnoreCase(paramString);
  }
  
  public static boolean b(List<String> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      if (Build.MODEL.equalsIgnoreCase(str)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean c()
  {
    return (a(a.C)) || (Build.VERSION.SDK_INT < 19);
  }
  
  private static boolean c(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    int i = 0;
    if (paramContext.hasNext())
    {
      if (!((ApplicationInfo)paramContext.next()).packageName.equals("com.enflick.textnowoobe")) {
        break label67;
      }
      i = 1;
    }
    label67:
    for (;;)
    {
      break;
      return (i != 0) && (a(a.D));
    }
  }
  
  public static final class a
  {
    public static final List<String> A = new ArrayList();
    public static final List<String> B = new ArrayList();
    public static final List<String> C = new ArrayList();
    public static final List<String> D = new ArrayList();
    public static final List<String> E = new ArrayList();
    public static final List<String> F = new ArrayList();
    public static Map<List<String>, List<Integer>> G = new HashMap();
    public static final List<String> a = Arrays.asList(new String[] { "BNTV", "BNRV", "NOOKColor" });
    public static final List<String> b = Collections.singletonList("Coolpad 3300A");
    public static final List<String> c = Collections.singletonList("LGLS740");
    public static final List<String> d = Collections.singletonList("LGLS660");
    public static final List<String> e = Collections.singletonList("LGLS620");
    public static final List<String> f = Collections.singletonList("LGLS665");
    public static final List<String> g = Collections.singletonList("XT1526");
    public static final List<String> h = Collections.singletonList("XT1031");
    public static final List<String> i = Collections.singletonList("MotoG3");
    public static final List<String> j = Collections.singletonList("Nexus S");
    public static final List<String> k = Collections.singletonList("306SH");
    public static final List<String> l = Collections.singletonList("SM-G930P");
    public static final List<String> m = Collections.singletonList("SPH-M840");
    public static final List<String> n = Collections.singletonList("SM-G360P");
    public static final List<String> o = Arrays.asList(new String[] { "SPH-D710", "SGH-T989", "GT-I9300", "SAMSUNG-SGH-I727", "GT-I9100", "SPH-D700", "SAMSUNG-SGH-I777", "GT-I9210" });
    public static final List<String> p = Collections.singletonList("SPH-L300");
    public static final List<String> q = Collections.singletonList("SPH-L710");
    public static final List<String> r = Collections.singletonList("SPH-L710T");
    public static final List<String> s = Collections.singletonList("SPH-L720");
    public static final List<String> t = Collections.singletonList("SPH-L720T");
    public static final List<String> u = Arrays.asList(new String[] { "SPH-L720T", "SGH-I337", "GT-I9505G", "SGH-M919", "SCH-I545", "SPH-L720", "SCH-R970" });
    public static final List<String> v = Collections.singletonList("SCH-I545");
    public static final List<String> w = Collections.singletonList("N861");
    public static final List<String> x = new ArrayList();
    public static final List<String> y = new ArrayList();
    public static final List<String> z = new ArrayList();
  }
}
