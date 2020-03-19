package com.startapp.android.publish.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Pair;
import com.startapp.android.publish.adsCommon.adinformation.AdInformationConfig;
import com.startapp.android.publish.common.commonUtils.b;
import com.startapp.android.publish.common.commonUtils.i;
import com.startapp.android.publish.common.metaData.MetaData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class g
{
  private static Pair<String, String> a;
  private static Pair<String, String> b;
  private static List<PackageInfo> c;
  private static List<PackageInfo> d;
  private static String e = "token";
  private static boolean f;
  private static boolean g;
  
  public static Pair<String, String> a()
  {
    return a;
  }
  
  private static List<String> a(Context paramContext, List<PackageInfo> paramList)
  {
    i.a("SimpleToken", 3, "getPackagesNames entered");
    paramContext = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      paramContext.add(((PackageInfo)paramList.next()).packageName);
    }
    return paramContext;
  }
  
  private static List<PackageInfo> a(List<PackageInfo> paramList)
  {
    if (paramList.size() <= 100) {
      return paramList;
    }
    b.a(paramList);
    return paramList.subList(0, 100);
  }
  
  public static void a(Context paramContext)
  {
    b(paramContext);
    f = true;
    g = true;
    e = "token";
  }
  
  public static void a(Context paramContext, Pair<String, String> paramPair)
  {
    i.a("SimpleToken", 3, "errorSendingToken entered");
    if (((String)paramPair.first).equals("token"))
    {
      e = "token";
      f = true;
      return;
    }
    e = "token2";
    g = true;
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    return ((paramPackageInfo.applicationInfo.flags & 0x1) != 0) || ((paramPackageInfo.applicationInfo.flags & 0x80) != 0);
  }
  
  public static Pair<String, String> b()
  {
    return b;
  }
  
  public static void b(Context paramContext)
  {
    i.a("SimpleToken", 3, "initSimpleToken entered");
    i(paramContext);
    a = new Pair("token", new com.startapp.android.publish.b.a.b.a().a(a(paramContext, c)));
    b = new Pair("token2", new com.startapp.android.publish.b.a.b.a().a(a(paramContext, d)));
    i.a("SimpleToken", 3, "simpleToken : [" + a + "]");
    i.a("SimpleToken", 3, "simpleToken2 : [" + b + "]");
  }
  
  public static Pair<String, String> c(Context paramContext)
  {
    i.a("SimpleToken", 3, "getSimpleToken entered");
    Pair localPair = new Pair("token", "");
    boolean bool1;
    boolean bool2;
    if (!com.startapp.android.publish.adsCommon.adinformation.a.b().a().g().a(paramContext))
    {
      bool1 = g(paramContext);
      bool2 = h(paramContext);
      if ((bool1) || (bool2)) {}
    }
    else
    {
      return localPair;
    }
    if (e.equals("token"))
    {
      if (bool1) {
        return e(paramContext);
      }
      return f(paramContext);
    }
    if (bool2) {
      return f(paramContext);
    }
    return e(paramContext);
  }
  
  public static List<PackageInfo> d(Context paramContext)
  {
    i(paramContext);
    return c;
  }
  
  private static Pair<String, String> e(Context paramContext)
  {
    f.b(paramContext, "shared_prefs_simple_token", (String)a.second);
    e = "token2";
    f = false;
    return new Pair("token", a.second);
  }
  
  private static Pair<String, String> f(Context paramContext)
  {
    f.b(paramContext, "shared_prefs_simple_token2", (String)b.second);
    e = "token";
    g = false;
    return new Pair("token2", b.second);
  }
  
  private static boolean g(Context paramContext)
  {
    if (com.startapp.android.publish.adsCommon.adinformation.a.b().a().g().b(paramContext))
    {
      if ((MetaData.getInstance().isAlwaysSendToken()) || (f)) {}
      while (!new Pair("token", f.a(paramContext, "shared_prefs_simple_token", "")).equals(a)) {
        return true;
      }
      return false;
    }
    return false;
  }
  
  private static boolean h(Context paramContext)
  {
    if (MetaData.getInstance().isSimpleToken2())
    {
      if ((MetaData.getInstance().isAlwaysSendToken()) || (g)) {}
      while (!new Pair("token2", f.a(paramContext, "shared_prefs_simple_token2", "")).equals(b)) {
        return true;
      }
      return false;
    }
    return false;
  }
  
  private static void i(Context paramContext)
  {
    i.a("SimpleToken", 3, "getPackages entered");
    PackageManager localPackageManager = paramContext.getPackageManager();
    Set localSet = MetaData.getInstance().getInstallersList();
    new ArrayList();
    c = new ArrayList();
    d = new ArrayList();
    label180:
    do
    {
      Object localObject;
      Iterator localIterator;
      PackageInfo localPackageInfo;
      for (;;)
      {
        try
        {
          localObject = localPackageManager.getInstalledPackages(8192);
          paramContext = null;
          localIterator = ((List)localObject).iterator();
          if (!localIterator.hasNext()) {
            break label180;
          }
          localPackageInfo = (PackageInfo)localIterator.next();
          if (!a(localPackageInfo))
          {
            c.add(localPackageInfo);
            localObject = localPackageManager.getInstallerPackageName(localPackageInfo.packageName);
            if ((localSet != null) && (localSet.contains(localObject))) {
              d.add(localPackageInfo);
            }
            localObject = paramContext;
            paramContext = (Context)localObject;
            break;
          }
          localObject = localPackageInfo;
        }
        catch (RuntimeException paramContext)
        {
          i.a("SimpleToken", 6, "Could not complete getInstalledPackages", paramContext);
          return;
        }
        if (!localPackageInfo.packageName.equals(Constants.j)) {
          localObject = paramContext;
        }
      }
      c = a(c);
      d = a(d);
    } while (paramContext == null);
    c.add(0, paramContext);
  }
}
