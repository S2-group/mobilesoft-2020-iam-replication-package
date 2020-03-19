package com.hyperspeed.rocket.applock.free;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.SparseIntArray;
import com.ihs.device.common.AppInfoProvider;
import com.ihs.device.common.HSAppFilter;
import com.ihs.device.common.HSAppFilter.a;
import com.ihs.device.common.HSAppRunningInfo;
import com.ihs.device.common.utils.processes.models.AndroidAppProcess;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class biv
{
  public static <T extends HSAppRunningInfo> List<T> as(Class<T> paramClass, HSAppFilter paramHSAppFilter)
  {
    Object localObject1 = paramHSAppFilter;
    if (paramHSAppFilter == null) {
      localObject1 = new HSAppFilter();
    }
    if (Build.VERSION.SDK_INT >= 26) {
      paramClass = er(paramClass, (HSAppFilter)localObject1);
    }
    int j;
    int i;
    int k;
    for (;;)
    {
      localObject1 = new SparseIntArray();
      paramHSAppFilter = paramClass.iterator();
      while (paramHSAppFilter.hasNext())
      {
        localObject2 = ((HSAppRunningInfo)paramHSAppFilter.next()).as();
        j = localObject2.length;
        i = 0;
        while (i < j)
        {
          k = localObject2[i];
          ((SparseIntArray)localObject1).put(k, ((SparseIntArray)localObject1).get(k, 0) + 1);
          i += 1;
        }
      }
      if (Build.VERSION.SDK_INT >= 21) {
        paramClass = td(paramClass, (HSAppFilter)localObject1);
      } else {
        paramClass = xv(paramClass, (HSAppFilter)localObject1);
      }
    }
    Object localObject2 = paramClass.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      HSAppRunningInfo localHSAppRunningInfo = (HSAppRunningInfo)((Iterator)localObject2).next();
      j = localHSAppRunningInfo.as().length;
      paramHSAppFilter = new double[0];
      if (j > 0)
      {
        paramHSAppFilter = new double[j];
        i = 0;
        if (i < j)
        {
          k = localHSAppRunningInfo.as()[i];
          int m = ((SparseIntArray)localObject1).get(k);
          if (m > 0) {
            if (m <= 0) {
              break label262;
            }
          }
          label262:
          for (double d = 1.0D / ((SparseIntArray)localObject1).get(k);; d = 1.0D)
          {
            paramHSAppFilter[i] = d;
            i += 1;
            break;
          }
        }
      }
      localHSAppRunningInfo.as(paramHSAppFilter);
    }
    return paramClass;
  }
  
  private static <T extends HSAppRunningInfo> List<T> er(Class<T> paramClass, HSAppFilter paramHSAppFilter)
  {
    ArrayList localArrayList = new ArrayList();
    paramClass = AppInfoProvider.as(paramClass, new HSAppFilter().nf());
    Object localObject1 = bdr.as().getPackageManager().getInstalledPackages(128);
    Object localObject2 = paramClass.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      HSAppRunningInfo localHSAppRunningInfo = (HSAppRunningInfo)((Iterator)localObject2).next();
      Iterator localIterator = ((List)localObject1).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (localHSAppRunningInfo.getPackageName().equals(localPackageInfo.packageName)) {
          localHSAppRunningInfo.setApplicationInfoFlag(localPackageInfo.applicationInfo.flags);
        }
      }
      if ((localHSAppRunningInfo.getApplicationInfoFlag() & 0x200000) != 0) {
        ((Iterator)localObject2).remove();
      }
    }
    localObject1 = new HashSet();
    try
    {
      localObject2 = bfc.as(Uri.parse("content://" + bdr.as().getPackageName() + ".recent_app/"), "METHOD_GET_RECENT_APP_LIST", null).getStringArrayList("EXTRA_RECENT_APP_LIST");
      if (localObject2 != null) {
        ((Set)localObject1).addAll((Collection)localObject2);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    paramHSAppFilter.as();
    paramClass = paramClass.iterator();
    while (paramClass.hasNext())
    {
      localObject2 = (HSAppRunningInfo)paramClass.next();
      ((HSAppRunningInfo)localObject2).as(new int[0]);
      ((HSAppRunningInfo)localObject2).setIsMusicPlayer(biw.as((HSAppRunningInfo)localObject2));
      ((HSAppRunningInfo)localObject2).setIsRecentApp(((Set)localObject1).contains(((HSAppRunningInfo)localObject2).getPackageName()));
      if (paramHSAppFilter.as((HSAppFilter.a)localObject2)) {
        localArrayList.add(localObject2);
      }
    }
    return localArrayList;
  }
  
  private static <T extends HSAppRunningInfo> List<T> td(Class<T> paramClass, HSAppFilter paramHSAppFilter)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = (ActivityManager)bdr.as().getSystemService("activity");
    if (localObject1 == null) {
      return localArrayList;
    }
    Object localObject2 = ((ActivityManager)localObject1).getRunningServices(Integer.MAX_VALUE);
    if ((localObject2 == null) || (((List)localObject2).size() == 0)) {
      return localArrayList;
    }
    localObject1 = new HashMap();
    localObject2 = ((List)localObject2).iterator();
    Object localObject4;
    Object localObject5;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (ActivityManager.RunningServiceInfo)((Iterator)localObject2).next();
      localObject4 = ((ActivityManager.RunningServiceInfo)localObject3).service.getPackageName();
      if (((Map)localObject1).get(localObject4) == null)
      {
        localObject5 = new ArrayList();
        ((List)localObject5).add(localObject3);
        ((Map)localObject1).put(localObject4, localObject5);
      }
      else
      {
        ((List)((Map)localObject1).get(localObject4)).add(localObject3);
      }
    }
    localObject2 = new HashSet();
    try
    {
      localObject3 = bfc.as(Uri.parse("content://" + bdr.as().getPackageName() + ".recent_app/"), "METHOD_GET_RECENT_APP_LIST", null).getStringArrayList("EXTRA_RECENT_APP_LIST");
      if (localObject3 != null) {
        ((Set)localObject2).addAll((Collection)localObject3);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
      localObject4 = ((Map)localObject1).keySet().iterator();
    }
    paramClass = AppInfoProvider.as(paramClass, new HSAppFilter().nf());
    Object localObject3 = new HashMap();
    paramClass = paramClass.iterator();
    while (paramClass.hasNext())
    {
      localObject4 = (HSAppRunningInfo)paramClass.next();
      ((Map)localObject3).put(((HSAppRunningInfo)localObject4).getPackageName(), localObject4);
    }
    for (;;)
    {
      HSAppRunningInfo localHSAppRunningInfo;
      SparseIntArray localSparseIntArray;
      if (((Iterator)localObject4).hasNext())
      {
        localObject5 = (String)((Iterator)localObject4).next();
        localHSAppRunningInfo = (HSAppRunningInfo)localException.get(localObject5);
        if ((localHSAppRunningInfo == null) || (biw.as(localHSAppRunningInfo, localArrayList))) {
          continue;
        }
        paramClass = (List)((Map)localObject1).get(localObject5);
        localSparseIntArray = new SparseIntArray();
        Iterator localIterator = paramClass.iterator();
        label403:
        if (localIterator.hasNext()) {
          paramClass = (ActivityManager.RunningServiceInfo)localIterator.next();
        }
      }
      try
      {
        if (paramClass.process.equalsIgnoreCase((String)localObject5)) {
          localHSAppRunningInfo.as(paramClass.pid);
        }
        localSparseIntArray.put(paramClass.pid, paramClass.pid);
        localHSAppRunningInfo.er.add(paramClass.service);
      }
      catch (Exception paramClass)
      {
        paramClass.printStackTrace();
        break label403;
        localHSAppRunningInfo.as(biz.as(localSparseIntArray));
        localHSAppRunningInfo.setIsMusicPlayer(biw.as(localHSAppRunningInfo));
        localHSAppRunningInfo.setIsRecentApp(((Set)localObject2).contains(localHSAppRunningInfo.getPackageName()));
        if (!paramHSAppFilter.as(localHSAppRunningInfo)) {
          continue;
        }
        localArrayList.add(localHSAppRunningInfo);
        continue;
        if (Build.VERSION.SDK_INT < 24)
        {
          paramClass = bja.as().iterator();
          while (paramClass.hasNext())
          {
            localObject1 = (AndroidAppProcess)paramClass.next();
            localObject4 = (HSAppRunningInfo)localException.get(((AndroidAppProcess)localObject1).as());
            if (localObject4 != null)
            {
              if (((AndroidAppProcess)localObject1).xv.equalsIgnoreCase(((HSAppRunningInfo)localObject4).getPackageName())) {
                ((HSAppRunningInfo)localObject4).as(((AndroidAppProcess)localObject1).td);
              }
              localObject5 = biz.as(((HSAppRunningInfo)localObject4).as());
              ((SparseIntArray)localObject5).put(((AndroidAppProcess)localObject1).td, ((AndroidAppProcess)localObject1).td);
              ((HSAppRunningInfo)localObject4).as(biz.as((SparseIntArray)localObject5));
              ((HSAppRunningInfo)localObject4).setIsMusicPlayer(biw.as((HSAppRunningInfo)localObject4));
              ((HSAppRunningInfo)localObject4).setIsRecentApp(((Set)localObject2).contains(((HSAppRunningInfo)localObject4).getPackageName()));
              if (paramHSAppFilter.as((HSAppFilter.a)localObject4))
              {
                localArrayList.remove(localObject4);
                localArrayList.add(localObject4);
              }
            }
          }
        }
        return localArrayList;
      }
      catch (Error paramClass)
      {
        for (;;) {}
      }
    }
  }
  
  private static <T extends HSAppRunningInfo> List<T> xv(Class<T> paramClass, HSAppFilter paramHSAppFilter)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = (ActivityManager)bdr.as().getSystemService("activity");
    if (localObject1 == null) {
      return localArrayList;
    }
    Object localObject2 = ((ActivityManager)localObject1).getRunningAppProcesses();
    if ((localObject2 == null) || (((List)localObject2).size() == 0)) {
      return localArrayList;
    }
    localObject1 = new HashMap();
    localObject2 = ((List)localObject2).iterator();
    Object localObject4;
    Object localObject5;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
      localObject4 = localObject3.processName.split(":")[0];
      if (((Map)localObject1).get(localObject4) == null)
      {
        localObject5 = new ArrayList();
        ((List)localObject5).add(localObject3);
        ((Map)localObject1).put(localObject4, localObject5);
      }
      else
      {
        ((List)((Map)localObject1).get(localObject4)).add(localObject3);
      }
    }
    localObject2 = new HashSet();
    try
    {
      localObject3 = bfc.as(Uri.parse("content://" + bdr.as().getPackageName() + ".recent_app/"), "METHOD_GET_RECENT_APP_LIST", null).getStringArrayList("EXTRA_RECENT_APP_LIST");
      if (localObject3 != null) {
        ((Set)localObject2).addAll((Collection)localObject3);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
      Iterator localIterator = ((Map)localObject1).keySet().iterator();
      while (localIterator.hasNext())
      {
        localObject4 = (String)localIterator.next();
        localObject5 = (HSAppRunningInfo)paramClass.get(localObject4);
        if (localObject5 != null)
        {
          Object localObject6 = (List)((Map)localObject1).get(localObject4);
          SparseIntArray localSparseIntArray = new SparseIntArray();
          localObject6 = ((List)localObject6).iterator();
          while (((Iterator)localObject6).hasNext())
          {
            ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject6).next();
            if (localRunningAppProcessInfo.processName.equalsIgnoreCase((String)localObject4)) {
              ((HSAppRunningInfo)localObject5).as(localRunningAppProcessInfo.pid);
            }
            localSparseIntArray.put(localRunningAppProcessInfo.pid, localRunningAppProcessInfo.pid);
            ((HSAppRunningInfo)localObject5).er.add(localRunningAppProcessInfo.importanceReasonComponent);
          }
          ((HSAppRunningInfo)localObject5).as(biz.as(localSparseIntArray));
          ((HSAppRunningInfo)localObject5).setIsMusicPlayer(biw.as((HSAppRunningInfo)localObject5));
          ((HSAppRunningInfo)localObject5).setIsRecentApp(((Set)localObject2).contains(((HSAppRunningInfo)localObject5).getPackageName()));
          if (paramHSAppFilter.as((HSAppFilter.a)localObject5)) {
            localArrayList.add(localObject5);
          }
        }
      }
    }
    Object localObject3 = AppInfoProvider.as(paramClass, new HSAppFilter().nf());
    paramClass = new HashMap();
    localObject3 = ((List)localObject3).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (HSAppRunningInfo)((Iterator)localObject3).next();
      paramClass.put(((HSAppRunningInfo)localObject4).getPackageName(), localObject4);
    }
    return localArrayList;
  }
}
