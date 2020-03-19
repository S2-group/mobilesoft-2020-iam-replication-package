package com.cmcm.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cmcm.adsdk.CMAdManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class j
{
  private static j a = new j();
  private Context b = CMAdManager.getContext();
  private PackageManager c = this.b.getPackageManager();
  private List<PackageInfo> d;
  private Object e = new Object();
  
  public j() {}
  
  public static j a()
  {
    try
    {
      j localJ = a;
      return localJ;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private List<PackageInfo> a(int paramInt)
  {
    try
    {
      synchronized (this.e)
      {
        if (this.d == null) {
          this.d = this.c.getInstalledPackages(paramInt);
        }
      }
    }
    catch (Exception localException) {}
    return this.d;
  }
  
  public List<String> a(boolean paramBoolean)
  {
    Object localObject = a(0);
    ArrayList localArrayList = new ArrayList();
    if ((localObject != null) && (((List)localObject).size() > 0))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((paramBoolean) || (Commons.isUserApp(localPackageInfo.applicationInfo))) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
    }
    return localArrayList;
  }
  
  public void a(String paramString)
  {
    synchronized (this.e)
    {
      if (this.d != null)
      {
        int i = 0;
        while (i < this.d.size())
        {
          if (((PackageInfo)this.d.get(i)).packageName.equals(paramString))
          {
            this.d.remove(i);
            break;
          }
          i += 1;
        }
      }
      return;
    }
  }
  
  public void a(String paramString, Context paramContext)
  {
    for (;;)
    {
      int i;
      try
      {
        synchronized (this.e)
        {
          if (this.d != null)
          {
            paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
            i = 0;
            if (i < this.d.size())
            {
              if (!((PackageInfo)this.d.get(i)).packageName.equals(paramString)) {
                break label104;
              }
              this.d.remove(i);
            }
            this.d.add(paramContext);
          }
        }
        return;
      }
      catch (Exception paramString)
      {
        return;
      }
      label104:
      i += 1;
    }
  }
}
