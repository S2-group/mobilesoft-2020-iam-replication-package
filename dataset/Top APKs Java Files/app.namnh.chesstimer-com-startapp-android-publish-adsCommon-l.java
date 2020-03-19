package com.startapp.android.publish.adsCommon;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.util.Pair;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.h;
import com.startapp.common.Constants;
import com.startapp.common.a.c;
import com.startapp.common.g.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class l
{
  private static final Object a = new Object();
  private static Pair<String, String> b;
  private static Pair<String, String> c;
  private static List<PackageInfo> d;
  private static List<PackageInfo> e;
  private static boolean f = false;
  private static long g = 0L;
  private static String h = "token";
  private static boolean i;
  private static boolean j;
  
  public static long a()
  {
    return g;
  }
  
  private static List<String> a(List<PackageInfo> paramList)
  {
    com.startapp.common.a.g.a("SimpleToken", 3, "getPackagesNames entered");
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
    i = true;
    j = true;
    h = "token";
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    paramContext.getApplicationContext().registerReceiver(new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        l.a(false);
        l.c(paramAnonymousContext);
      }
    }, localIntentFilter);
    MetaData.getInstance().addMetaDataListener(new com.startapp.android.publish.common.metaData.d()
    {
      public void a()
      {
        l.a(false);
        l.c(this.a);
      }
      
      public void b() {}
    });
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    com.startapp.common.a.g.a("SimpleToken", 3, "initSimpleToken entered");
    if ((!f) && (paramBoolean))
    {
      f = true;
      try
      {
        i(paramContext);
        b = new Pair("token", com.startapp.common.f.a(a(d)));
        c = new Pair("token2", com.startapp.common.f.a(a(e)));
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("simpleToken : [");
        localStringBuilder.append(b);
        localStringBuilder.append("]");
        com.startapp.common.a.g.a("SimpleToken", 3, localStringBuilder.toString());
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("simpleToken2 : [");
        localStringBuilder.append(c);
        localStringBuilder.append("]");
        com.startapp.common.a.g.a("SimpleToken", 3, localStringBuilder.toString());
        return;
      }
      catch (Exception localException)
      {
        com.startapp.common.a.g.a("SimpleToken", 6, "initSimpleToken failed", localException);
        com.startapp.android.publish.adsCommon.e.f.a(paramContext, com.startapp.android.publish.adsCommon.e.d.b, "initSimpleToken", localException.getMessage(), "");
      }
    }
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
      com.startapp.common.a.g.a("SimpleToken", 6, paramPackageManager.toString());
    }
  }
  
  public static void a(Pair<String, String> paramPair)
  {
    com.startapp.common.a.g.a("SimpleToken", 3, "errorSendingToken entered");
    if (((String)paramPair.first).equals("token"))
    {
      h = "token";
      i = true;
      return;
    }
    h = "token2";
    j = true;
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
  
  public static void b()
  {
    b = null;
    c = null;
    f = false;
  }
  
  public static void b(Context paramContext)
  {
    a(paramContext, MetaData.getInstance().getSimpleTokenConfig().b(paramContext));
  }
  
  public static Pair<String, String> c()
  {
    if (b != null) {
      return b;
    }
    return new Pair("token", "");
  }
  
  public static void c(Context paramContext)
  {
    com.startapp.common.a.g.a("SimpleToken", 3, "initSimpleTokenAsync entered");
    try
    {
      if ((!f) && (MetaData.getInstance().getSimpleTokenConfig().b(paramContext)))
      {
        com.startapp.common.g.a(g.a.b, new Runnable()
        {
          public void run()
          {
            l.b(this.a);
          }
        });
        return;
      }
    }
    catch (Exception localException)
    {
      com.startapp.common.a.g.a("SimpleToken", 6, "initSimpleTokenAsync failed", localException);
      com.startapp.android.publish.adsCommon.e.f.a(paramContext, com.startapp.android.publish.adsCommon.e.d.b, "initSimpleTokenAsync", localException.getMessage(), "");
    }
  }
  
  private static void c(List<PackageInfo> paramList)
  {
    if (Build.VERSION.SDK_INT >= 9) {
      Collections.sort(paramList, new Comparator()
      {
        @SuppressLint({"InlinedApi"})
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
  
  public static Pair<String, String> d()
  {
    if (c != null) {
      return c;
    }
    return new Pair("token2", "");
  }
  
  public static Pair<String, String> d(Context paramContext)
  {
    com.startapp.common.a.g.a("SimpleToken", 3, "getSimpleToken entered");
    Pair localPair = new Pair("token", "");
    try
    {
      if ((i.a()) || (!MetaData.getInstance().getSimpleTokenConfig().a(paramContext)))
      {
        boolean bool1 = g(paramContext);
        boolean bool2 = h(paramContext);
        if ((!bool1) && (!bool2)) {
          return localPair;
        }
        if (h.equals("token"))
        {
          if (bool1) {
            paramContext = e(paramContext);
          } else {
            paramContext = f(paramContext);
          }
        }
        else if (bool2) {
          paramContext = f(paramContext);
        } else {
          paramContext = e(paramContext);
        }
      }
    }
    catch (Exception paramContext)
    {
      com.startapp.common.a.g.a("SimpleToken", 6, "failed to get simpleToken ", paramContext);
    }
    return localPair;
    return paramContext;
  }
  
  private static Pair<String, String> e(Context paramContext)
  {
    k.b(paramContext, "shared_prefs_simple_token", (String)b.second);
    h = "token2";
    i = false;
    return new Pair("token", b.second);
  }
  
  private static Pair<String, String> f(Context paramContext)
  {
    k.b(paramContext, "shared_prefs_simple_token2", (String)c.second);
    h = "token";
    j = false;
    return new Pair("token2", c.second);
  }
  
  private static boolean g(Context paramContext)
  {
    boolean bool2 = i.a();
    boolean bool1 = true;
    if ((!bool2) && (!MetaData.getInstance().getSimpleTokenConfig().b(paramContext))) {
      return false;
    }
    if (!MetaData.getInstance().isAlwaysSendToken())
    {
      if (i) {
        return true;
      }
      bool1 = true ^ new Pair("token", k.a(paramContext, "shared_prefs_simple_token", "")).equals(b);
    }
    return bool1;
  }
  
  private static boolean h(Context paramContext)
  {
    boolean bool2 = MetaData.getInstance().isSimpleToken2();
    boolean bool1 = true;
    if (bool2)
    {
      if (!MetaData.getInstance().isAlwaysSendToken())
      {
        if (j) {
          return true;
        }
        return true ^ new Pair("token2", k.a(paramContext, "shared_prefs_simple_token2", "")).equals(c);
      }
    }
    else {
      bool1 = false;
    }
    return bool1;
  }
  
  private static void i(Context paramContext)
  {
    com.startapp.common.a.g.a("SimpleToken", 3, "getPackages entered");
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
          Object localObject1 = c.a(localPackageManager);
          if (Build.VERSION.SDK_INT >= 9)
          {
            l = Long.MAX_VALUE;
            g = l;
            paramContext = null;
            Iterator localIterator = ((List)localObject1).iterator();
            if (localIterator.hasNext())
            {
              localObject1 = (PackageInfo)localIterator.next();
              if ((Build.VERSION.SDK_INT >= 9) && (g > ((PackageInfo)localObject1).firstInstallTime)) {
                g = ((PackageInfo)localObject1).firstInstallTime;
              }
              if (!c.a((PackageInfo)localObject1))
              {
                d.add(localObject1);
                a((PackageInfo)localObject1, localPackageManager, localSet1);
                continue;
              }
              if (localSet2.contains(((PackageInfo)localObject1).packageName))
              {
                d.add(localObject1);
                continue;
              }
              if (!((PackageInfo)localObject1).packageName.equals(Constants.a)) {
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
        catch (Exception paramContext)
        {
          com.startapp.common.a.g.a("SimpleToken", 6, "Could not complete getInstalledPackages", paramContext);
          return;
        }
      }
      long l = 0L;
    }
  }
}
