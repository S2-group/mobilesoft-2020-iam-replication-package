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
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(PackageFilter.class.getPackage().getName());
    localStringBuilder.append(".PackageFilter");
    return localStringBuilder.toString();
  }
  
  public static List<String> getPackageNames()
  {
    Object localObject = mContext.getPackageManager();
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (localObject != null) {
      while (i < ((List)localObject).size())
      {
        if ((((PackageInfo)((List)localObject).get(i)).applicationInfo.flags & 0x1) == 0) {
          localArrayList.add(((PackageInfo)((List)localObject).get(i)).packageName);
        }
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public boolean exclude(Context paramContext, List<String> paramList)
  {
    if (TextUtils.isEmpty(this.mExclude)) {
      return false;
    }
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
    return false;
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
    if (TextUtils.isEmpty(this.mInclude)) {
      return true;
    }
    paramContext = this.mInclude.split(",");
    int i = 0;
    while (i < paramContext.length)
    {
      if ((!TextUtils.isEmpty(paramContext[i])) && (paramList.contains(paramContext[i])))
      {
        f.b(String.format("****** include package: %s*********\n", new Object[] { paramContext[i] }));
        return true;
      }
      i += 1;
    }
    return false;
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
