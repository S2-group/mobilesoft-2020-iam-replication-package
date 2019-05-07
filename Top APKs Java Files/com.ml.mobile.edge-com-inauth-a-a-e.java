package com.inauth.a.a;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.a;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class e
{
  private boolean a;
  
  public e() {}
  
  private void a(boolean paramBoolean)
  {
    this.a = paramBoolean;
  }
  
  private boolean a()
  {
    return this.a;
  }
  
  public List<Map> a(Application paramApplication)
  {
    localArrayList = new ArrayList();
    try
    {
      List localList = paramApplication.getPackageManager().getInstalledPackages(0);
      PackageManager localPackageManager = paramApplication.getPackageManager();
      int i = 0;
      while (i < localList.size())
      {
        HashMap localHashMap = new HashMap();
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        if ((localPackageInfo.packageName != null) && (!localPackageInfo.packageName.equals("")))
        {
          if (localPackageInfo.packageName.equals("com.android.keyguard")) {}
          for (Object localObject = "com.android.keyguard";; localObject = localPackageInfo.applicationInfo.loadLabel(paramApplication.getApplicationContext().getPackageManager()).toString())
          {
            localHashMap.put("name", localObject);
            localHashMap.put("package_name", localPackageInfo.packageName);
            localObject = localPackageManager.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, a.class });
            a(false);
            ((Method)localObject).invoke(localPackageManager, new Object[] { localPackageInfo.packageName, new f(this, localHashMap) });
            for (;;)
            {
              try
              {
                if (a()) {
                  break label235;
                }
                wait();
              }
              finally {}
            }
          }
          label235:
          localArrayList.add(localHashMap);
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (Exception paramApplication) {}
  }
}
