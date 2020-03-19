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

public class DisableSystemInstallers
{
  private static final String[] BLACKLISTED_INSTALLERS = { "com.altice.android.myapps", "com.dti.att", "com.dti.cricket", "com.dti.millicom", "com.dti.blu", "com.dti.vizio", "com.dti.mts", "com.facebook.system", "com.sfr.android.sfrjeux", "com.LogiaGroup.LogiaDeck" };
  private final ComponentName admin;
  private final Context context;
  private final DevicePolicyManager devicePolicyManager;
  
  public DisableSystemInstallers(Context paramContext, ComponentName paramComponentName)
  {
    this.context = paramContext;
    this.admin = paramComponentName;
    this.devicePolicyManager = ((DevicePolicyManager)paramContext.getSystemService("device_policy"));
  }
  
  public void cleanUpBlacklistedInstallers(List<String> paramList)
  {
    HashSet localHashSet = new HashSet();
    localHashSet.addAll(Arrays.asList(BLACKLISTED_INSTALLERS));
    localHashSet.addAll(paramList);
    paramList = localHashSet.iterator();
    Object localObject1;
    Object localObject2;
    while (paramList.hasNext())
    {
      localObject1 = (String)paramList.next();
      localObject2 = new StringBuilder(String.valueOf(localObject1).length() + 32);
      ((StringBuilder)localObject2).append("Removing blacklisted installer ");
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(".");
      Log.i("dpcsupport", ((StringBuilder)localObject2).toString());
      this.devicePolicyManager.setApplicationHidden(this.admin, (String)localObject1, true);
    }
    paramList = this.context.getPackageManager().getInstalledPackages(0).iterator();
    while (paramList.hasNext())
    {
      localObject1 = (PackageInfo)paramList.next();
      localObject2 = this.context.getPackageManager().getInstallerPackageName(((PackageInfo)localObject1).packageName);
      if (localHashSet.contains(localObject2))
      {
        String str = ((PackageInfo)localObject1).packageName;
        StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 24 + String.valueOf(localObject2).length());
        localStringBuilder.append("Removing ");
        localStringBuilder.append(str);
        localStringBuilder.append(" installed by ");
        localStringBuilder.append((String)localObject2);
        localStringBuilder.append(".");
        Log.i("dpcsupport", localStringBuilder.toString());
        this.devicePolicyManager.setApplicationHidden(this.admin, ((PackageInfo)localObject1).packageName, true);
      }
    }
  }
}
