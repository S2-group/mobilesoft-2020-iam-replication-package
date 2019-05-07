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
    ArrayList localArrayList;
    Object localObject;
    int i;
    label53:
    int j;
    if ((a != null) && (!a.isEmpty()))
    {
      paramContext = a;
      if (paramContext.size() < 7) {
        break label189;
      }
      localArrayList = new ArrayList();
      localObject = new int[5];
      i = 0;
      if (i >= 5) {
        break label142;
      }
      localObject[i] = a(0, paramContext.size() - 1);
      j = 0;
      label76:
      if (j >= i) {
        break label135;
      }
      if ((localObject[i] != localObject[j]) && (!a((PackageInfo)paramContext.get(localObject[i])))) {
        break label302;
      }
      i -= 1;
    }
    label135:
    label142:
    label189:
    label302:
    for (;;)
    {
      j += 1;
      break label76;
      paramContext = localPackageManager.getInstalledPackages(0);
      a = paramContext;
      break;
      i += 1;
      break label53;
      i = 0;
      while (i < localObject.length)
      {
        localArrayList.add(((PackageInfo)paramContext.get(localObject[i])).applicationInfo.loadIcon(localPackageManager));
        i += 1;
      }
      return localArrayList;
      localArrayList = new ArrayList();
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
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramPackageInfo.applicationInfo.flags & 0x1) == 0)
    {
      bool1 = bool2;
      if (!paramPackageInfo.packageName.equals("com.cam001.selfie"))
      {
        bool1 = bool2;
        if (!paramPackageInfo.packageName.equals("com.ufotosoft.justshot")) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
}
