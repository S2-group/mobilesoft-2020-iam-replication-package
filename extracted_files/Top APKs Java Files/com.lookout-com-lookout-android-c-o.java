package com.lookout.android.c;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.lookout.aa.as;
import java.util.ArrayList;
import java.util.List;

public class o
{
  public o() {}
  
  public n a(PackageInfo paramPackageInfo)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramPackageInfo);
    return a(localArrayList);
  }
  
  public n a(PackageManager paramPackageManager)
  {
    return a(paramPackageManager.getInstalledPackages(4096));
  }
  
  public n a(as paramAs)
  {
    n localN = null;
    if ((paramAs instanceof com.lookout.aa.a.a)) {
      localN = new n((com.lookout.aa.a.a)paramAs);
    }
    while (!(paramAs instanceof com.lookout.androidsecurity.a.a.a)) {
      return localN;
    }
    return new n((com.lookout.androidsecurity.a.a.a)paramAs);
  }
  
  public n a(List paramList)
  {
    return new n(paramList);
  }
}
