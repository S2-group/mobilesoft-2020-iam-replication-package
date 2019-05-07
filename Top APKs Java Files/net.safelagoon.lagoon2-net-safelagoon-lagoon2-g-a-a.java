package net.safelagoon.lagoon2.g.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.text.TextUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import net.safelagoon.library.api.locker.models.Application;
import net.safelagoon.library.api.locker.models.ApplicationOverride;
import net.safelagoon.library.api.locker.models.Category;
import net.safelagoon.library.api.locker.models.ProfileCallLimit;
import net.safelagoon.library.api.locker.models.ProfileCallLimitNumber;
import net.safelagoon.library.api.locker.models.ProfileSchedule;
import net.safelagoon.library.api.locker.models.ProfileTimeLimit;

public final class a
{
  public static String a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      paramContext = paramContext.getPackageInfo(paramString, 0).applicationInfo.loadLabel(paramContext).toString();
      return paramContext;
    }
    catch (Resources.NotFoundException paramContext)
    {
      for (;;) {}
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return "";
    return "";
  }
  
  public static String a(PackageInfo paramPackageInfo, PackageManager paramPackageManager)
  {
    try
    {
      paramPackageInfo = paramPackageInfo.applicationInfo.loadLabel(paramPackageManager).toString();
      return paramPackageInfo;
    }
    catch (Resources.NotFoundException paramPackageInfo)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static String a(String paramString, PackageManager paramPackageManager)
  {
    if ((paramPackageManager != null) && (!TextUtils.isEmpty(paramString))) {
      return paramPackageManager.getInstallerPackageName(paramString);
    }
    return null;
  }
  
  public static List<Application> a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      net.safelagoon.library.d.b.c.a("ApplicationsHelper", "Try to get applications list");
      paramContext = paramContext.getPackageManager();
      int i = 0;
      List localList = paramContext.getInstalledPackages(0);
      while (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        if ((!TextUtils.isEmpty(localPackageInfo.packageName)) && (!TextUtils.equals(localPackageInfo.packageName, "net.safelagoon.lagoon2")))
        {
          Application localApplication = new Application();
          localApplication.d = "ANDROID";
          localApplication.c = a(localPackageInfo, paramContext);
          localApplication.b = localPackageInfo.packageName;
          localApplication.g = localPackageInfo.versionCode;
          localApplication.f = localPackageInfo.versionName;
          localApplication.j = a(localPackageInfo.packageName, paramContext);
          if ((a(localPackageInfo)) && (!b(localPackageInfo.packageName, paramContext))) {
            localApplication.h = true;
          }
          if (!localApplication.h) {
            localArrayList.add(localApplication);
          }
        }
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public static void a(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      d();
      return;
    }
    Date localDate = net.safelagoon.lagoon2.b.a.p();
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).add(6, -1);
    localObject = ((Calendar)localObject).getTime();
    if ((localDate != null) && (localDate.before((Date)localObject))) {
      d();
    }
  }
  
  public static boolean a()
    throws SQLException
  {
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(Long.valueOf(0L));
    Application localApplication = new Application();
    localApplication.a = Long.valueOf(0L);
    localApplication.b = "device.screentime";
    localApplication.i = 2;
    localApplication.e = localArrayList;
    localApplication.h = false;
    return a(localApplication);
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x81) != 0;
  }
  
  public static boolean a(Long paramLong)
    throws SQLException
  {
    if (paramLong != null) {
      ((net.safelagoon.lagoon2.b.a.m)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.m.class)).b(paramLong);
    }
    return false;
  }
  
  public static boolean a(List<Application> paramList)
    throws SQLException
  {
    if (!net.safelagoon.library.d.b.b.a(paramList))
    {
      net.safelagoon.lagoon2.b.a.b localB = (net.safelagoon.lagoon2.b.a.b)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.b.class);
      ArrayList localArrayList = new ArrayList(paramList.size());
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Application localApplication = (Application)paramList.next();
        if (c.a(localApplication))
        {
          net.safelagoon.lagoon2.b.b.b localB1 = localB.a(localApplication.b);
          int i;
          int j;
          boolean bool;
          if (localB1 != null)
          {
            i = localB1.e();
            j = localB1.f();
            bool = localB1.g();
          }
          else
          {
            i = 0;
            j = 0;
            bool = false;
          }
          long l = ((Long)localApplication.e.get(0)).longValue();
          localArrayList.add(new net.safelagoon.lagoon2.b.b.b(localApplication.a, Long.valueOf(l), localApplication.b, localApplication.i, i, j, bool));
        }
      }
      localB.callBatchTasks(new -..Lambda.a.BA37VrYMDJLrZjLUy-t2t3PD1rA(localArrayList, localB));
      return true;
    }
    return false;
  }
  
  public static boolean a(Application paramApplication)
    throws SQLException
  {
    if (paramApplication != null)
    {
      net.safelagoon.lagoon2.b.a.b localB = (net.safelagoon.lagoon2.b.a.b)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.b.class);
      if (c.a(paramApplication))
      {
        net.safelagoon.lagoon2.b.b.b localB1 = localB.a(paramApplication.b);
        int i;
        int j;
        boolean bool;
        if (localB1 != null)
        {
          i = localB1.e();
          j = localB1.f();
          bool = localB1.g();
        }
        else
        {
          i = 0;
          j = 0;
          bool = false;
        }
        long l = ((Long)paramApplication.e.get(0)).longValue();
        localB1 = new net.safelagoon.lagoon2.b.b.b(paramApplication.a, Long.valueOf(l), paramApplication.b, paramApplication.i, i, j, bool);
        localB.b(paramApplication.b);
        localB.create(localB1);
        return true;
      }
    }
    return false;
  }
  
  public static boolean a(ApplicationOverride paramApplicationOverride)
    throws SQLException
  {
    if (paramApplicationOverride != null)
    {
      net.safelagoon.lagoon2.b.a.c localC = (net.safelagoon.lagoon2.b.a.c)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.c.class);
      localC.b(paramApplicationOverride.e);
      if ((!TextUtils.isEmpty(paramApplicationOverride.f)) && (paramApplicationOverride.d != null)) {
        localC.create(new net.safelagoon.lagoon2.b.b.c(paramApplicationOverride.a, paramApplicationOverride.e, paramApplicationOverride.b, paramApplicationOverride.c, paramApplicationOverride.d));
      }
      return true;
    }
    return false;
  }
  
  public static boolean a(ProfileCallLimit paramProfileCallLimit)
    throws SQLException
  {
    if (paramProfileCallLimit != null)
    {
      Object localObject = (net.safelagoon.lagoon2.b.a.d)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.d.class);
      ((net.safelagoon.lagoon2.b.a.d)localObject).b(paramProfileCallLimit.a);
      net.safelagoon.lagoon2.b.b.d localD = new net.safelagoon.lagoon2.b.b.d(paramProfileCallLimit.a, paramProfileCallLimit.b, paramProfileCallLimit.d);
      ((net.safelagoon.lagoon2.b.a.d)localObject).create(localD);
      if (!net.safelagoon.library.d.b.b.a(paramProfileCallLimit.c))
      {
        localObject = (net.safelagoon.lagoon2.b.a.e)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.e.class);
        paramProfileCallLimit = paramProfileCallLimit.c.iterator();
        while (paramProfileCallLimit.hasNext()) {
          ((net.safelagoon.lagoon2.b.a.e)localObject).create(new net.safelagoon.lagoon2.b.b.e(localD, ((ProfileCallLimitNumber)paramProfileCallLimit.next()).c));
        }
      }
      return true;
    }
    return false;
  }
  
  public static boolean a(ProfileSchedule paramProfileSchedule)
    throws SQLException
  {
    if (paramProfileSchedule != null)
    {
      Object localObject = (net.safelagoon.lagoon2.b.a.i)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.i.class);
      ((net.safelagoon.lagoon2.b.a.i)localObject).b(paramProfileSchedule.a);
      net.safelagoon.lagoon2.b.b.i localI = new net.safelagoon.lagoon2.b.b.i(paramProfileSchedule.a, paramProfileSchedule.b, paramProfileSchedule.c, paramProfileSchedule.d, paramProfileSchedule.e, paramProfileSchedule.f, paramProfileSchedule.g, paramProfileSchedule.h, paramProfileSchedule.i, paramProfileSchedule.j, paramProfileSchedule.k, paramProfileSchedule.l, paramProfileSchedule.m, paramProfileSchedule.n, paramProfileSchedule.o);
      ((net.safelagoon.lagoon2.b.a.i)localObject).create(localI);
      if (!net.safelagoon.library.d.b.b.a(paramProfileSchedule.p))
      {
        localObject = (net.safelagoon.lagoon2.b.a.g)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.g.class);
        Iterator localIterator = paramProfileSchedule.p.iterator();
        while (localIterator.hasNext()) {
          ((net.safelagoon.lagoon2.b.a.g)localObject).create(new net.safelagoon.lagoon2.b.b.g(localI, (Long)localIterator.next()));
        }
      }
      if (!net.safelagoon.library.d.b.b.a(paramProfileSchedule.q))
      {
        localObject = (net.safelagoon.lagoon2.b.a.h)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.h.class);
        paramProfileSchedule = paramProfileSchedule.q.iterator();
        while (paramProfileSchedule.hasNext()) {
          ((net.safelagoon.lagoon2.b.a.h)localObject).create(new net.safelagoon.lagoon2.b.b.h(localI, (Long)paramProfileSchedule.next()));
        }
      }
      return true;
    }
    return false;
  }
  
  public static boolean a(ProfileTimeLimit paramProfileTimeLimit)
    throws SQLException
  {
    if (paramProfileTimeLimit != null)
    {
      Object localObject = (net.safelagoon.lagoon2.b.a.m)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.m.class);
      ((net.safelagoon.lagoon2.b.a.m)localObject).b(paramProfileTimeLimit.a);
      net.safelagoon.lagoon2.b.b.m localM = new net.safelagoon.lagoon2.b.b.m(paramProfileTimeLimit.a, paramProfileTimeLimit.c, paramProfileTimeLimit.d, paramProfileTimeLimit.f, paramProfileTimeLimit.g, paramProfileTimeLimit.h, paramProfileTimeLimit.i, paramProfileTimeLimit.j, paramProfileTimeLimit.k, paramProfileTimeLimit.l, paramProfileTimeLimit.m);
      ((net.safelagoon.lagoon2.b.a.m)localObject).create(localM);
      if (!net.safelagoon.library.d.b.b.a(paramProfileTimeLimit.n))
      {
        localObject = (net.safelagoon.lagoon2.b.a.k)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.k.class);
        Iterator localIterator = paramProfileTimeLimit.n.iterator();
        while (localIterator.hasNext()) {
          ((net.safelagoon.lagoon2.b.a.k)localObject).create(new net.safelagoon.lagoon2.b.b.k(localM, (Long)localIterator.next()));
        }
      }
      if (!net.safelagoon.library.d.b.b.a(paramProfileTimeLimit.o))
      {
        localObject = (net.safelagoon.lagoon2.b.a.l)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.l.class);
        paramProfileTimeLimit = paramProfileTimeLimit.o.iterator();
        while (paramProfileTimeLimit.hasNext()) {
          ((net.safelagoon.lagoon2.b.a.l)localObject).create(new net.safelagoon.lagoon2.b.b.l(localM, (Long)paramProfileTimeLimit.next()));
        }
      }
      return true;
    }
    return false;
  }
  
  public static void b()
  {
    net.safelagoon.lagoon2.b.a.b localB = (net.safelagoon.lagoon2.b.a.b)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.b.class);
    try
    {
      localB.d();
      return;
    }
    catch (SQLException localSQLException)
    {
      net.safelagoon.library.d.b.c.a("ApplicationsHelper", "SQL error", localSQLException);
    }
  }
  
  public static boolean b(Long paramLong)
    throws SQLException
  {
    if (paramLong != null) {
      ((net.safelagoon.lagoon2.b.a.i)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.i.class)).b(paramLong);
    }
    return false;
  }
  
  private static boolean b(String paramString, PackageManager paramPackageManager)
  {
    return paramPackageManager.getLaunchIntentForPackage(paramString) != null;
  }
  
  public static boolean b(List<ProfileTimeLimit> paramList)
    throws SQLException
  {
    if (!net.safelagoon.library.d.b.b.a(paramList))
    {
      net.safelagoon.lagoon2.b.a.m localM = (net.safelagoon.lagoon2.b.a.m)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.m.class);
      localM.callBatchTasks(new -..Lambda.a._RQ3Jgx3PFg5xDQaFVueoR-3k3A(paramList, localM, (net.safelagoon.lagoon2.b.a.k)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.k.class), (net.safelagoon.lagoon2.b.a.l)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.l.class)));
      return true;
    }
    return false;
  }
  
  public static void c()
  {
    net.safelagoon.lagoon2.b.a.b localB = (net.safelagoon.lagoon2.b.a.b)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.b.class);
    try
    {
      localB.e();
      return;
    }
    catch (SQLException localSQLException)
    {
      net.safelagoon.library.d.b.c.a("ApplicationsHelper", "SQL error", localSQLException);
    }
  }
  
  public static boolean c(Long paramLong)
    throws SQLException
  {
    if (paramLong != null) {
      ((net.safelagoon.lagoon2.b.a.d)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.d.class)).b(paramLong);
    }
    return false;
  }
  
  public static boolean c(List<ProfileSchedule> paramList)
    throws SQLException
  {
    if (!net.safelagoon.library.d.b.b.a(paramList))
    {
      net.safelagoon.lagoon2.b.a.i localI = (net.safelagoon.lagoon2.b.a.i)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.i.class);
      localI.callBatchTasks(new -..Lambda.a.JtDrvQ1boTSGtFFZ7erL83b-g30(paramList, localI, (net.safelagoon.lagoon2.b.a.g)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.g.class), (net.safelagoon.lagoon2.b.a.h)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.h.class)));
      return true;
    }
    return false;
  }
  
  private static void d()
  {
    net.safelagoon.lagoon2.b.a.a(new Date());
    net.safelagoon.lagoon2.b.a.b localB = (net.safelagoon.lagoon2.b.a.b)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.b.class);
    try
    {
      localB.c();
      return;
    }
    catch (SQLException localSQLException)
    {
      net.safelagoon.library.d.b.c.a("ApplicationsHelper", "SQL error", localSQLException);
    }
  }
  
  public static boolean d(List<ProfileCallLimit> paramList)
    throws SQLException
  {
    if (!net.safelagoon.library.d.b.b.a(paramList))
    {
      net.safelagoon.lagoon2.b.a.d localD = (net.safelagoon.lagoon2.b.a.d)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.d.class);
      localD.callBatchTasks(new -..Lambda.a.tzDuJB2gUL4D9W0uWN4mOOE1sA4(paramList, localD, (net.safelagoon.lagoon2.b.a.e)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.e.class)));
      return true;
    }
    return false;
  }
  
  public static boolean e(List<Category> paramList)
    throws SQLException
  {
    if (!net.safelagoon.library.d.b.b.a(paramList))
    {
      net.safelagoon.lagoon2.b.a.a localA = (net.safelagoon.lagoon2.b.a.a)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.a.class);
      localA.callBatchTasks(new -..Lambda.a.jB1yUdyiZWTO5nsZxL8xjsfjIBc(paramList, localA));
      return true;
    }
    return false;
  }
  
  public static boolean f(List<String> paramList)
    throws SQLException
  {
    if (!net.safelagoon.library.d.b.b.a(paramList))
    {
      net.safelagoon.lagoon2.b.a.f localF = (net.safelagoon.lagoon2.b.a.f)net.safelagoon.lagoon2.b.b.a().b(net.safelagoon.lagoon2.b.b.f.class);
      Date localDate = new Date();
      ArrayList localArrayList = new ArrayList(paramList.size());
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        String str = (String)paramList.next();
        if (!TextUtils.isEmpty(str)) {
          localArrayList.add(new net.safelagoon.lagoon2.b.b.f(str, localDate));
        }
      }
      localF.callBatchTasks(new -..Lambda.a.MCkxN6H2dVcopornd8opp06oPVs(localArrayList, localF));
      return true;
    }
    return false;
  }
}
