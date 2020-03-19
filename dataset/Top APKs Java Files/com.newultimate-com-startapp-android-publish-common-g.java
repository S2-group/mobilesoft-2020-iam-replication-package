package com.startapp.android.publish.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.util.Pair;
import com.startapp.a.a.b.a;
import com.startapp.android.publish.common.commonUtils.k;
import com.startapp.android.publish.common.commonUtils.s;
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
  private static long f = 0L;
  private static String g = "token";
  private static boolean h;
  private static boolean i;
  
  public static long a()
  {
    return f;
  }
  
  private static List<String> a(List<PackageInfo> paramList)
  {
    k.a("SimpleToken", 3, "getPackagesNames entered");
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.add(((PackageInfo)paramList.next()).packageName);
    }
    return localArrayList;
  }
  
  public static void a(Context paramContext)
  {
    b(paramContext);
    h = true;
    i = true;
    g = "token";
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
    if ((paramPackageInfo.applicationInfo.flags & 0x1) == 0) {
      return (paramPackageInfo.applicationInfo.flags & 0x80) != 0;
    }
    return true;
  }
  
  public static Pair<String, String> b()
  {
    return b;
  }
  
  private static List<PackageInfo> b(List<PackageInfo> paramList)
  {
    if (paramList.size() <= 100) {
      return paramList;
    }
    paramList = new ArrayList(paramList);
    c(paramList);
    return paramList.subList(0, 100);
  }
  
  public static void b(Context paramContext)
  {
    k.a("SimpleToken", 3, "initSimpleToken entered");
    i(paramContext);
    b = new Pair("token", new a().a(a(d)));
    c = new Pair("token2", new a().a(a(e)));
    paramContext = new StringBuilder("simpleToken : [");
    paramContext.append(b);
    paramContext.append("]");
    k.a("SimpleToken", 3, paramContext.toString());
    paramContext = new StringBuilder("simpleToken2 : [");
    paramContext.append(c);
    paramContext.append("]");
    k.a("SimpleToken", 3, paramContext.toString());
  }
  
  public static Pair<String, String> c()
  {
    return c;
  }
  
  public static Pair<String, String> c(Context paramContext)
  {
    k.a("SimpleToken", 3, "getSimpleToken entered");
    Pair localPair2 = new Pair("token", "");
    Pair localPair1;
    try
    {
      if (!s.a())
      {
        localPair1 = localPair2;
        if (MetaData.getInstance().getSimpleTokenConfig().a(paramContext)) {}
      }
      else
      {
        boolean bool1 = g(paramContext);
        boolean bool2 = h(paramContext);
        if ((!bool1) && (!bool2)) {
          return localPair2;
        }
        if (g.equals("token"))
        {
          if (bool1) {
            return e(paramContext);
          }
          return f(paramContext);
        }
        if (bool2) {
          return f(paramContext);
        }
        localPair1 = e(paramContext);
      }
    }
    catch (Exception paramContext)
    {
      k.a("SimpleToken", 6, "failed to get simpleToken ", paramContext);
      localPair1 = localPair2;
    }
    return localPair1;
  }
  
  private static void c(List<PackageInfo> paramList)
  {
    if (Build.VERSION.SDK_INT >= 9) {
      Collections.sort(paramList, new Comparator()
      {
        @SuppressLint({"InlinedApi"})
        public final int a(PackageInfo paramAnonymousPackageInfo1, PackageInfo paramAnonymousPackageInfo2)
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
  
  public static int d(Context paramContext)
  {
    k.a("SimpleToken", 3, "getPackageList entered");
    int k = 0;
    int j = 0;
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
      int m;
      for (;;)
      {
        k = j;
        m = j;
        if (!paramContext.hasNext()) {
          break;
        }
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
      return m;
    }
    catch (Exception paramContext)
    {
      k.a("SimpleToken", 6, "Could not complete getPackagesList", paramContext);
      m = k;
    }
  }
  
  private static Pair<String, String> e(Context paramContext)
  {
    f.b(paramContext, "shared_prefs_simple_token", (String)b.second);
    g = "token2";
    h = false;
    return new Pair("token", b.second);
  }
  
  private static Pair<String, String> f(Context paramContext)
  {
    f.b(paramContext, "shared_prefs_simple_token2", (String)c.second);
    g = "token";
    i = false;
    return new Pair("token2", c.second);
  }
  
  private static boolean g(Context paramContext)
  {
    boolean bool1 = s.a();
    boolean bool2 = false;
    if (!bool1)
    {
      bool1 = bool2;
      if (!MetaData.getInstance().getSimpleTokenConfig().b(paramContext)) {}
    }
    else if ((!MetaData.getInstance().isAlwaysSendToken()) && (!h))
    {
      bool1 = bool2;
      if (new Pair("token", f.a(paramContext, "shared_prefs_simple_token", "")).equals(b)) {}
    }
    else
    {
      bool1 = true;
    }
    return bool1;
  }
  
  private static boolean h(Context paramContext)
  {
    boolean bool3 = MetaData.getInstance().isSimpleToken2();
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (bool3) {
      if ((!MetaData.getInstance().isAlwaysSendToken()) && (!i))
      {
        bool1 = bool2;
        if (new Pair("token2", f.a(paramContext, "shared_prefs_simple_token2", "")).equals(c)) {}
      }
      else
      {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private static void i(Context paramContext)
  {
    k.a("SimpleToken", 3, "getPackages entered");
    for (;;)
    {
      synchronized (a)
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        Set localSet1 = MetaData.getInstance().getInstallersList();
        Set localSet2 = MetaData.getInstance().getPreInstalledPackages();
        d = new CopyOnWriteArrayList();
        e = new CopyOnWriteArrayList();
        try
        {
          Object localObject1 = localPackageManager.getInstalledPackages(8192);
          if (Build.VERSION.SDK_INT >= 9)
          {
            l = Long.MAX_VALUE;
            f = l;
            paramContext = null;
            Iterator localIterator = ((List)localObject1).iterator();
            if (localIterator.hasNext())
            {
              localObject1 = (PackageInfo)localIterator.next();
              if ((Build.VERSION.SDK_INT >= 9) && (f > ((PackageInfo)localObject1).firstInstallTime)) {
                f = ((PackageInfo)localObject1).firstInstallTime;
              }
              if (!a((PackageInfo)localObject1))
              {
                d.add(localObject1);
                String str = localPackageManager.getInstallerPackageName(((PackageInfo)localObject1).packageName);
                if ((localSet1 == null) || (!localSet1.contains(str))) {
                  continue;
                }
                e.add(localObject1);
                continue;
              }
              if (localSet2.contains(((PackageInfo)localObject1).packageName))
              {
                d.add(localObject1);
                continue;
              }
              if (!((PackageInfo)localObject1).packageName.equals(Constants.k)) {
                continue;
              }
              paramContext = (Context)localObject1;
              continue;
            }
            d = b(d);
            e = b(e);
            if (paramContext != null) {
              d.add(0, paramContext);
            }
            return;
          }
        }
        catch (RuntimeException paramContext)
        {
          k.a("SimpleToken", 6, "Could not complete getInstalledPackages", paramContext);
          return;
        }
      }
      long l = 0L;
    }
  }
}
