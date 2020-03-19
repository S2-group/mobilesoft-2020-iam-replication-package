package com.inauth.a.a;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class g
{
  private Date a;
  private boolean b = false;
  
  public g() {}
  
  private Date a()
  {
    return this.a;
  }
  
  private void a(Date paramDate)
  {
    this.a = paramDate;
  }
  
  private void a(boolean paramBoolean)
  {
    this.b = paramBoolean;
  }
  
  private boolean b()
  {
    return this.b;
  }
  
  public List<Map> a(Application paramApplication)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = paramApplication.getApplicationContext().getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i < localList.size())
    {
      HashMap localHashMap = new HashMap();
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      String str;
      if (localPackageInfo.packageName.equals("com.android.keyguard"))
      {
        str = "com.android.keyguard";
        label73:
        Date localDate = new Date(new File(localPackageInfo.applicationInfo.sourceDir).lastModified());
        localHashMap.put("name", str);
        localHashMap.put("package_name", localPackageInfo.packageName);
        localHashMap.put("version_name", localPackageInfo.versionName);
        localHashMap.put("version_code", Integer.toString(localPackageInfo.versionCode));
        localHashMap.put("installed_at", localDate.toString());
        if (!b()) {
          break label234;
        }
        if (localDate.after(a())) {
          localArrayList.add(localHashMap);
        }
      }
      for (;;)
      {
        i += 1;
        break;
        str = localPackageInfo.applicationInfo.loadLabel(paramApplication.getApplicationContext().getPackageManager()).toString();
        break label73;
        label234:
        localArrayList.add(localHashMap);
      }
    }
    a(new Date());
    if (!b()) {
      a(true);
    }
    return localArrayList;
  }
}
