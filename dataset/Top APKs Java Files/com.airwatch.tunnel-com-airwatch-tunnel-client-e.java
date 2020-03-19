package com.airwatch.tunnel.client;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.airwatch.tunnel.model.UidInfo;
import com.airwatch.tunnel.util.GeneralUtil;
import com.airwatch.tunnel.util.b;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class e
  implements c
{
  private HashMap<Integer, UidInfo> a = new HashMap();
  
  public e() {}
  
  private ApplicationInfo a(String paramString, List<ApplicationInfo> paramList)
  {
    return a(paramString, paramList, 0, paramList.size() - 1);
  }
  
  private ApplicationInfo a(String paramString, List<ApplicationInfo> paramList, int paramInt1, int paramInt2)
  {
    try
    {
      int i = (paramInt1 + paramInt2) / 2;
      if (paramInt1 > paramInt2)
      {
        b.d("PkgUidInfo: Package info not found for pkg: " + paramString);
        return null;
      }
      int j = paramString.compareTo(((ApplicationInfo)paramList.get(i)).packageName);
      if (j == 0) {
        paramString = (ApplicationInfo)paramList.get(i);
      } else if (j < 0) {
        paramString = a(paramString, paramList, paramInt1, i - 1);
      } else {
        paramString = a(paramString, paramList, i + 1, paramInt2);
      }
    }
    catch (Exception paramString)
    {
      b.b("PkgUidInfo: Got an exception while searching for the package info. Exc: " + paramString, paramString);
      paramString = null;
    }
    return paramString;
  }
  
  public int a(Context paramContext, List<String> paramList)
  {
    int k = 0;
    int i = 0;
    boolean bool;
    if (paramContext != null)
    {
      bool = true;
      GeneralUtil.a(bool);
    }
    for (;;)
    {
      try
      {
        this.a.clear();
        PackageManager localPackageManager = paramContext.getPackageManager();
        List localList = localPackageManager.getInstalledApplications(128);
        Collections.sort(localList, new a(null));
        int j = k;
        if (paramList != null)
        {
          j = k;
          if (!paramList.isEmpty())
          {
            Collections.sort(paramList);
            paramList = paramList.iterator();
            j = i;
            if (paramList.hasNext())
            {
              Object localObject = a((String)paramList.next(), localList);
              if (localObject == null) {
                break label206;
              }
              paramContext = localPackageManager.getPackagesForUid(((ApplicationInfo)localObject).uid);
              HashMap localHashMap = this.a;
              j = ((ApplicationInfo)localObject).uid;
              k = ((ApplicationInfo)localObject).uid;
              localObject = ((ApplicationInfo)localObject).packageName;
              if (paramContext.length <= 1) {
                break label215;
              }
              localHashMap.put(Integer.valueOf(j), new UidInfo(k, (String)localObject, paramContext));
              i += 1;
              break label206;
            }
          }
        }
        return j;
      }
      finally {}
      label206:
      continue;
      bool = false;
      break;
      label215:
      paramContext = null;
    }
  }
  
  public Object[] a()
  {
    try
    {
      Object[] arrayOfObject = this.a.values().toArray();
      return arrayOfObject;
    }
    finally {}
  }
  
  private class a
    implements Comparator<ApplicationInfo>
  {
    private a() {}
    
    public int a(ApplicationInfo paramApplicationInfo1, ApplicationInfo paramApplicationInfo2)
    {
      return paramApplicationInfo1.packageName.compareTo(paramApplicationInfo2.packageName);
    }
  }
}
