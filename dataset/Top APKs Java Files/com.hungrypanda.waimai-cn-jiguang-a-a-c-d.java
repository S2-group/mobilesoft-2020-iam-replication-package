package cn.jiguang.a.a.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class d
{
  public static ArrayList a(Context paramContext, boolean paramBoolean)
  {
    localArrayList = new ArrayList();
    try
    {
      List localList = paramContext.getPackageManager().getInstalledPackages(0);
      int i = 0;
      for (;;)
      {
        int j = localList.size();
        if (i < j) {
          try
          {
            PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
            g localG = new g();
            localG.a = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
            localG.b = localPackageInfo.packageName;
            localG.c = localPackageInfo.versionName;
            localG.d = localPackageInfo.versionCode;
            localG.e = cn.jiguang.g.d.a(localPackageInfo.applicationInfo);
            localArrayList.add(localG);
            i += 1;
          }
          catch (Throwable localThrowable)
          {
            for (;;)
            {
              cn.jiguang.e.d.c("InstalledApplications", "get app info error:" + localThrowable.getMessage());
            }
          }
        }
      }
      return localArrayList;
    }
    catch (IndexOutOfBoundsException paramContext)
    {
      cn.jiguang.e.d.c("InstalledApplications", "getInstalledPackages IndexOutOfBoundsException errno");
      return localArrayList;
    }
    catch (Throwable paramContext)
    {
      cn.jiguang.e.d.c("InstalledApplications", "getInstalledPackages errno");
    }
  }
  
  public static String[] a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 4096).requestedPermissions;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      cn.jiguang.e.d.e("InstalledApplications", "", paramContext);
    }
    return null;
  }
  
  public static JSONArray b(Context paramContext)
  {
    Object localObject = a(paramContext, true);
    paramContext = new JSONArray();
    try
    {
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        g localG = (g)((Iterator)localObject).next();
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("name", localG.a);
        localJSONObject.put("pkg", localG.b);
        localJSONObject.put("ver_name", localG.c);
        localJSONObject.put("ver_code", localG.d);
        localJSONObject.put("install_type", localG.e);
        paramContext.put(localJSONObject);
      }
      return paramContext;
    }
    catch (Throwable localThrowable) {}
  }
}
