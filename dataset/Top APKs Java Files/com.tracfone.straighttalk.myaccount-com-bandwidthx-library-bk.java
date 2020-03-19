package com.bandwidthx.library;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class bk
  implements ec.g
{
  private HashMap<Integer, String> a = new HashMap();
  
  public bk() {}
  
  private JSONArray a(ch paramCh)
  {
    for (;;)
    {
      try
      {
        localJSONArray = new JSONArray();
      }
      catch (Exception paramCh)
      {
        PackageManager localPackageManager;
        Iterator localIterator;
        Object localObject1;
        ApplicationInfo localApplicationInfo;
        PackageInfo localPackageInfo;
        continue;
      }
      try
      {
        localPackageManager = ch.e().getPackageManager();
        localIterator = localPackageManager.getInstalledApplications(0).iterator();
        localObject1 = localJSONArray;
        if (localIterator.hasNext())
        {
          localApplicationInfo = (ApplicationInfo)localIterator.next();
          localObject1 = (String)this.a.get(Integer.valueOf(localApplicationInfo.uid));
          if (localObject1 != null)
          {
            if (((String)localObject1).length() <= 0) {
              continue;
            }
            localJSONArray.put(localObject1);
            continue;
          }
          str = "";
        }
      }
      catch (Exception paramCh)
      {
        return localJSONArray;
      }
      try
      {
        paramCh.i();
        localPackageInfo = localPackageManager.getPackageInfo(ac.c(Integer.valueOf(localApplicationInfo.uid)), 2);
        localObject1 = str;
        if (localPackageInfo != null)
        {
          ActivityInfo[] arrayOfActivityInfo = localPackageInfo.receivers;
          localObject1 = str;
          if (arrayOfActivityInfo != null)
          {
            int j = arrayOfActivityInfo.length;
            int i = 0;
            localObject1 = str;
            if (i < j) {
              if (arrayOfActivityInfo[i].name.contains("BxReceiver"))
              {
                localObject1 = new StringBuilder();
                ((StringBuilder)localObject1).append(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
                ((StringBuilder)localObject1).append(" ");
                ((StringBuilder)localObject1).append(localPackageInfo.versionName);
                localObject1 = ((StringBuilder)localObject1).toString();
              }
              else
              {
                i += 1;
                continue;
              }
            }
          }
        }
      }
      catch (Exception localException)
      {
        Object localObject2 = str;
        continue;
      }
      this.a.put(Integer.valueOf(localApplicationInfo.uid), localObject1);
      if (((String)localObject1).length() > 0) {
        localJSONArray.put(localObject1);
      }
    }
    localObject1 = null;
    return localObject1;
  }
  
  public void a(ch paramCh, JSONObject paramJSONObject)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("man", Build.MANUFACTURER);
    localJSONObject.put("mod", Build.MODEL);
    localJSONObject.put("os", "Android");
    localJSONObject.put("ver", Build.VERSION.RELEASE);
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(ac.g());
    ((StringBuilder)localObject).append(" ");
    ((StringBuilder)localObject).append(ac.i());
    ((StringBuilder)localObject).append(".");
    ((StringBuilder)localObject).append(ac.h());
    ((StringBuilder)localObject).append(" ");
    ((StringBuilder)localObject).append(ac.j());
    localJSONObject.put("app", ((StringBuilder)localObject).toString().trim());
    localJSONObject.put("tok", paramCh.P().c());
    localObject = a(paramCh);
    if ((localObject != null) && (((JSONArray)localObject).length() > 1)) {
      localJSONObject.put("apps", localObject);
    }
    paramCh.i();
    localJSONObject.put("bld", ac.k());
    paramCh.i();
    localJSONObject.put("cod", ac.h());
    paramCh.i();
    localJSONObject.put("lib", ac.e());
    paramCh.i();
    localJSONObject.put("nam", ac.d());
    paramCh.i();
    localJSONObject.put("lcl", ac.r());
    localJSONObject.put("bat", paramCh.w().i());
    try
    {
      localJSONObject.put("c2d", paramCh.A().a());
      paramJSONObject.put("dev", localJSONObject);
      return;
    }
    catch (Throwable paramCh)
    {
      for (;;) {}
    }
  }
}
