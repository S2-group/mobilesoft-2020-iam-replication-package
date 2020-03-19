package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.Base64;
import com.yandex.metrica.impl.utils.c;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
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
    throws JSONException
  {
    if (!paramJSONObject.has(paramString)) {
      paramJSONObject.put(paramString, paramT);
    }
    return paramJSONObject.get(paramString);
  }
  
  public u a()
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
  
  u a(Context paramContext)
    throws JSONException
  {
    Object localObject1 = (JSONObject)a((JSONObject)a(this.a, "dfid", new JSONObject()), "au", new JSONObject());
    JSONArray localJSONArray1 = (JSONArray)a((JSONObject)localObject1, "aun", new JSONArray());
    JSONArray localJSONArray2 = (JSONArray)a((JSONObject)localObject1, "ausf", new JSONArray());
    JSONArray localJSONArray3 = (JSONArray)a((JSONObject)localObject1, "audf", new JSONArray());
    JSONArray localJSONArray4 = (JSONArray)a((JSONObject)localObject1, "aulu", new JSONArray());
    ((JSONObject)localObject1).put("auv", 0);
    localObject1 = new HashSet();
    Object localObject3 = new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuYWN0aW9uLk1BSU4=", 0));
    Object localObject2 = new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuY2F0ZWdvcnkuTEFVTkNIRVI=", 0));
    localObject3 = new Intent((String)localObject3, null);
    ((Intent)localObject3).addCategory((String)localObject2);
    paramContext = paramContext.getPackageManager().queryIntentActivities((Intent)localObject3, 0).iterator();
    while (paramContext.hasNext())
    {
      localObject2 = ((ResolveInfo)paramContext.next()).activityInfo.applicationInfo;
      if (((HashSet)localObject1).add(((ApplicationInfo)localObject2).packageName))
      {
        localJSONArray1.put(((ApplicationInfo)localObject2).packageName);
        if ((((ApplicationInfo)localObject2).flags & 0x1) == 1)
        {
          bool = true;
          label257:
          localJSONArray2.put(bool);
          localJSONArray4.put(new File(((ApplicationInfo)localObject2).sourceDir).lastModified());
          if (((ApplicationInfo)localObject2).enabled) {
            break label310;
          }
        }
        label310:
        for (boolean bool = true;; bool = false)
        {
          localJSONArray3.put(bool);
          break;
          bool = false;
          break label257;
        }
      }
    }
    return this;
  }
  
  u b()
    throws JSONException
  {
    ((JSONObject)a(this.a, "dfid", new JSONObject())).put("boot_time", (System.currentTimeMillis() - SystemClock.elapsedRealtime()) / 1000L);
    return this;
  }
  
  u b(Context paramContext)
    throws JSONException
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
  
  u c()
    throws JSONException
  {
    JSONObject localJSONObject = (JSONObject)a(this.a, "dfid", new JSONObject());
    long l1 = bl.a(true);
    long l2 = bl.a(false);
    long l3 = bl.b(true);
    long l4 = bl.b(false);
    localJSONObject.put("tds", l1 + l2);
    localJSONObject.put("fds", l3 + l4);
    return this;
  }
  
  public String d()
  {
    return Base64.encodeToString(new c().a(this.a.toString().getBytes()), 0);
  }
  
  public String toString()
  {
    return this.a.toString();
  }
}
