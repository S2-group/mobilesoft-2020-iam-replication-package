package com.cam001.clean;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class b
{
  private static List<PackageInfo> a = new ArrayList();
  
  private static int a(int paramInt1, int paramInt2)
  {
    return new Random().nextInt(paramInt2) % (paramInt2 - paramInt1 + 1) + paramInt1;
  }
  
  public static List<Drawable> a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = a;
    int m = 0;
    if ((paramContext != null) && (!a.isEmpty()))
    {
      paramContext = a;
    }
    else
    {
      paramContext = localPackageManager.getInstalledPackages(0);
      a = paramContext;
    }
    Object localObject;
    if (paramContext.size() >= 7)
    {
      localArrayList = new ArrayList();
      localObject = new int[5];
      int j;
      for (int i = 0;; i = j + 1)
      {
        j = m;
        if (i >= 5) {
          break;
        }
        localObject[i] = a(0, paramContext.size() - 1);
        int k = 0;
        j = i;
        i = k;
        while (i < j)
        {
          if (localObject[j] != localObject[i])
          {
            k = j;
            if (!a((PackageInfo)paramContext.get(localObject[j]))) {}
          }
          else
          {
            k = j - 1;
          }
          i += 1;
          j = k;
        }
      }
      while (j < localObject.length)
      {
        localArrayList.add(((PackageInfo)paramContext.get(localObject[j])).applicationInfo.loadIcon(localPackageManager));
        j += 1;
      }
      return localArrayList;
    }
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      localObject = (PackageInfo)paramContext.next();
      if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) == 0)
      {
        Drawable localDrawable = ((PackageInfo)localObject).applicationInfo.loadIcon(localPackageManager);
        if ((!((PackageInfo)localObject).packageName.equals("com.cam001.selfie")) && (!((PackageInfo)localObject).packageName.equals("com.ufotosoft.justshot"))) {
          localArrayList.add(localDrawable);
        }
        Log.i("zzw", ((PackageInfo)localObject).packageName);
      }
    }
    return localArrayList;
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    if ((paramPackageInfo.applicationInfo.flags & 0x1) == 0) {
      return (!paramPackageInfo.packageName.equals("com.cam001.selfie")) && (!paramPackageInfo.packageName.equals("com.ufotosoft.justshot"));
    }
    return false;
  }
}
