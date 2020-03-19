package com.cmcm.orion.utils.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.cmcm.orion.adsdk.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class c
{
  private static c a = new c();
  private Context b = a.a();
  private PackageManager c = this.b.getPackageManager();
  private List<PackageInfo> d;
  private Object e = new Object();
  
  public c() {}
  
  public static c a()
  {
    try
    {
      c localC = a;
      return localC;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public final void a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    for (;;)
    {
      int i;
      synchronized (this.e)
      {
        if (this.d != null)
        {
          i = 0;
          if (i < this.d.size())
          {
            PackageInfo localPackageInfo = (PackageInfo)this.d.get(i);
            if ((localPackageInfo == null) || (!paramString.equals(localPackageInfo.packageName))) {
              break label91;
            }
            this.d.remove(i);
          }
        }
        return;
      }
      label91:
      i += 1;
    }
  }
  
  public final void a(String paramString, Context paramContext)
  {
    if (!TextUtils.isEmpty(paramString)) {
      if (paramContext == null) {
        return;
      }
    }
    for (;;)
    {
      int i;
      try
      {
        synchronized (this.e)
        {
          if (this.d != null)
          {
            paramContext = paramContext.getPackageManager();
            i = 0;
            paramContext = paramContext.getPackageInfo(paramString, 0);
            if (i < this.d.size())
            {
              PackageInfo localPackageInfo = (PackageInfo)this.d.get(i);
              if ((localPackageInfo == null) || (!paramString.equals(localPackageInfo.packageName))) {
                break label125;
              }
              this.d.remove(i);
            }
            this.d.add(paramContext);
          }
          return;
        }
        return;
      }
      catch (Throwable paramString)
      {
        return;
      }
      label125:
      i += 1;
    }
  }
  
  public final List<String> b()
  {
    try
    {
      Object localObject3 = c();
      synchronized (this.e)
      {
        ArrayList localArrayList = new ArrayList();
        if ((localObject3 != null) && (((List)localObject3).size() > 0))
        {
          localObject3 = ((List)localObject3).iterator();
          while (((Iterator)localObject3).hasNext()) {
            localArrayList.add(((PackageInfo)((Iterator)localObject3).next()).packageName);
          }
        }
        return localArrayList;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return null;
  }
  
  public final List<PackageInfo> c()
  {
    try
    {
      synchronized (this.e)
      {
        if (this.d == null) {
          this.d = this.c.getInstalledPackages(0);
        }
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return this.d;
  }
}
