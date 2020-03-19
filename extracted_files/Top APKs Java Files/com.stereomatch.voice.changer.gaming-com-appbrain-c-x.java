package com.appbrain.c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.SystemClock;
import java.util.Collections;
import java.util.List;

public class x
{
  private static final String a = "x";
  private static List b;
  private static long c;
  
  public x() {}
  
  public static List a()
  {
    e.c();
    long l = SystemClock.elapsedRealtime();
    if ((b == null) || (c < l - 60000L)) {
      try
      {
        b = w.a().getPackageManager().getInstalledPackages(0);
        c = l;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }
    List localList = b;
    Object localObject = localList;
    if (localList == null) {
      localObject = Collections.emptyList();
    }
    return localObject;
  }
  
  public static boolean a(String paramString)
  {
    return c(paramString) != null;
  }
  
  public static void b(String paramString)
  {
    Object localObject = null;
    Context localContext = w.a(null);
    try
    {
      paramString = localContext.getPackageManager().getLaunchIntentForPackage(paramString);
      if (paramString == null) {}
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
        paramString = localObject;
      }
    }
  }
  
  private static PackageInfo c(String paramString)
  {
    try
    {
      paramString = w.a().getPackageManager().getPackageInfo(paramString, 0);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    return null;
  }
}
