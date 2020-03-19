package com.oneaudience.sdk.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.oneaudience.sdk.c.c;
import com.oneaudience.sdk.model.InstalledPackage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class n
  extends a
{
  private final String f = n.class.getSimpleName();
  
  protected n(Context paramContext, String paramString, boolean paramBoolean1, boolean paramBoolean2, long paramLong)
  {
    super(paramContext, paramString, paramBoolean1, paramBoolean2, paramLong, "installed_apps", "disableInstallAppsCollector", true, true);
  }
  
  private ArrayList<InstalledPackage> i()
  {
    Object localObject = this.c.getPackageManager().getInstalledPackages(8192);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo != null) && (!localPackageInfo.packageName.equalsIgnoreCase("com.android.keyguard"))) {
        localArrayList.add(new InstalledPackage(localPackageInfo.packageName, localPackageInfo.firstInstallTime, localPackageInfo.lastUpdateTime));
      }
    }
    return localArrayList;
  }
  
  public String a()
  {
    try
    {
      String str = a(i());
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    this.c.getApplicationContext();
    c.e(this.f, "error collecting installed apps");
    return null;
  }
  
  public String[] b()
  {
    return new String[0];
  }
}
