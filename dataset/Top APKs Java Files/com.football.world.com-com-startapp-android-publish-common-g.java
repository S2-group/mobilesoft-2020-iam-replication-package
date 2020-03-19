package com.startapp.android.publish.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.Pair;
import com.startapp.android.publish.b.a.b.a;
import com.startapp.android.publish.common.commonUtils.k;
import com.startapp.android.publish.common.commonUtils.s;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.h;
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
    c(paramContext);
    h = true;
    i = true;
    g = "token";
  }
  
  private static void a(PackageInfo paramPackageInfo, PackageManager paramPackageManager, Set<String> paramSet)
  {
    try
    {
      paramPackageManager = paramPackageManager.getInstallerPackageName(paramPackageInfo.packageName);
      if ((paramSet != null) && (paramSet.contains(paramPackageManager)))
      {
        e.add(paramPackageInfo);
        return;
      }
    }
    catch (Exception paramPackageInfo)
    {
      paramPackageManager = new StringBuilder();
      paramPackageManager.append("addToPackagesFromInstallers - can't add app to list ");
      paramPackageManager.append(paramPackageInfo.getMessage());
      k.a("SimpleToken", 6, paramPackageManager.toString());
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
    int j = paramPackageInfo.applicationInfo.flags;
    boolean bool = true;
    if ((j & 0x1) == 0)
    {
      if ((paramPackageInfo.applicationInfo.flags & 0x80) != 0) {
        return true;
      }
      bool = false;
    }
    return bool;
  }
  
  public static Pair<String, String> b()
  {
    if (b != null) {
      return b;
    }
    return new Pair("token", "");
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
    try
    {
      j(paramContext);
      b = new Pair("token", new a().a(a(d)));
      c = new Pair("token2", new a().a(a(e)));
      paramContext = new StringBuilder();
      paramContext.append("simpleToken : [");
      paramContext.append(b);
      paramContext.append("]");
      k.a("SimpleToken", 3, paramContext.toString());
      paramContext = new StringBuilder();
      paramContext.append("simpleToken2 : [");
      paramContext.append(c);
      paramContext.append("]");
      k.a("SimpleToken", 3, paramContext.toString());
      return;
    }
    catch (Exception paramContext)
    {
      k.a("SimpleToken", 6, "initSimpleToken failed", paramContext);
    }
  }
  
  public static Pair<String, String> c()
  {
    if (c != null) {
      return c;
    }
    return new Pair("token2", "");
  }
  
  public static void c(Context paramContext)
  {
    k.a("SimpleToken", 3, "initSimpleTokenAsync entered");
    new Thread(new Runnable()
    {
      public void run()
      {
        Process.setThreadPriority(10);
        g.b(this.a);
      }
    }).start();
  }
  
  private static void c(List<PackageInfo> paramList)
  {
    if (Build.VERSION.SDK_INT >= 9) {
      Collections.sort(paramList, new Comparator()
      {
        @SuppressLint({"InlinedApi"})
        public int a(PackageInfo paramAnonymousPackageInfo1, PackageInfo paramAnonymousPackageInfo2)
        {
          boolean bool = paramAnonymousPackageInfo1.firstInstallTime < paramAnonymousPackageInfo2.firstInstallTime;
          if (bool) {
            return -1;
          }
          if (!bool) {
            return 0;
          }
          return 1;
        }
      });
    }
  }
  
  public static Pair<String, String> d(Context paramContext)
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
            paramContext = f(paramContext);
          } else {
            paramContext = g(paramContext);
          }
        }
        else if (bool2) {
          paramContext = g(paramContext);
        } else {
          paramContext = f(paramContext);
        }
      }
    }
    catch (Exception paramContext)
    {
      k.a("SimpleToken", 6, "failed to get simpleToken ", paramContext);
    }
    return localPair;
    return paramContext;
  }
  
  public static int e(Context paramContext)
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
    boolean bool2 = s.a();
    boolean bool1 = true;
    if ((!bool2) && (!MetaData.getInstance().getSimpleTokenConfig().b(paramContext))) {
      return false;
    }
    if (!MetaData.getInstance().isAlwaysSendToken())
    {
      if (h) {
        return true;
      }
      bool1 = true ^ new Pair("token", f.a(paramContext, "shared_prefs_simple_token", "")).equals(b);
    }
    return bool1;
  }
  
  private static boolean i(Context paramContext)
  {
    boolean bool2 = MetaData.getInstance().isSimpleToken2();
    boolean bool1 = true;
    if (bool2)
    {
      if (!MetaData.getInstance().isAlwaysSendToken())
      {
        if (i) {
          return true;
        }
        return true ^ new Pair("token2", f.a(paramContext, "shared_prefs_simple_token2", "")).equals(c);
      }
    }
    else {
      bool1 = false;
    }
    return bool1;
  }
  
  private static void j(Context paramContext)
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
          paramContext = localPackageManager.getInstalledPackages(8192);
          if (Build.VERSION.SDK_INT >= 9)
          {
            l = Long.MAX_VALUE;
            f = l;
            Iterator localIterator = paramContext.iterator();
            paramContext = null;
            if (localIterator.hasNext())
            {
              PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
              if ((Build.VERSION.SDK_INT >= 9) && (f > localPackageInfo.firstInstallTime)) {
                f = localPackageInfo.firstInstallTime;
              }
              if (!a(localPackageInfo))
              {
                d.add(localPackageInfo);
                a(localPackageInfo, localPackageManager, localSet1);
                continue;
              }
              if (localSet2.contains(localPackageInfo.packageName))
              {
                d.add(localPackageInfo);
                continue;
              }
              if (!localPackageInfo.packageName.equals(Constants.k)) {
                continue;
              }
              paramContext = localPackageInfo;
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
