package com.tendcloud.tenddata;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class s
{
  public s() {}
  
  private static long a(String paramString)
  {
    long l = 1125899906842597L;
    int j = paramString.length();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return l;
      }
      l = l * 131L + paramString.charAt(i);
      i += 1;
    }
  }
  
  public static List a(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      if ((ag.a(paramContext, "android.permission.ACCESS_FINE_LOCATION")) || (ag.a(paramContext, "android.permission.ACCESS_COARSE_LOCATION")))
      {
        LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
        Iterator localIterator = localLocationManager.getProviders(true).iterator();
        for (;;)
        {
          if (!localIterator.hasNext()) {
            return localArrayList;
          }
          String str = (String)localIterator.next();
          Object localObject = localLocationManager.getLastKnownLocation(str);
          if (localObject != null) {
            localArrayList.add(localObject);
          }
          try
          {
            localObject = PendingIntent.getBroadcast(paramContext, 0, new Intent(), 0);
            localLocationManager.requestLocationUpdates(str, 300000L, 0.0F, (PendingIntent)localObject);
            localLocationManager.removeUpdates((PendingIntent)localObject);
          }
          catch (Exception localException) {}
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext) {}
  }
  
  public static Long[][] b(Context paramContext)
  {
    Object localObject3 = (ActivityManager)paramContext.getSystemService("activity");
    Object localObject2 = paramContext.getPackageManager();
    Object localObject1 = new HashSet();
    ((Set)localObject1).add(paramContext.getPackageName());
    HashSet localHashSet = new HashSet();
    if (ag.a(paramContext, "android.permission.GET_TASKS"))
    {
      paramContext = ((ActivityManager)localObject3).getRecentTasks(10, 1);
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        if (paramContext.hasNext()) {
          break label247;
        }
      }
    }
    localObject3 = ((ActivityManager)localObject3).getRunningAppProcesses();
    paramContext = new HashSet();
    if (localObject3 != null)
    {
      localObject3 = ((List)localObject3).iterator();
      label110:
      if (((Iterator)localObject3).hasNext()) {}
    }
    else
    {
      localObject3 = ((PackageManager)localObject2).getInstalledApplications(0);
      localObject2 = new HashSet();
      if (localObject3 != null) {
        localObject3 = ((List)localObject3).iterator();
      }
    }
    for (;;)
    {
      if (!((Iterator)localObject3).hasNext())
      {
        localObject1 = new Long[3][];
        localObject1[0] = new Long[localHashSet.size()];
        localObject1[0] = ((Long[])localHashSet.toArray(localObject1[0]));
        localObject1[1] = new Long[paramContext.size()];
        localObject1[1] = ((Long[])paramContext.toArray(localObject1[1]));
        localObject1[2] = new Long[((Set)localObject2).size()];
        localObject1[2] = ((Long[])((Set)localObject2).toArray(localObject1[2]));
        return localObject1;
        label247:
        localObject4 = ((ActivityManager.RecentTaskInfo)paramContext.next()).baseIntent.getComponent();
        if (localObject4 == null) {
          break;
        }
        localObject4 = ((ComponentName)localObject4).getPackageName();
        if (!((Set)localObject1).add(localObject4)) {
          break;
        }
        localHashSet.add(Long.valueOf(a((String)localObject4)));
        break;
        localObject4 = ((ActivityManager.RunningAppProcessInfo)((Iterator)localObject3).next()).processName;
        if (((PackageManager)localObject2).getLaunchIntentForPackage((String)localObject4) == null) {
          break label110;
        }
        paramContext.add(Long.valueOf(a((String)localObject4)));
        break label110;
      }
      Object localObject4 = (ApplicationInfo)((Iterator)localObject3).next();
      if (((((ApplicationInfo)localObject4).flags & 0x1) <= 0) && (!((Set)localObject1).contains(((ApplicationInfo)localObject4).packageName))) {
        ((Set)localObject2).add(Long.valueOf(a(((ApplicationInfo)localObject4).packageName)));
      }
    }
  }
  
  public static List c(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    ArrayList localArrayList = new ArrayList();
    Object localObject = paramContext.getInstalledApplications(0);
    if (localObject != null) {
      localObject = ((List)localObject).iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext()) {
        return localArrayList;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      localArrayList.add(localApplicationInfo.packageName);
      localArrayList.add(m.a(paramContext.getApplicationLabel(localApplicationInfo).toString().getBytes()));
      if ((localApplicationInfo.flags & 0x1) > 0) {
        localArrayList.add("1");
      } else {
        localArrayList.add("0");
      }
    }
  }
}
