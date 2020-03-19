package com.a.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public class d
{
  private static String a = "com.uei.lg.quicksetsdk";
  private static String b;
  
  protected static String a(Context paramContext)
  {
    if (b != null) {
      return b;
    }
    if (a("com.uei.lg.quicksetsdk", paramContext)) {
      b = a;
    } else {
      b = "com.uei.lg.quicksetsdk";
    }
    return b;
  }
  
  private static boolean a(String paramString, Context paramContext)
  {
    paramString = paramContext.getPackageManager();
    if (paramString != null) {}
    try
    {
      paramString = paramString.getInstalledPackages(4);
      if (paramString == null) {
        break label72;
      }
      paramString = paramString.iterator();
      do
      {
        if (!paramString.hasNext()) {
          break;
        }
        paramContext = (PackageInfo)paramString.next();
      } while (!paramContext.packageName.startsWith("com.uei.lg.quicksetsdk"));
      a = paramContext.packageName;
    }
    catch (Exception paramString)
    {
      label72:
      for (;;) {}
    }
    return false;
    return true;
  }
}
