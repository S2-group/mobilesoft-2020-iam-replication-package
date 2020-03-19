package com.appbrain.c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.SystemClock;
import java.util.Collections;
import java.util.List;

public class n
{
  private static final String a = n.class.getSimpleName();
  private static List b;
  private static long c;
  
  public n() {}
  
  public static List a(Context paramContext, long paramLong)
  {
    ad.c();
    long l = SystemClock.elapsedRealtime();
    if ((b == null) || (c < l - paramLong)) {}
    try
    {
      b = paramContext.getPackageManager().getInstalledPackages(0);
      c = l;
      if (b == null) {
        return Collections.emptyList();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    return b;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return c(paramContext, paramString) != null;
  }
  
  public static void b(Context paramContext, String paramString)
  {
    Object localObject = null;
    try
    {
      paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
      if (paramString != null) {}
      try
      {
        paramString.addFlags(268435456);
        paramContext.startActivity(paramString);
        return;
      }
      catch (Exception paramContext) {}
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = localObject;
      }
    }
  }
  
  private static PackageInfo c(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
}
