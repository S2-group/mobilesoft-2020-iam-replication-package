package com.duapps.scene;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.duapps.c.a;
import com.duapps.c.e;
import com.duapps.c.g;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class at
{
  static int a;
  static int b;
  
  public at() {}
  
  public static int a()
  {
    int[] arrayOfInt = e.a();
    int i = arrayOfInt[1];
    float f = arrayOfInt[1] - arrayOfInt[0];
    if (i > 0) {}
    for (;;)
    {
      return (int)(f * 1.0F / i * 100.0F);
      i = 1;
    }
  }
  
  public static List<String> a(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    for (;;)
    {
      try
      {
        paramContext = ((PackageManager)localObject).getInstalledApplications(128);
        if ((paramContext == null) || (paramContext.isEmpty())) {
          return null;
        }
      }
      catch (Exception localException)
      {
        try
        {
          paramContext = ((PackageManager)localObject).getInstalledApplications(128);
        }
        catch (Exception paramContext)
        {
          if (!h.b()) {
            break label148;
          }
        }
        localException.printStackTrace();
      }
      finally {}
      LinkedList localLinkedList = new LinkedList();
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        localObject = (ApplicationInfo)paramContext.next();
        if ((localObject != null) && (((ApplicationInfo)localObject).metaData != null) && (!((ApplicationInfo)localObject).metaData.isEmpty()) && (((ApplicationInfo)localObject).metaData.getInt("duscene_version", -1) >= 0)) {
          localLinkedList.add(((ApplicationInfo)localObject).packageName);
        }
      }
      return localLinkedList;
      label148:
      paramContext = null;
    }
  }
  
  public static boolean a(Context paramContext, SceneType paramSceneType)
  {
    return false;
  }
  
  public static int b()
  {
    Object localObject4;
    try
    {
      localObject4 = a.a("/proc/stat");
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
          k = a;
          m = j - b;
          a = i;
          b = j;
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
  
  public static String b(Context paramContext)
  {
    Object localObject2 = a(paramContext);
    Object localObject1 = "";
    if ((localObject2 == null) || (((List)localObject2).isEmpty()))
    {
      ar.c(paramContext, "");
      return "";
    }
    long l1 = System.currentTimeMillis();
    Iterator localIterator = ((List)localObject2).iterator();
    while (localIterator.hasNext())
    {
      localObject2 = (String)localIterator.next();
      if (ar.b(paramContext, (String)localObject2))
      {
        long l2 = ar.d(paramContext, (String)localObject2);
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
      ar.c(paramContext, (String)localObject1);
      return localObject1;
    }
  }
  
  public static SceneType c(Context paramContext)
  {
    if (!g.b(paramContext, "com.dianxinos.optimizer.duplay"))
    {
      SceneType.BATTERY_SHARPDEC.priority = ScenePriority.A;
      SceneType.BG_CPU_OVERLOAD.priority = ScenePriority.A_PLUS;
      return SceneType.BG_CPU_OVERLOAD;
    }
    if (!g.b(paramContext, "com.dianxinos.dxbs"))
    {
      SceneType.BATTERY_SHARPDEC.priority = ScenePriority.A_PLUS;
      SceneType.BG_CPU_OVERLOAD.priority = ScenePriority.A;
      return SceneType.BATTERY_SHARPDEC;
    }
    return null;
  }
}
