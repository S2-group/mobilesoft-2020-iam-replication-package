package com.xiaomi.push.mpcd.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.xiaomi.a.a.a.a;
import com.xiaomi.h.a.y;
import java.util.Iterator;
import java.util.List;

public class c
  extends f
{
  public c(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }
  
  public int a()
  {
    return 4;
  }
  
  public String b()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      try
      {
        PackageManager localPackageManager = this.d.getPackageManager();
        Iterator localIterator = localPackageManager.getInstalledPackages(128).iterator();
        int i = 0;
        if (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          if ((localPackageInfo.applicationInfo.flags & 0x1) != 0) {
            continue;
          }
          if (localStringBuilder.length() > 0) {
            localStringBuilder.append(";");
          }
          String str1 = localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString();
          String str2 = a.f(this.d, localPackageInfo.packageName);
          localStringBuilder.append(str1).append(",").append(localPackageInfo.packageName).append(",").append(localPackageInfo.versionName).append(",").append(localPackageInfo.versionCode).append(",").append(str2);
          i += 1;
          if (i < 200) {
            continue;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        continue;
      }
      return localStringBuilder.toString();
    }
  }
  
  public y d()
  {
    return y.b;
  }
}
