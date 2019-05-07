import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class dle
{
  private static final String jdField_do = "dle";
  
  dle() {}
  
  private static Map<String, String> jdMethod_do(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getPackageManager().getInstalledPackages(128);
      paramContext = new HashMap();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (localPackageInfo.packageName != null) {
          if (localPackageInfo.versionName != null) {
            paramContext.put(localPackageInfo.packageName, localPackageInfo.versionName);
          } else {
            paramContext.put(localPackageInfo.packageName, "0");
          }
        }
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      dna.jdMethod_do(do, "Unable to get packages", paramContext);
    }
    return null;
  }
  
  static void jdMethod_do(Context paramContext, dkq paramDkq)
  {
    try
    {
      long l = paramContext.getSharedPreferences("placed_pg_prefs", 0).getLong("placed_pg_lpf", 0L);
      if (System.currentTimeMillis() - l > 86400000L)
      {
        Map localMap = jdMethod_do(paramContext);
        if (localMap == null) {
          return;
        }
        ArrayList localArrayList = new ArrayList(localMap.size());
        Iterator localIterator = localMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localArrayList.add(new dne(str, (String)localMap.get(str)));
        }
        paramDkq.jdMethod_do("packages", localArrayList);
        paramContext.getSharedPreferences("placed_pg_prefs", 0).edit().putLong("placed_pg_lpf", System.currentTimeMillis()).commit();
        return;
      }
    }
    catch (Exception paramContext)
    {
      dna.jdMethod_do(do, "Unable to log packages", paramContext);
    }
  }
}
