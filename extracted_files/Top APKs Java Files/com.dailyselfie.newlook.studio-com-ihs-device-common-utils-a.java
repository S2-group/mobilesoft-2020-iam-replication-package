package com.ihs.device.common.utils;

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

public class a
{
  public static <T extends HSAppRunningInfo> List<T> a(Class<T> paramClass, HSAppFilter paramHSAppFilter)
  {
    Object localObject = paramHSAppFilter;
    if (paramHSAppFilter == null) {
      localObject = new HSAppFilter();
    }
    if (Build.VERSION.SDK_INT >= 26) {
      paramClass = b(paramClass, (HSAppFilter)localObject);
    } else if (Build.VERSION.SDK_INT >= 21) {
      paramClass = d(paramClass, (HSAppFilter)localObject);
    } else {
      paramClass = c(paramClass, (HSAppFilter)localObject);
    }
    SparseIntArray localSparseIntArray = new SparseIntArray();
    paramHSAppFilter = paramClass.iterator();
    int j;
    int i;
    int k;
    while (paramHSAppFilter.hasNext())
    {
      localObject = ((HSAppRunningInfo)paramHSAppFilter.next()).b();
      j = localObject.length;
      i = 0;
      while (i < j)
      {
        k = localObject[i];
        localSparseIntArray.put(k, localSparseIntArray.get(k, 0) + 1);
        i += 1;
      }
    }
    Iterator localIterator = paramClass.iterator();
    while (localIterator.hasNext())
    {
      HSAppRunningInfo localHSAppRunningInfo = (HSAppRunningInfo)localIterator.next();
      j = localHSAppRunningInfo.b().length;
      paramHSAppFilter = new double[0];
      if (j > 0)
      {
        localObject = new double[j];
        i = 0;
        for (;;)
        {
          paramHSAppFilter = (HSAppFilter)localObject;
          if (i >= j) {
            break;
          }
          k = localHSAppRunningInfo.b()[i];
          int m = localSparseIntArray.get(k);
          if (m > 0)
          {
            double d = 1.0D;
            if (m > 0) {
              d = 1.0D / localSparseIntArray.get(k);
            }
            localObject[i] = d;
          }
          i += 1;
        }
      }
      localHSAppRunningInfo.a(paramHSAppFilter);
    }
    return paramClass;
  }
  
