package com.inmobile;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.a;
import android.content.pm.a.a;
import android.content.pm.b;
import android.os.Build.VERSION;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class f
{
  private boolean s;
  
  f() {}
  
  private void a(boolean paramBoolean)
  {
    this.s = paramBoolean;
  }
  
  private boolean t()
  {
    return this.s;
  }
  
  public List<Map<String, Object>> m(Application paramApplication)
  {
    localArrayList = new ArrayList();
    for (;;)
    {
      try
      {
        localList = paramApplication.getPackageManager().getInstalledPackages(0);
        localPackageManager = paramApplication.getPackageManager();
        i = 0;
        int j = localList.size();
        if (i >= j) {
          break;
        }
      }
      catch (Exception paramApplication)
      {
        List localList;
        PackageManager localPackageManager;
        int i;
        PackageInfo localPackageInfo;
        final HashMap localHashMap;
        String str;
        return localArrayList;
      }
      try
      {
        localPackageInfo = (PackageInfo)localList.get(i);
        localHashMap = new HashMap();
        str = localPackageInfo.packageName;
        if (str.equals("com.android.keyguard")) {
          paramApplication = "Keyguard";
        } else {
          paramApplication = localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo).toString();
        }
        localHashMap.put("name", paramApplication);
        localHashMap.put("package_name", str);
        if (Build.VERSION.SDK_INT <= 25)
        {
          paramApplication = localPackageManager.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, a.class });
          a(false);
          paramApplication.invoke(localPackageManager, new Object[] { localPackageInfo.packageName, new a.a()
          {
            public void onGetStatsCompleted(b paramAnonymousB, boolean paramAnonymousBoolean)
            {
              f localF = f.this;
              if (paramAnonymousBoolean) {}
              try
              {
                localHashMap.put("code_size", Long.toString(paramAnonymousB.b));
                localHashMap.put("data_size", Long.toString(paramAnonymousB.c));
                localHashMap.put("cache_size", Long.toString(paramAnonymousB.d));
                f.a(f.this, true);
                f.this.notify();
                return;
              }
              finally
              {
                for (;;) {}
              }
              throw paramAnonymousB;
            }
          } });
          try
          {
            if (!t())
            {
              wait();
              continue;
            }
          }
          finally {}
        }
        localArrayList.add(localHashMap);
      }
      catch (Exception paramApplication)
      {
        continue;
      }
      i += 1;
    }
    return localArrayList;
  }
}
