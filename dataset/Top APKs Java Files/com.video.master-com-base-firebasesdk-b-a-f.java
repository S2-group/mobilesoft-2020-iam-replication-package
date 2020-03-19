package com.base.firebasesdk.b.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public class f
  extends a
{
  protected String c = "";
  protected String d = "";
  
  public f() {}
  
  public static String b()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(f.class.getPackage().getName());
    localStringBuilder.append(".PackageFilter");
    return localStringBuilder.toString();
  }
  
  public static List<String> c()
  {
    Object localObject = b.getPackageManager();
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
  
  public void a(String paramString)
  {
    this.c = paramString;
  }
  
  public boolean a(Context paramContext, List<String> paramList)
  {
    if (TextUtils.isEmpty(this.c)) {
      return true;
    }
    paramContext = this.c.split(",");
    int i = 0;
    while (i < paramContext.length)
    {
      if ((!TextUtils.isEmpty(paramContext[i])) && (paramList.contains(paramContext[i])))
      {
        com.base.firebasesdk.f.f.b(String.format("****** include package: %s*********\n", new Object[] { paramContext[i] }));
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public void b(String paramString)
  {
    this.d = paramString;
  }
  
  public boolean b(Context paramContext, List<String> paramList)
  {
    if (TextUtils.isEmpty(this.d)) {
      return false;
    }
    paramContext = this.d.split(",");
    int i = 0;
    while (i < paramContext.length)
    {
      if ((!TextUtils.isEmpty(paramContext[i])) && (paramList.contains(paramContext[i])))
      {
        com.base.firebasesdk.f.f.b(String.format("****** exclude package: %s*********\n", new Object[] { paramContext[i] }));
        return true;
      }
      i += 1;
    }
    return false;
  }
}