  private static <T extends HSAppRunningInfo> List<T> b(Class<T> paramClass, HSAppFilter paramHSAppFilter)
  {
    ArrayList localArrayList = new ArrayList();
    paramClass = com.ihs.device.common.b.a(paramClass, new HSAppFilter().e());
    Object localObject1 = com.ihs.app.framework.b.a().getPackageManager().getInstalledPackages(128);
    Object localObject2 = paramClass.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      HSAppRunningInfo localHSAppRunningInfo2 = (HSAppRunningInfo)((Iterator)localObject2).next();
      Iterator localIterator = ((List)localObject1).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (localHSAppRunningInfo2.getPackageName().equals(localPackageInfo.packageName)) {
          localHSAppRunningInfo2.setApplicationInfoFlag(localPackageInfo.applicationInfo.flags);
        }
      }
      if ((localHSAppRunningInfo2.getApplicationInfoFlag() & 0x200000) != 0) {
        ((Iterator)localObject2).remove();
      }
    }
    localObject1 = new HashSet();
    try
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("content://");
      ((StringBuilder)localObject2).append(com.ihs.app.framework.b.a().getPackageName());
      ((StringBuilder)localObject2).append(".recent_app");
      ((StringBuilder)localObject2).append("/");
      localObject2 = com.ihs.commons.g.c.a(Uri.parse(((StringBuilder)localObject2).toString()), "METHOD_GET_RECENT_APP_LIST", null, null).getStringArrayList("EXTRA_RECENT_APP_LIST");
      if (localObject2 != null) {
        ((Set)localObject1).addAll((Collection)localObject2);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    paramHSAppFilter.c();
    paramClass = paramClass.iterator();
    while (paramClass.hasNext())
    {
      HSAppRunningInfo localHSAppRunningInfo1 = (HSAppRunningInfo)paramClass.next();
      localHSAppRunningInfo1.a(new int[0]);
      localHSAppRunningInfo1.setIsMusicPlayer(b.a(localHSAppRunningInfo1));
      localHSAppRunningInfo1.setIsRecentApp(((Set)localObject1).contains(localHSAppRunningInfo1.getPackageName()));
      if (paramHSAppFilter.a(localHSAppRunningInfo1)) {
        localArrayList.add(localHSAppRunningInfo1);
      }
    }
    return localArrayList;
  }
  
  private static <T extends HSAppRunningInfo> List<T> c(Class<T> paramClass, HSAppFilter paramHSAppFilter)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = (ActivityManager)com.ihs.app.framework.b.a().getSystemService("activity");
    if (localObject1 == null) {
      return localArrayList;
    }
    Object localObject2 = ((ActivityManager)localObject1).getRunningAppProcesses();
    if (localObject2 != null)
    {
      if (((List)localObject2).size() == 0) {
        return localArrayList;
      }
      localObject1 = new HashMap();
      localObject2 = ((List)localObject2).iterator();
      Object localObject3;
      Object localObject5;
      Object localObject6;
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
        localObject5 = localObject3.processName.split(":")[0];
        if (((Map)localObject1).get(localObject5) == null)
        {
          localObject6 = new ArrayList();
          ((List)localObject6).add(localObject3);
          ((Map)localObject1).put(localObject5, localObject6);
        }
        else
        {
          ((List)((Map)localObject1).get(localObject5)).add(localObject3);
        }
      }
      localObject2 = new HashSet();
      try
      {
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("content://");
        ((StringBuilder)localObject3).append(com.ihs.app.framework.b.a().getPackageName());
        ((StringBuilder)localObject3).append(".recent_app");
        ((StringBuilder)localObject3).append("/");
        localObject3 = com.ihs.commons.g.c.a(Uri.parse(((StringBuilder)localObject3).toString()), "METHOD_GET_RECENT_APP_LIST", null, null).getStringArrayList("EXTRA_RECENT_APP_LIST");
        if (localObject3 != null) {
          ((Set)localObject2).addAll((Collection)localObject3);
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      Object localObject4 = com.ihs.device.common.b.a(paramClass, new HSAppFilter().e());
      paramClass = new HashMap();
      localObject4 = ((List)localObject4).iterator();
      while (((Iterator)localObject4).hasNext())
      {
        localObject5 = (HSAppRunningInfo)((Iterator)localObject4).next();
        paramClass.put(((HSAppRunningInfo)localObject5).getPackageName(), localObject5);
      }
      localObject4 = ((Map)localObject1).keySet().iterator();
      while (((Iterator)localObject4).hasNext())
      {
        localObject5 = (String)((Iterator)localObject4).next();
        localObject6 = (HSAppRunningInfo)paramClass.get(localObject5);
        if (localObject6 != null)
        {
          Object localObject7 = (List)((Map)localObject1).get(localObject5);
          SparseIntArray localSparseIntArray = new SparseIntArray();
          localObject7 = ((List)localObject7).iterator();
          while (((Iterator)localObject7).hasNext())
          {
            ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject7).next();
            if (localRunningAppProcessInfo.processName.equalsIgnoreCase((String)localObject5)) {
              ((HSAppRunningInfo)localObject6).a(localRunningAppProcessInfo.pid);
            }
            localSparseIntArray.put(localRunningAppProcessInfo.pid, localRunningAppProcessInfo.pid);
            ((HSAppRunningInfo)localObject6).a.add(localRunningAppProcessInfo.importanceReasonComponent);
          }
          ((HSAppRunningInfo)localObject6).a(c.a(localSparseIntArray));
          ((HSAppRunningInfo)localObject6).setIsMusicPlayer(b.a((HSAppRunningInfo)localObject6));
          ((HSAppRunningInfo)localObject6).setIsRecentApp(((Set)localObject2).contains(((HSAppRunningInfo)localObject6).getPackageName()));
          if (paramHSAppFilter.a((HSAppFilter.a)localObject6)) {
            localArrayList.add(localObject6);
          }
        }
      }
      return localArrayList;
    }
    return localArrayList;
  }
  
  private static <T extends HSAppRunningInfo> List<T> d(Class<T> paramClass, HSAppFilter paramHSAppFilter)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = (ActivityManager)com.ihs.app.framework.b.a().getSystemService("activity");
    if (localObject1 == null) {
      return localArrayList;
    }
    Object localObject2 = ((ActivityManager)localObject1).getRunningServices(Integer.MAX_VALUE);
    if (localObject2 != null)
    {
      if (((List)localObject2).size() == 0) {
        return localArrayList;
      }
      localObject1 = new HashMap();
      localObject2 = ((List)localObject2).iterator();
      Object localObject3;
      Object localObject5;
      Object localObject6;
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (ActivityManager.RunningServiceInfo)((Iterator)localObject2).next();
        localObject5 = ((ActivityManager.RunningServiceInfo)localObject3).service.getPackageName();
        if (((Map)localObject1).get(localObject5) == null)
        {
          localObject6 = new ArrayList();
          ((List)localObject6).add(localObject3);
          ((Map)localObject1).put(localObject5, localObject6);
        }
        else
        {
          ((List)((Map)localObject1).get(localObject5)).add(localObject3);
        }
      }
      localObject2 = new HashSet();
      try
      {
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("content://");
        ((StringBuilder)localObject3).append(com.ihs.app.framework.b.a().getPackageName());
        ((StringBuilder)localObject3).append(".recent_app");
        ((StringBuilder)localObject3).append("/");
        localObject3 = com.ihs.commons.g.c.a(Uri.parse(((StringBuilder)localObject3).toString()), "METHOD_GET_RECENT_APP_LIST", null, null).getStringArrayList("EXTRA_RECENT_APP_LIST");
        if (localObject3 != null) {
          ((Set)localObject2).addAll((Collection)localObject3);
        }
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
      Object localObject4 = com.ihs.device.common.b.a(paramClass, new HSAppFilter().e());
      paramClass = new HashMap();
      localObject4 = ((List)localObject4).iterator();
      while (((Iterator)localObject4).hasNext())
      {
        localObject5 = (HSAppRunningInfo)((Iterator)localObject4).next();
        paramClass.put(((HSAppRunningInfo)localObject5).getPackageName(), localObject5);
      }
      localObject4 = ((Map)localObject1).keySet().iterator();
      while (((Iterator)localObject4).hasNext())
      {
        localObject5 = (String)((Iterator)localObject4).next();
        localObject6 = (HSAppRunningInfo)paramClass.get(localObject5);
        if ((localObject6 != null) && (!b.a(localObject6, localArrayList)))
        {
          Object localObject7 = (List)((Map)localObject1).get(localObject5);
          SparseIntArray localSparseIntArray = new SparseIntArray();
          localObject7 = ((List)localObject7).iterator();
          while (((Iterator)localObject7).hasNext())
          {
            ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)((Iterator)localObject7).next();
            try
            {
              if (localRunningServiceInfo.process.equalsIgnoreCase((String)localObject5)) {
                ((HSAppRunningInfo)localObject6).a(localRunningServiceInfo.pid);
              }
              localSparseIntArray.put(localRunningServiceInfo.pid, localRunningServiceInfo.pid);
              ((HSAppRunningInfo)localObject6).a.add(localRunningServiceInfo.service);
            }
            catch (Exception|Error localException2)
            {
              localException2.printStackTrace();
            }
          }
          ((HSAppRunningInfo)localObject6).a(c.a(localSparseIntArray));
          ((HSAppRunningInfo)localObject6).setIsMusicPlayer(b.a((HSAppRunningInfo)localObject6));
          ((HSAppRunningInfo)localObject6).setIsRecentApp(((Set)localObject2).contains(((HSAppRunningInfo)localObject6).getPackageName()));
          if (paramHSAppFilter.a((HSAppFilter.a)localObject6)) {
            localArrayList.add(localObject6);
          }
        }
      }
      if (Build.VERSION.SDK_INT < 24)
      {
        localObject1 = com.ihs.device.common.utils.processes.a.a(com.ihs.app.framework.b.a()).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject4 = (AndroidAppProcess)((Iterator)localObject1).next();
          localObject5 = (HSAppRunningInfo)paramClass.get(((AndroidAppProcess)localObject4).a());
          if (localObject5 != null)
          {
            if (((AndroidAppProcess)localObject4).c.equalsIgnoreCase(((HSAppRunningInfo)localObject5).getPackageName())) {
              ((HSAppRunningInfo)localObject5).a(((AndroidAppProcess)localObject4).d);
            }
            localObject6 = c.a(((HSAppRunningInfo)localObject5).b());
            ((SparseIntArray)localObject6).put(((AndroidAppProcess)localObject4).d, ((AndroidAppProcess)localObject4).d);
            ((HSAppRunningInfo)localObject5).a(c.a((SparseIntArray)localObject6));
            ((HSAppRunningInfo)localObject5).setIsMusicPlayer(b.a((HSAppRunningInfo)localObject5));
            ((HSAppRunningInfo)localObject5).setIsRecentApp(((Set)localObject2).contains(((HSAppRunningInfo)localObject5).getPackageName()));
            if (paramHSAppFilter.a((HSAppFilter.a)localObject5))
            {
              localArrayList.remove(localObject5);
              localArrayList.add(localObject5);
            }
          }
        }
      }
      return localArrayList;
    }
    return localArrayList;
  }
}
