package com.tappx.a.a.a.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.tappx.a.a.a.a.b.e;
import com.tappx.a.a.a.a.c.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c
  implements e
{
  private static final boolean a = true;
  private final Context b;
  
  public c(Context paramContext)
  {
    this.b = paramContext;
  }
  
  public List<a> a()
  {
    return a(true);
  }
  
  public List<a> a(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      PackageManager localPackageManager;
      PackageInfo localPackageInfo;
      int i;
      try
      {
        localPackageManager = this.b.getPackageManager();
        Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
        if (!localIterator.hasNext()) {
          break label156;
        }
        localPackageInfo = (PackageInfo)localIterator.next();
        localObject1 = localPackageInfo.applicationInfo;
        if (localObject1 == null) {
          continue;
        }
        if ((((ApplicationInfo)localObject1).flags & 0x1) != 1) {
          break label159;
        }
        i = 1;
      }
      catch (Exception localException) {}
      String str1 = ((ApplicationInfo)localObject1).packageName;
      Object localObject1 = ((ApplicationInfo)localObject1).loadLabel(localPackageManager);
      if (localObject1 != null)
      {
        localObject1 = ((CharSequence)localObject1).toString();
        String str2 = localPackageInfo.versionName;
        i = localPackageInfo.versionCode;
        if ((str1 == null) || (str1.isEmpty())) {
          continue;
        }
        localArrayList.add(new a(str1, (String)localObject1, str2, String.valueOf(i)));
        continue;
        label156:
        return localArrayList;
        label159:
        i = 0;
      }
      else
      {
        Object localObject2 = null;
        continue;
      }
      if (paramBoolean) {
        if (i != 0) {}
      }
    }
  }
}
