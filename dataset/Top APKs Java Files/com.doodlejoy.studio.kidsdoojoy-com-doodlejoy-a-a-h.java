package com.doodlejoy.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class h
{
  private static List a = new ArrayList();
  
  public static List a(Context paramContext)
  {
    a.clear();
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    if ((paramContext != null) && (paramContext.size() > 0))
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        if ((localApplicationInfo.packageName != null) && ((localApplicationInfo.flags & 0x1) == 0) && ((localApplicationInfo.flags & 0x80) == 0)) {
          a.add(localApplicationInfo.packageName);
        }
      }
    }
    return a;
  }
  
  public static boolean a(Context paramContext, int paramInt)
  {
    String str = new f().a(paramInt);
    return a(paramContext).contains(str);
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return a(paramContext).contains(paramString);
  }
}
