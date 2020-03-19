package com.dolphin.browser.extensions;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class h
  extends com.dolphin.browser.util.h
{
  h(g paramG, Context paramContext) {}
  
  protected Void a(Void... paramVarArgs)
  {
    g.a(this.b).clear();
    paramVarArgs = al.a();
    ThemeManager.a();
    paramVarArgs.m();
    Object localObject = this.a.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getInstalledPackages(4160).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        g.a(this.b, this.a, localPackageInfo);
      }
      return null;
    }
    catch (Exception localException)
    {
      paramVarArgs.k();
      g.a(this.b, false);
      paramVarArgs.n();
    }
  }
}
