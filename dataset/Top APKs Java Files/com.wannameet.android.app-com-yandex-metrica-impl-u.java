package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.SystemClock;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class u
{
  JSONObject a;
  
  public u(String paramString)
  {
    try
    {
      this.a = new JSONObject(paramString);
      return;
    }
    catch (Exception paramString)
    {
      this.a = new JSONObject();
    }
  }
  
  u(JSONObject paramJSONObject)
  {
    this(paramJSONObject.toString());
  }
  
  static <T> T a(JSONObject paramJSONObject, String paramString, T paramT)
  {
    if (!paramJSONObject.has(paramString)) {
      paramJSONObject.put(paramString, paramT);
    }
    return paramJSONObject.get(paramString);
  }
  
  u a()
  {
    ((JSONObject)a(this.a, "dfid", new JSONObject())).put("boot_time", (System.currentTimeMillis() - SystemClock.elapsedRealtime()) / 1000L);
    return this;
  }
  
  public u a(Context paramContext)
  {
    try
    {
      b();
      a();
      b(paramContext);
      return this;
    }
    catch (Exception paramContext) {}
    return this;
  }
  
  u b()
  {
    JSONObject localJSONObject = (JSONObject)a(this.a, "dfid", new JSONObject());
    long l1 = bi.a(true);
    long l2 = bi.a(false);
    long l3 = bi.b(true);
    long l4 = bi.b(false);
    localJSONObject.put("tds", l1 + l2);
    localJSONObject.put("fds", l3 + l4);
    return this;
  }
  
  u b(Context paramContext)
  {
    Object localObject = (JSONObject)a((JSONObject)a(this.a, "dfid", new JSONObject()), "apps", new JSONObject());
    JSONArray localJSONArray1 = (JSONArray)a((JSONObject)localObject, "names", new JSONArray());
    JSONArray localJSONArray2 = (JSONArray)a((JSONObject)localObject, "system_flags", new JSONArray());
    JSONArray localJSONArray3 = (JSONArray)a((JSONObject)localObject, "disabled_flags", new JSONArray());
    JSONArray localJSONArray4 = (JSONArray)a((JSONObject)localObject, "first_install_time", new JSONArray());
    JSONArray localJSONArray5 = (JSONArray)a((JSONObject)localObject, "last_update_time", new JSONArray());
    ((JSONObject)localObject).put("version", 0);
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    if (paramContext.hasNext())
    {
      localObject = (PackageInfo)paramContext.next();
      localJSONArray1.put(((PackageInfo)localObject).packageName);
      if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) == 1)
      {
        bool = true;
        label199:
        localJSONArray2.put(bool);
        if (((PackageInfo)localObject).applicationInfo.enabled) {
          break label272;
        }
      }
      label272:
      for (boolean bool = true;; bool = false)
      {
        localJSONArray3.put(bool);
        if (Build.VERSION.SDK_INT < 9) {
          break;
        }
        localJSONArray4.put(((PackageInfo)localObject).firstInstallTime / 1000L);
        localJSONArray5.put(((PackageInfo)localObject).lastUpdateTime / 1000L);
        break;
        bool = false;
        break label199;
      }
    }
    return this;
  }
  
  public String toString()
  {
    return this.a.toString();
  }
}
