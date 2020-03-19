package com.google.android.apps.work.dpcsupport;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class j
{
  private static final String[] a = { "com.altice.android.myapps", "com.dti.att", "com.dti.cricket", "com.dti.millicom", "com.dti.blu", "com.dti.vizio", "com.dti.mts", "com.facebook.system", "com.sfr.android.sfrjeux", "com.LogiaGroup.LogiaDeck" };
  private final Context b;
  private final ComponentName c;
  private final DevicePolicyManager d;
  
  public j(Context paramContext, ComponentName paramComponentName)
  {
    this.b = paramContext;
    this.c = paramComponentName;
    this.d = ((DevicePolicyManager)paramContext.getSystemService("device_policy"));
  }
  
  public final void a(List<String> paramList)
  {
    HashSet localHashSet = new HashSet();
    localHashSet.addAll(Arrays.asList(a));
    localHashSet.addAll(paramList);
    paramList = localHashSet.iterator();
    Object localObject;
    while (paramList.hasNext())
    {
      localObject = (String)paramList.next();
      Log.i("dpcsupport", String.valueOf(localObject).length() + 32 + "Removing blacklisted installer " + (String)localObject + ".");
      this.d.setApplicationHidden(this.c, (String)localObject, true);
    }
    paramList = this.b.getPackageManager().getInstalledPackages(0).iterator();
    while (paramList.hasNext())
    {
      localObject = (PackageInfo)paramList.next();
      String str1 = this.b.getPackageManager().getInstallerPackageName(((PackageInfo)localObject).packageName);
      if (localHashSet.contains(str1))
      {
        String str2 = ((PackageInfo)localObject).packageName;
        Log.i("dpcsupport", String.valueOf(str2).length() + 24 + String.valueOf(str1).length() + "Removing " + str2 + " installed by " + str1 + ".");
        this.d.setApplicationHidden(this.c, ((PackageInfo)localObject).packageName, true);
      }
    }
  }
}
