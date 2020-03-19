package com.peel.h;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.peel.config.d;
import com.peel.util.bv;
import java.util.Iterator;
import java.util.List;

public class c
{
  private static final String a = "com.peel.h.c";
  
  public c() {}
  
  public static boolean a()
  {
    Iterator localIterator = d.a().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if ("com.smartremote.control.android".equalsIgnoreCase(((PackageInfo)localIterator.next()).packageName))
      {
        bv.b(a, "packageName is found to be installed.");
        return true;
      }
    }
    return false;
  }
}
