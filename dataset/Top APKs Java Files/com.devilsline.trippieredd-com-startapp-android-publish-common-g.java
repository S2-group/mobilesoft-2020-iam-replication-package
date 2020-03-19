package com.startapp.android.publish.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.util.Pair;
import com.startapp.a.a.b.a;
import com.startapp.android.publish.common.commonUtils.j;
import com.startapp.android.publish.common.commonUtils.r;
import com.startapp.android.publish.common.metaData.MetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class g
{
  private static final Object a = new Object();
  private static Pair<String, String> b;
  private static Pair<String, String> c;
  private static List<PackageInfo> d;
  private static List<PackageInfo> e;
  private static String f = "token";
  private static boolean g;
  private static boolean h;
  
  public static Pair<String, String> a()
  {
    return b;
  }
  
  private static List<String> a(Context paramContext, List<PackageInfo> paramList)
  {
    j.a("SimpleToken", 3, "getPackagesNames entered");
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
    paramList = new ArrayList(paramList);
    b(paramList);
    return paramList.subList(0, 100);
  }
  
  public static void a(Context paramContext)
  {
    b(paramContext);
    g = true;
    h = true;
    f = "token";
  }
  
  public static void a(Context paramContext, Pair<String, String> paramPair)
  {
    j.a("SimpleToken", 3, "errorSendingToken entered");
    if (((String)paramPair.first).equals("token"))
    {
      f = "token";
      g = true;
      return;
    }
    f = "token2";
    h = true;
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    return ((paramPackageInfo.applicationInfo.flags & 0x1) != 0) || ((paramPackageInfo.applicationInfo.flags & 0x80) != 0);
  }
  
  public static Pair<String, String> b()
  {
    return c;
  }
  
  public static void b(Context paramContext)
  {
    j.a("SimpleToken", 3, "initSimpleToken entered");
    i(paramContext);
    b = new Pair("token", new a().a(a(paramContext, d)));
    c = new Pair("token2", new a().a(a(paramContext, e)));
    j.a("SimpleToken", 3, "simpleToken : [" + b + "]");
    j.a("SimpleToken", 3, "simpleToken2 : [" + c + "]");
  }
  
  private static void b(List<PackageInfo> paramList)
  {
    if (Build.VERSION.SDK_INT >= 9) {
      Collections.sort(paramList, new Comparator()
      {
        public int a(PackageInfo paramAnonymousPackageInfo1, PackageInfo paramAnonymousPackageInfo2)
        {
          long l1 = paramAnonymousPackageInfo1.firstInstallTime;
          long l2 = paramAnonymousPackageInfo2.firstInstallTime;
          if (l1 > l2) {
            return -1;
          }
          if (l1 == l2) {
            return 0;
          }
          return 1;
        }
      });
    }
  }
  
  public static Pair<String, String> c(Context paramContext)
  {
    j.a("SimpleToken", 3, "getSimpleToken entered");
    Pair localPair = new Pair("token", "");
    try
    {
      if ((r.a()) || (!MetaData.getInstance().getSimpleTokenConfig().a(paramContext)))
      {
        boolean bool1 = g(paramContext);
        boolean bool2 = h(paramContext);
        if ((!bool1) && (!bool2)) {
          return localPair;
        }
        if (f.equals("token"))
        {
          if (bool1) {
            return e(paramContext);
          }
          return f(paramContext);
        }
        if (bool2) {
          return f(paramContext);
        }
        paramContext = e(paramContext);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      j.a("SimpleToken", 6, "failed to get simpleToken ", paramContext);
    }
    return localPair;
  }
  
  public static int d(Context paramContext)
  {
    j.a("SimpleToken", 3, "getPackageList entered");
    int j = 0;
    int i = 0;
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
        j = i;
        int k = i;
        if (paramContext.hasNext())
        {
          j = i;
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          j = i;
          if (a(localPackageInfo))
          {
            j = i;
            boolean bool = localPackageInfo.packageName.equals(Constants.j);
            if (!bool) {}
          }
          else
          {
            i += 1;
          }
        }
        else
        {
          return k;
        }
      }
      catch (Exception paramContext)
      {
        j.a("SimpleToken", 6, "Could not complete getPackagesList", paramContext);
        k = j;
      }
    }
  }
  
  private static Pair<String, String> e(Context paramContext)
  {
    f.b(paramContext, "shared_prefs_simple_token", (String)b.second);
    f = "token2";
    g = false;
    return new Pair("token", b.second);
  }
  
  private static Pair<String, String> f(Context paramContext)
  {
    f.b(paramContext, "shared_prefs_simple_token2", (String)c.second);
    f = "token";
    h = false;
    return new Pair("token2", c.second);
  }
  
  private static boolean g(Context paramContext)
  {
    if ((r.a()) || (MetaData.getInstance().getSimpleTokenConfig().b(paramContext)))
    {
      if ((MetaData.getInstance().isAlwaysSendToken()) || (g)) {}
      while (!new Pair("token", f.a(paramContext, "shared_prefs_simple_token", "")).equals(b)) {
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
      if ((MetaData.getInstance().isAlwaysSendToken()) || (h)) {}
      while (!new Pair("token2", f.a(paramContext, "shared_prefs_simple_token2", "")).equals(c)) {
        return true;
      }
      return false;
    }
    return false;
  }
  
  private static void i(Context paramContext)
  {
    j.a("SimpleToken", 3, "getPackages entered");
    for (;;)
    {
      synchronized (a)
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        Set localSet1 = MetaData.getInstance().getInstallersList();
        Set localSet2 = MetaData.getInstance().getPreInstalledPackages();
        new ArrayList();
        d = new CopyOnWriteArrayList();
        e = new CopyOnWriteArrayList();
        PackageInfo localPackageInfo;
        try
        {
          localObject1 = localPackageManager.getInstalledPackages(8192);
          paramContext = null;
          Iterator localIterator = ((List)localObject1).iterator();
          if (!localIterator.hasNext()) {
            continue;
          }
          localPackageInfo = (PackageInfo)localIterator.next();
          if (!a(localPackageInfo))
          {
            d.add(localPackageInfo);
            localObject1 = localPackageManager.getInstallerPackageName(localPackageInfo.packageName);
            if ((localSet1 == null) || (!localSet1.contains(localObject1))) {
              break label265;
            }
            e.add(localPackageInfo);
          }
        }
        catch (RuntimeException paramContext)
        {
          j.a("SimpleToken", 6, "Could not complete getInstalledPackages", paramContext);
          return;
        }
        if (localSet2.contains(localPackageInfo.packageName))
        {
          d.add(localPackageInfo);
          localObject1 = paramContext;
          break label267;
        }
        localObject1 = localPackageInfo;
        if (localPackageInfo.packageName.equals(Constants.j)) {
          break label267;
        }
        localObject1 = paramContext;
        break label267;
        d = a(d);
        e = a(e);
        if (paramContext != null) {
          d.add(0, paramContext);
        }
        return;
      }
      label265:
      Object localObject1 = paramContext;
      label267:
      paramContext = (Context)localObject1;
    }
  }
}
