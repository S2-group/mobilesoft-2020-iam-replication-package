package wonder.city.baseutility.utility.d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class e
{
  public static Map<String, a> a(Context paramContext, List<String> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return null;
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    HashMap localHashMap = new HashMap();
    paramList = paramList.iterator();
    for (;;)
    {
      Object localObject1;
      if (paramList.hasNext())
      {
        localObject1 = (String)paramList.next();
        if (((String)localObject1).equals(paramContext)) {}
      }
      else
      {
        try
        {
          localObject1 = localPackageManager.getPackageInfo((String)localObject1, 4096);
          Object localObject2 = ((PackageInfo)localObject1).requestedPermissions;
          if (localObject2 != null)
          {
            int j = localObject2.length;
            int i = 0;
            while (i < j)
            {
              if ("android.permission.INTERNET".equalsIgnoreCase(localObject2[i]))
              {
                long l = TrafficStats.getUidRxBytes(((PackageInfo)localObject1).applicationInfo.uid) + TrafficStats.getUidTxBytes(((PackageInfo)localObject1).applicationInfo.uid);
                if (l == 0L) {
                  break;
                }
                localObject2 = new a();
                ((a)localObject2).a = ((PackageInfo)localObject1).applicationInfo.name;
                ((a)localObject2).b = ((PackageInfo)localObject1).packageName;
                ((a)localObject2).c = l;
                localHashMap.put(((PackageInfo)localObject1).packageName, localObject2);
                break;
              }
              i += 1;
            }
            return localHashMap;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
      }
    }
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {}
    for (paramContext = null; (paramContext != null) && (paramContext.isAvailable()); paramContext = paramContext.getActiveNetworkInfo()) {
      return true;
    }
    return false;
  }
  
  public static int b(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (paramContext == null)
        {
          paramContext = null;
          if ((paramContext != null) && (paramContext.isAvailable())) {
            break;
          }
          return 0;
        }
      }
      catch (NullPointerException paramContext)
      {
        return 0;
      }
      paramContext = paramContext.getActiveNetworkInfo();
    }
    if (paramContext.getType() == 1) {
      return 1;
    }
    if (paramContext.getType() == 0)
    {
      int i = paramContext.getSubtype();
      if ((i == 4) || (i == 1) || (i == 2)) {
        return 2;
      }
      if ((i == 3) || (i == 8) || (i == 6) || (i == 5) || (i == 12)) {
        return 3;
      }
      if (i == 13) {
        return 4;
      }
      return 10;
    }
    return 100;
  }
  
  public static Map<String, a> c(Context paramContext)
  {
    Object localObject1 = paramContext.getPackageManager().getInstalledPackages(12288);
    HashMap localHashMap = new HashMap();
    paramContext = paramContext.getPackageName();
    localObject1 = ((List)localObject1).iterator();
    label200:
    while (((Iterator)localObject1).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject1).next();
      if ((localPackageInfo != null) && (!paramContext.equals(localPackageInfo.packageName)))
      {
        Object localObject2 = localPackageInfo.requestedPermissions;
        if (localObject2 != null)
        {
          int j = localObject2.length;
          int i = 0;
          for (;;)
          {
            if (i >= j) {
              break label200;
            }
            if ("android.permission.INTERNET".equalsIgnoreCase(localObject2[i]))
            {
              long l = TrafficStats.getUidRxBytes(localPackageInfo.applicationInfo.uid) + TrafficStats.getUidTxBytes(localPackageInfo.applicationInfo.uid);
              if (l == 0L) {
                break;
              }
              localObject2 = new a();
              ((a)localObject2).a = localPackageInfo.applicationInfo.name;
              ((a)localObject2).b = localPackageInfo.packageName;
              ((a)localObject2).c = l;
              localHashMap.put(localPackageInfo.packageName, localObject2);
              break;
            }
            i += 1;
          }
        }
      }
    }
    return localHashMap;
  }
}
