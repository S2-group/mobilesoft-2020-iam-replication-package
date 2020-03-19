package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.Base64;
import com.yandex.metrica.impl.utils.d;
import java.io.File;
import java.util.HashSet;
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
  
  static <T> T a(JSONObject paramJSONObject, String paramString, T paramT)
  {
    if (!paramJSONObject.has(paramString)) {
      paramJSONObject.put(paramString, paramT);
    }
    return paramJSONObject.get(paramString);
  }
  
  public final u a()
  {
    try
    {
      c();
      b();
      return this;
    }
    catch (Exception localException) {}
    return this;
  }
  
  final u a(Context paramContext)
  {
    Object localObject = (JSONObject)a((JSONObject)a(this.a, "dfid", new JSONObject()), "au", new JSONObject());
    JSONArray localJSONArray1 = (JSONArray)a((JSONObject)localObject, "aun", new JSONArray());
    JSONArray localJSONArray2 = (JSONArray)a((JSONObject)localObject, "ausf", new JSONArray());
    JSONArray localJSONArray3 = (JSONArray)a((JSONObject)localObject, "audf", new JSONArray());
    JSONArray localJSONArray4 = (JSONArray)a((JSONObject)localObject, "aulu", new JSONArray());
    ((JSONObject)localObject).put("auv", 0);
    localObject = new HashSet();
    paramContext = bk.a(paramContext, new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuYWN0aW9uLk1BSU4=", 0), "UTF-8"), new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuY2F0ZWdvcnkuTEFVTkNIRVI=", 0), "UTF-8")).iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = ((ResolveInfo)paramContext.next()).activityInfo.applicationInfo;
      if (((HashSet)localObject).add(localApplicationInfo.packageName))
      {
        localJSONArray1.put(localApplicationInfo.packageName);
        if ((localApplicationInfo.flags & 0x1) == 1)
        {
          bool = true;
          label231:
          localJSONArray2.put(bool);
          localJSONArray4.put(new File(localApplicationInfo.sourceDir).lastModified());
          if (localApplicationInfo.enabled) {
            break label284;
          }
        }
        label284:
        for (boolean bool = true;; bool = false)
        {
          localJSONArray3.put(bool);
          break;
          bool = false;
          break label231;
        }
      }
    }
    return this;
  }
  
  final u b()
  {
    ((JSONObject)a(this.a, "dfid", new JSONObject())).put("boot_time", (System.currentTimeMillis() - SystemClock.elapsedRealtime()) / 1000L);
    return this;
  }
  
  final u b(Context paramContext)
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
  
  final u c()
  {
    JSONObject localJSONObject = (JSONObject)a(this.a, "dfid", new JSONObject());
    long l1 = bk.a(true);
    long l2 = bk.a(false);
    long l3 = bk.b(true);
    long l4 = bk.b(false);
    localJSONObject.put("tds", l1 + l2);
    localJSONObject.put("fds", l3 + l4);
    return this;
  }
  
  public final String d()
  {
    return Base64.encodeToString(new d().a(bi.c(this.a.toString())), 0);
  }
  
  public final String toString()
  {
    return this.a.toString();
  }
}
