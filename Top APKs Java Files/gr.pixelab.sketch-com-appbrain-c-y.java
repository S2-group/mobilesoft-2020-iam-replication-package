package com.appbrain.c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.SystemClock;
import java.util.Collections;
import java.util.List;

public class y
{
  private static final String a = "y";
  private static List b;
  private static long c;
  
  public y() {}
  
  public static List a()
  {
    e.c();
    long l = SystemClock.elapsedRealtime();
    if ((b == null) || (c < l - 60000L)) {
      try
      {
        b = x.a().getPackageManager().getInstalledPackages(0);
        c = l;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }
    if (b == null) {
      return Collections.emptyList();
    }
    return b;
  }
  
  public static boolean a(String paramString)
  {
    return c(paramString) != null;
  }
  
  public static void b(String paramString)
  {
    Context localContext = x.a(null);
    try
    {
      paramString = localContext.getPackageManager().getLaunchIntentForPackage(paramString);
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        try
        {
          paramString.addFlags(268435456);
          localContext.startActivity(paramString);
          return;
        }
        catch (Exception paramString) {}
        paramString = paramString;
      }
    }
    paramString = null;
    if (paramString != null) {}
  }
  
  private static PackageInfo c(String paramString)
  {
    try
    {
      paramString = x.a().getPackageManager().getPackageInfo(paramString, 0);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    return null;
  }
}
