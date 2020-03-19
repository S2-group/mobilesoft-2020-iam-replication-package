package com.mady.wifi.datatransfer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class NetworkStats
{
  public NetworkStats() {}
  
  @SuppressLint({"UseSparseArrays"})
  public HashMap<Integer, DataUsage> getDataUsageApps(Context paramContext)
  {
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      localHashMap2.put(Integer.valueOf(localApplicationInfo.uid), localApplicationInfo.packageName);
      localHashMap1.put(Integer.valueOf(localApplicationInfo.uid), new DataUsage(localApplicationInfo.uid, (String)localHashMap2.get(Integer.valueOf(localApplicationInfo.uid))));
    }
    return localHashMap1;
  }
  
  public int getWifiDataUsageRx()
  {
    int i = (int)TrafficStats.getMobileRxBytes();
    return (int)(TrafficStats.getTotalRxBytes() - i);
  }
  
  public int getWifiDataUsageTx()
  {
    int i = (int)TrafficStats.getMobileTxBytes();
    return (int)(TrafficStats.getTotalTxBytes() - i);
  }
}
