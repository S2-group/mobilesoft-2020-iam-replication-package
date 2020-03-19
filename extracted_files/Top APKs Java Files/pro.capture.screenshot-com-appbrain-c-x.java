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
  private static List avY;
  private static long c;
  
  public x() {}
  
  public static void V(String paramString)
  {
    Object localObject = null;
    Context localContext = w.o(null);
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
  
  public static boolean Z(String paramString)
  {
    return ak(paramString) != null;
  }
  
  private static PackageInfo ak(String paramString)
  {
    try
    {
      paramString = w.qC().getPackageManager().getPackageInfo(paramString, 0);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static List sX()
  {
    e.c();
    long l = SystemClock.elapsedRealtime();
    if ((avY == null) || (c < l - 60000L)) {
      try
      {
        avY = w.qC().getPackageManager().getInstalledPackages(0);
        c = l;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }
    List localList = avY;
    Object localObject = localList;
    if (localList == null) {
      localObject = Collections.emptyList();
    }
    return localObject;
  }
}
