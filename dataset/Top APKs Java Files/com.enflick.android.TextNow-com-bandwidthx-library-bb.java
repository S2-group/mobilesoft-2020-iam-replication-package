package com.bandwidthx.library;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class bb
  implements cy.f
{
  public bb() {}
  
  private static JSONArray a(bk paramBk)
  {
    try
    {
      paramBk = new JSONArray();
      PackageManager localPackageManager;
      Iterator localIterator;
      Object localObject;
      ActivityInfo[] arrayOfActivityInfo;
      int j;
      int i;
      label167:
      return paramBk;
    }
    catch (Exception paramBk)
    {
      try
      {
        localPackageManager = bk.e().getPackageManager();
        localIterator = localPackageManager.getInstalledApplications(0).iterator();
        if (localIterator.hasNext())
        {
          localObject = (ApplicationInfo)localIterator.next();
          for (;;)
          {
            try
            {
              bk.h();
              localObject = localPackageManager.getPackageInfo(aa.a(Integer.valueOf(((ApplicationInfo)localObject).uid)), 2);
              if (localObject == null) {
                break;
              }
              arrayOfActivityInfo = ((PackageInfo)localObject).receivers;
              if (arrayOfActivityInfo == null) {
                break;
              }
              j = arrayOfActivityInfo.length;
              i = 0;
              if (i >= j) {
                break;
              }
              if (!arrayOfActivityInfo[i].name.contains("BxReceiver")) {
                break label167;
              }
              paramBk.put(((PackageInfo)localObject).applicationInfo.loadLabel(localPackageManager).toString() + " " + ((PackageInfo)localObject).versionName);
            }
            catch (Exception localException2) {}
            break;
            i += 1;
          }
        }
        return paramBk;
      }
      catch (Exception localException1) {}
      paramBk = paramBk;
      return null;
    }
  }
  
  public final void a(bk paramBk, JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("man", Build.MANUFACTURER);
    localJSONObject.put("mod", Build.MODEL);
    localJSONObject.put("os", "Android");
    localJSONObject.put("ver", Build.VERSION.RELEASE);
    localJSONObject.put("app", (aa.e() + " " + aa.g() + "." + aa.f() + " " + aa.h()).trim());
    paramBk = a(paramBk);
    if ((paramBk != null) && (paramBk.length() > 1)) {
      localJSONObject.put("apps", paramBk);
    }
    bk.h();
    localJSONObject.put("bld", aa.i());
    bk.h();
    localJSONObject.put("cod", aa.f());
    bk.h();
    localJSONObject.put("lib", aa.d());
    bk.h();
    localJSONObject.put("nam", aa.c());
    bk.h();
    localJSONObject.put("lcl", aa.n());
    try
    {
      localJSONObject.put("c2d", bk.x().a());
      paramJSONObject.put("dev", localJSONObject);
      return;
    }
    catch (Exception paramBk)
    {
      for (;;) {}
    }
  }
}
