package ua.com.tim_berners.a.a.a;

import android.annotation.SuppressLint;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStats.Bucket;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class c
{
  public final HashMap<Integer, b> a = new HashMap();
  
  public c(Context paramContext)
  {
    Object localObject1 = paramContext.getPackageManager().getInstalledApplications(0);
    HashMap localHashMap = new HashMap();
    try
    {
      localObject1 = ((List)localObject1).iterator();
      Object localObject3;
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (ApplicationInfo)((Iterator)localObject1).next();
        localHashMap.put(Integer.valueOf(((ApplicationInfo)localObject3).uid), ((ApplicationInfo)localObject3).packageName);
      }
      Object localObject2;
      long l1;
      long l2;
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      if (Build.VERSION.SDK_INT >= 23) {
        try
        {
          localObject2 = a(paramContext);
          localObject3 = Calendar.getInstance();
          ((Calendar)localObject3).set(5, 0);
          ((Calendar)localObject3).set(11, 0);
          ((Calendar)localObject3).clear(12);
          ((Calendar)localObject3).clear(13);
          ((Calendar)localObject3).clear(14);
          l1 = ((Calendar)localObject3).getTimeInMillis();
          l2 = System.currentTimeMillis();
          paramContext = (NetworkStatsManager)paramContext.getSystemService("netstats");
          if (paramContext == null) {
            return;
          }
          a(paramContext.queryDetails(0, (String)localObject2, l1, l2), localHashMap);
          a(paramContext.queryDetails(1, (String)localObject2, l1, l2), localHashMap);
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
      }
      paramContext = localHashMap.keySet().iterator();
      while (paramContext.hasNext())
      {
        localObject2 = (Integer)paramContext.next();
        this.a.put(localObject2, new b(((Integer)localObject2).intValue(), (String)localHashMap.get(localObject2)));
      }
    }
  }
  
  @SuppressLint({"HardwareIds"})
  private String a(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null)
      {
        paramContext = paramContext.getSubscriberId();
        return paramContext;
      }
    }
    catch (SecurityException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  private void a(NetworkStats paramNetworkStats, HashMap<Integer, String> paramHashMap)
  {
    if (Build.VERSION.SDK_INT < 23) {
      return;
    }
    if (paramNetworkStats != null) {}
    for (;;)
    {
      try
      {
        if (paramNetworkStats.hasNextBucket())
        {
          NetworkStats.Bucket localBucket = new NetworkStats.Bucket();
          paramNetworkStats.getNextBucket(localBucket);
          int i = localBucket.getUid();
          b localB = (b)this.a.get(Integer.valueOf(i));
          Object localObject = localB;
          if (localB == null)
          {
            localObject = (String)paramHashMap.get(Integer.valueOf(i));
            if (localObject == null)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("android.");
              ((StringBuilder)localObject).append(String.valueOf(i));
              localObject = ((StringBuilder)localObject).toString();
            }
            localObject = new b(i, (String)localObject);
            this.a.put(Integer.valueOf(i), localObject);
          }
          l1 = localBucket.getRxBytes();
          long l2 = 0L;
          l1 = Math.max(0L, l1);
          long l3 = Math.max(0L, localBucket.getTxBytes());
          long l4 = ((b)localObject).b;
          if (l1 > 0L)
          {
            l1 /= 1024L;
            ((b)localObject).b = (l4 + l1);
            l4 = ((b)localObject).a;
            l1 = l2;
            if (l3 > 0L) {
              l1 = l3 / 1024L;
            }
            ((b)localObject).a = (l4 + l1);
          }
        }
        else
        {
          return;
        }
      }
      catch (Exception paramNetworkStats)
      {
        paramNetworkStats.printStackTrace();
      }
      long l1 = 0L;
    }
  }
}
