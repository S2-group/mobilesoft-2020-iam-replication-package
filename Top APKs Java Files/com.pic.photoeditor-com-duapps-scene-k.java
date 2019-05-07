package com.duapps.scene;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.duapps.utils.a;
import com.duapps.utils.e;
import com.duapps.utils.g;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class k
{
  static int cEG;
  static int cEH;
  
  public k() {}
  
  public static int afq()
  {
    int[] arrayOfInt = e.afr();
    int i = arrayOfInt[1];
    float f = arrayOfInt[1] - arrayOfInt[0];
    if (i > 0) {}
    for (;;)
    {
      return (int)(f * 1.0F / i * 100.0F);
      i = 1;
    }
  }
  
  public static int aiI()
  {
    Object localObject4;
    try
    {
      localObject4 = a.lz("/proc/stat");
      if (localObject4 == null) {
        return 0;
      }
    }
    catch (Exception localException1)
    {
      return 0;
    }
    Object localObject3 = null;
    Object localObject1 = null;
    localObject2 = localObject3;
    for (;;)
    {
      try
      {
        localObject4 = ((String)localObject4).split("\n");
        localObject2 = localObject3;
        j = localObject4.length;
        i = 0;
        localObject2 = localObject1;
        if (i >= j) {
          continue;
        }
        localObject3 = localObject4[i];
        localObject2 = localObject1;
        boolean bool = localObject3.startsWith("cpu ");
        if (!bool) {
          continue;
        }
        localObject1 = localObject3;
      }
      catch (Exception localException2)
      {
        int j;
        int i;
        if (localObject2 != null) {
          continue;
        }
        return 0;
        try
        {
          String[] arrayOfString = localObject2.split("\\s+");
          j = Integer.parseInt(arrayOfString[1]);
          int k = Integer.parseInt(arrayOfString[2]);
          int m = Integer.parseInt(arrayOfString[3]);
          i = Integer.parseInt(arrayOfString[4]);
          j = j + k + m + i;
          k = cEG;
          m = j - cEH;
          cEG = i;
          cEH = j;
          float f1 = 0.0F;
          if (m > 0) {
            f1 = (m - (i - k)) * 100 / m;
          }
          float f2 = f1;
          if (f1 > 100.0F) {
            f2 = (float)(10.0D * Math.random()) + 90.0F;
          }
          return (int)f2;
        }
        catch (Exception localException3)
        {
          return 0;
        }
        continue;
      }
      i += 1;
    }
  }
  
  public static List<String> la(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    try
    {
      paramContext = ((PackageManager)localObject).getInstalledApplications(128);
      if ((paramContext == null) || (paramContext.isEmpty())) {
        return null;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        try
        {
          paramContext = ((PackageManager)localObject).getInstalledApplications(128);
        }
        catch (Exception paramContext)
        {
          paramContext = null;
        }
      }
    }
    finally {}
    localObject = new LinkedList();
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null) && (!localApplicationInfo.metaData.isEmpty()) && (localApplicationInfo.metaData.getInt("duscene_version", -1) >= 0)) {
        ((LinkedList)localObject).add(localApplicationInfo.packageName);
      }
    }
    return localObject;
  }
  
  public static String mC(Context paramContext)
  {
    Object localObject2 = la(paramContext);
    Object localObject1 = "";
    if ((localObject2 == null) || (((List)localObject2).isEmpty()))
    {
      i.cu(paramContext, "");
      return "";
    }
    long l1 = System.currentTimeMillis();
    Iterator localIterator = ((List)localObject2).iterator();
    while (localIterator.hasNext())
    {
      localObject2 = (String)localIterator.next();
      if (i.ct(paramContext, (String)localObject2))
      {
        long l2 = i.cv(paramContext, (String)localObject2);
        if (l2 >= l1) {
          break label109;
        }
        localObject1 = localObject2;
        l1 = l2;
      }
    }
    label109:
    for (;;)
    {
      break;
      i.cu(paramContext, (String)localObject1);
      return localObject1;
    }
  }
  
  public static SceneType mZ(Context paramContext)
  {
    if (!g.e(paramContext, "com.dianxinos.optimizer.duplay"))
    {
      SceneType.cEL.priority = ScenePriority.cEn;
      SceneType.cEI.priority = ScenePriority.cEm;
      return SceneType.cEI;
    }
    if (!g.e(paramContext, "com.dianxinos.dxbs"))
    {
      SceneType.cEL.priority = ScenePriority.cEm;
      SceneType.cEI.priority = ScenePriority.cEn;
      return SceneType.cEL;
    }
    return null;
  }
  
  public static boolean q(Context paramContext, SceneType paramSceneType)
  {
    return false;
  }
}
