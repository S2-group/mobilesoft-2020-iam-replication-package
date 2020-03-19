package com.startapp.android.publish.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.util.Pair;
import com.startapp.android.publish.common.commonUtils.k;
import com.startapp.android.publish.common.commonUtils.s;
import com.startapp.android.publish.common.metaData.MetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class g
{
  private static final Object a = new Object();
  private static Pair<String, String> b = null;
  private static Pair<String, String> c = null;
  private static List<PackageInfo> d;
  private static List<PackageInfo> e;
  private static long f;
  private static String g = "token";
  private static boolean h;
  private static boolean i;
  
  public static long a()
  {
    return f;
  }
  
  public static void a(Context paramContext)
  {
    b(paramContext);
    h = true;
    i = true;
    g = "token";
  }
  
  private static void a(PackageInfo paramPackageInfo, PackageManager paramPackageManager, Set<String> paramSet)
  {
    try
    {
      paramPackageManager = paramPackageManager.getInstallerPackageName(paramPackageInfo.packageName);
      if ((paramSet != null) && (paramSet.contains(paramPackageManager))) {
        e.add(paramPackageInfo);
      }
      return;
    }
    catch (Exception paramPackageInfo)
    {
      k.a("SimpleToken", 6, "addToPackagesFromInstallers - can't add app to list " + paramPackageInfo.getMessage());
    }
  }
  
  public static void a(Pair<String, String> paramPair)
  {
    k.a("SimpleToken", 3, "errorSendingToken entered");
    if (((String)paramPair.first).equals("token"))
    {
      g = "token";
      h = true;
      return;
    }
    g = "token2";
    i = true;
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    return ((paramPackageInfo.applicationInfo.flags & 0x1) != 0) || ((paramPackageInfo.applicationInfo.flags & 0x80) != 0);
  }
  
  public static Pair<String, String> b()
  {
    if (b != null) {
      return b;
    }
    return new Pair("token", "");
  }
  
  private static List<String> b(List<PackageInfo> paramList)
  {
    k.a("SimpleToken", 3, "getPackagesNames entered");
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.add(((PackageInfo)paramList.next()).packageName);
    }
    return localArrayList;
  }
  
  public static void b(Context paramContext)
  {
    k.a("SimpleToken", 3, "initSimpleToken entered");
    new Thread(new g.1(paramContext)).start();
  }
  
  public static Pair<String, String> c()
  {
    if (c != null) {
      return c;
    }
    return new Pair("token2", "");
  }
  
  public static Pair<String, String> c(Context paramContext)
  {
    k.a("SimpleToken", 3, "getSimpleToken entered");
    Pair localPair = new Pair("token", "");
    try
    {
      if ((s.a()) || (!MetaData.getInstance().getSimpleTokenConfig().a(paramContext)))
      {
        boolean bool1 = h(paramContext);
        boolean bool2 = i(paramContext);
        if ((!bool1) && (!bool2)) {
          return localPair;
        }
        if (g.equals("token"))
        {
          if (bool1) {
            return f(paramContext);
          }
          return g(paramContext);
        }
        if (bool2) {
          return g(paramContext);
        }
        paramContext = f(paramContext);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      k.a("SimpleToken", 6, "failed to get simpleToken ", paramContext);
    }
    return localPair;
  }
  
  private static List<PackageInfo> c(List<PackageInfo> paramList)
  {
    if (paramList.size() <= 100) {
      return paramList;
    }
    paramList = new ArrayList(paramList);
    d(paramList);
    return paramList.subList(0, 100);
  }
  
  public static int d(Context paramContext)
  {
    k.a("SimpleToken", 3, "getPackageList entered");
    int k = 0;
    int j = 0;
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
        k = j;
        int m = j;
        if (paramContext.hasNext())
        {
          k = j;
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          k = j;
          if (a(localPackageInfo))
          {
            k = j;
            boolean bool = localPackageInfo.packageName.equals(Constants.k);
            if (!bool) {}
          }
          else
          {
            j += 1;
          }
        }
        else
        {
          return m;
        }
      }
      catch (Exception paramContext)
      {
        k.a("SimpleToken", 6, "Could not complete getPackagesList", paramContext);
        m = k;
      }
    }
  }
  
  private static void d(List<PackageInfo> paramList)
  {
    if (Build.VERSION.SDK_INT >= 9) {
      Collections.sort(paramList, new g.2());
    }
  }
  
  private static Pair<String, String> f(Context paramContext)
  {
    f.b(paramContext, "shared_prefs_simple_token", (String)b.second);
    g = "token2";
    h = false;
    return new Pair("token", b.second);
  }
  
  private static Pair<String, String> g(Context paramContext)
  {
    f.b(paramContext, "shared_prefs_simple_token2", (String)c.second);
    g = "token";
    i = false;
    return new Pair("token2", c.second);
  }
  
  private static boolean h(Context paramContext)
  {
    if ((s.a()) || (MetaData.getInstance().getSimpleTokenConfig().b(paramContext)))
    {
      if ((MetaData.getInstance().isAlwaysSendToken()) || (h)) {}
      while (!new Pair("token", f.a(paramContext, "shared_prefs_simple_token", "")).equals(b)) {
        return true;
      }
      return false;
    }
    return false;
  }
  
  private static boolean i(Context paramContext)
  {
    if (MetaData.getInstance().isSimpleToken2())
    {
      if ((MetaData.getInstance().isAlwaysSendToken()) || (i)) {}
      while (!new Pair("token2", f.a(paramContext, "shared_prefs_simple_token2", "")).equals(c)) {
        return true;
      }
      return false;
    }
    return false;
  }
  
  private static void j(Context paramContext)
  {
    k.a("SimpleToken", 3, "getPackages entered");
    for (;;)
    {
      Object localObject1;
      synchronized (a)
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        Set localSet1 = MetaData.getInstance().getInstallersList();
        Set localSet2 = MetaData.getInstance().getPreInstalledPackages();
        d = new CopyOnWriteArrayList();
        e = new CopyOnWriteArrayList();
        PackageInfo localPackageInfo;
        try
        {
          localObject1 = localPackageManager.getInstalledPackages(8192);
          if (Build.VERSION.SDK_INT < 9) {
            break label293;
          }
          l = Long.MAX_VALUE;
          f = l;
          paramContext = null;
          Iterator localIterator = ((List)localObject1).iterator();
          if (!localIterator.hasNext()) {
            continue;
          }
          localPackageInfo = (PackageInfo)localIterator.next();
          if ((Build.VERSION.SDK_INT >= 9) && (f > localPackageInfo.firstInstallTime)) {
            f = localPackageInfo.firstInstallTime;
          }
          if (!a(localPackageInfo))
          {
            d.add(localPackageInfo);
            a(localPackageInfo, localPackageManager, localSet1);
            localObject1 = paramContext;
          }
        }
        catch (RuntimeException paramContext)
        {
          k.a("SimpleToken", 6, "Could not complete getInstalledPackages", paramContext);
          return;
        }
        if (localSet2.contains(localPackageInfo.packageName))
        {
          d.add(localPackageInfo);
          localObject1 = paramContext;
        }
        else
        {
          localObject1 = localPackageInfo;
          if (!localPackageInfo.packageName.equals(Constants.k))
          {
            localObject1 = paramContext;
            break label288;
            d = c(d);
            e = c(e);
            if (paramContext != null) {
              d.add(0, paramContext);
            }
            return;
          }
        }
      }
      label288:
      paramContext = (Context)localObject1;
      continue;
      label293:
      long l = 0L;
    }
  }
}
