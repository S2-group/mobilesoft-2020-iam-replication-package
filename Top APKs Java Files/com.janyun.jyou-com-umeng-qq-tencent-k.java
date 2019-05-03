package com.umeng.qq.tencent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.Iterator;
import java.util.List;

public class k
{
  private b a;
  private n b;
  
  private k(String paramString)
  {
    this.b = new n(paramString);
    this.a = new b(this.b);
  }
  
  private int a(Activity paramActivity, String paramString1, j paramJ, String paramString2)
  {
    paramString2 = paramActivity.getApplicationContext().getPackageName();
    Iterator localIterator = paramActivity.getPackageManager().getInstalledApplications(128).iterator();
    while ((localIterator.hasNext()) && (!paramString2.equals(((ApplicationInfo)localIterator.next()).packageName))) {}
    h.f = false;
    return this.a.a(paramActivity, paramString1, paramJ, false);
  }
  
  public static k a(String paramString, Context paramContext)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.umeng.qq.tencent.AuthActivity"), 0);
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.umeng.qq.tencent.AssistActivity"), 0);
      return new k(paramString);
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  public int a(Activity paramActivity, String paramString, j paramJ)
  {
    return a(paramActivity, paramString, paramJ, "");
  }
  
  public n a()
  {
    return this.b;
  }
}
