package com.cleanmaster.func.cache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cleanmaster.common.Commons;
import com.d.e;
import com.d.g;
import com.d.h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PackageManagerWrapper
{
  private static PackageManagerWrapper instanceManagerWrapper = new PackageManagerWrapper();
  private Context mCtxContext = g.a().c();
  private AtomicBoolean mIsNeedUpdatePkgList = new AtomicBoolean(true);
  private PackageManager mPM = this.mCtxContext.getPackageManager();
  private List<PackageInfo> pkgList;
  
  public PackageManagerWrapper() {}
  
  private List<PackageInfo> getInstalledPkgNoThrow(int paramInt)
  {
    for (;;)
    {
      try
      {
        e localE = g.a().n();
        if (localE != null) {
          this.pkgList = localE.a();
        }
        if (this.pkgList != null) {
          continue;
        }
        this.pkgList = this.mPM.getInstalledPackages(paramInt);
      }
      catch (Exception localException)
      {
        continue;
      }
      return this.pkgList;
      this.mIsNeedUpdatePkgList.set(false);
    }
  }
  
  public static PackageManagerWrapper getInstance()
  {
    try
    {
      PackageManagerWrapper localPackageManagerWrapper = instanceManagerWrapper;
      return localPackageManagerWrapper;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void addPkg(String paramString, Context paramContext)
  {
    if (!this.mIsNeedUpdatePkgList.get()) {
      return;
    }
    for (;;)
    {
      int i;
      try
      {
        if (this.pkgList == null) {
          break;
        }
        paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
        i = 0;
        if (i < this.pkgList.size())
        {
          if (((PackageInfo)this.pkgList.get(i)).packageName.equals(paramString)) {
            this.pkgList.remove(i);
          }
        }
        else
        {
          this.pkgList.add(paramContext);
          return;
        }
      }
      catch (Exception paramString)
      {
        return;
      }
      i += 1;
    }
  }
  
  public void deletePkg(String paramString)
  {
    if (!this.mIsNeedUpdatePkgList.get()) {}
    for (;;)
    {
      return;
      if (this.pkgList != null)
      {
        int i = 0;
        while (i < this.pkgList.size())
        {
          if (((PackageInfo)this.pkgList.get(i)).packageName.equals(paramString))
          {
            this.pkgList.remove(i);
            return;
          }
          i += 1;
        }
      }
    }
  }
  
  public List<PackageInfo> getPkgInfoList()
  {
    return getInstalledPkgNoThrow(0);
  }
  
  public List<PackageInfo> getSystemPkgInfoList()
  {
    Object localObject = getInstalledPkgNoThrow(0);
    ArrayList localArrayList = new ArrayList();
    if ((localObject != null) && (((List)localObject).size() > 0))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (Commons.isSystemApp(localPackageInfo.applicationInfo)) {
          localArrayList.add(localPackageInfo);
        }
      }
    }
    return localArrayList;
  }
  
  public List<PackageInfo> getUserPkgInfoList()
  {
    try
    {
      Object localObject = getInstalledPkgNoThrow(0);
      ArrayList localArrayList = new ArrayList();
      if ((localObject != null) && (((List)localObject).size() > 0))
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
          if (Commons.isUserApp(localPackageInfo.applicationInfo)) {
            localArrayList.add(localPackageInfo);
          }
        }
      }
    }
    finally {}
    return localList;
  }
  
  public List<PackageInfo> getUserPkgInfoListWithPermisson()
  {
    Object localObject = getInstalledPkgNoThrow(4096);
    ArrayList localArrayList = new ArrayList();
    if ((localObject != null) && (((List)localObject).size() > 0))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (Commons.isUserApp(localPackageInfo.applicationInfo)) {
          localArrayList.add(localPackageInfo);
        }
      }
    }
    return localArrayList;
  }
  
  public List<String> getUserPkgNameList()
  {
    Object localObject = getInstalledPkgNoThrow(0);
    ArrayList localArrayList = new ArrayList();
    if ((localObject != null) && (((List)localObject).size() > 0))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (Commons.isUserApp(localPackageInfo.applicationInfo)) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
    }
    return localArrayList;
  }
}
