package com.xiaomi.push.mpcd.job;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.xiaomi.channel.commonutils.android.a;
import com.xiaomi.xmpush.thrift.d;
import java.util.Iterator;
import java.util.List;

public class c
  extends g
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
    try
    {
      PackageManager localPackageManager = this.d.getPackageManager();
      Object localObject = localPackageManager.getInstalledPackages(128);
      int i = 0;
      localObject = ((List)localObject).iterator();
      int j;
      do
      {
        PackageInfo localPackageInfo;
        do
        {
          if (!((Iterator)localObject).hasNext()) {
            break;
          }
          localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        } while ((localPackageInfo.applicationInfo.flags & 0x1) != 0);
        if (localStringBuilder.length() > 0) {
          localStringBuilder.append(";");
        }
        String str1 = localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString();
        String str2 = a.f(this.d, localPackageInfo.packageName);
        localStringBuilder.append(str1);
        localStringBuilder.append(",");
        localStringBuilder.append(localPackageInfo.packageName);
        localStringBuilder.append(",");
        localStringBuilder.append(localPackageInfo.versionName);
        localStringBuilder.append(",");
        localStringBuilder.append(localPackageInfo.versionCode);
        localStringBuilder.append(",");
        localStringBuilder.append(str2);
        j = i + 1;
        i = j;
      } while (j < 200);
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return localStringBuilder.toString();
  }
  
  public d d()
  {
    return d.b;
  }
}
