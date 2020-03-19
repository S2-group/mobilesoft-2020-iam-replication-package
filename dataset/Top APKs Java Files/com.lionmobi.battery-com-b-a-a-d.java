package com.b.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.b.a.b.c;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class d
{
  private static d a = null;
  private static ConcurrentHashMap<String, String> b = new ConcurrentHashMap();
  private ArrayList<PackageInfo> c = new ArrayList();
  
  private d() {}
  
  public static d getInstance()
  {
    if (a == null) {}
    try
    {
      if (a == null) {
        a = new d();
      }
      return a;
    }
    finally {}
  }
  
  public List<PackageInfo> getPackageInfoList(boolean paramBoolean)
  {
    int i = 1;
    c.getInstance();
    ??? = c.getContext();
    if (??? == null) {
      return (List)this.c.clone();
    }
    Object localObject2 = getPackageManager((Context)???);
    for (;;)
    {
      synchronized (a)
      {
        int j = this.c.size();
        if (j <= 0) {}
        try
        {
          this.c.clear();
          ArrayList localArrayList = this.c;
          if (!paramBoolean) {
            break label110;
          }
          localArrayList.addAll(((PackageManager)localObject2).getInstalledPackages(i));
        }
        catch (Exception localException2)
        {
          try
          {
            this.c.clear();
            this.c.addAll(localObject3.getInstalledPackages(1));
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
          }
          continue;
        }
        localObject2 = (List)this.c.clone();
        return localObject2;
      }
      label110:
      i = 0;
    }
  }
  
  public PackageManager getPackageManager(Context paramContext)
  {
    return paramContext.getPackageManager();
  }
}
