package com.base.firebasesdk.filter.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.base.firebasesdk.d.f;
import java.util.ArrayList;
import java.util.List;

public class PackageFilter
  extends BaseFilter
{
  protected String mExclude = "";
  protected String mInclude = "";
  
  public PackageFilter() {}
  
  public static String getPackageNameClassName()
  {
    return PackageFilter.class.getPackage().getName() + ".PackageFilter";
  }
  
  public static List<String> getPackageNames()
  {
    List localList = mContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (localList != null)
    {
      int i = 0;
      while (i < localList.size())
      {
        if ((((PackageInfo)localList.get(i)).applicationInfo.flags & 0x1) == 0) {
          localArrayList.add(((PackageInfo)localList.get(i)).packageName);
        }
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public boolean exclude(Context paramContext, List<String> paramList)
  {
    if (TextUtils.isEmpty(this.mExclude)) {}
    for (;;)
    {
      return false;
      paramContext = this.mExclude.split(",");
      int i = 0;
      while (i < paramContext.length)
      {
        if ((!TextUtils.isEmpty(paramContext[i])) && (paramList.contains(paramContext[i])))
        {
          f.b(String.format("****** exclude package: %s*********\n", new Object[] { paramContext[i] }));
          return true;
        }
        i += 1;
      }
    }
  }
  
  public String getExclude()
  {
    return this.mExclude;
  }
  
  public String getInclude()
  {
    return this.mInclude;
  }
  
  public boolean include(Context paramContext, List<String> paramList)
  {
    boolean bool2 = false;
    boolean bool1;
    if (TextUtils.isEmpty(this.mInclude))
    {
      bool1 = true;
      return bool1;
    }
    paramContext = this.mInclude.split(",");
    int i = 0;
    for (;;)
    {
      bool1 = bool2;
      if (i >= paramContext.length) {
        break;
      }
      if ((!TextUtils.isEmpty(paramContext[i])) && (paramList.contains(paramContext[i])))
      {
        f.b(String.format("****** include package: %s*********\n", new Object[] { paramContext[i] }));
        return true;
      }
      i += 1;
    }
  }
  
  public void setExclude(String paramString)
  {
    this.mExclude = paramString;
  }
  
  public void setInclude(String paramString)
  {
    this.mInclude = paramString;
  }
}
