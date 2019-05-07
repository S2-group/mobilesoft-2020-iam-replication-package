package net.daum.adam.common.b;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import net.daum.adam.common.report.a;

public class g
{
  public g() {}
  
  public static String a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128)).toString();
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      a.a().a(paramContext);
    }
    return "unknown";
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      paramContext = String.format("%s", new Object[] { ((Application)paramContext.getApplicationContext()).getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName });
      return paramContext;
    }
    catch (Exception paramContext)
    {
      a.a().a(paramContext);
    }
    return "unknown";
  }
  
  public static HashSet<String> c(Context paramContext)
  {
    localHashSet = new HashSet(0);
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
      while (paramContext.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        if ((localApplicationInfo.flags & 0x1) == 0) {
          localHashSet.add(localApplicationInfo.packageName);
        }
      }
      return localHashSet;
    }
    catch (Exception paramContext)
    {
      a.a().a(paramContext);
    }
  }
}
