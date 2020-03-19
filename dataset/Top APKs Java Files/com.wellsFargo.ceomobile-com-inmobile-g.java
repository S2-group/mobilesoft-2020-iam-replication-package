package com.inmobile;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class g
{
  private Date v;
  private boolean w = false;
  
  g() {}
  
  private void a(Date paramDate)
  {
    this.v = paramDate;
  }
  
  private void b(boolean paramBoolean)
  {
    this.w = paramBoolean;
  }
  
  private Date u()
  {
    return this.v;
  }
  
  private boolean v()
  {
    return this.w;
  }
  
  public List<Map<String, Object>> m(Application paramApplication)
  {
    localArrayList = new ArrayList();
    PackageManager localPackageManager = paramApplication.getPackageManager();
    int i = 0;
    for (;;)
    {
      try
      {
        localList = localPackageManager.getInstalledPackages(0);
        if (i >= localList.size()) {
          break;
        }
      }
      catch (Exception paramApplication)
      {
        List localList;
        PackageInfo localPackageInfo;
        String str;
        Date localDate;
        HashMap localHashMap;
        return localArrayList;
      }
      try
      {
        localPackageInfo = (PackageInfo)localList.get(i);
        str = localPackageInfo.packageName;
        if (str.equals("com.android.keyguard")) {
          paramApplication = "Keyguard";
        } else {
          paramApplication = localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo).toString();
        }
        localDate = new Date(new File(localPackageInfo.applicationInfo.sourceDir).lastModified());
        localHashMap = new HashMap();
        localHashMap.put("name", paramApplication);
        localHashMap.put("package_name", str);
        localHashMap.put("version_name", localPackageInfo.versionName);
        localHashMap.put("version_code", Integer.toString(localPackageInfo.versionCode));
        localHashMap.put("installed_at", localDate.toString());
        if ((!v()) || (localDate.after(u()))) {
          localArrayList.add(localHashMap);
        }
      }
      catch (Exception paramApplication)
      {
        continue;
      }
      i += 1;
    }
    a(new Date());
    if (!v()) {
      b(true);
    }
    return localArrayList;
  }
}
