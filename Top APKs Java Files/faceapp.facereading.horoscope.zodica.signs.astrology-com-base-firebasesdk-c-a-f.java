package com.base.firebasesdk.c.a;

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
    return f.class.getPackage().getName() + ".PackageFilter";
  }
  
  public static List<String> c()
  {
    List localList = b.getPackageManager().getInstalledPackages(0);
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
  
  public void a(String paramString)
  {
    this.c = paramString;
  }
  
  public boolean a(Context paramContext, List<String> paramList)
  {
    boolean bool2 = false;
    boolean bool1;
    if (TextUtils.isEmpty(this.c))
    {
      bool1 = true;
      return bool1;
    }
    paramContext = this.c.split(",");
    int i = 0;
    for (;;)
    {
      bool1 = bool2;
      if (i >= paramContext.length) {
        break;
      }
      if ((!TextUtils.isEmpty(paramContext[i])) && (paramList.contains(paramContext[i])))
      {
        com.base.firebasesdk.g.f.b(String.format("****** include package: %s*********\n", new Object[] { paramContext[i] }));
        return true;
      }
      i += 1;
    }
  }
  
  public void b(String paramString)
  {
    this.d = paramString;
  }
  
  public boolean b(Context paramContext, List<String> paramList)
  {
    if (TextUtils.isEmpty(this.d)) {}
    for (;;)
    {
      return false;
      paramContext = this.d.split(",");
      int i = 0;
      while (i < paramContext.length)
      {
        if ((!TextUtils.isEmpty(paramContext[i])) && (paramList.contains(paramContext[i])))
        {
          com.base.firebasesdk.g.f.b(String.format("****** exclude package: %s*********\n", new Object[] { paramContext[i] }));
          return true;
        }
        i += 1;
      }
    }
  }
}
